import java.util.Arrays;

public class tsp {

    // Method to solve the Travelling Salesperson Problem using Dynamic Programming
    public static int tspp(int[][] graph) {
        int n = graph.length;
        int VISITED_ALL = (1 << n) - 1;
        int[][] dp = new int[1 << n][n];

        // Initialize DP table with a large value (infinity)
        for (int i = 0; i < (1 << n); i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // Starting at the first city, with only the first city visited
        dp[1][0] = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) { // If city i is in the subset represented by mask
                    for (int j = 0; j < n; j++) {
                        if ((mask & (1 << j)) == 0) { // If city j is not in the subset
                            dp[mask | (1 << j)][j] = Math.min(dp[mask | (1 << j)][j], dp[mask][i] + graph[i][j]);
                        }
                    }
                }
            }
        }

        // Find the minimum cost to return to the starting city
        int minCost = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            minCost = Math.min(minCost, dp[VISITED_ALL][i] + graph[i][0]);
        }

        return minCost;
    }

    // Main method to test the TSP function
    public static void main(String[] args) {
        int[][] graph = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        int minCost = tspp(graph);
        System.out.println("The minimum cost to visit all cities is: " + minCost);
    }
}
