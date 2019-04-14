package upload;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.*;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import dbConnection.MySQLConnManager;
import fileRetriever.MyConnection;
import serverIPAddress.ServerIPAddressManager;

public class DBUserSongBuilder implements SongBuilder {
	private MySQLConnManager connManager;
	private ServerIPAddressManager ipManager;
	
	public DBUserSongBuilder(MySQLConnManager connManager, ServerIPAddressManager ipManager) {
		this.connManager = connManager;
		this.ipManager = ipManager;
	}

	public int buildSong(Map mapSongProperties) {
		String sSongQuery = "INSERT INTO song (uploaderID, title, year, artist, albumID, filePath, genre)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		int nSongID = 0;
		try {
			PreparedStatement preparedSongQuery = connManager.getConnection().prepareStatement(sSongQuery,Statement.RETURN_GENERATED_KEYS);
			String sUploaderID = (String) mapSongProperties.get("sUploaderID");
			int nUploaderID = Integer.parseInt(sUploaderID);
			preparedSongQuery.setInt(1, nUploaderID);
			
			preparedSongQuery.setString(2, (String) mapSongProperties.get("sTitle"));
			
			int nYear = (int) mapSongProperties.get("nYear");
			if (nYear <= 0)
				preparedSongQuery.setNull(3, java.sql.Types.INTEGER);
			else
				preparedSongQuery.setInt(3, nYear);
			
			preparedSongQuery.setString(4, (String) mapSongProperties.get("sArtist"));
			
			int nAlbumID = (int) mapSongProperties.get("nAlbumID");
			if (nAlbumID == 0)
				preparedSongQuery.setNull(5, java.sql.Types.INTEGER);
			else 
				preparedSongQuery.setInt(5, nAlbumID);
			
			preparedSongQuery.setString(7, (String) mapSongProperties.get("sGenre"));

			
			File fileAudio = (File) mapSongProperties.get("fileAudio");
			String sCurDateTime = LocalDateTime.now().toString();
			sCurDateTime = sCurDateTime.replaceAll("-", "");
			sCurDateTime = sCurDateTime.replaceAll(":", "");
			sCurDateTime = sCurDateTime.replaceAll("\\.", "");
			String sFilename = sCurDateTime + fileAudio.getName();
			
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpEntity httpAudioEntity = MultipartEntityBuilder.create().addPart("fileAudio", new FileBody(fileAudio, ContentType.MULTIPART_FORM_DATA, sFilename)).build();
			HttpPost postUploadRequest = new HttpPost("http://" + ipManager.getServerIPAddress() + "/audioUpload.php");
			postUploadRequest.setEntity(httpAudioEntity);
			HttpResponse responseUpload = httpClient.execute(postUploadRequest);
			EntityUtils.consume(httpAudioEntity);
			postUploadRequest.releaseConnection();
			
			preparedSongQuery.setString(6, sFilename);
			preparedSongQuery.executeUpdate();
			
			ResultSet resultSetID = preparedSongQuery.getGeneratedKeys();
			resultSetID.next();
			nSongID = resultSetID.getInt(1);
		} catch (SQLException ex) {
			ex.printStackTrace();
        } catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return nSongID;
	}
	
}
