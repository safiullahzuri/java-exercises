package Ex21;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Ex21_01 {

    public static void main(String[] args){

        String[] first = {"Chemistry", "Mathematics", "Biology", "English"};
        String[] second = {"Biology", "English", "Geography", "Physics"};

        Stack<String> firstStack = new Stack<>();
        Stack<String> secondStack = new Stack<>();

        addToStack(first, firstStack);
        addToStack(second, secondStack);

        Set<String> set = new HashSet<>();
        set.addAll(firstStack);
        set.addAll(secondStack);

        //only present in the first stack
        Set<String> elements1 = new HashSet<>();
        set.forEach(item-> {
            if(firstStack.contains(item)){
                elements1.add(item);
            }
        });

        //only present in the second stack
        Set<String> elements2 = new HashSet<>();
        set.forEach(item->{
            if (secondStack.contains(item)){
                elements2.add(item);
            }
        });
        Set<String> elements3 = new HashSet<>(firstStack);
        Set<String> elements4 = new HashSet<>(secondStack);

        elements3.retainAll(elements4);
        System.out.println(elements3);
    }

    private static void addToStack(String[] array, Stack<String> stack){
        Arrays.stream(array).forEach(item -> stack.add(item));
    }

}
