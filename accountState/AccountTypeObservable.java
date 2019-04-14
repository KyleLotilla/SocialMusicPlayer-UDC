package accountState;

public interface AccountTypeObservable {
	public void addObserver(AccountTypeObserver idObserver);
	public void notifyObservers();
}
