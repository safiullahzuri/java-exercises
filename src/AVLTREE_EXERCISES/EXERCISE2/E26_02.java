package AVLTREE_EXERCISES.EXERCISE2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class E26_02 {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = getNumbers(100000);
        BST<Integer> normalTree = new BST<>();

        long start = System.currentTimeMillis();
        for (Integer num: numbers){
            normalTree.insert(num);
        }
        Collections.shuffle(numbers);
        for (Integer num: numbers) {
            normalTree.search(num);
        }

        Collections.shuffle(numbers);

        for (Integer num: numbers){
            normalTree.delete(num);
        }

        long end = System.currentTimeMillis();

        System.out.println("BST: "+(end-start)+" ms");

        Collections.shuffle(numbers);

        AVLTree<Integer> avlTree = new AVLTree<>();
        start = System.currentTimeMillis();
        for (Integer num: numbers){
            avlTree.insert(num);
        }

        Collections.shuffle(numbers);
        for (Integer num: numbers){
            avlTree.search(num);
        }

        Collections.shuffle(numbers);
        for (Integer num: numbers){
            avlTree.delete(num);
        }

        end = System.currentTimeMillis();

        System.out.println("AVL: "+(end-start)+ "ms");

    }


    public static ArrayList<Integer> getNumbers(int n){
        HashSet<Integer> numbers = new HashSet<>();
        while (numbers.size() < n){
            numbers.add((int)(Math.random()*n*8));
        }
        return new ArrayList<>(numbers);
    }

}
