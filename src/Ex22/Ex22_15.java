package Ex22;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ex22_15 extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        MyPane pane2 = new MyPane();
        NCPP ncpp = new NCPP();
        Scene scene = new Scene(pane2);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static class MyPane extends Pane{

        List<Circle> circleList;
        Polygon polygon;

        public MyPane(){
            polygon = new Polygon();
            polygon.setStroke(Color.BLACK);
            polygon.setFill(Color.TRANSPARENT);
            circleList = new ArrayList<>();
            this.setOnMouseClicked(e-> addPoint(e));
        }

        private void addPoint(MouseEvent e) {
            Circle circle = new Circle(e.getX(), e.getY(), 5);
            circle.setOnMouseClicked(ev -> removeCircle(ev));
            this.getChildren().add(circle);

            circleList.add(circle);
            if (circleList.size() > 2){
                //draw the polygon
                drawPolygon();
                System.out.println("draw polygon");
            }
        }

        private void drawPolygon(){

            polygon.getPoints().clear();
            this.getChildren().remove(polygon);

            List<MyPoint> points = new ArrayList<>();
            for (Circle c: circleList){
                points.add(new MyPoint(c.getCenterX(), c.getCenterY()));
            }
            System.out.println(circleList.size());

            MyPoint rightmostLowest = getRightmostLowest(points);
            for (MyPoint mp: points){
                mp.setRightmostPoint(rightmostLowest);
            }

            Collections.sort(points);

            for (MyPoint mp: points){
                polygon.getPoints().addAll(mp.x, mp.y);
            }
            this.getChildren().add(polygon);


        }



        private MyPoint getRightmostLowest(List<MyPoint> points){
            points.sort(new Comparator<MyPoint>() {
                @Override
                public int compare(MyPoint o1, MyPoint o2) {
                    if (o1.y < o2.y){
                        return 1;
                    }else if (o1.y > o2.y){
                        return -1;
                    }else {
                        if (o1.x > o2.x){
                            return 1;
                        }else if (o1.x < o2.x){
                            return -1;
                        }
                    }
                    return 0;
                }
            });
            return points.get(0);
        }


        private void removeCircle(MouseEvent e){
            Circle circle = (Circle) e.getSource();
            this.getChildren().remove(circle);
        }


    }
}
