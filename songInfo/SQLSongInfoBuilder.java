package songInfo;
import java.sql.ResultSet;
import java.sql.SQLException;

import sqlResult.SQLResultBuilder;

public class SQLSongInfoBuilder implements SongInfoCollectionBuilder{
	private SQLResultBuilder sqlSongBuilder;
	
	public SQLSongInfoBuilder(SQLResultBuilder sqlSongBuilder) {
		this.sqlSongBuilder = sqlSongBuilder;
	}
	
	public SongInfoCollection buildSongInfoCollection() {
		SongInfoCollection songCollection = new SongInfoArrayList();
		ResultSet resultSong = sqlSongBuilder.buildResultSet();
		
		try {
			while (resultSong.next()) {
				SongInfo songInfoIndex = new DefaultSongInfo();
				songInfoIndex.putInfo("sSongID", String.valueOf(resultSong.getInt("SongID")));
				songInfoIndex.putInfo("sTitle", resultSong.getString("Title"));
				songInfoIndex.putInfo("sAlbumTitle", resultSong.getString("AlbumTitle"));
				songInfoIndex.putInfo("sGenre", resultSong.getString("Genre"));
				songInfoIndex.putInfo("nYear", resultSong.getInt("Year"));
				songInfoIndex.putInfo("sArtist", resultSong.getString("Artist"));
				songCollection.addSongInfo(songInfoIndex);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return songCollection;
	}
	
}
