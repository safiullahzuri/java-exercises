package graphs.learning2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestWeightedGraph {

    public static void main(String[] args) {

         String[] vertices = {"A", "B", "C", "D", "E", "F", "G"};
         List<WeightedEdge> edges = new ArrayList<>();
         edges.add(new WeightedEdge(0,1,3));
         edges.add(new WeightedEdge(1,2,4));
         edges.add(new WeightedEdge(0,3,5));
         edges.add(new WeightedEdge(1,4,6));
         edges.add(new WeightedEdge(3,4,2));
         edges.add(new WeightedEdge(4,5,7));
         edges.add(new WeightedEdge(3,6,8));
         edges.add(new WeightedEdge(5,6,3));
         edges.add(new WeightedEdge(2, 5, 10));
         edges.add(new WeightedEdge(4,6,1));


         WeightedGraph<String> graph1 = new WeightedGraph<>(Arrays.asList(vertices), edges);
//         WeightedGraph.MST mst =  graph1.getMinimumSpanningTreeKruskal(0);
//         System.out.println(mst.searchOrder);
//         System.out.println(mst.weightedEdges);
         System.out.println(graph1.getHamiltonianCycle(0));

         //WeightedGraph<Integer> graph2 = new WeightedGraph<>(edges, 5);
         //System.out.println("\nThe edges for graph2:");

         //graph2.printWeightedEdges();
        }
 }
