package accountState;

import java.util.ArrayList;

public class DefaultAccountTypeObservable implements AccountTypeObservable {
	private ArrayList<AccountTypeObserver> typeObservers;
	private String sType;
	
	public DefaultAccountTypeObservable() {
		typeObservers = new ArrayList<AccountTypeObserver>();
		sType = "";
	}
	
	public void setAccountType(String sType) {
		this.sType = sType;
		notifyObservers();
	}
	
	public void addObserver(AccountTypeObserver idObserver) {
		typeObservers.add(idObserver);
	}

	
	public void notifyObservers() {
		for (AccountTypeObserver typeCounter : typeObservers)
			typeCounter.accountTypeUpdated(sType);
	}
	
}
