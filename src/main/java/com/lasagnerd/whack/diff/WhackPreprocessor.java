package com.lasagnerd.whack.diff;

import com.intellij.lang.Language;
import com.intellij.lang.properties.psi.Property;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.lasagnerd.whack.completion.PropertiesUtils;
import com.lasagnerd.whack.injection.IfStatementTokenPatterns;
import com.lasagnerd.whack.injection.IfStatementTokenPatterns.IfStatement;
import com.lasagnerd.whack.lang.WhackLanguage;
import com.lasagnerd.whack.lang.psi.WhackFile;
import com.lasagnerd.whack.preprocessor.lang.psi.PreprocessorIfBlock;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static com.intellij.lang.Language.findLanguageByID;

public class WhackPreprocessor {
    public static CharSequence preprocess(String environment, String text, PsiElement root) {

        Language preprocessor = Objects.requireNonNull(findLanguageByID("Preprocessor"));
        PsiFile psiFile = PsiFileFactory
                .getInstance(root.getProject())
                .createFileFromText(preprocessor, text);
        try {
            return Interpreter.interpret(environment, psiFile, text);
        } catch (RuntimeException e) {
            return text;
        }
    }


    private static class Interpreter {
        private static String interpret(String environment, PsiFile psiFile, String text) {
            Interpreter interpreter = new Interpreter(environment, psiFile.getProject(), text);
            for (PsiElement child : psiFile.getChildren()) {
                interpreter.processChild(child);
            }

            return interpreter.text.toString();
        }
        StringBuilder text = new StringBuilder();
        private final String originalText;
        private final String environment;
        private final @NotNull Project project;
        Map<String, Object> variables = new HashMap<>();

        int lastOffset = 0;

        private Interpreter(String environment, @NotNull Project project, String originalText) {
            this.environment = environment;
            this.project = project;
            this.originalText = originalText;
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

                Optional<IfStatement> ifStatementOptional = IfStatementTokenPatterns.parseXmlCommentOpeningIfStatement(openingIfStatementText);
                if (ifStatementOptional.isPresent()) {
                    IfStatement ifStatement = ifStatementOptional.get();
                    String expression = ifStatement.getExpression().trim();

                    PsiFile whackPsiFile = PsiFileFactory.getInstance(project).createFileFromText(WhackLanguage.INSTANCE, expression);
                    if (whackPsiFile instanceof WhackFile) {
                        boolean interpretedValue = WhackInterpreter.interpret(variables, (WhackFile) whackPsiFile);
                        System.out.println("Interpreted value: " + interpretedValue);
                        System.out.println("Expression: " + expression);
                        if (interpretedValue) {
                            for (PsiElement child : preprocessorIfBlock.getChildren()) {
                                processChild(child);
                            }
                        }
                    } else {
                        throw new IllegalStateException(String.format("'%s' in if statement valid Whack Expression", expression));
                    }
                }
            } else {
                text.append(psiElement.getText());
            }
        }
    }
}
