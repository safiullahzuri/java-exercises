package Ex22_Difficult_Questions;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ex22_16_v2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        HistogramEntireGui_v2 histogramEntireGui_v2 =new HistogramEntireGui_v2();

        Scene scene = new Scene(histogramEntireGui_v2);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
