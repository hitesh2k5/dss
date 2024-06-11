import java.util.*;

public class tspbab {

    // Class to represent a state in the search space
    static class State implements Comparable<State> {
        int city; // The current city
        int level; // The current level in the search space
        int cost; // The cost so far
        BitSet visited; // Visited cities so far

        State(int city, int level, int cost, BitSet visited) {
            this.city = city;
            this.level = level;
            this.cost = cost;
            this.visited = (BitSet) visited.clone();
            this.visited.set(city);
        }

        // Compare states based on their cost
        public int compareTo(State other) {
            return this.cost - other.cost;
        }
    }

    // Method to solve the Traveling Salesman Problem using Branch and Bound
    public static int tspBranchAndBound(int[][] graph) {
        int n = graph.length;
        PriorityQueue<State> pq = new PriorityQueue<>();
        BitSet visited = new BitSet(n);

        // Start from city 0
        State initial = new State(0, 0, 0, visited);
        pq.add(initial);

        // Initialize minimum cost to maximum integer value
        int minCost = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            State current = pq.poll();

            // If all cities have been visited
            if (current.level == n - 1) {
                minCost = Math.min(minCost, current.cost + graph[current.city][0]);
            }

            // Explore all unvisited cities
            for (int nextCity = 0; nextCity < n; nextCity++) {
                if (!current.visited.get(nextCity)) {
                    int newCost = current.cost + graph[current.city][nextCity];

                    // Compute lower bound for the new state
                    int lb = newCost + computeLB(graph, current.visited, nextCity);

                    // Prune the branch if lower bound exceeds the current minimum cost
                    if (lb < minCost) {
                        pq.add(new State(nextCity, current.level + 1, newCost, current.visited));
                    }
                }
            }
        }

        return minCost;
    }

    // Method to compute a lower bound for the current state
    private static int computeLB(int[][] graph, BitSet visited, int currentCity) {
        int n = graph.length;
        int lb = 0;

        // Compute the lower bound using minimum spanning tree (MST)
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[currentCity] = 0;

        for (int i = 0; i < n; i++) {
            int minIndex = currentCity;
            for (int j = 0; j < n; j++) {
                if (!visited.get(j) && (minIndex == currentCity || minDist[j] < minDist[minIndex])) {
                    minIndex = j;
                }
            }
            visited.set(minIndex);

            for (int j = 0; j < n; j++) {
                if (!visited.get(j)) {
                    minDist[j] = Math.min(minDist[j], graph[minIndex][j]);
                }
            }
        }

        for (int dist : minDist) {
            lb += dist;
        }

        return lb;
    }

    // Main method to test the TSP using Branch and Bound
    public static void main(String[] args) {
        int[][] graph = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        int minCost = tspBranchAndBound(graph);
        System.out.println("Minimum cost to visit all cities: " + minCost);
    }
}
