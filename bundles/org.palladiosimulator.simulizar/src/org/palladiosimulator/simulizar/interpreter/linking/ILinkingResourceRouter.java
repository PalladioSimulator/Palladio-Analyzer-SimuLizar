package org.palladiosimulator.simulizar.interpreter.linking;

import java.util.Optional;

public interface ILinkingResourceRouter<NodeType, LinkType> {

    Optional<Iterable<LinkType>> findRoute(NodeType transmissionSource, NodeType transmissionTarget);

}
