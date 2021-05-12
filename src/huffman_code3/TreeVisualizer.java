package huffman_code3;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TreeVisualizer extends BorderPane {

    public TreeVisualizer(){
        drawGUI();
    }

    private void drawGUI(){
        TreePane treePane = new TreePane();
        Text tEnterText = new Text("Enter a text: ");
        Text tEnterBitString = new Text("Enter a bit string: ");
        TextField tfEnterText = new TextField();
        TextField tfEnterBitString = new TextField();

        Button btnShow = new Button("Show Huffman Tree");
        Button btnDecode = new Button("Decode to Text");

        HBox hbText = new HBox(10);
        hbText.getChildren().addAll(tEnterText, tfEnterText, btnShow);
        hbText.setAlignment(Pos.CENTER);

        HBox hbBit = new HBox(10);
        hbBit.getChildren().addAll(tEnterBitString, tfEnterBitString, btnDecode);
        hbBit.setAlignment(Pos.CENTER);

        VBox vbControl = new VBox();
        vbControl.getChildren().addAll(hbText, hbBit);
        vbControl.setAlignment(Pos.CENTER);

        btnShow.setOnAction(e->{
            if (!tfEnterText.getText().isEmpty()){
                treePane.show(tfEnterText.getText());
            }
        });

        btnDecode.setOnAction(e->{
            if (treePane.hasTree() && !tfEnterBitString.getText().isEmpty()){
                treePane.decode(tfEnterBitString.getText());
            }
        });





        setTop(vbControl);
        setCenter(treePane);
    }
}
