package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    public static Connection getConnection(){
        String userName = "Iamroot";
        String password = "root";
        String connectionUrl = "jdbc:mysql://localhost:3306/test";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionUrl, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
