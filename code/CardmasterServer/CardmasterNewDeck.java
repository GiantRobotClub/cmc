package webrunner.cardmaster;
import java.io.*;
//import java.util.StringTokenizer;
import java.util.Random;
// Card library/deck
public class CardmasterNewDeck {
	
//	CardmasterUser[] users;
	public CardmasterUser user;

	public CardmasterNewDeck() {
		
	}

	
	public boolean loadUser(String name) {
		user = CardmasterData.loadUser(name);
		if (user==null) return false;
		return true;
		
	}
	
	
	public int giveEmptyDeck() {
		CardmasterDeckFactory factory = new CardmasterDeckFactory();
		
		int deckno = factory.emptyDeck();
		if (deckno != -1) CardmasterData.userpatch(user.name,"addd",deckno,"x");
		return deckno;	
		
		
		
	}
	public int buyNewBooster(int expan) {
		int deck;
		if (expan > CardmasterData.MAXEXPAN) return -1;
		if (CardmasterData.BOOSTCOST > user.points) return -1;
		else deck = giveNewBooster(expan);
		if (deck != -1) CardmasterData.userpatch(user.name,"remp",CardmasterData.BOOSTCOST,"x");
		CardmasterData.userpatch(user.name,"addd",deck,"x");
		return deck;
		
		
	}
	
	public int buyNewBoosterBox(int expan) {
		int deck;
		if (expan > CardmasterData.MAXEXPAN) return -1;
		if (CardmasterData.BOOSTCOST*10 > user.points) return -1;
		else deck = giveNewBoosterBox(expan);
		if (deck != -1) CardmasterData.userpatch(user.name,"remp",CardmasterData.BOOSTCOST*10,"x");
		CardmasterData.userpatch(user.name,"addd",deck,"x");
		return deck;
		
		
	}
	

	public int giveNewBoosterBox(int expan) {
		CardmasterDeckFactory factory = new CardmasterDeckFactory();
		factory.init();
		int deckno = factory.createBoosterBox(expan);
		if (deckno != -1) CardmasterData.userpatch(user.name,"addd",deckno,"x");
		return deckno;	
		
		
		
	}
	
	public int giveNewBooster(int expan) {
		CardmasterDeckFactory factory = new CardmasterDeckFactory();
		factory.init();
		int deckno = factory.createBooster(expan);
		if (deckno != -1) CardmasterData.userpatch(user.name,"addd",deckno,"x");
		return deckno;	
		
		
		
	}
	public int buyThree(int expan) {
		int deck;
		if (expan > CardmasterData.MAXEXPAN) return -1;
		if (250 > user.points) return -1;
		else deck = giveNewDeckToUser(0,3,expan);
		if (deck != -1) CardmasterData.userpatch(user.name, "remp", 250, "x");	
		return deck;
		
		
	}
	public int giveNewDeckToUser(int colorcode, int numcards, int expan) {
		CardmasterDeckFactory factory = new CardmasterDeckFactory();	
		factory.init();
		int deckno = factory.createNewDeck(colorcode,numcards,expan);
		if (deckno != -1)  CardmasterData.userpatch(user.name,"addd",deckno,"x");
		
		return deckno;
		
	}

	public int buyNewDeck(int colorcode, int numcards) {
		
		return buyNewDeck(colorcode,numcards,-1);	
	}
	
	public int buyNewDeck(int colorcode, int numcards, int expan) {
		int deck;
		if (expan == 999) return -1; // cant buy Promotional Card decks.
		if (expan == 998) return -1; // cant buy Implementer Card decks
		if (cost(colorcode, numcards) > user.points) return -1;
		
		else deck = giveNewDeckToUser(colorcode,numcards, expan);
		//System.out.println("Have enough money..");
		if (deck != -1) CardmasterData.userpatch(user.name,"remp",cost(colorcode,numcards),"x");
		//System.out.println("Continue...");
		CardmasterData.userpatch(user.name,"addd",deck,"x");
		return deck;
		
		
	}
	public int buyNewPrebuilt(int deckn) {
		int price = 3000;
		int deck = -1;
		if (1700 > user.points) return -1;
		else deck = giveNewPrebuiltToUser(deckn);
		if (deck != -1)  CardmasterData.userpatch(user.name,"remp",3000,"x");
		CardmasterData.userpatch(user.name,"addd",deck,"x");
		return deck;	
		
		
	}
	public int giveNewPrebuiltToUser(int deckn) {
		CardmasterDeckFactory factory = new CardmasterDeckFactory();
		factory.init();
		int deckno = factory.newPreCopy(deckn,user.name);
		if (deckno != -1)  CardmasterData.userpatch(user.name,"addd",deckno,"x");
		return deckno;
		
		
	}

	public int giveNewPrebuiltToUser(String deckcode) {
		CardmasterDeckFactory factory = new CardmasterDeckFactory();
		factory.init();
		int deckno = factory.newPreCopy(deckcode,user.name);
		if (deckno != -1)  CardmasterData.userpatch(user.name,"addd",deckno,"x");
		return deckno;
		
		
	}
	

	public int cost(int colorcode, int numcards) {
		int COST_OF_ONE_CARD = 75;
		int all_cost = COST_OF_ONE_CARD * numcards;
		if (colorcode != 0) { 
			all_cost = all_cost * 3;
		
		}
		for (int i=1;i<=numcards;i++) {
			all_cost = (int)((double)all_cost * .985);	 // DISCOUNT.
			
			
		}

	//	//System.out.println("Cost: " + all_cost);
		return all_cost;
		
		
	}
	
}