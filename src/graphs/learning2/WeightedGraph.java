package graphs.learning2;

import java.util.*;

public class WeightedGraph<V> extends AbstractGraph<V> {

    public WeightedGraph(){}

    public WeightedGraph(V[] vertices, int[][] edges){
        createWeightedGraph(Arrays.asList(vertices), edges);
    }

    public WeightedGraph(int[][] edges, int numOfVertices){
        List<V> vertices = new ArrayList<>();
        for (int i=0; i<numOfVertices; i++){
            vertices.add((V)new Integer(i));
        }
        createWeightedGraph(vertices, edges);
    }

    public WeightedGraph(List<V> vertices, List<WeightedEdge> edges){
        createWeightedGraph(vertices, edges);
    }

    public WeightedGraph(List<WeightedEdge> edges, int numOfVertices){
        List<V> vertices = new ArrayList<>();
        for (int i=0; i<numOfVertices; i++){
            vertices.add((V)new Integer(i));
        }
        createWeightedGraph(vertices, edges);
    }

    private void createWeightedGraph(List<V> vertices, int[][] edges){
        this.vertices = vertices;
        for (int i=0; i<vertices.size(); i++){
            neighbors.add(new ArrayList<>());
        }

        for (int i=0; i< edges.length; i++){
            neighbors.get(edges[i][0]).add(new WeightedEdge(edges[i][0], edges[i][1], edges[i][2]));
        }
    }

    private void createWeightedGraph(List<V> vertices, List<WeightedEdge> edges){
        this.vertices = vertices;
        for (int i=0; i<vertices.size(); i++){
            neighbors.add(new ArrayList<>());
        }

        for (WeightedEdge edge: edges){
            neighbors.get(edge.u).add(edge);
        }
    }

    public double getWeight(int u, int v) throws Exception{
        for (Edge edge: neighbors.get(u)){
            if (edge.v == v){
                return ((WeightedEdge)edge).weight;
            }
        }
        throw new Exception("Edge does not exist.");
    }

    public void printWeightedEdges(){
        for (int i=0; i<getSize(); i++){
            System.out.print(getVertex(i)+" ("+i+"): ");
            for (Edge edge: neighbors.get(i)){
                System.out.print("("+edge.u+", "+edge.v+", "+((WeightedEdge)edge).weight+") ");
            }
            System.out.println();
        }
    }


    public boolean addEdge(int u, int v, double weight) {
        return addEdge(new WeightedEdge(u,v, weight));
    }

    public MST getMinimumSpanningTree(){
        return getMinimumSpanningTree(0);
    }


    public MST getMinimumSpanningTree2(int startingVertex){
        double[] cost = new double[getSize()];
        for (int i=0; i<cost.length; i++){
            cost[i] = Double.POSITIVE_INFINITY;
        }
        cost[startingVertex] = 0;

        int[] parent = new int[getSize()];
        parent[startingVertex] = -1;
        double totalWeight = 0;

        List<Integer> T = new ArrayList<>();

        while (T.size() < getSize()){
            int u = -1;
            double currentMinCost = Double.POSITIVE_INFINITY;
            for (int i=0; i<getSize(); i++){
                if (!T.contains(i) && cost[i] < currentMinCost){
                    currentMinCost = cost[i];
                    u = i;
                }
            }
            T.add(u);
            totalWeight += cost[u];

            for (Edge edge: neighbors.get(u)){
                if (!T.contains(edge.v) && cost[edge.v] > ((WeightedEdge)edge).weight){
                    cost[edge.v] = ((WeightedEdge) edge).weight;
                    parent[edge.v] = u;
                }
            }

        }

        return new MST(startingVertex, parent, T, totalWeight);
    }

    private void reorderNeigborsBasedOnDegree(List<Integer> list) {
        for (int i = list.size() - 1; i >= 1; i--) {
            // Find the maximum in the list[0..i]
            int currentMaxDegree = getDegree(list.get(0));
            int currentMaxIndex = 0;

            for (int j = 1; j <= i; j++) {
                if (currentMaxDegree < getDegree(list.get(j))) {
                    currentMaxDegree = getDegree(list.get(j));
                    currentMaxIndex = j;
                }
            }

            // Swap list[i] with list[currentMaxIndex] if necessary;
            if (currentMaxIndex != i) {
                int temp = list.get(currentMaxIndex);
                list.set(currentMaxIndex, list.get(i));
                list.set(i, temp);
            }
        }
    }

    /** Return true if all elements in array isVisited are true */


    private Double[][] getAdjacencyMatrix(){
        Double[][] m = new Double[getSize()][getSize()];
        for (int i=0; i<getSize(); i++){
            for (Edge edge: neighbors.get(i)){
                WeightedEdge e1 = (WeightedEdge)edge;
                m[edge.u][edge.v] = e1.weight;
            }
        }
        return m;
    }

