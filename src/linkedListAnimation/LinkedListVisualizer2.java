package linkedListAnimation;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class LinkedListVisualizer2 extends BorderPane {

    public LinkedListVisualizer2(){
        drawGUI();
    }

    private void drawGUI(){
        LinkedListPane2 listPane2 = new LinkedListPane2();
        listPane2.setStyle("-fx-border-color: black;");

        Text tEnter = new Text("Enter a value: ");
        Text tIndex = new Text("Enter an index: ");

        TextField tfValue = new TextField();
        TextField tfIndex = new TextField();

        tfValue.setPrefColumnCount(3);
        tfIndex.setPrefColumnCount(3);

        Button btnSearch = new Button("Search");
        Button btnInsert = new Button("Insert");
        Button btnDelete = new Button("Delete");

        btnSearch.setOnAction(e->{
            if(tfValue.getText().isEmpty())return;
            listPane2.search(Integer.parseInt(tfValue.getText()));
        });

        btnInsert.setOnAction(e->{
            if (tfValue.getText().isEmpty()){return;}

            if (tfIndex.getText().isEmpty()){
                listPane2.insert(Integer.parseInt(tfValue.getText()), -1);
            }else{
                listPane2.insert(Integer.parseInt(tfValue.getText()), Integer.parseInt(tfIndex.getText()));
            }

        });

        btnSearch.setOnAction(e->{
            if (tfValue.getText().isEmpty())return;
            int value = Integer.parseInt(tfValue.getText());

            listPane2.search(value);

        });



        btnDelete.setOnAction(e->{
            if (tfValue.getText().isEmpty())return;
            listPane2.delete(Integer.parseInt(tfValue.getText()));
        });

        HBox hbControl = new HBox(10);
        hbControl.getChildren().addAll(tIndex, tfIndex, tEnter, tfValue,btnSearch, btnInsert, btnDelete);

        hbControl.setAlignment(Pos.CENTER);

        setCenter(listPane2);
        setBottom(hbControl);



    }



}
