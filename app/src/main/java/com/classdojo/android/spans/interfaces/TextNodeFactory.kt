package com.classdojo.android.spans.interfaces

interface TextNodeFactory {
    fun newTextNodeReader(text: String): StyledTextReader
}