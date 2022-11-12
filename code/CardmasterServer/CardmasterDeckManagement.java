	
package webrunner.cardmaster;
import java.util.Calendar;
import java.io.*;
import java.util.StringTokenizer;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
public class CardmasterDeckManagement {






	synchronized public static int[] loadDeck(int id) {
		FileReader reader = null;
		BufferedReader in = null;
		try {
			int[] cards = new int[0];
			File oldfile = new File(CardmasterData.DIRECTORY +"decks/deck_" + id + ".csc");
			File newfile = new File(CardmasterData.DIRECTORY +"decks/deck_" + id + ".cmc");
			if (oldfile.exists() && !newfile.exists()) {
				CardmasterData.convertDeck(id);
			}
			if (!oldfile.exists() && !newfile.exists()) {
				newfile.createNewFile();
			}
			String inputLine;
			reader = new FileReader(CardmasterData.DIRECTORY +"decks/deck_" + id + ".cmc");
			in = new BufferedReader(reader);
			while ((inputLine = in.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(inputLine);
				int cardid = Integer.parseInt(tokenizer.nextToken());
				if (cardid > CardmasterData.NUMBER_OF_CARDS) continue;
				int number = 1;
				if (tokenizer.hasMoreTokens()) number = Integer.parseInt(tokenizer.nextToken());
				for (int i=0;i<number;i++) {
					if (i >= 5 && id == 99999) break;
					int[] tempcards = new int[cards.length+1];
					System.arraycopy(cards,0,tempcards,0,cards.length);
					cards = new int[tempcards.length];
					System.arraycopy(tempcards,0,cards,0,tempcards.length);
					cards[cards.length-1] = cardid;	
					
				}
			//	System.out.println("There are " + number + " of card id " + cardid);
			}
			reader.close();
			in.close();
			return cards;
		} catch (Exception e) {
			e.printStackTrace();
			try{if (in != null) in.close();}catch(Exception xe) {xe.printStackTrace();}
			//		try{writer.close();}catch(Exception xe) {xe.printStackTrace();}
			try{if (reader != null)reader.close();}catch(Exception xe) {xe.printStackTrace();}
						
						
			
			
			
			return null;
		}
		
		
	}
	
	synchronized public static void saveDeck(int cards2[], String id) {
		FileWriter writer = null;
		PrintWriter out = null;
		try {
		
		  File newfile = new File(CardmasterData.DIRECTORY +"decks/deck_" + id + ".cmc");
	 		writer = new FileWriter(newfile);
			out = new PrintWriter(writer);
			
			for (int i=0;i<cards2.length;i++) {
				if (cards2[i] > 0) out.println(i + " " + cards2[i]);
			}
			writer.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
				try{if (out != null) out.close();}catch(Exception xe) {xe.printStackTrace();}
			//		try{writer.close();}catch(Exception xe) {xe.printStackTrace();}
			try{if (writer != null)writer.close();}catch(Exception xe) {xe.printStackTrace();}
						
				
		
		}
	}
	
	
	synchronized public static boolean transferCard(int deck1, int deck2, int card)  { 
	
	   if (card != -1) { // ( -1 = all cards ) 
		
			CardmasterLibrary deckone = null;
			if (deck1 != -1) deckone = new CardmasterLibrary(deck1);
			CardmasterLibrary decktwo = null;
			if (deck2 != -1) decktwo = new CardmasterLibrary(deck2);
	
			boolean addcard = false;
			boolean takecard = false;
			
	
			if (deck1 == -1 || deckone == null) { takecard = false; }
			else { takecard = true; }
			
			if (takecard) {
				
				if (deckone.numcards == 0) return false; //cant move last card.
				if (!(deckone.hasCard(card))) return false;
				if (!(deckone.takeCard(card))) return false;
			}
	
			if (deck2 == -1 || decktwo == null) { addcard = false; }
			else if (deck2 != 99999 && deck2 != -1) { decktwo.addCard(card); addcard = true; }
			else if (deck2 == 99999 && decktwo.countCard(card) < 5) { decktwo.addCard(card); addcard = true; } // max sold cards =50;
			
			try{		
			if (takecard) {
		
				deckone.save();
				
			
			}
			if (addcard) {
				decktwo.save();
			}		
			return true;	
			}catch(Exception e) {
			e.printStackTrace();
			//System.out.println("******EXCEPTION*********");
			return false; }		
			
		} else {
			
			boolean addcard = false;
			boolean takecard = false;
			
			CardmasterLibrary deckone = null;
			if (deck1 != -1) deckone = new CardmasterLibrary(deck1);
			CardmasterLibrary decktwo = null;
			if (deck2 != -1) decktwo = new CardmasterLibrary(deck2);
	
			if (deck1 == -1 || deckone == null) { takecard = false; }
			else { takecard = true; }
			
			while (deckone.numcards  >= 1) {
				int i = 0;
				int thecard =  deckone.cards[i];
				if (takecard) {
					
				//	if (deckone.numcards == 0) return false; //cant move last card.
					if (!(deckone.hasCard(thecard))) return false;
					if (!(deckone.takeCard(thecard))) return false;
				}
			
			
				if (deck2 == -1 || decktwo == null) { addcard = false; }
				else if (deck2 != 99999 && deck2 != -1) { decktwo.addCard(thecard); addcard = true; }
				else if (deck2 == 99999 && decktwo.countCard(thecard) < 5) { decktwo.addCard(thecard); addcard = true; } // max sold cards =50;
				
				
			}
			


			
			
			
			
			try{		
			if (takecard) {
		
				deckone.save();
				
			
			}
			if (addcard) {
				decktwo.save();
			}		
			return true;	
			}catch(Exception e) {
			e.printStackTrace();
			//System.out.println("******EXCEPTION*********");
			return false; }		
						
			
			
			
			
			
			
		}
		
		
	}
}