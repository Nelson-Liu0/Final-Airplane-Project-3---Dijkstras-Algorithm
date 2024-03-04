import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Class with main method to run the airplane route tracker app
 */
public class AirplaneRouter {
    public static void main(String[] args) throws FileNotFoundException {
        // Data Wrangler data instantiated
        //GraphADT<IAirport, Double> airportGraph = (new AirportLoader().loadAirports("airportdata.gv"));
        AirportLoader loader = new AirportLoader();
        CS400Graph<IAirport, Double> csGraph = (CS400Graph<IAirport, Double>) loader.loadAirports("/Users/nelsonliu/Intellij Workspace/Final Airplane Project 3 - Dijkstras Algorithm/src/airportdata.gv"); //loads in the graph
        AirportGraph<Airport, Double> airportGraph = new AirportGraph(csGraph);
        // Backend instantiated
        AirplaneRouterBackend backend = new AirplaneRouterBackend(loader);

        // Frontend instantiated
        IAirportValidator airportValidator = new AirportValidator(backend);
        Scanner userInputScanner = new Scanner(System.in);
        AirplaneRouterFrontend frontend = new AirplaneRouterFrontend(userInputScanner, backend, airportValidator);

        // start the input loop of the front end
        frontend.runCommandLoop();
    }
}
