package playerProgress;
import java.util.HashMap;
import java.util.Map;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

public class MP3ProgressObservable implements PlaybackProgressObservable, BasicPlayerListener {
	
	private PlaybackProgressObserver progObserObserver;

	public void notifyObservers(Map mapProgressProperties) {
		progObserObserver.progressUpdated(mapProgressProperties);
	}

	public void newObserver(PlaybackProgressObserver progObserObserver) {
		this.progObserObserver = progObserObserver;
	}

	public void progress(int nBytesRead, long lMircoseconds, byte[] bytePcmData, Map mapAPIProperties) {
		HashMap<String, Object> mapProgressProperties = new HashMap<String, Object>();
		long lCurFrame = (long) mapAPIProperties.get("mp3.frame");
		long lSecondsPosition = ((long) mapAPIProperties.get("mp3.position.microseconds")) / 1000000;
		mapProgressProperties.put("lCurFrame", lCurFrame);
		mapProgressProperties.put("lSecondsPosition", lSecondsPosition);
		notifyObservers(mapProgressProperties);
	}

	public void setController(BasicController arg0) {}
	public void stateUpdated(BasicPlayerEvent arg0) {}
	public void opened(Object arg0, Map arg1) {}
}
