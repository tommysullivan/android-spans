package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class TextNodeBuilder<T>(
    private val containerNodeFactory: ContainerNodeFactory<T>,
    private val styledNodeFactory: StyledNodeFactory<T>,
    private val nodeReader: NodeReaderBasic
) : NodeBuilderBasic<T> {

    //TODO: Use this more efficient addText instead of NodeBuilderTextHelpers
//    fun addText(text:String):T {
//        return textNodeFactory.newTextNodeBuilder(nodeReader.fullText() + text)
//    }

    override fun addStyledSpan(styleReader: StyleReader, spanBuilder: NodeReaderBasic): T {
        return containerNodeFactory.newContainerNodeBuilder(
            listOf(
                nodeReader,
                styledNodeFactory.newStyledNodeReader(
                    styleReader,
                    spanBuilder
                )
            )
        )
    }
}