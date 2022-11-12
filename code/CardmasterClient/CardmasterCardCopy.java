import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.util.StringTokenizer;
import java.text.AttributedString;
import java.text.AttributedCharacterIterator;
import java.awt.geom.*;
import java.awt.font.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
public class CardmasterCardCopy extends JPanel {
	public boolean avatardraw;
	public String avatarname;
	public int attack;
	public int lifepoints; // used as two extra slots for Effect cards.
	public int Dcost;
	public int Lcost;
	public int Gcost;
	public boolean extendedart = false;
	public int Dsac;
	public int quantity = 0;
	public int Lsac;
	public String flags = "!";
	public int speed =0;
	public int Gsac;
//	String artist = "Mark Shallow";
	public boolean dataBlank = false;
	public boolean askfordata = false;
	public boolean token = false;
	public int damagetaken;
	public boolean showdamage = false;
	public boolean drawlarge = false;
	public int colorcode; // 2D, 4L, 8G add for code
	public int cardid;	
	public int expansioncode; 
	public boolean ability; // has an ability
	public boolean targete; // ability targets Effects
	public boolean targetp; // ability targets Players
	public boolean targetm; // ability targets monsters
	public boolean targets; // Ability targets slots
	public boolean targeta; // Ability automatically targets (no target specification required)
	public boolean targetg; // target grave
	public boolean unique; // can play only one
	public boolean dizzy; // is dizzy
	public boolean blkDizzy;
	public String mtype; // monster type code.
	public String name;
	public boolean facedown = false;
	public String cardtext;
	public String picture;
	public String urlcode2;
	public String typecode; // e, m, or s.
	public String targetmtype; // ability or spell target monster type if applicable.
	public boolean dummy;
 	public Image image;
 	public int background;
 	public String urlcodebase;
	public boolean drawdizzy;
	public String statusicons = "none";
	public boolean animation = false;
	public String animationfile;
	
	
	public BufferedImage cardimage = null;
	
	
	public String currentcardimage = "";
	
	public CardmasterImageManager manager;
	
	
	public void SetImageManager(CardmasterImageManager manager) {
		this.manager = manager;
	}
	
	
	Image animimage;
	// Constructor for MONSTERS
	CardmasterCardCopy(String name, String cardtext, String picture, String typecode, String targetmtype,
		 boolean targete, boolean targetp, boolean targetm, boolean targets, boolean targeta,
		 int cardid, int expansioncode, int colorcode, int Dcost, int Lcost, int Gcost,
		 int Dsac, int Lsac, int Gsac, boolean unique, boolean ability, int attack, int lifepoints, String mtype){	

	//	System.out.println(image);
		typecode = "m";
		this.name = name;
		this.cardtext = cardtext;
		this.picture = picture;
		this.typecode = typecode;
		this.cardtext = cardtext;
		this.targetmtype = targetmtype;
		this.targete = targete;
		this.targetp = targetp;
		this.targetm = targetm;
		this.targets = targets;
		this.targeta = targeta;
		this.cardid = cardid;
		this.expansioncode = expansioncode;
		this.colorcode = colorcode;
		this.Dcost = Dcost;
		this.Lcost = Lcost;
		this.Gcost = Gcost;
		this.Dsac = Dsac;
		this.Lsac = Lsac;
		this.Gsac = Gsac;
		this.unique = unique;
		this.ability = ability;
		this.attack = attack;
		this.lifepoints = lifepoints;
		this.mtype = mtype;
		this.targetg = false;
		this.blkDizzy = false;
	//	System.out.println(this.image);

		drawdizzy = true;
	}
	// Constructor for EFFECTS
	CardmasterCardCopy(String name, String cardtext, String picture, String typecode,String targetmtype,
		 boolean targete, boolean targetp, boolean targetm, boolean targets, boolean targeta,
		 int cardid, int expansioncode, int colorcode, int Dcost, int Lcost, int Gcost,
		 int Dsac, int Lsac, int Gsac, boolean unique, boolean ability, int attack, int lifepoints){	
	
	
			
		typecode = "e";
		this.name = name;
		this.cardtext = cardtext;
		this.picture = picture;
	//	System.out.println(image);
	//	System.out.println(this.image);
		this.typecode = typecode;
		this.cardtext = cardtext;
		this.targetmtype = targetmtype;
		this.targete = targete;
		this.targetp = targetp;
		this.targetm = targetm;
		this.targets = targets;
		this.targeta = targeta;
		this.cardid = cardid;
		this.expansioncode = expansioncode;
		this.colorcode = colorcode;
		this.Dcost = Dcost;
		this.Lcost = Lcost;
		this.Gcost = Gcost;
		this.Dsac = Dsac;
		this.Lsac = Lsac;
		this.Gsac = Gsac;
		this.unique = unique;
		this.ability = ability;
		this.attack = attack;
		this.lifepoints = lifepoints;	this.targetg = false;
		
		this.blkDizzy = false;
	drawdizzy = true;
	
	}
	// Constructor for MAGIC
	void setDizzy(boolean dizzy) {
		this.dizzy = dizzy;
	}
	CardmasterCardCopy(String name, String cardtext, String picture, String typecode, String targetmtype,
		 boolean targete, boolean targetp, boolean targetm, boolean targets, boolean targeta,
		 int cardid, int expansioncode, int colorcode, int Dcost, int Lcost, int Gcost){	
	
	
			
		typecode = "s";
		this.name = name;
		this.cardtext = cardtext;
		this.picture = picture;
		this.typecode = typecode;
		this.cardtext = cardtext;
		this.targetmtype = targetmtype;
		this.targete = targete;
		this.targetp = targetp;
		this.targetm = targetm;
		this.targets = targets;
		this.targeta = targeta;
		this.cardid = cardid;
		this.expansioncode = expansioncode;
		this.colorcode = colorcode;
		this.Dcost = Dcost;
		this.Lcost = Lcost;
		this.Gcost = Gcost;
		this.Dsac = Dsac;
		this.Lsac = Lsac;
		this.Gsac = Gsac;
		
		this.blkDizzy = false;
		drawdizzy = true;	this.targetg = false;
}
	CardmasterCardCopy(String name, String cardtext, String picture, String typecode,
		 int cardid, int expansioncode, int colorcode, int Dcost, int Lcost, int Gcost){	
	
	
			
		typecode = "l";
		this.name = name;
		this.cardtext = cardtext;
		this.picture = picture;
		this.typecode = typecode;
		this.cardtext = cardtext;
	
		this.cardid = cardid;
		this.expansioncode = expansioncode;
		this.colorcode = colorcode;
		this.Dcost = Dcost;
		this.Lcost = Lcost;
		this.Gcost = Gcost;

		this.blkDizzy = false;
		drawdizzy = true;	this.targetg = false;
}
	CardmasterCardCopy() {
		
		this.blkDizzy = false;
		dummy = true;
		drawdizzy = true;
	}
	void animate(String file) {
		try{
		if (animimage != null) animimage.flush();
		animationfile = file;
		animation = true;
		animimage = manager.getImage(( "animations/" + animationfile + ".gif"));
		
		//System.out.println("Animating " + file);
		} catch (Exception e) {animation = false; 
		//System.out.println("Animation error");
		}
	}
	void copydata(CardmasterCardCopy card) {
	//	System.out.println("copy..");
			//	System.out.println(card.image);
				this.name = card.name;
				this.cardtext = card.cardtext;
				this.picture = card.picture;
				//System.out.println(this.image);
				this.typecode = card.typecode;
				this.cardtext = card.cardtext;
				this.targetmtype = card.targetmtype;
				this.targete = card.targete;
				this.targetp = card.targetp;
				this.targetm = card.targetm;
				this.targets = card.targets;
				this.targeta = card.targeta;
				this.speed = card.speed;
				this.background = card.background;
				this.cardid = card.cardid;
				this.expansioncode = card.expansioncode;
				this.colorcode = card.colorcode;
				this.Dcost = card.Dcost;
				this.Lcost = card.Lcost;
				this.Gcost = card.Gcost;
				this.Dsac = card.Dsac;
				this.Lsac = card.Lsac;
				this.facedown = card.facedown;
				this.Gsac = card.Gsac;
				this.dataBlank = card.dataBlank;
				this.dummy = card.dummy;
				this.dizzy = card.dizzy;
				this.targetg = card.targetg;
				this.Dsac = card.Dsac;
				this.Lsac = card.Lsac;
				this.Gsac = card.Gsac;
				this.unique = card.unique;
				this.ability = card.ability;
				this.attack = card.attack;
				this.lifepoints = card.lifepoints;
				this.mtype = card.mtype;
				this.token = card.token;
				this.urlcodebase = urlcodebase;
				this.avatardraw = false;
				this.statusicons = card.statusicons;
		this.blkDizzy = card.blkDizzy;
			//	this.artist = card.artist;
		//		if (this.targetm) System.out.println("This card " + name + " targets monsters? ");
				
		
	}

