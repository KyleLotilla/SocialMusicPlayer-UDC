package upload;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import dbConnection.MySQLConnManager;
import serverIPAddress.ServerIPAddressManager;
import sqlResult.SQLResultBuilder;
import sqlResult.SqlIDResultBuilder;

public class DBArtistSongBuilder implements SongBuilder {

	private MySQLConnManager connManager;
	private ServerIPAddressManager ipManager;
	private SqlIDResultBuilder usernameResult;
	
	public DBArtistSongBuilder (MySQLConnManager connManager, ServerIPAddressManager ipManager, SqlIDResultBuilder usernameResult) {
		this.connManager = connManager;
		this.ipManager = ipManager;
		this.usernameResult = usernameResult;
	}

	public String buildSong(Map mapSongProperties) {
		String sSongQuery = "INSERT INTO song (uploaderID, title, year, artist, albumID, filePath, genre)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		String sSongID = "";
		try {
			String sUploaderID = (String) mapSongProperties.get("sUploaderID");
			ResultSet resultUsername = usernameResult.buildResultSet(sUploaderID);
			resultUsername.next();
			String sArtist = resultUsername.getString(1);
			
			PreparedStatement preparedSongQuery = connManager.getConnection().prepareStatement(sSongQuery,Statement.RETURN_GENERATED_KEYS);
			
			int nUploaderID =  Integer.parseInt(sUploaderID);
			preparedSongQuery.setInt(1, nUploaderID);
			
			preparedSongQuery.setString(2, (String) mapSongProperties.get("sTitle"));
			
			int nYear = (int) mapSongProperties.get("nYear");
			if (nYear <= 0)
				preparedSongQuery.setNull(3, java.sql.Types.INTEGER);
			else
				preparedSongQuery.setInt(3, nYear);
			
			preparedSongQuery.setString(4, sArtist);
			
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
			sSongID = String.valueOf(resultSetID.getInt(1));
		} catch (SQLException ex) {
			ex.printStackTrace();
        } catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sSongID;
	}

}
