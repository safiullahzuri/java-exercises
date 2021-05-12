package linkedListAnimation;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class LinkedListVisualizer extends BorderPane {

    public LinkedListVisualizer() {
        drawGUI();
    }

    private void drawGUI() {
        LinkedListPane linkedPane = new LinkedListPane();
        linkedPane.setStyle("-fx-border-color: black");

        Text tEnter = new Text("Enter a value:");
        Text tIndex = new Text("Enter an index:");
        TextField tfEnter = new TextField();
        TextField tfIndex = new TextField();
        tfEnter.setPrefColumnCount(3);
        tfIndex.setPrefColumnCount(3);
        Button btSearch = new Button("Search");
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");

        btSearch.setOnAction(e -> {
            if (tfEnter.getText().isEmpty()) { return; }
            linkedPane.search(Integer.parseInt(tfEnter.getText()));
        });

        btInsert.setOnAction(e -> {
            if (tfEnter.getText().isEmpty()) { return; }
            if (tfIndex.getText().isEmpty()) {
                linkedPane.insert(Integer.parseInt(tfEnter.getText()), -1);
            } else {
                linkedPane.insert(Integer.parseInt(tfEnter.getText()),
                        Integer.parseInt(tfIndex.getText()));
            }
        });

        btDelete.setOnAction(e -> {
            if (tfEnter.getText().isEmpty()) { return; }
            linkedPane.delete(Integer.parseInt(tfEnter.getText()));
        });

        HBox hbControl = new HBox(10);
        hbControl.getChildren().addAll(tEnter, tfEnter, tIndex, tfIndex,
                btSearch, btInsert, btDelete);
        hbControl.setAlignment(Pos.CENTER);

        setCenter(linkedPane);
        setBottom(hbControl);
    }
}
