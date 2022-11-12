/*
 * @(#)CardmasterClient.java 1.0 03/06/29
 *
 * You can modify the template of this file in the
 * directory ..\JCreator\Templates\Template_2\Project_Name.java
 *
 * You can also create your own project template by making a new
 * folder in the directory ..\JCreator\Template\. Use the other
 * templates as examples.
 *
 */


import java.awt.event.*; 
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import java.util.StringTokenizer;


public class CardmasterTradeClient extends JApplet implements ActionListener  {
	
	  Timer timer;
		public int roomnumber;
		Socket chatSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String fromServer;
        String fromUser;
        String chattext;
        String playername;
        String opponentname;
		public int playern;
		JScrollPane scroll_sdeck;
		JScrollPane scroll_sdata;
		JScrollPane scroll_stable;
		JScrollPane scroll_sside;
        JPanel panel_sdeck;
        JPanel panel_sdata;
        JPanel panel_stable;
        JPanel panel_sside;
		String currentdpointer;        
        JPanel panel_oside;
        JPanel panel_otable;
        JScrollPane scroll_otable;
        
       	JPanel panel_card;

       	JPanel panel_chat;

		 static int NUMCARDS = 1000;
        int askdatacounter = 0;
        boolean askdata[];
        CardmasterCard carddata[];
        JLabel label_quantity;
		JLabel label_Oname;
		JLabel label_Sname;
		JLabel label_Otpoints;
		JLabel label_Stpoints;

		JLabel label_Ostatus;
		JLabel label_Sstatus;
		JLabel label_currentdeck;
		JLabel label_currentpoints;
		
		JButton button_takepoints;
		JButton button_addpoints;
		JButton button_ready;
		JButton button_lock;
		
       	
       	CardmasterCard sdeck[];
       	CardmasterCard stable[];
       	CardmasterCard otable[];

       	CardmasterCard bigcard;
       	String[] decklist;
		int handid;
        	
  		boolean slock;
  		boolean sready;
  		boolean olock;
  		boolean oready;
  		JComboBox combo_decks;
  	
  		JTextField text_points;
  		int otpoints;
  		int stpoints;
  		int currentdeck;
  		int currentpoints;
  	
 	
  	
		JScrollPane chat_log_scroll;
  		JTextArea chat_log;
  		JTextField chat_enter;
   

   	CardmasterImageManager manager;

