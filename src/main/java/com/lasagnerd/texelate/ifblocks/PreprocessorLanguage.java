package com.lasagnerd.texelate.ifblocks;

import com.intellij.lang.Language;

public class PreprocessorLanguage extends Language {
    public static final Language INSTANCE = new PreprocessorLanguage();

    protected PreprocessorLanguage() {
        super("Preprocessor");
    }
}
