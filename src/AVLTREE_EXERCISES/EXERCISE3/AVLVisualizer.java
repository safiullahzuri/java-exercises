package AVLTREE_EXERCISES.EXERCISE3;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class AVLVisualizer extends BorderPane {

    public AVLVisualizer(){
        drawGUI();
    }

    private void drawGUI(){
        AVLPane avlPane = new AVLPane();
        avlPane.setStyle("-fx-border-color:black");

        Text tEnter = new Text("Enter a key: ");
        TextField tfEnter = new TextField();

        tfEnter.setPrefColumnCount(5);
        Button btnSearch = new Button("Search");
        Button btnInsert = new Button("Insert");
        Button btnRemove = new Button("Remove");

        btnSearch.setOnAction(e->{
            if (tfEnter.getText().isEmpty())return;
            avlPane.search(Integer.parseInt(tfEnter.getText()));
        });

        btnInsert.setOnAction(e->{
            if (tfEnter.getText().isEmpty())return;
            avlPane.insert(Integer.parseInt(tfEnter.getText()));
        });

        btnRemove.setOnAction(e->{
            if (tfEnter.getText().isEmpty())return;
            avlPane.delete(Integer.parseInt(tfEnter.getText()));
        });

        HBox hbControl = new HBox(10);
        hbControl.getChildren().addAll(tEnter, tfEnter, btnSearch, btnInsert, btnRemove);
        hbControl.setAlignment(Pos.CENTER);

        setCenter(avlPane);
        setBottom(hbControl);
    }

}
