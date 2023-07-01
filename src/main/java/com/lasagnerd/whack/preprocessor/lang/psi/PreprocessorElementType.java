package com.lasagnerd.whack.preprocessor.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.lasagnerd.whack.preprocessor.lang.PreprocessorLanguage;

public class PreprocessorElementType extends IElementType {
    public PreprocessorElementType(String name) {
        super(name, PreprocessorLanguage.INSTANCE);
    }
}
