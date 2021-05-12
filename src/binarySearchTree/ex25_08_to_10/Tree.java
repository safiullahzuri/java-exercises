package binarySearchTree.ex25_08_to_10;

public interface Tree<E> extends Iterable<E> {

    public boolean search(E e);
    public boolean delete(E e);
    public boolean insert(E e);

    public void inorder();
    public void preorder();
    public void postorder();

    public int getSize();
    public boolean isEmpty();
}
