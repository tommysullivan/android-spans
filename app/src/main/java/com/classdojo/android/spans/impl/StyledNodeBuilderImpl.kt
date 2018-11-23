package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class StyledNodeBuilderImpl<T>(
    private val styledNodeFactory:StyledNodeFactory<T>,
    private val combinesTextReaderSequences: CombinesTextReaderSequences<T>,
    private val styleReader: StyleReader,
    private val nodeReaderBasic: StyledTextReader
) : SubspanContainer<T> {

    override fun addStyledSpan(styleReader: StyleReader, nodeReaderBasic: StyledTextReader): T {
        return addSubspan(styledNodeFactory.newStyledNodeReader(
            styleReader,
            nodeReaderBasic
        ))
    }

    override fun addSubspan(nodeReaderBasic: StyledTextReader): T {
        return styledNodeFactory.newStyledNodeBuilder(
            this.styleReader,
            combinesTextReaderSequences.newStyledTextReaderFromSequence(
                listOf(
                    this.nodeReaderBasic,
                    nodeReaderBasic
                )
            )
        )
    }
}