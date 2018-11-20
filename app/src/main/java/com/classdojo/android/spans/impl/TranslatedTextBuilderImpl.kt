package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class TranslatedTextBuilderImpl<T>(
    private val translatedNodeFactory: TranslatedNodeFactory,
    private val previousBuilder:NodeBuilderBasic<T>
) : TranslatedTextBuilder<T> {

    override fun addTranslatedText(stringResourceId: Int, vararg substitutions: NodeReaderBasic): T {
        return previousBuilder.addSubspan(translatedNodeFactory.newTranslatedNode(stringResourceId, *substitutions))
    }
}