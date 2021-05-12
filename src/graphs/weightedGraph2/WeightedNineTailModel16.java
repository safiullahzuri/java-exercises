package graphs.weightedGraph2;

import java.util.ArrayList;
import java.util.List;

public class WeightedNineTailModel16 extends NineTailModel{

    public WeightedNineTailModel16(){
        List<WeightedEdge> edges = getEdges();
        WeightedGraph<Integer> graph = new WeightedGraph<>(edges, NUMBER_OF_NODES);
        tree = graph.getShortestPath(511);
    }

    private List<WeightedEdge> getEdges(){
        List<WeightedEdge> edges = new ArrayList<>();
        for (int u=0; u<NUMBER_OF_NODES; u++){
            for (int k=0; k<9; k++){
                char[] node = getNode(u);
                if (node[k] == 'H'){
                    int v = getFlippedNode(node, k);
                    int numberOfFlips = getNumberOfFlips(u, v);

                    edges.add(new WeightedEdge(v, u, numberOfFlips));
                }
            }
        }
        return edges;
    }

    private static int getNumberOfFlips(int u, int v){
        char[] node1 = getNode(u);
        char[] node2 = getNode(v);

        int count = 0;
        for (int i=0; i<node1.length; i++){
            if (node1[i] != node2[i])count++;
        }
        return count*3;
    }

}
