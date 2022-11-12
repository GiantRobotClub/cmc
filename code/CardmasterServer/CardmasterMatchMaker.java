	package webrunner.cardmaster;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Calendar;
import java.nio.*;
import java.nio.channels.*;
class CardmasterMatchmakerUserInfo {
	public String name;
	public String challenges;
	private static int MAXTIMEOUT = 30;
	public int cycle = MAXTIMEOUT; 
	public String flags = "!";
	public boolean admin;
	public boolean quit = false;
	public void TimeoutCycle() {
		cycle--;
	//	System.out.println("Timeout Cycle:" + name + " : " +cycle);
		if (cycle <=0) {
				if (trade == 3) {
					quit = true;	
				}
				else {
					trade = 3;
					ResetTimeout();
					
				}
			
			
			} // QUIT me!
		 // DO NOT QUIT ME PLEASE OH NO!
	}
	public void ResetTimeout() {
		cycle = MAXTIMEOUT;
	}
	public int wins;
	public int losses;
	public int idcode = 0;
	public int trade = 0;
	public int tourneywins = 0;
	public void LoadWins() {
		if (name == null) return;
		CardmasterUser user = CardmasterData.loadUser(name);
		if (user == null) return;
		
		wins = user.wins;
		losses = user.losses;
	}
	public void BuildFlags() {
		flags = "!";
		boolean donor = false;
		boolean implementer = false;
		CardmasterUser user = CardmasterData.loadUser(name);
		if (user == null) return;
		if (user.readExtraA("admin") != null)
			if (user.readExtraA("admin").equals("yes"))
				 admin = true;
		
		if (user.readExtraA("tourneywins") != null) {
			String tourneywincount = user.readExtraA("tourneywins");
			tourneywins = Integer.parseInt(tourneywincount);
			
			
		}
		if (user.readExtraA("donor") != null) {
			if (user.readExtraA("donor").equals("yes"))
				 donor = true;
		}
		if (user.readExtraA("implementer") != null) {
			if (user.readExtraA("implementer").equals("yes"))
				 implementer = true;
		}	
		if (admin) flags +="!admin!";
		if (donor) flags +="!donor!";
		if (implementer) flags+="!imple!";
		if (tourneywins > 0) flags += "!tstart-"+tourneywins+"-tend!";
	}
	public String toString() {
		return name + "#" + wins + "#" + losses + "#" + trade + "#" + flags + "#";
	}
	CardmasterMatchmakerUserInfo(String name) {
		
		CardmasterUser user = CardmasterData.loadUser(name);
		if (user == null) name = "$NULL$";
		
		else {
			this.name = name;
			LoadWins();
			trade = 0;
		}
	}
	
}

public class CardmasterMatchMaker{
	private String[] messageto;
	private String[] messagebuffer;
	private int[] messageid;
	public int currentmessageid;
	public int currentindex;
	public static int PROTOCOLVERSION = 01;
//	public CardmasterUser[] users;
	public String[] player;
	public String[] challenges;
	public int trade[];
	public CardmasterMatchmakerUserInfo users[];
	
	int sync = 0;
	
	public String[] obstuff;
	CardmasterChatRoom[] chatroom;
	CardmasterTradeRoom[] traderoom;
	
	CardmasterMatchMaker(CardmasterChatRoom[] rooms, CardmasterTradeRoom[] trooms) {
		super();
		chatroom = rooms;
		traderoom = trooms;
		messagebuffer = new String[100];
		messageid = new int[100];
		messageto = new String[100];
		currentmessageid = 1;
		currentindex = 0;
		player = new String[0];
		challenges = new String[0];
		users = new CardmasterMatchmakerUserInfo[0];
		trade = new int[0];
//		loadUserData();
	
		
	}
	public String playlist() {
		String playlist = "";
		
		for (int i=0;i<users.length;i++) {
			try {
				playlist += users[i];

			}catch (ArrayIndexOutOfBoundsException e) {
				return playlist;
			}
			
		}
		//System.out.println("OMG PLAYLIST: " + playlist);
		return playlist;
		
		/*
		String playlist = "";
		
		for (int i=0;i<player.length && i<trade.length;i++) {
		try{
			String news;
			news = player[i] + 
			"#" + loadUser(player[i]).wins + 
			"#" + loadUser(player[i]).losses + 
			"#" + trade[i] + "#";
			playlist =  playlist + news;
			
		}catch (ArrayIndexOutOfBoundsException e) {	
			
		return playlist;
		} 
		}
		
		return playlist;
		*/
	}
	
