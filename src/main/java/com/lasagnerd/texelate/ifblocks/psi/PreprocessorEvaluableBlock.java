package com.lasagnerd.texelate.ifblocks.psi;

import com.intellij.psi.PsiElement;

import java.util.Map;

public interface PreprocessorEvaluableBlock extends PsiElement {
    String evaluate(Map<String, Object> symbols);
}
