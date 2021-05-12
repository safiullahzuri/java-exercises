package graphs.weightedGraph2;

import graphs.learning2.Edge;
import graphs.learning2.UnweightedGraph;

import java.util.ArrayList;
import java.util.List;

public class NineTailModel16 {


    public static final int NUMBER_OF_NODES = 65536;
    protected UnweightedGraph<Integer>.Tree tree;
    //private final int nodeSize = 9;

    public NineTailModel16(){
        List<WeightedEdge> edges = getEdges();

        WeightedGraph<Integer> graph = new WeightedGraph<>(edges, NUMBER_OF_NODES);
        tree = graph.bfs(65535);
    }

    public List<Integer> getShortestPath(int nodeIndex){
        return tree.getPath(nodeIndex);
    }

    public static void printNode(char[] node){
        for (int i=0; i<16; i++){
            if (i%4 != 3){
                System.out.print(node[i]);
            }else {
                System.out.println(node[i]);
            }
        }
        System.out.println();
    }

    private List<WeightedEdge> getEdges(){
        List<WeightedEdge> edges = new ArrayList<>();
        for (int i=0; i<NUMBER_OF_NODES; i++){
            char[] node = getNode(i);
            for (int k=0; k<16; k++){
                if (node[k] == 'H'){
                    int v = getFlippedNode(node, k);
                    int w = getNumberOfFlippedCells(i, v);
                    edges.add(new WeightedEdge(v, i, w));
                }
            }
        }
        return edges;
    }

    private int getNumberOfFlippedCells(int u, int v){
        char[] node1 = getNode(u);
        char[] node2 = getNode(v);

        int count = 0;

        for (int i=0; i<node1.length; i++){
            if (node1[i] != node2[i])count++;
        }
        return count;
    }

    public int getFlippedNode(char[] node, int position){
        int row = position/4;
        int col = position%4;

        flipACell(node, row, col);
        flipACell(node, row-1, col);
        flipACell(node, row+1, col);
        flipACell(node, row, col-1);
        flipACell(node, row, col+1);

        return getIndex(node);
    }


    public static int getIndex(char[] node){
        int result = 0;
        for (int i=0; i<node.length; i++){
            if (node[i] == 'H'){
                result = result*2;
            }else{
                result = result*2 +1;
            }
        }
        return result;
    }

    public void flipACell(char[] node, int row, int col){
        if (row>=0 && row<=3 && col>=0 && col<=3){
            if (node[row*4+col] == 'H'){
                node[row*4+col] = 'T';
            }else{
                node[row*4+col] = 'H';
            }
        }
    }


    public static char[] getNode(int u){
        char[] result= new char[16];
        for (int i=0; i<16; i++){
            int a = u%2;
            if (a == 0){
                result[15-i] = 'H';
            }else{
                result[15-i] = 'T';
            }
            u = u/2;
        }
        return result;
    }

}
