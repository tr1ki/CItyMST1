/**
 * Represents a weighted edge in an undirected graph.
 * Implements Comparable to allow sorting edges by weight.
 */
public class Edge implements Comparable<Edge> {
    // The starting vertex of the edge
    public String from;

    // The ending vertex of the edge
    public String to;

    // The weight (cost) associated with this edge
    public int weight;

    /**
     * Constructs an edge with the given endpoints and weight.
     *
     * @param from   The source vertex
     * @param to     The destination vertex
     * @param weight The edge weight (e.g., construction cost)
     */
    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * Compares two edges based on their weights.
     * Enables sorting edges in ascending order (used in Kruskal's algorithm).
     *
     * @param other Another edge to compare with
     * @return A negative integer, zero, or a positive integer
     *         as this edge is less than, equal to, or greater than the specified edge
     */
    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}
