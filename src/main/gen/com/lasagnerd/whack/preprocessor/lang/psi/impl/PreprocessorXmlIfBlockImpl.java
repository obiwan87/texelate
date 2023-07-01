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

public class PreprocessorXmlIfBlockImpl extends PreprocessorIfBlockImpl implements PreprocessorXmlIfBlock {

  public PreprocessorXmlIfBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull PreprocessorVisitor visitor) {
    visitor.visitXmlIfBlock(this);
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
  public PsiElement getXmlClosingIf() {
    return findNotNullChildByType(XML_CLOSING_IF);
  }

  @Override
  @NotNull
  public PsiElement getXmlOpeningIf() {
    return findNotNullChildByType(XML_OPENING_IF);
  }

    @Override
    public String getOpeningStatement() {
        return getXmlOpeningIf().getText();
    }

    @Override
    public String getClosingStatement() {
        return getXmlClosingIf().getText();
    }
}
