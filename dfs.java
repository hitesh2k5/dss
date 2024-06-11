import java.util.*;

public class dfs {

    // Class to represent a graph
    static class Graph {
        private int V; // Number of vertices
        private LinkedList<Integer> adj[]; // Adjacency list

        // Constructor
        Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i) {
                adj[i] = new LinkedList();
            }
        }

        // Function to add an edge into the graph
        void addEdge(int v, int w) {
            adj[v].add(w); // Add w to v's list.
        }

        // Helper method for DFS that uses recursion
        void DFSUtil(int v, boolean visited[]) {
            // Mark the current node as visited and print it
            visited[v] = true;
            System.out.print(v + " ");

            // Recur for all the vertices adjacent to this vertex
            for (int n : adj[v]) {
                if (!visited[n]) {
                    DFSUtil(n, visited);
                }
            }
        }

        // The function to do DFS traversal. It uses recursive DFSUtil()
        void DFS(int v) {
            // Mark all the vertices as not visited (set as false by default)
            boolean visited[] = new boolean[V];

            // Call the recursive helper function to print DFS traversal
            DFSUtil(v, visited);
        }

        // A function to do DFS traversal of the complete graph
        void DFSComplete() {
            // Mark all the vertices as not visited (set as false by default)
            boolean visited[] = new boolean[V];

            // Call the recursive helper function to print DFS traversal
            // starting from all vertices one by one
            for (int i = 0; i < V; ++i) {
                if (!visited[i]) {
                    DFSUtil(i, visited);
                }
            }
        }
    }

    // Main method to test the DFS implementation
    public static void main(String args[]) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Depth First Traversal (starting from vertex 2)");

        g.DFS(2);
    }
}
