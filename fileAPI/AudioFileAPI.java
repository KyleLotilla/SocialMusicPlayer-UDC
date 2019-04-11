package fileAPI;
import playbackControl.PlaybackControl;
import playerProgress.PlaybackProgressObservable;
import playerState.PlaybackStateObservable;

public interface AudioFileAPI {
	public PlaybackControl createPlaybackControl();
	public PlaybackProgressObservable createProgressObservable();
	public PlaybackStateObservable createStateObservable();
}
