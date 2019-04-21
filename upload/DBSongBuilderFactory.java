package upload;

import accountState.AccountIDObserver;
import accountState.AccountTypeObserver;
import dbConnection.MySQLConnManager;
import serverIPAddress.LocalServerIPAddressManager;

public class DBSongBuilderFactory implements SongBuilderFactory, AccountTypeObserver, AccountIDObserver {
	private String sType;
	private String sAccountID;
	
	public SongBuilder createSongBuilder() {
		if (sType.contentEquals("Artist"))
			return new DBArtistSongBuilder(new MySQLConnManager(), new LocalServerIPAddressManager(), new UsernameResultBuilder(new MySQLConnManager()));
		else
			return new DBUserSongBuilder(new MySQLConnManager(), new LocalServerIPAddressManager());
	}

	public void accountTypeUpdated(String sType) {
		this.sType = sType;
	}

	
	public void accountIDChanged(String sAccountID) {
		this.sAccountID = sAccountID;
	}
	
}
