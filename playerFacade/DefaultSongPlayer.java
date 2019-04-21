package playerFacade;

import java.io.File;

import fileRetriever.AudioFileRetriever;

public class DefaultSongPlayer implements SongPlayer{
	private AudioFileRetriever audioRetriever;
	private FilePlayer filePlayer;
	
	public DefaultSongPlayer(AudioFileRetriever audioRetriever, FilePlayer filePlayer) {
		this.audioRetriever = audioRetriever;
		this.filePlayer = filePlayer;
	}
	
	public void playSong(String sSongID) {
		File fileAudio = audioRetriever.getAudio(sSongID);
		filePlayer.playFile(fileAudio);
	}
}
