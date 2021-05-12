package Ex22;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class NCPP extends Pane {
    private List<Circle> circles;
    private Polygon poly;

    public NCPP() {
        circles = new ArrayList<>();
        configurePoly();
        drawPane();
    }

    private void configurePoly() {
        poly = new Polygon();
        poly.setFill(Color.TRANSPARENT);
        poly.setStroke(Color.BLACK);
        getChildren().add(poly);
    }

    private void drawPane() {
        setPrefSize(500, 500);
        setOnMouseClicked(e -> placePoint(e));
    }

    private void placePoint(MouseEvent event) {
        if (!event.getButton().equals(MouseButton.PRIMARY)) { return; }
        Circle circle = new Circle(5);
        circle.setCenterX(event.getX());
        circle.setCenterY(event.getY());
        circles.add(circle);
        getChildren().add(circle);
        drawPoly();
    }

    private void removePoint(MouseEvent event) {
        if (!event.getButton().equals(MouseButton.SECONDARY)) { return ;}
        Circle circle = (Circle)event.getSource();
        circles.remove(circle);
        getChildren().remove(circle);
        drawPoly();
    }

    private void drawPoly() {
        if (circles.size() >= 3) {
            poly.getPoints().clear();
            List<MyPoint> points = getNonCrossedPolygon(getPoints(circles));
            addPointsToPoly(points);
        } else {
            poly.getPoints().clear();
        }
    }

    private void addPointsToPoly(List<MyPoint> list) {
        for (MyPoint m: list) {
            poly.getPoints().addAll(m.x, m.y);
        }
    }

    private static double[][] getPoints(List<Circle> list) {
        double[][] s = new double[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            Circle c = list.get(i);
            s[i][0] = c.getCenterX();
            s[i][1] = c.getCenterY();
        }
        return s;
    }

    private static ArrayList<MyPoint> getNonCrossedPolygon(double[][] s) {
        List<MyPoint> points = new ArrayList<>();
        for (double[] point: s) {
            points.add(new MyPoint(point[0], point[1]));
        }

        MyPoint rightmostLowest = getRightmostLowestPoint(points);
        points.remove(rightmostLowest);

        for (MyPoint m: points) {
            m.setRightmostLowestPoint(rightmostLowest);
        }

        Collections.sort(points);
        points.add(0, rightmostLowest);

        return (ArrayList<MyPoint>)points;
    }

    private static class MyPoint implements Comparable<MyPoint> {
        double x, y;
        MyPoint rightmostLowestPoint;

        MyPoint(double x, double y) {
            this.x = x;
            this.y = y;
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
            } else {
                if (getDistance() < o.getDistance()) {
                    return 1;
                } else if (getDistance() > o.getDistance()) {
                    return -1;
                }
            }
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