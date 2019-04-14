package accountState;

public interface AccountIDObservable {
	public void addObserver(AccountIDObserver idObserver);
	public void notifyObservers();
}
