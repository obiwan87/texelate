package com.lasagnerd.texelate.injection;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlAttribute;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IfStatementAnnotator implements Annotator {

    public static class CommentBlockDefinition {
        String start;
        String end;

        public CommentBlockDefinition(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }

    static Map<String, CommentBlockDefinition> extensionToCommentBlockDefinition = new HashMap<>();

    static {
        CommentBlockDefinition xmlCommentBlockDefinition = new CommentBlockDefinition("<!--", "-->");
        CommentBlockDefinition javaCommentBlockDefinition = new CommentBlockDefinition("//", "");
        CommentBlockDefinition kotlinCommentBlockDefinition = new CommentBlockDefinition("//", "");
        CommentBlockDefinition propertiesCommentBlockDefinition = new CommentBlockDefinition("#", "");
        CommentBlockDefinition yamlCommentBlockDefinition = new CommentBlockDefinition("#", "");
        extensionToCommentBlockDefinition.put("xml", xmlCommentBlockDefinition);
        extensionToCommentBlockDefinition.put("java", javaCommentBlockDefinition);
        extensionToCommentBlockDefinition.put("kotlin", kotlinCommentBlockDefinition);
        extensionToCommentBlockDefinition.put("properties", propertiesCommentBlockDefinition);
        extensionToCommentBlockDefinition.put("yaml", yamlCommentBlockDefinition);
        extensionToCommentBlockDefinition.put("yml", yamlCommentBlockDefinition);
    }

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        // Ensure the Psi Element is an expression
        if (!(element instanceof PsiComment comment)) {
            return;
        }

        String extension = element.getContainingFile().getVirtualFile().getExtension();


        Pattern pattern = createPattern(extension);
        if (pattern == null) {
            return;
        }

        Matcher matcher = pattern.matcher(comment.getText());
        if(!matcher.find()) {
            return;
        }
        highlightMatch(element, holder, matcher);
    }

    private @Nullable Pattern createPattern(String extension) {
        CommentBlockDefinition commentBlockDefinition = extensionToCommentBlockDefinition.get(extension);
        if (commentBlockDefinition == null) {
            return null;
        }
        String start = commentBlockDefinition.start;
        String end = commentBlockDefinition.end;
        String keywordsRegex = "(\\?if|\\?endif|\\?else|\\?exclude)";
        if (end.isEmpty()) {
            return Pattern.compile(String.format("(%s)(%s)[^\\r\\n]*", start, keywordsRegex));
        }
        String regex = String.format("(%s)(%s)[^\\r\\n]*?(%s)", start, keywordsRegex, end);
        return Pattern.compile(regex);
    }

    private void highlightMatch(@NotNull PsiElement element, @NotNull AnnotationHolder holder, Matcher matcher) {
        // Define the text ranges (start is inclusive, end is exclusive)
        // "?if>"

        String prefix = matcher.group(1);
        String keywordMatch = matcher.group(2);


        TextRange keyword = getTextRangeForGroup(element, matcher, 2);
        // highlight "simple" prefix and ":" separator
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(keyword).textAttributes(DefaultLanguageHighlighterColors.KEYWORD).create();

        // Comment start
        {
            TextRange matchRange = TextRange.from(element.getTextRange().getStartOffset(), prefix.length());
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(matchRange).textAttributes(DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR)
                    .create();
        }

        // Commend End
        {
            if (matcher.groupCount() < 3) {
                return;
            }
            TextRange matchRange = getTextRangeForGroup(element, matcher, 3);
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(matchRange).textAttributes(DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR)
                    .create();
        }
    }

    @NotNull
    private static TextRange getTextRangeForGroup(@NotNull PsiElement element, Matcher matcher, int group) {
        return TextRange.from(element.getTextOffset() + matcher.start(group), matcher.group(group).length());
    }

}
