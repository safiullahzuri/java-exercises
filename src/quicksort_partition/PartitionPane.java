package quicksort_partition;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class PartitionPane extends Pane {
    private int stateIndex;
    private int[] originalList;
    private ArrayList<PartitionState> states;

    public PartitionPane(){
        set();
    }



    private void set(){
        stateIndex = 0;
        originalList = getRandomList();
        states= PartitionState.getStates(Arrays.copyOf(originalList, originalList.length));
        drawPane(true);
    }

    private void drawPane(boolean original){
        getChildren().clear();
        final double WIDTH = 600.0;
        final double HEIGHT = 100.0;
        final double SLOT_WIDTH = (WIDTH-20)/20;
        final double SLOT_HEIGHT = 30.0;
        setPrefSize(WIDTH, HEIGHT);

        int[] list = original ? originalList : states.get(stateIndex).getList();
        int low = original ? -1: states.get(stateIndex).getLow();
        int high = original ? -1: states.get(stateIndex).getHigh();
        int pivot = original ? -1: states.get(stateIndex).getPivot();

        double xPos = 10.0;
        double yPos = 25.0;

        for (int i=0; i<list.length; i++, xPos+= SLOT_WIDTH){
            Rectangle rectangle = new Rectangle(xPos, yPos, SLOT_WIDTH, SLOT_HEIGHT);
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(Color.BLACK);

            Text tNum = new Text(rectangle.getX(), rectangle.getY()+15, list[i]+"");
            getChildren().addAll(rectangle, tNum);

            if (!original){
                if (list[i] == pivot){
                    Text tPivot = new Text(rectangle.getX()+10, rectangle.getY()+45, "Pivot");
                    getChildren().add(tPivot);
                }

                if (i == low){
                    Text tLow = new Text(rectangle.getX()+10, rectangle.getY()-5, "low");
                    getChildren().add(tLow);
                }

                if (i == high){
                    Text tHigh = new Text(rectangle.getX()+10, rectangle.getY()-5, "high");
                    getChildren().add(tHigh);
                }
            }
        }

    }

    public boolean step(){
        if (stateIndex >= states.size()-1){
            drawPane(false);
            return true;
        }
        drawPane(false);
        stateIndex++;
        return false;
    }

    public void reset(){
        set();
    }



    private static int[] getRandomList(){
        int[] list = new int[20];
        for (int i=0; i< list.length; i++){
            list[i] = (int)(Math.random()*999)+1;
        }
        return list;
    }

}
