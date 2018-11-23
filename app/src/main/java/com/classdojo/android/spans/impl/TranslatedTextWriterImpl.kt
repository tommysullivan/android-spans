package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class TranslatedTextWriterImpl<T>(
    private val translatedStyledTextReaderFactory: TranslatedStyledTextReaderFactory,
    private val previousBuilder:SubspanContainer<T>
) : TranslatedTextWriter<T> {

    override fun addTranslatedText(stringResourceId: Int, vararg substitutions: StyledTextReader): T {
        return previousBuilder.addSubspan(translatedStyledTextReaderFactory.newTranslatedStyledTextReader(stringResourceId, *substitutions))
    }
}