package algoexpert.medium;

import java.util.*;

public class Test2 {

    public static Map<Character, String[]> digitLettersMap = new HashMap<>();

    static{
        digitLettersMap.put('0', new String[]{"0"});
        digitLettersMap.put('1', new String[]{"1"});
        digitLettersMap.put('2', new String[]{"a", "b", "c"});
        digitLettersMap.put('3', new String[]{"d", "e", "f"});
        digitLettersMap.put('4', new String[]{"g", "h", "i"});
        digitLettersMap.put('5', new String[]{"j", "k", "l"});
        digitLettersMap.put('6', new String[]{"m", "n", "o"});
        digitLettersMap.put('7', new String[]{"p", "q", "r", "s"});
        digitLettersMap.put('8', new String[]{"t", "u", "v"});
        digitLettersMap.put('9', new String[]{"w", "x", "y", "z"});
    }


    public static List<String> getAllMnemonics(String phoneNo){
        List<String> allMnemonics = new ArrayList<>();
        String[] currentMnemonic = new String[phoneNo.length()];
        getAllMnemonicsHelper(0, currentMnemonic, phoneNo, allMnemonics);
        return allMnemonics;
    }

    public static void getAllMnemonicsHelper(int idx, String[] currentMnemonic, String phoneNo, List<String> allMnemonics){
        if (idx == phoneNo.length()){
            String mnemonic = String.join("", currentMnemonic);
            allMnemonics.add(mnemonic);
        }else{
            char digit = phoneNo.charAt(idx);
            for (String letter: digitLettersMap.get(digit)){
                currentMnemonic[idx] = letter;
                getAllMnemonicsHelper(idx+1, currentMnemonic, phoneNo, allMnemonics);
            }
        }
    }


