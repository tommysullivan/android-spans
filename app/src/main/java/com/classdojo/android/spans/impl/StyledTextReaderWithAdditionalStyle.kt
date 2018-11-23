package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class StyledTextReaderWithAdditionalStyle<TypeToReturnForChainedOperations>(
    private val styledNodeFactory:StyledNodeFactory<TypeToReturnForChainedOperations>,
    private val combinesTextReaderSequences: CombinesTextReaderSequences<TypeToReturnForChainedOperations>,
    private val styleReader: StyleReader,
    private val styledTextReader: StyledTextReader,
    private val emptyStyleBuilder: StyleBuilder
) : Subspannable<TypeToReturnForChainedOperations> {

    override fun addStyledSpan(styleBuilderBlock: StyleBuilderBlock, styledTextReader: StyledTextReader): TypeToReturnForChainedOperations {
        return addSubspan(styledNodeFactory.newStyledTextReaderWithExplicitStyle(
            styleBuilderBlock(emptyStyleBuilder),
            styledTextReader
        ))
    }

    override fun addSubspan(styledTextReaderForSubspan: StyledTextReader): TypeToReturnForChainedOperations {
        return styledNodeFactory.newStyledSpanBuilder(
            this.styleReader,
            combinesTextReaderSequences.newStyledTextReaderFromSequence(
                listOf(
                    this.styledTextReader,
                    styledTextReaderForSubspan
                )
            )
        )
    }
}