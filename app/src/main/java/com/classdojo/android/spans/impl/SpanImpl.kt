package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.Span
import com.classdojo.android.spans.interfaces.SpanWriter
import com.classdojo.android.spans.interfaces.SpanReader

class SpanImpl(
    spanWriter: SpanWriter<Span>,
    spanReader: SpanReader
) : Span,
    SpanWriter<Span> by spanWriter,
    SpanReader by spanReader