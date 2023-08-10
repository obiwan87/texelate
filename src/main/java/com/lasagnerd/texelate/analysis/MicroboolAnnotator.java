package com.lasagnerd.texelate.analysis;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.lang.properties.IProperty;
import com.intellij.lang.properties.PropertiesImplUtil;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.lasagnerd.texelate.environments.model.EnvironmentsModelService;
import com.lasagnerd.texelate.microbool.psi.MicroboolReferenceExpression;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MicroboolAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof MicroboolReferenceExpression))
            return;

        List<IProperty> properties = PropertiesImplUtil.findPropertiesByKey(element.getProject(), element.getText());


        Project project = element.getProject();
        List<String> filesInEnvironments = project
                .getService(EnvironmentsModelService.class)
                .getEnvironmentsConfig().getEnvironments()
                .stream().flatMap(env -> env.getPaths().stream()).toList();


        for (IProperty property : properties) {
            if (filesInEnvironments.contains(property.getPropertiesFile().getVirtualFile().getPath()))
                return;
        }
        holder.newAnnotation(HighlightSeverity.ERROR, String.format("Undefined variable '%s'", element.getText()))
                .create();
    }
}
