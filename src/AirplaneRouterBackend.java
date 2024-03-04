// --== CS400 Project 3 Airplane Router Backend Developer ==--
// Name: Nelson Liu
// CSL Username: nelsonl
// Email: naliu@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: <>

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AirplaneRouterBackend implements IAirplaneRouterBackend {

    public CS400Graph<IAirport, Double> csGraph;
    public IAirportGraph<IAirport, Double> airportGraph;

    public AirplaneRouterBackend(AirportLoader loader) {
        try {
            csGraph = (CS400Graph<IAirport, Double>) loader.loadAirports("/Users/nelsonliu/Intellij Workspace/Final Airplane Project 3 - Dijkstras Algorithm/src/airportdata.gv"); //loads in the graph
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.airportGraph = new AirportGraph(csGraph);
    }

    //constructor for role code
    public AirplaneRouterBackend(boolean placeholder, BDAirportLoader DWgraph) {
        if (placeholder) {
            try {
                csGraph = (CS400Graph<IAirport, Double>) DWgraph.loadAirports("/Users/nelsonliu/Intellij Workspace/Final Airplane Project 3 - Dijkstras Algorithm/src/airportdata.gv"); //loads in the graph from placeholder loader
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            this.airportGraph = new BDAirportGraph(csGraph); //AiportLoaderBD -> AirportGraphBD (CS400Graph)
        }
    }

    @Override
    public List<IAirport> fastestRoute(String departingCity, String destinationCity) {
        List<IAirport> fastestRoute = new ArrayList<>();
        IAirport depart = null;
        IAirport destination = null;

        // checks that input isn't blank/null
        if (departingCity == null || destinationCity == null
                || departingCity.equalsIgnoreCase("")
                || destinationCity.equalsIgnoreCase("")) {
            return fastestRoute;
        }

        List<IAirport> allAirports = new ArrayList<>();
        Set<IAirport> setOfKeys = csGraph.vertices.keySet();

        for (IAirport airport : setOfKeys) { //Loading airports to an arrayList
            allAirports.add(airport);
        }

        for (IAirport airport : allAirports) {
            if (csGraph.vertices.get(airport).data.getAirport().equalsIgnoreCase(departingCity)) {
                depart = airport;
            } else if (csGraph.vertices.get(airport).data.getAirport().equalsIgnoreCase(destinationCity)) {
                destination = airport;
            }
        }
        fastestRoute = csGraph.shortestPath(depart, destination);
        return fastestRoute;
    }

    @Override
    public double travelTime(String departingCity, String destinationCity) {
        IAirport depart = null;
        IAirport destination = null;

        // checks that input isn't blank/null
        if (departingCity == null || destinationCity == null
                || departingCity.equalsIgnoreCase("")
                || destinationCity.equalsIgnoreCase("")) {
            return 0;
        }

        List<IAirport> allAirports = new ArrayList<>();
        Set<IAirport> setOfKeys = csGraph.vertices.keySet();

        for (IAirport airport : setOfKeys) {
            allAirports.add(airport);
        }

        for (IAirport airport : allAirports) {
            boolean air1 = false;
            boolean air2 = false;
            if (csGraph.vertices.get(airport).data.getAirport().equalsIgnoreCase(departingCity)) {
                depart = airport;
                air1 = true;
            } else if (csGraph.vertices.get(airport).data.getAirport().equalsIgnoreCase(destinationCity)) {
                destination = airport;
                air2 = true;
            }

            if (air1 && air2) {
                break;
            }
        }

        return csGraph.getPathCost(depart, destination);
    }

    @Override
    public void addConnection(String departingCity, String destinationCity, double hours) {
        IAirport depart = null;
        IAirport destination = null;

        // checks that input isn't blank/null
        if (departingCity == null || destinationCity == null
                || departingCity.equalsIgnoreCase("")
                || destinationCity.equalsIgnoreCase("")) {
            return;
        }

        List<IAirport> allAirports = new ArrayList<>();
        Set<IAirport> setOfKeys = csGraph.vertices.keySet();

        for (IAirport airport : setOfKeys) { //Loading airports to an arrayList
            allAirports.add(airport);
        }

        for (IAirport airport : allAirports) {
            if (csGraph.vertices.get(airport).data.getAirport().equalsIgnoreCase(departingCity)) {
                depart = airport;
            } else if (csGraph.vertices.get(airport).data.getAirport().equalsIgnoreCase(destinationCity)) {
                destination = airport;
            }
        }

        if (csGraph.containsEdge(depart, destination)) { //checks if edge is already present
            return;
        }

        csGraph.insertEdge(depart, destination, hours);
    }

    @Override
    public List<IAirport> reachableAirports(String currentAirport) {
        List<IAirport> reachableAirports = new ArrayList<>();
        IAirport current = null;

        // checks that input isn't blank/null
        if (currentAirport == null || currentAirport.equalsIgnoreCase("")) {
            return reachableAirports;
        }

        List<IAirport> allAirports = new ArrayList<>();
        Set<IAirport> setOfKeys = csGraph.vertices.keySet();

        for (IAirport airport : setOfKeys) { //Loading airports to an arrayList
            allAirports.add(airport);
        }
        for (IAirport airport : allAirports) {
            if (csGraph.vertices.get(airport).data.getAirport().equalsIgnoreCase(currentAirport)) {
                current = airport;
                break;
            }
        }

        reachableAirports = airportGraph.getReachableAirports(current);
        return reachableAirports;
    }

    @Override
    public int size() {
        return csGraph.getVertexCount();
    }

    @Override
    public boolean isEmpty() {
        return csGraph.isEmpty();
    }
}
