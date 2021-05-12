package Ex21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Ex21_04 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine();

        File file = new File(fileName);

        Scanner fileReader = new Scanner(file);
        Map<Character, Integer> vowels = new HashMap<>();
        Map<Character, Integer> consonants = new HashMap<>();

        while (fileReader.hasNext()){
            String line = fileReader.nextLine();
            for (int i=0; i<line.length(); i++){
                char c = line.charAt(i);
                if (isVowel(c)){
                    if (!vowels.containsKey(c)){
                        vowels.put(c, 1);
                    }else{
                        int value = vowels.get(c);
                        vowels.put(c, ++value);
                    }
                }else{
                    if(!consonants.containsKey(c)){
                        consonants.put(c, 1);
                    }else{
                        int value = consonants.get(c);
                        consonants.put(c, ++value);
                    }
                }
            }

        }

        vowels.forEach((letter, value) -> {
            System.out.printf("letter %c repeated %d times.\n", letter, value);
        });
        consonants.forEach((letter, value) -> {
            System.out.printf("letter %c repeated %d times.\n", letter, value);
        });

    }

    private static boolean isVowel(char c){
        char[] vowelChars = {'a', 'e', 'i', 'o', 'u'};
        for (int i=0; i<vowelChars.length; i++){
            if(vowelChars[i] == c){
                return true;
            }
        }
        return false;
    }

}
