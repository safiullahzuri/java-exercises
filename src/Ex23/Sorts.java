package Ex23;

public class Sorts {

    public static void main(String[] args) {
        int[] array = {1,2,453,23,53,234,12,19,25,45,17,122,81,119};
        Sorts sorts = new Sorts();
        sorts.insertionSort2(array);
        sorts.printArray(array);
    }


    private void insertionSort2(int[] array){
        for (int i=1; i< array.length; i++){

            int k;
            int currentElement = array[i];
            for (k=i-1; k>=0 && array[k]>currentElement; k--){
                array[k+1] = array[k];
            }
            if (currentElement != array[i]){
                System.out.printf("current element not equal to array[%d].\n", i);
            }
            array[k+1] = currentElement;
        }
    }


    private void insertionSort(int[] array){
        for (int i=1; i< array.length; i++){
            System.out.println("Round "+i);
            int currentElement = array[i];
            int k;
            for (k=i-1; k>=0 && array[k]>currentElement; k--){
                array[k+1] = array[k];
            }
            System.out.println("K at this stage is "+k);
            array[k+1] = currentElement;
            printArray(array);
            System.out.println();
        }
    }


    private void bubbleSort(int[] array){
        boolean needNextPass = true;
        for (int i=0; i<array.length && needNextPass; i++){
            needNextPass = false;
            for (int j=0; j<array.length-1-i; j++){
                if (array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    needNextPass = true;
                }
            }
        }
    }



    private void selectionSort(int[] array){
        for (int i=0; i< array.length; i++){
            int currentSmall = array[i];
            int currentSmallIndex = i;
            for (int j=i+1; j< array.length; j++){
                if (array[j] < currentSmall){
                    currentSmall = array[j];
                    currentSmallIndex = j;
                }
            }
            if (currentSmallIndex != i){
                int temp = array[i];
                array[i] = currentSmall;
                array[currentSmallIndex] = temp;
            }
        }
    }

    public void printArray(int[] array){
        for (int a: array){
            System.out.printf(a+"\t");
        }
    }


}
