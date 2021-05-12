package Ex22_Difficult_Questions;

import Ex22.LinearSearchState;

import java.util.Arrays;

public class LinearSearchState_v2 {
    private int[] array;
    private int key;
    private int index;
    private boolean wasFound = false;


    public LinearSearchState_v2(int[] array, int key, int index){
        this.array = array;
        this.key = key;
        this.index = index;
    }

    public int getIndex(){
        return index;
    }

    public boolean getFound(){
        return wasFound;
    }

    private void setFound(boolean found){
        wasFound = found;
    }

    public static LinearSearchState_v2[] generateSearchStates(int[] array, int key){
        LinearSearchState_v2[] states = new LinearSearchState_v2[array.length];
        for (int i=0; i< array.length; i++){
            LinearSearchState_v2 lss = new LinearSearchState_v2(array, key, i);
            states[i] = lss;
            if (key == array[i]){
                lss.setFound(true);
                break;
            }
        }
        return states;
    }

    @Override
    public String toString() {
        return "LinearSearchState_v2{" +
                "array=" + Arrays.toString(array) +
                ", key=" + key +
                ", index=" + index +
                ", wasFound=" + wasFound +
                '}';
    }
}
