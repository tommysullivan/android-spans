package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class StyledNodeBuilderImpl<T>(
    private val styledNodeFactory:StyledNodeFactory<T>,
    private val containerNodeFactory: ContainerNodeFactory<T>,
    private val styleReader: StyleReader,
    private val nodeReaderBasic: NodeReaderBasic
) : NodeBuilderBasic<T> {

    override fun addStyledSpan(styleReader: StyleReader, nodeReaderBasic: NodeReaderBasic): T {
        return addSubspan(styledNodeFactory.newStyledNodeReader(
            styleReader,
            nodeReaderBasic
        ))
    }

    override fun addSubspan(nodeReaderBasic: NodeReaderBasic): T {
        return styledNodeFactory.newStyledNodeBuilder(
            this.styleReader,
            containerNodeFactory.newContainerNodeReader(
                listOf(
                    this.nodeReaderBasic,
                    nodeReaderBasic
                )
            )
        )
    }
}