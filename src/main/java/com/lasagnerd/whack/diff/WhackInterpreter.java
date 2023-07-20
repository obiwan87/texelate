package com.lasagnerd.whack.diff;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.lasagnerd.whack.lang.psi.WhackBinaryExpression;
import com.lasagnerd.whack.lang.psi.WhackExpression;
import com.lasagnerd.whack.lang.psi.WhackFile;

import java.util.List;
import java.util.Map;

public class WhackInterpreter {

    private WhackInterpreter() {

    }

    public static boolean interpret(Map<String, Object> variables, WhackFile whackFile) {
        PsiElement firstChild = whackFile.getFirstChild();
        if(firstChild instanceof WhackExpression root) {
            try {

                boolean result = root.evaluate(variables);
                                return result;
            } catch (Exception e) {
                throw new RuntimeException("Interpretation failed", e);
            }
        }
        return false;
    }

    private static void checkValidity(WhackFile whackFile) {

    }

    private void process(PsiElement child) {
        if(child instanceof WhackBinaryExpression) {
            WhackBinaryExpression whackBinaryExpr = (WhackBinaryExpression) child;
            List<WhackExpression> expressionList = whackBinaryExpr.getExpressionList();
            int i = 0;
        }
    }
}
