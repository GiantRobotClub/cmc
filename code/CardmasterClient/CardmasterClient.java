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
import javax.swing.event.*;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import java.util.StringTokenizer;
import java.util.Vector;


public class CardmasterClient extends JApplet implements ActionListener  {
		String myname;
		
		//int port = 4170;
		int port;
	  boolean viewmods = false;
	  String victorybycard = " ";
	  String victorybytype = " ";
		boolean observer = false;
		Socket chatSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String fromServer;
        String fromUser;
        String chattext;
        boolean changeresolve = false;
        String playername;
        String address;
        String lastcardstring;
        String modtype = "x";
        String lastmodstring;
        boolean showp1mana = true;
        boolean showp2mana = true;
        int giveuptimer = 0;
        
        
        String opponentname;
        static int protocolnum = 45;
       
       /*1.4
        static String jver = "1.4";
      //1.4*/
       
        ///*1.5
        static String jver = "1.5";
        ////1.5*/
        static String version=jver+"a3.365-" + protocolnum;//!?
        String status_text;
        String action;
        String picartist[];
        static int NUMCARDS = 1000;
        
        boolean modinplay = false;
        int askdatacounter = 0;
        boolean askdata[];
       	JPanel panel_hand;
       	JPanel panel_opdata;
       	JPanel panel_opmonster;
       	JPanel panel_opeffect;
       	JPanel panel_semonster;
       	JPanel panel_seeffect;
       	
       	
       		JPanel panel_opmonsterC;
       	JPanel panel_opeffectC;
       	JPanel panel_semonsterC;
       	JPanel panel_seeffectC;
       	
       		JPanel panel_opmonsterD;
       	JPanel panel_opeffectD;
       	JPanel panel_semonsterD;
       	JPanel panel_seeffectD;
       	
       	
       	JPanel panel_card;
       	JPanel panel_sedata;
       	JPanel panel_chat;
       	JPanel panel_top;
       	JPanel panel_grave;
       	Timer timer;
       	int nexttimer = 0;
       	JPanel panel_location;
       	JPanel panel_temp;
       	JTabbedPane tabbed_grave;
       	JScrollPane scroll_grave;
       	JLabel label_gamemessage;

		int resolvecounter = -600;
		int resolvetimerno = -600;
		int resolvetimerno2 = -600;
		int handcardsS = 0;
		int handcardsO = 0;

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
		JLabel label_artistname;
		
		
		JButton button_nextphase;
		JButton button_ability;
		JButton button_switchmod;
		JButton button_graveyard;
		JButton button_up;
		JButton button_down;
		JButton button_abilityresolve;
		int numabilities;
		JButton button_cancel;
		JButton button_surrender;
		JButton button_invisible;
		boolean giveup = false;
		boolean toggleFocus = false;
		
		boolean modInPlay = false;
		
		
		
		
		JPanel panel_carddata;
       	JList list_carddata;
		CardmasterCardListData cardlistdata[];	
		DefaultListModel cardlistmodel= new DefaultListModel();
		
		
		
		ReadFromServerThread readThread;
		
       	CardmasterCard sacCard;
       	CardmasterCard hand[];
       //	CardmasterCard Ohand[];
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
 
 
 

       	CardmasterCard dmO[];
       	CardmasterCard deO[];
       	CardmasterCard dmS[];
       	CardmasterCard deS[];
       	
       	CardmasterCard location;


		CardmasterCard carddata[];
		CardmasterCard[] grave;
		int attackdata[];
		int attacklock[];
		CardmasterCard selectedcard;
		int handid;
        	
        int sgravec = 0;
        int ogravec =0;
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
  		int roomnumber;
  		String abilityfrom;
  		
  		int playernumber;
  		boolean can_play;
  		boolean can_attack;
  		boolean can_defend;
  		boolean can_sac;
  		boolean must_discard;
 
 		boolean abilityresolve = false;
 		boolean pick_card_for_ability;
 		boolean pick_card_for_attack;
 		boolean pick_card_for_sac;
 		boolean pick_card_for_defend;
 		boolean pick_target_for_mod_ability;
 		boolean pick_target_for_ability;
 		boolean pick_target_for_spell;
 		boolean pick_target_for_attack;
 		boolean pick_target_for_defend;
 		boolean pick_target_for_play_monster;
 		boolean pick_target_for_play_modifier;
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
  		
  		JScrollPane status_log_scroll;
  		JTextArea status_log;
  		
  	int absoluteplayer(String so) {
  		if (so.equals("s")) {
  			return playernumber;
  		}
  		if (so.equals("o")) {
  			if (playernumber ==2) return 1;
  			else return 2;
  		}
  		return -1;
  	}



    boolean playable(CardmasterCard card) {
    	if (!checkmana(card)) return false; 
    	if (card.dummy) return false;
    	if (card.typecode.equals("l") && pick_card_to_play) return true;	
    	if (card.typecode.equals("s")) return true;
    	if ((card.typecode.equals("m")) && pick_card_to_play) return true;
     	if ((card.typecode.equals("e")) && pick_card_to_play) return true;
     	if ((card.typecode.startsWith("d")) && pick_card_to_play) return true;
     	
    	return false;   	
    	
    	
    }

    boolean targetablebycurrentability (String type, CardmasterCard card) {
    
    	if (selectedcard == null) return false;
    		//System.out.println(selectedcard.name);
     // if (selectedcard.typecode.startsWith("d")) {
      //	newMessage("DEBUG: " + selectedcard.name);
      //}
    

  		if (selectedcard.targets && card.dummy && selectedcard.targetm && type.equals("m")) return true;
     	else if (selectedcard.targets && card.dummy && selectedcard.targete && type.equals("e")) return true;
    	else if (selectedcard.targetg && isInGrave(card) && selectedcard.targete && card.typecode.equals("e")) return true;
    	else if (selectedcard.targetg && isInGrave(card) && selectedcard.targetm && card.typecode.equals("m")) return true;
    	//else if (selectedcard.targetg && isInGrave(card) && selectedcard.targetc && selectedcard.typecode.equals("c")) return true;
    	else if (selectedcard.targete && !selectedcard.targetg && !selectedcard.targets &&type.equals("e") && !card.dummy) return true;
    	
    	else if (selectedcard.targetm && !selectedcard.targetg && !selectedcard.targets&& type.equals("m") && !card.dummy) {
    	//	newMessage("DEBUG: " + selectedcard.targetmtype + " - " +card.mtype);
    		if (selectedcard.targetmtype.equals("none")) return true;
    		if (ismType(card,selectedcard.targetmtype)) return true;
    
    		}
    	else if (selectedcard.targetg && isInGrave(card) && !selectedcard.targetm && !selectedcard.targete && card.typecode.equals(selectedcard.targetmtype)) return true;
    	else if (selectedcard.targetg && isInGrave(card) && !selectedcard.targetm && !selectedcard.targete && selectedcard.targetmtype.equals("a")) return true;

    	return false;
    }
    void updateMessage() {
    	//System.out.println(currentmessage);
    	label_gamemessage.setText(messagebuffer[currentmessage]);
    }
   void newMessage(String message) {
   	 	status_log.append(message + "\n");
   	 	status_log.setText(status_log.getText());
 	//   	updateMessage(String message);
    }
	public boolean isInGrave(CardmasterCard card) {
		for (int i=0;i<grave.length;i++) {
			
			if (grave[i] == card) return true;	
		}
		return false;
	}
	
	
	void getArtistNameList() {
		try {
		
		URL artistnames = new URL( getCodeBase().toString() + "artistlist.txt");
		BufferedReader ain = new BufferedReader(new InputStreamReader(artistnames.openStream()));
		String inputLine;
		while ((inputLine = ain.readLine()) != null) {
			StringTokenizer token = new StringTokenizer(inputLine, "#");
			int number = Short.parseShort(token.nextToken());
			String name = token.nextToken();
			picartist[number] = name;
			
			
			
			
		}
		
		ain.close();
		} catch (Exception e) {
			e.printStackTrace();
			
			
		}
	}
	void setArtName() {
		if (bigcard.avatardraw)
		label_artistname.setText("<HTML><FONT SIZE=-2>Avatar of " + bigcard.avatarname + "</FONT></HTML>");
		else
		label_artistname.setText("<HTML><FONT SIZE=-2>Art by " + getArtistName(bigcard.cardid) + "</FONT></HTML>");
		
	}
	String getArtistName(int card) {
		if (card >= NUMCARDS) return "Mark Shallow";
		if (picartist[card] != null) return picartist[card];
		return "Mark Shallow";
		
		
		
		
	}
	public void start() {
try {
			//boolean numericjoin = false;
			//if (getParameter("numericjoin") != null) numericjoin true; 
			address = getParameter("address");
			getArtistNameList();
			playername = getParameter("player");
			String observerstring = getParameter("observer");
			
			if (observerstring == null) observerstring = "yes";
			if (observerstring.equals("yes")) observer = true;
			//if (!numericjoin) opponentname = getParameter("opponent");
			opponentname = "waiting..";
			roomnumber = Short.parseShort(getParameter("roomnumber"));
			port = Short.parseShort(getParameter("port"));
            chatSocket = new Socket(address, port);
          //  chatSocket = new Socket(address, 4170);
           
            out = new PrintWriter(chatSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(chatSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Couldn't connect to CMC Server");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: CMC server.");
            System.exit(1);
        }

        if (observer)myname = getParameter("myname");
   	//	out.println("OP#" + playername +  "#" + opponentname + "#"); // Tell the server the opponent the client is looking for.
      	if (!observer) out.println("JR#" + playername + "#" + roomnumber + "#"); //join room
      	if (observer) 	out.println("OB#"+roomnumber+"#" + myname +"#"); 		
	newMessage("Welcome to Cardmaster Conflict by Mark Shallow");	
	newMessage("Waiting for Player. . . . . ");
  	newMessage("Client version " + version);
		
String			checkcode = getParameter("checkcode");
		if (!observer)	out.println("CC#" + checkcode + "#" + playername + "#");
		readThread = new ReadFromServerThread(in);
		timer.start();	
		
		readThread.start();
	}
		CardmasterImageManager manager;
	public void init() {
			manager = new CardmasterImageManager(getCodeBase().toString());
		manager.LoadCommonCMCImages();
		timer = new Timer(100,this);
		picartist = new String[NUMCARDS];
		
	
		attacklock = new int[5];
		attackdata = new int[5];
		hand = new CardmasterCard[8];
		//Ohand = new CardmasterCard[8];
		Smonster = new CardmasterCard[5];
		Omonster = new CardmasterCard[5];
		Seffect = new CardmasterCard[5];
		Oeffect = new CardmasterCard[5];
		
		dmO = new CardmasterCard[5];
		dmS = new CardmasterCard[5];
		deO = new CardmasterCard[5];
		deS = new CardmasterCard[5];
		
		carddata = new CardmasterCard[NUMCARDS];
		askdata = new boolean[NUMCARDS];
		for (int i = 0; i<NUMCARDS; i++) {
			askdata[i] = false;	
			
		}
		cardlistdata = new CardmasterCardListData[NUMCARDS];
		for (int i=0;i<cardlistdata.length;i++) {
			cardlistdata[i] = new CardmasterCardListData(i);
		}
		carddata[0] = new CardmasterCard();
		carddata[0].SetImageManager(manager);
		grave = new CardmasterCard[0];
		bigcard = new CardmasterCard();
		bigcard.SetImageManager(manager);
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
			Smonster[i].SetImageManager(manager);
			Omonster[i].SetImageManager(manager);
			Seffect[i].SetImageManager(manager);
			Oeffect[i].SetImageManager(manager);
			
			
			dmO[i] = new CardmasterCard();
			dmS[i] = new CardmasterCard();
			deO[i] = new CardmasterCard();
			deS[i] = new CardmasterCard();
			dmO[i].SetImageManager(manager);
			deO[i].SetImageManager(manager);
			dmS[i].SetImageManager(manager);
			deS[i].SetImageManager(manager);
			
			
		}
		for (int i=0;i<8;i++) {
			hand[i] = new CardmasterCard();
			//Ohand[i] = new CardmasterCard();
			hand[i].SetImageManager(manager);
			//Ohand[i].SetImageManager(manager);
		}				
		
		location = new CardmasterCard();
		location.SetImageManager(manager);
		
        GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH; 

		getContentPane().setLayout(gridbag);



		panel_hand = new JPanel();
		panel_hand.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		
		
		panel_grave = new JPanel();
		panel_grave.setLayout(new GridLayout(0,2));
		scroll_grave = new JScrollPane(panel_grave);
		scroll_grave.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_grave.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			
		list_carddata = new JList(cardlistmodel);	
		list_carddata.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//list_carddata.setLayoutOrientation(JList.VERTICAL);
		panel_carddata = new JPanel();
		JScrollPane listscroller = new JScrollPane(list_carddata);
		listscroller.setPreferredSize(new Dimension(180, 375));
		panel_carddata.add(listscroller);
		list_carddata.addListSelectionListener(new CardmasterListSelectionListener());
		
		tabbed_grave = new JTabbedPane();
		String starter = "<img src=" + getCodeBase().toString()
						 + "/images/";
		tabbed_grave.addTab("<html>"+starter+"handsymbol.gif></html>",panel_hand);
		tabbed_grave.addTab("<html>"+starter+"graveyard.gif></html>",scroll_grave);
		tabbed_grave.addTab("<html>"+starter+"datasymbol.gif></html>",panel_carddata);
		
		tabbed_grave.setMinimumSize(new Dimension(160,(int)(getHeight()/(420.0/300.0))));
		tabbed_grave.setMaximumSize(new Dimension(160,(int)(getHeight()/(420.0/300.0))));
	
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 4;
		c.weighty = 1;
		gridbag.setConstraints(tabbed_grave, c);
		getContentPane().add(tabbed_grave);

//	seavatar = new JLabel();
//		oavatar = new JLabel();

		panel_opdata = new JPanel();
	//	oplayerpanel = new JPanel();
	//	oplayerpanel.add(oavatar);
	//	oplayerpanel.add(panel_opdata);
		panel_opdata.setLayout(new GridLayout(0,1));
			panel_opdata.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.weighty = 1;
		gridbag.setConstraints(panel_opdata, c);
		getContentPane().add(panel_opdata);		


		panel_opmonster = new JPanel();
		panel_opmonster.setMinimumSize(new Dimension(405,(int)(getHeight()/(420.0/70.0))));
		panel_opmonster.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.weighty = 1;
		gridbag.setConstraints(panel_opmonster, c);	
		getContentPane().add(panel_opmonster);



		panel_opeffect = new JPanel();
		panel_opeffect.setMinimumSize(new Dimension(405,(int)(getHeight()/(420.0/70.0))));
		panel_opeffect.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		c.gridx = 2;
		c.gridy = 2;
		c.gridheight = 1;
		c.weighty = 1;
		gridbag.setConstraints(panel_opeffect, c);
		getContentPane().add(panel_opeffect);

		panel_seeffect = new JPanel();
		panel_seeffect.setMinimumSize(new Dimension(405,(int)(getHeight()/(420.0/70.0))));
		c.gridx = 2;
		c.gridy = 3;
		c.gridheight = 1;
		c.weighty = 1;
		gridbag.setConstraints(panel_seeffect, c);
		panel_seeffect.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		getContentPane().add(panel_seeffect);
//		seplayerpanel = new JPanel();
		panel_sedata = new JPanel();
		//	seplayerpanel.add(seavatar);
		//seplayerpanel.add(panel_sedata);
		
		c.gridx = 1;
		c.gridy = 4;
		c.gridheight = 1;
		c.weighty = 1;
		gridbag.setConstraints(panel_sedata, c);
		panel_sedata.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		getContentPane().add(panel_sedata);
		panel_sedata.setLayout(new GridLayout(0,1));
		
		panel_semonster = new JPanel();
		panel_semonster.setMinimumSize(new Dimension(405,(int)(getHeight()/(420.0/70.0))));
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
		panel_card.setLayout(new BoxLayout(panel_card,BoxLayout.Y_AXIS));





	/*
		
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

*/

		panel_temp = new JPanel();
		panel_temp.setLayout(new BorderLayout());
		//panel_temp.setMinimumSize(new Dimension((int)(getWidth()/(620.0/150.0)),(int)(getHeight()/(420.0/80.0))));
		
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		gridbag.setConstraints(panel_temp,c);
		getContentPane().add(panel_temp);
		panel_temp.setMinimumSize(new Dimension((int)(getWidth()/(620.0/150.0)),(int)(getHeight()/(420.0/80.0))));
	
		status_log = new JTextArea();
		status_log.setEditable(false);
		status_log.setLineWrap(true);
		status_log.setWrapStyleWord(true);
		status_log_scroll = new JScrollPane(status_log);
		status_log_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		status_log_scroll.setMinimumSize(new Dimension((int)(getWidth()/(620.0/150.0)),(int)(getHeight()/(420.0/80.0))));
		
		panel_temp.add(status_log_scroll, BorderLayout.CENTER);
				
		panel_chat = new JPanel();
		panel_chat.setMinimumSize(new Dimension((int)(getWidth()/(620.0/450.0)),(int)(getHeight()/(420.0/100.0))));
		c.gridx = 1;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.weightx = 1;

		
		gridbag.setConstraints(panel_chat, c);
		//panel_chat.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(panel_chat);



		panel_top = new JPanel();
	//	panel_top.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()/(420.0/10.0))));
	//	panel_top.setMaximumSize(new Dimension(getWidth(),(int)(getHeight()/(420.0/10.0))));
	//	c.gridx = 0;
	//	c.gridy = 0;
	//	c.gridwidth = 3;
	//	c.weighty = 0;		
	//	gridbag.setConstraints(panel_top, c);
	//	getContentPane().add(panel_top);
		
		label_gamemessage = new JLabel(messagebuffer[currentmessage]);
		button_nextphase = new JButton("Next");
		button_ability = new JButton("Ability");
		button_switchmod = new JButton("Mods");
		
		button_abilityresolve = new JButton("Resolve (0)");
		button_abilityresolve.setEnabled(false);
		resolvecounter = -600;
		
		button_cancel = new JButton("Cancel");
		button_invisible = new JButton("Invisible");
		button_surrender = new JButton("Surrender");
	//	button_graveyard = new JButton("Graveyard");
	//	label_gamemessage.setPreferredSize(new Dimension((int)(getWidth()/(620.0/345.0)),(int)(getHeight()/(420.0/20.0))));
	//	button_graveyard.setPreferredSize(new Dimension((int)(getWidth()/(620.0/95.0)), (int)(getHeight()/(420.0/20.0))));
	//	button_nextphase.setPreferredSize(new Dimension((int)(getWidth()/(620.0/85.0)), (int)(getHeight()/(420.0/20.0))));
//		button_ability.setPreferredSize(new Dimension((int)(getWidth()/(620.0/90.0)), (int)(getHeight()/(420.0/20.0))));
	//	button_up = new JButton("/\\");
	//	button_down = new JButton("\\/	");
	//	button_up.setPreferredSize(new Dimension((int)(getWidth()/(620.0/50.0)), (int)(getHeight()/(420.0/20.0))));
	//	button_down.setPreferredSize(new Dimension((int)(getWidth()/(620.0/50.0)), (int)(getHeight()/(420.0/20.0))));
		
		panel_top.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));	
	//	panel_top.add(button_graveyard);
		panel_top.add(button_nextphase);
		
