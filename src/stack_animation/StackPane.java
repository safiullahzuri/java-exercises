package stack_animation;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Stack;

public class StackPane extends Pane {

    public Stack<Integer> stack = new Stack<>();

    public StackPane(){
        drawPane();
    }

    private void drawPane(){
        getChildren().clear();
        setPrefSize(600, 400);

        int xPos = 300;
        int yPos = 300;

        final double WIDTH = 40;
        final double HEIGHT = 20;

        for (int i=0; i<stack.size(); i++){
            Rectangle rect = new Rectangle(xPos, yPos, WIDTH, HEIGHT);
            rect.setFill(Color.WHITE); rect.setStroke(Color.BLACK);
            Text text = new Text(rect.getX()+10, rect.getY()+15, stack.get(i)+"");
            getChildren().addAll(rect,text);
            yPos -= HEIGHT;
        }
    }

    public void push(int value) {
        stack.push(value);
        drawPane();
    }

    public void pop(int value) {
        if (stack.size() > 0){
            stack.pop();
        }
        drawPane();
    }

    public void peek() {
        System.out.println(stack.peek());
    }
}
