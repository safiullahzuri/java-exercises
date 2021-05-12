package graphs.learning;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ConnectedCircles extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new CirclePane(), 450, 350);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class CirclePane extends Pane {

        public CirclePane(){
            this.setOnMouseClicked(e->{
                if (!isInsideACircle(new Point2D(e.getX(), e.getY()))){
                    getChildren().add(new Circle(e.getX(), e.getY(), 20));
                    colorIfConnected();
                }
            });
        }

        private boolean isInsideACircle(Point2D point2D){
            for (Node circle: this.getChildren()){
                if (circle.contains(point2D)){
                    return true;
                }
            }
            return false;
        }

        private void colorIfConnected(){

            if (getChildren().size() == 0){
                return;
            }

            List<Edge> edges = new ArrayList<>();
            for (int i=0; i<getChildren().size(); i++){
                for (int j=i+1; j<getChildren().size(); j++){
                    if (overlaps((Circle)(getChildren().get(i)), (Circle)(getChildren().get(j)))){
                        edges.add(new Edge(i, j));
                        edges.add(new Edge(j, i));
                    }
                }
            }

            Graph<Node> graph = new UnweightedGraph<>(getChildren(), edges);
            AbstractGraph<Node>.Tree tree = graph.dfs(0);

            boolean allCirclesConnected = getChildren().size() == tree.getNumberOfVerticesFound();

            for (Node circle: getChildren()){
                if (allCirclesConnected){
                    ((Circle) circle).setFill(Color.RED);
                }else{
                    ((Circle) circle).setFill(Color.WHITE);
                    ((Circle) circle).setStroke(Color.BLACK);
                }
            }

        }



    }
    public static boolean overlaps(Circle circle1, Circle circle2){
        return new Point2D(circle1.getCenterX(), circle1.getCenterY())
                .distance(circle2.getCenterX(), circle2.getCenterY())
                <= circle1.getRadius() + circle2.getRadius();
    }


}
