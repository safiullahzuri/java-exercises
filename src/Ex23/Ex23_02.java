package Ex23;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class Ex23_02 {

    public static void main(String[] args) {
        Integer[] list = {15, 25, 2, 4, 18, 92, -10, 54};
        mergeSort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1.compareTo(o2) > 0){
                    return -1;
                }else if (o1.compareTo(o2) < 0){
                    return 1;
                }
                return 0;
            }
        });
        printList(list);
    }

    public static <E extends Comparable<E>> void mergeSort(E[] list){
        mergeSort(list, new AscendingComparator<>());
    }


    public static <E extends Comparable<E>> void mergeSort(E[] list, Comparator<? super E> comparator){

        if (list.length > 1){
            E[] firstHalf = Arrays.copyOfRange(list,0, list.length/2);
            E[] secondHalf = Arrays.copyOfRange(list, list.length/2, list.length);

            mergeSort(firstHalf, comparator);
            mergeSort(secondHalf, comparator);

            int current1 = 0;
            int current2 = 0;
            int current3 = 0;

            while (current1< firstHalf.length && current2< secondHalf.length){
                if (firstHalf[current1].compareTo(secondHalf[current2]) < 0){
                    list[current3++] = firstHalf[current1++];
                }else{
                    list[current3++] = secondHalf[current2++];
                }
            }

            while (current1< firstHalf.length){
                list[current3++] = firstHalf[current1++];
            }

            while (current2< secondHalf.length){
                list[current3++] = secondHalf[current2++];
            }


        }

    }

    public static <E> void printList(E[] list) {
        for (E elem: list) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }

    private static class AscendingComparator<E extends Comparable<E>> implements Comparator<E>{


        @Override
        public int compare(E o1, E o2) {
            if (o1.compareTo(o2) < 0){
                return -1;
            }else if (o1.compareTo(o2) > 0){
                return 1;
            }
            return 0;
        }
    }
}
