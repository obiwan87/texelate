// This is a generated file. Not intended for manual editing.
package com.lasagnerd.whack.preprocessor.lang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class PreprocessorVisitor extends PsiElementVisitor {

  public void visitHashtagIfBlock(@NotNull PreprocessorHashtagIfBlock o) {
    visitIfBlock(o);
  }

  public void visitIfBlock(@NotNull PreprocessorIfBlock o) {
    visitPsiElement(o);
  }

  public void visitSemicolonIfBlock(@NotNull PreprocessorSemicolonIfBlock o) {
    visitIfBlock(o);
  }

  public void visitTextBlock(@NotNull PreprocessorTextBlock o) {
    visitPsiElement(o);
  }

  public void visitXmlIfBlock(@NotNull PreprocessorXmlIfBlock o) {
    visitIfBlock(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
