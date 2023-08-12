// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.ifblocks.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.lasagnerd.texelate.ifblocks.psi.PreprocessorTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.lasagnerd.texelate.ifblocks.psi.*;
import java.util.Map;

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

  @Override
  public @NotNull String evaluate(Map<String, Object> symbols) {
    return PreprocessorPsiUtil.evaluate(this, symbols);
  }

  @Override
  public @NotNull PsiElement ifBranch() {
    return PreprocessorPsiUtil.ifBranch(this);
  }

  @Override
  public PsiElement elseBranch() {
    return PreprocessorPsiUtil.elseBranch(this);
  }

}
