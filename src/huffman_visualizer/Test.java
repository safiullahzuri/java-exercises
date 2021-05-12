package huffman_visualizer;

import arraylist_animation.App;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TreeVisualizer tv = new TreeVisualizer();

        Scene scene = new Scene(tv, 800, 600);
        primaryStage.setTitle("E25_17");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
