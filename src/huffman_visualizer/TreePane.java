package huffman_visualizer;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class TreePane extends Pane {
    private HuffmanCode.Tree tree;
    private String[] codes;
    private Text status;
    private double radius = 15;
    private double vGap = 50;

    public TreePane(){
        tree = null;
        codes = null;
        status = new Text(20,20, "");
        setStatus("Huffman Tree is empty");
    }

    public void setStatus(String msg){
        getChildren().remove(status);
        status.setText(msg);
        getChildren().add(status);
    }

    public boolean hasTree(){
        return tree != null;
    }

    public void displayTree(){
        getChildren().clear();
        if (tree.root != null){
            displayTree(tree.root, getWidth()/2, vGap, getWidth()/4);
        }
    }

    public void displayTree(HuffmanCode.Tree.Node root, double x, double y, double hGap){
        if (root.left != null){
            Text tCode = new Text(x+hGap/2, y+vGap/2, "0");
            getChildren().addAll(new Line(x-hGap, y+vGap, x, y), tCode);
            displayTree(root.left, x-hGap, y+vGap, hGap/2);
        }
        if (root.right != null){
            Text tCode = new Text(x-hGap/2, y+vGap/2, "1");
            getChildren().addAll(new Line(x+hGap, y+vGap, x, y), tCode);
            displayTree(root.right, x+hGap, y+vGap, hGap/2);
        }

        Circle circle = new Circle(x,y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text tChar = new Text(x-4, y+30, root.element+"");
        getChildren().addAll(circle, tChar, new Text(x-4, y+4, root.weight+""));

    }


    public void show(String text) {
        int[] counts = HuffmanCode.getCharactersFrequency(text);
        tree = HuffmanCode.getHuffmanTree(counts);
        codes = HuffmanCode.getCode(tree.root);

        displayTree();
        StringBuilder encode = new StringBuilder();
        for (int i=0; i<text.length(); i++){
            char c = text.charAt(i);
            encode.append(codes[(int)c]);
        }
        setStatus(text+" is encoded to "+encode.toString());
    }

    public void decode(String bits){
        StringBuilder text = new StringBuilder();
        StringBuilder code = new StringBuilder();

        for (int i=0; i<bits.length(); i++){
            code.append(bits.charAt(i)+"");

            for (int j=0; j<codes.length; j++){
                if (code.toString().equals(codes[j])){
                    text.append((char) j);
                    code = new StringBuilder();
                }
            }
        }
        setStatus(bits+" is decoded to "+text.toString());
    }

}
