package webrunner.cardmaster;
import java.net.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.StringTokenizer;

import java.awt.event.*; 
public class CardmasterServerThread extends Thread {
   int messagetag = 1;
   int threadnum;
   boolean pingreply;
   int checkping = 0;
   Timer timer;
   Timer pingtimer;
	boolean die = false;
   String inputLine = "";
   private Socket socket = null;
   CardmasterChatRoom chatroom[];
   CardmasterTradeRoom traderoom[];
   CardmasterChatRoom playerroom;
   CardmasterTradeRoom traderroom;
   boolean observer = false;
   boolean gameplayer = false;
   boolean trader = false;
   boolean notstart = true;
   PrintWriter out;
   BufferedReader in;
   int idcode =0;
   String name;
   String observername = "";
   public int roomnumber;
   public CardmasterServerThread(Socket socket, CardmasterChatRoom chatroom[], CardmasterTradeRoom traderoom[], int threadnum) {
   		super("CardmasterServerThread");
   		this.chatroom = chatroom;
   		this.traderoom = traderoom;
		this.socket = socket;
		this.threadnum = threadnum;
		//System.out.println("Thread " +  threadnum + " created!!");
   }   	
   	
   public void run() {
   	
   	pingreply = true;
   try {
	     out = new PrintWriter(socket.getOutputStream(), true);
	     in = new BufferedReader(
				    new InputStreamReader(
				    socket.getInputStream()));
	

			timer = new Timer();
			timer.schedule(new threadtimer(),5,100);
			pingtimer = new Timer();
			pingtimer.schedule(new timerping(),0,10000);
			
		//	if (inputLine.equals("quit the game")) break;
			
			
			
			
	    
//	    out.close();
//	    in.close();
//	    socket.close();

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
   	
   	
   	boolean joinNumberedTrade(String name, int roomno) {
   		CardmasterTradeRoom room = traderoom[roomno];
		if (room.name1 == null) {
			room.name1 = name;
			traderroom = room;
			this.name = name;
			return true;
		
		}
		else if (room.name1.equals("")) {
			room.name1 = name;
			traderroom = room;
			this.name = name;
			return true;
			
		} else if (room.name2 == null) {
			room.name2 = name;
			traderroom = room;
			this.name = name;
			room.empty = false;
			room.opfound(roomno);
			notstart = false;
			return true;
			
		}
		else if (room.name2.equals("")) {
			room.name2 = name;
			traderroom = room;
			this.name = name;
			room.empty = false;
			room.opfound(roomno);
			notstart = false;
			return true;			
			
			
		} else {
			return false;
			
			
			
		}   		
   		
   	}
   	

	boolean joinNumberedRoom(String name, int roomno) {
		CardmasterChatRoom room = chatroom[roomno];
		if (room.name1 == null) {
			room.name1 = name;
			playerroom = room;
			this.name = name;
			return true;
		
		}
		else if (room.name1.equals("") || room.name1.equals(name)) {
			room.name1 = name;
			playerroom = room;
			this.name = name;
			return true;
			
		} else if (room.name2 == null) {
			room.name2 = name;
			playerroom = room;
			this.name = name;
			room.empty = false;
			room.opfound(roomno);
			notstart = false;
			return true;
			
		}
		else if (room.name2.equals("") || room.name2.equals(name)) {
			room.name2 = name;
			playerroom = room;
			this.name = name;
			room.empty = false;
			room.opfound(roomno);
			notstart = false;
			return true;			
			
			
		} else {
			return false;
			
			
			
		}
		
	}

 	
 	void realthreaddie() {
 		// erase yourself from existance.
		if ((gameplayer) && notstart) {
			System.out.println("Game not started..");
			if (chatroom[roomnumber] != null) {
				chatroom[roomnumber].name1 = "";
				chatroom[roomnumber].name2 = "";
				
				chatroom[roomnumber].dead = true;	
				
				System.out.println("ROOM IS DEAD");
				
			}
			
			
			
		} else if ((gameplayer) && !notstart) {
			if (chatroom[roomnumber] != null) {
				int i = roomnumber;
					
					if (chatroom[i].name1.equals(name)) chatroom[i].dead = true;
					if (chatroom[i].name2.equals(name)) chatroom[i].dead = true;
	
					//if (chatroom[i].name1.equals(name)) chatroom[i].name1 = "";
					//if (chatroom[i].name2.equals(name))	chatroom[i].name2 = "";
				
						
				
					
				System.out.println("ROOM IS DEAD 2");
			
				
			}
		
		}
		if (trader && !notstart) {
			
			if (traderoom[roomnumber] != null) {
				traderoom[roomnumber].ended = true;
				if (traderoom[roomnumber].name1.equals(name)) traderoom[roomnumber].name1 = "";
				if (traderoom[roomnumber].name2.equals(name)) traderoom[roomnumber].name2 = "";
			
			
				traderoom[roomnumber].dead = true;
				
				
			}	
			
			
			
		}
		if (timer != null)
		timer.cancel();
		if (pingtimer != null)
		pingtimer.cancel(); 		
 		
 		
 	}
	void threaddie() {
		realthreaddie();
	}

	class timerping extends TimerTask   {
		
		
		 public void run() {
		 	try{
		 		if (die) { System.out.println("DIE"); threaddie(); this.cancel(); return; }
		 	if (pingreply) checkping = 0;
		 	if (pingreply == false && checkping >= 6) {
		 		//System.out.println(threadnum + " No reply.. closing.");
		    	if (observer && playerroom != null) playerroom.message("CHA#ACTION#" + name + " is no longer observing.");
		   
		    	if (gameplayer && playerroom != null && !playerroom.ended && !playerroom.dead) playerroom.giveup(name);
			 	//if (trader && traderoom != null && !traderroom.ended && !traderoom.dead) traderoom.leave(name);
			 	else { System.out.println("ERRORX"); threaddie(); out.close(); in.close(); socket.close(); if (traderroom != null) {traderroom.ended = true;
			 	
			 	System.out.println("Traderroom.ended: " + traderroom.ended);} }	
		 	}
		 	pingreply = false;
		 	checkping++;
		 	if (checkping >= 7) checkping = 0; 
		 	out.println("PNG#");
		 	//System.out.println(threadnum + " " + name + " Ping...");
		 	
		 	
		 	
		 	
		 	if (playerroom != null)
		 	if (gameplayer && playerroom.GetIDCode(name) != idcode && playerroom.GetIDCode(name) != 0 && idcode != 0) {
				out.println("MES#Disconnecting...#");
				out.close();
				in.close();
				socket.close();
				if (timer != null)
				timer.cancel();
				if (pingtimer != null)
				pingtimer.cancel(); 
				return;	
				
				
			}
		 	
		 	

		 } catch (Exception e) {
		 	e.printStackTrace();
		 	threaddie();
		 	
		 	
		 	
		 	}
		 
	}
		 
	}

	class threadtimer extends TimerTask   {
		
		 public void run() 
    	{    try {
    		    String  outputLine;
			if (playerroom != null && playerroom.dead) {
				out.println("MES#Game Closed#");
				out.println("VICTORYBY#" + playerroom.duelendedbycard + "#" + playerroom.duelendedbytype + "#");
				out.println("WIN#" + playerroom.globalwinner + "#" + playerroom.globalwon + "#" + playerroom.globalloser + "#");
				out.close();
				in.close();
				socket.close();	
				System.out.println("DEAD");
				threaddie(); return;

				
			}
			
		

			if (in.ready()) {
				
				inputLine = in.readLine();
				//System.out.println("Recieved from thread " + threadnum + " : " + inputLine );
				StringTokenizer tokenizer = new StringTokenizer(inputLine);
				if (tokenizer.hasMoreTokens()) {
					String command = tokenizer.nextToken("#");
				//	pingreply = true;
					if (playerroom != null) playerroom.timeout = 1000;
					if (traderroom != null) traderroom.timeout = 1000;
					if (command.equals("JR")) {
						
						String name = tokenizer.nextToken("#");
						//pingreply = true;
						int roomno = Integer.parseInt(tokenizer.nextToken());
						if (roomno <0 || roomno >= CardmasterData.NUMROOMS) {
								out.println("CHA#SYSTEM#ERROR: ROOM CHOSEN OOB#");
								out.println("ONF#");
								
								threaddie();
							 	out.close();
						    	in.close();
						    	socket.close();
						    	return;
						}
						if (!chatroom[roomno].empty) {
							messagetag = chatroom[roomno].messageTag();
						}
						if (!chatroom[roomno].empty && !name.equals(chatroom[roomno].name1)&& !name.equals(chatroom[roomno].name2)) {
								out.println("CHA#SYSTEM#ERROR: ROOM CHOSEN NOT EMPTY#");
								//System.out.println("NOT EMPTY");
								out.println("ONF#");
								threaddie();
							 	out.close();
						    	in.close();
						    	socket.close();
						    	return;
						}
						if (!chatroom[roomno].reserve) {
							
								out.println("CHA#SYSTEM#ERROR: ROOM CHOSEN NOT RESERVED#");
									//System.out.println("NOT RESERVEd");
								out.println("ONF#");
								threaddie();
							 	out.close();
						    	in.close();
						    	socket.close();
						    	return;								
							
							
						}
						roomnumber =roomno;
						if (chatroom[roomno].gamebegun) {
							chatroom[roomno].message("CHA#ACTION#" + name + " is attempting to rejoin the game");
							out.println("OPF#" + chatroom[roomno].name1);
							out.println("OPF#" + chatroom[roomno].name2);
							out.println("REJ#");
							
							chatroom[roomno].MessageSTN();
							out.println(chatroom[roomno].STP());
							out.println(chatroom[roomno].GRA(name));
						
							
							chatroom[roomno].MessageSTC();
							
							out.println(chatroom[roomno].LOC());
						
						}
						if (!joinNumberedRoom(name,roomno)) {
								
								out.println("CHA#SYSTEM#ERROR: ROOM CHOSEN COULD NOT BE JOINED#");
								//System.out.println("NOT JOINED");
								out.println("ONF#");
								threaddie();
							 	out.close();
						    	in.close();
						    	socket.close();
						    	return;	
							
						}
						
						gameplayer = true;
						idcode = chatroom[roomno].NewIDCode(name);
					//	System.out.println(idcode);
						chatroom[roomno].timeout = 1000;
						
					}
				
					else if (command.equals("TR")) {
						trader = true;
						String name = tokenizer.nextToken("#");
						//pingreply = true;
						int roomno = Integer.parseInt(tokenizer.nextToken());
						if (!traderoom[roomno].empty) {
								out.println("TRCHA#SYSTEM#ERROR: ROOM CHOSEN NOT EMPTY#");
								//System.out.println("NOT EMPTY");
								out.println("ONF#");
								threaddie();
							 	out.close();
						    	in.close();
						    	socket.close();
						    	return;
						}
						if (!traderoom[roomno].reserve) {
							
								out.println("TRCHA#SYSTEM#ERROR: ROOM CHOSEN NOT RESERVED#");
									//System.out.println("NOT RESERVEd");
								out.println("ONF#");
								threaddie();
							 	out.close();
						    	in.close();
						    	socket.close();
						    	return;								
							
							
						}
						roomnumber =roomno;
						if (!joinNumberedTrade(name,roomno)) {
								
								out.println("TRCHA#SYSTEM#ERROR: ROOM CHOSEN COULD NOT BE JOINED#");
								//System.out.println("NOT JOINED");
								threaddie();
							 	out.close();
						    	in.close();
						    	socket.close();
						    	return;	
							
						}
						traderroom.timeout = 1000;
						
						
					}
				

					else if (command.equals("OB")) { // observer
						
						observer = true; //Observator.
						int room = Integer.parseInt(tokenizer.nextToken("#"));
						
						playerroom = chatroom[room];
						if (!playerroom.dead && !playerroom.empty) {
							
						out.println("OBN#" + playerroom.name1 + "#" + playerroom.name2 + "#");
							name = tokenizer.nextToken();
							
							messagetag = playerroom.messageTag();
							playerroom.message("CHA#ACTION#" + name + " is observing this game.");
							
							observername = name;
							playerroom.MessageSTN();
							out.println(playerroom.STP());
							playerroom.MessageSTC();
							
							out.println(playerroom.LOC());					
						}
						else {
							out.println("ONF#");
							
							threaddie();
							out.close();
							in.close();
							socket.close();	
							
						}
					}
					else if (command.equals("DS") && gameplayer) {
						playerroom.discard_card(name,Integer.parseInt(tokenizer.nextToken("#")));
						
						
					}
					
					else if (command.equals("NP") && gameplayer) {
						
						playerroom.nextPhase(name);	
						
					}
					else if (command.equals("PM") && gameplayer) { // Play Monster
						int cardid = Integer.parseInt(tokenizer.nextToken("#"));
						String player = name;
						int slot = Integer.parseInt(tokenizer.nextToken("#"));
						int handslot = Integer.parseInt(tokenizer.nextToken("#"));
						int returned = playerroom.play_monster(name,cardid,slot,handslot);
						//System.out.println("Returned: " + returned);
						if (returned == 1) {
							playerroom.MessageSTN();
							playerroom.message(playerroom.STP());
							playerroom.MessageSTC();
							playerroom.message(playerroom.LOC());
							playerroom.message(playerroom.HAND(name),name);
						//	playerroom.message("MES#Monster played...");
						}
							
						
					}
					else if (command.equals("PD") && gameplayer) {
	//	System.out.println(inputLine);
						//out.println("PD#" + selectedcard.cardid + "#" +playert + "#" + card.typecode + "#"+ i + "#" + handid + "#");
							int cardid = Integer.parseInt(tokenizer.nextToken("#"));
							String player = name;
							int tplayer = Integer.parseInt(tokenizer.nextToken("#"));
							String type = tokenizer.nextToken();
								int slot = Integer.parseInt(tokenizer.nextToken("#"));
						int handslot = Integer.parseInt(tokenizer.nextToken("#"));
							int returned = playerroom.play_modifier(name,cardid,slot,tplayer,type,handslot);
						//System.out.println("Returned: " + returned);
						if (returned == 1) {
							playerroom.MessageSTN();
							playerroom.message(playerroom.STP());
							playerroom.MessageSTC();
							playerroom.message(playerroom.LOC());
							playerroom.message(playerroom.HAND(name),name);
						//	playerroom.message("MES#Monster played...");
						//String playername, int cardid, int slot, int tplayer, String ttype, int handslo
						} else {
						playerroom.message("MES#Error Code " + returned +"#",name);
						}
					}

					else if (command.equals("PE") && gameplayer) { // Play Effect
						int cardid = Integer.parseInt(tokenizer.nextToken("#"));
						String player = name;
						int slot = Integer.parseInt(tokenizer.nextToken("#"));
						int handslot = Integer.parseInt(tokenizer.nextToken("#"));
						if (playerroom.play_effect(name,cardid,slot,handslot) == 1) {
							playerroom.MessageSTN();
							playerroom.message(playerroom.STP());
							playerroom.MessageSTC();
							playerroom.message(playerroom.LOC());
							playerroom.message(playerroom.HAND(name),name);
						//	playerroom.message("MES#Effect played...");
						}
							
						
					}
					else if (command.equals("SC") && gameplayer) {
						String type = tokenizer.nextToken("#");
						int number = Integer.parseInt(tokenizer.nextToken("#"));
						playerroom.sac_card(name,type,number);
						playerroom.MessageSTC();
						playerroom.MessageSTN();
						playerroom.message(playerroom.LOC());
						
					}
						
					else if (command.equals("CM") && (gameplayer || observer)) {
						try{
							
							String player = tokenizer.nextToken("#");
							if (name.equals(player))
							if (observer)
							playerroom.say(name + "(observer)",tokenizer.nextToken("#"));
							else
							playerroom.say(name,tokenizer.nextToken("#"));
						}
						catch(java.util.NoSuchElementException e) {
							System.err.println("Command CM got with invalid number of arguments: " + inputLine);
						}
						
					}
					
					else if (command.equals("CC")) {
						String code = tokenizer.nextToken("#");
						String name = tokenizer.nextToken("#");
//						String shouldname = CardmasterData.securityConvertFromBinary(code);
						
						if (webrunner.cardmaster.CardmasterData.DEBUGMODE) {
							System.out.println("Received authentication data: ("+name+")("+code+")");						
						}
						
						if  (!CardmasterData.securityCheckBinary(code,name)) {
							out.println("CHA#SYSTEM#AUTHENTICATION ERROR#");
							threaddie();
							 	out.close();
						    	in.close();
						    	socket.close();
						}
						else {
							out.println("CHA#SYSTEM#AUTHENTICATION COMPLETE#");
							
						}
					}
					
					
					else if (command.equals("PN")) {
						pingreply = true;
						//System.out.println(threadnum + " Pong...");
						
					}				
				
					else if (command.equals("WH") && (gameplayer || observer)) { // request for unknown card data
						int cardid = Integer.parseInt(tokenizer.nextToken("#"));
						playerroom.return_card_info(cardid,name);
						
					}
					else if (command.equals("WH") && (trader)) { // request for unknown card data
						int cardid = Integer.parseInt(tokenizer.nextToken("#"));
						traderroom.return_card_info(cardid);
						
					}
					else if (command.equals("DB") &&  gameplayer) {
							playerroom.MessageSTN();
							out.println(playerroom.STP());
							playerroom.MessageSTC();
							out.println(playerroom.LOC());
							playerroom.message(playerroom.HAND(name),name);
						
						
						
					}
					else if (command.equals("BP") && gameplayer) { // Ability on player
						String slottype = tokenizer.nextToken("#");
						int slot = Integer.parseInt(tokenizer.nextToken("#"));
						int target = Integer.parseInt(tokenizer.nextToken("#"));
						String player= name;
						playerroom.do_ability_player(player,slot,slottype,target);
						
					}
					
					else if (command.equals("AR") && gameplayer) { // Ability resolve
						
						
						int set = Integer.parseInt(tokenizer.nextToken("#"));
						boolean setbool = false;
						if (set == 1) setbool = true;
						else setbool = false;
						playerroom.abilityResolve(name,setbool);
						
					}					

					else if (command.equals("BG") && gameplayer) { // Ability on graveyard
						String slottype = tokenizer.nextToken("#");
						int slot = Integer.parseInt(tokenizer.nextToken("#"));
						int cardid = Integer.parseInt(tokenizer.nextToken("#"));
						String player= name;
						playerroom.do_ability_grave(player,slot,slottype,cardid);
						
					}
					else if (command.equals("AT") && gameplayer) { // Attack General
						int slot = Integer.parseInt(tokenizer.nextToken("#"));
						String player = name;
						playerroom.attack_command(name,slot,0,1);
							
						
					}
					else if (command.equals("DA") && gameplayer) { // ability modifier
					
					//	out.println("DA#" + modtype + "#" + i + "#g#"+0+"#"+0+"#"+((CardmasterCard)(e.getSource())).cardid+"#");
						String modtype = tokenizer.nextToken("#");
						int slot = Integer.parseInt(tokenizer.nextToken("#"));
						String abiltype = tokenizer.nextToken("#");
						int tplayer = Integer.parseInt(tokenizer.nextToken("#"));
						String ttype = tokenizer.nextToken("#");
						int tslot = Integer.parseInt(tokenizer.nextToken("#"));
						
						
						
						
						String player = name;
						int returned = playerroom.do_modifier_ability(player,  slot,  modtype,  tplayer,  ttype,  tslot,  abiltype);
							if (returned <= 0) { playerroom.message("MES#Error Code " + returned +"#",name); }
						
					
					}
					else if (command.equals("AM") && gameplayer) { // Attack Monster
						int slot = Integer.parseInt(tokenizer.nextToken("#"));
						int slot2 = Integer.parseInt(tokenizer.nextToken("#"));
						String player = name;
						
						playerroom.attack_command(name,slot,slot2,2);
							
						
					}				
					else if (command.equals("DF") && gameplayer) { // Defend
						int slot = Integer.parseInt(tokenizer.nextToken("#"));
						int slot2 = Integer.parseInt(tokenizer.nextToken("#"));
						String player = name;
						
						playerroom.attack_command(name,slot,slot2,3);
							
						
					}
				
					else if (command.equals("BS") && gameplayer) { // Ability on Slot
						String slottype = tokenizer.nextToken("#");
						int slot = Integer.parseInt(tokenizer.nextToken("#"));
						int slot2 = Integer.parseInt(tokenizer.nextToken("#"));
						String player= name;
						
						playerroom.do_ability_on_slot(player,slot,slottype,slot2);
							
						
					}				
					else if (command.equals("SS") && gameplayer) { // Spell on Monster
						int play = Integer.parseInt(tokenizer.nextToken("#"));
						int handslot = Integer.parseInt(tokenizer.nextToken("#"));
						String player= name;
						
						playerroom.cast_spell_on_slot(player,  handslot, play);
						
					}
					else if (command.equals("SP") && gameplayer) { // Spell on Monster
						int play = Integer.parseInt(tokenizer.nextToken("#"));
						int handslot = Integer.parseInt(tokenizer.nextToken("#"));
						String player= name;
						
						playerroom.cast_spell_on_player(player,  handslot , play);
						
					}
					else if (command.equals("SA") && gameplayer) { // Spell on Monster

						int handslot = Integer.parseInt(tokenizer.nextToken("#"));
						String player= name;
						
						playerroom.cast_spell_auto(player, handslot);
						
					}
					else if (command.equals("SG") && gameplayer) { // Spell on Monster

						int handslot = Integer.parseInt(tokenizer.nextToken("#"));
						int cardid = Integer.parseInt(tokenizer.nextToken("#"));
						
						String player= name;
						
						playerroom.cast_spell_grave(player, handslot,cardid);
					}
					else if (command.equals("SM") && gameplayer) { // Spell on Monster
						int tplayer = Integer.parseInt(tokenizer.nextToken("#"));
						int slot = Integer.parseInt(tokenizer.nextToken("#"));
						int handslot = Integer.parseInt(tokenizer.nextToken("#"));
						String player= name;
						
						playerroom.cast_spell_on_monster(player, handslot, tplayer, slot);
						
					}				
					else if (command.equals("SE") && gameplayer) { // Spell on Monster
						int tplayer = Integer.parseInt(tokenizer.nextToken("#"));
						int slot = Integer.parseInt(tokenizer.nextToken("#"));
						int handslot = Integer.parseInt(tokenizer.nextToken("#"));
						String player= name;
						
						playerroom.cast_spell_on_effect(player, handslot, tplayer, slot);
						
					}
					else if (command.equals("BM") && gameplayer) { // Ability on Monster
						String slottype = tokenizer.nextToken("#");
						int slot = Integer.parseInt(tokenizer.nextToken("#"));
						int tplayer = Integer.parseInt(tokenizer.nextToken("#"));
						int slot2 = Integer.parseInt(tokenizer.nextToken("#"));
						String player= name;
						
						playerroom.do_ability_on_monster(player,slot,slottype,tplayer, slot2);
							
						
					}				
					else if (command.equals("BE") && gameplayer) { // Ability on Monster
						String slottype = tokenizer.nextToken("#");
						int slot = Integer.parseInt(tokenizer.nextToken("#"));
						int tplayer = Integer.parseInt(tokenizer.nextToken("#"));
						int slot2 = Integer.parseInt(tokenizer.nextToken("#"));
						String player= name;
						
						playerroom.do_ability_on_effect(player,slot,slottype,tplayer, slot2);
							
						
					}
					else if (command.equals("BA") && gameplayer) { // Ability on auto
						String slottype = tokenizer.nextToken("#");
						int slot = Integer.parseInt(tokenizer.nextToken("#"));
						String player= name;
						
						playerroom.do_ability_auto(player,slot,slottype);
							
						
					}
				else if (command.equals("LO") && gameplayer) { // Ability on auto
						
						int handslot = Integer.parseInt(tokenizer.nextToken("#"));
					//	int cardid = Integer.parseInt(tokenizer.nextToken("#"));
						String player= name;
						
					 playerroom.play_location(player,handslot);
							
						
					}				
					else if (command.equals("DSWITCH") && trader)	 {
						
						int deckid = Integer.parseInt(tokenizer.nextToken("#"));
						
						traderroom.switch_deck(name,deckid);
						//out.println(traderroom.deckList(name));
						out.println(traderroom.deckText(name));
						out.println(traderroom.tables());	
						
					}				
					else if (command.equals("TRADER") && trader)	 {
						
						int ready = Integer.parseInt(tokenizer.nextToken("#"));
						boolean red;
						if (ready ==1) red = true;
						else red = false;
						traderroom.setReady(name,red);
						//out.println(traderroom.deckList(name));
						//out.println(traderroom.deckText(name));
						out.println(traderroom.tables());				
						
					}
					else if (command.equals("TRADEL") && trader)	 {
						
						int ready = Integer.parseInt(tokenizer.nextToken("#"));
						boolean red;
						if (ready ==1) red = true;
						else red = false;
						traderroom.setUserLock(name,red);
					//	out.println(traderroom.deckList(name));
					//	out.println(traderroom.deckText(name));
					traderroom.message(traderroom.tables());			
						
						
					}	
					else if (command.equals("ADDC") && trader)	 {
						
						int cardid = Integer.parseInt(tokenizer.nextToken("#"));
						
						traderroom.addTable(name,cardid);
					//	out.println(traderroom.deckList(name));
						out.println(traderroom.deckText(name));
						traderroom.message(traderroom.tables());	
						
						
					}					
					else if (command.equals("REMC") && trader)	 {
						
						int cardid = Integer.parseInt(tokenizer.nextToken("#"));
						
						traderroom.takeTable(name,cardid);
					//	out.println(traderroom.deckList(name));
						out.println(traderroom.deckText(name));
						traderroom.message(traderroom.tables());
						
						
					}
					else if (command.equals("ADDP") && trader) {
						
						int pts = Integer.parseInt(tokenizer.nextToken("#"));
						
						traderroom.modPoints(name,pts);
					//	out.println(traderroom.deckList(name));
						out.println(traderroom.deckText(name));
						traderroom.message(traderroom.tables());						
						
						
					}
					else if (command.equals("GETDATA")) {
						out.println(traderroom.deckList(name));
						out.println(traderroom.deckText(name));
					//	out.println(traderroom.tables());	
						
					}				
					else if (command.equals("TM") && (trader)) {
						String name = tokenizer.nextToken("#");
						if (observer)
						traderroom.say(name + "(observer)",tokenizer.nextToken("#"));
						else
						traderroom.say(name,tokenizer.nextToken("#"));
						
					}					
				
				}
				
				
				
				
				
			}
			if (gameplayer || observer)
    		if (playerroom != null) {

    			int cmessagetag = messagetag;
			//	//System.out.println(threadnum + " : " + playerroom.messageTag() + ">" + messagetag);
		    	if (playerroom.messageTag() >= messagetag) { // send chat message
		    //		for (cmessagetag = messagetag+1;cmessagetag < playerroom.messageTag();cmessagetag++) {
		    		//	//System.out.println(messagetag);
		    			
						outputLine = playerroom.getMessageFor(messagetag,name); 
						String toname = playerroom.getToName(messagetag);
						
						if (outputLine == null && observer) {
							outputLine = playerroom.getMessageFor(messagetag,"$obs$"); 
						}
						messagetag++;
					//	if (!((toname.equals("$all$") || (toname.equals("")) || (toname.equals(name))))) outputLine = null;
						
						//System.out.println("Sending to "  + name + " for " + toname + " : " + outputLine);
						if (outputLine != null) {
						
						if (outputLine != null) out.println(outputLine);
							if (outputLine.startsWith("OPF")) {
								notstart = false;	
								
								
								
							}
							if (outputLine.startsWith("OVR")) {	
								System.out.println("OVR");
		    				threaddie();
			    			out.close();
					    	in.close();
					    	socket.close();
					 		//System.out.println(threadnum + " Ending Game");
					    	if (playerroom != null) playerroom.close();			
    				
    						} 	
    					}				
				//	}
				//messagetag = cmessagetag; 
				}
				
				
    				

    		}
    		
    		if (trader) {
    			if (traderroom != null) {
    				int cmessagetag = messagetag;
    				if (traderroom.messageTag() >= messagetag) {
    					outputLine = traderroom.getMessage(messagetag); messagetag++;
    					////System.out.println(outputLine);
    					if (outputLine != null)
    						if (!outputLine.equals("null")) out.println(outputLine);
    						
    					if (outputLine.startsWith("TRDONE")) {
    						threaddie();
			    			out.close();
					    	in.close();
					    	socket.close();
					 		//System.out.println(threadnum + " Ending Trade");
    						if (traderroom != null) traderroom.close = true;			
    				
    					 
    						
    						
    					}
    					
    					
    					
    					
    				}	
    				
    				
    				
    				
    			}	
    			
    			
    		}	
    		
    } catch (IOException e) {
	    e.printStackTrace();
	}
    		
    		}	
		
		
	}
   
}