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


public class CardmasterObserverClient extends JApplet implements ActionListener  {
	
	  Timer timer;

		Socket chatSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String fromServer;
        String fromUser;
        String chattext;
        String playername;
        String opponentname;
        String  myname;
        static int protocolnum = 20;
        static String version="a2.020-" + protocolnum;
        String status_text;
        String action;
         String lastcardstring;
        boolean askdata[];
       	JPanel panel_hand;
       	JPanel panel_opdata;
       	JPanel panel_opmonster;
       	JPanel panel_opeffect;
       	JPanel panel_semonster;
       	JPanel panel_seeffect;
       	JPanel panel_card;
       	JPanel panel_sedata;
       	JPanel panel_chat;
       	JPanel panel_top;
       	JPanel panel_grave;
       	JScrollPane scroll_grave;
       	JLabel label_gamemessage;


		JLabel label_Omana;
		JLabel label_Smana;
		JLabel label_Olife;
		JLabel label_Slife;
		JLabel label_Ophase;
		JLabel label_Sphase;
		JLabel label_Oname;
		JLabel label_Sname;
		JLabel label_Sdamage;
		JLabel label_Odamage;
		JButton button_nextphase;
		JButton button_ability;
		JButton button_graveyard;
		JButton button_up;
		JButton button_down;
		
       	
       	CardmasterCard hand[];
       	CardmasterCard Ohand[];
       	CardmasterCard Smonster[];
       	CardmasterCard Seffect[];
       	CardmasterCard Omonster[];
       	CardmasterCard Oeffect[];
       	CardmasterCard bigcard;
       	
       	CardmasterCard h1[];
       	CardmasterCard h2[];
       	CardmasterCard m1[];
       	CardmasterCard e1[];
       	CardmasterCard m2[];
       	CardmasterCard e2[];

		CardmasterCard carddata[];
		CardmasterCard[] grave;
		int attackdata[];
		int attacklock[];
		CardmasterCard selectedcard;
		int handid;
        	
  		int slife;
  		int olife;
  		int sd;
  		int sl;
  		int sg;
  		int od;
  		int ol;
  		int og;
  		int cardstodiscard = 0;
  		int sdeck;
  		int odeck;
  		int handcardsO = 0;
  		int handcardsS = 0;
  		String abilityfrom;
  		
  		int playernumber;
  		boolean can_play;
  		boolean can_attack;
  		boolean can_defend;
  		boolean can_sac;
  		boolean must_discard;
 
 
 		boolean pick_card_for_ability;
 		boolean pick_card_for_attack;
 		boolean pick_card_for_sac;
 		boolean pick_card_for_defend;
 		boolean pick_target_for_ability;
 		boolean pick_target_for_spell;
 		boolean pick_target_for_attack;
 		boolean pick_target_for_defend;
 		boolean pick_target_for_play_monster;
 		boolean pick_target_for_play_effect;
 		boolean pick_card_to_play;
 
 		boolean sshowdamage = false;
 		boolean oshowdamage = false;		
 		int sdamage = 0;
 		int odamage = 0;
 		String[] messagebuffer;
		int currentmessage;
  		int playersturn; // 1 =self 2 = opponent
  		int phase; // 0 cleanup-draw 1 sac 2 play 3 attack 4 defend 5 resolve
       	
  //CHAT BOX
  	
		JScrollPane chat_log_scroll;
  		JTextArea chat_log;
  		JTextField chat_enter;

    void newMessage(String message) {
    	for (int i=28;i>=0;i--) {
    		messagebuffer[i+1] = messagebuffer[i];  	
    		
    	}
    	messagebuffer[0] = message;
    	currentmessage = 0;
    	updateMessage();
    }

