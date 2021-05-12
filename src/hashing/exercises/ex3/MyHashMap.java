package hashing.exercises.ex3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyHashMap<K, V> implements MyMap<K, V> {


    private static int DEFAULT_INITIAL_CAPACITY = 4;
    private static int MAXIMUM_CAPACITY = 1 << 30;

    private int capacity;
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.5f;

    private float loadFactorThreshold;
    private int size = 0;

    private ArrayList<Entry<K, V>> table;

    public MyHashMap(){
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity){
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity, float loadFactorThreshold){
        if (initialCapacity > MAXIMUM_CAPACITY){
            capacity = MAXIMUM_CAPACITY;
        }else{
            capacity = trimToPowerOf2(initialCapacity);
        }
        this.loadFactorThreshold = loadFactorThreshold;
        table = new ArrayList<>();
        for (int i=0; i<capacity; i++){
            table.add(null);
        }
    }

    private int trimToPowerOf2(int initialCapacity){
        int capacity = 1;
        while (capacity < initialCapacity){
            capacity = capacity << 1;
        }
        return capacity;
    }


    @Override
    public void clear() {
        size = 0;
        removeEntries();
    }

    private void removeEntries(){
        table.clear();
    }

    @Override
    public boolean containsKey(K key) {
        if (get(key) != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i=0; i<capacity; i++){
            if (table.get(i) != null && table.get(i).getValue() == value){
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        for (int i=0; i<capacity; i++){
            if (table.get(i) != null){
                set.add(table.get(i));
            }
        }
        return set;
    }

    @Override
    public V get(K key) {
        int hash1 = hash(key.hashCode());
        int index = hash1;
        int j = 0;

        while (table.get(index) != null){
            if (table.get(index).getKey().equals(key)){
                return table.get(index).getValue();
            }

            index = hash1 + j++ * hash2(hash1);
            index %= capacity;
        }
        return null;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i=0; i<table.size(); i++){
            if (table.get(i) != null){
                set.add(table.get(i).getKey());
            }
        }
        return set;
    }

//    @Override /** Add an entry (key, value) into the map */
//    public V put(K key, V value) {
//        int index = hash(key.hashCode());
//
//        while (table.get(index) != null) {
//            index++;
//            index %= capacity;
//        }
//
//        if (size >= capacity * loadFactorThreshold) {
//            if (capacity == MAXIMUM_CAPACITY) {
//                throw new RuntimeException("Exceeding maximum capacity");
//            }
//            rehash();
//        }
//
//        // Add a new entry (key, value) to hash table
//        table.set(index, new MyMap.Entry<K, V>(key, value));
//
//        size++; // Increase size
//
//        return value;
//    }


    @Override
    public V put(K key, V value) {
        int hash1 = hash(key.hashCode());
        int index = hash1;
        int j = 0;

        while (table.get(index) != null){
            if (table.get(index).getKey().equals(key)){
                Entry<K, V> entry = table.get(index);
                V oldValue = entry.getValue();

                entry.value = value;
                table.set(index, entry);
                return oldValue;
            }
            index = hash1 + j++*hash2(hash1);
            index %= capacity;
        }

        if (size >= capacity * loadFactorThreshold){
            if (capacity == MAXIMUM_CAPACITY)
                throw new RuntimeException("Exceeding maximum capacity.");
            rehash();
        }
        table.set(index, new Entry<>(key, value));
        size++;
        return value;
    }

    private void rehash(){
        Set<Entry<K, V>> set = entrySet();
        capacity <<= 1;
        size = 0;
        table.clear();
        for (int i=0; i<capacity; i++){
            table.add(null);
        }

        for (Entry<K, V> entry: set){
            put(entry.getKey(), entry.getValue());
        }

    }




    @Override
    public void remove(K key) {
        int hash1 = hash(key.hashCode());
        int index = hash1;
        int j =0;

        while (table.get(index) != null){
            if (table.get(index).getKey().equals(key)){
                table.remove(index);
                size--;
                break;
            }
            index = hash1 + j++*hash2(hash1);
            index %= capacity;
        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<V> values() {
        Set<V> set = new HashSet<>();
        for (int i=0; i<capacity; i++){
            if (table.get(i) != null){
                set.add(table.get(i).getValue());
            }
        }
        return set;
    }

    @Override
    public Set<V> getAll(K key) {
        Set<V> set = new HashSet<>();
        int index = hash(key.hashCode());

        while (table.get(index) != null){
            if (table.get(index).getKey().equals(key)){
                set.add(table.get(index).getValue());
            }
            index++;
            index %= capacity;
        }
        return set;
    }

    private int hash(int hashCode){
        return supplementalHash(hashCode) & (capacity-1);
    }

    private int supplementalHash(int h){
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private int hash2(int hash){
        return (7-hash) & (7-1);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (Entry<K, V> entry: table){
            if (entry != null && table.size() > 0){
                builder.append(entry);
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
