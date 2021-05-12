package graphs.exercises.day5;

import java.util.List;

public interface Graph {
    public int getSize();
    public Object[] getVertices();
    public Object getVertex(int v);
    public int getIndex(Object v);
    public List getNeighbors(int v);
    public int getDegree(int v);

    public int[][] getAdjacencyMatrix();

    public void printAdjacencyMatrix();
    public void printEdges();

    public UnweightedGraph.Tree bfs(int v);
    public UnweightedGraph.Tree dfs(int v);

    public List<Integer> getHamiltonianPath(int v);
    public List<Integer> getHamiltonianCycle(int v);

    public List<Integer> getHamiltonianCycle();

}
