package Ex23;

public class HeapSort {

    public static <E extends Comparable<E>> void heapSort(E[] list){
        Heap2<E> heap = new Heap2<>();
        for (int i=0; i< list.length; i++){
            heap.add(list[i]);
        }

        for (int i= list.length-1; i>=0; i--){
            list[i] = heap.remove();
        }
    }

    public static void main(String[] args) {
        Integer[] list = {-44,2,4,54,32,12,45,11,43};
        heapSort(list);

        for (int i=0; i< list.length; i++){
            System.out.print(list[i]+" ");
        }
    }
}
