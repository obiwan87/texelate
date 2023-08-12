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

public class PreprocessorXmlIfElseBlockImpl extends PreprocessorIfElseBlockImpl implements PreprocessorXmlIfElseBlock {

  public PreprocessorXmlIfElseBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull PreprocessorVisitor visitor) {
    visitor.visitXmlIfElseBlock(this);
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
  public PsiElement getXmlClosingIf() {
    return findNotNullChildByType(XML_CLOSING_IF);
  }

  @Override
  @NotNull
  public PsiElement getXmlElse() {
    return findNotNullChildByType(XML_ELSE);
  }

  @Override
  @NotNull
  public PsiElement getXmlOpeningIf() {
    return findNotNullChildByType(XML_OPENING_IF);
  }

}
