package com.classdojo.android.spans.interfaces

interface StyledTextReaderFactoryForPlainText {
    fun newStyledTextReader(text: String): StyledTextReader
}