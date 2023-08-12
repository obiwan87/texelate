// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.ifblocks.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface PreprocessorSemicolonIfElseBlock extends PreprocessorIfElseBlock {

  @NotNull
  PreprocessorElseBranch getElseBranch();

  @NotNull
  PreprocessorIfBranch getIfBranch();

  @NotNull
  PsiElement getSemicolonClosingIf();

  @NotNull
  PsiElement getSemicolonElse();

  @NotNull
  PsiElement getSemicolonOpeningIf();

}
