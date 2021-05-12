package algoexpert.medium;

import Ex22.LinearSearchState;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class RiverSizesTest {

    @Test
    public void testCase1(){
        int[][] input = {
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0},
        };

        int[] expected = {1,2,2,2,5};
        List<Integer> output = RiverSizes.riverSizes(input);
        Collections.sort(output);

        int[] result = new int[expected.length];
        for (int i=0; i<result.length; i++){
            result[i] = output.get(i);
        }

        System.out.println("Expected: ");

        System.out.println("result is:");
        System.out.println(output);
    }

    public static boolean compare(List<Integer> arr1, int[] arr2) {
        if (arr1.size() != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.size(); i++) {
            if (arr1.get(i) != arr2[i]) {
                return false;
            }
        }
        return true;
    }

}
