package playerProgress;
import java.util.*;

public class ProgressObserverList implements PlaybackProgressObserver {
	private ArrayList<PlaybackProgressObserver> progObservers;
	
	public ProgressObserverList() {
		progObservers = new ArrayList<PlaybackProgressObserver>();
	}

	public void progressUpdated(Map mapProgressProperties) {
		for (PlaybackProgressObserver progCounter: progObservers)
			progCounter.progressUpdated(mapProgressProperties);
	}
	
	public void addObserver(PlaybackProgressObserver progNew) {
		progObservers.add(progNew);
	}
}
