import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class CardmasterCardMaker extends JApplet implements ActionListener{
	JPanel cardPanel;
	JPanel stuffPanel;
	JTextField outText;
	
	
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
	
	JTextField text_dDam;
	JTextField text_gDam;
	JTextField text_lDam;
	
	JCheckBox gsneak;
	JCheckBox lsneak;
	JCheckBox dsneak;
	
	JCheckBox spellimmune;
	JCheckBox abilityimmune;
	
	JCheckBox invisible;
	
	JTextField com;
	
	JRadioButton mon;
	JRadioButton eff;
	JRadioButton spe;

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
		System.out.println("Stuff");
		card.urlcodebase = getCodeBase().toString();
		smallcard.urlcodebase = getCodeBase().toString();
		
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
		
		try { card.Dcost = Integer.parseInt(cd.getText()); }
		catch (NumberFormatException e) { card.Dcost = 0;}
		try { card.Gcost = Integer.parseInt(cg.getText()); }
		catch (NumberFormatException e) { card.Gcost = 0;}
		try { card.Lcost = Integer.parseInt(cl.getText()); }
		catch (NumberFormatException e) { card.Lcost = 0;}
		try { card.Dsac = Integer.parseInt(sd.getText()); }
		catch (NumberFormatException e) { card.Dsac = 0;}
		try { card.Gsac = Integer.parseInt(sg.getText()); }
		catch (NumberFormatException e) { card.Gsac = 0;}
		try { card.Lsac = Integer.parseInt(sl.getText()); }
		catch (NumberFormatException e) { card.Lsac = 0;}

		try { card.expansioncode = Integer.parseInt(exp.getText()); }
		catch (NumberFormatException e) { card.expansioncode = 0;}

		try { card.attack = Integer.parseInt(atk.getText()); }
		catch (NumberFormatException e) { card.attack = 0;}
		
		try { card.lifepoints = Integer.parseInt(lif.getText()); }
		catch (NumberFormatException e) { card.lifepoints = 0;}	
		try { card.printed = Integer.parseInt(com.getText()); }
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
		int sneakCode =0;
		int invis = 0;
		
		if (gsneak.isSelected()) sneakCode+=8;
		if (dsneak.isSelected()) sneakCode+=2;
		if (lsneak.isSelected()) sneakCode+=4;
		
		if (invisible.isSelected()) invis = 1;
		
		try {
			dDam = Double.parseDouble(text_dDam.getText());
			gDam = Double.parseDouble(text_gDam.getText());
			lDam = Double.parseDouble(text_lDam.getText());
		} 	
		catch (NumberFormatException e) { }
		
		card.dummy = false;
		smallcard.copydata(card);
		String flagcode = "";
		String si = "0";
		String ai = "0";
		if (abilityimmune.isSelected()) ai = "1";
		if (spellimmune.isSelected()) si = "1";
		if (card.typecode.equals("m")) flagcode = "!#" + dDam + "#" + lDam + "#" + gDam + "#" + sneakCode + "#" + invis + "#!#" + ai + "#!#" + si + "#";
		outText.setText(card.toString() +  flagcode);
		repaint();
		
	}
	

	public void init() {
		//setSize(800,500);
		Container contentPane = getContentPane();
		cardPanel = new JPanel();
		stuffPanel = new JPanel();
		stuffPanel.setMinimumSize(new Dimension(500,400));
		outText = new JTextField();
		cardPanel.setLayout(new GridLayout(0,1));
		
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

		contentPane.add(outText,"South");
	//	outText.setEditable(false);
		contentPane.add(cardPanel,"West");
		contentPane.add(stuffPanel,"East");
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
		
		mon.setActionCommand("m");
		eff.setActionCommand("e");
		spe.setActionCommand("s");
		
		mon.setSelected(true);
		
		ButtonGroup group = new ButtonGroup();
		group.add(mon);
		group.add(eff);
		group.add(spe);
		
		cardTPanel.add(mon);
		cardTPanel.add(eff);
		cardTPanel.add(spe);
		
		c.gridx = 1;
		c.gridy = 3;
		layout.setConstraints(cardTPanel,c);
		stuffPanel.add(cardTPanel);


		label = new JLabel("Card Color:");
		c.gridx = 0;
		c.gridy = 4;
		layout.setConstraints(label,c);
		stuffPanel.add(label);
		
		
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
		exp = new JTextField("000");
		com = new JTextField("100");
		cardTPanel.add(new JLabel("attack:"));
		cardTPanel.add(atk);
		cardTPanel.add(new JLabel("life:"));
		cardTPanel.add(lif);
		cardTPanel.add(new JLabel("expansion:"));
		cardTPanel.add(exp);
		cardTPanel.add(new JLabel("commonness:"));
		cardTPanel.add(com);
		
		
		c.gridx = 1;
		c.gridy = 14;
		layout.setConstraints(cardTPanel,c);
		stuffPanel.add(cardTPanel);
	
	
		cardTPanel = new JPanel();
		text_dDam = new JTextField("1.0");
		text_lDam = new JTextField("1.0");
		text_gDam = new JTextField("1.0");
		
		cardTPanel.add(new JLabel("Damage from D:"));
		cardTPanel.add(text_dDam);
		cardTPanel.add(new JLabel("L:"));
		cardTPanel.add(text_lDam);
		cardTPanel.add(new JLabel("G:"));
		cardTPanel.add(text_gDam);
		
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
		
	
	
		updateButton = new JButton("Update Text String");
		c.gridx = 1;
		c.gridy = 17;
		layout.setConstraints(updateButton,c);
		stuffPanel.add(updateButton);
		updateButton.addActionListener(this);


		refreshStuff();
	
	
	
		
	}	
	public void actionPerformed(ActionEvent e) {
		refreshStuff();		
		
	}

	
	private class WinListener extends WindowAdapter {
		
		public void windowClosing(WindowEvent event) {
			System.exit(0);
		
			}	
		
	}
	
}
	