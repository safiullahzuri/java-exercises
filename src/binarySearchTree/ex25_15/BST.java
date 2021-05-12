package binarySearchTree.ex25_15;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.*;

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
            root = createNewNode(e, null);
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


    private TreeNode<E> createNewNode(E e, TreeNode<E> parent){
        TreeNode<E> node = new TreeNode<>(e);
        node.parent = parent;
        return node;
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
        return current;
    }
    private boolean isLeaf(E element){
        TreeNode<E> node = getNode(element);
        if (node == null) return false;
        return node.left == null && node.right == null;
    }

    public ArrayList<E> getPath(E e){
        ArrayList<E> list = new ArrayList<>();
        TreeNode<E> node = getNode(e);

        while (node != null){
            list.add(node.element);
            node =node.parent;
        }
        return list;
    }

    public void breadthFirst(){
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            TreeNode<E> node = queue.poll();
            System.out.println(node.element);
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
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    @Override
    public void postorder() {
        postorder(root);
    }

    protected void postorder(TreeNode<E> root){
        if (root == null) return;
        postorder(root.left);
        System.out.print(root.element+" ");
        postorder(root.right);
    }

    @Override
    public void preorder() {
        preorder(root);
    }

    protected void preorder(TreeNode<E> root){
        if (root == null) return;
        System.out.print(root.element+" ");
        preorder(root.left);
        preorder(root.right);
    }

    @Override
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        while (current != null){
            if (e.compareTo(current.element) < 0){
                parent = current;
                current=  current.left;
            }else if (e.compareTo(current.element) > 0){
                parent = current;
                current = current.right;
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
                root.parent = null;
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

    public void nrPreorder(){
        nrPreorder(root);
    }
    public void nrPreorder(TreeNode<E> root){
        if (root == null)return;
        Stack<TreeNode<E>> stack = new Stack<>();
        stack.push(root);

        TreeNode<E> current = root;

        while (!stack.isEmpty()){
            if (current != null){
                stack.push(current);
                current = current.left;
            }else{
                TreeNode<E> node = stack.pop();
                System.out.println(node.element);
                current = current.right;
            }
        }

    }

    @Override
    public int getSize() {
        return size;
    }

    public TreeNode<E> getRoot(){
        return root;
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
    public Iterator<E> iterator() {
        return null;
    }


    public static class TreeNode<E extends Comparable<E>>{
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;
        protected TreeNode<E> parent;

        public TreeNode(E e){
            element = e;
        }
    }
}
