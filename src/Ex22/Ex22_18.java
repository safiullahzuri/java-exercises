package Ex22;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ex22_18 extends Application {

    //the program has four parts
    private BorderPane borderPane;
    private Label resultLabel;
    private HistogramPane3 histogramPane3;
    private Label label = new Label("Key (in double)");
    private TextField textField;
    private Button stepBtn, resetBtn;

    @Override
    public void start(Stage primaryStage) throws Exception {
        setUp();
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void setUp(){
        borderPane = new BorderPane();
        resultLabel = new Label();
        histogramPane3 = new HistogramPane3();
        textField = new TextField();
        stepBtn = new Button("Step");
        resetBtn = new Button("Reset");
        borderPane.setCenter(histogramPane3);
        HBox hBox = new HBox(resultLabel);
        borderPane.setTop(hBox);

        HBox hBox2 = new HBox(label, textField, stepBtn, resetBtn);
        borderPane.setBottom(hBox2);

        stepBtn.setOnMouseClicked(e->{
            System.out.println("here");
            histogramPane3.next(Integer.parseInt(textField.getText()));
        });

    }


    static class HistogramPane3 extends Pane {
        Rectangle[] bars;
        static int PANE_WIDTH = 500;
        static int PANE_HEIGHT = 300;
        private static final double MAX_BAR_HEIGHT = 250;
        private static final double BAR_WIDTH = (PANE_WIDTH-20)/20;
        private static int[] searchSet = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
                14, 15, 16, 17, 18, 19, 20};

        public HistogramPane3(){
            setStyle("-fx-border-color:black");
            bars = new Rectangle[searchSet.length];
            drawBars();
        }

        private void drawBars(){
            getChildren().clear();
            double xVal = 10.0;
            for (int i=0; i<20; i++, xVal+= BAR_WIDTH){
                double height = searchSet[i] / (double) searchSet.length * MAX_BAR_HEIGHT;
                Rectangle r= new Rectangle(xVal,PANE_HEIGHT-height, BAR_WIDTH, height );
                bars[i] = r;
                bars[i].setStroke(Color.BLACK);
                bars[i].setFill(Color.WHITE);
                Text text = new Text(r.getX()+5, r.getY()-5, searchSet[i]+"");
                this.getChildren().addAll(bars[i],text);
            }
        }

        int searchTime = 1;
        private void next(int key){
            //make the search range brown
            for (int i=0; i<searchTime/2; i++){
                bars[i].setFill(Color.BROWN);
                System.out.println("something");
            }
        }

        private int binarySearch(int[] numbers, int key){
            int low = 0;
            int high = numbers.length;
            int mid = (low+high)/2;

            while (low <= high){
                if (numbers[mid] == key){
                    return mid;
                }else if (numbers[mid] > key){
                    high = mid;
                }else if (numbers[mid] < key){
                    low = mid;
                }
            }
            return -1;
        }

    }
}
