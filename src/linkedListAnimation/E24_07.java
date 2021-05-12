package linkedListAnimation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class E24_07 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        LinkedListVisualizer2 lv = new LinkedListVisualizer2();

        Scene scene = new Scene(lv);
        primaryStage.setTitle("E24_07");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
