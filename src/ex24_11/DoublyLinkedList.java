package ex24_11;

import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class DoublyLinkedList<E> extends AbstractSequentialList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoublyLinkedList(){}

    public DoublyLinkedList(E[] objects){
        for (int i=0; i<objects.length; i++){
            add(objects[i]);
        }
    }

    public DoublyLinkedList(Collection<? extends E> collection){
        for (E obj: collection){
            add(obj);
        }
    }

    public E getFirst(){
        if (size == 0) return null;
        return head.element;
    }

    public E getLast(){
        if (size == 0 ) return null;
        return tail.element;
    }

    public void addFirst(E e){
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;

        if (size > 0){
            head.next.previous = head;
        }else{
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newNode = new Node<>(e);
        newNode.previous = tail;
        tail = newNode;

        if (size > 0){
            tail.previous.next = tail;
        }else{
            head = tail;
        }
        size++;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0) throw new IndexOutOfBoundsException(index+"");
        else if (index ==0) addFirst(element);
        else if (index >= size) addLast(element);
        else {
            Node<E> current = head;
            for (int i=1; i<index; i++){
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(element);

            current.next.previous = current;
            current.next.next = temp;
            temp.previous = current.next;
            size++;
        }
    }

    public E removeFirst(){
        if (size == 0) return null;
        else{
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head != null){
                head.previous = null;
            }else{
                tail = null;
            }
            return temp.element;
        }
    }

    public E removeLast(){
        if (size == 0) return null;
        else{
            Node<E> temp = tail;
            tail = tail.previous;
            size--;

            if (tail == null){
                head = null;
            }else{
                tail.next = null ;
            }
            return temp.element;
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException(index+" ");
        }else if (index == 0) return removeFirst();
        else if (index == size-1) return removeLast();
        else{
            Node<E> current = head;
            for (int i=1; i<index; i++){
                current = current.next;
            }

            //i changed it, it might be source of problem later
            Node<E> temp = current;
            current.previous.next = current.next;
            current.next.previous = current.previous;
            size--;
            return temp.element;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", ");
            } else {
                result.append("]");
            }
        }

        return result.toString();
    }

    @Override
    public void clear() {
        size = 0;
        head = tail= null;
    }

    @Override
    public boolean contains(Object o) {
        if (size == 0 ) return false;
        for (E object: this){
            if (object.equals(o))
                return true;
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index <0 || index>=size || size == 0)
            throw new IndexOutOfBoundsException(index+" ");
        else if (index == 0) return head.element;
        else if (index == size-1) return tail.element;
        else{
            Node<E> current = head;
            for (int i=1; i<index; i++){
                current = current.next;
            }
            return current.element;
        }
    }

    @Override
    public int indexOf(Object o) {
        if (size == 0) return -1;
        Node<E> current = head;
        for (int i=0 ;i<size; i++){
            if (o.equals(current.element)){
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (size== 0) return -1;
        Node<E> current = tail;
        for (int i=size-1; i >=0; i--){
            if (o.equals(current.element)){
                return i;
            }
            current = current.previous;
        }
        return -1;
    }

    @Override
    public E set(int index, E element) {
        if (index<0 || index>=size || size == 0){
            throw new IndexOutOfBoundsException(index+" ");
        }else{
            Node<E> current = head;
            for (int i=1; i<index; i++){
                current = current.next;
            }
            Node<E> temp = current;
            current.element=  element;
            return temp.element;
        }
    }

    private class TwoWayIterator implements Iterator<E>{
        private Node<E> current = head;
        private int index = 0;

        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            index++;
            return e;
        }
    }

    private class TwoWayListIterator implements ListIterator<E>{
        private Node<E> current = head;
        private int index = 0;

        public TwoWayListIterator(){}

        public TwoWayListIterator(int index){
            this.index = index;
            for (int i=0; i<index; i++){
                current = current.next;
            }
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public E previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }
    }

    @Override
    public ListIterator<E> listIterator() {
        return new TwoWayListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new TwoWayListIterator(index);
    }

    @Override
    public int size() {
        return size;
    }

    private class Node<E>{
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E e){
            element = e;
        }

    }
}
