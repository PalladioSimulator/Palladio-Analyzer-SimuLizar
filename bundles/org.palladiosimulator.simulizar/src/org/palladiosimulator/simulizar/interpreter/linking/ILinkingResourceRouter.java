package org.palladiosimulator.simulizar.interpreter.linking;

import java.util.Optional;

/**
 * A linking resource router determines the connection between two nodes.
 * 
 * @param <NodeType>
 *            The type of the nodes
 * @param <LinkType>
 *            The tyoe of the links
 */
public interface ILinkingResourceRouter<NodeType, LinkType> {

    /**
     * Determines a series of links between transmissionSource and transmissionTarget.
     * 
     * @param transmissionSource
     *            the node where the transmission is originating
     * @param transmissionTarget
     *            the target node of the transmission
     * @return an {@link Optional} of an {@link Iterable} of links if there is a route between the
     *         nodes. The iterable may be empty, e. g. if source equals target. If there is no route
     *         between source and target an empty optional is returned.
     */
    Optional<Iterable<LinkType>> findRoute(NodeType transmissionSource, NodeType transmissionTarget);

}
