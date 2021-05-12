package threads_review.exercises;

public class Ex13<E extends Comparable<E>> {

    public static void main(String[] args) {
        int[] list = new int[16];
        for (int i=0;i<list.length; i++){
            list[i] = (int) (Math.random()*100);
        }
        mergeSort(list);
        for (int i=0; i<list.length; i++){
            System.out.print(list[i]+" ");
        }
    }


    public static void mergeSort(int[] list){
        if (list.length > 1){
            int[] firstHalf = new int[list.length/2];
            System.arraycopy(list, 0, firstHalf, 0, list.length/2);
            mergeSort(firstHalf);

            int[] secondHalf = new int[list.length-list.length/2];
            System.arraycopy(list, list.length/2, secondHalf, 0, secondHalf.length);
            mergeSort(secondHalf);

            merge(firstHalf, secondHalf, list);
        }
    }

    public void merge(E[] firstHalf, E[] secondHalf, E[] temp){
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;

        while (current1<firstHalf.length && current2<secondHalf.length){
            if (firstHalf[current1].compareTo(secondHalf[current2]) < 0){
                temp[current3++] = firstHalf[current1++];
            }else{
                temp[current3++] = secondHalf[current2++];
            }
        }

        while (current1< firstHalf.length){
            temp[current3++] = firstHalf[current1++];
        }

        while (current2 < secondHalf.length){
            temp[current3++] = secondHalf[current2++];
        }
    }

    public static void merge(int[] firstHalf, int[] secondHalf, int[] temp){
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;

        while (current1 < firstHalf.length && current2 < secondHalf.length){
            if (firstHalf[current1] < secondHalf[current2]){
                temp[current3++] = firstHalf[current1++];
            }else{
                temp[current3++] = secondHalf[current2++];
            }
        }

        while (current1 < firstHalf.length){
            temp[current3++] = firstHalf[current1++];
        }

        while (current2 < secondHalf.length){
            temp[current3++] = secondHalf[current2++];
        }

    }
}
