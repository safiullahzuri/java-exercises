package linkedListAnimation;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.LinkedList;

public class LinkedListPane extends Pane {
    private LinkedList<Integer> list;
    private Text tMessage;

    public LinkedListPane() {
        setUp();
    }

    private void setUp() {
        list = new LinkedList<>();
        drawPane();
    }

    private void drawPane() {
        getChildren().clear();
        final double WIDTH = 600.0;
        final double HEIGHT = 300.0;
        setMinSize(WIDTH, HEIGHT);

        tMessage = new Text(10.0, 15.0, "");

        getChildren().add(tMessage);

        double xPos = 10.0;
        double yPos = 100.0;
        final double SLOT_WIDTH = 40.0;
        final double SLOT_HEIGHT = 20.0;
        final double LINK_WIDTH = 20.0;

        for (int i = 0; i < list.size(); i++) {
            Rectangle slotRect = new Rectangle(xPos, yPos, SLOT_WIDTH, SLOT_HEIGHT);
            Text slotText =
                    new Text(slotRect.getX() + 10, slotRect.getY() + 15, list.get(i) + "");
            xPos += SLOT_WIDTH;

            Rectangle linkRect = new Rectangle(xPos, yPos, LINK_WIDTH, SLOT_HEIGHT);

            slotRect.setFill(Color.WHITE);
            linkRect.setFill(Color.WHITE);
            slotRect.setStroke(Color.BLACK);
            linkRect.setStroke(Color.BLACK);
            getChildren().addAll(slotRect, slotText, linkRect);

            if (i == 0 || i == list.size() - 1) {
                Text headTailText = new Text(i == 0 ? "HEAD" : "TAIL");
                headTailText.setX(slotRect.getX() + 10);
                headTailText.setY(slotRect.getY() - 3);
                getChildren().add(headTailText);
            }
            xPos += SLOT_WIDTH;
            if (i < list.size() - 1) {
                Line connection = new Line();
                connection.setStartX(linkRect.getX() + (LINK_WIDTH / 2));
                connection.setStartY(linkRect.getY() + (SLOT_HEIGHT / 2));
                connection.setEndX(xPos);
                connection.setEndY(yPos + (SLOT_HEIGHT / 2));
                getChildren().add(connection);
            }
        }
    }

    public void search(int value) {
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == value) {
                index = i;
                break;
            }
        }

        if (index < 0) {
            tMessage.setText(value + " not found");
        } else {
            tMessage.setText(value + " is at index " + index);
        }
    }

    public void insert(int value, int index) {
        if (index < 0) {
            list.add(value);
        } else {
            list.add(index, value);
        }
        drawPane();
    }

    public void delete(int value) {
        if (list.remove(new Integer(value))) {
            drawPane();
        } else {
            tMessage.setText(value + " not found in list");
        }
    }
}
