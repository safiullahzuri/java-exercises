package binarySearchTree;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BTView2 extends Pane {

    private BST<Integer> tree = new BST<>();
    private double radius = 15;
    private double verticalGap = 50;

    public BTView2(BST<Integer> tree) {
        this.tree = tree;
        setStatus("Tree is empty.");
    }

    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    public void displayTree() {
        getChildren().clear();
        if (tree.getRoot() != null) {
            displayTree(tree.getRoot(), getWidth() / 2, verticalGap, getWidth() / 4);
        }

    }

    private void displayTree(BST.TreeNode<Integer> root, double x, double y, double hGap) {
        if (root.left != null) {
            getChildren().add(new Line());
        }
    }
}

