package threads_review;

public class QuickSort2<E extends Comparable<E>> {


    public static void main(String[] args) {
        QuickSort2<Integer> quickSort2=  new QuickSort2<>();
        Integer[] list = {3,5,7,4,12,453,645,23,65,345};

        quickSort2.quickSort(list);
        for (int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");
    }

    public  void quickSort(E[] list){
        quickSort(list, 0, list.length-1);
    }

    public void quickSort(E[] list, int low, int high){
        if (low < high){
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex);
            quickSort(list, pivotIndex+1, high);
        }
    }

    public int partition(E[] list, int first, int last){
        E pivot = list[first];
        int i = first;
        int j = last;

        while (i<j){

            while (list[i].compareTo(pivot) <= 0)
                i++;

            while (list[j].compareTo(pivot) > 0)
                j--;

            if (i<j){
                E temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }
        }

        while (j>first && list[j].compareTo(pivot)>0){
            j--;
        }

        if (pivot.compareTo(list[j]) > 0){
            list[first] = list[j];
            list[j] = pivot;
            return j;
        }else{
            return first;
        }
    }













}
