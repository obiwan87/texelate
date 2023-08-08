package com.lasagnerd.texelate.injection;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.lasagnerd.texelate.injection.IfBlocksFinder.IfBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class IfStatementFoldingBuilder extends FoldingBuilderEx implements DumbAware {

    @Override
    public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        List<FoldingDescriptor> descriptors = new ArrayList<>();

        List<IfBlock<PsiElement>> ifBlocks = IfBlocksFinder.findIfBlocks(root);

        for (IfBlock<PsiElement> ifBlock : ifBlocks) {
            PsiElement openingIf = ifBlock.getOpeningIf();
            PsiElement closingIf = ifBlock.getClosingIf();
            FoldingGroup group = FoldingGroup.newGroup("if-statement");
            TextRange textRange = new TextRange(openingIf.getTextRange().getEndOffset(),
                    closingIf.getTextRange().getEndOffset());
            FoldingDescriptor descriptor = new FoldingDescriptor(openingIf.getNode(),
                    textRange,
                    group);
            descriptors.add(descriptor);
        }

        return descriptors.toArray(new FoldingDescriptor[0]);
    }

    @Override
    public @Nullable String getPlaceholderText(@NotNull ASTNode node) {
        return "< ... >";
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return false;
    }
}
