package graphs.learning1;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.List;

public class GraphView extends Pane {

    private Graph<? extends Displayable> graph;

    public AbstractGraph<? extends Displayable>.Tree tree;

    public void setTree(AbstractGraph<? extends Displayable>.Tree tree) {
        this.tree = tree;
        //repaint the graph
        List<? extends Displayable> vertices = graph.getVertices();

        for (int i=0; i<graph.getSize(); i++){
            int x = vertices.get(i).getX();
            int y = vertices.get(i).getY();

            String name = vertices.get(i).getName();

            Circle circle = new Circle(x, y, 16);
            getChildren().add(circle);
            getChildren().add(new Text(x-8, y-18, name));
        }

        for (int i=0; i<graph.getSize(); i++){
            List<Integer> neighbors = graph.getNeighbors(i);

            int x1 = graph.getVertex(i).getX();
            int y1 = graph.getVertex(i).getY();

            for (int v: neighbors){
                int x2 = graph.getVertex(v).getX();
                int y2 = graph.getVertex(v).getY();
                Line line = new Line(x1, y1, x2, y2);
                getChildren().add(line);
            }
        }
        for (int v: tree.getSearchOrder()){
            if (tree.getParent(v) != -1){
                int x1 = graph.getVertex(tree.getParent(v)).getX();
                int y1 = graph.getVertex(tree.getParent(v)).getY();
                //find parent
                int x2 = graph.getVertex(v).getX();
                int y2 = graph.getVertex(v).getY();

                Line line = new Line(x1, y1, x2, y2);
                line.setStroke(Color.RED);
                getChildren().add(line);
            }
        }
    }

    public GraphView(Graph<? extends Displayable> graph){
        this.graph = graph;
//
//        List<? extends Displayable> vertices = graph.getVertices();
//
//        for (int i=0; i<graph.getSize(); i++){
//            int x = vertices.get(i).getX();
//            int y = vertices.get(i).getY();
//
//            String name = vertices.get(i).getName();
//
//            getChildren().add(new Circle(x, y, 16));
//            getChildren().add(new Text(x-8, y-18, name));
//        }
//
//        for (int i=0; i<graph.getSize(); i++){
//            List<Integer> neighbors = graph.getNeighbors(i);
//
//            int x1 = graph.getVertex(i).getX();
//            int y1 = graph.getVertex(i).getY();
//
//            for (int v: neighbors){
//                int x2 = graph.getVertex(v).getX();
//                int y2 = graph.getVertex(v).getY();
//
//                getChildren().add(new Line(x1, y1, x2, y2));
//            }
//        }

    }

}
