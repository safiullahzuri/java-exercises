package Ex22;

public class Ex22_24 {


    public static void main(String[] args) {
        int[] list = {3,12,5,31,86,43,12};
        System.out.println(smallest(list));
        System.out.println(highest(list));
    }

    private static int smallest(int[] list){
        return smallest(list, 0, list.length-1);
    }

    private static int smallest(int[] list, int low, int high){
        if (low == high){
            return list[low];
        }
        if (high-low == 1){
            return Math.min(list[low], list[high]);
        }

        int mid = low + (high-low)/2;

        int s1= smallest(list, low, mid);
        int s2 = smallest(list, mid+1, high);
        return Math.min(s1,s2);
    }

    private static int highest(int[] list){
        return highest(list, 0, list.length-1);
    }

    private static int highest(int[] list, int low, int high) {
        if (low == high){return list[low];}
        if (low-high==1){return Math.max(list[low], list[high]);}

        int mid = low + (high-low)/2;
        int s1 = highest(list, low, mid);
        int s2 = highest(list, mid+1, high);
        return Math.max(s1,s2);
    }
}
