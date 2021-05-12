package aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectDemo {

    public static void main(String[] args) {
        String[] names = {"John", "Peter", "Susan", "Kim", "Peter", "George", "Alan", "Stacy"};

        System.out.println("The number of characters for all names: "+
                Stream.of(names).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).length());


        List<String> list =
                Stream.of(names).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        list = Stream.of(names).collect(Collectors.toList());

        Map<String, Integer> map = Stream.of(names).collect(Collectors.toMap(e->e, e ->e.length()));
    }

}
