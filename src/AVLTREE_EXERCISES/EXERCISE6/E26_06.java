package AVLTREE_EXERCISES.EXERCISE6;

public class E26_06 {

    public static void main(String[] args) {
        Integer[] list = {0, -20, 5, 2, 4, 10, 51, -34, 8, 102, 5};

        AVLTree<Integer> tree1 = new AVLTree<>(list);
        System.out.println("print tree");
        tree1.inorder();

    }

}
