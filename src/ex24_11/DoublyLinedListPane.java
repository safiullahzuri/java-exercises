package ex24_11;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ListIterator;

public class DoublyLinedListPane extends Pane {

    DoublyLinkedList<Integer> list;
    Text tMessage;

    public DoublyLinedListPane(){
        setUp();
    }

    private void setUp(){
        list = new DoublyLinkedList<>();
        drawPane();
    }

    private void drawPane(){
        getChildren().clear();
        final double WIDTH = 700;
        final double HEIGHT = 200;
        final double SLOT_WIDTH = 60;
        final double SLOT_HEIGHT = 20;
        setPrefSize(WIDTH, HEIGHT);

        tMessage = new Text(WIDTH/2, 20, "");
        getChildren().add(tMessage);

        double xPos = 10.0;
        double yPos = 100.0;

        for (int i=0; i<list.size(); i++){
            makeBox(xPos, yPos, SLOT_WIDTH, SLOT_HEIGHT, list.get(i)+"");
            yPos += SLOT_HEIGHT;
            makeBox(xPos, yPos, SLOT_WIDTH, SLOT_HEIGHT, "next");
            yPos += SLOT_HEIGHT;
            makeBox(xPos, yPos, SLOT_WIDTH, SLOT_HEIGHT, "prev");

            if (list.size() > 1 && i<list.size()-1){
                Line nextLine = new Line(xPos+SLOT_WIDTH, yPos-(SLOT_HEIGHT/2), xPos+SLOT_WIDTH+30, yPos-(SLOT_HEIGHT/2)-SLOT_HEIGHT);
                Line prevLine = new Line(xPos + SLOT_WIDTH,
                        yPos - (SLOT_HEIGHT / 2) - SLOT_HEIGHT, xPos + SLOT_WIDTH + 30.0,
                        yPos + (SLOT_HEIGHT / 2));
                getChildren().addAll(nextLine, prevLine);
            }

            yPos= 100;
            if (i==0 || i == list.size()-1){
                Text heading;
                if (list.size() == 1){
                    heading = new Text(xPos+2, yPos-10, "H/T");
                }else{
                    heading = new Text(xPos+2, yPos-10, i==0 ? "HEAD": "TAIL");
                }
                getChildren().add(heading);
            }
            xPos += SLOT_WIDTH+30;
        }

    }

    private void makeBox(double xPos, double yPos, double width, double height, String text){

        Rectangle rect = new Rectangle(xPos, yPos, width, height);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);

        Text t = new Text(xPos+5, yPos+12, text);
        getChildren().addAll(rect, t);
    }

    public void search(int value){
        int index = -1;
        for (int i=0; i<list.size(); i++){
            if (list.get(i) == value){
                index = i;
                break;
            }
        }
        if (index == -1){
            tMessage.setText(value+" was not found.");
        }else{
            tMessage.setText(value+" found at index "+index);
        }
    }

    public void insert(int value, int index){
        if (index < 0){
            list.add(new Integer(value));
        }else{
            list.add(index, value);
        }
        drawPane();
    }

    public void delete(int value){
        int index = -1;
        for (int i=0; i<list.size(); i++){
            if (list.get(i) == value){
                index = i;
                break;
            }
        }

        if (index == -1){
            tMessage.setText(value+" nto in list");
        }else{
            list.remove(index);
            drawPane();;
            tMessage.setText(value+" removed at index "+index);
        }
    }

    public void forwardTraversal(){
        StringBuilder sb = new StringBuilder("Forward traversal: ");
        ListIterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()){
            sb.append(iterator.next()+" ");
        }
        tMessage.setText(sb.toString());
    }

    public void backwardTraversal(){
        StringBuilder sb = new StringBuilder("Backward traversal: ");
        ListIterator<Integer> iterator = list.listIterator(list.size()-1);
        while (iterator.hasPrevious()){
            sb.append(iterator.previous()+" ");
        }
        tMessage.setText(sb.toString());
    }


























}
