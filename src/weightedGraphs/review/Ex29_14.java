package weightedGraphs.review;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Ex29_14 extends Application {

    private City[] vertices = { new City("Seattle", 75, 50),
            new City("San Francisco", 50, 210),
            new City("Los Angeles", 75, 275), new City("Denver", 275, 175),
            new City("Kansas City", 400, 245),
            new City("Chicago", 450, 100), new City("Boston", 700, 80),
            new City("New York", 675, 120), new City("Atlanta", 575, 295),
            new City("Miami", 600, 400), new City("Dallas", 408, 325),
            new City("Houston", 450, 360) };

    private int[][] edges = {
            {0, 1, 807}, {0, 3, 1331}, {0, 5, 2097},
            {1, 0, 807}, {1, 2, 381}, {1, 3, 1267},
            {2, 1, 381}, {2, 3, 1015}, {2, 4, 1663}, {2, 10, 1435},
            {3, 0, 1331}, {3, 1, 1267}, {3, 2, 1015}, {3, 4, 599},
            {3, 5, 1003},
            {4, 2, 1663}, {4, 3, 599}, {4, 5, 533}, {4, 7, 1260},
            {4, 8, 864}, {4, 10, 496},
            {5, 0, 2097}, {5, 3, 1003}, {5, 4, 533},
            {5, 6, 983}, {5, 7, 787},
            {6, 5, 983}, {6, 7, 214},
            {7, 4, 1260}, {7, 5, 787}, {7, 6, 214}, {7, 8, 888},
            {8, 4, 864}, {8, 7, 888}, {8, 9, 661},
            {8, 10, 781}, {8, 11, 810},
            {9, 8, 661}, {9, 11, 1187},
            {10, 2, 1435}, {10, 4, 496}, {10, 8, 781}, {10, 11, 239},
            {11, 8, 810}, {11, 9, 1187}, {11, 10, 239}
    };

    private WeightedGraph<City> graph1 = new WeightedGraph<>(vertices, edges);
    private GraphView graphView = new GraphView(graph1, graph1.getMinimumSpanningTree());

    private Label lblStatus = new Label();
    private TextField tfStartCity = new TextField();
    private TextField tfEndCity = new TextField();
    private Button btnSP = new Button("Display Shortest Path");

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(graphView);

        BorderPane.setAlignment(lblStatus, Pos.CENTER);
        Scene scene = new Scene(borderPane, 450, 350);
        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    class City implements Displayable {
        private double x, y;
        private String name;

        City(String name, double x, double y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public String getName() {
            return name;
        }

        public boolean equals(Object o) {
            return ((City)o).name.equals(this.name);
        }
    }
}