	public void paintComponent(Graphics g) {
		if (picture == null) extendedart = false;
		else {
		
		if (picture.startsWith("extended_")) extendedart = true;
		else extendedart = false;
		}
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		
		
		if (avatardraw) {
		
						try {
			//String newname = java.net.URLEncoder.encode(avatarname,"UTF-8");
					String newname = avatarname;
			
					if (avatarname.indexOf(" ")!= -1) {
						newname = avatarname.replaceAll(" ", "%20");
						newname = avatarname.replaceAll("\\s", "%20");
					}
				//	g2.drawImage(manager.getImage((urlcode2  + "avatars/"+newname + ".png")),5,0,114,182,this);
					g2.drawImage(manager.getImage("avatars/"+newname + ".png"),5,0,114,182,this);
				
				
					}catch (Exception e) {
			//	e.printStackTrace();
		
		
		
					}
			
			return;
		}
		
		
		if (dataBlank) { 
		
		//	System.out.println("Printing Blank Data");
			g2.setColor(Color.white);
			g2.fillRect(0,0,getWidth(),getHeight());
			g2.setColor(Color.white);
			int fontsize1 = (int)(getHeight()/(60.0/8.0));
			Font f = new Font("SansSerif",Font.PLAIN,fontsize1);

			g2.setFont(f);
			g2.drawString("LOADING",5,30);
			
		
		
			
		}
		if (animation && !drawlarge && dummy) {
				
					try{	
					g2.drawImage(animimage, 0,0,getWidth(),getHeight(),this);
					
					}catch (Exception e) {
					e.printStackTrace();
			}
					
					
		
		
		}
	if (facedown) System.out.println("Face down!");
	
		if (!dummy && !(typecode == null) && !facedown) {
			
	//	if (dizzy) System.out.println("Is dizzy");
	//	if (drawdizzy) System.out.println("Will draw dizzy if applicable");	
			
	//System.out.println(urlcodebase);
	Image expimg;
	try{
		//System.out.println("PIC INFO : " + currentcardimage + picture);
		if (image == null || !currentcardimage.equals(picture)) {
			
		
			image = toolkit.getImage(new URL(urlcodebase  + "cardpics/"+ picture));	
			currentcardimage = picture;
	
		}	
	
	
	}catch (Exception e) {
		e.printStackTrace();
		
		
		
	}
	

	Color back;
	Color text;
	if (colorcode == 4) {
		back = Color.white;
		text = Color.black;
	} else if (colorcode == 2) {
		back = Color.black;
		text = new Color(255,204,99);
	} else if (colorcode == 8){
		back = Color.lightGray;
		text = new Color(20,20,20);
	} else if (colorcode == 6){
		back = Color.black;
		text = Color.red;
		
	} else if (colorcode == 10) {
		back = new Color(5,5,5);
		text = new Color(200,200,200);	
		
		
	} else if (colorcode == 12) {
		back = new Color(128,128,128);
		text = Color.black;	
		
		
	} else if (colorcode == 14) {
		back = new Color(140,98,57);
		text = Color.black;	
		
		
	}
	
	
	else {
		back = Color.yellow;
		text = Color.black;
	
	}
	String backset = "";
	if (background != 0) backset = background + "_";
	
	
			String imagesize;
			if (drawlarge) imagesize = "l_";
			else imagesize = "s_";
			g2.setColor(back);g2.fillRect(0,0,getWidth(),getHeight());
			try {
				
				
			if (!dummy) { 
			g2.drawImage(manager.getImage(( "images/"+ "cardback_" + backset + imagesize + colorcode + ".jpg")),0,0,getWidth(),getHeight(),this);
					
			}
			
			
			
			}catch (Exception e) {
		e.printStackTrace(); }
		
		
		
		
			
//			System.out.println("Draw Large: " + drawlarge);
			if (drawlarge) { // Big Version // assumes 100x140
				int fontsize1 = (int)(getHeight()/(140.0/10.0));
				int fontsize2 = (int)(getHeight()/(140.0/9.0));
				
				int fontsize3 = (int)(getHeight()/(140.0/15.0));
				
				int diconfont = (int)(getHeight()/(140.0/18.0));
				int vspace = 1;
				int hspace = 1;
				double imageratio = (double)96/(double)40;
				g2.setColor(text);
				Font f = new Font("SansSerif",Font.PLAIN,fontsize1);
				Font f2 = new Font("SansSerif",Font.PLAIN,fontsize2); 
				Font f3 = new Font("Serif",Font.PLAIN,fontsize3);
				g2.setFont(f);
				
				String manastring = ((Dcost > 0) ? ("D"+ Dcost) : "") + ((Lcost > 0) ? ("L"+ Lcost) : "") + ((Gcost > 0) ? ("G"+ Gcost) : "") + " ";				
				if ((Dcost + Lcost + Gcost) <= 0) manastring = "Zero";
				AttributedString ats = new AttributedString(manastring);
				
				try{
				ImageGraphicAttribute dmanai = new ImageGraphicAttribute(manager.getImage(( "images/"+ "dmana.gif")),(int)TOP_ALIGNMENT,0,10);
				ImageGraphicAttribute lmanai = new ImageGraphicAttribute(manager.getImage(( "images/"+ "lmana.gif")),(int)TOP_ALIGNMENT,0,10);
				ImageGraphicAttribute gmanai = new ImageGraphicAttribute(manager.getImage(( "images/"+ "gmana.gif")),(int)TOP_ALIGNMENT,0,10);
				
				if (manastring.indexOf("D") != -1) ats.addAttribute(TextAttribute.CHAR_REPLACEMENT, dmanai, manastring.indexOf('D'), manastring.indexOf('D')+1);
				if (manastring.indexOf("L") != -1)ats.addAttribute(TextAttribute.CHAR_REPLACEMENT, lmanai, manastring.indexOf('L'), manastring.indexOf('L')+1);
				if (manastring.indexOf("G") != -1)ats.addAttribute(TextAttribute.CHAR_REPLACEMENT, gmanai, manastring.indexOf('G'), manastring.indexOf('G')+1);
				ats.addAttribute(TextAttribute.FONT,f);
				AttributedCharacterIterator iter = ats.getIterator();
				FontRenderContext frc = g2.getFontRenderContext();
				TextLayout t1 = new TextLayout(iter, frc);
				t1.draw(g2,hspace,fontsize1);

				if(!(typecode.equals("s")) && !(typecode.equals("l"))) {
					String sacstring = ((Dsac > 0) ? ("D"+ Dsac) : "") + ((Lsac > 0) ? ("L"+ Lsac) : "") + ((Gsac > 0) ? ("G"+ Gsac) : "") + " ";				
					if ((Gsac + Lsac + Dsac) <=0 ) sacstring = "Zero";							
					AttributedString sacs = new AttributedString(sacstring);
					if (sacstring.indexOf("D") != -1)  sacs.addAttribute(TextAttribute.CHAR_REPLACEMENT, dmanai, sacstring.indexOf('D'), sacstring.indexOf('D')+1);
					if (sacstring.indexOf("L") != -1)  sacs.addAttribute(TextAttribute.CHAR_REPLACEMENT, lmanai, sacstring.indexOf('L'), sacstring.indexOf('L')+1);
					if (sacstring.indexOf("G") != -1)  sacs.addAttribute(TextAttribute.CHAR_REPLACEMENT, gmanai, sacstring.indexOf('G'), sacstring.indexOf('G')+1);
					sacs.addAttribute(TextAttribute.FONT,f);
					AttributedCharacterIterator siter = sacs.getIterator();
					TextLayout t2 = new TextLayout(siter, frc);
					
					t2.draw(g2,hspace,fontsize1*2+vspace);
					
					
				}
							
				}catch (Exception e) {
					g2.drawString(Dcost + "D" + Lcost + "L" + Gcost + "G",hspace,fontsize1);
				if(!(typecode.equals("s")) && !(typecode.equals("l")))	g2.drawString(Dsac + "D" + Lsac + "L" + Gsac + "G",hspace,fontsize1*2);
				
					
				}	
				
				
		//		g2.drawString("cost: " + Dcost + "D " + Lcost + "L " + Gcost + "G",hspace,fontsize1);
					g2.drawString(name,hspace,vspace + fontsize1*3);
				
				
				if (!extendedart){
				
					if (image != null) { g2.drawImage(image,2,vspace+ fontsize1*3 + vspace+1,getWidth()-4,(int)((double)(getWidth()-4) / imageratio),this);}
					g2.drawRect(1,vspace + fontsize1*3 + vspace,getWidth()-3,(int)((double)(getWidth()-3) / imageratio));
				
				}
				if (extendedart){
				
					if (image != null) { g2.drawImage(image,2,vspace+ fontsize1*3 + vspace+1,getWidth()-4,(int)((double)(getWidth()-4) /(imageratio / 2.0)),this);}
					g2.drawRect(1,vspace + fontsize1*3 + vspace,getWidth()-3,(int)((double)(getWidth()-3) / (imageratio / 2.0)));
				
				}
						
				
				
				
				
				
				if (!statusicons.equals("none")) {
					StringTokenizer tokenizer = new StringTokenizer(statusicons,",");
					int start = 1;					
					while (tokenizer.hasMoreTokens()) {
						String token = tokenizer.nextToken();
						try{
						
						g2.drawImage(manager.getImage(( "images/"+ "specialicon_"+token+".gif")),start,vspace+ fontsize1*3 + vspace+1,10,10,this);
						
						start+=11;
						}catch(Exception e){}
					}
					
					
				}
				
				
				
			
					
				g2.setFont(f2);
			if (!extendedart) {
				
				if (typecode.equals("s")) { g2.drawString("Cast Spell",hspace,vspace + fontsize1*3 + vspace+(int)((getWidth()-3) / imageratio) + fontsize2); }
				if (typecode.equals("e")) { 
					String mtypeline = "Effect";
					if (mtype != null) {
						if (!mtype.equals("none")) {
							mtypeline = mtype;
						}
					}
					
				g2.drawString("Create " + (unique ? "unique " : "" )+ mtypeline,hspace,vspace + fontsize1*3 + vspace+(int)((double)(getWidth()-3) / imageratio) + fontsize2); }
				if (typecode.equals("m")) { g2.drawString("Call " + (unique ? "unique " : "" )+ mtype,hspace,vspace + fontsize1*3 + vspace+(int)((double)(getWidth()-3) / imageratio) + fontsize2); }
				if (typecode.equals("l")) { g2.drawString("Move to Location",hspace,vspace + fontsize1*3 + vspace+(int)((double)(getWidth()-3) / imageratio) + fontsize2); }
			
			}
			
			else if (extendedart) {
				if (typecode.equals("s")) { g2.drawString("Cast Spell",hspace,vspace + fontsize1*3 -1+ vspace+(int)((getWidth()-3) / (imageratio/2.0)) + fontsize2); }
				if (typecode.equals("e")) { 
					String mtypeline = "Effect";
					if (mtype != null) {
						if (!mtype.equals("none")) {
							mtypeline = mtype;
						}
					}
				g2.drawString("Create " + (unique ? "unique " : "" )+ mtypeline,hspace,vspace + fontsize1*3 -1+ vspace+(int)((double)(getWidth()-3) / (imageratio/2.0)) + fontsize2); }
				if (typecode.equals("m")) { g2.drawString("Call " + (unique ? "unique " : "" )+ mtype,hspace,vspace + fontsize1*3 -1+ vspace+(int)((double)(getWidth()-3) / (imageratio/2.0)) + fontsize2); }
				if (typecode.equals("l")) { g2.drawString("Move to Location",hspace,vspace + fontsize1*3 -1+ vspace+(int)((double)(getWidth()-3) / (imageratio/2.0)) + fontsize2); }
			
			}
			
			
			if (!extendedart) {
				
			
			
				g2.drawRect(1,vspace + fontsize1*3 + vspace+vspace+(int)((double)(getWidth()-3) / imageratio) + fontsize2+vspace,getWidth()-3,fontsize2*5+ 1 + vspace * 2);
				g2.setColor(Color.white);
				g2.fillRect(2,vspace + fontsize1*3 + vspace+vspace+(int)((double)(getWidth()-3) / imageratio) + fontsize2+1+vspace,getWidth()-5,fontsize2*5+vspace*2);
				g2.setColor(Color.black);
				
			}
			else {
				g2.drawRect(1,vspace + fontsize1*3 -2+ vspace+vspace+(int)((double)(getWidth()-3) / (imageratio/2.0)) + fontsize2+vspace,getWidth()-3,fontsize2*1+ 1 + vspace * 2);
				g2.setColor(Color.white);
				g2.fillRect(2,vspace + fontsize1*3 -2+ vspace+vspace+(int)((double)(getWidth()-3) / (imageratio/2.0)) + fontsize2+1+vspace,getWidth()-5,fontsize2*1+vspace*2);
				g2.setColor(Color.black);	
				
				
			}
				String line1 = " ";
				String line2 = " ";
				String line3 = " ";
				String line4 = " "; 
				String line5 = " ";
				StringTokenizer tokenizer = new StringTokenizer(cardtext,"$");
				if (tokenizer.hasMoreTokens()) {
					line1 = tokenizer.nextToken() + " ";
					if (tokenizer.hasMoreTokens())
					line2 = tokenizer.nextToken() + " ";
					
					if (tokenizer.hasMoreTokens())
					line3 = tokenizer.nextToken() + " ";
					if (tokenizer.hasMoreTokens())
					line4 = tokenizer.nextToken() + " ";
					if (tokenizer.hasMoreTokens())
					line5 = tokenizer.nextToken() + " ";										
					
				}
				else line1 = cardtext;
				
	
				AttributedString at1 = new AttributedString(line1);
				AttributedString at2 = new AttributedString(line2);
				AttributedString at3 = new AttributedString(line3);
				AttributedString at4 = new AttributedString(line4);
				AttributedString at5 = new AttributedString(line5);
				try{
				
				ImageGraphicAttribute dmanai = new ImageGraphicAttribute(manager.getImage(( "images/"+ "dmana.gif")),(int)TOP_ALIGNMENT,0,10);
				ImageGraphicAttribute lmanai = new ImageGraphicAttribute(manager.getImage(( "images/"+ "lmana.gif")),(int)TOP_ALIGNMENT,0,10);
				ImageGraphicAttribute gmanai = new ImageGraphicAttribute(manager.getImage(( "images/"+ "gmana.gif")),(int)TOP_ALIGNMENT,0,10);
				ImageGraphicAttribute aarrow = new ImageGraphicAttribute(manager.getImage(( "images/"+ "abilitypointer.gif")),(int)TOP_ALIGNMENT,0,10);
				ImageGraphicAttribute adizzy = new ImageGraphicAttribute(manager.getImage(( "images/"+ "abilitydizzy.gif")),(int)TOP_ALIGNMENT,0,10);
				ImageGraphicAttribute blanker = new ImageGraphicAttribute(manager.getImage(( "images/"+ "blanker.gif")),(int)TOP_ALIGNMENT,2,10);
				
			//	System.out.println("Line 1 test: " + line1.indexOf("(D)") + line1.indexOf("(G)") + line1.indexOf("(L)") + line1.indexOf("(>)") + line1.indexOf("(Z)"));
				
				for (int z = 0;z<5;z++) {
					int foundindex = 0;
					AttributedString temp = at1;
					String line = line1;
					if (z == 0) { temp = at1; line = line1; }
					if (z == 1) { temp = at2; line = line2; }
					if (z == 2) { temp = at3; line = line3; }
					if (z == 3) { temp = at4; line = line4; }
					if (z == 4) { temp = at5; line = line5; }
					
					while (line.indexOf("(D)",foundindex) != -1)  {
						int index = line.indexOf("(D)",foundindex);
						temp.addAttribute(TextAttribute.CHAR_REPLACEMENT, blanker, index, index+3);
						temp.addAttribute(TextAttribute.CHAR_REPLACEMENT, dmanai, index+1, index+2);
						foundindex = index+1;
					}
					foundindex = 0;
					while (line.indexOf("(L)",foundindex) != -1)  {
						int index = line.indexOf("(L)",foundindex);
						temp.addAttribute(TextAttribute.CHAR_REPLACEMENT, blanker, index, index+3);
						temp.addAttribute(TextAttribute.CHAR_REPLACEMENT, lmanai, index+1, index+2);
						foundindex = index+1;
					}
					foundindex = 0;
				
					while (line.indexOf("(G)",foundindex) != -1)  {
						int index = line.indexOf("(G)",foundindex);
						temp.addAttribute(TextAttribute.CHAR_REPLACEMENT, blanker, index, index+3);
						temp.addAttribute(TextAttribute.CHAR_REPLACEMENT, gmanai, index+1, index+2);
						foundindex = index+1;
					}
					foundindex = 0;

					while (line.indexOf("(>)",foundindex) != -1)  {
						int index = line.indexOf("(>)",foundindex);
						temp.addAttribute(TextAttribute.CHAR_REPLACEMENT, blanker, index, index+3);
						temp.addAttribute(TextAttribute.CHAR_REPLACEMENT, aarrow, index+1, index+2);
						foundindex = index+1;
					}
					foundindex = 0;
					
					while (line.indexOf("(Z)",foundindex) != -1)  {
						int index = line.indexOf("(Z)",foundindex);
						temp.addAttribute(TextAttribute.CHAR_REPLACEMENT, blanker, index, index+3);
						temp.addAttribute(TextAttribute.CHAR_REPLACEMENT, adizzy, index+1, index+2);
						foundindex = index+1;
					}
					foundindex = 0;

				
					}
				at1.addAttribute(TextAttribute.FONT,f2);
				at2.addAttribute(TextAttribute.FONT,f2);
				at3.addAttribute(TextAttribute.FONT,f2);
				at4.addAttribute(TextAttribute.FONT,f2);
				at5.addAttribute(TextAttribute.FONT,f2);
				
				AttributedCharacterIterator iter1 = at1.getIterator();
				AttributedCharacterIterator iter2 = at2.getIterator();
				AttributedCharacterIterator iter3 = at3.getIterator();
				AttributedCharacterIterator iter4 = at4.getIterator();
				AttributedCharacterIterator iter5 = at5.getIterator();
				FontRenderContext frc = g2.getFontRenderContext();
				TextLayout layout1 = new TextLayout(iter1, frc);				
				TextLayout layout2 = new TextLayout(iter2, frc);				
				TextLayout layout3 = new TextLayout(iter3, frc);				
				TextLayout layout4 = new TextLayout(iter4, frc);				
				TextLayout layout5 = new TextLayout(iter5, frc);				
				
				if(!extendedart) {
				
				
				layout1.draw(g2,3,fontsize1*3 + vspace+vspace+(int)((getWidth()-3) / imageratio) + fontsize2+1+vspace + 1*fontsize2);				
				layout2.draw(g2,3,fontsize1*3 + vspace+vspace+(int)((getWidth()-3) / imageratio) + fontsize2+1+vspace + 2*fontsize2);				
				
				layout3.draw(g2,3,fontsize1*3 + vspace+vspace+(int)((getWidth()-3) / imageratio) + fontsize2+1+vspace + 3*fontsize2);				
				layout4.draw(g2,3,fontsize1*3 + vspace+vspace+(int)((getWidth()-3) / imageratio) + fontsize2+1+vspace + 4*fontsize2);				
				layout5.draw(g2,3,fontsize1*3 + vspace+vspace+(int)((getWidth()-3) / imageratio) + fontsize2+1+vspace + 5*fontsize2);				
				}
				else {
					layout1.draw(g2,3,fontsize1*3 -2+ vspace+vspace+(int)((getWidth()-3) / (imageratio/2.0)) + fontsize2+1+vspace + 1*fontsize2);				
				
				}			
				/*
			*/			
				}catch (Exception e) {
						g2.drawString(line1,3, fontsize1*3 + vspace+vspace+(int)((getWidth()-3) / imageratio) + fontsize2+1+vspace + fontsize2);
				g2.drawString(line2,3, fontsize1*3 + vspace+vspace+(int)((getWidth()-3) / imageratio) + fontsize2+1+vspace + 2*fontsize2);
				g2.drawString(line3,3, fontsize1*3 + vspace+vspace+(int)((getWidth()-3) / imageratio) + fontsize2+1+vspace + 3*fontsize2);
				g2.drawString(line4,3, fontsize1*3 + vspace+vspace+(int)((getWidth()-3) / imageratio) + fontsize2+1+vspace + 4*fontsize2);
				g2.drawString(line5,3, fontsize1*3 + vspace+vspace+(int)((getWidth()-3) / imageratio) + fontsize2+1+vspace + 5*fontsize2);
				e.printStackTrace();
					
				}
				
				try{
				g2.drawImage(manager.getImage(( "images/"+ "exp_" + expansioncode + ".gif")),getWidth()-fontsize1-fontsize1/2-1,getHeight()-fontsize1-fontsize1/2-1,fontsize1+fontsize1/2,fontsize1+fontsize1/2,this);
				}catch (Exception e) {
				e.printStackTrace();	}
				if (typecode.equals("s") || ability) {
				
					try{
					g2.drawImage(manager.getImage(( "images/"+ "speed_" + speed + ".gif")),getWidth()-(5*(fontsize1-fontsize1/2))-1,getHeight()-fontsize1-fontsize1/2-1,fontsize1+fontsize1/2,fontsize1+fontsize1/2,this);
					}catch (Exception e) {
					e.printStackTrace();	}										
					
				}				
				g2.setFont(f3);g2.setColor(text);
				if (typecode.equals("m")) g2.drawString(attack + "/" + lifepoints,2,getHeight()-2);
				if (typecode.equals("e") && attack >= 0) g2.drawString("" + attack,2,getHeight()-2);
				
				if (typecode.equals("e") && lifepoints >= 0 && attack >= 0) g2.drawString("" + lifepoints,getWidth()/2-15,getHeight()-2);
				if (typecode.equals("e") && lifepoints >= 0 && attack <= 0) g2.drawString("" + lifepoints,2,getHeight()-2);
				
				if (dizzy && drawdizzy) {
					try{
					
					g2.drawImage(manager.getImage(( "images/"+ "dizzy.gif")),2,2,getWidth()-4,(int)((double)(getWidth()-4) / imageratio),this);
					} catch (Exception e) {};
					
					 /*
					g2.setColor(Color.blue);
					Font dizf = new Font("Serif",Font.PLAIN,diconfont);
					g2.setFont(dizf);
					g2.drawString("D",getWidth()-diconfont,diconfont);
					*/
					
					
				}
				
				
				if (blkDizzy && drawdizzy) {
					try{
					
					g2.drawImage(manager.getImage(( "images/"+ "blockdizzy.gif")),2,2,getWidth()-4,(int)((double)(getWidth()-4) / imageratio),this);
					} catch (Exception e) {};
					
					 /*
					g2.setColor(Color.blue);
					Font dizf = new Font("Serif",Font.PLAIN,diconfont);
					g2.setFont(dizf);
					g2.drawString("D",getWidth()-diconfont,diconfont);
					*/
					
					
				}
			
				
				
			
									
			}else { // small version // assumes 70/60

				g2.setColor(text);
				
				int fontsize1 = (int)(getHeight()/(60.0/8.0));
				int fontsize3 = (int)(getHeight()/(60.0/12.0));
				int diconfont = (int)(getHeight()/(60.0/18.0));
				
				if (extendedart) {
					fontsize1 = (int)(getHeight()/(60.0/7.0));
					fontsize3 = (int)(getHeight()/(60.0/11.0));
					diconfont= (int)(getHeight()/(60.0/16.0));
					
				}
				
				int vspace = 1;
				int hspace = 1;
				if (extendedart) vspace = 0;
				 
				double imageratio = 96.0/40.0;
				Font f = new Font("SansSerif",Font.PLAIN,fontsize1);
				Font f3 = new Font("Serif",Font.PLAIN,fontsize3);
				
				g2.setFont(f);
				
				String manastring = ((Dcost > 0) ? ("D"+ Dcost) : "") + ((Lcost > 0) ? ("L"+ Lcost) : "") + ((Gcost > 0) ? ("G"+ Gcost) : "") + " ";				
				if ((Dcost + Lcost + Gcost) <= 0) manastring = "Zero";
				AttributedString ats = new AttributedString(manastring);
				
				try{
				ImageGraphicAttribute dmanai = new ImageGraphicAttribute(manager.getImage(( "images/"+ "dmana.gif")),(int)TOP_ALIGNMENT,0,10);
				ImageGraphicAttribute lmanai = new ImageGraphicAttribute(manager.getImage(( "images/"+ "lmana.gif")),(int)TOP_ALIGNMENT,0,10);
				ImageGraphicAttribute gmanai = new ImageGraphicAttribute(manager.getImage(( "images/"+ "gmana.gif")),(int)TOP_ALIGNMENT,0,10);
				
				if (manastring.indexOf("D") != -1) ats.addAttribute(TextAttribute.CHAR_REPLACEMENT, dmanai, manastring.indexOf('D'), manastring.indexOf('D')+1);
				if (manastring.indexOf("L") != -1)ats.addAttribute(TextAttribute.CHAR_REPLACEMENT, lmanai, manastring.indexOf('L'), manastring.indexOf('L')+1);
				if (manastring.indexOf("G") != -1)ats.addAttribute(TextAttribute.CHAR_REPLACEMENT, gmanai, manastring.indexOf('G'), manastring.indexOf('G')+1);
				ats.addAttribute(TextAttribute.FONT,f);
				AttributedCharacterIterator iter = ats.getIterator();
				FontRenderContext frc = g2.getFontRenderContext();
				TextLayout t1 = new TextLayout(iter, frc);
				t1.draw(g2,hspace,fontsize1);

				if(!(typecode.equals("s")) && !(typecode.equals("l"))) {
					String sacstring = ((Dsac > 0) ? ("D"+ Dsac) : "") + ((Lsac > 0) ? ("L"+ Lsac) : "") + ((Gsac > 0) ? ("G"+ Gsac) : "") + " ";				
					if ((Gsac + Lsac + Dsac) <=0 ) sacstring = "Zero";							
					AttributedString sacs = new AttributedString(sacstring);
					if (sacstring.indexOf("D") != -1)  sacs.addAttribute(TextAttribute.CHAR_REPLACEMENT, dmanai, sacstring.indexOf('D'), sacstring.indexOf('D')+1);
					if (sacstring.indexOf("L") != -1)  sacs.addAttribute(TextAttribute.CHAR_REPLACEMENT, lmanai, sacstring.indexOf('L'), sacstring.indexOf('L')+1);
					if (sacstring.indexOf("G") != -1)  sacs.addAttribute(TextAttribute.CHAR_REPLACEMENT, gmanai, sacstring.indexOf('G'), sacstring.indexOf('G')+1);
					sacs.addAttribute(TextAttribute.FONT,f);
					AttributedCharacterIterator siter = sacs.getIterator();
					TextLayout t2 = new TextLayout(siter, frc);
					
					t2.draw(g2,hspace,fontsize1*2+vspace);
					
					
				}
							
				}catch (Exception e) {
					e.printStackTrace();
					g2.drawString(Dcost + "D" + Lcost + "L" + Gcost + "G",hspace,fontsize1);
				if(!(typecode.equals("s")) && !(typecode.equals("l")))	g2.drawString(Dsac + "D" + Lsac + "L" + Gsac + "G",hspace,fontsize1*2);
				
					
				}	
				
				
				if (extendedart)
				g2.drawString(name,hspace,fontsize1*3-4);
				else
				g2.drawString(name,hspace,fontsize1*3);
				if (!extendedart) {
				
				if (image != null) { 
					g2.drawImage(image,2,vspace + fontsize1*3 + vspace+1,getWidth()-4,(int)((getWidth()-4) / imageratio),this);
					
				
				}
				g2.drawRect(1,vspace + fontsize1*3 + vspace,getWidth()-3,(int)((getWidth()-3) / imageratio));
				
				
				}
				
				else {
					
				if (image != null) { 
					g2.drawImage(image,2,vspace -1+ fontsize1*3 + vspace,getWidth()-4,(int)((getWidth()-4) / (imageratio/2.0)),this);
					
				
				}
				g2.drawRect(1,vspace + fontsize1*3 + vspace-2,getWidth()-3,(int)((getWidth()-3) / (imageratio/2.0)));
				
					
				}




				//System.out.println(name + " stautsicons: " + statusicons);
				if (!statusicons.equals("none")) {
					StringTokenizer tokenizer = new StringTokenizer(statusicons,",");
					int start = 1;					
					while (tokenizer.hasMoreTokens()) {
						String token = tokenizer.nextToken();
					//	System.out.println(token);
						try{
						
						g2.drawImage(manager.getImage(( "images/"+ "specialicon_"+token+".gif")),start,vspace + fontsize1*3 + vspace+1,10,10,this);
						
						start+=11;
						}catch(Exception e){}
					}
					
					
				}
				
				
	
			//	g2.drawRect(1,29,57,26);
					g2.setFont(f3);
				if (typecode.equals("m"))  g2.drawString(attack + "/" + lifepoints,2,getHeight()-3);
				if (typecode.equals("e") && attack >= 0)  g2.drawString("" + attack,2,getHeight()-3);					
				if (typecode.equals("e") && lifepoints >= 0 && attack >=0)  g2.drawString("" + lifepoints,getWidth()/2-7,getHeight()-3);					
				if (typecode.equals("e") && lifepoints >= 0 && attack < 0)  g2.drawString("" + lifepoints,2,getHeight()-3);					
			
				try{
			
				g2.drawImage(manager.getImage(( "images/"+"exp_" + expansioncode + ".gif")),getWidth()-fontsize1-fontsize1/2-1,getHeight()-fontsize1-fontsize1/2-1,fontsize1+fontsize1/2,fontsize1+fontsize1/2,this);
				}catch (Exception e) {e.printStackTrace();}
				
				if (typecode.equals("s") || ability) {
					try{
				
					g2.drawImage(manager.getImage(( "images/"+"speed_" + speed + ".gif")),getWidth()-5*(fontsize1-fontsize1/2)-1,getHeight()-fontsize1-fontsize1/2-1,fontsize1+fontsize1/2,fontsize1+fontsize1/2,this);
					}catch (Exception e) {e.printStackTrace();}
				}
				
				if (dizzy && drawdizzy) {
					
				//	Font dizf = new Font("Serif",Font.PLAIN,diconfont);
			//		g2.setColor(Color.blue);
			//		g2.setFont(dizf);
			//		g2.drawString("D",getWidth()-diconfont,diconfont);
				try{g2.drawImage(manager.getImage(( "images/"+ "dizzy.gif")),2,2,getWidth()-4,(int)((getWidth()-4) / imageratio),this);
					}catch(Exception e){
					}
						
					
					
				}
				if (blkDizzy && drawdizzy) {
					
				//	Font dizf = new Font("Serif",Font.PLAIN,diconfont);
			//		g2.setColor(Color.blue);
			//		g2.setFont(dizf);
			//		g2.drawString("D",getWidth()-diconfont,diconfont);
				try{g2.drawImage(manager.getImage(( "images/"+ "blockdizzy.gif")),2,2,getWidth()-4,(int)((getWidth()-4) / imageratio),this);
					}catch(Exception e){
					}
						
					
					
				}

				if (animation) {
				
					try{	
					g2.drawImage(animimage, 0,0,getWidth(),getHeight(),this);
					
					}catch (Exception e) {
					e.printStackTrace();
					
					
		
		
					}
		
					
					
				}
				
				if (showdamage && damagetaken > 0) {
					Font dizf = new Font("SansSerif",Font.PLAIN,(int)(getHeight()/(60.0/30.0)));
					g2.setColor(Color.red);
					g2.setFont(dizf);
					g2.drawString(damagetaken + "",0,55);					
					
					
				}
				
				
			}
		
		}
		else if (facedown) {
			if (drawlarge) {
				try{
				g2.drawImage(manager.getImage(( "images/"+ "cardback.gif")),0,0,getWidth(),getHeight(),this);
				}catch (Exception e) {e.printStackTrace();
				System.out.println( "images/"+ "cardback.gif");
				
				}
			}
			else {
				try{
				g2.drawImage(manager.getImage(( "images/"+ "cardback_s.gif")),0,0,getWidth(),getHeight(),this);
				}catch (Exception e) {e.printStackTrace();
				System.out.println( "images/"+ "cardback_s.gif");
				
				}	
			}
		}
		else { // draw the card back
			if (drawlarge) {
				try{
				g2.drawImage(manager.getImage(( "images/"+ "cardback.gif")),0,0,getWidth(),getHeight(),this);
				}catch (Exception e) {e.printStackTrace();
				System.out.println( "images/"+ "cardback.gif");
				
				}
				
				
			}
			
			
			
		}

	}	

