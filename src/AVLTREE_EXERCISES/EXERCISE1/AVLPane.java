package AVLTREE_EXERCISES.EXERCISE1;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class AVLPane extends Pane {

    private AVLTree<Integer> tree;
    private double vGap = 50;
    private double radius = 15;

    public AVLPane(AVLTree<Integer> tree){
        this.tree = tree;
        setWidth(800);
        setHeight(600);
        drawTree();
    }

    private void drawTree(){
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

        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        Text tChar = new Text(x-4, y+30, tree.balanceFactor((AVLTree.AVLTreeNode<Integer>)node)+"");
        getChildren().addAll(circle, tChar, new Text(x-4, y+4, node.element+""));

    }


}
