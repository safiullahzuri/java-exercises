package graphs.exercises.day3;


public class TestGraph {

    public static void main(String[] args) {

        String[] vertices = {"Seattle", "San Francisco", "Los Angeles", "Denver", "New York", "Dallas"};

        int[][] edges = {
                {0, 1},
                {1, 2},
                {2, 1}, {2, 3}, {2, 5},
                {3, 4},
                {5, 2}, {5, 4}
        };

        Graph<String> graph = new UnweightedGraph<String>(vertices, edges);
        AbstractGraph<String>.Tree tree =   graph.dfs(3);
        System.out.println(graph.getHamiltonianCycle(0));
        //tree.printTree();

    }
}