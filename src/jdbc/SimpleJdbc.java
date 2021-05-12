package jdbc;



import java.sql.*;

public class SimpleJdbc {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
        System.out.println("Database connected");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from student");

        while (resultSet.next()){
            System.out.println(resultSet.getString(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3));
        }

        connection.close();

    }
}
