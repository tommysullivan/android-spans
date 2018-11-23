package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.Span
import com.classdojo.android.spans.interfaces.SpanWriter
import com.classdojo.android.spans.interfaces.SpanReader

class SpanImpl(
    base: SpanWriter<Span>,
    reader: SpanReader
) : Span,
    SpanWriter<Span> by base,
    SpanReader by reader