// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.ifblocks.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface PreprocessorXmlIfElseBlock extends PreprocessorIfElseBlock {

  @NotNull
  PreprocessorElseBranch getElseBranch();

  @NotNull
  PreprocessorIfBranch getIfBranch();

  @NotNull
  PsiElement getXmlClosingIf();

  @NotNull
  PsiElement getXmlElse();

  @NotNull
  PsiElement getXmlOpeningIf();

}
