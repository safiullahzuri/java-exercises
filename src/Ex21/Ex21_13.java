package Ex21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ex21_13 {

    public static void main(String[] args) throws FileNotFoundException {
        Map<String, Integer>[] boyMaps = new HashMap[10];
        Map<String, Integer>[] girlMaps = new HashMap[10];

        for (int i=2001; i<=2010; i++){
            String filename = "D://names//yob"+i+".txt";
            boyMaps[i-2001] = new HashMap<>();
            girlMaps[i-2001] = new HashMap<>();
            Scanner fileReader = new Scanner(new File(filename));
            while (fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                String[] words = line.split(",");
                if (words[1].equals("M")){
                    boyMaps[i-2001].put(words[0], Integer.parseInt(words[2]));
                }else{
                    girlMaps[i-2001].put(words[0], Integer.parseInt(words[2]));
                }
            }
        }
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the year");
        int year = input.nextInt();

        System.out.println("Enter the gender:");
        String gender = input.nextLine();

        System.out.println("Enter the name:");
        String name = input.nextLine();

        int rank = -1;
        if (gender.equals("M")){
            rank = boyMaps[year-2001].get(name);
        }else{
            rank = boyMaps[year-2001].get(name);
        }
        System.out.printf(gender.equals("M")?"Boy":"Girl"+" name "+name+" is ranked #"+rank);

    }
}
