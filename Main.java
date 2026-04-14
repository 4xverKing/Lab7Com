import java.util.*;

class Main {
  public static void main(String[] args) {
    // 1. Define the vertices (buildings) based on the campus map
    String[] vertices = {
      "Liberal Arts",                  // 0
      "Student Services",             // 1
      "Health Careers & Sciences",    // 2
      "Health Technologies Center",   // 3
      "Recreation Center",            // 4
      "Technology Learning Center",   // 5
      "Business & Technology",        // 6
      "Theatre"                       // 7
    };

    // 2. Define the edges
    // Since this graph should act like walking in both directions,
    // each connection is added both ways.
    int[][] edges = {
      {0, 1}, {1, 0}, // Liberal Arts <-> Student Services
      {0, 7}, {7, 0}, // Liberal Arts <-> Theatre
      {1, 2}, {2, 1}, // Student Services <-> Health Careers & Sciences
      {1, 5}, {5, 1}, // Student Services <-> Technology Learning Center
      {1, 7}, {7, 1}, // Student Services <-> Theatre
      {2, 3}, {3, 2}, // Health Careers & Sciences <-> Health Technologies Center
      {2, 4}, {4, 2}, // Health Careers & Sciences <-> Recreation Center
      {4, 5}, {5, 4}, // Recreation Center <-> Technology Learning Center
      {5, 6}, {6, 5}, // Technology Learning Center <-> Business & Technology
      {6, 7}, {7, 6}  // Business & Technology <-> Theatre
    };

    // 3. Create the graph
    UnweightedGraph<String> graph = new UnweightedGraph<>(vertices, edges);

    // 4. Perform DFS starting from Business & Technology (vertex 6)
    UnweightedGraph<String>.SearchTree dfs = graph.dfs(6);

    // 5. Retrieve and print the search order
    System.out.println("DFS Search Order:");
    List<Integer> searchOrder = dfs.getSearchOrder();
    for (int i : searchOrder) {
      System.out.println(i + " - " + graph.getVertex(i));
    }

    // 6. Print parent-child relationships
    System.out.println("\nParent-Child Relationships:");
    for (int i = 0; i < graph.getSize(); i++) {
      int parent = dfs.getParent(i);
      if (parent == -1) {
        System.out.println(graph.getVertex(i) + " is the root");
      } else {
        System.out.println(graph.getVertex(parent) + " -> " + graph.getVertex(i));
      }
    }

    // 7. Print paths from Business & Technology
    System.out.println("\nPaths from Business & Technology:");
    
    dfs.printPath(3); // Health Technologies Center
    System.out.println();

    dfs.printPath(1); // Student Services
    System.out.println();

    dfs.printPath(4); // Recreation Center
    System.out.println();

    // 8. Print the entire DFS tree
    System.out.println("\nDFS Tree:");
    dfs.printTree();
  }
}