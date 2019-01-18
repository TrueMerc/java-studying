package ru.ryabtsev.algorithms;

import org.junit.Assert;
import org.junit.Test;
import ru.ryabtsev.algorithms.graph.City;
import ru.ryabtsev.algorithms.graph.Edge;
import ru.ryabtsev.algorithms.graph.SimpleGraph;

import java.util.function.Supplier;

/**
 * Unit test for simple MainApplication.
 */
public class GraphTest
{
    private Supplier<Edge> edgeSupplier = new Supplier<Edge>() {
        @Override
        public Edge get() {
            return new Edge();
        }
    };

    /**
     * Empty graph creation test.
     */
    @Test
    public void createGraphTest()
    {
        final Graph<City, Edge> citiesGraph = new SimpleGraph<>(edgeSupplier);
        Assert.assertTrue(citiesGraph.edgesSet().isEmpty());
        Assert.assertTrue(citiesGraph.verticesSet().isEmpty());
    }

    /**
     * Graph vertices addition test.
     */
    @Test
    public void addVerticesTest() {
        final Graph<City, Edge> citiesGraph = new SimpleGraph<>(edgeSupplier);

        final City moscow = new City("Moscow");
        final City stPetersburg = new City( "St. Petersburg");
        final City novosibirsk = new City("Novosibirsk");

        Assert.assertTrue(citiesGraph.addVertex(moscow));
        Assert.assertTrue(citiesGraph.addVertex(stPetersburg));
        Assert.assertFalse(citiesGraph.addVertex(moscow));

        Assert.assertTrue(citiesGraph.containsVertex(moscow));
        Assert.assertFalse(citiesGraph.containsVertex(novosibirsk));
    }

}
