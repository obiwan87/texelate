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
        WhackInterpreter whackInterpreter = new WhackInterpreter();
        PsiElement firstChild = whackFile.getFirstChild();

        if(firstChild instanceof WhackExpression) {
            WhackExpression root = (WhackExpression) firstChild;
            try {
                return root.evaluate(variables);
            } catch (Exception e) {
                throw new RuntimeException("Interpretation failed", e);
            }
        }
        return System.currentTimeMillis() % 2 == 0;
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
