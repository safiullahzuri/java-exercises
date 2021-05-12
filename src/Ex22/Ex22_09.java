package Ex22;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Ex22_09 {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("How many points there?");
        int n = input.nextInt();

        double[][] points = new double[n][2];

        for (int i=0; i<n; i++){
            double x1 = input.nextDouble();
            double y1 = input.nextDouble();

            points[i][0] = x1;
            points[i][1] = y1;
        }
        getConvexHull(points);
    }

    public static ArrayList<Point2D> getConvexHull(double[][] points){
        List<Point2D> points2D = new ArrayList<>();
        List<Point2D> convexHull = new ArrayList<>();
        for (int i=0; i< points.length; i++){
            points2D.add(new Point2D(points[i][0], points[i][1]));
        }

        //step one: select the rightmost lowest point
        sortByRightmostLowest(points2D);

        Point2D startingPoint = points2D.get(0);
        convexHull.add(startingPoint);

        Point2D rayStart = startingPoint;
        Point2D rayEnd = points2D.get(1);

        while (true){
            for (Point2D p: points2D){

            }
            break;
        }

        return null;

    }

    private static void sortByRightmostLowest(List<Point2D> points){
        points.sort(new Comparator<Point2D>() {
            @Override
            public int compare(Point2D o1, Point2D o2) {
                if (o1.getY() > o2.getY()) {
                    return -1;
                } else if (o1.getY() < o2.getY()) {
                    return 1;
                } else {
                    if (o1.getX() > o2.getX()) {
                        return -1;
                    } else if (o1.getX() < o2.getX()) {
                        return 1;
                    }
                }
                return 0;
            }

        });
    }

}
