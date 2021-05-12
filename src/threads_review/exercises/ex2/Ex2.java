package threads_review.exercises.ex2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Ex2 extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        CarPane carPane = new CarPane();
        Scene scene = new Scene(carPane, 200, 200);
        primaryStage.setTitle("Ex2");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.widthProperty().addListener(e -> carPane.setW(carPane.getWidth()));
        scene.heightProperty().addListener(e -> carPane.setH(carPane.getHeight()));

        carPane.setOnMousePressed(e -> carPane.suspend());
        carPane.setOnMouseReleased(e -> carPane.resume());

        carPane.requestFocus();

        carPane.setOnKeyPressed(e->{
            if (e.getCode() == KeyCode.UP){
                carPane.faster();
            }else if (e.getCode() == KeyCode.DOWN){
                carPane.slower();
            }
        });

    }
}
