package binarySearchTree;

import java.util.Iterator;

public class BST3<E extends Comparable<E>> implements Tree<E>{

    private TreeNode<E> root;
    private int size = 0;

    public BST3(){}

    public BST3(E[] objects){
        for (int i=0; i<objects.length; i++){
            add(objects[i]);
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

    private TreeNode<E> createNewNode(E e){
        return new TreeNode<>(e);
    }

    @Override
    public boolean insert(E e) {
        //how to insert an element into
        if (root == null){
            root = createNewNode(e);
        }else{
            //locate the node parent
            TreeNode<E> parent = null;
            TreeNode<E> current = root;

            while (current != null){
                if (e.compareTo(current.element) > 0){
                    parent= current;
                    current = current.right;
                }else if (e.compareTo(current.element) < 0){
                    parent = current;
                    current = current.left;
                }else{
                    break;
                }
            }
            //we have the node and it's
            if (e.compareTo(parent.element) > 0){
                parent.right = createNewNode(e);
            }else{
                parent.left = createNewNode(e);
            }
        }
        size++;
        return true;
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
            }
        }
        if (current == null){
            return false;
        }

        //case 1: no left child
        if (current.left == null){
            if (parent == null){
                //if this is the root that we are deleting
                root = current.right;
            }else{
                if (e.compareTo(parent.element) > 0){
                    parent.right = current.right;
                }else {
                    parent.left = current.right;
                }
            }
        }else{
            //current node has a left child
            //find its rightmost node and its' parent
            TreeNode<E> rightMostParent = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost != null){
                rightMostParent = rightMost;
                rightMost = rightMost.right;
            }

            current.element = rightMost.element;

            if (rightMostParent.right == rightMost){
                rightMostParent.right = rightMost.left;
            }else{
                rightMostParent.left = rightMost.left;
            }
        }
        size--;
        return true;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {

    }

    public static class TreeNode<E>{
        protected E element;
        protected TreeNode<E> right;
        protected TreeNode<E> left;

        public TreeNode(E element){
            this.element = element;
        }

    }
}
