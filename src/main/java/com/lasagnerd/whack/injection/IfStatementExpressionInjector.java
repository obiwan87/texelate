package com.lasagnerd.whack.injection;

import com.intellij.lang.Language;
import com.intellij.lang.injection.MultiHostInjector;
import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.psi.xml.XmlProcessingInstruction;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IfStatementExpressionInjector implements MultiHostInjector {

    public static final String KOTLIN_PREFIX = "fun dsl() { %s if(";
    public static final String KOTLIN_SUFFIX = ") {  } }";

    @Override
    public void getLanguagesToInject(@NotNull MultiHostRegistrar registrar, @NotNull PsiElement context) {
        if (context instanceof PsiComment && context instanceof PsiLanguageInjectionHost) {
            PsiComment psiComment = (PsiComment) context;
            String commentText = psiComment.getText();
            Pattern pattern;
            if (context.getLanguage().getID().equals("XML")) {
                pattern = IfStatementTokenPatterns.XML_OPENING_IF_STATEMENT_PATTERN;
            } else {
                pattern = IfStatementTokenPatterns.GENERIC_OPENING_IF_STATEMENT_PATTERN;
            }

            Matcher matcher = pattern.matcher(commentText);
            if (matcher.find()) {
                Language kotlin = Language.findLanguageByID("Whack");

                if (kotlin != null) {
                    //group(0)?if_
                    registrar.startInjecting(kotlin);
                    registrar.addPlace("",
                            "",
                            (PsiLanguageInjectionHost) context,
                            TextRange.from(matcher.group(1).length(), matcher.group(2).trim().length()));
                    registrar.doneInjecting();
                }
            }
        }
    }

    @Override
    public @NotNull
    List<? extends Class<? extends PsiElement>> elementsToInjectIn() {
        return List.of(PsiComment.class, XmlProcessingInstruction.class);
    }
}
