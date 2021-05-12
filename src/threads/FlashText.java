package threads;

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
        Label lblText = new Label("Programming is fun!");
        stackPane.getChildren().add(lblText);
        String text = "";
        new Thread(()->{
            try {
                String text2 = "";
                while (true){
                    if (lblText.getText().length() == 0){
                       text2 = "Welcome";
                    }else{
                        text2 = "";
                    }
                    String finalText = text2;
                    Platform.runLater(()->{
                        lblText.setText(finalText);
                    });
                    Thread.sleep(500);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        Scene scene = new Scene(stackPane, 200, 50);
        primaryStage.setTitle("Flash Text");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
