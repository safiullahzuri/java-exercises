package chapter24;

import java.util.Collection;
import java.util.LinkedList;

public class MyGenericQueue<E> extends LinkedList<E> {

    public MyGenericQueue(){}

    public MyGenericQueue(Collection<? extends E> collection){
        super(collection);
    }

    public void enqueue(E e){
        this.addLast(e);
    }

    public E dequeue(){
        return this.removeFirst();
    }

    public int getSize(){
        return size();
    }


}
