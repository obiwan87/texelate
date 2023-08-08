package com.lasagnerd.texelate.ifblocks;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.lasagnerd.texelate.ifblocks.psi.PreprocessorTypes;
import org.jetbrains.annotations.NotNull;

public class PreprocessorParserDefinition  implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(PreprocessorLanguage.INSTANCE);

    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new PreprocessorLexerAdapter();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new PreprocessorParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return TokenSet.EMPTY;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        return PreprocessorTypes.Factory.createElement(node);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new PreprocessorFile(viewProvider);
    }
}
