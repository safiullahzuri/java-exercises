package rmi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StudentServerInterfaceClient extends Application {

    private StudentServerInterface studentServerInterface;

    private Button btnGetScore = new Button("Get Score");
    private TextField tfName = new TextField();
    private TextField tfScore = new TextField();





    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.add(new Label("Name"), 0, 0);
        gridPane.add(new Label("Score"), 0, 1);
        gridPane.add(tfName, 1, 0);
        gridPane.add(tfScore, 1,1);

        gridPane.add(btnGetScore, 1, 2);


        Scene scene = new Scene(gridPane, 250, 250);
        primaryStage.setTitle("Student server interface client");
        primaryStage.setScene(scene);
        primaryStage.show();

        initRMi();

        btnGetScore.setOnAction(e->{
            getScore();
        });
    }


    protected void initRMi(){
        String host = "192.168.1.2";
        System.setProperty("java.rmi.server.hostname",host);


        try {
            Registry registry = LocateRegistry.getRegistry(host);

            studentServerInterface = (StudentServerInterface) registry.lookup("StudentServerInterfaceImpl");
            System.out.println("Server object "+studentServerInterface+" found.");

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            System.out.println(e);
        }

    }

    private void getScore(){
        try {
            double score = studentServerInterface.findScore(tfName.getText().trim());
            if (score < 0)
                tfScore.setText("Not found.");
            else
                tfScore.setText(new Double(score).toString());

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


}
