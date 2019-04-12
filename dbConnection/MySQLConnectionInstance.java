package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnectionInstance {
	private static MySQLConnectionInstance instance = null;
	private Connection dbConn = null;
	private final String DB_NAME = "musicplayer";
	private final String DB_USER = "root";
	private final String DB_PASS = "";
	private final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useTimezone=true&serverTimezone=UTC";
	
	
	private MySQLConnectionInstance() {
		try
        {
            Class.forName("com.mysql.jdbc.Driver");
            dbConn = DriverManager.getConnection(URL, DB_USER, DB_PASS);
        }catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	public static synchronized MySQLConnectionInstance getInstance() {
		if (instance == null)
			instance = new MySQLConnectionInstance();
		return instance;
	}
	
	public Connection getConnection() {
		return dbConn;
	}
}
