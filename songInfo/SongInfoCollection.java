package songInfo;

public interface SongInfoCollection {
	public void addSongInfo(SongInfo songInfo);
	public SongInfoIterator createIterator();
}
