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

public class PreprocessorHashtagIfElseBlockImpl extends PreprocessorIfElseBlockImpl implements PreprocessorHashtagIfElseBlock {

  public PreprocessorHashtagIfElseBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull PreprocessorVisitor visitor) {
    visitor.visitHashtagIfElseBlock(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PreprocessorVisitor) accept((PreprocessorVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PreprocessorElseBranch getElseBranch() {
    return findNotNullChildByClass(PreprocessorElseBranch.class);
  }

  @Override
  @NotNull
  public PreprocessorIfBranch getIfBranch() {
    return findNotNullChildByClass(PreprocessorIfBranch.class);
  }

  @Override
  @NotNull
  public PsiElement getHashtagClosingIf() {
    return findNotNullChildByType(HASHTAG_CLOSING_IF);
  }

  @Override
  @NotNull
  public PsiElement getHashtagElse() {
    return findNotNullChildByType(HASHTAG_ELSE);
  }

  @Override
  @NotNull
  public PsiElement getHashtagOpeningIf() {
    return findNotNullChildByType(HASHTAG_OPENING_IF);
  }

}
