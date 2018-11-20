package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class NodeBuilderEnhancedImpl(
    private val styleBuilderFactory: StyleBuilderFactory,
    private val textNodeReaderFactory: TextNodeFactory,
    nodeBuilderBasic: NodeBuilderBasic<NodeBuilderEnhanced>
) : NodeBuilderEnhanced, NodeBuilderBasic<NodeBuilderEnhanced> by nodeBuilderBasic
{
    override fun addText(text: String): NodeBuilderEnhanced {
        return addStyledText(styleBuilderFactory.newStyleBuilder().build(), text)
    }

    override fun addStyledText(styleBuilder: StyleReader, text: String): NodeBuilderEnhanced {
        return addStyledSpan(styleBuilder, textNodeReaderFactory.newTextNodeReader(text))
    }
}