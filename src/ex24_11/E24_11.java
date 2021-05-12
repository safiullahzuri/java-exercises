package ex24_11;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class E24_11 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        DoublyLinkedListVisualizer dv = new DoublyLinkedListVisualizer();

        Scene scene = new Scene(dv);
        primaryStage.setTitle("E24_11");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
