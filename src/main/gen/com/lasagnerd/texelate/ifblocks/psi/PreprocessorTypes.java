// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.ifblocks.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.lasagnerd.texelate.ifblocks.psi.impl.*;

public interface PreprocessorTypes {

  IElementType ELSE_BRANCH = new PreprocessorElementType("ELSE_BRANCH");
  IElementType HASHTAG_IF_BLOCK = new PreprocessorElementType("HASHTAG_IF_BLOCK");
  IElementType HASHTAG_IF_ELSE_BLOCK = new PreprocessorElementType("HASHTAG_IF_ELSE_BLOCK");
  IElementType IF_BLOCK = new PreprocessorElementType("IF_BLOCK");
  IElementType IF_BRANCH = new PreprocessorElementType("IF_BRANCH");
  IElementType IF_ELSE_BLOCK = new PreprocessorElementType("IF_ELSE_BLOCK");
  IElementType SEMICOLON_IF_BLOCK = new PreprocessorElementType("SEMICOLON_IF_BLOCK");
  IElementType SEMICOLON_IF_ELSE_BLOCK = new PreprocessorElementType("SEMICOLON_IF_ELSE_BLOCK");
  IElementType TEXT_BLOCK = new PreprocessorElementType("TEXT_BLOCK");
  IElementType XML_IF_BLOCK = new PreprocessorElementType("XML_IF_BLOCK");
  IElementType XML_IF_ELSE_BLOCK = new PreprocessorElementType("XML_IF_ELSE_BLOCK");

  IElementType ANY = new PreprocessorTokenType("ANY");
  IElementType HASHTAG_CLOSING_IF = new PreprocessorTokenType("HASHTAG_CLOSING_IF");
  IElementType HASHTAG_ELSE = new PreprocessorTokenType("HASHTAG_ELSE");
  IElementType HASHTAG_OPENING_IF = new PreprocessorTokenType("HASHTAG_OPENING_IF");
  IElementType SEMICOLON_CLOSING_IF = new PreprocessorTokenType("SEMICOLON_CLOSING_IF");
  IElementType SEMICOLON_ELSE = new PreprocessorTokenType("SEMICOLON_ELSE");
  IElementType SEMICOLON_OPENING_IF = new PreprocessorTokenType("SEMICOLON_OPENING_IF");
  IElementType WHITE_SPACE = new PreprocessorTokenType("WHITE_SPACE");
  IElementType XML_CLOSING_IF = new PreprocessorTokenType("XML_CLOSING_IF");
  IElementType XML_ELSE = new PreprocessorTokenType("XML_ELSE");
  IElementType XML_OPENING_IF = new PreprocessorTokenType("XML_OPENING_IF");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ELSE_BRANCH) {
        return new PreprocessorElseBranchImpl(node);
      }
      else if (type == HASHTAG_IF_BLOCK) {
        return new PreprocessorHashtagIfBlockImpl(node);
      }
      else if (type == HASHTAG_IF_ELSE_BLOCK) {
        return new PreprocessorHashtagIfElseBlockImpl(node);
      }
      else if (type == IF_BRANCH) {
        return new PreprocessorIfBranchImpl(node);
      }
      else if (type == SEMICOLON_IF_BLOCK) {
        return new PreprocessorSemicolonIfBlockImpl(node);
      }
      else if (type == SEMICOLON_IF_ELSE_BLOCK) {
        return new PreprocessorSemicolonIfElseBlockImpl(node);
      }
      else if (type == TEXT_BLOCK) {
        return new PreprocessorTextBlockImpl(node);
      }
      else if (type == XML_IF_BLOCK) {
        return new PreprocessorXmlIfBlockImpl(node);
      }
      else if (type == XML_IF_ELSE_BLOCK) {
        return new PreprocessorXmlIfElseBlockImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
