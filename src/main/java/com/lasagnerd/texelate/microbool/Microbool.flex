package com.lasagnerd.texelate.microbool;

import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.lasagnerd.texelate.microbool.psi.MicroboolTypes.*;
import com.intellij.lexer.FlexLexer;

%%

%{
  public MicroboolLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class MicroboolLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

SPACE=[ \t]+
BOOL=(True|False)
IDENTIFIER=[A-Za-z_][A-Za-z0-9_]*
STRING_LITERAL=\"([^"\\]|\\[tnbrf"'\\])*\"
INT_LITERAL=[0-9]+
DECIMAL_LITERAL=[0-9]+\.[0-9]+
%%
<YYINITIAL> {
  {WHITE_SPACE}      { return WHITE_SPACE; }

  "and"              { return OPERATOR_AND; }
  "or"               { return OPERATOR_OR; }
  "not"              { return OPERATOR_NOT; }
  "=="               { return OPERATOR_EQUALITY; }
  "!="               { return OPERATOR_INEQUALITY; }

  {STRING_LITERAL} { return STRING_LITERAL; }
  {SPACE}            { return SPACE; }
  {BOOL}             { return BOOL; }
  {IDENTIFIER}       { return IDENTIFIER; }

}

[^] { return BAD_CHARACTER; }
