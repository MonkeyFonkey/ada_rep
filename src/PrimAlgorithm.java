import java.util.Arrays;

public class PrimAlgorithm {
    private int[][] graph;
    private int numVertices;

    public PrimAlgorithm(int[][] graph) {
        this.graph = graph;
        numVertices = graph.length;
    }

    public void primMST() {
        int[] parent = new int[numVertices];
        int[] key = new int[numVertices];
        boolean[] mstSet = new boolean[numVertices];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(mstSet, false);

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < numVertices - 1; count++) {
            int u = getMinimumKeyVertex(key, mstSet);

            mstSet[u] = true;

            for (int v = 0; v < numVertices; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent);
    }

    private int getMinimumKeyVertex(int[] key, boolean[] mstSet) {
        int minKey = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < numVertices; v++) {
            if (!mstSet[v] && key[v] < minKey) {
                minKey = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    private void printMST(int[] parent) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < numVertices; i++)
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
    }

    public static void main(String[] args) {
        int[][] graph = new int[][] {
                { 0, 2, 0, 6, 0 },
                { 2, 0, 3, 8, 5 },
                { 0, 3, 0, 0, 7 },
                { 6, 8, 0, 0, 9 },
                { 0, 5, 7, 9, 0 } };

        PrimAlgorithm prims = new PrimAlgorithm(graph);
        prims.primMST();
    }
}
