package AVLTREE_EXERCISES.EXERCISE3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class E26_03 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AVLVisualizer avlVisualizer = new AVLVisualizer();
        Scene scene = new Scene(avlVisualizer, 800, 400);
        primaryStage.setTitle("E26_06");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
