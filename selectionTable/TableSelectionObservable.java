package selectionTable;

public interface TableSelectionObservable {
	public void addObserver(TableSelectionObserver tsObserver);
	public void notifyObservers(int nSelection);
}
