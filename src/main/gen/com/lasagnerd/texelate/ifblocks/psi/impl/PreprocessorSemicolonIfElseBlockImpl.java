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

public class PreprocessorSemicolonIfElseBlockImpl extends PreprocessorIfElseBlockImpl implements PreprocessorSemicolonIfElseBlock {

  public PreprocessorSemicolonIfElseBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull PreprocessorVisitor visitor) {
    visitor.visitSemicolonIfElseBlock(this);
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
  public PsiElement getSemicolonClosingIf() {
    return findNotNullChildByType(SEMICOLON_CLOSING_IF);
  }

  @Override
  @NotNull
  public PsiElement getSemicolonElse() {
    return findNotNullChildByType(SEMICOLON_ELSE);
  }

  @Override
  @NotNull
  public PsiElement getSemicolonOpeningIf() {
    return findNotNullChildByType(SEMICOLON_OPENING_IF);
  }

}
