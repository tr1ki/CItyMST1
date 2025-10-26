import java.util.*;

public class Prim {
    public static MSTResult run(Graph graph) {
        long start = System.nanoTime();
        int operations = 0;

        // Создаем список смежности (для неориентированного графа)
        Map<String, List<Edge>> adj = new HashMap<>();
        for (Edge e : graph.edges) {
            adj.computeIfAbsent(e.from, k -> new ArrayList<>()).add(e);
            adj.computeIfAbsent(e.to, k -> new ArrayList<>()).add(new Edge(e.to, e.from, e.weight)); // ✅ исправлено
        }

        Set<String> visited = new HashSet<>();
        List<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // начинаем с первой вершины
        String startNode = graph.nodes.get(0);
        visited.add(startNode);
        pq.addAll(adj.getOrDefault(startNode, new ArrayList<>()));

        while (!pq.isEmpty() && mst.size() < graph.nodes.size() - 1) {
            Edge e = pq.poll();
            operations++;

            if (visited.contains(e.to))
                continue;

            mst.add(e);
            visited.add(e.to);

            // добавляем все рёбра, ведущие из новой вершины
            for (Edge next : adj.getOrDefault(e.to, new ArrayList<>())) {
                if (!visited.contains(next.to)) {
                    pq.add(next);
                }
            }
        }

        int totalCost = mst.stream().mapToInt(edge -> edge.weight).sum();
        long end = System.nanoTime();

        // === Вывод информации для отчёта ===
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
