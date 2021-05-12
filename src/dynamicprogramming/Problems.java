package dynamicprogramming;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Problems {

    public static void main(String[] args) {

        //System.out.println("abdefg".substring(3));
        //System.out.println(canConstruct("abcdefg", new String[]{"ab", "abc","cde","fg"}));
        //System.out.println(countConstruct("abcdefg", new String[]{"ab", "abc","cde","de","fg"}, 0));

        for (int i=0; i<10; i++){
            System.out.println(fib(i));
        }
    }

    public static int fib(int n){
        int[] array = new int[n+1];
        if (n ==0) return 0;
        if (n == 1) return 1;
        array[0] = 0;
        array[1] = 1;
        for(int i=2; i<array.length; i++){
            array[i] = array[i-1] + array[i-2];
        }
        return array[n];
    }

    public static boolean canConstruct(String target, String[] array){
        HashMap<String, Boolean> memo = new HashMap<>();
        return canConstruct(target, array, memo);
    }

    public static int countConstruct(String target, String[] wordBank, int result){
        if (target.equals("")){
            return ++result;
        }
        for (String word: wordBank){
            if (word.length() > target.length() || !target.substring(0, word.length()).equals(word)){
                continue;
            }

            result = countConstruct(target.substring(word.length()), wordBank, result);

        }
        return result;
    }

    public static boolean canConstruct(String target, String[] array, HashMap<String, Boolean> memo){
        if (memo.containsKey(target)){
            return memo.get(target);
        }
        if (target.equals("")) return true;

        for (String word: array){

            if (word.length()>target.length() || !target.substring(0, word.length()).equals(word)){
                continue;
            }

            boolean canConstruct = canConstruct(target.substring(word.length()), array);
            if (canConstruct){
                memo.put(target.substring(word.length()), true);
                return true;
            }
        }

        return false;
    }

    public static boolean canSum(int target, int[] numbers ){
        HashMap<Integer, Boolean> map = new HashMap<>();
        return canSum(target, numbers, map);
    }

    public static List<Integer> howSum(int target, int[] numbers){
        return howSum(target, numbers, new ArrayList<Integer>());
    }

    public static List<Integer> howSum(int target, int[] numbers, ArrayList<Integer> list){
        if (target == 0) return list;
        if (target < 0) return null;

        for (int num: numbers){
            int remainder =  target-num;

            List<Integer> remainderResult = howSum(remainder, numbers);

            if (remainderResult != null){
                list.add(num);
                return list;
            }
        }
        return null;
    }




    public static boolean canSum(int target, int[] numbers, HashMap<Integer, Boolean> map){
        if (map.containsKey(target)){
            if (map.get(target)){
                return true;
            }
        }
        if (target == 0 ) return true;
        if (target < 0) return false;
        for (int num: numbers){
            boolean result = canSum(target-num, numbers);

            if (result){
                map.put(target-num, true);
                return true;
            }
        }
        return false;
    }

}
