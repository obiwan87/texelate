// This is a generated file. Not intended for manual editing.
package com.lasagnerd.whack.preprocessor.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.lasagnerd.whack.preprocessor.lang.psi.impl.*;

public interface PreprocessorTypes {

  IElementType HASHTAG_IF_BLOCK = new PreprocessorElementType("HASHTAG_IF_BLOCK");
  IElementType IF_BLOCK = new PreprocessorElementType("IF_BLOCK");
  IElementType SEMICOLON_IF_BLOCK = new PreprocessorElementType("SEMICOLON_IF_BLOCK");
  IElementType TEXT_BLOCK = new PreprocessorElementType("TEXT_BLOCK");
  IElementType XML_IF_BLOCK = new PreprocessorElementType("XML_IF_BLOCK");

  IElementType ANY = new PreprocessorTokenType("ANY");
  IElementType HASHTAG_CLOSING_IF = new PreprocessorTokenType("HASHTAG_CLOSING_IF");
  IElementType HASHTAG_OPENING_IF = new PreprocessorTokenType("HASHTAG_OPENING_IF");
  IElementType SEMICOLON_CLOSING_IF = new PreprocessorTokenType("SEMICOLON_CLOSING_IF");
  IElementType SEMICOLON_OPENING_IF = new PreprocessorTokenType("SEMICOLON_OPENING_IF");
  IElementType WHITE_SPACE = new PreprocessorTokenType("WHITE_SPACE");
  IElementType XML_CLOSING_IF = new PreprocessorTokenType("XML_CLOSING_IF");
  IElementType XML_OPENING_IF = new PreprocessorTokenType("XML_OPENING_IF");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == HASHTAG_IF_BLOCK) {
        return new PreprocessorHashtagIfBlockImpl(node);
      }
      else if (type == SEMICOLON_IF_BLOCK) {
        return new PreprocessorSemicolonIfBlockImpl(node);
      }
      else if (type == TEXT_BLOCK) {
        return new PreprocessorTextBlockImpl(node);
      }
      else if (type == XML_IF_BLOCK) {
        return new PreprocessorXmlIfBlockImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
