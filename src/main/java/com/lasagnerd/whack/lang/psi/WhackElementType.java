package com.lasagnerd.whack.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.lasagnerd.whack.lang.WhackLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class WhackElementType extends IElementType {
    public WhackElementType(@NonNls @NotNull String debugName) {
        super(debugName, WhackLanguage.INSTANCE);
    }
}
