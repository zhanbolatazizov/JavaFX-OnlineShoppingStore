package sample;

import java.sql.*;

public class DataBase {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{

        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oss", "root", "");

        return dbConnection;
    }

    public void singIN(User user){
        String insert = "INSERT INTO users (name, surname, username, password, email) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet login(User user) {
        ResultSet resultSet = null;

        String select = "SELECT * FROM users WHERE username =? AND password =? ";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            resultSet = preparedStatement.executeQuery() ;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
