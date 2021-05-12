package Ex22;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex22_01 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String s = input.nextLine();

        System.out.println(giveBiggestConsecutive(s));
        //String a ="abcdefghi";
        //System.out.println(a.substring(1,2));
        //giveBiggestConsecutive("abcdefabcabcdefghi");
    }

    private static String giveBiggestConsecutive(String s){
       StringBuilder sb = new StringBuilder();
        ArrayList<String> strings = new ArrayList<>();
        for (int i=1; i<s.length(); i++){
            if (s.charAt(i) > s.charAt(i-1)){
                if (sb.length() == 0){
                    sb.append(s.charAt(i-1));
                }
                sb.append(s.charAt(i));
            }else{
                strings.add(sb.toString());
                sb.setLength(0);
            }
        }
        strings.add(sb.toString());
        String max=strings.get(0);
        for(String s1: strings){
            if (s1.length() > max.length()){
                max = s1;
            }
        }
        return max;
    }
}
