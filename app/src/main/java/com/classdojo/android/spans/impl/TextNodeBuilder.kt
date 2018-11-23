package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class TextNodeBuilder<TypeToReturnForChainedOperations>(
    private val combinesTextReaderSequences: CombinesTextReaderSequences<TypeToReturnForChainedOperations>,
    private val styledNodeFactory: StyledNodeFactory<TypeToReturnForChainedOperations>,
    private val previousStyledText: StyledTextReader
) : Subspannable<TypeToReturnForChainedOperations> {

    override fun addStyledSpan(styleReader: StyleReader, styledTextToAdd: StyledTextReader): TypeToReturnForChainedOperations {
        return addSubspan(styledNodeFactory.newStyledTextReaderWithExplicitStyle(
            styleReader,
            styledTextToAdd
        ))
    }

    override fun addSubspan(styledTextReaderForSubspan: StyledTextReader): TypeToReturnForChainedOperations {
        return combinesTextReaderSequences.newSpanBuilderForSequence(
            listOf(
                this.previousStyledText,
                styledTextReaderForSubspan
            )
        )
    }
}