package graphs.weightedGraph2;

import AVLTREE_EXERCISES.EXERCISE3.AVLTree;
import graphs.learning2.Edge;
import graphs.learning2.UnweightedGraph;

import java.util.*;
import java.util.stream.Collectors;

public class WeightedGraph<V> extends UnweightedGraph<V> {

    public WeightedGraph(){}

    public WeightedGraph(V[] vertices, int[][] edges){
        createWeightedGraph(Arrays.asList(vertices), edges);
    }

    public WeightedGraph(int[][] edges, int numberOfVertices){
        List<V> vertices = new ArrayList<>();
        for (int i=0; i<numberOfVertices; i++){
            vertices.add((V)new Integer(i));
        }
        createWeightedGraph(vertices, edges);
    }

    public WeightedGraph(List<V> vertices, List<WeightedEdge> edges){
        createWeightedGraph(vertices, edges);
    }

    public WeightedGraph(List<WeightedEdge> edges, int numberOfVertices ){
        List<V> vertices = new ArrayList<>();
        for (int i=0; i<numberOfVertices; i++){
            vertices.add((V) new Integer(i));
        }
        createWeightedGraph(vertices, edges);
    }


    private void createWeightedGraph(List<V> vertices, int[][] edges){
        this.vertices = vertices;

        for (int i=0; i<edges.length; i++){
            neighbors.add(new ArrayList<>());
        }

        for (int i=0; i<edges.length; i++){
            neighbors.get(edges[i][0]).add(new WeightedEdge(edges[i][0], edges[i][1], edges[i][2]));
        }
    }

    private void createWeightedGraph(List<V> vertices, List<WeightedEdge> edges){
        this.vertices = vertices;

        for (int i=0; i<edges.size(); i++){
            neighbors.add(new ArrayList<>());
        }

        for (WeightedEdge weightedEdge: edges){
            neighbors.get(weightedEdge.u).add(weightedEdge);
        }
    }

    public double getWeight(int u, int v) throws Exception{
        for (Edge edge: neighbors.get(u)){
            if (edge.v == v){
                return ((WeightedEdge) edge).weight;
            }
        }
        throw new Exception("Edge does not exit");
    }

    public void printWeightedEdges(){
        for (int i=0; i<getSize(); i++){
            System.out.println(getVertex(i)+" ("+i+"): ");
            for (Edge edge: neighbors.get(i)){
                System.out.print((WeightedEdge)edge);
            }
            System.out.println();
        }
    }

    public boolean addEdge(int u, int v, double weight) {
        return addEdge(new WeightedEdge(u, v, weight));
    }

    public MST getMinimumSpanningTree(){
        return getMinimumSpanningTree(0);
    }

    public MST primWithAdjacency(int sourceVertex){

        boolean[] isInT = new boolean[getSize()];

        double[] cost = new double[getSize()];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);

