package Ex23;

import java.util.Comparator;

import static Ex23.Ex23_01.printArray;

public class Ex23_06 {

    public static void main(String[] args) {
        Integer[] array = {2,1,5,4,32,12,57};
        insertionSort(array, new DescendingComparator<>());
        printArray(array);
    }

    public static <E extends Comparable<E>> void insertionSort(E[] list){
        for (int i=1; i<list.length; i++){
            E currentElement = list[i];
            int k;
            for (k=i-1; k>=0 && currentElement.compareTo(list[k]) < 0; k--){
                list[k+1] = list[k];
            }
            list[k+1] = currentElement;
        }
    }

    public static <E> void insertionSort(E[] list, Comparator<? super E> comparator){
        for (int i=1; i<list.length; i++){
            int k;
            E currentElement = list[i];
            for (k=i-1; k>=0 && comparator.compare(currentElement, list[k])<0; k--){
                list[k+1] = list[k];
            }
            list[k+1] = currentElement;
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
