package upload;

public class DBSongUploadViewFactory implements UploaderViewFactory {

	private int nUploaderID;
	private SongBuilder songBuild;
	
	public void createUploadView() {
		 DBSongUploadView uploadView = new DBSongUploadView(songBuild, nUploaderID);
	}

	public void setSongBuilder(SongBuilder songBuild) {
		this.songBuild = songBuild;
	}
	
}
