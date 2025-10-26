import java.util.List;

/**
 * Represents the result of a Minimum Spanning Tree (MST) computation.
 *
 * <p>This class stores key performance metrics and the resulting MST edges
 * produced by algorithms such as Prim's or Kruskal's.</p>
 */
public class MSTResult {

    /** The list of edges included in the MST. */
    public List<Edge> mst_edges;

    /** The total cost (sum of all edge weights) of the MST. */
    public int total_cost;

    /** The number of operations performed during the algorithm execution. */
    public int operations_count;

    /** The total execution time in milliseconds. */
    public double execution_time_ms;

    /**
     * Constructs an MSTResult object with the given metrics and results.
     *
     * @param edges list of edges forming the MST
     * @param cost total cost of the MST
     * @param ops number of operations performed
     * @param time execution time in milliseconds
     */
    public MSTResult(List<Edge> edges, int cost, int ops, double time) {
        this.mst_edges = edges;
        this.total_cost = cost;
        this.operations_count = ops;
        this.execution_time_ms = time;
    }
}
