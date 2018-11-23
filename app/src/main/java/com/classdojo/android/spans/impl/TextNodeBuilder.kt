package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class TextNodeBuilder<T>(
    private val combinesTextReaderSequences: CombinesTextReaderSequences<T>,
    private val styledNodeFactory: StyledNodeFactory<T>,
    private val nodeReaderBasic: StyledTextReader
) : SubspanContainer<T> {

    //TODO: Use this more efficient addText instead of SpanTextWriter
//    fun addText(text:String):T {
//        return textNodeFactory.newTextNodeBuilder(nodeReader.fullText() + text)
//    }

    override fun addStyledSpan(styleReader: StyleReader, nodeReaderBasic: StyledTextReader): T {
        return addSubspan(styledNodeFactory.newStyledNodeReader(
            styleReader,
            nodeReaderBasic
        ))
    }

    override fun addSubspan(nodeReaderBasic: StyledTextReader): T {
        return combinesTextReaderSequences.newSpanSequenceBuilder(
            listOf(
                this.nodeReaderBasic,
                nodeReaderBasic
            )
        )
    }
}