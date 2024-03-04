// --== CS400 Project Three File Header ==--
// Name: Roshni Venkat
// CSL Username: roshni
// Email: rvenkat@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: <any optional extra notes to your grader>
import java.io.FileNotFoundException;

/**
 * Instances of this interface can be used to load airport data from a DOT file.
 */
public interface IAirportLoader {

    /**
     * This method loads the list of airports and the time taken to travel from one airport to another
     * from a DOT file.
     *
     * @param filepathToDOT path to the DOT file relative to the executable
     * @return a graph of Airport objects and the paths between them
     * @throws FileNotFoundException if the file is not found
     */
    GraphADT<IAirport, Double> loadAirports(String filepathToDOT) throws FileNotFoundException;

}