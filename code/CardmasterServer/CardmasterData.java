
package webrunner.cardmaster;
import java.util.Calendar;
import java.io.*;
import java.util.StringTokenizer;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.security.MessageDigest;


	
		


public class CardmasterData {
	
//		 CMC Live (cmc.mrx.ca)
//		/*
		public static String codebase = "/cmc/";
		public static String smtp = "localhost";	
		public static String host = "cmc.mrx.ca";	
		public static int GamePort = 4170;
		public static int MatchPort = 4171;
		public static String DIRECTORY = "/home/mshallow/cmcdata/";	
		public static String SITEFOLDER = "/opt/tomcat/webapps/cmc/";
		public static String urlhost = "http://cmc.mrx.ca/cmc";
		public static String fromaddress = "cardmaster@mrx.ca";	
		public static String dbname = "cardmaster";
	    public static String dbuser = "cmc";
	    public static String dbpass = "cmcpass";
	    public static String dbhost= "127.0.0.1";
	    public static int donationhandler = 1;	
	    	public static int DEFAULTLOCATION = 401;
//		*/	


		

//		 CMC dev  (emperorwebrunner.com)
		/*
		public static String codebase = "/cmc/";
		public static String smtp = "localhost";	
		public static String host = "www.emperorwebrunner.com";	
		public static int GamePort = 4170;
		public static int MatchPort = 4171;
		public static String DIRECTORY = "/home/mshallow/cmcdata/";	
		public static String SITEFOLDER = "/var/www/webapps/cmc/";
		public static String urlhost = "http://www.emperorwebrunner.com:8180/cmc";
		public static String fromaddress = "cardmaster@mrx.ca";	
		public static String dbname = "cardmaster";
	    public static String dbuser = "cmc";
	    public static String dbpass = "cmcpass";
	    public static String dbhost= "127.0.0.1";
	    public static int donationhandler = 0;	
	    	public static int DEFAULTLOCATION = 401;
		*/

	
//		 CMC Mythos (cmcbn.mrx.ca)
	/*
	 	public static String codebase = "/cmcbn/";
		public static String DIRECTORY = "/home/mshallow/mythosdata/";
		public static String SITEFOLDER = "/opt/tomcat/webapps/cmcbn/";
		public static String urlhost = "http://cmcbn.mrx.ca/cmcbn";
		public static String fromaddress = "harwood@ticats.ca";
		public static String host = "cmc.blacknova.net";
		public static String smtp = "localhost";
		public static int GamePort = 4174;
		public static int MatchPort = 4175;
			    public static String dbname = "mythos";
	    public static String dbuser = "cmc";
	    public static String dbpass = "cmcpass";
	    public static String dbhost= "127.0.0.1";
	    
	    public static int donationhandler = 0;
	    	public static int DEFAULTLOCATION = 401;
	*/
	
	
		
		// CMC Local	
		/*
			public static String codebase = "/";
			public static String SITEFOLDER = "d:/cmc/site/"; 	
			public static String DIRECTORY = "d:/cmcdata/
			public static String host = "theclacks.dyndns.org";
		public static String urlhost = "theclacks.dyndns.org";	
			public static String smtp = "localhost";		
			public static int GamePort = 4170;
			public static int MatchPort = 4171;
			public static String fromaddress = "cardmaster@mrx.ca";	
				    public static String dbname = "cardmaster";
	    public static String dbuser = "cmc";
	    public static String dbpass = "cmcpass";
	    public static String dbhost= "127.0.0.1";	
	    	public static int DEFAULTLOCATION = 401;
	    	
	    public staticint donationhandler = 0;
	
		*/
				// CMC DEV
		/*
		public static String codebase = "/";
		public static String smtp = "localhost";	
		public static String host = "cmc.redleaf.de";
		public static String urlhost = "http://cmc.redleaf.de:8001";	
		public static int GamePort = 4170;
		public static int MatchPort = 4171;
		public static String DIRECTORY = "/home/www/web2/cmcdata/";	
		public static String SITEFOLDER = "/home/www/web2/jak/jakarta-tomcat-5.0.18/webapps/ROOT";
		public static String fromaddress = "cardmaster@mrx.ca";	
		
			    public static String dbname = "usr_web2_3";
	    public static String dbuser = "web2";
	    public static String dbpass = "wAt2pNz1";
	    public static String dbhost= "127.0.0.1";
	    	public static int DEFAULTLOCATION = 401;
	    	
	    public static int donationhandler = 0;
		
			
		*/
		
		
		public static int SLEEP_INTERVAL = 1000;

		//	
		public static int NUMBER_OF_CARDS = 1100;	

		

		
	

		//	
	//public static String host = "localhost";
	//	public static String hostport = "8001";
		public static int MAXEXPAN = 5;
		public static int BOOSTCOST = 800;
		public static int NUMROOMS = 20;
	//	public static int NUMROOMMAX = 30;
	
	//	public static boolean DEBUGMODE = true;
	    public static boolean DEBUGMODE = false;
	    public static int NUMBER_OF_PARTS = 1000;
	    
	    
	    
	    

	    
	    
	    
	    
	public boolean validateDeck(int deck) {
		return true;
			
		
		//will check for more than 6 of any given card not counting mana generators.
		
	}
	
	
	
	
	
