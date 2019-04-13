package songInfo;

import java.util.ArrayList;

public class SongInfoArrayList implements SongInfoCollection {
	private ArrayList<SongInfo> songInfoList;
	
	public SongInfoArrayList() {
		songInfoList = new ArrayList<SongInfo>(); 
	}

	public void addSongInfo(SongInfo songInfo) {
		songInfoList.add(songInfo);
	}

	public SongInfoIterator createIterator() {
		return new SongInfoArrayListIterator(songInfoList);
	}
	
	
}
