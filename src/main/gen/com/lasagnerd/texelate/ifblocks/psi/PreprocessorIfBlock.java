// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.ifblocks.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import java.util.Map;

public interface PreprocessorIfBlock extends ConditionalBlock, PreprocessorEvaluableBlock {

  @NotNull String evaluate(Map<String, Object> symbols);

  @NotNull PsiElement ifBranch();

  PsiElement elseBranch();

}