		panel_top.add(button_ability);
		panel_top.add(button_switchmod);
		panel_top.add(button_cancel);
		//panel_top.add(button_invisible);
		
		panel_top.add(button_abilityresolve);
		panel_top.add(button_surrender);
		
	//	button_down.setEnabled(false);
	//	button_up.addActionListener(this);
	//	button_down.addActionListener(this);
		button_nextphase.addActionListener(this);
		button_ability.addActionListener(this);
		button_cancel.setEnabled(false);
		button_cancel.addActionListener(this);
		button_invisible.setEnabled(false);
		button_surrender.setEnabled(false);
		button_invisible.addActionListener(this);
		button_surrender.addActionListener(this);
		button_abilityresolve.addActionListener(this);
		button_switchmod.addActionListener(this);
// Setting up Chat Box

		
		
		
		panel_location = new JPanel();
		panel_location.add(location, BorderLayout.CENTER);
		
		
		JPanel panel_chatwindow = new JPanel();
		panel_chatwindow.setLayout(new BorderLayout());
		
		
		panel_chat.setLayout(new BorderLayout());
		
	
		chat_enter = new JTextField();
		chat_enter.addActionListener(this);
		chat_enter.setEditable(false);
		panel_chatwindow.add(BorderLayout.SOUTH,chat_enter);
		
		chat_log = new JTextArea();
		chat_log.setEditable(false);
		chat_log.setLineWrap(true);
		chat_log.setWrapStyleWord(true);
		chat_log_scroll = new JScrollPane(chat_log);
		chat_log_scroll.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()/(420.0/85.0))));
		chat_log_scroll.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
	//	panel_chatsideways.add(panel_location,BorderLayout.EAST);
		panel_chat.add(panel_location,BorderLayout.EAST);
		panel_chat.add(panel_chatwindow,BorderLayout.CENTER);
		panel_chatwindow.add(BorderLayout.CENTER,chat_log_scroll);
		panel_chatwindow.add(panel_top,BorderLayout.NORTH);
		bigcard.setBorder(BorderFactory.createLineBorder(Color.black));
		bigcard.setPreferredSize(new Dimension((int)(getWidth()/(620.0/100.0)),(int)(getHeight()/(420.0/140.0))));
		bigcard.setMaximumSize(new Dimension((int)(getWidth()/(620.0/100.0)),(int)(getHeight()/(420.0/140.0))));
		bigcard.setMinimumSize(new Dimension((int)(getWidth()/(620.0/100.0)),(int)(getHeight()/(420.0/140.0))));
		
		panel_card.add(bigcard);
		
		label_artistname = new JLabel("XXXXXXXXXXX XXXXXXXX XXXXXXXXXX XXXX XXXXXXXX XXXXXXXXX XXXXXXX");		
		label_artistname.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_card.add(label_artistname);
	//	label_artistname.setPreferredSize(new Dimension(panel_card.getWidth(),label_artistname.getHeight()));

		label_artistname.setText("<HTML><FONT SIZE=-2>Art by Mark Shallow</FONT></HTML>");
		
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
		
	//	button_graveyard.setEnabled(false);
		
	
		panel_semonster.setLayout(new CardLayout());
		panel_opmonster.setLayout(new CardLayout());
		panel_seeffect.setLayout(new CardLayout());
		panel_opeffect.setLayout(new CardLayout());
		
		
		panel_semonsterC = new JPanel();
		panel_opmonsterC = new JPanel();
		panel_seeffectC = new JPanel();
		panel_opeffectC = new JPanel();
	
		panel_semonsterD = new JPanel();
		panel_opmonsterD = new JPanel();
		panel_seeffectD = new JPanel();
		panel_opeffectD = new JPanel();
		
		
		panel_semonster.add(panel_semonsterC,"CARD");
		panel_opmonster.add(panel_opmonsterC,"CARD");
		panel_seeffect.add(panel_seeffectC,"CARD");
		panel_opeffect.add(panel_opeffectC,"CARD");

		panel_semonster.add(panel_semonsterD,"MOD");
		panel_opmonster.add(panel_opmonsterD,"MOD");
		panel_seeffect.add(panel_seeffectD,"MOD");
		panel_opeffect.add(panel_opeffectD,"MOD");	
			
		((CardLayout)panel_opmonster.getLayout()).show(panel_opmonster,"CARD");
		((CardLayout)panel_semonster.getLayout()).show(panel_semonster,"CARD");
		((CardLayout)panel_opeffect.getLayout()).show(panel_opeffect,"CARD");
		((CardLayout)panel_seeffect.getLayout()).show(panel_seeffect,"CARD");
		
	//	panel_hand.setLayout(new GridLayout(4,2));
	 	cardMouseEvents cme = new cardMouseEvents();
		panel_opdata.addMouseListener(cme);
		panel_sedata.addMouseListener(cme);
		for (int i = 0; i <5; i++) {
			Smonster[i].setBorder(BorderFactory.createLineBorder(Color.black));
			Smonster[i].setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
			Omonster[i].setBorder(BorderFactory.createLineBorder(Color.black));
			Omonster[i].setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
			Seffect[i].setBorder(BorderFactory.createLineBorder(Color.black));
			Seffect[i].setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
			Oeffect[i].setBorder(BorderFactory.createLineBorder(Color.black));
			Oeffect[i].setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
			Oeffect[i].addMouseListener(cme);
			Seffect[i].addMouseListener(cme);
			Omonster[i].addMouseListener(cme);
			Smonster[i].addMouseListener(cme);

			panel_semonsterC.add(Smonster[i]);
			panel_opeffectC.add(Oeffect[i]);
			panel_opmonsterC.add(Omonster[i]);
			panel_seeffectC.add(Seffect[i]);
			
			
			
			dmS[i].setBorder(BorderFactory.createLineBorder(Color.black));
			dmS[i].setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
			dmO[i].setBorder(BorderFactory.createLineBorder(Color.black));
			dmO[i].setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
			deS[i].setBorder(BorderFactory.createLineBorder(Color.black));
			deS[i].setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
			deO[i].setBorder(BorderFactory.createLineBorder(Color.black));
			deO[i].setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
			deO[i].addMouseListener(cme);
			deS[i].addMouseListener(cme);
			dmO[i].addMouseListener(cme);
			dmS[i].addMouseListener(cme);
			
			panel_semonsterD.add(dmS[i]);
			panel_opeffectD.add(deO[i]);
			panel_opmonsterD.add(dmO[i]);
			panel_seeffectD.add(deS[i]);
			
			
	
		}
		
		location.addMouseListener(cme);
			location.setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
				
		for (int i = 0; i <8; i++) {	
			hand[i].setBorder(BorderFactory.createLineBorder(Color.black));
			hand[i].setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
			panel_hand.add(hand[i]);
			hand[i].drawdizzy = false;
			hand[i].addMouseListener(cme);
		}			
		
	


  			location.urlcodebase = getCodeBase().toString();
				location.urlcodebase = location.urlcodebase + "/";
			location.setBorder(BorderFactory.createLineBorder(Color.black));
  		bigcard.urlcodebase = getCodeBase().toString();
  	//	bigcard.urlcodebase = bigcard.urlcodebase.substring(0,bigcard.urlcodebase.length()-3) + "/";
  		bigcard.urlcodebase = bigcard.urlcodebase + "/";
  		bigcard.urlcode2 = getCodeBase().toString();
  		bigcard.drawlarge = true;

		
	}

