package huffman_code3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ex25_15 extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        TreeVisualizer tv = new TreeVisualizer();
        Scene scene = new Scene(tv, 800, 600);

        primaryStage.setTitle("E25_17");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
