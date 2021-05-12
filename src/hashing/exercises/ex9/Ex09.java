package hashing.exercises.ex9;

public class Ex09 {

    public static void main(String[] args) {
        String a = "abcd";
        System.out.println(a.hashCode());

        System.out.println(hashCodeForString(a));
    }


    public static int hashCodeForString(String s){
        int b = 31;
        int hashCode = 0;

        for (int i=0; i<s.length(); i++){
            hashCode = b * hashCode + (int) s.charAt(i);
        }
        return hashCode;
    }



}
