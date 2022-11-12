	package webrunner.cardmaster;
import java.util.StringTokenizer;



class CardStatusHolder {
	public String name;
	public int value;
	CardStatusHolder() { name = "default"; value = 0;
	}
	CardStatusHolder(String nname, int nvalue) { name = nname; value = nvalue;
	}
	
	 
}


public class CardmasterServerCard  {
	public int rand;public int sn = -1;
	public int attack;
	public int lifepoints; // used as two extra slots for Effect cards+ "." + 
	public int Dcost;
	public int owner = -1;
	public int Lcost;
	public int Gcost; String tempstatusstring = "";
	public int Dsac;
	public boolean markedForUse = false;
	public int Lsac;
	public String modflag = "";	public String modtype = "";
	public int Gsac;
	public int speed = 0;
	public int colorcode; // 2D, 4L, 8G add for code
	public int cardid = 0;
	public int expansioncode; 
	public int printed;
	public int attackmod = 0;
	public double tempcount1 = 1;
	public double tempcount2 = 1;
	public int lifemod = 0;
	public String flags;
	public int accountedfor;
	public int background = 0;
	public boolean abilityimmune = false;
	public boolean spellimmune = false;
	public boolean available;
	public boolean matchable = true;
	public boolean ability; // has an ability
	public boolean targete; // ability targets Effects
	public boolean targetp; // ability targets Players
	public boolean targetm; // ability targets monsters
	public boolean targets; // Ability targets slots
	public boolean targeta; // Ability automatically targets (no target specification required)
	public boolean targetg;
	public boolean unique; // can play only one
	public boolean dizzy; // is dizzy
	public boolean token = false;
	public boolean fleet = false;
	public boolean facedown = false;
	public String mtype; // monster type code+ "." + 
	public String name;
	public String cardtext;
	public String picture;
	public String typecode; // e, m, or s+ "." + 
	public String targetmtype; // ability or spell target monster type if applicable+ "." + 
	public boolean dummy;
 	public String urlcodebase;
 	public boolean markfordeath = false;
	public int healfor = 0;
	public boolean noundizzy = false;
	public String attanim;
	
	
	public double dDam;
	public double lDam;
	public double gDam;
	public boolean nodeath = false;
	public int sneak;
	public boolean invis;
	public CardStatusHolder[] CardStatus;

	// Existing temporarty flags:
		// khrima - can't attack.  used for WOTW Khrima classic.
		// 
public boolean modFlag(String name) {

		if (modflag == null) return false;
		if (modflag.indexOf(name) != -1) return true;
		else return false;
	
}
		// 
public boolean modType(String name) {

		if (modtype == null) return true;
		if (modtype.indexOf(name) != -1) return true;
		if (modtype.length() == 0) return true;
		if (modtype.equals("!")) return true;
		StringTokenizer tokenizer = new StringTokenizer(modtype);
		while (tokenizer.hasMoreTokens()) {
			if (name.indexOf(tokenizer.nextToken()) != -1) return true;
			
		}
		
		return false;
	
}


	public int GetStatusValue(String name) {
		CardStatusHolder holder = GetStatusHolder(name,false);
		if (holder == null) return 0;
		return holder.value;
	}
	public void SetStatusValue(String name, int value) {
		if (value == 0 && GetStatusHolder(name,false) == null) return;
		CardStatusHolder holder = GetStatusHolder(name,true);
		holder.value = value;
		
		if (value == 0) {
			
			holder.name = "xx-deleted-xx"; // so it acts as if it no longer has it.
			
		}
		
	}
	
	CardStatusHolder GetStatusHolder(String name, boolean createnew) {
		if (name.equals("face")) name = "facedown";
		if (CardStatus == null) CardStatus = new CardStatusHolder[0];
		 
		for (int i=0;i<CardStatus.length;i++) {
			if (CardStatus[i].name.equals(name)) return CardStatus[i];
		}
		
		if (createnew) {
			if (GetStatusHolder("xx-deleted-xx",false) != null) {
				CardStatusHolder holder = GetStatusHolder("xx-deleted-xx",false);
				holder.name = name;
				holder.value = 0;
				return holder;
				
			}
			else {
				
				CardStatusHolder holder = new CardStatusHolder(name,0);
				
				CardStatusHolder[] temp = new CardStatusHolder[CardStatus.length +1];
				System.arraycopy(CardStatus,0,temp,0,CardStatus.length);
				temp[temp.length-1] = holder;
				CardStatus = new CardStatusHolder[CardStatus.length +1];
				System.arraycopy(temp,0,CardStatus,0,CardStatus.length);
				return holder;
				
			}
		
		
		}
		else return null;
	}
	
