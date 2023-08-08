package com.lasagnerd.texelate.ifblocks;

import com.intellij.lexer.FlexAdapter;

public class PreprocessorLexerAdapter extends FlexAdapter {
    public PreprocessorLexerAdapter() {
        super(new PreprocessorLexer());
    }
}
