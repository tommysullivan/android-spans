package com.classdojo.android.spans

import com.classdojo.android.immutable.spannable.SpannableFactoryImpl
import io.mockk.*
import org.junit.Test
import org.junit.Assert.*

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val mockSpannableString = mockk<SpannableString>()
        every { mockSpannableString.setSpan(any(), any(), any(), any()) } just Runs

        class CustomSpannableFactory() : SpannableFactoryImpl() {
            override fun newSpannableString(text: String): SpannableString {
                return mockSpannableString
            }
        }

        val spannableFactory = CustomSpannableFactory()
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
}