    public static int gridTraveler(int row, int col){
        int[][] grid = new int[row+1][col+1];

        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                grid[i][j] = 0;
            }
        }


        grid[1][1] = 1;

        for (int i=1; i<row+1; i++){
            for (int j=1; j<col+1; j++){

                if (i+1 <= row){
                    grid[i+1][j] += grid[i][j];

                    if (i == 1 && j==1){
                        System.out.println(grid[i+1][j]);
                    }
                }
                if (j+1 <= col){
                    grid[i][j+1] += grid[i][j];
                }
            }
        }
        return grid[row][col];
    }

    public static boolean canSum(int[] array, int target){
        boolean[] results = new boolean[target+1];
        results[0] = true;

        for (int i=0; i<results.length; i++){
            if (results[i]){
                for (int n: array){
                    if (i+n < results.length){
                        results[i+n] = true;
                    }
                }
            }
        }

        return results[target];
    }


    public static boolean balancedBrackets(String str){
        Stack<Character> bracketStack = new Stack<>();
        Set<Character> openings = new HashSet<>();
        Set<Character> closings = new HashSet<>();
        openings.add('{');openings.add('[');openings.add('(');
        closings.add('}');closings.add(']');closings.add(')');

        for (int i=0; i<str.length(); i++){
            boolean isOpening = openings.contains(str.charAt(i));
            char character = str.charAt(i);
            if (isOpening){
                bracketStack.push(character);
            }else {
                if (closings.contains(character)){
                    char opening = bracketStack.pop();
                    if (!corresponds(opening, character)){
                        return false;
                    }
                }
            }
        }
        return bracketStack.size()==0;
    }

    public static boolean corresponds(char opening, char closing){
        return (opening=='{' && closing=='}') || (opening=='(' && closing==')') || (opening=='[' && closing==']');
    }





    public static Integer[] howSum(int[] array, int target){
        List<List<Integer>>[] table = new List[target+1];

        table[0] = new ArrayList<>();

        for (int i=0; i<table.length; i++){
            if (table[i] != null){
                //get all the lists of integers
                //create a new list for this list
                //add it to the designated place

                for (int n: array){
                    List<List<Integer>> lists = table[i];

                    for (List<Integer> list: lists){
                        List<Integer> newList = new ArrayList<>(list);
                        if (i +n < array.length){
                            newList.add(n);
                            table[i+n].add(newList);
                        }
                    }

                }

            }
        }

        return (Integer[]) table[target].get(0).toArray();


    }


    public static boolean isAnagram(String str1, String str2){
        Set<String> permutations = getMyPermutations(str1);
        if (permutations.contains(str2)){
            return true;
        }
        return false;
    }

    public static Set<String> getMyPermutations(String str1){
        Set<String> permutations = new HashSet<>();
        permute("", str1, permutations);
        return permutations;
    }

    public static void permute(String prefix,   String string, Set<String> permutations){
        if (string.length() == 0){
            permutations.add(prefix);
        }else{
            for (int i=0; i<string.length(); i++){
                permute(prefix+string.charAt(i), string.substring(0, i)+string.substring(i+1, string.length()), permutations);
            }
        }
    }




    public static void main(String[] args) {
        //System.out.println(powerSet(new int[]{1,2,3}));
        //System.out.println(howSum(new int[]{5,3,4}, 7).toString());
        //System.out.println(gridTraveler(3,3));

        //System.out.println(isAnagram("abc", "cba"));
        //System.out.println(isAnagram("catt", "taca"));

        ArrayList<String> strings = validIPAddresses("1921681201");
        System.out.println(strings);
    }





    public static List<List<Integer>> powerSet(int[] array){
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        for (int n: array){

            List<List<Integer>> newLists = new ArrayList<>();
            for (List<Integer> existingList: list){
                List<Integer> newList = new ArrayList<>(existingList);
                newList.add(n);

                newLists.add(newList);
            }
            list.addAll(newLists);
        }
        return list;
    }

    public static ArrayList<String> validIPAddresses(String string) {
        // Write your code here.
        if(string.length() < 4){
            return new ArrayList<>();
        }
        ArrayList<String> strings = new ArrayList<>();
        for(int i=0; i<string.length(); i++){
            for(int j=i+1; j<string.length(); j++){
                for(int k=j+1; k<string.length(); k++){
                    for(int l=k+1; l<string.length(); l++){

                        StringBuilder sb = new StringBuilder();
                        sb.append(string.substring(i,j+1)+"."+string.substring(j+1, k+1)+"."+string.substring(k+1, l+1)+"."+string.substring(l+1, string.length()));

                        if (isValidIp(sb.toString())){
                            strings.add(sb.toString());
                        }

                    }
                }
            }
        }
        return strings;

    }

    public static boolean isValidIp(String string){
        String[] parts = string.split(".");
        for (String part: parts){
            int p = Integer.parseInt(part);
            if (p <0 || p>255){
                return false;
            }
        }
        return true;
    }


//
//    public static void main(String[] args) {
//        //permutation("abcd");
//        //System.out.println(getPerms("abcd"));
//        Integer[] array = new Integer[]{1,2,3};
//        List<List<Integer>> perms = getPermutations(Arrays.asList(array));
//
//        System.out.println(perms);
//    }

    public static List<List<Integer>> getPermutations(List<Integer> array) {
        // Write your code here.
        List<List<Integer>> result = new ArrayList<>();
        return integerPermutationHelper(new ArrayList<>(), array, result);
    }

    public static List<List<Integer>> integerPermutationHelper(List<Integer> prefix, List<Integer> mainList, List<List<Integer>> result){
        if (mainList.size() == 0){
            result.add(prefix);
        }else{
            for (int i=0; i<mainList.size(); i++){
                List<Integer> newPrefix = prefix;
                newPrefix.add(mainList.get(i));

                List<Integer> newMainList = new ArrayList<>();
                newMainList.addAll(mainList.subList(0, i));
                newMainList.addAll(mainList.subList(i+1, mainList.size()));
                integerPermutationHelper(prefix, newMainList, result);
            }
        }
        return result;
    }

    public static void permutation(String str) {
        permutation("", str);
    }

    public static List<String> getPerms(String str){
        List<String> list = new ArrayList<>();
        return getPerms("", str, list);
    }

    public static List<String> getPerms(String prefix, String str, List<String> strings){
        if (str.length() == 0){
            strings.add(prefix);
        }else{
            for (int i=0; i<str.length(); i++){
                getPerms(prefix+str.charAt(i), str.substring(0, i)+str.substring(i+1, str.length()), strings);
            }
        }
        return strings;
    }





    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) System.out.println(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }
}
