package chapter24;

import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class TwoWayLinkedList<E> extends AbstractSequentialList<E> {

    private Node2<E> tail;
    private Node2<E> head;
    private int size;

    public TwoWayLinkedList(){}

    public TwoWayLinkedList(E[] list){
        for (int i=0; i<list.length; i++){
            add(list[i]);
        }
    }

    public TwoWayLinkedList(Collection<? extends E> collection){
        for (E e: collection){
            add(e);
        }
    }

    public E getFirst(){
        if (size == 0) return null;
        return head.element;
    }

    public E getLast(){
        if (size == 0) return null;
        return tail.element;
    }

    public void addFirst(E e){
        Node2<E> newNode = new Node2<>(e);
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
        Node2<E> newNode = new Node2<>(e);
        newNode.previous = tail;
        tail = newNode;

        if (size > 0){
            tail.previous.next = tail;
        }else{
            head = tail;
        }
        size++;
        //consider when there is no element
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 ) throw new IndexOutOfBoundsException("index: "+index);
        if (index == 0) addFirst(element);
        else if (index  >= size) addLast(element);
        else{
            Node2<E> current = head;
            for (int i=1; i<index; i++){
                current = current.next;
            }
            Node2<E> nodeAfterCurrent = current.next;

            current.next = new Node2<>(element);

            (current.next).next = nodeAfterCurrent;
            (current.next).previous = current;

            nodeAfterCurrent.previous = current.next;
            size++;
        }
    }

    public E removeFirst(){
        if (size == 0) return null;
        else {
            Node2<E> temp = head;
            head = head.next;
            size--;

            if (head!=null){
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
            Node2<E> temp = tail;

            tail = tail .previous;
            size--;

            if (tail != null){
                tail.next = null;
            }else{
                head = null;
            }
            return temp.element;
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size){
            throw  new IndexOutOfBoundsException(index+"");
        }else if (index == 0) return removeFirst();
        else if (index == size -1)return removeLast();
        else{
            Node2<E> current= head.next;
            for (int i=1; i<index; i++){
                current = current.next;
            }
            Node2<E> temp = current;

            current.previous.next = current.next;
            current.next.previous = current.previous;
            size--;
            return temp.element;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node2<E> current = head;
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
        size=0;
        head = tail = null;
    }

    @Override
    public boolean contains(Object o) {
        if (size == 0) return false;
        for (E object: this){
            if ((o.equals(object))){
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index<0 || index>=size || size==0){
            throw new IndexOutOfBoundsException(index+"");
        }else if (index == 0){
            return head.element;
        }else if (index == size-1){
            return tail.element;
        }else{
            Node2<E> current = head.next;
            for (int i=1; i<index; i++){
                current = current.next;
            }
            return current.element;
        }
    }

    @Override
    public int indexOf(Object o) {
        if (size ==0 )return -1;
        Node2<E> current = tail;
        for (int i=size-1; i>=0; i--){
            if (o.equals(current.element)) return i;
            else{
                current = current.previous;
            }
        }
        return -1;
    }

    @Override
    public E set(int index, E element) {
        if (index <0 || index >=size || size == 0){
            throw new IndexOutOfBoundsException(index+"");
        }else{
            Node2<E> current = head;
            for (int i=1; i<index; i++){
                current = current.next;
            }
            Node2<E> temp = current;
            current.element = element;
            return temp.element;
        }
    }

    private class TwoWayIterator implements Iterator<E>{
        private Node2<E> current = head;
        private int index = 0;

        @Override
        public boolean hasNext() {
            return (current != null);
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
        private Node2<E> current = head;
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
            return current!=null;
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            index++;
            return e;
        }

        @Override
        public boolean hasPrevious() {
            return current!=null;
        }

        @Override
        public E previous() {
            E e = current.element;
            current = current.previous;
            index--;
            return e;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            if (hasPrevious()){return index;}
            return -1;
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
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
