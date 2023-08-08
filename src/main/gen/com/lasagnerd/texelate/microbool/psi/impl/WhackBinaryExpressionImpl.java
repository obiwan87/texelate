// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.microbool.psi.impl;

import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;

import com.lasagnerd.texelate.microbool.psi.*;

public class WhackBinaryExpressionImpl extends WhackExpressionImpl implements WhackBinaryExpression {

    public WhackBinaryExpressionImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public void accept(@NotNull WhackVisitor visitor) {
        visitor.visitBinaryExpression(this);
    }

    @Override
    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof WhackVisitor) accept((WhackVisitor) visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public WhackBinaryOperator getBinaryOperator() {
        return findNotNullChildByClass(WhackBinaryOperator.class);
    }

    @Override
    @NotNull
    public List<WhackExpression> getExpressionList() {
        return PsiTreeUtil.getChildrenOfTypeAsList(this, WhackExpression.class);
    }

    @Override
    public boolean evaluate(Map<String, Object> variables) {
        WhackExpression left = getLeft();
        WhackExpression right = getRight();

        boolean leftEvaluation = left.evaluate(variables);
        boolean rightEvaluation = right.evaluate(variables);

        return getOperator(getBinaryOperator().getText()).apply(leftEvaluation, rightEvaluation);
    }

    public BinaryOperator<Boolean> getOperator(String operatorText) {
        switch (operatorText) {
            case "and":
                return (x, y) -> x && y;

            case "or":
                return (x, y) -> x || y;

            case "==":
                return (x, y) -> x == y;

            case "!=":
                return (x, y) -> x != y;

          default:
            throw new IllegalStateException(String.format("Unkwnon operator '%s'", operatorText));
        }
    }

    private WhackExpression getRight() {
        return getExpressionList().get(1);
    }

    private WhackExpression getLeft() {
        return getExpressionList().get(0);
    }
}
