package graphs.learning2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class WeightedGraph2<V> extends AbstractGraph<V> {

    public WeightedGraph2(){}

    public WeightedGraph2(V[] vertices, int[][] edges){
        createWeightedGraph(Arrays.asList(vertices), edges);
    }

    public WeightedGraph2(int[][] edges, int numOfVertices){
        List<V> vertices = new ArrayList<>();
        for (int i=0; i<numOfVertices; i++){
            vertices.add((V)new Integer(i));
        }
        createWeightedGraph(vertices, edges);
    }


    public WeightedGraph2(List<V> vertices, List<WeightedEdge> edges){
        createWeightedGraph(vertices, edges);
    }

    public WeightedGraph2(List<WeightedEdge> edges, int numOfVertices){
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
        for (int i=0 ;i< edges.length; i++){
            neighbors.get(edges[i][0]).add(new WeightedEdge(edges[i][0], edges[i][1], edges[i][2]));
        }
    }

    private void createWeightedGraph(List<V> vertices, List<WeightedEdge> edges){
        this.vertices = vertices;
        for (int i=0 ;i<vertices.size(); i++){
            neighbors.add(new ArrayList<>());
        }
        for (WeightedEdge edge: edges){
            neighbors.get(edge.u).add(edge);
        }
    }

    public double getWeight(int u, int v) throws Exception{
        for (Edge edge: neighbors.get(u)){
            if (edge.v == v){
                return ((WeightedEdge) edge).weight;
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


    public boolean addEdge(int u, int v, double weight){
        return addEdge(new WeightedEdge(u,v, weight));
    }

    public MST getMinimumSpanningTree(){
        return getMinimumSpanningTree(0);
    }
/*
    public MST getMinimumSpanningTreeKruskal(int startVertex){
        //while it does not form a cycle
        //add the lowest cost edges
        //until the tree size is less than

        List<WeightedEdge> T = new ArrayList<>();
        List<WeightedEdge> edges = new ArrayList<>();

        for (int i=0; i<getSize(); i++){
            //edges.addAll((Collection<? extends WeightedEdge>) neighbors.get(i));
        }
        double totalWeight = 0;

        while (T.size() < getSize()){
            WeightedEdge currentSmallest = null;
            double currentSmallestWeight = Double.POSITIVE_INFINITY;
            int smallestIndex = -1;
            for (int i=0; i<edges.size(); i++){
                if (edges.get(i).weight < currentSmallestWeight){
                    currentSmallestWeight = edges.get(i).weight;
                    currentSmallest = edges.get(i);
                    smallestIndex = i;
                }
            }
            if (currentSmallest == null) return null;
            edges.remove(smallestIndex);

            if (!T.contains(currentSmallest)){
                //it should not form a cycle
                //if we already have both u and v, it will form a cycle
                boolean containsU = false;
                boolean containsV = false;
                for (WeightedEdge edge: T){
                    if (edge.u == currentSmallest.u){
                        containsU = true;
                    }
                    if (edge.v == currentSmallest.v){
                        containsU = true;
                    }
                }
                if (!(containsU && containsV)){
                    T.add(currentSmallest);
                    totalWeight += currentSmallest.weight;
                }
            }
        }
        int[] parent = new int[getSize()];
        List<Integer> searchOrder = new ArrayList<>();
        for (WeightedEdge edge: T){
            if (!searchOrder.contains(edge.u)){
                searchOrder.add(edge.u);
            }
            if (!searchOrder.contains(edge.v)){
                searchOrder.add(edge.v);
            }
        }
        return new MST(startVertex, parent, searchOrder, totalWeight);
    }
*/
    public MST getMinimumSpanningTree(int startVertex){
        double[] cost = new double[getSize()];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        int[] parent = new int[getSize()];
        parent[startVertex] = -1;
        cost[startVertex] = 0;
        double totalWeight = 0;

        List<Integer> T = new ArrayList<>();

        while (T.size() < getSize()){
            int u = -1;
            double currentMinCost = Double.POSITIVE_INFINITY;

            for (int i=0 ;i<getSize(); i++){
                if (!T.contains(i) && cost[i] < currentMinCost){
                    currentMinCost = cost[i];
                    u= i;
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
        return new MST(startVertex, parent, T, totalWeight);
    }



    public class MST extends Tree{

        private double totalWeight;

        public MST(int root, int[] parent, List<Integer> searchOrder, double totalWeight) {
            super(root, parent, searchOrder);
            this.totalWeight= totalWeight;
        }

        public double getTotalWeight() {
            return totalWeight;
        }
    }

    public ShortestPathTree getShortestPath(int sourceVertex){
        int[] parent = new int[getSize()];
        Arrays.fill(parent, -1);
        double[] cost = new double[getSize()];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);

        cost[sourceVertex] = -1;
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
                if (!T.contains(edge.v) && cost[edge.v] > cost[u]+((WeightedEdge)edge).weight){
                    cost[edge.v] = cost[u] + ((WeightedEdge)edge).weight;
                    parent[edge.v] = u;
                }
            }
        }
        return new ShortestPathTree(sourceVertex, parent, T, cost);
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



    }












}
