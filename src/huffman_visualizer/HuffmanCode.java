package huffman_visualizer;

public class HuffmanCode {


    public static String[] getCode(Tree.Node root){
        if (root == null) return null;
        String[] codes = new String[2 * 128];
        assignCode(root, codes);
        return codes;
    }


    public static void assignCode(Tree.Node root, String[] codes){
        if (root.left != null){
            root.left.code = root.code+"0";
            assignCode(root.left, codes);

            root.right.code = root.code+"1";
            assignCode(root.right, codes);
        }else{
            codes[(int) root.element] = root.code;
        }
    }


    public static int[] getCharactersFrequency(String text){
        int[] counts = new int[256];

        for (int i=0; i<text.length(); i++){
            counts[(int) text.charAt(i)]++;
        }
        return counts;
    }

    public static Tree getHuffmanTree(int[] counts){
        Heap<Tree> forest = new Heap<>();
        for (int i=0; i<counts.length; i++){
            if (counts[i] > 0){
                forest.add(new Tree(counts[i],(char) i));
            }
        }

        while (forest.getSize() > 1){
            Tree t1 = forest.remove();
            Tree t2 = forest.remove();

            forest.add(new Tree(t1, t2));
        }
        return forest.remove();
    }





    public static class Tree implements Comparable<Tree>{
        Node root;

        public Tree(int weight, char element){
            root = new Node(weight, element);
        }

        public Tree(Tree t1, Tree t2){
            root = new Node();
            root.left = t1.root;
            root.right = t2.root;
            root.weight = t1.root.weight + t2.root.weight;
        }

        @Override
        public int compareTo(Tree t) {
            if (this.root.weight > t.root.weight){
                return -1;
            }else if (this.root.weight < t.root.weight){
                return 1;
            }else{
                return 0;
            }
        }


        public class Node{
            int weight;
            char element;
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
