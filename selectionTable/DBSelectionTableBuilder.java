package selectionTable;

import java.sql.ResultSet;

import songInfo.SongInfoCollection;

public interface DBSelectionTableBuilder {
	public DBSelectionTable buildTable(SongInfoCollection songInfoCollection);
}
