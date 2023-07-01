// This is a generated file. Not intended for manual editing.
package com.lasagnerd.whack.preprocessor.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.lasagnerd.whack.preprocessor.lang.psi.PreprocessorTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.lasagnerd.whack.preprocessor.lang.psi.*;

public abstract class PreprocessorIfBlockImpl extends ASTWrapperPsiElement implements PreprocessorIfBlock {

  public PreprocessorIfBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PreprocessorVisitor visitor) {
    visitor.visitIfBlock(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PreprocessorVisitor) accept((PreprocessorVisitor)visitor);
    else super.accept(visitor);
  }

}
