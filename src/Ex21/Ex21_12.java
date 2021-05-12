package Ex21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Ex21_12 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        File file = new File(input.nextLine());

        Scanner fileReader = new Scanner(file);

        Set<String> boyNames = new HashSet<>();
        Set<String> girlNames = new HashSet<>();

        while (fileReader.hasNextLine()){
            String line = fileReader.nextLine();
            String[] words = line.split(",");

            if (words[1].equals("M")){
                boyNames.add(words[0]);
            }else{
                girlNames.add(words[0]);
            }
        }
        System.out.println(boyNames);
        System.out.println(boyNames.size());
        boyNames.retainAll(girlNames);
        System.out.println(boyNames);
        System.out.println(boyNames.size());

    }
}
