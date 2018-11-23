package com.classdojo.android.spans

import com.classdojo.android.spans.impl.SpansImpl
import com.classdojo.android.spans.interfaces.SpannableString
import com.classdojo.android.spans.interfaces.StyleMarker
import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class SpansForTesting(
    private val mockSpannableString:SpannableString,
    getStringResourceWithoutPerformingReplacements:(resourceId:Int) -> String
) : SpansImpl(getStringResourceWithoutPerformingReplacements) {
    override fun newSpannableString(text: String): SpannableString = mockSpannableString
}

class SpannableUnitTest {
    fun newMockSpannableString():SpannableString {
        val mockSpannableString = mockk<SpannableString>()
        every { mockSpannableString.setSpan(any(), any(), any(), any()) } just Runs
        return mockSpannableString
    }

    @Test
    fun spannableBasicTestCase() {
        val mockSpannableString = newMockSpannableString()
        val spansForTesting = SpansForTesting(mockSpannableString) { _ -> throw NotImplementedError() }
        val emptySpan = spansForTesting.newEmptySpan()
        val styles = spansForTesting.styles()
        val span = emptySpan
            .addText("Tommy")
            .addStyledText(styles.color().red(), " is red")
            .addText(" but not blue")
            .addStyledSpan(
                styles.color().green().onClick{-> Unit},
                emptySpan.addText("green text")
            )

        assertEquals("Tommy is red but not bluegreen text", span.fullText())
        assertNotNull(span.asSpannableString())
        verify { mockSpannableString.setSpan(any(), 5, 12, any()) }
    }

    @Test
    fun translationWithNoReplacements() {
        val mockSpannableString = newMockSpannableString()
        val template = "sfidhsiduhs"
        val spansForTesting = SpansForTesting(mockSpannableString) { _ -> template }
        val spanWithTranslatedText = spansForTesting.newEmptySpan().addTranslatedText(1)
        assertEquals(template, spanWithTranslatedText.fullText())
        assertEquals(emptyList<StyleMarker>(), spanWithTranslatedText.styleMarkersFromOutermostToInnermost())
    }

    @Test
    fun translatedStringWithTextAndStyleReplacements() {
        val mockSpannableString = mockk<SpannableString>()
        every { mockSpannableString.setSpan(any(), any(), any(), any()) } just Runs
        val template = "tom%1\$smike%1\$s%2\$smyes%%3\$s"
        val spansForTesting = SpansForTesting(mockSpannableString) { _ -> template }
        val emptySpan = spansForTesting.newEmptySpan()
        val styles = spansForTesting.styles()
        val translatedNode = emptySpan.addTranslatedText(
            1,
            emptySpan.addText(" swe").addText("nu "),
            emptySpan.addStyledText(styles.color().red(), " flaenu ")
        )
        assertEquals("tom swenu mike swenu  flaenu myes%3\$s", translatedNode.fullText())
    }
}