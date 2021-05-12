package hashing.exercises.ex5;

import hashing.learning.MyHashMap;
import hashing.learning.MyMap;
import hashing.learning.MySet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements MySet<E> {

    MyMap<E, E> map;

    public MyHashSet(){
        map = new MyHashMap<>();
    }

    public MyHashSet(int initialCapacity){
        map = new MyHashMap<>(initialCapacity);
    }

    public MyHashSet(int initialCapacity, float loadFactorThreshold){
        map = new MyHashMap<>(initialCapacity, loadFactorThreshold);
    }





    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(E e) {
        return map.containsKey(e);
    }

    @Override
    public boolean add(E e) {
        map.put(e,e);
        return true;
    }

    @Override
    public boolean remove(E e) {
        if (!map.containsKey(e)){
            return false;
        }
        map.remove(e);
        return true;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Iterator<E> iterator() {
        return new MyHashSetIterator(this);
    }

    private class MyHashSetIterator implements Iterator<E>{
        ArrayList<E> list;
        private int current = 0;
        private MyHashSet<E> set;

        public MyHashSetIterator(MyHashSet<E> set){
            this.set = set;
            list = setToList();
        }

        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        @Override
        public E next() {
            return list.get(current++);
        }

        @Override
        public void remove() {
            set.remove(list.get(current));
            list.remove(current);
        }
    }

    private ArrayList<E> setToList(){
        ArrayList<E> list = new ArrayList<>();
        Set<E> set = map.keySet();

        for (E e: set){
            list.add(e);
        }
        return list;
    }
}
