import java.util.Scanner;

public class ka {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 3; // Assuming 'n' is the number of vertices
        int[][] matrix = new int[n][n];
        int[] parent = new int[n];
        int min;
        int total = 0;
        int u = 0;
        int v = 0;
        int noOfEdges = 1;

        // Initialize the matrix and parent array
        for (int i = 0; i < n; i++) {
            parent[i] = 0;
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
                if (matrix[i][j] == 0) {
                    matrix[i][j] = 999;
                }
            }
        }

        // Kruskal's algorithm
        while (noOfEdges < n) {
            min = 999;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] < min) {
                        min = matrix[i][j];
                        u = i;
                        v = j;
                    }
                }
            }

            while (parent[u] != 0) {
                u = parent[u];
            }
            while (parent[v] != 0) {
                v = parent[v];
            }

            if (v != u) {
                noOfEdges++;
                System.out.println("Edge found: " + u + " -> " + v + " Minimum cost: " + min);
                total += min;
                parent[v] = u;
            }

            // This line should be inside the if block
            matrix[u][v] = matrix[v][u] = 999;
        }

        System.out.println("The weight of the MST is " + total);
        sc.close();
    }
}