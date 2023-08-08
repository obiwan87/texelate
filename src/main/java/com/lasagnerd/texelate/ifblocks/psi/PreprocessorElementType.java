package com.lasagnerd.texelate.ifblocks.psi;

import com.intellij.psi.tree.IElementType;
import com.lasagnerd.texelate.ifblocks.PreprocessorLanguage;

public class PreprocessorElementType extends IElementType {
    public PreprocessorElementType(String name) {
        super(name, PreprocessorLanguage.INSTANCE);
    }
}
