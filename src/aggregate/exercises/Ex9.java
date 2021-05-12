package aggregate.exercises;

import java.util.stream.Stream;

public class Ex9 {

    public static void main(String[] args) {
        Student[] students = {new Student("safi", 54), new Student("Ahmad", 32),
        new Student("gholam", 65), new Student("naser", 45)};

        Stream.of(students).sorted((s2,s1)->{
            if (s1.getScore()>s2.getScore()) return 1;
            else return -1;
        }).forEach(std -> {
            System.out.println(std);
        });
    }
}
