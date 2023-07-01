// This is a generated file. Not intended for manual editing.
package com.lasagnerd.whack.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface WhackBinaryExpression extends WhackExpression {

  @NotNull
  WhackBinaryOperator getBinaryOperator();

  @NotNull
  List<WhackExpression> getExpressionList();

}
