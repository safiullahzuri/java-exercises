package heap_visualisation;

import java.util.ArrayList;

public class MyHeap {
    private ArrayList<Integer> list = new ArrayList<>();

    public MyHeap(){}

    public void add(int number){
        list.add(number);

        int currentIndex = list.size()-1;

        while (currentIndex > 0){
            int parentIndex = (currentIndex-1)/2;

            if (list.get(parentIndex) < number){
                int temp = list.get(parentIndex);
                list.set(parentIndex, number);
                list.set(currentIndex, temp);
            }else {
                break;
            }
            currentIndex = parentIndex;
        }

    }

    public int remove(){
        if (list.size() == 0 ) return Integer.MIN_VALUE;

        int removedElement = list.get(0);
        list.set(0, list.get(list.size()-1));
        list.remove(list.size()-1);

        int currentIndex = 0;

        while (currentIndex < list.size()){
            int leftChildIndex = 2*currentIndex+1;
            int rightChildIndex = 2*currentIndex+2;

            if (leftChildIndex >= list.size()) break;
            int minIndex = leftChildIndex;

            if (rightChildIndex < list.size()){
                minIndex = Math.min(list.get(leftChildIndex), list.get(rightChildIndex));
            }

            if (list.get(currentIndex) < list.get(minIndex)){
                int temp = list.get(currentIndex);
                list.set(currentIndex, list.get(minIndex));
                list.set(minIndex, temp);

                currentIndex = minIndex;
            }else {
                break;
            }

        }
        return removedElement;
    }




}
