package ex24_12;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class QueueVisualizer extends BorderPane {

    public QueueVisualizer(){
        drawGUI();
    }

    private void drawGUI(){
        QueuePane queuePane = new QueuePane();
        queuePane.setStyle("-fx-border-color: black;");

        Text tEnter = new Text("Enter a value: ");
        TextField tfEnter = new TextField();
        tfEnter.setPrefColumnCount(3);

        Button btnEnqueue = new Button("Enqueue");
        Button btnDequeue = new Button("Dequeue");

        btnEnqueue.setOnAction(e->{
            if (tfEnter.getText().isEmpty())return;
            queuePane.enqueue(Integer.parseInt(tfEnter.getText()));
        });

        btnDequeue.setOnAction(e->{
            queuePane.dequeue();
        });

        HBox hbControl  =new HBox(10);
        hbControl.getChildren().addAll(tEnter, tfEnter, btnEnqueue, btnDequeue);
        hbControl.setAlignment(Pos.CENTER);

        setCenter(queuePane);
        setBottom(hbControl);
    }
}
