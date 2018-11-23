package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

open class SpansImpl(
    private val getStringResourceWithoutPerformingReplacements:(resourceId:Int) -> String
) : Spans {

    override fun newSpannableString(text: String): SpannableString {
        return SpannableStringImpl(text)
    }

    fun newSpan(b:SpanWriter<Span>, reader:SpanReader):Span = SpanImpl(b, reader)

    override fun newStyledTextReader(text: String): StyledTextReader {
        return StyledTextReaderImpl(text)
    }

    override fun newSpanBuilderForSequence(sequenceOfStyledTextReadersToBuildUpon: List<StyledTextReader>): Span {
        return newT(
            SubspannableImpl<Span>(
                this,
                this,
                sequenceOfStyledTextReadersToBuildUpon
            ),
            newNodeReader(newStyledTextReaderFromSequence(sequenceOfStyledTextReadersToBuildUpon))
        )
    }

    private fun newT(subspannable: Subspannable<Span>, spanReader:SpanReader): Span {
        return newSpan(newNodeBuilderEnhanced(subspannable, spanReader), spanReader)
    }

    private fun newNodeBuilderEnhanced(subspannable: Subspannable<Span>, spanReader:SpanReader):SpanWriter<Span> {
        return SpanWriterImpl<Span>(
            newNodeBuilderTextHelpersImpl(subspannable),
            subspannable,
            TranslatedTextWriterImpl(this, subspannable),
            ConvertibleToReadOnlySpanImpl(spanReader)
        )
    }

    private fun newNodeBuilderTextHelpersImpl(subspannable: Subspannable<Span>) =
        SpanTextWriterImpl(this, subspannable)

    private fun newNodeReader(nodeReaderBasic:StyledTextReader):SpanReader {
        return SpanReaderImpl(
            ConvertibleToSpannableStringImpl(this, nodeReaderBasic),
            nodeReaderBasic
        )
    }

    override fun newStyledTextReaderFromSequence(sequenceOfStyledTextReadersToGroupIntoSingleReader: List<StyledTextReader>): StyledTextReader {
        return SubspannableReader(sequenceOfStyledTextReadersToGroupIntoSingleReader)
    }

    override fun newStyledSpanBuilder(styleReader: StyleReader, styledTextReaderToStyleFurther: StyledTextReader): Span {
        return newT(
            StyledNodeBuilderImpl(
                this,
                this,
                styleReader,
                styledTextReaderToStyleFurther
            ),
            newNodeReader(newStyledTextReaderWithExplicitStyle(styleReader, styledTextReaderToStyleFurther))
        )
    }

    override fun newStyledTextReaderWithExplicitStyle(styleReader: StyleReader, styledTextReaderToStyleFurther: StyledTextReader): StyledTextReader {
        return StyledNodeReaderImpl(this, styledTextReaderToStyleFurther, styleReader)
    }

    override fun newStyleMarker(startIndex: Int, length: Int, styleReader: StyleReader): StyleMarker {
        return StyleMarkerImpl(startIndex, length, styleReader, this)
    }

    override fun styleBuilder(): StyleBuilder {
        return styles()
    }

    override fun styleBuilder(crappyAndroidStyleObjects: List<Any>): StyleBuilder {
        return newStyle(crappyAndroidStyleObjects)
    }

    override fun styles():Styles {
        return newStyle(emptyList())
    }

    fun newStyle(crappyAndroidStyleObjects: List<Any>):Styles {
        return StyleBuilderImpl(crappyAndroidStyleObjects, this)
    }

    override fun newTextNodeBuilder(): Span {
        return newTextNodeBuilder("")
    }

    override fun newTextNodeBuilder(text: String): Span {
        val textNodeReader = newNodeReader(newStyledTextReader(text))
        return newT(
            TextNodeBuilder<Span>(
                this,
                this,
                textNodeReader
            ),
            textNodeReader
        )
    }

    override fun newTranslatedStyledTextReader(stringResourceIdOfTranslationWithPossiblePlacholders: Int, vararg styledTextToSubstituteInForPlaceholders: StyledTextReader): StyledTextReader {
        return LazilyTranslatedAndReplacedTextReader(
            stringResourceIdOfTranslationWithPossiblePlacholders,
            listOf(*styledTextToSubstituteInForPlaceholders),
            getStringResourceWithoutPerformingReplacements,
            this,
            this
        )
    }
}