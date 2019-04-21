package tabBottom;

import java.awt.Color;
import java.awt.FlowLayout;

import panelView.TabView;

public class TabBottom extends TabView {
	private JTabFactory jTabFactory;
	
	public TabBottom(JTabFactory jTabFactory) {
		super();
		this.jTabFactory = jTabFactory;
		setBackground(new Color(46, 49, 49));
		setLayout(new FlowLayout());
	}
	
	public void refreshView() {
		removeAll();
		add(jTabFactory.createJTab());
	}

}
