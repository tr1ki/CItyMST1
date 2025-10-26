import java.util.List;

public class MSTResult {
    public List<Edge> mst_edges;
    public int total_cost;
    public int operations_count;
    public double execution_time_ms;

    public MSTResult(List<Edge> edges, int cost, int ops, double time) {
        this.mst_edges = edges;
        this.total_cost = cost;
        this.operations_count = ops;
        this.execution_time_ms = time;
    }
}
