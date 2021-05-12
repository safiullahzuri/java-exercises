package algoexpert.medium;

import java.io.File;
import java.util.*;

public class Test {




    public int validStartingCity(int[] distances, int[] fuel, int mpg) {
        // Write your code here.
        //i is the starting city

        double currentPermittedMiles = 0;

        for(int i=0; i<distances.length; i++){
            currentPermittedMiles = fuel[i]*mpg;
            System.out.println("i is "+i);
            for(int j=i+1; j<distances.length+i; j++){
                System.out.println("entering inner for loop");
                int rotatedIndex = (j+distances.length)%distances.length;
                currentPermittedMiles = currentPermittedMiles-distances[rotatedIndex-1];

                if(currentPermittedMiles < 0){
                    break;
                }else{
                    currentPermittedMiles += fuel[rotatedIndex]*mpg;
                }
            }

            if(currentPermittedMiles >= 0){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(staircaseTraversal(4, 2));
    }

    public static int staircaseTraversal(int height, int maxSteps) {
        // Write your code here.
        if(height == 0 || height == 1){
            return 1;
        }else{
            int result = 0;
            for(int i=maxSteps; i>0; i--){
                result += staircaseTraversal(height-i, maxSteps);
            }
            return result;
        }

    }

    public static ArrayList<Integer> sortStack(ArrayList<Integer> list) {
        // Write your code here.
        Stack<Integer> stack = new Stack<>();
        stack.push(list.get(0));


        for(int i=1; i<list.size(); i++){
            //iterate through the elments
            if(list.get(i) > list.get(i-1)){
                stack.push(list.get(i));
            }else{
                int j=i;

                while(stack.get(j) < stack.get(j-1) && j>=1){
                    //problem here
                    //swap the elements
                    int temp =stack.pop();
                    stack.push(list.get(j));
                    stack.push(temp);
                    j--;
                }
            }
        }
        return new ArrayList<>(stack);
    }

    public static int gridTravelerMemo(int row, int col, Map<String, Integer> map){
        String key = row+", "+col;

        if (row == 0 || col == 0) return 0;
        else if (row == 1 && col == 1) return 1;
        else if (map.containsKey(key)){
            return map.get(key);
        }else{
            //int r1 = gridTravelerMemo(row-1, col, map);
            //map.put(new Integer[]{row-1, col}, r1);

            //int r2 = gridTravelerMemo(row, col-1, map);
            //map.put(new Integer[]{row, col-1}, r2);
            map.put(key, gridTravelerMemo(row-1, col, map)+gridTravelerMemo(row, col-1, map));
            return map.get(key);
        }
    }


    public static int gridTraveler(int row, int col){
        if (row == 0 || col == 0) return 0;
        else if (row == 1 && col == 1) return 1;
        else
            return gridTraveler(row-1, col) + gridTraveler(row, col-1);
    }


    public static int fib(int n){
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(2, 1);
        return fib(n, map);
    }

    public static int fib(int n, HashMap<Integer, Integer> map){
        if (map.containsKey(n)){
            return map.get(n);
        }else{
            int result = fib(n-1, map) + fib(n-2, map);
            map.put(n, result);
            return result;
        }
    }


    public static void showDirectories(File file){
        showDirectories(file, 0);
    }

    public static void showDirectories(File file, int tabs){

            File[] files = file.listFiles();
            tabs++;
            String t = "";
            for(int i=0; i<tabs-1; i++){
                t += "\t";
            }
            for (int i=0; files != null && i< files.length; i++){
                System.out.println(t+"+--"+files[i].getName());
                showDirectories(files[i], tabs);
            }

    }


    public static long getSize(File file){
        if (file.isFile()){
            return file.length();
        }
        long length = 0;
        File[] files = file.listFiles();
        for (int i=0; files != null && i< files.length; i++){
            length += getSize(files[i]);
        }
        return length;
    }



    public static int recursiveBinarySearch(int[] array, int number){
        //Arrays.sort(array);
        return recursiveBinarySearch(array, number, 0, array.length-1);
    }

    public static int recursiveBinarySearch(int[] array, int number, int low, int high){
        if (low>high){
            return -1;
        }

        int mid = (low+high)/2;
        if (number > array[mid]){
            return recursiveBinarySearch(array, number, mid+1, high);
        }else if (number < array[mid]){
            return recursiveBinarySearch(array, number, low, mid-1);
        }else if (number == array[mid]){
            return mid;
        }
        return -1;
    }



    public static int binarySearch(int[] array, int number){
        Arrays.sort(array);

        int low = 0;
        int high = array.length-1;

        while (low <= high){
            int mid = (low+high)/2;

            if (number < array[mid]){
                high = mid-1;
            }else if (number > array[mid]){
                low  = mid + 1;
            }else{
                return mid;
            }

        }
        return -1;
    }




    public static void recursiveSelectionSort(int[] array){
        recursive(array, 0, array.length-1);
    }

    public static void recursive(int[] array, int low, int high){

        if (low>high){
            return;
        }

        int smallest = array[low];
        int smallestIndex = low;

        //choose the smallest for the low and proceed
        for (int i=low+1; i<=high; i++){
            if(smallest > array[i]){
                smallest = array[i];
                smallestIndex = i;
            }

        }
        int temp = array[low];
        array[low] = smallest;
        array[smallestIndex] = temp;

        recursive(array, low+1, high);
    }


    public static void selectionSort(int[] array){
        //{5,3,643,23,67,8,54,1}

        for (int i=0; i<array.length; i++){

            int smallestIndex = i;
            int smallest = array[i];

            for (int j=i+1; j<array.length; j++){
                if(smallest > array[j]){
                    smallestIndex = j;
                    smallest = array[j];
                }
            }
            //swap the smallest with array[i]
            int temp = array[i];
            array[i] = smallest;
            array[smallestIndex] = temp;
        }

    }

    public static void printArray(int[] array){
        for (int i=0; i<array.length; i++){
            System.out.print(array[i]+" ");
        }
    }

}
