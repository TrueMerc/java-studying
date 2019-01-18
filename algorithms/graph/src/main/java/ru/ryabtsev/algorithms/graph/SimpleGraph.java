package ru.ryabtsev.algorithms.graph;

import ru.ryabtsev.algorithms.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Simple graph implementation (simple graph contains neither loops nor multiple edges.
 * @param <VertexType> the vertices type.
 * @param <EdgeType> the edges type.
 */
public class SimpleGraph<VertexType, EdgeType extends Edge> implements Graph<VertexType, EdgeType> {

    private final Map<VertexType, Set<EdgeType> > graph;
    private final Supplier<EdgeType> edgeSupplier;

    /**
     * Creates simple graph instance.
     */
    public SimpleGraph(Supplier<EdgeType> edgeSupplier) {
        graph = new HashMap<>();
        this.edgeSupplier = edgeSupplier;
    }

    @Override
    public EdgeType addEdge(VertexType source, VertexType target) {
        if(!containsVertex(source) || !containsVertex(target) || source.equals(target)) {
            return null;
        }

        if(containsEdge(source, target)) {
            final Set<EdgeType> edges = graph.get(source);
            for(EdgeType edge : edges) {
                if( target.equals(edge.getTarget()) ) {
                    return edge;
                }
            }
        }

        EdgeType result = edgeSupplier.get();
        result.reset(source, target);

        graph.get(source).add(result);
        graph.get(target).add(result);
        return result;
    }

    @Override
    public boolean containsEdge(VertexType source, VertexType target) {
        return graph.get(source).contains(target);
    }

    @Override
    public boolean addVertex(VertexType vertex) {
        boolean result = !containsVertex(vertex);
        if( result ) {
            graph.put(vertex, new HashSet<>());
        }
        return result;
    }

    @Override
    public boolean containsVertex(VertexType vertex) {
        return graph.containsKey(vertex);
    }

    @Override
    public Set<EdgeType> edgesOf(VertexType vertexType) {
        return graph.get(vertexType);
    }

    @Override
    public Set<EdgeType> edgesSet() {
        final Set< Map.Entry<VertexType, Set<EdgeType>> > set = graph.entrySet();
        if(set == null) {
            return null;
        }

        final Set<EdgeType> edges = new HashSet<>();

        for( Map.Entry<VertexType, Set<EdgeType>> entry: set) {
            edges.addAll( entry.getValue() );
        }
        return edges;
    }

    @Override
    public Set<VertexType> verticesSet() {
        return graph.keySet();
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        final Set<VertexType> vertices = this.verticesSet();

        for(VertexType vertex : vertices) {
            stringBuilder.append(vertex.toString() + ":");
            final Set<EdgeType> edges = this.edgesOf(vertex);

            for( EdgeType edge : edges ) {
                if( edge.getSource().equals(vertex)) {
                    stringBuilder.append(" " + edge.getTarget().toString());
                }
                else {
                    stringBuilder.append(" " + edge.getSource().toString());
                }
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
