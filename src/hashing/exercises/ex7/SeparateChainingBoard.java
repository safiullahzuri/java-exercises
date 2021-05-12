package hashing.exercises.ex7;

import com.sun.org.apache.regexp.internal.RE;
import hashing.learning.MyHashMap;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class SeparateChainingBoard extends Pane {

    private MyHashMap<Integer, Integer> map;

    public SeparateChainingBoard(){
        map = new MyHashMap<>();
        drawPane();
    }

    public void insert(Integer key, Integer value){
        map.put(key, value);
        drawPane();
    }

    public void delete(Integer key){
        map.remove(key);
        drawPane();
    }

    public void deleteAll(){
        map.clear();
        drawPane();
    }


    private void drawPane(){
        this.getChildren().clear();
        int START_X = 100;
        int START_Y = 100;

        for (int i=0; i<map.capacity; i++){

            boolean sameCell = false;

            if (map.hashTable[i] == null){
                Rectangle rectangle = new Rectangle(START_X, START_Y,60, 30);
                rectangle.setFill(Color.WHITE);
                rectangle.setStroke(Color.BLACK);

                this.getChildren().add(rectangle);
                START_X = 100;
                START_Y += 30;
            }else{
                for (int j=0; j<map.hashTable[i].size(); j++){

                    Rectangle rectangle = new Rectangle(START_X, START_Y,60, 30);
                    rectangle.setFill(Color.WHITE);
                    rectangle.setStroke(Color.BLACK);
                    String t = "";
                    if (map.hashTable[i].get(j) != null){
                        t = map.hashTable[i].get(j).getValue()+"";
                    }

                    Text text = new Text(START_X+10, START_Y+15, t+" ");

                    this.getChildren().addAll(rectangle, text);

                    if (sameCell){
                        START_X += 70;
                    }else{
                        START_Y += 30;
                        START_X = 100;
                    }
                    sameCell = true;
                }
                START_X = 100;
            }


        }


    }


}
