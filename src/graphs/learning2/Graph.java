package graphs.learning2;

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

    //dfs
    //bfs
    public AbstractGraph<V>.Tree dfs(int v);

    public AbstractGraph<V>.Tree bfs(int v);

    public AbstractGraph<V>.Tree dfs2(int v);

    public AbstractGraph<V>.Tree bfs2(int v);

}
