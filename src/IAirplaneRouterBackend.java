import java.util.List;

public interface IAirplaneRouterBackend {

    /**
     * Finds all the city airports from departure to destination that has the fastest route
     *
     * @param departingCity   the city that you are departing and start from
     * @param destinationCity the city from where you reach your destination
     * @returns List containing all the airports that have the fastest route
     */
    public List<IAirport> fastestRoute(String departingCity, String destinationCity);


    /**
     * Finds the fastest travel time between two city airports
     *
     * @param departingCity   the city that you are departing and start from
     * @param destinationCity the city from where you reach your destination
     * @returns a double which holds the number of hours
     */
    public double travelTime(String departingCity, String destinationCity);

    /**
     * Adds a connection from one existing city airport to another
     *
     * @param departingCity   the city that you are departing and start from
     * @param destinationCity the city from where you reach your destination
     * @param hours           the number of hours from the newly added connection
     */
    public void addConnection(String departingCity, String destinationCity, double hours);

    /**
     * Finds all the airports the currentAirport can reach
     *
     * @param currentAirport   the airport you are searching for connections
     * @returns a list of reachable airports in the graph from the currentAirport
     */
    public List<IAirport> reachableAirports(String currentAirport); // gets input from frontend, returns a List containing all the airports that have the fastest route

    /**
     * Return the current size
     *
     * @returns the size in integer
     */
    public int size();

    /**
     * Return the boolean value of whether the current list is empty
     *
     * @returns true if the list is empty, otherwise false
     */
    public boolean isEmpty();



}
