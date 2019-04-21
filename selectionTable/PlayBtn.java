package selectionTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;

import playerFacade.SongPlayer;

public class PlayBtn extends JButton implements TableSelectionObserver {
	private SongPlayer songPlayer;
	private int nSelection;
	private Map<Integer, String> mapSelect;
	
	public PlayBtn(SongPlayer songPlayer) {
		super("Play Song");
		this.songPlayer = songPlayer;
		nSelection = -1;
		addActionListener(new PlayBtnListener());
	}
	
	public void setMapSelect(Map<Integer, String> mapSelect) {
		this.mapSelect = mapSelect;
	}
	
	public void selectionChanged(int nSelection) {
		this.nSelection = nSelection;
	}
	
	private class PlayBtnListener implements ActionListener {

		public void actionPerformed(ActionEvent aeEvent) {
			if (nSelection >= 0)
				songPlayer.playSong(mapSelect.get(nSelection));
		}
		
	}
}
