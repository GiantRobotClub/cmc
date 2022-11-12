package webrunner.cardmaster;
import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;
public class CardmasterDeckFactory {
	
	public CardmasterDeckFactory(){}
	CardmasterServerCard carddata[];
	public void init() {
		init(0);	
		
	}
	public void init(int x) {
		loadCardData();	
		createCirculation(x);
		
	}

	public void addToCirculation(int i) {
		int[] temp = new int[circulation.length+1];
		System.arraycopy(circulation,0,temp,0,circulation.length);
		temp[circulation.length] = i;
		circulation = new int[temp.length];
		System.arraycopy(temp,0,circulation,0,temp.length);
		
	}
	public void createCirculation(int expansion) {
		createCirculationBetween(expansion,1,100);
		
		
	}
	
	public void createCirculationBetween(int expansion, int lowCom, int highCom) {
		circulation = new int[1];
		circulation[0] = -1;
		for (int i =10;i<CardmasterData.NUMBER_OF_CARDS;i++) {
			if (!(carddata[i]==null)) 
			if ((carddata[i].expansioncode == expansion) && (carddata[i].available)){
			//	//System.out.println("Doing circulation for card " + i);
				int cardamount = carddata[i].printed;
				if (cardamount >= lowCom && cardamount <= highCom) {
					cardamount = cardamount + (int)((double)((double)cardamount / 100.0) * cardamount);
					cardamount = cardamount / 10 + 1;
					for (int j = 0;j<cardamount;j++)
						addToCirculation(i);
				}
					
			
			}	
			
		}	
		
		
	}

	public int[] circulation;
	
