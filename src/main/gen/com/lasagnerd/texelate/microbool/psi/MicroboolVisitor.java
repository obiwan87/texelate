// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.microbool.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class MicroboolVisitor extends PsiElementVisitor {

  public void visitBinaryExpression(@NotNull MicroboolBinaryExpression o) {
    visitExpression(o);
  }

  public void visitBinaryOperator(@NotNull MicroboolBinaryOperator o) {
    visitPsiElement(o);
  }

  public void visitBinaryOperatorAnd(@NotNull MicroboolBinaryOperatorAnd o) {
    visitBinaryOperator(o);
  }

  public void visitBinaryOperatorEquality(@NotNull MicroboolBinaryOperatorEquality o) {
    visitBinaryOperator(o);
  }

  public void visitBinaryOperatorInequality(@NotNull MicroboolBinaryOperatorInequality o) {
    visitBinaryOperator(o);
  }

  public void visitBinaryOperatorOr(@NotNull MicroboolBinaryOperatorOr o) {
    visitBinaryOperator(o);
  }

  public void visitBoolLiteralExpression(@NotNull MicroboolBoolLiteralExpression o) {
    visitExpression(o);
  }

  public void visitDecimalLiteralExpression(@NotNull MicroboolDecimalLiteralExpression o) {
    visitExpression(o);
  }

  public void visitExpression(@NotNull MicroboolExpression o) {
    visitEvaluableMicroboolExpression(o);
  }

  public void visitIntLiteralExpression(@NotNull MicroboolIntLiteralExpression o) {
    visitExpression(o);
  }

  public void visitReferenceExpression(@NotNull MicroboolReferenceExpression o) {
    visitExpression(o);
  }

  public void visitStringLiteralExpression(@NotNull MicroboolStringLiteralExpression o) {
    visitExpression(o);
  }

  public void visitUnaryExpression(@NotNull MicroboolUnaryExpression o) {
    visitExpression(o);
  }

  public void visitUnaryOperator(@NotNull MicroboolUnaryOperator o) {
    visitPsiElement(o);
  }

  public void visitUnaryOperatorNot(@NotNull MicroboolUnaryOperatorNot o) {
    visitUnaryOperator(o);
  }

  public void visitEvaluableMicroboolExpression(@NotNull EvaluableMicroboolExpression o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
