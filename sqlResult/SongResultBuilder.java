package sqlResult;

import java.sql.ResultSet;
import java.sql.SQLException;

import accountState.AccountIDObserver;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dbConnection.DBConnManager;

public class SongResultBuilder implements SQLResultBuilder, AccountIDObserver {
	private DBConnManager connManager;
	private int nAccountID;
	
	public SongResultBuilder(DBConnManager connManager) {
		this.connManager = connManager;
	}

	public ResultSet buildResultSet() {
		Connection dbConn = connManager.getConnection();
		String sSongQuery = "SELECT s.songID AS 'SongID', s.title AS 'Title', a.title AS 'AlbumTitle', s.genre AS 'Genre', s.year AS 'Year', s.artist AS 'Artist'" + 
				" FROM song s LEFT JOIN album a ON s.albumID = a.albumID" + 
				"			INNER JOIN account ac ON ac.accountID = s.uploaderID" + 
				" WHERE s.uploaderID = ?" + 
				" OR ac.type = \"Artist\"";
		
		try {
			PreparedStatement preparedSongQuery = dbConn.prepareStatement(sSongQuery);
			preparedSongQuery.setInt(1, nAccountID);
			ResultSet resultSong = preparedSongQuery.executeQuery();
			return resultSong;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void accountIDChanged(String sAccountID) {
		nAccountID = Integer.parseInt(sAccountID);
	}
	
}
