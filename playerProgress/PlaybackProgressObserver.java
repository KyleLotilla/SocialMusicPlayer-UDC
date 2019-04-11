package playerProgress;
import java.util.Map;

public interface PlaybackProgressObserver {
	public void progressUpdated(Map mapProgressProperties);
}
