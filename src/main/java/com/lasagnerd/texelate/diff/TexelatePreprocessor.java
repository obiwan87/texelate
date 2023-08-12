package com.lasagnerd.texelate.diff;

import com.intellij.lang.Language;
import com.intellij.lang.properties.psi.Property;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.lasagnerd.texelate.completion.PropertiesUtils;
import com.lasagnerd.texelate.ifblocks.PreprocessorFile;
import com.lasagnerd.texelate.ifblocks.psi.PreprocessorEvaluableBlock;
import com.lasagnerd.texelate.microbool.MicroboolLanguage;
import com.lasagnerd.texelate.microbool.psi.MicroboolExpression;
import com.lasagnerd.texelate.microbool.psi.MicroboolFile;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.Pattern;

import static com.intellij.lang.Language.findLanguageByID;
import static com.lasagnerd.texelate.microbool.psi.MicroboolPsiUtil.asBoolean;

public class TexelatePreprocessor {
    public static CharSequence preprocess(String environment, String text, PsiElement root) {

        Language preprocessor = Objects.requireNonNull(findLanguageByID("Preprocessor"));
        PreprocessorFile psiFile = (PreprocessorFile) PsiFileFactory
                .getInstance(root.getProject())
                .createFileFromText(preprocessor, text);
        try {
            return Interpreter.interpret(environment, psiFile);
        } catch (RuntimeException e) {
            return text;
        }
    }

    public static boolean evaluate(Map<String, Object> variables, MicroboolFile microboolFile) {
        if (microboolFile.getFirstChild() instanceof MicroboolExpression microboolExpression) {
            try {
                return asBoolean(microboolExpression.evaluate(variables));
            } catch (Exception e) {
                throw new RuntimeException("Interpretation failed", e);
            }
        }
        return false;
    }

    public static Object interpretValue(String value) {

        if (value.toLowerCase(Locale.ROOT).equals("true")) {
            return true;
        }

        if (value.toLowerCase(Locale.ROOT).equals("false")) {
            return false;
        }

        return value;
    }

    @NotNull
    private static String interpolateVariable(String variableName, Object value, String currentText) {
        // escape variableName for regex
        String valueString = value.toString();
        String replacement = valueString.replaceAll("\\\\", "\\\\\\\\")
                .replaceAll("\\$", "\\\\\\$");

        currentText = currentText.replaceAll("\\$\\{%s}".formatted(variableName), replacement);

        currentText = currentText
                .replaceAll("\\$%s".formatted(variableName), replacement);
        return currentText;
    }

    public static boolean evaluate(Project project, Map<String, Object> variables, String expression) {
        PsiFile microboolPsiFile = PsiFileFactory.getInstance(project).createFileFromText(MicroboolLanguage.INSTANCE, expression);
        if (microboolPsiFile instanceof MicroboolFile microboolFile) {
            return TexelatePreprocessor.evaluate(variables, microboolFile);
        } else {
            throw new IllegalStateException(String.format("'%s' in if statement valid Microbool Expression", expression));
        }
    }
    private static class VariablesExpander {
        Map<String, Object> expandedVariables = new HashMap<>();
        final Map<String, Object> variables;

        public static Map<String, Object> expandVariables(Map<String, Object> variables) {
            VariablesExpander variablesExpander = new VariablesExpander(variables);
            variablesExpander.expandVariables();
            return variablesExpander.expandedVariables;
        }

        private VariablesExpander(Map<String, Object> variables) {
            this.variables = variables;
        }

        public void expandVariables() {
            for (Map.Entry<String, Object> entry : variables.entrySet()) {
                expandVariable(entry.getKey(), new HashSet<>());
            }
        }

        public void expandVariable(String name, Set<String> visited) {
            if(expandedVariables.containsKey(name)) {
                return;
            }

            if(visited.contains(name)) {
                throw new RuntimeException("Circular dependency detected");
            }

            visited.add(name);

            Object value = variables.get(name);
            if(value == null) {
                expandedVariables.put(name, "");
                return;
            }

            String valueString = value.toString();
            List<String> referencedVariables = extractReferencedVariables(value.toString());
            for (String referencedVariable : referencedVariables) {
                expandVariable(referencedVariable, visited);
                valueString = interpolateVariable(referencedVariable, expandedVariables.get(referencedVariable), valueString);
            }
            expandedVariables.put(name, interpretValue(valueString));
        }

        public static List<String> extractReferencedVariables(String expression) {
            // Find all expressions in the form of ${variable}
            List<String> variables = new ArrayList<>();

            Pattern pattern = Pattern.compile("\\$\\{([^}]+)}");
            pattern.matcher(expression).results().forEach(matchResult -> {
                String variableName = matchResult.group(1);
                variables.add(variableName);
            });

            return variables;
        }
    }

    private static class Interpreter {
        private static String interpret(String environment, PreprocessorFile psiFile) {
            StringBuilder text = new StringBuilder();
            Interpreter interpreter = new Interpreter(environment, psiFile.getProject());
            for (PsiElement child : psiFile.getChildren()) {
                if (child instanceof PreprocessorEvaluableBlock block) {
                    text.append(block.evaluate(interpreter.variables));
                } else {
                    text.append(child.getText());
                }
            }

            return replaceVariables(text, VariablesExpander.expandVariables(interpreter.variables));
        }

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



        private static String replaceVariables(StringBuilder text, Map<String, Object> variables) {
            String currentText = text.toString();

            // Replaces strings in the for $variable or ${variable} with the value of the variable
            for (Map.Entry<String, Object> variable : variables.entrySet()) {
                currentText = interpolateVariable(variable.getKey(), variable.getValue(), currentText);
            }

            return currentText;
        }
    }
}
