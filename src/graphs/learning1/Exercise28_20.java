package graphs.learning1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise28_20 extends Application {
    private List<Vertex> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();


    @Override
    public void start(Stage primaryStage) throws Exception {
        readGraph();
        Graph<Vertex> graph = new UnweightedGraph<>(vertices, edges);

        GraphView graphView = new GraphView(graph);
        Scene scene = new Scene(graphView, 250, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void readGraph(){
        try {
            System.out.println("Enter file name: ");
            Scanner input = new Scanner(System.in);
            String fileName = input.nextLine();

            Scanner file = new Scanner(new File(fileName));

            int n = Integer.parseInt(file.nextLine());

            for (int i=0; i<n; i++){
                String[] tokens = file.nextLine().split("[\\s+]");
                int x = Integer.parseInt(tokens[1].trim());
                int y = Integer.parseInt(tokens[2].trim());

                vertices.add(new Vertex(i+"", x, y));

                for (int k=3; k< tokens.length; k++){
                    edges.add(new Edge(i, Integer.parseInt(tokens[k].trim())));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class Vertex implements Displayable{

        private int x, y;
        private String name;

        Vertex(String name, int x, int y){
            this.name = name;
            this.x = x;
            this.y = y;
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
