package heap_visualisation;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.geometry.Pos;

public class HeapVisualizer extends BorderPane {
    public HeapVisualizer() {
        drawGUI();
    }

    private void drawGUI() {
        HeapPane hPane = new HeapPane();
        Text tEnterKey = new Text("Enter a key:");
        TextField tfEnterKey = new TextField();
        Button btInsert = new Button("Insert");
        Button btRemoveRoot = new Button("Remove the root");

        btInsert.setOnAction(e -> insert(hPane,
                Integer.parseInt(tfEnterKey.getText())));
        btRemoveRoot.setOnAction(e -> removeRoot(hPane));

        tfEnterKey.setPrefColumnCount(4);

        HBox hbControl = new HBox(10);
        hbControl.getChildren().addAll(tEnterKey, tfEnterKey, btInsert,
                btRemoveRoot);
        hbControl.setAlignment(Pos.CENTER);

        setCenter(hPane);
        setBottom(hbControl);
    }

    private void insert(HeapPane h, int n) {
        h.insert(n);
    }

    private void removeRoot(HeapPane h) {
        h.removeRoot();
    }
}
