package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.Span
import com.classdojo.android.spans.interfaces.SpanReader
import com.classdojo.android.spans.interfaces.SpanWriter

class SpanImpl(
    spanWriter: SpanWriter<Span>,
    spanReader: SpanReader
) : Span,
    SpanWriter<Span> by spanWriter,
    SpanReader by spanReader