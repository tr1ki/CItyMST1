import java.util.*;

/**
 * Implements Kruskal's algorithm to find the Minimum Spanning Tree (MST)
 * of a given weighted, undirected graph.
 *
 * <p>The algorithm sorts all edges by weight and then iteratively adds the smallest
 * edge that does not form a cycle (using Disjoint Set Union to detect cycles).</p>
 */
public class Kruskal {

    /**
     * Disjoint Set Union (Union-Find) data structure used to efficiently
     * check whether adding an edge would form a cycle.
     */
    static class DSU {
        // Maps each vertex to its parent in the disjoint set
        Map<String, String> parent = new HashMap<>();

        /**
         * Finds the representative (root) of the set containing x,
         * applying path compression for optimization.
         *
         * @param x the vertex to find
         * @return the representative of the set
         */
        String find(String x) {
            if (!parent.containsKey(x)) parent.put(x, x);
            if (!parent.get(x).equals(x))
                parent.put(x, find(parent.get(x))); // Path compression
            return parent.get(x);
        }

        /**
         * Merges the sets containing vertices a and b.
         *
         * @param a first vertex
         * @param b second vertex
         */
        void union(String a, String b) {
            parent.put(find(a), find(b));
        }
    }

    /**
     * Executes Kruskal's algorithm on the given graph.
     *
     * @param graph The input graph (must contain nodes and weighted edges)
     * @return MSTResult object containing the resulting MST, total cost,
     *         number of operations, and execution time.
     */
    public static MSTResult run(Graph graph) {
        long start = System.nanoTime();
        int operations = 0;

        // Sort edges by weight (ascending order)
        List<Edge> edges = new ArrayList<>(graph.edges);
        Collections.sort(edges);

        DSU dsu = new DSU();
        List<Edge> mst = new ArrayList<>();

        // Build the MST
        for (Edge e : edges) {
            operations++;
            if (!dsu.find(e.from).equals(dsu.find(e.to))) {
                dsu.union(e.from, e.to);
                mst.add(e);
            }
        }

        // Calculate total cost
        int totalCost = mst.stream().mapToInt(edge -> edge.weight).sum();
        long end = System.nanoTime();

        // === Console output for report ===
        System.out.println("\n=== Kruskal's Algorithm Results ===");
        System.out.println("Number of vertices: " + graph.nodes.size());
        System.out.println("Number of edges: " + graph.edges.size());
        System.out.println("MST edges:");
        for (Edge edge : mst) {
            System.out.println(edge.from + " - " + edge.to + " : " + edge.weight);
        }
        System.out.println("Total cost of MST: " + totalCost);
        System.out.println("Operations performed: " + operations);
        System.out.println("Execution time (ms): " + (end - start) / 1_000_000.0);
        System.out.println("================================\n");

        return new MSTResult(mst, totalCost, operations, (end - start) / 1_000_000.0);
    }
}
