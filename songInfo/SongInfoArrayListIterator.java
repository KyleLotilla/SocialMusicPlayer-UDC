package songInfo;

import java.util.ArrayList;

public class SongInfoArrayListIterator implements SongInfoIterator {
	private ArrayList<SongInfo> songInfoList;
	private int nIndex;
	
	public SongInfoArrayListIterator(ArrayList<SongInfo> songInfoList) {
		this.songInfoList = songInfoList; 
		nIndex = 0;
	}

	public void next() {
		nIndex++;
	}

	public boolean hasnext() {
		if (nIndex < songInfoList.size())
			return true;
		else
			return false;
	}

	public SongInfo getCurSongInfo() {
		return songInfoList.get(nIndex);
	}
}
