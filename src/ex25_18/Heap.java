package ex25_18;

import java.util.ArrayList;

public class Heap<E extends Comparable<E>> {
    private ArrayList<E> list = new ArrayList<>();

    public Heap(){}
    public Heap(E[] objects){
        for (int i=0; i<objects.length; i++){
            add(objects[i]);
        }
    }

    public E remove(){

        if (list.size() == 0) return null;

        E removedElement  = list.get(0);

        list.set(0, list.get(list.size()-1));
        list.remove(list.size()-1);

        int currentIndex = 0;

        while (currentIndex < list.size()){
            int leftChildIndex = 2* currentIndex+1;
            int rightChildIndex = 2* currentIndex+2;

            if (leftChildIndex >= list.size()) break;

            int biggerIndex = leftChildIndex;

            if (rightChildIndex < list.size()){
                if (list.get(rightChildIndex).compareTo(list.get(biggerIndex)) > 0){
                    biggerIndex = rightChildIndex;
                }
            }
            if (list.get(biggerIndex).compareTo(list.get(currentIndex)) > 0){
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(biggerIndex));
                list.set(biggerIndex, temp);
                currentIndex = biggerIndex;
            }else{
                break;
            }
        }
        return removedElement;
    }

    public E remove2(){
        if (list.size() == 0) return null;

        E removedObject = list.get(0);
        list.set(0, list.get(list.size()-1));
        list.remove(list.get(list.size()-1));

        int currentIndex = 0;

        while (currentIndex < list.size()){
            int leftChildIndex = 2*currentIndex+1;
            int rightChildIndex = 2*currentIndex+2;
            if (leftChildIndex >= list.size()) break;

            int biggerIndex = leftChildIndex;
            if (rightChildIndex < list.size()){
                if (list.get(rightChildIndex).compareTo(list.get(biggerIndex)) > 0){
                    biggerIndex = rightChildIndex;
                }
            }

            if (list.get(currentIndex).compareTo(list.get(biggerIndex)) < 0){
                E temp = list.get(biggerIndex);
                list.set(biggerIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = biggerIndex;
            }else{
                break;
            }
        }
        return removedObject;
    }

    public int size(){
        return list.size();
    }


    public void add(E element){
        list.add(element);
        int currentIndex = list.size()-1;

        while (currentIndex > 0){
            int parentIndex = (currentIndex-1)/2;

            if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0){
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            }else{
                break;
            }
            currentIndex = parentIndex;
        }

    }



}
