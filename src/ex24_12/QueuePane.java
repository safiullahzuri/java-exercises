package ex24_12;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.LinkedList;
import java.util.Queue;

public class QueuePane extends Pane {
    Queue<Integer> queue;

    public QueuePane(){
        setUp();
    }

    private void setUp(){
        queue = new LinkedList<>();
        drawPane();
    }

    private void drawPane(){
        getChildren().clear();
        final double WIDTH = 600;
        final double HEIGHT = 200;
        final double SLOT_WIDTH = 40;
        final double SLOT_HEIGHT = 20;
        setPrefSize(WIDTH, HEIGHT);

        Text title = new Text(10, 20, "Queue Visualizer");
        getChildren().add(title);

        double xPos = 10;
        double yPos = 100;

        Integer[] arr = new Integer[queue.size()];
        queue.toArray(arr);

        for (int i=0; i<arr.length; i++){
            Rectangle r = new Rectangle(xPos, yPos, SLOT_WIDTH, SLOT_HEIGHT);
            r.setFill(Color.WHITE);
            r.setStroke(Color.BLACK);
            Text t = new Text(xPos+5, yPos+15, arr[i]+"");
            getChildren().addAll(r,t);

            if (i == 0 || i == arr.length-1) {
                Text heading;
                if (queue.size() == 1){
                    heading = new Text(xPos+2, yPos-10, "H/T");
                }else{
                    heading = new Text(xPos+2, yPos-10, i==0 ? "HEAD": "TAIL");
                }
                getChildren().add(heading);
            }
            xPos += SLOT_WIDTH;
        }
    }

    public void enqueue(int value){
        queue.offer(value);
        drawPane();
    }

    public void dequeue(){
        if (queue.isEmpty())return;
        queue.poll();
        drawPane();
    }


}
