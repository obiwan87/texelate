// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.ifblocks.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class PreprocessorVisitor extends PsiElementVisitor {

  public void visitElseBranch(@NotNull PreprocessorElseBranch o) {
    visitPsiElement(o);
  }

  public void visitHashtagIfBlock(@NotNull PreprocessorHashtagIfBlock o) {
    visitIfBlock(o);
  }

  public void visitHashtagIfElseBlock(@NotNull PreprocessorHashtagIfElseBlock o) {
    visitIfElseBlock(o);
  }

  public void visitIfBlock(@NotNull PreprocessorIfBlock o) {
    visitConditionalBlock(o);
    // visitEvaluableBlock(o);
  }

  public void visitIfBranch(@NotNull PreprocessorIfBranch o) {
    visitPsiElement(o);
  }

  public void visitIfElseBlock(@NotNull PreprocessorIfElseBlock o) {
    visitConditionalBlock(o);
    // visitEvaluableBlock(o);
  }

  public void visitSemicolonIfBlock(@NotNull PreprocessorSemicolonIfBlock o) {
    visitIfBlock(o);
  }

  public void visitSemicolonIfElseBlock(@NotNull PreprocessorSemicolonIfElseBlock o) {
    visitIfElseBlock(o);
  }

  public void visitTextBlock(@NotNull PreprocessorTextBlock o) {
    visitEvaluableBlock(o);
  }

  public void visitXmlIfBlock(@NotNull PreprocessorXmlIfBlock o) {
    visitIfBlock(o);
  }

  public void visitXmlIfElseBlock(@NotNull PreprocessorXmlIfElseBlock o) {
    visitIfElseBlock(o);
  }

  public void visitConditionalBlock(@NotNull ConditionalBlock o) {
    visitElement(o);
  }

  public void visitEvaluableBlock(@NotNull PreprocessorEvaluableBlock o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
