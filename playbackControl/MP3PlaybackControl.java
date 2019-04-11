package playbackControl;
import java.io.*;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;

public class MP3PlaybackControl implements PlaybackControl {
	private BasicPlayer playerAPI;
	private File fileMP3;
	private BytesPositionListener bytesPosListener;
	
	public MP3PlaybackControl(BasicPlayer playerAPI, File fileMP3) {
		this.playerAPI = playerAPI;
		this.fileMP3 = fileMP3;
		bytesPosListener = new BytesPositionListener();
		playerAPI.addBasicPlayerListener(bytesPosListener);
	}

	public void play() {
		try {
			playerAPI.play();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	
	public void pause() {
		try {
			playerAPI.pause();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	
	public void resume() {
		try {
			playerAPI.resume();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			playerAPI.stop();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	
	public void seek(int nSeekedSeconds) {
		MpegAudioFileReader mpegReader = new MpegAudioFileReader();
		AudioFileFormat audioFormatFileMP3 = null;
		try {
			audioFormatFileMP3 = mpegReader.getAudioFileFormat(fileMP3);
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Map mapProperties = audioFormatFileMP3.properties();
		int nBytesRate = ((int) mapProperties.get("mp3.bitrate.nominal.bps")) / 8;
		int nBytesPos = bytesPosListener.getBytesPosition();
		try {
			playerAPI.seek(nBytesPos + ((nSeekedSeconds - 2) * nBytesRate));
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	
	private class BytesPositionListener implements BasicPlayerListener {
		private int nBytesPos;
	
		public void progress(int nBytesRead, long lMircoseconds, byte[] bytePcmData, Map mapAPIProperties) {
			nBytesPos = nBytesRead;
		}
		
		public int getBytesPosition() {
			return nBytesPos;
		}
		
		public void opened(Object arg0, Map arg1) {}

		public void setController(BasicController arg0) {}

		public void stateUpdated(BasicPlayerEvent arg0) {}
		
	}

}
