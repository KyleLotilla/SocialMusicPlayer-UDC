package selectionTable;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import songInfo.SongInfo;
import songInfo.SongInfoCollection;
import songInfo.SongInfoIterator;

public class SongTableBuilder implements DBSelectionTableBuilder {

	public DBSelectionTable buildTable(SongInfoCollection songInfoCollection) {
		DBSelectionTable dbstSongTable =  new DBSelectionTable();
		DefaultTableModel dtmTableModel = (DefaultTableModel) dbstSongTable.getModel();
		SongInfoIterator songInfoIterator = songInfoCollection.createIterator();
		Object songInfoRow[] = new String[5];
		
		while (songInfoIterator.hasnext()) {
			songInfoIterator.next();
			SongInfo songInfoIndex = songInfoIterator.getCurSongInfo();
			
			songInfoRow[0] = songInfoIndex.getTitle();
			songInfoRow[1] = songInfoIndex.getAlbumTitle();
			songInfoRow[2] = songInfoIndex.getGenre();
			songInfoRow[3] = songInfoIndex.getYear();
			songInfoRow[4] = songInfoIndex.getArtist();
			dtmTableModel.addRow(songInfoRow);
			
		}
		
		return dbstSongTable;
	}

}