	public void init() {
			manager = new CardmasterImageManager(getCodeBase().toString());
		manager.LoadCommonCMCImages();
		text_points = new JTextField(0);
		sdeck = new CardmasterCard[0];
		stable = new CardmasterCard[0];
		otable = new CardmasterCard[0];

		bigcard = new CardmasterCard();
		
		carddata = new CardmasterCard[NUMCARDS];
		askdata = new boolean[NUMCARDS];
		for (int i = 0; i<NUMCARDS; i++) {
			askdata[i] = false;	
			
		}
		carddata[0] = new CardmasterCard();
		
		try {
			//boolean numericjoin = false;
			//if (getParameter("numericjoin") != null) numericjoin true; 
			String address = getParameter("address");
			playername = getParameter("player");
			//if (!numericjoin) opponentname = getParameter("opponent");
			//opponentname = "waiting..";
			roomnumber = Short.parseShort(getParameter("roomnumber"));
			int port = Short.parseShort(getParameter("port"));
            chatSocket = new Socket(address, port);
            out = new PrintWriter(chatSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(chatSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: taranis.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection.");
            System.exit(1);
        }

        
      	out.println("TR#" + playername + "#" + roomnumber + "#"); //join trade room
    String  			checkcode = getParameter("checkcode");
			out.println("CC#" + checkcode + "#" + playername + "#");
        GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH; 

		getContentPane().setLayout(gridbag);



		
		panel_card = new JPanel();
		panel_card.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel_card.setMinimumSize(new Dimension((int)(getWidth()/(620.0/150.0)),(int)(getHeight()/(420.0/300.0))));
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 4;
		c.weighty = 1;
		gridbag.setConstraints(panel_card, c);
		getContentPane().add(panel_card);









		panel_oside = new JPanel();
		panel_oside.setLayout(new GridLayout(0,1));
			panel_oside.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.weighty = 1;
		gridbag.setConstraints(panel_oside, c);
		getContentPane().add(panel_oside);		





		panel_otable = new JPanel();
		scroll_otable = new JScrollPane(panel_otable);
		scroll_otable.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scroll_otable.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll_otable.setMinimumSize(new Dimension((int)(getWidth()/(620.0/300.0)),(int)(getHeight()/(4))));
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 1;
		c.weighty = 1;
		gridbag.setConstraints(scroll_otable, c);
		//panel_chat.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(scroll_otable);


		panel_sside = new JPanel();
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.weighty = 1;
		gridbag.setConstraints(panel_sside, c);
		panel_sside.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		getContentPane().add(panel_sside);
		panel_sside.setLayout(new GridLayout(0,1));


		panel_stable = new JPanel();
		scroll_stable = new JScrollPane(panel_stable);
		scroll_stable.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scroll_stable.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll_stable.setMinimumSize(new Dimension((int)(getWidth()/(620.0/300.0)),(int)(getHeight()/4)));
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.weighty = 1;
		gridbag.setConstraints(scroll_stable, c);
		//panel_chat.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(scroll_stable);



		panel_sdata = new JPanel();
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.weighty = 1;
		gridbag.setConstraints(panel_sdata, c);
		panel_sdata.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		getContentPane().add(panel_sdata);
		panel_sdata.setLayout(new GridLayout(0,2));


		
		panel_sdeck = new JPanel();
		scroll_sdeck = new JScrollPane(panel_sdeck);
		scroll_sdeck.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scroll_sdeck.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll_sdeck.setMinimumSize(new Dimension((int)(getWidth()/(620.0/300.0)),(int)(getHeight()/4)));
		c.gridx = 2;
		c.gridy = 2;
		c.gridheight = 1;
		c.weighty = 1;
		gridbag.setConstraints(scroll_sdeck, c);
		//panel_chat.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(scroll_sdeck);





		panel_chat = new JPanel();
		panel_chat.setMinimumSize(new Dimension((int)(getWidth()/(620.0/450.0)),(int)(getHeight()/(4))));
		c.gridx = 1;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.weightx = 1;


		gridbag.setConstraints(panel_chat, c);
		//panel_chat.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(panel_chat);





		
	
		button_ready = new JButton("Ready");
		button_lock = new JButton("Lock");
		button_addpoints = new JButton("+");
		button_takepoints = new JButton("-");
		
		button_ready.setEnabled(false);
		button_ready.addActionListener(this);
		button_lock.addActionListener(this);
		button_addpoints.addActionListener(this);
		button_takepoints.addActionListener(this);
// Setting up Chat Box

		

		panel_chat.setLayout(new BorderLayout());
		
	
		chat_enter = new JTextField();
		chat_enter.addActionListener(this);
		chat_enter.setEditable(false);
		panel_chat.add(BorderLayout.SOUTH,chat_enter);
		
		chat_log = new JTextArea();
		chat_log.setEditable(false);
		chat_log.setLineWrap(true);
		chat_log.setWrapStyleWord(true);
		chat_log_scroll = new JScrollPane(chat_log);
		chat_log_scroll.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()/(420.0/85.0))));
		chat_log_scroll.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		panel_chat.add(BorderLayout.CENTER,chat_log_scroll);
		label_quantity = new JLabel("Quantity: 0");
		bigcard.setBorder(BorderFactory.createLineBorder(Color.black));
		bigcard.setPreferredSize(new Dimension((int)(getWidth()/(620.0/100.0)),(int)(getHeight()/(420.0/140.0))));
		panel_card.add(bigcard);
		panel_card.add(label_quantity);
		
		
		label_Oname = new JLabel("Name");
		label_Sname = new JLabel("Name");
		label_Otpoints = new JLabel("0 Points");
		label_Stpoints = new JLabel("0 Points");
		label_currentdeck = new JLabel("Deck # 0000");
		label_currentpoints = new JLabel("0 Points");
		label_Ostatus = new JLabel("Locked: NO/Ready: NO");
		label_Sstatus = new JLabel("Locked: NO/Ready: NO");
		
		decklist = new String[1];
		decklist[0] = "0000";
		currentdpointer = decklist[0];
		
		
		panel_oside.add(label_Oname);
		panel_oside.add(label_Ostatus);
		panel_oside.add(label_Otpoints);
		
		panel_sside.add(label_Sname);
		panel_sside.add(label_Sstatus);
		panel_sside.add(label_Stpoints);
		
		panel_sdata.add(label_currentdeck);
		panel_sdata.add(label_currentpoints);

		panel_sdata.add(text_points);
		JPanel panel_updown = new JPanel();
		panel_updown.add(button_addpoints);
		panel_updown.add(button_takepoints);
		panel_sdata.add(panel_updown);
		
		panel_sdata.add(button_lock);
		panel_sdata.add(button_ready);
		

		

		timer = new Timer(200,this);
		timer.start();


  		bigcard.urlcodebase = getCodeBase().toString();
  		bigcard.SetImageManager(manager);
  		bigcard.urlcodebase = bigcard.urlcodebase.substring(0,bigcard.urlcodebase.length()) + "/";
  	
  		bigcard.drawlarge = true;
  		updateData();
		
	}

