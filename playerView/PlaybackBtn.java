package playerView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import playbackControl.PlaybackControl;
import playbackControl.PlaybackControlObserver;
import playerState.PlaybackStateObserver;
public class PlaybackBtn extends JButton implements  PlaybackStateObserver, PlaybackControlObserver {
	private PlaybackControl playbackCtrl;
	private boolean bPaused;
	
	public PlaybackBtn () {
		super("Pause");
		bPaused = false;
		addActionListener(new PlaybackListener());
	}
	
	public void playbackStateUpdated(String sState) {
		if (sState.contentEquals("PAUSED")) {
			bPaused = true;
			setText("Play");
		}
		else if (sState.contentEquals("RESUMED") || sState.contentEquals("PLAYING")) {
			bPaused = false;
			setText("Pause");
			setEnabled(true);
		}
		else if (sState.contentEquals("STOPPED") || sState.contentEquals("FINISHED")) {
			bPaused = false;
			setEnabled(false);
		}
	}

	public void setPlaybackControl(PlaybackControl playbackCtrlNew) {
		playbackCtrl = playbackCtrlNew;
	}

	private class PlaybackListener implements ActionListener {
		public void actionPerformed(ActionEvent aeEvent) {
			if (bPaused)
				playbackCtrl.resume();
			else
				playbackCtrl.pause();
		}
	}
}
