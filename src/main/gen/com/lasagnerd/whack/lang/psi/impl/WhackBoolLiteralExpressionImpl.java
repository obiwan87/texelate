// This is a generated file. Not intended for manual editing.
package com.lasagnerd.whack.lang.psi.impl;

import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.lasagnerd.whack.lang.psi.WhackTypes.*;
import com.lasagnerd.whack.lang.psi.*;

public class WhackBoolLiteralExpressionImpl extends WhackExpressionImpl implements WhackBoolLiteralExpression {

  public WhackBoolLiteralExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull WhackVisitor visitor) {
    visitor.visitBoolLiteralExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof WhackVisitor) accept((WhackVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getBool() {
    return findNotNullChildByType(BOOL);
  }

  @Override
  public boolean evaluate(Map<String, Object> variables) {
    String text = getText();

    if (text != null) {
      if (text.equals("True")) {
        return true;
      }

      if(text.equals("False")) {
        return false;
      }
    }
    throw new IllegalStateException(String.format("Illegal boolean literal '%s'", text));
  }
}
