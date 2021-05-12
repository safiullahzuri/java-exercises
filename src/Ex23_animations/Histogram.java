package Ex23_animations;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Histogram extends Pane {

    private Rectangle[] bars;
    private int[] numbers;

    private static final int RECT_BASE = 15;
    private static final int PANE_WIDTH = 25 * RECT_BASE + 50;
    private static final int PANE_HEIGHT = 200;

    public Histogram(){
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
        selectionSort(numbers);
    }

    int step = 1;
    public void selectionSort(int[] list){

        //setAllRectanglesToWhite();

        if (step >25){
            return;
        }

        for (int i=0; i< list.length; i++){

            int currentSmallest = list[i];
            int smallestIndex = i;

            for (int j=i; step <= list.length &&  j<step; j++){
                if (list[j] < currentSmallest){
                    smallestIndex = j;
                    currentSmallest = list[j];
                }
            }
            if ( i != smallestIndex){
                int temp = list[i];
                list[i] = list[smallestIndex];
                list[smallestIndex] = temp;
            }
        }
        drawRectangle();
        setRectangleJequalToBlack(step-1);
        step++;
    }

    private void setRectangleJequalToBlack(int j) {
        setAllRectanglesToWhite();
        bars[j].setFill(Color.BLACK);
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
