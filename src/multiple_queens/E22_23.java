package multiple_queens;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class E22_23 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MultipleBoardPane multipleBoardPane = new MultipleBoardPane();
        Scene scene = new Scene(multipleBoardPane);
        primaryStage.setScene(scene);
        primaryStage.show();;
    }
}
