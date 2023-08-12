package com.lasagnerd.texelate.microbool.psi;

import com.intellij.psi.PsiElement;

import java.util.Map;

public interface EvaluableMicroboolExpression extends PsiElement {
    Object evaluate(Map<String, Object> symbols);

    void accept(MicroboolVisitor visitor);
}
