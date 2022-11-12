
package webrunner.cardmaster;
public class CardmasterDonationEntry {
	public int errorcode = 0;
	public boolean filled = false;
	public String accountname = "";
	public int quantity = 0;
	public String deckname = "";
	public String orderid = "";
	public long id = 0;
	
	
	public static int ERROR_USER_NOT_FOUND = 1;
	public static int ERROR_DECK_NOT_FOUND = 2;
	public static int ERROR_QUANTITY_ZERO = 3;
	public static int ERROR_COULDNT_CREATE = 4;
	
	public CardmasterDonationEntry() { 
	}
	
	public String getStatus() {
		if (errorcode == 0 && filled) return "Completed";
		else if (errorcode == 0 && !filled) return "Waiting";
		else if (errorcode == ERROR_USER_NOT_FOUND) return "User not found";
		else if (errorcode == ERROR_DECK_NOT_FOUND) return "Deck not found";
		else if (errorcode == ERROR_QUANTITY_ZERO) return "Quantity Zero";
		else if (errorcode == ERROR_COULDNT_CREATE) return "Couldn't Create";
		
		else return "Unknown Error";
		
	}
	
	
}