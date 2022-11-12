package webrunner.cardmaster;

import java.io.*;
import java.util.StringTokenizer;

public class CardmasterTradeRoom {
		public boolean reserve = false;
		public boolean dead =false;
		public boolean empty = true;
		public boolean used =false;
		public boolean close =false;
		public boolean ended = false;
		public boolean started = false;
		int roomnumber = 0;
		public int timeout = 1000;
		private String[] messagebuffer;
		private int[] messageid;
		//private String[] messageto;
		private int currentmessageid;
		private int currentindex;
		
		public int roomno;
		public String name1;
		public String name2;
		
		public CardmasterLibrary[] decks1;
		public int d1no;
		
		public CardmasterLibrary[] decks2;
		public int d2no;
		
		public CardmasterLibrary table1;
		public CardmasterLibrary table2;
		
		public int points1 = 0;
		public int points2 = 0;
		
		
		public boolean locked1 = false;
		public boolean locked2 = false;
		public boolean ready1 = false;
		public boolean ready2 = false;
		
		public CardmasterUser user1;
		public CardmasterUser user2;

		public int selectedDeck1;
		public int selectedDeck2;
		
		CardmasterServerCard[] carddata;
		
		public int table1num;
		public int table2num;
	
	
	public void opfound(int roomno) {
		this.roomno = roomno;
		init_trade();
		message("TRADEINIT#" + name1 + "#" + name2 + "#");
	}
			
	CardmasterTradeRoom() {
		////System.out.println("Room created.");
		empty = true;
		dead = false;
		name1 = "";
		name2 = "";
		messagebuffer = new String[100];
		messageid = new int[100];
		currentmessageid = 1;
		currentindex = 0;
		carddata = CardmasterData.loadCardData();
		table1 = new CardmasterLibrary();
		table2 = new CardmasterLibrary();
		
	
		
		
		
		}
	public void init_trade() {
		System.out.println(name1 + " trading with " + name2);
		CardmasterLogManager.WriteLog(name1 + " begins trading with " + name2);
		
		user1 = CardmasterData.loadUser(name1);
		user2 = CardmasterData.loadUser(name2);
		selectedDeck1 = 1;
		selectedDeck2 = 1;
		load_decks();
		started = true;	
		
	

		
	}
	void return_card_info(int cardid) {
	//	for (int i=0;i<1000;i++);
		if (cardid == 0) message("CDD#0#b#");
		else message("CDD#" + cardid + "#" + carddata[cardid] + "#");	
		
	}
	public void switch_deck(String name, int deckid) {
		CardmasterUser user = userNamed(name);
		if (!(user.hasDeck(deckid))) {
			
			
			//System.out.println(name + " doesn't have deck " + deckid);
			 return;	 
			 
			 
			 
		}
		if (user == user1) {
			for (int i=1;i<user.decks.length;i++) {
				if (user.decks[i] == deckid) { selectedDeck1 = i; return; }
					
			}	
			
			
		}
		else if (user == user2) {
			for (int i=1;i<user.decks.length;i++) {
				if (user.decks[i] == deckid) { selectedDeck2 = i; return; }
					
			}	
			
			
		}	
		
	}
	
	public void load_decks() {
		
		CardmasterNewDeck newd1 = new CardmasterNewDeck();
		CardmasterNewDeck newd2 = new CardmasterNewDeck();
		
		newd1.loadUser(name1);
		newd2.loadUser(name2);
		
		table1num = newd1.giveEmptyDeck();
		table2num = newd2.giveEmptyDeck();
		
		
		
		
		decks1 = new CardmasterLibrary[1];
		for (int i=1;i<user1.decks.length;i++) {
			CardmasterLibrary[] temp = new CardmasterLibrary[decks1.length];
			System.arraycopy(decks1,0,temp,0,decks1.length);
			decks1 = new CardmasterLibrary[decks1.length+1];
			System.arraycopy(temp,0,decks1,0,temp.length);
			decks1[decks1.length-1] = new CardmasterLibrary(user1.name, user1.decks[i]);
			
		}
		decks2 = new CardmasterLibrary[1];
		for (int i=1;i<user2.decks.length;i++) {
			CardmasterLibrary[] temp = new CardmasterLibrary[decks2.length];
			System.arraycopy(decks2,0,temp,0,decks2.length);
			decks2 = new CardmasterLibrary[decks2.length+1];
			System.arraycopy(temp,0,decks2,0,temp.length);
			decks2[decks2.length-1] = new CardmasterLibrary(user2.name, user2.decks[i]);
			
		}
		
		table1 = new CardmasterLibrary();
		table2 = new CardmasterLibrary();
		
	}

