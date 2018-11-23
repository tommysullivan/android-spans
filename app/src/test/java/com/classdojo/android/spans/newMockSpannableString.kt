package com.classdojo.android.spans

import com.classdojo.android.spans.interfaces.SpannableString
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk

fun newMockSpannableString(): SpannableString {
    val mockSpannableString = mockk<SpannableString>()
    every { mockSpannableString.setSpan(any(), any(), any(), any()) } just Runs
    return mockSpannableString
}