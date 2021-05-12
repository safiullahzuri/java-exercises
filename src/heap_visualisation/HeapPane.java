package heap_visualisation;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.util.ArrayList;

public class HeapPane extends Pane {
    private Heap<Integer> heap = new Heap<>();

    public HeapPane() {
        drawPane();
    }

    private void drawPane() {
        getChildren().clear();

        final double WIDTH = 600;
        final double HEIGHT = 400;
        setPrefSize(WIDTH, HEIGHT);

        double initXPos = WIDTH / 2;
        double xPos = initXPos;
        double yPos = HEIGHT / 20;

        ArrayList<GraphCircle> circles = new ArrayList<>();
        ArrayList<Integer> heapArray = heap.getList();

        for (int index = 0, maxItemsForRow = 1; index < heapArray.size();) {
            double baseXPos = xPos;
            for (int i = index, count = 1; count <= maxItemsForRow &&
                    i < heapArray.size(); i++, count++) {
                Text t = new Text(xPos - 7, yPos + 4, String.valueOf(heapArray.get(i)));
                GraphCircle c = new GraphCircle(xPos, yPos, 20);
                c.setFill(Color.TRANSPARENT);
                c.setStroke(Color.BLACK);
                getChildren().addAll(t, c);
                circles.add(c);
                xPos += baseXPos * 2;
                if (index == 0) { break; }
            }
            index += maxItemsForRow;
            maxItemsForRow *= 2;
            xPos = initXPos / maxItemsForRow;
            yPos += 50;
        }

        for (int i = 0; i < circles.size(); i++) {
            GraphCircle c = circles.get(i);
            int leftChildIndex = i * 2 + 1;
            int rightChildIndex = i * 2 + 2;
            if (leftChildIndex >= circles.size()) { break; }

            Line line = new Line();
            line.setStartX(c.getBottomLeftHook().getX());
            line.setStartY(c.getBottomLeftHook().getY());
            line.setEndX(circles.get(leftChildIndex).getTopRightHook().getX());
            line.setEndY(circles.get(leftChildIndex).getTopRightHook().getY());

            getChildren().add(line);

            if (rightChildIndex < circles.size()) {
                line = new Line();
                line.setStartX(c.getBottomRightHook().getX());
                line.setStartY(c.getBottomRightHook().getY());
                line.setEndX(circles.get(rightChildIndex).getTopLeftHook().getX());
                line.setEndY(circles.get(rightChildIndex).getTopLeftHook().getY());
                getChildren().add(line);
            }
        }
    }

    public void insert(int n) {
        heap.add(n);
        drawPane();
    }

    public void removeRoot() {
        heap.remove();
        drawPane();
    }
}