    void updateMessage() {
    	//System.out.println(currentmessage);
    	label_gamemessage.setText(messagebuffer[currentmessage]);
    }
	static int NUMCARDS = 500;
	public void init() {
		attackdata = new int[5];
		attacklock = new int[5];
		hand = new CardmasterCard[8];
		Ohand = new CardmasterCard[8];
		Smonster = new CardmasterCard[5];
		Omonster = new CardmasterCard[5];
		Seffect = new CardmasterCard[5];
		Oeffect = new CardmasterCard[5];
		carddata = new CardmasterCard[NUMCARDS];
		askdata = new boolean[NUMCARDS];
		for (int i = 0; i<NUMCARDS; i++) {
			askdata[i] = false;	
			
		}
		carddata[0] = new CardmasterCard();
		grave = new CardmasterCard[0];
		bigcard = new CardmasterCard();
		currentmessage= 0;
		messagebuffer = new String[30];
		for (int i = 0;i<30;i++) {
			messagebuffer[i] = "";
		}
		
		for (int i=0;i<5;i++) {
			Smonster[i] = new CardmasterCard();
			Omonster[i] = new CardmasterCard();
			Seffect[i] = new CardmasterCard();
			Oeffect[i] = new CardmasterCard();
			
		}
		for (int i=0;i<8;i++) {
			hand[i] = new CardmasterCard();
			Ohand[i] = new CardmasterCard();
		}				
		int roomno = 0;
		try {
			String address = getParameter("address");
			 roomno = Integer.parseInt(getParameter("number"));
			
            chatSocket = new Socket(address, 4170);
            out = new PrintWriter(chatSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(chatSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host CMC Server");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to CMC server");
            System.exit(1);
        }

         myname = getParameter("myname");
   		out.println("OB#"+roomno+"#" + myname +"#"); 
        GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH; 

		getContentPane().setLayout(gridbag);


		
		panel_hand = new JPanel();
		panel_hand.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel_hand.setMinimumSize(new Dimension((int)(getWidth()/(620.0/150.0)),(int)(getHeight()/(420.0/300.0))));
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 4;
		c.weighty = 1;
		gridbag.setConstraints(panel_hand, c);
		getContentPane().add(panel_hand);

		panel_opdata = new JPanel();
		panel_opdata.setLayout(new GridLayout(0,1));
			panel_opdata.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.weighty = 1;
		gridbag.setConstraints(panel_opdata, c);
		getContentPane().add(panel_opdata);		


		panel_opmonster = new JPanel();
		panel_opmonster.setMinimumSize(new Dimension((int)(getWidth()/(620.0/300.0)),(int)(getHeight()/(420.0/70.0))));
		panel_opmonster.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.weighty = 1;
		gridbag.setConstraints(panel_opmonster, c);	
		getContentPane().add(panel_opmonster);



		panel_opeffect = new JPanel();
		panel_opeffect.setMinimumSize(new Dimension((int)(getWidth()/(620.0/300.0)),(int)(getHeight()/(420.0/70.0))));
		panel_opeffect.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		c.gridx = 2;
		c.gridy = 2;
		c.gridheight = 1;
		c.weighty = 1;
		gridbag.setConstraints(panel_opeffect, c);
		getContentPane().add(panel_opeffect);

		panel_seeffect = new JPanel();
		panel_seeffect.setMinimumSize(new Dimension((int)(getWidth()/(620.0/300.0)),(int)(getHeight()/(420.0/70.0))));
		c.gridx = 2;
		c.gridy = 3;
		c.gridheight = 1;
		c.weighty = 1;
		gridbag.setConstraints(panel_seeffect, c);
		panel_seeffect.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		getContentPane().add(panel_seeffect);

		panel_sedata = new JPanel();
		c.gridx = 1;
		c.gridy = 4;
		c.gridheight = 1;
		c.weighty = 1;
		gridbag.setConstraints(panel_sedata, c);
		panel_sedata.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		getContentPane().add(panel_sedata);
		panel_sedata.setLayout(new GridLayout(0,1));
		
		panel_semonster = new JPanel();
		panel_semonster.setMinimumSize(new Dimension((int)(getWidth()/(620.0/300.0)),(int)(getHeight()/(420.0/70.0))));
		c.gridx = 2;
		c.gridy = 4;
		c.gridheight = 1;
		c.weighty = 1;
		gridbag.setConstraints(panel_semonster, c);
		panel_semonster.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		getContentPane().add(panel_semonster);

		panel_card = new JPanel();
		panel_card.setBorder(BorderFactory.createEtchedBorder());
		panel_card.setMinimumSize(new Dimension((int)(getWidth()/(620.0/110.0)),(int)(getHeight()/(420.0/140.0))));
		panel_card.setMaximumSize(new Dimension((int)(getWidth()/(620.0/110.0)),(int)(getHeight()/(420.0/140.0))));
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 2;
		c.weighty = .5;
		gridbag.setConstraints(panel_card, c);
		getContentPane().add(panel_card);
		



		panel_grave = new JPanel();
		panel_grave.setLayout(new GridLayout(0,2));
		scroll_grave = new JScrollPane(panel_grave);
		scroll_grave.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_grave.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll_grave.setMinimumSize(new Dimension((int)(getWidth()/(620.0/150.0)),(int)(getHeight()/(420.0/80.0))));
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		gridbag.setConstraints(scroll_grave, c);
		//panel_chat.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(scroll_grave);


		panel_chat = new JPanel();
		panel_chat.setMinimumSize(new Dimension((int)(getWidth()/(620.0/450.0)),(int)(getHeight()/(420.0/80.0))));
		c.gridx = 1;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.weightx = 1;


		gridbag.setConstraints(panel_chat, c);
		//panel_chat.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(panel_chat);



		panel_top = new JPanel();
		panel_top.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()/(420.0/10.0))));
		panel_top.setMaximumSize(new Dimension(getWidth(),(int)(getHeight()/(420.0/10.0))));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.weighty = 0;		
		gridbag.setConstraints(panel_top, c);
		getContentPane().add(panel_top);
		
		label_gamemessage = new JLabel(messagebuffer[currentmessage]);
		button_nextphase = new JButton("Next");
		button_ability = new JButton("Ability");
		button_graveyard = new JButton("Graveyard");
		label_gamemessage.setPreferredSize(new Dimension((int)(getWidth()/(620.0/345.0)),(int)(getHeight()/(420.0/20.0))));
		button_graveyard.setPreferredSize(new Dimension((int)(getWidth()/(620.0/95.0)), (int)(getHeight()/(420.0/20.0))));
		button_nextphase.setPreferredSize(new Dimension((int)(getWidth()/(620.0/85.0)), (int)(getHeight()/(420.0/20.0))));
		button_ability.setPreferredSize(new Dimension((int)(getWidth()/(620.0/90.0)), (int)(getHeight()/(420.0/20.0))));
		button_up = new JButton("/\\");
		button_down = new JButton("\\/	");
		button_up.setPreferredSize(new Dimension((int)(getWidth()/(620.0/50.0)), (int)(getHeight()/(420.0/20.0))));
		button_down.setPreferredSize(new Dimension((int)(getWidth()/(620.0/50.0)), (int)(getHeight()/(420.0/20.0))));
		
		panel_top.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));	
	//	panel_top.add(button_graveyard);
		panel_top.add(button_nextphase);
		panel_top.add(button_ability);
		panel_top.add(label_gamemessage);
		panel_top.add(button_up);
		panel_top.add(button_down);
		button_down.setEnabled(false);
		button_up.addActionListener(this);
		button_down.addActionListener(this);
	//	button_nextphase.addActionListener(this);
	//	button_ability.addActionListener(this);
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
		bigcard.setBorder(BorderFactory.createLineBorder(Color.black));
		bigcard.setPreferredSize(new Dimension((int)(getWidth()/(620.0/100.0)),(int)(getHeight()/(420.0/140.0))));
		panel_card.add(bigcard);
		
		
		label_Oname = new JLabel("Name");
		label_Sname = new JLabel("Name");
		label_Sdamage = new JLabel("");
		label_Odamage = new JLabel("");
		label_Olife = new JLabel("300");
		label_Slife = new JLabel("300");
		label_Ophase = new JLabel("No Turn");
		label_Sphase = new JLabel("No Turn");
		label_Omana = new JLabel("0D 0L 0G");
		label_Smana = new JLabel("0D 0L 0G");
		
		panel_opdata.add(label_Oname);
		panel_opdata.add(label_Odamage);
		panel_opdata.add(label_Olife);
		panel_opdata.add(label_Omana);
		panel_opdata.add(label_Ophase);
		
		panel_sedata.add(label_Sname);
		panel_sedata.add(label_Sdamage);
		panel_sedata.add(label_Slife);
		panel_sedata.add(label_Smana);
		panel_sedata.add(label_Sphase);
		
		button_nextphase.setEnabled(false);
		button_ability.setEnabled(false);
		button_graveyard.setEnabled(false);
		
		
	//	panel_semonster.setLayout(new GridLayout(1,5));
	//	panel_opmonster.setLayout(new GridLayout(1,5));
	//	panel_seeffect.setLayout(new GridLayout(1,5));
	//	panel_opeffect.setLayout(new GridLayout(1,5));
	//	panel_hand.setLayout(new GridLayout(4,2));
		//panel_opdata.addMouseListener(new cardMouseEvents());
		//panel_sedata.addMouseListener(new cardMouseEvents());
		for (int i = 0; i <5; i++) {
			Smonster[i].setBorder(BorderFactory.createLineBorder(Color.black));
			Smonster[i].setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
			Omonster[i].setBorder(BorderFactory.createLineBorder(Color.black));
			Omonster[i].setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
			Seffect[i].setBorder(BorderFactory.createLineBorder(Color.black));
			Seffect[i].setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
			Oeffect[i].setBorder(BorderFactory.createLineBorder(Color.black));
			Oeffect[i].setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
			Oeffect[i].addMouseListener(new cardMouseEvents());
			Seffect[i].addMouseListener(new cardMouseEvents());
			Omonster[i].addMouseListener(new cardMouseEvents());
			Smonster[i].addMouseListener(new cardMouseEvents());

			panel_semonster.add(Smonster[i]);
			panel_opeffect.add(Oeffect[i]);
			panel_opmonster.add(Omonster[i]);
			panel_seeffect.add(Seffect[i]);
	
		}		
		for (int i = 0; i <8; i++) {	
			hand[i].setBorder(BorderFactory.createLineBorder(Color.black));
			hand[i].setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
			panel_hand.add(hand[i]);
			hand[i].drawdizzy = false;
		//	hand[i].addMouseListener(new cardMouseEvents());
		}			
		
		timer = new Timer(200,this);
		timer.start();


	bigcard.urlcodebase = getCodeBase().toString();
  	//	bigcard.urlcodebase = bigcard.urlcodebase.substring(0,bigcard.urlcodebase.length()-3) + "/";
  		bigcard.urlcodebase = bigcard.urlcodebase.substring(0,bigcard.urlcodebase.length()-3) + "/";
  	
  		bigcard.drawlarge = true;
  	newMessage("Waiting for Player");
  	newMessage("Client version " + version);
		
	}

