// --== CS400 File Header Information ==--
// Name: Nelson Liu
// Email: naliu@wisc.edu
// Team: Blue: CN
// TA: Abhinav Agarwal
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
public class BDAirportLoader implements IAirportLoader {
    @Override
    public GraphADT<IAirport, Double> loadAirports(String filepathToDOT) throws FileNotFoundException {
        CS400Graph<IAirport, Double> graph = new CS400Graph<IAirport, Double>();
        IAirport airportLondon = new BDAirport("London");
        IAirport airportNewYork = new BDAirport("New York");
        IAirport airportSF = new BDAirport("San Francisco");
        IAirport airportDelhi = new BDAirport("Delhi");
        IAirport airportRome = new BDAirport("Rome");

        graph.insertVertex(airportLondon);
        graph.insertVertex(airportNewYork);
        graph.insertVertex(airportSF);
        graph.insertVertex(airportDelhi);
        graph.insertVertex(airportRome);

        graph.insertEdge(airportLondon, airportNewYork, 8.01);
        graph.insertEdge(airportNewYork, airportSF, 6.62);
        graph.insertEdge(airportDelhi, airportRome, 8.33);
        graph.insertEdge(airportRome, airportSF, 1.88);
        graph.insertEdge(airportSF, airportRome, 14.33);
        graph.insertEdge(airportRome, airportDelhi, 13.35);

        return graph;
    }
}
