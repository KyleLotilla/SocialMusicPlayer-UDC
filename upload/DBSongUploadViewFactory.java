package upload;

import dbConnection.MySQLConnManager;

public class DBSongUploadViewFactory implements UploaderViewFactory {

	private int nUploaderID;
	private SongBuilder songBuild;
	
	public void createUploadView() {
		 DBSongUploadView uploadView = new DBSongUploadView(songBuild, nUploaderID, new MySQLConnManager());
	}

	public void setSongBuilder(SongBuilder songBuild) {
		this.songBuild = songBuild;
	}
	
}
