// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.ifblocks.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface PreprocessorHashtagIfBlock extends PreprocessorIfBlock {

  @NotNull
  List<PreprocessorIfBlock> getIfBlockList();

  @NotNull
  List<PreprocessorIfElseBlock> getIfElseBlockList();

  @NotNull
  List<PreprocessorTextBlock> getTextBlockList();

  @NotNull
  PsiElement getHashtagClosingIf();

  @NotNull
  PsiElement getHashtagOpeningIf();

}
