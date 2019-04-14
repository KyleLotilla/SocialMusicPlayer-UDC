package serverIPAddress;

public class LocalServerIPAddress {
	 private static LocalServerIPAddress instance = null;
	 final String sIPAddress = "127.0.0.1";
	 
	 private LocalServerIPAddress() {}
	 
	 public static synchronized LocalServerIPAddress getInstance() {
			if (instance == null)
				instance = new LocalServerIPAddress();
			return instance;
	 }
	 
	 public String getIPAddress() {
		return sIPAddress; 
	 }
}
