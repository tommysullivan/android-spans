package com.classdojo.android.spans

import android.support.annotation.StringRes
import com.classdojo.android.immutable.spannable.NodeReader
import com.classdojo.android.immutable.spannable.SpannableFactory
import com.classdojo.android.immutable.spannable.SpannableFactoryImpl
import com.classdojo.android.immutable.spannable.StyleMarker
import io.mockk.*
import org.junit.Test
import org.junit.Assert.*

typealias GetStringWithReplacements = (value:Int, replacements:List<String>) -> String

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

    //TODO: Support %d, %dm, %dd, %dh - tough because we would need to retrieve subSpans as number and date objects (now we have only fullText())

    @Test
    fun translatedStringWithTextAndStyleReplacements() {
        val mockSpannableString = mockk<SpannableString>()
        every { mockSpannableString.setSpan(any(), any(), any(), any()) } just Runs
        val spannableFactory = CustomSpannableFactory(mockSpannableString)
        val spans = spannableFactory.newContainerNodeBuilder()
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
        val actualSpannableString = translatedNode.asSpannableString()
        assertEquals(mockSpannableString, actualSpannableString )
    }

    class StringFormattedNode(
        private val pattern:String,
        private val replacements:List<NodeReader>,
        private val replacementIndex:Int
    ) : NodeReader {
        override fun fullText(): String {
            String.format(pattern, *replacements.map{r -> r.fullText()}.toTypedArray())
        }

        override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> {
            replacements[replacementIndex].styleMarkersFromOutermostToInnermost()
        }

        override fun asSpannableString(): SpannableString { //TODO: Dry this from BaseNode
            val spannableString = spannableFactory.newSpannableString(fullText())
            return styleMarkersFromOutermostToInnermost().fold(spannableString) {
                    spannableSoFar, styleMarker -> styleMarker.applyStyle(spannableSoFar)
            }
        }
    }

    class TranslatedNode(
        @StringRes private val stringResourceId:Int,
        private val substitutions:List<NodeReader>,
        private val spannableFactory: SpannableFactory,
        private val getStringResourceWithoutPerformingReplacements:(value:Int) -> String,
        private val getStringResourceWithReplacements:GetStringWithReplacements
    ) : NodeReader {

        override fun fullText(): String {
            return getStringResourceWithReplacements(
                stringResourceId,
                substitutions.map{s -> s.fullText()}
            )
        }

        override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> {
            //TODO: Either make newContainerNodeBuilder accept List<NodeReader>, or make TranslatedNode and StringFormattedNode implement NodeBuilder
            return spannableFactory.newContainerNodeBuilder(
                sections().map{section ->
                    if(section.isPlainText) spannableFactory.newTextNodeBuilder(section.text)
                    else StringFormattedNode(section.text, substitutions, section.replacementIndex)
                }
            ).build().styleMarkersFromOutermostToInnermost()
        }

        //TODO: Return List<{isPlainText:Boolean, text:String, replacementIndex:Int}
        fun sections():List<String> {
            val regexString = "(?<!%)(%\\d+\\\$(dd|dm|dh|[ds]))"
            val regex = Regex(regexString)
            val template = getStringResourceWithoutPerformingReplacements(stringResourceId)
            val zero = Pair(emptyList<String>(), 0)
            val parts = regex.findAll(template).fold(zero) { soFar, current ->
                val newIndex = current.range.endInclusive + 1
                val currentStartIndex = current.range.start
                if(currentStartIndex == soFar.second)
                    Pair(soFar.first + current.value, newIndex)
                else
                    Pair(soFar.first + template.substring(soFar.second, currentStartIndex) + current.value, newIndex)
            }
            return if(parts.second == template.lastIndex) parts.first
                   else parts.first + template.substring(parts.second)
        }

        override fun asSpannableString(): SpannableString { //TODO: Dry this from BaseNode
            val spannableString = spannableFactory.newSpannableString(fullText())
            return styleMarkersFromOutermostToInnermost().fold(spannableString) {
                    spannableSoFar, styleMarker -> styleMarker.applyStyle(spannableSoFar)
            }
        }
    }
}

