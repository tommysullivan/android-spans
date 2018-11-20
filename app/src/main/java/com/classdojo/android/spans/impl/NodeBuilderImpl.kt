package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.NodeBuilder
import com.classdojo.android.spans.interfaces.NodeBuilderBasic
import com.classdojo.android.spans.interfaces.NodeBuilderEnhanced

class NodeBuilderImpl(
    nodeBuilderEnhanced: NodeBuilderEnhanced<NodeBuilder>,
    nodeBuilderBasic: NodeBuilderBasic<NodeBuilder>
) : NodeBuilderEnhanced<NodeBuilder> by nodeBuilderEnhanced, NodeBuilderBasic<NodeBuilder> by nodeBuilderBasic, NodeBuilder