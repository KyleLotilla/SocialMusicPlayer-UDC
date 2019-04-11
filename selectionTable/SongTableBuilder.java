package selectionTable;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class SongTableBuilder implements DBSelectionTableBuilder {

	public DBSelectionTable buildTable(ResultSet rsData) {
		DBSelectionTable dbstSongTable =  new DBSelectionTable();
		DefaultTableModel dtmTableModel = (DefaultTableModel) dbstSongTable.getModel();
		
		try {
			rsData.beforeFirst();
			Object row[] = new Object[5];
			while (rsData.next()) {
				row[0] = rsData.getString("Title");
				row[1] = rsData.getString("AlbumTitle");
				row[2] = rsData.getString("Genre");
				row[3] = rsData.getString("Year");
				row[4] = rsData.getString("Artist");
				dtmTableModel.addRow(row);
			}
			rsData.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dbstSongTable;
	}

}
