package jdbc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class FindGrade extends Application {

    private PreparedStatement preparedStatement;
    private TextField tfSSN = new TextField();
    private TextField tfCourseId = new TextField();
    private Label lblStatus = new Label();

    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeDB();

        Button btnShowGrade = new Button("Show Grade");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("SSN"), tfSSN, new Label("Course ID"), tfCourseId, btnShowGrade);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(hBox, lblStatus);

        tfSSN.setPrefColumnCount(6);
        tfCourseId.setPrefColumnCount(6);
        btnShowGrade.setOnAction(e-> showGrade());

        Scene scene = new Scene(vBox, 420, 80);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Find Grade");
        primaryStage.show();
    }

    private void showGrade(){
        String ssn= tfSSN.getText();
        String courseId = tfCourseId.getText();

        try {
            String queryString = "select firstname, mi, lastName, title, grade from Student, Enrollment, Course where student.ssn='"+ssn+"' and Enrollment.courseId='"+courseId+"' and Enrollment.courseId=Course.courseId and Enrollment.ssn = Student.ssn";
            preparedStatement.setString(1, ssn);
            preparedStatement.setString(2, courseId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                String lastName = resultSet.getString(1);
                String mi  =resultSet.getString(2);
                String firstName = resultSet.getString(3);
                String title = resultSet.getString(4);
                String grade = resultSet.getString(5);

                lblStatus.setText(firstName+" "+mi+" "+lastName+" "+"'s grade on course "+title+" is "+grade);
            }else {
                lblStatus.setText("Not found");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void initializeDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
            System.out.println("Database connected");
            String queryString = "select firstname, mi, lastName, title, grade from Student, Enrollment, Course where student.ssn=? and Enrollment.courseId=? and Enrollment.courseId=Course.courseId and Enrollment.ssn = Student.ssn";
            preparedStatement = connection.prepareStatement(queryString);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
