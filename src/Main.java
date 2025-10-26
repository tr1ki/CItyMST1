import com.google.gson.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject input = gson.fromJson(new FileReader("input.json"), JsonObject.class);

        List<Graph> graphs = Arrays.asList(gson.fromJson(input.get("graphs"), Graph[].class));
        JsonArray results = new JsonArray();

        for (Graph g : graphs) {
            MSTResult prim = Prim.run(g);
            MSTResult kruskal = Kruskal.run(g);

            JsonObject res = new JsonObject();
            res.addProperty("graph_id", g.id);

            JsonObject inputStats = new JsonObject();
            inputStats.addProperty("vertices", g.nodes.size());
            inputStats.addProperty("edges", g.edges.size());
            res.add("input_stats", inputStats);

            res.add("prim", gson.toJsonTree(prim));
            res.add("kruskal", gson.toJsonTree(kruskal));

            results.add(res);
        }

        JsonObject output = new JsonObject();
        output.add("results", results);
        try (FileWriter writer = new FileWriter("output.json")) {
            gson.toJson(output, writer);
        }

        System.out.println("✅ Results written to output.json");
    }
}
