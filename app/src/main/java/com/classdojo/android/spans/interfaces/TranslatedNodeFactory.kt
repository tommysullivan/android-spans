package com.classdojo.android.spans.interfaces

interface TranslatedNodeFactory {
    fun newTranslatedNode(stringResourceId: Int, vararg substitutions: NodeReaderBasic):NodeReaderBasic
}