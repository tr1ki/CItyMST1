import java.util.*;

public class Kruskal {
    static class DSU {
        Map<String, String> parent = new HashMap<>();

        String find(String x) {
            if (!parent.containsKey(x)) parent.put(x, x);
            if (!parent.get(x).equals(x))
                parent.put(x, find(parent.get(x)));
            return parent.get(x);
        }

        void union(String a, String b) {
            parent.put(find(a), find(b));
        }
    }

    public static MSTResult run(Graph graph) {
        long start = System.nanoTime();
        int operations = 0;

        List<Edge> edges = new ArrayList<>(graph.edges);
        Collections.sort(edges);
        DSU dsu = new DSU();

        List<Edge> mst = new ArrayList<>();

        for (Edge e : edges) {
            operations++;
            if (!dsu.find(e.from).equals(dsu.find(e.to))) {
                dsu.union(e.from, e.to);
                mst.add(e);
            }
        }

        int totalCost = mst.stream().mapToInt(edge -> edge.weight).sum();
        long end = System.nanoTime();

        // === Вывод информации для отчёта ===
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
