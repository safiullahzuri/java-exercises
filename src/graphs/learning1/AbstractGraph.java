package graphs.learning1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGraph<V> implements Graph<V> {

    protected List<V> vertices = new ArrayList<>();
    protected List<List<Edge>> neighbors = new ArrayList<>();

    protected AbstractGraph(){}

    protected AbstractGraph(V[] vertices, int[][] edges){
        for (int i=0; i<vertices.length; i++){
            addVertex(vertices[i]);
        }
        createAdjacencyLists(edges, vertices.length);
    }

    public AbstractGraph(List<Edge> edges, int numberOfVertices){
        for (int i=0; i<numberOfVertices; i++){
            addVertex((V)(new Integer(i)));
        }
        createAdjacencyLists(edges, numberOfVertices);
    }

    public AbstractGraph(List<V> vertices, List<Edge> edges){
        for (int i=0; i<vertices.size(); i++){
            addVertex(vertices.get(i));
        }
        createAdjacencyLists(edges, vertices.size());
    }




    private void createAdjacencyLists(int[][] edges, int numberOfVertices){
        for (int i=0; i<edges.length; i++){
            addEdge(edges[i][0], edges[i][1]);
        }

    }

    private void createAdjacencyLists(List<Edge> edges, int numberOfVertices){
        for (Edge edge: edges){
            addEdge(edge);
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
        List<Edge> edges = neighbors.get(index);

        List<Integer> neighbors = new ArrayList<>();

        for (Edge edge: edges){
            neighbors.add(edge.v);
        }
        return neighbors;
    }

    @Override
    public int getDegree(int v) {
        return neighbors.get(v).size();
    }

    @Override
    public void printEdges() {
        for (int u=0; u<neighbors.size(); u++){
            System.out.println(getVertex(u)+" ("+u+"): ");
            for (Edge e: neighbors.get(u)){
                System.out.println("("+getVertex(e.u)+", "+getVertex(e.v)+") ");
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
        if (edge.u < 0 || edge.u > getSize()-1){
            throw new IllegalArgumentException("No such index: "+edge.u);
        }
        if (edge.v < 0 || edge.v > getSize()-1){
            throw new IllegalArgumentException("No such index: "+edge.v);
        }
        if (!neighbors.get(edge.u).contains(edge)){
            neighbors.get(edge.u).add(edge);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Tree dfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];

        for (int i=0; i<parent.length; i++){
            parent[i] = -1;
        }

        boolean[] isVisited = new boolean[vertices.size()];

        dfs(v, parent, searchOrder, isVisited);
        return new Tree(v, parent, searchOrder);
    }

    private void dfs(int v, int[] parent, List<Integer> searchOrder, boolean[] isVisted){
        searchOrder.add(v);
        isVisted[v] = true;

        for (Edge e: neighbors.get(v)){
            if (!isVisted[e.v]){
                parent[e.v] = v;
                dfs(e.v, parent, searchOrder, isVisted);
            }
        }
    }



    @Override
    public Tree bfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i=0; i<parent.length; i++){
            parent[i] = -1;
        }

        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[vertices.size()];

        queue.offer(v);
        isVisited[v] = true;

        while (!queue.isEmpty()){
            int u = queue.poll();
            searchOrder.add(u);

            for (Edge edge: neighbors.get(u)){
                if (!isVisited[edge.v]){
                    queue.offer(edge.v);
                    parent[edge.v] = u;
                    isVisited[edge.v] = true;
                }
            }
        }
        return new Tree(v, parent, searchOrder);
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

    public List<List<Integer>> getConnectedComponents(){
        List<Integer> vertices = new ArrayList<>();
        for (V v: getVertices()){
            vertices.add(getIndex(v));
        }

        Tree spanningTree =  bfs(0);
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

    public class Tree{

        private int root;
        private int[] parent;
        private List<Integer> searchOrder;

        public Tree(int root, int[] parent, List<Integer> searchOrder){
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        public int getRoot(){
            return root;
        }

        public int getParent(int v) {
            return parent[v];
        }

        public List<Integer> getSearchOrder(){
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
            System.out.println("Edges: ");

            for (int i=0; i<parent.length; i++){
                if (parent[i] != -1){
                    System.out.println("("+vertices.get(parent[i])+", "+vertices.get(i)+") ");
                }
            }
            System.out.println();
        }



    }





























}
