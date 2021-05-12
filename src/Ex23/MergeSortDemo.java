package Ex23;

public class MergeSortDemo {

    public static void main(String[] args) {
        int[] list = {21,13,4,9,6,1,-2,543,32};

        mergeSort(list);
    }

    static void printArray(int[] list){
        for (int a: list)
            System.out.print(a+" ");
        System.out.println();
    }

    public static void mergeSort(int[] list){
        if (list.length>1){

            int[] firstHalf = new int[list.length/2];
            System.arraycopy(list, 0, firstHalf, 0, firstHalf.length);
            mergeSort(firstHalf);

            System.out.println("Merge sorting the first half completed:");
            printArray(firstHalf);

            int secondHalfLength = list.length - list.length/2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length/2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            System.out.println("Merge sorting the second half completed:");
            printArray(secondHalf);

            merge(firstHalf, secondHalf, list);
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

        while (current1< firstHalf.length){
            temp[current3++] = firstHalf[current1++];
        }

        while (current2< secondHalf.length){
            temp[current3++] = secondHalf[current2++];
        }

    }


}
