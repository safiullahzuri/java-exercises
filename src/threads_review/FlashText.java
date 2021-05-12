package threads_review;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FlashText extends Application {

    private String text = "";

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane stackPane = new StackPane();
        Label label = new Label("Welcome");
        stackPane.getChildren().add(label);


        new Thread(()->{
            try {
                while (true) {
                    if (label.getText().length() == 0){
                        text = "Welcome";
                    }else{
                        text = "";
                    }

                    Platform.runLater(()->{
                        label.setText(text);
                    });
                    Thread.sleep(333);
                }
            }catch (Exception e){
                e.printStackTrace();
            }


        }).start();

        Scene scene = new Scene(stackPane, 200, 50);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