    public MST getMSTEx2(int startVertex){

        Double[][] m = this.getAdjacencyMatrix();

        double[] cost = new double[getSize()];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        cost[startVertex] = 0;
        int[] parent = new int[getSize()];
        parent[startVertex] = -1;
        List<Integer> T = new ArrayList<>();
        double totalWeight = 0;
        while (T.size() < getSize()){
            int u = -1;
            double currentMinCost = Double.POSITIVE_INFINITY;
            for (int i=0; i<getSize(); i++){
                if (!T.contains(i) && cost[i] < currentMinCost){
                    currentMinCost = cost[i];
                    u = i;
                }
            }
            T.add(u);
            totalWeight += cost[u];

            for (int k=0; k<getSize(); k++){
                if (m[u][k] != null && !T.contains(k) && cost[k]>m[u][k]){
                    cost[k] = m[u][k];
                    parent[k] = u;
                }
            }
        }
        return new MST(startVertex, parent, T, totalWeight);
    }


    public List<Integer> getHamiltonianCycle(int v) {
        // A path starts from v. (i, next[i]) represents an edge in
        // the path. isVisited[i] tracks whether i is currently in the
        // path.
        int[] next = new int[getSize()];
        for (int i = 0; i < next.length; i++)
            next[i] = -1; // Indicate no subpath from i is found yet

        boolean[] isVisited = new boolean[getSize()];

        // The vertices in the H-path are stored in result
        List<Integer> result = null;

        // To speed up search, reorder the adjacency list for each
        // vertex so that the vertices in the list are in increasing
        // order of their degrees
        for (int i = 0; i < getSize(); i++)
            reorderNeigborsBasedOnDegree(getNeighbors(i));


        getHamiltonianCycle(v, next, isVisited, v);
        totalWeightOfPath = Double.MAX_VALUE;
        double currentWeight = 0;
        try {
            for (int i = 0; i < result.size() - 1; i++) {
                currentWeight += getWeight(result.get(i),
                        result.get(i + 1));
            }
            currentWeight += getWeight(result.get(0),
                    result.get(result.size() - 1));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("currentWeight: " + currentWeight);

        if (totalWeightOfPath > currentWeight) {
            totalWeightOfPath = currentWeight;
            shortestHamiltonianCycle = (ArrayList<Integer>)result;
        }
        return shortestHamiltonianCycle;
    }

    ArrayList<Integer> shortestHamiltonianCycle;
    double totalWeightOfPath = Double.MAX_VALUE;
    int[] nextCopy;

    public boolean getHamiltonianCycle(int v, int[] next,
                                       boolean[] isVisited, int startV) {
        isVisited[v] = true; // Vertex v visited

        if (allVisited(isVisited) && isCycle(v, startV)) {
            ArrayList<Integer> result = new ArrayList<>(); // Create a list for path
            int vertex = startV;
            while (vertex != v) {
                result.add(vertex); // Insert vertex to result
                vertex = next[vertex];
            }
            result.add(v);
        }

        for (int i = 0; i < getNeighbors(v).size(); i++) {
            int u = getNeighbors(v).get(i);
            if (!isVisited[u]) {
                next[v] = u;
                getHamiltonianCycle(u, next, isVisited, startV);
            }
        }

        isVisited[v] = false;
        return false;
    }

    private boolean isCycle(int v, int startV) {
        return getNeighbors(v).contains(startV);
    }


    public MST getMinimumSpanningTree(int startingVertex){
        double[] cost = new double[getSize()];
        for (int i=0; i<cost.length; i++){
            cost[i] = Double.POSITIVE_INFINITY;
        }
        cost[startingVertex] = 0;
        int[] parent = new int[getSize()];
        parent[startingVertex] = -1;
        double totalWeight = 0;

        List<Integer> T = new ArrayList<>();

        while (T.size() < getSize()){
            int u = -1;
            double currentMinCost = Double.POSITIVE_INFINITY;
            for (int i=0; i<getSize(); i++){
                if (!T.contains(i) && cost[i] < currentMinCost){
                    currentMinCost = cost[i];
                    u = i;
                }
            }
            T.add(u);
            totalWeight += cost[u];

            for (Edge edge: neighbors.get(u)){
                if (!T.contains(edge.v) && cost[edge.v]>((WeightedEdge)edge).weight){
                    cost[edge.v] = ((WeightedEdge)edge).weight;
                    parent[edge.v] = u;
                }
            }
        }
        return new MST(startingVertex, parent, T, totalWeight);
    }

    public ShortestPathTree getShortestPath(int sourceVertex){
        double[] cost = new double[getSize()];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);

        cost[sourceVertex] = 0;
        int[] parent = new int[getSize()];
        parent[sourceVertex] = -1;
        List<Integer> T = new ArrayList<>();
        while (T.size() < getSize()){
            int u = -1;
            double currentMinCost = Double.POSITIVE_INFINITY;
            for (int i=0; i<getSize(); i++){
                if (!T.contains(i) && cost[i] < currentMinCost){
                    currentMinCost = cost[i];
                    u = i;
                }
            }
            T.add(u);
            for (Edge edge: neighbors.get(u)){
                if (!T.contains(edge.v) && cost[edge.v]>cost[u]+((WeightedEdge)edge).weight){
                    cost[edge.v] = cost[u] + ((WeightedEdge)edge).weight;
                    parent[edge.v] = u;
                }
            }
        }
        return new ShortestPathTree(sourceVertex, parent, T, cost);
    }


