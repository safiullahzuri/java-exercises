package binarySearchTree.ex25_13;

import binarySearchTree.BST;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class BSTAnimation extends BorderPane {

    private BST<Integer> tree;

    private Text iteratorText = new Text(getWidth()/3, 10, "");
    private  Text statusText = new Text(10,40, "status");

    public BSTAnimation(BST<Integer> bst){

        this.tree = bst;

        getChildren().add(iteratorText);
        getChildren().add(statusText);

        setPrefSize(500,500);

        BTView btView = new BTView(tree);
        setCenter(btView);

        HBox hControl = new HBox(10);
        Text lbl = new Text("Enter a key: ");
        TextField tfKey = new TextField();

        Button btnInsert = new Button("Insert");
        Button btnDelete = new Button("Delete");
        Button btnInorder = new Button("Show Inorder");
        Button btnPreorder = new Button("Show Preorder");
        Button btnPostorder = new Button("Show Postorder");

        hControl.getChildren().addAll(lbl, tfKey, btnInsert, btnDelete, btnInorder, btnPreorder, btnPostorder);
        setBottom(hControl);

        btnInsert.setOnAction(e->{
            String number = tfKey.getText();
            if (number.equals("")) return;
            btView.insert(Integer.parseInt(number));
        });

        btnDelete.setOnAction(e->{
            String number = tfKey.getText();
            if (number.equals("")) return;
            btView.delete(Integer.parseInt(number));
        });

        btnInorder.setOnAction(e->{
            ArrayList<Integer> inorder = btView.inorderList();
            iteratorText.setText("Inorder: "+inorder);
        });

        btnPreorder.setOnAction(e->{
            ArrayList<Integer> preorder = btView.preorderList();
            iteratorText.setText("Preorder: "+preorder);
        });

        btnPostorder.setOnAction(e->{
            ArrayList<Integer> postorder = btView.postorder();
            iteratorText.setText("Postorder: "+postorder);
        });


    }


}
