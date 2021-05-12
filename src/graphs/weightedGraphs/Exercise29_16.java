package graphs.weightedGraphs;

import Ex22_Difficult_Questions.LargestBlockGUI;
import graphs.learning2.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.List;

public class Exercise29_16 extends Application {

    private TextField tfVertexName = new TextField();
    private TextField tfX = new TextField();
    private TextField tfY = new TextField();
    private Button btnAddVertex = new Button("Add Vertex");

    private TextField tfu = new TextField();
    private TextField tfv = new TextField();
    private TextField tfw = new TextField();
    private Button btnAddEdge = new Button("Add Edge");

    private Button btnStartOver = new Button("Start Over");
    private Label lblStatus = new Label();

    private WeightedGraph<Vertex> graph = new WeightedGraph<>();
    private GraphView graphView = new GraphView(graph);

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane gridPane1 = new GridPane();
        gridPane1.add(new Label("Add new vertex"), 0, 0);
        gridPane1.add(new Label("Vertex name: "), 0, 1);
        gridPane1.add(new Label("x-coordinate: "), 0, 2);
        gridPane1.add(new Label("y-coordinate: "), 0, 3);
        gridPane1.add(tfVertexName, 1,1);
        gridPane1.add(tfX,1,2);
        gridPane1.add(tfY, 1,3);
        gridPane1.add(btnAddVertex, 1, 4);


        GridPane gridPane2 = new GridPane();

        gridPane2.add(new Label("Add a new edge"), 0,0);
        gridPane2.add(new Label("Vertex u (index): "), 0, 1);
        gridPane2.add(new Label("Vertex v (index): "), 0, 2);
        gridPane2.add(new Label("Weight: "), 0, 3);

        gridPane2.add(tfu, 1, 1);
        gridPane2.add(tfv, 1, 2);
        gridPane2.add(tfw, 1, 3);
        gridPane2.add(btnAddEdge, 1,4);

        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(gridPane1, gridPane2);

        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(hBox, btnStartOver);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(lblStatus);
        borderPane.setCenter(graphView);
        borderPane.setBottom(vBox);

        BorderPane.setAlignment(lblStatus, Pos.CENTER);

        Scene scene=  new Scene(borderPane, 650, 350);
        primaryStage.setTitle("title");
        primaryStage.setScene(scene);
        primaryStage.show();



        btnAddVertex.setOnAction(e->{
            try{
                String name = tfVertexName.getText();
                if (graph.getSize() != Integer.parseInt(name.trim())){
                    lblStatus.setText("The next vertex to be added must be index "+graph.getSize());
                }else {
                    double x = Integer.parseInt(tfX.getText().trim());
                    double y = Integer.parseInt(tfY.getText().trim());

                    graph.addVertex(new Vertex((int) x,(int) y, name));
                    graphView.paint();
                }
            }catch (Exception e1){
                lblStatus.setText("The input must be an integer index.");
                e1.printStackTrace();
            }
        });

        btnAddEdge.setOnAction(e->{
            try{

                int u = Integer.parseInt(tfu.getText().trim());
                int v = Integer.parseInt(tfv.getText().trim());
                double weight = Integer.parseInt(tfw.getText().trim());



                if (u<0 || u>= graph.getSize()){
                    lblStatus.setText("Vertex "+u+" is not in the graph.");
                }else if (v<0 || v>=graph.getSize()){
                    lblStatus.setText("Vertex "+v+" is not in the graph");
                }else if (u == v){
                    lblStatus.setText("Two vertices cannot be the same.");
                }else{
                    graph.addEdge(u, v, weight);
                    graph.addEdge(v, u, weight);
                    graphView.setTree(graph.getMinimumSpanningTree());
                    graphView.paint();
                }
            }catch (Exception ex){
                lblStatus.setText("The input must be an integer index.");
            }
        });

        btnStartOver.setOnAction(e->{
            graph.clear();
            graphView.paint();
        });



    }

    class GraphView extends Pane{
        private Graph<? extends Displayable> graph;
        private UnweightedGraph<? extends Displayable>.Tree tree;

        public GraphView(Graph<? extends Displayable> graph){
            this.graph = graph;
            paint();
        }

        public void setTree(AbstractGraph<? extends Displayable>.Tree tree) {
            this.tree = tree;
            paint();
        }

        protected void paint(){
            getChildren().clear();

            List<? extends Displayable> vertices = graph.getVertices();

            for (int i=0; i<graph.getSize(); i++){
                int x = vertices.get(i).getX();
                int y = vertices.get(i).getY();
                String name = vertices.get(i).getName();

                getChildren().addAll(new Circle(x, y, 8), new Text(x-12, y-12, name));
            }

            for (int i=0; i<graph.getSize(); i++){
                List<Integer> neighbors = graph.getNeighbors(i);
                for (int j=0; j<neighbors.size(); j++){
                    int v = neighbors.get(j);

                    int x1 = graph.getVertex(i).getX();
                    int y1 = graph.getVertex(i).getY();

                    int x2 = graph.getVertex(v).getX();
                    int y2 = graph.getVertex(v).getY();

                    try {
                        getChildren().addAll(new Line(x1, y1, x2, y2),
                                new Text((x1+x2)/2-4, (y1+y2)/2-6, ((WeightedGraph)graph).getWeight(i,v)+""));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if (tree != null){
                for (int i=0; i<tree.getNumberOfVerticesFound(); i++){
                    if (tree.getParent(i) != -1){
                        int v = tree.getParent(i);

                        int x1 = graph.getVertex(i).getX();
                        int y1 = graph.getVertex(i).getY();

                        int x2 = graph.getVertex(v).getX();
                        int y2 = graph.getVertex(v).getY();

                        Line line = new Line(x1, y1, x2, y2);
                        line.setStroke(Color.RED);
                        getChildren().add(line);
                    }
                }
            }


        }

    }

    class Vertex implements Displayable{
        private int x,y;
        private String name;

        public Vertex(int x, int y, String name){
            this.x = x;
            this.y= y;
            this.name = name;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
