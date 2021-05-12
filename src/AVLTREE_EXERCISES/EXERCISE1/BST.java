package AVLTREE_EXERCISES.EXERCISE1;


import java.util.ArrayList;
import java.util.Iterator;

public class BST<E extends Comparable<E>> extends AbstractTree<E>{

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
            TreeNode<E> parent = null;
            TreeNode<E> current = root;

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
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        while (current != null){
            if (e.compareTo(current.element) > 0){
                parent = current;
                current = current.right;
            }else if (e.compareTo(current.element) < 0){
                parent = current;
                current = current.left;
            }else{
                break;
                //we have the parent of the node to be deleted and the node itself
            }
        }

        if (current == null){
            //the element did not exist
            return false;
        }

        if (current.left == null){
            if (parent == null){
                //if this is the root we are deleting
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
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null){
                parentOfRightmost = rightMost;
                rightMost = rightMost.right;
            }

            current.element = rightMost.element;

            if (parentOfRightmost.right == rightMost){
                parentOfRightmost.right = rightMost.left;
            }else{
                parentOfRightmost.left = rightMost.left;
            }
        }
        size--;
        return true;
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
            if (current < list.size()){
                return true;
            }else{
                return false;
            }
        }

        @Override
        public E next() {
            return list.get(current++);
        }

        @Override
        public void remove() {
            delete(list.get(current));
            list.clear();
            inorder();
        }
    }

    public void clear(){
        root = null;
        size = 0;
    }


    public TreeNode<E> createNewNode(E e){
        return new TreeNode<>(e);
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
