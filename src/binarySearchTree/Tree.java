package binarySearchTree;

import java.util.Collection;

public interface Tree<E> extends Collection<E> {

    public boolean search(E e);
    public boolean insert(E e);
    public boolean delete(E e);

    public int getSize();

    public default void inorder(){};

    public default void postorder(){};

    public default void preorder(){};

    public default boolean isEmpty(){
        return size() == 0;
    }

    @Override
    public default boolean contains(Object o){
        return search((E) o);
    }

    @Override
    public default boolean add(E e){
        return insert(e);
    }

    @Override
    public default boolean remove(Object e){
        return delete((E) e);
    }
    @Override
    public default int size(){
        return getSize();
    }

    @Override
    public default boolean containsAll(Collection<?> c){
        //left as an exercise
        return false;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c){
        //left as an exercise
        return false;
    }

    @Override
    public default boolean removeAll(Collection<?> c){
        return false;
    }

    @Override
    public default boolean retainAll(Collection<?> c){
        return false;
    }

    @Override
    public default Object[] toArray(){
        return null;
    }

    @Override
    public default <T> T[] toArray(T[] array){
        //left as an exercise
        return null;
    }























}
