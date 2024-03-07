package com.humber.atm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/atm_management?user=root&password=Welcome24!&useUnicode=true&characterEncoding=UTF-8";
    //private static final String USERNAME = "root";
    //private static final String PASSWORD = "Welcome24!";
   
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() throws SQLException {
    	return DriverManager.getConnection(JDBC_URL);
    }
    
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
