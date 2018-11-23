package com.classdojo.android.spans

import com.classdojo.android.spans.interfaces.SpannableString
import com.classdojo.android.spans.interfaces.StyleMarker
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class TranslatedSpanTestCases {

    @Test
    fun translationWithNoReplacements() {
        val mockSpannableString = newMockSpannableString()
        val template = "sfidhsiduhs"
        val spansForTesting = SpansForTesting(mockSpannableString) { _ -> template }
        val spanUnderTest = spansForTesting.newEmptySpan().addTranslatedText(1)
        assertEquals(template, spanUnderTest.fullText())
        assertEquals(emptyList<StyleMarker>(), spanUnderTest.styleMarkersFromOutermostToInnermost())
    }

    @Test
    fun translatedStringWithTextAndStyleReplacements() {
        val mockSpannableString = mockk<SpannableString>()
        every { mockSpannableString.setSpan(any(), any(), any(), any()) } just Runs
        val template = "tom%1\$smike%1\$s%2\$smyes%%3\$s"
        val spansForTesting = SpansForTesting(mockSpannableString) { _ -> template }
        val emptySpan = spansForTesting.newEmptySpan()
        val styles = spansForTesting.styles()
        val spanUnderTest = emptySpan.addTranslatedText(
            1,
            emptySpan.addText(" swe").addText("nu "),
            emptySpan.addStyledText(styles.color.red, " flaenu ")
        )
        assertEquals("tom swenu mike swenu  flaenu myes%3\$s", spanUnderTest.fullText())
    }
}