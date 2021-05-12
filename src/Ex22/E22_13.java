package Ex22;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class E22_13 extends Application {
    @Override
    public void start(Stage primaryStage) {
        HullPane hp = new HullPane();

        Scene scene = new Scene(hp);
        primaryStage.setTitle("E22_13");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}