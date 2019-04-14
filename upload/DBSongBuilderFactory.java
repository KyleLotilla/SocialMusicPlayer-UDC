package upload;

import accountState.AccountTypeObserver;
import dbConnection.MySQLConnManager;

public class DBSongBuilderFactory implements SongBuilderFactory,  AccountTypeObserver {
	private String sType;
	
	
	public SongBuilder createSongBuilder() {
		if (sType.contentEquals("Artist"))
			return new DBArtistSongBuilder(new MySQLConnManager());
		else
			return new DBUserSongBuilder(new MySQLConnManager());
	}

	public void accountTypeUpdated(String sType) {
		this.sType = sType;
	}
	
}