	public int btoi(boolean bool) {
		if (bool) return 1;
		else return 0;	
	}

	public String tables() {
		
		String p1text = "P1#" + name1 + "#" + points1 + "#" + btoi(locked1) + "#" + btoi(ready1) + "#";
		if (table1 != null) {
		
		int currentquantity = 0;
		
		for (int i=0;i<table1.numcards;i++) {
			int gotcard = table1.getCard(i);
			int nextcard =-1;
			if ((i+1) < table1.numcards) nextcard = table1.getCard(i+1);
			if (nextcard == gotcard) currentquantity++;
			else { 
				currentquantity++;
				p1text = p1text + gotcard + "x" + currentquantity + "x#";
				currentquantity = 0;
			
			
			}

			
		}
		}
		String p2text = "P2#" + name2 + "#" + points2 + "#" + btoi(locked2) + "#" + btoi(ready2) + "#";
		if (table2 != null) {
		
		int currentquantity = 0;
		
		for (int i=0;i<table2.numcards;i++) {
			int gotcard = table2.getCard(i);
			int nextcard =-1;
			if ((i+1) < table2.numcards) nextcard = table2.getCard(i+1);
			if (nextcard == gotcard) currentquantity++;
			else { 
				currentquantity++;
				p2text = p2text + gotcard + "x" + currentquantity + "x#";
				currentquantity = 0;
			
			
			}

			
		}
		}
		return "TABLE#" + p1text + p2text;
				
		
	}
	

	
	public int say(String name, String message) {
	//	//System.out.println("Received message from " + name + " : " + message);
	// CHA#" + playerroom.getName() + "#" + outputLine + "#
		message("TRCHA#" + name + "#" + message + "#");
		
	//	//System.out.println("Message Number " + messageid);
		return 1;
	}	
	public CardmasterUser userNamed(String name) {
		if (name.equals(name1)) return user1;
		else if (name.equals(name2)) return user2;
		else return null;	
		
		
	}
	
	public void setUserLock(String name, boolean lock) {
			CardmasterUser user = userNamed(name);
			
			if (user == user1) {
				locked1 = lock;
				ready1 = false;
				
			}
			else if (user == user2) {
				locked2 = lock;	
				ready2 = false;
				
			}
			message(tables());
		
		
	}