	// Constructor with TEXT STRING
	
	public int bToI(boolean bool) {
		if (bool) return 1;
		else return 0;	
	}
	
	public String toString() {
		if (dummy) return "b";
		
		if (typecode == null)  {
			dummy = true;
			return "b";
		}
		if (facedown) {
			//System.out.println("Face down !");
			String facedownstring = "f.";
			int dizzyno = bToI(dizzy);
		 	if (noundizzy) dizzyno+= 10;
		 	facedownstring+= typecode + ".";
		 	
		 	facedownstring += dizzyno + ".";
		 	
				
			return facedownstring;
			
			
		}
		
		String returnstring = cardid + "." + typecode + "." + speed + "." + background + "." + name+ "." + Dcost+ "." + Lcost+ "." + Gcost+
		 "." + colorcode+ "." + picture + "." + expansioncode + "." + cardtext + ".";
		 
		 
		 if (typecode.startsWith("d") || typecode.equals("m") || typecode.equals("e") || typecode.equals("s")) returnstring = returnstring +bToI(targete)+ "." + bToI(targetp)+ "." + bToI(targets)+ "." + bToI(targetm)+ "." + bToI(targeta)+ "." + bToI(targetg)+
		  "." + targetmtype + ".";
		  
		 if (typecode.equals("e") || typecode.equals("m") || typecode.startsWith("d")) {
		 	String statusstring = "";
		 	if (GetStatusValue("prevent") > 0) {
		 		statusstring+= "pre,";
		 	}
		 	if (GetStatusValue("prevnx") > 0) {
		 		statusstring+= "pre,";
		 	}
		 	if (GetStatusValue("prevtn") > 0) {
		 		statusstring+= "pre,";
		 	}
		 	if (GetStatusValue("prevntn") > 0) {
		 		statusstring+= "pre,";
		 	}
		 	if (GetStatusValue("khrima") == 1) {
		 		statusstring+= "khrima,";
		 	}
		 	if (GetStatusValue("glue") >=1) {
		 		statusstring+="glue,";
		 	}
		 	if (GetStatusValue("year2") >=1) {
		 		statusstring+="y2,";
		 	}
		 //	if (GetStatusValue("poison") >=1) {
		 	//	statusstring+="poison,";
		 	//}
		 //	if (GetStatusValue("nouse") >=1) {
		 //		statusstring+="nu,";
		 //	}
		 
		 	int numcounters = GetStatusValue("counter");
		 	
		 	int counter50 = numcounters  / 50;
		 	
		 	numcounters-= (counter50*50);
		 	int counter10 = numcounters  / 10;
		 	numcounters-= (counter10*10);
		 	
		 	int counter5 = numcounters  / 5;
		 	numcounters-= (counter5*5);
		 	int counter = numcounters;
		 	for (int i=0;i<counter50;i++) {
		 		
		 		statusstring+="cnt50,";
		 	
		 	}
		 	for (int i=0;i<counter10;i++) {
		 		
		 		statusstring+="cnt10,";
		 	
		 	}
		 	
		 	for (int i=0;i<counter5;i++) {
		 		
		 		statusstring+="cnt5,";
		 	
		 	}
		 	for (int i=0;i<counter;i++) {
		 		statusstring+= "cnt,";
		 	}
		 	
		 counter5 = GetStatusValue("poison") / 5;
			counter = GetStatusValue("poison") % 5;
		 	
		 	for (int i=0;i<counter5;i++) {
		 		
		 		statusstring+="poison5,";
		 	
		 	}
		for (int i=0;i<counter;i++) {
		 		statusstring+= "poison,";
		 	}
		 	
		 	
		 	 counter5 = GetStatusValue("charm") / 5;
			counter = GetStatusValue("charm") % 5;
		 	
		 	for (int i=0;i<counter5;i++) {
		 		
		 		statusstring+="charm5,";
		 	
		 	}
		for (int i=0;i<counter;i++) {
		 		statusstring+= "charm,";
		 	}
		 	
		 	
		 	if (GetStatusValue("burn") >= 1) {
		 		statusstring+="burn,";
		 	}
		 if (GetStatusValue("unstable") >= 1) {
		 		statusstring+="uns,";
		 	}
		 	
		 	if (GetStatusValue("facedown") >= 1) {
		 		statusstring+="face,";
		 	}
		 	if (typecode.equals("m")) {
		 		if (healfor >0) statusstring+="heal,";
		 	}
		 	if (token) statusstring+="tok,";
		 	if (!tempstatusstring.equals("")) {
		 		statusstring += tempstatusstring;
		 	}
		 	if (statusstring.equals("")) statusstring = "none";
		 	int dizzyno = bToI(dizzy);
		 	if (noundizzy) dizzyno+= 10;
		 	returnstring = returnstring + bToI(ability)+ "." + Dsac+ "." + Lsac+ "." +Gsac+ "." + 
		 	bToI(unique)+ "." + dizzyno+ "." + attack+ "." + lifepoints + "." +
		 	statusstring+".";
		 	returnstring = returnstring + mtype + ".";
		 	
		 	
		 }
	
		return returnstring;
		
		
	}
	CardmasterServerCard(String datastring) {
		CardStatus = new CardStatusHolder[0];
		StringTokenizer tokenizer = new StringTokenizer(datastring, "#");
		cardid = Integer.parseInt(tokenizer.nextToken());
		name = tokenizer.nextToken();
		speed = Integer.parseInt(tokenizer.nextToken());
		background = Integer.parseInt(tokenizer.nextToken());
	//	if (background != 0) System.out.println("Card " + name + " uses background " + background);
		typecode = tokenizer.nextToken();
		Dcost = Integer.parseInt(tokenizer.nextToken());
		Lcost = Integer.parseInt(tokenizer.nextToken());
		Gcost = Integer.parseInt(tokenizer.nextToken());
		colorcode = Integer.parseInt(tokenizer.nextToken());			
		picture = tokenizer.nextToken();
		flags = tokenizer.nextToken();
		expansioncode = Integer.parseInt(tokenizer.nextToken());
		cardtext = tokenizer.nextToken();
		printed = Integer.parseInt(tokenizer.nextToken());
		available = (Integer.parseInt(tokenizer.nextToken()) == 1) ? true : false;
		if (typecode.equals("m") || typecode.equals("e") || typecode.equals("s") || typecode.startsWith("d")) {
			targete = (Integer.parseInt(tokenizer.nextToken()) == 1) ? true : false;
			targetp = (Integer.parseInt(tokenizer.nextToken()) == 1) ? true : false;
			targets = (Integer.parseInt(tokenizer.nextToken()) == 1) ? true : false;
			targetm = (Integer.parseInt(tokenizer.nextToken()) == 1) ? true : false;
			targeta = (Integer.parseInt(tokenizer.nextToken()) == 1) ? true : false;
			////System.out.println(cardid);
			targetg = (Integer.parseInt(tokenizer.nextToken()) == 1) ? true : false; 
			targetmtype = tokenizer.nextToken();
			if (typecode.equals("m") || typecode.equals("e") || typecode.startsWith("d")) {
				ability = (Integer.parseInt(tokenizer.nextToken()) == 1) ? true : false;
				Dsac = Integer.parseInt(tokenizer.nextToken());
				Lsac = Integer.parseInt(tokenizer.nextToken());
				Gsac = Integer.parseInt(tokenizer.nextToken());
				unique = (Integer.parseInt(tokenizer.nextToken()) == 1) ? true : false;
				dizzy = (Integer.parseInt(tokenizer.nextToken()) == 1) ? true : false;
				attack = Integer.parseInt(tokenizer.nextToken());
				lifepoints = Integer.parseInt(tokenizer.nextToken());
				
				if (typecode.equals("e") && tokenizer.hasMoreTokens() ) {
					mtype = tokenizer.nextToken();
					
				}
				else if (typecode.equals("e")) {
					mtype = "none";
				}
				if (typecode.startsWith("d")) {
					mtype = tokenizer.nextToken();
					modflag = tokenizer.nextToken();
					modtype = tokenizer.nextToken();
					
				}
				
				if (typecode.equals("m")) { 
				mtype = tokenizer.nextToken();
					if (tokenizer.hasMoreTokens()) { // flags
						tokenizer.nextToken(); // buffer space.  may use for a future thing.
						if (tokenizer.hasMoreTokens()) {
							dDam = Double.parseDouble(tokenizer.nextToken());
							lDam = Double.parseDouble(tokenizer.nextToken());
							gDam = Double.parseDouble(tokenizer.nextToken());
							sneak = Integer.parseInt(tokenizer.nextToken());  // uses a color code
							invis = (Integer.parseInt(tokenizer.nextToken()) == 1) ? true : false;
							
							
							if (tokenizer.hasMoreTokens()) {
								tokenizer.nextToken();  // second buffer
								if (tokenizer.hasMoreTokens()) {
									
									abilityimmune = (Integer.parseInt(tokenizer.nextToken()) == 1) ? true : false;
									spellimmune = (Integer.parseInt(tokenizer.nextToken()) == 1) ? true : false;
									attanim = tokenizer.nextToken();
								//	System.out.println(attanim);
									//System.out.println(abilityimmune + " " + spellimmune);
								}
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
			accountedfor = 0;
			
			
			
			
		}
		
		
	}
	// Constructor for MONSTERS
	CardmasterServerCard(String name, String cardtext, String picture, String typecode, String targetmtype,
		 boolean targete, boolean targetp, boolean targetm, boolean targets, boolean targeta,
		 int cardid, int expansioncode, int colorcode, int Dcost, int Lcost, int Gcost,
		 int Dsac, int Lsac, int Gsac, boolean unique, boolean ability, int attack, int lifepoints, String mtype){	
CardStatus = new CardStatusHolder[0];
	//	//System.out.println(image);
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

		this.
		targetg = false;
	
	//	//System.out.println(this.image);

	
	}
	// Constructor for EFFECTS
	CardmasterServerCard(String name, String cardtext, String picture, String typecode,String targetmtype,
		 boolean targete, boolean targetp, boolean targetm, boolean targets, boolean targeta,
		 int cardid, int expansioncode, int colorcode, int Dcost, int Lcost, int Gcost,
		 int Dsac, int Lsac, int Gsac, boolean unique, boolean ability, int attack, int lifepoints){	
	
	CardStatus = new CardStatusHolder[0];
			
		typecode = "e";
		this.name = name;
		this.cardtext = cardtext;
		this.picture = picture;
	//	//System.out.println(image);
	//	//System.out.println(this.image);
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
		targetg = false;
	
	}
	// Constructor for MAGIC
	void setDizzy(boolean dizzy)
	{ this.dizzy = dizzy; }
	CardmasterServerCard(String name, String cardtext, String picture, String typecode, String targetmtype,
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
		targetg = false;
}

	CardmasterServerCard() {
		CardStatus = new CardStatusHolder[0];
		cardid = 0;
		dummy = true;
	}
	public boolean flag(String flag) {
		if (flags == null) return false;
		if (flags.indexOf(flag) != -1) return true;
		else return false;
	}
	void copydata(CardmasterServerCard card) {
	//	//System.out.println("copy..");
			//	//System.out.println(card.image);
				if (card == null) return;
			if (card.name != null)	this.name = card.name.toString(); else this.name = null;
			if (card.cardtext != null)	this.cardtext = card.cardtext.toString(); else this.cardtext = null;
			if (card.picture!= null)	this.picture = card.picture.toString(); else this.picture = null;
				////System.out.println(this.image);
			if (card.typecode != null)	this.typecode = card.typecode.toString(); else this.typecode = null;
			if (card.cardtext != null)	this.cardtext = card.cardtext.toString(); else this.cardtext = null;
			if (card.targetmtype != null)	this.targetmtype = card.targetmtype.toString(); else this.targetmtype = null;
				this.targete = card.targete;
				this.targetp = card.targetp;
				this.speed = card.speed;
				this.owner = card.owner;
				this.background = card.background;
				this.targetm = card.targetm;
				this.targets = card.targets;
				this.targeta = card.targeta;
				this.cardid = card.cardid;
				this.expansioncode = card.expansioncode;
				this.colorcode = card.colorcode;
				this.Dcost = card.Dcost;
				this.flags = card.flags;
				this.Lcost = card.Lcost;
				this.Gcost = card.Gcost;
				this.Dsac = card.Dsac;
					this.fleet = card.fleet;
				this.Lsac = card.Lsac;
				this.Gsac = card.Gsac;
				this.targetg = card.targetg;
			this.dummy = card.dummy;
			this.dizzy = card.dizzy;
			
				this.Dsac = card.Dsac;
				this.Lsac = card.Lsac;
				this.Gsac = card.Gsac;
				this.unique = card.unique;
				this.ability = card.ability;
				this.attack = card.attack ;
				this.lifemod = card.lifemod;
				this.attackmod = card.attackmod;
				this.lifepoints = card.lifepoints ;
				this.mtype = card.mtype;
				this.healfor = card.healfor;
				this.token = card.token;
				this.dDam  = card.dDam;
				this.lDam = card.lDam;
				this.gDam = card.gDam;
				this.sneak = card.sneak;
				this.invis = card.invis;
				this.spellimmune = card.spellimmune;
				this.abilityimmune = card.abilityimmune;
			    this.markfordeath = card.markfordeath;
				
			CardStatus = new CardStatusHolder[0];
			for (int i=0;i<card.CardStatus.length;i++) {
				SetStatusValue(card.CardStatus[i].name,card.CardStatus[i].value);
				
			}
				this.noundizzy = card.noundizzy;
				this.markedForUse = false;
				this.modtype = card.modtype;
				this.modflag = card.modflag;
			if (card.attanim != null)this.attanim = card.attanim.toString(); else this.attanim = null;
	}


		
}	



