package com.lasagnerd.texelate.refactoring;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.util.IncorrectOperationException;
import com.lasagnerd.texelate.microbool.psi.impl.MicroboolReferenceExpressionImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TexelateReferenceManipulator extends AbstractElementManipulator<MicroboolReferenceExpressionImpl> {
    @Override
    public @Nullable MicroboolReferenceExpressionImpl handleContentChange(@NotNull MicroboolReferenceExpressionImpl element, @NotNull TextRange range, String newContent) throws IncorrectOperationException {
        final String oldText = element.getText();
        String newText = oldText.substring(0, range.getStartOffset()) + newContent + oldText.substring(range.getEndOffset());
        PsiElement oldLeafElement =  element.getFirstChild();
        if(oldLeafElement instanceof LeafPsiElement leafElement) {
            leafElement.replaceWithText(newText);
        }
        return element;
    }
}
