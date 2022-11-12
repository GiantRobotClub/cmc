package webrunner.cardmaster;
import java.util.StringTokenizer;
import java.io.*;
public  class BuySellCard {
	CardmasterUser user;
		public BuySellCard(){}
	
		CardmasterServerCard carddata[];
		public void loadCardData() {
		carddata = new CardmasterServerCard[CardmasterData.NUMBER_OF_CARDS];
		try {
			FileReader reader = new FileReader(CardmasterData.DIRECTORY + "cards.csc");
			BufferedReader in = new BufferedReader(reader);
			
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(inputLine,"#");
				int cardid = Integer.parseInt(token.nextToken());
				 carddata[cardid] = new CardmasterServerCard(inputLine);
				
		//	//System.out.println(carddata[cardid]);
				
				
			}
			
			in.close();
			reader.close();
			
			
		}
		catch (Exception e) {
			e.printStackTrace();}	
		
		
	}
	
	public int sellDeck(String name, int deck) {
		if (!loadUser(name)) return 0;
		if ((!(user.hasDeck(deck)))) return 0;
		if (user.primarydeck == deck) return 0;
		int allprice = 0;
		CardmasterShowDeckBean deckshow = new CardmasterShowDeckBean();
		deckshow.setName(name);
		deckshow.loadData(deck);
		for (int i=0;i<deckshow.getDeckSize();i++) {
			allprice+=deckshow.getSellCost(i);
		}
		CardmasterData.transferCard(deck, 99999, -1);
		killDeckRegardless(name,deck);
		return allprice;
		
		
	}
	public boolean killDeckRegardless(String name, int deck1) {
		if (!loadUser(name)) return false;
		if ((!(user.hasDeck(deck1)) )) return false;
		if (user.primarydeck == deck1) return false;
		
		 CardmasterData.userpatch(name,"rmd!",deck1,"x");
		File file = new File(CardmasterData.DIRECTORY + "decks/deck_" + deck1 + ".cmc");
		//if (file.exists()) if (file.length() >2) return false;
		
		File deckname = new File(CardmasterData.DIRECTORY + "decks/deckname_" + deck1 + ".csc");
		if (deckname.exists()) { deckname.delete();} 
		
		if (file.exists()) { file.delete();  return true;} 
		
		
		return false;
		
	}	
	public boolean killDeck(String name, int deck1) {
		if (!loadUser(name)) return false;
		if ((!(user.hasDeck(deck1)) )) return false;
		if (user.primarydeck == deck1) return false;
		
		 CardmasterData.userpatch(name,"remd",deck1,"x");
		File file = new File(CardmasterData.DIRECTORY + "decks/deck_" + deck1 + ".cmc");
		if (file.exists()) if (file.length() >2) return false;
		
		File deckname = new File(CardmasterData.DIRECTORY + "decks/deckname_" + deck1 + ".csc");
		if (deckname.exists()) { deckname.delete();} 
		
		if (file.exists()) { file.delete();  return true;} 
		
		
		return false;
		
	}	
	// move card from deck1, to deck2.

	public int buyCard(int deck2,String name, int card) {
		//File waitfile = new File(CardmasterData.DIRECTORY +"cardwait");
				if (!loadUser(name)) {  return 0; }
		int deck1 = 99999;
		if ((!(user.hasDeck(deck2)) )) {  return 0; }
		
		CardmasterLibrary deckone = new CardmasterLibrary(deck1);
		CardmasterLibrary decktwo = new CardmasterLibrary(deck2);
		try{
	//	while (waitfile.exists()) {};
	//	waitfile.createNewFile();		

		int price = CardmasterData.calcBuy(carddata[card].printed);	
		if (! carddata[card].available ) price = 2000;
		if (user.points < price) return 0;
		if (!CardmasterData.transferCard(deck1, deck2, card)) price = 0;
	//	waitfile.delete();
		return price;	
		}catch(Exception e) {
		//waitfile.delete();
		e.printStackTrace();
		//System.out.println("******EXCEPTION*********");
		return 0; }
		
		
	}
	
	public int sellCard(int deck1,String name, int card)  {
	//	File waitfile = new File(CardmasterData.DIRECTORY +"cardwait");
		
		if (card != -1) {
			if (!loadUser(name)) {return 0;}
			int deck2 = 99999;
	
			if ((!(user.hasDeck(deck1)) )) {return 0;}
			
			try{
		//	while (waitfile.exists()) {};
		//	waitfile.createNewFile();		
	
			int price = CardmasterData.calcBuy(carddata[card].printed) /5;	
			if (!CardmasterData.transferCard(deck1, deck2, card)) price = 0;
		//	waitfile.delete();
			return price;	
			}catch(Exception e) {
		//	waitfile.delete();
			e.printStackTrace();
			//System.out.println("******EXCEPTION*********");
			return 0; }
		} else {
			if (!loadUser(name)) { return 0; }
			int deck2 = 99999;
			if ((!(user.hasDeck(deck1)))) {return 0;}
			
			return 0;
			
		}
		
	}
	
	
	
	


	public boolean loadUser(String name) {
		user = CardmasterData.loadUser(name);
		if (user==null) return false;
		return true;
		
		
	}	
}