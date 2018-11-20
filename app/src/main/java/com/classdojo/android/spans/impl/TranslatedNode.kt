package com.classdojo.android.spans.impl

import android.support.annotation.StringRes
import com.classdojo.android.spans.interfaces.*

class TranslatedNode(
    @StringRes private val stringResourceId:Int,
    private val substitutions:List<NodeReaderBasic>,
    private val getStringResourceWithoutPerformingReplacements:(resourceId:Int) -> String,
    private val textReaderFactory: TextNodeFactory,
    private val containerFactory: ContainerNodeFactory<NodeBuilderEnhanced>
) : NodeReaderBasic {

    override fun fullText(): String {
        return containerNode().fullText()
    }

    override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> {
        return containerNode().styleMarkersFromOutermostToInnermost()
    }

    fun containerNode():NodeReaderBasic {
        return containerFactory.newContainerNodeReader(
            sections().map{section ->
                if(section.isPlainText) textReaderFactory.newTextNodeReader(section.text)
                else StringFormattedNode(
                    section.text,
                    substitutions,
                    section.replacementIndex!!
                )
            }
        )
    }

    class Section(public val isPlainText:Boolean, public val text:String, public val replacementIndex:Int?) {}

    fun sections():List<Section> {
        val placholderOrEscapedPercentRegex = Regex("%((\\d+)\\\$)?(%|dd|dm|dh|[ds])")
        val stringResourceWithPlaceholders = getStringResourceWithoutPerformingReplacements(stringResourceId)
        val zero = Pair(emptyList<Section>(), 0)
        val parts = placholderOrEscapedPercentRegex
            .findAll(stringResourceWithPlaceholders)
            .fold(zero) { soFar, current ->
                foldMatchesIntoAllStringSections(current, soFar, stringResourceWithPlaceholders)
            }

        val startIndexOfLastPlainTextSection = parts.second
        val allSectionsExceptPossibleLastTextSection = parts.first
        val atEndOfString = startIndexOfLastPlainTextSection == stringResourceWithPlaceholders.lastIndex
        val allSections = if(atEndOfString)
            allSectionsExceptPossibleLastTextSection
        else {
            val lastPlainTextSection = Section(
                true,
                stringResourceWithPlaceholders.substring(startIndexOfLastPlainTextSection),
                -1
            )
            allSectionsExceptPossibleLastTextSection + lastPlainTextSection
        }

        return allSections
    }

    private fun foldMatchesIntoAllStringSections(
        currentMatch: MatchResult,
        soFar: Pair<List<Section>, Int>,
        template: String
    ): Pair<List<Section>, Int> {
        val newIndex = currentMatch.range.endInclusive + 1
        val currentStartIndex = currentMatch.range.start
        val currentSection = getCurrentSection(currentMatch)
        val startIndexOfLastPlainTextSection = soFar.second
        val needToAppendPrecedingPlainTextBeforeCurrentSection = currentStartIndex != startIndexOfLastPlainTextSection
        val newlyCompletedSections = if (needToAppendPrecedingPlainTextBeforeCurrentSection) {
            val textPrecedingCurrentMatch = template.substring(soFar.second, currentStartIndex)
            val sectionOfTextPrecedingCurrentMatch = Section(
                true,
                textPrecedingCurrentMatch,
                -1
            )
            listOf(sectionOfTextPrecedingCurrentMatch, currentSection)
        } else listOf(currentSection)
        return Pair(soFar.first + newlyCompletedSections, newIndex)
    }

    private fun getCurrentSection(currentMatch: MatchResult): Section {
        return if (currentMatch.value == "%%") {
            Section(true, "%", -1)
        } else {
            val replacementIndex = currentMatch.groupValues[2].toInt() - 1
            Section(false, currentMatch.value, replacementIndex)
        }
    }
}