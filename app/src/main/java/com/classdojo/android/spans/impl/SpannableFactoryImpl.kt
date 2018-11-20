package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

open class SpannableFactoryImpl(
    containerNodeFactoryImpl:ContainerNodeFactory<NodeBuilder>,
    styledNodeFactoryImpl:StyledNodeFactory<NodeBuilder>,
    styleMarkerFactory:StyleMarkerFactory
) :
    SpannableFactory<NodeBuilder>,
    ContainerNodeFactory<NodeBuilder> by containerNodeFactoryImpl,
    StyledNodeFactory<NodeBuilder> by styledNodeFactoryImpl,
    StyleMarkerFactory by styleMarkerFactory
{
    override fun newTextNodeReader(text: String): NodeReaderBasic = TextNodeReader(text)
    override fun newStyleBuilder(): StyleBuilder = newStyleBuilder(emptyList())
    override fun newStyleBuilder(crappyAndroidStyleObjects: List<Any>): StyleBuilder = StyleBuilderImpl(crappyAndroidStyleObjects, this)
    override fun newSpannableString(text: String):SpannableString = OurSpannableString(text)
    override fun newNodeBuilder(): NodeBuilder {
        throw NotImplementedError()
    }
}

class StyleMarkerFactoryImpl : StyleMarkerFactory {
    override fun newStyleMarker(startIndex: Int, length: Int, styleReader: StyleReader): StyleMarker {
        return StyleMarkerImpl(startIndex, length, styleReader, this)
    }
}

class ContainerNodeFactoryImpl(private val styledNodeFactory: StyledNodeFactory<NodeBuilder>) : ContainerNodeFactory<NodeBuilder> {
    override fun newContainerNodeBuilder(childNodes: List<NodeReaderBasic>): NodeBuilder {
        val containerNodeBuilder = ContainerNodeBuilder(styledNodeFactory, this, childNodes, newContainerNodeReader(childNodes))
        return containerNodeBuilder
    }

    override fun newContainerNodeReader(childNodeReaders: List<NodeReaderBasic>):NodeReaderBasic = ContainerNodeReader(childNodeReaders)
}

class StyledNodeFactoryImpl(private val styleMarkerFactory: StyleMarkerFactory, private val containerNodeFactory:ContainerNodeFactory<NodeBuilder>) : StyledNodeFactory<NodeBuilder> {
    override fun newStyledNodeBuilder(styleReader: StyleReader, nodeToStyle: NodeReaderBasic): NodeBuilder {
        val styledNodeBuilder = StyledNodeBuilder(this, containerNodeFactory, styleReader, newStyledNodeReader(styleReader, nodeToStyle))
        return styledNodeBuilder
    }

    override fun newStyledNodeReader(styleReader: StyleReader, nodeToStyle: NodeReaderBasic): NodeReaderBasic {
        return StyledNodeReader(styleMarkerFactory, nodeToStyle, styleReader)
    }
}