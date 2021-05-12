package Ex22_Difficult_Questions;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import static Ex22_Difficult_Questions.MaximumSizeSubMatrix.printArray;

public class Sudoku2 extends Application {

    public static int[][] grid = {
            {9,0,0,0,0,0,0,0,5},
            {0,0,2,0,0,0,0,0,0},
            {8,0,0,3,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,1,0,0,0},
            {7,0,0,0,0,0,0,0,0},
            {0,0,0,5,0,0,0,0,0},
            {0,0,0,0,0,0,8,0,9}
    };

    private ArrayList<int[][]> solutions = new ArrayList<>();

    private boolean solve(){

        for (int i=0; i< 9; i++){
            for (int j=0; j<9; j++){

                if (grid[i][j] == 0){

                    for (int number=1; number<=9; number++){

                        if (isValid(i, j, number)){
                            grid[i][j] = number;
                            if (solve()){
                                return true;
                            }else{
                                grid[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        solutions.add(grid);
        return true;

    }

    private boolean isValid(int row, int column, int number){
        return !isInBox(row, column, number) && !isInColumn(column, number) && !isInRow(row, number);
    }


    private boolean isInRow(int row, int number){
        for (int i=0; i<9; i++){
            if (grid[row][i] == number){
                return true;
            }
        }
        return false;
    }

    private boolean isInColumn(int column, int number){
        for (int j=0; j<9; j++){
            if (grid[j][column] == number){
                return true;
            }
        }
        return false;
    }

    private boolean isInBox(int row, int column, int number){
        int r = row - row%3;
        int c = column - column%3;

        for (int i=r; i<r+3; i++){
            for (int j=c; j<c+3; j++){
                if (grid[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    FlowPane flowPane;
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(270,270);

        flowPane = new FlowPane();

        setUpTextFeilds(grid);

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        Button btnSolve = new Button("Solve");
        Button btnNext = new Button("Next");

        hBox.getChildren().addAll(btnSolve, btnNext);

        btnSolve.setOnAction(e->{
            solveMySudoku();
        });

        btnNext.setOnAction(e->{
            next();
        });

        borderPane.setCenter(flowPane);
        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    TextField[][] textFields;

    void setUpTextFeilds(int[][] grid){
        flowPane.getChildren().clear();;
        textFields = new TextField[9][9];
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                TextField textField = new TextField();
                textField.setPrefSize(30,30);
                textField.setText(grid[i][j]+"");
                textFields[i][j] = textField;
                flowPane.getChildren().add(textField);
            }
        }
    }

    static int solutionId= 0;
    void solveMySudoku(){
        int[][] grid= new int[9][9];
        for (int i=0; i< grid.length; i++){
            for (int j=0; j<grid[i].length; j++){
                grid[i][j] = Integer.parseInt(textFields[i][j].getText());
            }
        }
        this.solve();
        System.out.println("solutions size: "+solutions.size());
        int[][] firstGrid = solutions.get(solutionId);
        solutionId++;
        printArray(firstGrid);



        setUpTextFeilds(firstGrid);
        for (int i=1; i<solutions.size(); i++){
            if (solutions.get(i) == solutions.get(i-1)){
                System.out.printf("solutions %d and %d are the same.\n", i, (i+1));
            }
        }

    }

    private void next(){
        if (solutions.size() > 1){
            printArray(solutions.get(solutionId));
            setUpTextFeilds(solutions.get(solutionId));
            solutionId++;
        }
    }
}
