package Ex23;

import static Ex23.Ex23_04.printArray;

public class Ex23_20 {

    public static void main(String[] args) {
        int[] list1 = {2,4,6,7,12};
        int[] list2 = {3,5,6,9,11,14};
        int[] list = new int[list1.length+ list2.length];
        merge(list1, list2, list);
        //printArray(list);
    }

    static void merge(int[] list1, int[] list2, int[] list){
        int counter1=0, counter2=0, counter3=0;

        while (counter1 < list1.length && counter2< list2.length){
            if (list1[counter1] < list2[counter2]){
                list[counter3++] = list1[counter1++];
            }else{
                list[counter3++] = list2[counter2++];
            }
        }

        for (; counter1< list1.length; counter1++){
            list[counter3++] = list1[counter1];
        }

        for (; counter2<list2.length; counter2++){
            list[counter3++] = list2[counter2];
        }

    }

}
