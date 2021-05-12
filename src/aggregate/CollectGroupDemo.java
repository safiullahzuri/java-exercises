package aggregate;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectGroupDemo {

    public static void main(String[] args) {
        String[] names = {"John", "Peter", "Susan", "Kim", "Jen", "George", "Alan", "Stacy", "Steve", "john"};

        Map<String, Long> map1 = Stream.of(names).map(e-> e.toUpperCase()).collect(Collectors.groupingBy(e->e, Collectors.counting()));
        System.out.println(map1);

        Map<Character, Long> map2 = Stream.of(names).collect(Collectors.groupingBy(e-> e.charAt(0), TreeMap::new, Collectors.counting()));
        System.out.println(map2);


        Student[] students = {new Student("John", "Lu", "CS", 32, 78), new Student("Susan", "Yao", "Math", 31, 85.4),
        new Student("Kim", "Johnson", "CS", 30, 78.1)};

        System.out.printf("%10s%10s\n", "Department", "Average");

        Stream.of(students).collect(Collectors.groupingBy(Student::getMajor, TreeMap::new, Collectors.averagingDouble(Student::getScore))).forEach((k,v) -> {
            System.out.printf("%10s%10.2f\n", k, v);
        });

    }
}
