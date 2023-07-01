package com.lasagnerd.whack.preprocessor.lang;

import com.intellij.lexer.FlexAdapter;

public class PreprocessorLexerAdapter extends FlexAdapter {
    public PreprocessorLexerAdapter() {
        super(new PreprocessorLexer());
    }
}
