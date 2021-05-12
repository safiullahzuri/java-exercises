package Ex23;

import java.util.Comparator;

public class Ex23_05 {

    public static void main(String[] args) {
        Integer[] array = {1,2,42,65,321,23,7,5};
        HeapWithComparator<Integer> heapWithComparator = new HeapWithComparator<>(array, new AscendingComparator<>());

        for (int i= array.length-1; i>=0; i--){
            array[i] = heapWithComparator.remove();
        }

        for (int i=0; i< array.length; i++){
            System.out.print(array[i]+" ");
        }
    }

    private static  class AscendingComparator<E extends Comparable> implements Comparator<E>{

        @Override
        public int compare(E o1, E o2) {
            if (o1.compareTo(o2) > 0){
                return 1;
            }else if (o1.compareTo(o2) < 0){
                return -1;
            }else
                return 0;
        }
    }
}
