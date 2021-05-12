package graphs.exercises.day1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class UnweightedGraphWithNonrecursiveDFS<V> extends UnweightedGraph<V>{

    public UnweightedGraphWithNonrecursiveDFS(V[] vertices, int[][] edges){
        super(vertices, edges);
    }

    @Override
    public SearchTree dfs(int v) {

        System.out.println("starting overridden dfs method using stack");
        Stack<Integer> stack = new Stack<>();

        int[] parent = new int[getSize()];
        Arrays.fill(parent, -1);
        boolean[] isVisited = new boolean[getSize()];
        List<Integer> searchOrder = new ArrayList<>();

        searchOrder.add(v);

        stack.push(v);
        isVisited[v] = true;

        while (!stack.isEmpty()){

            int u = stack.pop();
            //searchOrder.add(u);

            for (int n: getNeighbors(u)){
                if (!isVisited[n]){
                    parent[n] = u;
                    stack.push(n);
                    searchOrder.add(n);
                    isVisited[n] = true;
                }
            }
        }
        return new SearchTree(v, parent, searchOrder);

    }
}