// 	out.println to send to the file

 class cardMouseEvents implements MouseListener {
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() != bigcard && e.getSource() != panel_opdata &&
				e.getSource() != panel_sedata) {
				bigcard.copydata((CardmasterCard)e.getSource());
				repaint();
				
				
				
			}	
		}	
			 public void mousePressed(MouseEvent e) { 
			 return;}
			 public void mouseClicked(MouseEvent e) { 
			 return;}
			  public void mouseExited(MouseEvent e) { 
			 return;}
			  public void mouseReleased(MouseEvent e) { 
			 return;}
	}	
		
		void turn(String turnplayer, int turnphase, int turnno) {
				int newplayersturn;
				String messagestring = turnplayer + "'s ";
				if (turnplayer.equals(playername)) {
					playersturn = 1;
				}
				else {
					 playersturn = 2;
				
				}
			String sphase = "Draw Phase";
			if (turnphase==0) {
				sphase = "Draw Phase";
					
				
				for (int i=0;i<5;i++) {
					Omonster[i].showdamage = false;
					Smonster[i].showdamage = false;
					Smonster[i].damagetaken = 0;
					Omonster[i].damagetaken = 0;
					
					
				}
				sshowdamage = false;
				oshowdamage = false;
				odamage = 0;
				sdamage = 0;	
									
			}else if (turnphase==1) {
				
				sphase = "Sacrifice Phase";	
				
			}else if (turnphase==2){
			
				sphase = "Play Phase";		
			}else if (turnphase==3) {
				for (int i =0;i<5;i++){ attacklock[i] = 0; attackdata[i] = 0; }
				
				
				sphase = "Attack Phase";	
			}else if (turnphase==4){
				if (turnplayer.equals(playername)) messagestring = opponentname + "'s ";
				else messagestring = playername + "'s ";
				sphase = "Defend Phase";	
			}else if (turnphase==5) {
				sphase = "Attack Resolution";
				
				for (int i=0;i<5;i++) {
					Omonster[i].showdamage = true;
					Smonster[i].showdamage = true;
				
					
					
				}
					sshowdamage = true;
					oshowdamage = true;	
				
		}
				messagestring = messagestring + sphase;
				
				if (phase != turnphase || turnno == 1) newMessage(messagestring);
				phase = turnphase;
			
				updateData();
			
		}
    void mustdiscard(int player) {
    	if (playernumber == player) {
    		newMessage("Pick a card to discard.");	
    		cardstodiscard++;
    	
    	
    		set_flags();
    		must_discard = true;
    	
    }
    } 

		void set_flags() {
				can_attack = false;
				can_sac = false;
				can_defend = false;
				//must_discard = false;
				pick_card_for_ability = false;
				pick_card_for_attack = false;
				pick_card_for_defend = false;
				pick_card_for_sac = false;
				pick_card_to_play = false; 
				pick_target_for_ability = false;
				pick_target_for_spell = false;
				pick_target_for_attack = false;
				pick_target_for_defend = false;
				pick_target_for_play_monster = false;
				pick_target_for_play_effect = false;	
	}
		void updateData() {
			//player data
			selectedcard = null;
			label_Omana.setText("Mana:" + od + "D " + ol + "L " + og + "G");
			label_Smana.setText("Mana:" + sd + "D " + sl + "L " + sg + "G");
			label_Olife.setText("Life:" + olife + " Deck:" + odeck);
			label_Slife.setText("Life:" + slife + " Deck:" + sdeck);
			
			int maxchar = 20;
			if (opponentname.length() < maxchar) maxchar = opponentname.length();
			label_Oname.setText(opponentname.substring(0,maxchar));
			maxchar = 20;
			if (playername.length() < maxchar) maxchar = playername.length();
			label_Sname.setText(playername.substring(0,maxchar));
			if (oshowdamage && odamage > 0) label_Odamage.setText("Hand: " + handcardsO + "Damage: " + odamage);
			else label_Odamage.setText("Hand: " + handcardsO);
	
			if (sshowdamage && sdamage > 0) label_Sdamage.setText("Hand: " + handcardsO + "Damage: " + sdamage);
			else label_Sdamage.setText("Hand: " + handcardsS);
			String ophase = "No Phase";
			String sphase = "No Phase";
			if ((playersturn == 1)&&(phase==0)) {
				ophase = "Cleanup Phase";
				sphase = "Draw Phase";
				button_nextphase.setEnabled(true);	
				
			}else if ((playersturn == 1)&&(phase==1)) {
				ophase = "No Phase";
				sphase = "Sacrifice Phase";	
				button_nextphase.setEnabled(true);
				set_flags();
				 pick_card_for_sac = true;

				
			}else if ((playersturn == 1)&&(phase==2)) {
				ophase = "No Phase";
				sphase = "Play Phase";
				set_flags();
				pick_card_to_play = true;	
				button_nextphase.setEnabled(true);	
			}else if ((playersturn == 1)&&(phase==3)) {
				ophase = "No Phase";
				sphase = "Attack Phase";
				set_flags();
				pick_card_for_attack = true;
				button_nextphase.setEnabled(true);	
			}else if ((playersturn == 1)&&(phase==4)) {
				ophase = "Defend Phase";
				sphase = "No Phase";
				set_flags();	
				button_nextphase.setEnabled(false);
			}else if ((playersturn == 1)&&(phase==5)) {
				ophase = "Attack Resolution";
				sphase = "Attack Resolution";
				set_flags();
				button_nextphase.setEnabled(true);
				
			}else if ((playersturn == 2)&&(phase==0)) {
				sphase = "Cleanup Phase";
				ophase = "Draw Phase";	
				set_flags();
				button_nextphase.setEnabled(false);
				
			}else if ((playersturn == 2)&&(phase==1)) {
				sphase = "No Phase";
				ophase = "Sacrifice Phase";
				set_flags();	
				button_nextphase.setEnabled(false);
				
			}else if ((playersturn == 2)&&(phase==2)) {
				sphase = "No Phase";
				ophase = "Play Phase";	
				set_flags();
				button_nextphase.setEnabled(false);	
			}else if ((playersturn == 2)&&(phase==3)) {
				sphase = "No Phase";
				ophase = "Attack Phase";
				set_flags();	
				button_nextphase.setEnabled(false);
			}else if ((playersturn == 2)&&(phase==4)) {
				sphase = "Defend Phase";
				ophase = "No Phase";	
				set_flags();
				pick_card_for_defend = true;
				button_nextphase.setEnabled(true);
			}else if ((playersturn == 2)&&(phase==5)) {
				sphase = "Attack Resolution";
				ophase = "Attack Resolution";
				set_flags();	
				button_nextphase.setEnabled(false);
			}
			
			label_Ophase.setText(ophase);
			label_Sphase.setText(sphase);
					
		// Highlight playable cards.
erase_highlights();
			
			
			
		}
		
				
		void erase_highlights() {
			for (int i =0; i<grave.length;i++) {
			grave[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));	
				
			}
			for (int i = 0; i<5; i++) {
				Smonster[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));
				Omonster[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));
				Seffect[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));
				Oeffect[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));	
				hand[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));
				Ohand[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));
			}
			for (int i = 5; i<8; i++) {
				hand[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));
				Ohand[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));
			}
			panel_sedata.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			panel_opdata.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));	
		
		}
		
			
		void bigcardspell(String datastring) {
			StringTokenizer tokenizer2 = new StringTokenizer(datastring,".");
			String first = tokenizer2.nextToken();
				
			if (!first.toString().equals("b")) {
				int cardid = Integer.parseInt(first);
				String cardtype = tokenizer2.nextToken();
			
			
			String cardname = tokenizer2.nextToken();//stem.out.println("Found " + cardname);
						int D = Integer.parseInt(tokenizer2.nextToken());
						int L = Integer.parseInt(tokenizer2.nextToken());
						int G = Integer.parseInt(tokenizer2.nextToken());
						int colorcode = Integer.parseInt(tokenizer2.nextToken());
						String picture = tokenizer2.nextToken() + ".gif";
						int expansion = Integer.parseInt(tokenizer2.nextToken());
						String text = tokenizer2.nextToken();
						boolean targete = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetp = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targets = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetm = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targeta = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetg = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						
						String targetmtype = tokenizer2.nextToken();
						
						
						CardmasterCard newCard = new CardmasterCard(cardname, text, picture,"s", targetmtype,
								 targete, targetp, targetm,  targets,  targeta,
								  cardid,  expansion,  colorcode, D,  L,  G);
				 
				 
							 		newCard.setDizzy(false);
				 		
				 		newCard.urlcodebase = getCodeBase().toString();newCard.urlcodebase = newCard.urlcodebase.substring(0,newCard.urlcodebase.length()-3) + "/";newCard.urlcodebase = newCard.urlcodebase.substring(0,newCard.urlcodebase.length()-3) + "/";
						bigcard.copydata(newCard);
						bigcard.targetg = targetg;
						repaint();
					//	bigcard.urlcodebase = getCodeBase().toString();
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
			
			return newCard;
		}
		else {
			CardmasterCard newCard = new CardmasterCard();
			newCard.copydata(carddata[cardid]);
			return newCard;
		}
			
		
		
	}
	
	CardmasterCard convertToCard(String singlecard) { // converts a single card

									
				StringTokenizer tokenizer2 = new StringTokenizer(singlecard,".");
				String first = tokenizer2.nextToken();
				
				if (!first.toString().equals("b")) {
					int cardid = Integer.parseInt(first);
					String cardtype = tokenizer2.nextToken();
					if (cardtype.equals("m")) {
						String cardname = tokenizer2.nextToken();//System.out.println("Found " + cardname);
						int D = Integer.parseInt(tokenizer2.nextToken());
						int L = Integer.parseInt(tokenizer2.nextToken());
						int G = Integer.parseInt(tokenizer2.nextToken());
						int colorcode = Integer.parseInt(tokenizer2.nextToken());
						String picture = tokenizer2.nextToken() + ".gif";
						int expansion = Integer.parseInt(tokenizer2.nextToken());
						String text = tokenizer2.nextToken();
						boolean targete = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetp = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targets = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetm = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targeta = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetg = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						String targetmtype = tokenizer2.nextToken();
						boolean ability = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						int sacD = Integer.parseInt(tokenizer2.nextToken());
						int sacL = Integer.parseInt(tokenizer2.nextToken());
						int sacG = Integer.parseInt(tokenizer2.nextToken());
						boolean unique = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean dizzy = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						int attack = Integer.parseInt(tokenizer2.nextToken());
						int life = Integer.parseInt(tokenizer2.nextToken());	
						String mtype = tokenizer2.nextToken();
						
						CardmasterCard newCard = new CardmasterCard(cardname, text, picture,"m", targetmtype,
								 targete, targetp, targetm,  targets,  targeta,
								  cardid,  expansion,  colorcode, D,  L,  G,
								 sacD, sacL, sacG,  unique, ability,  attack,  life, mtype);
				 
				 		newCard.setDizzy(dizzy);
				 		newCard.drawdizzy = false;
				 
				 		newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase.substring(0,newCard.urlcodebase.length()-3) + "/";
  		
						return newCard;
						
					}else if (cardtype.equals("e")) {
						String cardname = tokenizer2.nextToken();//System.out.println("Found " + cardname);
						int D = Integer.parseInt(tokenizer2.nextToken());
						int L = Integer.parseInt(tokenizer2.nextToken());
						int G = Integer.parseInt(tokenizer2.nextToken());
						int colorcode = Integer.parseInt(tokenizer2.nextToken());
						String picture = tokenizer2.nextToken() + ".gif";
						int expansion = Integer.parseInt(tokenizer2.nextToken());
						String text = tokenizer2.nextToken();
						boolean targete = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetp = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targets = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetm = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targeta = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetg = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						String targetmtype = tokenizer2.nextToken();
						boolean ability = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						int sacD = Integer.parseInt(tokenizer2.nextToken());
						int sacL = Integer.parseInt(tokenizer2.nextToken());
						int sacG = Integer.parseInt(tokenizer2.nextToken());
						boolean unique = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean dizzy = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						int attack = Integer.parseInt(tokenizer2.nextToken());
						int life = Integer.parseInt(tokenizer2.nextToken());	
						
						
						CardmasterCard newCard = new CardmasterCard(cardname, text, picture,"e", targetmtype,
								 targete, targetp, targetm,  targets,  targeta,
								  cardid,  expansion,  colorcode, D,  L,  G,
								 sacD, sacL, sacG,  unique, ability,  attack,  life);
				 
				 		newCard.setDizzy(dizzy);
				 		newCard.drawdizzy = false;
				 
				 		
				 		newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase.substring(0,newCard.urlcodebase.length()-3) + "/";
  		

						return newCard;
						
					}else if (cardtype.equals("s")) {
						String cardname = tokenizer2.nextToken();//System.out.println("Found " + cardname);
						int D = Integer.parseInt(tokenizer2.nextToken());
						int L = Integer.parseInt(tokenizer2.nextToken());
						int G = Integer.parseInt(tokenizer2.nextToken());
						int colorcode = Integer.parseInt(tokenizer2.nextToken());
						String picture = tokenizer2.nextToken() + ".gif";
						int expansion = Integer.parseInt(tokenizer2.nextToken());
						String text = tokenizer2.nextToken();
						boolean targete = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetp = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targets = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetm = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targeta = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						boolean targetg = (Integer.parseInt(tokenizer2.nextToken()) == 1) ? true : false;
						String targetmtype = tokenizer2.nextToken();
						
						
						CardmasterCard newCard = new CardmasterCard(cardname, text, picture,"s", targetmtype,
								 targete, targetp, targetm,  targets,  targeta,
								  cardid,  expansion,  colorcode, D,  L,  G);
				 
				 
							 		newCard.setDizzy(false);
				 		
				 		newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase.substring(0,newCard.urlcodebase.length()-3) + "/";
  						newCard.targetg = targetg;
						return newCard;
						
						
					}
			
				} else return new CardmasterCard();		
				return null;
		
		
	}
		void updateCards(String carddatastring) {
			handcardsS = 0;
			handcardsO = 0;
			StringTokenizer tokenizer = new StringTokenizer(carddatastring,"#");
			tokenizer.nextToken(); // eat the command.
			//System.out.println("Player 1 Monsters");
			for(int i=0;i<5;i++) { //player 1 monsters
				
				CardmasterCard newCard = convertToCard(tokenizer.nextToken());
					PlayerCard(1,"m",i).copydata(newCard);
					PlayerCard(1,"m",i).urlcodebase = newCard.urlcodebase;			
			}//System.out.println("Player 1 Effects");
			for(int i=0;i<5;i++) { //player 1 effects
				
					CardmasterCard newCard = convertToCard(tokenizer.nextToken());
					PlayerCard(1,"e",i).copydata(newCard);
					PlayerCard(1,"e",i).urlcodebase = newCard.urlcodebase;		
			}
			
			int p1deck = Integer.parseInt(tokenizer.nextToken()); // player 1 deck
			String x = tokenizer.nextToken();
			StringTokenizer handtokenizer = new StringTokenizer(x,"^");
			//System.out.println("Player 1 Hand");
			//System.out.println(x);
			for(int i=0;i<8;i++) { //player 1 hand
						CardmasterCard newCard = createLoadableCard(Integer.parseInt(handtokenizer.nextToken()), playernumber==1);
					 	newCard.drawdizzy = false;
						newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase.substring(0,newCard.urlcodebase.length()-3) + "/";
  						
						PlayerCard(1,"h",i).copydata(newCard);
						PlayerCard(1,"h",i).urlcodebase = newCard.urlcodebase;
						if (playernumber == 1) handcardsS++;
						else handcardsO++;
						
						
			}			
			
			//System.out.println("Player 2 monsters");
			for(int i=0;i<5;i++) { //player 2 monsters
					CardmasterCard newCard = convertToCard(tokenizer.nextToken());
						PlayerCard(2,"m",i).copydata(newCard);
					PlayerCard(2,"m",i).urlcodebase = newCard.urlcodebase;		
			}
			for(int i=0;i<5;i++) { //player 2 effects
				CardmasterCard newCard = convertToCard(tokenizer.nextToken());
						PlayerCard(2,"e",i).copydata(newCard);
					PlayerCard(2,"e",i).urlcodebase = newCard.urlcodebase;			
			}
			
			int p2deck = Integer.parseInt(tokenizer.nextToken()); // player 2 deck
		x = tokenizer.nextToken();
		 handtokenizer = new StringTokenizer(x,"^");
			//System.out.println("Player 1 Hand");
			//System.out.println(x);
			
			for(int i=0;i<8;i++) { //player 2 hand
				
			CardmasterCard newCard = createLoadableCard(Integer.parseInt(handtokenizer.nextToken()), playernumber==2);
					 			 	newCard.drawdizzy = false;			 	
				 		newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase.substring(0,newCard.urlcodebase.length()-3) + "/";
						PlayerCard(2,"h",i).copydata(newCard);
						PlayerCard(2,"h",i).urlcodebase = newCard.urlcodebase;
						if (playernumber == 2) handcardsS++;
						else handcardsO++;				 		
				 				
						
			}
			
			
			if (playernumber == 1) {
				sdeck = p1deck;
				odeck = p2deck;
			}else { sdeck = p2deck; odeck = p1deck; }
			updateData();
			
		}	
		void updateUnknowns() {
	/*		for (int i =0;i<5;i++) {
				if (m1[i].dataBlank && carddata[m1[i].cardid]] != null) {
					m1[i].copydata(carddata[m1[i].cardid]]);
					m1[i].dataBlank= false;
				}
				if (e1[i].dataBlank && carddata[e1[i].cardid]] != null) {
					e1[i].copydata(carddata[e1[i].cardid]]);
					e1[i].dataBlank= false;
				}				
				if (m2[i].dataBlank && carddata[m2[i].cardid]] != null) {
					m2[i].copydata(carddata[m2[i].cardid]);
					m2[i].dataBlank= false;
				}
				if (e2[i].dataBlank && carddata[e2[i]] != null) {
					e2[i].copydata(carddata[e2[i].cardid]);
					e2[i].dataBlank= false;
				}
			}*/
			for (int i=0;i<8;i++) {
				if (hand[i].dataBlank && carddata[hand[i].cardid] != null) {
					hand[i].copydata(carddata[hand[i].cardid]);
					hand[i].dataBlank= false;
				}
				if (Ohand[i].dataBlank && carddata[Ohand[i].cardid] != null) {
					Ohand[i].copydata(carddata[Ohand[i].cardid]);
					Ohand[i].dataBlank= false;
				}				
				
			}
			for (int i= 0;i<grave.length;i++) {
				if (grave[i].dataBlank && carddata[grave[i].cardid] != null) {
					grave[i].copydata(carddata[grave[i].cardid]);
					grave[i].dataBlank= false;
				}				
				
			}
			updateData();
			if (lastcardstring != null) updateCards(lastcardstring);
			
		}			

	CardmasterCard PlayerCard(int player,String type, int i) {
			
			if (playernumber == player) {
				////System.out.println("Self" + player + playernumber);
				if (type.equals("m")) {
					return Smonster[i];
				}
				else if (type.equals("e")) {
					return Seffect[i];
				}
				else if (type.equals("h")) {
					return hand[i];
				}
				else if (type.equals("g")) {
					if (i >= grave.length) return null;
					else return grave[i];	
				}
				else return null;
				
			}
			
			else {
				////System.out.println("Opponent" + player + playernumber);
				if (type.equals("m")) {
					return Omonster[i];
				}
				else if (type.equals("e")) {
					return Oeffect[i];
				}
				else if (type.equals("h")) {
					return Ohand[i];
				}
				else return null;
				
				
				
			}
			
			
			
		}
        public void actionPerformed(ActionEvent evt){ 
		
        	if (evt.getSource() == button_up) {
        		currentmessage++;
        		button_up.setEnabled(true);
        		button_down.setEnabled(true);
        		if (currentmessage >= 29) {
        			currentmessage = 29;
        			button_up.setEnabled(false);
        		}
        		else if (currentmessage <= 0) {
        			currentmessage = 0;
        			button_down.setEnabled(false);
        		}
        		
        		updateMessage();
        		
        		
        	}
        	else if (evt.getSource() == button_down) {
        		currentmessage--;
        		button_up.setEnabled(true);
        		button_down.setEnabled(true);
        		if (currentmessage >= 29) {
        			currentmessage = 29;
        			button_up.setEnabled(false);
        		}
        		else if (currentmessage <= 0) {
        			currentmessage = 0;
        			button_down.setEnabled(false);
        		}
        		
        		updateMessage();
        		
        		
        	}
        	else if (evt.getSource() == chat_enter) {
        		if (!(chat_enter.getText().equals(""))) {
	        		out.println("CM#" + myname + "#" + chat_enter.getText().replace('#',' ') + "#");
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
						//System.out.println("Got " + command + " " + fromServer);
						if (command.equals("CHA")) {
							String name = tokenizer.nextToken("#");
							chat_log.append("<" + name + ">" + tokenizer.nextToken("#") + "\n");
							chat_log.setText(chat_log.getText());
						}else if (command.equals("MES")) {
							
							newMessage(tokenizer.nextToken("#"));
							
						

						}else if (command.equals("SBC")) {
							
							bigcardspell(tokenizer.nextToken("#"));
							
						} 
						else if (command.equals("PRO")) {
							int protover = Integer.parseInt(tokenizer.nextToken());
							if (protover != protocolnum) {
							
								newMessage("Protocol Version Mismatch!");
								chat_log.append(" * PROTOCOL MISMATCH * ");
								chat_log.append("Chances are, this game will crash");
								chat_log.append("Please clear your browser cache to ensure the newest Client version is downloaded");
								chat_log.setText(chat_log.getText());
							}
						}
						else if (command.equals("WIN")) {
							
							int winner = Integer.parseInt(tokenizer.nextToken("#"));
							int winpoints = Integer.parseInt(tokenizer.nextToken("#"));
							int losspoints = Integer.parseInt(tokenizer.nextToken("#"));
							String winnern;
							if (winner == playernumber)
								winnern = playername;
							else winnern = opponentname;
								
							getAppletContext().showDocument(new URL(getCodeBase().toString() + "page.jsp?pageurl=ReportObWin.jsp&a=" + winnern));
							
							
						
							
							
						} 

						else if (command.equals("CDD")) {
							int cardid = Integer.parseInt(tokenizer.nextToken());
							String cardData = tokenizer.nextToken();	
							carddata[cardid] = convertToCard(cardData);
							updateUnknowns();
							
							
							
							
						}						
						else if (command.equals("ATR")) {
							int attacker = Integer.parseInt(tokenizer.nextToken());
							for (int i = 0;i<5;i++) { // defender monsters damage
								if (attacker != playernumber) {
									Smonster[i].showdamage = true;
									Smonster[i].damagetaken = Integer.parseInt(tokenizer.nextToken());	
									
									
								}	
								else {
									Omonster[i].showdamage = true;
									Omonster[i].damagetaken = Integer.parseInt(tokenizer.nextToken());	
									
									
								}									
								
							}
							for (int i = 0;i<5;i++) { //attacker monster damager
								if (attacker != playernumber) {
									Omonster[i].showdamage = true;
									Omonster[i].damagetaken = Integer.parseInt(tokenizer.nextToken());	
									
									
								}	
								else  {
									Smonster[i].showdamage = true;
									Smonster[i].damagetaken = Integer.parseInt(tokenizer.nextToken());	
									
									
								}									
								
							}
							if (attacker != playernumber) { //player damage.
								sshowdamage = true;
								sdamage = Integer.parseInt(tokenizer.nextToken());
								
								
								}							
							else  {
								oshowdamage = true;
								odamage = Integer.parseInt(tokenizer.nextToken());
								
								
							}
							updateData();							
						}
						else if (command.equals("OPF")) {
							chat_enter.setEditable(true);
							button_nextphase.setEnabled(true);
							button_ability.setEnabled(true);
						//	button_graveyard.setEnabled(true);
							chat_log.append("* " + opponentname + " has joined the duel!\n");
							
							chat_log.setText(chat_log.getText());
							newMessage("It's Dueling Time!");
							
							
							
							out.println("DB#");
						}	    		
						else if (command.equals("ONF")) {
							
							newMessage("Opponent could not be found.");
							
						}			    		
						else if (command.equals("PNG")) {
							
							out.println("PN#");
							
						}
						else if (command.equals("OBN")) { // observer names
							playernumber = 1;
							playername = tokenizer.nextToken("#");
							opponentname = tokenizer.nextToken("#");
							chat_enter.setEditable(true);
							
						}			    		
						else if (command.equals("CLO")) {
							
							newMessage("Gameroom Closed\n");
							chat_enter.setEditable(false);
						}
						else if (command.equals("STP")) { // PHASE STATUS
							String turnplayer = tokenizer.nextToken("#");
							int turnphase = Integer.parseInt(tokenizer.nextToken("#"));
							int turnno = Integer.parseInt(tokenizer.nextToken("#"));
							
							turn(turnplayer,turnphase,turnno);
							
							
						}
						else if (command.equals("ATD")) { // Attack Data.
							int attacker = Integer.parseInt(tokenizer.nextToken());
							if (attacker != playernumber) { // this is your time to shine!
								for(int i = 0;i<5;i++) {
									attackdata[i] = Integer.parseInt(tokenizer.nextToken("#"));
									if (attackdata[i] > 0 && attackdata[i] < 6) attacklock[attackdata[i]-1] = 1;
											

			
								}
							updateData();					
								
								
							}
							
							
						}						
						else if (command.equals("STN")) { //NAMES AND STATS
							String p1name = tokenizer.nextToken("#");
							int p1life = Integer.parseInt(tokenizer.nextToken("#"));
							int p1d = Integer.parseInt(tokenizer.nextToken("#"));
							int p1l = Integer.parseInt(tokenizer.nextToken("#"));
							int p1g = Integer.parseInt(tokenizer.nextToken("#"));
							String p2name = tokenizer.nextToken("#");
							int p2life = Integer.parseInt(tokenizer.nextToken("#"));
							int p2d = Integer.parseInt(tokenizer.nextToken("#"));
							int p2l = Integer.parseInt(tokenizer.nextToken("#"));
							int p2g = Integer.parseInt(tokenizer.nextToken("#"));
							
							if (p1name.equals(playername)) {
								this.playernumber = 1;
								slife =p1life;
								sd = p1d;
								sl = p1l; 
								sg = p1g;
								h1 = hand;
								m1 = Smonster;
								e1 = Seffect;
								olife =p2life;
								od = p2d;
								ol = p2l;
								og = p2g;
								h2 = Ohand;
								m2 = Omonster;
								e2 = Oeffect;
	
							}					
							else if (p2name.equals(playername)) {
								this.playernumber = 2;
								olife =p1life;
								od = p1d;
								ol = p1l;
								og = p1g;
								h2 = hand;
								m2 = Smonster;
								e2 = Seffect;
								
								slife =p2life;
								sd = p2d;
								sl = p2l;
								sg = p2g;
								h1 = Ohand;
								m1 = Omonster;
								e1 = Oeffect;
	
							}
							updateData();							
							
						}
						else if (command.equals("STC")) { // CARD STATUS
							updateCards(fromServer);
			
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
