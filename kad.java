public class kad {

    // Method to solve 0/1 Knapsack problem using Dynamic Programming
    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        // Build the DP table
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0; // Base case: no item or zero capacity
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Return the maximum value that can be put in the knapsack of capacity
        return dp[n][capacity];
    }

    // Main method to test the knapsack function
    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 8};
        int[] values = {20, 5, 10, 50};
        int capacity = 10;

        int maxValue = knapsack(weights, values, capacity);
        System.out.println("Maximum value in knapsack = " + maxValue);
    }
}
