package hashing.exercises.ex1;

import java.util.HashSet;
import java.util.Set;

public class LinearProbingMap<K, V> implements MyMap<K, V> {

    private static int DEFAULT_INITIAL_CAPACITY = 4;
    private static int MAXIMUM_CAPACITY = 1 << 30;
    public int capacity;
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.5f;
    private float loadFactorThreshold;
    private int size = 0;

    public MyMap.Entry<K, V>[] table;

    public LinearProbingMap(int initialCapacity){
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    public LinearProbingMap(){
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }


    public LinearProbingMap(int initialCapacity, float loadFactorThreshold){
        if (initialCapacity > MAXIMUM_CAPACITY){
            capacity = MAXIMUM_CAPACITY;
        }else{
            capacity = trimToPowerOf2(initialCapacity);
        }
        this.loadFactorThreshold = loadFactorThreshold;
        table = new MyMap.Entry[capacity];
    }

    private int trimToPowerOf2(int initialCapacity){
        int capacity = 1;
        while (capacity < initialCapacity){
            capacity <<= 1;
        }
        return capacity;
    }



    @Override
    public void clear() {
        size = 0;
        for (int i=0; i<capacity; i++){
            table[i] = null;
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (get(key) != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean containsValue(V value) {
        for (int i=0; i<capacity; i++){
            if (table[i] != null){
                if (table[i].getValue().equals(value)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<MyMap.Entry<K, V>> set = new HashSet<>();

        for (int i=0; i<capacity; i++){
            if (table[i] != null){
                set.add(table[i]);
            }
        }
        return set;
    }

    public V get2(K key) {
        int index = hash(key.hashCode());
        if (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                return table[index].getValue();
            }
        }
        return null;
    }

    @Override
    public V get(K key) {
        int bucketIndex = hash(key.hashCode());
        if (table[bucketIndex] != null){

            if (table[bucketIndex].getKey().equals(key)){
                return table[bucketIndex].getValue();
            }else{
                bucketIndex++;
                while (bucketIndex < capacity && table[bucketIndex] != null){
                    if (table[bucketIndex].getKey().equals(key)){
                        return table[bucketIndex].getValue();
                    }
                    bucketIndex++;
                    if (bucketIndex == capacity){
                        bucketIndex= 0;
                    }
                }
            }


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

        for (int i=0; i<capacity; i++){
            if (table[i] != null){
                set.add(table[i].getKey());
            }
        }
        return set;
    }

    @Override
    public V put(K key, V value) {
        if (get(key) != null){
            int index = hash(key.hashCode());
            if (table[index].getKey().equals(key)){
                V oldValue = table[index].getValue();
                table[index].value = value;
                return oldValue;
            }
        }

        if (size >= capacity * loadFactorThreshold){
            if (capacity == MAXIMUM_CAPACITY){
                throw new RuntimeException("Exceeding maximum capacity.");
            }
            rehash();
        }

        int index = hash(key.hashCode());
        if (table[index] == null){
            table[index] = new Entry<>(key, value);
        }else{
            index += 1;
            do{
                if (index >= capacity){
                    index = 0;
                }
                if (table[index] == null){
                    table[index] = new Entry<>(key, value);
                    break;
                }
                index++;
            }while (true);
        }
        size++;
        return value;
    }

    private void rehash(){
        Set<Entry<K, V>> set = entrySet();
        capacity <<= 1;
        table = new Entry[capacity];
        size = 0;

        for (Entry<K, V> entry: set){
            put(entry.getKey(), entry.getValue());
        }
    }



    @Override
    public void remove(K key) {
        int index = hash(key.hashCode());
        if (table[index] != null){
            if (table[index].getKey().equals(key)){
                table[index] = null;
                size--;
            }
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
            if (table[i] != null){
                set.add(table[i].getValue());
            }
        }
        return set;
    }

    private int hash(int hashCode){
        return hashCode % capacity;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i=0; i<capacity; i++){
            if (table[i] != null){
                stringBuilder.append(table[i]);
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
