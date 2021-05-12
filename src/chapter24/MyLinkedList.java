package chapter24;

import java.util.Iterator;

public class MyLinkedList<E> extends MyAbstractList<E> {

    private Node<E> head, tail;

    public MyLinkedList(){}

    public MyLinkedList(E[] objects){
        super(objects);
    }

    public E getFirst(){
        if (size == 0){
            return null;
        }else{
            return head.element;
        }
    }

    public E getLast(){
        if (size == 0){
            return null;
        }else{
            return tail.element;
        }
    }

    public void addFirst(E e){
        Node<E> newNode = new Node<>(e); //create a new node
        newNode.next = head;
        head = newNode;
        size++;

        if (tail == null){
            tail = head;
        }

    }

    public void addLast(E e){
        Node<E> lastNode = new Node<>(e);
        tail.next = lastNode;
        tail = lastNode;
        size++;

        if (head == null){
            head = tail;
        }
    }

    public void addLast2(E e){
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
        if (index == 0)
            addFirst(e);
        else if (index >= size)
            addLast(e);
        else{
            //insert it into the middle
            Node<E> current = head;
            for (int i=1; i<index; i++){
                current = current.next;
            }
            Node<E> temp = current.next;
            Node<E> newNode = new Node<>(e);

            current.next = newNode;
            current.next.next = temp;
            //newNode.next = temp;
        }
    }

    public E removeFirst(){
        if (size == 0){
            return null;
        }else {
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
        if (size == 0){
            return  null;
        }else if (size == 1){
            Node<E> temp = tail;
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

    public E remove(int index){
        if (index == 0 )
            return removeFirst();
        if (index == size-1)
            return removeLast();
        else{
            Node<E> previous = head;
            for (int i=1; i<index; i++){
                previous = previous.next;
            }
            Node<E> nodeToRemove = previous.next;
            previous.next = nodeToRemove.next;
            size--;
            return nodeToRemove.element;
        }
    }

    @Override
    public void clear() {
        size = 0;
        head = tail = null;
    }

    @Override
    public boolean contains(E e) {
        if (head == null){
            return false;
        }
        Node<E> current = head;
        for (int i=1; i<size; i++){
            if (current.element == e){
                return true;
            }else{
                current = current.next;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index >= size)return null;
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
            if (current.element == e){
                return i;
            }else{
                current = current.next;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        Node<E> current = head;
        int index = -1;
        for (int i=0; i<size; i++){
            if (current.element == e){
                index = i;
            }
            current = current.next;
        }
        return index;
    }



    @Override
    public E set(int index, E e) {

        Node<E> nodeAtCurrentIndex = head;
        for (int i=0; i<index; i++){
            nodeAtCurrentIndex = nodeAtCurrentIndex.next;
        }
        E temp = nodeAtCurrentIndex.element;
        nodeAtCurrentIndex.element = e;
        return temp;

    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        for (int i=0; i<size; i++){
            result.append(current.element);
            current = current.next;
            if (current != null){
                result.append(", ");
            }else{
                result.append("]");
            }
        }
        return result.toString();
    }
}