// 	out.println to send to the file

	class graveMouseEvents implements MouseListener {
		public void mouseEntered(MouseEvent e) {
			//System.out.println(e);
			bigcard.copydata((CardmasterCard)e.getSource());
			setArtName();
			invalidate();
			validate();
		
			repaint();
			return;
			
		}	
		public void mouseClicked(MouseEvent e) {
			nexttimer = 0;
			if (pick_target_for_spell && targetablebycurrentability("x",((CardmasterCard)(e.getSource())))) {
					out.println("SG#" + handid + "#" + ((CardmasterCard)(e.getSource())).cardid + "#"); // spell on grave, yo.
					selectedcard = null;
					handid = -1;
					newMessage("Attempting to cast spell on grave..");
					updateData();
				
				
			}
			else if (pick_target_for_ability && targetablebycurrentability("x",((CardmasterCard)(e.getSource())))) {
					out.println("BG#" +  abilityfrom + handid + "#" + ((CardmasterCard)(e.getSource())).cardid + "#"); // ability on grave, yo.
					selectedcard = null;
					handid = -1;
					newMessage("Attempting to use ability..");
					updateData();
				
				
			}
			else if (pick_target_for_mod_ability && targetablebycurrentability("x",((CardmasterCard)(e.getSource())))) {
				out.println("DA#" + modtype + "#" + handid + "#g#"+0+"#"+0+"#"+((CardmasterCard)(e.getSource())).cardid+"#");
				selectedcard = null;
					handid = -1;
					newMessage("Attempting to use ability..");
					updateData();
							
			}			
		}
	 public void mousePressed(MouseEvent e) { 
			 return;}		
			   public void mouseExited(MouseEvent e) { return;}
			   public void mouseReleased(MouseEvent e) {return; }

		
		
	}
	 class cardMouseEvents implements MouseListener {
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() != bigcard && e.getSource() != panel_opdata &&
				e.getSource() != panel_sedata) {
				bigcard.copydata((CardmasterCard)e.getSource());
				setArtName();
				invalidate();
				validate();
				repaint();
				
				
				
			} else if (e.getSource() == panel_opdata) {
				bigcard.avatardraw = true;
				bigcard.avatarname = opponentname;
					setArtName();
				invalidate();
				validate();
				repaint();
				
			}else if (e.getSource() == panel_sedata) {
				bigcard.avatardraw = true;
				bigcard.avatarname = playername;
					setArtName();
				invalidate();
				validate();
				repaint();
				
			}	
		}	
			 public void mousePressed(MouseEvent e) { 
			 return;}
			  
			  
		public void mouseClicked(MouseEvent e) {
			
			if (e.getSource() != panel_opdata && e.getSource() != panel_sedata) {
				 CardmasterCard card = (CardmasterCard)(e.getSource());
			
				if (card.dataBlank) {
					out.println("WH#" + card.cardid + "#");
					askdata[card.cardid] = true;
					askdatacounter = 1000;
					
				}
			}
			
			if (!observer)
			for (int i=0;i<8;i++) {
				if (e.getSource() == hand[i]) { //if selecting from your hand.
					 if (must_discard && !hand[i].dummy) {
					 	out.println("DS#"+i+"#");
					 	
					 	
					 	
					 }
			 	 	 else if (!playable(hand[i])) return;
			 	 	 else if (hand[i].typecode.equals("m") && pick_card_to_play) {
			 	 	 	pick_card_to_play = false;
			 	 	 	pick_target_for_play_monster = true;
			 	 	 	selectedcard = hand[i];
			 	 	 	handid = i;
			 	 	 	newMessage("Select place to play Monster.");
						update_highlights();
					 }			 	 	 		
			 	 	 else if (hand[i].typecode.equals("e")&& pick_card_to_play) {
			 	 	 	pick_card_to_play = false;
			 	 	 	pick_target_for_play_effect = true;
			 	 	 	selectedcard = hand[i];
			 	 	 	handid = i;
			 	 	 	newMessage("Select place to play Effect.");
						update_highlights();

					 }
					  else if (hand[i].typecode.startsWith("d")&& pick_card_to_play) {
			 	 	 	pick_card_to_play = false;
			 	 	 	pick_target_for_play_modifier = true;
			 	 	 	selectedcard = hand[i];
			 	 	 	handid = i;
			 	 	 	newMessage("Select card to play Modifier on...");
						update_highlights();

					 }
					 else if (hand[i].typecode.equals("l")) {
					 	out.println("LO#" + i + "#");
					 	newMessage("Attempting to change location...");
					 	updateData();	
					 	
					 }			 	 	 		
			 	 	 else if (hand[i].typecode.equals("s")) {
			 	 	 	if (!hand[i].targeta) {
				 	 	 	pick_target_for_spell = true;
				 	 	 	selectedcard = hand[i];
				 	 	 	handid = i;
				 	 	 	newMessage("Select target for spell");
							update_highlights();
						} else {
							out.println("SA#" + i + "#"); 
							
							newMessage("Attempting to cast spell...");
							updateData();
						}

					 }else {updateData();}	


						
		
							
		




				}  		
			  	
			}
			if (!observer)
			if (e.getSource() == panel_opdata) {
				//System.out.println("Clicked on opponent panel");
				if (pick_target_for_ability && selectedcard.targetp) { //target player
					int play = 1;
					if (playernumber == 1) play = 2;
					out.println("BP#" + abilityfrom + handid + "#" + play + "#");
					selectedcard = null;
					handid = -1;
					newMessage("Attempting to use ability..");
					updateData();	
					
					
					
				}
				else if (pick_target_for_spell && selectedcard.targetp) { //target player
					int play = 1;
					if (playernumber == 1) play = 2;
					out.println("SP#" + play + "#" + handid + "#");
					selectedcard = null;
					handid = -1;
					newMessage("Attempting to cast spell..");
					updateData();	
					
					
					
				}
				else if (pick_target_for_attack) { //Attack general
					out.println("AT#" + handid + "#");
					selectedcard = null;
					handid = -1;
					newMessage("Attempting to attack Player..");
					updateData();
						
				}
				else {updateData();}	
					
					
			}	
				
			else if (e.getSource() == panel_sedata) { // Your Own Panel.
				//System.out.println("Clicked on opponent panel");

				if (pick_target_for_spell && selectedcard.targetp) { //target player
					out.println("SP#" + playernumber + "#" + handid + "#");
					selectedcard = null;
					handid = -1;
					newMessage("Attempting to cast spell..");
					updateData();	
					
					
					
				}

				if (pick_target_for_ability && selectedcard.targetp) { //target player
					out.println("BP#" + abilityfrom + handid + "#" + playernumber  + "#");
					selectedcard = null;
					handid = -1;
					newMessage("Attempting to use ability..");
					updateData();	
					
					
					
				}
				//Abilities, Spells.
					
					
			}				
			if (!observer)
			for (int i=0;i<5;i++) {
				
				
				if (pick_target_for_play_modifier) {
					
					CardmasterCard card = null;
					int playert = -1;
					if (e.getSource() == Omonster[i] || e.getSource() == Oeffect[i]) {
						card = (CardmasterCard)e.getSource();
						playert = absoluteplayer("o");
						
					}
					if (e.getSource() == Smonster[i] || e.getSource() == Seffect[i]) {
						card = (CardmasterCard)e.getSource();
						playert = absoluteplayer("s");
						
					}
					
					if (card != null) {
					
						if (!card.dummy) {
							
							if (selectedcard.typecode.endsWith("b") || selectedcard.typecode.endsWith(card.typecode)) {
							
							
						
								out.println("PD#" + selectedcard.cardid + "#" +playert + "#" + card.typecode + "#"+ i + "#" + handid + "#");
								newMessage("Attempting to play Modifier on " + card.name);
									selectedcard = null;
								handid = -1;
								updateData();
								pick_target_for_play_modifier = false;
							}
						}
					}			
					
				}
				
				
				
				else if (pick_target_for_mod_ability) {
					if ((e.getSource() == panel_opdata || e.getSource() == panel_sedata) && selectedcard.targetp) {
						String playerst = "o";
						if (e.getSource() == panel_sedata) playerst = "s";
						
						out.println("DA#"+modtype+"#"+handid+"#p#"+absoluteplayer(playerst)+"#0#0#");
						newMessage("Attempting to use modifier ability on player...");
						updateData();
						
					}
					
					else {
					
						CardmasterCard card = (CardmasterCard) e.getSource();
						int tslot = -1;
						String ttype = "x";
						int tplayer = 0;
						for (int j=0;j<5;j++) {
							if (card == Seffect[j]) {
								ttype = "e";
								tslot = j;
								tplayer = absoluteplayer("s");
								break;
							}
							if (card == Oeffect[j]) {
								ttype = "e";
								tslot = j;
								tplayer = absoluteplayer("o");
								break;
							}
							if (card == Smonster[j]) {
								ttype = "m";
								tslot = j;
								tplayer = absoluteplayer("s");
								break;
							}
							if (card == Omonster[j]) {
								ttype = "m";
								tslot = j;
								tplayer = absoluteplayer("o");
								break;
							}
							
						}
							
						if (tslot != -1) {
							
							String tcode = "c";
							if (card.dummy) {
								tcode = "s";
							}
						//	newMessage("DEBUG: " + handid + " " + modtype + " " + tcode + " " + tplayer + " " + ttype + " " + tslot + " " + card.name);		
							if (!ttype.equals("x") && targetablebycurrentability(ttype,card)) {
								
								out.println("DA#" + modtype + "#" + handid + "#"+ tcode + "#"+tplayer+"#"+ttype+"#"+tslot+"#");
								String targetname = "slot";
								if (!card.dummy) targetname = card.name;
								newMessage("Attempting to use modifier ability on " + targetname);
								updateData();
								
							} else { 
							} 
						} else { 
						}
						
					}
					
				}
				else if (e.getSource() == deS[i] || e.getSource() == dmS[i]) {
					CardmasterCard card = (CardmasterCard) e.getSource();
					if (pick_card_for_ability && !card.dummy && !card.dizzy && card.ability) {
						if (!card.targeta) {
							handid = i;
							selectedcard = card;
							if (e.getSource() == deS[i]) modtype = "e";
							if (e.getSource() == dmS[i]) modtype = "m";
							
							pick_target_for_mod_ability = true;
							pick_card_for_ability = false;
							newMessage("Select target for ability..");
							update_highlights();
							
						}
						else {
							
							if (e.getSource() == deS[i]) modtype = "e";
							if (e.getSource() == dmS[i]) modtype = "m";
							out.println("DA#" + modtype + "#" + i + "#a#0#0#0#");
						//	System.out.println("DA#" + modtype + "#" + i + "#a#0#0#0#");
							newMessage("Attempting to use modifier ability...");
							updateData();
						}
						
						
						
					}
					
					
					
				}
				
				
				else
				
				if (e.getSource() == Omonster[i]) {
					if (pick_target_for_attack && !Omonster[i].dummy) { //picking attack target
						out.println("AM#" + handid + "#" + i + "#");
						selectedcard = null;
						handid = -1;
						newMessage("Attempting to attack monster...");
						updateData();
					
					}else if (pick_target_for_ability && targetablebycurrentability("m",Omonster[i]) && !Omonster[i].dummy) { //picking attack target
						String command = "BM#"; 
						int opponentnumber = 1;
						if (playernumber ==1) opponentnumber = 2;
						String play = opponentnumber + "#"; // different command if targeting monster
						
						out.println(command + abilityfrom + handid  + "#" + play + i + "#");
						selectedcard = null;
						handid = -1;
						newMessage("Attempting to use ability...");
						updateData();
						
					}else if (pick_target_for_spell && targetablebycurrentability("m",Omonster[i]) && !Omonster[i].dummy) { //picking attack target
						String command = "SM#"; 
						int opponentnumber = 1;
						if (playernumber ==1) opponentnumber = 2;
						String play = opponentnumber + "#"; // different command if targeting monster
						
						out.println(command + play + i + "#"  + handid  + "#");
						selectedcard = null;
						handid = -1;
						newMessage("Attempting to cast spell...");
						updateData();
												
					} else if (pick_card_for_defend && attackdata[i]==6) { // pick attacker
						
						selectedcard = Omonster[i];
						handid = i;
						pick_card_for_defend = false;
						pick_target_for_defend = true;
						
						newMessage("Who shall defend?");
						update_highlights();
						
						
					}
					else {updateData();}	
					//Abilities, Spells.
					
				}
				
				else if (e.getSource() == Smonster[i]) { // One of your monster slots.
					if (pick_card_for_ability && !Smonster[i].dummy && 
						!Smonster[i].dizzy && Smonster[i].ability) {

						
						if (!Smonster[i].targeta) {
							handid = i;
							selectedcard = Smonster[i];
							pick_target_for_ability = true;
							pick_card_for_ability = false;
							newMessage("Select target for ability..");
							update_highlights();
							abilityfrom = "m#";	
							
						}else {
							out.println("BA#m#" + i + "#"); 
							
							newMessage("Attempting to use ability...");
							updateData();	
						}
						
						
					}


					else if ((pick_target_for_defend && !Smonster[i].dummy && (attacklock[i]==0)) && !(Smonster[i].dizzy)) { //picking attack target
						out.println("DF#" + i + "#" + handid  + "#");
						selectedcard = null;
						handid = -1;
						newMessage("Attempting to defend with " + Smonster[i].name + "...");
						updateData();
						
					} 
					else if (pick_target_for_ability && targetablebycurrentability("m",Smonster[i])) { //picking attack target
						String command = "BS#";
						String play = "";
						if (Smonster[i].dummy) command = "BS#";
						else { command = "BM#"; play = playernumber + "#"; } // different command if targeting monster
						
						out.println(command + abilityfrom + handid  + "#" + play + i + "#");
						selectedcard = null;
						handid = -1;
						newMessage("Attempting to use ability...");
						updateData();
						
					} 
					//insert handler for abilitiy
					//spells
					else if (pick_target_for_spell && targetablebycurrentability("m",Smonster[i])) { //picking attack target
						String command = "SS#";
						String play = "";
						if (Smonster[i].dummy) command = "SS#";
						else { command = "SM#"; play = playernumber + "#"; } // different command if targeting monster
						
						out.println(command + play + i + "#" + handid  + "#");
						selectedcard = null;
						handid = -1;
						newMessage("Attempting to cast spell...");
						updateData();
						
					} 					
					else if (pick_card_for_attack && !Smonster[i].dummy && !Smonster[i].dizzy) { //attack
					
						handid = i;
						selectedcard = Smonster[i];
						pick_card_for_attack = false;
						pick_target_for_attack = true;	
						newMessage("Select target to attack.");
						update_highlights();
						
					}

					else if (pick_card_for_sac && !Smonster[i].dummy) { //sac
						if (sacCard == Smonster[i]) {
							out.println("SC#m#" + i + "#");
							updateData();
						
						}
						else sacCard = Smonster[i];
						
					}
					else if (pick_target_for_play_monster && Smonster[i].dummy) {
						//System.out.println(selectedcard.cardid + " " + selectedcard.name);
						out.println("PM#" + selectedcard.cardid + "#" + i + "#" + handid + "#");
						selectedcard = null;
						handid = -1;
						updateData();
						
					}
					else {updateData();}
					
					
				}	
				else if (e.getSource() == Seffect[i]) { // One of your effect slots.
					if (pick_card_for_ability && !Seffect[i].dummy && //click for ability
						!Seffect[i].dizzy && Seffect[i].ability) {

						
						if (!Seffect[i].targeta) {
							handid = i;
							selectedcard = Seffect[i];
							pick_target_for_ability = true;
							pick_card_for_ability = false;
							newMessage("Select target for ability..");
							update_highlights();
							abilityfrom = "e#";	
							
						}else {
							out.println("BA#e#" + i + "#"); 
							
							newMessage("Attempting to use ability ...");
							updateData();	
						}
						
						
					}
					else if (pick_target_for_spell && targetablebycurrentability("e",Seffect[i])) { //picking attack target
						String command = "SS#";
						String play = "";
						if (Seffect[i].dummy) command = "SS#";
						else { command = "SE#"; play = playernumber + "#"; } // different command if targeting monster
						
						out.println(command + play + i + "#" + handid  + "#");
						selectedcard = null;
						handid = -1;
						newMessage("Attempting to cast spell on your effect... ");
						updateData();
						
					} 

					else if (pick_target_for_ability && targetablebycurrentability("e",Seffect[i])) { //picking attack target
						String command = "BS#";
						String play = "";
						if (Seffect[i].dummy) command = "BS#";
						else { command = "BE#"; play = playernumber + "#"; } // different command if targeting monster
						
						out.println(command + abilityfrom + handid  + "#" + play + i + "#");
						selectedcard = null;
						handid = -1;
						newMessage("Attempting to use ability on your effect ....");
						updateData();
						
					} 
					//spells
					
					else if (pick_card_for_sac && !Seffect[i].dummy) {
						if (sacCard == Seffect[i]) {
						out.println("SC#e#" +   i + "#");
						updateData(); }
						else sacCard = Seffect[i];
						 
						
					}
					else if (pick_target_for_play_effect && Seffect[i].dummy) {
						//System.out.println(selectedcard.cardid + " " + selectedcard.name);
						out.println("PE#" + selectedcard.cardid + "#" + i + "#" + handid + "#");
						selectedcard = null;
						handid = -1;
						updateData();
						
					}
					
					else {updateData();}
					
				}
				else if (e.getSource() == Oeffect[i]) { //opponent effect cards
					if (pick_target_for_ability && targetablebycurrentability("e",Oeffect[i]) && !Oeffect[i].dummy) { //picking attack target
						String command = "BE#"; 
						int opponentnumber = 1;
						if (playernumber ==1) opponentnumber = 2;
						String play = opponentnumber + "#"; // different command if targeting monster
						
						out.println(command + abilityfrom + handid  + "#" + play + i + "#");
						selectedcard = null;
						handid = -1;
						newMessage("Attempting to use ability...");
						updateData();
						
					}else if (pick_target_for_spell && targetablebycurrentability("e",Oeffect[i]) && !Oeffect[i].dummy) { //picking attack target
						String command = "SE#"; 
						int opponentnumber = 1;
						if (playernumber ==1) opponentnumber = 2;
						String play = opponentnumber + "#"; // different command if targeting monster
						
						out.println(command + play + i + "#"  + handid  + "#");
						selectedcard = null;
						handid = -1;
						newMessage("Attempting to use ability...");
						updateData();	
				 }else {updateData();}
					
					
					
					
				}			
				
				
				
			}
			  	
			return;
		}
			   public void mouseExited(MouseEvent e) { return;}
			   public void mouseReleased(MouseEvent e) {return; }

			
		}
		
		void turn(String turnplayer, int turnphase, int turnno) {
				nexttimer = 0;
				int newplayersturn;
				String messagestring = turnplayer + "'s ";
				if (turnplayer.equals(playername)) {
					playersturn = 1;
				}
				else {
					 playersturn = 2;
				
				}
				
				if (turnphase != 5)
					for (int i=0;i<5;i++) {
						Omonster[i].animation = false;
						Smonster[i].animation = false;
						Seffect[i].animation = false;
						Oeffect[i].animation = false;
						
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
    		boolean handcards = false;
    		for (int i =0;i<8;i++) {
    				if (!(hand[i].dummy)) handcards = true;
    			
    		}
    		if (!handcards) {
    			
    			return;
    		}
    		newMessage("Pick a card to discard.");	
    		
    	
    	
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
				pick_target_for_mod_ability = false;
				pick_target_for_spell = false;
				pick_target_for_attack = false;
				pick_target_for_defend = false;
				pick_target_for_play_monster = false;
				pick_target_for_play_modifier = false;
				pick_target_for_play_effect = false;
				if (cardstodiscard > handcardsS) cardstodiscard = handcardsS;
				if (cardstodiscard >= 1) must_discard = true;
				else must_discard = false;	
	}
		void updateData() {
			
			//player data
			selectedcard = null;
			String starter = "<img src=" + getCodeBase().toString()
						 + "/images/";
			
			
			if (playernumber == 2 && !showp1mana ||playernumber == 1 && !showp2mana  ) {
					label_Omana.setText("<HTML>" +starter+"dmana.gif>"+ od +"?" +starter+"lmana.gif>"+ ol+"?" +starter+"gmana.gif>"+ og+"?" + "</HTML>");
			}
			else {
				label_Omana.setText("<HTML>" +starter+"dmana.gif>"+ od +starter+"lmana.gif>"+ ol +starter+"gmana.gif>"+ og + "</HTML>");
				
			}
			if (playernumber == 1 && !showp1mana ||playernumber == 2 && !showp2mana  ) {
				
				label_Smana.setText("<HTML>" +starter+"dmana.gif>"+ sd+"?" +starter+"lmana.gif>"+ sl+"?" +starter+"gmana.gif>"+ sg+"?" + "</HTML>");
	}
			else {
				label_Smana.setText("<HTML>" +starter+"dmana.gif>"+ sd +starter+"lmana.gif>"+ sl +starter+"gmana.gif>"+ sg + "</HTML>");

			}
			
			
			
			String olifesymbol = "lifesymbol.gif";
			if (olife >= 300) olifesymbol = "lifesymbol-healthy.gif";
			else if (olife <=75) olifesymbol = "lifesymbol-dying.gif";
			olifesymbol = starter
						  + olifesymbol + ">";


			String slifesymbol = "lifesymbol.gif";
			if (slife >= 300) slifesymbol = "lifesymbol-healthy.gif";
			else if (slife <=75) slifesymbol = "lifesymbol-dying.gif";
			slifesymbol = starter  + slifesymbol + ">";
			
			
			String sdecksymbol = "library-full.gif";
			if (sdeck <= 30) sdecksymbol = "library-half.gif";
			if (sdeck <= 10) sdecksymbol = "library-nearempty.gif";
			if (sdeck == 0) sdecksymbol = "library-empty.gif";
			sdecksymbol = starter  + sdecksymbol + ">";

			String odecksymbol = "library-full.gif";
			if (odeck <= 30) odecksymbol = "library-half.gif";
			if (odeck <= 10) odecksymbol = "library-nearempty.gif";
			if (odeck == 0) odecksymbol = "library-empty.gif";
			odecksymbol = starter  + odecksymbol + ">";
			
			String gravesymbol = starter + "graveyard.gif>";
			String handsymbol=starter + "handsymbol.gif>";
			String damsymbol = starter + "playerdamage.gif>";
			
			int maxchar = 20;
			if (opponentname.length() < maxchar) maxchar = opponentname.length();
			label_Oname.setText(opponentname.substring(0,maxchar));
			maxchar = 20;
			if (playername.length() < maxchar) maxchar = playername.length();
			label_Sname.setText(playername.substring(0,maxchar));
			
			
			if (oshowdamage && odamage > 0) label_Olife.setText("<HTML>"  +olifesymbol + olife + damsymbol + odamage + "</HTML>");
			else label_Olife.setText("<HTML>" +olifesymbol + olife + "</HTML>");
			
			if (sshowdamage && sdamage > 0) label_Slife.setText("<HTML>" + slifesymbol + slife + damsymbol + sdamage + "</HTML>");
			else 	label_Slife.setText("<HTML>" +slifesymbol + slife + "</HTML>");
		
			
//			seavatar.setText("<HTML><img src=" + getCodeBase().toString() + "avatars/" + playername + ".png></HTML>");
//			oavatar.setText("<HTML><img src=" + getCodeBase().toString() + "avatars/" + opponentname + ".png></HTML>");
			
			
			label_Sdamage.setText("<HTML>" +handsymbol +  handcardsS+ " " + sdecksymbol + sdeck + " " + gravesymbol + sgravec+ "</HTML>");
			label_Odamage.setText("<HTML>" +handsymbol +  handcardsO+ " " + odecksymbol + odeck + " "  + gravesymbol + ogravec+ "</HTML>");
		
			
							
			String ophase = "No Phase";
			String sphase = "No Phase";
			button_invisible.setEnabled(true);
			button_surrender.setEnabled(true);
			button_cancel.setEnabled(true);
			for (int i=0; i<5; i++) {
				if ((Omonster[i].dizzy || Omonster[i].dummy)) {
					if (attackdata[i] != 0 && attackdata[i] < 6) attacklock[attackdata[i]-1] = 0;
					attackdata[i] = 0;
					
					
				}
				
				
				
			}
			if ((playersturn == 1)&&(phase==0)) {
				ophase = "Cleanup Phase";
				sphase = "Draw Phase";
				button_nextphase.setEnabled(true);	
			//	button_cancel.setEnabled(false);
			}else if ((playersturn == 1)&&(phase==1)) {
				ophase = "No Phase";
				sphase = "Sac Phase";	
				button_nextphase.setEnabled(true);
				set_flags();
				 pick_card_for_sac = true;
				 sacCard = null;
				//button_cancel.setEnabled(false);
				
			}else if ((playersturn == 1)&&(phase==2)) {
				ophase = "No Phase";
				sphase = "Play Phase";
				set_flags();
				pick_card_to_play = true;	
				button_nextphase.setEnabled(true);
				//button_cancel.setEnabled(false);	
			}else if ((playersturn == 1)&&(phase==3)) {
				ophase = "No Phase";
				sphase = "Attack Phase";
				set_flags();
				pick_card_for_attack = true;
				button_nextphase.setEnabled(true);	
				button_cancel.setEnabled(true);
			}else if ((playersturn == 1)&&(phase==4)) {
				ophase = "Defend Phase";
				sphase = "No Phase";
				set_flags();	
				button_nextphase.setEnabled(false);
				//button_cancel.setEnabled(false);
			}else if ((playersturn == 1)&&(phase==5)) {
				ophase = "Waiting";
				sphase = "Resolution";
				set_flags();
				button_nextphase.setEnabled(false);
				//button_cancel.setEnabled(false);
			}else if ((playersturn == 2)&&(phase==0)) {
				sphase = "Cleanup Phase";
				ophase = "Draw Phase";	
				set_flags();
				button_nextphase.setEnabled(false);
				//button_cancel.setEnabled(false);
			}else if ((playersturn == 2)&&(phase==1)) {
				sphase = "No Phase";
				ophase = "Sac Phase";
				set_flags();	
				button_nextphase.setEnabled(false);
				//button_cancel.setEnabled(false);
			}else if ((playersturn == 2)&&(phase==2)) {
				sphase = "No Phase";
				ophase = "Play Phase";	
				set_flags();
				button_nextphase.setEnabled(false);	
				//button_cancel.setEnabled(false);
			}else if ((playersturn == 2)&&(phase==3)) {
				sphase = "No Phase";
				ophase = "Attack Phase";
				set_flags();	
				button_nextphase.setEnabled(false);
				//button_cancel.setEnabled(true);
			}else if ((playersturn == 2)&&(phase==4)) {
				sphase = "Defend Phase";
				ophase = "No Phase";	
				set_flags();
				pick_card_for_defend = true;
				button_nextphase.setEnabled(true);
			}else if ((playersturn == 2)&&(phase==5)) {
				sphase = "Resolution";
				ophase = "Waiting";
				set_flags();	
				button_nextphase.setEnabled(true);
				//button_cancel.setEnabled(false);
			}
			
			label_Ophase.setText(ophase);
			label_Sphase.setText(sphase);
					
		// Highlight playable cards.
			update_highlights();
		//refresh();
		repaint();	
			invalidate();
			validate();
			
		}
		
		void update_highlights() {
			if (observer) return;
			erase_highlights();		
			for (int i=0 ;i<8;i++) { //playable Spells
				if (checkmana(hand[i])) 
					if (hand[i].typecode.equals("s"))
						hand[i].setBorder(BorderFactory.createLineBorder(Color.green,2));
						
						
				if (selectedcard == hand[i])
						hand[i].setBorder(BorderFactory.createLineBorder(Color.orange,2));	
						
			}			 
			if (pick_card_for_sac) { // Sacrificable Monsters
				for (int i=0; i<5; i++) {
					if (!Smonster[i].dummy && (sacCard == null || sacCard == Smonster[i]))
						Smonster[i].setBorder(BorderFactory.createLineBorder(Color.red,2));	
					if (!Seffect[i].dummy&& (sacCard == null || sacCard == Seffect[i]))
						Seffect[i].setBorder(BorderFactory.createLineBorder(Color.red,2));
						
				}	
				
			}
			if (pick_card_to_play) { // playable monsters during Play Phase.
				for (int i=0 ;i<8;i++) {
					if (checkmana(hand[i]))
						hand[i].setBorder(BorderFactory.createLineBorder(Color.green,2));
				if (selectedcard == hand[i])
						hand[i].setBorder(BorderFactory.createLineBorder(Color.orange,2));	
				}		
				
				
			}
					
			if (pick_target_for_play_monster) {
				for (int i=0; i<5; i++) {
					if (Smonster[i].dummy)
						Smonster[i].setBorder(BorderFactory.createLineBorder(Color.red,2));		
					
					
				}
				
				
			}
			
			if (pick_target_for_play_modifier) {
				for (int i=0; i<5; i++) {
				
					String type = selectedcard.typecode;
					
					
					
					if (type.endsWith("m") || type.endsWith("b")) {
						if (!Omonster[i].dummy) Omonster[i].setBorder(BorderFactory.createLineBorder(Color.red,2));	
						if (!Smonster[i].dummy) Smonster[i].setBorder(BorderFactory.createLineBorder(Color.red,2));	
					}
					if (type.endsWith("e") || type.endsWith("b")) {
						if (!Seffect[i].dummy) Seffect[i].setBorder(BorderFactory.createLineBorder(Color.red,2));	
						if (!Oeffect[i].dummy) Oeffect[i].setBorder(BorderFactory.createLineBorder(Color.red,2));	
					}
					
					
				}
				
				
			}		
			


			if (pick_target_for_play_effect) {
				for (int i=0; i<5; i++) {
					if (Seffect[i].dummy)
						Seffect[i].setBorder(BorderFactory.createLineBorder(Color.red,2));		
					
					
				}
				
				
			}

// ATTACKING

			if (pick_card_for_attack) {
				for (int i=0; i<5; i++) {
					if (!Smonster[i].dummy && !Smonster[i].dizzy) {
					
						
						if (Smonster[i].dummy) continue;
						Smonster[i].setBorder(BorderFactory.createLineBorder(Color.green,2));						
					}
				}
				
				
			}

			if (pick_target_for_attack) {
				for (int i=0; i<5; i++) {
					if (!Omonster[i].dummy)
						Omonster[i].setBorder(BorderFactory.createLineBorder(Color.red,2));		
					if (handid == i) {
						//System.out.println("Border for " + i + " is now orange");
						Smonster[i].setBorder(BorderFactory.createLineBorder(Color.orange,2));	
					}
				}
				
					panel_opdata.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.red,new Color(100,0,0)));
				
			}

