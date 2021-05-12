package graphs.learning1;

import java.util.List;

public class UnweightedGraph<V> extends AbstractGraph<V>{

    public UnweightedGraph(){}

    public UnweightedGraph(V[] vertices, int[][] edges){
        super(vertices, edges);
    }

    public UnweightedGraph(List<Edge> edges, int numberOfVertices){
        super(edges, numberOfVertices);
    }

    public UnweightedGraph(List<V> vertices, List<Edge> edges){
        super(vertices, edges);
    }


}
