package binarySearchTree.ex25_08_to_10;

import java.util.ListIterator;

public class E25_08 {
    public static void main(String[] args) {
        String[] list = {"George", "Ben", "Mark", "Adam", "Carl", "Mack", "Nick"};
        BST<String> tree = new BST<>(list);

        ListIterator<String> iter = tree.listIterator();
        for (int i = 0; i < 6; i++) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();

        while (iter.hasPrevious()) {
            System.out.print(iter.previous() + " ");
        }
        System.out.println();
    }
}
