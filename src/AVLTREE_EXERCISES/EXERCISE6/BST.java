package AVLTREE_EXERCISES.EXERCISE6;

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

    public ArrayList<TreeNode<E>> path(E e){
        ArrayList<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current = root;

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
                    parent = current;
                    current = current.left;
                }else{
                    //duplicate entry
                    return false;
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

    public TreeNode<E> createNewNode(E e){
        return new TreeNode<>(e);
    }

    @Override
    public boolean delete(E e) {
        TreeNode<E> current = root;
        TreeNode<E> parent = null;

        while (current != null){
            if (e.compareTo(current.element) > 0){
                parent = current;
                current = current.right;
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
                if (e.compareTo(parent.element) > 0){
                    parent.right = current.right;
                }else{
                    parent.left = current.right;
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

    public void clear(){
        root = null;
        size = 0;
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
