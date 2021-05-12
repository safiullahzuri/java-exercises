package Ex21;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Ex21_09 {
    public static void main(String[] args) {
        String[][] states = {{"herat", "herat"}, {"kabul", "kabul"}, {"zabul", "zabul"}, {"kandahar", "kandahar"}};

        Map<String, String> stateMap = new HashMap<>();
        for (int i=0; i< states.length; i++){
            stateMap.put(states[i][0], states[i][1]);
        }
        Scanner input = new Scanner(System.in);

        List<Map.Entry<String, String>> kv = new ArrayList<>(stateMap.entrySet());
        Collections.shuffle(kv);
        AtomicInteger points = new AtomicInteger();
        kv.forEach((entry)->{
            System.out.printf("What is the capital of %s ?\n", entry.getKey());
            String answer = input.nextLine();;
            if (answer.equals(entry.getValue())){
                points.getAndIncrement();
            }
        });
        System.out.println("Your score is: "+points.doubleValue());
    }
}
