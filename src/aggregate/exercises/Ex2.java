package aggregate.exercises;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ex2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        List<Integer> list = new ArrayList<>();

        int num = input.nextInt();
        while (num != 0){
            list.add(num);
            num = input.nextInt();
        }

//        list.stream()
//                .collect(Collectors.groupingBy(e->e, TreeMap::new, Collectors.counting())).forEach((k, v)->{
//            System.out.printf("%d occurs %d times.\n", k, v);
//        });

        double average = list.stream().reduce(1, (e1, e2)->e1+e2).doubleValue()/list.size();

        long aboveOnes = list.stream().filter(e ->e> average).count();
        System.out.println("above ones: "+aboveOnes);
    }
}
