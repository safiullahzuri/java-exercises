package weightedGraphs.review;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.List;

public class GraphView extends Pane {

    private Graph<? extends Displayable> graph;
    private UnweightedGraph<? extends Displayable>.SearchTree tree;

    public GraphView(Graph<? extends Displayable> graph,
                     UnweightedGraph<? extends Displayable>.SearchTree tree){
        this.graph = graph;
        this.tree = tree;


        paint();
    }

    public void setTree(UnweightedGraph<? extends Displayable>.SearchTree tree){
        this.tree = tree;
        paint();
    }

    protected void paint(){
        List<? extends Displayable> vertices = graph.getVertices();

        for (int i=0; i<graph.getSize(); i++){
            double x = vertices.get(i).getX();
            double y = vertices.get(i).getY();
            String name = vertices.get(i).getName();

            getChildren().addAll(new Circle(x, y, 20),
                    new Text(x-24, y-24, name));
        }

        //display edges and weights
        for (int i=0; i<graph.getSize(); i++){
            List<Integer> neighbors = graph.getNeighbors(i);

            for (int j=0; j<neighbors.size(); j++){
                int v = neighbors.get(j);

                double x1 = graph.getVertex(i).getX();
                double y1 = graph.getVertex(i).getY();

                double x2 = graph.getVertex(v).getX();
                double y2 = graph.getVertex(v).getY();

                try {
                    getChildren().add(new Line(x1, y1, x2, y2));
                    getChildren().add(new Text((x1+x2)/2, (y1+y2)/2-6, ((WeightedGraph)graph).getWeight(i, v)+""));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        if (tree == null) return;

        for (int i=0; i<graph.getSize(); i++){
            if (tree.getParent(i) != -1){
                int v = tree.getParent(i);

                double x1 = graph.getVertex(i).getX();
                double y1 = graph.getVertex(i).getY();

                double x2 = graph.getVertex(v).getX();
                double y2 = graph.getVertex(v).getY();

                Line treeLine = new Line(x1, y1, x2, y2);
                treeLine.setStroke(Color.RED);
                getChildren().add(treeLine);
            }
        }


    }



}
