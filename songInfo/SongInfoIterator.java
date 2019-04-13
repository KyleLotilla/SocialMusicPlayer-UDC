package songInfo;

public interface SongInfoIterator {
	public void next();
	public boolean hasnext();
	public SongInfo getCurSongInfo();
}
