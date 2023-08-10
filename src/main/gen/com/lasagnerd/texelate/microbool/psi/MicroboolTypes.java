// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.microbool.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.lasagnerd.texelate.microbool.psi.impl.*;

public interface MicroboolTypes {

  IElementType BINARY_EXPRESSION = new MicroboolElementType("BINARY_EXPRESSION");
  IElementType BINARY_OPERATOR = new MicroboolElementType("BINARY_OPERATOR");
  IElementType BINARY_OPERATOR_AND = new MicroboolElementType("BINARY_OPERATOR_AND");
  IElementType BINARY_OPERATOR_EQUALITY = new MicroboolElementType("BINARY_OPERATOR_EQUALITY");
  IElementType BINARY_OPERATOR_INEQUALITY = new MicroboolElementType("BINARY_OPERATOR_INEQUALITY");
  IElementType BINARY_OPERATOR_OR = new MicroboolElementType("BINARY_OPERATOR_OR");
  IElementType BOOL_LITERAL_EXPRESSION = new MicroboolElementType("BOOL_LITERAL_EXPRESSION");
  IElementType EXPRESSION = new MicroboolElementType("EXPRESSION");
  IElementType REFERENCE_EXPRESSION = new MicroboolElementType("REFERENCE_EXPRESSION");
  IElementType UNARY_EXPRESSION = new MicroboolElementType("UNARY_EXPRESSION");
  IElementType UNARY_OPERATOR = new MicroboolElementType("UNARY_OPERATOR");
  IElementType UNARY_OPERATOR_NOT = new MicroboolElementType("UNARY_OPERATOR_NOT");

  IElementType BOOL = new MicroboolTokenType("BOOL");
  IElementType IDENTIFIER = new MicroboolTokenType("IDENTIFIER");
  IElementType OPERATOR_AND = new MicroboolTokenType("and");
  IElementType OPERATOR_EQUALITY = new MicroboolTokenType("==");
  IElementType OPERATOR_INEQUALITY = new MicroboolTokenType("!=");
  IElementType OPERATOR_NOT = new MicroboolTokenType("not");
  IElementType OPERATOR_OR = new MicroboolTokenType("or");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == BINARY_EXPRESSION) {
        return new MicroboolBinaryExpressionImpl(node);
      }
      else if (type == BINARY_OPERATOR_AND) {
        return new MicroboolBinaryOperatorAndImpl(node);
      }
      else if (type == BINARY_OPERATOR_EQUALITY) {
        return new MicroboolBinaryOperatorEqualityImpl(node);
      }
      else if (type == BINARY_OPERATOR_INEQUALITY) {
        return new MicroboolBinaryOperatorInequalityImpl(node);
      }
      else if (type == BINARY_OPERATOR_OR) {
        return new MicroboolBinaryOperatorOrImpl(node);
      }
      else if (type == BOOL_LITERAL_EXPRESSION) {
        return new MicroboolBoolLiteralExpressionImpl(node);
      }
      else if (type == REFERENCE_EXPRESSION) {
        return new MicroboolReferenceExpressionImpl(node);
      }
      else if (type == UNARY_EXPRESSION) {
        return new MicroboolUnaryExpressionImpl(node);
      }
      else if (type == UNARY_OPERATOR_NOT) {
        return new MicroboolUnaryOperatorNotImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
