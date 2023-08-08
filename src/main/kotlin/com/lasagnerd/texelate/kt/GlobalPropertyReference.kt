package com.lasagnerd.texelate.kt

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.lang.properties.PropertiesIcons
import com.intellij.lang.properties.psi.PropertiesFile
import com.intellij.lang.properties.references.PropertyReferenceBase
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.lasagnerd.texelate.completion.PropertiesUtils

class GlobalPropertyReference(key: String, soft: Boolean, element: PsiElement, textRange: TextRange) :
    PropertyReferenceBase(key, soft, element, textRange) {

    override fun getPropertiesFiles(): MutableList<PropertiesFile>? {
        return PropertiesUtils.getPropertiesFiles(element.project)
    }

    override fun getVariants(): Array<Any> {
        val variants = mutableListOf<LookupElement>()
        val properties = PropertiesUtils.getProjectProperties(element.project)
        for (property in properties) {
            val withTypeText = LookupElementBuilder
                .create(property)
                .withIcon(PropertiesIcons.XmlProperties)
                .withTypeText(property.containingFile.name)

            variants.add(withTypeText)
        }

        return variants.toTypedArray()
    }
}