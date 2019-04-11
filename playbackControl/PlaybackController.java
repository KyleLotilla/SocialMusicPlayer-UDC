package playbackControl;

import java.util.ArrayList;

public class PlaybackController implements PlaybackControlObservable{
	
	private PlaybackControl playbackCtrl;
	private ArrayList<PlaybackControlObserver> playbackObservers;
	
	public PlaybackController () {
		playbackObservers = new ArrayList<PlaybackControlObserver>();
	}

	public void setPlaybackControl(PlaybackControl playbackCtrlNew) {
		playbackCtrl = playbackCtrlNew;
		notifyObservers(playbackCtrlNew);
	}

	public void addObserver(PlaybackControlObserver playbackObserverNew) {
		playbackObserverNew.setPlaybackControl(playbackCtrl);
		playbackObservers.add(playbackObserverNew);
	}

	public void notifyObservers(PlaybackControl playbackCtrlNew) {
		for (PlaybackControlObserver playbackCounter : playbackObservers)
			playbackCounter.setPlaybackControl(playbackCtrlNew);
	}
	
}
