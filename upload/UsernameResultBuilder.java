package upload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbConnection.DBConnManager;
import sqlResult.SQLResultBuilder;
import sqlResult.SqlIDResultBuilder;

public class UsernameResultBuilder implements SqlIDResultBuilder {
	private DBConnManager connManager;
	
	public UsernameResultBuilder(DBConnManager connManager) {
		this.connManager = connManager;
	}
	
	public ResultSet buildResultSet(String sAccountID) {
		Connection dbConn = connManager.getConnection();
		ResultSet resultUsername = null;
		try {
			PreparedStatement preparedUsernameQuery = dbConn.prepareStatement("SELECT username FROM account WHERE accountID = ?");
			preparedUsernameQuery.setInt(1, Integer.parseInt(sAccountID));
			resultUsername = preparedUsernameQuery.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultUsername;
	}

}
