package com.lasagnerd.texelate.microbool;

import com.intellij.lexer.FlexAdapter;

public class MicroboolLexerAdapter extends FlexAdapter {
    public MicroboolLexerAdapter() {
        super(new MicroboolLexer(null));
    }
}
