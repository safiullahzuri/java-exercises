package algoexpert.trees;

import java.util.Iterator;

public class AbstractTree<E> implements Tree<E> {
    @Override
    public boolean insert(E e) {
        return false;
    }

    @Override
    public boolean search(E e) {
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
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
