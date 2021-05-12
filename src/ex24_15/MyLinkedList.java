package ex24_15;

import java.util.Iterator;

public class MyLinkedList<E> extends MyAbstractList<E> {

    private Node<E> head, tail;

    public MyLinkedList(){}

    public MyLinkedList(E[] objects){
        super(objects);
    }

    public E getFirst(){
        if (size == 0) return null;
        else
            return head.element;
    }

    public E getLast(){
        if (size == 0) return null;
        else
            return tail.element;
    }

    public void addFirst(E e){
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        size++;
        if (tail == null){
            tail = head;
        }
    }

    public void addLast(E e){
        Node<E> newNode = new Node<>(e);

        if (tail == null){
            head = tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }


    @Override
    public void add(int index, E e) {
        if (index == 0) addFirst(e);
        else if (index >= size) addLast(e);
        else{
            Node<E> current = head;
            for (int i=1; i<index; i++){
                current = current.next;
            }

            Node<E> afterNode = current.next;
            current.next = new Node<>(e);

            current.next.next = afterNode;
            size++;

        }
    }

    public E removeFirst(){
        if (size == 0) return null;
        else{
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null){
                tail = null;
            }
            return temp.element;
        }
    }

    public E removeLast(){
        if (size== 0) return null;
        else if (size == 1){
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        }else{
            Node<E> current = head;
            for (int i=0; i<size-2; i++){
                current = current.next;
            }
            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

//    public E remove(int index){
//        if (index <0 || index >= size) return null;
//        else if (index == 0) return removeFirst();
//        else if (index == size-1) return removeLast();
//        else{
//            Node<E> previous = head;
//            for (int i=1; i<index; i++){
//                previous = previous.next;
//            }
//            Node<E> current = previous.next;
//            previous.next = current.next;
//            size--;
//            return current.element;
//        }
//    }

    @Override
    public void clear() {
        size = 0;
        head = tail = null;
    }

    @Override
    public boolean contains(E e) {
        Node<E> current = head;
        for (int i=0; i<size; i++){
            if (e.equals(current.element)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index <0 || index >= size || size == 0){
            throw new IndexOutOfBoundsException(index+" ");
        }
        Node<E> current = head;
        for (int i=0; i<index; i++){
            current = current.next;
        }
        return current.element;
    }

    @Override
    public int indexOf(E e) {
        Node<E> current = head;
        for (int i=0; i<size; i++){
            if (e.equals(current.element)){
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        Node<E> current = head;
        int index = -1;
        for (int i=0; i<size; i++){
            if (current.element.equals(e)){index= i;}
            current = current.next;
        }
        return index;
    }

    @Override
    public E remove(int index) {
        if (index <0 || index >= size) return null;
        else if (index == 0) return removeFirst();
        else if (index == size-1) return removeLast();
        else{
            Node<E> previous = head;
            for (int i=1; i<index; i++){
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    @Override
    public Object set(int index, E e) {
        if (index < 0 || index >= size || size == 0){
            throw new IndexOutOfBoundsException(index +" ");
        }
        Node<E> current = head;
        for (int i=0; i<index; i++){
            current = current.next;
        }
        E temp = current.element;
        current.element = e;
        return temp;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E>{
        private Node<E> current = head;
        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            return e;
        }

        @Override
        public void remove() {
            MyLinkedList.this.removeLast();
        }
    }

    private class Node<E>{
        public Node<E> next;
        public E element;

        public Node(E element){
            this.element = element;
        }
    }
}
