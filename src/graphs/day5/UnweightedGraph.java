package graphs.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class UnweightedGraph<V> implements Graph<V> {

    protected List<V> vertices = new ArrayList<>();
    protected List<List<Edge>> neighbors = new ArrayList<>();

    public UnweightedGraph(){}

    public UnweightedGraph(V[] vertices, int[][] edges){
        for (int i=0; i<vertices.length; i++){
            addVertex(vertices[i]);
        }
        createAdjacencyLists(edges, vertices.length);
    }

    public UnweightedGraph(List<V> vertices, List<Edge> edges){
        for (int i=0; i<vertices.size(); i++){
            addVertex(vertices.get(i));
        }
        createAdjacencyLists(edges, vertices.size());
    }

    public UnweightedGraph(List<Edge> edges, int numVertices){
        for (int i=0; i<numVertices; i++){
            addVertex((V)new Integer(i));
        }
        createAdjacencyLists(edges, numVertices);
    }



    private void createAdjacencyLists(int[][] edges, int numVertices){
        for (int i=0; i<edges.length; i++){
            addEdge(edges[i][0], edges[i][1]);
        }
    }

    private void createAdjacencyLists(List<Edge> edges, int numVertices){
        for (Edge edge: edges ){
            addEdge(edge.u, edge.v);
        }
    }

    @Override
    public int getSize() {
        return vertices.size();
    }

    @Override
    public List<V> getVertices() {
        return vertices;
    }

    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }

    @Override
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    @Override
    public List<Integer> getNeighbors(int index) {
        List<Integer> vertexNeighbors  = new ArrayList<>();
        for (Edge edge: neighbors.get(index) ){
            vertexNeighbors.add(edge.v);
        }
        return vertexNeighbors;
    }

    @Override
    public int getDegree(int v) {
        return neighbors.get(v).size();
    }

    @Override
    public void printEdges() {
        for (int u=0; u<neighbors.size(); u++){
            System.out.print(getVertex(u)+" ("+u+"): ");
            for (Edge edge: neighbors.get(u)){
                System.out.print("("+getVertex(edge.u)+", "+getVertex(edge.v)+") ");
            }
            System.out.println();
        }
    }

    @Override
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    @Override
    public boolean addVertex(V vertex) {
        if (!vertices.contains(vertex)){
            neighbors.add(new ArrayList<>());
            vertices.add(vertex);
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(int u, int v) {
        return addEdge(new Edge(u, v));
    }

    public boolean addEdge(Edge edge){
        if (edge.u<0 || edge.u>getSize()-1){
            throw new IllegalArgumentException("invalid index: "+edge.u);
        }
        if (edge.v<0 || edge.v>getSize()-1)
            throw new IllegalArgumentException("invalid index: "+edge.v);

        if (!neighbors.get(edge.u).contains(edge)){
            neighbors.get(edge.u).add(edge);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public SearchTree dfs(int v) {
        int[] parent = new int[getSize()];
        Arrays.fill(parent, -1);
        List<Integer> searchOrder = new ArrayList<>();
        boolean[] isVisited = new boolean[getSize()];

        dfs(v, parent, searchOrder, isVisited);
        return new SearchTree(v, parent, searchOrder);
    }

    private void dfs(int v, int[] parent, List<Integer> searchOrder, boolean[] isVisited){
        searchOrder.add(v);
        isVisited[v] = true;

        for (Edge e: neighbors.get(v)){
            if (!isVisited[e.v]){
                parent[e.v] = v;
                dfs(e.v, parent, searchOrder, isVisited);
            }
        }
    }

    @Override
    public SearchTree bfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[getSize()];
        Arrays.fill(parent, -1);
        boolean[] isVisited = new boolean[getSize()];

        LinkedList<Integer> queue = new LinkedList<>();

        queue.offer(v);
        isVisited[v] = true;

        while (!queue.isEmpty()){
            int u = queue.poll();

            searchOrder.add(u);
            isVisited[u] = true;

            for (Edge edge: neighbors.get(u)){
                if (!isVisited[edge.v]){
                    queue.offer(edge.v);
                    isVisited[edge.v] = true;
                    parent[edge.v] = u;
                }
            }
        }
        return new SearchTree(v, parent, searchOrder);
    }



    public class SearchTree{

        private int root;
        private int[] parent;
        private List<Integer> searchOrder;

        public SearchTree(int root, int[] parent, List<Integer> searchOrder){
            this.root = root;
            this.parent= parent;
            this.searchOrder=  searchOrder;
        }

        public int getRoot() {
            return root;
        }

        public int getParent(int v) {
            return parent[v];
        }

        public List<Integer> getSearchOrder() {
            return searchOrder;
        }

        public int getNumberOfVerticesFound(){
            return searchOrder.size();
        }

        public List<V> getPath(int index){
            ArrayList<V> path = new ArrayList<>();
            do {
                path.add(vertices.get(index));
                index = parent[index];
            }while (index != -1);

            return path;
        }

        public void printPath(int index){
            List<V> path = getPath(index);
            System.out.println("A path from "+vertices.get(root)+" to "+vertices.get(index)+": ");
            for (int i=path.size()-1; i>=0; i--){
                System.out.print(path.get(i)+" ");
            }
        }

        public void printTree(){
            System.out.println("Root is: "+vertices.get(root));
            System.out.print("Edges: ");
            for (int i=0; i<parent.length; i++){
                if (parent[i] != -1){
                    System.out.print("("+vertices.get(parent[i]) +", "+vertices.get(i)+") ");
                }
            }
            System.out.println();
        }
    }
}
