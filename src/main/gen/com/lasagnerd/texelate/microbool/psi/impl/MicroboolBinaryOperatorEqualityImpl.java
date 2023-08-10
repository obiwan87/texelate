// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.microbool.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.lasagnerd.texelate.microbool.psi.MicroboolTypes.*;
import com.lasagnerd.texelate.microbool.psi.*;

public class MicroboolBinaryOperatorEqualityImpl extends MicroboolBinaryOperatorImpl implements MicroboolBinaryOperatorEquality {

  public MicroboolBinaryOperatorEqualityImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull MicroboolVisitor visitor) {
    visitor.visitBinaryOperatorEquality(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MicroboolVisitor) accept((MicroboolVisitor)visitor);
    else super.accept(visitor);
  }

}
