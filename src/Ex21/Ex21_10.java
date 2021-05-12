package Ex21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Ex21_10 {

    public static void main(String[] args) throws FileNotFoundException {
        String keywords[] = { "abstract", "assert", "boolean",
                "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "extends", "false",
                "final", "finally", "float", "for", "goto", "if", "implements",
                "import", "instanceof", "int", "interface", "long", "native",
                "new", "null", "package", "private", "protected", "public",
                "return", "short", "static", "strictfp", "super", "switch",
                "synchronized", "this", "throw", "throws", "transient", "true",
                "try", "void", "volatile", "while" };

        Set<String> kw = new HashSet<>(Arrays.asList(keywords));

        Scanner input = new Scanner(System.in);
        File file = new File(input.nextLine());

        Scanner fileReader = new Scanner(file);
        Map<String, Integer> occurrences = new HashMap<>();
        while (fileReader.hasNextLine()){
            String line = fileReader.nextLine();
            String[] words = line.split("[(){}\\[\\]\\s]");
            if (!line.startsWith("//")){
                for (int i=0; i<words.length; i++){
                    if (kw.contains(words[i])){
                        if (!occurrences.containsKey(words[i])){
                            occurrences.put(words[i],1);
                        }else{
                            int value = occurrences.get(words[i]);
                            occurrences.put(words[i], ++value);
                        }
                    }
                }
            }
        }
        occurrences.forEach((k, v)-> System.out.println(k+" is used "+v+" times."));

    }
}
