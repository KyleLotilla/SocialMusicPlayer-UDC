package upload;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.lang3.StringUtils;

import fileRetriever.MyConnection;

public class DBSongUploadView extends JFrame {
	private SongBuilder songBuild;
	private JTextField txtfldTitle;
	private JTextField txtfldYear;
	private JTextField txtfldArt;
	private JTextField txtfldGenre;
	private AlbumComboBox cmbboxAlb;
	private JButton btnUploadFile;
	private JButton btnUploadSong;
	private int nUploaderID;
	private File fileAudio;
	private HashMap mapCmb;
	
	public DBSongUploadView (SongBuilder songBuild, int nUploaderID) {
		super("Upload Song");
		setBounds(100, 100, 282, 375);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		this.songBuild = songBuild;
		this.nUploaderID = nUploaderID;
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(new Color(103, 128, 159));
		pnlHeader.setBounds(0, 0, 282, 56);
		getContentPane().add(pnlHeader);
		pnlHeader.setLayout(null);
		
		JLabel lblUplSong = new JLabel("Upload Song");
		lblUplSong.setHorizontalAlignment(SwingConstants.LEFT);
		lblUplSong.setForeground(Color.WHITE);
		lblUplSong.setFont(new Font("Agency FB", Font.PLAIN, 35));
		lblUplSong.setBounds(10, 0, 132, 56);
		pnlHeader.add(lblUplSong);
		
		JPanel pnlBody = new JPanel();
		pnlBody.setBackground(new Color(108, 122, 137));
		pnlBody.setBounds(0, 56, 282, 308);
		getContentPane().add(pnlBody);
		pnlBody.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);;
		lblTitle.setForeground(new Color(236, 240, 241));
		lblTitle.setFont(new Font("Agency FB", Font.PLAIN, 16));
		lblTitle.setBounds(10, 11, 30, 20);
		pnlBody.add(lblTitle);;
		
		txtfldTitle = new JTextField();
		txtfldTitle.setFont(new Font("Arial", Font.PLAIN, 11));
		txtfldTitle.setBackground(new Color(46, 49, 49));
		txtfldTitle.setForeground(new Color(228, 241, 254));
		txtfldTitle.setBounds(50, 12, 222, 20);
		pnlBody.add(txtfldTitle);
		txtfldTitle.setColumns(10);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setHorizontalAlignment(SwingConstants.LEFT);
		lblYear.setForeground(new Color(236, 240, 241));
		lblYear.setFont(new Font("Agency FB", Font.PLAIN, 16));
		lblYear.setBounds(10, 41, 30, 20);
		pnlBody.add(lblYear);
		
		txtfldYear = new JTextField();
		txtfldYear.setFont(new Font("Arial", Font.PLAIN, 11));
		txtfldYear.setBackground(new Color(46, 49, 49));
		txtfldYear.setForeground(new Color(228, 241, 254));
		txtfldYear.setBounds(50, 42, 222, 20);
		pnlBody.add(txtfldYear);
		txtfldYear.setColumns(10);
		
		JLabel lblAlbum = new JLabel("Album:");
		lblAlbum.setHorizontalAlignment(SwingConstants.LEFT);
		lblAlbum.setForeground(new Color(236, 240, 241));
		lblAlbum.setFont(new Font("Agency FB", Font.PLAIN, 16));
		lblAlbum.setBounds(10, 210, 40, 20);
		pnlBody.add(lblAlbum);
		
		cmbboxAlb = new AlbumComboBox();
		cmbboxAlb.setBounds(50, 211, 222, 20);
		pnlBody.add(cmbboxAlb);
		
		JLabel lblArtist = new JLabel("Artist:");
		lblArtist.setHorizontalAlignment(SwingConstants.LEFT);
		lblArtist.setForeground(new Color(236, 240, 241));
		lblArtist.setFont(new Font("Agency FB", Font.PLAIN, 16));
		lblArtist.setBounds(10, 72, 40, 20);
		pnlBody.add(lblArtist);
		
		txtfldArt = new JTextField();
		txtfldArt.setForeground(new Color(228, 241, 254));
		txtfldArt.setFont(new Font("Arial", Font.PLAIN, 11));
		txtfldArt.setColumns(10);
		txtfldArt.setBackground(new Color(46, 49, 49));
		txtfldArt.setBounds(50, 72, 222, 20);
		pnlBody.add(txtfldArt);
		
		txtfldArt = new JTextField();
		txtfldArt.setForeground(new Color(228, 241, 254));
		txtfldArt.setFont(new Font("Arial", Font.PLAIN, 11));
		txtfldArt.setColumns(10);
		txtfldArt.setBackground(new Color(46, 49, 49));
		txtfldArt.setBounds(50, 72, 222, 20);
		pnlBody.add(txtfldArt);
		
		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setHorizontalAlignment(SwingConstants.LEFT);
		lblGenre.setForeground(new Color(236, 240, 241));
		lblGenre.setFont(new Font("Agency FB", Font.PLAIN, 16));
		lblGenre.setBounds(10, 99, 40, 20);
		pnlBody.add(lblGenre);
		
