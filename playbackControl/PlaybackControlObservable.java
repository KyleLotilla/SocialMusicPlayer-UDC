package playbackControl;

public interface PlaybackControlObservable {
	public void setPlaybackControl(PlaybackControl playbackCtrlNew);
	public void addObserver(PlaybackControlObserver playbackCTRLNew);
	public void notifyObservers(PlaybackControl playbackCtrlNew);
}
