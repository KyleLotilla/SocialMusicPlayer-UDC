package upload;

import accountState.AccountIDObserver;
import dbConnection.MySQLConnManager;

public class DBSongUploadViewFactory implements UploaderViewFactory, AccountIDObserver {

	private SongBuilderFactory buildFactory;
	private String sUploaderID;
	
	public DBSongUploadViewFactory(SongBuilderFactory buildFactory) {
		this.buildFactory = buildFactory;
	}
	
	public void createUploadView() {
		 DBUserSongUploadView uploadView = new DBUserSongUploadView(buildFactory.createSongBuilder(), sUploaderID, new MySQLConnManager());
	}


	public void accountIDChanged(String sAccountID) {
		sUploaderID = sAccountID;
	}

}
