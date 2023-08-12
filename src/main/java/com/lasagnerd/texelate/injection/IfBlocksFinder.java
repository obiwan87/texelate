package com.lasagnerd.texelate.injection;

import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class IfBlocksFinder {
    public record IfBlock<T>(T openingIf, T closingIf) {
    }

    public static List<IfBlock<PsiElement>> findIfBlocks(PsiElement root) {
        Collection<PsiComment> comments = PsiTreeUtil.findChildrenOfType(root, PsiComment.class);
        List<IfBlock<PsiElement>> ifBlocks = new ArrayList<>();
        Stack<PsiComment> psiCommentStack = new Stack<>();
        for (PsiComment comment : comments) {
            if (IfStatementTokenPatterns.OPENING_IF_STATEMENT_PREFIX.matcher(comment.getText()).find()) {
                psiCommentStack.push(comment);
            }

            if (IfStatementTokenPatterns.CLOSING_IF_STATEMENT_PREFIX.matcher(comment.getText()).find()) {
                if (!psiCommentStack.empty()) {
                    PsiComment lastOpeningComment = psiCommentStack.pop();
                    ifBlocks.add(new IfBlock<>(lastOpeningComment, comment));
                }
            }
        }

        return ifBlocks;
    }
}
