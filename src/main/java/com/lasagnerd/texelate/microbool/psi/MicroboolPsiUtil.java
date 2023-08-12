package com.lasagnerd.texelate.microbool.psi;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class MicroboolPsiUtil {
    public static MicroboolExpression getLeft(MicroboolBinaryExpression expression) {
        return expression.getExpressionList().get(0);
    }

    public static MicroboolExpression getRight(MicroboolBinaryExpression expression) {
        return expression.getExpressionList().get(1);
    }

    public static Object evaluate(MicroboolExpression expression, Map<String, Object> symbols) {
        if (expression instanceof MicroboolBinaryExpression bin) {
            return evaluateBinaryExpression(bin, symbols);
        } else if (expression instanceof MicroboolUnaryExpression un) {
            return evaluateUnaryExpression(un, symbols);
        } else if (expression instanceof MicroboolReferenceExpression) {
            return evaluateReferenceExpression((MicroboolReferenceExpression) expression, symbols);
        } else if (expression instanceof MicroboolBoolLiteralExpression b) {
            return b.getBool().getText().equalsIgnoreCase("true");
        } else if (expression instanceof MicroboolStringLiteralExpression s) {
            return s.getText();
        } else if (expression instanceof MicroboolIntLiteralExpression n) {
            return Integer.parseInt(n.getText());
        } else if (expression instanceof MicroboolDecimalLiteralExpression d) {
            return Double.parseDouble(d.getText());
        } else {
            throw new RuntimeException("Unknown expression type " + expression);
        }
    }

    private static Object evaluateUnaryExpression(MicroboolUnaryExpression un, Map<String, Object> symbols) {
        return !asBoolean(evaluate(un.getExpression(), symbols));
    }

    public static Object evaluateBinaryExpression(@NotNull MicroboolBinaryExpression expression, Map<String, Object> symbols) {
        MicroboolBinaryOperator operator = expression.getBinaryOperator();
        Object left = evaluate(getLeft(expression), symbols);
        Object right = evaluate(getRight(expression), symbols);
        return switch (operator.getText()) {
            case "&&" -> asBoolean(left) && asBoolean(right);
            case "||" -> asBoolean(left) || asBoolean(right);
            case "==" -> left.equals(right);
            case "!=" -> !left.equals(right);
            default -> throw new RuntimeException("Unknown operator " + operator.getText());
        };
    }

    public static Object evaluateReferenceExpression(@NotNull MicroboolReferenceExpression expression, @NotNull Map<String, Object> symbols) {
        String identifier = expression.getIdentifier().getText();
        return symbols.get(identifier);
    }

    public static boolean asBoolean(Object value) {
        if (value instanceof Boolean) {
            return (Boolean) value;
        } else if (value instanceof Number) {
            return ((Number) value).intValue() != 0;
        } else if (value instanceof String) {
            return !((String) value).isEmpty();
        } else {
            throw new RuntimeException("Cannot convert " + value + " to boolean");
        }
    }
}
