package fileRetriever;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;

import dbConnection.DBConnManager;
import serverIPAddress.ServerIPAddressManager;

public class AudioFileDownload implements AudioFileRetriever {
	private DBConnManager connManager;
	private ServerIPAddressManager ipManager;
	
	public AudioFileDownload(DBConnManager connManager, ServerIPAddressManager ipManager) {
		this.connManager = connManager;
		this.ipManager = ipManager; 
	}
	
	public File getAudio (String songID) {
		File fileAudio = null;
		try {
			PreparedStatement preparedIDQuery = connManager.getConnection().prepareStatement("SELECT filePath AS filePath FROM song WHERE songID = ?");
			preparedIDQuery.setInt(1, Integer.parseInt(songID));
			preparedIDQuery.executeQuery();
			ResultSet resultSetID = preparedIDQuery.getResultSet();
			resultSetID.next();
			String sFilePath = resultSetID.getString("filePath");
			fileAudio = new File("/Audio/" + sFilePath);
			
			URL urlFile = new URL("http://" + ipManager.getServerIPAddress() + "/Audio/" + sFilePath);
			FileUtils.copyURLToFile(urlFile, fileAudio);
		} catch (SQLException ex) {
			ex.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
		
		return fileAudio;
	}
	
}
