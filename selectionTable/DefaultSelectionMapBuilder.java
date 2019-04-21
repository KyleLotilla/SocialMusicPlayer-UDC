package selectionTable;

import java.util.HashMap;
import java.util.Map;

import songInfo.SongInfo;
import songInfo.SongInfoCollection;
import songInfo.SongInfoIterator;

public class DefaultSelectionMapBuilder implements SelectionMapBuilder {

	
	public Map<Integer, String> buildMap(SongInfoCollection songCollection) {
		Map<Integer, String> mapSelect = new HashMap<Integer, String>();
		SongInfoIterator songIterator = songCollection.createIterator();
		int nIndex = 0;
		
		while (songIterator.hasnext()) {
			SongInfo songInfoIndex = songIterator.getCurSongInfo();
			String sSongID = (String) songInfoIndex.getInfo("sSongID");
			mapSelect.put(nIndex, sSongID);
			nIndex++;
			songIterator.next();
		}
		
		return mapSelect;	
	}

}
