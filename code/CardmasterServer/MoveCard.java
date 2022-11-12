package webrunner.cardmaster;
import java.io.*;
public class MoveCard {
	//CardmasterUser[] users;
	CardmasterUser user;
	
	// move card from deck1, to deck2.
	public boolean moveCard(int deck1, int deck2, String name, int card) {
		//loadUserData();
		if (!loadUser(name)) return false;
		if ((!(user.hasDeck(deck1)) )||( !(user.hasDeck(deck2)))) return false;
		if (deck1 == deck2) return false;
		return CardmasterData.transferCard(deck1, deck2, card);
			
		
	}
	public MoveCard() {
	}
	

	
		
/*	public boolean userpatch(String name, String command, int amount, String text) {
		try{
			FileWriter writer = new FileWriter(CardmasterData.DIRECTORY + "userpatch.csc", true); 
			PrintWriter out = new PrintWriter(writer);
			//out.print(users[1]);
			out.println(name + ":" + command + ":" + text + ":" + amount + ":");
			out.close();
			return true;
	}catch(Exception e) {}
		return false;
	}*/
	public boolean loadUser(String name) {
		user = CardmasterData.loadUser(name);
		if (user==null) return false;
		return true;
		
	}	
}