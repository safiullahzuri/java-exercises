package Ex21;

import java.io.File;
import java.util.*;

public class Ex21_02 {

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        Set<File> files = new HashSet<>();
        String fileName = input.next();
        while (!fileName.equals("end")){
            files.add(new File(fileName));
            fileName = input.next();
        }

        Map<String, Integer> presentStudents = new HashMap<>();

        files.forEach(file -> {
            try{
                if (file.exists()){
                    Scanner fileReader = new Scanner(file);
                    if (fileReader.hasNextLine()){
                        fileReader.nextLine();
                    }
                    while (fileReader.hasNext()){
                        String student = fileReader.nextLine();
                        if (!presentStudents.containsKey(student)){
                            presentStudents.put(student, 1);
                        }else{
                            int presentDays = presentStudents.get(student);
                            presentDays++;
                            presentStudents.put(student, presentDays);
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(presentStudents.entrySet());
        Collections.sort(entryList, (entry1, entry2) -> {
            return entry1.getValue().compareTo(entry2.getValue());
        });

        entryList.forEach((entry) -> {
            System.out.printf("%s has been present for %d days.\n", entry.getKey(), entry.getValue());
        });

        //Exercise 21.03
        Set<String> moreThan = new HashSet<>();
        presentStudents.forEach((student, days) -> {
            String[] fullName = student.split(" ");
            String firstName = "";
            String lastName = "";
            if(fullName.length > 1){
                firstName = fullName[0];
                lastName = fullName[1];
            }
            if (!moreThan.contains(student) && days>1){
                moreThan.add(student);
            }
        });
        System.out.printf("Students present more than one day here: \n");
        moreThan.forEach((student) -> {
            System.out.println(student);
        });

    }
}