	void loadCardData() {
		carddata = new CardmasterServerCard[CardmasterData.NUMBER_OF_CARDS];
		try {
			FileReader reader = new FileReader(CardmasterData.DIRECTORY + "cards.csc");
			BufferedReader in = new BufferedReader(reader);
			
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(inputLine,"#");
				int cardid = Integer.parseInt(token.nextToken());
				carddata[cardid] = new CardmasterServerCard(inputLine);
				
			////System.out.println(carddata[cardid]);
				
				
			}
			
			in.close();
			reader.close();
			
			
		}
		catch (Exception e) {
			e.printStackTrace();}	
		
		
	}
	public int createNewDeck(int colorcode,int numcards) {
		
		return createNewDeck(colorcode,numcards,-1);	
	}
	
	
	public int createDeckFromCard(int card, int quantity, String deckname) {
		FileWriter writer = null;
		PrintWriter out = null;
		
		try {
			int decknumber = getClearDeckCode();
			writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deck_" + decknumber + ".cmc");
			out = new PrintWriter(writer);
			out.println(card + " " + quantity);
			out.close();
			writer.close();
			writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deckname_" + decknumber + ".csc");
			out = new PrintWriter(writer);
			
				
			out.println(deckname);
			
			out.close();
			
			writer.close();
			return decknumber;
			
			
		}catch(IOException e) {
			e.printStackTrace();
			try {if (out != null) out.close();	} catch (Exception be) {be.printStackTrace();}
			try {if (writer != null) writer.close();	} catch (Exception be) {be.printStackTrace();}
			
			
			return -1;	
		}
		
	}
	public int newPreCopy(String deckno, String name) {
		FileWriter writer = null;
		PrintWriter out = null;
		FileReader reader = null;
		BufferedReader in = null;
		try {
			CardmasterUser user = CardmasterData.loadUser(name);
			
			File file = new File(CardmasterData.DIRECTORY + "prebuilt/deck_" + deckno + ".cmc");
			//System.out.println(file);
			if (!file.exists()) return -1;
			//System.out.println("Exists");
			int decknumber = getClearDeckCode();
			writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deck_" + decknumber + ".cmc");
			out = new PrintWriter(writer);
	
			reader = new FileReader(CardmasterData.DIRECTORY + "prebuilt/deck_" + deckno + ".cmc");
			 in = new BufferedReader(reader);
			
			String readline;
			boolean avatarset = false;
			while ((readline = in.readLine()) != null) {
				if (readline.startsWith("avatar")) {
					if (name != null) {
					
						String avatarnum = readline.substring(6);
						int avatarint = Integer.parseInt(avatarnum);
						
						if (user.readExtraA("avatar_basehead") == null && !avatarset) {
							avatarset = true;
							webrunner.cardmaster.CardmasterData.GiveBasicAvatars(name);
						}
						
						CardmasterAvatarManager.givePart(name,avatarint);
					}
				}
				else {out.println(readline);}
				
				
				
			}
			
			in.close();
			out.close();
			writer.close();
			reader.close();
			writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deckname_" + decknumber + ".csc");
			 out = new PrintWriter(writer);
	
			reader = new FileReader(CardmasterData.DIRECTORY + "prebuilt/deckname_" + deckno + ".csc");
			 in = new BufferedReader(reader);
			
			out.println(in.readLine());
			in.close();
			out.close();
			reader.close();
			writer.close();
			return decknumber;
		}catch(IOException e) {
			e.printStackTrace();
			try {if (in != null) in.close();	} catch (Exception be) {be.printStackTrace();}
			try {if (out != null) out.close();	} catch (Exception be) {be.printStackTrace();}
			try {if (reader != null) reader.close();	} catch (Exception be) {be.printStackTrace();}
			try {if (writer != null) writer.close();	} catch (Exception be) {be.printStackTrace();}
			
			
			return -1;	
		}
		
		
	}
	
	
	public int newPreCopy(String deckno) {
	
	FileWriter writer = null;
	FileReader reader = null;
	BufferedReader in = null;
	PrintWriter out = null;
		try {
			
			
			File file = new File(CardmasterData.DIRECTORY + "prebuilt/deck_" + deckno + ".cmc");
			if (!file.exists()) return -1;
			
			int decknumber = getClearDeckCode();
			writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deck_" + decknumber + ".cmc");
			out = new PrintWriter(writer);
	
			reader = new FileReader(CardmasterData.DIRECTORY + "prebuilt/deck_" + deckno + ".cmc");
			in = new BufferedReader(reader);
			
			String readline;
			while ((readline = in.readLine()) != null) {
				if (!readline.startsWith("avatar")) 
					
					out.println(readline);	
				
				
				
			}
			
			in.close();
			out.close();
			writer.close();
			reader.close();
			writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deckname_" + decknumber + ".csc");
			 out = new PrintWriter(writer);
	
			reader = new FileReader(CardmasterData.DIRECTORY + "prebuilt/deckname_" + deckno + ".csc");
			 in = new BufferedReader(reader);
			
			out.println(in.readLine());
			in.close();
			out.close();
			reader.close();
			writer.close();
			return decknumber;
		}catch(IOException e) {
			try{if (out != null) out.close();}catch(Exception be){be.printStackTrace();}
			try{if (in != null) in.close();}catch(Exception be){be.printStackTrace();}
			try{if (reader != null) reader.close();}catch(Exception be){be.printStackTrace();}
			try{if (writer != null) writer.close();}catch(Exception be){be.printStackTrace();}
			
			return -1;	
		}
		
		
	}	
	public int newPreCopy(int deckno) {
			FileWriter writer = null;
	FileReader reader = null;
	BufferedReader in = null;
	PrintWriter out = null;
		try {
			int decknumber = getClearDeckCode();
			writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deck_" + decknumber + ".cmc");
			out = new PrintWriter(writer);
	
			reader = new FileReader(CardmasterData.DIRECTORY + "prebuilt/deck_" + deckno + ".cmc");
			 in = new BufferedReader(reader);
			
			String readline;
			while ((readline = in.readLine()) != null) {
				if (!readline.startsWith("avatar")) 
				
					out.println(readline);	
				
				
				
			}
			
			in.close();
			out.close();
			writer.close();
			reader.close();
			writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deckname_" + decknumber + ".csc");
			 out = new PrintWriter(writer);
	
			reader = new FileReader(CardmasterData.DIRECTORY + "prebuilt/deckname_" + deckno + ".csc");
			 in = new BufferedReader(reader);
			
			out.println(in.readLine());
			in.close();
			out.close();
			reader.close();
			writer.close();
			return decknumber;
		}catch(IOException e) {
				try{if (out != null) out.close();}catch(Exception be){be.printStackTrace();}
			try{if (in != null) in.close();}catch(Exception be){be.printStackTrace();}
			try{if (reader != null) reader.close();}catch(Exception be){be.printStackTrace();}
			try{if (writer != null) writer.close();}catch(Exception be){be.printStackTrace();}
			
			return -1;	
		}
		
		
	}


	
	public int newPreCopy(int deckno, String name) {
			FileWriter writer = null;
	FileReader reader = null;
	BufferedReader in = null;
	PrintWriter out = null;
		try {
			CardmasterUser user = CardmasterData.loadUser(name);
			int decknumber = getClearDeckCode();
			writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deck_" + decknumber + ".cmc");
			out = new PrintWriter(writer);
	
			reader = new FileReader(CardmasterData.DIRECTORY + "prebuilt/deck_" + deckno + ".cmc");
			 in = new BufferedReader(reader);
			
			String readline;
			boolean avatarset = false;
			while ((readline = in.readLine()) != null) {
				if (readline.startsWith("avatar")) {
					if (name != null) {
					
						String avatarnum = readline.substring(6);
						int avatarint = Integer.parseInt(avatarnum);
						if (user.readExtraA("avatar_basehead") == null && !avatarset) {
							avatarset = true;
							webrunner.cardmaster.CardmasterData.GiveBasicAvatars(name);
						}
						CardmasterAvatarManager.givePart(name,avatarint);
					}
				}
				else {out.println(readline);}
				
				
				
			}
			
			in.close();
			out.close();
			writer.close();
			reader.close();
			writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deckname_" + decknumber + ".csc");
			 out = new PrintWriter(writer);
	
			reader = new FileReader(CardmasterData.DIRECTORY + "prebuilt/deckname_" + deckno + ".csc");
			 in = new BufferedReader(reader);
			
			out.println(in.readLine());
			in.close();
			out.close();
			reader.close();
			writer.close();
			return decknumber;
		}catch(IOException e) {
				try{if (out != null) out.close();}catch(Exception be){be.printStackTrace();}
			try{if (in != null) in.close();}catch(Exception be){be.printStackTrace();}
			try{if (reader != null) reader.close();}catch(Exception be){be.printStackTrace();}
			try{if (writer != null) writer.close();}catch(Exception be){be.printStackTrace();}
			
			return -1;	
		}
		
		
	}
	public int emptyDeck() {
	try {
			int decknumber = getClearDeckCode();
			File file = new File(CardmasterData.DIRECTORY + "decks/deck_" + decknumber + ".cmc");
			file.createNewFile();
			return decknumber;
		}
		catch (Exception e)	{
			return -1;	
			
		}
		
	}



	public int createBoosterBox(int expan) {
		FileWriter writer = null;
		PrintWriter out = null;
		try{	
			int decknumber = getClearDeckCode();
			writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deck_" + decknumber + ".csc");
			out = new PrintWriter(writer);
			MersenneTwisterFast random = new MersenneTwisterFast();
			createCirculationBetween(expan,41,100);
			for (int i = 0; i <90; i++) {
			
				int card = -1; int rand = -1;	
				while (card == -1) {
					rand = random.nextInt(circulation.length);
					card = circulation[rand];
				//	//System.out.println(card);
					if (card != -1) {	
						if ((!(carddata[card].available)) || ((expan != -1) && (carddata[card].expansioncode != expan))) card = -1;
						
					}		
				//	//System.out.println(colorcode " - " + card);
				}
				
				circulation[rand] = -1;
				
				
				out.println(card);
				
			
			}			
			createCirculationBetween(expan,16,40);
			for (int i = 0; i <50; i++) {
			
				int card = -1; int rand = -1;	
				while (card == -1) {
					rand = random.nextInt(circulation.length);
					card = circulation[rand];
				//	//System.out.println(card);
					if (card != -1) {	
						if ((!(carddata[card].available)) || ((expan != -1) && (carddata[card].expansioncode != expan))) card = -1;
						
					}
				//	//System.out.println(colorcode " - " + card);
				}
				
				circulation[rand] = -1;
				
				
				out.println(card);
				
			
			}				
			createCirculationBetween(expan,1,15);
			for (int i = 0; i <10; i++) {
			
				int card = -1; int rand = -1;	
				while (card == -1) {
					rand = random.nextInt(circulation.length);
					card = circulation[rand];
				//	//System.out.println(card);
					if (card != -1) {	
						if ((!(carddata[card].available)) || ((expan != -1) && (carddata[card].expansioncode != expan))) card = -1;
						
					}
				//	//System.out.println(colorcode " - " + card);
				}
				
				circulation[rand] = -1;
				
				
				out.println(card);
				
			
			}		
			out.close();
			writer.close();
			writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deckname_" + decknumber + ".csc");
			 out = new PrintWriter(writer);
		if (expan == 0)   out.println("Prime Booster Box");
			else if (expan == 1)   out.println("Clash of the Webcomics Booster Box");
		//	else if (expan == 2)   out.println("Expedition Box");
			else if (expan == 3)   out.println("Principalities Booster Box");
			else if (expan == 4)   out.println("War of the Webcomics Booster Box");
			else if (expan == 5)   out.println("Mechanical Chaos Booster Box");
		//	else if (expan == 6)   out.println("Tool Box");
			else if (expan == 7)   out.println("Forged Hammer Booster Box");
			else if (expan == 8)   out.println("Melrak's Wrath Booster Box");
			else if (expan == 9)   out.println("Dimensions Booster Box");
			else out.println("Booster Box");
			out.close();
			writer.close();
			CardmasterData.convertDeck(decknumber);
			return decknumber;	
		
		}catch(Exception e) {
			e.printStackTrace();
				try{if (out != null) out.close();}catch(Exception be){be.printStackTrace();}
				try{if (writer != null) writer.close();}catch(Exception be){be.printStackTrace();}
			
			return -1;
		}
	}


	public int createBooster(int expan) {
		FileWriter writer = null;
		PrintWriter out = null;
		try{	
			int decknumber = getClearDeckCode();
			writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deck_" + decknumber + ".csc");
			out = new PrintWriter(writer);
			MersenneTwisterFast random = new MersenneTwisterFast();
			createCirculationBetween(expan,41,100);
			for (int i = 0; i <9; i++) {
			
				int card = -1; int rand = -1;	
				while (card == -1) {
					rand = random.nextInt(circulation.length);
					card = circulation[rand];
				//	//System.out.println(card);
					if (card != -1) {	
						if ((!(carddata[card].available)) || ((expan != -1) && (carddata[card].expansioncode != expan))) card = -1;
						
					}		
				//	//System.out.println(colorcode " - " + card);
				}
				
				circulation[rand] = -1;
				
				
				out.println(card);
				
			
			}			
			createCirculationBetween(expan,16,40);
			for (int i = 0; i <5; i++) {
			
				int card = -1; int rand = -1;	
				while (card == -1) {
					rand = random.nextInt(circulation.length);
					card = circulation[rand];
				//	//System.out.println(card);
					if (card != -1) {	
						if ((!(carddata[card].available)) || ((expan != -1) && (carddata[card].expansioncode != expan))) card = -1;
						
					}
				//	//System.out.println(colorcode " - " + card);
				}
				
				circulation[rand] = -1;
				
				
				out.println(card);
				
			
			}				
			createCirculationBetween(expan,1,15);
			for (int i = 0; i <1; i++) {
			
				int card = -1; int rand = -1;	
				while (card == -1) {
					rand = random.nextInt(circulation.length);
					card = circulation[rand];
				//	//System.out.println(card);
					if (card != -1) {	
						if ((!(carddata[card].available)) || ((expan != -1) && (carddata[card].expansioncode != expan))) card = -1;
						
					}
				//	//System.out.println(colorcode " - " + card);
				}
				
				circulation[rand] = -1;
				
				
				out.print(card);
				
			
			}		
			out.close();
			writer.close();
			writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deckname_" + decknumber + ".csc");
			 out = new PrintWriter(writer);
			if (expan == 0)   out.println("Prime Booster Pack");
			else if (expan == 1)   out.println("Clash of the Webcomics Booster Pack");
		//	else if (expan == 2)   out.println("Expedition Pack");
			else if (expan == 3)   out.println("Principalities Booster Pack");
			else if (expan == 4)   out.println("War of the Webcomics Booster Pack");
			else if (expan == 5)   out.println("Mechanical Chaos Booster Pack");
		//	else if (expan == 6)   out.println("Tool Pack");
			else if (expan == 7)   out.println("Forged Hammer Booster Pack");
			else if (expan == 8)   out.println("Melrak's Wrath Booster Pack");
			else if (expan == 9)   out.println("Dimensions Booster Pack");
			
			else out.println("Booster Pack");	
			out.close();
			writer.close();
			CardmasterData.convertDeck(decknumber);
			return decknumber;	
		
		}catch(Exception e) {
			e.printStackTrace();
				try{if (out != null) out.close();}catch(Exception be){be.printStackTrace();}
				try{if (writer != null) writer.close();}catch(Exception be){be.printStackTrace();}
			
			return -1;
		}
	}

	public int createNewDeck(int colorcode, int numcards, int expan) {
		FileWriter writer = null;
		PrintWriter out = null;
		//System.out.println("CODE: " + colorcode);
		try{
		int decknumber = getClearDeckCode();
		writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deck_" + decknumber + ".csc");
		out = new PrintWriter(writer);
		MersenneTwisterFast random = new MersenneTwisterFast();
		//System.out.print("A");
		createCirculationBetween(expan,1,100);
		for (int i = 0; i <numcards; i++) {
	//	System.out.print("B");	
			int card = -1; int rand = -1;	
			while (card == -1) {
			//	System.out.print("C");
				rand = random.nextInt(circulation.length);
				card = circulation[rand];
		///	System.out.println(card);
				if (card != -1) {
					//System.out.print("D");	
					if (((colorcode != 0) && (carddata[card].colorcode != colorcode))
					||(!(carddata[card].available)) || ((expan != -1) && (carddata[card].expansioncode != expan))) card = -1;
					if (card == -1) System.out.println("d");
				}
				System.out.println(card);
//			System.out.println(colorcode " - " + card);
			}
			
			circulation[rand] = -1;
		//	System.out.print("E");
			if (i < numcards-1) out.println(card);	
			else out.print(card);
			
			
			
		}
		out.close();
		writer.close();
		CardmasterData.convertDeck(decknumber);
			writer = new FileWriter(CardmasterData.DIRECTORY + "decks/deckname_" + decknumber + ".csc");
			 out = new PrintWriter(writer);
			 if (expan == 2)  out.println("Expedition Pack");
				else if (expan == 6)  out.println("Tool Pack");
			 else out.println("Mini Pack");	
			 
			out.close();
			writer.close();
		
		return decknumber;	
		}catch(Exception e) {
		e.printStackTrace();
		
		
			try{if (out != null) out.close();}catch(Exception be){be.printStackTrace();}
			try{if (writer != null) writer.close();}catch(Exception be){be.printStackTrace();}
			
		
		//System.out.println("******EXCEPTION*********");
		return -1; }
	}	
	public synchronized int getClearDeckCode()  {
		MersenneTwisterFast random = new MersenneTwisterFast();
		int number = 1;
		while (((new File(CardmasterData.DIRECTORY + "decks/deck_" + number + ".csc")).exists())
		|| ((new File(CardmasterData.DIRECTORY + "decks/deck_" + number + ".cmc")).exists())
		
		) {
			number= random.nextInt(9999999);

		}
		
		return number;
		
		
	}
	
	
	

	
	
	
	
	
}