package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class ContainerNodeBuilder<T> (
    private val styledNodeFactory: StyledNodeFactory<T>,
    private val containerNodeFactory: ContainerNodeFactory<T>,
    private val childNodeReaders: List<NodeReaderBasic>,
    private val nodeReader: NodeReader
) : NodeBuilderBasic<T>
{
    override fun addStyledSpan(styleReader: StyleReader, nodeReader: NodeReaderBasic): T {
        val newSpanBuilder = styledNodeFactory.newStyledNodeReader(
            styleReader,
            nodeReader
        )
        return containerNodeFactory.newContainerNodeBuilder(childNodeReaders + newSpanBuilder)
    }

    override fun build(): NodeReader {
        return nodeReader
    }
}