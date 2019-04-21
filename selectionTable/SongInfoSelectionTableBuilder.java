package selectionTable;

import java.sql.ResultSet;

import songInfo.SongInfoCollection;

public interface SongInfoSelectionTableBuilder {
	public SongInfoSelectionTable buildTable(SongInfoCollection songInfoCollection);
}
