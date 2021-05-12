import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane borderPane = new Pane();

        borderPane.setStyle("-fx-border-color:red;");
        borderPane.setPrefSize(500,500);

        Rectangle rectangle = new Rectangle(10,100, 100, 200);

        borderPane.getChildren().add(rectangle);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
