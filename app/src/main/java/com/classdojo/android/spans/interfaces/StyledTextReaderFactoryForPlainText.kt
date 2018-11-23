package com.classdojo.android.spans.interfaces

interface StyledTextReaderFactoryForPlainText {
    fun newTextNodeReader(text: String): StyledTextReader
}