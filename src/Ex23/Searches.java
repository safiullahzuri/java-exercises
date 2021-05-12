package Ex23;

import java.util.Arrays;
import java.util.stream.Stream;

public class Searches {

    public static void main(String[] args){
        int[] array = {2,3,6,4,3,28,54,32,12};
        Arrays.sort(array);
        Arrays.stream(array).forEach(e-> System.out.print(e+" "));
        System.out.println();
        Searches searches = new Searches();
        System.out.println(searches.binarySearch(12, array));
    }

    private int linearSearch(int key, int[] array){
        for (int i=0; i<array.length; i++){
            if (array[i] == key){
                return i;
            }
        }
        return -1;
    }

    private int binarySearch(int key, int[] array){
        int low=0;
        int high = array.length-1;
        int mid = (low+high)/2;

        while (low<=high){
            if (key == array[mid]){
                return mid;
            }else if (key < array[mid]){
                high = mid-1;
            }else if (key > array[mid]){
                low = mid + 1;
            }
            mid = (low+high)/2;
        }
        return -1;
    }
}
