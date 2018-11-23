package com.classdojo.android.spans.impl

import android.support.annotation.StringRes
import com.classdojo.android.spans.interfaces.*

class TranslatedNode<T>(
    @StringRes private val stringResourceIdOfTranslationWithPossiblePlacholders:Int,
    private val styledTextToSubstituteInForPlaceholders:List<StyledTextReader>,
    private val getStringResourceWithoutPerformingReplacements:(resourceId:Int) -> String,
    private val textReaderFactory: TextNodeFactory,
    private val combinesTextReaderSequences: CombinesTextReaderSequences<T>
) : StyledTextReader {

    override fun fullText(): String {
        return styledTextReaderEncapsulatingSequenceOfLiteralsAndPlaceholders().fullText()
    }

    override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> {
        return styledTextReaderEncapsulatingSequenceOfLiteralsAndPlaceholders().styleMarkersFromOutermostToInnermost()
    }

    fun styledTextReaderEncapsulatingSequenceOfLiteralsAndPlaceholders():StyledTextReader {
        return combinesTextReaderSequences.newStyledTextReaderFromSequence(
            literalsAndPlaceholdersInOrder().map{ literalOrPlaceholderSubstring ->
                if(literalOrPlaceholderSubstring.isLiteralString) textReaderFactory.newTextNodeReader(literalOrPlaceholderSubstring.text)
                else StringFormattedNode(
                    literalOrPlaceholderSubstring.text,
                    styledTextToSubstituteInForPlaceholders,
                    literalOrPlaceholderSubstring.zeroBasedIndexOfReplacement!!
                )
            }
        )
    }

    class LiteralOrPlaceholderString(public val isLiteralString:Boolean, val text:String, val zeroBasedIndexOfReplacement:Int?) {}

    fun literalsAndPlaceholdersInOrder():List<LiteralOrPlaceholderString> {
        val regexThatMatchesPlaceholderTokensAndDoublePercentSigns = Regex("%((\\d+)\\\$)?(%|dd|dm|dh|[ds])")
        val stringResourceWithUnreplacedPlaceholders = getStringResourceWithoutPerformingReplacements(stringResourceIdOfTranslationWithPossiblePlacholders)
        val literalsAndPlaceholdersListWithIndexOfEndOfLastMatch = regexThatMatchesPlaceholderTokensAndDoublePercentSigns
            .findAll(stringResourceWithUnreplacedPlaceholders)
            .fold(Pair(emptyList<LiteralOrPlaceholderString>(), 0)) {
                literalsAndPlaceholdersSoFar, currentRegexMatch -> (
                    appendLiteralAndOrPlaceholderBasedOnMatchAndMoveIndexFurtherAlong(
                        currentRegexMatch,
                        literalsAndPlaceholdersSoFar,
                        stringResourceWithUnreplacedPlaceholders
                    )
                )
            }

        val literalsAndPlaceholdersWithLastSectionPossiblyMissing = literalsAndPlaceholdersListWithIndexOfEndOfLastMatch.first
        val indexOfEndOfLastMatch = literalsAndPlaceholdersListWithIndexOfEndOfLastMatch.second
        val atEndOfString = indexOfEndOfLastMatch == stringResourceWithUnreplacedPlaceholders.lastIndex
        val allLiteralsAndPlaceholders = if(atEndOfString)
            literalsAndPlaceholdersWithLastSectionPossiblyMissing
        else {
            val lastPlainTextSection = LiteralOrPlaceholderString(
                true,
                stringResourceWithUnreplacedPlaceholders.substring(indexOfEndOfLastMatch),
                -1
            )
            literalsAndPlaceholdersWithLastSectionPossiblyMissing + lastPlainTextSection
        }

        return allLiteralsAndPlaceholders
    }

    private fun appendLiteralAndOrPlaceholderBasedOnMatchAndMoveIndexFurtherAlong(
        currentRegexMatch: MatchResult,
        literalsAndPlaceholdersSoFar: Pair<List<LiteralOrPlaceholderString>, Int>,
        stringResourceWithUnreplacedPlaceholders: String
    ): Pair<List<LiteralOrPlaceholderString>, Int> {
        val newIndex = currentRegexMatch.range.endInclusive + 1
        val currentStartIndex = currentRegexMatch.range.start
        val currentSection = getCurrentSection(currentRegexMatch)
        val startIndexOfLastPlainTextSection = literalsAndPlaceholdersSoFar.second
        val needToAppendPrecedingPlainTextBeforeCurrentSection = currentStartIndex != startIndexOfLastPlainTextSection
        val newlyCompletedSections = if (needToAppendPrecedingPlainTextBeforeCurrentSection) {
            val textPrecedingCurrentMatch = stringResourceWithUnreplacedPlaceholders.substring(literalsAndPlaceholdersSoFar.second, currentStartIndex)
            val sectionOfTextPrecedingCurrentMatch = LiteralOrPlaceholderString(
                true,
                textPrecedingCurrentMatch,
                -1
            )
            listOf(sectionOfTextPrecedingCurrentMatch, currentSection)
        } else listOf(currentSection)
        return Pair(literalsAndPlaceholdersSoFar.first + newlyCompletedSections, newIndex)
    }

    private fun getCurrentSection(currentMatch: MatchResult): LiteralOrPlaceholderString {
        return if (currentMatch.value == "%%") {
            LiteralOrPlaceholderString(true, "%", -1)
        } else {
            val replacementIndex = currentMatch.groupValues[2].toInt() - 1
            LiteralOrPlaceholderString(false, currentMatch.value, replacementIndex)
        }
    }
}