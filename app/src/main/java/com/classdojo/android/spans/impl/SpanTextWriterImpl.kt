package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class SpanTextWriterImpl<T>(
    private val stylesFactory: StylesFactory,
    private val styledTextReaderFactoryForPlainText: StyledTextReaderFactoryForPlainText,
    private val subspanContainer: SubspanContainer<T>
) : SpanTextWriter<T>
{
    override fun addText(text: String): T {
        return subspanContainer.addSubspan(styledTextReaderFactoryForPlainText.newTextNodeReader(text))
    }

    override fun addStyledText(styleReader: StyleReader, text: String): T {
        return subspanContainer.addStyledSpan(styleReader, styledTextReaderFactoryForPlainText.newTextNodeReader(text))
    }
}