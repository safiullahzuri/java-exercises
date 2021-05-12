package Ex23_animations;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BSHistogram extends Pane {
    private Rectangle[] bars;
    private int[] numbers;

    private static final int RECT_BASE = 15;
    private static final int PANE_WIDTH = 25 * RECT_BASE + 50;
    private static final int PANE_HEIGHT = 200;

    public BSHistogram(){
        setStyle("-fx-border: 1px;");
        numbers = new int[25];
        for (int i=0; i<numbers.length;i++){
            numbers[i] = i+1;
        }
        setPrefSize(PANE_WIDTH, PANE_HEIGHT);
        shuffleNumbers();
        drawRectangle();
    }

    public void drawRectangle(){
        bars = new Rectangle[numbers.length];
        this.getChildren().clear();
        int xVal = 10;
        for (int i=0; i<numbers.length; i++, xVal+=RECT_BASE){
            int height = numbers[i]* (PANE_HEIGHT/25);
            int y = PANE_HEIGHT - height;
            Rectangle rectangle = new Rectangle(xVal,y,RECT_BASE, height);
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(Color.BLACK);
            bars[i] = rectangle;

            Text text = new Text(rectangle.getX()+5, rectangle.getY()-10, numbers[i]+" ");
            this.getChildren().addAll(rectangle, text);
        }
    }

    private void shuffleNumbers(){
        for (int i=0; i< numbers.length; i++){
            int random = (int) (Math.random()*numbers.length);
            if (i != random){
                int temp = numbers[i];
                numbers[i] = numbers[random];
                numbers[random] = temp;
            }
        }
        printArray(numbers);
    }

    void printArray(int[] list){
        for (int i=0; i< list.length; i++){
            System.out.print(list[i]+" ");
        }
        System.out.println("here");
    }


    public void step() {
        bubbleSort(numbers);
        //do not sort all the numbers at once
        //make one comparison at time
    }

    int step = 1;

    private void bubbleSort(int[] list){

        if (step >= numbers.length)return;

        for (int i=0; i<step; i++){
            for (int j=1; j < numbers.length-i; j++){

                if (list[j] < list[j-1]){
                    //swap them here
                    //color them
                    int temp = list[j];
                    list[j] = list[j-1];
                    list[j-1] = temp;
                }
            }
        }
        step++;
        drawRectangle();
        setRectangleJequalToBlack(step-1);
    }


    private void setRectangleJequalToBlack(int i) {
        System.out.println("i am called");
        setAllRectanglesToWhite();
        bars[i].setFill(Color.BLACK);
    }

    private void setAllRectanglesToWhite() {
        for (Rectangle r: bars){
            r.setFill(Color.WHITE);
        }
    }

    public void reset() {
        step = 1;
        shuffleNumbers();
        drawRectangle();
    }

}
