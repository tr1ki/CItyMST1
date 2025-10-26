# Minimum Spanning Tree (MST) — Prim’s and Kruskal’s Algorithm Analysis

**Student:** Balakarev Sergey  
**University:** AITU 
**Course:** Design and Analysis of Algorithms  
**Assignment:** №6 — Minimum Spanning Tree (Prim & Kruskal)

---

## 1. Summary of Input Data and Algorithm Results

### Input Graph
- **Number of vertices:** 5
- **Number of edges:** 7
- **Edges:**  
  A - B : 10  
  A - C : 3  
  B - C : 2  
  B - D : 5  
  C - D : 8  
  C - E : 7  
  D - E : 6

### Prim’s Algorithm Results
| Metric | Value |
|--------|--------|
| MST Edges | A - C : 3<br>C - B : 2<br>B - D : 5<br>D - E : 6 |
| Total Cost | **16** |
| Operations Performed | 5 |
| Execution Time (ms) | 3.5237 |

### Kruskal’s Algorithm Results
| Metric | Value |
|--------|--------|
| MST Edges | B - C : 2<br>A - C : 3<br>B - D : 5<br>D - E : 6 |
| Total Cost | **16** |
| Operations Performed | 7 |
| Execution Time (ms) | 1.7086 |

---

## 2. Comparison of Prim’s and Kruskal’s Algorithms

| Criterion | Prim’s Algorithm | Kruskal’s Algorithm |
|------------|------------------|---------------------|
| **Approach** | Grows MST one vertex at a time | Builds MST by sorting edges |
| **Data Structures** | Priority Queue (Min Heap) | Disjoint Set (Union-Find) |
| **Best for** | Dense graphs | Sparse graphs |
| **Time Complexity** | O(E log V) | O(E log E) |
| **Space Complexity** | O(V + E) | O(E) |
| **Observed Performance** | Slightly slower (3.52 ms) | Faster (1.70 ms) |
| **Operation Count** | 5 | 7 |

**Observation:**  
Both algorithms produced the same total MST cost (**16**), confirming correctness.  
Kruskal’s algorithm executed faster on this dataset, likely due to efficient sorting and fewer priority queue operations.

---

## 3. Conclusions

- **For sparse graphs** (few edges compared to vertices): **Kruskal’s algorithm** is generally more efficient.
- **For dense graphs** (many edges): **Prim’s algorithm** performs better because it doesn’t need to sort all edges.
- **Implementation complexity:**
    - Prim’s algorithm is easier to implement with adjacency lists and priority queues.
    - Kruskal’s algorithm benefits from a clean design with union-find structures.
- **In this experiment:**  
  Kruskal’s algorithm was faster, but both algorithms achieved identical MST costs and correctness.

---

## 4. References

1. Wikipedia. *«Алгоритм Крускала»* — [https://ru.wikipedia.org/wiki/Алгоритм_Крускала](https://ru.wikipedia.org/wiki/Алгоритм_Крускала)
2. Wikipedia. *«Алгоритм Прима»* — [https://ru.wikipedia.org/wiki/Алгоритм_Прима](https://ru.wikipedia.org/wiki/Алгоритм_Прима)
3. Habr. *«Алгоритмы поиска минимального остовного дерева: Прима и Крускала»* — [https://habr.com/ru/articles/569444/](https://habr.com/ru/articles/569444/)


---

