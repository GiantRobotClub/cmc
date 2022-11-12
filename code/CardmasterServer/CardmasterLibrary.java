package webrunner.cardmaster;
import java.io.*;
import java.util.StringTokenizer;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.Arrays;
import java.util.Vector;

// Card library/deck
public class CardmasterLibrary {
	public boolean created = false;
	public int cards[];
	public int deck[];
	public int grave[];
	public int id;
public	int numcards = 0;
public	int deckcards = 0;
public int gravecards = 0;
public String deckname;
MersenneTwisterFast random;
//CardmasterUser[] users;
public CardmasterUser user;
	
	public void PrepareDeck(MersenneTwisterFast random) {
		deck = new int[0];
		grave = new int[0];
		for (int i =0; i<cards.length; i++) {
			int[] tempcards = new int[deck.length+1];
			System.arraycopy(deck,0,tempcards,0,deck.length);
			deck = new int[tempcards.length];
			System.arraycopy(tempcards,0,deck,0,tempcards.length);
			deck[i] = cards[i];
	//	//System.out.println("Card... " + cards[i]);	
		}
			
		deckcards = numcards;
		this.random = random;
		shuffleDeck();
	}
	
	
	public void shuffleDeck() {


//System.out.println("SHUFFLING: ");
		for (int j=0;j<deck.length / 5 + 5;j++) {
		
			for(int i =0;i<deckcards;i++) {
				int b = i+random.nextInt(deckcards-i);
				//System.out.print(deck[i] + "," + deck[b]);
				int temp = deck[i];
				deck[i] = deck[b];
				deck[b] = temp;
				
			//	System.out.print("->"+deck[i] + "," + deck[b] +" | ");
				
			}
		
		}	
		
	}

	public int getCard(int i) {
		return cards[i];
		
		
		
	}
	
	public boolean movemTypeToTop(String type, CardmasterServerCard[] carddata) {
			int foundnum = -1;
		for (int i=1;i<deckcards;i++) {
			if (deck[i] <= 0 || deck[i] > CardmasterData.NUMBER_OF_CARDS) continue;
			if (carddata[deck[i]].dummy) continue;
			if (carddata[deck[i]].typecode.equals("m") || carddata[deck[i]].typecode.equals("e") || carddata[deck[i]].typecode.startsWith("d")) {
				
				if (carddata[deck[i]].mtype.indexOf(type,0) != -1 || type.equals("a")) {
					foundnum = i;
					break;
				}
				if (carddata[deck[i]].flag("alltype"))  {
					foundnum = i;
					break;
				}
				if (carddata[deck[i]].flag("waltype"))  {
					foundnum = i;
					break;
				}
				
			}
		}
		if (foundnum == -1) {
			boolean cont = true;
			if (deck[0] <= 0 || deck[0] > CardmasterData.NUMBER_OF_CARDS) cont = false;
			if (cont) if (carddata[deck[0]].dummy) cont = false;
			if (cont) {
				if (carddata[deck[0]].typecode.equals("m") || carddata[deck[0]].typecode.equals("e")|| carddata[deck[0]].typecode.startsWith("d")) {
			
					if (carddata[deck[0]].mtype.indexOf(type,0) != -1) {
						foundnum = 0;
					}
					if (carddata[deck[0]].flag("alltype"))  {
						foundnum = 0;
					}
					if (carddata[deck[0]].flag("alltype"))  {
						foundnum = 0;
					}
				}
			}
		}
		if (foundnum == -1) return true;
		
		if (foundnum == 0) {
			return true;
		}
		else {
			int c = foundnum;
			int cardid = deck[c];
			for (int i=c;i<deckcards-1;i++) {
				deck[i] = deck[i+1];
			}
			deck[deckcards-1] = deck[0];
			deck[0] = cardid;
			
			return true;
			
		}
	}
	
	public boolean moveToTop(String type,CardmasterServerCard[] carddata) {
		
		int foundnum = -1;
		for (int i=1;i<deckcards;i++) {
			if (deck[i] <= 0 || deck[i] > CardmasterData.NUMBER_OF_CARDS) continue;
			if (carddata[deck[i]].dummy) continue;
			
			if (type.indexOf(carddata[deck[i]].typecode,0) != -1 || type.equals("a") || type.equals(carddata[deck[i]].typecode)) {
				foundnum = i;
				break;
			}
		}
		if (foundnum == -1) {
			boolean cont = true;
			if (deck[0] <= 0 || deck[0] > CardmasterData.NUMBER_OF_CARDS) cont = false;
			if (cont) if (carddata[deck[0]].dummy) cont = false;
			if (cont) if (type.indexOf(carddata[deck[0]].typecode,0) != -1|| type.equals("a") || type.equals(carddata[deck[0]].typecode)) {
				foundnum = 0;
				
			}
		}
		if (foundnum == -1) {
			System.out.println("FOUNDNUM -1");
			return true;
		}
		if (foundnum == 0) {
			System.out.println("FOUNDNUM 0");
			return true;
		}
		else {
			int c = foundnum;
			int cardid = deck[c];
			for (int i=c;i<deckcards-1;i++) {
				deck[i] = deck[i+1];
			}
			deck[deckcards-1] = deck[0];
			deck[0] = cardid;
			
			return true;
			
		}
		
	}

