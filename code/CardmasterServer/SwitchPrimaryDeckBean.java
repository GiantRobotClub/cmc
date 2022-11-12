package webrunner.cardmaster;
import java.io.*;

public class SwitchPrimaryDeckBean {

//	CardmasterUser[] users;
	CardmasterUser user;
	int numdecks;
	
	public boolean loadUser(String name) {
		user = CardmasterData.loadUser(name);
		if (user==null) return false;
		return true;
	}

	public SwitchPrimaryDeckBean() {
	}
	
	public int switchPrimaryDeckTo(String name, int i) {
		//loadUserData();
		loadUser(name);
		if (user.hasDeck(i)) {
			user.setPrimaryDeck(i);
		//saveusers();
			userpatch(name,"setp",i,"x");
		}
		if (user.hasDeck(i)) return i;
		else return -1;
		
		
	}
	public boolean userpatch(String name, String command, int amount, String text) {
		return CardmasterData.userpatch(name,command,amount,text);
	}
	
	
}