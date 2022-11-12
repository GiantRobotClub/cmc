import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.StringTokenizer;
import java.io.*;

class CardmasterCardEditorCard {
	
	public CardmasterCard card;
	public String datast;
	public String abilityst;
	public double dDam;
	public double lDam;
	public double gDam;
	public int sneak;
	
	
	public boolean invis;	
	public boolean available;
	public boolean abilityimmune = false;
	public boolean spellimmune = false;
	public int printed;
	public String attanim;
	
	public String modflag = "!";
	public String modtype = "!";
	
	public CardmasterCardEditorCard() {
		card = new CardmasterCard();	
		
		
	}
	
	public String toString() {
		if (card.dummy) return card.cardid	+ " - NO CARD";
		return card.cardid + " - " + card.name;
		
	}
	
	public String ability_intoplay = "NCODE;";
	public String ability_carddie = "NCODE;";
	public String ability_cleanup = "NCODE;";
	public String ability_draw = "NCODE;";
	public String ability_attack = "NCODE;";
	public String ability_afterattack = "NCODE;";
	public String ability_defend = "NCODE;";
	public String ability_command = "NCODE;";
	
	public String abilitystring() {
		return card.cardid + "#" + ability_intoplay + "#"	
		+ ability_carddie + "#" + ability_cleanup + "#"  
		+ ability_draw + "#" + ability_attack + "#" 
		+ ability_afterattack + "#" + ability_defend + "#" 
		+ ability_command + "#" ; 
		
	}
	
	public String dataString() {
		String ai = "0";
		String si = "0";
		String flagcode = "";
		String in = "0";
		if (abilityimmune) ai = "1";
		if (spellimmune) si = "1";
		if (invis) in = "1";
		if (card.typecode.equals("m")) flagcode = "!#" + dDam + "#" + lDam + "#" + gDam + "#" + sneak + "#" + in + "#!#" + ai + "#" + si + "#"  + attanim + "#";
		if (card.typecode.startsWith("d")) flagcode = modflag + "#" + modtype + "#";
		return card.toString() +  flagcode;
	
		
		
	}
	public void readAbilities(String datastring) {
		StringTokenizer tokenizer = new StringTokenizer(datastring, "#");
	 	int cardid = Short.parseShort(tokenizer.nextToken());
	 	if (card.cardid != cardid) return;
	 	ability_intoplay = tokenizer.nextToken();
	 	ability_carddie = tokenizer.nextToken();
	 	ability_cleanup = tokenizer.nextToken();
	 	ability_draw = tokenizer.nextToken();
		ability_attack = tokenizer.nextToken();
	 	ability_afterattack = tokenizer.nextToken();
	 	ability_defend = tokenizer.nextToken();
	 	ability_command = tokenizer.nextToken();
	 		
		
	}

	public void readData(String datastring) {
		datast = datastring;
		StringTokenizer tokenizer = new StringTokenizer(datastring, "#");
		card.cardid = Short.parseShort(tokenizer.nextToken());
		System.out.println("Loading card number " + card.cardid);
		card.name = tokenizer.nextToken();
		String next = tokenizer.nextToken();
		
		
		
		
		char testchar = next.toCharArray()[0];
		
		
		if (Character.isDigit(testchar)) {
			card.speed = Short.parseShort(next);
			
			next = tokenizer.nextToken();
			System.out.println("Speed read; " + card.speed);
			
		}
		testchar = next.toCharArray()[0];

		if (Character.isDigit(testchar)) {
			card.background = Short.parseShort(next);

			next = tokenizer.nextToken();
			System.out.println("Background read; " + card.background);
			
		}		
		
		card.typecode = next;
		
		
		
		
		card.dummy = false;
		card.Dcost = Short.parseShort(tokenizer.nextToken());
		card.Lcost = Short.parseShort(tokenizer.nextToken());
		card.Gcost = Short.parseShort(tokenizer.nextToken());
		card.colorcode = Short.parseShort(tokenizer.nextToken());
	
					
		card.picture = tokenizer.nextToken() + ".gif";
		next = tokenizer.nextToken();
		testchar = next.toCharArray()[0];	
		if (!Character.isDigit(testchar)) {
			card.flags = next;
			next = tokenizer.nextToken();
		}
		card.expansioncode = Short.parseShort(next);
		card.cardtext = tokenizer.nextToken();
		card.printed = Short.parseShort(tokenizer.nextToken());
		card.available = (Short.parseShort(tokenizer.nextToken()) == 1) ? true : false;
		if (! card.typecode.equals("l")) {
			card.targete = (Short.parseShort(tokenizer.nextToken()) == 1) ? true : false;
			card.targetp = (Short.parseShort(tokenizer.nextToken()) == 1) ? true : false;
			card.targets = (Short.parseShort(tokenizer.nextToken()) == 1) ? true : false;
			card.targetm = (Short.parseShort(tokenizer.nextToken()) == 1) ? true : false;
			card.targeta = (Short.parseShort(tokenizer.nextToken()) == 1) ? true : false;
			////System.out.println(cardid);
			card.targetg = (Short.parseShort(tokenizer.nextToken()) == 1) ? true : false; 
			card.targetmtype = tokenizer.nextToken();
			
			
			 if (card.typecode.equals("m") || card.typecode.equals("e") || card.typecode.startsWith("d")) {
				card.ability = (Short.parseShort(tokenizer.nextToken()) == 1) ? true : false;
				card.Dsac = Short.parseShort(tokenizer.nextToken());
				card.Lsac = Short.parseShort(tokenizer.nextToken());
				card.Gsac = Short.parseShort(tokenizer.nextToken());
				card.unique = (Short.parseShort(tokenizer.nextToken()) == 1) ? true : false;
				card.dizzy = (Short.parseShort(tokenizer.nextToken()) == 1) ? true : false;
				card.attack = Short.parseShort(tokenizer.nextToken());
				card.lifepoints = Short.parseShort(tokenizer.nextToken());
				
				if (card.typecode.equals("e") && tokenizer.hasMoreTokens() ) {
					card.mtype = tokenizer.nextToken();
					
				}
				else if (card.typecode.equals("e")) {
					card.mtype = "none";
				}
				if (card.typecode.startsWith("d")) {
					card.mtype = tokenizer.nextToken();
					modflag = tokenizer.nextToken();
					modtype = tokenizer.nextToken();
				
				}
				
				if (card.typecode.equals("m")) { 
				card.mtype = tokenizer.nextToken();
				
				
					if (tokenizer.hasMoreTokens()) { // flags
						tokenizer.nextToken(); // buffer space.  may use for a future thing.
						if (tokenizer.hasMoreTokens()) {
							dDam = Double.parseDouble(tokenizer.nextToken());
							lDam = Double.parseDouble(tokenizer.nextToken());
							gDam = Double.parseDouble(tokenizer.nextToken());
							sneak = Short.parseShort(tokenizer.nextToken());  // uses a color code
							invis = (Short.parseShort(tokenizer.nextToken()) == 1) ? true : false;
							
							
							if (tokenizer.hasMoreTokens()) {
								tokenizer.nextToken();  // second buffer
									abilityimmune = (Short.parseShort(tokenizer.nextToken()) == 1) ? true : false;
									spellimmune = (Short.parseShort(tokenizer.nextToken()) == 1) ? true : false;
								
								if (tokenizer.hasMoreTokens()) {
									attanim = tokenizer.nextToken();	
								
								
									//System.out.println(abilityimmune + " " + spellimmune);
								} else attanim = "default";
							}
						
						}else {
							gDam = 1;
							lDam = 1;
							dDam = 1;
							sneak = 0;
							invis = false;
							}
					} else {
						gDam = 1;
						lDam = 1;
						dDam = 1;
						sneak = 0;
						invis = false;
	
						
						
						
					}
				
				
				
				}	
				
				
			}
			
		}
		
		
		
				
		System.out.println("DATA READ FOR: "+ card.name);
		
	}
}

