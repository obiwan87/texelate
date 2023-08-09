package com.lasagnerd.texelate.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.properties.PropertiesIcons;
import com.intellij.lang.properties.psi.Property;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import com.lasagnerd.texelate.microbool.psi.WhackTypes;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MicroboolCompletionContributor extends CompletionContributor {
    public MicroboolCompletionContributor() {
        extend(
                CompletionType.BASIC,
                PlatformPatterns.psiElement(WhackTypes.IDENTIFIER),
                new CompletionParametersCompletionProvider()

        );
    }

    private static class CompletionParametersCompletionProvider extends CompletionProvider<CompletionParameters> {
        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
            List<Property> properties = PropertiesUtils.getProjectProperties(parameters.getEditor().getProject());

            for (Property property : properties) {
                String key = property.getKey();
                if(key != null) {
                    result.addElement(LookupElementBuilder.create(key)
                            .withIcon(PropertiesIcons.XmlProperties)
                            .withTailText(" " + property.getContainingFile().getName()));

                }
            }
        }
    }
}
