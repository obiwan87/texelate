package com.lasagnerd.texelate.microbool.psi;

import com.intellij.lang.ASTNode;
import com.intellij.lang.properties.psi.Property;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiReference;
import com.lasagnerd.texelate.completion.PropertiesUtils;
import com.lasagnerd.texelate.kt.GlobalPropertyReference;
import com.lasagnerd.texelate.microbool.psi.impl.MicroboolExpressionImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class MicroboolReferenceExpressionMixin extends MicroboolExpressionImpl {

    public MicroboolReferenceExpressionMixin(ASTNode node) {
        super(node);
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
                GlobalPropertyReference globalPropertyReference = new GlobalPropertyReference(projectProperty.getKey(),
                        false,
                        this,
                        TextRange.allOf(getText()));
                references.add(globalPropertyReference);
            }
        }
        return references.toArray(new PsiReference[0]);
    }

}
