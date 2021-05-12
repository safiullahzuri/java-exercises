package Ex22_Difficult_Questions;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class HistogramPane3 extends Pane {
    private static int[] searchSet = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
            14, 15, 16, 17, 18, 19, 20};

    private static final double WIDTH = 500.0;
    private static final double PANE_HEIGHT = 300.0;
    private static final double MAX_BAR_HEIGHT = 250.0;
    private static final double BAR_WIDTH = (WIDTH-20)/ searchSet.length;


    private BinarySearchState[] states;
    private Rectangle[] bars;
    private int stateIndex = 0;

    public HistogramPane3(){
        setPrefSize(WIDTH, PANE_HEIGHT);
        setStyle("-fx-border-color:black;");
        drawBars();
    }

    public void setup(){
        stateIndex = 0;
        states = null;
        drawBars();
    }

    private void drawBars(){
        getChildren().clear();;
        bars = new Rectangle[searchSet.length];
        double xVal = 10;

        for (int i=0; i<searchSet.length; i++, xVal+= BAR_WIDTH){
            double height = searchSet[i]/(double)searchSet.length *  MAX_BAR_HEIGHT;
            Rectangle r = new Rectangle(xVal, PANE_HEIGHT-height, BAR_WIDTH, height);
            r.setFill(Color.WHITE);
            r.setStroke(Color.BLACK);
            Text t = new Text(r.getX()+5, r.getY()-5, searchSet[i]+"");
            getChildren().addAll(r,t);
            bars[i] = r;
        }

    }

    private void getSearchStates(int key){
        states = BinarySearchState.generateSearchStates(searchSet, key);
    }

    public int next(int key){
        if (states == null){
            getSearchStates(key);
        }
        BinarySearchState s = states[stateIndex];
        paintBarsForState(s.getLow(), s.getHigh(), s.getMid());
        if (s.getFound()){
            return s.getMid();
        }else if (stateIndex >= states.length || states[stateIndex+1] == null){
            return -1;
        }
        stateIndex++;
        return -2;
    }

    private void paintBarsForState(int low, int high, int mid){
        paintBarsWhite();
        paintRangeGray(low, high);
        paintMidBlack(mid);
    }

    private void paintBarsWhite(){
        for (Rectangle r: bars){
            r.setFill(Color.WHITE);
        }
    }

    private void paintRangeGray(int low,int high){
        for (int i=low; i<=high; i++){
            bars[i].setFill(Color.GRAY);
        }
    }

    private void paintMidBlack(int mid){
        bars[mid].setFill(Color.BLACK);
    }

}
