package Ex23;

import java.util.Comparator;

import static Ex23.Ex23_01.printArray;

public class GenericHeapSort<E> {

    public static void main(String[] args) {
        Integer[] list = {3,2,5,4,22,12,67};
        heapSort(list, new DescendingComparator<>());
        printArray(list);
    }

    public static <E extends Comparable<E>> void heapSort(E[] list){
        Heap2<E> heap2 = new Heap2<>();
        for (int i=0; i< list.length; i++){
            heap2.add(list[i]);
        }

        for (int i= list.length-1; i>=0; i--){
            list[i] = heap2.remove();
        }
    }

    public static <E> void heapSort(E[] list, Comparator<? super E> comparator){
        HeapWithComparator heapWithComparator = new HeapWithComparator(comparator);

        for (int i=0; i< list.length; i++){
            heapWithComparator.add(list[i]);
        }

        for (int i= list.length-1; i>=0; i--){
            list[i] = (E) heapWithComparator.remove();
        }

    }

    private static class DescendingComparator<E extends Comparable> implements Comparator<E>{

        @Override
        public int compare(E o1, E o2) {
            if (o1.compareTo(o2) > 0){
                return -1;
            }else if (o1.compareTo(o2) < 0){
                return 1;
            }else
                return 0;
        }
    }

}
