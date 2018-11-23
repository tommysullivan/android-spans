package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class SubspannableImpl<TypeToReturnForChainedOperations> (
    private val styledNodeFactory: StyledNodeFactory<TypeToReturnForChainedOperations>,
    private val combinesTextReaderSequences: CombinesTextReaderSequences<TypeToReturnForChainedOperations>,
    private val subspans: List<StyledTextReader>,
    private val emptyStyleBuilder:StyleBuilder
) : Subspannable<TypeToReturnForChainedOperations>
{
    override fun addStyledSpan(styleBuilderBlock: StyleBuilderBlock, nodeReader: StyledTextReader): TypeToReturnForChainedOperations {
        return addSubspan(styledNodeFactory.newStyledTextReaderWithExplicitStyle(
            styleBuilderBlock(emptyStyleBuilder),
            nodeReader
        ))
    }

    override fun addSubspan(styledTextReader: StyledTextReader): TypeToReturnForChainedOperations {
        return combinesTextReaderSequences.newSpanBuilderForSequence(subspans + styledTextReader)
    }
}