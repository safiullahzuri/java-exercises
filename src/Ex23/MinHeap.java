package Ex23;

import java.util.ArrayList;

public class MinHeap<E extends Comparable<E>> {

    public static void main(String[] args) {
        Integer[] array = {2,1,5,4,32,12,57};
        MinHeap<Integer> minHeap = new MinHeap<>(array);

        for (int i= array.length-1; i>=0; i--){
            array[i] = minHeap.remove();
        }

        for (int i=0; i< array.length; i++){
            System.out.print(array[i]+" ");
        }

    }


    private ArrayList<E> list = new ArrayList<>();

    public MinHeap(){}
    public MinHeap(E[] objects){
        for (E object: objects){
            add(object);
        }
    }

    public void add(E object){
        list.add(object);
        int currentIndex = list.size()-1;

        while (currentIndex > 0){
            int parentIndex = (currentIndex-1)/2;
            if (list.get(currentIndex).compareTo(list.get(parentIndex)) < 0){
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            }else{
                break;
            }
            currentIndex = parentIndex;
        }
    }

    public E remove(){
        if (list.size() == 0){return null;}
        E removedObject = list.get(0);
        list.set(0, list.get(list.size()-1));
        list.remove(list.size()-1);

        int currentIndex = 0;

        while (currentIndex < list.size()){
            int leftChildIndex=  2* currentIndex+1;
            int rightChildIndex = 2* currentIndex+2;

            if (leftChildIndex >= list.size()){break;}
            int minIndex = leftChildIndex;

            if (rightChildIndex < list.size()){
                if (list.get(rightChildIndex).compareTo(list.get(leftChildIndex)) < 0){
                    minIndex = rightChildIndex;
                }
            }

            if (list.get(currentIndex).compareTo(list.get(minIndex)) > 0){
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(minIndex));
                list.set(minIndex, temp);
                currentIndex = minIndex;
            }else{
                break;
            }

        }



        return removedObject;
    }

}
