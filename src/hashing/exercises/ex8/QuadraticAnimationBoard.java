package hashing.exercises.ex8;

import hashing.exercises.ex1.LinearProbingMap;
import hashing.exercises.ex2.MyHashMap;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class QuadraticAnimationBoard extends Pane {

    public MyHashMap<Integer, Integer> map;

    public QuadraticAnimationBoard(){

        setPrefSize(800, 600);
        this.map = new MyHashMap<>();

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
            if (map.table.get(i) != null){
                t = map.table.get(i).getValue()+"";
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
