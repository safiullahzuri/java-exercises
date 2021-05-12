package recursion;

import java.util.ArrayList;
import java.util.List;

public class Exercises {

    public static void main(String[] args) {
        //reverseDisplay("abcdefghijklmnopqrs");
        //int count = count("salam", 'a');
        //System.out.println("count "+count);
        //String s = "abcdef";
        //System.out.println(s.substring(1, s.length()));

        //int[] array = {3,1,5,4,3,7,128,57,64};
        //System.out.println(largest(array));

        //System.out.println(sumDigits(987654));
        //reverseDisplay(12345678);

        int upc = uppercases("salaMA".toCharArray());
        System.out.println(upc);

    }

    public static int uppercases(char[] chars){
        return uppercases(chars,0, chars.length-1, 0);
    }

    private static int uppercases(char[] chars,int low, int high, int count){
        if (low<=high){
            if (Character.isUpperCase(chars[low])){
                count++;
            }
            return uppercases(chars,low+1, high, count);
        }
        return count;
    }


    public static void reverseDisplay(int value){
        reverseDisplay(value, "");
    }

    public static void reverseDisplay(int value, String currentNumber){
        if (value > 0){
            reverseDisplay(value/10, currentNumber+value%10);
        }else{
            System.out.println(currentNumber);
        }
    }




    public static int sumDigits(long n){
        return sumDigits(n, 0);
    }

    public static int sumDigits(long n, int currentSum){
        if (n > 0){
            return sumDigits(n/10, (int) (currentSum+ n%10));
        }
        return currentSum;
    }


    public static int largest(int[] array){
        return largest(array, 0 , array.length-1);
    }

    public static int largest(int[] array, int low, int high){
        if (low < high){
            return Math.max(array[low], largest(array, low+1, high));
        }
        return array[low];
    }




    public static boolean areAnagrams(String word1, String word2){
        if (word1.length() != word2.length()){
            return false;
        }
        if (word2.length() > 0 &&  word1.contains(word2.charAt(0)+"")){
            areAnagrams(word1, word2.substring(1, word2.length()));
        }else{
            return false;
        }
        return true;
    }


    public static void reverseDisplay(String s){
        if (s.length() > 0){
            System.out.print(s.charAt(s.length()-1));
            reverseDisplay(s.substring(0, s.length()-1));
        }
    }

    public static int count(String str, char a){
        return count(str, a, 0);
    }

    public static int count(String str, char a, int count){
        if (str.length() > 0){
            int d = 0;
            if (str.charAt(0) == a){
                d = 1;
            }
            return count(str.substring(1, str.length()), a , count+d);
        }
        return count;
    }



}
