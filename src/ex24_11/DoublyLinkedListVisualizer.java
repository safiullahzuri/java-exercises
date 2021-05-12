package ex24_11;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class DoublyLinkedListVisualizer extends BorderPane {
    public DoublyLinkedListVisualizer() {
        drawGUI();
    }

    private void drawGUI() {
        DoublyLinedListPane dPane = new DoublyLinedListPane();
        dPane.setStyle("-fx-border-color: black");

        Text tEnter = new Text("Enter a value:");
        Text tIndex = new Text("Enter an index:");
        TextField tfEnter = new TextField();
        TextField tfIndex = new TextField();
        tfEnter.setPrefColumnCount(3);
        tfIndex.setPrefColumnCount(3);
        Button btSearch = new Button("Search");
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        Button btForward = new Button("Forward Traversal");
        Button btBackward = new Button("Backward Traversal");

        btSearch.setOnAction(e -> {
            if (tfEnter.getText().isEmpty()) {
                return;
            }
            dPane.search(Integer.parseInt(tfEnter.getText()));
        });

        btInsert.setOnAction(e -> {
            if (tfEnter.getText().isEmpty()) {
                return;
            }
            if (tfIndex.getText().isEmpty()) {
                dPane.insert(Integer.parseInt(tfEnter.getText()), -1);
            } else {
                dPane.insert(Integer.parseInt(tfEnter.getText()),
                        Integer.parseInt(tfIndex.getText()));
            }
        });

        btDelete.setOnAction(e -> {
            if (tfEnter.getText().isEmpty()) {
                return;
            }
            dPane.delete(Integer.parseInt(tfEnter.getText()));
        });

        btForward.setOnAction(e -> dPane.forwardTraversal());

        btBackward.setOnAction(e -> dPane.backwardTraversal());

        HBox hbControl = new HBox(10);
        hbControl.getChildren().addAll(tEnter, tfEnter, tIndex, tfIndex,
                btSearch, btInsert, btDelete, btForward, btBackward);
        hbControl.setAlignment(Pos.CENTER);

        setCenter(dPane);
        setBottom(hbControl);
    }
}

