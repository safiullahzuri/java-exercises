package graphs.day5;

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
    public boolean addVertex(V vertex);

    public boolean addEdge(int u, int v);
    public UnweightedGraph<V>.SearchTree dfs(int v);
    public UnweightedGraph<V>.SearchTree bfs(int v);

    public List<Integer> getHamiltonianPath(V vertex);
    public List<Integer> getHamiltonianPath(int v);

    public List<Integer> getHamiltonianCycle();
    public List<Integer> getHamiltonianCycle(int v);


}
