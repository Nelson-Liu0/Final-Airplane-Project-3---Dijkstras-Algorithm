// --== CS400 Project Three File Header ==--
// Name: Roshni Venkat
// CSL Username: roshni
// Email: rvenkat@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: <any optional extra notes to your grader>

/**
 * This class is used to create an Airport object
 *
 * @author roshnivenkat
 */
public class Airport implements IAirport {
    private String airportName;

    /**
     * This constructor creates a new airport.
     *
     * @param name name of the city that the airport is located in
     */
    public Airport(String name) {
        this.airportName = name;
    }

    /**
     * Gets the name of the city that the airport is located in.
     *
     * @return name of the city
     */
    public String getAirport() {
        return this.airportName;
    }

    /**
     * Checks whether this Airport equals another object passed as input.
     *
     * @param o other object to compare
     * @return true if o refers to an Airport object with the same name
     */
    @Override
    public boolean equals(Object o) {
        // check if the object is an instance of Airport
        if (!(o instanceof Airport)) {
            return false;
        }
        Airport airport = (Airport) o;
        // check if both the airport names are equal
        return (this.airportName.equals(airport.airportName));
    }

    /**
     * Calculates the hashcode value of the name of the airport.
     *
     * @return hashcode value of the name of the airport
     */
    @Override
    public int hashCode() {
        return this.airportName.hashCode();
    }
}

