package hashing.learning;

import java.security.Key;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;



//this is a HashMap which uses separate chaining
public class MyHashMap<K, V> implements MyMap<K, V> {

    private static int DEFAULT_INITIAL_CAPACITY = 4;
    private static int MAXIMUM_CAPACITY = 1 << 30;

    public int capacity;

    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;

    private float loadFactorThreshold;
    private int size = 0;

    public LinkedList<Entry<K,V>>[] hashTable;

    public MyHashMap(){
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity){
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity, float loadFactorThreshold){
        if (initialCapacity > MAXIMUM_CAPACITY){
            this.capacity = MAXIMUM_CAPACITY;
        }else{
            this.capacity = trimToPowerOf2(initialCapacity);
        }
        this.loadFactorThreshold = loadFactorThreshold;
        hashTable = new LinkedList[capacity];
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
            if (hashTable[i] != null){
                LinkedList<Entry<K, V>> bucket = hashTable[i];
                for (Entry<K, V> entry: bucket){
                    if (entry.getValue().equals(value)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        for (int i=0; i<capacity; i++){
            if (hashTable[i] != null){
                LinkedList<Entry<K, V>> bucket = hashTable[i];
                for (Entry<K, V> entry: bucket){
                    set.add(entry);
                }
            }
        }
        return set;
    }

    @Override
    public V get(K key) {
        int bucketIndex = hash(key.hashCode());
        if (hashTable[bucketIndex] != null){
            LinkedList<Entry<K, V>> bucket = hashTable[bucketIndex];
            for (Entry<K, V> entry: bucket){
                if (entry.getKey().equals(key)){
                    return entry.getValue();
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
        java.util.Set<K> set = new HashSet<>();
        for (int i=0; i<capacity; i++){
            if (hashTable[i] != null){
                LinkedList<Entry<K, V>> bucket = hashTable[i];
                for (Entry<K, V> entry: bucket){
                    set.add(entry.getKey());
                }
            }
        }
        return set;
    }

    @Override
    public V put(K key, V value) {
        if (get(key) != null){
            int bucketIndex = hash(key.hashCode());
            LinkedList<Entry<K, V>> bucket = hashTable[bucketIndex];
            for (Entry<K, V> entry: bucket){
                if (entry.getKey().equals(key)){
                    V oldValue = entry.getValue();
                    entry.value = value;
                    return oldValue;
                }
            }
        }

        if (size >= capacity * loadFactorThreshold){
            if (capacity == MAXIMUM_CAPACITY){
                throw new RuntimeException("Exceeding maximum capacity");
            }
            rehash();
        }
        int bucketIndex = hash(key.hashCode());
        if (hashTable[bucketIndex] == null){
            hashTable[bucketIndex] = new LinkedList<>();
        }
        hashTable[bucketIndex].add(new Entry<>(key, value));
        size++;
        return value;
    }

    @Override
    public void remove(K key) {
        int bucketIndex = hash(key.hashCode());

        if (hashTable[bucketIndex] != null){
            LinkedList<Entry<K, V>> bucket = hashTable[bucketIndex];

            for (Entry<K, V> entry: bucket){
                if (entry.getKey().equals(key)){
                    bucket.remove(entry);

                    size--;
                    break;
                }
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<V> values() {
        java.util.Set<V> set = new HashSet<>();

        for (int i=0; i<capacity; i++){
            if (hashTable[i] != null){
                LinkedList<Entry<K, V>> bucket = hashTable[i];

                for (Entry<K, V> entry: bucket){
                    set.add(entry.getValue());
                }
            }
        }
        return set;
    }

    private void removeEntries(){
        for (int i=0; i<capacity; i++){
            if (hashTable[i] != null){
                hashTable[i].clear();
            }
        }
    }

    private void rehash(){
        java.util.Set<Entry<K, V>> set = entrySet();
        capacity = capacity << 1;

        hashTable = new LinkedList[capacity];
        size = 0;

        for (Entry<K, V> entry: set){
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        for (int i=0; i<capacity; i++){
            if (hashTable[i] != null && hashTable[i].size() > 0){
                for (Entry<K, V> entry: hashTable[i]){
                    builder.append(entry);
                }
            }
        }
        builder.append("]");
        return builder.toString();
    }

    private int hash(int hashCode){
        return supplementalHash(hashCode) & (capacity-1);
    }

    private static int supplementalHash(int h){
        h = h ^ ((h >>> 7) ^ (h >>> 4) );
        return h ^ (h >>> 7 ) ^ (h >>> 4);
    }

}
