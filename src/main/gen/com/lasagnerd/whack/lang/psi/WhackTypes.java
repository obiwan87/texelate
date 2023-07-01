// This is a generated file. Not intended for manual editing.
package com.lasagnerd.whack.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.lasagnerd.whack.lang.psi.impl.*;

public interface WhackTypes {

  IElementType BINARY_EXPRESSION = new WhackElementType("BINARY_EXPRESSION");
  IElementType BINARY_OPERATOR = new WhackElementType("BINARY_OPERATOR");
  IElementType BINARY_OPERATOR_AND = new WhackElementType("BINARY_OPERATOR_AND");
  IElementType BINARY_OPERATOR_EQUALITY = new WhackElementType("BINARY_OPERATOR_EQUALITY");
  IElementType BINARY_OPERATOR_INEQUALITY = new WhackElementType("BINARY_OPERATOR_INEQUALITY");
  IElementType BINARY_OPERATOR_OR = new WhackElementType("BINARY_OPERATOR_OR");
  IElementType BOOL_LITERAL_EXPRESSION = new WhackElementType("BOOL_LITERAL_EXPRESSION");
  IElementType EXPRESSION = new WhackElementType("EXPRESSION");
  IElementType REFERENCE_EXPRESSION = new WhackElementType("REFERENCE_EXPRESSION");
  IElementType UNARY_EXPRESSION = new WhackElementType("UNARY_EXPRESSION");
  IElementType UNARY_OPERATOR = new WhackElementType("UNARY_OPERATOR");
  IElementType UNARY_OPERATOR_NOT = new WhackElementType("UNARY_OPERATOR_NOT");

  IElementType BOOL = new WhackTokenType("BOOL");
  IElementType IDENTIFIER = new WhackTokenType("IDENTIFIER");
  IElementType OPERATOR_AND = new WhackTokenType("and");
  IElementType OPERATOR_EQUALITY = new WhackTokenType("==");
  IElementType OPERATOR_INEQUALITY = new WhackTokenType("!=");
  IElementType OPERATOR_NOT = new WhackTokenType("not");
  IElementType OPERATOR_OR = new WhackTokenType("or");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == BINARY_EXPRESSION) {
        return new WhackBinaryExpressionImpl(node);
      }
      else if (type == BINARY_OPERATOR_AND) {
        return new WhackBinaryOperatorAndImpl(node);
      }
      else if (type == BINARY_OPERATOR_EQUALITY) {
        return new WhackBinaryOperatorEqualityImpl(node);
      }
      else if (type == BINARY_OPERATOR_INEQUALITY) {
        return new WhackBinaryOperatorInequalityImpl(node);
      }
      else if (type == BINARY_OPERATOR_OR) {
        return new WhackBinaryOperatorOrImpl(node);
      }
      else if (type == BOOL_LITERAL_EXPRESSION) {
        return new WhackBoolLiteralExpressionImpl(node);
      }
      else if (type == REFERENCE_EXPRESSION) {
        return new WhackReferenceExpressionImpl(node);
      }
      else if (type == UNARY_EXPRESSION) {
        return new WhackUnaryExpressionImpl(node);
      }
      else if (type == UNARY_OPERATOR_NOT) {
        return new WhackUnaryOperatorNotImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
