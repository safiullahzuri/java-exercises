package Ex22_Difficult_Questions;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class HistogramPane_v2 extends Pane {
    Rectangle[] bars;
    static int[] searchSet = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    static final int MAX_BAR_HEIGHT = 250;
    static final int WIDTH = 500;
    static final int RECTANGLE_WIDTH = WIDTH/ searchSet.length;


    public HistogramPane_v2(){
        setStyle("-fx-border-color:black;");
        bars = new Rectangle[searchSet.length];
        drawBars();
    }

    private void drawBars(){
        //shuffle the searchSet
        shuffleSearchSet();
        int xVal = 10;
        for (int i=0; i<searchSet.length; i++, xVal+=RECTANGLE_WIDTH){
            double height = searchSet[i]/ (double) searchSet.length * MAX_BAR_HEIGHT;
            Rectangle r = new Rectangle(xVal, MAX_BAR_HEIGHT-height, RECTANGLE_WIDTH, height);
            r.setFill(Color.WHITE);
            r.setStroke(Color.BLACK);
            Text text = new Text(r.getX()+5, r.getY()-5, searchSet[i]+"");
            this.getChildren().addAll(r, text);
            bars[i] = r;
        }
    }

    private void shuffleSearchSet(){
        for (int i=0; i< searchSet.length; i++){
            int randomIndex = (int) (Math.random()*searchSet.length);
            if (i != randomIndex){
                int temp = searchSet[i];
                searchSet[i] = searchSet[randomIndex];
                searchSet[randomIndex] = temp;
            }
        }
    }

    public void setUp() {
        linearSearchStateV2s = null;
        stateIndex = 0;
        shuffleSearchSet();
        drawBars();
    }

    private LinearSearchState_v2[] linearSearchStateV2s;
    private int stateIndex = 0;

    public int next(int key) {
        if (linearSearchStateV2s == null){
            generateSearchStates(key);
        }
        LinearSearchState_v2 s = linearSearchStateV2s[stateIndex];
        paintBarForState(s.getIndex());

        if (s.getFound()){
            return s.getIndex();
        }else if (stateIndex == linearSearchStateV2s.length-1){
            return -1;
        }
        stateIndex++;
        return -2;
    }

    private void paintBarForState(int index){
        paintBarsWhite();
        bars[index].setFill(Color.BLACK);
    }

    private void paintBarsWhite(){
        for (int i=0; i< bars.length; i++){
            bars[i].setFill(Color.WHITE);
        }
    }


    private void generateSearchStates(int key){
        linearSearchStateV2s = LinearSearchState_v2.generateSearchStates(searchSet, key);
    }
}
