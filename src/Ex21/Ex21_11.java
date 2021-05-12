package Ex21;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ex21_11 extends Application {

    private ComboBox<String> cbGender;
    private ComboBox<Integer> cbYear;
    private TextField tfName;
    private Button btnSearch;
    private Label resultLabel;

    Map<String, Integer>[] boyMaps = new HashMap[10];
    Map<String, Integer>[] girlMaps = new HashMap[10];



    @Override
    public void start(Stage primaryStage) throws Exception {

        initMaps();
        System.out.println(boyMaps[3].get("Dajuan"));
        GridPane gridPane = new GridPane();
        Label labelYear = new Label("Select a Year");

        cbGender = new ComboBox<>();
        cbGender.getItems().addAll("Male", "Female");

        cbYear = new ComboBox<>();
        for (int i=1; i<=10; i++){
            int baseYear = 2000;
            int finalyear = baseYear+i;
            cbYear.getItems().add(finalyear);
        }
        tfName = new TextField();
        btnSearch = new Button("Search");
        resultLabel = new Label();

        gridPane.add(labelYear, 0, 0);
        gridPane.add(cbYear, 1, 0);

        Label lblGender = new Label("Boy or Girl?");
        gridPane.add(lblGender, 0, 1);
        gridPane.add(cbGender, 1,1);

        Label lblName = new Label("Enter the name");
        gridPane.add(lblName, 0, 2);
        gridPane.add(tfName, 1,2);

        gridPane.add(btnSearch,1,3);

        gridPane.add(resultLabel, 1,4);
        btnSearch.setOnAction(e->{
            String gender = cbGender.getValue();
            int year= cbYear.getValue();
            String name = tfName.getText();
            findResultsFor(gender, year, name);
        });

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setWidth(300);
        primaryStage.setHeight(200);
        primaryStage.show();
    }

    private void findResultsFor(String gender, int year, String name) {
        System.out.println(gender+" "+year+" "+name);
        try{
            int lYear = year-2001;
            int rank = -1;
            if (gender.equals("Male")){
                rank = boyMaps[lYear].get(name);
            }else{
                rank = girlMaps[lYear].get(name);
            }
            resultLabel.setText(String.format("Name %s has been ranked %d.\n", name, rank));
        }catch (Exception e){
            resultLabel.setText(e.toString());
            e.printStackTrace();
        }

    }
    private void initMaps() throws Exception{
        int baseYear = 2000;
        for(int i=0; i<10; i++){
            boyMaps[i] = new HashMap<>();
            girlMaps[i] = new HashMap<>();

            int myYear = baseYear+i+1;
            String fileName = "D://names//yob"+myYear+".txt";
            Scanner fileReader= new Scanner(new File(fileName));
            while (fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                String words[] = line.split(",");
                if (words[1].equals("M")){
                    boyMaps[i].put(words[0], Integer.parseInt(words[2]));
                }else{
                    girlMaps[i].put(words[0], Integer.parseInt(words[2]));
                }
            }
        }
    }

}
