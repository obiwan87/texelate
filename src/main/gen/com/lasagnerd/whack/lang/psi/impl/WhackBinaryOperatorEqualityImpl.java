// This is a generated file. Not intended for manual editing.
package com.lasagnerd.whack.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.lasagnerd.whack.lang.psi.WhackTypes.*;
import com.lasagnerd.whack.lang.psi.*;

public class WhackBinaryOperatorEqualityImpl extends WhackBinaryOperatorImpl implements WhackBinaryOperatorEquality {

  public WhackBinaryOperatorEqualityImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull WhackVisitor visitor) {
    visitor.visitBinaryOperatorEquality(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof WhackVisitor) accept((WhackVisitor)visitor);
    else super.accept(visitor);
  }

}
