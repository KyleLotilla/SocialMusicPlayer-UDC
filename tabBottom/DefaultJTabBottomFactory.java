package tabBottom;

import javax.swing.JPanel;

import upload.UploaderViewFactory;

public class DefaultJTabBottomFactory implements JTabFactory {
	private UploaderViewFactory uploadFactory;
	
	public DefaultJTabBottomFactory(UploaderViewFactory uploadFactory) {
		this.uploadFactory = uploadFactory;
	}

	public JPanel createJTab() {
		return new DefaultJTabBottom(uploadFactory);
	}
}
