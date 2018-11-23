package com.classdojo.android.spans.interfaces

interface CombinesTextReaderSequences<TypeToReturnForChainedOperations> {
    fun newSpanBuilderForSequence(sequenceOfStyledTextReadersToBuildUpon: List<StyledTextReader>): TypeToReturnForChainedOperations
    fun newStyledTextReaderFromSequence(sequenceOfStyledTextReadersToGroupIntoSingleReader: List<StyledTextReader>): StyledTextReader
}