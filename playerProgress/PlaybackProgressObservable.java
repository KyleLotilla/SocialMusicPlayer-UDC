package playerProgress;
import java.util.ArrayList;
import java.util.Map;

public interface PlaybackProgressObservable {
	public void notifyObservers(Map mapProgressProperties);
	public void newObserver (PlaybackProgressObserver progObserObserver);
}
