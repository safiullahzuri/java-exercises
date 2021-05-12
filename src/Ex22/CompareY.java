package Ex22;

import java.util.Comparator;

public class CompareY implements Comparator<Point> {
    @Override
    public int compare(Point o1, Point o2) {
        if (o1.y > o2.y){
            return 1;
        }else if (o1.y == o2.y){
            if (o1.x > o2.x){
                return 1;
            }else if (o1.x == o2.x){
                return 0;
            }else{
                return -1;
            }
        }else{
            return -1;
        }
    }
}
