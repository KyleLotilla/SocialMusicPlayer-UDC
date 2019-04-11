package playerState;
import java.util.ArrayList;

public interface PlaybackStateObservable {
	public void notifyObservers(String sState);
	public void newObserver (PlaybackStateObserver stateObserObserver);
}
