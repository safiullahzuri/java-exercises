package weightedGraphs.review;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Ex29_15 extends Application {

    private Label lblVertex = new Label("Add a new vertex");
    private Label lblVertexName = new Label("Vertex Name");
    private TextField tfVertexName = new TextField();
    private Label lblXCoordinate = new Label("x-coordinate");
    private TextField tfXCoordinate = new TextField();
    private Label lblYCoordinate = new Label("y-coordinate");
    private TextField tfYCoordinate = new TextField();
    private Button btnAddVertex = new Button("Add Vertex");

    private Label lblNewEdge = new Label("Add a new Edge");
    private Label lblUIndex = new Label("vertex u (index):");
    private TextField tfVertexUIndex = new TextField();
    private Label lblVIndex = new Label("vertex v (index): ");
    private TextField tfVertexVIndex = new TextField();
    private Label lblWeight= new Label("Weight (int)");
    private TextField tfWeight= new TextField();
    private Button btnAddEdge = new Button("Add Edge");

    private Label lblFindShortestPath= new Label("Find a shortest path");
    private Label lblStartingVertex = new Label("Starting vertex: ");
    private TextField tfStartingVertex = new TextField();
    private Label lblEndingVertex = new Label("Ending vertex: ");
    private TextField tfEndingVertex = new TextField();
    private Button btnShortestPath= new Button("Shortest Path");

    private Button btnStartOver = new Button("Start Over");

    WeightedGraph<Vertex> graph = new WeightedGraph<>();
    GraphView graphView;

    List<Vertex> vertices = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        HBox hBox = new HBox();
        GridPane gp1 = new GridPane();
        gp1.add(lblVertex, 0, 0);
        gp1.add(lblVertexName, 0, 1);
        gp1.add(tfVertexName, 1, 1);
        gp1.add(lblXCoordinate, 0, 2);
        gp1.add(tfXCoordinate, 1, 2);
        gp1.add(lblYCoordinate, 0, 3);
        gp1.add(tfYCoordinate, 1, 3);
        gp1.add(btnAddVertex, 1, 4);

        hBox.getChildren().add(gp1);

        GridPane gp2 = new GridPane();
        gp2.add(lblNewEdge, 0, 0);
        gp2.add(lblUIndex, 0,1);
        gp2.add(tfVertexUIndex, 1,1);
        gp2.add(lblVIndex, 0,2);
        gp2.add(tfVertexVIndex, 1, 2);
        gp2.add(lblWeight, 0, 3);
        gp2.add(tfWeight, 1, 3);
        gp2.add(btnAddEdge, 1, 4);

        hBox.getChildren().add(gp2);

        GridPane gp3 = new GridPane();
        gp3.add(lblFindShortestPath, 0, 0);
        gp3.add(lblStartingVertex, 0, 1);
        gp3.add(tfStartingVertex, 1,1);
        gp3.add(lblEndingVertex, 0, 2);
        gp3.add(tfEndingVertex, 1, 2);
        gp3.add(btnShortestPath, 1, 3);
        hBox.getChildren().add(gp3);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(hBox);
        borderPane.setBottom(btnStartOver);

        defineEvents();

        BorderPane mainPane = new BorderPane();
        mainPane.setBottom(borderPane);

        graphView = new GraphView(graph, null);

        graphView.setOnMouseClicked(e->{

            Point2D point2D = new Point2D(e.getX(), e.getY());
            //if point is inside any of the previously added vertices, remove that vertex
            boolean add = true;
            for (Vertex vertex: graph.getVertices()){
                Circle circle = new Circle(vertex.x, vertex.y, 20);
                if (circle.contains(point2D)){
                    graph.removeVertex(vertex);
                    graphView.paint();
                    add = false;
                    break;
                }
            }
            if (add){
                addVertex(new Vertex(vn+"", e.getX(), e.getY()));
            }
        });

        mainPane.setCenter(graphView);
        mainPane.setPrefSize(750, 600);

        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    int vn = 0;
    private void addVertex(Vertex vertex){
        graph.addVertex(vertex);
        vn++;
        graphView.paint();
    }

    private void defineEvents(){
        btnAddVertex.setOnAction(e->{
            String name = tfVertexName.getText();
            int xC = Integer.parseInt(tfXCoordinate.getText());
            int yC = Integer.parseInt(tfYCoordinate.getText());
            graph.addVertex(new Vertex(name, xC, yC));
            graphView.paint();
        });

        btnAddEdge.setOnAction(e->{
            int u = Integer.parseInt(tfVertexUIndex.getText());
            int v = Integer.parseInt(tfVertexVIndex.getText());
            int w = Integer.parseInt(tfWeight.getText());
            graph.addEdge(u, v, w);
            graphView.paint();
        });

        btnShortestPath.setOnAction(e->{
            int u = Integer.parseInt(tfStartingVertex.getText());
            int v = Integer.parseInt(tfEndingVertex.getText());

            UnweightedGraph.SearchTree searchTree =  graph.getShortestPath(u, v);

            graphView.setTree(searchTree);
            graphView.paint();
        });
    }


    class Vertex implements Displayable{
        private String name;
        private double x, y;

        public Vertex(String name, double x, double y){
            this.name = name;
            this.x= x;
            this.y = y;
        }

        @Override
        public double getX() {
            return x;
        }

        @Override
        public double getY() {
            return y;
        }

        @Override
        public String getName() {
            return name+"";
        }
    }
}
