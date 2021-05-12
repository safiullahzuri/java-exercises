package Ex23;

import java.util.Comparator;

public class Ex23_01 {

    public static void main(String[] args) {
        Integer[] array = {2,5,4,32,64,23,11,3};
        bubbleSort(array);
        printArray(array);
    }

    public static <E extends Comparable<E>> void  bubbleSort(E[] list){
        for (int i=0; i< list.length; i++){
            for (int j=0; j<list.length-1; j++){
                if (list[j].compareTo(list[j+1]) > 0){
                    E temp = list[j];
                    list[j] = list[j+1];
                    list[j+1] = temp;
                }
            }
        }
    }



    public static void printArray(Integer[] array){
        for (int a: array){
            System.out.printf(a+"\t");
        }
    }

}
