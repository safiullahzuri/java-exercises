package arraylist_animation;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ArrayListVisualizer extends BorderPane {


    public ArrayListVisualizer(){
        //set buttons here

        ArrayListPane arrayListPane = new ArrayListPane();

        setCenter(arrayListPane);

        Text tValue = new Text("Enter a value: ");
        TextField tfValue = new TextField();

        Text tIndex = new Text("Enter an index: ");
        TextField tfIndex = new TextField();

        Button btnSearch = new Button("Search");
        Button btnInsert = new Button("Insert");
        Button btnDelete = new Button("Delete");
        Button btnTrim = new Button("TrimToSize");

        btnInsert.setOnAction(e-> {
            if (tfValue.getText().isEmpty())return;
            int index = -1;
            if (!tfIndex.getText().isEmpty()){
                index = Integer.parseInt(tfIndex.getText());
            }
            int value = Integer.parseInt(tfValue.getText());
            arrayListPane.insert(index, value);
        });

        btnSearch.setOnAction(e->{
            if (tfValue.getText().isEmpty())return;
            int value = Integer.parseInt(tfValue.getText());

            arrayListPane.search(value);

        });

        btnDelete.setOnAction(e->{
            if (tfValue.getText().isEmpty())return;
            int value = Integer.parseInt(tfValue.getText());

            arrayListPane.delete(value);
        });

        btnTrim.setOnAction(e->{
            arrayListPane.trim();
        });


        HBox hbControl = new HBox(10);
        hbControl.getChildren().addAll(tValue, tfValue, tIndex, tfIndex, btnSearch, btnInsert, btnDelete,btnTrim);

        setBottom(hbControl);


    }


}
