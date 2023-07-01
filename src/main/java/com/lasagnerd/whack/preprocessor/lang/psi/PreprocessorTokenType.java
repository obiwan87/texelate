package com.lasagnerd.whack.preprocessor.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.lasagnerd.whack.preprocessor.lang.PreprocessorLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class PreprocessorTokenType extends IElementType {
    public PreprocessorTokenType(@NonNls @NotNull String debugName) {
        super(debugName, PreprocessorLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return PreprocessorTokenType.class.getSimpleName() + "." + super.toString();
    }
}
