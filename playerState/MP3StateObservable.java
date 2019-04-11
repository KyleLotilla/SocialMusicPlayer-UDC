package playerState;
import java.util.Map;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

public class MP3StateObservable implements PlaybackStateObservable, BasicPlayerListener {
	
	private PlaybackStateObserver stateObserObserver;
	private boolean bFinished = false;
	private int nByteSize;

	public void notifyObservers(String sState) {
		stateObserObserver.playbackStateUpdated(sState);
	}

	public void newObserver(PlaybackStateObserver stateObserObserver) {
		this.stateObserObserver = stateObserObserver;
	}

	public void stateUpdated(BasicPlayerEvent eventState) {
		if (bFinished) {
			System.out.println("Finished");
			notifyObservers("FINISHED");
		}
		else {
			String sInitalStateDesc = eventState.toString();
			String sState[] = sInitalStateDesc.split(":");
			System.out.println(sState[0]);
			notifyObservers(sState[0]);
		}
	}
	
	public void opened(java.lang.Object stream, Map mapProperties) {
		nByteSize = (int) mapProperties.get("mp3.length.bytes");
	}
	public void progress(int nBytesRead, long lMircoseconds, byte[] bytePcmData, Map mapAPIProperties) {
		if (nBytesRead == nByteSize)
				bFinished = true;
	}
	public void setController(BasicController arg0) {}
	
}
