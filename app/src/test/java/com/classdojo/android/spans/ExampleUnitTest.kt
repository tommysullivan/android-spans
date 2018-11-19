package com.classdojo.android.spans

import android.support.annotation.StringRes
import com.classdojo.android.immutable.spannable.NodeReader
import com.classdojo.android.immutable.spannable.SpannableFactoryImpl
import com.classdojo.android.immutable.spannable.StyleMarker
import io.mockk.*
import org.junit.Test
import org.junit.Assert.*

class ExampleUnitTest {
    class CustomSpannableFactory(private val mockSpannableString:SpannableString) : SpannableFactoryImpl() {
        override fun newSpannableString(text: String) = mockSpannableString
    }

    @Test
    fun spannableBasicTestCase() {
        val mockSpannableString = mockk<SpannableString>()
        every { mockSpannableString.setSpan(any(), any(), any(), any()) } just Runs
        val spannableFactory = CustomSpannableFactory(mockSpannableString)
        val spans = spannableFactory.newContainerNodeBuilder()
        val styles = spannableFactory.newStyleBuilder()

        val span = spans
            .addText("Tommy")
            .addStyledText(styles.color().red(), " is red")
            .addText(" but not blue")
            .build()

        assertEquals("Tommy is red but not blue", span.fullText())
        assertNotNull(span.asSpannableString())
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
        val actualSpannableString = translatedNode.asSpannableString()
        assertEquals(mockSpannableString, actualSpannableString )
    }

    //TODO: Support %d, %dm, %dd, %dh

    @Test
    fun translatedStringWithTextAndStyleReplacements() {
        val mockSpannableString = mockk<SpannableString>()
        every { mockSpannableString.setSpan(any(), any(), any(), any()) } just Runs
        val spannableFactory = CustomSpannableFactory(mockSpannableString)
        val spans = spannableFactory.newContainerNodeBuilder()
        val styles = spannableFactory.newStyleBuilder()
        val template = "tom%1\$smike%1\$s%2\$smyes"
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
        assertEquals("tom swenu mike swenu  flaenu myes", translatedNode.fullText())
        assertEquals(emptyList<StyleMarker>(), translatedNode.styleMarkersFromOutermostToInnermost())
        val actualSpannableString = translatedNode.asSpannableString()
        assertEquals(mockSpannableString, actualSpannableString )
    }

    class TranslatedNode(
        @StringRes private val stringResourceId:Int,
        private val substitutions:List<NodeReader>,
        private val spannableStringFactory:SpannableStringFactory,
        private val getStringResourceWithoutPerformingReplacements:(value:Int) -> String,
        private val getStringResourceWithReplacements:(value:Int, replacements:List<String>) -> String
    ) : NodeReader {

        override fun fullText(): String {
            return getStringResourceWithReplacements(
                stringResourceId,
                substitutions.map{s -> s.fullText()}
            )
        }

        override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> { //TODO: Complete
            return emptyList()
        }

        override fun asSpannableString(): SpannableString { //TODO: Dry this from BaseNode
            val spannableString = spannableStringFactory.newSpannableString(fullText())
            return styleMarkersFromOutermostToInnermost().fold(spannableString) {
                    spannableSoFar, styleMarker -> styleMarker.applyStyle(spannableSoFar)
            }
        }
    }
}

