package Ex22;

import Ex22_Difficult_Questions.SudokuSolver;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class Ex22_21 extends Application {

    private TextField[][] textFields = new TextField[SIZE][SIZE];
    private int[][] grid = new int[SIZE][SIZE];
    static final int SIZE = 9;
    private BorderPane borderPane = new BorderPane();
    private FlowPane flowPane = new FlowPane();


    @Override
    public void start(Stage primaryStage) throws Exception {

        flowPane.setPrefSize(270,270);
        borderPane.setCenter(flowPane);

        HBox hBox = new HBox();
        Button btnSolve = new Button("Solve");
        Button btnClear = new Button("Clear");
        Button btnNext = new Button("Next");

        hBox.getChildren().addAll(btnSolve, btnClear, btnNext);
        hBox.setAlignment(Pos.CENTER);

        btnSolve.setOnAction(e->{
            solveIt();
        });

        btnClear.setOnAction(e->{
            clearIt();
        });

        btnNext.setOnAction(e->{
            next();
        });

        borderPane.setBottom(hBox);
        setUpTextFields();
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void clearIt(){
        for (int i=0; i< textFields.length; i++){
            for (int j=0; j<textFields[i].length; j++){
                grid[i][j] = 0;
                textFields[i][j].setText(grid[i][j]+"");
            }
        }
    }
    SudokuSolver sudokuSolver;

    private void next(){
        if (sudokuSolver.solutions.size() > 1){
            System.out.println("next clicked");
            grid = sudokuSolver.solutions.get(solutionId);
            solutionId++;
            for (int i=0; i<grid.length; i++){
                for (int j=0; j<grid[i].length; j++){
                    textFields[i][j].setText(grid[i][j]+"");
                }
            }
        }
    }

    int solutionId = 0;
    private void solveIt(){

        for (int i=0; i< textFields.length; i++){
            for (int j=0; j<textFields[i].length; j++){
                int numberInCell = Integer.parseInt(textFields[i][j].getText());
                grid[i][j] = numberInCell;
            }
        }

        sudokuSolver = new SudokuSolver(grid);
        sudokuSolver.solve();
        grid = sudokuSolver.solutions.get(solutionId);
        System.out.println("solutions size: "+sudokuSolver.solutions.size());
        solutionId++;
        //grid = SudokuSolver.grid;


        for (int i=0; i<grid.length; i++){
            for (int j=0; j<grid[i].length; j++){
                textFields[i][j].setText(grid[i][j]+"");
            }
        }

    }

    private boolean sudokuSolver(int[][] grid){
        Set<String> seen = new HashSet<>();
        for (int i=0; i< grid.length; i++){
            for (int j=0; j<grid[i].length; j++){
                int cellContent = Integer.parseInt(textFields[i][j].getText());
                if (cellContent != 0){
                    if (!seen.add(cellContent+" found at row "+i) ||
                        !seen.add(cellContent+" found at column "+j)||
                        !seen.add(cellContent+" found in the box "+i/3+"-"+j/3)){
                        return false;
                    }
                }
            }
        }
        return true;
    }


    void setUpTextFields(){
        flowPane.getChildren().clear();
        for (int i=0; i<SIZE; i++){
            for (int j=0; j<SIZE; j++){
                TextField textField = new TextField();
                textField.setPrefSize(30,30);
                textField.setText("0");
                textFields[i][j] = textField;
                flowPane.getChildren().add(textField);
            }
        }
    }
}
