package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class TextNodeBuilder<T>(
    private val containerNodeFactory: ContainerNodeFactory<T>,
    private val styledNodeFactory: StyledNodeFactory<T>,
    private val nodeReaderBasic: NodeReaderBasic
) : NodeBuilderBasic<T> {

    //TODO: Use this more efficient addText instead of NodeBuilderTextHelpers
//    fun addText(text:String):T {
//        return textNodeFactory.newTextNodeBuilder(nodeReader.fullText() + text)
//    }

    override fun addStyledSpan(styleReader: StyleReader, nodeReaderBasic: NodeReaderBasic): T {
        return addSubspan(styledNodeFactory.newStyledNodeReader(
            styleReader,
            nodeReaderBasic
        ))
    }

    override fun addSubspan(nodeReaderBasic: NodeReaderBasic): T {
        return containerNodeFactory.newContainerNodeBuilder(
            listOf(
                this.nodeReaderBasic,
                nodeReaderBasic
            )
        )
    }
}