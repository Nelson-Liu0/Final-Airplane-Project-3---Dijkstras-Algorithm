// --== CS400 File Header Information ==--
// Name: Mazzy Chen
// Email: xchen2263@wisc.edu
// Team: CN
// TA: Abhinav Agarwal
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class AirportValidator<NodeType, EdgeType extends Number> implements IAirportValidator {


    private IAirportGraph<IAirport, Double> airports;
    private CS400Graph<IAirport, Double> airplane;

    @SuppressWarnings("unchecked")
    public AirportValidator(AirplaneRouterBackend airplane) {
        this.airplane = airplane.csGraph;
        this.airports = airplane.airportGraph;
    }

    //IndividualTest constructor
    public AirportValidator(IAirportGraph<IAirport, Double> airports, CS400Graph<IAirport, Double> airplane) {
        this.airports = airports;
        this.airplane = airplane;
    }

    /**
     * Checks if the given airport have connection between them
     *
     * @param departure
     * @param destination
     * @return true if if the given airport names have connection, false otherwise
     */
    @Override
    public boolean validate(String departure, String destination) {
        IAirport start = null;
        IAirport end = null;
        if (departure == null || destination == null) {
            return false;
        }
        List<IAirport> allAirports = new ArrayList<>();
        Set<IAirport> setOfKeys = airplane.vertices.keySet();

        for (IAirport airport:setOfKeys) {
            allAirports.add(airport);
        }
        for (IAirport airport : allAirports) {
            if (airplane.vertices.get(airport).data.getAirport().equalsIgnoreCase(departure)) {
                start = airport;
            } else if (airplane.vertices.get(airport).data.getAirport().equalsIgnoreCase(destination)) {
                end = airport;
            }
        }

        for (CS400Graph<IAirport, Double>.Edge e : airplane.vertices.get(start).edgesLeaving) {
            if (e.target == airplane.vertices.get(end)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given airport names exist in database
     *
     * @param airport
     *
     * @return true if if the given airport exist in database, false otherwise
     */
    public boolean validate(String airport) {
        IAirport start = null;
        if (airport == null) {
            return false;
        }
        List<IAirport> allAirports = new ArrayList<>();
        Set<IAirport> setOfKeys = airplane.vertices.keySet();

        for (IAirport key:setOfKeys) {
            allAirports.add(key);
        }
        for (IAirport a : allAirports) {
            if (airplane.vertices.get(a).data.getAirport().equalsIgnoreCase(airport)) {
                start = a;
            }
        }
        try {
            if (airports.containsVertex(start)) {}
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
