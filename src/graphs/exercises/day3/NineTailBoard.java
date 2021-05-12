package graphs.exercises.day3;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

public class NineTailBoard extends HBox {

    public NineTailBoard(String initialNode){
        setSpacing(5);
        setPrefSize(900, 170);
        //NineTailModel nineTailModel = new NineTailModel();
        //List<Integer> path =  nineTailModel.getShortestPath(NineTailModel.getIndex(initialNode.toCharArray()));

        NineTailModel16 nineTailModel16 = new NineTailModel16();
        List<Integer> path = nineTailModel16.getShortestPath(NineTailModel16.getIndex(initialNode.toCharArray()));

        if (path.size() <= 1){
            System.out.println("no solution");
        }

        for (int i=0; i<path.size(); i++){
            System.out.println(path.get(i));
            char[] node = NineTailModel16.getNode(path.get(i));
            addPane(node);
        }

    }

    public void addPane(char[] node){
        String result = "";
        for (int i=0; i<node.length; i++){
            result += node[i];
            if (i == 3 || i==7 || i==11){
                result+="\n";
            }
        }

        Text text = new Text(result);

        text.setFont(Font.font(30));
        Pane pane = new Pane();
        pane.setStyle("-fx-border:black 1px");

        pane.getChildren().add(new ScrollPane(text));
        getChildren().add(pane);
    }


}
