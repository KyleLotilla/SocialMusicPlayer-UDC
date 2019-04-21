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
import sqlResult.SQLResultBuilder;
import sqlResult.SqlIDResultBuilder;

public class AudioFileDownload implements AudioFileRetriever {
	private ServerIPAddressManager ipManager;
	private SqlIDResultBuilder idResultBuilder;
	
	public AudioFileDownload(ServerIPAddressManager ipManager, SqlIDResultBuilder idResultBuilder) {
		this.ipManager = ipManager; 
		this.idResultBuilder = idResultBuilder;
	}
	
	public File getAudio (String songID) {
		File fileAudio = null;
		try {
			ResultSet idResult = idResultBuilder.buildResultSet(songID);
			idResult.next();
			String sFilePath = idResult.getString("filePath");
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
