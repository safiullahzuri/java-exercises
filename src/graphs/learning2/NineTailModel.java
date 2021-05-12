package graphs.learning2;

import java.util.ArrayList;
import java.util.List;

public class NineTailModel {
    public final static int NUMBER_OF_NODES = 512;
    protected AbstractGraph<Integer>.Tree tree;

    public NineTailModel(){
        //create edges
        List<Edge> edges = getEdges();
        UnweightedGraph<Integer> graph = new UnweightedGraph<>(edges, NUMBER_OF_NODES);
        tree = graph.bfs(511);
    }

    private List<Edge> getEdges(){
        ArrayList<Edge> edges = new ArrayList<>();

        for (int i=0; i<NUMBER_OF_NODES; i++){
            for (int k=0; k<9; k++){
                char[] node = getNode(i);

                if (node[k] == 'H'){
                    int v = getFlippedNode(node, k);
                    //make an edge between the two
                    edges.add(new Edge(v, i));
                }
            }
        }
        return edges;
    }

    public int getFlippedNode(char[] node, int position ){
        int row = position/3;
        int column = position%3;

        flipACell(node, row, column);
        flipACell(node, row-1, column);
        flipACell(node, row+1, column);
        flipACell(node, row, column-1);
        flipACell(node, row, column+1);

        return getIndex(node);
    }

    public static int getIndex(char[] node){
        int result = 0;
        for (int i=0; i<9; i++){
            if (node[i] == 'T'){
                result = result*2 +1;
            }else{
                result = result*2;
            }
        }
        return result;
    }

    private static void flipACell(char[] node, int row, int column){
        if (row>=0 && row<=2 && column>=0 && column<=2){
            int position = row*3 + column;
            if (node[position] == 'H'){
                node[position] = 'T';
            }else{
                node[position] = 'H';
            }
        }
    }


    public static char[] getNode(int i){
        char[] result = new char[9];
        for (int x=0; x<9; x++){
            if (i%2 == 0){
                result[8-x] = 'H';
            }else{
                result[8-x] = 'T';
            }
            i = i/2;
        }
        return result;
    }

    public List<Integer> getShortestPath(int nodeIndex){
        return tree.getPath(nodeIndex);
    }

    public static void printNodes(char[] node){
        for (int i=0; i<9; i++){
            if (i%3 != 2){
                System.out.print(node[i]);
            }else{
                System.out.println(node[i]);
            }
        }
        System.out.println();
    }


}
