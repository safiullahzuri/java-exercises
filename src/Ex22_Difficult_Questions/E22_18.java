package Ex22_Difficult_Questions;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class E22_18 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        HistogramGUI histogramGUI = new HistogramGUI();
        Scene scene = new Scene(histogramGUI);
        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
