package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

//TODO: Combine the factories into a single open factory
open class SpansImplWithReader(
    val getStringResourceWithoutPerformingReplacements:(resourceId:Int) -> String
) : SpansImplT<Span>(getStringResourceWithoutPerformingReplacements)  {
    override val factory = { b:SpanWriter<Span>, reader:SpanReader -> SpanImpl(b, reader) }
}

abstract class SpansImplT<T>(
    private val getStringResourceWithoutPerformingReplacements:(resourceId:Int) -> String
) : Spans<T> {
    abstract val factory:(spanBuilder:SpanWriter<T>, spanReader:SpanReader) -> T

    override fun newSpannableString(text: String): SpannableString {
        return OurSpannableString(text)
    }

    override fun newTextNodeReader(text: String): StyledTextReader {
        return StyledTextReaderImpl(text)
    }

    override fun newSpanSequenceBuilder(sequenceOfStyledTextReadersToBuildUpon: List<StyledTextReader>): T {
        return newT(
            ContainerNodeBuilder<T>(
                this,
                this,
                sequenceOfStyledTextReadersToBuildUpon
            ),
            newNodeReader(newStyledTextReaderFromSequence(sequenceOfStyledTextReadersToBuildUpon))
        )
    }

    private fun newT(subspanContainer: SubspanContainer<T>, spanReader:SpanReader):T {
        return factory(newNodeBuilderEnhanced(subspanContainer, spanReader), spanReader)
    }

    private fun newNodeBuilderEnhanced(subspanContainer: SubspanContainer<T>, spanReader:SpanReader):SpanWriter<T> {
        return SpanWriterImpl<T>(
            newNodeBuilderTextHelpersImpl(subspanContainer),
            subspanContainer,
            TranslatedTextWriterImpl(this, subspanContainer),
            ConvertibleToReadOnlySpanImpl(spanReader)
        )
    }

    private fun newNodeBuilderTextHelpersImpl(subspanContainer: SubspanContainer<T>) =
        SpanTextWriterImpl(this, this, subspanContainer)

    private fun newNodeReader(nodeReaderBasic:StyledTextReader):SpanReader {
        return SpanReaderImpl(
            ConvertibleToSpannableStringImpl(this, nodeReaderBasic),
            nodeReaderBasic
        )
    }

    override fun newStyledTextReaderFromSequence(sequenceOfStyledTextReadersToGroupIntoSingleReader: List<StyledTextReader>): StyledTextReader {
        return ContainerNodeReaderImpl(sequenceOfStyledTextReadersToGroupIntoSingleReader)
    }

    override fun newStyledNodeBuilder(styleReader: StyleReader, styledTextReaderToStyleFurther: StyledTextReader): T {
        return newT(
            StyledNodeBuilderImpl(
                this,
                this,
                styleReader,
                styledTextReaderToStyleFurther
            ),
            newNodeReader(newStyledNodeReader(styleReader, styledTextReaderToStyleFurther))
        )
    }

    override fun newStyledNodeReader(styleReader: StyleReader, styledTextReaderToStyleFurther: StyledTextReader): StyledTextReader {
        return StyledNodeReaderImpl(this, styledTextReaderToStyleFurther, styleReader)
    }

    override fun newStyleMarker(startIndex: Int, length: Int, styleBuilder: StyleReader): StyleMarker {
        return StyleMarkerImpl(startIndex, length, styleBuilder, this)
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

    override fun newTextNodeBuilder(): T {
        return newTextNodeBuilder("")
    }

    override fun newTextNodeBuilder(text: String): T {
        val textNodeReader = newNodeReader(newTextNodeReader(text))
        return newT(
            TextNodeBuilder<T>(
                this,
                this,
                textNodeReader
            ),
            textNodeReader
        )
    }

    override fun newTranslatedStyledTextReader(stringResourceIdOfTranslationWithPossiblePlacholders: Int, vararg styledTextToSubstituteInForPlaceholders: StyledTextReader): StyledTextReader {
        return TranslatedNode(
            stringResourceIdOfTranslationWithPossiblePlacholders,
            listOf(*styledTextToSubstituteInForPlaceholders),
            getStringResourceWithoutPerformingReplacements,
            this,
            this
        )
    }
}