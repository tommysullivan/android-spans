package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

interface NodeBuilderEnhancedWithReader : NodeBuilderEnhanced<NodeBuilderEnhancedWithReader>, NodeReader
open class SpannableFactoryImplWithReader(
    val getStringResourceWithoutPerformingReplacements:(resourceId:Int) -> String
) : SpannableFactoryImplT<NodeBuilderEnhancedWithReader>(getStringResourceWithoutPerformingReplacements)  {
    override val factory = { b:NodeBuilderEnhanced<NodeBuilderEnhancedWithReader>, reader:NodeReader -> NodeBuilderEnhancedSelfImplWithReader(b, reader) }
}

class NodeBuilderEnhancedSelfImplWithReader(
    base:NodeBuilderEnhanced<NodeBuilderEnhancedWithReader>,
    reader:NodeReader
) : NodeBuilderEnhancedWithReader,
    NodeBuilderEnhanced<NodeBuilderEnhancedWithReader> by base,
    NodeReader by reader


//interface NodeBuilderEnhancedSelf : NodeBuilderEnhanced<NodeBuilderEnhancedSelf>
//open class SpannableFactoryImpl(
//    val getStringResourceWithoutPerformingReplacements:(resourceId:Int) -> String
//) : SpannableFactoryImplT<NodeBuilderEnhancedSelf>(getStringResourceWithoutPerformingReplacements)  {
//    override val factory = { b:NodeBuilderEnhanced<NodeBuilderEnhancedSelf>, _:NodeReader -> NodeBuilderEnhancedSelfImpl(b) }
//}
//
//class NodeBuilderEnhancedSelfImpl(
//    base:NodeBuilderEnhanced<NodeBuilderEnhancedSelf>
//) : NodeBuilderEnhancedSelf,
//    NodeBuilderEnhanced<NodeBuilderEnhancedSelf> by base

abstract class SpannableFactoryImplT<T>(
    private val getStringResourceWithoutPerformingReplacements:(resourceId:Int) -> String
) : SpannableFactory<T> {
    abstract val factory:(nodeBuilderEnhanced:NodeBuilderEnhanced<T>, nodeReader:NodeReader) -> T

    override fun newSpannableString(text: String): SpannableString {
        return OurSpannableString(text)
    }

    override fun newTextNodeReader(text: String): NodeReaderBasic {
        return TextNodeReader(text)
    }

    override fun newContainerNodeBuilder(childNodes: List<NodeReaderBasic>): T {
        return newT(
            ContainerNodeBuilder<T>(
                this,
                this,
                childNodes
            ),
            newNodeReader(newContainerNodeReader(childNodes))
        )
    }

    private fun newT(nodeBuilderBasic: NodeBuilderBasic<T>, nodeReader:NodeReader):T {
        return factory(newNodeBuilderEnhanced(nodeBuilderBasic, nodeReader), nodeReader)
    }

    private fun newNodeBuilderEnhanced(nodeBuilderBasic: NodeBuilderBasic<T>, nodeReader:NodeReader):NodeBuilderEnhanced<T> {
        return NodeBuilderEnhancedImpl<T>(
            newNodeBuilderTextHelpersImpl(nodeBuilderBasic),
            nodeBuilderBasic,
            TranslatedTextBuilderImpl(this, nodeBuilderBasic),
            NodeBuilderImpl(nodeReader)
        )
    }

    private fun newNodeBuilderTextHelpersImpl(nodeBuilderBasic: NodeBuilderBasic<T>) =
        NodeBuilderTextHelpersImpl(this, this, nodeBuilderBasic)

    private fun newNodeReader(nodeReaderBasic:NodeReaderBasic):NodeReader {
        return NodeReaderImpl(
            SpannableStringReaderImpl(this, nodeReaderBasic),
            nodeReaderBasic
        )
    }

    override fun newContainerNodeReader(childNodes: List<NodeReaderBasic>): NodeReaderBasic {
        return ContainerNodeReaderImpl(childNodes)
    }

    override fun newStyledNodeBuilder(styleReader: StyleReader, nodeToStyle: NodeReaderBasic): T {
        return newT(
            StyledNodeBuilderImpl(
                this,
                this,
                styleReader,
                nodeToStyle
            ),
            newNodeReader(newStyledNodeReader(styleReader, nodeToStyle))
        )
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

    override fun newTextNodeBuilder(): T {
        return newTextNodeBuilder("")
    }

    override fun newTextNodeBuilder(text: String): T {
        val textNodeReader = newNodeReader(newTextNodeReader(text))
        return newT(
            TextNodeBuilder<T>(
                this,
                this,
                textNodeReader
            ),
            textNodeReader
        )
    }

    override fun newTranslatedNode(stringResourceId: Int, vararg substitutions: NodeReaderBasic): NodeReaderBasic {
        return TranslatedNode(
            stringResourceId,
            listOf(*substitutions),
            getStringResourceWithoutPerformingReplacements,
            this,
            this
        )
    }
}