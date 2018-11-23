package com.classdojo.android.spans.interfaces

import android.support.annotation.StringRes

interface TranslatedTextWriter<T> {
    fun addTranslatedText(@StringRes stringResourceId:Int, vararg substitutions:StyledTextReader):T
}