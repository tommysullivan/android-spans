package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class ContainerNodeBuilder<T> (
    private val styledNodeFactory: StyledNodeFactory<T>,
    private val combinesTextReaderSequences: CombinesTextReaderSequences<T>,
    private val childNodeReaders: List<StyledTextReader>
) : SubspanContainer<T>
{
    override fun addStyledSpan(styleReader: StyleReader, nodeReader: StyledTextReader): T {
        return addSubspan(styledNodeFactory.newStyledNodeReader(
            styleReader,
            nodeReader
        ))
    }

    override fun addSubspan(styledTextReader: StyledTextReader): T {
        return combinesTextReaderSequences.newSpanSequenceBuilder(childNodeReaders + styledTextReader)
    }
}