// 	out.println to send to the file


	 class cardMouseEvents implements MouseListener {
	
		
			  
			  
		public void mouseClicked(MouseEvent e) {
		//	System.out.println("Clicked on something");
		
			if (((CardmasterCard)(e.getSource())).dataBlank) {
					out.println("WH#" + ((CardmasterCard)(e.getSource())).cardid + "#");
					askdata[((CardmasterCard)(e.getSource())).cardid] = true;
					askdatacounter = 1000;
					
			}
		
		
			for (int i=0;i<sdeck.length;i++) {
			//	System.out.println("Checking against sdeck "+ i);
				if ((CardmasterCard)(e.getSource()) == sdeck[i]) { 
				
					out.println("ADDC#" + sdeck[i].cardid + "#");
					//out.println("TRADER#0#");
					//out.println("TRADEL#0#");
					return;
					 
				}
			}

			for (int i=0;i<stable.length;i++) {
				//System.out.println("Checking against stable "+ i);
				
				if ((CardmasterCard)(e.getSource()) == stable[i]) { 
					out.println("REMC#" + stable[i].cardid + "#");
				//	out.println("TRADER#0#");
			//		out.println("TRADEL#0#");
					return;
					 
				}
			}
		}
			 	public void mousePressed(MouseEvent e) { 
			 return;}
			   public void mouseExited(MouseEvent e) { return;}
			   public void mouseReleased(MouseEvent e) {return; }
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() != bigcard) {
				bigcard.copydata((CardmasterCard)e.getSource());
				int quantity = ((CardmasterCard)e.getSource()).quantity;
				label_quantity.setText("Quantity: " + quantity);
				repaint();
				
				
				
			}	
		}
			
	}
		
    
		void updateData() {
			//player data
			label_Ostatus.setText("Locked:" + olock + "/Ready:" + oready);
			label_Sstatus.setText("Locked:" + slock + "/Ready:" + sready);
			
			
			
			label_Otpoints.setText(otpoints + " Points");
			label_Stpoints.setText(stpoints + " Points");
			label_currentpoints.setText(currentpoints + " Points");
			label_currentdeck.setText("Deck #" + currentdeck);
			
		
			
			label_Oname.setText(opponentname);
			label_Sname.setText(playername);
			
			if (slock) button_lock.setText("Unlock");
			else button_lock.setText("Lock");
			
			if (sready) button_ready.setText("Unready");
			else button_ready.setText("Ready");
			
			
			button_ready.setEnabled(olock && slock);
		
			scroll_sdeck.invalidate();
			scroll_sdeck.validate();
			scroll_otable.invalidate();
			scroll_otable.validate();
			scroll_stable.invalidate();
			scroll_stable.validate();
			panel_stable.doLayout();
			panel_otable.doLayout();
			
			panel_sdeck.invalidate();
			panel_sdeck.validate();
			panel_otable.invalidate();
			panel_otable.validate();
			panel_stable.invalidate();
			panel_stable.validate();
						
		
		}
		
	
			CardmasterCard textToCard(String singlecard) { // converts a single card

								
				StringTokenizer tokenizer2 = new StringTokenizer(singlecard,".");
				String first = tokenizer2.nextToken();
				
				
				if (first.toString().equals("f")) {
					CardmasterCard newCard = new CardmasterCard();
					newCard.SetImageManager(manager);
					newCard.dummy = false;
					
					newCard.facedown = true;
					
					String cardtype = tokenizer2.nextToken();
					int dizzyno = Short.parseShort(tokenizer2.nextToken());
					boolean blockdizzy = (dizzyno >=10); 
					if (blockdizzy) dizzyno-=10;
					boolean dizzy = (dizzyno == 1) ? true : false;
					newCard.typecode = cardtype;
					newCard.setDizzy(dizzy);
					newCard.blkDizzy = blockdizzy;
					if (cardtype.equals("m")) {
						newCard.mtype="unk";
					}
						newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase + "/";
  						
					return newCard;
					
					
				}
				else if (!first.toString().equals("b")) {
					int cardid = Short.parseShort(first);
					String cardtype = tokenizer2.nextToken();
					int speed = Short.parseShort(tokenizer2.nextToken());
					int background = Short.parseShort(tokenizer2.nextToken());
					if (cardtype.equals("m")) {
						String cardname = tokenizer2.nextToken();//System.out.println("Found " + cardname);
						int D = Short.parseShort(tokenizer2.nextToken());
						int L = Short.parseShort(tokenizer2.nextToken());
						int G = Short.parseShort(tokenizer2.nextToken());
						int colorcode = Short.parseShort(tokenizer2.nextToken());
						String picture = tokenizer2.nextToken() + ".gif";
						int expansion = Short.parseShort(tokenizer2.nextToken());
						String text = tokenizer2.nextToken();
						boolean targete = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetp = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targets = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetm = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targeta = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetg = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						String targetmtype = tokenizer2.nextToken();
						boolean ability = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						int sacD = Short.parseShort(tokenizer2.nextToken());
						int sacL = Short.parseShort(tokenizer2.nextToken());
						int sacG = Short.parseShort(tokenizer2.nextToken());
						boolean unique = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						int dizzyno = Short.parseShort(tokenizer2.nextToken());
						boolean blockdizzy = (dizzyno >=10); 
						if (blockdizzy) dizzyno-=10;
						boolean dizzy = (dizzyno == 1) ? true : false;
						
						int attack = Short.parseShort(tokenizer2.nextToken());
						int life = Short.parseShort(tokenizer2.nextToken());
						String status = tokenizer2.nextToken();	
						String mtype = tokenizer2.nextToken();
						
						CardmasterCard newCard = new CardmasterCard(cardname, text, picture,"m", targetmtype,
								 targete, targetp, targetm,  targets,  targeta,
								  cardid,  expansion,  colorcode, D,  L,  G,
								 sacD, sacL, sacG,  unique, ability,  attack,  life, mtype);
								 newCard.blkDizzy = blockdizzy;
								 newCard.SetImageManager(manager);
				 	newCard.speed = speed;
				 	newCard.background = background;
				 		newCard.setDizzy(dizzy);
				 		newCard.drawdizzy = false;
				 		newCard.targetg = targetg;
				 		newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase + "/";
  						newCard.statusicons = status;
						return newCard;
						
					}else if (cardtype.equals("e")) {
						String cardname = tokenizer2.nextToken();//System.out.println("Found " + cardname);
						int D = Short.parseShort(tokenizer2.nextToken());
						int L = Short.parseShort(tokenizer2.nextToken());
						int G = Short.parseShort(tokenizer2.nextToken());
						int colorcode = Short.parseShort(tokenizer2.nextToken());
						String picture = tokenizer2.nextToken() + ".gif";
						int expansion = Short.parseShort(tokenizer2.nextToken());
						String text = tokenizer2.nextToken();
						boolean targete = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetp = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targets = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetm = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targeta = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetg = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						String targetmtype = tokenizer2.nextToken();
						boolean ability = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						int sacD = Short.parseShort(tokenizer2.nextToken());
						int sacL = Short.parseShort(tokenizer2.nextToken());
						int sacG = Short.parseShort(tokenizer2.nextToken());
						boolean unique = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						int dizzyno = Short.parseShort(tokenizer2.nextToken());
						boolean blockdizzy = (dizzyno >=10); 
						if (blockdizzy) dizzyno-=10;
						boolean dizzy = (dizzyno == 1) ? true : false;
						int attack = Short.parseShort(tokenizer2.nextToken());
						int life = Short.parseShort(tokenizer2.nextToken());	
						String status = tokenizer2.nextToken();	
						String mtype = tokenizer2.nextToken();
						CardmasterCard newCard = new CardmasterCard(cardname, text, picture,"e", targetmtype,
								 targete, targetp, targetm,  targets,  targeta,
								  cardid,  expansion,  colorcode, D,  L,  G,
								 sacD, sacL, sacG,  unique, ability,  attack,  life);
				 	newCard.SetImageManager(manager);
				 		newCard.setDizzy(dizzy);
				 		newCard.drawdizzy = false;
				 		newCard.targetg = targetg;
				 		newCard.speed = speed;
				 		newCard.statusicons = status;
				 		newCard.background = background;
				 		newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase + "/";
  		
						newCard.blkDizzy = blockdizzy;
						newCard.mtype = mtype;
						return newCard;
						
					}else if (cardtype.startsWith("d")) {
						String cardname = tokenizer2.nextToken();//System.out.println("Found " + cardname);
						int D = Short.parseShort(tokenizer2.nextToken());
						int L = Short.parseShort(tokenizer2.nextToken());
						int G = Short.parseShort(tokenizer2.nextToken());
						int colorcode = Short.parseShort(tokenizer2.nextToken());
						String picture = tokenizer2.nextToken() + ".gif";
						int expansion = Short.parseShort(tokenizer2.nextToken());
						String text = tokenizer2.nextToken();
						boolean targete = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetp = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targets = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetm = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targeta = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetg = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						String targetmtype = tokenizer2.nextToken();
						boolean ability = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						int sacD = Short.parseShort(tokenizer2.nextToken());
						int sacL = Short.parseShort(tokenizer2.nextToken());
						int sacG = Short.parseShort(tokenizer2.nextToken());
						boolean unique = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						int dizzyno = Short.parseShort(tokenizer2.nextToken());
						boolean blockdizzy = (dizzyno >=10); 
						if (blockdizzy) dizzyno-=10;
						boolean dizzy = (dizzyno == 1) ? true : false;
						int attack = Short.parseShort(tokenizer2.nextToken());
						int life = Short.parseShort(tokenizer2.nextToken());	
						String status = tokenizer2.nextToken();	
						String mtype = tokenizer2.nextToken();
						CardmasterCard newCard = new CardmasterCard(cardname, text, picture,cardtype, targetmtype,
								 targete, targetp, targetm,  targets,  targeta,
								  cardid,  expansion,  colorcode, D,  L,  G,
								 sacD, sacL, sacG,  unique, ability,  attack,  life);
				 	newCard.SetImageManager(manager);
				 		newCard.setDizzy(dizzy);
				 		newCard.drawdizzy = false;
				 		newCard.targetg = targetg;
				 		newCard.speed = speed;
				 		newCard.statusicons = status;
				 		newCard.background = background;
				 		newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase + "/";
  		
						newCard.blkDizzy = blockdizzy;
						newCard.mtype = mtype;
						newCard.typecode = cardtype;
						return newCard;
					}else if (cardtype.equals("s")) {
						String cardname = tokenizer2.nextToken();//System.out.println("Found " + cardname);
						int D = Short.parseShort(tokenizer2.nextToken());
						int L = Short.parseShort(tokenizer2.nextToken());
						int G = Short.parseShort(tokenizer2.nextToken());
						int colorcode = Short.parseShort(tokenizer2.nextToken());
						String picture = tokenizer2.nextToken() + ".gif";
						int expansion = Short.parseShort(tokenizer2.nextToken());
						String text = tokenizer2.nextToken();
						boolean targete = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetp = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targets = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetm = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targeta = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetg = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						String targetmtype = tokenizer2.nextToken();
						
						
						CardmasterCard newCard = new CardmasterCard(cardname, text, picture,"s", targetmtype,
								 targete, targetp, targetm,  targets,  targeta,
								  cardid,  expansion,  colorcode, D,  L,  G);
				 
				 		newCard.SetImageManager(manager);
							 		newCard.setDizzy(false);
				 		newCard.speed = speed;
				 		newCard.background = background;
				 		newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase + "/";
  						newCard.targetg = targetg;
						return newCard;
							
						
					}else if (cardtype.equals("l")) {
						String cardname = tokenizer2.nextToken();//System.out.println("Found " + cardname);
						int D = Short.parseShort(tokenizer2.nextToken());
						int L = Short.parseShort(tokenizer2.nextToken());
						int G = Short.parseShort(tokenizer2.nextToken());
						int colorcode = Short.parseShort(tokenizer2.nextToken());
						String picture = tokenizer2.nextToken() + ".gif";
						int expansion = Short.parseShort(tokenizer2.nextToken());
						String text = tokenizer2.nextToken();
						/*boolean targete = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetp = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targets = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetm = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targeta = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetg = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						String targetmtype = tokenizer2.nextToken();*/
						
						
						CardmasterCard newCard = new CardmasterCard(cardname, text, picture,"s", 
								  cardid,  expansion,  colorcode, D,  L,  G);
				 
				 		newCard.SetImageManager(manager);
							 		newCard.setDizzy(false);
				 		newCard.speed = speed;
				 		newCard.background = background;
				 		newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase + "/";
//  						newCard.targetg = targetg;
						return newCard;
						
						
					}
			
				} else{
					CardmasterCard newc = new CardmasterCard();
					newc.SetImageManager(manager);
					 return newc	;
				
				}
				return null;
		
		
	}

	
	CardmasterCard textToCard2(String singlecard) { // converts a single card

									
				StringTokenizer tokenizer2 = new StringTokenizer(singlecard,".");
				String first = tokenizer2.nextToken();
				
				if (!first.toString().equals("b")) {
					int cardid = Short.parseShort(first);
					String cardtype = tokenizer2.nextToken();
						int speed = Short.parseShort(tokenizer2.nextToken());
						int background = Short.parseShort(tokenizer2.nextToken());
					if (cardtype.equals("m")) {
						String cardname = tokenizer2.nextToken();//System.out.println("Found " + cardname);
						int D = Short.parseShort(tokenizer2.nextToken());
						int L = Short.parseShort(tokenizer2.nextToken());
						int G = Short.parseShort(tokenizer2.nextToken());
						int colorcode = Short.parseShort(tokenizer2.nextToken());
						String picture = tokenizer2.nextToken() + ".gif";
						int expansion = Short.parseShort(tokenizer2.nextToken());
						String text = tokenizer2.nextToken();
						boolean targete = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetp = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targets = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetm = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targeta = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetg = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						String targetmtype = tokenizer2.nextToken();
						boolean ability = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						int sacD = Short.parseShort(tokenizer2.nextToken());
						int sacL = Short.parseShort(tokenizer2.nextToken());
						int sacG = Short.parseShort(tokenizer2.nextToken());
						boolean unique = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean dizzy = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						int attack = Short.parseShort(tokenizer2.nextToken());
						int life = Short.parseShort(tokenizer2.nextToken());
							String status = tokenizer2.nextToken();		
						String mtype = tokenizer2.nextToken();
						
						CardmasterCard newCard = new CardmasterCard(cardname, text, picture,"m", targetmtype,
								 targete, targetp, targetm,  targets,  targeta,
								  cardid,  expansion,  colorcode, D,  L,  G,
								 sacD, sacL, sacG,  unique, ability,  attack,  life, mtype);
				 
				 		newCard.setDizzy(dizzy);
				 		newCard.drawdizzy = false;
				 		newCard.speed = speed;
				 		newCard.background = background;
				 		newCard.urlcodebase = getCodeBase().toString();
				 		newCard.SetImageManager(manager);
				 		newCard.urlcodebase = newCard.urlcodebase.substring(0,newCard.urlcodebase.length()) + "/";
  		
						return newCard;
						
					}else if (cardtype.equals("e")) {
						String cardname = tokenizer2.nextToken();//System.out.println("Found " + cardname);
						int D = Short.parseShort(tokenizer2.nextToken());
						int L = Short.parseShort(tokenizer2.nextToken());
						int G = Short.parseShort(tokenizer2.nextToken());
						int colorcode = Short.parseShort(tokenizer2.nextToken());
						String picture = tokenizer2.nextToken() + ".gif";
						int expansion = Short.parseShort(tokenizer2.nextToken());
						String text = tokenizer2.nextToken();
						boolean targete = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetp = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targets = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetm = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targeta = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetg = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						String targetmtype = tokenizer2.nextToken();
						boolean ability = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						int sacD = Short.parseShort(tokenizer2.nextToken());
						int sacL = Short.parseShort(tokenizer2.nextToken());
						int sacG = Short.parseShort(tokenizer2.nextToken());
						boolean unique = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean dizzy = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						int attack = Short.parseShort(tokenizer2.nextToken());
						int life = Short.parseShort(tokenizer2.nextToken());	
							String status = tokenizer2.nextToken();	
						
						CardmasterCard newCard = new CardmasterCard(cardname, text, picture,"e", targetmtype,
								 targete, targetp, targetm,  targets,  targeta,
								  cardid,  expansion,  colorcode, D,  L,  G,
								 sacD, sacL, sacG,  unique, ability,  attack,  life);
				 
				 		newCard.setDizzy(dizzy);
				 		newCard.drawdizzy = false;
				 
				 		newCard.speed = speed;
				 		newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase.substring(0,newCard.urlcodebase.length()) + "/";
  		

				 		newCard.SetImageManager(manager);
						return newCard;
						
					}else if (cardtype.equals("s")) {
						String cardname = tokenizer2.nextToken();//System.out.println("Found " + cardname);
						int D = Short.parseShort(tokenizer2.nextToken());
						int L = Short.parseShort(tokenizer2.nextToken());
						int G = Short.parseShort(tokenizer2.nextToken());
						int colorcode = Short.parseShort(tokenizer2.nextToken());
						String picture = tokenizer2.nextToken() + ".gif";
						int expansion = Short.parseShort(tokenizer2.nextToken());
						String text = tokenizer2.nextToken();
						boolean targete = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetp = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targets = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetm = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targeta = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetg = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						String targetmtype = tokenizer2.nextToken();
						
						
						CardmasterCard newCard = new CardmasterCard(cardname, text, picture,"s", targetmtype,
								 targete, targetp, targetm,  targets,  targeta,
								  cardid,  expansion,  colorcode, D,  L,  G);
				 
				 
							 		newCard.setDizzy(false);
				 		newCard.speed = speed;
				 		newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase.substring(0,newCard.urlcodebase.length()) + "/";
  						newCard.targetg = targetg;
  						
				 		newCard.SetImageManager(manager);
						return newCard;
						
						
					}else if (cardtype.equals("l")) {
						String cardname = tokenizer2.nextToken();//System.out.println("Found " + cardname);
						int D = Short.parseShort(tokenizer2.nextToken());
						int L = Short.parseShort(tokenizer2.nextToken());
						int G = Short.parseShort(tokenizer2.nextToken());
						int colorcode = Short.parseShort(tokenizer2.nextToken());
						String picture = tokenizer2.nextToken() + ".gif";
						int expansion = Short.parseShort(tokenizer2.nextToken());
						String text = tokenizer2.nextToken();
						/*boolean targete = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetp = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targets = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetm = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targeta = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetg = (Short.parseShort(tokenizer2.nextToken()) == 1) ? true : false;
						String targetmtype = tokenizer2.nextToken();*/
						
						
						CardmasterCard newCard = new CardmasterCard(cardname, text, picture,"s", 
								  cardid,  expansion,  colorcode, D,  L,  G);
				 
				 
							 		newCard.setDizzy(false);
				 		newCard.speed = speed;
				 		newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase.substring(0,newCard.urlcodebase.length()) + "/";
//  						newCard.targetg = targetg;


				 		newCard.SetImageManager(manager);
						return newCard;
						
						
					}
			
				} else {
					CardmasterCard newCard = new CardmasterCard();
					
				 		newCard.SetImageManager(manager);
					
					return newCard;
					}		
				return null;
		
		
	}
	
	void updateUnknowns() {
		for (int i =0;i < sdeck.length; i++) {
				if (sdeck[i].dataBlank && carddata[sdeck[i].cardid] != null) {
					
					sdeck[i].copydata(carddata[sdeck[i].cardid]);
					sdeck[i].dataBlank= false;
					askdatacounter = -1;
					sdeck[i].repaint();
				}
			
			
			
		}	
		
			for (int i =0;i < otable.length; i++) {
				if (otable[i].dataBlank && carddata[otable[i].cardid] != null) {
					
					otable[i].copydata(carddata[otable[i].cardid]);
					otable[i].dataBlank= false;
					askdatacounter = -1;
					otable[i].repaint();
				}
			
			
			
		}	
				for (int i =0;i < stable.length; i++) {
				if (stable[i].dataBlank && carddata[stable[i].cardid] != null) {
					
					stable[i].copydata(carddata[stable[i].cardid]);
					stable[i].dataBlank= false;
					askdatacounter = -1;
					stable[i].repaint();
				}
			
			
			
		}	
	}		
	CardmasterCard createLoadableCard(int cardid) {
		return createLoadableCard(cardid,true);
	}
	CardmasterCard createLoadableCard(int cardid, boolean ask)  {
		if (carddata[cardid] == null) {
			System.out.println(cardid + " UNKNOWN ");
		
			CardmasterCard newCard = new CardmasterCard();
			//newCard.dummy = false;
			newCard.dataBlank = true;
			newCard.cardid = cardid;
			if (! askdata[cardid] && ask) {
				System.out.println("Asking about it..");
				out.println("WH#" + cardid + "#");
			
			}
			else {
				System.out.println("Already asked about it..");
			}
			askdata[cardid] = true;
			askdatacounter = 100;
			
			return newCard;
		}
		else {
			CardmasterCard newCard = new CardmasterCard();
			newCard.copydata(carddata[cardid]);
			return newCard;
		}
			
		
		
	}
		
			
		
		
		
        public void actionPerformed(ActionEvent evt){ 
			if (evt.getSource() == button_ready) {
				if (sready) {
					out.println("TRADER#0#");
				}
				else { out.println("TRADER#1#"); }
					
				
			}
			else if (evt.getSource() == button_lock) {
				if (slock) {
					out.println("TRADEL#0#");
				}
				else { out.println("TRADEL#1#"); }
					
				
			}
        	else if (evt.getSource() == chat_enter) {
        		if (!(chat_enter.getText().equals(""))) {
	        		out.println("TM#" + playername + "#" + chat_enter.getText().replace('#',' ') + "#");
	        		chat_enter.setText("");
	        	}
        	}
        	
        

        	else if (evt.getSource() == button_addpoints) {
        		try {
        			int amount = Short.parseShort(text_points.getText());
        			out.println("ADDP#" + amount + "#");	
        			
        			
        		} catch (NumberFormatException e) {
        				
        				
        		}	
        		
        		
        	}
        	else if (evt.getSource() == combo_decks) {
        		 String deck = (String)(combo_decks.getSelectedItem());
        		 out.println("DSWITCH#" + deck + "#");	
        		
        		
        	}
        	else if (evt.getSource() == button_takepoints) {
        		try {
        			int amount = -1 * Short.parseShort(text_points.getText());
        			out.println("ADDP#" + amount + "#");	
        			
        			
        		} catch (NumberFormatException e) {
        				
        				
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
						if (command.equals("TRCHA")) {
							String name = tokenizer.nextToken("#");
							chat_log.append("<" + name + ">" + tokenizer.nextToken("#") + "\n");
							chat_log.setText(chat_log.getText());
							
						} 
						else if (command.equals("TRDONE")) {
							
							getAppletContext().showDocument(new URL(getCodeBase().toString() + "page.jsp?pageurl=tradedone.html"));
							
						
						} 
						
						
						else if (command.equals("TRADEINIT")) {
							String temp = tokenizer.nextToken();
							String temp2 = tokenizer.nextToken();
							if (temp.equals(playername)) {
								opponentname = temp2;	
								playern = 1;
								
								
							}
							else if (temp2.equals(playername)) {
								opponentname = temp;
								playern = 2;	
								
							}
							else { chat_log.append("* General Error\n");
							System.exit(1);
							}
						
								chat_enter.setEditable(true);
								button_addpoints.setEnabled(true);
								button_takepoints.setEnabled(true);
								button_lock.setEnabled(true);
								
								chat_log.append("* " + opponentname + " is ready to trade!\n");
								chat_log.setText(chat_log.getText());
								out.println("GETDATA#");
							
						}	    		
			    		
						else if (command.equals("PNG")) {
							
							out.println("PN#");
							
						}			    		
						else if (command.equals("YDECKS")) { // your deck list
							decklist = new String[0];
							
							while (tokenizer.hasMoreTokens()) {
								String newDeck = tokenizer.nextToken();
								
								String[] temp = new String[decklist.length +1 ];
								System.arraycopy(decklist,0,temp,0,decklist.length);
								temp[decklist.length] = newDeck;
								decklist = new String[decklist.length+1];
								System.arraycopy(temp,0,decklist,0,decklist.length);
								
								
							}
							updateData();
			combo_decks = new JComboBox(decklist);
			combo_decks.addActionListener(this);
			combo_decks.setSelectedItem(currentdpointer);
			
			System.out.println("DECK LIST:");
			for (int i=0;i<decklist.length;i++) {
				System.out.print(decklist[i] + " ");
			}
			System.out.println("^^^^");							
		panel_sdata.add(new JLabel("Deck:"));
		panel_sdata.add(combo_decks);							
						}
						
						
						
				
						
					
						
						
						
						else if (command.equals("YOURDECK")) { // PHASE STATUS
							String deckno = tokenizer.nextToken("#");
							
							currentdeck = Integer.parseInt(deckno);
							currentpoints = Integer.parseInt(tokenizer.nextToken("#"));
							System.out.println("Removing all from deck");
							panel_sdeck.removeAll();
							for(int i=0;i<decklist.length;i++) {
								if (decklist[i].equals(currentdeck + "")) {
									currentdpointer = decklist[i];
									
									break;	
								}	
								
							}
							
							sdeck = new CardmasterCard[0];
							if (!tokenizer.hasMoreTokens()) {
								System.out.println("This got called bad");
									
							}
							while (tokenizer.hasMoreTokens()) {
							StringTokenizer quanttokenizer = new StringTokenizer(tokenizer.nextToken(),"x");
									int cardid = Short.parseShort(quanttokenizer.nextToken());
									int quantity = Short.parseShort(quanttokenizer.nextToken());
							//	for (int i=0;i<quantity;i++) {
									
									CardmasterCard newCard = createLoadableCard(cardid);
								//	CardmasterCard newCard = textToCard(tokenizer.nextToken());
									newCard.urlcodebase = getCodeBase().toString();
							 		newCard.urlcodebase = newCard.urlcodebase.substring(0,newCard.urlcodebase.length()) + "/";
	  								
				 		newCard.SetImageManager(manager);
									
									CardmasterCard[] temp = new CardmasterCard[sdeck.length];
									System.arraycopy(sdeck,0,temp,0,sdeck.length);
									sdeck = new CardmasterCard[sdeck.length+1];
									System.arraycopy(temp,0,sdeck,0,temp.length);
									newCard.setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
									newCard.quantity = quantity;
									sdeck[sdeck.length-1] = newCard;
									
									panel_sdeck.add(sdeck[sdeck.length-1]);
									//System.out.println(sdeck.length);
									sdeck[sdeck.length-1].addMouseListener(new cardMouseEvents());
									updateData();
							//	}
								
							}
							
							
							updateData();
							
						}
						
						
						
						
						
						else if (command.equals("CDD")) { // card data
							int cardid = Short.parseShort(tokenizer.nextToken());
							System.out.println("Received data for " + cardid);
							String cardData = tokenizer.nextToken();	
							carddata[cardid] = textToCard(cardData);
							updateUnknowns();
							
							
							
							
							
						}
						
						else if (command.equals("TABLE")) { // TABLE STATUS
							
							
					panel_otable.removeAll();
					panel_stable.removeAll();
					panel_otable.add(new CardmasterCard());
					panel_stable.add(new CardmasterCard());
						stable = new CardmasterCard[0];
		otable = new CardmasterCard[0];
					repaint();
					updateData();
							String splayer = "P" + playern;
							String oplayer = "P1";
							if (playern == 1) {
								splayer = "P1";
								oplayer = "P2";
							}
							else if (playern == 2) {
								splayer = "P2";
								oplayer = "P1";
							}
							else return;
							String tokenize;
							CardmasterCard[] tabl = new CardmasterCard[0];
							int playerc = 0;
							JPanel panel = new JPanel();
							while (tokenizer.hasMoreTokens()) {
								tokenize = tokenizer.nextToken("#");
								//System.out.println("Tokenizing " + tokenize);
								if (tokenize.equals(splayer)) {
									//System.out.println("it's me");
									tabl = new CardmasterCard[0];
									playerc = 1;
									panel = panel_stable;
									
									System.out.println(tokenizer.nextToken("#")); // name
									int points = Integer.parseInt(tokenizer.nextToken("#"));
									int locked = Short.parseShort(tokenizer.nextToken("#"));
									int ready = Short.parseShort(tokenizer.nextToken("#"));
									
									
										stpoints = points;
										if (locked ==1) slock = true; else slock = false;
										if (ready ==1) sready = true; else sready = false;
											label_Stpoints.setText(stpoints + " Points");
										panel_stable.removeAll();
									panel_stable.repaint();
										
											
		
									
									
								}
								else if (tokenize.equals(oplayer)) {
									//System.out.println("It's the other player");
									tabl = new CardmasterCard[0];
									playerc = 2;
									System.out.println(tokenizer.nextToken("#")); // name
									panel = panel_otable;
									
									int points = Integer.parseInt(tokenizer.nextToken("#"));
									int locked = Short.parseShort(tokenizer.nextToken("#"));
									int ready = Short.parseShort(tokenizer.nextToken("#"));
									
										otpoints = points;
										if (locked ==1) olock = true; else olock = false;
										if (ready ==1) oready = true; else oready = false;										
										
									label_Otpoints.setText(otpoints + " Points");	
									panel_otable.removeAll();
									panel_otable.repaint();	
										
																		
									
									
									
									
								}
								else {
									//System.out.println("Card for " + playerc);
									StringTokenizer quanttokenizer = new StringTokenizer(tokenize,"x");
									int cardid = Short.parseShort(quanttokenizer.nextToken());
									int quantity = Short.parseShort(quanttokenizer.nextToken());
								//	for (int i=0;i<quantity;i++) {		
										CardmasterCard newCard = createLoadableCard(cardid);		
										newCard.urlcodebase = getCodeBase().toString();
								 		newCard.urlcodebase = newCard.urlcodebase.substring(0,newCard.urlcodebase.length()) + "/";
		  		
				 		newCard.SetImageManager(manager);
									//	CardmasterCard newCard = textToCard(tokenize);
										CardmasterCard[] temp = new CardmasterCard[tabl.length];
										System.arraycopy(tabl,0,temp,0,tabl.length);
										tabl = new CardmasterCard[tabl.length+1];
										System.arraycopy(temp,0,tabl,0,temp.length);
										newCard.setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
										newCard.quantity = quantity;
										tabl[tabl.length-1] = newCard;									
										tabl[tabl.length-1].addMouseListener(new cardMouseEvents());
										
										
										
										if (playerc == 1) stable = tabl;
										else if (playerc == 2) otable = tabl;	
										
										
										
										updateData();
									//}	
											
								}
								updateData();
								
								
								
								
								
							}
							System.out.println("No more tokens");
								for (int i=0;i<stable.length;i++)
									panel_stable.add(stable[i]);
								for (int i=0;i<otable.length;i++)
									panel_otable.add(otable[i]);
							
							updateData();
							System.out.println("End of tokenization of TABLE");
							
						}								
			    	}
			    }
		
		
		
		
			
			    		
			    }catch (IOException e) {
			    	e.printStackTrace();
		            System.err.println("Couldn't get I/O for the connection");
		            		
	    		}	
    		} 
    		
			
   
   

	 
	
		}


}
