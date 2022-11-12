package webrunner.cardmaster;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
public class CMCserver {
	
	
	/*
	 *		System.out.println("Attempting to create Database...");
		String returns = CardmasterDatabase.CreateCMCDatabase();
		if (returns.equals("Finished")) {
				System.out.println("Database created or already exists!");
			}
		*/

	
	public static void main(String args[])  {

		if (true) {
		
		
		
		
		
		File waitfile = new File(CardmasterData.DIRECTORY +"cardwait");
		waitfile.delete();
		waitfile = new File(CardmasterData.DIRECTORY +"userwait");
		waitfile.delete();
		waitfile = new File(CardmasterData.DIRECTORY +"userpatchwait");
		waitfile.delete();
		CardmasterChatRoom chatroom[];

		chatroom = new CardmasterChatRoom[CardmasterData.NUMROOMS];
		
		CardmasterTradeRoom traderoom[] = new CardmasterTradeRoom[CardmasterData.NUMROOMS];
		for (int i=0;i<CardmasterData.NUMROOMS;i++) {
			chatroom[i] = new CardmasterChatRoom();
			traderoom[i] = new CardmasterTradeRoom();
		}
		
		
		CardmasterServer serverThread = new CardmasterServer(chatroom, traderoom);
		CardmasterMatchServer matchThread = new CardmasterMatchServer(chatroom, traderoom);
		serverThread.start();
		matchThread.start();
		
		
		
		Timer cleanuptimer;
	//	Timer hourlybackup = new Timer();
	//	hourlybackup.schedule(new backupper(),0,60*1000*60);
		cleanuptimer = new Timer();
		cleanuptimer.schedule(new quitterhandler(serverThread,matchThread),0,10000);		
		}
	
	}
	

}

class quitterhandler extends TimerTask   {
	
	CardmasterServer	serverThread;
	CardmasterMatchServer matchThread;
		public quitterhandler(CardmasterServer serverThread, CardmasterMatchServer matchThread) {
			super();
			this.serverThread = serverThread;
			this.matchThread = matchThread;	
			
			
		}
		 public void run() {
			File file = new File(CardmasterData.DIRECTORY + "quit");
			if (file.exists()) {
				//System.out.println("Quitting");
				serverThread.breakit = true;
				matchThread.breakit = true;
				this.cancel();
			}

		 }
        
	}



