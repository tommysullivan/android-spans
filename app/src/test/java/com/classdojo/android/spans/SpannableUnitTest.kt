package com.classdojo.android.spans

import com.classdojo.android.spans.impl.SpansImpl
import com.classdojo.android.spans.interfaces.SpannableString
import com.classdojo.android.spans.interfaces.StyleMarker
import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

//TODO: Is there an anonymous way to do this? how about via MockK?
class SpansImplTest(
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
        val spannableFactory = SpansImplTest(mockSpannableString) { _ -> throw NotImplementedError() }
        val spans = spannableFactory.newTextNodeBuilder()
        val styles = spannableFactory.styles()
        val span = spans
            .addText("Tommy")
            .addStyledText(styles.color().red(), " is red")
            .addText(" but not blue")
            .addStyledSpan(
                styles.color().green().onClick{-> Unit},
                spans.addText("green text")
            )

        assertEquals("Tommy is red but not bluegreen text", span.fullText())
        assertNotNull(span.asSpannableString())
        verify { mockSpannableString.setSpan(any(), 5, 12, any()) }
    }

    @Test
    fun translationWithNoReplacements() {
        val mockSpannableString = newMockSpannableString()
        val template = "sfidhsiduhs"
        val spannableFactory = SpansImplTest(mockSpannableString) { _ -> template }
        val translatedNode = spannableFactory.newTextNodeBuilder().addTranslatedText(1)
        assertEquals(template, translatedNode.fullText())
        assertEquals(emptyList<StyleMarker>(), translatedNode.styleMarkersFromOutermostToInnermost())
    }

    @Test
    fun translatedStringWithTextAndStyleReplacements() {
        val mockSpannableString = mockk<SpannableString>()
        every { mockSpannableString.setSpan(any(), any(), any(), any()) } just Runs
        val template = "tom%1\$smike%1\$s%2\$smyes%%3\$s"
        val spannableFactory = SpansImplTest(mockSpannableString) { _ -> template }
        val spans = spannableFactory.newTextNodeBuilder()
        val styles = spannableFactory.styles()
        val translatedNode = spans.addTranslatedText(
            1,
            spans.addText(" swe").addText("nu "),
            spans.addStyledText(styles.color().red(), " flaenu ")
        )
        assertEquals("tom swenu mike swenu  flaenu myes%3\$s", translatedNode.fullText())
    }
}