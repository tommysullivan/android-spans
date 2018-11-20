package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class ContainerNodeBuilder<T> (
    private val styledNodeFactory: StyledNodeFactory<T>,
    private val containerNodeFactory: ContainerNodeFactory<T>,
    private val childNodeReaders: List<NodeReaderBasic>
) : NodeBuilderBasic<T>
{
    override fun addStyledSpan(styleReader: StyleReader, nodeReader: NodeReaderBasic): T {
        return addSubspan(styledNodeFactory.newStyledNodeReader(
            styleReader,
            nodeReader
        ))
    }

    override fun addSubspan(spanReader: NodeReaderBasic): T {
        return containerNodeFactory.newContainerNodeBuilder(childNodeReaders + spanReader)
    }
}