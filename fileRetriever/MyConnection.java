package fileRetriever;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;

/**
 *
 * @author user
 */
public class MyConnection {

    //Function to connect with the MySQL DB
    public static Connection getConnection() {
        
    final String DB_NAME = "musicplayer";
	final String DB_USER = "root";
	final String DB_PASS = "";
	final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useTimezone=true&serverTimezone=UTC";
	
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return conn;
    }
}