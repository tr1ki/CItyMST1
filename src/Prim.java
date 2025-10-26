import java.util.*;

/**
 * Implements Prim’s algorithm for finding the Minimum Spanning Tree (MST)
 * of a weighted undirected graph.
 *
 * <p>The algorithm starts from an arbitrary vertex and repeatedly adds
 * the smallest edge that connects a visited node to an unvisited one.</p>
 */
public class Prim {

    /**
     * Executes Prim’s algorithm on the given graph.
     *
     * @param graph the input graph containing nodes and weighted edges
     * @return an {@link MSTResult} containing the MST edges, total cost,
     *         number of operations, and execution time in milliseconds
     */
    public static MSTResult run(Graph graph) {
        long start = System.nanoTime();
        int operations = 0;

        // Build adjacency list (since the graph is undirected)
        Map<String, List<Edge>> adj = new HashMap<>();
        for (Edge e : graph.edges) {
            adj.computeIfAbsent(e.from, k -> new ArrayList<>()).add(e);
            adj.computeIfAbsent(e.to, k -> new ArrayList<>()).add(new Edge(e.to, e.from, e.weight)); // mirror edge
        }

        Set<String> visited = new HashSet<>();
        List<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // Start from the first vertex
        String startNode = graph.nodes.get(0);
        visited.add(startNode);
        pq.addAll(adj.getOrDefault(startNode, new ArrayList<>()));

        // Main loop: continue until MST has (V - 1) edges
        while (!pq.isEmpty() && mst.size() < graph.nodes.size() - 1) {
            Edge e = pq.poll();
            operations++;

            // Skip if destination vertex already visited
            if (visited.contains(e.to))
                continue;

            // Add edge to MST
            mst.add(e);
            visited.add(e.to);

            // Add all edges from the new vertex to the priority queue
            for (Edge next : adj.getOrDefault(e.to, new ArrayList<>())) {
                if (!visited.contains(next.to)) {
                    pq.add(next);
                }
            }
        }

        int totalCost = mst.stream().mapToInt(edge -> edge.weight).sum();
        long end = System.nanoTime();

        // === Report Output ===
        System.out.println("\n=== Prim's Algorithm Results ===");
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
