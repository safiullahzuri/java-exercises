package binarySearchTree.ex25_13;

import binarySearchTree.BST;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        BST<Integer> tree = new BST<>();
        BSTAnimation animation = new BSTAnimation(tree);
        Scene scene = new Scene(animation);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
