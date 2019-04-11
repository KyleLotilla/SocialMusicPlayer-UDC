package playerState;
import java.util.ArrayList;
import java.util.Map;

public class StateObserverList implements PlaybackStateObserver {
	private ArrayList<PlaybackStateObserver> stateObservers;
	
	public StateObserverList() {
		stateObservers = new ArrayList<PlaybackStateObserver>();
	}
	
	public void playbackStateUpdated(String sState) {
		for (PlaybackStateObserver stateCounter : stateObservers)
			stateCounter.playbackStateUpdated(sState);
	}
	
	public void addObserver(PlaybackStateObserver stateNew) {
		stateObservers.add(stateNew);
	}

}
