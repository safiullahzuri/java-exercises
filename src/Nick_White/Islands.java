package Nick_White;


public class Islands{

    public static void main(String[] args) {
        char[][] grid = {{'1','1','0','0','0'},
                         {'0','0','1','0','1'},
                         {'1','1','1','0','1'},
                         {'0','0','0','0','1'},
                         {'1','1','0','0','1'}
        };
        System.out.println(findIslands(grid));
        System.out.println("salam zibi");
    }

    public static int findIslands(char[][] grid){
        int count = 0;
        for (int i=0; i<grid.length; i++){
            for (int j=0; j<grid[i].length; j++){
                if(grid[i][j] == '1'){
                    updateAdjacents(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void updateAdjacents(char[][] grid, int i, int j ){
        if (i<0 || j<0 || i>= grid.length || j>= grid[i].length || grid[i][j] == '0' ) {
            return;
        }
        grid[i][j] = '0';
        updateAdjacents(grid, i-1, j); //up
        updateAdjacents(grid,i+1, j);
        updateAdjacents(grid, i, j-1); //left
        updateAdjacents(grid, i, j+1); //right
    }

}