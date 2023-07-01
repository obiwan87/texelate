// This is a generated file. Not intended for manual editing.
package com.lasagnerd.whack.preprocessor.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.lasagnerd.whack.preprocessor.lang.psi.PreprocessorTypes.*;
import com.lasagnerd.whack.preprocessor.lang.psi.*;

public class PreprocessorHashtagIfBlockImpl extends PreprocessorIfBlockImpl implements PreprocessorHashtagIfBlock {

  public PreprocessorHashtagIfBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull PreprocessorVisitor visitor) {
    visitor.visitHashtagIfBlock(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PreprocessorVisitor) accept((PreprocessorVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<PreprocessorIfBlock> getIfBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PreprocessorIfBlock.class);
  }

  @Override
  @NotNull
  public List<PreprocessorTextBlock> getTextBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PreprocessorTextBlock.class);
  }

  @Override
  @NotNull
  public PsiElement getHashtagClosingIf() {
    return findNotNullChildByType(HASHTAG_CLOSING_IF);
  }

  @Override
  @NotNull
  public PsiElement getHashtagOpeningIf() {
    return findNotNullChildByType(HASHTAG_OPENING_IF);
  }

  @Override
  public String getOpeningStatement() {
    return getHashtagOpeningIf().getText();
  }

  @Override
  public String getClosingStatement() {
    return getHashtagClosingIf().getText();
  }
}
