package com.classdojo.android.spans.interfaces

import android.support.annotation.StringRes

interface TranslatedTextBuilder<T> {
    fun addTranslatedText(@StringRes stringResourceId:Int, vararg substitutions:NodeReaderBasic):T
}