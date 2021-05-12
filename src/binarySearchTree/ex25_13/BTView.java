package binarySearchTree.ex25_13;

import binarySearchTree.BST;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Iterator;

public class BTView extends Pane {

    BST<Integer> tree;
    void updateScene(){
        displayTree(tree.getRoot(), getWidth()/2, 60, getWidth()/3);
    }

    public BTView(BST<Integer> tree) {
        this.tree = tree;
        if (tree.getRoot() != null){
            //display tree
            updateScene();
        }
    }
    double vGap = 40;
    private void displayTree(BST.TreeNode<Integer> root, double x, double y, double hGap){
        if (root.left != null){
            getChildren().add(new Line(x-hGap, y+vGap, x, y));
            displayTree(root.left, x-hGap, y+vGap, hGap/2);
        }
        if (root.right != null){
            getChildren().add(new Line(x+hGap, y+vGap, x,y));
            displayTree(root.right, x+hGap, y+vGap, hGap/2);
        }

        Circle circle = new Circle(x,y, 20);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(x+4, y-4, root.element+""));

    }

    public void insert(int parseInt) {
        tree.insert(parseInt);
        updateScene();
    }

    public void delete(int parseInt) {
        tree.delete(parseInt);
        updateScene();
    }

    public ArrayList<Integer> inorderList() {
        ArrayList<Integer> list = new ArrayList<>();
        Iterator<Integer> iterator = tree.myInorderIterator();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }
        return list;
    }

    public ArrayList<Integer> preorderList() {
        ArrayList<Integer> list = new ArrayList<>();
        Iterator<Integer> iterator = tree.myPreorderIterator();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }
        return list;
    }

    public ArrayList<Integer> postorder() {
        ArrayList<Integer> list = new ArrayList<>();
        Iterator<Integer> iterator = tree.postOrderIterator();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }
        return list;
    }
}
