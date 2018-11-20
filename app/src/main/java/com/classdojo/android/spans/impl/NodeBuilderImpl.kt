//package com.classdojo.android.spans.impl
//
//import com.classdojo.android.spans.interfaces.NodeBuilder
//import com.classdojo.android.spans.interfaces.NodeBuilderBasic
//import com.classdojo.android.spans.interfaces.NodeBuilderEnhanced
//
//class NodeBuilderImpl(
//    nodeBuilderEnhanced: NodeBuilderEnhanced,
//    nodeBuilderBasic: NodeBuilderBasic<NodeBuilder>
//) : NodeBuilderEnhanced by nodeBuilderEnhanced, NodeBuilderBasic<NodeBuilder> by nodeBuilderBasic, NodeBuilder