		txtfldGenre = new JTextField();
		txtfldGenre.setForeground(new Color(228, 241, 254));
		txtfldGenre.setFont(new Font("Arial", Font.PLAIN, 11));
		txtfldGenre.setColumns(10);
		txtfldGenre.setBackground(new Color(46, 49, 49));
		txtfldGenre.setBounds(50, 99, 222, 23);
		pnlBody.add(txtfldGenre);
		
		btnUploadSong = new JButton("Upload Song");
		btnUploadSong.setOpaque(true);
		btnUploadSong.setBackground(new Color(0, 153, 51));
		btnUploadSong.setBorderPainted(true);
		btnUploadSong.setFont(new Font("Agency FB", Font.PLAIN, 16));
		btnUploadSong.setForeground(Color.WHITE);
		btnUploadSong.setHorizontalAlignment(btnUploadSong.CENTER);
		btnUploadSong.setBounds(92, 275, 98, 20);
		btnUploadSong.addActionListener(new UploadBtnListener());
		pnlBody.add(btnUploadSong);
		
		btnUploadFile = new JButton("Upload File");
		btnUploadFile.setOpaque(true);
		btnUploadFile.setHorizontalAlignment(SwingConstants.CENTER);
		btnUploadFile.setForeground(Color.WHITE);
		btnUploadFile.setFont(new Font("Agency FB", Font.PLAIN, 16));
		btnUploadFile.setBorderPainted(true);
		btnUploadFile.setBackground(new Color(34, 167, 240));
		btnUploadFile.setBounds(156, 241, 116, 21);
		btnUploadFile.addActionListener(new UploadFileBtnListener());
		pnlBody.add(btnUploadFile);
		
		setVisible(true);
		
	}
	
	private class UploadFileBtnListener implements ActionListener {

		public void actionPerformed(ActionEvent aeEvent) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("MP3 file", "mp3"));
			fileChooser.setAcceptAllFileFilterUsed(false);
			int nReturnVal = fileChooser.showOpenDialog(null);
			
			if (nReturnVal == JFileChooser.APPROVE_OPTION)
				fileAudio = fileChooser.getSelectedFile();
		}
		
	}
	
	private class UploadBtnListener implements ActionListener {

		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent aeEvent) {
			if (txtfldTitle.getText().contentEquals("") || fileAudio == null)
				JOptionPane.showMessageDialog(null, "Please Input Required Fields");
			else if (!StringUtils.isNumeric(txtfldYear.getText()) && !txtfldYear.getText().contentEquals(""))
				JOptionPane.showMessageDialog(null, "Please Input Valid Year");
			else {
				HashMap mapSongProperties = new HashMap<String, Object>();
				mapSongProperties.put("nUploaderID", nUploaderID);
				mapSongProperties.put("sTitle", txtfldTitle.getText());
				
				String sYear = txtfldYear.getText();
				if (!sYear.contentEquals(""))
					mapSongProperties.put("nYear", Integer.parseInt(sYear));
				else
					mapSongProperties.put("nYear", -1);
				
				
				int nSelected = cmbboxAlb.getSelectedIndex();
				if (nSelected == 0)
					mapSongProperties.put("nAlbumID", 0);
				else
					mapSongProperties.put("nAlbumID", mapCmb.get(nSelected));
				
				String sGenre = txtfldGenre.getText();
				if (!sGenre.contentEquals(""))
					mapSongProperties.put("sGenre", sGenre);
				else
					mapSongProperties.put("sGenre", "");
				
				String sArtist = txtfldArt.getText();
				if (!sArtist.contentEquals(""))
					mapSongProperties.put("sArtist", sArtist);
				else
					mapSongProperties.put("sArtist", "");
				
				mapSongProperties.put("fileAudio", fileAudio);
				
				int songID = songBuild.buildSong(mapSongProperties);
				
				if (songID <= 0)
					JOptionPane.showMessageDialog(null, "Uploaded Failed");
				else
					JOptionPane.showMessageDialog(null, "Uploaded");
				
				DBSongUploadView.this.dispose();

			}
		}
		
	}
	
	private class AlbumComboBox extends JComboBox {
		
		@SuppressWarnings("unchecked")
		public AlbumComboBox() {
			super();
			mapCmb = new HashMap<Integer, Integer>();
			try {
				PreparedStatement preparedAlbumQuery = MyConnection.getConnection().prepareStatement("SELECT albumID, title FROM album WHERE uploaderID = ?");
				preparedAlbumQuery.setInt(1, nUploaderID);
				ResultSet resultAblums = preparedAlbumQuery.executeQuery();
				
				this.addItem("None");
				mapCmb.put(0, 0);
				
				for (int i = 1; resultAblums.next(); i++) {
					this.addItem(resultAblums.getString("title"));
					mapCmb.put(i, resultAblums.getInt("albumID"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
