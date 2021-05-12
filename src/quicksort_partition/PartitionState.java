package quicksort_partition;

import java.util.ArrayList;
import java.util.Arrays;

public class PartitionState {

    private int low;
    private int high;
    private int pivot;

    private int[] list;


    public PartitionState(int low, int high, int pivot, int[] list) {
        this.low = low;
        this.high = high;
        this.pivot = pivot;
        this.list = list;
    }

    public int getLow() {
        return low;
    }

    public int getHigh() {
        return high;
    }

    public int getPivot() {
        return pivot;
    }

    public int[] getList() {
        return list;
    }

    public static ArrayList<PartitionState> getStates(int[] list){
        ArrayList<PartitionState> states = new ArrayList<>();
        int pivot = list[0];
        int low = 1;
        int high = list.length-1;

        while (high > low){
            while (low <= high && list[low] <= pivot){
                low++;
                states.add(new PartitionState(low, high, pivot, Arrays.copyOf(list, list.length)));
            }

            while (low <= high && list[high] > pivot){
                high--;
                states.add(new PartitionState(low, high, pivot, Arrays.copyOf(list, list.length)));
            }

            if (high > low){
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
                states.add(new PartitionState(low, high, pivot, Arrays.copyOf(list, list.length)));
            }
        }

        while (high > 0 && list[high] >= pivot){
            high--;
            states.add(new PartitionState(low, high, pivot, Arrays.copyOf(list, list.length)));
        }

        if (pivot > list[high]){
            list[0] = list[high];
            list[high] = pivot;
            states.add(new PartitionState(low, high, pivot, Arrays.copyOf(list, list.length)));
        }

        return states;
    }


}
