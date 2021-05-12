package Ex22;

public class Point implements Comparable<Point> {
    double x, y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public int compareTo(Point point) {
        if (this.x > point.x){
            return 1;
        }else if (this.x == point.x){
            if (this.y > point.y){
                return 1;
            }else if (this.y == point.y){
                return -1;
            }else{
                return 0;
            }
        }else{
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
