package Ex22;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JarvisMarch2 {

    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {

    }

    public List<Point> findConvexHull(Point[] points) {

        Point starterPoint = points[0];
        for (Point p : points) {
            if (p.x < starterPoint.x) {
                starterPoint = p;
            }
        }

        Point current = starterPoint;
        List<Point> collinearPoints = new ArrayList<>();
        Set<Point> convexPoints = new HashSet<>();


        //loop forever until you find a target point which is the same as your starting point
        while (true) {
            Point nextTarget = points[0];
            for (int i = 1; i < points.length; i++) {

                if (points[i] == current) {
                    continue;
                }

                double val = crossProduct(current, nextTarget, points[i]);
                if (val > 0) {
                    nextTarget = points[i];
                    collinearPoints = new ArrayList<>();
                } else if (val == 0) {
                    if (distance(current, nextTarget, points[i]) < 0) {
                        collinearPoints.add(nextTarget);
                        nextTarget = points[i];
                    } else {
                        collinearPoints.add(points[i]);
                    }
                }
            } //first foor loop ended here

            for (Point p : collinearPoints) {
                convexPoints.add(p);
            }
            if (nextTarget == starterPoint) break;
            convexPoints.add(nextTarget);
            current = nextTarget;
        }
        return new ArrayList<Point>(convexPoints);
    }

    double distance(Point a, Point b, Point c) {
        double x1 = a.x - b.x;
        double x2 = a.x - c.x;
        double y1 = a.y - b.y;
        double y2 = a.y - c.y;
        return Double.compare(y1 * y2 + x1 * x2, y2 * y2 + x2 * x2);
    }


    double crossProduct(Point a, Point b, Point c) {
        double x1 = a.x - b.x;
        double x2 = a.x - c.x;
        double y1 = a.y - b.y;
        double y2 = a.y - c.y;
        return x1 * y2 - x2 * y1;
    }

}


