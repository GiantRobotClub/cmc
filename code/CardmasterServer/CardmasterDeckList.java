package webrunner.cardmaster;
import java.io.*;

public class CardmasterDeckList {
	//CardmasterUser[] users;
	CardmasterUser user;
	int numdecks;
	int countcards[];
	
	public CardmasterDeckList(){}
	
	public void CountCards() {
		countcards = new int[user.decks.length];
		for (int i=1;i<user.decks.length;i++) {
		//	System.out.println("Checking size of deck ... " + i);
			try{
			
			CardmasterLibrary library = new CardmasterLibrary(user.decks[i]);
			countcards[i] = library.numcards;	
			} catch(Exception e) { countcards[i] = 0;
			}
			
		}
	}
	public int getDeckSize(int i) {
		
		try{
		
		return countcards[i];
		}catch(Exception e){ return 0;}
	}
	public boolean loadUser(String name) {
		user = CardmasterData.loadUser(name);
		if (user == null) return false;
		else return true;
			
		
	}
	
	
	
	public int numDecks() {
		return user.decks.length;
		
		
	}

	public int getDeck(int i) {
		return user.decks[i];	
		
		
	}
	public String getDeckName(int deck) {
		File file = new File(	CardmasterData.DIRECTORY + "decks/deckname_" + deck + ".csc");
		if (! file.exists() ) return "Generic Deck";
		else {
			try {
				FileReader reader = new FileReader(file);
				BufferedReader in = new BufferedReader(reader);
				String deckname =  in.readLine();
				reader.close();
				in.close();
				return deckname;
				
				
			}catch(Exception e) {
				
			return "Generic Deck";	
				
			}	
			
			
		}
	}
	
	public String primaryDeckMarker(int i) {
		if (user.decks[i] == user.primarydeck) return "(Primary)";
		else return "";
		
		
	}
	
	
	
	
	
}