//DEFENDING
			Color[] colors =new Color[5];
			colors[0] = new Color(125,0,0);
			colors[1] = new Color(121,85,0);
			colors[2] = new Color(112,121,0);
			colors[3] = new Color(120,1,120);
			colors[4] = new Color(229,240,57);
			if (pick_card_for_defend) { //click an opponent attacking monster.
				for (int i=0; i<5; i++) {
					if (!Omonster[i].dummy && !Omonster[i].dizzy && attackdata[i] == 6)
						Omonster[i].setBorder(BorderFactory.createLineBorder(Color.red,2));		
					else if (selectedcard == Omonster[i] && !Omonster[i].dizzy && !Omonster[i].dummy)
						Omonster[i].setBorder(BorderFactory.createLineBorder(Color.orange,2));
					else if (!Omonster[i].dummy && !Omonster[i].dizzy && attackdata[i] > 0) {
						Omonster[i].setBorder(BorderFactory.createLineBorder(colors[i],2));
						Smonster[attackdata[i]-1].setBorder(BorderFactory.createLineBorder(colors[i],2));
						
						
					}	
					
				}
				
				
			}

			if (pick_target_for_defend) {
				for (int i=0; i<5; i++) {
					if (!Smonster[i].dummy && attacklock[i] == 0 )
						if (!Smonster[i].dizzy) Smonster[i].setBorder(BorderFactory.createLineBorder(Color.green,2));		
					if (selectedcard == Omonster[i])
						Omonster[i].setBorder(BorderFactory.createLineBorder(Color.orange,2));	
					
				}
				
				
			}

			if (must_discard) { // playable monsters during Play Phase.
				for (int i=0 ;i<8;i++) {
					
					if(!hand[i].dummy) hand[i].setBorder(BorderFactory.createLineBorder(Color.red,2));
				
						
				}		
				
				
			}
			
			if (pick_card_for_ability) {
				for (int i=0;i<5;i++) {
					if(!Smonster[i].dummy && !Smonster[i].dizzy) {
						if (Smonster[i].ability)
							Smonster[i].setBorder(BorderFactory.createLineBorder(Color.green,2));		

					}
					if(!Seffect[i].dummy && !Seffect[i].dizzy) {
						if (Seffect[i].ability)
							Seffect[i].setBorder(BorderFactory.createLineBorder(Color.green,2));		

					}				
					
					
					if(!dmS[i].dummy && !dmS[i].dizzy) {
						if (dmS[i].ability)
							dmS[i].setBorder(BorderFactory.createLineBorder(Color.green,2));		

					}
					if(!deS[i].dummy && !deS[i].dizzy) {
						if (deS[i].ability)
							deS[i].setBorder(BorderFactory.createLineBorder(Color.green,2));		

					}	
					
					
					
					
					
				}				
				
				
				
			}	

			if (pick_target_for_ability || pick_target_for_mod_ability) {
				for (int i=0;i<5;i++) {
					
						if (targetablebycurrentability ("m",Smonster[i]))
							Smonster[i].setBorder(BorderFactory.createLineBorder(Color.red,2));		

						if (targetablebycurrentability ("m",Omonster[i]) && !Omonster[i].dummy)
							Omonster[i].setBorder(BorderFactory.createLineBorder(Color.red,2));				
					
						if (targetablebycurrentability ("e",Seffect[i]))
							Seffect[i].setBorder(BorderFactory.createLineBorder(Color.red,2));		

						if (targetablebycurrentability ("e",Oeffect[i]) && !Oeffect[i].dummy)
							Oeffect[i].setBorder(BorderFactory.createLineBorder(Color.red,2));								
						
					
				}
				for (int i= 0;i<grave.length;i++) {
					if (targetablebycurrentability ("x",grave[i]))
						grave[i].setBorder(BorderFactory.createLineBorder(Color.red,2));	
					
					
				}				
				if (selectedcard != null)
				if (selectedcard.dummy == false)
				if (selectedcard.targetp) {
					panel_opdata.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.red,new Color(40,0,0)));
					panel_sedata.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.red,new Color(40,0,0)));
					
					
				}
				
				
			}

			if (pick_target_for_spell) {
				for (int i=0;i<5;i++) {
					
						if (targetablebycurrentability ("m",Smonster[i]))
							Smonster[i].setBorder(BorderFactory.createLineBorder(Color.red,2));		

						if (targetablebycurrentability ("m",Omonster[i]) && !Omonster[i].dummy)
							Omonster[i].setBorder(BorderFactory.createLineBorder(Color.red,2));				
					
						if (targetablebycurrentability ("e",Seffect[i]))
							Seffect[i].setBorder(BorderFactory.createLineBorder(Color.red,2));		

						if (targetablebycurrentability ("e",Oeffect[i]) && !Oeffect[i].dummy)
							Oeffect[i].setBorder(BorderFactory.createLineBorder(Color.red,2));								
					
					
				}				
				for (int i= 0;i<grave.length;i++) {
					if (targetablebycurrentability ("x",grave[i]))
						grave[i].setBorder(BorderFactory.createLineBorder(Color.red,2));	
					
					
				}
				if (selectedcard != null) 
					if (!selectedcard.dummy && selectedcard.targetp) {
					panel_opdata.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.red,new Color(40,0,0)));
					panel_sedata.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.red,new Color(40,0,0)));
					
					
					
				}
				
				
			}

			
		}
	    boolean isinplay(int cardid) {
	    	for (int i=0;i<5;i++) {
	    		if (e1[i].cardid == cardid ) return true;
	    		if (e2[i].cardid == cardid ) return true;
	    			    		if (m2[i].cardid == cardid ) return true;
	    			    		if (m1[i].cardid == cardid ) return true;
	    			    		
	    	}
	    	return false;
	    }
		boolean checkmana(CardmasterCard card) {
		 	if (card.dummy) return false;
		 	int D = card.Dcost;
		 	int L = card.Lcost;
		 	int G = card.Gcost;
		 	
		 	if (location.cardid == 420) { G-=30; if (G<=0) G = 0; }
		 	if (location.cardid == 419) { L-=30; if (L<=0) L = 0; }
		 	if (location.cardid == 418) { D-=30; if (D<=0) D = 0; }
		 	if (location.cardid == 679) { 
		 		if (card.typecode.equals("m")) { 
		 			D-=30; if (D<=0) D = 0;
		 			G-=30; if (G<=0) G = 0;
		 		}
		 		
		 	
		 	}
		 	
		 	
		 	if (isinplay(122)) { G-=30; if (G<=0) G = 0;  L-=30; if (L<=0) L = 0; }
			if (card.mtype != null)
			if (isinplay(834) && ismType(card,"ship")) {
				G = 0;
				D = 0;
				L = 0;
			}
		 	if (location.cardid == 540 && card.typecode.equals("e")) { D-=30; if (D<=0) D = 0; L-=30; if (L<=0) L = 0;  G-=30; if (G<=0) G = 0; }
		 	
		 	if (isinplay(726)) { G-=30; if (G<=0) G = 0;}
		 	
		 	if (card.mtype != null)
		 	if (isinplay(780) && ismType(card,"generator")) { G-=30;L-=30;D-=30;}
		 	if (D == 0 && D > sd) D = sd;
		 	if (L == 0 && L > sl) L = sl;
		 	if (G == 0 && G > sg) G = sg;		 	
		 	if (D <= sd && L <= sl && G <= sg) return true;
		 	else return false;	
		 	
		 	
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
				
				dmS[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));
				
				deS[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));
				
				dmO[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));
				
				deO[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));	
				hand[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));
				//Ohand[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));
			}
			for (int i = 5; i<8; i++) {
				hand[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));
				//Ohand[i].setBorder(BorderFactory.createLineBorder(Color.darkGray));
			}
			panel_sedata.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			panel_opdata.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));	
		
		}
		
			
		void bigcardspell(String datastring) {
			StringTokenizer tokenizer2 = new StringTokenizer(datastring,".");
			String first = tokenizer2.nextToken();
				
			if (!first.toString().equals("b")) {
				int cardid = Short.parseShort(first);
				askAboutCard(cardid);
				String cardtype = tokenizer2.nextToken();
				int speed = Short.parseShort(tokenizer2.nextToken());
				int background = Short.parseShort(tokenizer2.nextToken());
			
			String cardname = tokenizer2.nextToken();//stem.out.println("Found " + cardname);
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
							 		newCard.blkDizzy = false;
							 		newCard.speed = speed;
							 		newCard.background = background;
				 		newCard.targetm = targetm;
				 		newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase + "/";
  		
						bigcard.copydata(newCard);
						setArtName();
						bigcard.targetg = targetg;
						repaint();
					//	bigcard.urlcodebase = getCodeBase().toString();
					}
				
		}	
		CardmasterCard newGraveCard() {
			CardmasterCard[] tempcards = new CardmasterCard[grave.length];
			System.arraycopy(grave,0,tempcards,0,grave.length);
			grave = new CardmasterCard[grave.length +1];
			System.arraycopy(tempcards,0,grave,0,tempcards.length);
			grave[grave.length-1] = new CardmasterCard();
			grave[grave.length-1].SetImageManager(manager);
			//stem.out.println("Adding new card to grave...");
			return grave[grave.length-1];
			
			
		}	
		
		void updateHand(String carddatastring) {
				StringTokenizer handtokenizer = new StringTokenizer(carddatastring,"#");
				handtokenizer.nextToken();
				String name = handtokenizer.nextToken();
				if (! name.equals(playername)) return;
				//handcardsS = 0;
				
				for(int i=0;i<8;i++) { //player 1 hand
					//	if (!observer) {
						CardmasterCard newCard = createLoadableCard(Short.parseShort(handtokenizer.nextToken()));
					 	newCard.drawdizzy = false;
						newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase + "/";
  						
					if (!observer)	PlayerCard(playernumber,"h",i).copydata(newCard);
					if (!observer)	PlayerCard(playernumber,"h",i).urlcodebase = newCard.urlcodebase;
					//	}
					//	else handtokenizer.nextToken();
						
						// handcardsS++;
						
						}
						updateData();
		}
								
		void updateGrave(String carddatastring) {
			
				StringTokenizer tokenizer = new StringTokenizer(carddatastring,"#");
				tokenizer.nextToken();
				String gravefor = tokenizer.nextToken();
				if (!gravefor.equals(playername)) return;

			panel_grave.removeAll();	
			grave = new CardmasterCard[0];
			String ctext;
			while (tokenizer.hasMoreTokens()) {
			ctext = tokenizer.nextToken();
				

			CardmasterCard newCard = createLoadableCard(Short.parseShort(ctext));
					 
			//	CardmasterCard newCard = convertToCard(ctext);
						CardmasterCard graveCard = newGraveCard();
						graveCard.copydata(newCard);
						graveCard.urlcodebase = newCard.urlcodebase;//	System.out.println("Card in grave: " + graveCard.name);
						graveCard.setPreferredSize(new Dimension((int)(getWidth()/(620.0/60.0)),(int)(getHeight()/(420.0/70.0))));
						graveCard.addMouseListener(new graveMouseEvents());
							graveCard.urlcodebase = getCodeBase().toString();
				 		graveCard.urlcodebase = graveCard.urlcodebase + "/";
  						JPanel temppane = new JPanel();
						panel_grave.add(temppane);
						temppane.add(graveCard);					
						
					}
					
						updateData();
				
		}
	public boolean ismType(CardmasterCard card, String type) {
		if (card.dummy) return false;
		String cardtype = card.mtype.toLowerCase();
		type = type.toLowerCase();
		boolean istype = true;
		if (cardtype.indexOf(type) == -1 && cardtype.indexOf("*") == -1) istype = false;
		else istype = true;
		return istype;
		
	}			
	CardmasterCard createLoadableCard(int cardid) {
		return createLoadableCard(cardid,true);
	}
	void askAboutCard(int cardid) {
		if (carddata[cardid] == null) {
				if (! askdata[cardid]) {
					out.println("WH#" + cardid + "#");
				}
				askdata[cardid] = true;
			}
		}
	CardmasterCard createLoadableCard(int cardid, boolean ask)  {
		if (carddata[cardid] == null) {
		//	System.out.println(cardid + " UNKNOWN ");
		
			CardmasterCard newCard = new CardmasterCard();
			newCard.SetImageManager(manager);
			//newCard.dummy = false;
			newCard.dataBlank = true;
			newCard.cardid = cardid;
			if (! askdata[cardid] && ask) {
			//	System.out.println("Asking about it..");
				out.println("WH#" + cardid + "#");
			
			}
			else {
			//	System.out.println("Already asked about it..");
			}
			askdata[cardid] = true;
			askdatacounter = 1000;
			
			return newCard;
		}
		else {
			CardmasterCard newCard = new CardmasterCard();
			newCard.SetImageManager(manager);
			newCard.copydata(carddata[cardid]);
			return newCard;
		}
			
		
		
	}
	
	CardmasterCard convertToCard(String singlecard) { // converts a single card

								
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
	
		void updateMods(String moddatastring) {
			lastmodstring =null;
			StringTokenizer tokenizer = new StringTokenizer(moddatastring,"#");
			tokenizer.nextToken();

		
				for (int j=0;j<4;j++) {
					String type = "dm";
					int play = 1;
					if (j==1 || j==3 ) type = "de";
					if (j >= 2) play = 2;
					
					
					for(int i=0;i<5;i++) {
						modinplay = false;
						
						
						String nexttok = tokenizer.nextToken();
						
						
						
					
						
			if (nexttok.equals("NONE")) {
				modinplay = false;
				for (int k=0;k<5;k++) {
					dmO[k].copydata(new CardmasterCard());
					dmS[k].copydata(new CardmasterCard());
					deO[k].copydata(new CardmasterCard());
					deS[k].copydata(new CardmasterCard());
					
				}
				updateData();
				return;
			}						
						
						
							
						CardmasterCard newCard = convertToCard(nexttok);
						if (!newCard.dummy) askAboutCard(newCard.cardid);
						
						PlayerCard(play,type,i).copydata(newCard);
						PlayerCard(play,type,i).urlcodebase = newCard.urlcodebase;
					
					
					}
				}
				
				
				
			
			
			
			updateData();	
		}
		void updateCards(String carddatastring) {
			lastcardstring = null;
			//System.out.println(carddatastring);
		//	handcardsS = 0;
		//	handcardsO = 0;
			StringTokenizer tokenizer = new StringTokenizer(carddatastring,"#");
			tokenizer.nextToken(); // eat the command.
			//System.out.println("Player 1 Monsters");
			for(int i=0;i<5;i++) { //player 1 monsters
				
				CardmasterCard newCard = convertToCard(tokenizer.nextToken());
					if (!newCard.dummy) askAboutCard(newCard.cardid);
					PlayerCard(1,"m",i).copydata(newCard);
					PlayerCard(1,"m",i).urlcodebase = newCard.urlcodebase;			
			}//System.out.println("Player 1 Effects");
			for(int i=0;i<5;i++) { //player 1 effects
				
					CardmasterCard newCard = convertToCard(tokenizer.nextToken());
					if (!newCard.dummy) askAboutCard(newCard.cardid);
					PlayerCard(1,"e",i).copydata(newCard);
					PlayerCard(1,"e",i).urlcodebase = newCard.urlcodebase;		
			}
			
			int p1deck = Short.parseShort(tokenizer.nextToken()); // player 1 deck
		//	String x = tokenizer.nextToken();
		//	StringTokenizer handtokenizer = new StringTokenizer(x,"^");
			//System.out.println("Player 1 Hand");
			//System.out.println(x);
		/*	for(int i=0;i<8;i++) { //player 1 hand
					//	if (!observer) {
						CardmasterCard newCard = createLoadableCard(Short.parseShort(handtokenizer.nextToken()), playernumber==1);
					 	newCard.drawdizzy = false;
						newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase.substring(0,newCard.urlcodebase.length()-3) + "/";
  						
					if (!observer)	PlayerCard(1,"h",i).copydata(newCard);
					if (!observer)	PlayerCard(1,"h",i).urlcodebase = newCard.urlcodebase;
					//	}
					//	else handtokenizer.nextToken();
						
						if (newCard.cardid !=0) if (playernumber == 1) handcardsS++;
						else handcardsO++; 
						
						
						
			}	*/		
			
			//System.out.println("Player 2 monsters");
			for(int i=0;i<5;i++) { //player 2 monsters
					CardmasterCard newCard = convertToCard(tokenizer.nextToken());
					if (!newCard.dummy) askAboutCard(newCard.cardid);
						PlayerCard(2,"m",i).copydata(newCard);
					PlayerCard(2,"m",i).urlcodebase = newCard.urlcodebase;		
			}
			for(int i=0;i<5;i++) { //player 2 effects
		//	System.out.println(i);
				CardmasterCard newCard = convertToCard(tokenizer.nextToken());
				if (!newCard.dummy) askAboutCard(newCard.cardid);
						PlayerCard(2,"e",i).copydata(newCard);
					PlayerCard(2,"e",i).urlcodebase = newCard.urlcodebase;			
			}
			
			int p2deck = Short.parseShort(tokenizer.nextToken()); // player 2 deck
	//	x = tokenizer.nextToken();
	//	 handtokenizer = new StringTokenizer(x,"^");
			//System.out.println("Player 1 Hand");
			//System.out.println(x);
			
		/*	for(int i=0;i<8;i++) { //player 2 hand
			
			CardmasterCard newCard = createLoadableCard(Short.parseShort(handtokenizer.nextToken()), playernumber==2);
					 	
					 	//if (!observer) {
					 	//		 	newCard.drawdizzy = false;			 	
				 		newCard.urlcodebase = getCodeBase().toString();
				 		newCard.urlcodebase = newCard.urlcodebase.substring(0,newCard.urlcodebase.length()-3) + "/";
					if (!observer)	PlayerCard(2,"h",i).copydata(newCard);
					if (!observer)	PlayerCard(2,"h",i).urlcodebase = newCard.urlcodebase;
						
						//}
						//else handtokenizer.nextToken();
							if (newCard.cardid !=0) if (playernumber == 2) handcardsS++;
						else handcardsO++;				 		
				 				
						
			}*/
			
			
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
		//	if (location.dataBlank) System.out.println("Location Datablank");
		//	if (carddata[location.cardid] != null) System.out.println("have location data");
		//	System.out.println("Location cardid: " + location.cardid);
			if (location.dataBlank && carddata[location.cardid] != null) {
				//System.out.println("Updating Location dATA!!!!!!!!!!!");
				location.copydata(carddata[location.cardid]);
				location.dataBlank = false;	
				location.repaint();
				
				
			}
			if (!observer)
			for (int i=0;i<8;i++) {
				if (hand[i].dataBlank && carddata[hand[i].cardid] != null) {
					//askdatacounter = -1;
					hand[i].copydata(carddata[hand[i].cardid]);
					hand[i].dataBlank= false;
					hand[i].repaint();
				}
			/*	if (Ohand[i].dataBlank && carddata[Ohand[i].cardid] != null) {
					//askdatacounter = -1;
					Ohand[i].copydata(carddata[Ohand[i].cardid]);
					Ohand[i].dataBlank= false;
				}				
			*/	
			}
			for (int i= 0;i<grave.length;i++) {
				if (grave[i].dataBlank && carddata[grave[i].cardid] != null) {
					//askdatacounter = -1;
					grave[i].copydata(carddata[grave[i].cardid]);
					grave[i].dataBlank= false;
					grave[i].repaint();
				}				
				
			}
			updateData();
			if (lastcardstring != null) updateCards(lastcardstring);
			if (lastmodstring != null) updateMods(lastmodstring);	
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
				else if (type.equals("dm")) {
					return dmS[i];
				}
				else if (type.equals("de")) {
					return deS[i];
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
			/*	else if (type.equals("h")) {
					return Ohand[i];
				}*/
				else if (type.equals("dm")) {
					return dmO[i];
				}
				else if (type.equals("de")) {
					return deO[i];
				}
				else return null;
				
				
				
			}
			
			
			
		}	
		
        public void actionPerformed(ActionEvent evt){
        	
			if (evt.getSource() == button_ability) {
				set_flags();
				selectedcard = null;
				pick_card_for_ability = true;
				update_highlights();
				nexttimer = 0;
				
			}
			else if (evt.getSource() == button_switchmod) {
				String layoutcard = "CARD";
				if (viewmods == false) {
					layoutcard = "MOD";
					button_switchmod.setText("Cards");
					
				} 
				else {
					button_switchmod.setText("Mods");
				}
				viewmods = !viewmods;
				((CardLayout)panel_opmonster.getLayout()).show(panel_opmonster,layoutcard);
				((CardLayout)panel_semonster.getLayout()).show(panel_semonster,layoutcard);
				((CardLayout)panel_opeffect.getLayout()).show(panel_opeffect,layoutcard);
				((CardLayout)panel_seeffect.getLayout()).show(panel_seeffect,layoutcard);
				
				
				
				
			}
        	else if (evt.getSource() == chat_enter) {
        		String nametouse = playername;
        		if (observer) nametouse = myname;
        		
        		if (chat_enter.getText().equals("/loadfix")) {
        			for (int i=0;i<8;i++) {
        				
 			     		if (hand[i].dataBlank)	out.println("WH#" + hand[i].cardid + "#"); 	
        				
        			}	
        			if (location.dataBlank) out.println("WH#" + location.cardid + "#");
        			chat_enter.setText("");
        			
        		}
        		else if (!(chat_enter.getText().equals(""))) {
	        		out.println("CM#" + nametouse + "#" + chat_enter.getText().replace('#',' ') + "#");
	        		chat_enter.setText("");
	        	}
	        	nexttimer = 0;
        	}
        	else if (evt.getSource() == button_up) {
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
        	else if (evt.getSource() == button_abilityresolve && !observer) {
        		int sendno = 0;
        		if (abilityresolve) sendno = 0;
        		else sendno = 1;
        		out.println("AR#" + sendno + "#");
        		nexttimer = 0;
        		
        	}
        	else if (evt.getSource() == button_cancel && !observer) {
        		
        		if (playersturn == 1 && phase == 3) out.println("CM#" + playername + "#/attackcancel#");
        		if (playersturn == 2 && phase == 4) out.println("CM#" + playername + "#/attackcancel#");
        		
        		button_surrender.setText("Surrender");
        		giveup = false;
        		updateData();
        		nexttimer = 0;
        	
        		
        	}
        	else if (evt.getSource() == button_surrender && !observer) {
        		if (giveup == false) {
        			button_surrender.setText("You Sure?");
        			giveup = true;
       			}
        		else out.println("CM#" + playername + "#/surrender#");
        		
        		nexttimer = 0;
        		
        	}
        
        	else if (evt.getSource() == button_nextphase) {
        		if (!observer)
        		out.println("NP#");
        		nexttimer = 0;
        		
        	}
        	   
        	else if (evt.getSource() == timer) { 
        	
        	if (!observer && button_nextphase.isEnabled()) {
						
						nexttimer++;
						if (nexttimer >= 3000) {
							nexttimer = 0;
							
	        				out.println("NP#");
							
	
	
						}
						if (nexttimer == 1500) {
							newMessage("Warning: If you do not perform an action soon your phase will automatically advance");
						}
						if (nexttimer == 600) {
							newMessage("Waiting for your action");
						}
						
						if (nexttimer == 2000) {
							newMessage("Warning: Automatic phase advancement immenent");
						}
					//	System.out.println("TEST TIMER A: " + nexttimer);
	
					}
        		  
        		if (resolvecounter >= 0 && numabilities >0 && !abilityresolve){
        			resolvecounter--;
        			int old = resolvetimerno;
	        		resolvetimerno = resolvecounter / 30;
	        		
	        		if (old != resolvetimerno) toggleFocus = !toggleFocus;
					
					if (toggleFocus) button_abilityresolve.setText("Waiting (" + numabilities + ")" + "[" + resolvetimerno + "]");		
					else  button_abilityresolve.setText("Resolve (" + numabilities + ")" + "[" + resolvetimerno + "]");		
					
	        		if (resolvetimerno == 0) {
	        			out.println("AR#1#");
	        			resolvetimerno = -600;
	        			resolvecounter = -600;
	        						
	        		}
				}
	        	try{
	        		if (askdatacounter > 0) askdatacounter--;
	        		if (askdatacounter == 0) {
        				for (int i=0;i<8;i++) {
        				
 			     			if (hand[i].dataBlank)	out.println("WH#" + hand[i].cardid + "#"); 
 			     			askdata[hand[i].cardid] = false;		
        				
        				}	
        				if (location.dataBlank) out.println("WH#" + location.cardid + "#");	        			
	        			askdata[location.cardid] = false;
	        			
	        			
	        			askdatacounter = 1000;
	        		}
		        	if (readThread != null)
		        	if (readThread.isNext()) 
		        	if ((fromServer = readThread.getNext()) != null)
		        	{
		        		
		        		readThread.remNext();
		        		
		        		
			    		
			    		
			    		
			    		
			    		StringTokenizer tokenizer = new StringTokenizer(fromServer,"#");
			    		if (tokenizer.hasMoreTokens()) {
						String command = tokenizer.nextToken("#");
					//	System.out.println("Got " + command + " " + fromServer);
						if (command.equals("CHA")) {
							String name = tokenizer.nextToken("#");
							if (name.equals("SYSTEM"))
								newMessage(tokenizer.nextToken("#"));							
							else {
							
								chat_log.append("<" + name + ">" + tokenizer.nextToken("#") + "\n");
								chat_log.setText(chat_log.getText());
							}
						}else if (command.equals("MES")) {
							
							newMessage(tokenizer.nextToken("#"));
							
						

						}else if (command.equals("SBC")) {
							
							bigcardspell(tokenizer.nextToken("#"));
							
						} 
						else if (command.equals("VICTORYBY")) {
						//	System.out.println("GOT " + fromServer);
							victorybycard = tokenizer.nextToken("#");
							victorybytype = tokenizer.nextToken("#");
							
						}
						else if (command.equals("WIN")) {
							
							//System.out.println("GOT " + fromServer);
							if (!observer) {
							int winner = Short.parseShort(tokenizer.nextToken("#"));
							int winpoints = Short.parseShort(tokenizer.nextToken("#"));
							int losspoints = Short.parseShort(tokenizer.nextToken("#"));
							if (winner == playernumber) { newMessage("You are victorious!"); 
							getAppletContext().showDocument(new URL(getCodeBase().toString() + "page.jsp?pageurl=ReportWin.jsp&a=1&points=" + winpoints+"&card=" + victorybycard + "&type=" +victorybytype));
							
							
							}
							else { newMessage("You have lost!"); 
							getAppletContext().showDocument(new URL(getCodeBase().toString() + "page.jsp?pageurl=ReportWin.jsp&a=0&points=" + losspoints+"&card=" + victorybycard + "&type=" +victorybytype));
							
							}
							} else {
									int winner = Short.parseShort(tokenizer.nextToken("#"));
							int winpoints = Short.parseShort(tokenizer.nextToken("#"));
							int losspoints = Short.parseShort(tokenizer.nextToken("#"));
							String winnern = "NULL";
							if (winner == playernumber)
								winnern = playername;
							else winnern = opponentname;
								
							getAppletContext().showDocument(new URL(getCodeBase().toString() + "page.jsp?pageurl=ReportObWin.jsp&a=" + winnern +"&card=" + victorybycard + "&type=" +victorybytype));
							
								
								
								
							}
							
						} 
						else if (command.equals("PRO")) {
							int protover = Short.parseShort(tokenizer.nextToken());
							if (protover != protocolnum) {
							
								newMessage("Protocol Version Mismatch!");
								chat_log.append(" * PROTOCOL MISMATCH * ");
								chat_log.append("Chances are, this game will crash");
								chat_log.append("Please clear your browser cache to ensure the newest Client version is downloaded");
								chat_log.setText(chat_log.getText());
							}
						}
						else if (command.equals("CDD")) {
							int cardid = Short.parseShort(tokenizer.nextToken());
						//	System.out.println("Received data for " + cardid);
							boolean alreadyhas = (carddata[cardid] != null);
							String cardData = tokenizer.nextToken();	
							carddata[cardid] = convertToCard(cardData);
							if (!alreadyhas) {
								cardlistdata[cardid].setName(carddata[cardid].name);
								cardlistmodel.addElement(cardlistdata[cardid]);	
								
							}
							updateUnknowns();
							
							
							
							
							
						}
						else if (command.equals("DSS")) {
							
							if (!observer && Short.parseShort(tokenizer.nextToken())==playernumber) {
								
								//cardstodiscard--;
								cardstodiscard = Short.parseShort(tokenizer.nextToken());
    							boolean handcards = false;
    							for (int i =0;i<8;i++) {
    								if (!(hand[i].dummy)) handcards = true;
    			
    							}								
								if (cardstodiscard <= 0 || !handcards) must_discard= false;
								updateData();
								
								
								
							}	
							
							
						}
						else if (command.equals("OBN") && observer) { // observer names
							playernumber = 1;
							playername = tokenizer.nextToken("#");
							opponentname = tokenizer.nextToken("#");
							chat_enter.setEditable(true);
							
						}
						else if (command.equals("ANM")) {
							//System.out.println(fromServer);
							int playernum = Short.parseShort(tokenizer.nextToken());
							String type = tokenizer.nextToken();
							int slot = Short.parseShort(tokenizer.nextToken());
							String filename = tokenizer.nextToken();
							PlayerCard(playernum,type,slot).animate(filename);
							
							
							
						}
						else if (command.equals("MDN") && !observer) {
							int playernum = Short.parseShort(tokenizer.nextToken());
							
							int newcards = Short.parseShort(tokenizer.nextToken());
							if (playernum == playernumber) {
								cardstodiscard = newcards;
								}	
							if (cardstodiscard <= 0 && playernum == playernumber) must_discard = false;
							updateData();
							
							if (cardstodiscard > 0)
							mustdiscard(playernum);	
							
							
							
						}
						
					else if (command.equals("ABR") && !observer) {
							int p1abr = Short.parseShort(tokenizer.nextToken());
							int p2abr = Short.parseShort(tokenizer.nextToken());
							int numabilities = Short.parseShort(tokenizer.nextToken());
							this.numabilities = numabilities;
							int pabr = 0;
							if (playernumber == 1) pabr = p1abr;
							if (playernumber == 2) pabr = p2abr;
							boolean oldar = abilityresolve;
							
							if (pabr == 1) abilityresolve = true;
							if (pabr == 0) abilityresolve = false;
							
							if (oldar != abilityresolve) changeresolve = true;
							else changeresolve = false;
							
							if (numabilities ==0) button_abilityresolve.setEnabled(false);
							if (numabilities >=1) button_abilityresolve.setEnabled(true);
							if (numabilities > 0) {
								
								if (abilityresolve)	{ 
									button_abilityresolve.setText("Cancel (" + numabilities + ")");
									resolvecounter = -600;
									resolvetimerno = -600;
								}
								else  {
									 resolvecounter = 2000;
									 resolvetimerno = 10;
									button_abilityresolve.setText("Resolve (" + numabilities + ")");		
									
								}								
							}
							else {
								button_abilityresolve.setText("Resolve (0)[0]");		
								
								
							}
						}
						
						else if (command.equals("ATR")) {
							int attacker = Short.parseShort(tokenizer.nextToken());
							for (int i = 0;i<5;i++) { // defender monsters damage
								if (attacker != playernumber) {
									Smonster[i].showdamage = true;
									Smonster[i].damagetaken = Short.parseShort(tokenizer.nextToken());	
									
									
								}	
								else {
									Omonster[i].showdamage = true;
									Omonster[i].damagetaken = Short.parseShort(tokenizer.nextToken());	
									
									
								}									
								
							}
							for (int i = 0;i<5;i++) { //attacker monster damager
								if (attacker != playernumber) {
									Omonster[i].showdamage = true;
									Omonster[i].damagetaken = Short.parseShort(tokenizer.nextToken());	
									
									
								}	
								else  {
									Smonster[i].showdamage = true;
									Smonster[i].damagetaken = Short.parseShort(tokenizer.nextToken());	
									
									
								}									
								
							}
							if (attacker != playernumber) { //player damage.
								sshowdamage = true;
								sdamage = Short.parseShort(tokenizer.nextToken());
								
								
								}							
							else  {
								oshowdamage = true;
								odamage = Short.parseShort(tokenizer.nextToken());
								
								
							}
							updateData();							
						}
						else if (command.equals("OPF")) {
							String temp = tokenizer.nextToken();
							if (!(temp.equals(playername))) {
								opponentname = temp;
								chat_enter.setEditable(true);
								button_nextphase.setEnabled(true);
								button_ability.setEnabled(true);
							//	button_graveyard.setEnabled(true);
								chat_log.append("* " + opponentname + " has joined the duel!\n");
								
								chat_log.setText(chat_log.getText());
								newMessage("It's Dueling Time!");
								
								
								
								out.println("DB#");
							}
						
						}
								
						else if (command.equals("ONF")) {
							
							newMessage("Opponent could not be found.");
							
						}			    		
						else if (command.equals("PNG")) {
							
							out.println("PN#");
							
						}			    		
						else if (command.equals("CLO")) {
							
							newMessage("Game Closed\n");
							chat_enter.setEditable(false);
						}
						else if (command.equals("STP")) { // PHASE STATUS
							String turnplayer = tokenizer.nextToken("#");
							int turnphase = Short.parseShort(tokenizer.nextToken("#"));
							int turnno = Short.parseShort(tokenizer.nextToken("#"));
							
							turn(turnplayer,turnphase,turnno);
							
							
						}
						else if (command.equals("ATD")) { // Attack Data.
							for (int i=0;i<5;i++)attacklock[i] = 0;
							int attacker = Short.parseShort(tokenizer.nextToken());
							if (attacker != playernumber) { // this is your time to shine!
								for(int i = 0;i<5;i++) {
									attackdata[i] = Short.parseShort(tokenizer.nextToken("#"));
									if (attackdata[i] > 0 && attackdata[i] < 6) attacklock[attackdata[i]-1] = 1;
									
											

			
								}
							updateData();					
								
								
							}
							
							
						}						
						else if (command.equals("STN")) { //NAMES AND STATS
							String p1name = tokenizer.nextToken("#");
							int p1life = Short.parseShort(tokenizer.nextToken("#"));
							
							String p1dt = tokenizer.nextToken("#");
							int p1d = 0;
							int p1l = 0;
							int p1g = 0;
							if (p1dt.startsWith("00") || p1dt.startsWith("-00")) {
								showp1mana = false;
							
							}
							else {
								showp1mana = true;
							
							}
								p1d = Short.parseShort(p1dt);
								p1l = Short.parseShort(tokenizer.nextToken("#"));
								p1g = Short.parseShort(tokenizer.nextToken("#"));
							
						
						
							int p1hand = Short.parseShort(tokenizer.nextToken("#"));
							int p1grave = Short.parseShort(tokenizer.nextToken("#"));
							
							String p2name = tokenizer.nextToken("#");
							int p2life = Short.parseShort(tokenizer.nextToken("#"));
							
							String p2dt = tokenizer.nextToken("#");
							int p2d = 0;
							int p2l = 0;
							int p2g = 0;
							if (p2dt.startsWith("00") || p2dt.startsWith("-00")) {
								showp2mana = false;
							}
							else {
								showp2mana = true;
							
							}
							
								p2d = Short.parseShort(p2dt);
								p2l = Short.parseShort(tokenizer.nextToken("#"));
								p2g = Short.parseShort(tokenizer.nextToken("#"));
							
						//	int p2d = Short.parseShort(tokenizer.nextToken("#"));
							int p2hand = Short.parseShort(tokenizer.nextToken("#"));
							int p2grave = Short.parseShort(tokenizer.nextToken("#"));
							
							if (p1name.equals(playername)) {
								this.playernumber = 1;
								handcardsS = p1hand;
								handcardsO = p2hand;
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
								//h2 = Ohand;
								m2 = Omonster;
								e2 = Oeffect;
								sgravec = p1grave;
								ogravec = p2grave;
								
	
							}					
							else if (p2name.equals(playername)) {
								this.playernumber = 2;
								handcardsO = p1hand;
								handcardsS = p2hand;
								
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
								//h1 = Ohand;
								m1 = Omonster;
								e1 = Oeffect;
								sgravec = p2grave;
								ogravec = p1grave;
	
							}
							updateData();							
							
						}
						else if (command.equals("LOC")) {
							
								location.copydata(createLoadableCard(Short.parseShort(tokenizer.nextToken("#"))));
								updateData();
							
						}
						else if (command.equals("STC")) { // CARD STATUS
							updateCards(fromServer);
							lastcardstring = fromServer;
			
						}
						else if (command.equals("MOD")) {
							updateMods(fromServer);
							lastmodstring = fromServer;
							
						}
						else if (command.equals("HND")) {
							//	newMessage(fromServer);
							updateHand(fromServer);
							
							
							
						}
						else if (command.equals("GRA") && !observer) {
							updateGrave(fromServer);
							
							}				    		
			    	}
			    }
		
		
		
		
			
			    		
			    }catch (IOException e) {
			    	e.printStackTrace();
		            System.err.println("Couldn't get I/O for the connection");
		            		
	    		}	
    		} 
    		
			
   
   

	 
	
		}
		class CardmasterListSelectionListener implements ListSelectionListener{
			public void valueChanged(ListSelectionEvent e) {
				
        		CardmasterCardListData data = (CardmasterCardListData)list_carddata.getSelectedValue();
        		//System.out.println(data);
        		bigcard.copydata(carddata[data.id]);
        		setArtName();
				invalidate();
				validate();
			
				repaint();
			}
			
			
		}
	class CardmasterCardListData {
		public int id =0;
		String name = "not seen";
		CardmasterCardListData(int id){
			this.id = id;
	
		}
		public void setName(String name) {
			this.name = name;
		}
		public String toString() {
			if (id != 0)
			return id + ": " + name;
			else return "-- Seen Card List--";
		}
	}
	



	class ReadFromServerThread extends Thread {
		ReadFromServerBlockThread rfs;		
		Timer readtimer;
		public BufferedReader in;

/*1.4
		public Vector vec;
//1.4*/


///*1.5
		public Vector<String> vec;
////1.5*/	
	
	
		public ReadFromServerThread(BufferedReader in) {
/*1.4 
		 vec = new Vector();
//1.4*/
///*1.5
			vec = new Vector<String>();
////1.5*/
			this.in = in;
		}
		public void run() {
		rfs = new ReadFromServerBlockThread();
			rfs.start();
		//	readtimer = new Timer(100,new ReadFromServerThreadTimer());
		//	readtimer.start();	
			
		}
		
		
			public void stopTimer() {
			if (rfs != null) {
				if (rfs.started) {
					rfs.end = true;
				}
			}
		//	if (readtimer != null)
		//		if (readtimer.isRunning())
		//			readtimer.stop();
		}
		
		public boolean isNext() {
		//	if (!rfs.ready) return false;
			return (vec.size() > 0);
		}
		public String getNext() {
			if (vec.size() == 0) return null;
			String first = (String)vec.firstElement();
			return first;
		}
		public void remNext() {
			if (vec.size() == 0) return;
			vec.remove(vec.firstElement());
		}
		
		
		class ReadFromServerBlockThread extends Thread {
			boolean started = false;
			boolean end = false;
			boolean ready = true;
			public void run() {
				started = true;
				while (!end){
					
					try{
					
	
					//if (in.ready()) {
					//	if (vec.size()==0)
					//		ready = false;
			        //	while (!in.ready()) {
			        //		 try{Thread.currentThread().sleep(100);}catch(Exception e) {break;}
		
			        //	}
			        	String	fromSrv = in.readLine();
			        	
			        	vec.add(fromSrv);	
			        	
			        //	System.out.println(vec.size() +"-"+fromServer );	
			        //	ready = true;
			        		
			        //}
					}
					catch(Exception e) {
						e.printStackTrace();
					
								chat_log.append("ERROR: " + e + "\n- Refreshing is advised.");
								chat_log.setText(chat_log.getText());
						stopTimer();
					}
				
				
				}
				started = false;
				
				
				
			}
		}
		
		
		
		class ReadFromServerThreadTimer implements ActionListener {
			public void actionPerformed(ActionEvent evt)  {
				try{
			

				if (in.ready()) {
		        		fromServer = in.readLine();
		        	vec.add(fromServer);		
		        		
		        		
		        }
				}
				catch(Exception e) {
					e.printStackTrace();
				
							chat_log.append("ERROR: " + e + "\n- Refreshing is advised.");
							chat_log.setText(chat_log.getText());
					stopTimer();
				}
				
				
				
			}
		}
		
	} 

}
