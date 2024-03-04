/**
 * Implementations of this interface can be used to validate airport names.
 */
public interface IAirportValidator {

    /**
     * Checks if the given airport have connection between them
     *
     * @param departure
     * @param destination
     * @return true if if the given airports have connection, false otherwise
     */
    public boolean validate(String departure, String destination);

    /**
     * Checks if the given airport names exist in database
     *
     * @param airport
     *
     * @return true if if the given airport exist in database, false otherwise
     */
    public boolean validate(String airport);

}