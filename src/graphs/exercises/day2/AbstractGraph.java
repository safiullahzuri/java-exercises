package graphs.exercises.day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGraph<V> implements Graph<V>{

    public List<V> vertices = new ArrayList<>();
    public List<List<Edge>> neighbors = new ArrayList<>();

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

    public AbstractGraph(List<Edge> edges, int numberOfVertices){
        for (int i=0; i<numberOfVertices; i++){
            addVertex((V)new Integer(i));
        }
        createAdjacencyList(edges, numberOfVertices);
    }

    private void createAdjacencyList(List<Edge> edges, int numberOfVertices){
        for (Edge edge: edges){
            addEdge(edge);
        }
    }

    private void createAdjacencyList(int[][] edges, int numberOfVertices){
        for (int i=0 ;i<edges.length; i++){
            addEdge(edges[i][0], edges[i][1]);
        }
    }

    @Override
    public List<V> getVertices() {
        return vertices;
    }

    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }

    public List<Integer> getPath(int u, int v){
        SpanningTree spanningTree = bfs(u);
        if (!spanningTree.searchOrder.contains(v)){
            return null;
        }
        List<Integer> path = new ArrayList<>();
        path.add(v);
        int index = v;
        while (spanningTree.parent[index] != -1){
            index = spanningTree.parent[index];
            path.add(index);
        }

        return path;
    }

    public List<Integer> getACycle(int u){

        if (!isCyclic()){
            return null;
        }

        List<Integer> visitedNodes = new ArrayList<>();
        boolean[] isVisited = new boolean[getSize()];
        LinkedList<Integer> queue = new LinkedList<>();

        queue.offer(u);

        while (!queue.isEmpty()){
            int n = queue.poll();
            visitedNodes.add(n);

            for (Edge edge: neighbors.get(n)){

                if (!isVisited[edge.v]){
                    queue.offer(edge.v);
                    isVisited[edge.v] = true;
                }
            }
        }
        System.out.println(visitedNodes);
        List<Integer> cycle = new ArrayList<>();
        int firstIndex = visitedNodes.indexOf(new Integer(u));
        visitedNodes.remove(firstIndex);
        int secondIndex = visitedNodes.indexOf(new Integer(u));

        System.out.println("first occurrence: "+firstIndex);
        System.out.println("second occurrence: "+secondIndex);

        List<Integer> newList =  visitedNodes.subList(firstIndex, secondIndex);
        newList.add(0, u);
        return newList;
    }

    public boolean isBipartite(){
        //let's see if we can divide vertices into two disjoint subsets such that no edge from the first set
        //has any connection with itself

        int[] colors = new int[getSize()];
        boolean[] isVisited = new boolean[getSize()];

        Arrays.fill(colors, -1);

        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while (!queue.isEmpty()){
            int u = queue.poll();
            if (colors[u] == -1){
                colors[u] = 1;
            }
            for (Edge edge: neighbors.get(u)){

                if (!isVisited[edge.v]){
                    queue.offer(edge.v);
                    isVisited[edge.v] = true;
                }

                if (colors[edge.v] == -1){
                    colors[edge.v] = 1 - colors[u];
                }else{
                    if (colors[edge.v] == colors[u]){
                        return false;
                    }
                }
            }
        }
        System.out.println("colors is :"+ Arrays.toString(colors));

        return true;
    }

    public List<List<Integer>> getBipartite(){
        boolean[] isVisited = new boolean[getSize()];
        int[] colors = new int[getSize()];

        Arrays.fill(colors, -1);
        LinkedList<Integer> queue = new LinkedList<>();

        queue.offer(0);
        while (!queue.isEmpty()){
            int u = queue.poll();
            if (colors[u] == -1){
                colors[u] = 1;
            }

            for (Edge edge: neighbors.get(u)){
                if (!isVisited[edge.v]){
                    queue.offer(edge.v);
                    isVisited[edge.v] = true;
                }

                if (colors[edge.v] == -1){
                    colors[edge.v] = 1 - colors[u];
                }else{
                    if (colors[edge.v] == colors[u]){
                        return null;
                    }
                }
            }
        }

        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();



        for (int i=0; i< colors.length; i++){
            if (colors[i] == 0){
                first.add(i);
            }else if (colors[i] == 1){
                second.add(i);
            }
        }
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(first);
        lists.add(second);

        return lists;
    }



    public boolean isCyclic(){
        boolean[] isVisited = new boolean[getSize()];
        LinkedList<Integer> queue = new LinkedList<>();
        int[] parent = new int[getSize()];
        Arrays.fill(parent, -1);
        queue.offer(0);

        while (!queue.isEmpty()){
            int u  = queue.poll();

            for (Edge edge: neighbors.get(u)){
                if (!isVisited[edge.v]){
                    parent[edge.v] = u;
                    queue.offer(edge.v);
                    isVisited[edge.v] = true;
                }else{
                    if (parent[edge.v] != u){
                        return true;
                    }
                }
            }
        }
        return false;
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
    public int getDegree(int index) {
        return neighbors.get(index).size();
    }

    @Override
    public void printEdges() {
        System.out.println("Edges in the graph: ");
        for (int i=0; i<neighbors.size(); i++){
            List<Edge> edges = neighbors.get(i);
            System.out.println("Edges to vertex "+vertices.get(i));
            for (Edge edge: edges){
                System.out.print(edge);
            }
            System.out.println();
        }
    }

    @Override
    public void clear() {
        neighbors.clear();
        vertices.clear();
    }



    public boolean addEdge(Edge edge){
        if (edge.u <0 || edge.u >= getSize()){
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
    public int getSize() {
        return vertices.size();
    }

    public List<Integer> shortestPath2(int u, int v){

        List<Integer> path = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();

        int[] predecessors = new int[getSize()];
        int[] distances = new int[getSize()];
        Arrays.fill(predecessors, -1);
        Arrays.fill(distances, -1);

        queue.add(u);
        distances[u] = 0;

        while (!queue.isEmpty()){
            System.out.println("queue loop");
            int x = queue.poll();

            for (Edge n: neighbors.get(x)){

                predecessors[n.v] = x;
                distances[n.v] = distances[x] + 1;

                if (distances[n.v] == -1){
                    queue.offer(n.v);
                }
            }
        }
        System.out.println("predecessors: ");
        printArray(predecessors);
        System.out.println("distances: ");
        printArray(distances);
        return path;
    }

    public void printArray(int[] array){
        for (int i=0; i<array.length; i++){
            System.out.print(array[i]+"\t");
        }
        System.out.println();
    }

    public List<Integer> shortestPath(int u, int v){

        boolean[] isVisited = new boolean[getSize()];
        LinkedList<Integer> queue = new LinkedList<>();
        List<Integer> path = new ArrayList<>();

        queue.add(u);
        isVisited[u] = true;

        while (!queue.isEmpty()){
            int x = queue.poll();
            path.add(x);

            for (Edge edge: neighbors.get(x)){
                if (!isVisited[edge.v]){
                    if (edge.v == v){
                        path.add(edge.v);
                        return path;
                    }
                    queue.offer(edge.v);
                    isVisited[edge.v] = true;
                }
            }
        }
        return null;
    }



    @Override
    public boolean addEdge(int u, int v) {
        return addEdge(new Edge(u, v));
    }

    @Override
    public SpanningTree dfs(int index) {
        int[] parent = new int[getSize()];
        Arrays.fill(parent, -1);
        List<Integer> searchOrder = new ArrayList<>();
        boolean[] isVisited = new boolean[getSize()];

        dfs(index, parent, searchOrder, isVisited);
        return new SpanningTree(index, parent, searchOrder);
    }

    private void dfs(int index, int[] parent, List<Integer> searchOrder, boolean[] isVisited){
        searchOrder.add(index);
        isVisited[index] = true;

        for (Edge edge: neighbors.get(index)){
            if (!isVisited[edge.v]){
                parent[edge.v] = index;
                dfs(edge.v, parent, searchOrder, isVisited);
            }
        }

    }

    @Override
    public SpanningTree bfs(int index) {
        int[] parent = new int[getSize()];
        Arrays.fill(parent, -1);
        boolean[] isVisited = new boolean[getSize()];
        List<Integer> searchOrder = new ArrayList<>();

        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(index);
        isVisited[index] = true;

        while (!queue.isEmpty()){
            int u = queue.poll();
            searchOrder.add(u);

            for (Edge e: neighbors.get(u)){
                if (!isVisited[e.v]){
                    queue.offer(e.v);
                    isVisited[e.v] = true;
                    parent[e.v] = u;
                }
            }
        }
        return new SpanningTree(index, parent, searchOrder);
    }

    public class SpanningTree {

        int[] parent;
        int root;
        List<Integer> searchOrder;

        public SpanningTree(int root, int[] parent, List<Integer> searchOrder){
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

        public int getParent(int index){
            return parent[index];
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
            System.out.println("A path from "+vertices.get(root)+" to "+vertices.get(index));

            for (int i=path.size()-1; i>=0; i--){
                System.out.print(path.get(i)+" ");
            }
        }

        public void printTree(){
            System.out.println("Root is: "+vertices.get(root));
            System.out.print("Edges: ");

            for (int i=0; i<parent.length; i++){
                if (parent[i] != -1){
                    System.out.print("("+vertices.get(parent[i])+", "+vertices.get(i)+") " );
                }
            }
            System.out.println();
        }




    }






}
