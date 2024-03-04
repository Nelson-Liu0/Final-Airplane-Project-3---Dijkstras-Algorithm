// --== CS400 Project Three File Header ==--
// Name: Shraddha Byndoor
// CSL Username: shraddha
// Email: sbyndoor@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: <any optional extra notes to your grader>

/**
 * Instances of this interface can be used to display the UI for the Airplane Route Finder
 *
 * @author Shraddha Byndoor
 *
 */

public interface IAirplaneRouterFrontend {

    /**
     * The constructor that the implementation this interface will
     * provide. It takes the Scanner that will read user input as
     * a parameter as well as the backend and the AirportValidator.
     */
    // AirplaneRouterFrontend(Scanner userInputScanner, IAirplaneRouterBackend backend, IAirportValidator validator);

    /**
     * This method starts the command loop for the frontend, and will
     * terminate when the user exists the app.
     */
    public void runCommandLoop();

    // to help make it easier to test the functionality of this program, 
    // the following helper methods will help support runCommandLoop():

    public void displayMainMenu(); // prints command options to System.out

    public void displayFastestPath(); // prints out the fastest route between two cities

    public void displayTravelTime(); // prints out the travel time between two cities if there is a direct flight between them

    public void addConnection(); // reads input from System.in, displays message when the connection has been successfully added

    public void reachableAirports(); // reads input from System.in, displays message showing all reachable airports from current airport in graph

}