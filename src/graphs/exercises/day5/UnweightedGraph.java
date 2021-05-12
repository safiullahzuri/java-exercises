package graphs.exercises.day5;



import java.util.*;

public class UnweightedGraph implements Graph{

    protected Object[] vertices;
    protected LinkedList<Integer>[] neighbors;

    protected UnweightedGraph(int[][] edges, Object[] vertices){
        this.vertices = vertices;
        createAdjacencyList(edges, vertices.length);
    }

    protected UnweightedGraph(List<Edge> edges, List<Object> vertices){
        this.vertices = vertices.toArray();
        createAdjacencyList(edges, vertices.size());
    }

    protected UnweightedGraph(List<Edge> edges, int numberOfVertices){
        vertices = new Integer[numberOfVertices];
        for (int i=0; i<numberOfVertices; i++){
            vertices[i] = new Integer(i);
        }
        createAdjacencyList(edges, numberOfVertices);
    }

    protected UnweightedGraph(int[][] edges, int numberOfVertices){
        vertices = new Integer[numberOfVertices];
        for (int i=0; i<numberOfVertices; i++){
            vertices[i] = new Integer(i);
        }
        createAdjacencyList(edges, numberOfVertices);
    }

    private void createAdjacencyList(int[][] edges, int numberOfVertices){
        neighbors = new LinkedList[numberOfVertices];
        for (int i=0; i<numberOfVertices; i++){
            neighbors[i] = new LinkedList<>();
        }
        for (int i=0; i<edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            neighbors[u].add(v);
        }
    }

    private void createAdjacencyList(List<Edge> edges, int numberOfVertices){
        neighbors = new LinkedList[numberOfVertices];
        for (int i=0; i<numberOfVertices; i++){
            neighbors[i] = new LinkedList<>();
        }

        for (Edge edge: edges){
            neighbors[edge.u].add(edge.v);
        }
    }


    @Override
    public int getSize() {
        return vertices.length;
    }

    @Override
    public Object[] getVertices() {
        return vertices;
    }

    @Override
    public Object getVertex(int v) {
        return vertices[v];
    }

