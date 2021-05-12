package hashing.exercises.ex6;

import hashing.exercises.ex1.LinearProbingMap;
import hashing.exercises.ex1.MyMap;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class LinearProbingMapBoard extends Pane {

    public LinearProbingMap<Integer, Integer> map;

    public LinearProbingMapBoard(){
        setPrefSize(800, 600);
        this.map = new LinearProbingMap<>();

        drawMap();
    }

    private void drawMap(){

        this.getChildren().clear();

        int START_X = 60;
        int START_Y = 100;

        for (int i=0; i<map.capacity; i++){
            Rectangle rectangle = new Rectangle(START_X, START_Y,50, 20);
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(Color.BLACK);
            String t = "";
            if (map.table[i] != null){
                t = map.table[i].getValue()+"";
            }
            Text text = new Text(START_X+10, START_Y+15, t);
            this.getChildren().addAll(rectangle, text);
            START_Y += 30;
        }


    }

    public void insert(Integer key, Integer value){
        map.put(key, value);
        drawMap();
    }

    public void delete(Integer key){
        map.remove(key);
        drawMap();
    }

    public void deleteAll(){
        map.clear();
        drawMap();
    }






}
