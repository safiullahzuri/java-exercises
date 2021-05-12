package Ex22;

public class WhileTest {

    public static void main(String[] args) {
        int[] bs = {1,2,4,5,8,53,231,2321,52322,2132321,23213232};

        int value = 8;

        int low = 0;
        int high = bs.length;
        int mid = (low+high)/2;

        while (low<high){
            if (bs[mid] == value){
                System.out.println("Found at position "+mid);
                return;
            }else if (value < bs[mid]){
                high = mid;
            }else if (value > bs[mid]){
                low = mid;
            }
        }

        System.out.println("could not find anything");

    }
}