public class CardmasterCardEditor implements ActionListener  {
	int currentcard = 0;
	JPanel totalpane;
	JPanel mainpane;
	JPanel toppane;
	final int NUMCARDS = 1000;
	JPanel bottompane;
	JButton loadc;
	JButton savec;
		 JFileChooser fc;
	JList cardlist;
	public CardmasterCardEditorCard carddata[];
	CardmasterImageManager manager;
	JButton loada;
	JButton savea;
	public JFrame frame;
	//JFrame frame;
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == updateButton)
			refreshStuff();		
		else if (e.getSource() == loada) {
			
			int returnval = fc.showOpenDialog(mainpane);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				System.out.println("Opening : " + file.getName());
					
				
				loadAbilityData(file);
				
			}
			else { System.out.println("Error!");	}		
			
		}
		else if (e.getSource() == savea) {
			int returnval = fc.showSaveDialog(mainpane);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				System.out.println("Saving : " + file.getName());
					
				
				saveAbilityData(file);
				
			}
			else { System.out.println("Error!"); }			
			
			
		}
		else if (e.getSource() == loadc) {
			
			int returnval = fc.showOpenDialog(mainpane);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				System.out.println("Opening : " + file.getName());
					
				loadCardData(file);
				
				
			}
			else { System.out.println("Error!"); }
			
		}
		else if (e.getSource() == savec) {
			int returnval = fc.showSaveDialog(mainpane);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				System.out.println("Saving : " + file.getName()); 
					
				saveCardData(file);
				
				
			}
			else { System.out.println("Error!"); }			
			
		}
	}

	
	
	private class WinListener extends WindowAdapter {
		
		public void windowClosing(WindowEvent event) {
			System.exit(0);
		
			}	
		
	}	
	public static void main(String[] args) {
			
			JFrame frame = new JFrame("CMC Editor");
		
		
		CardmasterCardEditor app = new CardmasterCardEditor();
		app.manager = new CardmasterImageManager("file:");
		app.manager.LoadCommonCMCImages();
		JPanel contents = app.createComponents();
		app.frame = frame;
		frame.getContentPane().add(contents,BorderLayout.CENTER);
		
		
		frame.setSize(900,700);
	
		frame.pack();
		frame.setVisible(true);
		frame.pack();
		frame.setSize(1024,700);
		frame.repaint();
		
	}
	
		
		
	public void loadCardData(File file) { 
	int totalcards = 0;
	try{
		FileReader fr = new FileReader(file);
		BufferedReader in = new BufferedReader(fr);
		String inputline = "";
		while ((inputline = in.readLine()) != null) {
			
			StringTokenizer cardtok = new StringTokenizer(inputline,"#");
			int cardno = Short.parseShort(cardtok.nextToken());
			if (cardno <= 0) continue;
			System.out.println("Reading.. : " + cardno);
			carddata[cardno].readData(inputline);
			
			totalcards++;		
				
				
		}
		System.out.println("Total cards: "+ totalcards);
	//	refreshStuff();
	if (frame != null) frame.repaint();
		in.close();
		fr.close();
	}catch(Exception e){e.printStackTrace();}
	for (int i=0;i<NUMCARDS;i++) {
		if (! carddata[i].card.dummy) System.out.println(carddata[i].card.name); }	
		
		
		System.out.println("Total cards: "+ totalcards);
	}
	
	
	
	public void loadAbilityData(File file) {
		try{
			FileReader fr = new FileReader(file);
			BufferedReader in = new BufferedReader(fr);
			String inputline = "";
			while ((inputline = in.readLine()) != null) {
				StringTokenizer cardtok = new StringTokenizer(inputline,"#");
				int cardno = Short.parseShort(cardtok.nextToken());
				if (cardno <=0) continue;
				System.out.println("Reading.. " + cardno);
				carddata[cardno].readAbilities(inputline);
				
				
			}
			if (frame != null) frame.repaint();
		in.close();
		fr.close();
	}catch(Exception e){e.printStackTrace();}
	for (int i=0;i<NUMCARDS;i++) {
		if (! carddata[i].card.dummy) System.out.println(carddata[i].card.name); }	
		
		
	
	
	}
	
	
	public void saveCardData(File file) {
		try{
			FileWriter fw = new FileWriter(file);
			PrintWriter out = new PrintWriter(fw);
			for (int i=0;i<NUMCARDS;i++) {
				if (!carddata[i].card.dummy)	{
					out.println(carddata[i].dataString());
					System.out.println(carddata[i].dataString());
					
					
				}
				
				
				
			}
		
		
		fw.close();
		out.close();
		} catch(Exception e) {
			e.printStackTrace();}
	}
	
	public void saveAbilityData(File file) {
		try{
			FileWriter fw = new FileWriter(file);
			PrintWriter out = new PrintWriter(fw);
			for (int i=0;i<NUMCARDS;i++) {
				if (!carddata[i].card.dummy)	{
					out.println(carddata[i].abilitystring());
					
					
					
				}
				
				
				
			}
		
		
		fw.close();
		out.close();
		} catch(Exception e) {}		
		
		
	}	
		
	public void loadIntoEditor(CardmasterCardEditorCard card) {
	
		currentcard = card.card.cardid;
		if (card.card.dummy) return;
		textName.setText(card.card.name);
		mtype.setText(card.card.mtype);
		tmtype.setText(card.card.targetmtype);
		pic.setText(card.card.picture.substring(0,card.card.picture.length()-4));
	//	pic.setText(card.card.picture);//
		StringTokenizer cardtexttokenizer = new StringTokenizer(card.card.cardtext,"$");
		t1.setText(cardtexttokenizer.nextToken());
	if (cardtexttokenizer.hasMoreTokens())	t2.setText(cardtexttokenizer.nextToken());
		else t2.setText("");
	if (cardtexttokenizer.hasMoreTokens())	t3.setText(cardtexttokenizer.nextToken());
		else t3.setText("");
	if (cardtexttokenizer.hasMoreTokens())	t4.setText(cardtexttokenizer.nextToken());
		else t4.setText("");
	if (cardtexttokenizer.hasMoreTokens())	t5.setText(cardtexttokenizer.nextToken());
		else t5.setText("");

		if (card.card.typecode.equals("m")) mon.setSelected(true);
		else if (card.card.typecode.equals("e")) eff.setSelected(true);
		else if (card.card.typecode.equals("s")) spe.setSelected(true);
		else if (card.card.typecode.equals("l")) loc.setSelected(true);
		
		else if (card.card.typecode.equals("dm")) dmr.setSelected(true);
		else if (card.card.typecode.equals("de")) der.setSelected(true);
		else if (card.card.typecode.equals("db")) dbr.setSelected(true);
	
	
		textmodflag.setText(card.modflag);
		textmodtype.setText(card.modtype);
		
		abil.setSelected(card.card.ability);
		dizzy.setSelected(card.card.dizzy);
		avail.setSelected(card.card.available);
		unique.setSelected(card.card.unique);
		int colorcode = card.card.colorcode;
		if (colorcode == 2) { 
			d.setSelected(true);
			l.setSelected(false);
			g.setSelected(false);
		}
		else if (colorcode == 4) {
			d.setSelected(false);
			l.setSelected(true);
			g.setSelected(false);			
			
			
		}
		else if (colorcode == 8) {
			d.setSelected(false);
			l.setSelected(false);
			g.setSelected(true);			
			
			
		}
		else if (colorcode == 6) {
			d.setSelected(true);
			l.setSelected(true);
			g.setSelected(false);			
			
			
		}
		else if (colorcode == 10) {
			d.setSelected(true);
			l.setSelected(false);
			g.setSelected(true);			
			
			
		}
		else if (colorcode == 12) {
			d.setSelected(false);
			l.setSelected(true);
			g.setSelected(true);			
			
			
		}
		else if (colorcode == 14) {
			d.setSelected(true);
			l.setSelected(true);
			g.setSelected(true);			
			
			
		}
		cd.setText(card.card.Dcost + "");
		cg.setText(card.card.Gcost + "");
		cl.setText(card.card.Lcost + "");
		sd.setText(card.card.Dsac + "");
		sg.setText(card.card.Gsac + "");
		sl.setText(card.card.Lsac + "");
		
		exp.setText(card.card.expansioncode + "");
		atk.setText(card.card.attack + "");
		lif.setText(card.card.lifepoints + "");
		com.setText(card.card.printed + "");
		
		targeta.setSelected(card.card.targeta);
		targets.setSelected(card.card.targets);
		targete.setSelected(card.card.targete);
		targetp.setSelected(card.card.targetp);
		targetg.setSelected(card.card.targetg);
		targetm.setSelected(card.card.targetm);

		

		text_attack.setText(card.ability_attack);
		text_aattack.setText(card.ability_afterattack);
		text_carddie.setText(card.ability_carddie);
		text_command.setText(card.ability_command);
		text_draw.setText(card.ability_draw);
		text_cleanup.setText(card.ability_cleanup);
		text_defend.setText(card.ability_defend);
		text_intoplay.setText(card.ability_intoplay);

		
		
		
		
		text_dDam.setText(card.dDam + "");
		text_gDam.setText(card.gDam + "");
		text_lDam.setText(card.lDam + "");
		text_speed.setText(card.card.speed + "");
		text_flags.setText(card.card.flags + "");
		text_background.setText(card.card.background + "");
		
		invisible.setSelected(card.invis);
		
		int sneakcode = card.sneak;
		if (sneakcode == 0) {
			dsneak.setSelected(false);
			gsneak.setSelected(false);
			lsneak.setSelected(false);
			
		}
		else if (sneakcode == 2) {
			dsneak.setSelected(true);
			lsneak.setSelected(false);
			gsneak.setSelected(false);
				
			
		} else if (sneakcode == 4) {
			dsneak.setSelected(false);
			lsneak.setSelected(true);
			gsneak.setSelected(false);
				
			
		} else		if (sneakcode == 8) {
			dsneak.setSelected(false);
			lsneak.setSelected(false);
			gsneak.setSelected(true);
				
			
		} else		if (sneakcode == 6) {
			dsneak.setSelected(true);
			lsneak.setSelected(true);
			gsneak.setSelected(false);
				
			
		} else		if (sneakcode == 10) {
			dsneak.setSelected(true);
			lsneak.setSelected(false);
			gsneak.setSelected(true);
				
			
		} else		if (sneakcode == 12) {
			dsneak.setSelected(false);
			lsneak.setSelected(true);
			gsneak.setSelected(true);
				
			
		} else		if (sneakcode == 14) {
			dsneak.setSelected(true);
			lsneak.setSelected(true);
			gsneak.setSelected(true);
				
			
		} 
		spellimmune.setSelected(card.spellimmune);
		abilityimmune.setSelected(card.abilityimmune);
		apic.setText(card.attanim);		
		refreshStuff();
		//frame.pack();
	}
	public JPanel createComponents() {
		fc = new JFileChooser();
		carddata = new CardmasterCardEditorCard[NUMCARDS];
			for (int i=0;i<NUMCARDS;i++) {
			carddata[i] = new CardmasterCardEditorCard();	
			carddata[i].card.cardid = i;
		}
		totalpane = new JPanel();
		mainpane = new JPanel();
		toppane = new JPanel();
		bottompane = new JPanel();
		
		loadc = new JButton("Load Cards");
	
		loada = new JButton("Load Abilities");
	
		savec = new JButton("Save Cards");
	
		savea = new JButton("Save Abilities");
		loadc.addActionListener(this);
		loada.addActionListener(this);
		savea.addActionListener(this);
		savec.addActionListener(this);
		
		totalpane.setLayout(new BorderLayout());
		toppane.add(loadc,BorderLayout.CENTER);
		toppane.add(loada,BorderLayout.CENTER);
		toppane.add(savea,BorderLayout.CENTER);
		toppane.add(savec,BorderLayout.CENTER);
		totalpane.add(mainpane,BorderLayout.CENTER);	
		totalpane.add(toppane,BorderLayout.NORTH);
		
		totalpane.add(bottompane,BorderLayout.SOUTH);
	
		Container contentPane = mainpane;
		cardPanel = new JPanel();
		stuffPanel = new JPanel();
		stuffPanel.setMinimumSize(new Dimension(800,600));
		outText = new JTextField();
		cardPanel.setLayout(new GridLayout(0,2));
		
		JPanel blah = new JPanel();
		card = new CardmasterCard();
	    blah.add(card);
	    card.setBorder(BorderFactory.createLineBorder(Color.black));
		card.setPreferredSize(new Dimension(125,183));
		cardPanel.add(blah);
		
		blah = new JPanel();
		smallcard = new CardmasterCard();
	    blah.add(smallcard);
	    smallcard.setBorder(BorderFactory.createLineBorder(Color.black));
		smallcard.setPreferredSize(new Dimension(75,90));
		cardPanel.add(blah);
		

		
		cardlist = new JList(carddata);
		JScrollPane listscroller = new JScrollPane(cardlist);
		cardlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		cardlist.setLayoutOrientation(JList.VERTICAL);
		listscroller.setPreferredSize(new Dimension(130, 130));
		cardPanel.add(listscroller);

		blah = new JPanel();
		balancelabel = new JLabel("0");
		blah.add(balancelabel);
		cardPanel.add(blah);		
		
	ListSelectionModel listSelectionModel = cardlist.getSelectionModel();
    listSelectionModel.addListSelectionListener(
                            new SharedListSelectionHandler());		
		
	//	outText.setEditable(false);
		contentPane.add(cardPanel,BorderLayout.WEST);
		contentPane.add(stuffPanel,BorderLayout.EAST);
		
		GridBagLayout layout = new GridBagLayout();
			

		
		GridBagConstraints c = new GridBagConstraints();
	 	c.fill = GridBagConstraints.HORIZONTAL; 
	 	stuffPanel.setLayout(layout);
		JLabel label = new JLabel("Card Name");
		c.weightx = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		layout.setConstraints(label,c);
		stuffPanel.add(label);
		
		textName = new JTextField("Card Name",30);
		c.weightx = 0.9;
		c.gridx = 1;
		c.gridy = 0;
		layout.setConstraints(textName,c);
		stuffPanel.add(textName);
		
		label = new JLabel("Card Cost:");
		c.gridx = 0;
		c.gridy = 1;
		layout.setConstraints(label,c);
		stuffPanel.add(label);
		
		cd = new JTextField("0",4);
		cl = new JTextField("0",4);
		cg = new JTextField("0",4);
		sd = new JTextField("0",4);
		sl = new JTextField("0",4);
		sg = new JTextField("0",4);
		JPanel costPanel = new JPanel();
		JPanel sacPanel = new JPanel();
		costPanel.add(cd);
		costPanel.add(new JLabel("D"));
		costPanel.add(cl);
		
		costPanel.add(new JLabel("L"));
		costPanel.add(cg);
		
		costPanel.add(new JLabel("G"));
		sacPanel.add(sd);
		sacPanel.add(new JLabel("D"));
		sacPanel.add(sl);
		
		sacPanel.add(new JLabel("L"));
		sacPanel.add(sg);
		
		sacPanel.add(new JLabel("G"));
		
		c.gridx= 1;
		c.gridy =1;
		layout.setConstraints(costPanel,c);
		
		stuffPanel.add(costPanel);
		
		label = new JLabel("Card Sac:");
		c.gridx = 0;
		c.gridy = 2;
		layout.setConstraints(label,c);
		stuffPanel.add(label);
		
		
		c.gridx = 1;
		c.gridy = 2;
		layout.setConstraints(sacPanel,c);
		stuffPanel.add(sacPanel);


		label = new JLabel("Card Type:");
		c.gridx = 0;
		c.gridy = 3;
		layout.setConstraints(label,c);
		stuffPanel.add(label);


		JPanel cardTPanel = new JPanel();
		
		mon = new JRadioButton("Monster");
		eff = new JRadioButton("Effect");
		spe = new JRadioButton("Spell");
		loc = new JRadioButton("Location");
		dmr = new JRadioButton("M mod");
		der = new JRadioButton("E mod");
		dbr = new JRadioButton("ME mod");
		
		mon.setActionCommand("m");
		eff.setActionCommand("e");
		spe.setActionCommand("s");
		loc.setActionCommand("l");
		
		dmr.setActionCommand("dm");
		der.setActionCommand("de");
		dbr.setActionCommand("db");
		
		
		
		mon.setSelected(true);
		
		ButtonGroup group = new ButtonGroup();
		group.add(mon);
		group.add(eff);
		group.add(spe);
		group.add(loc);
		group.add(dmr);
		group.add(der);
		group.add(dbr);
		
		cardTPanel.add(mon);
		cardTPanel.add(eff);
		cardTPanel.add(spe);
		cardTPanel.add(loc);
		cardTPanel.add(dmr);
		cardTPanel.add(der);
		cardTPanel.add(dbr);
		
		c.gridx = 1;
		c.gridy = 3;
		layout.setConstraints(cardTPanel,c);
		stuffPanel.add(cardTPanel);


		label = new JLabel("Card Color:");
		c.gridx = 0;
		c.gridy = 4;
		layout.setConstraints(label,c);
		stuffPanel.add(label);
		bottompane.setLayout(new BoxLayout(bottompane,BoxLayout.PAGE_AXIS));
		bottompane.add(outText);
		
		JPanel abilitygrid = new JPanel();
		bottompane.add(abilitygrid);
		
		abilitygrid.setLayout(new GridLayout(5,1));
		
		label =new JLabel ("Into Play:");
		text_intoplay = new JTextField("NCODE;");
		abilitygrid.add(label);
		abilitygrid.add(text_intoplay);
	
		label =new JLabel ("Draw Phase:");
		text_draw = new JTextField("NCODE;");
		abilitygrid.add(label);
		abilitygrid.add(text_draw);

		label =new JLabel ("Cleanup:");
		text_cleanup = new JTextField("NCODE;");
		abilitygrid.add(label);
		abilitygrid.add(text_cleanup);

		label =new JLabel ("Attack:");
		text_attack = new JTextField("NCODE;");
		abilitygrid.add(label);
		abilitygrid.add(text_attack);

		label =new JLabel ("Defend:");
		text_defend = new JTextField("NCODE;");
		abilitygrid.add(label);
		abilitygrid.add(text_defend);

		label =new JLabel ("Post Attack:");
		text_aattack = new JTextField("NCODE;");
		abilitygrid.add(label);
		abilitygrid.add(text_aattack);

		label =new JLabel ("Command:");
		text_command = new JTextField("NCODE;");
		abilitygrid.add(label);
		abilitygrid.add(text_command);

		label =new JLabel ("Destroy:");
		text_carddie = new JTextField("NCODE;");
		abilitygrid.add(label);
		abilitygrid.add(text_carddie);				
					
		
		
		label = new JLabel("Mod Flags:");
		textmodflag = new JTextField("!");
		abilitygrid.add(label);
		abilitygrid.add(textmodflag);
		
		label = new JLabel("Mod Types:");
		textmodtype = new JTextField("!");
		abilitygrid.add(label);
		abilitygrid.add(textmodtype);		
		
			
		cardTPanel = new JPanel();
		l = new JCheckBox("Light");
		g = new JCheckBox("Grey");
		d = new JCheckBox("Dark");
		
		cardTPanel.add(d);
		cardTPanel.add(l);
		cardTPanel.add(g);
		
		c.gridx = 1;
		c.gridy = 4;
		layout.setConstraints(cardTPanel,c);
		stuffPanel.add(cardTPanel);	


		label = new JLabel("Monster Type:");
		c.gridx = 0;
		c.gridy = 5;
		layout.setConstraints(label,c);
		stuffPanel.add(label);

		mtype = new JTextField("mtype",30);

		c.gridx = 1;
		c.gridy = 5;
		layout.setConstraints(mtype,c);
		stuffPanel.add(mtype);

		label = new JLabel("Targets:");
		c.gridx = 0;
		c.gridy = 6;
		layout.setConstraints(label,c);
		stuffPanel.add(label);







		cardTPanel = new JPanel();
		
		targetg = new JCheckBox("Grave");
		targeta = new JCheckBox("Auto");
		targetm = new JCheckBox("Monster");
		targets = new JCheckBox("Slot");
		targete = new JCheckBox("Effect");
		targetp = new JCheckBox("Player");
		tmtype = new JTextField("monster type");
		
		
		cardTPanel.add(targeta);
		cardTPanel.add(targetm);
		cardTPanel.add(targete);
		cardTPanel.add(targets);
		cardTPanel.add(targetg);
		cardTPanel.add(targets);
		cardTPanel.add(targetp);
		cardTPanel.add(new JLabel("Target type:"));
		cardTPanel.add(tmtype);
		
		c.gridx = 1;
		c.gridy = 6;
		layout.setConstraints(cardTPanel,c);
		stuffPanel.add(cardTPanel);


		label = new JLabel("Flags:");
		c.gridx = 0;
		c.gridy = 7;
		layout.setConstraints(label,c);
		stuffPanel.add(label);


		cardTPanel = new JPanel();
		
		abil = new JCheckBox("Ability");
		avail = new JCheckBox("Available");
		unique = new JCheckBox("Unique");
		dizzy = new JCheckBox("Dizzy");
		invisible = new JCheckBox("Invisible");
		
		
		cardTPanel.add(abil);
		cardTPanel.add(avail);
		cardTPanel.add(unique);
		cardTPanel.add(dizzy);
		cardTPanel.add(invisible);
		
		c.gridx = 1;
		c.gridy = 7;
		layout.setConstraints(cardTPanel,c);
		stuffPanel.add(cardTPanel);


		label = new JLabel("Card Text:");
		c.gridx = 0;
		c.gridy = 8;
		layout.setConstraints(label,c);
		stuffPanel.add(label);		

		
		t1= new JTextField("text",30);
		JTextField temp = t1;
		c.gridx = 1;
		c.gridy = 8;
		layout.setConstraints(temp,c);
		stuffPanel.add(temp);

		
		t2 = new JTextField("text",30);
temp = t2;
		c.gridx = 1;
		c.gridy = 9;
		layout.setConstraints(temp,c);
		stuffPanel.add(temp);

		
		t3 = new JTextField("text",30);
temp = t3;
		c.gridx = 1;
		c.gridy = 10;
		layout.setConstraints(temp,c);
		stuffPanel.add(temp);
		
		
		t4 = new JTextField("text",30);
temp = t4;
		c.gridx = 1;
		c.gridy = 11;
		layout.setConstraints(temp,c);
		stuffPanel.add(temp);
		
		
		t5= new JTextField("text",30);
temp = t5;
		c.gridx = 1;
		c.gridy = 12;
		layout.setConstraints(temp,c);
		stuffPanel.add(temp);
		

		label = new JLabel("Picture:");
		c.gridx = 0;
		c.gridy = 13;
		layout.setConstraints(label,c);
		stuffPanel.add(label);

		
		pic = new JTextField("image",30);
		temp = pic;
		c.gridx = 1;
		c.gridy = 13;
		layout.setConstraints(temp,c);
		stuffPanel.add(temp);
		

	
		label = new JLabel("Other Data:");
		c.gridx = 0;
		c.gridy = 14;
		layout.setConstraints(label,c);
		stuffPanel.add(label);
		
		cardTPanel = new JPanel();
		lif = new JTextField("999");
		atk = new JTextField("999");
		exp = new JTextField("00000000");
		com = new JTextField("100");
		cardTPanel.add(new JLabel("attack:"));
		cardTPanel.add(atk);
		cardTPanel.add(new JLabel("life:"));
		cardTPanel.add(lif);
		cardTPanel.add(new JLabel("expansion:"));
		cardTPanel.add(exp);
		cardTPanel.add(new JLabel("commonness:"));
		cardTPanel.add(com);
		text_flags = new JTextField(20);
		cardTPanel.add(new JLabel("Othflag:"));
		cardTPanel.add(text_flags);
		
		c.gridx = 1;
		c.gridy = 14;
		layout.setConstraints(cardTPanel,c);
		stuffPanel.add(cardTPanel);
	
	
		cardTPanel = new JPanel();
		text_dDam = new JTextField("1.0");
		text_lDam = new JTextField("1.0");
		text_gDam = new JTextField("1.0");
		text_speed = new JTextField("0");
	
		text_background = new JTextField("0");
		
		
		cardTPanel.add(new JLabel("Damage from D:"));
		cardTPanel.add(text_dDam);
		cardTPanel.add(new JLabel("L:"));
		cardTPanel.add(text_lDam);
		cardTPanel.add(new JLabel("G:"));
		cardTPanel.add(text_gDam);
		cardTPanel.add(new JLabel("Spd:"));
		cardTPanel.add(text_speed);
		
		
		cardTPanel.add(new JLabel("Bkg:"));
		cardTPanel.add(text_background);
		
		
		label = new JLabel("Att anim:");
		cardTPanel.add(label);
		
		apic = new JTextField("default",10);
		cardTPanel.add(apic);
		
		
		c.gridx = 1;
		c.gridy = 15;
		layout.setConstraints(cardTPanel,c);
		stuffPanel.add(cardTPanel);		
	
	
	
		cardTPanel = new JPanel();
		gsneak = new JCheckBox("Sneak G");
		dsneak = new JCheckBox("Sneak D");
		lsneak = new JCheckBox("Sneak L");
		spellimmune = new JCheckBox("Spell Immune");
		abilityimmune = new JCheckBox("Ability Immune");
		
		cardTPanel.add(dsneak);
		cardTPanel.add(lsneak);
		cardTPanel.add(gsneak);
		cardTPanel.add(spellimmune);
		cardTPanel.add(abilityimmune);
		c.gridx = 1;
		c.gridy = 16;
		layout.setConstraints(cardTPanel,c);
		stuffPanel.add(cardTPanel);		
		
	
	
		updateButton = new JButton("Update Array");
		c.gridx = 1;
		c.gridy = 17;
		layout.setConstraints(updateButton,c);
		stuffPanel.add(updateButton);
		updateButton.addActionListener(this);
		




		refreshStuff();


		
		return totalpane;
	
			
	}
	
	JPanel cardPanel;
	JLabel balancelabel;
	JPanel stuffPanel;
	JTextField outText;
	
	class SharedListSelectionHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
		
		
			if (e.getValueIsAdjusting()== false) {
				
				int cardid = cardlist.getSelectedIndex();
				card.cardid = cardid;
				smallcard.cardid = cardid;
				currentcard = cardid;
				System.out.println("Switching to " + cardid);
				
				loadIntoEditor(carddata[cardid]);
				
				
				
				
				}	
		}
		
		
	}
		
		
