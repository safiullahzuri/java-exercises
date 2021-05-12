package arraylist_animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class ArrayListPane extends Pane {

    private List<Integer> list = new ArrayList<>();
    private Text tMessage;

    private int capacity = 4;

    public ArrayListPane(){
        drawPane(false);
    }

    private void drawPane(boolean trim){
        getChildren().clear();
        if (list.size() == capacity && !trim){
            capacity *=2;
        }
        String label  = String.format("array list is empty\tsize=%d and capacity is %d", list.size(), capacity);
        tMessage = new Text(10, 15, label);

        setMinSize(600,300);
        getChildren().add(tMessage);



        drawCrossedRectangles(capacity);



    }

    private void addAnimationTo(Node node){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setByX(200);
        translateTransition.setNode(node);
        translateTransition.setAutoReverse(true);
        translateTransition.setDuration(Duration.millis(400));
        translateTransition.setCycleCount(2);
        translateTransition.play();
    }

    private void drawCrossedRectangles(int capacity){

        double xPos = 10;
        double yPos = 100;

        final double WIDTH = 60;
        final double HEIGHT = 30;
        for (int i=0; i<capacity; i++){
            Rectangle rectangle = new Rectangle(xPos, yPos, WIDTH, HEIGHT);

            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(Color.BLACK);

            getChildren().add(rectangle);

            addAnimationTo(rectangle);
            if (i >= list.size()){
                Line line = new Line(rectangle.getWidth()+xPos, rectangle.getY(), rectangle.getX(), rectangle.getY()+rectangle.getHeight());
                getChildren().add(line);


            }else{
                Text text = new Text(rectangle.getX()+rectangle.getWidth()/2, rectangle.getY()+rectangle.getHeight()/2, list.get(i)+"");
                getChildren().add(text);
            }


            xPos += WIDTH;
        }

    }

    public void insert(int index, int value) {
        if (index < 0){
            list.add(value);
        }else{
            if (index > list.size()){
                index = list.size();
            }
            list.add(index, value);
        }
        drawPane(false);
    }

    public void search(int value) {
        int index = -1;
        for (int i=0; i<list.size(); i++){
            if (list.get(i) == value){
                index = i;
                break;
            }
        }
        String message = "";
        if (index >= 0){
            message = value+" found at index "+index;
        }else{
            message = value+" does not exist.";
        }
        tMessage.setText(message);

    }

    public void delete(int value) {
        if (list.remove(new Integer(value))){
            tMessage.setText("Successfully deleted value: "+value);
            drawPane(false);
        }else{
            tMessage.setText("Could not find the specified value.");
        }
    }

    public void trim() {
        capacity = list.size();
        System.out.println("capacity is "+capacity);
        System.out.println("list size is "+list.size());
        drawPane(true);
        System.out.println("drawing pane");
    }


}
