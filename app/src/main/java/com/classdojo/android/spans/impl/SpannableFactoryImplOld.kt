//package com.classdojo.android.spans.impl
//
//import com.classdojo.android.spans.interfaces.*
//
//open class SpannableFactoryImpl(
//    containerNodeFactoryImpl:ContainerNodeFactory<NodeBuilder>,
//    styledNodeFactoryImpl:StyledNodeFactory<NodeBuilder>,
//    styleMarkerFactory:StyleMarkerFactory,
//    styleBuilderFactory:StyleBuilderFactory,
//    textNodeFactory:TextNodeFactory,
//    spannableStringFactory:SpannableStringFactory
//) :
//    SpannableFactory<NodeBuilder>,
//    ContainerNodeFactory<NodeBuilder> by containerNodeFactoryImpl,
//    StyledNodeFactory<NodeBuilder> by styledNodeFactoryImpl,
//    StyleMarkerFactory by styleMarkerFactory,
//    StyleBuilderFactory by styleBuilderFactory,
//    TextNodeFactory by textNodeFactory,
//    SpannableStringFactory by spannableStringFactory
//{
//    override fun newNodeBuilder(): NodeBuilder {
//        throw NotImplementedError()
//    }
//}
//
//class SpannableStringFactoryImpl : SpannableStringFactory {
//    override fun newSpannableString(text: String):SpannableString = OurSpannableString(text)
//}
//
//class TextNodeFactoryImpl : TextNodeFactory {
//    override fun newTextNodeReader(text: String): NodeReaderBasic = TextNodeReader(text)
//}
//
//class StyleBuilderFactoryImpl : StyleBuilderFactory {
//    override fun newStyleBuilder(): StyleBuilder = newStyleBuilder(emptyList())
//    override fun newStyleBuilder(crappyAndroidStyleObjects: List<Any>): StyleBuilder = StyleBuilderImpl(crappyAndroidStyleObjects, this)
//}
//
//class StyleMarkerFactoryImpl : StyleMarkerFactory {
//    override fun newStyleMarker(startIndex: Int, length: Int, styleReader: StyleReader): StyleMarker {
//        return StyleMarkerImpl(startIndex, length, styleReader, this)
//    }
//}
//
//class ContainerNodeFactoryImpl(
//    private val styledNodeFactory: StyledNodeFactory<NodeBuilder>,
//    private val styleBuilderFactory:StyleBuilderFactory,
//    private val textNodeFactory:TextNodeFactory
//) : ContainerNodeFactory<NodeBuilder> {
//    override fun newContainerNodeBuilder(childNodes: List<NodeReaderBasic>): NodeBuilder {
//        val containerNodeBuilder = ContainerNodeBuilder(styledNodeFactory, this, childNodes, newContainerNodeReader(childNodes))
//        return NodeBuilderEnhancedImpl(styleBuilderFactory, textNodeFactory, containerNodeBuilder)
//    }
//
//    override fun newContainerNodeReader(childNodeReaders: List<NodeReaderBasic>):NodeReaderBasic = ContainerNodeReaderImpl(childNodeReaders)
//}
//
//class StyledNodeFactoryImpl(
//    private val styleMarkerFactory: StyleMarkerFactory,
//    private val containerNodeFactory:ContainerNodeFactory<NodeBuilder>,
//    private val styleBuilderFactory:StyleBuilderFactory,
//    private val textNodeFactory:TextNodeFactory
//) : StyledNodeFactory<NodeBuilder> {
//    override fun newStyledNodeBuilder(styleReader: StyleReader, nodeToStyle: NodeReaderBasic): NodeBuilder {
//        val styledNodeBuilder = StyledNodeBuilderImpl(this, containerNodeFactory, styleReader, newStyledNodeReader(styleReader, nodeToStyle))
//        return NodeBuilderEnhancedImpl(styleBuilderFactory, textNodeFactory, styledNodeBuilder)
//    }
//
//    override fun newStyledNodeReader(styleReader: StyleReader, nodeToStyle: NodeReaderBasic): NodeReaderBasic {
//        return StyledNodeReaderImpl(styleMarkerFactory, nodeToStyle, styleReader)
//    }
//}