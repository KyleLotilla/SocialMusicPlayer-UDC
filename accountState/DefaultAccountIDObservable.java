package accountState;

import java.util.ArrayList;

public class DefaultAccountIDObservable implements AccountIDObservable {
	private ArrayList<AccountIDObserver> idObservers;
	private String sAccountID;
	
	public DefaultAccountIDObservable() {
		idObservers = new ArrayList<AccountIDObserver>();
		sAccountID = "";
	}
	
	public void setAccountID(String sAccountID) {
		this.sAccountID = sAccountID;
		notifyObservers();
	}

	public void addObserver(AccountIDObserver idObserver) {
		idObservers.add(idObserver);
	}

	public void notifyObservers() {
		for (AccountIDObserver idCounter : idObservers)
			idCounter.accountIDChanged(sAccountID);
	}
	
	
}
