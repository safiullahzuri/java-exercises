package aggregate.exercises;

import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ex4 {

    public static void main(String[] args) {

        int[] nums = new Random().ints(100, 0, 10).toArray();

        IntStream.of(nums).mapToObj(e-> e).collect(Collectors.groupingBy(e->e, TreeMap::new, Collectors.counting())).forEach((k,v) -> {
            System.out.printf("%d repeated %d times.\n", k, v);
        });


    }
}
