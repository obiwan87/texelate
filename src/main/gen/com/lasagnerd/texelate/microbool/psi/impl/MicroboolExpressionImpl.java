// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.microbool.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.lasagnerd.texelate.microbool.psi.MicroboolTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.lasagnerd.texelate.microbool.psi.*;
import java.util.Map;

public abstract class MicroboolExpressionImpl extends ASTWrapperPsiElement implements MicroboolExpression {

  public MicroboolExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MicroboolVisitor visitor) {
    visitor.visitExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MicroboolVisitor) accept((MicroboolVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public Object evaluate(Map<String, Object> symbols) {
    return MicroboolPsiUtil.evaluate(this, symbols);
  }

}
