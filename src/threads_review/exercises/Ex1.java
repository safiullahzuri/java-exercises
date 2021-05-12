package threads_review.exercises;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex1 extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        StackPane pane = new StackPane();
        pane.getChildren().add(textArea);

        Scene scene = new Scene(pane, 250, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Concurrent Output");
        primaryStage.show();

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new PrintChar(textArea,'a', 100));
        service.execute(new PrintChar(textArea, 'b', 100));
        service.execute(new PrintNum(textArea, 100));
        service.shutdown();
    }


    static class PrintChar implements Runnable{
        char c;
        int num;
        TextArea textArea;

        public PrintChar(TextArea textArea, char c, int num){
            this.textArea = textArea;
            this.c = c;
            this.num = num;
        }

        @Override
        public void run() {
            for (int i=0; i<num; i++){
                textArea.appendText(c+" ");
            }
        }
    }

    static class PrintNum implements Runnable{

        int num;
        TextArea textArea;

        public PrintNum(TextArea textArea, int num){
            this.textArea = textArea;

            this.num = num;
        }

        @Override
        public void run() {
            for (int i=0; i<num; i++){
                textArea.appendText(i+" ");
            }
        }
    }
}
