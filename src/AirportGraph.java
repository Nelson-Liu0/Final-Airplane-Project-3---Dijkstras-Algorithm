// --== CS400 File Header Information ==--
// Name: Mazzy Chen
// Email: xchen2263@wisc.edu
// Team: CN
// TA: Abhinav Agarwal
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.NoSuchElementException;

public class AirportGraph<NodeType, EdgeType extends Number> extends CS400Graph<NodeType, EdgeType> implements IAirportGraph<NodeType, EdgeType> {

    public CS400Graph<NodeType, EdgeType> graph;
    public Hashtable<IAirport, Vertex> hbvertices;

    @SuppressWarnings("unchecked")
    public AirportGraph (CS400Graph<NodeType, EdgeType> Graph) {
        graph = Graph;
        hbvertices = (Hashtable<IAirport, Vertex>) Graph.vertices.clone();
    }

    //IndividualTest constructor
    public AirportGraph() {

    }

    @Override
    public List<NodeType> getReachableAirports(NodeType departure) {
        if (departure == null)
            throw new NoSuchElementException("Can not find path for null departure");
        if (!graph.containsVertex(departure))
            throw new NoSuchElementException("Can not find path for departure not exist");
        PriorityQueue<Path> frontier = new PriorityQueue<Path>();
        LinkedList<Vertex> visited = new LinkedList<Vertex>();
        Path flight = new Path(graph.vertices.get(departure));
        frontier.add(flight);
        while (!frontier.isEmpty()) {
            // visit shortest path
            flight = frontier.poll();
            // Mark visited vertex
            if (!visited.contains(flight.end)) {
                visited.add(flight.end);
            }
            // if edges exist, loop all edges and add possible path to pq
            if (flight.end.edgesLeaving.size() != 0) {
                for (Edge e : flight.end.edgesLeaving) {
                    // Avoid visited vertex
                    if (!visited.contains(e.target)) {
                        Path flights = new Path(flight, e);
                        if (flights != null)
                            frontier.add(flights);
                    }
                }
            }
        }
        List<NodeType> reachableAirports = new ArrayList<>();

        for (Vertex v : visited) {
            reachableAirports.add(v.data);
        }
        reachableAirports.remove(departure);
        return reachableAirports;
    }

    @Override
    public boolean insertVertex(NodeType data) {
        return graph.insertVertex(data);
    }

    @Override
    public boolean removeVertex(NodeType data) {
        return graph.removeVertex(data);
    }

    @Override
    public boolean insertEdge(NodeType source, NodeType target, EdgeType weight) {
        return graph.insertEdge(source, target, weight);
    }

    @Override
    public boolean removeEdge(NodeType source, NodeType target) {
        return graph.removeEdge(source, target);
    }

    @Override
    public boolean containsVertex(NodeType data) {
        return graph.containsVertex(data);
    }

    @Override
    public boolean containsEdge(NodeType source, NodeType target) {
        return graph.containsEdge(source, target);
    }

    @Override
    public EdgeType getWeight(NodeType source, NodeType target) {
        return graph.getWeight(source, target);
    }

    @Override
    public List<NodeType> shortestPath(NodeType start, NodeType end) {
        return graph.shortestPath(start, end);
    }

    @Override
    public double getPathCost(NodeType start, NodeType end) {
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
