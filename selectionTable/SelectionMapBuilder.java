package selectionTable;

import java.util.Map;

import songInfo.SongInfoCollection;

public interface SelectionMapBuilder {
	public Map<Integer, String> buildMap(SongInfoCollection songCollection);
}
