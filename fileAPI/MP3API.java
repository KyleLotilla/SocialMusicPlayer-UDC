package fileAPI;
import java.io.File;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import playbackControl.MP3PlaybackControl;
import playbackControl.PlaybackControl;
import playerProgress.MP3ProgressObservable;
import playerProgress.PlaybackProgressObservable;
import playerState.MP3StateObservable;
import playerState.PlaybackStateObservable;

public class MP3API implements AudioFileAPI {
	private BasicPlayer playerAPI;
	private File fileMP3;
	
	public MP3API (File fileMP3) {
		this.fileMP3 = fileMP3;
		playerAPI = new BasicPlayer();
		try {
			playerAPI.open(fileMP3);
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	
	public PlaybackControl createPlaybackControl() {
		return new MP3PlaybackControl(playerAPI, fileMP3);
	}
	
	public PlaybackProgressObservable createProgressObservable() {
		MP3ProgressObservable mp3ProgObservable = new MP3ProgressObservable();
		playerAPI.addBasicPlayerListener(mp3ProgObservable);
		return mp3ProgObservable;
	}
	
	public PlaybackStateObservable createStateObservable() {
		MP3StateObservable mp3StateObservable = new MP3StateObservable();
		playerAPI.addBasicPlayerListener(mp3StateObservable);
		return mp3StateObservable;
	}
	
}
