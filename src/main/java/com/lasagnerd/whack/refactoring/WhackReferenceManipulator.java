package com.lasagnerd.whack.refactoring;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.AbstractElementManipulator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.util.IncorrectOperationException;
import com.lasagnerd.whack.lang.psi.impl.WhackReferenceExpressionImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WhackReferenceManipulator extends AbstractElementManipulator<WhackReferenceExpressionImpl> {
    @Override
    public @Nullable WhackReferenceExpressionImpl handleContentChange(@NotNull WhackReferenceExpressionImpl element, @NotNull TextRange range, String newContent) throws IncorrectOperationException {
        final String oldText = element.getText();
        String newText = oldText.substring(0, range.getStartOffset()) + newContent + oldText.substring(range.getEndOffset());
        PsiElement oldLeafElement =  element.getFirstChild();
        if(oldLeafElement instanceof LeafPsiElement) {
            LeafPsiElement leafElement = (LeafPsiElement) oldLeafElement;
            leafElement.replaceWithText(newText);
        }
        return element;
    }
}
