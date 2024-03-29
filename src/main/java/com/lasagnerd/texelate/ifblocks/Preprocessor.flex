package com.lasagnerd.texelate.ifblocks;

import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.FlexLexer;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.lasagnerd.texelate.ifblocks.psi.PreprocessorTypes.*;

%%

%{
  public PreprocessorLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class PreprocessorLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
//WHITE_SPACE=\s+

WHITE_SPACE=[ \t\n\x0B\f\r]+
XML_OPENING_IF=<\!--\?if[^\r\n]*-->
XML_ELSE=<\!--\?else[^\r\n]*-->
XML_CLOSING_IF=<\!--\?endif[^\r\n]*-->
HASHTAG_OPENING_IF=#\?if[^\n\r]*
HASHTAG_ELSE=#\?else[^\n\r]*
HASHTAG_CLOSING_IF=#\?endif[^\n\r]*
SEMICOLON_OPENING_IF=;\?if[^\n\r]*
SEMICOLON_ELSE=;\?else[^\n\r]*
SEMICOLON_CLOSING_IF=;\?endif[^\n\r]*
ANY=.+

%%
<YYINITIAL> {

  {WHITE_SPACE}               { return WHITE_SPACE; }
  {XML_OPENING_IF}            { return XML_OPENING_IF; }
  {XML_CLOSING_IF}            { return XML_CLOSING_IF; }
  {HASHTAG_OPENING_IF}        { return HASHTAG_OPENING_IF; }
  {HASHTAG_CLOSING_IF}        { return HASHTAG_CLOSING_IF; }
  {SEMICOLON_OPENING_IF}      { return SEMICOLON_OPENING_IF; }
  {SEMICOLON_CLOSING_IF}      { return SEMICOLON_CLOSING_IF; }
  {XML_ELSE}                  { return XML_ELSE; }
  {HASHTAG_ELSE}              { return HASHTAG_ELSE; }
  {SEMICOLON_ELSE}            { return SEMICOLON_ELSE; }
  {ANY}                       { return ANY; }

}

[^] { return BAD_CHARACTER; }
