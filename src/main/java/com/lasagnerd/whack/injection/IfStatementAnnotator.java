package com.lasagnerd.whack.injection;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IfStatementAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        // Ensure the Psi Element is an expression
        if (!(element instanceof PsiComment)) {
            return;
        }

        // Ensure the Psi element contains a string that starts with the prefix and separator
        PsiComment comment = (PsiComment) element;
        String value = comment.getText();

        {
            Pattern pattern = Pattern.compile("^(.*)(\\?if)(.*?)(-->)");
            Matcher matcher = pattern.matcher(value);
            if (matcher.find()) {
                highlightOpeningIf(element, holder, matcher);
            }
        }

        {
            Pattern pattern = Pattern.compile("^(.*)(\\?endif).*");
            Matcher matcher = pattern.matcher(value);
            if (matcher.find()) {
                highlightClosingIf(element, holder, matcher);
            }

        }
    }

    private void highlightClosingIf(@NotNull PsiElement element, @NotNull AnnotationHolder holder, Matcher matcher) {
        String prefix = matcher.group(1);
        String endifStatement = matcher.group(2);
        TextRange endifStatementRange = TextRange.from(element.getTextRange().getStartOffset() + prefix.length(), endifStatement.length());
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(endifStatementRange).textAttributes(DefaultLanguageHighlighterColors.KEYWORD).create();

        highlightComment(holder, element, matcher.group(0));
    }

    private void highlightOpeningIf(@NotNull PsiElement element, @NotNull AnnotationHolder holder, Matcher matcher) {
        // Define the text ranges (start is inclusive, end is exclusive)
        // "?if>"

        String prefix = matcher.group(1);
        String ifStatement = matcher.group(2);

        TextRange ifStatementRange = TextRange.from(element.getTextRange().getStartOffset() + prefix.length(), ifStatement.length());
        // highlight "simple" prefix and ":" separator
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(ifStatementRange).textAttributes(DefaultLanguageHighlighterColors.KEYWORD).create();

        String match = matcher.group(0);
        highlightComment(holder, element, match);
    }

    private void highlightComment(@NotNull AnnotationHolder holder, @NotNull PsiElement element, String match) {
        TextRange matchRange = TextRange.from(element.getTextRange().getStartOffset(), match.length());
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(matchRange).textAttributes(DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR)
                .create();
    }

}
