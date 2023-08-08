package com.lasagnerd.texelate.microbool.psi;

import com.intellij.psi.tree.IElementType;
import com.lasagnerd.texelate.microbool.MicroboolLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class MicroboolElementType extends IElementType {
    public MicroboolElementType(@NonNls @NotNull String debugName) {
        super(debugName, MicroboolLanguage.INSTANCE);
    }
}
