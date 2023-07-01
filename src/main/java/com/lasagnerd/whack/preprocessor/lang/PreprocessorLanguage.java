package com.lasagnerd.whack.preprocessor.lang;

import com.intellij.lang.Language;

public class PreprocessorLanguage extends Language {
    public static final Language INSTANCE = new PreprocessorLanguage();

    protected PreprocessorLanguage() {
        super("Preprocessor");
    }
}
