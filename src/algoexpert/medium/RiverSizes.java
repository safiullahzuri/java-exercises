package algoexpert.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class RiverSizes {

    public static void main(String[] args) {
        int[][] input = {
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0},
        };

        List<Integer> output = riverSizes(input);

        System.out.println(output);
    }

    public static List<Integer> riverSizes(int[][] matrix){
        boolean[][] isVisited = new boolean[matrix.length][matrix[0].length];
        //Arrays.fill(isVisited, false);

        List<Integer> rivers = new ArrayList<>();
        traverseNodeRecursively(0, 0, matrix, isVisited, rivers, 0);


//        for (int i=0; i<matrix.length; i++){
//            for (int j=0; j<matrix[i].length; j++){
//
//                if (isVisited[i][j]){
//                    continue;
//                }
//                traverseNodeRecursively(i, j, matrix, isVisited, rivers, 0);
//            }
//        }
        return rivers;
    }



    public static void traverseNodeRecursively(int row, int col, int[][] matrix, boolean[][] visited, List<Integer> rivers, int currentRiverSize){
        if (visited[row][col]){
            return;
        }

        visited[row][col] = true;

        if (matrix[row][col] == 0){

            if (currentRiverSize > 0){
                rivers.add(currentRiverSize);
                currentRiverSize = 0;
            }

            return;
        }

        currentRiverSize++;

        List<Integer[]> neighbors = getUnvisitedNeighbors(row, col, matrix, visited);
        for (Integer[] neighbor: neighbors){
            traverseNodeRecursively(neighbor[0], neighbor[1], matrix, visited, rivers, currentRiverSize);
        }

    }

    public static void traverseNode(int row, int col, int[][] matrix, boolean[][] visited, List<Integer> rivers){
        Stack<Integer[]> nodes = new Stack<>();

        nodes.add(new Integer[]{row, col});
        int currentRiverSize = 0;

        while (!nodes.isEmpty()){
            Integer[] node = nodes.pop();

            int newRow = node[0];
            int newCol = node[1];

            if (visited[newRow][newCol]){
                continue;
            }
            visited[newRow][newCol] = true;

            if (matrix[newRow][newCol] == 0){
                continue;
            }
            currentRiverSize++;
            List<Integer[]> unvisitedNeighbors = getUnvisitedNeighbors(newRow, newCol, matrix, visited);
            for (Integer[] neighbor: unvisitedNeighbors){
                nodes.push(neighbor);
            }
        }

        if (currentRiverSize > 0){
            rivers.add(currentRiverSize);
        }

    }

    public static List<Integer[]> getUnvisitedNeighbors(int row, int col, int[][] matrix, boolean[][] visited){
        List<Integer[]> neighbors = new ArrayList<>();

        if (row > 0 && !visited[row-1][col]){
            neighbors.add(new Integer[]{row-1, col});
        }

        if (row < matrix.length-1 && !visited[row+1][col]){
            neighbors.add(new Integer[]{row+1, col});
        }

        if (col > 0 && !visited[row][col-1]){
            neighbors.add(new Integer[]{row, col-1});
        }

        if (col < matrix[row].length-1 && !visited[row][col+1]){
            neighbors.add(new Integer[]{row, col+1});
        }
        return neighbors;
    }





//    public static List<Integer> riverSizes(int[][] matrix){
//        List<Integer> sizes = new ArrayList<>();
//        boolean[][] isVisited = new boolean[matrix.length][matrix[0].length];
//
//        for (int i=0; i<matrix.length; i++){
//            for (int j=0; j<matrix[0].length; j++){
//                if (isVisited[i][j]){
//                    continue;
//                }
//                traverseNode(i, j, matrix, isVisited, sizes);
//            }
//        }
//        return sizes;
//    }
//
//    public static void traverseNode(int i, int j, int[][] matrix, boolean[][] isVisited, List<Integer> sizes){
//        int currentRiverSize = 0;
//        Stack<Integer[]> nodesToExplore = new Stack<>();
//        nodesToExplore.push(new Integer[]{i, j});
//
//        while (!nodesToExplore.empty()){
//            Integer[] currentNode = nodesToExplore.pop();
//            i = currentNode[0];
//            j = currentNode[1];
//
//            if (isVisited[i][j]){
//                continue;
//            }
//
//            isVisited[i][j] = true;
//
//            if (matrix[i][j] == 0){
//                continue;
//            }
//
//            currentRiverSize++;
//
//            List<Integer[]> unvisitedNeighbors = getUnvisitedNeighbors(i, j, matrix, isVisited);
//
//            for (Integer[] neighbor: unvisitedNeighbors){
//                nodesToExplore.add(neighbor);
//            }
//        }
//        if (currentRiverSize > 0){
//            sizes.add(currentRiverSize);
//        }
//
//    }
//
//    public static List<Integer[]> getUnvisitedNeighbors(int i, int j, int[][] matrix, boolean[][] visited){
//        List<Integer[]> unvisitedNeighbors = new ArrayList<>();
//
//        if (i>0 && !visited[i-1][j]){
//            unvisitedNeighbors.add(new Integer[]{i-1, j});
//        }
//        if (i < matrix.length-1 && !visited[i+1][j]){
//            unvisitedNeighbors.add(new Integer[]{i+1, j});
//        }
//
//        if (j > 0 && !visited[i][j-1]){
//            unvisitedNeighbors.add(new Integer[]{i, j-1});
//        }
//
//        if (j<matrix[0].length-1 && !visited[i][j+1]){
//            unvisitedNeighbors.add(new Integer[]{i, j+1});
//        }
//        return unvisitedNeighbors;
//    }






}
