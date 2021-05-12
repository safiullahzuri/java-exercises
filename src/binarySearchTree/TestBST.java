package binarySearchTree;

import java.util.ArrayList;

public class TestBST {

    public static void main(String[] args) {


        Integer[] numbers = {45,12,47,10,23,13,47,56};
        BST<Integer> intTree = new BST<>(numbers);

//        intTree.inorder();
//
//        System.out.println("\nBreadth first traversal");
//        intTree.breadthFirstTraversal();

        System.out.println("nr preorder: ");
        intTree.nrPreorder();

    }

}
