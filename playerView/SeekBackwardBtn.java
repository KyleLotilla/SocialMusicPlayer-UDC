package playerView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import playbackControl.PlaybackControl;
import playbackControl.PlaybackControlObserver;
import playerState.PlaybackStateObserver;

public class SeekBackwardBtn extends JButton implements PlaybackStateObserver, PlaybackControlObserver {
	private PlaybackControl playbackCtrl;
	
	public SeekBackwardBtn() {
		super("<<");
		addActionListener(new SeekBackwardListener());
	}
	
	public void setPlaybackControl(PlaybackControl playbackCtrlNew) {
		playbackCtrl = playbackCtrlNew;
	}

	public void playbackStateUpdated(String sState) {
		if (sState.contentEquals("PAUSED") || sState.contentEquals("STOPPED") 
				|| sState.contentEquals("SEEKING") || sState.contentEquals("FINISHED"))
			setEnabled(false);
		else if (sState.contentEquals("RESUMED") || sState.contentEquals("PLAYING"))
			setEnabled(true);
	}
	
	private class SeekBackwardListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			playbackCtrl.seek(-10);
		}
	}
}
