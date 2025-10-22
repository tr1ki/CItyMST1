import java.util.*;
public class Prim {
    public static MSTResult run(Graph graph){
        long start = System.nanoTime();
        int operations = 0;

        Map<String, List<Edge>> adj = new HashMap<>();
        for (Edge e : graph.edges) {
            adj.computeIfAbsent(e.from, k -> new ArrayList<>()).add(e);
            adj.computeIfAbsent(e.to, k -> new ArrayList<>()).add(new Edge(e.from, e.to, e.weight));
        }
        Set<String> visited = new HashSet<>();
        List<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

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