	public static void SaveEmailFor(CardmasterUser user) {
		/*
		try{
		
		String email = user.email;
		File emailfile = new File(CardmasterData.DIRECTORY + "email/" + email);
		emailfile.createNewFile();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
	}
	
	
	public static Boolean securityCheckBinary(String str, String name) {
		boolean check = securityConvertToBinary(name).equals(str);
		if (!check) {
		System.out.println("SECURITY CHECK FOR " + name + " FAILED : GOT " + str + " and looking for " + securityConvertToBinary(name));
		}
		return (check);
		
	}
	
	public static String securityConvertToBinary(String name) {
		CardmasterUser user = loadUser(name);
		if (user == null) return "XXX - USER LOAD FAILED";
		
		String fullstring = user.name + user.readExtraA("avatar_background") +  user.email  + " " +  user.verifcode + "blob";
	//	System.out.println("TEST:"+user.name + user.readExtraA("avatar_background") +  user.email  + " " +  user.verifcode + "blob");
		try {
		  byte[] intext = fullstring.getBytes();
		  StringBuffer sb = new StringBuffer();
		  MessageDigest md5 = MessageDigest.getInstance("MD5");
		  byte[] md5rslt = md5.digest(intext);
		  for( int i = 0 ; i < md5rslt.length ; i++ ) {
		    String tmpStr = "0"+Integer.toHexString( (0xff & md5rslt[i]));
		    sb.append(tmpStr.substring(tmpStr.length()-2));
		  }
		  String md5string = sb.toString();
		  return md5string;
		} catch (java.security.NoSuchAlgorithmException e) {
		  System.out.println("No such algorithm");
		  return "XXX - COULDNT LOAD ALGORITHM";
		}

		
	}
	
	
	public static String securityConvertToBinaryOld(String name) {
		char[] array = name.toCharArray();
		String binarystring = "";
		for (int i=0;i<array.length;i++) {
			String tempstring = Integer.toHexString(((int)array[i]) + 1);
			binarystring = binarystring  + tempstring + "G";
			
		}
		if (DEBUGMODE) System.out.println("Converted ("+name+") to ("+binarystring+")");
		return binarystring;
		
	}
	
	
	public static  String securityConvertFromBinaryOld(String binarystring) {
		StringTokenizer tokenizer = new StringTokenizer(binarystring, "G");
		char[] array = new char[0];
		while (tokenizer.hasMoreTokens()) {
			String binaryv = tokenizer.nextToken();
			int integerv = Integer.parseInt(binaryv,16) -1;
			char characterv = (char)integerv;
			char[] temparray = new char[array.length];
			System.arraycopy(array,0,temparray,0,array.length);
			array = new char[temparray.length + 1];
			System.arraycopy(temparray,0,array,0,temparray.length);
			array[array.length-1] = characterv;
			
			
		}
		String name = new String(array);
		if (DEBUGMODE) System.out.println("Converted  ("+binarystring+") to ("+name+")");
		return name;	
	}
	public static void convertAllDecks() {
		try{
		
		File userdir = new File(CardmasterData.DIRECTORY + "decks/");
		File[] files = userdir.listFiles();
		java.util.Arrays.sort(files);
		for (int i=0;i<files.length;i++) {
			String filename = files[i].getName();
			if (!filename.startsWith("deck_")) continue;
			String noext = filename.substring(5,filename.length()-4);
			
			//System.out.println(noext);
			int id = Integer.parseInt(noext);
			//System.out.println("Convertering " + id);
			convertDeck(id);
			}
			}
		catch (Exception e) {
			e.printStackTrace();
			}
	}
	
		public static void convertPreBuilt(String id) {
		try {
		
			File oldfile = new File(CardmasterData.DIRECTORY +"prebuilt/deck_" + id + ".csc");
			File newfile = new File(CardmasterData.DIRECTORY +"prebuilt/deck_" + id + ".cmc");
			if (!oldfile.exists()) {
				return;
			}
			if (newfile.exists() && oldfile.exists()) {
				oldfile.delete();
				return;
			}
			String inputLine;
			FileReader reader = new FileReader(oldfile);
			BufferedReader in = new BufferedReader(reader);
			int[] cards = new int[0];
			int[] cards2 = new int[NUMBER_OF_CARDS];
			for (int i=0;i<cards2.length;i++) cards2[i] = 0;
			while ((inputLine = in.readLine()) != null) {
				int cardid = Integer.parseInt(inputLine);
				if (cardid > CardmasterData.NUMBER_OF_CARDS) continue;
					int[] tempcards = new int[cards.length+1];
					System.arraycopy(cards,0,tempcards,0,cards.length);
					cards = new int[tempcards.length];
					System.arraycopy(tempcards,0,cards,0,tempcards.length);
					cards[cards.length-1] = cardid;	
				
				////System.out.println("added " + cardid + " for " + name);
				//currentcard++;
				//numcards++;	
			
		
					
			}
			reader.close();
			in.close();
			for (int i=0;i<cards.length;i++) {
				cards2[cards[i]]++;
			}
			saveDeck(cards2,id);	
			oldfile.delete();
			
		} catch (Exception e) { return; }
		
	}
	
	public static void convertDeck(int id) {
		FileReader reader = null;
		BufferedReader in = null;
		try {
		
			File oldfile = new File(CardmasterData.DIRECTORY +"decks/deck_" + id + ".csc");
			File newfile = new File(CardmasterData.DIRECTORY +"decks/deck_" + id + ".cmc");
			if (!oldfile.exists()) {
				return;
			}
			if (newfile.exists() && oldfile.exists()) {
				oldfile.delete();
				return;
			}
			String inputLine;
			reader = new FileReader(oldfile);
			in = new BufferedReader(reader);
			int[] cards = new int[0];
			int[] cards2 = new int[NUMBER_OF_CARDS];
			for (int i=0;i<cards2.length;i++) cards2[i] = 0;
			while ((inputLine = in.readLine()) != null) {
				int cardid = Integer.parseInt(inputLine);
				if (cardid > CardmasterData.NUMBER_OF_CARDS) continue;
					int[] tempcards = new int[cards.length+1];
					System.arraycopy(cards,0,tempcards,0,cards.length);
					cards = new int[tempcards.length];
					System.arraycopy(tempcards,0,cards,0,tempcards.length);
					cards[cards.length-1] = cardid;	
				
				////System.out.println("added " + cardid + " for " + name);
				//currentcard++;
				//numcards++;	
			
		
					
			}
			reader.close();
			in.close();
			for (int i=0;i<cards.length;i++) {
				cards2[cards[i]]++;
			}
			saveDeck(cards2,id);	
			oldfile.delete();
			
		} catch (Exception e) { 
			e.printStackTrace();
			if (reader != null) try{reader.close();}catch(Exception be){be.printStackTrace();}
			if (in != null) try{in.close();}catch(Exception be){be.printStackTrace();}
			
		
		return; }
		
	}
	public static String getDeckName(int deck) {
		File file = new File(	CardmasterData.DIRECTORY + "decks/deckname_" + deck + ".csc");
		if (! file.exists() ) return "Generic Deck";
		else {
			FileReader reader = null;
			BufferedReader in = null;
			try {
				reader = new FileReader(file);
				in = new BufferedReader(reader);
				String deckname =  in.readLine();
				reader.close();
				in.close();
				return deckname;
				
				
			}catch(Exception e) {
				if (reader != null) try{reader.close();}catch(Exception be){be.printStackTrace();}
			if (in != null) try{in.close();}catch(Exception be){be.printStackTrace();}
			
				
				
			return "Generic Deck";	
				
			}	
			
			
		}
	}
	
	public static void disableStore(String name) {
		CardmasterStoreLock.disableStore(name);
	}
	public static void enableStore(String name) {
		CardmasterStoreLock.enableStore(name);
	}
	public static  boolean lockStore(String name) {
		return CardmasterStoreLock.lockStore(name);
	}
	public static void unlockStore(String name) {
		 CardmasterStoreLock.unlockStore(name);
	}
	public static int[] loadDeck(int id) {
		return CardmasterDeckManagement.loadDeck(id);
	}
	/*	
	 public static void disableStore(String name) {
		try{
			File file = new File(CardmasterData.DIRECTORY +"store/exists/" + name);
			if (file.exists()) file.delete();
		} catch (Exception e) {
		}
	}
	 public static void enableStore(String name) {
		try{
			File file = new File(CardmasterData.DIRECTORY +"store/exists/" + name);
			file.createNewFile();
		} catch (Exception e) {
		}
	}

	static  public boolean lockStore(String name) {
	try{
			File file = new File(CardmasterData.DIRECTORY +"wait/store_" + name);
			if (file.exists()) return false;
			file.createNewFile();
			return true;
		} catch (Exception e) {
			return false;
		}
	

		
		
		
	}
	static  public void unlockStore(String name) {
	try{
			File file = new File(CardmasterData.DIRECTORY +"wait/store_" + name);
			if (file.exists())	file.delete();
		} catch (Exception e) {
		}
	

		
		
		
	}
	 public static int[] loadDeck(int id) {
		try {
			int[] cards = new int[0];
			File oldfile = new File(CardmasterData.DIRECTORY +"decks/deck_" + id + ".csc");
			File newfile = new File(CardmasterData.DIRECTORY +"decks/deck_" + id + ".cmc");
			if (oldfile.exists() && !newfile.exists()) {
				CardmasterData.convertDeck(id);
			}
			String inputLine;
			FileReader reader = new FileReader(CardmasterData.DIRECTORY +"decks/deck_" + id + ".cmc");
			BufferedReader in = new BufferedReader(reader);
			while ((inputLine = in.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(inputLine);
				int cardid = Integer.parseInt(tokenizer.nextToken());
				if (cardid > CardmasterData.NUMBER_OF_CARDS) continue;
				int number = 1;
				if (tokenizer.hasMoreTokens()) number = Integer.parseInt(tokenizer.nextToken());
				for (int i=0;i<number;i++) {
					int[] tempcards = new int[cards.length+1];
					System.arraycopy(cards,0,tempcards,0,cards.length);
					cards = new int[tempcards.length];
					System.arraycopy(tempcards,0,cards,0,tempcards.length);
					cards[cards.length-1] = cardid;	
					
				}
			//	System.out.println("There are " + number + " of card id " + cardid);
			}
			return cards;
		} catch (Exception e) {
			return null;
		}
			
	}
	
	*/
	
	public static void saveDeck(int cards2[], int id) {
		saveDeck(cards2,id + "");
	}
	
	public static boolean transferCard(int deck1, int deck2, int card) {
		return CardmasterDeckManagement.transferCard(deck1, deck2, card);
	}
	
	
	public static void saveDeck(int cards2[], String id) {
		CardmasterDeckManagement.saveDeck(cards2,id);
	}
	/*
	 public static void saveDeck(int cards2[], String id) {
		try {
		
		  File newfile = new File(CardmasterData.DIRECTORY +"decks/deck_" + id + ".cmc");
	 		FileWriter writer = new FileWriter(newfile);
			PrintWriter out = new PrintWriter(writer);
			
			for (int i=0;i<cards2.length;i++) {
				if (cards2[i] > 0) out.println(i + " " + cards2[i]);
			}
			writer.close();
			out.close();
		} catch (Exception e) {
		
		}
	}
	
	
	 public static boolean transferCard(int deck1, int deck2, int card)  { 
	
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
			else if (deck2 == 99999 && decktwo.countCard(card) < 25) { decktwo.addCard(card); addcard = true; } // max sold cards =50;
			
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
				else if (deck2 == 99999 && decktwo.countCard(thecard) < 25) { decktwo.addCard(thecard); addcard = true; } // max sold cards =50;
				
				
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
	*/
 	public static int findUser (String name, CardmasterUser users[]) {
 		for (int i=1;i<users.length;i++) {
 			if (users[i].name.equals(name)) return i;	
 			
 		}
 		return -1;
 		
 		
 		
 		
 	}
 	
 	public static boolean GiveBasicAvatars(String name) {
 		CardmasterUser user = loadUser(name);
 		if (user == null) return false;
 		userpatch(name,"extr",0,"avatar_background%7%0,7,%");
 		userpatch(name,"extr",0,"avatar_back%0%0,%");
 		userpatch(name,"extr",0,"avatar_baselegs%1%0,1,%");
 		userpatch(name,"extr",0,"avatar_basebody%2%0,2,%");
 		userpatch(name,"extr",0,"avatar_basehead%3%0,3,%");
 		userpatch(name,"extr",0,"avatar_shirt%5%0,5,%");
 		userpatch(name,"extr",0,"avatar_pants%17%0,17,%");
 		userpatch(name,"extr",0,"avatar_shoes%0%0,%");
 		userpatch(name,"extr",0,"avatar_front%0%0,%");
 		userpatch(name,"extr",0,"avatar_hat%0%0,%");
 		userpatch(name,"extr",0,"avatar_hair%0%0,%");
 		userpatch(name,"extr",0,"avatar_hands%0%0,%");
 		userpatch(name,"extr",0,"avatar_jacket%0%0,%");
 		return true;
 		
 	}
	public static boolean isTrading(String name) {
		try {
			File file = new File(CardmasterData.DIRECTORY + "games.csc");
			if (!(file.exists())) return false;
			FileReader reader = new FileReader(	CardmasterData.DIRECTORY + "games.csc");
			BufferedReader in = new BufferedReader(reader);
			String inputLine;
			while (((inputLine = in.readLine()) != null)) {			
				StringTokenizer tokenizer = new StringTokenizer(inputLine,"#");
				String type = tokenizer.nextToken();
				if (type.equals("TRADE")) {
					tokenizer.nextToken();
					String name1 = tokenizer.nextToken();
					String name2 = tokenizer.nextToken();
					if (name1.equals(name) || name2.equals(name)) return true;	
					
					
					
				}
			
			}
			in.close();
			reader.close();
			return false;
			
			
		} catch( Exception e) { return false; }	
		
		
	}
	
	public static boolean isAlphaNumeric(String string) {
		char str[] = string.toCharArray();
		for (int i=0;i<str.length;i++) 
			if (!Character.isJavaIdentifierPart(str[i]) && !(str[i] == ' ')) return false;
		return true;
		
		
	}
	public static CardmasterAvatarPart[] loadAvatarParts() {
		CardmasterAvatarPart[] partdata = new CardmasterAvatarPart[CardmasterData.NUMBER_OF_PARTS];
		try {
			FileReader reader = new FileReader(CardmasterData.DIRECTORY + "avatar.cmc");
			BufferedReader in = new BufferedReader(reader);
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				CardmasterAvatarPart newPart = new CardmasterAvatarPart(inputLine);
				partdata[newPart.id] = newPart;
				//System.out.println(newPart.partname + " " + newPart.filename);
			}
			reader.close();
			in.close();
			return partdata;
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		return null;
	}
		
	public static CardmasterServerCard[] loadCardData() {

		CardmasterServerCard[] carddata = new CardmasterServerCard[CardmasterData.NUMBER_OF_CARDS];
		FileReader reader =null;
		BufferedReader in = null;
		int cardid = 0;
		try {
		 	reader = new FileReader(CardmasterData.DIRECTORY + "cards.csc");
			 in = new BufferedReader(reader);
				
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(inputLine,"#");
				cardid = Integer.parseInt(token.nextToken());
				carddata[cardid] = new CardmasterServerCard(inputLine);
				
			//	//System.out.println(carddata[cardid]);
				
				
			}
			
			
			reader.close();
			
			in.close();
			
			return carddata;
			
		}
		catch (Exception e) {
			
			System.out.println("CARDID: " + cardid);
			
			if (reader != null) {
				try{
					reader.close();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			if (in != null) {
			
				try{
					in.close();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}				
				
				
			
			
			e.printStackTrace();
				return null;}	
		
		
		
	}
		
	


	public static int calcBuy(int printed) {
		int rarity = 101 - printed;;
		int price = ((int)(rarity * Math.sqrt(rarity) / 3) + 1) + 4 ;
		if (rarity >= 101) price = 3000; 		
		return price;
		
	}

	public static int givePrebuiltDeck(String name, int deckno) {
		CardmasterNewDeck newdeck = new CardmasterNewDeck();
		newdeck.loadUser(name);
		int newdeckn = newdeck.giveNewPrebuiltToUser(deckno);
		return newdeckn;
	}

	public static boolean tooold(CardmasterUser user) {
		if ((user.wins + user.losses) > 30)
		{
			return false;
		}
		Calendar rightNow = Calendar.getInstance();
		if (user.year == 0) user.year = rightNow.get(Calendar.YEAR);
		if (user.month == 0) user.month = rightNow.get(Calendar.MONTH);
		
		int yeardelta = rightNow.get(Calendar.YEAR) - user.year;
		int monthdelta = rightNow.get(Calendar.MONTH) + yeardelta*12 - user.month;
		if ((monthdelta > 2 && !user.emailverified)) return true;
		if ((user.year < 1990)) return true; // to autodelete stuff.
		else return false;
		
		
	}
	public static void deleteuser(CardmasterUser user) { // doesnt actually delete user, 
									//but saves a backup.  Just in case, you know?
		try{
		FileWriter writer = new FileWriter(CardmasterData.DIRECTORY + "delusers.csc", true); 
		PrintWriter out = new PrintWriter(writer);
	
		out.println(user);
		writer.close();
		out.close();
		for (int i=1;i<user.decks.length;i++) {
			try {
			FileReader reader = new FileReader(CardmasterData.DIRECTORY + "decks/deck_" + user.decks[i] + ".cmc");
			writer = new FileWriter(CardmasterData.DIRECTORY + "backupdecks/deck_" + user.decks[i] + ".cmc");
			BufferedReader in = new BufferedReader(reader);
			out = new PrintWriter(writer);
			String inputline;
			while ( (inputline = in.readLine()) != null) {
				out.println(inputline);	
				
				
			}
			
			File file = new File(CardmasterData.DIRECTORY + "decks/deck_" + user.decks[i] + ".cmc");
			out.close();
			in.close();
			reader.close();
			writer.close();
			file.delete();
			File userfile = new File(CardmasterData.DIRECTORY + "users/" + user.name + ".usr");
			userfile.delete();
			 } catch (Exception e) {
	    	e.printStackTrace();} 
		}
		}catch(Exception e) {e.printStackTrace();}
	}
	public static CardmasterUser loadUser(String name) {
	//	return loadUserOld(name);
		return CardmasterDatabase.LoadUserReplace(name);
	}
	
	public static CardmasterUser loadUserOld(String name) {
		System.out.println("Loading User " + name);System.out.flush();
		File file = new File(CardmasterData.DIRECTORY + "users/" + name + ".usr");
		BufferedReader in = null;
		FileChannel channel = null;
		FileLock lock = null;
		if (!file.exists()) return null;
	
		
		int timeout = 0;
		try{
		
		
		 channel = new RandomAccessFile(file, "rw").getChannel();

		lock = channel.lock();	
	
			System.out.println("LU locked " + name);System.out.flush();
		
		
	//	FileReader reader = new FileReader(userfile);
		java.io.Reader read = Channels.newReader(channel,"ISO-8859-1");
		//System.out.println("Reader created..");System.out.flush();
		 in = new BufferedReader(read);
		String inputLine = in.readLine();
		//System.out.println(inputLine);
		if (inputLine == null) {
			System.out.println("InputLine is Null");
			in.close();
			channel.close();
			lock.release();
			System.out.println("LU unlock"); return null;
		}
		if (inputLine.length() == 0) { in.close(); channel.close(); lock.release();  System.out.println("LU unlock"); return null; }
		CardmasterUser user = null;
		try{
		
		user = new CardmasterUser(inputLine);
		} catch (Exception e) {
			System.out.println("Error in LoadUser.");
			e.printStackTrace();
			in.close();
			channel.close();
			 //System.out.println("LU unlock " + name); System.out.flush();
			lock.release();
			return null;
		}
		/*
		if (tooold(user)) {
						deleteuser(user);
						in.close();
						reader.close();	
						return null;
					}
					
					
				*/	
					
		//System.out.println("loading extra data..");	System.out.flush();		
		user.ed = new ExtraData[0];		
		while ((inputLine = in.readLine()) != null) {
			if (inputLine.length() == 0) continue;
			StringTokenizer tokenizer =new StringTokenizer(inputLine,"#");
			String n = tokenizer.nextToken();
			String a = tokenizer.nextToken();
			String b = tokenizer.nextToken();
			
			ExtraData newEd = new ExtraData(n,a,b);
			
			ExtraData[] edt = new ExtraData[user.ed.length + 1];
			System.arraycopy(user.ed,0,edt,0,user.ed.length);
			edt[user.ed.length] = newEd;
			user.ed = new ExtraData[edt.length];
			System.arraycopy(edt,0,user.ed,0,edt.length);
			
			
			
		}		
		in.close();
		channel.close();
		//System.out.println("loading password..");System.out.flush();
		
		if (user.password.equals("BYTEPASSWORD")) {
			File passfile = new File(CardmasterData.DIRECTORY + "users/password_" +name+ ".bin");
			FileInputStream bin = new FileInputStream(passfile);
			user.bytepassword = new byte[(int)(passfile.length())];
			bin.read(user.bytepassword);
			bin.close();
		}
	//	System.out.println("LU unlock attempt "); System.out.flush();
		lock.release();
		System.out.println("LU finished"); System.out.flush();
		return user;
		
		}catch (IOException e) { 
		
			e.printStackTrace();
			if (channel != null) {
				try{
					channel.close();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			if (in != null) {
			
				try{
					in.close();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		
			if (lock != null) {
				try{
						lock.release();
					
				}	
				catch(Exception ex) {
					ex.printStackTrace();
				}
			
				
				
			}
		System.out.println("Error in Loaduser 2");e.printStackTrace(); return null; 
		
		
		
		}
		catch (Exception e) {
		try{
			System.out.println("Error in Loaduser Later");
			
			
			
			e.printStackTrace();
			
			
			
			if (channel != null) channel.close();
			if (in != null) in.close();
			if (lock != null) {
				lock.release();
				//System.out.println("LU unlock "); System.out.flush();
			}
		} catch(Exception be) {
			
			be.printStackTrace();
			
			if (channel != null) {
				try{
					channel.close();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			if (in != null) {
			
				try{
					in.close();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			
			if (lock != null) {
				try{
						lock.release();
					
				}	
				catch(Exception ex) {
					ex.printStackTrace();
				}
			
				
				
			}
			
			
			
				System.out.println("Error in Loaduser Error Handler");
				be.printStackTrace();
				}
		return null;
		}
//		waitfile.delete();
//		waitnamefile.delete();
	}	
	
	
	
	
	
	
	
	
	
	public static CardmasterUser loadUserOldFolder(String name) {
		System.out.println("Loading User " + name);System.out.flush();
		File file = new File(CardmasterData.DIRECTORY + "oldusers/" + name + ".usr");
		BufferedReader in = null;
		FileChannel channel = null;
		FileLock lock = null;
		if (!file.exists()) return null;
	
		
		int timeout = 0;
		try{
		
		
		 channel = new RandomAccessFile(file, "rw").getChannel();

		lock = channel.lock();	
	
			System.out.println("LU locked " + name);System.out.flush();
		
		
	//	FileReader reader = new FileReader(userfile);
		java.io.Reader read = Channels.newReader(channel,"ISO-8859-1");
		//System.out.println("Reader created..");System.out.flush();
		 in = new BufferedReader(read);
		String inputLine = in.readLine();
		//System.out.println(inputLine);
		if (inputLine == null) {
			System.out.println("InputLine is Null");
			in.close();
			channel.close();
			lock.release();
			System.out.println("LU unlock"); return null;
		}
		if (inputLine.length() == 0) { in.close(); channel.close(); lock.release();  System.out.println("LU unlock"); return null; }
		CardmasterUser user = null;
		try{
		
		user = new CardmasterUser(inputLine);
		} catch (Exception e) {
			System.out.println("Error in LoadUser.");
			e.printStackTrace();
			in.close();
			channel.close();
			 //System.out.println("LU unlock " + name); System.out.flush();
			lock.release();
			return null;
		}
		/*
		if (tooold(user)) {
						deleteuser(user);
						in.close();
						reader.close();	
						return null;
					}
					
					
				*/	
					
		//System.out.println("loading extra data..");	System.out.flush();		
		user.ed = new ExtraData[0];		
		while ((inputLine = in.readLine()) != null) {
			if (inputLine.length() == 0) continue;
			StringTokenizer tokenizer =new StringTokenizer(inputLine,"#");
			String n = tokenizer.nextToken();
			String a = tokenizer.nextToken();
			String b = tokenizer.nextToken();
			
			ExtraData newEd = new ExtraData(n,a,b);
			
			ExtraData[] edt = new ExtraData[user.ed.length + 1];
			System.arraycopy(user.ed,0,edt,0,user.ed.length);
			edt[user.ed.length] = newEd;
			user.ed = new ExtraData[edt.length];
			System.arraycopy(edt,0,user.ed,0,edt.length);
			
			
			
		}		
		in.close();
		channel.close();
		//System.out.println("loading password..");System.out.flush();
		
		if (user.password.equals("BYTEPASSWORD")) {
			File passfile = new File(CardmasterData.DIRECTORY + "users/password_" +name+ ".bin");
			FileInputStream bin = new FileInputStream(passfile);
			user.bytepassword = new byte[(int)(passfile.length())];
			bin.read(user.bytepassword);
			bin.close();
		}
	//	System.out.println("LU unlock attempt "); System.out.flush();
		lock.release();
		System.out.println("LU finished"); System.out.flush();
		return user;
		
		}catch (IOException e) { 
		
			e.printStackTrace();
			if (channel != null) {
				try{
					channel.close();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			if (in != null) {
			
				try{
					in.close();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		
			if (lock != null) {
				try{
						lock.release();
					
				}	
				catch(Exception ex) {
					ex.printStackTrace();
				}
			
				
				
			}
		System.out.println("Error in Loaduser 2");e.printStackTrace(); return null; 
		
		
		
		}
		catch (Exception e) {
		try{
			System.out.println("Error in Loaduser Later");
			
			
			
			e.printStackTrace();
			
			
			
			if (channel != null) channel.close();
			if (in != null) in.close();
			if (lock != null) {
				lock.release();
				//System.out.println("LU unlock "); System.out.flush();
			}
		} catch(Exception be) {
			
			be.printStackTrace();
			
			if (channel != null) {
				try{
					channel.close();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			if (in != null) {
			
				try{
					in.close();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			
			if (lock != null) {
				try{
						lock.release();
					
				}	
				catch(Exception ex) {
					ex.printStackTrace();
				}
			
				
				
			}
			
			
			
				System.out.println("Error in Loaduser Error Handler");
				be.printStackTrace();
				}
		return null;
		}
//		waitfile.delete();
//		waitnamefile.delete();
	}	
	
	
	
	
	
	public static void saveUserToOldFolder(CardmasterUser user) {
		if (user == null) return;
		System.out.println("Starting SAVEUSER " + user.name); System.out.flush();
		String name = user.name;
		File file = new File(CardmasterData.DIRECTORY + "oldusers/" + name + ".usr");
		
	//	System.out.println("--- save user --- " + name);System.out.flush();
		FileChannel channel = null;
		FileLock lock = null;
		//	FileWriter writer = null;
				PrintWriter out = null;
			
		
		try{
		
		channel = new FileOutputStream(file, false).getChannel();
	
	
		if (!file.exists()) file.createNewFile();
		// writer = new FileWriter(file);
		 out = new PrintWriter(Channels.newWriter(channel,"ISO-8859-1"));
			lock = channel.lock();
			
				//System.out.println("SU locked " + name);System.out.flush();
		
		String outLine = user.toString();
		out.println(outLine);
		for (int i=0;i<user.ed.length;i++) {
			if (user.ed[i].toString().length() >3) out.println(user.ed[i]);	
			//System.out.println("Line:");
		//	System.out.println(i + " : " + user.ed[i]);
		//System.out.println("Line Done!");
		}
		
		out.close();
		
		channel.close();
		
		if (user.password.equals("BYTEPASSWORD") && user.bytepassword != null) {
			File passfile = new File(CardmasterData.DIRECTORY + "users/password_" +name+ ".bin");
			FileOutputStream bout = new FileOutputStream(passfile);
			bout.write(user.bytepassword);
			bout.close();
		}
		 
		 //writer.close();	
		 
		lock.release();
	System.out.println("SU done " + name);System.out.flush();
	//	waitnamefile.delete();
	//	waitfile.delete();
		
		
		}catch (Exception e) {
		//	System.out.println("ERROR FOLLOWS");
			e.printStackTrace();
			System.out.println("ERROR IN SAVEUSER");
			try{
				try{out.close();}catch(Exception xe) {xe.printStackTrace();}
			//	try{writer.close();}catch(Exception xe) {xe.printStackTrace();}
				try{lock.release();}catch(Exception xe) {xe.printStackTrace();}
				try{channel.close();}catch(Exception xe) {xe.printStackTrace();}
				
				
				
			
			}catch(Exception eb) {
				
				
				
				
				
				System.out.println("ERROR IN SAVEUSER2");
				eb.printStackTrace();
				
				}
			
			 }
	}	
	
	
	public static void saveUser(CardmasterUser user) {
		CardmasterDatabase.SaveUserDB(user);
	}
	public static void saveUserOld(CardmasterUser user) {
		System.out.println("Starting SAVEUSER " + user.name); System.out.flush();
		String name = user.name;
		File file = new File(CardmasterData.DIRECTORY + "users/" + name + ".usr");
		
	//	System.out.println("--- save user --- " + name);System.out.flush();
		FileChannel channel = null;
		FileLock lock = null;
		//	FileWriter writer = null;
				PrintWriter out = null;
			
		
		try{
		
		channel = new FileOutputStream(file, false).getChannel();
	
	
		if (!file.exists()) file.createNewFile();
		// writer = new FileWriter(file);
		 out = new PrintWriter(Channels.newWriter(channel,"ISO-8859-1"));
			lock = channel.lock();
			
				//System.out.println("SU locked " + name);System.out.flush();
		
		String outLine = user.toString();
		out.println(outLine);
		for (int i=0;i<user.ed.length;i++) {
			if (user.ed[i].toString().length() >3) out.println(user.ed[i]);	
			//System.out.println("Line:");
		//	System.out.println(i + " : " + user.ed[i]);
		//System.out.println("Line Done!");
		}
		
		out.close();
		
		channel.close();
		
		if (user.password.equals("BYTEPASSWORD") && user.bytepassword != null) {
			File passfile = new File(CardmasterData.DIRECTORY + "users/password_" +name+ ".bin");
			FileOutputStream bout = new FileOutputStream(passfile);
			bout.write(user.bytepassword);
			bout.close();
		}
		 
		 //writer.close();	
		 
		lock.release();
	System.out.println("SU done " + name);System.out.flush();
	//	waitnamefile.delete();
	//	waitfile.delete();
		
		
		}catch (Exception e) {
		//	System.out.println("ERROR FOLLOWS");
			e.printStackTrace();
			System.out.println("ERROR IN SAVEUSER");
			try{
				try{out.close();}catch(Exception xe) {xe.printStackTrace();}
			//	try{writer.close();}catch(Exception xe) {xe.printStackTrace();}
				try{lock.release();}catch(Exception xe) {xe.printStackTrace();}
				try{channel.close();}catch(Exception xe) {xe.printStackTrace();}
				
				
				
			
			}catch(Exception eb) {
				
				
				
				
				
				System.out.println("ERROR IN SAVEUSER2");
				eb.printStackTrace();
				
				}
			
			 }
	}	
	
	
	
	public static  CardmasterUser[] loadUserData() {
		return CardmasterDatabase.LoadUserData();
	}
	public static  CardmasterUser[] loadUserDataOld() {
		
		File userdir = new File(CardmasterData.DIRECTORY + "users/");
		CardmasterUser[] users = new CardmasterUser[1];
		try {
		
			File[] files = userdir.listFiles();
			java.util.Arrays.sort(files);
					if (DEBUGMODE) System.out.println("Number of files" + files.length); 
			for (int i=0;i<files.length;i++) {

				FileLock lock = null;
			BufferedReader in = null;
	FileChannel channel = null;
				try{
					
					if (DEBUGMODE) System.out.println("File number: " + i + "/" + files.length + " (" + files[i].getName() + ")"); 
			
			
			
					//FileReader reader = new FileReader(files[i]);
					 channel = new RandomAccessFile(files[i], "rw").getChannel();
				//	System.out.println("---Loaduserdata---" + files[i]);System.out.flush();
					lock = channel.lock();	
					System.out.println("LUD Locked");
					if (DEBUGMODE) System.out.println("Does it get here?0");
					 in = new BufferedReader(Channels.newReader(channel,"ISO-8859-1"));
					String inputLine = in.readLine();
					in.close();
					channel.close();
				
					
					lock.release();
				System.out.println("LUD unlock");
					if (inputLine.length() == 0) {  continue;  }
					if (DEBUGMODE) System.out.println("Does it get here?1");
					CardmasterUser user = new CardmasterUser(inputLine);



					CardmasterUser[] tempusers = new CardmasterUser[users.length + 1];
					System.arraycopy(users,0,tempusers,0,users.length);
				//	//System.out.println("Line " + inputLine);
					tempusers[users.length] = user;
					users = new CardmasterUser[tempusers.length];
					if (DEBUGMODE) System.out.println("Does it get here?2");
					System.arraycopy(tempusers,0,users,0,tempusers.length);
					
				} catch (Exception e) { 
					e.printStackTrace();
					try {
					//	 System.out.println("LUD unlock");
						try{if (lock != null) lock.release();}catch(Exception xe){xe.printStackTrace();
						}
						
						
						try{if (in != null) in.close();}catch(Exception xe) {xe.printStackTrace();}
			//		try{writer.close();}catch(Exception xe) {xe.printStackTrace();}
						try{if (channel != null)channel.close();}catch(Exception xe) {xe.printStackTrace();}
						
						
						
						
						
						
						
					}catch(Exception be) {
						
						
						
						
						be.printStackTrace();
					}
					continue;
				
				
				}
			
				
			}	
			
		return users;	
		} catch (Exception e) { return null; }
		
		
		
	}

	public static boolean userverify(String name, int verif) {
	//	System.out.println("USER VERIFY, BITCH");
		CardmasterUser users = CardmasterData.loadUser(name);
	//	System.out.println("Attempting load user: " + name);
	//	System.out.println(users);
		if (users == null) return false;
	//	System.out.println("Checking given: " + name + "to " + users.name + " Checking given: " + verif + " to " + users.verifcode);
			
			
		if (verif == users.verifcode) {
			CardmasterData.userpatch(name,"verf",0,"x");
			CardmasterLogManager.WriteLog("User Verified: "+ name);
			return true;	
		}
		else return false;	

		
		
		
	}
	
	// new command: addtodeck card# text
	/*
	 public static boolean userpatch(String name, String command, int amount, String text) {
		if (name.length() == 0 || command.length() == 0)  return false;
			File waitfile = new File(CardmasterData.DIRECTORY +"userpatchwait");
			File wherefile = new File(CardmasterData.DIRECTORY +"userpatch.where");
		
		try{
			File file = new File(CardmasterData.DIRECTORY + "userpatch.csc");
			while (waitfile.exists()) {try{Thread.currentThread().sleep(SLEEP_INTERVAL);}catch(Exception e) {break;}
			  }
			waitfile.createNewFile();
			wherefile.createNewFile();
			
			FileWriter writer = new FileWriter(CardmasterData.DIRECTORY + "userpatch.csc", true); 
			PrintWriter out = new PrintWriter(writer);
			//out.print(users[1]);
			out.println(name + ":" + command + ":" + text + ":" + amount + ":");
			
			waitfile.delete();
			out.close();
			writer.close();
			wherefile.delete();
			return true;
	}catch(Exception e) {}
		waitfile.delete();
		wherefile.delete();
		return false;
	
	}
	*/	
	
	public static boolean userpatch(String name, String command, int amount, String text) {
		boolean returnv = false;
		for (int i=0;i<5;i++) {
			returnv = CardmasterDatabaseUserpatch.userpatch(name,command,amount,text);
			if (returnv) break;
		}
		return returnv;
	}
	 public static boolean userpatchold(String name, String command, int amount, String text) {
		if (name.length() == 0 || command.length() == 0)  return false;
		
		File file;
		FileChannel channel = null;
		FileLock lock = null;
		FileWriter writer = null;
		PrintWriter out = null; 
		

		//	System.out.println("--- userpatch --- " + name);
			System.out.flush();
		
		try {
			file = new File(CardmasterData.DIRECTORY + "userpatch.csc");
			
			if (file.exists()) if (file.length() > 60000) file = new File(CardmasterData.DIRECTORY + "userpatchtemp.csc");
		
			
		//	if (!file.exists()) file.createNewFile();
		
				channel = new FileOutputStream(file, true).getChannel();
	
			lock = channel.lock();
			//	System.out.println("UP locked");
		
		
			//writer = new FileWriter(CardmasterData.DIRECTORY + "userpatch.csc", true); 
			out = new PrintWriter(Channels.newWriter(channel,"ISO-8859-1"));
			//out.print(users[1]);
			out.println(name + ":" + command + ":" + text + ":" + amount + ":");
			out.close();
			if (channel != null)	channel.close();
			
			if (lock != null) { lock.release();   }
			
		//	writer.close();
			
			return true;
	}catch(Exception e) {e.printStackTrace();}
		try {
			if (lock != null) { lock.release(); }
 			try{if (out != null) out.close();}catch(Exception xe){xe.printStackTrace();}
			//try{if (writer != null)	writer.close();	}catch(Exception xe){xe.printStackTrace();}
			try{if (channel != null) channel.close();}catch(Exception xe){xe.printStackTrace();}
			
			return false;
		
		}
		catch (Exception be) {
			try{if (lock != null) lock.release();}catch(Exception xe){xe.printStackTrace();}
			
			try{if (out != null) out.close();}catch(Exception xe){xe.printStackTrace();}
			//try{if (writer != null)	writer.close();	}catch(Exception xe){xe.printStackTrace();}
			try{if (channel != null) channel.close();}catch(Exception xe){xe.printStackTrace();}
			
			
			be.printStackTrace();
			return false;
		}
//		System.out.println("---Got here somehow?--" + name);
//		return false;
	
	}
	
	
	
	 public static  boolean checkPassword(String name, String passwordtocheck) {
		CardmasterUser user = loadUser(name);
		if (user == null) return false;
		byte[] bytepasswordtocheck = encrypt(passwordtocheck);
		
		
		if (DEBUGMODE) System.out.println("user.bytepassword:" + new String(user.bytepassword)+"-");
		if (DEBUGMODE) System.out.println("check password:" + new String(bytepasswordtocheck )+"-");
	
		
		
		
		if ( (new String(user.bytepassword)).equals(new String(bytepasswordtocheck)) ) return true;
		if (DEBUGMODE) System.out.println("oops!");
		return false;
		
	}
	
	 public static boolean createUserBinaryPassword(CardmasterUser user) {
	
		if (user == null) return false;
		
	 	byte[] bytepassword = encrypt(user.password);
	 	if (bytepassword == null) return false;
	 	user.bytepassword = bytepassword;
	 	
	 	
	 	user.password = "BYTEPASSWORD";
	 	try{
	 	
	 	if (user.password.equals("BYTEPASSWORD") && user.bytepassword != null) {
			File passfile = new File(CardmasterData.DIRECTORY + "users/password_" +user.name+ ".bin");
			FileOutputStream bout = new FileOutputStream(passfile);
			bout.write(user.bytepassword);
			bout.close();
		}
		 }catch(Exception e) {e.printStackTrace();
		 return false;
		 }
	 	
	 	
	 	
	 	
	 	return true;
	 	
	}
	
	public static byte[] encrypt(String x) {
		try {
		
			 java.security.MessageDigest d =null;
		     d = java.security.MessageDigest.getInstance("SHA-1");
		     d.reset();
		     d.update(x.getBytes());
		     return  d.digest();
		}
		catch (Exception e) { return null;}
	}
    
}