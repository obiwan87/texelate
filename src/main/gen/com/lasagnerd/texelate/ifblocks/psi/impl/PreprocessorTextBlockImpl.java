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

public class PreprocessorTextBlockImpl extends ASTWrapperPsiElement implements PreprocessorTextBlock {

  public PreprocessorTextBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PreprocessorVisitor visitor) {
    visitor.visitTextBlock(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PreprocessorVisitor) accept((PreprocessorVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public String evaluate(Map<String, Object> ignore) {
    return PreprocessorPsiUtil.evaluate(this, ignore);
  }

}
