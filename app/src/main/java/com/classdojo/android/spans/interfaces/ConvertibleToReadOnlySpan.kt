package com.classdojo.android.spans.interfaces

interface ConvertibleToReadOnlySpan {
    fun asReadOnly(): SpanReader
}