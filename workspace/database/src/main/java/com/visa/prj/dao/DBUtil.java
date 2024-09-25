package com.visa.prj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL ="jdbc:mysql://localhost:3306/VRP";
    private static String USER = "root";
    private  static  String PASSWORD = "Welcome123";

    static  {
        try {
            Class.forName(DRIVER); // load the drivers
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection con) {
        if(con != null) {
            try {
                con.close();
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
