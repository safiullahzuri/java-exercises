package graphs.learning2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DisplayUsMap extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        City[] vertices = {
                new City( 75, 150, "Seattle"),
                new City(50, 210, "San Francisco"),
                new City(450, 100, "Chicago"),
                new City(600, 400, "Miami")
        };

        int[][] edges = {
                {0,1},{0,3},
                {1,2},
                {2,0},
                {3,2}
        };

        GraphView graphView = new GraphView(new UnweightedGraph<>(vertices, edges));

        Scene scene = new Scene(graphView, 750, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static class City implements Displayable{

        int x,y;
        String name;

        public City(int x, int y, String name){
            this.x = x;
            this.y = y;
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
