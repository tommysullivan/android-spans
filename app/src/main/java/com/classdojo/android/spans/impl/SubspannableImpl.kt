package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class SubspannableImpl<TypeToReturnForChainedOperations> (
    private val styledNodeFactory: StyledNodeFactory<TypeToReturnForChainedOperations>,
    private val combinesTextReaderSequences: CombinesTextReaderSequences<TypeToReturnForChainedOperations>,
    private val subspans: List<StyledTextReader>
) : Subspannable<TypeToReturnForChainedOperations>
{
    override fun addStyledSpan(styleReader: StyleReader, nodeReader: StyledTextReader): TypeToReturnForChainedOperations {
        return addSubspan(styledNodeFactory.newStyledTextReaderWithExplicitStyle(
            styleReader,
            nodeReader
        ))
    }

    override fun addSubspan(styledTextReader: StyledTextReader): TypeToReturnForChainedOperations {
        return combinesTextReaderSequences.newSpanBuilderForSequence(subspans + styledTextReader)
    }
}