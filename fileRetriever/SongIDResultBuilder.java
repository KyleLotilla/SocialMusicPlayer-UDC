package fileRetriever;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbConnection.DBConnManager;
import sqlResult.SQLResultBuilder;
import sqlResult.SqlIDResultBuilder;

public class SongIDResultBuilder implements SqlIDResultBuilder {
	private DBConnManager connManager;
	
	public SongIDResultBuilder(DBConnManager connManager) {
		this.connManager = connManager;
	}
	
	public ResultSet buildResultSet(String sSongID) {
		PreparedStatement preparedIDQuery;
		ResultSet idResult = null;
		try {
			preparedIDQuery = connManager.getConnection().prepareStatement("SELECT filePath AS filePath FROM song WHERE songID = ?");
			preparedIDQuery.setInt(1, Integer.parseInt(sSongID));
			idResult = preparedIDQuery.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return idResult;
	}
	
}
