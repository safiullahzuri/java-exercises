package Ex23_animations;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Ex23_17Pane extends Pane {

    Rectangle[] array;
    TextArea[] buckets;
    Text[] arrayText;
    int[] numbers;


    public Ex23_17Pane(){
        setStyle("-fx-border-color:black;");
        setPrefSize(700, 350);
        numbers = new int[20];
        for (int i=0; i< numbers.length; i++){
            numbers[i] = (int) (Math.random()*1000);
        }

        setUpLayout();
    }

    private void setUpLayout(){
        this.getChildren().clear();
        array = new Rectangle[20];
        buckets = new TextArea[10];
        arrayText = new Text[20];

        int xVal=20;
        int y=30;

        for (int i=0; i< numbers.length; i++, xVal+=30){
            Rectangle rectangle = new Rectangle(xVal, y, 30,20);
            rectangle.setStroke(Color.BLACK); rectangle.setFill(Color.TRANSPARENT);
            array[i] = rectangle;

            Text text = new Text(rectangle.getX()+5, rectangle.getY()+15, numbers[i]+"");
            arrayText[i] = text;
            this.getChildren().addAll(rectangle, text);
        }

        //set up the text areas
        for (int i=0; i< buckets.length; i++){
            TextArea textArea = new TextArea();
            buckets[i] = textArea;
            textArea.setPrefSize(45,160);
            textArea.setLayoutX(array[i*2].getX()+15);
            textArea.setLayoutY(array[i*2].getY()+50);
            textArea.setEditable(false);

            Label label = new Label("Bucket["+i+"]");
            label.setLayoutX(buckets[i].getLayoutX());
            label.setLayoutY(this.getHeight()+270);

            this.getChildren().addAll(label, textArea);
        }

    }

    int step = 1;
    public void step() {
        radixSort(numbers);
        //repaint the array
        for (int i=0; i<arrayText.length; i++){
            arrayText[i].setText(numbers[i]+"");
        }
    }

    public void reset() {
        numbers = new int[20];
        for (int i=0; i< numbers.length; i++){
            numbers[i] = (int) (Math.random()*1000);
        }
        setUpLayout();
        step = 1;
    }


    public  void radixSort(int[] list){
        int largest = list[0];
        for (int i=1; i< list.length; i++){
            if (list[i] > largest){
                largest = list[i];
            }
        }

        int digits = 0;
        do {
            digits++;
            largest= largest/10;
        }while (largest != 0);
        if (step > digits)return;

        ArrayList<ArrayList<Integer>> lists = getBuckets();
        int mod = 10;
        int divisor = 1;

        for (int i=0; i<step; i++){

            for (int ie=0; ie<buckets.length; ie++){
                buckets[ie].clear();
            }

            for (int k=0; k< list.length; k++){
                int bucketIndex = (list[k]%mod)/divisor;
                lists.get(bucketIndex).add(list[k]);
                buckets[bucketIndex].appendText(list[k]+"\n");
            }

            int count = 0;
            for (int l=0; l< lists.size(); l++){
                for (int q=0; q<lists.get(l).size(); q++){
                    list[count++] = lists.get(l).get(q);
                }
            }

            divisor*=10;
            mod*=10;

            lists = getBuckets();
        }
        step++;
    }

    public  ArrayList<ArrayList<Integer>> getBuckets(){
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for (int i=0; i<10; i++){
            lists.add(new ArrayList<>());
        }
        return lists;
    }

}
