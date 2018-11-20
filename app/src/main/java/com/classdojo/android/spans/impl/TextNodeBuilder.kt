package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class TextNodeBuilder<T : NodeBuilderBasic<T>>(
    private val containerNodeFactory: ContainerNodeFactory<T>,
    private val styledNodeFactory: StyledNodeFactory<T>,
    private val nodeReader: NodeReaderBasic
) : NodeBuilderBasic<T> {
    override fun addStyledSpan(styleReader: StyleReader, spanBuilder: NodeReaderBasic): T {
        return containerNodeFactory.newContainerNodeBuilder(
            listOf(
                this.nodeReader,
                styledNodeFactory.newStyledNodeReader(
                    styleReader,
                    spanBuilder
                )
            )
        )
    }

    override fun build(): NodeReaderBasic {
        return nodeReader;
    }
}