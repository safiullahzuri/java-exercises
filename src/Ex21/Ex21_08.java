package Ex21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Ex21_08 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Scanner fileReader = new Scanner(new File(input));
        Map<Character, Integer> map = new HashMap<>();
        while (fileReader.hasNextLine()){
            String line = fileReader.nextLine();
            if (!line.isEmpty()){
                String[] words = line.split("[\\s+\\p{P}]");

                for (int i=0; i<words.length; i++){
                    String word = words[i].toLowerCase();
                        for (int j=0; j<word.length();j++){
                            char c = word.charAt(j);
                            if(Character.isLetter(c)){
                                if(!map.containsKey(c)){
                                    map.put(c, 1);
                                }else{
                                    int value = map.get(c);
                                    map.put(c, ++value);
                                }
                            }
                    }
                }
            }
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (entry1, entry2)->{
            return entry2.getValue().compareTo(entry1.getValue());
        });

        list.forEach((entry -> {
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }));


    }
}
