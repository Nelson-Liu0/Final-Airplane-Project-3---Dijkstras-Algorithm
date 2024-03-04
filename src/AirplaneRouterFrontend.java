// --== CS400 File Header Information ==--
// Name: Shraddha Byndoor
// Email: sbyndoor@wisc.edu
// Team: CN
// TA: Abhinav Agarwal
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is the Frontend class which can be used to display the UI for the Airplane Route Finder.
 * Contains methods to print the different menu options
 *
 * @author Shraddha Byndoor
 *
 */
public class AirplaneRouterFrontend implements IAirplaneRouterFrontend {

    private Scanner userInput = new Scanner(System.in);
    private IAirplaneRouterBackend backend;
    private IAirportValidator validator;

    /**
     * Constructor which takes the Scanner that will read user input as
     * a parameter as well as the backend and the AirportValidator interfaces.
     *
     * @param userInputScanner the scanner to take input
     * @param backend the backend interface: IAirplaneRouterBackend
     * @param validator the validator interface: IAirportValidator
     */
    public AirplaneRouterFrontend(Scanner userInputScanner, IAirplaneRouterBackend backend,
                                  IAirportValidator validator) {
        userInput = userInputScanner;
        this.backend = backend;
        this.validator = validator;
    }

    /**
     * This method starts the command loop for the frontend, and will terminate when the user exists
     * the app.
     */
    @Override
    public void runCommandLoop() {
        System.out.println("Welcome to the Airplane Route Finder!");
        System.out.println("======================================\n");
        displayMainMenu();
    }

    // to help make it easier to test the functionality of this program,
    // the following helper methods will help support runCommandLoop():

