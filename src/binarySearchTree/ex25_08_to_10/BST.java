package binarySearchTree.ex25_08_to_10;

import java.util.*;

public class BST<E extends Comparable<E>> extends AbstractTree<E> implements Cloneable {

    private TreeNode<E> root;
    private int size = 0;

    public BST(){}

    public BST(E[] objects){
        for (int i=0; i<objects.length; i++){
            insert(objects[i]);
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        BST<E> copy = new BST<>();
        Iterator<E> iterator = new BreadthFirstIterator();
        while (iterator.hasNext()){
            copy.insert(iterator.next());
        }
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if ( o == this) return true;
        BST<E> comparedTree = (BST<E>) o;
        ArrayList<E> first = this.inorderlist();
        ArrayList<E> second = comparedTree.inorderlist();

        return first.equals(second);

    }

    public ArrayList<E> inorderlist(){
        ArrayList<E> list = new ArrayList<>();
        inorderList(list, root);
        return list;
    }

    private void inorderList(ArrayList<E> list, TreeNode<E> node){
        if (node == null) return;
        inorderList(list, node.left);
        list.add(node.element);
        inorderList(list, node.right);
    }


    @Override
    public int hashCode() {
        return size;
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
        //we got the element to be deleted stored in the current variable

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
            //if the current element has a left node
            TreeNode<E> rightMost = current.left;
            TreeNode<E> rightMostParent = current;

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
    public boolean insert(E e) {
        if (root == null){
            root = createNewNode(e);
        }else{
            TreeNode<E> futureParent = null;
            TreeNode<E> current = root;
            while (current != null){
                if (e.compareTo(current.element) > 0){
                    futureParent = current;
                    current = current.right;
                }else if (e.compareTo(current.element) < 0){
                    futureParent = current;
                    current = current.left;
                }else{
                    //duplicate entry
                    return false;
                }
            }
            if (e.compareTo(futureParent.element) > 0){
                futureParent.right = createNewNode(e);
            }else{
                futureParent.left = createNewNode(e);
            }
        }
        size++;
        return true;
    }

    private TreeNode<E> createNewNode(E e){
        return new TreeNode(e);
    }

    @Override
    public void inorder() {
        inorder(root);
    }

    @Override
    public void preorder() {
        preorder(root);
    }

    @Override
    public void postorder() {
        postorder(root);
    }

    private void postorder(TreeNode<E> root){
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.element);
    }

    private void preorder(TreeNode<E> root){
        if (root == null) return;
        System.out.println(root.element);
        preorder(root.left);
        preorder(root.right);
    }

    private void inorder(TreeNode<E> root){
        if (root == null) return;
        inorder(root.left);
        System.out.println(root.element);
        inorder(root.right);
    }

    @Override
    public int getSize() {
        return size;
    }

    public ArrayList<TreeNode<E>> path(E e){
        ArrayList<TreeNode<E>> path = new ArrayList<>();
        TreeNode<E> current = root;
        while (current != null){
            if (e.compareTo(current.element) > 0){
                path.add(current);
                current = current.right;
            }else if (e.compareTo(current.element) < 0){
                path.add(current);
                current = current.left;
            }else{
                break;
            }
        }
        return path;
    }

    public static class TreeNode<E extends Comparable<E>>{
        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(){}
        public TreeNode(E e){
            element = e;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<E>{
        private ArrayList<E> list = new ArrayList<>();
        private int current = 0;

        public InOrderIterator(){
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
            }
            return false;
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

    public ListIterator<E> listIterator(){
        return new BidirectionalIterator();
    }

    private class BidirectionalIterator implements ListIterator<E>{

        private ArrayList<E> list = new ArrayList<>();
        private int current = 0;

        public BidirectionalIterator(){
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
            return (current < list.size());
        }

        @Override
        public E next() {
            return list.get(current++);
        }

        @Override
        public boolean hasPrevious() {
            return (current >= 0);
        }

        @Override
        public E previous() {
            return list.get(current--);
        }

        @Override
        public int nextIndex() {
            return current;
        }

        @Override
        public int previousIndex() {
            return current;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }
    }

    private class BreadthFirstIterator implements Iterator<E>{
        private ArrayList<E> list = new ArrayList<>();
        private int current = 0;

        public BreadthFirstIterator(){
            breadthFirstIterator();
        }

        private void breadthFirstIterator(){
            if (root == null) return;
            Queue<TreeNode<E>> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()){
                TreeNode<E> node = queue.poll();
                list.add(node.element);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

        }

        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        @Override
        public E next() {
            return list.get(current++);
        }

        public void clear(){
            root = null;
            size = 0;
        }
    }

    public Iterator<E> preorderIterator(){
        return new PreorderIterator();
    }

    public class PreorderIterator implements Iterator<E>{

        private ArrayList<E> list = new ArrayList<>();
        private int current = 0;

        public PreorderIterator(){
            preorder();
        }

        private void preorder(){
            preorder(root);
        }

        private void preorder(TreeNode<E> root){
            if (root == null) return;
            list.add(root.element);
            preorder(root.left);
            preorder(root.right);
        }


        @Override
        public boolean hasNext() {
            return (current < list.size());
        }

        @Override
        public E next() {
            return list.get(current++);
        }

        public void clear(){
            root = null;
            size = 0;
        }
    }


}
