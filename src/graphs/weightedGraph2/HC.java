package graphs.weightedGraph2;

public class HC {
    private int numOfVertices;
    private int[] hamiltonianPath;
    private int[][] adjacencyMatrix;

    public HC(int[][] adjacencyMatrix){
        this.adjacencyMatrix = adjacencyMatrix;
        this.hamiltonianPath = new int[adjacencyMatrix.length];
        this.numOfVertices = adjacencyMatrix.length;

        this.hamiltonianPath[0] = 0;
        if (findFeasibleSolution(1)){
            printSolution();
        }else{
            System.out.println("No feasible solution...");
        }
    }

    private boolean findFeasibleSolution(int position){
        if (position == numOfVertices){
            if (adjacencyMatrix[position-1][hamiltonianPath[0]] == 1){
                return true;
            }
        }
        for (int vertexIndex=1; vertexIndex<numOfVertices; vertexIndex++){
            if (isFeasible(vertexIndex, position)){
                hamiltonianPath[position] = vertexIndex;
                if (findFeasibleSolution(position+1)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isFeasible(int vertexIndex, int position){
        if (adjacencyMatrix[position-1][vertexIndex] == 0){
            return false;
        }
        for (int i=0; i<position; i++){
            if (hamiltonianPath[i] == vertexIndex)
                return false;
        }
        return true;
    }

    private void printSolution(){

    }
}
