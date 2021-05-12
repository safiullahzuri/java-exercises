package ex25_18;

public class HuffmanCode {

    public static int[] getFrequencyCount(String text){
        int[] counts = new int[256];
        for (int i=0; i<text.length(); i++){
            counts[text.charAt(i)]++;
        }
        return counts;
    }

    public static Tree getHuffmanTree(int[] counts){
        Heap<Tree> heap = new Heap<>();
        for (int i=0; i<counts.length; i++){
            if (counts[i] != 0){
                heap.add(new Tree(counts[i], (char) i));
            }
        }

        while (heap.size() > 1){
            Tree t1 = heap.remove();
            Tree t2 = heap.remove();

            heap.add(new Tree(t1, t2));
        }
        return heap.remove();
    }

    public static String[] getCode(Tree.Node root){
        if (root == null) return null;
        String[] codes = new String[2*128];
        assignCodes(root, codes);
        return codes;
    }

    private static void assignCodes(Tree.Node root, String[] codes){
        if (root.left != null){
            root.left.code = root.code+"0";
            assignCodes(root.left, codes);

            root.right.code = root.code+"1";
            assignCodes(root.right, codes);
        }else{
            codes[root.element] = root.code;
        }
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
            String code = "";
            Node left;
            Node right;

            public Node(){}
            public Node(int weight, char element){
                this.weight = weight;
                this.element = element;
            }

        }
    }
}