	 public void returnCards() {
		/*
		int newdeck1 =factory.getClearDeckCode();
		try {
			/*
			FileWriter writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deck_" + newdeck1 + ".csc");
			PrintWriter out = new PrintWriter(writer);
			for (int i=0;i<table2.numcards;i++) {
				if (i < table2.numcards-1) out.println(table2.cards[i]);	
				else out.print(table2.cards[i]);
		
			}
			writer.close();
			out.close();
			
			int[] cards2 = new int[CardmasterData.NUMBER_OF_CARDS];
			for (int i=0;i<cards1.length;i++) cards1[i] = 0;
			for (int i=0;i<table1.cards.length;i++) {
				cards1[table1.cards[i]]++;
			}
			CardmasterData.saveDeck(cards1,newdeck1);
				
		}
			
		catch (Exception e) {  return; }
		int newdeck2 = factory.getClearDeckCode();
		try {
			/*
			FileWriter writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deck_" + newdeck2 + ".csc");
			PrintWriter out = new PrintWriter(writer);
			for (int i=0;i<table1.numcards;i++) {
				if (i < table1.numcards-1) out.println(table1.cards[i]);	
				else out.print(table1.cards[i]);
		
			}
			writer.close();
			out.close();
			int[] cards2 = new int[CardmasterData.NUMBER_OF_CARDS];
			for (int i=0;i<cards2.length;i++) cards2[i] = 0;
			for (int i=0;i<table2.cards.length;i++) {
				cards2[table2.cards[i]]++;
			}
			CardmasterData.saveDeck(cards2,newdeck2);				
		}
			
		catch (Exception e) {  return; }					
		CardmasterData.userpatch(name1,"addd",newdeck1,"x");
		CardmasterData.userpatch(name2,"addd",newdeck2,"x");
		table1 = new CardmasterLibrary();
		table2 = new CardmasterLibrary();	
		*/	
					
	}
	public void doTrade() {
		System.out.println(name1 + " finishes trade with " + name2);
		CardmasterLogManager.WriteLog(name1 + " ends trade with " + name2);
		String tempname1 = name1;
		String tempname2 = name2;
		locked1 = false;
		locked2 = false;
		ready1 = false;
		ready2 = false;
		// create two decks
		message(tables());
		message("TRDONE#");	
		
		/*
		
		CardmasterDeckFactory factory = new CardmasterDeckFactory();
	/int newdeck1 =factory.getClearDeckCode();
		try {
			
			FileWriter writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deck_" + newdeck1 + ".csc");
			PrintWriter out = new PrintWriter(writer);
			for (int i=0;i<table2.numcards;i++) {
				if (i < table2.numcards-1) out.println(table2.cards[i]);	
				else out.print(table2.cards[i]);
		
			}
			writer.close();
			out.close();
			
			int[] cards2 = new int[CardmasterData.NUMBER_OF_CARDS];
			for (int i=0;i<cards2.length;i++) cards2[i] = 0;
			for (int i=0;i<table2.cards.length;i++) {
				cards2[table2.cards[i]]++;
			}
			CardmasterData.saveDeck(cards2,newdeck1);		
		}
			
		catch (Exception e) {  return; }		
		int newdeck2 = factory.getClearDeckCode();
		try {
			
			FileWriter writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deck_" + newdeck2 + ".csc");
			PrintWriter out = new PrintWriter(writer);
			for (int i=0;i<table1.numcards;i++) {
				if (i < table1.numcards-1) out.println(table1.cards[i]);	
				else out.print(table1.cards[i]);
		
			}
			writer.close();
			out.close();
			int[] cards2 = new int[CardmasterData.NUMBER_OF_CARDS];
			for (int i=0;i<cards2.length;i++) cards2[i] = 0;
			for (int i=0;i<table1.cards.length;i++) {
				cards2[table1.cards[i]]++;
			}
			CardmasterData.saveDeck(cards2,newdeck2);				
		}
			
		catch (Exception e) {  return; }			
   
		// save all the decks
		
		for (int j=1;j<user1.decks.length;j++) {
				int[] cards2 = new int[CardmasterData.NUMBER_OF_CARDS];
			for (int i=0;i<cards2.length;i++) cards2[i] = 0;
			for (int i=0;i<decks1[j].cards.length;i++) {
				cards2[decks1[j].cards[i]]++;
			}
			CardmasterData.saveDeck(cards2,user1.decks[j]);	
			
		}		


		for (int j=1;j<user2.decks.length;j++) {
			/*
			try{
			FileWriter writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deck_" + user2.decks[i] + ".csc");
			PrintWriter out = new PrintWriter(writer);
				for (int j=0;j<decks2[i].numcards;j++) {
					if (j < decks2[i].numcards-1) out.println(decks2[i].cards[j]);	
					else out.print(decks2[i].cards[j]);		
				}
			out.close();
			writer.close();
	
			} catch (Exception e) { return; }
			
			int[] cards2 = new int[CardmasterData.NUMBER_OF_CARDS];
			for (int i=0;i<cards2.length;i++) cards2[i] = 0;
			for (int i=0;i<decks2[j].cards.length;i++) {
				cards2[decks2[j].cards[i]]++;
			}
			CardmasterData.saveDeck(cards2,user2.decks[j]);		
			
		}				
		
		*/
		// give those two decks to the opposing people.
		if (tempname1 != null && tempname2 != null)
			if (!tempname1.equals("") && !tempname2.equals("")) {
				
		CardmasterData.userpatch(tempname1,"rmdx",table1num,"x");
		CardmasterData.userpatch(tempname2,"rmdx",table2num,"x");	
		CardmasterData.userpatch(tempname1,"addd",table2num,"x");
		CardmasterData.userpatch(tempname2,"addd",table1num,"x");
		
	

		
		// give the points to the opposing people
			CardmasterLogManager.WriteLog("point trade: " + name1 + " ("+points1+") "+ name2+ " ("+points2+") ");
		CardmasterData.userpatch(tempname1,"addp",points2,"x");
		CardmasterData.userpatch(tempname2,"addp",points1,"x");
		CardmasterData.userpatch(tempname1,"remp",points1,"x");
		CardmasterData.userpatch(tempname2,"remp",points2,"x");
		}			
		
		// tell people the trade is complete.
	
		
		// die
		
		dead = true;
		close = true;
		ended = true;
		
	}
	
