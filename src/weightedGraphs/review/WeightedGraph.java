package weightedGraphs.review;

import AVLTREE_EXERCISES.EXERCISE1.AVLTree;
import AVLTREE_EXERCISES.EXERCISE1.BST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeightedGraph<V> extends UnweightedGraph<V> {

    public WeightedGraph(){}

    public WeightedGraph(V[] vertices, int[][] edges){
        createWeightedGraph(Arrays.asList(vertices), edges);
    }

    public WeightedGraph(int[][] edges, int numVertices){
        List<V> vertices = new ArrayList<>();
        for (int i=0; i<numVertices; i++){
            vertices.add((V)new Integer(i));
        }
        createWeightedGraph(vertices, edges);
    }

    @Override
    public void removeVertex(V name) {
        vertices.remove(getIndex(name));
        //clear all the neighbors
        neighbors.remove(getIndex(name));

        for (int i=0; i<neighbors.size(); i++){
            for (int j=0; j<neighbors.get(i).size(); j++){
                Edge edge = neighbors.get(i).get(j);
                if (edge.v == getIndex(name)){
                    neighbors.get(i).remove(j);
                }
            }
        }
    }

    public WeightedGraph(List<V> vertices, List<WeightedEdge> edges){
        createWeightedGraph(vertices, edges);
    }

    public WeightedGraph(List<WeightedEdge> edges, int numVertices){
        List<V> vertices = new ArrayList<>();
        for (int i=0; i<numVertices; i++){
            vertices.add((V)new Integer(i));
        }
        createWeightedGraph(vertices, edges);
    }

    private void createWeightedGraph(List<V> vertices, List<WeightedEdge> edges){
        this.vertices = vertices;
        for (int i=0; i<edges.size(); i++){
            neighbors.add(new ArrayList<>());
        }
        for(WeightedEdge edge: edges){
            neighbors.get(edge.u).add(edge);
        }
    }

    private void createWeightedGraph(List<V> vertices, int[][] edges){
        this.vertices = vertices;
        for (int i=0; i<edges.length; i++){
            neighbors.add(new ArrayList<>());
        }
        for (int i=0; i<edges.length; i++){
            neighbors.get(edges[i][0]).add(
              new WeightedEdge(edges[i][0], edges[i][1], edges[i][2])
            );
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

    public ShortestPathTree getShortestPath2(int source){
        List<Integer> T = new ArrayList<>();
        double[] cost = new double[getSize()];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        int[] parent = new int[getSize()];
        Arrays.fill(parent, -1);
        cost[source] = 0;
        T.add(source);

        while (T.size() < getSize()){
            int v = -1;
            double currentMinCost = Double.POSITIVE_INFINITY;

            for (int i=0; i<getSize(); i++){
                if (T.contains(i)){
                    try {
                        for (int n: getNeighbors(i)){
                            if (!T.contains(n)){
                                if (cost[i]+getWeight(i,n) < currentMinCost){
                                    currentMinCost = cost[i]+getWeight(i, n);
                                    cost[n] = cost[i] + getWeight(i, n);
                                    v = n;
                                    parent[n] = i;
                                }
                            }
                        }
                    }catch (Exception exception){}
                }
            }
            if(v == -1)break;
            T.add(v);

        }

        return new ShortestPathTree(source, parent, T, cost);

    }



    public ShortestPathTree getShortestPath(int source){

        double[] cost = new double[getSize()];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        cost[source] = 0;
        int[] parent = new int[getSize()];
        parent[source] = -1;

        List<Integer> T = new ArrayList<>();
        AVLTree<VertexCost> avlTree = new AVLTree<>();

        for (int i=0; i<getSize(); i++){
            avlTree.insert(new VertexCost(i, cost[i]));
        }

        while (T.size() < getSize()){
            int u = -1;
            double currentMinCost = Double.POSITIVE_INFINITY;

            VertexCost vertexCost =  avlTree.getLeftMost();
            if (!T.contains(vertexCost.vertex)){
                currentMinCost = vertexCost.cost;
                u = vertexCost.vertex;

                avlTree.delete(vertexCost);
            }

//            for (int i=0; i<getSize(); i++){
//                if (!T.contains(i) && cost[i] < currentMinCost){
//                    currentMinCost = cost[i];
//                    u = i;
//                }
//            }
            if (u == -1)break;
            T.add(u);

            for (Edge edge: neighbors.get(u)){
                if (!T.contains(edge.v) && cost[edge.v]>cost[u]+((WeightedEdge)edge).weight){
                    cost[edge.v] = cost[u]+ ((WeightedEdge)edge).weight;
                    //avlTree.search(new VertexCost(edge.v, cost[edge.v]));
                    avlTree.delete(new VertexCost(edge.v, cost[edge.v]));
                    avlTree.insert(new VertexCost(edge.v, cost[u]+((WeightedEdge)edge).weight));
                    parent[edge.v] = u;
                }
            }
        }
        return new ShortestPathTree(source, parent, T, cost);
    }

    public ShortestPathTree getShortestPath(int source, int destination){
        double[] cost = new double[getSize()];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        cost[source] = 0;
        int[] parent = new int[getSize()];
        parent[source] = -1;

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
            if (u == -1)break;
            T.add(u);


            for (Edge edge: neighbors.get(u)){
                if (!T.contains(edge.v) && cost[edge.v]>cost[u]+((WeightedEdge)edge).weight){
                    cost[edge.v] = cost[u]+ ((WeightedEdge)edge).weight;
                    parent[edge.v] = u;
                }
            }
        }

        ArrayList<Integer> path = new ArrayList<>();


        ShortestPathTree shortestPathTree = new  ShortestPathTree(source, parent, path, cost);
        for (V v: shortestPathTree.getPath(destination)){
            path.add(getIndex(v));
        }
        return new ShortestPathTree(source, parent, path, cost);
    }

    public MST getMinimumSpanningTree(){
        return getMinimumSpanningTree(0);
    }

    //Prim's algorithm
    public MST getMinimumSpanningTree(int startingVertex){
        double[] cost = new double[getSize()];
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        cost[startingVertex] = 0;
        int[] parent = new int[getSize()];
        parent[startingVertex] = -1;

        double totalWeight = 0;

        List<Integer> T = new ArrayList<>();
        //T.add(startingVertex);

        while (T.size() < getSize()){
            double currentMinCost = Double.POSITIVE_INFINITY;
            int u=-1;
            for (int i=0; i<getSize(); i++){
                if (!T.contains(i) && cost[i] < currentMinCost){
                    currentMinCost= cost[i];
                    u = i;
                }
            }
            if (u == -1) break;
            T.add(u);
            totalWeight += cost[u];

            //update T's neighbors

            for (Edge edge: neighbors.get(u)){
                if (!T.contains(edge.v) && cost[edge.v]>((WeightedEdge)edge).weight){
                    cost[edge.v] = ((WeightedEdge)edge).weight;
                    parent[edge.v] = u;
                }
            }
        }
        return new MST(startingVertex, parent, T, totalWeight);
    }


    public class MST extends SearchTree{

        private double totalWeight;

        public MST(int root, int[] parent, List<Integer> searchOrder, double totalWeight) {
            super(root, parent, searchOrder);
            this.totalWeight= totalWeight;
        }

        public double getTotalWeight() {
            return totalWeight;
        }
    }

    public class ShortestPathTree extends SearchTree{

        private double[] cost;

        public ShortestPathTree(int root, int[] parent, List<Integer> searchOrder, double[] cost) {
            super(root, parent, searchOrder);
            this.cost= cost;
        }

        public double getCost(int v){
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

    public boolean addEdge(int u, int v, double weight){
        return addEdge(new WeightedEdge(u,v, weight));
    }

    @Override
    public List<Integer> getHamiltonianPath(V vertex) {
        return getHamiltonianPath(getIndex(vertex));
    }

    @Override
    public List<Integer> getHamiltonianPath(int v) {
        boolean[] isVisited = new boolean[getSize()];
        int[] next = new int[getSize()];
        Arrays.fill(next, -1);

        List<Integer> result = null;

        if (getHamiltonianPath(v, next, isVisited)){
            result = new ArrayList<>();
            int vertex = v;
            while (vertex != -1){
                result.add(vertex);
                vertex = next[vertex];
            }
        }
        return result;
    }

    private boolean getHamiltonianPath(int v, int[] next, boolean[] isVisited){
        isVisited[v] = true;
        if (allVisited(isVisited)){
            return true;
        }

        for (int n: getNeighbors(v)){
            if (!isVisited[n]){
                if (getHamiltonianPath(n, next, isVisited)){
                    next[v] = n;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean allVisited(boolean[] isVisited){
        for (boolean b : isVisited) {
            if (!b) return false;
        }
        return true;
    }

    ArrayList<Integer> shortestHamiltonianCycle;
    double totalWeightOfPath = Double.MAX_VALUE;

    @Override
    public List<Integer> getHamiltonianCycle() {
        return getHamiltonianCycle(0);
    }

    @Override
    public List<Integer> getHamiltonianCycle(int v) {
        int[] next = new int[getSize()];
        Arrays.fill(next, -1);

        boolean[] isVisited = new boolean[getSize()];

        getHamiltonianCycle(v, next, isVisited, v);
        return shortestHamiltonianCycle;
    }

    public boolean getHamiltonianCycle(int v, int[] next, boolean[] isVisited, int startV){
        isVisited[v] = true;
        if (allVisited(isVisited) && isCycle(v, startV)){
            List<Integer> result = new ArrayList<>();
            int vertex = startV;
            while (vertex != v){
                result.add(vertex);
                vertex = next[vertex];
            }
            result.add(v);

            //calculate if this is more costly than the previous one
            double currentWeight = 0;
            try {
                for (int i=0; i<result.size()-1; i++){
                    currentWeight += getWeight(result.get(i), result.get(i+1));
                }
                currentWeight += getWeight(0, result.get(result.size()-1));

            }catch (Exception e){
                e.printStackTrace();
            }

            if (totalWeightOfPath > currentWeight){
                totalWeightOfPath = currentWeight;
                shortestHamiltonianCycle = (ArrayList<Integer>) result;
            }
        }
        for (int n: getNeighbors(v)){
            if (!isVisited[n]){
                next[v] = n;
                getHamiltonianCycle(n, next, isVisited, startV);
            }
        }
        isVisited[v] = false;
        return false;
    }

    private boolean isCycle(int v, int startV){
        return getNeighbors(v).contains(startV);
    }

}
