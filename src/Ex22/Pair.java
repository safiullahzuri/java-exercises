package Ex22;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pair {
    public Point point1, point2;

    public Pair(Point p1, Point p2){
        this.point1 = p1;
        this.point2 = p2;
    }

    public double getDistance(){
        return Math.sqrt(Math.pow(point2.x-point1.x, 2) + Math.pow(point2.y-point1.y, 2));
    }

    public static Pair getClosestPair(double[][] points){
        Point[] p = new Point[points.length];
        for (int i=0; i<points.length; i++){
            p[i] = new Point(points[i][0], points[i][1]);
        }
        return getClosestPair(p);
    }

    public static Pair getClosestPair(Point[] points){

        Arrays.sort(points);
        Point[] ySorted = new Point[points.length];
        System.arraycopy(points, 0, ySorted,0, ySorted.length);
        Arrays.sort(ySorted, new CompareY());
        return getClosestPair(points,0,points.length-1, ySorted);
    }

    public static Pair getClosestPair2(Point[] points){

        Arrays.sort(points);
        Point[] ySorted = new Point[points.length];
        System.arraycopy(points, 0, ySorted,0, ySorted.length);
        Arrays.sort(ySorted, new CompareY());
        return getClosestPair2(points,0,points.length-1, ySorted);
    }


    public static Pair getClosestPair2(Point[] pointsOrderedOnX, int low, int high, Point[] pointsOrderedOnY){
        if (high - low <=3){
            return bruteForceClosestPair(pointsOrderedOnX, low, high);
        }
        int mid = (low+high)/2;
        Pair p1 = getClosestPair(pointsOrderedOnX, low, mid, pointsOrderedOnY);
        Pair p2 = getClosestPair(pointsOrderedOnX, mid+1, high, pointsOrderedOnY);

        double d = Math.min(p1.getDistance(), p2.getDistance());

        List<Point> stripL = new ArrayList<>();
        List<Point> stripR = new ArrayList<>();

        double midX = pointsOrderedOnX[mid].getX();

        for (Point p: pointsOrderedOnY){
            if (p.getX() <= midX && midX-p.getX() <= d){
                stripL.add(p);
            }else if (p.getX() >= midX && p.getX()-midX <=d){
                stripR.add(p);
            }
        }


        Pair p3 = null;

        for (Point p: stripL){

            for (int r1 = 0; r1< stripR.size() && Math.abs(stripR.get(r1).getY()-p.getY()) <= d; r1++){
                if (distance(p, stripR.get(r1)) < d){
                    d = distance(p, stripR.get(r1));
                    p3 = new Pair(p, stripR.get(r1));
                }
            }
        }

        if (p3 == null)return min(p1, p2);
        else return p3;

    }
    public static Pair getClosestPair(Point[] pointsOrderedOnX, int low, int high, Point[] pointsOrderedOnY){
        if (high - low <=3){
            return bruteForceClosestPair(pointsOrderedOnX, low, high);
        }
        int mid = (low+high)/2;
        Pair p1 = getClosestPair(pointsOrderedOnX, low, mid, pointsOrderedOnY);
        Pair p2 = getClosestPair(pointsOrderedOnX, mid+1, high, pointsOrderedOnY);

        double d = Math.min(p1.getDistance(), p2.getDistance());

        List<Point> stripL = new ArrayList<>();
        List<Point> stripR = new ArrayList<>();

        double midX = pointsOrderedOnX[mid].getX();

        for (Point p: pointsOrderedOnY){
            if (p.getX() <= midX && midX-p.getX() <= d){
                stripL.add(p);
            }else if (p.getX() >= midX && p.getX()-midX <=d){
                stripR.add(p);
            }
        }


        Pair p3 = null;

        int rightIndex = 0;//strip right current index
        for (Point p: stripL){
            while (rightIndex < stripR.size() && stripR.get(rightIndex).getY() <= p.getY()-d){
                rightIndex++;
            }
            for (int r1 = rightIndex; r1< stripR.size() && Math.abs(stripR.get(r1).getY()-p.getY()) <= d; r1++){
                if (distance(p, stripR.get(r1)) < d){
                    d = distance(p, stripR.get(r1));
                    p3 = new Pair(p, stripR.get(r1));
                }
            }
        }

        if (p3 == null)return min(p1, p2);
        else return p3;

    }

    public static Pair min(Pair p1, Pair p2){
        return p1.getDistance() < p2.getDistance() ? p1: p2;
    }

    public static double distance(Point p1, Point p2) {
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        return distance(x1, y1, x2, y2);
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }


    public static Pair bruteForceClosestPair(Point[] points, int low, int high){
        Point p1 = points[low];
        Point p2 = points[low+1];

        for (int i=low; i<=high; i++){
            for (int j=i+1; j<=high; j++){
                if (distance(points[i], points[j]) < distance(p1,p2)){
                    p1 = points[i];
                    p2 = points[j];
                }
            }
        }
        return new Pair(p1, p2);
    }

    public static Pair myBruteForceClosestPairs(Point[] points){
        Point p1 = points[0];
        Point p2 = points[1];

        for (int i=0; i< points.length; i++){
            for (int j=1; j<points.length; j++){
                if (distance(points[i], points[j]) < distance(p1, p2)){
                    p1 = points[i];
                    p2 = points[j];
                }
            }
        }
        return new Pair(p1, p2);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "point1=" + point1 +
                ", point2=" + point2 +
                '}';
    }
}
