package com.lasagnerd.texelate.ifblocks.psi;

import com.intellij.psi.PsiElement;
import com.lasagnerd.texelate.diff.TexelatePreprocessor;
import com.lasagnerd.texelate.injection.IfStatementTokenPatterns;
import com.lasagnerd.texelate.injection.IfStatementTokenPatterns.IfStatement;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

public class PreprocessorPsiUtil {

    public static @NotNull String evaluate(PreprocessorIfBlock block, Map<String, Object> symbols) {
        String ifCondition = getIfCondition(block);
        boolean condition = TexelatePreprocessor.evaluate(block.getProject(), symbols, ifCondition);

        if (condition) {
            StringBuilder text = new StringBuilder();
            for (PsiElement child : block.getChildren()) {
                if (child instanceof PreprocessorEvaluableBlock childBlock) {
                    text.append(childBlock.evaluate(symbols));
                }
            }
            return text.toString();
        }

        return "";
    }

    public static @NotNull String evaluate(ConditionalBlock block, Map<String, Object> symbols) {
        String ifCondition = getIfCondition(block);
        boolean condition = TexelatePreprocessor.evaluate(block.getProject(), symbols, ifCondition);

        StringBuilder text = new StringBuilder();
        if (condition) {
            PsiElement ifBranch = block.ifBranch();
            for (PsiElement child : ifBranch.getChildren()) {
                if (child instanceof PreprocessorEvaluableBlock childBlock) {
                    text.append(childBlock.evaluate(symbols));
                }
            }
        } else {
            PsiElement elseBranch = block.elseBranch();
            if(elseBranch != null) {
                for (PsiElement child : elseBranch.getChildren()) {
                    if (child instanceof PreprocessorEvaluableBlock childBlock) {
                        text.append(childBlock.evaluate(symbols));
                    }
                }
            }
        }
        return text.toString();
    }


    public static PsiElement ifBranch(ConditionalBlock block) {
        if (block instanceof PreprocessorXmlIfElseBlock xmlIfElseBlock) {
            return xmlIfElseBlock.getIfBranch();
        }

        if (block instanceof PreprocessorHashtagIfElseBlock hashtagIfElseBlock) {
            return hashtagIfElseBlock.getIfBranch();
        }

        if (block instanceof PreprocessorSemicolonIfElseBlock semicolonIfElseBlock) {
            return semicolonIfElseBlock.getIfBranch();
        }

        if (block instanceof PreprocessorXmlIfBlock xmlIfBlock) {
            return xmlIfBlock;
        }

        if (block instanceof PreprocessorHashtagIfBlock hashtagIfBlock) {
            return hashtagIfBlock;
        }

        if (block instanceof PreprocessorSemicolonIfBlock semicolonIfBlock) {
            return semicolonIfBlock;
        }

        return null;
    }

    public static PsiElement elseBranch(ConditionalBlock block) {
        if (block instanceof PreprocessorXmlIfElseBlock xmlIfElseBlock) {
            return xmlIfElseBlock.getElseBranch();
        }

        if (block instanceof PreprocessorHashtagIfElseBlock hashtagIfElseBlock) {
            return hashtagIfElseBlock.getElseBranch();
        }

        if (block instanceof PreprocessorSemicolonIfElseBlock semicolonIfElseBlock) {
            return semicolonIfElseBlock.getElseBranch();
        }

        // null psi element
        return null;
    }

    public static String evaluate(@NotNull PreprocessorTextBlock block, Map<String, Object> ignore) {
        return block.getText();
    }


    private static String getIfCondition(@NotNull PsiElement block) {
        if (block instanceof PreprocessorXmlIfBlock xmlIfBlock) {
            Optional<IfStatement> ifStatementOptional = IfStatementTokenPatterns
                    .parseXmlCommentOpeningIfStatement(xmlIfBlock.getXmlOpeningIf().getText());
            return ifStatementOptional.map(IfStatement::expression).orElse("");
        }

        if (block instanceof PreprocessorHashtagIfBlock hashtagIfBlock) {
            Optional<IfStatement> ifStatementOptional = IfStatementTokenPatterns
                    .parseOpeningIfStatement(hashtagIfBlock.getHashtagOpeningIf().getText());
            return ifStatementOptional.map(IfStatement::expression).orElse("");

        }

        if (block instanceof PreprocessorSemicolonIfBlock semicolonIfBlock) {
            Optional<IfStatement> ifStatementOptional = IfStatementTokenPatterns
                    .parseOpeningIfStatement(semicolonIfBlock.getSemicolonOpeningIf().getText());
            return ifStatementOptional.map(IfStatement::expression).orElse("");
        }

        if (block instanceof PreprocessorXmlIfElseBlock xmlIfElseBlock) {
            Optional<IfStatement> ifStatementOptional = IfStatementTokenPatterns
                    .parseXmlCommentOpeningIfStatement(xmlIfElseBlock.getXmlOpeningIf().getText());
            return ifStatementOptional.map(IfStatement::expression).orElse("");
        }

        if (block instanceof PreprocessorSemicolonIfElseBlock semicolonIfBlock) {
            Optional<IfStatement> ifStatementOptional = IfStatementTokenPatterns
                    .parseOpeningIfStatement(semicolonIfBlock.getSemicolonOpeningIf().getText());
            return ifStatementOptional.map(IfStatement::expression).orElse("");
        }

        if (block instanceof PreprocessorHashtagIfElseBlock hashtagIfElseBlock) {
            Optional<IfStatement> ifStatementOptional = IfStatementTokenPatterns
                    .parseOpeningIfStatement(hashtagIfElseBlock.getHashtagOpeningIf().getText());
            return ifStatementOptional.map(IfStatement::expression).orElse("");
        }

        throw new IllegalArgumentException("Unknown if block type: " + block.getClass().getName());
    }
}
