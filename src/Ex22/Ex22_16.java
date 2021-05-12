package Ex22;

public class Ex22_16 {

    public static void main(String[] args) {

    }

    private static int linearSearch(int[] numbers, int value){
        for (int i=0; i<numbers.length; i++){
            if (numbers[i] == value){
                return i;
            }
        }
        return -1;
    }
}
