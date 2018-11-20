package com.classdojo.android.spans

import com.classdojo.android.spans.impl.SpannableFactoryImpl
import com.classdojo.android.spans.impl.TranslatedNode
import com.classdojo.android.spans.interfaces.SpannableString
import com.classdojo.android.spans.interfaces.StyleMarker
import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Test

//TODO: Support %d, %dm, %dd, %dh - tough because we would need to retrieve subSpans as number and date objects (now we have only fullText())
//TODO: DRY up these tests since we repeat nearly the same thing
class ExampleUnitTest {
    class CustomSpannableFactory(private val mockSpannableString: SpannableString) : SpannableFactoryImpl() {
        override fun newSpannableString(text: String):SpannableString = mockSpannableString
    }

    @Test
    fun spannableBasicTestCase() {
        val mockSpannableString = mockk<SpannableString>()
        every { mockSpannableString.setSpan(any(), any(), any(), any()) } just Runs
        val spannableFactory = CustomSpannableFactory(mockSpannableString)
        val spans = spannableFactory.newNodeBuilder()
        val styles = spannableFactory.newStyleBuilder()

        val span = spans
            .addText("Tommy")
            .addStyledText(styles.color().red(), " is red")
            .addText(" but not blue")
            .addStyledSpan(styles.color().green().build(), spans.addText("green text").build())
            .build()

        assertEquals("Tommy is red but not blue", span.fullText())
//        assertNotNull(span.asSpannableString())
        verify { mockSpannableString.setSpan(any(), 5, 12, any()) }
    }

    @Test
    fun translationWithNoReplacements() {
        val mockSpannableString = mockk<SpannableString>()
        every { mockSpannableString.setSpan(any(), any(), any(), any()) } just Runs
        val spannableFactory = CustomSpannableFactory(mockSpannableString)
        val translatedNode = TranslatedNode(
            1,
            emptyList(),
            spannableFactory,
            { i -> "sfidhsiduhs" },
            { i, _ -> "sfidhsiduhs" }
        )
        assertEquals("sfidhsiduhs", translatedNode.fullText())
        assertEquals(emptyList<StyleMarker>(), translatedNode.styleMarkersFromOutermostToInnermost())
    }

    @Test
    fun translatedStringWithTextAndStyleReplacements() {
        val mockSpannableString = mockk<SpannableString>()
        every { mockSpannableString.setSpan(any(), any(), any(), any()) } just Runs
        val spannableFactory = CustomSpannableFactory(mockSpannableString)
        val spans = spannableFactory.newNodeBuilder()
        val styles = spannableFactory.newStyleBuilder()
        val template = "tom%1\$smike%1\$s%2\$smyes%%3\$s"
        val translatedNode = TranslatedNode(
            1,
            listOf(
                spans.addText(" swe").addText("nu ").build(),
                spans.addStyledText(styles.color().red(), " flaenu ").build()
            ),
            spannableFactory,
            { i -> template },
            { i, replacements -> String.format(template, *replacements.toTypedArray()) }
        )
        assertEquals("tom swenu mike swenu  flaenu myes%3\$s", translatedNode.fullText())
        assertEquals(emptyList<StyleMarker>(), translatedNode.styleMarkersFromOutermostToInnermost())
        assertEquals(listOf("tom", "%1\$s", "mike", "%1\$s", "%2\$s", "myes%%3\$s"), translatedNode.sections())
    }
}

