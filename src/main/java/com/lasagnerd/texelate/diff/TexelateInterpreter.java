package com.lasagnerd.texelate.diff;

import com.intellij.psi.PsiElement;
import com.lasagnerd.texelate.microbool.psi.MicroboolFile;
import com.lasagnerd.texelate.microbool.psi.WhackBinaryExpression;
import com.lasagnerd.texelate.microbool.psi.WhackExpression;

import java.util.List;
import java.util.Map;

public class TexelateInterpreter {

    private TexelateInterpreter() {

    }

    public static boolean interpret(Map<String, Object> variables, MicroboolFile microboolFile) {
        PsiElement firstChild = microboolFile.getFirstChild();
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

    private static void checkValidity(MicroboolFile microboolFile) {

    }

    private void process(PsiElement child) {
        if(child instanceof WhackBinaryExpression) {
            WhackBinaryExpression whackBinaryExpr = (WhackBinaryExpression) child;
            List<WhackExpression> expressionList = whackBinaryExpr.getExpressionList();
            int i = 0;
        }
    }
}
