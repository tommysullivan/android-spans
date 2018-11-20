package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class StyledNodeBuilderImpl<T>(
    private val styledNodeFactory:StyledNodeFactory<T>,
    private val containerNodeFactory: ContainerNodeFactory<T>,
    private val styleReader: StyleReader,
    private val nodeReader: NodeReaderBasic
) : NodeBuilderBasic<T> {

//    TODO: This specialization would require that we track a styleBuilder and not just a styleReader
//    override fun addText(text: String): NodeBuilder {
//        return spannableFactory.newStyledNodeBuilder(
//            styleBuilder,
//            styledNode.addText(text)
//        )
//    }

    override fun addStyledSpan(styleReader: StyleReader, span: NodeReaderBasic): T {
        return styledNodeFactory.newStyledNodeBuilder(
            this.styleReader,
            containerNodeFactory.newContainerNodeReader(
                listOf(
                    this.nodeReader,
                    styledNodeFactory.newStyledNodeReader(
                        styleReader,
                        span
                    )
                )
            )
        )
    }
}