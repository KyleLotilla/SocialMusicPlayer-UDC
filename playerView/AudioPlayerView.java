package playerView;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AudioPlayerView extends JPanel {
	private SecondsPositionLabel lblSecondPos;
	private SeekBackwardBtn btnSeekBack;
	private SeekForwardBtn btnSeekForward;
	private PlaybackBtn btnPlayback;
	private JFrame frame;
	
	public AudioPlayerView() {
		super(new FlowLayout());
		
		lblSecondPos = new SecondsPositionLabel();
		add(lblSecondPos);
		lblSecondPos.setBounds(100, 150, 300, 300);
		
		btnSeekBack = new SeekBackwardBtn();
		add(btnSeekBack);
		btnSeekBack.setBounds(150, 100, 300, 300);
		
		btnPlayback = new PlaybackBtn();
		add(btnPlayback);
		btnPlayback.setBounds(100, 100, 300, 300);
		
		btnSeekForward = new SeekForwardBtn();
		add(btnSeekForward);
		lblSecondPos.setBounds(50, 100, 300, 300);
	}
	
	public SecondsPositionLabel getLblSecondPos() {
		return lblSecondPos;
	}
	
	public SeekBackwardBtn getBtnSeekBack() {
		return btnSeekBack;
	}
	
	public SeekForwardBtn getBtnSeekForward() {
		return btnSeekForward;
	}
	
	public PlaybackBtn getBtnPlayback() {
		return btnPlayback;
	}
}
