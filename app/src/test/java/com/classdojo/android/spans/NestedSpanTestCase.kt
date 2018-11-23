package com.classdojo.android.spans

import io.mockk.verify
import org.junit.Assert
import org.junit.Test

class NestedSpanTestCase {
    @Test
    fun spannableBasicTestCase() {
        val mockSpannableString = newMockSpannableString()
        val spansForTesting = SpansForTesting(mockSpannableString) { _ -> throw NotImplementedError() }
        val emptySpan = spansForTesting.newEmptySpan()
        val spanUnderTest = emptySpan
            .addText("Tommy")
            .addStyledText(" is red") {it.hasColor.red}
            .addText(" but not blue")
            .addStyledSpan(
                {it.hasColor.green.onClick{-> Unit}},
                emptySpan.addText("green text")
            )

        Assert.assertEquals("Tommy is red but not bluegreen text", spanUnderTest.fullText())
        Assert.assertNotNull(spanUnderTest.asSpannableString())
        verify { mockSpannableString.setSpan(any(), 5, 12, any()) }
    }
}