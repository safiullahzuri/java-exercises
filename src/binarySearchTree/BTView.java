package binarySearchTree;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BTView extends Pane {

    private BST<Integer> tree = new BST<>();
    private double radius = 15;
    private double vGap = 50;

    public BTView(BST<Integer> tree){
        this.tree = tree;
        setStatus("Tree is empty");
    }

    public void setStatus(String message){
        getChildren().add(new Text(20, 20, message));
    }

    public void displayTree(boolean horizontal){
        if (!horizontal){
            this.getChildren().clear();
            if (tree.getRoot() != null){
                displayTree(tree.getRoot(), getWidth()/2, vGap, getWidth()/4);
            }
        }else{
            getChildren().clear();
            if (tree.getRoot() != null){
                displayHorizontal(tree.getRoot(), 15, getHeight()/2, getHeight()/4);
            }
        }
    }

    private void displayTree(BST.TreeNode<Integer> root, double x, double y, double hGap){

        if (root.left != null){
            getChildren().add(new Line(x-hGap, y+vGap, x, y));
            displayTree(root.left, x-hGap, y+vGap, hGap/2);
        }
        if (root.right != null){
            getChildren().add(new Line(x+hGap, y+vGap, x, y));
            displayTree(root.right, x+hGap, y+vGap, hGap/2);
        }
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(x-4, y+4, root.element+" "));
    }

    private void displayHorizontal(BST.TreeNode<Integer> root, double x, double y, double vGap){

        if (root.right != null){
            getChildren().add(new Line(x,y, x+30, y+vGap));
            displayHorizontal(root.right, x+30, y+vGap, vGap/2);
        }
        if (root.left != null){
            getChildren().add(new Line(x,y, x+30, y-vGap));
            displayHorizontal(root.left, x+30, y-vGap, vGap/2);
        }
        Circle circle = new Circle(x, y, 15);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(x-4, y+4, root.element+""));
    }

    private void displayTree2(BST.TreeNode<Integer> current, double x, double y, double hGap){
        if (current.left != null){
            getChildren().add(new Line(x-hGap, y+vGap, x, y));
            displayTree2(current.left, x-hGap, y+vGap, hGap/2);
        }
        if (current.right != null){
            getChildren().add(new Line(x+hGap, y+vGap, x, y));
            displayTree2(current.right, x+hGap, y+vGap, hGap/2);
        }
        Circle circle = new Circle(x,y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(x-4, y+4, current.element+""));
    }



}
