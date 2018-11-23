package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class TextNodeBuilder<TypeToReturnForChainedOperations>(
    private val combinesTextReaderSequences: CombinesTextReaderSequences<TypeToReturnForChainedOperations>,
    private val styledNodeFactory: StyledNodeFactory<TypeToReturnForChainedOperations>,
    private val previousStyledText: StyledTextReader,
    private val emptyStyleBuilder:StyleBuilder
) : Subspannable<TypeToReturnForChainedOperations> {

    override fun addStyledSpan(styleBuilderBlock: StyleBuilderBlock, styledTextToAdd: StyledTextReader): TypeToReturnForChainedOperations {
        return addSubspan(styledNodeFactory.newStyledTextReaderWithExplicitStyle(
            styleBuilderBlock(emptyStyleBuilder),
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