package binarySearchTree;

import java.util.*;

public class BST<E extends Comparable<E>> implements Tree<E> {

    protected TreeNode<E> root;
    protected int size = 0;
    private Comparator<? super E> comparator;

    public BST(E[] objects, Comparator<E> comparator){
        this.comparator = comparator;
        for (int i=0; i<objects.length; i++){
            add(objects[i]);
        }
    }

    private List<TreeNode<E>> nodes = new ArrayList<>();

    public BST(){}
    public BST(Comparator<E> comparator){
        this.comparator = comparator;
    }

    public BST(E[] objects){
        for (int i=0; i<objects.length; i++){
            add(objects[i]);
        }
    }

    public void nrPreorder(){
        nrPreorder(root);
    }
    public void nrPreorder(TreeNode<E> root){
        if (root == null)return;
        Stack<TreeNode<E>> stack = new Stack<>();
        stack.push(root);

        TreeNode<E> current = root;

        while (!stack.isEmpty()){
            while (current != null){
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            System.out.println(current.element);
            current = current.right;

        }

    }


    @Override
    public boolean search(E e) {
        TreeNode<E> current = root;
        while (current != null){
            if (comparator != null){
                if (e.compareTo(current.element) > 0){
                    current = current.right;
                }else if (e.compareTo(current.element) < 0){
                    current = current.left;
                }else{
                    return true;
                }
            }else{
                if (comparator.compare(e, current.element) > 0){
                    current = current.right;
                }else if (comparator.compare(e, current.element) < 0){
                    current = current.left;
                }else{
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean insert(E e) {
        if (root == null){
            root = createNewNode(e, null);
            nodes.add(root);
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
                TreeNode<E> newNode = createNewNode(e, parent);
                parent.right = newNode;
                nodes.add(newNode);
            }else{
                TreeNode<E> newNode = createNewNode(e, parent);
                parent.left = newNode;
                nodes.add(newNode);
            }
        }
        size++;
        return true;
    }

    protected TreeNode<E> createNewNode(E e, TreeNode<E> parent){
        return new TreeNode<>(e);
    }

    @Override
    public void inorder() {
        inorder(root);
    }

    @Override
    public void postorder(){
        postorder(root);
    }

    @Override
    public void preorder() {
        preorder(root);
    }
    private void preorder(TreeNode<E> root){
        if (root == null) return;
        System.out.println(root.element+" ");
        preorder(root.left);
        preorder(root.right);
    }

    private void postorder(TreeNode<E> root){
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.element+" ");
    }

    //root here means node to traverse
    private void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.println(root.element+" ");
        inorder(root.right);
    }

    @Override
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root; // the node to be deleted

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
                //if we are deleting the root node
                root = current.right;
            }else{
                if (e.compareTo(parent.element) < 0){
                    parent.left = current.right;
                }else{
                    parent.right = current.right;
                }
            }
        }else{
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null){
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
        size--;
        return true;
    }

    @Override
    public int getSize() {
        return size;
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
            System.out.println(root.element+" ");
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
            if (current == 0) throw new IllegalStateException();
            delete(list.get(--current));
            list.clear();
            inorder();
        }
    }


    public TreeNode<E> getRoot(){
        return root;
    }

    public ArrayList<TreeNode<E>> path(E e){
        ArrayList<TreeNode<E>> path = new ArrayList<>();
        TreeNode<E> current = root;

        while (current != null){
            path.add(current);
            if (e.compareTo(current.element) > 0){
                current = current.right;
            }else if (e.compareTo(current.element) < 0){
                current = current.left;
            }else{
                //we have reached here
                break;
            }
        }
        return path;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    public void breadthFirstTraversal(){
        TreeNode<E> current = root;
        ArrayList<TreeNode<E>> nextLevel = new ArrayList<>();

        nextLevel.add(current);

        while (!nextLevel.isEmpty()){
            System.out.println(nextLevel.get(0).element);
            if (nextLevel.get(0).left != null){
                nextLevel.add(nextLevel.get(0).left);
            }
            if (nextLevel.get(0).right != null){
                nextLevel.add(nextLevel.get(0).right);
            }
            nextLevel.remove(nextLevel.get(0));
        }
    }




    public int height(){
        return height(root);
    }

    private int height(TreeNode<E> root){
        if (root == null) return -1;
        else
            return 1+Math.max(height(root.left), height(root.right));
    }

    public boolean isFullBST(){
        return Math.pow(2, height()+1)-1 == size;
    }

    public void inorder2(){
        if (root == null)return;
        Stack<TreeNode<E>> stack = new Stack<>();

        TreeNode<E> current = root;

        while (true){
            if (current != null){
                stack.push(current);
                current = current.left;
            }else{
                if (stack.isEmpty()){break;}
                current = stack.pop();
                System.out.print(current.element+" ");
                current = current.right;
            }
        }
    }

    public void preorder2(){
        if (root == null) return;
        Stack<TreeNode<E>> stack = new Stack<>();

        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode<E> node = stack.pop();
            System.out.print(node.element+" ");
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }

    }





    public int findLeaves(){
        int leaves = 0;
        for (TreeNode<E> node: nodes){
            if (node.left == null && node.right == null){
                leaves++;
            }
        }
        return leaves;
    }


    //static treeNode class
    public static class TreeNode<E>{

        public E element;
        public TreeNode<E> parent;
        public TreeNode<E> left;
        public TreeNode<E> right;

        protected boolean visited = false;

        public TreeNode(E e){
            element = e;
        }
    }
    public Iterator<E> myInorderIterator(){
        return new MyInorderIterator();
    }




    private class MyInorderIterator implements Iterator<E>{

        private ArrayList<E> list = new ArrayList<>();
        private int current = 0;

        public MyInorderIterator(){
            iterate(root);
        }

        private void iterate(TreeNode<E> root){
            if (root == null) return;
            iterate(root.left);
            list.add(root.element);
            iterate(root.right);
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

    public Iterator<E> myPreorderIterator(){
        return new MyPreorderIterator();
    }

    public class MyPreorderIterator implements Iterator<E>{
        private ArrayList<E> list = new ArrayList<>();
        private int current = 0;

        public MyPreorderIterator(){
            preIterator(root);
        }
        private void preIterator(TreeNode<E> root){
            if (root == null) return;
            list.add(root.element);
            preIterator(root.left);
            preIterator(root.right);
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

    public Iterator<E> postOrderIterator(){
        return new PostOrderIterator();
    }

    public class PostOrderIterator implements Iterator<E>{

        private ArrayList<E> list = new ArrayList<>();
        private int current = 0;

        public PostOrderIterator(){
            postIterate(root);
        }

        private void postIterate(TreeNode<E> root){
            if (root == null) return;
            postIterate(root.left);
            postIterate(root.right);
            list.add(root.element);
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




}
