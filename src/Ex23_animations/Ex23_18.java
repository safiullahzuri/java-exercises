package Ex23_animations;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ex23_18 extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        MergeAnimationWholePane mergeAnimationWholePane = new MergeAnimationWholePane();
        Scene scene = new Scene(mergeAnimationWholePane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
