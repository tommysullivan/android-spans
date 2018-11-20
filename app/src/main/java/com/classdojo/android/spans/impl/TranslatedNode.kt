package com.classdojo.android.spans.impl

import android.support.annotation.StringRes
import com.classdojo.android.spans.interfaces.NodeReader
import com.classdojo.android.spans.interfaces.NodeReaderBasic
import com.classdojo.android.spans.interfaces.SpannableFactory
import com.classdojo.android.spans.interfaces.StyleMarker

class TranslatedNode(
    @StringRes private val stringResourceId:Int,
    private val substitutions:List<NodeReaderBasic>,
    private val spannableFactory: SpannableFactory,
    private val getStringResourceWithoutPerformingReplacements:(value:Int) -> String,
    private val getStringResourceWithReplacements:(value:Int, replacements:List<String>) -> String
) : NodeReaderBasic {

    override fun fullText(): String {
        return getStringResourceWithReplacements(
            stringResourceId,
            substitutions.map{s -> s.fullText()}
        )
    }

    override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> {
        return spannableFactory.newContainerNodeReader(
            sections().map{section ->
                if(section.isPlainText) spannableFactory.newTextNodeReader(section.text)
                else StringFormattedNode(
                    section.text,
                    substitutions,
                    section.replacementIndex!!
                )
            }
        ).styleMarkersFromOutermostToInnermost()
    }

    class Section(public val isPlainText:Boolean, public val text:String, public val replacementIndex:Int?) {}

    fun sections():List<Section> {
        val regexString = "(?<!%)(%\\d+\\\$(dd|dm|dh|[ds]))"
        val regex = Regex(regexString)
        val template = getStringResourceWithoutPerformingReplacements(stringResourceId)
        val zero = Pair(emptyList<Section>(), 0)
        val parts = regex.findAll(template).fold(zero) { soFar, current ->
            val newIndex = current.range.endInclusive + 1
            val currentStartIndex = current.range.start

            //TODO: Find the correct replacementIndex
            val replacementIndex = 1

            val currentSection =
                Section(false, current.value, replacementIndex)
            if(currentStartIndex == soFar.second)
                Pair(soFar.first + currentSection, newIndex)
            else {
                val textPrecedingCurrentMatch = template.substring(soFar.second, currentStartIndex)
                val textSection =
                    Section(true, textPrecedingCurrentMatch, -1)
                Pair(soFar.first + textSection + currentSection, newIndex)
            }
        }
        return if(parts.second == template.lastIndex) parts.first
        else parts.first + Section(
            true,
            template.substring(parts.second),
            -1
        )
    }
}