package graphs.learning2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public abstract class AbstractGraph<V> implements Graph<V> {

    protected List<V> vertices = new ArrayList<>();
    protected List<List<Edge>> neighbors = new ArrayList<>();

    public AbstractGraph(){}

    public AbstractGraph(V[] vertices, int[][] edges){
        for (int i=0; i<vertices.length; i++){
            addVertex(vertices[i]);
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
            addVertex((V)(new Integer(i)));
        }
        createAdjacencyList(edges, numberOfVertices);
    }

    public AbstractGraph(List<Edge> edges, int numberOfVertices){
        for (int i=0; i<numberOfVertices; i++){
            addVertex((V) new Integer(i));
        }
        createAdjacencyList(edges, numberOfVertices);
    }




    @Override
    public boolean addVertex(V v) {
        if (!vertices.contains(v)){
            vertices.add(v);
            neighbors.add(new ArrayList<>());
            return true;
        }else{
            return false;
        }
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

    public boolean addEdge(int u, int v){
        return addEdge(new Edge(u, v));
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
    public int getDegree(int index) {
        return neighbors.get(index).size();
    }

    @Override
    public void printEdges() {
        for (int u=0; u<neighbors.size(); u++){
            System.out.print(getVertex(u)+"("+ u+"): ");
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
    public Tree dfs(int v) {
        int[] parent = new int[vertices.size()];
        Arrays.fill(parent, -1);
        List<Integer> searchOrder = new ArrayList<>();
        boolean[] isVisited = new boolean[vertices.size()];

        dfs(v, parent, searchOrder, isVisited);
        return new Tree(v, parent, searchOrder);
    }

    private void dfs(int v, int[] parent, List<Integer> searchOrder, boolean[] isVisited){
        searchOrder.add(v);
        isVisited[v] = true;

        for (Edge edge: neighbors.get(v)){
            if (!isVisited[edge.v]){
                parent[edge.v] = v;
                dfs(edge.v, parent, searchOrder, isVisited);
            }
        }
    }

    @Override
    public Tree bfs(int v) {
        int[] parent = new int[vertices.size()];
        Arrays.fill(parent, -1);

        List<Integer> searchOrder = new ArrayList<>();
        boolean[] isVisited = new boolean[vertices.size()];

        LinkedList<Integer> queue = new LinkedList<>();

        queue.offer(v);
        isVisited[v] = true;

        while (!queue.isEmpty()){
            int u = queue.poll();
            searchOrder.add(u);

            for (Edge edge: neighbors.get(u)){
                if (!isVisited[edge.v]){
                    queue.offer(edge.v);
                    isVisited[edge.v] = true;
                    parent[edge.v] = u;

                }
            }
        }

        return new Tree(v, parent, searchOrder);
    }

    public boolean addEdge(Edge edge){
        if (edge.u < 0 || edge.u >= getSize()){
            throw new IllegalArgumentException("edge.u illegal: "+edge.u);
        }
        if (edge.v < 0 || edge.v >= getSize()){
            throw new IllegalArgumentException("edge.v illegal: "+edge.v);
        }

        if (!neighbors.get(edge.u).contains(edge.v)){
            neighbors.get(edge.u).add(edge);
            return true;
        }else{
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

        public int getParent(int v){
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
            System.out.print("A path from "+vertices.get(root)+" to "+vertices.get(index)+": ");
            for (int i=path.size()-1; i>=0; i--){
                System.out.print(path.get(i)+" ");
            }
        }

        public void printTree(){
            System.out.println("Root is "+vertices.get(root));
            System.out.print("Edges: ");

            for (int i=0; i<parent.length; i++){
                if (parent[i] != -1){
                    System.out.print("("+vertices.get(parent[i])+", "+vertices.get(i)+") ");
                }
            }
            System.out.println();
        }


    }


    @Override
    public Tree bfs2(int v) {
        int[] parent = new int[vertices.size()];
        boolean[] isVisited = new boolean[vertices.size()];
        List<Integer> searchOrder = new ArrayList<>();

        Arrays.fill(parent, -1);

        LinkedList<Integer> queue = new LinkedList<>();
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

    @Override
    public Tree dfs2(int v) {
        int[] parent = new int[vertices.size()];
        boolean[] isVisited = new boolean[vertices.size()];
        List<Integer> searchOrder = new ArrayList<>();

        Arrays.fill(parent, -1);

        dfs2(v, parent, searchOrder, isVisited);
        return new Tree(v, parent, searchOrder);
    }

    public void dfs2(int v, int[] parent, List<Integer> searchOrder, boolean[] isVisited){
        searchOrder.add(v);
        isVisited[v] = true;

        for (Edge edge: neighbors.get(v)){
            if (!isVisited[edge.v]){
                parent[edge.v] = v;
                dfs2(v, parent, searchOrder, isVisited);
            }
        }

    }

    public List<Integer> getHamiltonianPath2(int v){
        int[] next = new int[getSize()];
        Arrays.fill(next, -1);

        boolean[] isVisited = new boolean[getSize()];
        List<Integer> result = null;

        if (getHamiltonianPath2(v, next, isVisited)){
            result = new ArrayList<>();
            int vertex = v;
            while (vertex != -1){
                result.add(vertex);
                vertex = next[v];
            }
        }
        return result;
    }

    public List<Integer> getHamiltonianPath(int v){
        int[] next = new int[getSize()];
        Arrays.fill(next, -1);

        boolean[] isVisited = new boolean[getSize()];
        List<Integer> result = null;

        if (getHamiltonianPath(v, next, isVisited)){
            result = new ArrayList<>();
            int vertex = v;
            while (vertex != -1){
                result.add(vertex);
                vertex = next[v];
            }
        }
        return result;
    }
    private boolean getHamiltonianPath2(int v, int[] next, boolean[] isVisited){
        isVisited[v] = true;
        if (allVisited(isVisited)){
            return true;
        }
        for (int n: getNeighbors(v)){
            if (!isVisited[n]){
                next[v] = n;
                if (getHamiltonianPath2(n, next, isVisited)){
                    return true;
                }else{
                    isVisited[v] = false;
                }
            }
        }
        return false;
    }

    private boolean getHamiltonianPath(int v, int[] next, boolean[] isVisited){
        isVisited[v] = true;
        if (allVisited(isVisited))
            return true;

        for (int i=0; i<getNeighbors(v).size(); i++){
            int u = getNeighbors(v).get(i);
            if (!isVisited[u] && getHamiltonianPath(u, next, isVisited)){
                next[v] = u;
                return true;
            }
        }
        isVisited[v] = false;
        return false;
    }

    public List<Integer> getHamiltonianCycle(int v){
        int[] next = new int[getSize()];
        Arrays.fill(next, -1);
        boolean[] isVisited = new boolean[getSize()];
        List<Integer> result = null;

        totalWeightOfPath = Double.MAX_VALUE;
        double currentWeight = 0;

        try {
            for (int i=0; i<result.size()-1; i++){
                currentWeight += getWeight(result.get(i), result.get(i+1));
            }
            currentWeight += getWeight(result.get(0), result.get(result.size()-1));
        }catch (Exception exception){
            exception.printStackTrace();
        }

        System.out.println("currentWeight: "+currentWeight);
        if (totalWeightOfPath > currentWeight){
            totalWeightOfPath = currentWeight;
            shortestHamiltonianCycle = (ArrayList<Integer>) result;
        }
        getHamiltonianCycle(v, next, isVisited, v);
        return shortestHamiltonianCycle;

    }

    ArrayList<Integer> shortestHamiltonianCycle;
    double totalWeightOfPath = Double.MAX_VALUE;
    int[] nextCopy;


    public boolean getHamiltonianCycle(int v, int[] next, boolean[] isVisited, int startV){
        isVisited[v] = true;
        if (allVisited(isVisited) && isCycle(v, startV)){
            ArrayList<Integer> result = new ArrayList<>();
            int vertex = startV;
            while (vertex != v){
                result.add(vertex);
                vertex = next[vertex];
            }
            result.add(v);
        }
        for (int i=0; i<getNeighbors(v).size(); i++){
            int u = getNeighbors(v).get(i);
            if (!isVisited[u]){
                next[v] = u;
                getHamiltonianCycle(u, next, isVisited, startV);
            }
        }
        isVisited[v] = false;
        return false;
    }


    private boolean isCycle(int v, int startV){
        return getNeighbors(v).contains(startV);
    }


    public double getWeight(int u, int v) throws Exception {
        for (Edge edge: neighbors.get(u)){
            if (edge.v == v)
                return ((WeightedEdge)edge).weight;
        }
        return -1;
    }



    public boolean allVisited(boolean[] isVisited){
        for (int i=0; i<isVisited.length; i++){
            if (!isVisited[i]) return false;
        }
        return true;
    }


}
