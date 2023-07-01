package com.lasagnerd.whack.lang;

import com.intellij.lang.Language;

public class WhackLanguage extends Language {
    public static final Language INSTANCE = new WhackLanguage();

    protected WhackLanguage() {
        super("Whack");
    }
}
