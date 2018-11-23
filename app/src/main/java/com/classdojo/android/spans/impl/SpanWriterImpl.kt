package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class SpanWriterImpl<T>(
    spanTextWriter:SpanTextWriter<T>,
    subspanContainer:SubspanContainer<T>,
    translatedTextWriter:TranslatedTextWriter<T>,
    convertibleToReadOnlySpan:ConvertibleToReadOnlySpan
) : SpanWriter<T>,
    SpanTextWriter<T> by spanTextWriter,
    SubspanContainer<T> by subspanContainer,
    TranslatedTextWriter<T> by translatedTextWriter,
    ConvertibleToReadOnlySpan by convertibleToReadOnlySpan