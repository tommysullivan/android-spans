package com.classdojo.android.spans.deprecated

//package com.classdojo.android.immutable.spannable
//
//abstract class BaseNode(
//    protected val spannableFactory: SpannableFactory
//    ) : Node {
//
//    override fun build(): NodeReader {
//        return this
//    }
//
//    override fun addStyledText(styleBuilder: StyleBuilder, text: String): NodeBuilder {
//        return addStyledSpan(styleBuilder, spannableFactory.newTextNodeBuilder(text))
//    }
//
//    override fun addText(text: String): NodeBuilder {
//        return addStyledText(spannableFactory.newStyleBuilder(), text)
//    }
//
//}