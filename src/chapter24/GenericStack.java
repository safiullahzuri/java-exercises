package chapter24;

import java.util.ArrayList;


public class GenericStack<E> {

    private ArrayList<E> list = new ArrayList<>();

    public void push(E e){
        list.add(e);
    }

    public int getSize(){
        return list.size();
    }

    public E pop(){
        E o = list.get(getSize()-1);
        list.remove(getSize()-1);
        return o;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
