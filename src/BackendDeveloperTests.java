// --== CS400 File Header Information ==--
// Name: Nelson Liu
// Email: naliu@wisc.edu
// Team: Blue: CN
// TA: Abhinav Agarwal
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.FileNotFoundException;
import static org.junit.Assert.assertEquals;

public class BackendDeveloperTests {

    /**
     * Tests fastestRoute method
     *
     * @return true if method returns correct list, false if otherwise
     */
    @Test
    public void IndividualTest1() {
        BDAirportLoader DWgraph = new BDAirportLoader();
        AirplaneRouterBackend backend = new AirplaneRouterBackend(true, DWgraph);

        List<IAirport> list = backend.fastestRoute("London", "San Francisco");

        assertTrue(list.get(0).getAirport() == "London");
        assertTrue(list.get(1).getAirport() == "New York");
        assertTrue(list.get(2).getAirport() == "San Francisco");

    }

    /**
     * Tests travelTime method
     *
     * @return true if method returns correct travel time, false if otherwise
     */
    @Test
    public void IndividualTest2() {
        BDAirportLoader DWgraph = new BDAirportLoader();
        AirplaneRouterBackend backend = new AirplaneRouterBackend(true, DWgraph);

        assertTrue(backend.travelTime("Rome", "Delhi") == 13.35);
    }

    /**
     * Tests addConnection method
     *
     * @return true if method added a connection between 2 valid cities, false if otherwise
     */
    @Test
    public void IndividualTest3() {
        BDAirportLoader DWgraph = new BDAirportLoader();
        AirplaneRouterBackend backend = new AirplaneRouterBackend(true, DWgraph);
        List<IAirport> list = backend.fastestRoute("London", "Rome");

        assertTrue(list.get(0).getAirport() == "London");
        assertTrue(list.get(1).getAirport() == "New York");
        assertTrue(list.get(2).getAirport() == "San Francisco");
        assertTrue(list.get(3).getAirport() == "Rome");

        backend.addConnection("London", "Rome", 11.30);

        List<IAirport> list2 = backend.fastestRoute("London", "Rome");


        assertTrue(list2.get(0).getAirport() == "London");
        assertTrue(list2.get(1).getAirport() == "Rome");

    }

    /**
     * Tests reachableAirports method
     *
     * @return true if method returns correct list size, false if otherwise
     */
    @Test
    public void IndividualTest4() {
        BDAirportLoader DWgraph = new BDAirportLoader();

        AirplaneRouterBackend backend = new AirplaneRouterBackend(true, DWgraph);
        List<IAirport> list = backend.reachableAirports("Wherever");

        assertTrue(list.size() == 2);
    }

    /**
     * Tests size and isEmpty methods
     *
     * @return true if graph is not empty, false if otherwise
     */
    @Test
    public void IndividualTest5() {
        BDAirportLoader DWgraph = new BDAirportLoader();
        AirplaneRouterBackend backend = new AirplaneRouterBackend(true, DWgraph);

        assertTrue(!backend.isEmpty());
    }

    /**
     * Checks the correctness of the implementation of the AirportLoader class. Checks if the
     * loadAirports() method works as expected and contains the correct number of vertices and edges.
     *
     * @return true when this test returns the correct number of edges, vertices, and false otherwise
     */
    @Test
    public void CodeReviewOfDataWrangler1() {
        try {
            String file = "/Users/nelsonliu/Intellij Workspace/Final Airplane Project 3 - Dijkstras Algorithm/src/airportdata.gv";
            AirportLoader loader = new AirportLoader();
            GraphADT<IAirport, Double> testGraph = null;
            try {
                testGraph = loader.loadAirports(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            assertTrue(testGraph.isEmpty() != true);
            assertTrue(testGraph.getVertexCount()== 10);
            assertTrue(testGraph.getEdgeCount() == 22);
        } catch (Exception e){
            System.out.println("Your tester threw an unexpected exception.");
        }
    }

    /**
     * Checks the correctness of the implementation of the AirportLoader class. Checks if it works as
     * expected when a wrong file name is passed in.
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    @Test
    public void CodeReviewOfDataWrangler2() {
        try {
            AirportLoader wrongLoader = new AirportLoader();
            GraphADT<IAirport, Double> testWrongGraph = null;
            try {
                testWrongGraph = wrongLoader.loadAirports("AiPoRtData.gv");
            } catch (FileNotFoundException e) {
                // behavior expected
            }
            String correctFile = "/Users/nelsonliu/Intellij Workspace/Final Airplane Project 3 - Dijkstras Algorithm/src/airportdata.gv";
            AirportLoader correctLoader = new AirportLoader();
            GraphADT<IAirport, Double> testCorrectGraph = null;
            try {
                testCorrectGraph = correctLoader.loadAirports(correctFile);
            } catch (FileNotFoundException e) {
                System.out.println("FileNotFoundException while using the correct file name.");
            }
        } catch (Exception e) {
            System.out.println("Threw an unexpected exception.");
        }
    }

    /**
     * Tests the fastest route method when using the the graph recived by the AirportLoader class
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    @Test
    public void IntegrationTest1() {
        AirportLoader loader = new AirportLoader();
        try {
            CS400Graph<IAirport, Double> csGraph =
                    (CS400Graph<IAirport, Double>) loader.loadAirports("/Users/nelsonliu/Intellij Workspace/Final Airplane Project 3 - Dijkstras Algorithm/src/airportdata.gv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        AirplaneRouterBackend backend = new AirplaneRouterBackend(loader);
        List<IAirport> airports = backend.fastestRoute("Toronto", "Beijing");

        assertTrue(airports.get(0).getAirport().equals("Toronto"));
        assertTrue(airports.get(1).getAirport().equals("Delhi"));
        assertTrue(airports.get(2).getAirport().equals("Beijing"));

    }

    /**
     * Tests the reachableAirports method when using the the graph recived by the AirportLoader class
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    @Test
    public void IntegrationTest2() {
        AirportLoader loader = new AirportLoader();
        try {
            CS400Graph<IAirport, Double> csGraph =
                    (CS400Graph<IAirport, Double>) loader.loadAirports("/Users/nelsonliu/Intellij Workspace/Final Airplane Project 3 - Dijkstras Algorithm/src/airportdata.gv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        AirplaneRouterBackend backend = new AirplaneRouterBackend(loader);
        List<IAirport> airports = backend.reachableAirports("Toronto");

        assertTrue(airports.get(0).getAirport().equals("Dubai"));
        assertTrue(airports.get(1).getAirport().equals("Delhi"));
        assertTrue(airports.get(2).getAirport().equals("Beijing"));
        assertTrue(airports.get(3).getAirport().equals("London"));
        assertTrue(airports.get(4).getAirport().equals("Rome"));
        assertTrue(airports.get(5).getAirport().equals("Paris"));
        assertTrue(airports.get(8).getAirport().equals("Tokyo"));
    }
}