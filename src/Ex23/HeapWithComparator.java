package Ex23;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class HeapWithComparator<E> {

    private ArrayList<E> list = new ArrayList<>();

    private Comparator<? super E> comparator;

    public int getSize(){
        return list.size();
    }

    public HeapWithComparator(){

    }

    public HeapWithComparator(Comparator<? super E> comparator){
        this.comparator = comparator;
    }

    //implement add, remove and getSize method

    public HeapWithComparator(E[] objects, Comparator<? super E> comparator){
        this.comparator = comparator;
        for (E object: objects){
            add(object);
        }
    }

    public void add(E object){
        list.add(object);
        int currentIndex = list.size()-1;

        while (currentIndex > 0){
            int parentIndex = (currentIndex-1)/2;
            if (comparator.compare(list.get(currentIndex), list.get(parentIndex)) > 0){
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
        if (list.size() == 0)return null;
        E removedObject = list.get(0);
        list.set(0, list.get(list.size()-1));
        int currentIndex = 0;
        while (currentIndex < list.size()){
            int leftChildIndex = 2*currentIndex+1;
            int rightChildIndex = 2*currentIndex+2;

            if (leftChildIndex >= list.size())break;

            int maxIndex = leftChildIndex;
            if (rightChildIndex < list.size()){
                if (comparator.compare(list.get(rightChildIndex), list.get(leftChildIndex)) > 0){
                    maxIndex = rightChildIndex;
                }
            }

            if (comparator.compare(list.get(maxIndex), list.get(currentIndex)) > 0){
                E temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            }else{
                break;
            }


        }



        return removedObject;
    }

    @Override
    protected HeapWithComparator<E> clone() throws CloneNotSupportedException {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeapWithComparator<?> that = (HeapWithComparator<?>) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
