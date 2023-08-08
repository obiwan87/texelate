// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.microbool.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.lasagnerd.texelate.microbool.psi.*;

public abstract class WhackBinaryOperatorImpl extends ASTWrapperPsiElement implements WhackBinaryOperator {

  public WhackBinaryOperatorImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull WhackVisitor visitor) {
    visitor.visitBinaryOperator(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof WhackVisitor) accept((WhackVisitor)visitor);
    else super.accept(visitor);
  }

}
