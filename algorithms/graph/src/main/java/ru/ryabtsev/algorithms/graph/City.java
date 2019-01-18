package ru.ryabtsev.algorithms.graph;

/**
 * Dummy class for {@link ru.ryabtsev.algorithms.Graph} testing.
 */
public class City {

    private String name;

    /**
     * Creates new city with given name.
     * @param name
     */
    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
