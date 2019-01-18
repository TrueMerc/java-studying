package ru.ryabtsev.algorithms;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Simple graph implementation (simple graph contains neither loops nor multiple edges.
 * @param <Vertex> the vertices type.
 * @param <Edge> the edges type.
 */
public class SimpleGraph<Vertex, Edge> implements Graph<Vertex, Edge> {

    private final List<Vertex> vertices = ;

    @Override
    public Edge addEdge(Vertex source, Vertex target) {
        return null;
    }

    @Override
    public boolean containsEdge(Vertex source, Vertex target) {
        return false;
    }

    @Override
    public boolean addVertex(Vertex vertex) {
        return false;
    }

    @Override
    public boolean containsVertex(Vertex vertex) {
        return false;
    }

    @Override
    public Set<Edge> edgesSet() {
        return null;
    }

    @Override
    public Set<Vertex> verticesSet() {
        return null;
    }
}
