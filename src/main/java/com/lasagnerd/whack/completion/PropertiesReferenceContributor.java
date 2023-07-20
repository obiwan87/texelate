package com.lasagnerd.whack.completion;

import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import com.lasagnerd.whack.lang.psi.WhackTypes;
import org.jetbrains.annotations.NotNull;

public class PropertiesReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(
                PlatformPatterns.psiElement(WhackTypes.IDENTIFIER),
                new PropertiesReferenceProvider()
        );
    }

    static class PropertiesReferenceProvider extends PsiReferenceProvider {

        @Override
        public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        return new PsiReference[0];
        }
    }
}
