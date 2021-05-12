package Ex23;

import java.util.Comparator;

public class Ex23_03 {

    public static void main(String[] args) {
        Integer[] array = {3,5,6,4,2,1,11,8,9,7,10,14,12,13};
        //quickSort(array, 0, array.length-1, new AscendingComparator<>());
        quickSort(array);
        printArray(array);
    }

    public static <E extends Comparable<E>> void quickSort(E[] list){
        quickSort(list, 0, list.length-1);
    }

    public static<E extends Comparable<E>> void quickSort(E[] list, int low, int high){
        if (high > low){
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex-1);
            quickSort(list, pivotIndex+1, high);
        }
    }

    static void printArray(Integer[] list){
        for (int a: list)
            System.out.print(a+" ");
        System.out.println();
    }

    public static <E extends Comparable<E>> int partition(E[] list, int low, int high){
        int i = low+1;
        int j = high;
        E pivot = list[low];

        while (i < j){

            while (i<=j && list[i].compareTo(pivot) <= 0 ){
                i++;
            }

            while (i<=j && list[j].compareTo(pivot) > 0){
                j--;
            }

            if (i < j){
                E temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }
        }


        E temp = list[low];
        list[low] = list[j];
        list[j]= temp;
        return j;
    }

    public static <E> void quickSort(E[] list,int first, int last, Comparator<? super E> comparator){
        if (last > first){
            int pivotIndex = partition(list, first, last, comparator);
            quickSort(list, first, pivotIndex-1, comparator);
            quickSort(list, pivotIndex+1, last, comparator);
        }
    }

    private static <E> int partition(E[] list, int first, int last, Comparator<? super E> comparator) {
        E pivot = list[first];
        int low = first+1;
        int high = last;

        while (high> low){
            while (low <=high && comparator.compare(list[low], pivot) <= 0){
                low++;
            }
            while (low <= high && comparator.compare(list[high], pivot) > 0){
                high--;
            }
            if (high > low){
                E temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high>first && comparator.compare(list[high], pivot) >= 0){
            high--;
        }

        if (comparator.compare(pivot, list[high]) > 0){
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }else{
            return first;
        }

    }

    private static class AscendingComparator<E extends Comparable<E>>
            implements Comparator<E> {
        @Override
        public int compare(E o1, E o2) {
            if (o1.compareTo(o2) > 0) {
                return 1;
            } else if (o1.compareTo(o2) < 0) {
                return -1;
            }
            return 0;
        }
    }

}
