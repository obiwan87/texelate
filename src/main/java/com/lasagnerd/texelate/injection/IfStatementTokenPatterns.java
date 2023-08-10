package com.lasagnerd.texelate.injection;

import com.intellij.openapi.util.TextRange;
import lombok.Value;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IfStatementTokenPatterns {
    public static final Pattern XML_OPENING_IF_STATEMENT_PATTERN = Pattern.compile("^(.*\\?if\\s+)(.*?)(-->)");
    public static final Pattern XML_EXCLUDE_STATEMENT_PREFIX = Pattern.compile("^(.*\\?exclude\\s+)(.*?)(-->)");

    public static final Pattern GENERIC_OPENING_IF_STATEMENT_PATTERN = Pattern.compile("^(.*\\?if\\s+)(.*)");
    public static final Pattern OPENING_IF_STATEMENT_PREFIX = Pattern.compile(".*\\?if.*");
    public static final Pattern CLOSING_IF_STATEMENT_PREFIX = Pattern.compile(".*\\?endif.*");
    public static final Pattern ELSE_STATEMENT_PREFIX = Pattern.compile(".*\\?else.*");
    public static final Pattern EXCLUDE_STATEMENT_PREFIX = Pattern.compile(".*\\?exclude.*");

    @Value
    public static class IfStatement {
        String prefix;
        String expression;
        String suffix;

        TextRange prefixTextRange() {
            return TextRange.from(0, prefix.length());
        }

        TextRange expressionTextRange() {
            return TextRange.from(prefix.length(), expression.length());
        }
    }

    public static Optional<IfStatement> parseXmlCommentOpeningIfStatement(String xmlComment) {
        Matcher matcher = XML_OPENING_IF_STATEMENT_PATTERN.matcher(xmlComment);
        if(matcher.find()) {
            String prefix = matcher.group(1);
            String expression = matcher.group(2);
            String suffix = matcher.group(3);

            return Optional.of(new IfStatement(prefix, expression, suffix));
        }
        return Optional.empty();
    }

    public static Optional<IfStatement> parseOpeningIfStatement(String line) {
        Matcher matcher = GENERIC_OPENING_IF_STATEMENT_PATTERN.matcher(line);
        if(matcher.find()) {
            String prefix = matcher.group(1);
            String expression = matcher.group(2);

            return Optional.of(new IfStatement(prefix, expression, ""));
        }
        return Optional.empty();
    }
}
