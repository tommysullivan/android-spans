//package com.classdojo.android.spans.impl
//
//import com.classdojo.android.spans.interfaces.NodeBuilderEnhanced
//import com.classdojo.android.spans.interfaces.SpannableFactory
//import com.classdojo.android.spans.interfaces.StyleBuilder
//
//class NodeBuilderEnhancedImpl : NodeBuilderEnhanced<>(
//    private val spannableFactory: SpannableFactory<NodeBuilderEnhanced>,
//    private val
//) : NodeBuilderEnhanced<T> {
//
//    override fun addText(text: String): T {
//        return addStyledText(spannableFactory.newStyleBuilder(), text)
//    }
//
//    override fun addStyledText(styleBuilder: StyleBuilder, text: String): T {
//        return addStyledSpan(styleBuilder, spannableFactory.newTextNodeReader(text))
//    }
//
//}