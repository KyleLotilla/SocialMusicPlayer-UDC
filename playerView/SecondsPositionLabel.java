package playerView;
import java.util.Map;

import javax.swing.JLabel;

import playerProgress.PlaybackProgressObserver;
import playerState.PlaybackStateObserver;

public class SecondsPositionLabel extends JLabel implements PlaybackProgressObserver, PlaybackStateObserver {
	public SecondsPositionLabel () {
		super("0:00");
	}

	public void playbackStateUpdated(String sState) {
		if (sState.contentEquals("STOPPED"))
			setText("0:00");
	}

	public void progressUpdated(Map mapProgressProperties) {
		long lSecondsPosition = (Long) mapProgressProperties.get("lSecondsPosition");
		int nMinutes = (int) (lSecondsPosition / 60);
		int nSeconds = (int) (lSecondsPosition % 60);
		String sPosition = nMinutes + ":";
		
		if (nSeconds / 10 <= 0)
			sPosition += "0" + nSeconds;
		else
			sPosition += nSeconds;
		
		this.setText(sPosition);
	}
}
