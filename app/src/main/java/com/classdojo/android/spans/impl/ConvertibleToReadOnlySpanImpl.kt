package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.ConvertibleToReadOnlySpan
import com.classdojo.android.spans.interfaces.SpanReader

class ConvertibleToReadOnlySpanImpl(private val spanReader:SpanReader) : ConvertibleToReadOnlySpan {
    override fun asReadOnly() = spanReader
}