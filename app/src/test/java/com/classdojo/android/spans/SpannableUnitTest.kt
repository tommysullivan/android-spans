package com.classdojo.android.spans

import com.classdojo.android.spans.impl.SpannableFactoryImpl
import com.classdojo.android.spans.impl.SpannableFactoryImplWithReader
import com.classdojo.android.spans.interfaces.SpannableString
import com.classdojo.android.spans.interfaces.SpannableStringFactory
import com.classdojo.android.spans.interfaces.StyleMarker
import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

//TODO: Is there an anonymous way to do this? how about via MockK?
class SpannableFactoryImplTest(
    private val mockSpannableString:SpannableString,
    getStringResourceWithoutPerformingReplacements:(resourceId:Int) -> String
) : SpannableFactoryImplWithReader(getStringResourceWithoutPerformingReplacements) {
    override fun newSpannableString(text: String): SpannableString = mockSpannableString
}

//TODO: DRY up these tests since we repeat nearly the same thing
class SpannableUnitTest {
    class MockSpannableStringFactory(private val mockSpannableString: SpannableString) : SpannableStringFactory {
        override fun newSpannableString(text: String):SpannableString = mockSpannableString
    }

    @Test
    fun spannableBasicTestCase() {
        val mockSpannableString = mockk<SpannableString>()
        every { mockSpannableString.setSpan(any(), any(), any(), any()) } just Runs
        val spannableFactory = SpannableFactoryImplTest(mockSpannableString) { _ -> throw NotImplementedError() }
        val spans = spannableFactory.newTextNodeBuilder()
        val styles = spannableFactory.newStyleBuilder()
        val span = spans
            .addText("Tommy")
            .addStyledText(styles.color().red().build(), " is red")
            .addText(" but not blue")
            .addStyledSpan(
                styles.color().green().onClick{-> Unit}.build(),
                spans.addText("green text")
            )

        assertEquals("Tommy is red but not bluegreen text", span.fullText())
        assertNotNull(span.asSpannableString())
        verify { mockSpannableString.setSpan(any(), 5, 12, any()) }
    }

    @Test
    fun translationWithNoReplacements() {
        val mockSpannableString = mockk<SpannableString>()
        every { mockSpannableString.setSpan(any(), any(), any(), any()) } just Runs
        val template = "sfidhsiduhs"
        val spannableFactory = SpannableFactoryImplTest(mockSpannableString) { _ -> template }
        val translatedNode = spannableFactory.newTextNodeBuilder().addTranslatedText(1)
        assertEquals(template, translatedNode.fullText())
        assertEquals(emptyList<StyleMarker>(), translatedNode.styleMarkersFromOutermostToInnermost())
    }

    @Test
    fun translatedStringWithTextAndStyleReplacements() {
        val mockSpannableString = mockk<SpannableString>()
        every { mockSpannableString.setSpan(any(), any(), any(), any()) } just Runs
        val template = "tom%1\$smike%1\$s%2\$smyes%%3\$s"
        val spannableFactory = SpannableFactoryImplTest(mockSpannableString) { _ -> template }
        val spans = spannableFactory.newTextNodeBuilder()
        val styles = spannableFactory.newStyleBuilder()
        val translatedNode = spans.addTranslatedText(
            1,
            spans.addText(" swe").addText("nu "),
            spans.addStyledText(styles.color().red().build(), " flaenu ")
        )

        assertEquals("tom swenu mike swenu  flaenu myes%3\$s", translatedNode.fullText())

        //TODO: This could be a unit test of TranslatedNode
//        assertEquals(listOf("tom", "%1\$s", "mike", "%1\$s", "%2\$s", "myes", "%", "3\$s"), translatedNode.sections().map{s -> s.text})

        //TODO: Assert appropriate styles in the right places
//        assertEquals(emptyList<StyleMarker>(), translatedNode.styleMarkersFromOutermostToInnermost())
    }
}