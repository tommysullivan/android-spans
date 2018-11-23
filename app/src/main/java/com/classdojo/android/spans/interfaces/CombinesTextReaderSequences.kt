package com.classdojo.android.spans.interfaces

interface CombinesTextReaderSequences<T> {
    fun newSpanSequenceBuilder(sequenceOfStyledTextReadersToBuildUpon: List<StyledTextReader>): T
    fun newStyledTextReaderFromSequence(sequenceOfStyledTextReadersToGroupIntoSingleReader: List<StyledTextReader>): StyledTextReader
}