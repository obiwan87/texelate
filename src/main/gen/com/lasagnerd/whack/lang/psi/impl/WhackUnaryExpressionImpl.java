// This is a generated file. Not intended for manual editing.
package com.lasagnerd.whack.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.lasagnerd.whack.lang.psi.WhackExpression;
import com.lasagnerd.whack.lang.psi.WhackUnaryExpression;
import com.lasagnerd.whack.lang.psi.WhackUnaryOperator;
import com.lasagnerd.whack.lang.psi.WhackVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class WhackUnaryExpressionImpl extends WhackExpressionImpl implements WhackUnaryExpression {

  public WhackUnaryExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull WhackVisitor visitor) {
    visitor.visitUnaryExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof WhackVisitor) accept((WhackVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public WhackExpression getExpression() {
    return findChildByClass(WhackExpression.class);
  }

  @Override
  @NotNull
  public WhackUnaryOperator getUnaryOperator() {
    return findNotNullChildByClass(WhackUnaryOperator.class);
  }

  @Override
  public boolean evaluate(Map<String, Object> variables) {
    if(getExpression() != null) {
      boolean evaluation = getExpression().evaluate(variables);

      if (getUnaryOperator().getText().equals("not")) {
        return !evaluation;
      }
      throw new IllegalStateException(String.format("Unknown unary operator '%s'", getText()));
    }
    throw new IllegalStateException("Unary operator doesn't an expression to operate on");
  }
}
