import java.awt.event.*; 
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.StringTokenizer;

public class CardmasterMatchClient extends JApplet implements ActionListener  {
	
		int port = 4175;
		// int port = 4171;
	  Timer timer;
		public int trademode = 0;
		Socket chatSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
         String fromServer;
        String fromUser;
        String chattext;
        String playername;
        String opponentname;
        
        String status_text;
        String action;
        public static int protocolversion  = 01;
        
		JPanel panel_top;
       	JPanel panel_chat;
       	JPanel panel_playerbox;
       	JScrollPane scroll_playerbox;
       	JLabel label_top;
		

  	   	
  //CHAT BOX
  	
		JScrollPane chat_log_scroll;
  		JTextArea chat_log;
  		JTextField chat_enter;
 
 		JButton modePlay;
 		JButton modeTrade;
 		JButton modeAway;
 		JButton modeChat;
 
	public void init() {
		String code = getParameter("check");
		String address = getParameter("address");
		try {
			
			
			playername = getParameter("player");
          //  chatSocket = new Socket(address, 4171);
          port = Short.parseShort(getParameter("port"));
           chatSocket = new Socket(address, port);
            out = new PrintWriter(chatSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(chatSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host");
          
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to Matchmaker Server @ " + port);
            
        }
        
       	panel_chat = new JPanel();
        
        out.println("LOGIN#" + playername + "#" + code + "#" + protocolversion + "#"); // Look who I am!
       // label_top =new JLabel("Cardmaster Conflict!");
        //getContentPane().add(label_top,BorderLayout.NORTH);
      //  switchModeButton = new JButton("Switch to Trading Mode");
       
        modePlay = new JButton("PLAY");
        modeTrade = new JButton("TRADE");
        modeAway = new JButton("AWAY");
        modeChat = new JButton("CHAT");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(modePlay);
        buttonPanel.add(modeTrade);
        buttonPanel.add(modeChat);
        buttonPanel.add(modeAway);
       
        getContentPane().add(buttonPanel,BorderLayout.NORTH);
        panel_playerbox = new JPanel();
        JScrollPane scroll_playerbox = new JScrollPane(panel_playerbox);
        getContentPane().add(scroll_playerbox,BorderLayout.CENTER);
        scroll_playerbox.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll_playerbox.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(panel_chat,BorderLayout.SOUTH);
			chat_enter = new JTextField();
		chat_enter.addActionListener(this);
		modePlay.addActionListener(this);
		modeTrade.addActionListener(this);
		modeAway.addActionListener(this);
		modeChat.addActionListener(this);
	
	
	
	chat_enter.setEditable(true);
		chat_enter.setPreferredSize(new Dimension(700,25));

		
		chat_log = new JTextArea();
		chat_log.setEditable(false);
		chat_log.setLineWrap(true);
		chat_log.setWrapStyleWord(true);
		chat_log_scroll = new JScrollPane(chat_log);
		chat_log_scroll.setPreferredSize(new Dimension(700, 160));
		panel_chat.setPreferredSize(new Dimension(700, 200));
		chat_log_scroll.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		panel_chat.add(chat_log_scroll,BorderLayout.CENTER);
		panel_chat.add(chat_enter,BorderLayout.SOUTH);
		timer = new Timer(200,this);
		timer.start();

	} 		
 
 
	 public void actionPerformed(ActionEvent evt){ 
	 	//System.out.println(evt.getSource());
	 	if (evt.getSource() == modeTrade) {
	 	
	 				out.println("TRON#"); // trade mode off.
	 				
	 				
	 		
	 		
	 	}
	 	else if (evt.getSource() == modeChat) {
	 		
	 				out.println("CHAT#/chat#"); // trade mode on
	 		
	 			
	 			
	 		
	 	}
	 	else if (evt.getSource() == modeAway) {
	 		
	 				out.println("CHAT#/away#"); // trade mode on
	 		
	 			
	 			
	 		
	 	}
	 	else if (evt.getSource() == modePlay) {
	 		
	 					out.println("TROF#"); // trade mode on
	 		
	 			
	 			
	 		
	 	}
	 	else if (evt.getSource() == chat_enter) {
        		if (!(chat_enter.getText().equals(""))) {
	        		out.println("CHAT#" + chat_enter.getText().replace('#',' ') + "#");
	        		chat_enter.setText("");
	        	}	 		
	 		
	 		
	 	}
		else if (evt.getSource() == timer) {   
	        	try{
	        		
		        	if (in.ready()) {
		        		fromServer = in.readLine();
		        		
			    		
			    		
			    		
			    		
			    		StringTokenizer tokenizer = new StringTokenizer(fromServer,"#");
			    		if (tokenizer.hasMoreTokens()) {
						String command = tokenizer.nextToken("#");
						System.out.println("Got " + command + " " + fromServer);
							if (command.equals("CHAT")) {
								String name = tokenizer.nextToken("#");
								chat_log.append("<" + name + ">" + tokenizer.nextToken("#") + "\n");
								chat_log.setText(chat_log.getText());
							}
							else if (command.equals("PLAYLIST")) {
								panel_playerbox.removeAll();
								int column = 1;
								JPanel panel_leftpanel = new JPanel();
								panel_leftpanel.setLayout(new BoxLayout(panel_leftpanel,BoxLayout.Y_AXIS));
									
								JPanel panel_centerpanel = new JPanel();
								panel_centerpanel.setLayout(new BoxLayout(panel_centerpanel,BoxLayout.Y_AXIS));
								
								JPanel panel_rightpanel = new JPanel();
								panel_rightpanel.setLayout(new BoxLayout(panel_rightpanel,BoxLayout.Y_AXIS));
								
								
								
								
								panel_playerbox.setLayout(new GridLayout(1,3));
								panel_playerbox.add(panel_leftpanel);
								panel_playerbox.add(panel_centerpanel);
								
								panel_playerbox.add(panel_rightpanel);
								while (tokenizer.hasMoreTokens()) {
									CardmasterPlayer blah = new CardmasterPlayer(
										tokenizer.nextToken(),
										Short.parseShort(tokenizer.nextToken()),
										Short.parseShort(tokenizer.nextToken()));
									int trader = Short.parseShort(tokenizer.nextToken());
									blah.flags = tokenizer.nextToken();
									blah.BuildFlags();
									blah.trader = trader;
									blah.urlcode2 = getCodeBase().toString();
									blah.urlcodebase = getCodeBase().toString();
									blah.urlcodebase = blah.urlcodebase + "/";
									blah.setPreferredSize(new Dimension(220,97));
									if (blah.name.equals(playername)) trademode =blah.trader;
									blah.setMaximumSize(new Dimension(220,97));
									blah.setMinimumSize(new Dimension(220,97));
								//	panel_playerbox.setLayout(new GridLayout(0,2));
									/*
									JPanel newPanel = new JPanel();
									newPanel.setMaximumSize(new Dimension(330,40));
									newPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
									newPanel.add(blah);*/
									
									System.out.println("Adding " + blah.name + " to column " + column);
									if (column == 1) {
										panel_leftpanel.add(new Box.Filler(new Dimension(2,2),new Dimension(2,2), new Dimension(2,2)));						
										panel_leftpanel.add(blah);
										
										column = 2;
									}
									else if (column == 2) {
										panel_centerpanel.add(new Box.Filler(new Dimension(2,2),new Dimension(2,2), new Dimension(2,2)));						
										panel_centerpanel.add(blah);
										column = 3;
									}
									else if (column == 3) {
										panel_rightpanel.add(new Box.Filler(new Dimension(2,2),new Dimension(2,2), new Dimension(2,2)));
										panel_rightpanel.add(blah);
										column = 1;
									}
									else {
										panel_rightpanel.add(new Box.Filler(new Dimension(2,2),new Dimension(2,2), new Dimension(2,2)));
										panel_rightpanel.add(blah);
										
										 column = 1;
									}
									blah.addMouseListener(new matchMouseEvents());
									blah.setBorder(BorderFactory.createLineBorder(Color.black));
									blah.setVisible(true);
									//blah.drawEnabled = blah.trader == trademode;
								
										
									
								}	
								repaint();
								
							}
							else if (command.equals("CHASTAT")) {
								System.out.println("Chat Status");
							//	String name = tokenizer.nextToken("#");
								chat_log.append("**" + tokenizer.nextToken("#") + "\n");
							}
							else if (command.equals("CHALFROM")) {
								chat_log.append("!!!" + tokenizer.nextToken() + " has challenged you..\n");	
								
								
							}
						//	else if (command.equals("MATCHMADE")) { // LEGACY SUPPORT
						//		String matchname = tokenizer.nextToken();
						//		chat_log.append("Match foun: " + matchname);
						///		getAppletContext().showDocument(new URL(getCodeBase().toString() + "game.jsp?opponent=" + matchname));
							
						//	}
							else if (command.equals("MATCHROOM")) {
								String matchname = tokenizer.nextToken();
								chat_log.append("room found: " + matchname);
								getAppletContext().showDocument(new URL(getCodeBase().toString() + "playcmc.jsp?room=" + matchname));
							
							}

							else if (command.equals("TRADEROOM")) {
								String matchname = tokenizer.nextToken();
								chat_log.append("trade room found: " + matchname);
								getAppletContext().showDocument(new URL(getCodeBase().toString() + "traderoom.jsp?room=" + matchname));
							
							}
							else if (command.equals("PNG")) {
								out.println("PN#");
							}

						}
				}
		}catch(Exception e) { return; }
						 	
		}
	 



	 }		
 


	 class matchMouseEvents implements MouseListener {
		public void mouseEntered(MouseEvent e) {
	//	System.out.println(e.getSource());	
		}	
			 public void mousePressed(MouseEvent e) { 
			 return;}
			  
			  
		public void mouseClicked(MouseEvent e) {
			CardmasterPlayer player = (CardmasterPlayer)(e.getSource());
			if (playername.equals(player.name)) return;
		//	if (!(player.challenge)) {
		//		player.challenge = true;
				out.println("CHALNG#" + player.name + "#");
				chat_log.append("!!! Challenging " + player.name + "\n");	
								
				repaint();
				
			//} else {
			//	player.challenge = false;	
				
			//	out.println("UNCHAL#" + player.name + "#");
			//	repaint();
				
			//}
			return;
		}
			   public void mouseExited(MouseEvent e) { return;}
			   public void mouseReleased(MouseEvent e) {return; }

			
		}



 class CardmasterPlayer extends JPanel {
 	public String name;
 	public int wins;
 	public int losses;
 	public boolean challengedby;
 
 //	public boolean challenge;
 	public int trader;
 	public boolean drawEnabled;
 	public String urlcode2;
 	public String urlcodebase;
 	public String flags = "!";
 	boolean flagAdmin = false;
 	int tourneywins = 0;
 	boolean flagDonor = false;
 	boolean flagImplementer = false;
 	CardmasterPlayer(String name, int wins, int losses) {
	 	this.wins = wins;
	 	this.losses = losses;
	 	this.name = name;
//	 	challenge = false;
	 	challengedby = false;
	 	drawEnabled = true;
	 	flagAdmin = false;
	 	System.out.println(name + wins + losses);	
 		
 	}
 	public String toString() {
 		return (name + " wins: " + wins + " losses: " + losses);	
 		
 	}
 	public void BuildFlags() {
 		if (flags == null) flags = "!";
		if (flags.indexOf("!admin!") != -1) flagAdmin = true;
		if (flags.indexOf("!donor!") != -1) flagDonor = true;
		if (flags.indexOf("!imple!") != -1) flagImplementer = true;
	    if (flags.indexOf("!tstart-") != -1) {
	    	int start = flags.indexOf("!tstart-");
	    	int end = flags.indexOf("-tend!");
	    	//System.out.println("S:" + start +  " E:" + end);
	    //	System.out.println(flags.substring(start,end));
	    	start+=8;
	    //	System.out.println(flags.substring(start,end));
	    	if (start != end) {
	    		try{
	    		
	    		tourneywins = Short.parseShort(flags.substring(start,end));
	    		}catch (Exception e) {tourneywins=0;
	    		}
	    	//	System.out.println(flags.substring(start,end));
	    	}
	    	
	    	
	    }
 	
 	}
 	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	//	System.out.println("Drawin " + this);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
	

		g2.setFont(new Font("SansSerif",Font.PLAIN,15));

	/*	if (trader == 0) g2.setColor(Color.black);
		else if (trader == 1 )g2.setColor(Color.red);
		else if (trader == 2 )g2.setColor(Color.blue);
		else if (trader == 3 )g2.setColor(Color.darkGray);
		else g2.setColor(Color.pink);
		
	*/	
	/*	
		String tradetext = "";
		if (trader==0) tradetext = " (player)";
		else if (trader==1) tradetext = " (trader)";
		else if (trader==2) tradetext = " (chatter)";
		else if (trader==3) tradetext = " (away)";*/
		g2.drawString(name,62,17);
		g2.drawString("W:"+wins + "L:"+losses,62,37);
	Toolkit toolkit = Toolkit.getDefaultToolkit();

	
	
		
		g2.drawRect(1,1,60,95);	
		try {
			String newname = name;
			
			if (name.indexOf(" ")!= -1) {
				newname = name.replaceAll(" ", "%20");
				newname = name.replaceAll("\\s", "%20");
				
			}
			//System.out.println(urlcode2 + "avatars/"+java.net.URLEncoder.encode(name,"UTF-8")+".png");
		
			g2.drawImage(toolkit.getImage(new URL(urlcode2 + "avatars/"+newname+".png")),1,1,60,95,this);
		}
		catch (Exception e) {
			e.printStackTrace();
		
		}
	
	
		
		try{
		
		g2.drawImage(toolkit.getImage(new URL(urlcodebase  + "images/"+ "chatmode_" + trader + ".gif")),62,55,40,20,this);
	
	
		if (trademode == trader && trader == 0 && !playername.equals(name)) {
		  	g2.drawImage(toolkit.getImage(new URL(urlcodebase  + "images/"+ "chatok.gif")),102,55,40,20,this);	
		}
		else if (trademode == trader && trader == 1&& !playername.equals(name)) {
		  	g2.drawImage(toolkit.getImage(new URL(urlcodebase  + "images/"+ "chatok.gif")),102,55,40,20,this);	
		}
	/*	else {
		  	g2.drawImage(toolkit.getImage(new URL(urlcodebase  + "images/"+ "chatno.gif")),250,2,40,40,this);	
		}*/
		// SYMBOL FITTING SYSTEM
		
		
		int symbolx = 0;
		int symboly = 0;
	
		int xlocbase = 218;
		
		int ylocbase = 95;
		
		if (flagAdmin) {
			
		
				
			
						
				g2.drawImage(toolkit.getImage(new URL(urlcodebase  + "images/"+ "chat_admin.gif")),xlocbase - 40 - (symbolx*45),ylocbase - 20 - (symboly*22),40,20,this);	
			
				symbolx++;
				if (symbolx >= 2) { symbolx = 0; symboly++; }
		}
		
		if (flagDonor) {
			
		
				
			
						
				g2.drawImage(toolkit.getImage(new URL(urlcodebase  + "images/"+ "chat_donor.gif")),xlocbase - 40 - (symbolx*45),ylocbase - 20 - (symboly*22),40,20,this);	
			
				symbolx++;
				if (symbolx >= 2) { symbolx = 0; symboly++; }
		}
		
		if (flagImplementer) {
			g2.drawImage(toolkit.getImage(new URL(urlcodebase  + "images/"+ "chat_imple.gif")),xlocbase - 40 - (symbolx*45),ylocbase - 20 - (symboly*22),40,20,this);	
			
				symbolx++;
				if (symbolx >= 2) { symbolx = 0; symboly++; }
		}
		
		
		if (tourneywins > 0) {
			
		
			
			for (int i =0;i<tourneywins;i++)
				g2.drawImage(toolkit.getImage(new URL(urlcodebase  + "images/"+ "chat_trophy.gif")),62+(i*20),97-22,20,20,this);	
			
			
		}
		
		
		}catch(Exception e){e.printStackTrace();}
	
	}	
 	
}




 
 }
 