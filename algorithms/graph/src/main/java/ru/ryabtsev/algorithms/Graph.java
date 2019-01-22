package ru.ryabtsev.algorithms;

import java.util.Set;

/**
 * Main interface for graph objects.
 */
public interface Graph<Vertex, Edge> {

    /**
     * Adds an edge between the source and target vertices.
     * @param source source vertex.
     * @param target target vertex.
     * @return new edge between vertices.
     */
    Edge addEdge(Vertex source, Vertex target);

    /**
     * Returns true if there is an edge between given vertices or false in other case.
     */
    boolean containsEdge(Vertex source, Vertex target);

    /**
     * Adds given vertex at this graph if this graph isn't contains it yet.
     * @param vertex vertex which should be added.
     * @return true if vertex is added successfully or false in the other case.
     */
    boolean addVertex(Vertex vertex);

    /**
     * Returns true if this graph contains given vertex and false in other case.
     * @return true if this graph contains given vertex and false in other case.
     */
    boolean containsVertex(Vertex vertex);

    /**
     * Returns the set of all edges touches specific vertex.
     * @return the set of all edges touches specific vertex.
     */
    Set<Edge> edgesOf(Vertex vertex);

    /**
     * Returns the set of edges contained in this graph.
     * @return the set of edges contained in this graph
     */
    Set<Edge> edgesSet();

    /**
     * Returns the set of edges contained in this graph.
     * @return the set of edges contained in this graph
     */
    Set<Vertex> verticesSet();
}
