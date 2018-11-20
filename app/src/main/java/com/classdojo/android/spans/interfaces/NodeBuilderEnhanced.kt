package com.classdojo.android.spans.interfaces

interface NodeBuilderEnhanced : NodeBuilderBasic<NodeBuilderEnhanced> {
    fun addText(text: String): NodeBuilderEnhanced
    fun addStyledText(styleBuilder: StyleReader, text: String): NodeBuilderEnhanced
    fun newLine(): NodeBuilderEnhanced {
        return addText("\n")
    }
}