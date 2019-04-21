package selectionTable;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class SongInfoSelectionTable extends JTable implements TableSelectionObservable {
	private ArrayList<TableSelectionObserver> tsObservers;
	
	public SongInfoSelectionTable() {
		super();
		tsObservers = new ArrayList<TableSelectionObserver>();
		setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Title", "Album", "Genre", "Year", "Artist"
				}
				)
		);
		this.setRowSelectionAllowed(true);
		this.getSelectionModel().addListSelectionListener(new TableSelectionListener());
	}

	public void addObserver(TableSelectionObserver tsObserver) {
		tsObservers.add(tsObserver);
	}

	public void notifyObservers(int nSelection) {
		for (TableSelectionObserver tsCounter : tsObservers)
			tsCounter.selectionChanged(nSelection);
	}
	
	
	private class TableSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent lsEvent) {
			notifyObservers(getSelectedRow());
		}
	}
}
