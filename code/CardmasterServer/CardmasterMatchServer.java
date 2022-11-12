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
class CardmasterMatchServer extends Thread{
	
	CardmasterChatRoom[] chatroom;
	CardmasterTradeRoom[] traderoom;
   boolean breakit = false;
	public CardmasterMatchServer(CardmasterChatRoom[] rooms, CardmasterTradeRoom[] trooms) {
		super();
		chatroom = rooms;
		traderoom = trooms;
		
		
	}
	public void run() {
		//System.out.println("Starting Cardmaster Conflict Matchmaker Server...");
		
		
	
		

	

	
		ServerSocket matchSock = null;
		boolean listening = true;
		try {
		//	servSock = new ServerSocket(4171);
			matchSock = new ServerSocket(CardmasterData.MatchPort,25);
			matchSock.setReuseAddress(true);
		} catch (IOException e) {
			System.err.println("Could not listen on port" + CardmasterData.MatchPort);
			System.exit(-1);
		}
		int thread = 1;
		CardmasterMatchMaker matchmaker = new CardmasterMatchMaker(chatroom, traderoom);
		Timer cleanuptimer;
		cleanuptimer = new Timer();
		cleanuptimer.schedule(new matchtimerhandler(matchmaker),0,10000);			
	///	CardmasterChatRoom chatroom = new CardmasterChatRoom(0000,"lobby");
		while (listening) {
			try{
				matchSock.setSoTimeout(60000);
				matchSock.setReuseAddress(true);
				Socket newSocket = matchSock.accept();
				newSocket.setReuseAddress(true);
				newSocket.setSoTimeout(60000);
				new CardmasterMatchmakerThread(newSocket,matchmaker,thread).start();}
			catch(SocketTimeoutException  e) {
				
				continue;
				
			}catch (Exception e) {
				e.printStackTrace();
				continue;	
			}
		//	new CardmasterServerThread(servSock.accept(),chatroom, thread).start();
			thread++;
			
			if (breakit) break;
			
		}
		//System.out.println("Quitting matchmaker...");
		try{matchSock.close();}catch(Exception e){e.printStackTrace();}
		
	}
	
}

class matchtimerhandler extends TimerTask   {
	



 	CardmasterMatchMaker match;
 	 matchtimerhandler(CardmasterMatchMaker match) {
 	 	super();
 	 	this.match = match;
 	 }
	
	 public void run() {
	 	//System.out.println("Doing el Timeouty Thing");
	 	if (match != null) {
	 		match.TimeoutCycle();
	 		match.QuitTimeout();
	 	}	
	 	
	 	
	 }
}