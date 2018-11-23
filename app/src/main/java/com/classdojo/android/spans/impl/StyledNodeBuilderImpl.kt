package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class StyledNodeBuilderImpl<TypeToReturnForChainedOperations>(
    private val styledNodeFactory:StyledNodeFactory<TypeToReturnForChainedOperations>,
    private val combinesTextReaderSequences: CombinesTextReaderSequences<TypeToReturnForChainedOperations>,
    private val styleReader: StyleReader,
    private val styledTextReader: StyledTextReader
) : Subspannable<TypeToReturnForChainedOperations> {

    override fun addStyledSpan(styleReader: StyleReader, styledTextReader: StyledTextReader): TypeToReturnForChainedOperations {
        return addSubspan(styledNodeFactory.newStyledTextReaderWithExplicitStyle(
            styleReader,
            styledTextReader
        ))
    }

    override fun addSubspan(nodeReaderBasic: StyledTextReader): TypeToReturnForChainedOperations {
        return styledNodeFactory.newStyledSpanBuilder(
            this.styleReader,
            combinesTextReaderSequences.newStyledTextReaderFromSequence(
                listOf(
                    this.styledTextReader,
                    nodeReaderBasic
                )
            )
        )
    }
}