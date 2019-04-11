package playerFacade;
import java.io.File;

import javax.swing.JPanel;

import fileAPI.AudioFileAPI;
import fileAPI.AudioFileAPIFactory;
import fileAPI.AudioFileExtenAPIFactory;
import playbackControl.PlaybackControl;
import playbackControl.PlaybackControlObservable;
import playbackControl.PlaybackController;
import playerProgress.PlaybackProgressObservable;
import playerProgress.PlaybackProgressObserver;
import playerProgress.ProgressObserverList;
import playerState.PlaybackStateObservable;
import playerState.PlaybackStateObserver;
import playerState.StateObserverList;
import playerView.AudioPlayerView;

public class FilePlayer {
	private AudioFileAPIFactory apiFactory;
	private PlaybackControlObservable playbackCtrlObservable;
	private PlaybackProgressObservable progObservable;
	private ProgressObserverList progObservers;
	private PlaybackStateObservable stateObservable;
	private StateObserverList stateObservers;
	private AudioPlayerView playerView;
	private PlaybackControl playbackCtrl;
	
	public FilePlayer() {
		apiFactory = new AudioFileExtenAPIFactory();
		playbackCtrlObservable = new PlaybackController();
		progObservers = new ProgressObserverList();
		stateObservers = new StateObserverList();
		playerView = new AudioPlayerView();
		
		playbackCtrlObservable.addObserver(playerView.getBtnPlayback());
		playbackCtrlObservable.addObserver(playerView.getBtnSeekBack());
		playbackCtrlObservable.addObserver(playerView.getBtnSeekForward());
		
		progObservers.addObserver(playerView.getLblSecondPos());
		
		stateObservers.addObserver(playerView.getBtnPlayback());
		stateObservers.addObserver(playerView.getBtnSeekBack());
		stateObservers.addObserver(playerView.getBtnSeekForward());
		stateObservers.addObserver(playerView.getLblSecondPos());
	}
	
	public void playFile(File fileAudio) {
		stopFile();
		AudioFileAPI audioAPI= apiFactory.creatAudioFileAPI(fileAudio);
		playbackCtrl = audioAPI.createPlaybackControl();
		playbackCtrlObservable.setPlaybackControl(playbackCtrl);
		progObservable = audioAPI.createProgressObservable();
		progObservable.newObserver(progObservers);
		stateObservable = audioAPI.createStateObservable();
		stateObservable.newObserver(stateObservers);
		playbackCtrl.play();
	}
	
	public void stopFile() {
		if (playbackCtrl != null)
			playbackCtrl.stop();
		playbackCtrl = null;
		playbackCtrlObservable.setPlaybackControl(null);
		progObservable = null;
		stateObservable = null;
	}
	
	public void addProgressObserver (PlaybackProgressObserver progNew) {
		progObservers.addObserver(progNew);
	}
	
	public void addStateObserver (PlaybackStateObserver stateNew) {
		stateObservers.addObserver(stateNew);
	}
	
	public JPanel getAudioPlayerView() {
		return playerView;
	}
}
