package AVLTREE_EXERCISES.EXERCISE3;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class AVLPane extends Pane {

    private AVLTree<Integer> tree;
    private double vGap = 50;
    private double radius = 15;
    private Text tMessage = new Text(20,20, "");
    private static double WIDTH = 800;
    private static double HEIGHT = 400;

    public AVLPane(){
        this.tree = new AVLTree<>();
        getChildren().add(tMessage);
        setWidth(WIDTH);
        setHeight(HEIGHT);
    }

    public void insert(int n){
        if (tree.insert(n)){
            drawTree();
            tMessage.setText(n+" successfully inserted.");
            getChildren().add(tMessage);
        }else{
            tMessage.setText(n+" not inserted.");
        }
    }

    public void delete(int n){
        if (tree.delete(n)){
            drawTree();
            tMessage.setText(n+" deleted.");
            getChildren().add(tMessage);
        }else{
            tMessage.setText(n+" not deleted.");
        }
    }

    public void search(int n){
        if (tree.search(n)){
            tMessage.setText(n+" found in tree.");
        }else{
            tMessage.setText(n+" not found.");
        }
    }

    private void drawTree(){
        getChildren().clear();
        if (tree.isEmpty()) return;
        drawTree(tree.root, getWidth()/2, vGap, getWidth()/4);
    }

    private void drawTree(BST.TreeNode<Integer> node, double x, double y, double hGap){
        if (node.left != null){
            getChildren().add(new Line(x-hGap, y+vGap, x, y));
            drawTree(node.left, x-hGap, y+vGap, hGap/2);
        }
        if (node.right != null){
            getChildren().add(new Line(x+hGap, y+vGap, x, y));
            drawTree(node.right, x+hGap, y+vGap, hGap/2);
        }

        Circle circle = new Circle(x,y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        Text tChar = new Text(x-4, y+30, tree.balanceFactor((AVLTree.AVLTreeNode<Integer>) node)+"");
        getChildren().addAll(circle, tChar, new Text(x-4, y+4, node.element+""));
        //getChildren().add(circle);
    }




}
