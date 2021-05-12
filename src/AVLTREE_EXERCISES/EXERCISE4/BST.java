package AVLTREE_EXERCISES.EXERCISE4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

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
            if (e.compareTo(current.element) < 0){
                current = current.left;
            }else if (e.compareTo(current.element) > 0){
                current = current.right;
            }else{
                return true;
            }
        }
        return false;
    }

    public TreeNode<E> createNewNode(E e, TreeNode<E> parent){
        TreeNode<E> node = new TreeNode<>(e);
        node.parent = parent;
        return node;
    }

    @Override
    public boolean insert(E e) {
        if (root == null){
            root = createNewNode(e, null);
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
                    return false;
                }
            }
            if (e.compareTo(parent.element) > 0){
                parent.right = createNewNode(e, parent);
            }else{
                parent.left = createNewNode(e, parent);
            }
        }
        size++;
        return true;
    }


    private TreeNode<E> getNode(E element){
        TreeNode<E> current = root;

        while (current != null){
            if (element.compareTo(current.element) > 0){
                current = current.right;
            }else if (element.compareTo(current.element) < 0){
                current = current.left;
            }else{
                break;
            }
        }
        if (current == null) return null;
        return current;
    }

    public boolean isLeaf(E element){
        TreeNode<E> node = getNode(element);
        if (node == null) return false;
        return node.left == null && node.right == null;
    }

    public ArrayList<E> getPath(E e){
        ArrayList<E> path = new ArrayList<>();
        TreeNode<E> node = getNode(e);

        while (node != null){
            path.add(node.element);
            node = node.parent;
        }
        return path;
    }

    public void breadthFirst(){
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            TreeNode<E> node = queue.poll();
            System.out.print(node.element+"\t");
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

    @Override
    public void inorder() {
        inorder(root);
    }

    protected void inorder(TreeNode<E> root){
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element+"\t");
        inorder(root.right);
    }

    @Override
    public void postorder() {
        postorder(root);
    }

    protected void postorder(TreeNode<E> root){
        if (root == null) return;
        inorder(root.left);
        inorder(root.right);
        System.out.print(root.element+"\t");
    }

    @Override
    public void preorder() {
        preorder(root);
    }

    protected void preorder(TreeNode<E> root){
        if (root == null) return;
        System.out.print(root.element+"\t");
        preorder(root.left);
        preorder(root.right);
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
                //meaning we already have hit this node
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
            }
        }
        //element not found
        if (current == null) return false;

        if (current.left == null){
            if (parent == null){
                root = current.right;
                root.parent = null;
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

            if (parentOfRightmost.left == rightmost){
                parentOfRightmost.left = rightmost.left;
            }else{
                parentOfRightmost.right = rightmost.left;
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
        private int current= 0;

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
        protected TreeNode<E> parent;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e){
            element = e;
        }
    }
}
