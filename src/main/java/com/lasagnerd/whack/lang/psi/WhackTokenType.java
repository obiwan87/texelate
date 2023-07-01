package com.lasagnerd.whack.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.lasagnerd.whack.lang.WhackLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class WhackTokenType extends IElementType {
    public WhackTokenType(@NonNls @NotNull String debugName) {
        super(debugName, WhackLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return WhackTokenType.class.getSimpleName() + "." + super.toString();
    }
}
