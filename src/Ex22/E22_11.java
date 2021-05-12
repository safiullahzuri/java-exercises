package Ex22;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Comparator;
import java.util.Scanner;

public class E22_11 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double[][] s = {{1,2.4},{2.5,2},{1.5,34.5},{5.5, 6}, {6, 2.4}, {5.5, 9}};
        List<MyPoint> list = getConvexHull(s);
        System.out.println("The convex hull is");
        for (MyPoint p: list) {
            System.out.print(p + " ");
        }
        System.out.println();
    }

    public static ArrayList<MyPoint> getMyConvexHull(double[][] s){
        List<MyPoint> points = new ArrayList<>();
        for (double[] point: s){
            points.add(new MyPoint(point[0], point[1]));
        }

        MyPoint rightmostPoint = getRightmostLowestPoint2(points);
        for (MyPoint p: points){p.setRightmostLowestPoint(rightmostPoint);}
        Collections.sort(points);

        Stack<MyPoint> convexHull = new Stack<>();
        convexHull.push(points.get(0));
        convexHull.push(points.get(1));

        for (int i=2; i<points.size(); i++){
            MyPoint top = convexHull.pop();
            if (!isLeftOfLine(points.get(i), top, convexHull.peek())){
                top = convexHull.pop();
            }

            convexHull.push(top);
            convexHull.push(points.get(i));
        }
        return new ArrayList<>(convexHull);
    }

    private static MyPoint getRightmostLowestPoint2(List<MyPoint> points) {
        points.sort(new Comparator<MyPoint>() {
            @Override
            public int compare(MyPoint o1, MyPoint o2) {
                if (o1.y > o2.y){return -1;}
                else if (o1.y < o2.y){return 1;}
                else {
                    if (o1.x > o2.x){return 1;}
                    else if (o1.x < o2.x){return -1;}
                }
                return 0;
            }
        });
        return points.get(0);
    }

    public static ArrayList<MyPoint> getConvexHull(double[][] s) {
        List<MyPoint> points = new ArrayList<>();
        for (double[] point: s) {
            points.add(new MyPoint(point[0], point[1]));
        }
        // Get the rightmost-lowest point and remove it from the array
        MyPoint rightmostLowest = getRightmostLowestPoint(points);
        points.remove(rightmostLowest);
        // Set the rightmost-lowest point for all remaining points in the array
        for (MyPoint p: points) {
            p.setRightmostLowestPoint(rightmostLowest);
        }
        // Sort the points by their angles
        Collections.sort(points);
        // Remove points marked for deletion
        List<MyPoint> copy = new ArrayList<>(points);
        points.add(0, rightmostLowest);
        // Push definite points onto convexHull
        Stack<MyPoint> convexHull = new Stack<>();
        convexHull.push(points.get(0));
        convexHull.push(points.get(1));
        for (int i = 2; i < points.size(); i++) {
            System.out.println();
            MyPoint top = convexHull.pop();

            //check if the current element within iteration, the top element of stack and the second top element of the stack make a
            if (!isLeftOfLine(points.get(i), convexHull.peek(), top)) {
                top = convexHull.pop();
            }
            convexHull.push(top);
            convexHull.push(points.get(i));
        }

        return new ArrayList<>(convexHull);
    }

    private static boolean isLeftOfLine(MyPoint p, MyPoint start, MyPoint end) {
        boolean isLeftOfLine =  (p.y - start.y) * (end.x - start.x) <
                (p.x - start.x) * (end.y - start.y);
        String is = isLeftOfLine?" is ":" is not ";
        System.out.println("Point p"+p+is+" on the left of line starting at "+start+" and ending at "+end);
        return isLeftOfLine;
    }

    private static class MyPoint implements Comparable<MyPoint> {
        double x, y;
        int deletion; // -1: delete, 1: retain
        MyPoint rightmostLowestPoint;

        MyPoint(double x, double y) {
            this.x = x;
            this.y = y;
            deletion = 1;
        }

        public void setRightmostLowestPoint(MyPoint p) {
            rightmostLowestPoint = p;
        }

        public double getAngle() {
            double dy = rightmostLowestPoint.y - y;
            double dx = x - rightmostLowestPoint.x;
            double theta = Math.atan2(dy, dx);
            theta *= 180 / Math.PI;
            return theta;
        }

        public double getDistance() {
            double x2 = rightmostLowestPoint.x;
            double y2 = rightmostLowestPoint.y;
            return Math.sqrt(Math.pow(x2 - x, 2) + Math.pow(y2 - y, 2));
        }

        @Override
        public int compareTo(MyPoint o) {
            if (getAngle() < o.getAngle()) {
                return -1;
            } else if (getAngle() > o.getAngle()) {
                return 1;
            }
            // if two points have the same angle, the one closer to the
            // rightmost-lowest point will be marked for removal from candidacy
            // in the Graham scan
            MyPoint closer = getDistance() < o.getDistance() ? this : o;
            closer.deletion = -1;
            return 0;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    private static MyPoint getRightmostLowestPoint(List<MyPoint> points) {
        points.sort(new Comparator<MyPoint>() {
            @Override
            public int compare(MyPoint o1, MyPoint o2) {
                if (o1.y > o2.y) {
                    return -1;
                } else if (o1.y < o2.y) {
                    return 1;
                } else {
                    if (o1.x > o2.x) {
                        return -1;
                    } else if (o1.x < o2.x) {
                        return 1;
                    }
                }
                return 0;
            }
        });
        return points.get(0);
    }
}