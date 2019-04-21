package panelView;

import java.awt.FlowLayout;

import selectionTable.PlayBtn;
import selectionTable.SelectionMapBuilder;
import selectionTable.SongInfoSelectionTable;
import selectionTable.SongInfoSelectionTableBuilder;
import songInfo.SongInfoCollection;
import songInfo.SongInfoCollectionBuilder;

public class SongListPanel extends JMainPanelView {
	private PlayBtn btnPlay;
	private SongInfoCollectionBuilder songInfoBuilder;
	private SongInfoSelectionTable songTable;
	private SongInfoSelectionTableBuilder songTableBuilder;
	private SelectionMapBuilder selectMapBuilder;
	
	public SongListPanel(PlayBtn btnPlay, SongInfoCollectionBuilder songInfoBuilder, SongInfoSelectionTableBuilder songTableBuilder, SelectionMapBuilder selectMapBuilder) {
		this.btnPlay = btnPlay;
		this.songInfoBuilder = songInfoBuilder;
		this.songTableBuilder = songTableBuilder;
		this.selectMapBuilder = selectMapBuilder;
		
		setLayout(new FlowLayout());
	}
	
	public void refreshView() {
		removeAll();
		SongInfoCollection songCollection = songInfoBuilder.buildSongInfoCollection();
		songTable = songTableBuilder.buildTable(songCollection);
		songTable.addObserver(btnPlay);
		btnPlay.setMapSelect(selectMapBuilder.buildMap(songCollection));
		add(songTable);
		songTable.setBounds(100, 100, 700, 500);
		add(btnPlay);
	}

}
