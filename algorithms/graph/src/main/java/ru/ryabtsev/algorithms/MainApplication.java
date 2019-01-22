package ru.ryabtsev.algorithms;

import ru.ryabtsev.algorithms.graph.City;
import ru.ryabtsev.algorithms.graph.Edge;
import ru.ryabtsev.algorithms.graph.SimpleGraph;

import java.util.Set;
import java.util.function.Supplier;

/**
 * Main application which contains the seventh homework of 'Algorithms' course main functionality.
 */
public class MainApplication
{
    private static final City Bryansk = new City("Bryansk");
    private static final City Vladivostok = new City("Vladivostok");
    private static final City Ivanovo = new City( "Ivanovo");
    private static final City Moscow = new City("Moscow");
    private static final City NiznyNovgorod = new City( "Nizny Novgorod");
    private static final City Novgorod = new City( "Novgorod");
    private static final City Orel = new City("Orel");
    private static final City Ryazan = new City( "Ryazan");
    private static final City StPetersburg = new City( "St. Petersburg");
    private static final City Tver = new City("Tver");
    private static final City Tula = new City( "Tula");

    public static void main( String[] args )
    {
        final Graph<City, Edge> citiesGraph = new SimpleGraph<>(new Supplier<Edge>() {
            @Override
            public Edge get() {
                return new Edge();
            }
        });

        addCities(citiesGraph);
        addRoads(citiesGraph);

        System.out.println("Connection between cities:");
        System.out.println(citiesGraph);
        System.out.println("Total cities: " + citiesGraph.verticesSet().size());

        final BreadthFirstSearch<City, Edge> bfs = new BreadthFirstSearch<>(citiesGraph);

        findShortestWay(bfs, Bryansk, StPetersburg);
        findShortestWay(bfs, Moscow, StPetersburg);
        findShortestWay(bfs, Novgorod, Ryazan);
        findShortestWay(bfs, Vladivostok, Novgorod);
        findShortestWay(bfs, Vladivostok, Ivanovo);
    }

    private static void addCities(Graph<City, Edge> citiesGraph) {
        citiesGraph.addVertex(Bryansk);
        citiesGraph.addVertex(Vladivostok);
        citiesGraph.addVertex(Ivanovo);
        citiesGraph.addVertex(Moscow);
        citiesGraph.addVertex(Novgorod);
        citiesGraph.addVertex(NiznyNovgorod);
        citiesGraph.addVertex(Orel);
        citiesGraph.addVertex(Ryazan);
        citiesGraph.addVertex(StPetersburg);
        citiesGraph.addVertex(Tver);
        citiesGraph.addVertex(Tula);
    }

    private static void addRoads(Graph<City, Edge> citiesGraph) {
        citiesGraph.addEdge(Bryansk, Moscow);
        citiesGraph.addEdge(Bryansk, Orel);
        citiesGraph.addEdge(Vladivostok, Moscow);
        //citiesGraph.addEdge(Vladivostok, StPetersburg);
        citiesGraph.addEdge(Ivanovo, Moscow);
        citiesGraph.addEdge(Ivanovo, NiznyNovgorod);
        citiesGraph.addEdge(Ivanovo, Tver);
        citiesGraph.addEdge(Moscow, NiznyNovgorod);
        citiesGraph.addEdge(Moscow, Ryazan);
        citiesGraph.addEdge(Moscow, Tver);
        citiesGraph.addEdge(Moscow, Tula);
        citiesGraph.addEdge(NiznyNovgorod, Ryazan);
        citiesGraph.addEdge(NiznyNovgorod, Tula);
        citiesGraph.addEdge(Novgorod, StPetersburg);
        citiesGraph.addEdge(Novgorod, Tver);
        citiesGraph.addEdge(Orel, Tula);
        citiesGraph.addEdge(Ryazan, Tula);
        citiesGraph.addEdge(StPetersburg, Tver);
    }

    private static void findShortestWay(BreadthFirstSearch<City, Edge> bfs, City from, City to) {
        final Set<City> path = bfs.getShortestWay(from, to);
        System.out.println(
                "The shortest way between: " + from + " and " + to + ": " + path + ", length = " + (path.size() - 1)
        );
    }
}
