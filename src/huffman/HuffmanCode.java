package huffman;

import java.util.Scanner;

public class HuffmanCode {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = input.nextLine();

        int[] counts = getCharacterFrequency(text);

        System.out.printf("%-15s%-15s%-15s%-15s\n",
                "ASCII Code", "Character", "Frequency", "Code");

        Tree tree = getHuffmanTree(counts);
        String[] codes = getCode(tree.root);

        for (int i = 0; i < codes.length; i++) {
            if (counts[i] != 0) {
                System.out.printf("%-15d%-15s%-15d%-15s\n",
                        i, (char)i + "", counts[i], codes[i]);
            }
        }
    }

    public static String[] getCode(Tree.Node root){
        if (root == null) return null;
        String[] codes = new String[2 * 128];
        assignCode(root, codes);
        return codes;
    }

    private static void assignCode(Tree.Node node, String[] codes){
        if (node.left != null){
            node.left.code = node.code+"0";
            assignCode(node.left, codes);

            node.right.code = node.code+"1";
            assignCode(node.right, codes);
        }else{
            codes[node.element] = node.code;
        }
    }

    public static Tree getHuffmanTree(int[] counts){
        Heap<Tree> treeHeap = new Heap<>();

        for (int i=0; i<counts.length; i++){
            if (counts[i] > 0){
                treeHeap.add(new Tree(counts[i], (char) i));
            }
        }

        while (treeHeap.getSize() > 1){
            Tree t1 = treeHeap.remove();
            Tree t2 = treeHeap.remove();

            treeHeap.add(new Tree(t1, t2));
        }
        return treeHeap.remove();
    }


    public static int[] getCharacterFrequency(String text){
        int[] counts = new int[256];

        for (int i=0; i<text.length(); i++){
            if (counts[i] != 0){
                counts[text.charAt(i)]++;
            }
        }
        return counts;
    }






    public static class Tree implements Comparable<Tree>{
        Node root;

        public Tree(Tree t1, Tree t2){
            root = new Node();
            root.left = t1.root;
            root.right = t2.root;
            root.weight = t1.root.weight + t2.root.weight;
        }

        public Tree(int weight, char element){
            root = new Node(weight, element);
        }


        @Override
        public int compareTo(Tree tree) {
            if (root.weight > tree.root.weight){
                return -1;
            }else if (root.weight < tree.root.weight){
                return 1;
            }else{
                return 0;
            }
        }

        public class Node{
            char element;
            int weight;
            Node left;
            Node right;
            String code = "";

            public Node(){}
            public Node(int weight, char element){
                this.weight = weight;
                this.element = element;
            }
        }
    }
}
