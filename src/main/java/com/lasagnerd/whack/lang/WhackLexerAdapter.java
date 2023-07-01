package com.lasagnerd.whack.lang;

import com.intellij.lexer.FlexAdapter;

public class WhackLexerAdapter extends FlexAdapter {
    public WhackLexerAdapter() {
        super(new WhackLexer(null));
    }
}
