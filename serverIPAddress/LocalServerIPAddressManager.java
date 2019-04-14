package serverIPAddress;

public class LocalServerIPAddressManager implements ServerIPAddressManager {

	
	public String getServerIPAddress() {
		return LocalServerIPAddress.getInstance().getIPAddress();
	}
	
}
