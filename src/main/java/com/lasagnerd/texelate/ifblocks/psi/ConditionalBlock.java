package com.lasagnerd.texelate.ifblocks.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public interface ConditionalBlock extends PsiElement {
    PsiElement ifBranch();
    @Nullable
    PsiElement elseBranch();
}
