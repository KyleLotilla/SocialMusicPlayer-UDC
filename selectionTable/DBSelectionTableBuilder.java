package selectionTable;

import java.sql.ResultSet;

public interface DBSelectionTableBuilder {
	public DBSelectionTable buildTable(ResultSet rsData);
}
