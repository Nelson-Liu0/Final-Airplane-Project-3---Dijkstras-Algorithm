import java.util.List;

// this interface extends GraphADT with additional methods to satisfy requirements of our AirplaneRouter application
public interface IAirportGraph<NodeType, EdgeType extends Number> extends GraphADT<NodeType, EdgeType> {

    /**
     * Return a list of the reachable airports of departure city
     *
     * @param departure input of departure city from the AirplaneRouter application
     * @return a list of the reachable airports of departure city
     */
    public List<NodeType> getReachableAirports(NodeType departure);

}

