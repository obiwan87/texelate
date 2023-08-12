// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.ifblocks;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.lasagnerd.texelate.ifblocks.psi.PreprocessorTypes.*;
import static com.lasagnerd.texelate.ifblocks.PreprocessorParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class PreprocessorParser implements PsiParser, LightPsiParser {

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
    return preprocessorFile(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(HASHTAG_IF_BLOCK, IF_BLOCK, SEMICOLON_IF_BLOCK, XML_IF_BLOCK),
    create_token_set_(HASHTAG_IF_ELSE_BLOCK, IF_ELSE_BLOCK, SEMICOLON_IF_ELSE_BLOCK, XML_IF_ELSE_BLOCK),
  };

  /* ********************************************************** */
  // item*
  public static boolean else_branch(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "else_branch")) return false;
    Marker m = enter_section_(b, l, _NONE_, ELSE_BRANCH, "<else branch>");
    while (true) {
      int c = current_position_(b);
      if (!item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "else_branch", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // HASHTAG_OPENING_IF item* HASHTAG_CLOSING_IF
  public static boolean hashtag_if_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hashtag_if_block")) return false;
    if (!nextTokenIs(b, HASHTAG_OPENING_IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HASHTAG_OPENING_IF);
    r = r && hashtag_if_block_1(b, l + 1);
    r = r && consumeToken(b, HASHTAG_CLOSING_IF);
    exit_section_(b, m, HASHTAG_IF_BLOCK, r);
    return r;
  }

  // item*
  private static boolean hashtag_if_block_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hashtag_if_block_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "hashtag_if_block_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // HASHTAG_OPENING_IF if_branch HASHTAG_ELSE else_branch HASHTAG_CLOSING_IF
  public static boolean hashtag_if_else_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hashtag_if_else_block")) return false;
    if (!nextTokenIs(b, HASHTAG_OPENING_IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HASHTAG_OPENING_IF);
    r = r && if_branch(b, l + 1);
    r = r && consumeToken(b, HASHTAG_ELSE);
    r = r && else_branch(b, l + 1);
    r = r && consumeToken(b, HASHTAG_CLOSING_IF);
    exit_section_(b, m, HASHTAG_IF_ELSE_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // xml_if_block |
  //                hashtag_if_block |
  //                semicolon_if_block
  public static boolean if_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_block")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, IF_BLOCK, "<if block>");
    r = xml_if_block(b, l + 1);
    if (!r) r = hashtag_if_block(b, l + 1);
    if (!r) r = semicolon_if_block(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // item*
  public static boolean if_branch(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_branch")) return false;
    Marker m = enter_section_(b, l, _NONE_, IF_BRANCH, "<if branch>");
    while (true) {
      int c = current_position_(b);
      if (!item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "if_branch", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // xml_if_else_block |
  //                   hashtag_if_else_block |
  //                   semicolon_if_else_block
  public static boolean if_else_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_else_block")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, IF_ELSE_BLOCK, "<if else block>");
    r = xml_if_else_block(b, l + 1);
    if (!r) r = hashtag_if_else_block(b, l + 1);
    if (!r) r = semicolon_if_else_block(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // text_block | if_block | if_else_block
  static boolean item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item")) return false;
    boolean r;
    r = text_block(b, l + 1);
    if (!r) r = if_block(b, l + 1);
    if (!r) r = if_else_block(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // item*
  static boolean preprocessorFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "preprocessorFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "preprocessorFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // SEMICOLON_OPENING_IF item* SEMICOLON_CLOSING_IF
  public static boolean semicolon_if_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "semicolon_if_block")) return false;
    if (!nextTokenIs(b, SEMICOLON_OPENING_IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON_OPENING_IF);
    r = r && semicolon_if_block_1(b, l + 1);
    r = r && consumeToken(b, SEMICOLON_CLOSING_IF);
    exit_section_(b, m, SEMICOLON_IF_BLOCK, r);
    return r;
  }

  // item*
  private static boolean semicolon_if_block_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "semicolon_if_block_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "semicolon_if_block_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // SEMICOLON_OPENING_IF if_branch SEMICOLON_ELSE else_branch SEMICOLON_CLOSING_IF
  public static boolean semicolon_if_else_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "semicolon_if_else_block")) return false;
    if (!nextTokenIs(b, SEMICOLON_OPENING_IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON_OPENING_IF);
    r = r && if_branch(b, l + 1);
    r = r && consumeToken(b, SEMICOLON_ELSE);
    r = r && else_branch(b, l + 1);
    r = r && consumeToken(b, SEMICOLON_CLOSING_IF);
    exit_section_(b, m, SEMICOLON_IF_ELSE_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // (WHITE_SPACE | ANY)+
  public static boolean text_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "text_block")) return false;
    if (!nextTokenIs(b, "<text block>", ANY, WHITE_SPACE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TEXT_BLOCK, "<text block>");
    r = text_block_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!text_block_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "text_block", c)) break;
    }
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // WHITE_SPACE | ANY
  private static boolean text_block_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "text_block_0")) return false;
    boolean r;
    r = consumeToken(b, WHITE_SPACE);
    if (!r) r = consumeToken(b, ANY);
    return r;
  }

  /* ********************************************************** */
  // XML_OPENING_IF item* XML_CLOSING_IF
  public static boolean xml_if_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_if_block")) return false;
    if (!nextTokenIs(b, XML_OPENING_IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, XML_OPENING_IF);
    r = r && xml_if_block_1(b, l + 1);
    r = r && consumeToken(b, XML_CLOSING_IF);
    exit_section_(b, m, XML_IF_BLOCK, r);
    return r;
  }

  // item*
  private static boolean xml_if_block_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_if_block_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "xml_if_block_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // XML_OPENING_IF if_branch XML_ELSE else_branch XML_CLOSING_IF
  public static boolean xml_if_else_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "xml_if_else_block")) return false;
    if (!nextTokenIs(b, XML_OPENING_IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, XML_OPENING_IF);
    r = r && if_branch(b, l + 1);
    r = r && consumeToken(b, XML_ELSE);
    r = r && else_branch(b, l + 1);
    r = r && consumeToken(b, XML_CLOSING_IF);
    exit_section_(b, m, XML_IF_ELSE_BLOCK, r);
    return r;
  }

}
