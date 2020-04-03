package org.palladiosimulator.simulizar.interpreter.linking;

public interface ILinkingResourceRouter<NodeType, LinkType> {

    Iterable<LinkType> findRoute(NodeType transmissionSource, NodeType transmissionTarget);

}
