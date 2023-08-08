package com.lasagnerd.texelate.microbool;


import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.lasagnerd.texelate.microbool.psi.WhackTypes;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import static com.lasagnerd.texelate.microbool.psi.WhackTypes.*;

public class MicroboolSyntaxHighlighter extends SyntaxHighlighterBase {


    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("WHACK_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("WHACK_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);

    public static final TextAttributesKey IDENTIFIER =
            createTextAttributesKey("WHACK_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);


    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[]{IDENTIFIER};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new MicroboolLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        }

        if (tokenType.equals(WhackTypes.IDENTIFIER)) {
            return IDENTIFIER_KEYS;
        }

        if(List.of(OPERATOR_AND, OPERATOR_OR, BOOL, OPERATOR_NOT).contains(tokenType)) {
            return KEYWORD_KEYS;
        }

        return EMPTY_KEYS;
    }

}
