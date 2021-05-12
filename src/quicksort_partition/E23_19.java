package quicksort_partition;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class E23_19  extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        PartitionVisualizer partitionVisualizer = new PartitionVisualizer();
        Scene scene = new Scene(partitionVisualizer);

        primaryStage.setTitle("E23_19");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