        int[] parent = new int[getSize()];
        parent[sourceVertex] = -1;
        cost[sourceVertex] = 0;
        Double[][] m = getAdjacencyMatrix();
        double totalCost = 0;
        List<Integer> T = new ArrayList<>();
        while (T.size() < getSize()){
            int u = -1;
            double currentMinCost = Double.POSITIVE_INFINITY;
            for (int i=0; i<getSize(); i++){
                if (!isInT[i] && cost[i] < currentMinCost){
                    currentMinCost = cost[i];
                    u = i;
                }
            }
            T.add(u);
            isInT[u] = true;
            totalCost += cost[u];

            for (int k=0; k<getSize(); k++){
                if (m[u][k] != null && !T.contains(k) && cost[k] > m[u][k]){
                    cost[k] = m[u][k];
                    parent[k] = u;
                }
            }
        }
        return new MST(sourceVertex, parent, T, totalCost);
    }

    public ShortestPathTree alternativeDijkstra(int startVertex){
        double[] cost = new double[getSize()];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);

        int[] parent = new int[getSize()];
        parent[startVertex] = -1;

        List<Integer> T = new ArrayList<>();
        cost[startVertex] = 0;

        T.add(startVertex);

        return null;
    }

    public ShortestPathTree dijkstraWithAdjacencyMatrix(int startVertex){
        double[] cost = new double[getSize()];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        int[] parent = new int[getSize()];
        parent[startVertex] = -1;

        Double[][] m = new Double[getSize()][getSize()];

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
            if (u == -1)
                break;
            else
                T.add(u);

            totalWeight += cost[u];

            for (int k=0; k<getSize(); k++){
                if (m[u][k] != null && !T.contains(k)){
                    if (cost[k] > m[u][k] + cost[u]){
                        cost[k] = m[u][k] + cost[u];
                    }
                }
            }
        }
        return new ShortestPathTree(startVertex, parent, T, cost);
    }

    private Double[][] getAdjacencyMatrix(){
        Double[][] m = new Double[getSize()][getSize()];
        for (int i=0; i<getSize(); i++){
            for (Edge e: neighbors.get(i)){
                WeightedEdge we = (WeightedEdge) e;
                m[we.u][we.v] = we.weight;
            }
        }
        return m;
    }

    public MST getMinimumSpanningTreeUsingKruskalAlgorithm(int startVertex){
        //get all the edges first
        List<WeightedEdge> allEdges = new ArrayList<>();

        int[] parent = new int[getSize()];

        for (int i=0; i<getSize(); i++){
            for (Edge edge: neighbors.get(i)){
                if (!allEdges.contains(edge)){
                    allEdges.add((WeightedEdge) edge);
                }
            }
        }

        //2. sort the edges
        allEdges.sort(new Comparator<WeightedEdge>() {
            @Override
            public int compare(WeightedEdge o1, WeightedEdge o2) {
                if (o1.weight > o2.weight) return 1;
                else if (o1.weight == o2.weight) return 0;
                else return -1;
            }
        });

        //search orders
        List<V> T = new ArrayList<>();

        List<Set<Integer>> unionSets = new ArrayList<>();

        vertices.forEach(t -> {
            HashSet<Integer> hashSet = new HashSet<>();
            hashSet.add(getIndex(t));
            unionSets.add(hashSet);
        });

        List<WeightedEdge> takenEdges = new ArrayList<>();
        int totalWeight = 0;
        while (takenEdges.size() < getSize()-1){
            //remove an edge
            WeightedEdge removedEdge = allEdges.remove(0);
            //see if it does not form a cycle
            if(!formsCycle(removedEdge, unionSets)){
                takenEdges.add(removedEdge);
                //merge the sets
                unionSets.get(removedEdge.u).add(removedEdge.v);
                unionSets.get(removedEdge.v).add(removedEdge.u);

                T.add(getVertex(removedEdge.u));
                T.add(getVertex(removedEdge.v));

                totalWeight += removedEdge.weight;
            }
        }

        return new MST(startVertex, parent, T.stream().map(v -> getIndex(v)).collect(Collectors.toList()), totalWeight);
    }

    private boolean formsCycle(WeightedEdge removedEdge, List<Set<Integer>> unionSets){
        int firstVertex = removedEdge.u;
        int secondVertex = removedEdge.v;

        if (unionSets.get(firstVertex).contains(secondVertex)){
            if (unionSets.get(secondVertex).contains(firstVertex)){
                return true;
            }
        }
        return false;
    }

    public MST getMinimumSpanningTree(int startVertex){
        double[] cost = new double[getSize()];
        int[] parent = new int[getSize()];

        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        parent[startVertex] = -1;

        List<Integer> T = new ArrayList<>();
        double totalWeight = 0;

        while (T.size() < getSize()){
            int u =-1;
            double currentMinCost = Double.POSITIVE_INFINITY;
            for (int i=0; i<getSize(); i++){
                if (!T.contains(i) && currentMinCost < cost[i]){
                    cost[i] = currentMinCost;
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

        return new MST(startVertex, parent, T, totalWeight);
    }

    public ShortestPathTree dijkstra(int source){
        double[] cost = new double[getSize()];
        Arrays.fill(cost, -1);

        int[] parent = new int[getSize()];
        parent[source] = -1;

        cost[source] = 0;
        List<Integer> T = new ArrayList<>();

        while (T.size() < getSize()){
            int u =-1;
            double currentMinCost = Double.POSITIVE_INFINITY;

            for (int i=0; i<getSize(); i++){
                if (!T.contains(i) && cost[i] > currentMinCost){
                    currentMinCost = cost[i];
                    u = i;
                }
            }

            if (u == -1) break;
            T.add(u);

            for (Edge edge: neighbors.get(u)){
                if (!T.contains(edge.v) && cost[edge.v] > cost[u]+((WeightedEdge)edge).weight){
                    cost[edge.v] = cost[u] + ((WeightedEdge)edge).weight;
                    parent[edge.v] = u;
                }
            }
        }
        return new ShortestPathTree(source, parent, T, cost);
    }

    public ShortestPathTree getShortestPath(int source){
        double[] cost = new double[getSize()];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);

        List<Integer> T = new ArrayList<>();
        int[] parent = new int[getSize()];
        parent[source] = -1;
        cost[source] = 0;

        AVLTree<Integer> integerAVLTree = new AVLTree<>();


        while (T.size() < getSize()){

            int u = -1;
            double currentMinCost = Double.POSITIVE_INFINITY;



            for (int i=0; i<getSize(); i++){
                if (!T.contains(i) && cost[i] < currentMinCost){
                    currentMinCost = cost[i];
                    u = i;
                }
            }

            if (u == -1) break;
            else
                T.add(u);

            for (Edge edge: neighbors.get(u)){
                if (!T.contains(edge.v) && cost[edge.v] > cost[u]+((WeightedEdge)edge).weight){
                    cost[edge.v] = cost[u] + ((WeightedEdge) edge).weight;
                    parent[edge.v] = u;
                }
            }
        }
        return new ShortestPathTree(source, parent, T, cost);
    }

    public class ShortestPathTree extends Tree{
        private double[] cost;

        public ShortestPathTree(int root, int[] parent, List<Integer> searchOrder, double[] cost) {
            super(root, parent, searchOrder);
            this.cost = cost;
        }

        public double getCost(int v){
            return cost[v];
        }

        public void printAllEdges(){
            System.out.println("All shortest paths from "+vertices.get(getRoot())+" are: ");
            for (int i=0; i<cost.length; i++){
                printPath(i);
                System.out.println("(cost: "+cost[i]+")");
            }
        }

    }

    ArrayList<Integer> shortestHamiltonianCycle;
    double totalWeightOfPath = Double.MAX_VALUE;

    public List<Integer> getHamiltonianCycle(int v){
        int[] next = new int[getSize()];
        Arrays.fill(next, -1);

        boolean[] isVisited = new boolean[getSize()];
        List<Integer> result = null;

        getHamiltonianCycle(v, next, isVisited, v);
        return shortestHamiltonianCycle;
    }

    public boolean getHamiltonianCycle(int v, int[] next, boolean[] isVisited, int startV){
        isVisited[v] = true;
        if (allVisited(isVisited) && isCyclic(v, startV)){
            ArrayList<Integer> result = new ArrayList<>();
            int vertex = startV;
            while (vertex != v){
                result.add(vertex);
                vertex = next[vertex];
            }
            result.add(v);

            double currentWeight = 0;
            try {
                for (int i=0; i<result.size()-1; i++){
                    currentWeight += getWeight(result.get(i), result.get(i+1));
                }
                currentWeight += getWeight(result.get(0), result.get(result.size()-1));
            }catch (Exception ex){}
            System.out.println("this iteration produced cycle "+result+" with weight "+currentWeight );

            if (totalWeightOfPath > currentWeight){
                totalWeightOfPath = currentWeight;
                shortestHamiltonianCycle = (ArrayList<Integer>) result.clone();
            }
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





    private boolean isCyclic(int v, int startV){
        return getNeighbors(v).contains(startV);
    }




    public class MST extends Tree{
        private double totalWeight;

        public MST(int root, int[] parent, List<Integer> searchOrder, double totalWeight) {
            super(root, parent, searchOrder);
            this.totalWeight = totalWeight;
        }

        public double getTotalWeight() {
            return totalWeight;
        }
    }






}
