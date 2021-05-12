package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class A {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1,2,4,5,3,23,43,53,432};
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(arr));

        Collections.sort(list);

        System.out.println(list.get(list.size()-1));
    }

}
