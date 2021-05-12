package graphs.exercises.day2;

import graphs.learning.AbstractGraph;
import graphs.learning.Graph;
import graphs.learning.UnweightedGraph;

import java.util.ArrayList;
import java.util.List;

public class TestDFS {

    public static void main(String[] args) {
//        String[] vertices = {"Seattle", "San Francisco", "Los Angeles", "Denver", "Kansas City", "Chicago", "Boston", "New York",
//                "Atlanta", "Miami", "Dallas", "Houston"};
//
//        int[][] edges = {
//                {0, 1}, {0, 3}, {0, 5},
//                {1, 0}, {1, 2}, {1, 3},
//                {2, 1}, {2, 3}, {2, 4}, {2, 10},
//                {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
//                {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
//                {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
//                {6, 5}, {6, 7},
//                {7, 4}, {7, 5}, {7, 6}, {7, 8},
//                {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
//                {9, 8}, {9, 11},
//                {10, 2}, {10, 4}, {10, 8}, {10, 11},
//                {11, 8}, {11, 9}, {11, 10}
//        };

        String[] vertices = {"Seattle", "San Francisco", "Los Angeles", "Denver","New York", "Dallas"};

        int[][] edges = {
                {0, 1}, {0, 3},{0, 5},
                {1, 0}, {1, 2},
                {2, 1}, {2, 3},{2, 5},
                {3, 0}, {3, 2}, {3, 4},
                {4, 3}, {4, 5},
                {5, 0}, {5, 2}, {5, 4}
        };

        MyGraph<String> graph = new MyGraph<>(vertices, edges);
        //System.out.println("graph is bipartite? "+graph.isBipartite());
        //System.out.println(graph.getBipartite());

        System.out.println("shortest path: "+graph.shortestPath(0,4));
        System.out.println("get path method: "+graph.getPath(0, 4));

//        System.out.println("is cyclic? "+graph.isCyclic());
//
//        for (int i=0; i<11; i++){
//            System.out.println(graph.getACycle(i));
//        }

    }
}