    @Override
    public int getIndex(Object v) {
        for (int i=0; i<getSize(); i++){
            if (v.equals(vertices[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public List getNeighbors(int v) {
        return neighbors[v];
    }

    @Override
    public int getDegree(int v) {
        return neighbors[v].size();
    }

    @Override
    public int[][] getAdjacencyMatrix() {
        int[][] adjacencyMatrix = new int[getSize()][getSize()];
        for (int i=0; i<neighbors.length; i++){
            for (int j=0; j<neighbors[i].size(); j++){
                int v = neighbors[i].get(j);
                adjacencyMatrix[i][v] = 1;
            }
        }
        return adjacencyMatrix;
    }

    @Override
    public void printAdjacencyMatrix() {
        int[][] adjacencyMatrix = getAdjacencyMatrix();
        for (int i=0; i<adjacencyMatrix.length; i++){
            for (int j=0; j<adjacencyMatrix[0].length; j++){
                System.out.print(adjacencyMatrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    @Override
    public void printEdges() {
        for (int u=0; u<neighbors.length; u++){
            System.out.print("Vertex "+u+": ");
            for (int j=0; j<neighbors[u].size(); j++){
                System.out.print("("+u+", "+neighbors[u].get(j)+") ");
            }
            System.out.println();
        }
    }

    public class Edge{
        public int u;
        public int v;

        public Edge(int u, int v){
            this.u = u;
            this.v = v;
        }
    }






    @Override
    public UnweightedGraph.Tree bfs(int v) {
        List<Integer> searchOrders = new ArrayList<>();
        int[] parent = new int[vertices.length];
        Arrays.fill(parent, -1);

        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[vertices.length];
        queue.offer(v);
        isVisited[v] = true;

        while (!queue.isEmpty()){
            int u = queue.poll();
            searchOrders.add(u);
            for (int w: neighbors[u]){
                if (!isVisited[w]){
                    queue.offer(w);
                    parent[w] = u;
                    isVisited[w] = true;
                }
            }
        }
        return new Tree(v, parent, searchOrders);
    }

    @Override
    public UnweightedGraph.Tree dfs(int v) {
        List<Integer> searchOrders = new ArrayList<>();
        int[] parent = new int[vertices.length];
        Arrays.fill(parent, -1);

        boolean[] isVisited = new boolean[vertices.length];
        dfs(v, parent, searchOrders, isVisited);
        return new Tree(v, parent, searchOrders);
    }

    private void dfs(int v, int[] parent, List<Integer> searchOrders, boolean[] isVisited){
        searchOrders.add(v);
        isVisited[v] = true;

        for (int i: neighbors[v]){
            if (!isVisited[i]){
                parent[i] = v;
                dfs(i, parent, searchOrders, isVisited);
            }
        }
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

        public Tree(int root, int[] parent){
            this.root = root;
            this.parent = parent;
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

        public Iterator<Integer> pathIterator(int v){
            return new PathIterator(v);
        }

        public void printPath(int v){
            Iterator<Integer> iterator = pathIterator(v);
            System.out.print("A path from "+vertices[root]+" to "+vertices[v]+": ");
            while (iterator.hasNext())
                System.out.print(iterator.next()+" ");
        }

        public void printTree(){
            System.out.println("Root is: "+vertices[root]);
            System.out.print("Edges: ");
            for (int i=0; i<parent.length; i++){
                if (parent[i] != -1){
                    System.out.print("("+vertices[parent[i]] +", "+vertices[i]+")" );
                }
            }
            System.out.println();
        }


        //iterator within inner class
        public class PathIterator implements Iterator<Integer>{
            private Stack<Integer> stack;
            public PathIterator(int v){
                stack = new Stack<>();
                do {
                    stack.add(v);
                    v = parent[v];
                }while (v != -1);
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public Integer next() {
                return (Integer) vertices[stack.pop()];
            }
        }
    }

    @Override
    public List<Integer> getHamiltonianPath(int v) {
        List<Integer> searchOrders = new ArrayList<>();
        int[] parent = new int[vertices.length];
        Arrays.fill(parent, -1);

        boolean[] isVisited = new boolean[vertices.length];
        hamiltonianPath(v, parent, searchOrders, isVisited);
        return searchOrders;
    }

    private void hamiltonianPath(int v, int[] parent, List<Integer> searchOrders, boolean[] isVisited){
        searchOrders.add(v);

        for (int w: neighbors[v]){
            if (!isVisited[w]){
                isVisited[w] = true;
                parent[w] = v;
                hamiltonianPath(v, parent, searchOrders, isVisited);
            }
        }

        if (!allVisited(isVisited)){
            isVisited[v] = false;
            parent[v] = -1;
            searchOrders.remove(new Integer(v));
        }

    }

    private boolean allVisited(boolean[] isVisited){
        for (int i=0; i<isVisited.length; i++){
            if (!isVisited[i]){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Integer> getHamiltonianCycle(int v) {
        List<Integer> searchOrders = new ArrayList<>();
        int[] parent = new int[vertices.length];
        Arrays.fill(parent, -1);

        boolean[] isVisited = new boolean[vertices.length];
        hamiltonianCycle(v, parent, searchOrders, isVisited);
        return searchOrders;
    }

    private void hamiltonianCycle(int v, int[] parent, List<Integer> searchOrders, boolean[] isVisited){
        searchOrders.add(v);

        for (int i: neighbors[v]){
            if (!isVisited[i]){
                parent[i] = v;
                isVisited[i] = true;
                hamiltonianCycle(i, parent, searchOrders, isVisited);
            }
        }
        if (!allVisited(isVisited) || !checkCycle(searchOrders)){
            isVisited[v] = false;
            parent[v] = -1;
            searchOrders.remove(new Integer(v));
        }
    }

    private boolean checkCycle(List<Integer> searchOrder){
        int startVertex = (Integer) searchOrder.get(0);
        int lastVertex = (Integer) searchOrder.get(searchOrder.size()-1);
        int abs1 = Math.abs((startVertex/8) - (lastVertex/8));
        int abs2 = Math.abs((startVertex%8) - (lastVertex%8));

        if ((abs1 == 2 && abs2==1) || (abs1==1 && abs2==2)){
            return true;
        }
        return false;
    }

    @Override
    public List<Integer> getHamiltonianCycle() {
        return getHamiltonianCycle(0);
    }


    public class Model {
        Graph graph = null;
        public Model(){
            List<Object> vertices = new ArrayList<>();
            List<Edge> edges = new ArrayList<>();

            for (int i=0; i<8; i++){
                for (int j=0; j<8; j++){
                    vertices.add(new String(i*8+j+""));

                    int nextX = 0;
                    int nextY = 0;

                    for (int g=-2; g<=2; j+=4){
                        for (int h=01; h<=1; h+=2){
                            nextX = i + g;
                            nextY = j + h;


                            if (nextX >= 0 && nextX<8 && nextY >=0 && nextY<8){
                                edges.add(new Edge(i*8+j, nextX*8+nextY));
                            }
                            nextX = i+h;
                            nextY = j+g;
                            if (nextX >= 0 && nextX<8 && nextY >=0 && nextY<8){
                                edges.add(new Edge(i*8+j, nextX*8+nextY));
                            }
                        }
                    }


                }
            }
            graph = new UnweightedGraph(edges, vertices);

        }
    }


}
