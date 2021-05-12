package weightedGraphs.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class UnweightedGraph<V> implements Graph<V> {

    protected List<V> vertices = new ArrayList<>();
    protected List<List<Edge>> neighbors = new ArrayList<>();

    protected UnweightedGraph(){}

    protected UnweightedGraph(V[] vertices, int[][] edges){
        for (V v: vertices){
            addVertex(v);
        }
        createAdjacencyList(edges);
    }

    protected UnweightedGraph(List<V> vertices, List<Edge> edges){
        for (V v: vertices){
            addVertex(v);
        }
        createAdjacencyList(edges);
    }

    protected UnweightedGraph(List<Edge> edges, int numVertices){
        for (int i=0; i<numVertices; i++){
            vertices.add((V)new Integer(i));
        }
        createAdjacencyList(edges);
    }

    protected UnweightedGraph(int[][] edges, int numVertices){
        for (int i=0; i<numVertices; i++){
            vertices.add((V)new Integer(i));
        }
        createAdjacencyList(edges);
    }

    private void createAdjacencyList(List<Edge> edges){
        for (Edge edge: edges){
            addEdge(edge.u, edge.v);
        }
    }

    private void createAdjacencyList(int[][] edges){
        for (int i=0; i<edges.length; i++){
            addEdge(edges[i][0], edges[i][1]);
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
        for (Edge e: neighbors.get(index)){
            result.add(e.v);
        }
        return result;
    }

    @Override
    public int getDegree(int v) {
        return neighbors.get(v).size();
    }

    @Override
    public void printEdges() {
        for (int u=0; u< neighbors.size(); u++){
            System.out.println("Edges for vertex: "+getVertex(u));
            for (Edge edge: neighbors.get(u)){
                System.out.print(edge);
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
            vertices.add(vertex);
            neighbors.add(new ArrayList<>());
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addEdge(int u, int v) {
        return addEdge(new Edge(u, v));
    }

    protected boolean addEdge(Edge edge){
        if (edge.u < 0 || edge.u >= getSize()){
            throwException(edge.u);
        }
        if (edge.v < 0 || edge.v >=getSize()){
            throwException(edge.v);
        }
        if (!neighbors.get(edge.u).contains(edge)){
            neighbors.get(edge.u).add(edge);
            return true;
        }else{
            return false;
        }
    }

    private void throwException(int n){
        throw new IllegalArgumentException("No such index: "+n  );
    }

    @Override
    public SearchTree dfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[getSize()];
        Arrays.fill(parent, -1);

        boolean[] isVisited = new boolean[getSize()];
        dfs(v, parent, searchOrder, isVisited);
        return new SearchTree(v, parent, searchOrder);
    }

    private void dfs(int u, int[] parent, List<Integer> searchOrder, boolean[] isVisited){
        isVisited[u] = true;
        searchOrder.add(u);

        for (int n: getNeighbors(u)){
            if (!isVisited[n]){
                parent[n] = u;
                dfs(n, parent, searchOrder, isVisited);
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


        while (!queue.isEmpty()){
            int u = queue.poll();
            searchOrder.add(u);

            for (int n: getNeighbors(u)){
                if (!isVisited[n]){
                    queue.offer(n);
                    isVisited[n] = true;
                    parent[n] = u;
                }
            }

        }
        return new SearchTree(v, parent, searchOrder);
    }

    public abstract void removeVertex(V name);

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

        public List<Integer> getSearchOrder() {
            return searchOrder;
        }

        public int getParent(int v){
            return parent[v];
        }

        public int getNumberOfVerticesFound(){
            return searchOrder.size();
        }

        public List<V> getPath(int index){
            ArrayList<V> path = new ArrayList<>();
            while (index != -1){
                path.add(vertices.get(index));
                index = parent[index];
            }
            return path;
        }

        public void printPath(int index){
            List<V> path = getPath(index);
            System.out.print("A path from "+vertices.get(root)+" to "+vertices.get(index)+": ");

            for (int i=path.size()-1; i>=0; i--){
                System.out.print(path.get(i)+" ");
            }
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
