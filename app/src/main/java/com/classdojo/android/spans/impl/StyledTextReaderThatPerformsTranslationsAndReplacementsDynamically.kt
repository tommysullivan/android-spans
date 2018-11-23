package com.classdojo.android.spans.impl

import android.support.annotation.StringRes
import com.classdojo.android.spans.interfaces.*

class StyledTextReaderThatPerformsTranslationsAndReplacementsDynamically<T>(
    @StringRes private val stringResourceIdOfTranslationWithPossiblePlacholders:Int,
    private val styledTextToSubstituteInForPlaceholders:List<StyledTextReader>,
    private val getStringResourceWithoutPerformingReplacements:(resourceId:Int) -> String,
    private val styledTextReaderFactoryForPlainText: StyledTextReaderFactoryForPlainText,
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
                if(literalOrPlaceholderSubstring.isLiteralString) styledTextReaderFactoryForPlainText.newTextNodeReader(literalOrPlaceholderSubstring.text)
                else StringFormattedNode(
                    literalOrPlaceholderSubstring.text,
                    styledTextToSubstituteInForPlaceholders,
                    literalOrPlaceholderSubstring.zeroBasedIndexOfReplacement!!
                )
            }
        )
    }

    data class LiteralOrPlaceholderString(public val isLiteralString:Boolean, val text:String, val zeroBasedIndexOfReplacement:Int?) {}

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
        literalsAndPlaceholdersSoFarWithStartIndexOfLastPlainTextSection: Pair<List<LiteralOrPlaceholderString>, Int>,
        stringResourceWithUnreplacedPlaceholders: String
    ): Pair<List<LiteralOrPlaceholderString>, Int> {
        val indexOfEndOfCurrentMatch = currentRegexMatch.range.endInclusive + 1
        val indexWhereCurrentMatchBegins = currentRegexMatch.range.start
        val literalOrPlaceholderStringForCurrentMatch = literalOrPlaceholderStringForCurrentMatch(currentRegexMatch)
        val startIndexOfLastPlainTextSection = literalsAndPlaceholdersSoFarWithStartIndexOfLastPlainTextSection.second
        val needToAppendPrecedingPlainTextBeforeCurrentSection = indexWhereCurrentMatchBegins != startIndexOfLastPlainTextSection
        val newlyCompletedSections = if (needToAppendPrecedingPlainTextBeforeCurrentSection) {
            val textPrecedingCurrentMatch = stringResourceWithUnreplacedPlaceholders.substring(
                literalsAndPlaceholdersSoFarWithStartIndexOfLastPlainTextSection.second,
                indexWhereCurrentMatchBegins
            )
            val sectionOfTextPrecedingCurrentMatch = LiteralOrPlaceholderString(
                true,
                textPrecedingCurrentMatch,
                -1
            )
            listOf(sectionOfTextPrecedingCurrentMatch, literalOrPlaceholderStringForCurrentMatch)
        } else listOf(literalOrPlaceholderStringForCurrentMatch)
        return Pair(literalsAndPlaceholdersSoFarWithStartIndexOfLastPlainTextSection.first + newlyCompletedSections, indexOfEndOfCurrentMatch)
    }

    private fun literalOrPlaceholderStringForCurrentMatch(currentMatch: MatchResult): LiteralOrPlaceholderString {
        return if (currentMatch.value == "%%") {
            LiteralOrPlaceholderString(true, "%", -1)
        } else {
            val replacementIndex = currentMatch.groupValues[2].toInt() - 1
            LiteralOrPlaceholderString(false, currentMatch.value, replacementIndex)
        }
    }
}