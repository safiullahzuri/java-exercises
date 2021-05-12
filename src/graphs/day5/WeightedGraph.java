package graphs.day5;

import java.util.ArrayList;
import java.util.List;

public class WeightedGraph<V> extends UnweightedGraph<V> {

    public WeightedGraph(){}

    public WeightedGraph(V[] vertices, int[][] edges){

    }


    private void createWeightedGraph(List<V> vertices, int[][] edges){
        this.vertices = vertices;
        for (int i=0; i<vertices.size(); i++){
            neighbors.add(new ArrayList<>());
        }
        for (int i=0; i<edges.length; i++){
        }
    }

    @Override
    public List<Integer> getHamiltonianPath(V vertex) {
        return null;
    }

    @Override
    public List<Integer> getHamiltonianPath(int v) {
        return null;
    }

    @Override
    public List<Integer> getHamiltonianCycle() {
        return null;
    }

    @Override
    public List<Integer> getHamiltonianCycle(int v) {
        return null;
    }
}
