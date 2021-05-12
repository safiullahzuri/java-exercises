package Ex23_animations;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class QuickSortPartitionAnimation extends Application {

    BorderPane borderPane;
    Button btnStep, btnReset;
    MyPane myPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        setUp();
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void setUp(){
        borderPane = new BorderPane();
        btnStep = new Button("Step");
        btnReset = new Button("Reset");


        myPane = new MyPane();
        borderPane.setCenter(myPane);
        HBox hBox = new HBox(5, btnStep, btnReset);
        hBox.setAlignment(Pos.CENTER);
        borderPane.setBottom(hBox);

        btnStep.setOnAction(e-> {
            myPane.step();
        });

    }

    static class MyPane extends Pane{

        Rectangle[] rectangles;
        Text[] texts;
        int[] numbers;

        public MyPane(){
            setPrefSize(650,200);
            setUPLayout();

            int[] list = {1,2,5,6,4321,123,11,5324,1233,90,789,21};

            //addArrow( rectangles[0], "low");
            //addArrow(rectangles[list.length-1], "high");
            //quickSort(list);
            //printArray(list);

            addPivot(rectangles[0]);
            addArrow(rectangles[1], "low");
            addArrow(rectangles[19], "high");
        }

        void printArray(int[] list){
            for (int i=0; i<list.length; i++){
                System.out.print(list[i]+" ");
            }
        }

        private void setUPLayout(){
            Random random = new Random();
            numbers = random.ints(20,1,999).toArray();
            rectangles = new Rectangle[20];
            texts = new Text[20];

            int xVal=40;
            int yVal=80;

            for (int i=0; i< numbers.length; i++, xVal+=30){

                Rectangle rectangle = new Rectangle(xVal, yVal, 30,20);
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangles[i] = rectangle;

                Text text = new Text(rectangle.getX()+5, rectangle.getY()+15, numbers[i]+"");
                texts[i] = text;

                this.getChildren().addAll(rectangle, text);
            }
            //addArrow(rectangles[0], "pivot");

        }


        void addPivot(Rectangle rect){
            Line line = new Line(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()+5, rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()+45);
            Text text= new Text(line.getEndX()-rect.getWidth()/2, line.getEndY()+10, "pivot");
            this.getChildren().addAll(line, text);
            arrowNodes.put("pivot", new ArrayList<>(Arrays.asList(line,text)));
        }

        void addArrow(Rectangle rect, String str){
            Line line = new Line(rect.getX()+rect.getWidth()/2, rect.getY()-45, rect.getX()+rect.getWidth()/2, rect.getY()-5);
            Text text= new Text(line.getStartX()-rect.getWidth()/2, line.getStartY()-10, str);
            this.getChildren().addAll(line, text);
            arrowNodes.put(str, new ArrayList<>(Arrays.asList(line,text)));
        }



        HashMap<String, List<Node>> arrowNodes = new HashMap<>();

        void quickSort(int[] list){
            quickSort(list, 0, list.length-1);
        }

        void quickSort(int[] list, int low, int high){
            if(low < high){
                int pivotIndex = partition(list, low, high);
                quickSort(list, low, pivotIndex-1);
                quickSort(list, pivotIndex+1, high);
            }
        }



        boolean oneStepDone = false;
        int partition(int[] list, int low, int high){
            int first = low+1;
            int last = high;
            int pivot = list[low];



            while (first < last){

                while (first<=last && pivot >= list[first] && !oneStepDone){
                    first++;
                    oneStepDone = true;
                }

                while (first <= last && list[last] > pivot && !oneStepDone){
                    last--;
                    oneStepDone = true;
                }

                if (first < last && !oneStepDone){
                    int temp = list[first];
                    list[first] = list[last];
                    list[last] = temp;
                    oneStepDone = true;
                }
            }

            int valueToReturn;
            if (pivot > list[last] && !oneStepDone){
                list[low] = list[last];
                list[last] = pivot;
                valueToReturn = high;
            }else{
                valueToReturn =  low;
            }

            updateArrows(pivot, first, last);

            return valueToReturn;
        }

        void updateArrows(int pivot, int first, int last){
            List<Node> nodes =  arrowNodes.get("pivot");
            this.getChildren().removeIf(node -> nodes.contains(node));
            System.out.println("called");
        }

        int step = 0;

        public void step() {
            partition(numbers, 0, numbers.length-1);
            oneStepDone = false;
        }
    }
}
