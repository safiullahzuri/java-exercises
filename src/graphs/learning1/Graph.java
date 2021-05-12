package graphs.learning1;

import java.util.List;

public interface Graph<V> {

    public int getSize();

    public List<V> getVertices();

    public int getIndex(V v);

    public List<Integer> getNeighbors(int index);

    public int getDegree(int v);

    public void printEdges();

    public V getVertex(int index);

    public void clear();

    public boolean addVertex(V vertex);

    public boolean addEdge(int u, int v);
    public List<List<Integer>> getConnectedComponents();
    public List<List<Integer>> getConnectedComponents2();

    public AbstractGraph<V>.Tree bfs(int vertexIndex);

    public AbstractGraph<V>.Tree dfs(int vertexIndex);



}