	public void quit (String name) {
		while (sync > 0) {
			
			 try{Thread.currentThread().sleep(CardmasterData.SLEEP_INTERVAL);}catch(Exception e) {break;}
		
			//System.out.println("Waiting for sync: quit" + sync);
			}
		sync = 1;
		try{
	//	System.out.println("Quitting " + name);
		for (int i=0;i<users.length;i++) {
			
			if (users[i] == null ) continue;
			if (users[i].name == null) continue;
		//	System.out.println("Looking at.." + i + " " + users[i].name);
			if (users[i].name.equals(name) ) {
			//	System.out.println("Found " + name);
				int oldlength = users.length;
			//	System.out.println(users[i].name + users[users.length-1].name);
				users[i] = users[users.length-1];
				
			//	System.out.println(users[i].name + users[users.length-1].name);
				CardmasterMatchmakerUserInfo[] temp = new CardmasterMatchmakerUserInfo[users.length-1];
				System.arraycopy(users,0,temp,0,users.length-1);
				users = new CardmasterMatchmakerUserInfo[temp.length];
				System.arraycopy(temp,0,users,0,users.length);
				message("PLAYLIST#" + playlist(),"$all$");
			//	System.out.println(playlist());
		    	//System.out.println("oldlength: " + oldlength + " newlength: " + users.length);
				message("CHASTAT#" + name + " has quit..", "$all$");
				sync = 0;
				return;
			}
		}	
			
			
		/*
		for (int i =0; i<player.length; i++) {
			if (player[i].equals(name)) {
				player[i] = player[player.length - 1];
				challenges[i] = challenges[player.length -1];
				trade[i] = trade[trade.length -1];
				String[] temps = new String[player.length -1];
				String[] tempc = new String[player.length -1];
				int[] tempt = new int[player.length - 1];
				System.arraycopy(player,0,temps,0,player.length-1);
				System.arraycopy(challenges,0,tempc,0,player.length-1);
				System.arraycopy(trade,0,tempt,0,player.length-1);
				
				player = new String[temps.length];
				challenges = new String[temps.length];
				trade = new int[temps.length];
								
				System.arraycopy(temps,0,player,0,player.length);
				System.arraycopy(tempc,0,challenges,0,player.length);
				System.arraycopy(tempt,0,trade,0,player.length);
				
				message("PLAYLIST#" + playlist(),"$all$");
				message("CHASTAT#" + name + " has quit..", "$all$");
				
			
				
					sync = 0;
				return;
			} 	
		
		}
		*/
		} catch (Exception e) { e.printStackTrace(); sync=0; }

		sync = 0;	
	}	
	public boolean hasName (String name) {
		try {
		for (int i=0; i<users.length; i++) { 
		if (users[i] == null) continue;
			if (users[i].name == null) continue;
			if (users[i].name.equals(name))  {
				return true;
				
			}
		}
		return false;		
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean login (String name) {
		while (sync > 0) {
			 try{Thread.currentThread().sleep(CardmasterData.SLEEP_INTERVAL);}catch(Exception e) {break;}
		
		//	System.out.println("Waiting for sync: login" + sync);
		}
		sync = 2;
		//System.out.println("Attempting join " + name);
		
		CardmasterUser user = CardmasterData.loadUser(name);
		if (user == null) { System.out.println("Player null"); sync = 0; return false; }
		sync = 20004;
		
		for (int i=0;i<users.length;i++) { 
			if (users[i] == null) continue;
			if (users[i].name == null) continue;
			if (users[i].name.equals(name)) {
				
				users[i].ResetTimeout();
				users[i].BuildFlags();
					message("PLAYLIST#" + playlist(), "$all$");
				message("CHASTAT#" + name + " left for a second but is back now","$all$");
				sync =0;
				return true;
			}
		}
		sync = 20006;
		
		/*
		for (int i=0; i<player.length; i++) { 
			if (player[i].equals(name))  {
				sync = 0;
				quit(name);
				
				return false;
				
			}
		}*/
		sync = 20009;
		try {
		
		CardmasterMatchmakerUserInfo[] temp = new CardmasterMatchmakerUserInfo[users.length+1];
		System.arraycopy(users,0,temp,0,users.length);
		users = new CardmasterMatchmakerUserInfo[temp.length];
		System.arraycopy(temp,0,users,0,temp.length);
		users[users.length-1] = new CardmasterMatchmakerUserInfo(name);
		users[users.length-1].BuildFlags();
		message("PLAYLIST#" + playlist(), "$all$");
		message("CHASTAT#" + name + " has joined..","$all$");
		sync = 0;
		return true;
		} catch (Exception e) { 
		 e.printStackTrace();
		sync = 0; return false;
		}
		
		
			
	}
	
	public boolean setTrade (String name, int traden) {
		while (sync > 0) {
			 try{Thread.currentThread().sleep(CardmasterData.SLEEP_INTERVAL);}catch(Exception e) {break;}
		
		}
		sync = 3;
		int playerno = -1;
		for (int i=0;i<users.length;i++) {
			if (users[i] == null) continue;
			if (users[i].name.equals(name)) { 
				playerno = i;
			
			}	
			
			
		}
		if (playerno == -1) { sync = 0; return false; }
	    users[playerno].trade = traden;
		message("PLAYLIST#" + playlist(),"$all$");
		String tradetext = "Play Mode";
	
		if (traden == 0) tradetext = "Play Mode";
		if (traden ==1) tradetext = "Trade Mode";
		if (traden == 2) tradetext = "Chat/Idle Mode";
		if (traden == 3) tradetext = "Away Mode";
		message("CHASTAT#" + name + " has switched to " + tradetext + "#", "$all$");
		sync = 0;
		return true;
		
	}
	
	public boolean challenge (String name, String toname) {
		while (sync > 0) {
			 try{Thread.currentThread().sleep(CardmasterData.SLEEP_INTERVAL);}catch(Exception e) {break;}
		
		//	System.out.println("Waiting for sync: chal" + sync);
		}
		sync = 4;
		int playerno = -1;
		for (int i=0;i<users.length;i++) {
			if (users[i] == null) continue;
			if (name.equals(users[i].name)) { 
				playerno = i;
			
			}	
			
			
		}
		if (playerno == -1) { sync = 0; return false; }

		int player2no = -1;
		for (int i=0;i<users.length;i++) {
			if (users[i] == null) continue;
			if (toname.equals(users[i].name)) { 
				player2no = i;
			
			}	
			
			
		}		
		if (player2no == -1) {sync = 0; return false; }
		

		if (users[playerno].trade == 2 || users[playerno].trade == 3) {
			message("CHASTAT#You cannot challenge people while in Away or Chat modes#",name);
			sync = 0;
			return false;
		}
		if (users[player2no].trade == 2 || users[playerno].trade == 3) {
			message("CHASTAT#You cannot challenge people that are in Away or Chat modes#",name);
			sync = 0;
			return false;
		}
		if (users[playerno].trade != users[player2no].trade) { 
		
			message("CHASTAT#You cannot challenge people who aren't in the same mode as you#",name);
			sync = 0;
			return false;
		}
		if (users[playerno].challenges != null) {
			unchallenge(name,users[playerno].challenges); // cancel player's previous challenge.
				
				
		}
		
		users[playerno].challenges = toname;
	//	challenges[playerno] = toname;
		message("CHALFROM#" + name + "#",toname);
		
		if (! (users[player2no].challenges == null))
		
	 	if (users[player2no].challenges.equals(name) && (users[playerno].trade == users[player2no].trade)) match(name,toname, users[playerno].trade); 
		sync = 0;
		return true;	
	}
	
	int findRoom() {
		for (int i=0;i<CardmasterData.NUMROOMS;i++) {
			if (chatroom[i] != null)
			if (chatroom[i].empty && !(chatroom[i].reserve) && !(chatroom[i].dead) && !(chatroom[i].used)) {
				chatroom[i].reserve = true;
				chatroom[i].used = true;
				return i;
			}
		}	
		
		return -1;
	}

	int findTradeRoom() {
		for (int i=0;i<CardmasterData.NUMROOMS;i++) {
			if (traderoom[i].empty && !(traderoom[i].reserve) && !(traderoom[i].dead) && !(traderoom[i].used)) {
				traderoom[i].reserve = true;
				traderoom[i].used = true;
				return i;
			}
		}	
		
		return -1;
	}
	
	void match(String name, String toname, int traden) {
		sync =0;
		if (traden == 0) {
			int roomno = findRoom();
			if (roomno == -1) {
				message("CHASTAT#ERROR: Couldn't find empty room.#",toname);
				message("CHASTAT#ERROR: Couldn't find empty room.#",name);
					return;
				
			} 
			if (!chatroom[roomno].reserve) {
				message("CHASTAT#ERROR: Couldn't reserve empty room.#",toname);
				message("CHASTAT#ERROR: Couldn't reserve empty room.#",name);
				return;
			}
		
	
			message("MATCHROOM#" + roomno + "#",name);
			message("MATCHROOM#" + roomno + "#", toname);
			message("MATCHMADE#",toname);
			message("MATCHMADE#",name);
			message("CHASTAT#Playing against: " + name + "#",toname);
			message("CHASTAT#Playing against: " + toname + "#",name);
			 
		//	quit(name);
		//	quit(toname);	
			message("CHASTAT#" + name + " and " + toname + " are playing Cardmaster Conflict!#","$all$");
			CardmasterLogManager.WriteLog("Attempt Game Start: "+ name+" vs. " +toname);
		}
		
		else {
			int roomno = findTradeRoom();
			if (roomno == -1) {
				message("CHASTAT#ERROR: Couldn't find empty trading table#",toname);
				message("CHASTAT#ERROR: Couldn't find empty trading table#",name);
					return;
				}
			message("TRADEROOM#" + roomno + "#",name);
			message("TRADEROOM#" + roomno + "#", toname);
			message("MATCHMADE#",toname);
			message("MATCHMADE#",name);
			message("CHASTAT#Trading With: " + name + "#",toname);
			message("CHASTAT#Trading With: " + toname + "#",name);	
		//	quit(name);
		//	quit(toname);	
			message("CHASTAT#" + name + " and " + toname + " are trading!#","$all$");
			
			CardmasterLogManager.WriteLog("Attempt Trade Start: "+ name+" and " +toname);
			
		}

	}
	public boolean unchallenge (String name, String toname) {
		int playerno = -1;
		for (int i=0;i<users.length;i++) {
			if (users[i] == null) continue;
			if (name.equals(users[i].name)) { 
				playerno = i;
			
			}	
			
			
		}
		if (playerno == -1) {sync = 0; return false; }
		if (users[playerno].challenges == null) {sync = 0; return false; }
		if (!(users[playerno].challenges.equals(toname))) {sync = 0; return false; }
		message("UNCHALFR#" + users[playerno].challenges + "#",name);
		users[playerno].challenges = null;
		return true;	
	}
	
	public void message(String message, String name) {
		//System.out.println(message);
		
		currentindex ++;
		if (currentindex >= 100) {
			currentindex = 0;	
			
			
		}
		currentmessageid++;
	////System.out.println("In " + currentindex + " message " + currentmessageid + " : " + message);
		messagebuffer[currentindex] = message;
		messageid[currentindex] = currentmessageid;
		messageto[currentindex] = name;
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
	

	public String getToName(int id) {
		int index;
		index = -1;
	//	//System.out.println("Looking for name " + id);
		if (id > currentmessageid) return "";
		for (int i = 0;i<100;i++) {
			if (messageid[i] == id) { 
			index = i;
			//	//System.out.println("Found name in "+ i);
				}
		}
		if (index == -1) return "";
	//	//System.out.println("Returning name " + messageto[index]);
		return messageto[index];
	}	

/*
 	public void encryptPasswords() {
 		users = CardmasterData.loadUserData();
 		for (int i=0;i<users.length;i++) {
 			
 			if (users[i] != null) {
 			////System.out.println(users[i].encryptedpassword);
 			if (!(users[i].encryptedpassword)) {
 			//	//System.out.println("Encrypting password for " + users[i].name);
 			//	//System.out.print("Old password: " + users[i].password);
 				users[i].encryptPassword();	
 			//	//System.out.println("New password: " + users[i].password);
 				
 				
 				
 			}
 			}
 			
 			
 		}
 			
 		
 	}
 	*/
 	public boolean kick(String name, String target) {
 		while (sync > 0) {
 			 try{Thread.currentThread().sleep(CardmasterData.SLEEP_INTERVAL);}catch(Exception e) {break;}
		
 			//System.out.println("Waiting for sync: Kick" + sync);
 			}
 		sync = 101;
 		int playerno = -1;
 		for (int i=0;i<users.length;i++) {
 			if (users[i] == null) continue;
 			if (users[i].name == null) continue;
			if (users[i].name.equals(name)) { 
				playerno = i;
			
			}	
			
			
		}
		if (playerno == -1) {  sync=0; return false; }
		
		if (!users[playerno].admin) { sync = 0;
		message("CHASTAT#You are not an administrator#",name);
		 return false;}

 		int playerno2 = -1;
 		for (int j=0;j<users.length;j++) {
 			if (users[j].name == null) continue;
			if (users[j].name.equals(target)) { 
				playerno2 = j;
			
			}	
			
			
		}
		if (playerno2 == -1) {  sync=0;
			message("CHASTAT#Target player not found#",name);
		return false; }
		
		
		sync =0;
		message("CHASTAT#" + name + " has kicked " + target + " from the matchmaker.","$all$");
		message("CHASTAT#" + name + " has kicked you from the matchmaker.",target);
		CardmasterLogManager.WriteLog(target + " was kicked from matchmaker by " + name);
		quit(target);
		return true;
 		
 	}
	public int GetIDCode(String name) {
		while (sync > 0) {
			//System.out.println("Waiting for sync: GETID" + sync);
		 try{Thread.currentThread().sleep(CardmasterData.SLEEP_INTERVAL);}catch(Exception e) {break;}
			}
		sync = 10;
		int playerno = -1;
 		for (int i=0;i<users.length;i++) {
 			if (users[i] == null) continue;
 			if (users[i].name != null)
			if (users[i].name.equals(name)) { 
				playerno = i;
			
			}	
			
			
		}
		if (playerno == -1) {  sync=0; return 0; }
		sync = 0;
		return users[playerno].idcode;
	}
	public int NewIDCode(String name) {
		while (sync > 0) {
			 try{Thread.currentThread().sleep(CardmasterData.SLEEP_INTERVAL);}catch(Exception e) {break;}
		
			
			//System.out.println("Waiting for sync: NEWID" + sync);
			}
		sync = 11;
		int playerno = -1;
 		for (int i=0;i<users.length;i++) {
 			if (users[i] == null) continue;
 			if (users[i].name == null) continue;
			if (users[i].name.equals(name)) { 
				playerno = i;
			
			}	
	
		}
		if (playerno == -1) {  sync=0; return 0; }
	
				
		MersenneTwisterFast rand =new MersenneTwisterFast();
		users[playerno].idcode = rand.nextInt(600000);
		sync = 0;
		return users[playerno].idcode;
	}
 	public void PlayerAction(String name) {
 		while (sync > 0) {
 			
 			 try{Thread.currentThread().sleep(CardmasterData.SLEEP_INTERVAL);}catch(Exception e) {break;}
		
 			//System.out.println("Waiting for sync: PlayerAction" + sync);
 			}
		sync = 8;
 		int playerno = -1;
 		for (int i=0;i<users.length;i++) {
 			if (users[i] == null) continue;
 			if (users[i].name == null) continue;
			if (users[i].name.equals(name)) { 
				playerno = i;
			
			}	
			
			
		}
		if (playerno == -1) {  sync=0; return; }
		users[playerno].ResetTimeout();
		sync = 0;
		
 	}
 	
 	public void QuitTimeout() {
 	while (sync > 0) {
		 try{Thread.currentThread().sleep(CardmasterData.SLEEP_INTERVAL);}catch(Exception e) {break;}
		
		//System.out.println("Waiting for sync: TimeoutCycle " + sync);
		}
		CardmasterMatchmakerUserInfo[] usercopy = new CardmasterMatchmakerUserInfo[users.length];
		System.arraycopy(users,0,usercopy,0,users.length);
		
		for (int i=0;i<usercopy.length;i++) {
			if (usercopy[i] == null) continue;
 			if (usercopy[i].name == null) continue;
 			if (usercopy[i].quit) {
 				quit(usercopy[i].name);
 				
 				
 				
 				
 				
 			}
		}		 
		 	
 	}
 	
 	public  void TimeoutCycle() {
	while (sync > 0) {
		 try{Thread.currentThread().sleep(CardmasterData.SLEEP_INTERVAL);}catch(Exception e) {break;}
		
		//System.out.println("Waiting for sync: TimeoutCycle " + sync);
		}
		sync = 9;
		utimeout++;
		
		if (utimeout >= 180) {
			
			utimeout = 0;
if (webrunner.cardmaster.CardmasterData.donationhandler == 1)
{

			int[] returnvals = webrunner.cardmaster.CardmasterDonationHandler.FillAllEntries();
			CardmasterLogManager.WriteLog("Ran donation handler: Code: " + returnvals[0] + " Filled: " + returnvals[1] + " Not Filled: " + returnvals[2]);
}		
		
	}
 		
 		for (int i=0;i<users.length;i++) {
 			if (users[i] == null) continue;
 			if (users[i].name == null) continue;
			
			int playerno = i;
			users[playerno].TimeoutCycle();
		
			
			
			
			
		}
		
		sync = 0;
		
 		
 	}
	int utimeout = 0;


/*
public void loadUserPatches() {
	
	
	
	
	FileWriter writer = null;
	PrintWriter out = null;
	FileChannel channel  = null;
	FileLock lock = null;
		File file = new File(CardmasterData.DIRECTORY + "userpatch.csc");
	try{ 

	
	
	if (!(file.exists())) return;


	 channel = new RandomAccessFile(file, "rw").getChannel();
	System.out.println("--- load user patches --- ");System.out.flush();
	lock = channel.lock();	
	System.out.println("LUP locked");System.out.flush();


//	FileReader reader = new FileReader(CardmasterData.DIRECTORY + "userpatch.csc");
	BufferedReader in = new BufferedReader(Channels.newReader(channel,"ISO-8859-1"));
	System.out.println("File openened");System.out.flush();
	String inputLine;
	int numlines = 0;
	while (((inputLine = in.readLine()) != null) && numlines <= 500) {
		System.out.println("USER PATCH LINE ("+numlines+"): " + inputLine);System.out.flush();
		try {
		numlines++;
		if (inputLine.length() == 0) continue;
		StringTokenizer tokenizer = new StringTokenizer(inputLine,":");
		if (!(tokenizer.hasMoreTokens()))  { System.out.println("Continue: notoken"); continue; }
		String name = tokenizer.nextToken();
		
		String command = tokenizer.nextToken();
		String text = tokenizer.nextToken();
		int amount = Integer.parseInt(tokenizer.nextToken());
		System.out.println("Loading user");System.out.flush();
		CardmasterUser user = loadUser(name);
		System.out.println("user loaded: " + user);System.out.flush();
		
		if (user == null) { System.out.println("Continue: user=null"); continue; }
		System.out.println("Name: " + name + " Command: " + command + " text: " + text + " num: " + amount); System.out.flush();
		if (command.equals("addd")) {
			
	System.out.println("Adding deck " + amount + " for " + user.name);System.out.flush();
			
			user.addDeck(amount);
			
			CardmasterLogManager.WriteLog(name + " gained deck " + amount);	
		}
		else if (command.equals("setp")) {
	System.out.println("Setting user " + user.name + " primary deck to " + amount);System.out.flush();
			user.setPrimaryDeck(amount);
			
			CardmasterLogManager.WriteLog(name + " set primary deck " + amount);
			
		}	
		else if (command.equals("addw")) {
//	System.out.println("Adding win for " + user.name);System.out.flush();
			user.wins++;
		//	remove_game(user.name);
			CardmasterLogManager.WriteLog(name + " gains win");
		
		}
		else if (command.equals("addl")) {
//	System.out.println("Adding loss for " + user.name);System.out.flush();
			user.losses++;
			
			CardmasterLogManager.WriteLog(name + " gains loss");
			
		}
		else if (command.equals("rmd!")) {
//	System.out.println("Removing deck " + amount + " for " + user.name);System.out.flush();
		//	File testfile = new File(CardmasterData.DIRECTORY +"decks/deck_" + amount + ".csc");
		//	boolean remove = true;
		//	if (testfile.exists()) if (testfile.length() > 2) remove = false;
			user.removeDeck(amount);
			
			CardmasterLogManager.WriteLog(name + " removes deck " + amount);		
			
		}
		
		else if (command.equals("remd")) {
//	System.out.println("Removing deck " + amount + " for " + user.name);System.out.flush();
			if (CardmasterData.DEBUGMODE) System.out.println("Removing deck " + amount + " for " + user.name);
			File testfile = new File(CardmasterData.DIRECTORY +"decks/deck_" + amount + ".cmc");
			boolean remove = true;
			if (CardmasterData.DEBUGMODE) System.out.println(CardmasterData.DIRECTORY +"decks/deck_" + amount + ".cmc - " + testfile.exists() );
			if (testfile.exists()) { System.out.println(testfile.length());
				 if (testfile.length() > 2) remove = false;
				 }
			if (remove) user.removeDeck(amount);
			if (CardmasterData.DEBUGMODE && remove)  System.out.println("Fine!");
			
			CardmasterLogManager.WriteLog(name + " removes deck " + amount + "(Deck no longer exists)");	
		}



		else if (command.equals("rmdx")) { // remove deck, no check
//	System.out.println("Removing deck " + amount + " for " + user.name);System.out.flush();		
			 user.removeDeck(amount);
			 
				CardmasterLogManager.WriteLog(name + " removes deck " + amount);	
		
			
			
		}
		else if (command.equals("addp")) {
			
//	System.out.println("Adding points " + amount + " for " + user.name);System.out.flush();
			user.points += amount;
			
			CardmasterLogManager.WriteLog(name + " gains points " + amount);	
		
			
		}
		else if (command.equals("remp")) {
			
//	System.out.println("Taking Points " + amount + " for " + user.name);System.out.flush();
			user.points -= amount;
			
			CardmasterLogManager.WriteLog(name + " loses points " + amount);	
		
			
		}
		else if (command.equals("verf")) {
//	System.out.println("Verifying account for " + user.name);System.out.flush();	
			user.emailverified = true;
			CardmasterLogManager.WriteLog(name + " verified account");	
		
			
		}	
		else if (command.equals("pass")) {
//	System.out.println("Setting password for " + user.name);System.out.flush();
			user.password = text;	
			user.encryptedpassword = false;
			
			CardmasterLogManager.WriteLog(name + " password changed by admin to " + text);	
		
			
		}
			else if (command.equals("pssw")) {
//	System.out.println("Setting password for " + user.name);System.out.flush();
			user.password = text;	
			user.encryptedpassword = false;
			
			CardmasterLogManager.WriteLog(name + " original password set");	
		
			
		}
		else if (command.equals("logn")) {
	//System.out.println("Setting login time for " + user.name);System.out.flush();
			Calendar rightNow = Calendar.getInstance();
			user.month = rightNow.get(Calendar.MONTH);
			user.year = rightNow.get(Calendar.YEAR);
					
			
			
		}
		else if (command.equals("delu")) { // delete user
		
			user.month = 1;
			user.year = 1950;		
			
			
		}
		else if (command.equals("encp")) { //new Encrypt Password
			if (!( user.password.equals("BYTEPASSWORD") && user.bytepassword != null)) {
				CardmasterData.createUserBinaryPassword(user);
			}
			
		}
		else if (command.equals("encr")) { // encrypt password.
			if (!(user.encryptedpassword)) { user.encryptPassword(); }
				
			
		}
		else if (command.equals("adav")) { // add avatar part
		
		
			int partid = amount;
			CardmasterAvatarPart[] partdata = CardmasterData.loadAvatarParts();
		 	if (partid <= 0 || partid >= CardmasterData.NUMBER_OF_PARTS) continue;
		 	if (partdata[partid] == null) continue;
		 	if (partdata[partid].type.equals("xx")) continue;
			String fieldname = "avatar_"+partdata[partid].type;
			String previous = user.readExtraB(fieldname);
	 		String original = user.readExtraA(fieldname);
			if (previous.indexOf(","+partid+",") == -1) {
				user.changeExtraData(fieldname,original,previous+partid+",");	
				
			}
			CardmasterLogManager.WriteLog("Adding avatar part for " + name + ": "  + partid);	
		}
		else if (command.equals("seav")) { // set avatar part
			int partid = amount;
			String type = text;
			CardmasterAvatarPart[] partdata = CardmasterData.loadAvatarParts();
			
			if (partdata[partid] == null) continue;
			if (!partdata[partid].type.equals(type) && !partdata[partid].type.equals("xx")) continue;
			String fieldname = "avatar_"+type;
 			String previous = user.readExtraB(fieldname);
			
			if (previous.indexOf(","+partid+",") != -1) {
 				user.changeExtraData(fieldname,partid+"",previous);
 					
 			}
 			if (partid == 0) {
 				user.changeExtraData(fieldname,partid+"",previous);
 			
 			}
 			CardmasterLogManager.WriteLog("Setting avatar part for "+user.name +":" + type +" " + partid);	
		
		}
		else if (command.equals("extr")) { // modify extra data
	//System.out.println("Extra Data for "  + user.name);System.out.flush();
			StringTokenizer blah = new StringTokenizer(text,"%");
			try{
			//	System.out.println("Extra data change..");
				user.changeExtraData(blah.nextToken(),blah.nextToken(),blah.nextToken());
			
			//	System.out.println("Extra data change complete");
				
		
			
			}
			catch(java.util.NoSuchElementException e) {
				System.out.println("Tokenizer error");
				System.out.println(e);
				//
			}
			
			
		}
		
		else {
			System.out.println("Command Not Found");System.out.flush();
		}
		System.out.println("About to save user");System.out.flush();	
		CardmasterData.saveUser(user);	
		System.out.println("Done line...");	System.out.flush();
			}catch (Exception e) { System.out.println(e); System.out.println("Continue: catch"); continue;  }
			
	}
		//System.out.println("Getting out of loaduserpatch1");System.out.flush();
		if (file.exists())file.delete();	 
		lock.release();
		if (file.exists()) file.delete();
	
		
		
		System.out.println("LUP unlock");System.out.flush();
		if (out != null) out.close();
		if (writer != null )writer.close();
	
	//reader.close();
	channel.close();
	in.close();
	
	

//	saveusers();
	
	
	
	}catch(Exception e) { 
//	System.out.println("Getting out of loaduserpatch2");
		System.out.println(e); System.out.flush();
	
		if (file.exists()) file.delete();
		if (lock != null)  try{lock.release(); System.out.println("LUP unlock");System.out.flush();}catch (Exception eb) {System.out.println("uplr");}
		else { System.out.println("No unlock"); System.out.flush(); }
		if (out != null)	out.close();
		if (writer != null ) try{writer.close();}catch (Exception eb) {System.out.println("upwc");}
			if (channel != null ) try{channel.close();}catch (Exception eb) {System.out.println("upcc");}
		
	
		if (file.exists()) System.out.println("INSIDE THE CATCH OMG STILL EXISTS");
		return;
	}
	try {
	
		if (file.exists()) file.delete();
	//	System.out.println("Getting out of loaduserpatch3");
		if (lock != null)   {lock.release(); System.out.println("LUP unlock");System.out.flush();}
		else { System.out.println("No unlock"); System.out.flush(); }
		if (out != null) out.close();
 		if (writer != null ) try{writer.close();}catch (Exception eb) {System.out.println(eb); System.out.flush(); }
		if (channel != null ) try{channel.close();}catch (Exception eb) {System.out.println(eb); System.out.flush();  }
		
	}
	catch (Exception e) { System.out.println("Oh noses!"); System.out.println(e); System.out.flush(); }
	
	

		if (file.exists()) System.out.println("AT END OMG STILL EXISTS");
		
}

*/

	

	public CardmasterUser loadUser(String name) {
		return CardmasterData.loadUser(name);
	}
	/*
	public void saveusers_ind() {
		
		for(int i=1;i<users.length;i++) {
			
			File waitfile = new File(CardmasterData.DIRECTORY + "wait/" + users[i].name");
			try {
				while (waitfile.exists()) {};
				waitfile.createNewFile();
				FileWriter writer = new FileWriter(CardmasterData.DIRECTORY + "users/" + users[i].name + ".usr"); 
				PrintWriter out = new PrintWriter(writer);	
				out.println(users[i]);
				
				out.close();
				waitfile.delete();
				
			} 
			catch (Exception e) { waitfile.delete(); }	
			
			
			
			
		}
		
		
		
		
	}
	
	public boolean saveusers() {
		makeBackup_ind();
		saveusers_ind();
		return true;
	}
	
		
	public boolean saveusers() {
		makeBackup();
		File waitfile = new File(CardmasterData.DIRECTORY +"userwait");

		try{
			while (waitfile.exists()) {};
			waitfile.createNewFile();
		//	System.out.println("Writing users..");
			FileWriter writer = new FileWriter(CardmasterData.DIRECTORY + "users.csc"); 
			PrintWriter out = new PrintWriter(writer);
			out.print(users[1]);
			//System.out.println(users[1]);
			for (int i =2;i<users.length;i++) {
				out.print(System.getProperty("line.separator") + users[i]);
				
				
			}
			out.close();
			waitfile.delete();
			return true;
			
	}catch(Exception e) {}
		waitfile.delete(); return false;
	}
*/

}
	