package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class SpannableFactoryImpl(
    spannableStringFactory: SpannableStringFactory
) : SpannableFactory, SpannableStringFactory by spannableStringFactory {

    override fun newTextNodeReader(text: String): NodeReaderBasic {
        return TextNodeReader(text)
    }

    override fun newContainerNodeBuilder(childNodes: List<NodeReaderBasic>): NodeBuilderEnhanced {
        val containerNodeBuilder = ContainerNodeBuilder(this, this, childNodes, newContainerNodeReader(childNodes))
        return NodeBuilderEnhancedImpl(this, this, containerNodeBuilder)
    }

    override fun newContainerNodeReader(childNodes: List<NodeReaderBasic>): NodeReaderBasic {
        return ContainerNodeReaderImpl(childNodes)
    }

    override fun newStyledNodeBuilder(styleReader: StyleReader, nodeToStyle: NodeReaderBasic): NodeBuilderEnhanced {
        val styledNodeBuilder = StyledNodeBuilderImpl(this, this, styleReader, newStyledNodeReader(styleReader, nodeToStyle))
        return NodeBuilderEnhancedImpl(this, this, styledNodeBuilder)
    }

    override fun newStyledNodeReader(styleReader: StyleReader, nodeToStyle: NodeReaderBasic): NodeReaderBasic {
        return StyledNodeReaderImpl(this, nodeToStyle, styleReader)
    }

    override fun newStyleMarker(startIndex: Int, length: Int, styleBuilder: StyleReader): StyleMarker {
        return StyleMarkerImpl(startIndex, length, styleBuilder, this)
    }

    override fun newStyleBuilder(): StyleBuilder {
        return newStyleBuilder(emptyList())
    }

    override fun newStyleBuilder(crappyAndroidStyleObjects: List<Any>): StyleBuilder {
        return StyleBuilderImpl(crappyAndroidStyleObjects, this)
    }

    override fun newNodeBuilder():NodeBuilderEnhanced {
        return newContainerNodeBuilder(emptyList())
    }
}