package program;

import java.awt.Color;

import javax.swing.JPanel;

import accountState.AccountIDObservable;
import accountState.DefaultAccountIDObservable;
import accountState.DefaultAccountTypeObservable;
import dbConnection.DBConnManager;
import dbConnection.MySQLConnManager;
import fileRetriever.AudioFileDownload;
import fileRetriever.SongIDResultBuilder;
import gui.DefaultJMainFrame;
import gui.JMainFrame;
import panelView.DefaultMainPanelManager;
import panelView.DefaultTabView;
import panelView.JMainPanelViewManager;
import panelView.MainPanelManager;
import panelView.SongListPanel;
import panelView.TabView;
import playerFacade.DefaultFilePlayer;
import playerFacade.DefaultSongPlayer;
import playerFacade.SongPlayer;
import selectionTable.DefaultSelectionMapBuilder;
import selectionTable.PlayBtn;
import selectionTable.SongTableBuilder;
import serverIPAddress.LocalServerIPAddressManager;
import songInfo.SQLSongInfoBuilder;
import sqlResult.SongResultBuilder;
import tabBottom.DefaultJTabBottomFactory;
import tabBottom.TabBottom;
import upload.DBSongBuilderFactory;
import upload.DBSongUploadViewFactory;
import upload.SongBuilder;
import upload.SongBuilderFactory;

public class MusicProgram {
	public MusicProgram() {
		DefaultMainPanelManager mainManager = new DefaultMainPanelManager();
		JMainFrame mainFrame = new  DefaultJMainFrame(mainManager);
		DBConnManager connManager = new MySQLConnManager();
		DefaultAccountIDObservable accountID = new DefaultAccountIDObservable();
		DefaultAccountTypeObservable accountType = new DefaultAccountTypeObservable();
		 
		DBSongBuilderFactory songBuilderFactory = new DBSongBuilderFactory();
		accountType.addObserver(songBuilderFactory);
		accountID.addObserver(songBuilderFactory);
		DBSongUploadViewFactory songUploadViewFactory = new DBSongUploadViewFactory(songBuilderFactory);
		accountID.addObserver(songUploadViewFactory);
		accountType.addObserver(songUploadViewFactory);
		
		DefaultJTabBottomFactory tabBottomFactory = new DefaultJTabBottomFactory(songUploadViewFactory);
		
		SongResultBuilder songResultBuilder = new SongResultBuilder(connManager);
		accountID.addObserver(songResultBuilder);
		
		accountID.setAccountID("2");
		accountType.setAccountType("Listener");
		
		TabView tabView1 = new DefaultTabView();
		mainFrame.addTabView(tabView1);
		tabView1.setBounds(20, 20, 200, 400);
		tabView1.setBackground(new Color(46, 49, 49));
		tabView1.setLayout(null);
		
		
		TabView tabView2 = new TabBottom(tabBottomFactory);
		mainFrame.addTabView(tabView2);
		tabView2.setBounds(250, 450, 1088, 72);
		
		DefaultFilePlayer filePlayer = new DefaultFilePlayer();
		JPanel playerView = filePlayer.getAudioPlayerView();
		mainFrame.addJPanel(playerView);
		playerView.setBounds(20, 550, 1318, 50);
		playerView.setBackground(new Color(46, 49, 49));
		
		SongPlayer songPlayer = new DefaultSongPlayer(new AudioFileDownload(new LocalServerIPAddressManager(), new SongIDResultBuilder(connManager)), filePlayer);
		
		JPanel panelMain = mainManager.getPanel();
		mainFrame.addJPanel(panelMain);
		panelMain.setBounds(250, 20, 1088, 400);
		SongListPanel songListPanel = new SongListPanel(new PlayBtn(songPlayer), new SQLSongInfoBuilder(songResultBuilder), new SongTableBuilder(), new DefaultSelectionMapBuilder());
		mainManager.addMainView(songListPanel, "SONGLIST");
		mainManager.showMainView("SONGLIST");
		mainFrame.setVisible(true);
	}

}
