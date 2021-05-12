package CTCI.arrays;

import sun.nio.cs.ext.MacHebrew;

import java.util.stream.Stream;

public class Arrays {



    static boolean oneEditAway(String first, String second){
        if (first.length() == second.length()){
            return oneEditReplace(first, second);
        }else if (first.length()+1 == second.length()){
            return oneEditInsert(first, second);
        }else if (first.length() == second.length()+1){
            return oneEditInsert(first, second);
        }
        return false;
    }

    static boolean oneEditInsert(String first, String second){
        int idx1 = 0;
        int idx2 = 0;

        while (idx1<first.length() && idx2 < second.length()){
            if (first.charAt(idx1) == second.charAt(idx2)){
                idx1++;
            }else{
                if (idx1 != idx2){
                    return false;
                }
            }
            idx2++;
        }
        return true;
    }

    static boolean oneEditReplace(String first, String second){
        boolean changeFound = false;
        for (int i=0; i<first.length(); i++){
            if (first.charAt(i) != second.charAt(i)){
                if (changeFound){
                    return false;
                }
                changeFound = true;
            }
        }
        return true;
    }

    static void setZeros(int[][] matrix){
        boolean[] rows = new boolean[matrix.length];
        boolean[] cols = new boolean[matrix[0].length];

        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[0].length; j++){
                if (matrix[i][j] == 0){
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        for (int i=0; i<rows.length; i++){
            if (rows[i]) nullifyRow(matrix, i);
        }

        for (int j=0; j< cols.length; j++){
            if (cols[j]) nullifyCol(matrix, j);
        }
    }

    static void nullifyRow(int[][] matrix, int row){
        for (int col=0; col<matrix[0].length; col++){
            matrix[row][col] = 0;
        }
    }

    static boolean isRotation(String s1, String s2){
        if (s1.length() != s2.length()) return false;
        String s1s1 = s1+s1;
        return isSubstring(s1s1, s2);
    }

    static boolean isSubstring(String longer, String shorter){
        return false;
    }


    static void nullifyCol(int[][] matrix, int col){
        for (int row=0; row<matrix.length; row++){
            matrix[row][col] = 0;
        }
    }




    public static void main(String[] args) {
        int[][] d1 = {{1,2,3},{4,5,6}, {7,8,9}};

        rotate(d1);

        for (int i=0; i<d1.length; i++){
            for (int j=0;j<d1[i].length; j++){
                System.out.print(d1[i][j]+" ");
            }
            System.out.println();
        }
    }

    static boolean rotate(int[][] matrix){
        if (matrix.length == 0 || matrix.length != matrix[0].length) return false;

        int n = matrix.length;

        for (int layer = 0; layer<n/2; layer++){
            int first = layer;
            int last = n-(layer+1);

            for (int i=first; i<last; i++){
                int offset = i-first;
                int top = matrix[first][i];

                matrix[first][i] = matrix[last-offset][first];

                matrix[last-offset][first] = matrix[last][last-offset];

                matrix[last][last-offset] = matrix[i][last];

                matrix[i][last] = top;

            }
        }
        return true;
    }



    static String compress(String str){
        String compressed = "";
        int count = 0;
        for (int i=0; i<str.length(); i++){
            count++;

            if (i == str.length()-1 || str.charAt(i) != str.charAt(i+1)){
                compressed += ""+str.charAt(i)+count;
                count = 0;
            }
        }
        return compressed;
    }

    static boolean oneEditAway2(String first, String second){
        if (Math.abs(first.length()-second.length()) > 1){
            return false;
        }

        int idx1= 0;
        int idx2=0;

        boolean foundDifference = false;

        while (idx1 < first.length() && idx2<second.length()){
            if (first.charAt(idx1) == second.charAt(idx2)){
                idx1++;
            }else{
                if (foundDifference) return false;
                foundDifference = true;

                //for handling the replacement

                if (idx1 != idx2) return false;

            }
            idx2++;
        }
        return true;
    }



    static boolean isPermutationOfPalindrome(String phrase){
        int[] table = buildCharFrequencyTable(phrase);
        return checkMaxOneOdd(table);
    }


    static boolean checkMaxOneOdd(int[] table){
        boolean foundOdd = false;
        for (int count: table){
            if (count % 2 == 1){
                if (foundOdd){
                    return false;
                }
                foundOdd = true;
            }
        }
        return true;
    }

    static int[] buildCharFrequencyTable(String phrase){
        int[] table = new int[Character.getNumericValue('z')-Character.getNumericValue('a')+1];
        for (char c: phrase.toCharArray()){
            int x = getCharNumber(c);
            if (x != -1){
                table[x]++;
            }
        }
        return table;
    }

    static int getCharNumber(Character c){
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');

        int val = Character.getNumericValue(c);

        if (a <= val && val <= z){
            return val - a;
        }
        return -1;
    }





    public static char[] urlifier(String string, int trueLength){
        char[] charArray = new char[string.length()];

        int arrayIndex = string.length()-1;

        for (int i=trueLength-1; i>=0; i--){
            if (string.charAt(i) != ' '){
                charArray[arrayIndex] = string.charAt(i);
                arrayIndex--;

            }else if (string.charAt(i) == ' '){
                charArray[arrayIndex] = '0';
                charArray[arrayIndex-1] = '2';
                charArray[arrayIndex-2] = '%';

                arrayIndex -= 3;
            }
        }
        return charArray;
    }

//    public static char[] urlifier(String input, int size){
//        char[] charArray = new char[input.length()];
//
//        int diff = input.length()-size;
//        //diff => 18-13 = 5
//
//        for (int i = charArray.length-1; i>=0; i--){
//            if (i-diff>=0){
//                if (input.charAt(i-diff) == ' '){
//                    charArray[i] = '0';
//                    charArray[i-1] = '2';
//                    charArray[i-2] = '%';
//
//                    i -= 2;
//                    diff -=2;
//                }else{
//                    charArray[i] = input.charAt(i-diff);
//                }
//            }
//        }
//        return charArray;
//
//    }

    static void printArray(char[] array){
        for (int i=0; i<array.length; i++){
            System.out.print(array[i]+" ");
        }
    }








    static void replaceSpaces(char[] str, int trueLength){

        int spaceCount = 0, index, i=0;
        for (i=0; i<str.length; i++){
            if (str[i] == ' '){
                spaceCount++;
            }
        }

        index = trueLength + spaceCount*2;

        //if (trueLength < str.length) str[trueLength] = '\0';

        for (i=trueLength-1; i>=0; i--){
            if (str[i] != ' '){
                str[index-1] = str[i];
                index--;
            }else if (str[i] == ' '){
                str[index-1] = '0';
                str[index-2] = '2';
                str[index-3] = '%';

                index = index- 3;
            }
        }

    }


    public static boolean isUnique(String string){
        if (string.length() > 128) return false;
        boolean[] charSets = new boolean[128];

        for (int i=0; i<string.length(); i++){
            if (charSets[string.charAt(i)]){
                return false;
            }
            charSets[string.charAt(i)] = true;
        }
        return true;
    }
}
