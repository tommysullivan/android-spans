package com.classdojo.android.spans.interfaces

interface SpanWriter<T> :
    SpanTextWriter<T>,
    SubspanContainer<T>,
    TranslatedTextWriter<T>,
    ConvertibleToReadOnlySpan