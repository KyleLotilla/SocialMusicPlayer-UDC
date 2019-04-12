package panelView;

import java.awt.CardLayout;
import java.util.Map;

import javax.swing.JPanel;

public class DefaultMainPanelManager implements JMainPanelViewManager {
	private Map<String, JMainPanelView> mapNameMapping;
	private JPanel panelMain;
	private CardLayout cardMain;
	
	public DefaultMainPanelManager(Map<String, JMainPanelView> mapNameMapping) {
		this.mapNameMapping = mapNameMapping;
		cardMain = new CardLayout();
		panelMain = new JPanel(cardMain);
	}
	
	public void showMainView(String sPanel) {
		JMainPanelView panelSelected = mapNameMapping.get(sPanel);
		panelSelected.refreshView();
		cardMain.show(panelMain, sPanel);
	}

	public void addMainView(JMainPanelView mainPanel, String sPanel) {
		panelMain.add(sPanel, mainPanel);
		mapNameMapping.put(sPanel, mainPanel);
	}
	
}
