package graphs.learning2;

import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.List;

public class GraphView extends BorderPane {

    private Graph<? extends Displayable> graph;
    private Group group = new Group();

    public GraphView(Graph<? extends Displayable> graph){
        this.graph = graph;
        this.setCenter(group);

        repaintGraph();
    }


    private void repaintGraph(){
        group.getChildren().clear();

        List<? extends Displayable> vertices = graph.getVertices();

        for (int i=0; i<vertices.size(); i++){
            int x = vertices.get(i).getX();
            int y = vertices.get(i).getY();
            String name = vertices.get(i).getName();

            group.getChildren().add(new Circle(x, y, 16));
            group.getChildren().add(new Text(x-8, y-16, name));
        }

        for (int i=0; i<vertices.size(); i++){
            List<Integer> neighbors =  graph.getNeighbors(i);

            int x1 = vertices.get(i).getX();
            int y1 = vertices.get(i).getY();

            for (int v: neighbors){
                int x2 = vertices.get(v).getX();
                int y2 = vertices.get(v).getY();

                group.getChildren().add(new Line(x1,y1, x2, y2));
            }
        }

    }


}
