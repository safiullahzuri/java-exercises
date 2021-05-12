package AVLTREE_EXERCISES.EXERCISE2;

import java.util.ArrayList;
import java.util.Iterator;

public class BST<E extends Comparable<E>> extends AbstractTree<E> {
    protected TreeNode<E> root;
    protected int size = 0;

    public BST(){}

    public BST(E[] objects){
        for (int i=0; i<objects.length; i++){
            insert(objects[i]);
        }
    }


    @Override
    public boolean search(E e) {
        TreeNode<E> current = root;
        while (current != null){
            if (e.compareTo(current.element) > 0){
                current = current.right;
            }else if (e.compareTo(current.element) < 0){
                current = current.left;
            }else{
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insert(E e) {
        if (root == null){
            root = createNewNode(e);
        }else{
            TreeNode<E> current = root;
            TreeNode<E> parent = null;

            while (current != null){
                if (e.compareTo(current.element) > 0){
                    parent = current;
                    current = current.right;
                }else if (e.compareTo(current.element) < 0){
                    parent= current;
                    current = current.left;
                }else{
                    return false;
                    //duplicate entry
                }
            }

            if (e.compareTo(parent.element) > 0){
                parent.right = createNewNode(e);
            }else{
                parent.left = createNewNode(e);
            }
        }
        size++;
        return true;
    }
    public TreeNode<E> createNewNode(E element){
        return new TreeNode<>(element);
    }

    public ArrayList<TreeNode<E>> path(E e){
        TreeNode<E> current = root;

        ArrayList<TreeNode<E>> list = new ArrayList<>();

        while (current != null){
            list.add(current);
            if (e.compareTo(current.element) > 0){
                current = current.right;
            }else if (e.compareTo(current.element) < 0){
                current = current.left;
            }else{
                break;
            }
        }
        return list;
    }

    @Override
    public boolean delete(E e) {
        TreeNode<E> current = root;
        TreeNode<E> parent = null;

        while (current != null){
            if (e.compareTo(current.element) > 0){
                parent = current;
                current= current.right;
            }else if (e.compareTo(current.element) < 0){
                parent = current;
                current = current.left;
            }else{
                break;
            }
        }
        if (current == null){
            return false;
        }

        if (current.left == null){
            if (parent == null){
                root = current.right;
            }else{
                if (e.compareTo(parent.element) < 0){
                    parent.left = current.right;
                }else{
                    parent.right = current.right;
                }
            }
        }else{
            TreeNode<E> parentOfRightmost = current;
            TreeNode<E> rightmost = current.left;

            while (rightmost.right != null){
                parentOfRightmost = rightmost;
                rightmost = rightmost.right;
            }

            current.element = rightmost.element;
            if (parentOfRightmost.right == rightmost){
                parentOfRightmost.right = rightmost.left;
            }else{
                parentOfRightmost.left = rightmost.left;
            }
        }
        size--;
        return true;
    }

    @Override
    public void inorder() {
        inorder(root);
    }

    private void inorder(TreeNode<E> root){
        if (root == null) return;
        inorder(root.left);
        System.out.println(root.element);
        inorder(root.right);
    }


    @Override
    public void postorder() {
        postorder(root);
    }

    private void postorder(TreeNode<E> root){
        if (root == null)return;
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.element);
    }

    @Override
    public void preorder() {
        preorder(root);
    }

    private void preorder(TreeNode<E> root){
        if (root == null) return;
        System.out.println(root.element);
        preorder(root.left);
        preorder(root.right);
    }

    public void clear(){
        root = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new InorderIterator();
    }

    private class InorderIterator implements Iterator<E>{
        private ArrayList<E> list = new ArrayList<>();
        private int current = 0;

        public InorderIterator(){
            inorder();
        }
        private void inorder(){
            inorder(root);
        }

        private void inorder(TreeNode<E> root){
            if (root == null) return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        @Override
        public E next() {
            return list.get(current++);
        }
    }

    public static class TreeNode<E extends Comparable<E>>{
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e){
            element = e;
        }
    }
}
