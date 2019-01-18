package ru.ryabtsev.algorithms.graph;

/**
 * Graph edge base implementation.
 */
public class Edge {

    private Object source;
    private Object target;

    /**
     * Creates new edge without source and target.
     */
    public Edge() {
        this.source = null;
        this.target = null;
    }

    /**
     * Creates new edge with given source and target.
     * @param source edge source vertex.
     * @param target edge target vertex.
     */
    Edge(Object source, Object target) {
        this.source = source;
        this.target = target;
    }

    /**
     * Return the source vertex of this edge.
     * @return the source vertex of this edge.
     */
    public Object getSource() {
        return source;
    }

    /**
     * Return the target vertex of this edge.
     * @return the target vertex of this edge.
     */
    public Object getTarget() {
        return target;
    }

    /**
     * Resets edge source and target vertices.
     * @param source graph source vertex.
     * @param target graph target vertex.
     */
    public void reset(Object source, Object target) {
        this.source = source;
        this.target = target;
    }
}
