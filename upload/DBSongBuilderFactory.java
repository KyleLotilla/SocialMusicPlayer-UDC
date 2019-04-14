package upload;

import accountState.AccountTypeObserver;
import dbConnection.MySQLConnManager;
import serverIPAddress.LocalServerIPAddressManager;

public class DBSongBuilderFactory implements SongBuilderFactory,  AccountTypeObserver {
	private String sType;
	
	
	public SongBuilder createSongBuilder() {
		if (sType.contentEquals("Artist"))
			return new DBArtistSongBuilder(new MySQLConnManager(), new LocalServerIPAddressManager());
		else
			return new DBUserSongBuilder(new MySQLConnManager(), new LocalServerIPAddressManager());
	}

	public void accountTypeUpdated(String sType) {
		this.sType = sType;
	}
	
}
