package hashing.exercises.ex8;

import hashing.exercises.ex6.LinearProbingMapBoard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();

        HBox hControl = new HBox();
        Text text = new Text("Enter the key: ");

        TextField tfKey = new TextField();
        Text  text1 = new Text("Enter a value: ");
        TextField tfValue = new TextField();

        Button insert = new Button("Insert");
        Button delete = new Button("Delete");
        Button removeAll = new Button("Remove all");


        hControl.getChildren().addAll(text, tfKey, text1, tfValue, insert, delete, removeAll);

        LinearProbingMapBoard board = new LinearProbingMapBoard();

        borderPane.setCenter(board);
        borderPane.setBottom(hControl);


        insert.setOnAction(e ->{
            board.insert(Integer.parseInt(tfKey.getText()), Integer.parseInt(tfValue.getText()));
        });

        delete.setOnAction(e->{
            board.delete(Integer.parseInt(tfKey.getText()));
        });

        removeAll.setOnAction(e->{
            board.deleteAll();
        });

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
