package com.lasagnerd.texelate.diff;

import com.intellij.lang.Language;
import com.intellij.lang.properties.psi.Property;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.lasagnerd.texelate.completion.PropertiesUtils;
import com.lasagnerd.texelate.injection.IfStatementTokenPatterns;
import com.lasagnerd.texelate.injection.IfStatementTokenPatterns.IfStatement;
import com.lasagnerd.texelate.microbool.MicroboolLanguage;
import com.lasagnerd.texelate.microbool.psi.MicroboolExpression;
import com.lasagnerd.texelate.microbool.psi.MicroboolFile;
import com.lasagnerd.texelate.ifblocks.psi.PreprocessorIfBlock;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static com.intellij.lang.Language.findLanguageByID;
import static com.lasagnerd.texelate.microbool.psi.MicroboolPsiUtil.asBoolean;

public class TexelatePreprocessor {
    public static CharSequence preprocess(String environment, String text, PsiElement root) {

        Language preprocessor = Objects.requireNonNull(findLanguageByID("Preprocessor"));
        PsiFile psiFile = PsiFileFactory
                .getInstance(root.getProject())
                .createFileFromText(preprocessor, text);
        try {
            return Interpreter.interpret(environment, psiFile);
        } catch (RuntimeException e) {
            return text;
        }
    }

    public static boolean interpret(Map<String, Object> variables, MicroboolFile microboolFile) {
        if (microboolFile.getFirstChild() instanceof MicroboolExpression microboolExpression) {
            try {
                return asBoolean(microboolExpression.evaluate(variables));
            } catch (Exception e) {
                throw new RuntimeException("Interpretation failed", e);
            }
        }
        return false;
    }


    private static class Interpreter {
        private static String interpret(String environment, PsiFile psiFile) {
            Interpreter interpreter = new Interpreter(environment, psiFile.getProject());
            for (PsiElement child : psiFile.getChildren()) {
                interpreter.processChild(child);
            }

            return replaceVariables(interpreter.text, interpreter.variables);
        }

        StringBuilder text = new StringBuilder();
        private final String environment;
        private final @NotNull Project project;
        Map<String, Object> variables = new HashMap<>();

        private Interpreter(String environment, @NotNull Project project) {
            this.environment = environment;
            this.project = project;
            initializeVariables();
        }

        private void initializeVariables() {
            List<Property> projectProperties = PropertiesUtils.getProjectPropertiesForEnvironment(this.environment, project);
            for (Property projectProperty : projectProperties) {
                if (projectProperty.getKey() != null && projectProperty.getValue() != null) {
                    String variableName = projectProperty.getKey().trim();
                    Object value = interpretValue(projectProperty.getValue());
                    System.out.printf("[%s] %s = %s%n", environment, variableName, value);
                    variables.put(variableName, value);
                }
            }
        }

        private Object interpretValue(String value) {

            if (value.toLowerCase(Locale.ROOT).equals("true")) {
                return true;
            }

            if (value.toLowerCase(Locale.ROOT).equals("false")) {
                return false;
            }

            return value;
        }

        private void processChild(PsiElement psiElement) {
            if (psiElement instanceof PsiErrorElement) {
                throw new RuntimeException("Preprocessor code is not well-formed!");
            }

            if (psiElement instanceof PreprocessorIfBlock preprocessorIfBlock) {
                String openingIfStatementText = preprocessorIfBlock.getOpeningStatement();

                Optional<IfStatement> ifStatementOptional = IfStatementTokenPatterns.parseOpeningIfStatement(openingIfStatementText);
                if (ifStatementOptional.isPresent()) {
                    IfStatement ifStatement = ifStatementOptional.get();
                    String expression = ifStatement.getExpression().trim();

                    PsiFile microboolPsiFile = PsiFileFactory.getInstance(project).createFileFromText(MicroboolLanguage.INSTANCE, expression);
                    if (microboolPsiFile instanceof MicroboolFile microboolFile) {
                        boolean interpretedValue = TexelatePreprocessor.interpret(variables, microboolFile);
                        if (interpretedValue) {
                            for (PsiElement child : preprocessorIfBlock.getChildren()) {
                                processChild(child);
                            }
                        }
                    } else {
                        throw new IllegalStateException(String.format("'%s' in if statement valid Microbool Expression", expression));
                    }
                }
            } else {
                text.append(psiElement.getText());
            }
        }

        private static String replaceVariables(StringBuilder text, Map<String, Object> variables) {
            String currentText = text.toString();

            // Replaces strings in the for $variable or ${variable} with the value of the variable
            for (Map.Entry<String, Object> variable : variables.entrySet()) {
                String variableName = variable.getKey();
                // escape variableName for regex
                Object value = variable.getValue();
                String valueString = value.toString();
                String replacement = valueString.replaceAll("\\\\", "\\\\\\\\")
                        .replaceAll("\\$", "\\\\\\$");

                currentText = currentText.replaceAll("\\$\\{%s}".formatted(variableName), replacement);

                currentText = currentText
                        .replaceAll("\\$%s".formatted(variableName), replacement);
            }

            return currentText;
        }
    }
}
