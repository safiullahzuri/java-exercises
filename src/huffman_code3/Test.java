package huffman_code3;

public class Test {

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>();
        int[] numbers = {2,1,4,5,3,23,5,4342,123};

        for (int i=0; i<numbers.length; i++){
            heap.add(numbers[i]);
        }

        while (heap.size() > 0){
            System.out.println(heap.remove());
        }

    }

}
