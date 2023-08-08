package com.lasagnerd.texelate.kt

import com.intellij.lang.properties.references.PropertyReferenceBase
import com.intellij.openapi.util.TextRange
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext
import com.lasagnerd.texelate.injection.IfStatementTokenPatterns
import com.lasagnerd.texelate.completion.PropertiesUtils
import java.util.regex.Pattern

class PropertiesReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        return registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(PsiComment::class.java),
            object : PsiReferenceProvider() {

                override fun getReferencesByElement(
                    element: PsiElement,
                    context: ProcessingContext
                ): Array<PsiReference> {
                    if (element is PsiComment) {
                        val text = element.text
                        val matcher = IfStatementTokenPatterns.XML_OPENING_IF_STATEMENT_PATTERN.matcher(text)
                        if (matcher.find()) {
                            val properties = PropertiesUtils.getProjectProperties(element.project)
                            val propertyReferences = mutableListOf<PropertyReferenceBase>()
                            for (property in properties) {
                                if (property.key != null) {
                                    val group = matcher.group(1)
                                    val variableNameMatcher = Pattern.compile("[A-Za-z0-9_]+").matcher(matcher.group(2))
                                    if(variableNameMatcher.find()) {
                                        val textRange = TextRange.from(group.length, variableNameMatcher.group().length)
                                        val propertyReference =
                                            GlobalPropertyReference(property.key!!, false, element, textRange)
                                        propertyReferences.add(propertyReference)
                                    }
                                }
                            }

                            return propertyReferences.toTypedArray()
                        }
                    }
                    return PsiReference.EMPTY_ARRAY
                }
            })
    }
}

