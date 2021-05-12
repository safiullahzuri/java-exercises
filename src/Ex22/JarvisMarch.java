package Ex22;

import java.util.*;

public class JarvisMarch {

    static class Point{
        double x, y;
        Point(double x, double y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Point[] points = new Point[n];

        for (int i=0; i< points.length; i++){
            points[i] = new Point(input.nextDouble(), input.nextDouble());
        }

        JarvisMarch jarvisMarch = new JarvisMarch();
        List<Point> points1 = jarvisMarch.findConvexHull(points);
        for (Point point: points1){
            System.out.println(point);
        }
    }

    public List<Point> findConvexHull(Point[] points){
        Point starter = points[0];

        for (int i=1; i<points.length; i++){
            if (starter.x < points[i].x){
                starter = points[i];
            }
        }

        Point current = starter;
        Set<Point> convexHull = new HashSet<>();
        List<Point> collinearPoints = new ArrayList<>();

        while (true){
            Point nextTarget = points[0];

            for (int i=1; i<points.length ;i++){

                if (current == nextTarget){continue;}

                double val = crossProduct(current, nextTarget, points[i]);
                if (val > 0){
                    nextTarget = points[i];
                }else if (val == 0){
                    if (distance(current, nextTarget, points[i]) > 0){
                        collinearPoints.add(nextTarget);
                        nextTarget = points[i];
                    }else{
                        collinearPoints.add(points[i]);
                    }
                }
            }
            for (Point p: collinearPoints){convexHull.add(p);}
            convexHull.add(nextTarget);
            current = nextTarget;

            if (starter == nextTarget){break;}

        }
        return new ArrayList<>(convexHull);
    }


    public List<Point> findConvexHull2(Point[] points){
        Point starter = points[0];

        for (int i=1; i<points.length; i++){
            if (starter.x > points[i].x){
                starter = points[i];
            }
        }

        Point current = starter;
        Set<Point> result = new HashSet<>();
        List<Point> collinearPoints = new ArrayList<>();

        while (true){
            Point nextTarget = points[0];
            for (int i=1; i<points.length; i++){
                double crossProduct = crossProduct(current, nextTarget, points[i]) ;
                if (crossProduct > 0){
                    nextTarget = points[i];
                    collinearPoints = new ArrayList<>();
                }else if (crossProduct == 0){
                    if (distance(current, nextTarget, points[i]) > 0){
                        collinearPoints.add(nextTarget);
                        nextTarget = points[i];
                    }else{
                        collinearPoints.add(points[i]);
                    }
                }

            }

            for (Point p: collinearPoints){
                result.add(p);
            }

            if (nextTarget == starter){
                break;
            }
            result.add(nextTarget);
            current = nextTarget;
        }
        return new ArrayList<>(result);

    }















    public List<Point> findConvexHull3(Point[] points){
        Point start = points[0];
        for (int i=1; i< points.length; i++){
            if (points[i].x < start.x){
                start = points[i];
            }
        }

        Point current = start;
        Set<Point> result = new HashSet<>();
        List<Point> collinearPoints = new ArrayList<>();

        while (true){
            Point nextTarget = points[0];
            for (int i=1; i< points.length; i++){
                if (points[i] == current){
                    continue;
                }
                double val = crossProduct(current, nextTarget, points[i]);
                if (val  >0){
                    nextTarget = points[i];
                    collinearPoints = new ArrayList<>();
                }else if (val == 0){
                    if (distance(current, nextTarget, points[i]) < 0){
                        collinearPoints.add(nextTarget);
                        nextTarget = points[i];
                    }else{
                        collinearPoints.add(points[i]);
                    }
                }
            }

            for (Point p: collinearPoints){
                result.add(p);
            }
            if (nextTarget == start){break;}
            result.add(nextTarget);
            current = nextTarget;
        }
        return new ArrayList<>(result);
    }

    /**
     * returns <0 if 'b' is closer to 'a' compared to 'c', == 0 if 'b' and 'c' are same
     * distance form 'a' or > 0 if 'c' is closer to 'a' compared to 'b'
     */
    private int distance(Point a, Point b, Point c){
        double y1 = a.y - b.y;
        double y2 = a.y - c.y;
        double x1 = a.x - b.x;
        double x2 = a.x - c.x;
        return Double.compare(y1*y2+x1*x2, y2*y2+x2*x2);
    }

    /**
     *  cross product to find where c belongs in reference to vector ab
     *  if result > 0, it means 'c' is on left of ab
     *  result == 0 it means 'a', 'b' and 'c' are collinear
     *  result < 0, it means 'c' is on the right of ab
     */
    private double crossProduct(Point a, Point b, Point c){
        double y1 = a.y - b.y;
        double y2 = a.y - c.y;
        double x1 = a.x - b.x;
        double x2 = a.x - c.x;
        return y2*x1 - y1*x2;
    }


}
