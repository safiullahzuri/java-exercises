package AVLTREE_EXERCISES.EXERCISE1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class E26_01 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Integer[] list = {78, 2, 5, 18, 24, -67, 32, -1, 0, 5};
        AVLTree<Integer> tree = new AVLTree<>(list);

        AVLPane avlPane = new AVLPane(tree);

        Scene scene = new Scene(avlPane, 800, 600);
        primaryStage.setTitle("E26_01");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
