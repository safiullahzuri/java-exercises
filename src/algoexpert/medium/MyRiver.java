package algoexpert.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static algoexpert.medium.RiverSizes.riverSizes;

public class MyRiver {


    public static void main(String[] args) {
        int[][] input = {
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0},
        };

        List<Integer> output = getRiverSizes(input);

        System.out.println(output);

        List<Integer> output2 = riverSizes(input);
        System.out.println(output2);
    }


    public static List<Integer> getRiverSizes(int[][] matrix){

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        List<Integer> rivers = new ArrayList<>();

        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[i].length; j++){

                if (!visited[i][j]){
                    traverseNode(i, j, matrix, visited, rivers);
                }
            }
        }
        return rivers;
    }

    public static void traverseNode(int row, int col, int[][] matrix, boolean[][] visited, List<Integer> rivers){

        Queue<Integer[]> nodes = new LinkedList<>();
        nodes.add(new Integer[]{row, col});

        int currentRiverSize = 0;

        while (!nodes.isEmpty()){
            Integer[] node = nodes.remove();
            int currentRow = node[0];
            int currentCol = node[1];

            if (visited[currentRow][currentCol]){
                continue;
            }
            visited[currentRow][currentCol] = true;

            if (matrix[currentRow][currentCol] == 0){
                continue;
            }
            currentRiverSize++;
            List<Integer[]> neighbors = getUnvisitedNeighbors(currentRow, currentCol, matrix, visited);

            for (Integer[] neighbor: neighbors){
                nodes.add(neighbor);
            }
        }
        if (currentRiverSize > 0){
            rivers.add(currentRiverSize);
        }

    }

    public static List<Integer[]> getUnvisitedNeighbors(int row, int col, int[][] matrix, boolean[][] visited){
        List<Integer[]> neighbors = new ArrayList<>();

        //top neighbor
        if (row>0 && !visited[row-1][col]){
            neighbors.add(new Integer[]{row-1, col});
        }

        //bottom neighbor
        if (row < matrix.length-1 && !visited[row+1][col]){
            neighbors.add(new Integer[]{row+1, col});
        }

        //left neighbor
        if (col>0 && !visited[row][col-1]){
            neighbors.add(new Integer[]{row, col-1});
        }

        //right neighbor
        if (col<matrix[row].length-1 && !visited[row][col+1]){
            neighbors.add(new Integer[]{row, col+1});
        }
        return neighbors;
    }



}
