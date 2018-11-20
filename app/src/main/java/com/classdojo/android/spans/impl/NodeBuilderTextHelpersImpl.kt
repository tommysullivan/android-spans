package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class NodeBuilderTextHelpersImpl<T>(
    private val styleBuilderFactory: StyleBuilderFactory,
    private val textNodeReaderFactory: TextNodeFactory,
    nodeBuilderBasic: NodeBuilderBasic<T>
) : NodeBuilderTextHelpers<T>,
    NodeBuilderBasic<T> by nodeBuilderBasic

{
    override fun addText(text: String): T {
        return addStyledText(styleBuilderFactory.newStyleBuilder().build(), text)
    }

    override fun addStyledText(styleBuilder: StyleReader, text: String): T {
        return addStyledSpan(styleBuilder, textNodeReaderFactory.newTextNodeReader(text))
    }
}