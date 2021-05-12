package graphs.exercises.day2;

import java.util.List;

public interface Graph<V> {

    public int getSize();
    public List<V> getVertices();
    public V getVertex(int index);
    public int getIndex(V v);
    public List<Integer> getNeighbors(int index);
    public int getDegree(int index);
    public void printEdges();
    public void clear();
    public boolean addVertex(V v);
    public boolean addEdge(int u, int v);

    public List<Integer> shortestPath(int u, int v);
    public List<Integer> shortestPath2(int u, int v);

    //dfs and bfs
    public AbstractGraph<V>.SpanningTree bfs(int index);

    public AbstractGraph<V>.SpanningTree dfs(int index);

}
