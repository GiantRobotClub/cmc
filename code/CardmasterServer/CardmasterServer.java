package webrunner.cardmaster;
/*
 * @(#)CardmasterServer.java 1.0 03/06/29
 *
 * You can modify the template of this file in the
 * directory ..\JCreator\Templates\Template_1\Project_Name.java
 *
 * You can also create your own project template by making a new
 * folder in the directory ..\JCreator\Template\. Use the other
 * templates as examples.
 *
 */

import java.net.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;


//		
//			
class CardmasterServer extends Thread{
	
	CardmasterChatRoom[] chatroom;
	CardmasterTradeRoom[] traderoom;
   boolean breakit = false;
	public CardmasterServer(CardmasterChatRoom[] rooms, CardmasterTradeRoom[] trooms) {
		super();
		chatroom = rooms;
		traderoom = trooms;
		
		
	}
	public void run()  {
		System.out.println("Starting Cardmaster Conflict Server...");
		System.out.println("Version " + CardmasterChatRoom.version);
		
		CardmasterLogManager.WriteLog("Starting server... version " + CardmasterChatRoom.version);	
		
	
		

		
		Timer cleanuptimer;
		cleanuptimer = new Timer();
		cleanuptimer.schedule(new matchtimeouthandler(chatroom, traderoom),0,1000);	
		ServerSocket servSock = null;
		//ServerSocket matchSock = null;
		boolean listening = true;
		try {
			servSock = new ServerSocket(CardmasterData.GamePort,25);
			//matchSock = new ServerSocket(4171);
		} catch (IOException e) {
			System.err.println("Could not listen on port"+CardmasterData.GamePort);
			System.exit(-1);
		}
		int thread = 1;
	//	CardmasterMatchMaker matchmaker = new CardmasterMatchMaker();
	///	CardmasterChatRoom chatroom = new CardmasterChatRoom(0000,"lobby");
		while (listening) {
		//	Socket socket = matchSock.accept;
		//	if (socket != 
		//	new CardmasterMatchmakerThread(matchSock.accept,matchmaker,thread).start();
			try{
				servSock.setReuseAddress(true);
				Socket newSocket = servSock.accept();
				newSocket.setReuseAddress(true);
				servSock.setSoTimeout(60000);
				newSocket.setSoTimeout(60000);
				new CardmasterServerThread(newSocket,chatroom, traderoom, thread).start();}
			catch(SocketTimeoutException  e) {
				
				continue;
				
			}catch (Exception e) {
				e.printStackTrace();
				continue;	
			}
			thread++;
			if (breakit) break;
			
		}
		//System.out.println("Quitting game server..");
		try{servSock.close();} catch (Exception e){e.printStackTrace();}
		
	}
}

/*
 * cleanuptimerhandler
 * Goes through all the rooms regularly cleaning up the dead ones.
 */
class matchtimeouthandler extends TimerTask   {
	
		CardmasterChatRoom chatroom[];
		CardmasterTradeRoom traderoom[];
		 matchtimeouthandler(CardmasterChatRoom chatroom[], CardmasterTradeRoom traderoom[]) {
		 	super();
		 	this.chatroom = chatroom;
		 	this.traderoom = traderoom;
		 	
		 	
		 	
		 }
		 public void run() {
			try{
			FileWriter writer = new FileWriter(CardmasterData.DIRECTORY + "games.csc"); 
						PrintWriter out = new PrintWriter(writer);
		 	for (int i=0;i<CardmasterData.NUMROOMS;i++) {
		 		chatroom[i].timeout--;
		 		traderoom[i].timeout--;
		 		if (chatroom[i].timeout <= 0) chatroom[i].dead = true;
		 		if (traderoom[i].timeout <= 0) traderoom[i].dead = true;
		 		
		 	
		 		
		 		if (chatroom[i].dead) {
		 			chatroom[i] = null;
		 			chatroom[i] = new CardmasterChatRoom();	
		 		
		 		}else if (!chatroom[i].empty && !chatroom[i].dead) {
		 		
					if (!chatroom[i].invisible) 
					out.println("GAME#" + i + "#" + chatroom[i].name1 + "#" + chatroom[i].name2 + "#");
					else
					out.println("INVIS#" + i + "#" + chatroom[i].name1 + "#" + chatroom[i].name2 + "#");
			
		 			
		 			
		 			
		 		}
		 		else {
		 			if (!(chatroom[i].empty && ! chatroom[i].reserve && !chatroom[i].ended && !chatroom[i].dead && !chatroom[i].used ))
		 			out.println("OTHER#" + i + "#" + chatroom[i].empty + "#" + chatroom[i].reserve + "#" + chatroom[i].ended + "#" +chatroom[i].dead + "#" + chatroom[i].used + "#");
		 		}
		 		if (traderoom[i].dead) {
		 			traderoom[i] = null;
		 			traderoom[i] = new CardmasterTradeRoom();
		 		}
		 		else if (traderoom[i].started && !traderoom[i].ended) {
		 		
				
					out.println("TRADE#" + i + "#" + traderoom[i].name1 + "#" + traderoom[i].name2 + "#");
				
			
		 			
		 			
		 			
		 		}
		 	 		
		 	}
		 	
		
		 			out.close();
						writer.close();
					}catch(Exception e){e.printStackTrace();}	
         }
	}

