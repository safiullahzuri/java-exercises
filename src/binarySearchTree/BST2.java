package binarySearchTree;

import java.util.Arrays;
import java.util.Iterator;

public class BST2<E extends Comparable<E>> implements Tree<E> {

    protected TreeNode<E> root;
    protected int size = 0;

    public BST2(){}
    public BST2(E[] objects){
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
                    break;
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
        //now we have the node to delete and also its parent
        if (current == null){
            return false;
        }

        //case 1: the node to be deleted does not have a left child, so we attach it's right child to its parent
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
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost != null){
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }

            current.element = rightMost.element;

            if (parentOfRightMost.right == rightMost){
                parentOfRightMost.right = rightMost.left;
            }else{
                parentOfRightMost.left = rightMost.left;
            }

        }

        //case 2: the node to be deleted has a right child and
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
        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(E e){
            element = e;
        }

    }

}
