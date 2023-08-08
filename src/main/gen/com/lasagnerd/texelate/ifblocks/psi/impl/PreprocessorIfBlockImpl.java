// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.ifblocks.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.lasagnerd.texelate.ifblocks.psi.PreprocessorIfBlock;
import com.lasagnerd.texelate.ifblocks.psi.PreprocessorVisitor;
import org.jetbrains.annotations.NotNull;

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