	public void setReady(String name, boolean lock) {
		CardmasterUser user = userNamed(name);
			if (user == user1) {
				ready1 = lock && locked1;	
				
			}
			else if (user == user2) {
				ready2 = lock && locked2;	
				
			}
			if (ready1 && ready2) { 
				doTrade();
				return;
				
			}	
		message(tables());
		
	}
	public void addTable(String name, int cardid) {
		CardmasterUser user = userNamed(name);
		if (user == user1)  {
			
			if (locked1) return;
			int num = cardid;
			if (! (decks1[selectedDeck1].takeCard(cardid))) num = -1;
			if (num == -1) return;
			
			table1.addCard(num);
			
			CardmasterData.transferCard(decks1[selectedDeck1].id,table1num,cardid);
			
			
		}
		else if (user == user2) {
			if (locked2) return;
			
			int num = cardid;
			if (! (decks2[selectedDeck2].takeCard(cardid))) num = -1;
			if (num == -1) return;
			
			table2.addCard(num);
			
				CardmasterData.transferCard(decks2[selectedDeck2].id,table2num,cardid);
				
			
		}
		message(tables());	
			
		
	}
	
	public void takeTable(String name, int cardid) {
		CardmasterUser user = userNamed(name);
		if (user == user1)  {
			
			if (locked1) return;
			int num = cardid;
			if (! (table1.takeCard(cardid))) num = -1;
			if (num == -1) return;
			
			decks1[selectedDeck1].addCard(num);
			
				CardmasterData.transferCard(table1num,decks1[selectedDeck1].id,cardid);
			
			
			
		}
		else if (user == user2) {
			if (locked2) return;
			
			int num = cardid;
			if (! (table2.takeCard(cardid))) num = -1;
			if (num == -1) return;
			
			decks2[selectedDeck2].addCard(num);	
			
			CardmasterData.transferCard(table2num,decks2[selectedDeck2].id,cardid);
			
		}	
			
		message(tables());		
		
	}

	
	public void modPoints(String name, int num) {
		CardmasterUser user = userNamed(name);
		if (user == user1 && locked1) return;
		if (user == user2 && locked2) return;
		if (user == null) return;
		
			if (user.points - num < 0) num = user.points;
		if (user == user1) {
		
			if (points1 + num <0) num = -points1;
			
			
		}
		else if (user == user2) {
			if (points2 + num <0) num = -points2;
					
			
		}
		else return;
		
		
		user.points -= num;
		if (user == user1) points1 += num;
		else if (user == user2) points2 += num;
		
		message(tables());
		
	}

	public String deckList(String name) {
		CardmasterUser user = userNamed(name);	
		String decklist = "YDECKS#";
		for (int i =1;i<user.decks.length;i++) {
			decklist = decklist + user.decks[i] + "#";
			
		}
		return decklist;
		
	}
	public String deckText(String name) {
		CardmasterLibrary temp;
		int deckno;
		int points;
		if (name.equals(name1)) {
			temp = decks1[selectedDeck1];
			deckno = user1.decks[selectedDeck1];
			points = user1.points;
			
		} else if (name.equals(name2)) {
			temp = decks2[selectedDeck2];
			deckno = user2.decks[selectedDeck2];
			points = user2.points;	
			
			
		}
		else return "";	
		String dt = "YOURDECK#" + deckno + "#" + points + "#";
		int currentquantity = 0;
		for (int i=0;i<temp.numcards;i++) {
			int gotcard = temp.getCard(i);
			int nextcard = -1;
			
			if ((i+1) < temp.numcards) nextcard = temp.getCard(i+1);
			if (nextcard == gotcard) currentquantity++;
			else { 
				currentquantity++;
				dt = dt + gotcard + "x" + currentquantity + "x#";
				currentquantity = 0;
			
			
			}
		
			if (CardmasterData.DEBUGMODE) System.out.println(gotcard + " " + nextcard + " " + currentquantity);
			
			
			
		}
		return dt;
		
		
		
	}







	public void message(String message) {
		
		currentindex ++;
		if (currentindex >= 100) {
			currentindex = 0;	
			
			
		}
		currentmessageid++;
	////System.out.println("In " + currentindex + " message " + currentmessageid + " : " + message);
		messagebuffer[currentindex] = message;
		messageid[currentindex] = currentmessageid;
	}

	public int messageTag() {
		return currentmessageid;
	}
	public String getMessage(int id) {
		int index;
		index = -1;
	//	//System.out.println("Looking for " + id);
		if (id > currentmessageid) return "";
		for (int i = 0;i<100;i++) {
			if (messageid[i] == id) { 
			index = i;
			//	//System.out.println("Found message in "+ i);
				}
		}
		if (index == -1) return "";
	//	//System.out.println("Returning " + messagebuffer[index]);
		return messagebuffer[index];
	}
	

}