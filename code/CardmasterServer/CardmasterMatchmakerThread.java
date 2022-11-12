package webrunner.cardmaster;
import java.net.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.StringTokenizer;

public class CardmasterMatchmakerThread extends Thread{
   CardmasterMatchMaker matchmaker;
   int messagetag = 1;
   int threadnum;
   boolean pingreply;
   boolean success = false;
   Timer timer;
   Timer timeouter;
   Timer pingtimer;
   String inputLine = "";
   private Socket socket = null;
   PrintWriter out;
   BufferedReader in;
   int idcode = 0;
   int connecttimeout = 0;
   String name;
	public CardmasterMatchmakerThread(Socket socket,   CardmasterMatchMaker matchmaker, int threadnum) {
 		super("CardmasterMatchmakerThread");
   		this.matchmaker = matchmaker;
		this.socket = socket;
		this.threadnum = threadnum;
		//System.out.println("Matchmaker Thread " +  threadnum + " created.");
 	
 		messagetag = matchmaker.messageTag() + 1;
 	
 	}
 	
	
	  public void run() {
		pingreply = true;
		timeouter = new Timer();
		timeouter.schedule(new timeouter(),1000,1000);
  	 try {
	     out = new PrintWriter(socket.getOutputStream(), true);
	     in = new BufferedReader(
				    new InputStreamReader(
				    socket.getInputStream()));
	
			out.println("RDY#");
			timer = new Timer();
			pingtimer = new Timer();
		try{
		
			if (timer != null) timer.schedule(new threadtimer(),5,200);
		if (pingtimer != null)	pingtimer.schedule(new timerping(),1000,20000);
		}catch(Exception e) { 
			threaddie();
			
		}						
		   
	  	
	  	
	  	
	  	
	  }

	 catch (IOException e) {
	    e.printStackTrace();
	}
   	 
  }

	void threaddie() {
		//matchmaker.quit(name);
		if (timer != null) timer.cancel();
		if (pingtimer != null) pingtimer.cancel();
		if (name!=null) matchmaker.quit(name);
		if (timeouter!=null) timeouter.cancel();
		
		
	}
	
	class timeouter extends TimerTask {
		public void run() {
		try{
	/*	out.println("CHASTAT#Automatic timeout#");
		threaddie(); out.close(); in.close(); socket.close();
		pingtimer.cancel();
		timer.cancel();
		this.cancel(); */
	//	System.out.println("Running Timeout Cycle for" + name);
		if (matchmaker.GetIDCode(name) != idcode) {
			
			
			if (out != null) out.close(); 
			if (in != null)  in.close(); 
			if (socket != null) socket.close();
			
			pingtimer.cancel();
			this.cancel(); // quietly back off, svp	
			timer.cancel();
		//	System.out.println("id code timeouter");
			
			
			
			
			return;
		}
		if (name != null)
	    if (!matchmaker.hasName(name)) {
	    //	out.println("CHASTAT#Automatic timeout#");
			System.out.println("timeouter2");
			threaddie(); 
			if (out != null) out.close(); 
			if (in != null)  in.close(); 
			if (socket != null) socket.close();
			if (pingtimer != null) pingtimer.cancel();
			if (timer != null) timer.cancel();
			pingtimer = null;
			timer = null;
			this.cancel();
			return;
	    }
	    /*
		if (!matchmaker.TimeoutCycle(name)) {
			out.println("CHASTAT#Automatic timeout#");
			System.out.println("timeouter");
			threaddie(); out.close(); in.close(); socket.close();
			pingtimer.cancel();
			timer.cancel();
			this.cancel();
		}
		*/
		}
		catch (Exception e) {
			e.printStackTrace();}	
		}
		
	}

	class timerping extends TimerTask   {
		
		
		 public void run() {
		 	try{
		 		//System.out.println(threadnum + " " + success);
		 	if (success) {
		
		 	/*if (pingreply == false) {
		 		//System.out.println("Matchmaker " + threadnum + " No reply.. closing.");
		 		
		 	 threaddie(); out.close(); in.close(); socket.close(); 
		 	}*/
		 	pingreply = false;
		 		if (matchmaker.hasName(name)) out.println("PNG#");
		 		else {
		 			connecttimeout++;
		 		//	System.out.println("Connecttimeout " + connecttimeout);
		 			if (connecttimeout >=6) {
		 				System.out.println("PNG TIMEOUT");
		 				threaddie(); out.close(); in.close(); socket.close(); 	
		 			}
		 			
		 			
		 		}
		 	//System.out.println("Matchmaker " + threadnum + " Ping...");
		 	
			}
			else {
				connecttimeout++;
		 		System.out.println("Connecttimeout " + connecttimeout);
		 		if (connecttimeout >=6) {
		 			System.out.println("PNG TIMEOUT2");
		 				threaddie(); out.close(); in.close(); socket.close(); 	
		 		}
			}
		 } catch (Exception e) {e.printStackTrace();}
		 
	}
		 
	}

	class threadtimer extends TimerTask   {
		