    /**
     * Displays the main menu and calls the different methods according to the user input
     */
    @Override
    public void displayMainMenu() {
        while (true) {
            // prints command options to System.out
            String line1 = "1) Find the fastest route between two cities";
            String line2 = "2) Show the travel time between two cities";
            String line3 = "3) Add a new connection";
            String line4 = "4) Show all reachable airports from departure city";
            String line5 = "5) Exit Application";

            System.out.println("Please select one of the following functions:");
            System.out.println(line1);
            System.out.println(line2);
            System.out.println(line3);
            System.out.println(line4);
            System.out.println(line5);

            int menuInput = userInput.nextInt();
            userInput.nextLine();

            if (menuInput == 1) {
                displayFastestPath();
            } else if (menuInput == 2) {
                displayTravelTime();
            } else if (menuInput == 3) {
                addConnection();
            } else if (menuInput == 4) {
                reachableAirports();
            } else if (menuInput == 5) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid input, try again!");
            }
        }
    }

    /**
     * Method which prints out the fastest route between the departing city and
     * the destination city which is taken from the user.
     */
    @Override
    public void displayFastestPath() {
        System.out.println("You are in the Find the Fastest Route Menu:");
        System.out.print("	Enter the departing city: ");
        String departure = userInput.nextLine().trim();
        // removes space if present in airport name
        if (departure.contains(" ")) {
            String[] substrings = departure.split(" ");
            departure = "";
            for (int i = 0; i < substrings.length; i++) {
                departure += substrings[i];
            }
        }
        //System.out.println();
        System.out.print("	Enter the destination city: ");
        String destination = userInput.nextLine().trim();
        // removes space if present in airport name
        if (destination.contains(" ")) {
            String[] substrings = destination.split(" ");
            destination = "";
            for (int i = 0; i < substrings.length; i++) {
                destination += substrings[i];
            }
        }
        //System.out.println();
        List<IAirport> airports = backend.fastestRoute(departure, destination);
        double travelTime = 0.0;
        if (airports != null) {
            for (int i = 0; i < airports.size(); i++) {
                if (i == airports.size() - 1) {
                    System.out.print(addSpace(airports.get(i).getAirport()));
                }
                else {
                    System.out.print(addSpace(airports.get(i).getAirport()) + " => ");
                }
                if (i != 0) {
                    travelTime += backend.travelTime(airports.get(i - 1).getAirport(), airports.get(i).getAirport());
                }
            }
        }
        System.out.println();
        System.out.println("Total Travel Time: " + travelTime + " hour(s)");
        System.out.println();
    }

    /**
     * Prints out the travel time between two cities if there is a direct flight between them.
     * The 2 cities are given by the user. Uses the validator to check if there is a direct flight
     * between the 2 cities.
     */
    @Override
    public void displayTravelTime() {
        System.out.println("You are in the Find the Travel Time Menu:");
        System.out.print("	Enter the departing city: ");
        String departure = userInput.nextLine().trim();
        // removes space if present in airport name
        if (departure.contains(" ")) {
            String[] substrings = departure.split(" ");
            departure = "";
            for (int i = 0; i < substrings.length; i++) {
                departure += substrings[i];
            }
        }
        //System.out.println();
        System.out.print("	Enter the destination city: ");
        String destination = userInput.nextLine().trim();
        // removes space if present in airport name
        if (destination.contains(" ")) {
            String[] substrings = destination.split(" ");
            destination = "";
            for (int i = 0; i < substrings.length; i++) {
                destination += substrings[i];
            }
        }
        //System.out.println();
        if (validator.validate(departure, destination)) {
            double travelTime = backend.travelTime(departure, destination);
            System.out.println(addSpace(departure) + " => " + addSpace(destination) + " | " + "Direct Flight Travel Time: " + travelTime + " hour(s)");
        } else {
            System.out.println("There is no direct flight between these two cities!");
        }
        System.out.println();

    }

    /**
     * Reads input from System.in, and displays a message when the connection has been
     * successfully added. Checks with the validator and displays the message when the departure
     * or destination city does not exist in the dataset.
     */
    @Override
    public void addConnection() {
        System.out.println("You are in the Add A New Connection Menu:");
        System.out.print("	Enter the departing city: ");
        String departure = userInput.nextLine().trim();
        // removes space if present in airport name
        if (departure.contains(" ")) {
            String[] substrings = departure.split(" ");
            departure = "";
            for (int i = 0; i < substrings.length; i++) {
                departure += substrings[i];
            }
        }
        //System.out.println();
        System.out.print("	Enter the destination city: ");
        // removes space if present in airport name
        String destination = userInput.nextLine().trim();
        if (destination.contains(" ")) {
            String[] substrings = destination.split(" ");
            destination = "";
            for (int i = 0; i < substrings.length; i++) {
                destination += substrings[i];
            }
        }
        //System.out.println();
        System.out.print("	Enter the travel time: ");
        double travelTime = userInput.nextDouble();
        //System.out.println();
        if (validator.validate(departure) && validator.validate(destination)) {
            backend.addConnection(departure, destination, travelTime);
            System.out.println("Flight successfully added!");
        }
        else {
            System.out.println("Flight cannot be added because the departure/destination city does not exist!");
        }
        System.out.println();

    }

    /**
     * Reads input from System.in, displays message showing all reachable airports
     * from current airport in graph
     */
    @Override
    public void reachableAirports() {
        System.out.println("You are in the Show All Reachable Airports Menu:");
        System.out.print("	Enter the departing city: ");
        String departure = userInput.nextLine().trim();
        // removes space if present in airport name
        if (departure.contains(" ")) {
            String[] substrings = departure.split(" ");
            departure = "";
            for (int i = 0; i < substrings.length; i++) {
                departure += substrings[i];
            }
        }
        //System.out.println();
        List<IAirport> reachableAirports = backend.reachableAirports(departure);
        for (int i = 0; i < reachableAirports.size(); i++) {
            System.out.println((i + 1) + ". " + addSpace(reachableAirports.get(i).getAirport()));
        }
        System.out.println();
    }

    /**
     * Private helper method which adds a space if the city has 2 words, after getting the city
     * from backend. For example, New York is returned from backend as NewYork, and this method is
     * used to add a space to print out New York to the user.
     *
     * @param airport the name of the airport
     * @return a new string with a space between the 2 parts of the string if necessary
     */
    private String addSpace(String airport) {
        String newAirport = airport;
        List<Integer> temp = new ArrayList<Integer>();
        for (int i = 0; i < airport.length(); i++) {
            if (Character.isUpperCase(airport.charAt(i))) {
                temp.add(i);
            }
        }
        if (temp.size() > 1) {
            newAirport = "";
            newAirport = airport.substring(0, temp.get(temp.size() - 1)) +
                    " " + airport.substring(temp.get(temp.size() - 1));
        }
        return newAirport;
    }

}