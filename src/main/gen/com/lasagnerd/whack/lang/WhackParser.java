// This is a generated file. Not intended for manual editing.
package com.lasagnerd.whack.lang;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.lasagnerd.whack.lang.psi.WhackTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class WhackParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return whackFile(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(UNARY_OPERATOR, UNARY_OPERATOR_NOT),
    create_token_set_(BINARY_EXPRESSION, BOOL_LITERAL_EXPRESSION, EXPRESSION, REFERENCE_EXPRESSION,
      UNARY_EXPRESSION),
    create_token_set_(BINARY_OPERATOR, BINARY_OPERATOR_AND, BINARY_OPERATOR_EQUALITY, BINARY_OPERATOR_INEQUALITY,
      BINARY_OPERATOR_OR),
  };

  /* ********************************************************** */
  // binary_operator_or | binary_operator_and | binary_operator_equality | binary_operator_inequality
  public static boolean binary_operator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binary_operator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, BINARY_OPERATOR, "<binary operator>");
    r = binary_operator_or(b, l + 1);
    if (!r) r = binary_operator_and(b, l + 1);
    if (!r) r = binary_operator_equality(b, l + 1);
    if (!r) r = binary_operator_inequality(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // OPERATOR_AND
  public static boolean binary_operator_and(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binary_operator_and")) return false;
    if (!nextTokenIs(b, OPERATOR_AND)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPERATOR_AND);
    exit_section_(b, m, BINARY_OPERATOR_AND, r);
    return r;
  }

  /* ********************************************************** */
  // OPERATOR_EQUALITY
  public static boolean binary_operator_equality(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binary_operator_equality")) return false;
    if (!nextTokenIs(b, OPERATOR_EQUALITY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPERATOR_EQUALITY);
    exit_section_(b, m, BINARY_OPERATOR_EQUALITY, r);
    return r;
  }

  /* ********************************************************** */
  // OPERATOR_INEQUALITY
  public static boolean binary_operator_inequality(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binary_operator_inequality")) return false;
    if (!nextTokenIs(b, OPERATOR_INEQUALITY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPERATOR_INEQUALITY);
    exit_section_(b, m, BINARY_OPERATOR_INEQUALITY, r);
    return r;
  }

  /* ********************************************************** */
  // OPERATOR_OR
  public static boolean binary_operator_or(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binary_operator_or")) return false;
    if (!nextTokenIs(b, OPERATOR_OR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPERATOR_OR);
    exit_section_(b, m, BINARY_OPERATOR_OR, r);
    return r;
  }

  /* ********************************************************** */
  // unary_operator_not
  public static boolean unary_operator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_operator")) return false;
    if (!nextTokenIs(b, OPERATOR_NOT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, UNARY_OPERATOR, null);
    r = unary_operator_not(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // OPERATOR_NOT
  public static boolean unary_operator_not(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_operator_not")) return false;
    if (!nextTokenIs(b, OPERATOR_NOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPERATOR_NOT);
    exit_section_(b, m, UNARY_OPERATOR_NOT, r);
    return r;
  }

  /* ********************************************************** */
  // expression
  static boolean whackFile(PsiBuilder b, int l) {
    return expression(b, l + 1, -1);
  }

  /* ********************************************************** */
  // Expression root: expression
  // Operator priority table:
  // 0: BINARY(binary_expression)
  // 1: PREFIX(unary_expression)
  // 2: ATOM(reference_expression) ATOM(bool_literal_expression)
  public static boolean expression(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expression")) return false;
    addVariant(b, "<expression>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = unary_expression(b, l + 1);
    if (!r) r = reference_expression(b, l + 1);
    if (!r) r = bool_literal_expression(b, l + 1);
    p = r;
    r = r && expression_0(b, l + 1, g);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  public static boolean expression_0(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expression_0")) return false;
    boolean r = true;
    while (true) {
      Marker m = enter_section_(b, l, _LEFT_, null);
      if (g < 0 && binary_operator(b, l + 1)) {
        r = expression(b, l, 0);
        exit_section_(b, l, m, BINARY_EXPRESSION, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  public static boolean unary_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_expression")) return false;
    if (!nextTokenIsSmart(b, OPERATOR_NOT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = unary_operator(b, l + 1);
    p = r;
    r = p && expression(b, l, 1);
    exit_section_(b, l, m, UNARY_EXPRESSION, r, p, null);
    return r || p;
  }

  // IDENTIFIER
  public static boolean reference_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "reference_expression")) return false;
    if (!nextTokenIsSmart(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REFERENCE_EXPRESSION, "<expression>");
    r = consumeTokenSmart(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // BOOL
  public static boolean bool_literal_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bool_literal_expression")) return false;
    if (!nextTokenIsSmart(b, BOOL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BOOL_LITERAL_EXPRESSION, "<expression>");
    r = consumeTokenSmart(b, BOOL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
