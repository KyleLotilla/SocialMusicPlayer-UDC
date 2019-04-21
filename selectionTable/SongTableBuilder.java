package selectionTable;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import songInfo.SongInfo;
import songInfo.SongInfoCollection;
import songInfo.SongInfoIterator;

public class SongTableBuilder implements SongInfoSelectionTableBuilder {

	public SongInfoSelectionTable buildTable(SongInfoCollection songInfoCollection) {
		SongInfoSelectionTable dbstSongTable =  new SongInfoSelectionTable();
		DefaultTableModel dtmTableModel = (DefaultTableModel) dbstSongTable.getModel();
		SongInfoIterator songInfoIterator = songInfoCollection.createIterator();
		Object songInfoRow[] = new String[5];
		
		while (songInfoIterator.hasnext()) {
			SongInfo songInfoIndex = songInfoIterator.getCurSongInfo();
			
			songInfoRow[0] = songInfoIndex.getInfo("sTitle");
			songInfoRow[1] = songInfoIndex.getInfo("sAlbumTitle");
			songInfoRow[2] = songInfoIndex.getInfo("sGenre");
			songInfoRow[3] = String.valueOf(songInfoIndex.getInfo("nYear"));
			songInfoRow[4] = songInfoIndex.getInfo("sArtist");
			dtmTableModel.addRow(songInfoRow);
			
			songInfoIterator.next();
		}
		
		return dbstSongTable;
	}

}
