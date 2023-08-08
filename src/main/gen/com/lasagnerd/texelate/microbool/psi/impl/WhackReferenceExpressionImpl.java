// This is a generated file. Not intended for manual editing.
package com.lasagnerd.texelate.microbool.psi.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.intellij.lang.properties.psi.Property;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiReference;
import com.lasagnerd.texelate.completion.PropertiesUtils;
import com.lasagnerd.texelate.kt.GlobalPropertyReference;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static com.lasagnerd.texelate.microbool.psi.WhackTypes.*;

import com.lasagnerd.texelate.microbool.psi.*;

public class WhackReferenceExpressionImpl extends WhackExpressionImpl implements WhackReferenceExpression {

    public WhackReferenceExpressionImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public void accept(@NotNull WhackVisitor visitor) {
        visitor.visitReferenceExpression(this);
    }

    @Override
    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof WhackVisitor) accept((WhackVisitor) visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public PsiElement getIdentifier() {
        return findNotNullChildByType(IDENTIFIER);
    }

    @Override
    public PsiReference getReference() {
        PsiReference[] references = getReferences();
        if (references.length > 0) {
            return references[0];
        }
        return super.getReference();
    }

    @Override
    public PsiReference @NotNull [] getReferences() {
        String text = getText();

        List<Property> projectProperties = PropertiesUtils.getProjectProperties(getProject());
        List<GlobalPropertyReference> references = new ArrayList<>();
        for (Property projectProperty : projectProperties) {
            if (text != null && text.equals(projectProperty.getKey())) {
                GlobalPropertyReference globalPropertyReference = new GlobalPropertyReference(projectProperty.getKey(), false, this, TextRange.allOf(getText()));
                references.add(globalPropertyReference);
            }
        }
        return references.toArray(new PsiReference[0]);
    }

    @Override
    public boolean evaluate(Map<String, Object> variables) {
        String identifier = getIdentifier().getText();
        Object value = variables.get(identifier);
        if(value != null) {
            if(value instanceof Boolean) {
                return (boolean) value;
            }

            return true;
        }
        throw new IllegalStateException(String.format("Undefined variable '%s'", identifier));
    }
}
