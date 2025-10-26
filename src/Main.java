import com.google.gson.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Create a Gson instance with pretty-printing enabled for readable JSON output
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Read and parse the input JSON file containing graph data
        JsonObject input = gson.fromJson(new FileReader("input.json"), JsonObject.class);

        // Deserialize the list of graphs from the JSON input
        List<Graph> graphs = Arrays.asList(gson.fromJson(input.get("graphs"), Graph[].class));

        // Prepare an array to store all computation results
        JsonArray results = new JsonArray();

        // Process each graph individually
        for (Graph g : graphs) {
            // Run Prim’s and Kruskal’s algorithms on the current graph
            MSTResult prim = Prim.run(g);
            MSTResult kruskal = Kruskal.run(g);

            // Create a JSON object to store results for this graph
            JsonObject res = new JsonObject();
            res.addProperty("graph_id", g.id);

            // Add basic input statistics (number of vertices and edges)
            JsonObject inputStats = new JsonObject();
            inputStats.addProperty("vertices", g.nodes.size());
            inputStats.addProperty("edges", g.edges.size());
            res.add("input_stats", inputStats);

            // Serialize algorithm results into JSON
            res.add("prim", gson.toJsonTree(prim));
            res.add("kruskal", gson.toJsonTree(kruskal));

            // Append this result to the final results array
            results.add(res);
        }

        // Build the final JSON output object
        JsonObject output = new JsonObject();
        output.add("results", results);

        // Write all results into 'output.json' file with automatic resource management
        try (FileWriter writer = new FileWriter("output.json")) {
            gson.toJson(output, writer);
        }

        // Notify the user that the results were successfully saved
        System.out.println("✅ Results written to output.json");
    }
}
