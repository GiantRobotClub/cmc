package webrunner.cardmaster;
import java.io.*;
//import java.util.StringTokenizer;
import java.util.Random;
// Card library/deck
public class CardmasterUserData {
	
	public CardmasterUserData() {
		
	}
	
	CardmasterUser[] users;
	public CardmasterUser user;

	//public void loadUserData() {	
	//		users = CardmasterData.loadUserData();
	//}
	
	public boolean userpatch(String name, String command, int amount, String text) {
		
		return CardmasterData.userpatch(name,command,amount,text);
	}
	
	public boolean loadUser(String name) {
		user = CardmasterData.loadUser(name);
		if (user==null) return false;
		else return true;
		
	}

}