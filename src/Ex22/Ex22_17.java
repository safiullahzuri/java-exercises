package Ex22;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Ex22_17 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new Pane2());
        primaryStage.setScene(scene);
        primaryStage.show();
    }



}

class Pane2 extends Pane {

    public ArrayList<Circle> circles;
    private Line line;


    public Pane2(){
        line = new Line();
        line.setFill(Color.BLACK);
        circles = new ArrayList<>();
        this.setOnMouseClicked(e-> clicked(e));
    }

    private void clicked(MouseEvent e){
        Circle circle = new Circle(e.getX(), e.getY(), 10);
        circles.add(circle);
        this.getChildren().add(circle);

        if (circles.size() > 1){
            drawTheClosestPair();
        }
    }

    public void drawTheClosestPair(){
        this.getChildren().remove(line);
        line = closestLine();
        this.getChildren().add(line);
        System.out.println(line);
    }


    private Line closestLine(){
        Circle starting = circles.get(0);
        Circle ending = circles.get(1);
        for (int i=0; i<circles.size()-1; i++){
            for ( int j= i+1; j<circles.size(); j++){
                if (distance(circles.get(i), circles.get(j)) < distance(starting, ending)){
                    starting = circles.get(i);
                    ending = circles.get(j);
                }
            }
        }
        return new Line(starting.getCenterX(), starting.getCenterY(), ending.getCenterX(), ending.getCenterY());
    }

    private double distance(Circle circle1, Circle circle2){
        return Math.sqrt(Math.pow(circle1.getCenterY()-circle2.getCenterY(), 2)+Math.pow(circle1.getCenterX()-circle2.getCenterX(), 2));
    }


}

class Pair3{
    Point2 p1,p2;
    public Pair3(Point2 p1, Point2 p2){
        this.p1 = p1;
        this.p2 = p2;
    }
}

class Point2 implements Comparable<Point2>{

    double x, y;
    public Point2(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point2 o) {
        if (this.x > o.x){
            return 1;
        }else if (this.x < o.x){
            return -1;
        }else{
            return 0;
        }
    }
}
