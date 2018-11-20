package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class TranslatedTextBuilderImpl<T>(
    private val translatedNodeFactory: TranslatedNodeFactory,
    private val previousBuilder:NodeBuilderBasic<T>,
    private val emptyStyleReader:StyleReader
) : TranslatedTextBuilder<T> {

    override fun addTranslatedText(stringResourceId: Int, vararg substitutions: NodeReaderBasic): T {

        //TODO: Could we have a more basic 'addChildNode(child:BasicNodeReader):T` ?
        return previousBuilder.addStyledSpan(
            emptyStyleReader,
            translatedNodeFactory.newTranslatedNode(stringResourceId, *substitutions)
        )
    }
}