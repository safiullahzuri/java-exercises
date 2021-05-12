package algoexpert;

public class Cesar {

    public static void main(String[] args) {
        int[] abc = new int[]{2,4,1,23,53,432,231,64,12};
        bubbleSort(abc);

        for(int i=0; i<abc.length; i++){
            System.out.println(abc[i]);
        }
    }


    public static int[] bubbleSort(int[] array) {
        // Write your code here.
        boolean isSorted = false;

        for(int i=0; i<array.length-1 && !isSorted; i++){
            isSorted = true;
            for(int j=0; j<array.length - (1+i); j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;

                    isSorted = false;
                }
            }
        }

        return array;
    }

    public static String caesarCypherEncryptor(String str, int key) {
        // Write your code here.
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] albet = new char[26];

        for(int i=0; i<alphabet.length(); i++){
            int code = str.charAt(i) + (key % 26);

            if(code > 122){
                code = 96 + (code%122);
            }

            albet[i] = (char) code;
        }

        String mod = "";
        for(int i=0; i<str.length(); i++){
            mod += albet[str.charAt(i) - 'a'];
        }
        return mod;
    }
}
