package AVLTree;

import java.util.ArrayList;
import java.util.Iterator;

public class Ex26_06 {

    public static void main(String[] args) {
        AVLTree<Integer> tree1 = new AVLTree<Integer>();

        System.out.print(
                "inorder() on empty tree created with no-arg constructor: ");
        tree1.inorder();
        System.out.println();

        Integer[] list = {0, -20, 5, 2, 4, 10, 51, -34, 8, 102, 5};
        AVLTree<Integer> tree2 = new AVLTree<>(list);

        System.out.print(
                "inorder() on tree created with array-arg constructor: ");
        tree2.inorder();
        System.out.println();

        tree2.insert(20);
        System.out.print("insert(20): ");
        tree2.inorder();
        System.out.println();

        System.out.println("delete(20): " + tree2.delete(20));
        tree2.inorder();
        System.out.println();

        System.out.println("search(20): " + tree2.search(20));
        System.out.println("search(-34): " + tree2.search(-34));

        System.out.print("postorder(): ");
        tree2.postorder();
        System.out.println();

        System.out.print("preorder(): ");
        tree2.preorder();
        System.out.println();

        System.out.println("getSize(): " + tree2.getSize());
        //System.out.println("getRoot().element: " + tree2.getRoot().element);

        System.out.print("path(102): ");
        ArrayList<BST.TreeNode<Integer>> path = tree2.path(102);
        for (BST.TreeNode t: path) {
            System.out.print(t.element + " ");
        }
        System.out.println();

        System.out.print("iterator(): ");
        Iterator<Integer> iter = tree2.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();

        System.out.println("isEmpty(): " + tree2.isEmpty());
        tree2.clear();
        System.out.print("clear() followed by inorder(): ");
        tree2.inorder();
        System.out.println();
        System.out.println("isEmpty(): " + tree2.isEmpty());
    }

}
