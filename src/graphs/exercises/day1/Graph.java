package graphs.exercises.day1;

import java.util.List;

public interface Graph<V> {

    public int getSize();

    public List<V> getVertices();
    public V getVertex(int index);
    public int getIndex(V v);
    public List<Integer> getNeighbors(int index);
    public int getDegree(int v);
    public void printEdges();
    public void clear();
    public boolean addVertex(V v);
    public boolean addEdge(int u, int v);

    //tree for bfs
    public AbstractGraph<V>.SearchTree dfs(int v);

    public AbstractGraph<V>.SearchTree bfs(int v);

    //tree for dfs

}
