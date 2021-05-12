package Ex22_Difficult_Questions;

public class BinarySearchState {

    private int[] array;
    private int key;
    private int low;
    private int high;
    private int mid;
    private boolean found = false;

    public BinarySearchState(int[] array, int key, int low, int high, int mid){
        this.array = array;
        this.key = key;
        this.low = low;
        this.high = high;
        this.mid = mid;
    }

    public int getLow() {
        return low;
    }

    public int getHigh() {
        return high;
    }

    public int getMid() {
        return mid;
    }

    public boolean getFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public static BinarySearchState[] generateSearchStates(int[] array, int key){
        BinarySearchState[] states = new BinarySearchState[array.length];
        int low = 0;
        int high = array.length-1;
        int mid = (low+high)/2;
        int i=0;

        while (low <= high){
            BinarySearchState b = new BinarySearchState(array, key, low, high, mid);
            states[i] = b;
            if (array[mid] == key){
                b.setFound(true);
                break;
            }else if (key < array[mid]){
                high = mid-1;
            }else if (key > array[mid]){
                low = mid+1;
            }
            mid = (low+high)/2;
            i++;
        }
        return states;
    }

}
