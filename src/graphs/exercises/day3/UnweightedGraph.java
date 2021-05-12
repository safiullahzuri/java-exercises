package graphs.exercises.day3;

import java.util.ArrayList;
import java.util.List;

public class UnweightedGraph<V> extends AbstractGraph<V> {

    public UnweightedGraph(List<Edge> edges, int numberOfVertices){
        super(edges, numberOfVertices);
    }

    public UnweightedGraph(int[][] edges, int numberOfVertices){
        super(edges, numberOfVertices);
    }

    public UnweightedGraph(V[] vertices, int[][] edges){
        super(vertices, edges);
    }



    /**
     * Obtain a Hamiltonian Path tree starting from vertex v
     */
    /**
     * To be discussed in Section 13.6
     */
//    public List<Integer> getHamiltonianPath(int v) {
//        List<Integer> searchOrders = new ArrayList<Integer>();
//        int[] parent = new int[getSize()];
//        for (int i = 0; i < parent.length; i++) {
//            parent[i] = -1; // Initialize parent[i] to -1
//        }
//        // Mark visited vertices
//        boolean[] isVisited = new boolean[getSize()];
//
//        // Recursively search
//        hamiltonianPath(v, parent, searchOrders, isVisited);
//
//        // Return a search tree
//        return searchOrders;
//    }
//
//    /**
//     * Recursive method for hamiltonianPath search
//     */
//    private void hamiltonianPath(int v, int[] parent, List<Integer> searchOrders,
//                                 boolean[] isVisited) {
//        //System.out.println("Visiting: " + v + "which has " + neighbors[v].size() + " neighbors.");
//        // Store the visited vertex
//        searchOrders.add(v);
//        isVisited[v] = true; // Vertex v visited
//
//        for (int i : getNeighbors(v)) {
//            if (!isVisited[i]) {
//                parent[i] = v; // The parent of vertex i is v
//                hamiltonianPath(i, parent, searchOrders, isVisited); // Recursive search
//            }
//
//        }
//        if (!allVisited(isVisited)) {
//            //System.out.println("Backtracking...");
//            isVisited[v] = false;
//            parent[v] = -1;
//            searchOrders.remove(new Integer(v));
//        }
//    }
//
//    /**
//     * Obtain a Hamiltonian Cycle tree starting from vertex v
//     */
//    /**
//     * To be discussed in Section 13.6
//     */
//    public List<Integer> getHamiltonianCycle(int v) {
//        List<Integer> searchOrders = new ArrayList<>();
//        int[] parent = new int[getSize()];
//        for (int i = 0; i < parent.length; i++) {
//            parent[i] = -1; // Initialize parent[i] to -1
//        }
//        // Mark visited vertices
//        boolean[] isVisited = new boolean[getSize()];
//
//        // Recursively search
//        hamiltonianCycle(v, parent, searchOrders, isVisited);
//
//        // Return a search tree
//        return searchOrders;
//    }
//
//    /**
//     * Recursive method for hamiltonianPath search
//     */
//    private void hamiltonianCycle(int v, int[] parent, List<Integer> searchOrders,
//                                  boolean[] isVisited) {
//        //System.out.println("Visiting: " + v + "which has " + neighbors[v].size() + " neighbors.");
//        // Store the visited vertex
//        searchOrders.add(v);
//        isVisited[v] = true; // Vertex v visited
//
//        for (int i : getNeighbors(v)) {
//            if (!isVisited[i]) {
//                parent[i] = v; // The parent of vertex i is v
//                hamiltonianCycle(i, parent, searchOrders, isVisited); // Recursive search
//            }
//
//        }
//        if (!allVisited(isVisited) || !checkCycle(searchOrders)) {
//            //System.out.println("Backtracking...");
//            isVisited[v] = false;
//            parent[v] = -1;
//            searchOrders.remove(new Integer(v));
//        }
//    }
//
//    private boolean allVisited(boolean[] isVisited) {
//        for (int i = 0; i < isVisited.length; i++) {
//            if (!isVisited[i]) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean checkCycle(List<Integer> searchOrders) {
//
//        int startVertex = (Integer) searchOrders.get(0);
//        int lastVertex = (Integer) searchOrders.get(searchOrders.size() - 1);
//        //System.out.println("startVertex: " + startVertex + "lastVertex: " + lastVertex);
//        int firstAbs = Math.abs((startVertex / 8) - (lastVertex / 8));
//        int secondAbs = Math.abs((startVertex % 8) - (lastVertex % 8));
//        if ((firstAbs == 2 && secondAbs == 1) || (firstAbs == 1 && secondAbs == 2)) {
//            return true;
//        }
//
//        return false;
//    }


}