		public String toString() {
		if (dummy) return "b";
		if (typecode == null)  {
			dummy = true;
			return "b";
		}
		
		String returnstring = cardid + "#"  + name  + "#" +  speed + "#" + background + "#" +typecode +"#" + Dcost+ "#" + Lcost+ "#" + Gcost+
		 "#" + colorcode+ "#"  + picture.substring(0,picture.length()-4) + "#" + flags + "#" + expansioncode + "#" + cardtext + "#"  + printed + "#" + bToI(available) + "#";
		 
		 if (typecode.equals("e") || typecode.equals("m") || typecode.equals("s")) returnstring = returnstring +  bToI(targete)+ "#" + bToI(targetp)+ "#" + bToI(targets)+ "#" + bToI(targetm)+ "#" + bToI(targeta)+ "#" + bToI(targetg)+ "#" + targetmtype + "#";
		  
		 if (typecode.equals("e") || typecode.equals("m")) {
		 	returnstring = returnstring + bToI(ability)+ "#" + Dsac+ "#" + Lsac+ "#" +Gsac+ "#" + 
		 	bToI(unique)+ "#" + bToI(dizzy)+ "#" + attack+ "#" + lifepoints + "#";
		 	if (typecode.equals("m") || typecode.equals("e")) returnstring = returnstring + mtype + "#";
		 	
		 	
		 }
		returnstring= returnstring.replace('.',' ');
		return returnstring;
		
		
	}
	public boolean available;
	public int printed;
	public int bToI(boolean bool) {
		if (bool) return 1;
		else return 0;	
	}	


}
