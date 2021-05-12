package graphs.exercises.day3;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGraph<V> implements Graph<V> {

    List<V> vertices = new ArrayList<>();
    List<List<Edge>> neighbors = new ArrayList<>();

    public AbstractGraph(){}

    public AbstractGraph(V[] vertices, int[][] edges){
        for (V v: vertices){
            addVertex(v);
        }
        createAdjacencyList(edges, vertices.length);
    }

    public AbstractGraph(List<V> vertices, List<Edge> edges){
        for (V v: vertices){
            addVertex(v);
        }
        createAdjacencyList(edges, vertices.size());
    }

    public AbstractGraph(int[][] edges, int numberOfVertices){
        for (int i=0; i<numberOfVertices; i++){
            addVertex((V)new Integer(i));
        }
        createAdjacencyList(edges, numberOfVertices);
    }

    public AbstractGraph(List<Edge> edges, int numberOfVertices){
        for (int i=0; i<numberOfVertices; i++){
            addVertex((V) new Integer(i));
        }
        createAdjacencyList(edges, numberOfVertices);
    }

    private void createAdjacencyList(List<Edge> edges, int numberOfVertices){
        for (Edge edge: edges){
            addEdge(edge);
        }
    }

    private void createAdjacencyList(int[][] edges, int numberOfVertices){
        for (int i=0; i<edges.length; i++){
            addEdge(edges[i][0], edges[i][1]);
        }
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
    public void printEdges() {

    }

    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }

    @Override
    public List<V> getVertices() {
        return vertices;
    }

    @Override
    public int getDegree(int v) {
        return neighbors.get(v).size();
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
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    @Override
    public int getSize() {
        return vertices.size();
    }

    @Override
    public boolean addEdge(int u, int v) {
        return addEdge(new Edge(u, v));
    }

    public boolean addEdge(Edge edge){
        if (edge.u < 0 || edge.u>=getSize()){
            throw new IllegalArgumentException("No such index: "+edge.u);
        }
        if (edge.v < 0 || edge.v >= getSize()){
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
        int[] parent = new int[getSize()];
        Arrays.fill(parent, -1);
        boolean[] isVisited = new boolean[getSize()];
        List<Integer> searchOrder = new ArrayList<>();

        dfs(v, parent, isVisited, searchOrder);

        return new Tree(v, parent, searchOrder);
    }

    private void dfs(int root, int[] parent, boolean[] isVisited, List<Integer> searchOrder){
        isVisited[root] = true;
        searchOrder.add(root);

        for (Edge edge: neighbors.get(root)){
            if (!isVisited[edge.v]){
                parent[edge.v] = root;
                dfs(edge.v, parent, isVisited,searchOrder);
            }
        }
    }

    @Override
    public Tree bfs(int v) {
        int[] parent = new int[getSize()];
        boolean[] isVisited = new boolean[getSize()];
        List<Integer> searchOrder = new ArrayList<>();
        Arrays.fill(parent, -1);

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(v);
        isVisited[v] = true;

        while (!queue.isEmpty()){
            int u = queue.poll();
            searchOrder.add(u);

            for (Edge edge: neighbors.get(u)){
                if (!isVisited[edge.v]){
                    parent[edge.v] = u;
                    isVisited[edge.v] = true;
                    queue.offer(edge.v);
                }
            }
        }

        return new Tree(v, parent, searchOrder);
    }

    @Override
    public List<Integer> getHamiltonianCycle(int v) {
        int[] parent = new int[getSize()];
        Arrays.fill(parent, -1);

        boolean[] isVisited = new boolean[getSize()];
        List<Integer> searchOrder = new ArrayList<>();

        hamiltonianCycle(v, parent, isVisited, searchOrder);
        return searchOrder;
    }

    private void hamiltonianCycle(int v, int[] parent, boolean[] isVisited, List<Integer> searchOrder){
        isVisited[v] = true;
        searchOrder.add(v);

        for (int n: getNeighbors(v)){
            if (!isVisited[n]){
                System.out.printf("parent of %d set to %d\n", n, v);
                parent[n] = v;
                hamiltonianCycle(n, parent, isVisited, searchOrder);
            }
        }
        if (!allVisited(isVisited) && !isCycle(searchOrder)){
            isVisited[v] = false;
            parent[v] = -1;
            searchOrder.remove(new Integer(v));
            System.out.printf("search order removing %d \n.", v);
        }
    }

    private boolean isCycle(List<Integer> searchOrders) {

        int startVertex = (Integer) searchOrders.get(0);
        int lastVertex = (Integer) searchOrders.get(searchOrders.size() - 1);
        //System.out.println("startVertex: " + startVertex + "lastVertex: " + lastVertex);
        if ((Math.abs((startVertex / 8) - (lastVertex / 8)) == 2 && Math.abs((startVertex % 8) - (lastVertex % 8)) == 1)
                || (Math.abs((startVertex / 8) - (lastVertex / 8)) == 1 && Math.abs((startVertex % 8) - (lastVertex % 8)) == 2)) {
            return true;
        }

        return false;
    }


    private boolean allVisited(boolean[] isVisited) {
        for (int i = 0; i < isVisited.length; i++) {
            if (!isVisited[i]) {
                return false;
            }
        }
        return true;
    }


    public static<V> Graph<V> maxInducedSubgraph(Graph<V> g, int k){
        List<Integer> verticesToRemove = new ArrayList<>();
        for (int i=0; i<g.getSize(); i++){
            if (g.getDegree(i) < k){
                verticesToRemove.add(i);
            }
        }
        //delete those vertices
        for (int v: verticesToRemove){
            //remove vertices
        }
        return g;
    }


    public boolean removeVertex(V v){
        if (vertices.contains(v)){
            int index = getIndex(v);
            vertices.remove(v);
            neighbors.remove(index);

            for (List<Edge> list: neighbors){
                for (int i=0; i<list.size();){
                    if (list.get(i).v == index){
                        list.remove(i);
                    }else{
                        i++;
                    }
                }
            }

            for (List<Edge> list: neighbors){
                for (int i=0; i<list.size(); i++){
                    if (list.get(i).u >= index){
                        list.get(i).u = list.get(i).u - 1;
                    }
                    if (list.get(i).v >= index) {
                        list.get(i).v = list.get(i).v - 1;
                    }
                }
            }
            return true;
        }else {
            return false;
        }
    }

    public class Tree {
        int root;
        int[] parent;
        List<Integer> searchOrder;

        public Tree(int root, int[] parent, List<Integer> searchOrder){
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

        public List<Integer> getSearchOrder() {
            return searchOrder;
        }

        public int getNumberOfVerticesFound(){
            return searchOrder.size();
        }

        public void printTree(){
            System.out.println("Root is: "+vertices.get(root));
            System.out.println("Edges: ");
            for (int i=0; i<parent.length; i++){
                if (parent[i] != -1){
                    System.out.print("("+vertices.get(parent[i])+", "+vertices.get(i)+")");
                }
            }
        }

        public List<V> getPath(int index){
            ArrayList<V> path = new ArrayList<>();
            do {
                path.add(vertices.get(index));
                index = parent[index];
            }while (index != -1);
            return path;
        }

    }
}
