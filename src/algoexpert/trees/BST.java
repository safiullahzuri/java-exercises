package algoexpert.trees;

import java.util.ArrayList;
import java.util.Iterator;

public class BST<E extends Comparable<E>> extends AbstractTree<E> {

    protected TreeNode<E> root;
    protected int size = 0;

    public BST(){}

    public BST(E[] objects){
        for (E e: objects){
            insert(e);
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
                    parent=  current;
                    current = current.right;
                }else if (e.compareTo(current.element) < 0){
                    parent = current;
                    current = current.left;
                }else{
                    //the node already exists
                    return false;
                }
            }

            if (e.compareTo(parent.element) < 0){
                parent.left = createNewNode(e);
            }else{
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true;
    }

    @Override
    public void inorder() {
        inorder(root);
    }

    public void inorder(TreeNode<E> root){
        if (root == null) return;
        inorder(root.left);
        System.out.println(root.element+" ");
        inorder(root.right);
    }

    @Override
    public void preorder() {
        preorder(root);
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
            }else {
                //at this point, we have come across the node to be deleted.
                break;
            }
        }

        if (current == null) return false;

        if (current.left == null){
            if (parent == null) root = current.right;
            else
                parent.right = current.right;
        }else {
            TreeNode<E> parentOfRightmost = current;
            TreeNode<E> rightmost = current.left;

            while (rightmost.right != null){
                parentOfRightmost = rightmost;
                rightmost = rightmost.right;
            }

            current.element = rightmost.element;

            if (parentOfRightmost.right == rightmost){
                parentOfRightmost.right = rightmost.left;
            }else {
                parentOfRightmost.left = rightmost.left;
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
        ArrayList<E> list = new ArrayList<>();
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
            return current<list.size();
        }

        @Override
        public E next() {
            return list.get(current++);
        }
    }



    public ArrayList<TreeNode<E>> path(E e){
        ArrayList<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current = root;

        while (current != null){
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





    public void preorder(TreeNode<E> root){
        if (root == null) return;
        preorder(root.left);
        preorder(root.right);
        System.out.println(root.element+" ");
    }

    @Override
    public void postorder() {
        postorder(root);
    }

    public void postorder(TreeNode<E> root){
        if (root == null) return;
        System.out.println(root.element+" ");
        postorder(root.left);
        postorder(root.right);
    }




    public TreeNode<E> createNewNode(E element){
        return new TreeNode<>(element);
    }


}
