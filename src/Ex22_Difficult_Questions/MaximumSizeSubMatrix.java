package Ex22_Difficult_Questions;

public class MaximumSizeSubMatrix {


    public static void main(String[] args) {
        int[][] m= new int[][]{
                              {0,0,1,1},
                              {1,1,0,1},
                              {0,1,1,0},
                              {0,1,1,1}
        };
        printArray(findLargestBlock(m));
    }

    public static int[][] findLargestBlock(int[][] m){
        int[][] aux = new int[m.length][m[0].length];

        for (int i=0; i<aux.length; i++){
            aux[i][0] = m[i][0];
        }

        for (int j=0; j<aux[0].length; j++){
            aux[0][j] = m[0][j];
        }

        for (int i=1; i<m.length; i++){
            for ( int j=1; j<m[i].length; j++){
                if (m[i][j] == 1){
                    aux[i][j] = Math.min(aux[i][j-1], Math.min(aux[i-1][j], aux[i-1][j-1]))+1;
                }else {
                    aux[i][j] = 0;
                }
            }
        }

        int largest = -1;
        int x=0, y=0;

        for ( int i=0; i<aux.length; i++){
            for (int j=0; j<aux[i].length; j++){
                if (aux[i][j] > largest){
                    largest = aux[i][j];
                    x = i;
                    y = j;
                }
            }
        }
        x -= (largest-1);
        y -= (largest-1);

        System.out.println("largest value is "+largest+" starting at "+x+","+y);

        return aux;
    }

    public static void printArray(int[][] arr){
        for (int i=0; i<arr.length; i++){
            for (int j=0; j<arr[0].length; j++){
                System.out.print(arr[i][j]+"\t");
            }
            System.out.println();
        }
    }

}
