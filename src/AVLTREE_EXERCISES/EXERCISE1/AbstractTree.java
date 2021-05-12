package AVLTREE_EXERCISES.EXERCISE1;

import java.util.Iterator;

public class AbstractTree<E> implements Tree<E> {
    @Override
    public boolean search(E e) {
        return false;
    }

    @Override
    public boolean insert(E e) {
        return false;
    }

    @Override
    public boolean delete(E e) {
        return false;
    }

    @Override
    public void inorder() {

    }

    @Override
    public void postorder() {

    }

    @Override
    public void preorder() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return getSize()==0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