	public int nextDrawCard(int waiting) {
		if (waiting > deckcards) return -1;
		if (waiting == 0) {
	
			int cardid = deck[0];
			return cardid;

		}
		else {
			int cardid = deck[deckcards-waiting];
			return cardid;
			
		}
		
	}
	
	
	public int drawCard() {
		if (deckcards <= 0) return -1;
		
		int a;
		/*if (deckcards == 1) a = 0;
		else a = random.nextInt(deckcards);
		*/
		a = 0;
		//System.out.println(a + " - " + deck[a]);
		int cardid = 0;
		for (int i=0;i<deckcards;i++) {
			
			cardid = deck[i];
			if (cardid != 0) break;
		}
		
	
		
		////System.out.println(this);
		int returnedcard= drawCard(cardid);
		return returnedcard;
		
		
	}
	public int drawCard(int id) {
		if (deckcards == 0) return -1; // no cards left.  YOU LOSE, HA HAHAHAHAHAHAHAHAHAHAHAHAHAAHA
		for (int i = 0; i<deckcards; i++) {
			if (deck[i] == id) {
				deck[i] = deck[deckcards-1];
				deck[deckcards-1] = 0;
				deckcards--;
//System.out.println("DRAWING CARD: " + id);
				return id;
			
				
			}
		}
//System.out.println("Couldn'd find " + id + " to draw");
		return 0; // card not found.
	}

	public boolean isInGrave(int id) {
		if (gravecards == 0) return false; // no cards left in graveyard
		for (int i = 0; i<gravecards; i++) {
			if (grave[i] == id) {
			return true;
			
				
			}
		}
		return false; // card not found.
	}
	
	public void recyclegraveold() {
		int[] newdeck = new int[0];
		
		/*
		for (int i=deckcards;i<deckcards+gravecards && i < deck.length;i++) {
			deck[i] = grave[i-deckcards];
		}
		*/
				
	//	deckcards = deckcards;
		
		//int tempdeck = deckcards;
		
		for (int i=0;i<grave.length;i++) {
			addPlayDeck(grave[i]);
			
		}
		
		gravecards = 0;
		
		shuffleDeck();
		grave = new int[0];
		
		
		
	}


	public void recyclegrave() {
	//	int[] newdeck = new int[0];
		Vector <Integer>  vec = new Vector<Integer>();
		
		
		for (int i=0;i<deckcards;i++) {
			vec.add(deck[i]);
//System.out.println("RG:::::: ADDING " + deck[i] + " FROM DECK - " + i);
			
		}
			
		for (int i=0;i<grave.length;i++) {
			vec.add(grave[i]);
//System.out.println("RG:::::: ADDING " + grave[i] + " FROM GRAVE - " + i);
		}





		
		deck = new int[vec.size()];
		
		int i= 0;
		for (Integer n:vec) {
			if (i > vec.size()) break;
			deck[i] = n;
		
			
//System.out.println("RG:::::: ADDING " + n + " BACK TO DECK - " + deck[i] + " - " + i + " - " + vec.size());
				i++;
		}
		

		
		deckcards = i;
		gravecards = 0;
		grave = new int[0];
		shuffleDeck();


		
	}

	public int drawGrave(int id) {

		if (gravecards == 0) return -1; // no cards left in graveyard
		for (int i = 0; i<gravecards; i++) {
			if (grave[i] == id) {
				grave[i] = grave[gravecards-1];
				grave[gravecards-1] = 0;
				gravecards--;
				int[] tempgrave = new int[gravecards];
				System.arraycopy(grave,0,tempgrave,0,gravecards);
				grave = new int[gravecards];
				System.arraycopy(tempgrave,0,grave,0,gravecards);
				
				return id;
			
				
			}
		}
		return 0; // card not found.
	}


