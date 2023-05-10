import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

class DisjointSet {
    int[] parent;
    int[] rank;

    public DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return;
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}

public class KruskalMST {
    private static void kruskalMST(int[][] graph, int numNodes) {
        List<Edge> edges = new ArrayList<>();
        DisjointSet ds = new DisjointSet(numNodes);

        // Add all edges to the list
        for (int i = 0; i < numNodes; i++) {
            for (int j = i + 1; j < numNodes; j++) {
                if (graph[i][j] != 0) {
                    edges.add(new Edge(i, j, graph[i][j]));
                }
            }
        }

        // Sort the edges in ascending order of weights
        Collections.sort(edges);

        List<Edge> mst = new ArrayList<>();
        int totalCost = 0;

        // Process edges in sorted order
        for (Edge edge : edges) {
            int srcRoot = ds.find(edge.src);
            int destRoot = ds.find(edge.dest);

            if (srcRoot != destRoot) {
                mst.add(edge);
                ds.union(srcRoot, destRoot);
                totalCost += edge.weight;
            }
        }

        System.out.println("Minimum Spanning Tree Edges:");
        for (Edge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + ", weight: " + edge.weight);
        }

        System.out.println("Total cost of the Minimum Spanning Tree: " + totalCost);
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("input5_10.txt"));
            int numNodes = Integer.parseInt(br.readLine().trim());

            int numEdges = Integer.parseInt(br.readLine());
            int[][] graph = new int[numNodes][numNodes];

            for (int i = 0; i < numEdges; i++) {
                String[] edgeData = br.readLine().split(" ");
                int src = Integer.parseInt(edgeData[0]);
                int dest = Integer.parseInt(edgeData[1]);
                int weight = Integer.parseInt(edgeData[2]);
                graph[src][dest] = weight;
                graph[dest][src] = weight;
            }

            br.close();

            kruskalMST(graph, numNodes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

