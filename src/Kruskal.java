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

        return new MSTResult(mst, totalCost, operations, (end - start) / 1_000_000.0);
    }
}
