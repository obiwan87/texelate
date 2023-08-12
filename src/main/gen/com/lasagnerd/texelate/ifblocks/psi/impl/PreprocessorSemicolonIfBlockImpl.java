// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.ifblocks.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.lasagnerd.texelate.ifblocks.psi.PreprocessorTypes.*;
import com.lasagnerd.texelate.ifblocks.psi.*;

public class PreprocessorSemicolonIfBlockImpl extends PreprocessorIfBlockImpl implements PreprocessorSemicolonIfBlock {

  public PreprocessorSemicolonIfBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull PreprocessorVisitor visitor) {
    visitor.visitSemicolonIfBlock(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PreprocessorVisitor) accept((PreprocessorVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<PreprocessorIfBlock> getIfBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PreprocessorIfBlock.class);
  }

  @Override
  @NotNull
  public List<PreprocessorIfElseBlock> getIfElseBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PreprocessorIfElseBlock.class);
  }

  @Override
  @NotNull
  public List<PreprocessorTextBlock> getTextBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PreprocessorTextBlock.class);
  }

  @Override
  @NotNull
  public PsiElement getSemicolonClosingIf() {
    return findNotNullChildByType(SEMICOLON_CLOSING_IF);
  }

  @Override
  @NotNull
  public PsiElement getSemicolonOpeningIf() {
    return findNotNullChildByType(SEMICOLON_OPENING_IF);
  }

}
