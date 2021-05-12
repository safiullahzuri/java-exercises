package AVLTREE_EXERCISES.EXERCISE4;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;

public class E26_04 {

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        for (int i=1; i<=100; i++){
            tree.insert(i);
        }

        for (Integer i: tree){
            if (tree.isLeaf(i) ){
                System.out.print(i+" ");
                printPath(tree.getPath(i));
            }
        }
    }

    public static void printPath(ArrayList<Integer> list){
        for (Integer i: list){
            System.out.print(i+" ");
        }
        System.out.println();
    }

}
