package ru.ryabtsev.algorithms;

import ru.ryabtsev.algorithms.graph.Edge;

import java.util.*;

/**
 * Implements breadth-first step algorithm to find the shortest way
 * between two vertices of the graph.
 * @param <V> type of vertices.
 * @param <E> type of edge.
 */
public class BreadthFirstSearch<V, E extends Edge> {

    private final Graph<V, E> graph;
    private final Queue<V> verticesQueue;
    private final Map<V, Edge> visitedVertices;

    /**
     * Constructs algorithm instance for given graph.
     * @param graph graph where algorithm will be work.
     */
    public BreadthFirstSearch(Graph<V, E> graph) {
        this.graph = graph;
        verticesQueue = new ArrayDeque<>();
        visitedVertices = new HashMap<>();
        final Set<V> verticesSet = graph.verticesSet();

        for( V vertex: verticesSet ) {
            visitedVertices.put( vertex, null );
        }
    }

    /**
     * Returns the set of vertices which represents the shortest way between given vertices
     * or null if this way doesn't exist.
     * @param source initial vertex of the way.
     * @param destination destination vertex of the way.
     * @return the set of vertices which represents the shortest way between given vertices.
     */
    Set<V> getShortestWay(V source, V destination) {
        if(!graph.containsVertex(source) || !graph.containsVertex(destination)) {
            return null;
        }

        initiate();

        verticesQueue.add(source);
        setVisited(source, new Edge());

        boolean isFound = false;

        while( !verticesQueue.isEmpty() ) {
            final V vertex = verticesQueue.poll();
            if(destination.equals(vertex)) {
                isFound = true;
                break;
            }

            Set<E> edges = graph.edgesOf(vertex);
            for( E edge : edges ) {
                final V nextVertex = (V)edge.getDestination(vertex);

                if( !isVisited(nextVertex) ) {
                    verticesQueue.add(nextVertex);
                    setVisited(nextVertex, edge);
                }
            }
        }

        if(isFound) {
            Deque<V> verticesDeque = new ArrayDeque<>();
            verticesDeque.addFirst(destination);
            V previous = (V)visitedVertices.get(destination).getDestination(destination);
            while(!source.equals(previous)) {
                verticesDeque.addFirst(previous);
                previous = (V)visitedVertices.get(previous).getDestination(previous);
            }
            verticesDeque.addFirst(source);

            Set<V> result = new LinkedHashSet<>();
            result.addAll(verticesDeque);
            return result;
        }
        return null;
    }

    private void initiate() {
        // First, clear vertices queue...
        verticesQueue.clear();

        // ..., second, mark all vertices as unvisited.
        for( V vertex : visitedVertices.keySet() ) {
            visitedVertices.put(vertex, null);
        }
    }

    private boolean isVisited(V vertex) {
        return visitedVertices.get(vertex) != null;
    }

    private void setVisited(V vertex, Edge from) {
        visitedVertices.put(vertex, from);
    }
}
