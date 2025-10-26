import java.util.*;
<<<<<<< HEAD

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

=======
public class Prim {
    public static MSTResult run(Graph graph){
        long start = System.nanoTime();
        int operations = 0;

        Map<String, List<Edge>> adj = new HashMap<>();
        for (Edge e : graph.edges) {
            adj.computeIfAbsent(e.from, k -> new ArrayList<>()).add(e);
            adj.computeIfAbsent(e.to, k -> new ArrayList<>()).add(new Edge(e.from, e.to, e.weight));
        }
>>>>>>> a59628bf6eb05784c9e5bfc99832dd1bdc08079d
        Set<String> visited = new HashSet<>();
        List<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

<<<<<<< HEAD
        // начинаем с первой вершины
=======
>>>>>>> a59628bf6eb05784c9e5bfc99832dd1bdc08079d
        String startNode = graph.nodes.get(0);
        visited.add(startNode);
        pq.addAll(adj.getOrDefault(startNode, new ArrayList<>()));

        while (!pq.isEmpty() && mst.size() < graph.nodes.size() - 1) {
            Edge e = pq.poll();
            operations++;
<<<<<<< HEAD

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

        return new MSTResult(mst, totalCost, operations, (end - start) / 1_000_000.0);
    }
}
=======
            if (visited.contains(e.to))
                continue;
            mst.add(e);
            visited.add(e.to);
            for (Edge next : adj.getOrDefault(e.to, new ArrayList<>())) {

                visited.contains(next.to)) pq.add(next);
            }
        }
        int totalCost =
                mst.stream().mapToInt(edge -> edge.weight).sum();
        long end = System.nanoTime();

        return new MSTResult(mst,totalCost, operations, (end - start) / 1_000_000.0);
    }
}


}
>>>>>>> a59628bf6eb05784c9e5bfc99832dd1bdc08079d
