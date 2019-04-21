package panelView;

import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class DefaultMainPanelManager implements JMainPanelViewManager {
	private Map<String, JMainPanelView> mapNameMapping;
	private JPanel panelMain;
	private CardLayout cardMain;
	private JMainPanelView panelCurrent;
	
	public DefaultMainPanelManager() {
		mapNameMapping = new HashMap<String, JMainPanelView>();
		cardMain = new CardLayout();
		panelMain = new JPanel(cardMain);
		panelCurrent = null;
	}
	
	public void showMainView(String sPanel) {
		panelCurrent = mapNameMapping.get(sPanel);
		panelCurrent.refreshView();
		cardMain.show(panelMain, sPanel);
	}

	public void addMainView(JMainPanelView mainPanel, String sPanel) {
		panelMain.add(sPanel, mainPanel);
		mapNameMapping.put(sPanel, mainPanel);
	}

	public void refreshCurPanel() {
		panelCurrent.refreshView();
	}
	
	public JPanel getPanel() {
		return panelMain;
	}
}
