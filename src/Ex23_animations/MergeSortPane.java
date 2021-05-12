package Ex23_animations;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MergeSortPane extends Pane {

    Rectangle[] firstListRects = new Rectangle[10];
    Rectangle[] secondListRects = new Rectangle[10];
    Rectangle[] sortedListRects = new Rectangle[20];

    Text[] firstTextsList = new Text[10];
    Text[] secondTextsList = new Text[10];
    Text[] sortedListTexts = new Text[20];

    int[] list1;
    int[] list2;

    public MergeSortPane(){
        initLists();
        setPrefSize(700,300);

        setUpLayout();
    }

    private void setUpLayout(){
        this.getChildren().clear();

        int xVal=20;
        int yVal=100;

        for (int i=0; i<list1.length;xVal+=30, i++){
            Rectangle rectangle = new Rectangle(xVal, yVal, 30,20);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.TRANSPARENT);
            firstListRects[i] = rectangle;
            Text text = text(list1[i]+"", rectangle);
            firstTextsList[i] = text;
            this.getChildren().addAll(rectangle, text);
        }

        xVal+= 50;
        for (int i=0; i<list2.length; xVal+=30, i++){
            Rectangle rectangle = new Rectangle(xVal, yVal, 30,20);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.TRANSPARENT);
            secondListRects[i] = rectangle;
            Text text = text(list2[i]+"", rectangle);
            secondTextsList[i] = text;
            this.getChildren().addAll(rectangle, text);
        }

        int xVal2=50;
        int yVal2 = 230;

        for (int i=0; i<20; i++, xVal2+=30){
            Rectangle rectangle = new Rectangle(xVal2, yVal2, 30,20);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.TRANSPARENT);
            sortedListRects[i] = rectangle;
            Text text = text("", rectangle);
            sortedListTexts[i] = text;
            this.getChildren().addAll(rectangle, text);
        }

        addPointer(firstListRects[0]);
        addPointer(secondListRects[0]);
        addPointer(sortedListRects[0]);
    }

    private Text text(String str, Rectangle rect){
        Text text = new Text(rect.getX()+5, rect.getY()+15, str);
        return text;
    }

    private void initLists(){
        Random random = new Random();
        list1 = random.ints(10,1,100).toArray();
        list2 = random.ints(10,1,100).toArray();

        Arrays.sort(list1);
        Arrays.sort(list2);

    }

    //add pointer one
    ArrayList<Line> lineArrayList = new ArrayList<>();
    public void addPointer(Rectangle rectangle){
        Line mainLine = new Line(rectangle.getX()+rectangle.getWidth()/2, rectangle.getY()-40, rectangle.getX()+rectangle.getWidth()/2, rectangle.getY());
        //Line left = new Line(mainLine.getEndX(), mainLine.getEndY(), mainLine.getEndX()*Math.cos(-Math.PI/4), mainLine.getEndY()*Math.sin(-Math.PI/4));
        //Line right = new Line(mainLine.getEndX(), mainLine.getEndY(), mainLine.getEndX()*Math.cos(Math.PI/4), mainLine.getEndY()*Math.sin(-Math.PI/4));

        this.getChildren().addAll(mainLine);
    }

    int counter1 = 0;
    int counter2 = 0;
    int counter3 = 0;

    public void step() {
        //compare pointer one and pointer two

        if (counter3 == 20) return;

        if (counter1 == 10 && counter2<10){
            sortedListTexts[counter3++].setText(secondTextsList[counter2++].getText());
            repaintTheHeaders(counter1, counter2, counter3);
            return;
        }

        if (counter2 == 10 && counter1<10){
            sortedListTexts[counter3++].setText(secondTextsList[counter1++].getText());
            repaintTheHeaders(counter1, counter2, counter3);
            return;
        }


        if (Integer.parseInt(firstTextsList[counter1].getText()) < Integer.parseInt(secondTextsList[counter2].getText())){
            //remove pointer one
            sortedListTexts[counter3++].setText(firstTextsList[counter1++].getText());
        }else{
            sortedListTexts[counter3++].setText(secondTextsList[counter2++].getText());
        }

        repaintTheHeaders(counter1, counter2, counter3);


    }

    private void repaintTheHeaders(int counter1, int counter2, int counter3) {
        this.getChildren().removeIf(node -> node instanceof Line);
        if (counter1 >= 10){
            addPointer(firstListRects[9]);
        }else{
            addPointer(firstListRects[counter1]);
        }

        if (counter2 >= 10){
            addPointer(secondListRects[9]);
        }else{
            addPointer(secondListRects[counter2]);
        }

        if (counter3 >= 20){
            addPointer(sortedListRects[19]);
        }else{
            addPointer(sortedListRects[counter3]);
        }


    }

    public void reset() {
        initLists();
        setUpLayout();
        counter1=counter2=counter3=0;

        this.getChildren().removeIf(node-> node instanceof Line);

        addPointer(firstListRects[0]);
        addPointer(secondListRects[0]);
        addPointer(sortedListRects[0]);
    }
}