	public int drawGrave() { //pick a random card from Deh Grave.
		if (gravecards <= 0) return -1; if (gravecards == 1) return drawGrave(grave[0]);
		int a = 0;
		if (gravecards > 0) {
			a = random.nextInt(gravecards-1);
		}
		int cardid = grave[a];
		////System.out.println(this);
		return drawGrave(cardid);
		
	}

	public String toString() {
		String string = "[";
		for (int i = 0; i<numcards;	i++) {
			string = string + cards[i] + ",";
			
			
		}
		string = string + "][";
		for (int i = 0; i<deckcards;	i++) {
			string = string + deck[i] + ",";
			
			
		}
		string = string + "]";		
		return string;
		
		
		
	}
	public boolean loadUser(String name) {
		user = CardmasterData.loadUser(name);
		if (user==null) return false;
		else return true;
		
		
	}
		public int countCard(int id) {
		////System.out.println("lookin for the Card..");
		int count = 0;
		for (int i = 0;i<cards.length;i++) {
		//	//System.out.println("Checking " + cards[i] + " against " + id);
			if (cards[i]==id) count++;
			
		}	
		////System.out.println("not Found");
		return count;
		
		
	}
	public boolean hasCard(int id) {
		////System.out.println("lookin for the Card..");
		for (int i = 0;i<cards.length;i++) {
		//	//System.out.println("Checking " + cards[i] + " against " + id);
			if (cards[i]==id) return true;
			
		}	
		////System.out.println("not Found");
		return false;
		
		
	}
	public void addCard(int i) {
		int cardid = i;
		int[] tempcards = new int[cards.length+1];
		System.arraycopy(cards,0,tempcards,0,cards.length);
		cards = new int[tempcards.length];
		System.arraycopy(tempcards,0,cards,0,tempcards.length);
		cards[tempcards.length-1] = cardid;
		numcards++;		
		
		
	}

	public void addGrave(int i) {
		int cardid = i;
		int[] tempcards = new int[grave.length+1];
		System.arraycopy(grave,0,tempcards,0,grave.length);
		grave = new int[tempcards.length];
		System.arraycopy(tempcards,0,grave,0,tempcards.length);
		grave[tempcards.length-1] = cardid;
		gravecards++;	
		
		
	}

	public void addPlayDeck(int i) {
		int cardid = i;
		int[] tempcards = new int[deck.length+1];
		System.arraycopy(deck,0,tempcards,0,deck.length);
		deck = new int[tempcards.length];
		System.arraycopy(tempcards,0,deck,0,tempcards.length);
		deck[deck.length-1] = cardid;
		deckcards++;		
		
		
	}
	
	public boolean takeCard(int id) {
		if (numcards == 0) return false;
		if (cards.length == 0) return false;
		for (int i = 0; i<cards.length; i++) {
			if (cards[i] == id) {
				cards[i] = cards[numcards-1];
				int[] tempcards = new int[cards.length-1];
				System.arraycopy(cards,0,tempcards,0,cards.length-1);
				cards = new int[tempcards.length];
				System.arraycopy(tempcards,0,cards,0,tempcards.length);
				numcards--;
				return true;
			
				
			}
		}
		return false; // card not found.
	}

	public void loadDeckName(int id) {
		deckname = CardmasterData.getDeckName(id);
		
		
		
	}
	public CardmasterLibrary() { //empty library
		cards = new int[0];
		grave = new int[0];
		deck = new int[0];
		 numcards = 0;
		 deckcards =0;
		 gravecards =0;
		 id = 0;
		
		}	
	public CardmasterLibrary(String name) {
		//cards = new int[0];
		int currentcard = 0;
		loadUser(name);
		if (loadUser(name)) {
			id = user.primarydeck;	
			
			
			
		}
		else id = -1; 
		if (id != -1) created = true;
		cards = CardmasterData.loadDeck(id);
		loadDeckName(id);
		numcards = cards.length;
	}
	
public 	CardmasterLibrary(String name, int id) {
		cards = CardmasterData.loadDeck(id);
		System.out.println("Loading deck: " + id);
		this.id = id;
		loadUser(name);
		loadDeckName(id);
		numcards = cards.length;
		
		
		
	}
	
	public void save() {
			int[] cards2 = new int[CardmasterData.NUMBER_OF_CARDS];
			for (int i=0;i<cards2.length;i++) cards2[i] = 0;
			for (int i=0;i<cards.length;i++) {
				cards2[cards[i]]++;
			}
			CardmasterData.saveDeck(cards2,id);	
	}
	
public 	CardmasterLibrary(int id) {
		this.id = id;
			cards = CardmasterData.loadDeck(id);
			loadDeckName(id);
	
	numcards = cards.length;
	
	
	}
}