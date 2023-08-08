// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.microbool.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class WhackVisitor extends PsiElementVisitor {

  public void visitBinaryExpression(@NotNull WhackBinaryExpression o) {
    visitExpression(o);
  }

  public void visitBinaryOperator(@NotNull WhackBinaryOperator o) {
    visitPsiElement(o);
  }

  public void visitBinaryOperatorAnd(@NotNull WhackBinaryOperatorAnd o) {
    visitBinaryOperator(o);
  }

  public void visitBinaryOperatorEquality(@NotNull WhackBinaryOperatorEquality o) {
    visitBinaryOperator(o);
  }

  public void visitBinaryOperatorInequality(@NotNull WhackBinaryOperatorInequality o) {
    visitBinaryOperator(o);
  }

  public void visitBinaryOperatorOr(@NotNull WhackBinaryOperatorOr o) {
    visitBinaryOperator(o);
  }

  public void visitBoolLiteralExpression(@NotNull WhackBoolLiteralExpression o) {
    visitExpression(o);
  }

  public void visitExpression(@NotNull WhackExpression o) {
    visitPsiElement(o);
  }

  public void visitReferenceExpression(@NotNull WhackReferenceExpression o) {
    visitExpression(o);
  }

  public void visitUnaryExpression(@NotNull WhackUnaryExpression o) {
    visitExpression(o);
  }

  public void visitUnaryOperator(@NotNull WhackUnaryOperator o) {
    visitPsiElement(o);
  }

  public void visitUnaryOperatorNot(@NotNull WhackUnaryOperatorNot o) {
    visitUnaryOperator(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
