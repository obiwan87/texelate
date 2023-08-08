package com.lasagnerd.texelate.microbool;

import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.lasagnerd.texelate.microbool.psi.WhackTypes.*;

%%

%{
  public WhackLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class WhackLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

SPACE=[ \t]+
BOOL=(True|False)
IDENTIFIER=[A-Za-z_][A-Za-z0-9_]*

%%
<YYINITIAL> {
  {WHITE_SPACE}      { return WHITE_SPACE; }

  "and"              { return OPERATOR_AND; }
  "or"               { return OPERATOR_OR; }
  "not"              { return OPERATOR_NOT; }
  "=="               { return OPERATOR_EQUALITY; }
  "!="               { return OPERATOR_INEQUALITY; }

  {SPACE}            { return SPACE; }
  {BOOL}             { return BOOL; }
  {IDENTIFIER}       { return IDENTIFIER; }

}

[^] { return BAD_CHARACTER; }
