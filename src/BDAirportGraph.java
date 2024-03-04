// --== CS400 Fall 2022 File Header Information ==--
// Name: Nelson Liu
// CSL Username: nelsonl
// Email: naliu@wisc.edu
// Team: CN
// TA: Abhinav Agarwal
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra note

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class BDAirportGraph extends CS400Graph implements IAirportGraph {
    public CS400Graph graph;
    public Hashtable<IAirport, Vertex> vertices;

    public BDAirportGraph (CS400Graph<IAirport, Double> csGraph) {
        graph = csGraph;
        vertices = (Hashtable<IAirport, Vertex>) csGraph.vertices.clone();
    }

    @Override
    public List getReachableAirports(Object departure) {
        List<IAirport> reachableAirports = new ArrayList<>();
        BDAirport london = new BDAirport("London");
        BDAirport newYork = new BDAirport("New York");

        reachableAirports.add(london);
        reachableAirports.add(newYork);
        return reachableAirports; //ae implements
    }

    @Override
    public boolean insertVertex(Object data) {
        return graph.insertVertex(data);
    }

    @Override
    public boolean removeVertex(Object data) {
        return false;
    }

    @Override
    public boolean insertEdge(Object source, Object target, Number weight) {
        return graph.insertEdge(source, target, weight.doubleValue());
    }

    @Override
    public boolean removeEdge(Object source, Object target) {
        return true;
    }

    @Override
    public boolean containsVertex(Object data) {
        return true;
    }

    @Override
    public boolean containsEdge(Object source, Object target) {
        return true;
    }

    @Override
    public Number getWeight(Object source, Object target) {
        return 0;
    }

    @Override
    public List shortestPath(Object start, Object end) {
        return graph.shortestPath(start, end);
    }

    @Override
    public double getPathCost(Object start, Object end) {
        return graph.getPathCost(start, end);
    }

    @Override
    public boolean isEmpty() {
        return graph.isEmpty();
    }

    @Override
    public int getEdgeCount() {
        return graph.getEdgeCount();
    }

    @Override
    public int getVertexCount() {
        return graph.getVertexCount();
    }
}
