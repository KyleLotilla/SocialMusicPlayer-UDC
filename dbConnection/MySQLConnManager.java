package dbConnection;

import java.sql.Connection;

public class MySQLConnManager implements DBConnManager {

	public Connection getConnection() {
		return MySQLConnectionInstance.getInstance().getConnection();
	}
	
}
