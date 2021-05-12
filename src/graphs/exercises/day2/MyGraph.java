package graphs.exercises.day2;

import java.util.ArrayList;
import java.util.List;

public class MyGraph<V> extends UnweightedGraph<V> {

    public MyGraph(V[] vertices, int[][] edges){
        super(vertices, edges);
    }

    public List<List<Integer>> getConnectedComponents(){
        List<Integer> vertices = new ArrayList<>();
        for (V v: getVertices()){
            vertices.add(getIndex(v));
        }

        SpanningTree spanningTree =  bfs(0);
        List<List<Integer>> components = new ArrayList<>();

        while (vertices.size() > 0){
            components.add(spanningTree.getSearchOrder());
            for (int v: spanningTree.getSearchOrder()){
                vertices.remove(new Integer(v));
            }
            if (vertices.size() > 0){
                spanningTree = bfs(vertices.get(0));
            }
        }
        return components;
    }

    public List<List<Integer>> getConnectedComponents2() {
        List<List<Integer>> components = new ArrayList<>();
        List<Integer> v = new ArrayList<>(vertices.size());
        for (int i = 0; i < vertices.size(); i++)
            v.add(i);

        getConnectedComponents2(v, components);
        return components;
    }

    /** Recursive method for finding connected components */
    public void getConnectedComponents2(
            List<Integer> v, List<List<Integer>> components) {
        if (v.size() > 0) {

            List<Integer> c = dfs(v.get(0)).getSearchOrder();
            components.add(c);
            v.removeAll(c);
            getConnectedComponents2(v, components);

        }
    }

}
