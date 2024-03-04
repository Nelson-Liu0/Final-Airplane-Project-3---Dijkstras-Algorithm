// --== CS400 Project Three File Header ==--
// Name: Roshni Venkat
// CSL Username: roshni
// Email: rvenkat@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: <any optional extra notes to your grader>

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is used to load airport data from a DOT file.
 *
 * @author roshnivenkat
 */
public class AirportLoader implements IAirportLoader {
    private GraphADT<IAirport, Double> graph;

    /**
     * Constructor that is used to create an instance of the AirportLoader class
     *
     * @param file name of the file
     */
    public AirportLoader() {
        graph = new CS400Graph<IAirport, Double>();
    }

    /**
     * This method loads the list of airports and the time taken to travel from one airport to another
     * from a DOT file into a graph.
     *
     * @param filepathToDOT path to the DOT file relative to the executable
     * @return a graph of airports and the paths between them
     * @throws FileNotFoundException if the file is not found
     */
    @Override
    public GraphADT<IAirport, Double> loadAirports(String filepathToDOT)
            throws FileNotFoundException {
        try {
            // Create a new file using the file path
            File airportsDOT = new File(filepathToDOT);
            // Use a scanner to read the file
            Scanner reader = new Scanner(airportsDOT);
            // Iterate through a while loop as long the file has more lines
            while (reader.hasNextLine()) {
                // Read the next line
                String line = reader.nextLine();
                // disregard the first line
                if (line.contains("digraph")) {
                    line = reader.nextLine();
                }
                // break the loop if all the vertices and edges are added to the graph
                if (!line.contains("->")) {
                    break;
                }
                // check if line is not blank
                if (!line.isBlank()) {
                    // check if the line contains an edge
                    if (line.contains("->")) {
                        int indexStart = line.indexOf("=");
                        int indexEnd = line.indexOf("]");
                        // extract the edge weight from the file
                        Double weight = Double.parseDouble(line.substring(indexStart + 2, indexEnd - 1));
                        // extract the start and end airports
                        Airport startAirport = new Airport(line.substring(5, line.indexOf("-") - 1));
                        Airport endAirport =
                                new Airport(line.substring(line.indexOf(">") + 2, line.indexOf("[") - 1));
                        // add the airports as vertices to the graph
                        graph.insertVertex(startAirport);
                        graph.insertVertex(endAirport);
                        // add the edge between the airports to the graph
                        graph.insertEdge(startAirport, endAirport, weight);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File cannot be found.");
        }
        return graph;
    }

}
