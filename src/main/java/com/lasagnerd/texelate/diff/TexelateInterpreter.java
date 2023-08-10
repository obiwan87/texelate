package com.lasagnerd.texelate.diff;

import com.intellij.psi.PsiElement;
import com.lasagnerd.texelate.microbool.MicroboolInterpreter;
import com.lasagnerd.texelate.microbool.psi.*;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class TexelateInterpreter {



    private TexelateInterpreter() {

    }

    public static boolean interpret(Map<String, Object> variables, MicroboolFile microboolFile) {
        PsiElement firstChild = microboolFile.getFirstChild();
        if(firstChild instanceof MicroboolExpression root) {
            try {
                return MicroboolInterpreter.evaluate(root, variables);
            } catch (Exception e) {
                throw new RuntimeException("Interpretation failed", e);
            }
        }
        return false;
    }

    private static void checkValidity(MicroboolFile microboolFile) {

    }

    private void process(PsiElement child) {
        if(child instanceof MicroboolBinaryExpression) {
            MicroboolBinaryExpression MicroboolBinaryExpr = (MicroboolBinaryExpression) child;
            List<MicroboolExpression> expressionList = MicroboolBinaryExpr.getExpressionList();
            int i = 0;
        }
    }
}
