package upload;

import dbConnection.MySQLConnManager;

public class DBSongUploadViewFactory implements UploaderViewFactory {

	private SongBuilderFactory buildFactory;
	private String sUploaderID;
	
	public DBSongUploadViewFactory(SongBuilderFactory buildFactory) {
		this.buildFactory = buildFactory;
	}
	
	public void createUploadView() {
		 DBUserSongUploadView uploadView = new DBUserSongUploadView(buildFactory.createSongBuilder(), sUploaderID, new MySQLConnManager());
	}

}