		 public void run() 
    		{    try {
    		    String  outputLine;

			
		 		
			if (in.ready()) {
				inputLine = in.readLine();
				//System.out.println("Matchmaker Recieved from thread " + threadnum + " : " + inputLine );
				StringTokenizer tokenizer = new StringTokenizer(inputLine);
				if (tokenizer.hasMoreTokens()) {
					String command = tokenizer.nextToken("#");
					
					
					if (command.equals("LOGIN")) {
						int protocolversion = 0;
						if (tokenizer.hasMoreTokens()) {
							name = tokenizer.nextToken();
							
							
								
							
							String code = (tokenizer.nextToken());
							protocolversion = Integer.parseInt(tokenizer.nextToken());
							if (matchmaker.PROTOCOLVERSION == protocolversion ) {
							
							if (CardmasterData.securityCheckBinary(code,name)) {
							
								if (!matchmaker.login(name)) {
									out.println("CHASTAT#ERROR: Player not found#");
									System.out.println("nameis");
									threaddie();
					    			out.close();
							    	in.close();
							    	socket.close();
							    	
									
									}
									else { 
									success = true; 
									matchmaker.NewIDCode(name);
									idcode = matchmaker.GetIDCode(name);
									matchmaker.PlayerAction(name);
									out.println("PLAYLIST#" + matchmaker.playlist());
									}
									
								}
								else {
										out.println("CHASTAT#AUTHENTICATION CODE ERROR#");
						   		System.out.println("nameis2");
								threaddie();
				    			out.close();
						    	in.close();
						    	socket.close();
									
								}	
								}
								else {
									
									out.println("CHASTAT#VERSION MISMATCH!  Please restart your browser, clear your cache, etc.#");
									threaddie();
						    		out.close();
								   	in.close();
								   	socket.close();
								}
						}
						else {
						   		out.println("CHASTAT#AUTHENTICATION CODE ERROR#");
						   		System.out.println("nameis22");
								threaddie();
				    			out.close();
						    	in.close();
						    	socket.close();
						    	
						}	
						
					}
					else if (command.equals("NAMEIS")) {
						
							out.println("CHASTAT#VERSION MISMATCH!  Please restart your browser, clear your cache, etc.#");
							threaddie();
				    		out.close();
						   	in.close();
						   	socket.close();
					}
					else if (command.equals("CHAT") && success) {
						if (matchmaker.hasName(name)) // can't send a chat message if you're not in the chat.
						//System.out.println("Chat Message");
						if (tokenizer.hasMoreTokens()) {
							
							String chatmessage = tokenizer.nextToken();
							if (chatmessage.equals("/away")) {
								matchmaker.setTrade(name,3);
							}
							else if (chatmessage.equals("/chat")) {
								matchmaker.setTrade(name,2);
							}
							else if (chatmessage.startsWith("/kick ")) {
							
								String target = chatmessage.substring(6);
								matchmaker.kick(name,target);
							//	boolean good = true;
								
								
								
								
							}
							else {
								matchmaker.message("CHAT#" + name + "#" + chatmessage + "#", "$all$");	
								
							}
							
							matchmaker.PlayerAction(name);
							
						}	
						
						
					}
					
					else if (command.equals("CHALNG")&& success) {
						if (tokenizer.hasMoreTokens()) {
							String challenge = tokenizer.nextToken();
							matchmaker.challenge(name,challenge);	
							matchmaker.PlayerAction(name);
							
						}
						
					}
					else if (command.equals("UNCHAL")&& success) {
						if (tokenizer.hasMoreTokens()) {
							String challenge = tokenizer.nextToken();
							matchmaker.unchallenge(name,challenge);	
							matchmaker.PlayerAction(name);
							
							
						}
						
					}
						
					else if (command.equals("TRON")) {
							matchmaker.setTrade(name,1);
							matchmaker.PlayerAction(name);
						
						
						}
					else if (command.equals("TROF")) {
							matchmaker.setTrade(name,0);
							matchmaker.PlayerAction(name);
						
						}				
					else if (command.equals("PN") && success) {
						pingreply = true;
						matchmaker.PlayerAction(name);
						//System.out.println("Matchmaker" + threadnum + " Pong...");
						
					}	
				}
			}


     			int cmessagetag = messagetag;
		    	if (matchmaker.messageTag() >= messagetag && success) { // send message

		    			
						outputLine = matchmaker.getMessage(messagetag);
					
						String toname = matchmaker.getToName(messagetag);
						messagetag++;
						//System.out.println("Test:" + toname);
						if ((toname.equals("$all$") || (toname.equals(name)))) {
							//System.out.println("Sending to " + name + " : " + outputLine );
						
							if (outputLine != null)
							if (!outputLine.equals("null")) out.println(outputLine);
								if (outputLine.startsWith("MATCHMADE") && (toname.equals(name))) {
									System.out.println("Match Made!  Quitting with " + name);
									matchmaker.quit(name);		
				    				threaddie();
					    			out.close();
							    	in.close();
							    	socket.close();
						 		//System.out.println(threadnum + " Starting Game....");
						    			
	    				
	    						}
	    				//		if (outputLine.startsWith(") 					
						}
				 
				}
				
				
    				

    			
    		
    } catch (IOException e) {
	   threaddie();
	}
    		
    		}	
		
		
	}
   
}