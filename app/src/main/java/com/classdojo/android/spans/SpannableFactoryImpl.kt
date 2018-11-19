package com.classdojo.android.immutable.spannable

import com.classdojo.android.spans.SpannableString

open class SpannableFactoryImpl() : SpannableFactory {

    override fun newContainerNodeBuilder(): NodeBuilder {
        return newContainerNodeBuilder(emptyList())
    }

    override fun newContainerNodeBuilder(childNodes:List<NodeBuilder>): NodeBuilder {
        return ContainerNode(this, childNodes)
    }

    override fun newStyledNodeBuilder(styleBuilder: StyleBuilder, nodeToStyle: NodeBuilder): NodeBuilder {
        return StyledNode(this, styleBuilder, nodeToStyle, this)
    }

    override fun newTextNodeBuilder(text: String): NodeBuilder {
        return TextNode(this, text)
    }

    override fun newStyleMarker(startIndex: Int, length: Int, styleBuilder: StyleBuilder): StyleMarker {
        return StyleMarkerImpl(startIndex, length, styleBuilder, this)
    }

    override fun newStyleBuilder(): StyleBuilder {
        return newStyleBuilder(emptyList())
    }

    override fun newStyleBuilder(crappyAndroidStyleObjects: List<Any>): StyleBuilder {
        return StyleBuilderImpl(crappyAndroidStyleObjects) { styles ->
            this.newStyleBuilder(styles) //TODO: Can i pass method as HOF here?
        }
    }

    override fun newSpannableString(text: String): SpannableString {
        return OurSpannableString(text)
    }

    class OurSpannableString(text:String) : android.text.SpannableString(text), SpannableString {}
}