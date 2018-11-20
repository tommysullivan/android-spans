package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class TextNodeBuilder<T : NodeBuilderBasic<T>>(
    private val spannableFactory: SpannableFactory,
    private val nodeReader: NodeReaderBasic
) : NodeBuilderBasic<T> {
    override fun addStyledSpan(styleReader: StyleReader, spanBuilder: NodeReaderBasic): T {
        return spannableFactory.newContainerNodeBuilder(
            listOf(
                this.nodeReader,
                spannableFactory.newStyledNodeReader(
                    styleReader,
                    spanBuilder
                )
            )
        )
    }

    override fun build(): NodeReaderBasic {
        return nodeReader;
    }

//    TODO: This specialization coule be added here or elsewhere (or subsumed by general impl)
//    override fun addText(text: String): NodeBuilder {
//        return spannableFactory.newTextNodeBuilder(this.text + text)
//    }

}