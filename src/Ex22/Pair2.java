package Ex22;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pair2 {

    private Point p1, p2;

    public Pair2(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    private double getDistance(){
        return Math.sqrt(Math.pow(p2.getX()-p1.getX(), 2)+Math.pow(p2.getY()-p2.getY(),2));
    }

    public static Pair getClosestPair(double[][] points){
        Point[] p = new Point[points.length];
        for (int i=0; i<p.length; i++){
            p[i] = new Point(points[i][0], points[i][1]);
        }
        return getClosestPair(p);
    }

    public static Pair getClosestPair(Point[] points){
        Arrays.sort(points);
        Point[] ySorted = new Point[points.length];

        System.arraycopy(points, 0, ySorted, 0, points.length);
        Arrays.sort(ySorted, new CompareY());
        return getClosestPair(points, 0, points.length-1, ySorted);
    }

    public static Pair getClosestPair(Point[] pointsOrderedOnx, int low, int high, Point[] pointsOrderedOnY){
        if (high - low <=3){
            return bruteForcePair(pointsOrderedOnx, low, high);
        }

        int mid = (low+high) / 2;

        Pair pair1 = getClosestPair(pointsOrderedOnx, low, mid, pointsOrderedOnY);
        Pair pair2 = getClosestPair(pointsOrderedOnx, mid+1, high, pointsOrderedOnY);

        double d = Math.min(pair1.getDistance(), pair2.getDistance());

        List<Point> stripL = new ArrayList<>();
        List<Point> stripR = new ArrayList<>();

        double midX = pointsOrderedOnx[mid].getX();
        for (Point p: pointsOrderedOnY){
            if (p.getX() <= midX && midX-p.getX() <= d){
                stripL.add(p);
            }else if (p.getX()>midX && p.getX()-midX <= d){
                stripR.add(p);
            }
        }

        Pair p3 = null;
        for (Point p: stripL){
            for (Point q: stripR){
                if (distance(p, q) < d){
                    d = distance(p, q);
                    p3 = new Pair(p, q);
                }
            }
        }
        if (p3 == null){
            return min(pair1, pair2);
        }
        else return p3;
    }

    public static Pair min(Pair p1, Pair p2){
        return p1.getDistance() < p2.getDistance() ? p1: p2;
    }

    public static Pair bruteForcePair(Point[] pointsOrdered, int low, int high){
        Point p1 = pointsOrdered[0];
        Point p2 = pointsOrdered[1];

        for (int i=low; i<= high; i++){
            for (int j=i+1; j<=high; j++){
                if (distance(pointsOrdered[i], pointsOrdered[j]) < distance(p1,p2)){
                    p1 = pointsOrdered[i];
                    p2 = pointsOrdered[j];
                }
            }
        }
        return new Pair(p1, p2);
    }

    public static double distance(Point p1, Point p2){
        return distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public static double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1,2));
    }



}
