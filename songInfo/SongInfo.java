package songInfo;

public class SongInfo {
	private String sSongID;
	private String sTitle;
	private String sAlbumTitle;
	private String sGenre;
	private int nYear;
	private String sArtist;
	
	public SongInfo(String sSongID, String sTitle, String sAlbumTitle, String sGenre, int nYear, String sArtist) {
		this.sSongID = sSongID;
		this.sTitle = sTitle;
		this.sAlbumTitle = sAlbumTitle;
		this.sGenre = sGenre;
		this.nYear = nYear;
		this.sArtist = sArtist;
	}
	
	public String getSongID() {
		return sSongID;
	}
	
	public String getTitle() {
		return sTitle;
	}
	
	public String getAlbumTitle() {
		return sAlbumTitle;
	}
	
	public String getGenre() {
		return sGenre;
	}
	
	public int getYear() {
		return nYear;
	}
	
	public String getArtist() {
		return sArtist;
	}
}
