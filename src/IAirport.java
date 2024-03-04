// --== CS400 Project Three File Header ==--
// Name: Roshni Venkat
// CSL Username: roshni
// Email: rvenkat@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: <any optional extra notes to your grader>

/**
 * Implementations of this interface can be used to create an Airport object.
 *
 * @author roshnivenkat
 */
public interface IAirport {

    /**
     * Gets the name of the city that the airport is located in.
     *
     * @return name of the city
     */
    String getAirport();

    /**
     * Checks whether this Airport equals another object passed as input
     *
     * @param o other object to compare
     * @return true if o refers to an Airport object with the same name
     */
    @Override
    public boolean equals(Object o);

    /**
     * Calculates the hashcode value of the name of the airport
     *
     * @return hashcode value of the name of the airport
     */
    @Override
    public int hashCode();
}
