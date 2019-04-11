package playbackControl;


public interface PlaybackControl {
	public void play();
	public void pause();
	public void resume();
	public void stop();
	public void seek(int nSeekedSeconds);
}
