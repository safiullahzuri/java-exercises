package graphs.learning2;


import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class ConnectedRectangles extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new RectanglePane(), 450, 350);
        primaryStage.setTitle("Connected Circles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    class RectanglePane extends Pane{

        public RectanglePane(){
            setOnMouseClicked(e->{
                if (!isInsideACircle(new Point2D(e.getX(), e.getY()))){
                    Rectangle rectangle = new Rectangle(e.getX(), e.getY(), 20, 20);
                    rectangle.setFill(Color.WHITE);
                    rectangle.setStroke(Color.BLACK);
                    getChildren().add(rectangle);
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
                for (Node rectangle: getChildren()){
                    if (rectangle.contains(new Point2D(e.getX(), e.getY()))){
                        ((Rectangle) rectangle).setX(e.getX());
                        ((Rectangle) rectangle).setY(e.getY());
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
                    if (overlaps((Rectangle)(getChildren().get(i)), (Rectangle)(getChildren().get(j)))){
                        edges.add(new Edge(i, j));
                        edges.add(new Edge(j, i));
                    }
                }
            }

            Graph<Node> graph = new UnweightedGraph<>(getChildren(), edges);
            AbstractGraph<Node>.Tree tree = graph.dfs(0);
            boolean connected = getChildren().size() == tree.getNumberOfVerticesFound();

            for (Node rectangle: getChildren()){
                if (!connected){
                    ((Rectangle) rectangle).setStroke(Color.BLACK);
                    ((Rectangle) rectangle).setFill(Color.WHITE);
                }else{
                    ((Rectangle) rectangle).setFill(Color.RED);
                }
            }

        }

        private boolean isInsideACircle(Point2D p){
            for (Node rectangle: getChildren()){
                if (rectangle.contains(p)){
                    return true;
                }
            }
            return false;
        }
    }




    public static boolean overlaps(Rectangle rectangle1, Rectangle rectangle2){
        return new Point2D(rectangle1.getX(), rectangle1.getY()).distance(new Point2D(rectangle2.getX(), rectangle2.getY()))
                <= rectangle1.getWidth() + rectangle2.getWidth();
    }


}