/*	int cd = 0;
	int cl = 0;
	int cg = 0;
	int sd = 0;
	int sl = 0;
	int sg = 0;
	int atk = 0;
	int lif = 0;
	int exp = 0;
*/
	
	JTextField textName;
	JTextField textmodflag;
	JTextField textmodtype;
	JTextField cd;
	JTextField cl;
	JTextField cg;
	JTextField sd;
	JTextField sl;
	JTextField sg;
	JTextField atk;
	JTextField lif;
	JTextField exp;
	JTextField mtype;
	JTextField t1;
	JTextField t2;
	JTextField t3;
	JTextField t4;
	JTextField t5;
	JTextField tmtype;
	JTextField pic;
	JTextField apic;
	
	JTextField text_dDam;
	JTextField text_gDam;
	JTextField text_lDam;
	JTextField text_speed;
	JTextField text_flags;
	JTextField text_background;
	
	JTextField text_intoplay; // ability text boxes
	JTextField text_carddie;
	JTextField text_attack;
	JTextField text_aattack;
	JTextField text_defend;
	JTextField text_command;
	JTextField text_draw;
	JTextField text_cleanup;
	
	JCheckBox gsneak;
	JCheckBox lsneak;
	JCheckBox dsneak;
	
	JCheckBox spellimmune;
	JCheckBox abilityimmune;
	
	JCheckBox invisible;
	
	JTextField com;
	
	JRadioButton loc;
	JRadioButton mon;
	JRadioButton eff;
	JRadioButton spe;
	JRadioButton dmr;
	JRadioButton der;
	JRadioButton dbr;

	JCheckBox g;
	JCheckBox l;
	JCheckBox d;
	
	
	JCheckBox targeta;
	JCheckBox targete;
	JCheckBox targetm;
	JCheckBox targetg;
	JCheckBox targets;
	JCheckBox targetp;
	JCheckBox abil;
	
	JButton updateButton;
	JCheckBox avail;
	JCheckBox unique;
	JCheckBox dizzy;

	CardmasterCard card;
	CardmasterCard smallcard;	

	public void refreshStuff() {
//		System.out.println("Stuff");
//		card.urlcodebase = "file:d:\\cmc\\site\\";
//		smallcard.urlcodebase = "file:d:\\cmc\\site\\";
		card.urlcodebase = "file:";
		smallcard.urlcodebase = "file:";
		card.SetImageManager(manager);
		smallcard.SetImageManager(manager);
		
		
		
		int cardno = currentcard;
		card.cardid = cardno;
		card.drawlarge = true;
		card.name = textName.getText();
		card.mtype = mtype.getText();
		card.targetmtype = tmtype.getText();
		card.cardtext = t1.getText() + "$" 	+ t2.getText() + "$" + t3.getText() + 
		"$" + t4.getText() + "$"+ t5.getText() + "$";
		card.picture = pic.getText() +  ".gif";
		String cardType = "m";
		if (mon.isSelected()) { cardType = "m"; }
		if (eff.isSelected()) { cardType = "e"; }
		if (spe.isSelected()) { cardType = "s"; }
		if (loc.isSelected()) { cardType = "l"; }
		
		if (dmr.isSelected()) { cardType = "dm"; }
		if (dbr.isSelected()) { cardType = "db"; }
		if (der.isSelected()) { cardType = "de"; }
		
		
		card.typecode = cardType;
		card.ability =abil.isSelected();
		card.dizzy = dizzy.isSelected();
		card.available = avail.isSelected();
		card.unique = unique.isSelected();
		
		int colorcode =0;
		if (g.isSelected()) colorcode+=8;
		if (d.isSelected()) colorcode+=2;
		if (l.isSelected()) colorcode+=4;
		card.colorcode = colorcode;
		
		try { card.Dcost = Short.parseShort(cd.getText()); }
		catch (NumberFormatException e) { card.Dcost = 0;}
		try { card.Gcost = Short.parseShort(cg.getText()); }
		catch (NumberFormatException e) { card.Gcost = 0;}
		try { card.Lcost = Short.parseShort(cl.getText()); }
		catch (NumberFormatException e) { card.Lcost = 0;}
		try { card.Dsac = Short.parseShort(sd.getText()); }
		catch (NumberFormatException e) { card.Dsac = 0;}
		try { card.Gsac = Short.parseShort(sg.getText()); }
		catch (NumberFormatException e) { card.Gsac = 0;}
		try { card.Lsac = Short.parseShort(sl.getText()); }
		catch (NumberFormatException e) { card.Lsac = 0;}

		try { card.expansioncode = Short.parseShort(exp.getText()); }
		catch (NumberFormatException e) { card.expansioncode = 0;}

		try { card.attack = Short.parseShort(atk.getText()); }
		catch (NumberFormatException e) { card.attack = 0;}
		
		try { card.lifepoints = Short.parseShort(lif.getText()); }
		catch (NumberFormatException e) { card.lifepoints = 0;}	
		try { card.printed = Short.parseShort(com.getText()); }
		catch (NumberFormatException e) { card.printed = 0;}	
		
		card.targeta = targeta.isSelected();
		card.targets = targets.isSelected();
		card.targete = targete.isSelected();
		card.targetp = targetp.isSelected();
		card.targetg = targetg.isSelected();
		card.targetm = targetm.isSelected();
		
		
		double dDam = 0;
		double lDam = 0;
		double gDam = 0;
		int speed = 0;
		int background = 0;
		
		int sneakCode =0;
		int invis = 0;
		
		String animimage = apic.getText();
		if (gsneak.isSelected()) sneakCode+=8;
		if (dsneak.isSelected()) sneakCode+=2;
		if (lsneak.isSelected()) sneakCode+=4;
		
		if (invisible.isSelected()) invis = 1;
		card.flags = text_flags.getText();
		try {
			dDam = Double.parseDouble(text_dDam.getText());
			gDam = Double.parseDouble(text_gDam.getText());
			lDam = Double.parseDouble(text_lDam.getText());
			card.speed = Short.parseShort(text_speed.getText());
			
			card.background = Short.parseShort(text_background.getText());
		} 	
		catch (NumberFormatException e) { }
		
		card.dummy = false;
		smallcard.copydata(card);
		String flagcode = "";
		String si = "0";
		String ai = "0";
		String modflag = textmodflag.getText();
		String modtype = textmodtype.getText();
		if (abilityimmune.isSelected()) ai = "1";
		if (spellimmune.isSelected()) si = "1";
		if (card.typecode.startsWith("d")) flagcode = modflag + "#" + modtype + "#";
		if (card.typecode.equals("m")) flagcode = "!#" + dDam + "#" + lDam + "#" + gDam + "#" + sneakCode + "#" + invis + "#!#" + ai + "#" + si + "#" + animimage + "#";
		outText.setText(card.toString() +  flagcode);
		if (frame != null) frame.repaint();
		carddata[currentcard].attanim = animimage;
		carddata[currentcard].spellimmune = spellimmune.isSelected();
		carddata[currentcard].abilityimmune = abilityimmune.isSelected();
		
		carddata[currentcard].readData(outText.getText());
		carddata[currentcard].card.dummy = false;
		carddata[currentcard].attanim = animimage;
		carddata[currentcard].ability_attack = text_attack.getText();
		carddata[currentcard].ability_afterattack = text_aattack.getText();
	 	carddata[currentcard].ability_carddie = text_carddie.getText();
		carddata[currentcard].ability_command = text_command.getText();
		carddata[currentcard].ability_draw= text_draw.getText();
		carddata[currentcard].ability_cleanup= text_cleanup.getText();
		carddata[currentcard].ability_defend= text_defend.getText();
		carddata[currentcard].ability_intoplay= text_intoplay.getText();
		
			
		balancelabel.setText("" + checkBalance(card));

		
	}
	public int checkBalance(CardmasterCard card) {
		int balance = 0;
		if (!card.typecode.equals("m")) return 0;
		int attack = card.attack;
		int life = card.lifepoints;
		int modifier = 0;
		//if (attack > life) modifier = (attack-life) /2;
		//if (life > attack) modifier = (life - attack ) /4; 
		if (card.unique) modifier+=15;
		int stats = attack + life + modifier;
		
	//	if (card.attack > (card.lifepoints + card.lifepoints/2)) stats = stats + (card.attack /2);
	//	if (card.lifepoints > (card.attack + card.attack/2)) stats = stats + (card.lifepoints /2);
		
		int cost = card.Gcost + card.Dcost + card.Lcost;
		int shouldbe = 0;
		for (int i=1;i<=cost;i++) {
			if (i <= 4) shouldbe+=10;
			if (i <= 5) shouldbe+=5;
			if (i <= 9) shouldbe+=5;
			if (i >=11) shouldbe+=5;
			shouldbe+=2;
			
		}
		return shouldbe-stats;
		
		
		
		
	}		
		
}