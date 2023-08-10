package com.lasagnerd.texelate.microbool;

import com.intellij.psi.PsiElement;
import com.lasagnerd.texelate.microbool.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MicroboolInterpreter {

    public static boolean evaluate(@NotNull PsiElement element, Map<String, Object> variables) {
        Visitor visitor = new Visitor(variables);
        element.accept(visitor);
        return asBoolean(visitor.results.get(element));
    }

    private static boolean asBoolean(Object value) {
        if (value instanceof String s) {
            return !s.isEmpty();
        }
        if (value instanceof Boolean b) {
            return b;
        }
        if (value instanceof Number n) {
            return n.doubleValue() != 0;
        }

        return value != null;
    }

    private static class Visitor extends MicroboolVisitor {

        private final Map<String, Object> symbolTable;
        Map<Object, Object> results = new HashMap<>();

        private Visitor(Map<String, Object> symbolTable) {
            this.symbolTable = symbolTable;
        }

        @Override
        public void visitBinaryExpression(@NotNull MicroboolBinaryExpression o) {

            MicroboolExpression left = o.getExpressionList().get(0);
            MicroboolExpression right = o.getExpressionList().get(1);

            left.accept(this);
            right.accept(this);

            var leftResult = results.get(left);
            var rightResult = results.get(right);

            if (leftResult == null || rightResult == null) {
                throw new RuntimeException("Could not evaluate expression");
            }

            MicroboolBinaryOperator operator = o.getBinaryOperator();
            if (operator instanceof MicroboolBinaryOperatorAnd) {
                results.put(o, asBoolean(leftResult) && asBoolean(rightResult));
            } else if (operator instanceof MicroboolBinaryOperatorOr) {
                results.put(o, asBoolean(leftResult) || asBoolean(rightResult));
            } else if (operator instanceof MicroboolBinaryOperatorEquality) {
                results.put(o, leftResult.equals(rightResult));
            } else if (operator instanceof MicroboolBinaryOperatorInequality) {
                results.put(o, !leftResult.equals(rightResult));
            } else {
                throw new RuntimeException("Unknown operator");
            }
        }


        @Override
        public void visitBoolLiteralExpression(@NotNull MicroboolBoolLiteralExpression o) {
            results.put(o, o.getText().equalsIgnoreCase("true"));
        }

        @Override
        public void visitReferenceExpression(@NotNull MicroboolReferenceExpression o) {
            String text = o.getIdentifier().getText();
            Object value = symbolTable.get(text);
            results.put(o, value);
        }

        @Override
        public void visitUnaryExpression(@NotNull MicroboolUnaryExpression o) {
            if (o.getUnaryOperator() instanceof MicroboolUnaryOperatorNot) {
                MicroboolExpression expression = o.getExpression();
                if (expression == null) {
                    throw new RuntimeException("Could not evaluate expression");
                }
                expression.accept(this);
                boolean result = asBoolean(results.get(expression));
                results.put(o, !result);
            } else {
                throw new RuntimeException("Unknown operator");
            }
        }
    }
}
