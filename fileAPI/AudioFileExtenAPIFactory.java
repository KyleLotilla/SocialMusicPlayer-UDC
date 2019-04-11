package fileAPI;
import java.io.File;

import org.apache.commons.io.FilenameUtils;

public class AudioFileExtenAPIFactory implements AudioFileAPIFactory {

	public AudioFileAPI creatAudioFileAPI(File fileAudio) {
		String sExtension = FilenameUtils.getExtension(fileAudio.getName());
		System.out.println(sExtension);
		if (sExtension.contentEquals("mp3"))
			return new MP3API(fileAudio);
		else
			return null;
	}

}
