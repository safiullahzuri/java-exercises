package graphs.learning2;

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
        primaryStage.setTitle("Connected Circles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    class CirclePane extends Pane{

        public CirclePane(){
            setOnMouseClicked(e->{
                if (!isInsideACircle(new Point2D(e.getX(), e.getY()))){
                    getChildren().add(new Circle(e.getX(), e.getY(), 20));
                    colorIfConnected();
                }else{
//                    for (Node node: getChildren()){
//                        if (node.contains(e.getX(), e.getY())){
//                            getChildren().remove(node);
//                            System.out.println("node removed");
//                            colorIfConnected();
//                            break;
//                        }
//                    }
                }
            });

            setOnMouseDragged(e->{
                for (Node circle: getChildren()){
                    if (circle.contains(new Point2D(e.getX(), e.getY()))){
                        ((Circle) circle).setCenterX(e.getX());
                        ((Circle) circle).setCenterY(e.getY());
                        break;
                    }
                }
                colorIfConnected();
            });
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
            boolean connected = getChildren().size() == tree.getNumberOfVerticesFound();

            for (Node circle: getChildren()){
                if (!connected){
                    ((Circle) circle).setStroke(Color.BLACK);
                    ((Circle) circle).setFill(Color.WHITE);
                }else{
                    ((Circle) circle).setFill(Color.RED);
                }
            }

        }

        private boolean isInsideACircle(Point2D p){
            for (Node circle: getChildren()){
                if (circle.contains(p)){
                    return true;
                }
            }
            return false;
        }
    }




    public static boolean overlaps(Circle circle1, Circle circle2){
        return new Point2D(circle1.getCenterX(), circle1.getCenterY()).distance(new Point2D(circle2.getCenterX(), circle2.getCenterY()))
                <= circle1.getRadius() + circle2.getRadius();
    }


}