    public MST getMinimumSpanningTreeKruskal(int startVertex){
        //while it does not form a cycle
        //add the lowest cost edges
        //until the tree size is less than

        List<Integer> T = new ArrayList<>();
        List<WeightedEdge> edges = new ArrayList<>();

        List<WeightedEdge> treeEdges = new ArrayList<>();
        for (int i=0; i<getSize(); i++){
            for (Edge edge: neighbors.get(i)){
                edges.add((WeightedEdge)edge);
            }
        }

        edges.sort(new Comparator<WeightedEdge>() {
            @Override
            public int compare(WeightedEdge o1, WeightedEdge o2) {
                double diff = o1.weight-o2.weight;
                if (diff > 0){
                    return 1;
                }else if (diff == 0){
                    return 0;
                }
                return -1;
            }
        });

        double totalWeight = 0;

        while (T.size() < getSize()){
            WeightedEdge removedEdge =  edges.remove(0);
            if (!formsCycle(removedEdge, T, treeEdges)){
                if (!T.contains(removedEdge.u)){
                    T.add(removedEdge.u);
                }
                if (!T.contains(removedEdge.v)){
                    T.add(removedEdge.v);
                }
                totalWeight += removedEdge.weight;
                treeEdges.add(removedEdge);
            }
        }
        int[] parent = new int[getSize()];
        //List<Integer> searchOrder = new ArrayList<>();

        MST mst =  new MST(startVertex, parent, T, totalWeight);
        mst.weightedEdges = treeEdges;
        return mst;
    }

    private boolean formsCycle(WeightedEdge edge, List<Integer> vertices, List<WeightedEdge> edgesAlready){
         if (vertices.contains(edge.u) && vertices.contains(edge.v)){
             //if there is an edge to which both of these guys are connected
             return true;
         }
         return false;
    }

    public ShortestPathTree getShortestPath2(int sourceVertex){

        double[] cost = new double[getSize()];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        cost[sourceVertex] = 0;
        int[] parent = new int[getSize()];
        parent[sourceVertex] = -1;

        List<Integer> T = new ArrayList<>();

        while (T.size() < getSize()){
            int u = -1;
            double currentMinCost = Double.POSITIVE_INFINITY;
            for (int i=0; i<getSize(); i++){
                if (!T.contains(i) && cost[i] < currentMinCost){
                    currentMinCost = cost[i];
                    u = i;
                }
            }
            T.add(u);

            for (Edge e: neighbors.get(u)){
                if (!T.contains(e.v) && cost[e.v]>cost[u]+((WeightedEdge)e).weight){
                    cost[e.v] = cost[u] + ((WeightedEdge)e).weight;
                    parent[e.v] = u;
                }
            }
        }
        return new ShortestPathTree(sourceVertex, parent, T, cost);
    }

    public class ShortestPathTree extends Tree{
        private  double[] cost;


        public ShortestPathTree(int root, int[] parent, List<Integer> searchOrder, double[] cost) {
            super(root, parent, searchOrder);
            this.cost = cost;
        }

        public double getCost(int v) {
            return cost[v];
        }

        public void printAllPaths(){
            System.out.println("All shortest paths from "+vertices.get(getRoot())+" are: ");
            for (int i=0; i<cost.length; i++){
                printPath(i);
                System.out.println("(cost: "+cost[i]+")");
            }
        }
    }


    public class MST extends Tree{

        private double totalWeight;
        public List<WeightedEdge> weightedEdges = new ArrayList<>();

        public MST(int root, int[] parent, List<Integer> searchOrder, double totalWeight) {
            super(root, parent, searchOrder);
            this.totalWeight = totalWeight;
        }

        public double getTotalWeight() {
            return totalWeight;
        }
    }


}
