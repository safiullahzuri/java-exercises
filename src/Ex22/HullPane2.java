package Ex22;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import java.util.*;

public class HullPane2 extends Pane {

    private List<Circle> circleList;
    private Polygon hull;

    public HullPane2(){
        circleList = new ArrayList<>();
        hull = new Polygon();
        hull.setStroke(Color.BLACK);
        hull.setFill(Color.TRANSPARENT);
        this.setOnMouseClicked(e-> handleClick(e));
        setPrefSize(500, 500);
    }

    private void remove(MouseEvent e){
        Circle c = (Circle) e.getSource();
        circleList.remove(c);
        getChildren().remove(c);
        paintConvexHull();
    }

    private void handleClick(MouseEvent e) {
        double x =  e.getX();
        double y = e.getY();
        Circle circle = new Circle(x, y, 5);

        circle.setOnMouseClicked(event -> remove(event));

        circleList.add(circle);
        if (circleList.size() > 2){
            //find the convex hull of the points
            //paint the convex hull polygon

            paintConvexHull();
        }

        this.getChildren().add(circle);
    }

    private void paintConvexHull(){

        this.getChildren().remove(hull);
        hull = new Polygon();
        hull.setFill(Color.TRANSPARENT);
        hull.setStroke(Color.BLACK);

        List<MyPoint> points = findConvexHull(circleList);

        System.out.println("convex hull is: "+points);

        for (MyPoint p: points){
            double x = p.x;
            double y = p.y;
            hull.getPoints().addAll(x, y);
        }
//
//        for (Circle c: circleList){
//            double x = c.getCenterX();
//            double y = c.getCenterY();
//            hull.getPoints().addAll(x, y);
//        }
        this.getChildren().add(hull);
    }

    private static boolean isOnLeftOfLine(MyPoint startPoint, MyPoint endPoint, MyPoint point){
        return (point.y-startPoint.y)*(endPoint.x-startPoint.x)>(endPoint.y-startPoint.y)*(point.x-startPoint.x);
    }

    private ArrayList<MyPoint> findConvexHull(List<Circle> points){

        List<MyPoint> myPoints = new ArrayList<>();

        for (Circle c: points){
            myPoints.add(new MyPoint(c.getCenterX(), c.getCenterY()));
        }

        MyPoint rightMostPoint = getRightMostPoint(myPoints);
        myPoints.remove(rightMostPoint);


        for (MyPoint mp: myPoints){
            mp.setRightmostPoint(rightMostPoint);
        }

        Collections.sort(myPoints);

        myPoints.add(0, rightMostPoint);

        Stack<MyPoint> convexStack = new Stack<>();

        convexStack.push(myPoints.get(0));
        convexStack.push(myPoints.get(1));

        for (int i=2; i< myPoints.size(); i++){
            MyPoint top = convexStack.pop();
            if (!isOnLeftOfLine(top, convexStack.peek(), myPoints.get(i))){
                top = convexStack.pop();
            }
            convexStack.push(top);
            convexStack.push(myPoints.get(i));
        }
        return new ArrayList<>(convexStack);
    }

    private MyPoint getRightMostPoint(List<MyPoint> myPoints) {
        myPoints.sort(new Comparator<MyPoint>() {
            @Override
            public int compare(MyPoint o1, MyPoint o2) {
                if (o1.y > o2.y){
                    return -1;
                }else if (o1.y < o2.y){
                    return 1;
                }else{
                    if (o1.x > o2.x){
                        return 1;
                    }else if (o1.x < o2.x){
                        return -1;
                    }
                }
                return 0;
            }
        });
        return myPoints.get(0);
    }


    private static class MyPoint implements Comparable<MyPoint>{
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






}
