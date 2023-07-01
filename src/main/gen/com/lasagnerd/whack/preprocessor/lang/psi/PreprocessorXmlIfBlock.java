// This is a generated file. Not intended for manual editing.
package com.lasagnerd.whack.preprocessor.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface PreprocessorXmlIfBlock extends PreprocessorIfBlock {

  @NotNull
  List<PreprocessorIfBlock> getIfBlockList();

  @NotNull
  List<PreprocessorTextBlock> getTextBlockList();

  @NotNull
  PsiElement getXmlClosingIf();

  @NotNull
  PsiElement getXmlOpeningIf();

}
