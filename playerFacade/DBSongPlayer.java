package playerFacade;

import java.io.File;

import fileRetriever.AudioFileRetriever;

public class DBSongPlayer implements SongPlayer{
	private AudioFileRetriever audioRetriever;
	private FilePlayer filePlayer;
	
	public DBSongPlayer(AudioFileRetriever audioRetriever, FilePlayer filePlayer) {
		this.audioRetriever = audioRetriever;
		this.filePlayer = filePlayer;
	}
	
	public void playSong(String sSongID) {
		File fileAudio = audioRetriever.getAudio(sSongID);
		filePlayer.playFile(fileAudio);
	}
}
