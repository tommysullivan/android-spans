package com.classdojo.android.spans.interfaces

interface NodeBuilderEnhanced<T : NodeBuilderEnhanced<T>> {
    fun addText(text: String): T
    fun addStyledText(styleBuilder: StyleBuilder, text: String): T
    fun newLine(): T {
        return addText("\n")
    }
}