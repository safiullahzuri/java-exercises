package arraylist_animation;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TranslateTransitionExample extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Circle circle = new Circle(50, 100, 50);
        circle.setFill(Color.RED);
        circle.setStroke(Color.BLACK);

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setByX(400);
        translateTransition.setDuration(Duration.millis(400));
        translateTransition.setCycleCount(5);
        translateTransition.setAutoReverse(false);
        translateTransition.setNode(circle);
        translateTransition.play();

        Group root = new Group();
        root.getChildren().addAll(circle);
        Scene scene = new Scene(root,500,200,Color.WHEAT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Translate Transition example");
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}