package webrunner.cardmaster;
import java.io.*;

public class CardmasterSetDeckName {
	

	public String name;
	//CardmasterUser[] users;
	CardmasterUser user;
	
public CardmasterSetDeckName(){}
	
	public boolean loadUser(String name) {
user = CardmasterData.loadUser(name);
if (user==null) return false;
return true;
		
	}
		


	
	public boolean setDeckName(int deck, String deckName) {
		//if (!loadUserData()) return false;
		if (!loadUser(name)) return false;
		if (!user.hasDeck(deck)) return false;
		FileWriter writer = null;
		PrintWriter out = null;
		try {
			writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deckname_" + deck + ".csc");
			out = new PrintWriter(writer);
			out.println(deckName);
			out.close();
			writer.close();
			return true;
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
				try{if (out != null) out.close();}catch(Exception xe) {xe.printStackTrace();}
			//		try{writer.close();}catch(Exception xe) {xe.printStackTrace();}
			try{if (writer != null)writer.close();}catch(Exception xe) {xe.printStackTrace();}
						
			
			
			return false;	
			
		}
		
		
		
		
	}
	
	
	
}