package doublyLinkedlistAnimation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnimationPane animationPane = new AnimationPane();

        Scene scene = new Scene(animationPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
