package com.lasagnerd.texelate.analysis;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.lang.properties.IProperty;
import com.intellij.lang.properties.PropertiesImplUtil;
import com.intellij.psi.PsiElement;
import com.lasagnerd.texelate.microbool.psi.WhackReferenceExpression;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TexelateAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if(!(element instanceof WhackReferenceExpression))
            return;

        List<IProperty> properties = PropertiesImplUtil.findPropertiesByKey(element.getProject(), element.getText());

        if(properties.isEmpty()) {
            holder.newAnnotation(HighlightSeverity.ERROR, String.format("Undefined variable '%s'", element.getText()))
                    .create();
        }
    }
}
