package com.classdojo.android.spans.interfaces

interface SpanTextWriter<T> {
    fun addText(text: String): T
    fun addStyledText(styleBuilder: StyleReader, text: String): T
    fun newLine(): T = addText("\n")
}