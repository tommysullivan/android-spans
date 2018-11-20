package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class StyledNodeBuilder<T : NodeBuilderBasic<T>>(
    private val styledNodeFactory:StyledNodeFactory<T>,
    private val containerNodeFactory: ContainerNodeFactory<T>,
    private val styleReader: StyleReader,
    private val nodeReader: NodeReaderBasic
) : NodeBuilderBasic<T> {

//    TODO: Figure out if we want this specialization somewhere, else delete and assume it is impl more generally by NodeBuilderEnhanced impl
//    override fun addText(text: String): NodeBuilder {
//        return spannableFactory.newStyledNodeBuilder(
//            styleBuilder,
//            styledNode.addText(text)
//        )
//    }

    override fun build(): NodeReaderBasic {
        return nodeReader;
    }

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