package upload;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class UploadSongBtn extends JButton {
	private UploaderViewFactory uploadFactory;
	
	public UploadSongBtn(UploaderViewFactory uploadFactory) {
		super("Upload Song");
		setForeground(Color.WHITE);
		setFont(new Font("Agency FB", Font.BOLD, 15));
		setBackground(new Color(0, 153, 51));
		
		this.uploadFactory = uploadFactory;
	}
	
	private class UploadBtnListener implements ActionListener {

		public void actionPerformed(ActionEvent aeEvent) {
			uploadFactory.createUploadView();
		}
	}
	
}
