package songInfo;

import java.util.HashMap;
import java.util.Map;

public class DefaultSongInfo implements SongInfo {
	private Map<String, Object> mapInfo;
	
	public DefaultSongInfo() {
		mapInfo = new HashMap<String, Object>();
	}

	public void putInfo(String sInfo, Object info) {
		mapInfo.put(sInfo, info);
	}

	public Object getInfo(String sInfo) {
		return mapInfo.get(sInfo);
	}
}
