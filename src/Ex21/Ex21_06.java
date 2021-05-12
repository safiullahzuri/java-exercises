package Ex21;

import java.io.File;
import java.util.*;

public class Ex21_06 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine();

        Set<File> files = new HashSet<>();

        while (!fileName.equals("end")){
            files.add(new File(fileName));
            fileName = input.nextLine();
        }

        Map<String, Integer> presentStudents = new TreeMap<>();
        files.forEach(file->{
            if (file.exists()){

                try{
                    Scanner fileReader = new Scanner(file);
                    if (fileReader.hasNextLine()){
                        fileReader.nextLine();
                    }
                    while (fileReader.hasNextLine()){
                        String line = fileReader.nextLine();
                        if (!presentStudents.containsKey(line)){
                            presentStudents.put(line, 1);
                        }else{
                            int value = presentStudents.get(line);
                            value++;
                            presentStudents.put(line, value);
                        }
                    }
                }catch (Exception e){

                }
            }
        });

//        presentStudents.forEach((student, times) -> {
//            System.out.printf("%s present for %d days.\n", student, times);
//        });

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(presentStudents.entrySet());
        Collections.sort(entryList, (entry1, entry2) -> {
            if (entry2.getValue() == entry1.getValue()){
                return entry2.getKey().compareTo(entry1.getKey());
            }else{
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });

        entryList.forEach(entry -> {
            System.out.printf("Student %s present for %d days.\n", entry.getKey(), entry.getValue());
        });
    }
}
