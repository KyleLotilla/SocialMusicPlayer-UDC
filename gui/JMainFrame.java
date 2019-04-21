package gui;

import javax.swing.JPanel;

import panelView.TabView;

public interface JMainFrame extends MainFrame{
	public void addTabView(TabView tabNew);
	public void addJPanel(JPanel panelNew);
}
