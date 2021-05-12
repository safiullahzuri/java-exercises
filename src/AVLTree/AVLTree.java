package AVLTree;

import com.sun.deploy.panel.ExceptionListDialog;

import java.util.ArrayList;

public class AVLTree<E extends Comparable<E>> extends BST<E> {

    public AVLTree(){}

    public AVLTree(E[] objects){
        super(objects);
    }


    @Override
    public TreeNode<E> createNewNode(E element) {
        return new AVLTreeNode<E>(element);
    }

    @Override
    public boolean insert(E e) {
        boolean successful = super.insert(e);
        if (!successful){
            return false;
        }else{
            balancePath(e);
        }
        return true;
    }

    private void updateHeight(AVLTreeNode<E> node){
        if (node.left == null && node.right == null){
            node.height = 0;
        }else if (node.left == null){
            node.height = 1 + ((AVLTreeNode<E>) node.right).height;
        }else if (node.right == null){
            node.height = 1 + ((AVLTreeNode<E>) node.left).height;
        }else{
            node.height = 1 + Math.max(((AVLTreeNode<E>)(node.right)).height, ((AVLTreeNode<E>)(node.left)).height);
        }
    }

    private void balancePath(E element){
        ArrayList<TreeNode<E>> path = path(element);

        for (int i=path.size()-1; i>=0; i--){
            AVLTreeNode<E> A = (AVLTreeNode<E>)(path.get(i));
            updateHeight(A);

            AVLTreeNode<E> parentOfA = (A == root) ? null: (AVLTreeNode<E>)(path.get(i-1));

            switch (balanceFactor(A)){
                case -2:
                    if (balanceFactor((AVLTreeNode<E>)A.right) >= 0){
                        balanceLL(A, parentOfA);
                    }else{
                        balanceLR(A, parentOfA);
                    }
                    break;
                case +2:
                    if (balanceFactor((AVLTreeNode<E>)A.right) >= 0){
                        balanceRR(A, parentOfA);
                    }else{
                        balanceRL(A, parentOfA);
                    }
            }

        }

    }

    private void balanceLL(TreeNode<E> A, TreeNode<E> parenOfA){
        TreeNode<E> B = A.left;

        if (A == root){
            root = B;
        }else{
            if (parenOfA.left == A){
                parenOfA.left = B;
            }else{
                parenOfA.right = B;
            }
        }
        A.left = B.right;
        B.right = A;
        updateHeight((AVLTreeNode<E>)A);
        updateHeight((AVLTreeNode<E>)B);
    }


    private void balanceLR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.left;
        TreeNode<E> C = B.right;

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }

        A.left = C.right;
        B.right = C.left;
        C.left = B;
        C.right = A;

        updateHeight((AVLTreeNode<E>)A);
        updateHeight((AVLTreeNode<E>)B);
        updateHeight((AVLTreeNode<E>)C);
    }

    private void balanceRR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.right;

        if (A == root) {
            root = B;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = B;
            } else {
                parentOfA.right = B;
            }
        }

        A.right = B.left;
        B.left = A;
        updateHeight((AVLTreeNode<E>)A);
        updateHeight((AVLTreeNode<E>)B);
    }

    private void balanceRL(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.right;
        TreeNode<E> C = B.left;

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }

        A.right = C.left;
        B.left = C.right;
        C.left = A;
        C.right = B;

        updateHeight((AVLTreeNode<E>)A);
        updateHeight((AVLTreeNode<E>)B);
        updateHeight((AVLTreeNode<E>)C);
    }

    @Override
    public boolean delete(E element) {
        if (root == null) {
            return false;
        }

        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (element.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (element.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break;
            }
        }

        if (current == null) {
            return false;
        }

        if (current.left == null) {
            if (parent == null) {
                root = current.right;
            } else {
                if (element.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
                balancePath(parent.element);
            }
        } else {
            TreeNode<E> parentOfRightmost = current;
            TreeNode<E> rightmost = current.left;

            while (rightmost.right != null) {
                parentOfRightmost = rightmost;
                rightmost = rightmost.right;
            }

            current.element = rightmost.element;

            if (parentOfRightmost.right == rightmost) {
                parentOfRightmost.right = rightmost.left;
            } else {
                parentOfRightmost.left = rightmost.left;
            }
            balancePath(parentOfRightmost.element);
        }

        size--;
        return true;
    }


    private int balanceFactor(AVLTreeNode<E> node){
        if (node.right == null){
            return -node.height;
        }else if (node.left == null){
            return node.height;
        }else{
            return ((AVLTreeNode<E>)node.right).height -
                    ((AVLTreeNode<E>)node.left).height;
        }
    }











    protected static class AVLTreeNode<E extends Comparable<E>> extends BST.TreeNode<E>{

        protected int height = 0;

        public AVLTreeNode(E e) {
            super(e);
        }
    }










}
