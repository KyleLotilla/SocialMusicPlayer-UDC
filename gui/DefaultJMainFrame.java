package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import accountState.AccountIDObserver;
import accountState.AccountTypeObserver;
import panelView.MainPanelManager;
import panelView.TabView;

public class DefaultJMainFrame implements JMainFrame {
	private JFrame frameMain;
	private JPanel panelBack;
	private MainPanelManager mainManager;
	private ArrayList<TabView> tabViews;
	
	public DefaultJMainFrame(MainPanelManager mainManager) {
		this.mainManager = mainManager;
		tabViews = new ArrayList<TabView>();
		
		frameMain = new JFrame("Music Player");
		frameMain.setLayout(null);
		frameMain.setSize(1400, 700);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		panelBack = new JPanel();
		panelBack.setSize(screenSize.width, screenSize.height);
		panelBack.setBackground(new Color(108, 122, 137));
		panelBack.setLayout(null);
		frameMain.add(panelBack);
		
		frameMain.setVisible(false);
	}
	
	public void setVisible(boolean bVisible) {
		if (bVisible) {
			for (TabView tabCounter: tabViews)
				tabCounter.refreshView();
			frameMain.setVisible(true);
		}
		else
			frameMain.setVisible(true);
	}
	
	public void addTabView(TabView tabNew) {
		tabViews.add(tabNew);
		panelBack.add(tabNew);
	}

	
	public void addJPanel(JPanel panelNew) {
		panelBack.add(panelNew);
	}

}
