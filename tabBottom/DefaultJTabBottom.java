package tabBottom;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import upload.UploadSongBtn;
import upload.UploaderViewFactory;

public class DefaultJTabBottom extends JPanel {
	
	public DefaultJTabBottom(UploaderViewFactory uploadFactory) {
		super();
		setLayout(new FlowLayout());
		add(new UploadSongBtn(uploadFactory));
	}
}
