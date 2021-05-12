package graphs.exercises.day1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGraph<V> implements Graph<V> {

    protected List<V> vertices = new ArrayList<>();
    protected List<List<Edge>> neighbors = new ArrayList<>();

    protected AbstractGraph(){}

    protected AbstractGraph(V[] vertices, int[][] edges){
        for (V v: vertices){
            addVertex(v);
        }
        createAdjacencyList(edges, vertices.length);
    }

    protected AbstractGraph(List<V> vertices, List<Edge> edges){
        for (V v: vertices){
            addVertex(v);
        }
        createAdjacencyList(edges, vertices.size());
    }

    protected AbstractGraph(List<Edge> edges, int numberOfVertices){
        for (int i=0; i<numberOfVertices; i++){
            addVertex((V)(new Integer(i)));
        }
        createAdjacencyList(edges, numberOfVertices);
    }

    protected AbstractGraph(int[][] edges, int numberOfVertices){
        for (int i=0; i<numberOfVertices; i++){
            addVertex((V)new Integer(i));
        }
        createAdjacencyList(edges, numberOfVertices);
    }


    @Override
    public boolean addVertex(V v) {
        if (!vertices.contains(v)){
            vertices.add(v);
            neighbors.add(new ArrayList<>());
            return true;
        }else {
            return false;
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
        List<Integer> result = new ArrayList<>();
        for (Edge edge: neighbors.get(index)){
            result.add(edge.v);
        }
        return result;
    }

    @Override
    public int getDegree(int v) {
        return neighbors.get(v).size();
    }

    @Override
    public void printEdges() {
        System.out.println("Vertices are: ");
        System.out.println(vertices);

        System.out.println("Edges are: ");
        System.out.println(neighbors);
    }

    @Override
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    @Override
    public boolean addEdge(int u, int v) {
        return addEdge(new Edge(u, v));
    }

    private void createAdjacencyList(int[][] edges, int numberOfVertices){
        for (int i=0; i<edges.length; i++){
            addEdge(edges[i][0], edges[i][1]);
        }
    }

    private void createAdjacencyList(List<Edge> edges, int numberOfVertices){
        for (Edge edge: edges){
            addEdge(edge);
        }
    }

    public boolean addEdge(Edge edge){
        if (edge.u < 0 || edge.u >= getSize()){
            throw new IllegalArgumentException("No such index: "+edge.u);
        }

        if (edge.v<0 || edge.v >= getSize()){
            throw new IllegalArgumentException("No such index: "+edge.v);
        }
        if (!neighbors.get(edge.u).contains(edge)){
            neighbors.get(edge.u).add(edge);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public SearchTree bfs(int u) {
        int[] parent = new int[getSize()];
        Arrays.fill(parent, -1);
        boolean[] isVisited = new boolean[getSize()];
        List<Integer> searchOrder = new ArrayList<>();

        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(u);
        isVisited[u] = true;

        while (!queue.isEmpty()){
            int v = queue.poll();
            searchOrder.add(v);

            for (Edge e: neighbors.get(v)){
                if (!isVisited[e.v]){
                    parent[e.v] = v;
                    queue.offer(e.v);
                    isVisited[e.v] = true;
                }
            }
        }
        return new SearchTree(u, parent, searchOrder);
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

    private void dfs(int root, int[] parent, List<Integer> searchOrder, boolean[] isVisited){
        searchOrder.add(root);
        isVisited[root] = true;

        for (Edge edge: neighbors.get(root)){
            if (!isVisited[edge.v]){
                parent[edge.v] = root;
                dfs(edge.v, parent,searchOrder, isVisited);
            }
        }

    }

    public class SearchTree{
        int root;
        int[] parent;
        List<Integer> searchOrder;

        public SearchTree(int root, int[] parent, List<Integer> searchOrder){
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        public int getRoot() {
            return root;
        }

        public int getParent(int index){
            return parent[index];
        }

        public int getNumberOfFoundVertices(){
            return searchOrder.size();
        }

        public List<Integer> getSearchOrder(){
            return searchOrder;
        }

        //from vertex:index to the root
        public List<V> getSearchPath(int index){
            ArrayList<V> path = new ArrayList<>();
            do {
                path.add(vertices.get(index));
                index = parent[index];
            }while (index != -1);
            return path;
        }

        public void printTree(){
            System.out.println("Root is: "+vertices.get(root));
            System.out.print("Edges: ");

            for (int i=0; i<parent.length; i++){
                if (parent[i] != -1){
                    System.out.print("("+vertices.get(parent[i])+", "+vertices.get(i)+") ");
                }
            }
            System.out.println();
        }

    }



}
