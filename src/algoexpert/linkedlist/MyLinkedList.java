package algoexpert.linkedlist;

import java.util.Iterator;

public class MyLinkedList<E> extends MyAbstractList<E>{

    private Node<E> head, tail;

    public MyLinkedList(){}

    public MyLinkedList(E[] objects){
        super(objects);
    }

    public E getFirst(){
        if (size == 0 ) return null;
        else return head.element;
    }

    public E getLast(){
        if (size ==0) return null;
        else return tail.element;
    }

    public void addFirst(E e){
        Node<E> node = new Node<>(e);

        node.next = head;
        head = node;

        size++;

        if (tail == null){
            tail = head;
        }

    }

    public void addLast(E e){
        Node<E> node = new Node<>(e);

        if (tail == null){
            head = tail = node;
        }else{
            tail.next = node;
            tail = node;
        }

        size++;

    }


    @Override
    public void add(int index, E e) {
        if (index ==0) addFirst(e);
        else if (index >= size) addLast(e);

        else{
            Node<E> current = head;
            for (int i=1; i<index; i++ ){
                current = current.next;
            }
            Node<E> node = new Node<>(e);

            node.next = current.next;
            current.next = node;
        }

    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(E e) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public int indexOf(E e) {
        return 0;
    }

    @Override
    public int lastIndexOf(E e) {
        return 0;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public Object set(int index, E e) {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
