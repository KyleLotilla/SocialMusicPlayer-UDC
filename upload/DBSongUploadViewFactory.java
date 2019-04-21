package upload;

import accountState.AccountIDObserver;
import accountState.AccountTypeObserver;
import dbConnection.MySQLConnManager;

public class DBSongUploadViewFactory implements UploaderViewFactory, AccountIDObserver, AccountTypeObserver {

	private SongBuilderFactory buildFactory;
	private String sUploaderID;
	private String sType;
	
	public DBSongUploadViewFactory(SongBuilderFactory buildFactory) {
		this.buildFactory = buildFactory;
	}
	
	public void createUploadView() {
		 if (sType.contentEquals("Artist")) {
			 DBArtistSongUploadView uploadArtistView = new DBArtistSongUploadView(buildFactory.createSongBuilder(), sUploaderID, new MySQLConnManager());
		 }
		 else {
			 DBUserSongUploadView uploadView = new DBUserSongUploadView(buildFactory.createSongBuilder(), sUploaderID, new MySQLConnManager());
		 }
	}


	public void accountIDChanged(String sAccountID) {
		sUploaderID = sAccountID;
	}

	
	public void accountTypeUpdated(String sType) {
		this.sType = sType;
	}

}
