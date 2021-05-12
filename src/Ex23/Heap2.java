package Ex23;

import java.util.ArrayList;

public class Heap2<E extends Comparable<E>> {


    private ArrayList<E> list = new ArrayList<>();

    public Heap2(){

    }

    public int getSize(){
        return list.size();
    }

    public Heap2(E[] objects){
        for (E newObject: objects){
            add(newObject);
        }
    }

    //add method add()

    public void add(E object){
        list.add(object);
        int currentIndex = list.size()-1;

        while (currentIndex > 0){
            int parentIndex = (currentIndex-1)/2;
            if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0){
                E temp = list.get(parentIndex);
                list.set(parentIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
            }else
                break;
            currentIndex = parentIndex;
        }

    }


    //add method remove()
    public E remove(){
        //remove the top

        if (list.size() == 0)return null;

        E removedObject = list.get(0);
        list.set(0, list.get(list.size()-1));
        list.remove(list.size()-1);

        //organize the heap
        int currentIndex = 0;
        while (currentIndex < list.size()){
            int leftChildIndex = 2*currentIndex+1;
            int rightChildIndex = 2*currentIndex+2;

            if (leftChildIndex >= list.size())break;
            int maxIndex = leftChildIndex;
            //see if there will be a difference here
            if (rightChildIndex < list.size()){
                if (list.get(rightChildIndex).compareTo(list.get(maxIndex)) > 0){
                    maxIndex = rightChildIndex;
                }
            }

            //swap here
            if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0){
                E temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            }else
                break;

        }
        return removedObject;

    }



}
