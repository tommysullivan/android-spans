package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class SpannableFactoryImpl(
    spannableStringFactory: SpannableStringFactory,
    private val getStringResourceWithoutPerformingReplacements:(resourceId:Int) -> String
) : SpannableFactory, SpannableStringFactory by spannableStringFactory {

    override fun newTextNodeReader(text: String): NodeReaderBasic {
        return TextNodeReader(text)
    }

    override fun newContainerNodeBuilder(childNodes: List<NodeReaderBasic>): NodeBuilderEnhanced {
        return newNodeBuilderEnhanced(
            ContainerNodeBuilder<NodeBuilderEnhanced>(
                this,
                this,
                childNodes
            ),
            newNodeReader(newContainerNodeReader(childNodes))
        )
    }

    private fun newNodeBuilderEnhanced(nodeBuilderBasic: NodeBuilderBasic<NodeBuilderEnhanced>, nodeReader:NodeReader):NodeBuilderEnhanced {
        return NodeBuilderEnhancedImpl(
            NodeBuilderTextHelpersImpl(this, this, nodeBuilderBasic),
            nodeBuilderBasic,
            TranslatedTextBuilderImpl(this, nodeBuilderBasic, newStyleBuilder().build()),
            NodeBuilderImpl(nodeReader)
        )
    }

    private fun newNodeReader(nodeReaderBasic:NodeReaderBasic):NodeReader {
        return NodeReaderImpl(
            SpannableStringReaderImpl(this, nodeReaderBasic),
            nodeReaderBasic
        )
    }

    override fun newContainerNodeReader(childNodes: List<NodeReaderBasic>): NodeReaderBasic {
        return ContainerNodeReaderImpl(childNodes)
    }

    override fun newStyledNodeBuilder(styleReader: StyleReader, nodeToStyle: NodeReaderBasic): NodeBuilderEnhanced {
        return newNodeBuilderEnhanced(
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

    override fun newTextNodeBuilder(): NodeBuilderEnhanced {
        return newTextNodeBuilder("")
    }

    override fun newTextNodeBuilder(text: String): NodeBuilderEnhanced {
        val textNodeReader = newNodeReader(newTextNodeReader(text))
        return newNodeBuilderEnhanced(
            TextNodeBuilder<NodeBuilderEnhanced>(
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