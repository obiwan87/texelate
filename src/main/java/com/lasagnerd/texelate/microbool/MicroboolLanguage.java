package com.lasagnerd.texelate.microbool;

import com.intellij.lang.Language;

public class MicroboolLanguage extends Language {
    public static final Language INSTANCE = new MicroboolLanguage();

    protected MicroboolLanguage() {
        super("Microbool");
    }
}
