package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class NodeBuilderTextHelpersImpl<T>(
    private val styleBuilderFactory: StyleBuilderFactory,
    private val textNodeReaderFactory: TextNodeFactory,
    private val nodeBuilderBasic: NodeBuilderBasic<T>
) : NodeBuilderTextHelpers<T>
{
    override fun addText(text: String): T {
        return nodeBuilderBasic.addSubspan(textNodeReaderFactory.newTextNodeReader(text))
    }

    override fun addStyledText(styleReader: StyleReader, text: String): T {
        return nodeBuilderBasic.addStyledSpan(styleReader, textNodeReaderFactory.newTextNodeReader(text))
    }
}