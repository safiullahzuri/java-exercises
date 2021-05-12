package Ex22;

public class MyPoint implements Comparable<MyPoint>{
    double x,y;
    MyPoint rightmostPoint;

    MyPoint(double x, double y){
        this.x = x;
        this.y = y;
    }

    void setRightmostPoint(MyPoint point){
        this.rightmostPoint = point;
    }

    public double getAngle(){
        double dx = this.x - rightmostPoint.x;
        double dy = this.y - rightmostPoint.y;

        double angle =  Math.atan2(dy, dx);
        angle = angle * (Math.PI/180);
        return angle;
    }

    @Override
    public int compareTo(MyPoint o) {
        if (this.getAngle() < o.getAngle()){
            return 1;
        }else if (this.getAngle() > o.getAngle()){
            return -1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return "MyPoint{" +
                "x=" + x +
                ", y=" + y +
                ", rightmostPoint=" + rightmostPoint +
                '}';
    }
}