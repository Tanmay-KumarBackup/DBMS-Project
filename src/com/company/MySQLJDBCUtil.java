package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLJDBCUtil {
    public static Connection getConnection() {
        Connection conn = null;

        try  {
            // assign db parameters
            String url = "jdbc:mysql://localhost:3306/Project_DBMS";
            String user = "root";
            String password = "0310";

            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
