package webrunner.cardmaster;
import java.io.*;
import java.util.Vector;
import java.util.StringTokenizer;
import java.security.SecureRandom;
//import java.util.Random;
public class CardmasterChatRoom{
	boolean lowestfound = false;
	static int protocolnum = 45;
	static String version = "d3.372-" + protocolnum;
	/* 
	
	 *Fixed crash bug involving Rust Dragon
	 */
	 
	public int globalwinner = 0;
	public int globalwon = 0;
	public int globalloser = 0;
	public int last1text =0;
	public int last2text =0;
	public boolean gamebegun = false;
	int highhealth1 = 300;
	int highhealth2 = 300;
	int lowhealth1 = 300;
	int lowhealth2 = 300;
	public int timeout = 1000;
	boolean player1crossedwires = false;
	boolean player2crossedwires = false;
	AbilitySpell[] abilities;
	boolean gift_1_8000 = false;
	boolean gift_2_8000 = false;
	boolean gift_1_8001 = false;
	boolean gift_2_8001 = false;
	boolean gift_1_8002 = false;
	boolean gift_2_8002 = false;
	int playercastingspell = 0;
	
	public String duelendedbycard = " ";
	public String duelendedbytype = " ";
	
	boolean gift_1_8003 = false;
	boolean gift_2_8003 = false;
	boolean gift_1_8004 = false;
	boolean gift_2_8004 = false;
	boolean gift_1_8005 = false;
	boolean gift_2_8005 = false;


	boolean gift_1_8006 = false;
	boolean gift_2_8006 = false;
	boolean gift_1_8007 = false;
	boolean gift_2_8007 = false;
	boolean gift_1_8008 = false;
	boolean gift_2_8008 = false;
	boolean gift_1_8009 = false;
	boolean gift_2_8009 = false;
	boolean gift_1_8010 = false;
	boolean gift_2_8010 = false;
	
	boolean gift_1_8010_1 = false;
	boolean gift_1_8010_2 = false;
	boolean gift_1_8010_3 = false;
	
	
	boolean gift_2_8010_1 = false;
	boolean gift_2_8010_2 = false;
	boolean gift_2_8010_3 = false;
	
	
	boolean p1abilityresolve = false;
	boolean p2abilityresolve = false;
	int decksize1 = 0;
	int decksize2 = 0;
//	private int roomnumber;
	public boolean reserve = false;
	public String name1;
	public boolean invisible = false;
	public boolean p1invisible = false;
	public boolean p2invisible = false;
	public String name2wait;
	public String name2;
	//public String messagebuffer;
	private String messagebuffername;
	public boolean used = false;
	public boolean empty;
	public boolean dead;
	int roomnumber = 0;
	private String[] messagebuffer;
	private String[] messageto;
	private int[] messageid;
	int[] waitcardlog1;
	int[] waitcardlog2;
	int drawcard1 = 0;
	int drawcard2 = 0;
	int drawcardopp1 = 0;
	int drawcardopp2 = 0;
	//boolean noeffects = false;
	int spellcostG = 0;
	int spellcostL = 0;
	int spellcostD = 0;
	
	private int currentmessageid;
	private int currentindex;
	private int waitdraw1 = 0;
	private int waitdraw2 = 0;
	private int waitdrawopp1 = 0;
	private int waitdrawopp2 = 0;
	
	public boolean close = false;
	boolean ended = false;
	CardmasterLibrary deck1;
	CardmasterLibrary deck2;
	MersenneTwisterFast random;
	CardmasterServerCard h1[];
    CardmasterServerCard h2[];
    CardmasterServerCard m1[];
    CardmasterServerCard e1[];
    CardmasterServerCard m2[];
    CardmasterServerCard e2[];
    
    CardmasterServerCard dm1[];
    CardmasterServerCard dm2[];
    CardmasterServerCard de1[];
    CardmasterServerCard de2[];
    
    boolean nextmfacedown = false;
    boolean nextefacedown = false;
    boolean nextentfacedown = false;
    
    boolean abilitycounter = false;
    boolean abilitycounternext = false;
    
   // CardmasterServerCard mm1[]; // Modifiers
   // CardmasterServerCard em1[];
   // CardmasterServerCard mm2[];
   // CardmasterServerCard em2[];
    
       CardmasterServerCard tempp1card = null;
       CardmasterServerCard tempp2card = null;
    
    CardmasterServerCard location; // location card.
    int locationowner =0;
	CardmasterServerCard returntohand;
	int returntohandplayer;
	CardmasterServerCard carddata[];
	String ability_intoplay[];
	String ability_command[];
	String ability_leaveplay[];
	String ability_endturn[];
	String ability_startturn[];
	String ability_attack[];
	String ability_afterattack[];
	String ability_defend[];
	boolean discardwait = false;
	int p1life = 300;
	int p2life = 300;	
	int p1D = 1;
	int p1L = 1;
	int p1G = 1;
	int p2D = 1;
	int p2G = 1;
	int p2L = 1;
	
	
	int p1Du = 1;
	int p2Du = 1;
	int p1Lu = 1;
	int p2Lu = 1;
	int p1Gu = 1;
	int p2Gu = 1;
	
	
	int playerphase = 1;
	int winner = 0;
	int turncount = 1;
	double tempcount1 = 0;
	double tempcount2 = 0;
	// = 200;
	static int PHASE_DRAW = 0;
	static int PHASE_SAC = 1;
	static int PHASE_PLAY = 2;
	static int PHASE_ATTACK = 3;
	static int PHASE_DEFEND = 4;
	static int PHASE_RESOLVE = 5;
	int phase = PHASE_DRAW;
	public int waitcards1 = 0;
	public int waitcards2 = 0;
	boolean turndamage1 = false;
	boolean turndamage2 = false;
	boolean attacking = false;
	int idcode1 = 0;
	int idcode2 = 0;
	CardmasterUser user1;
	CardmasterUser user2;
	int[] attacker;
	int[] defender;
	int[] attackresult;
	boolean[] defenderlock;
	
	public int skipturn1 = 0;
	public int skipturn2 = 0;
	public boolean skipturn = false;
	public void requestclose() {
		
		message("OVR#");	
		
	}
	boolean switchhands = false;
	public void SwitchHands() {
		CardmasterServerCard[] temp = h1;
		h1 = h2;
		h2 = temp;
		switchhands = true;
	}
	
	public CardmasterServerCard GetModifierParent(CardmasterServerCard card) {
		if (card == null) return null;
		if (card.dummy) return null;
		for (int i=0;i<5;i++) {
			if (dm1[i] == card) return m1[i];
			if (de1[i] == card) return e1[i];
			if (dm2[i] == card) return m2[i];
			if (de2[i] == card) return e2[i];
			
		}
		
		return null;
	}
	
	void ShellGame(int player) {
		java.util.ArrayList<Integer> newslots = new java.util.ArrayList<Integer>();
		
		
		//int newslots[] = new int[5];
		for (int i=0;i<5;i++) {
			newslots.add(i);
		//	newslots[i] = i;
		}
		
		java.util.Collections.shuffle(newslots);
		java.util.Collections.shuffle(newslots);
		java.util.Collections.shuffle(newslots);
		
		
		CardmasterServerCard[] cards = new CardmasterServerCard[5];
		
		CardmasterServerCard[] modifiers = new CardmasterServerCard[5];
		for (int i=0;i<5;i++) {
			cards[i] = new CardmasterServerCard();
			
			cards[i].copydata(getSlot(player,i,"m"));
			modifiers[i] = new CardmasterServerCard();
			modifiers[i].copydata(GetModifierCard(getSlot(player,i,"m")));
		}
		
		 for (int i=0;i<5;i++) {
		 	System.out.println("SHELLGAME: Moving " + i + " to " + newslots.get(i));
		 	
			getSlot(player,newslots.get(i),"m").copydata(cards[i]);
			getSlot(player,newslots.get(i),"dm").copydata(modifiers[i]);
			
		}
		
			
			
			
		
		
		
	}

	
	public CardmasterServerCard GetModifierCard(CardmasterServerCard card) {
		if (card == null) return null;
		if (card.dummy) return null;
		int owner = owner(card);
		if (owner != 1 && owner != 2) return null;
		String type = card.typecode;
		if (!type.equals("m") && !type.equals("e")) return null;
		
		int slotnum = findSlotNumber(card);
		if (slotnum == -1) return null;
		return getSlot(owner,slotnum,"d"+type);
		
	}
	
	
	String tempname = null;

	String getName(CardmasterServerCard card, boolean play) {
		String newname = tempname;
		tempname =null;
		if (card == null) return "NULL NAME";
   		if (card.dummy) {
   			if (newname != null) {
   			//	String newname = tempname;
   			//	tempname = null;
   				return newname;
   			}
   			return "DUMMY";
   		}
   	
   		if (card.GetStatusValue("facedown") >=1) {
   			return "Face Down Card";
   		}
   		if (play && card.flag("face")) return "Face Down Card";
   		return card.name;
		}
	
	   String getName(CardmasterServerCard card) {
	   		return getName(card,false);
   	
   	}
	
	
	
	public int GetIDCode(String name) {
		if (name.equals(name1)) {
		//	System.out.println("id code for player 1: " + idcode1);
			return idcode1;
			
		}
		else if (name.equals(name2)) {
		//	System.out.println("id code for player 2: " + idcode2);
			return idcode2;
		}		
		
		else return 0;
		
	}
	
	public int NewIDCode(String name) {
		if (name.equals(name1)) {
			idcode1 = random.nextInt(600000);
		//	System.out.println("New id code for player 1: " + idcode1);
			return idcode1;
		}
		else if (name.equals(name2)) {
			idcode2 = random.nextInt(600000);
			//System.out.println("New id code for player 1: " + idcode2);
			return idcode2;
		}		
		
		else return 0;
		
	}
	
	
	
	
	
	
	
	void addSkip(int player, int numturns) {
		if (player==1) skipturn1+= numturns;
		if (player==2) skipturn2+= numturns;
if (player == 1)		message("MES#" + player_named(player) + " will skip " + numturns + " turns! - total " + skipturn1 +"#");
if (player == 2)		message("MES#" + player_named(player) + " will skip " + numturns + " turns! - total " + skipturn2 +"#");
		
	}
	void SetNoDamage(int player) {
		if (player == 1) turndamage1 = true;
		if (player==2) turndamage2 = true;
		message("MES#" + player_named(player) + "'s monsters are protected this turn!#");
	}
	
	void listcards(int player) {
		CardmasterServerCard hand[];
		String name;
		if (player == 1) { hand = h1; name = name1; }
		else if (player == 2) { hand = h2; name = name2; }
		else return;
		String handstring = "Hand of " + name + ": ";
		for (int i=0;i<8;i++) {
			String blah = " ";
			if (i != 0) blah = ", ";
			if (!hand[i].dummy) {
				handstring = handstring + 	blah + hand[i].name;
				return_card_info(hand[i].cardid,player_named(not_player(player)));
				return_card_info(hand[i].cardid,"$obs$");
			}
		}
		message("MES#" + handstring + "#");
		
		
	}
	
	
	void showtopcards(int player, int amount) {
			CardmasterLibrary[] deck = new CardmasterLibrary[2];
				deck[0] = deck1;
				deck[1] = deck2;
			player = player-1;
				
		String deckread = "Top "+ amount + " of deck: ";
		
		for (int i=0;i<amount && i<deck[player].deckcards;i++) {
			int cardnum = deck[player].nextDrawCard(i);  if(cardnum == -1) break;
				return_card_info(cardnum,player_named((player+1)));
			deckread += carddata[cardnum].name + ", ";
		}
		message("MES#" + deckread + "#",player_named((player+1)));
	
	}
	
	void ResetMatchable() {
		for (int i=0;i<5;i++) {
			m1[i].matchable = true;
			m2[i].matchable = true;
			e1[i].matchable = true;
			e2[i].matchable = true;
			
		}
		
	}
	
	boolean MoveToFirstFreeSlot(CardmasterServerCard source, int player) {
		if (source.dummy) return false;
		CardmasterServerCard target = GetFirstFreeSlot(player,source.typecode);
		if (target == null) return false;
		return MoveToSlot(source,target);
	}
	boolean CanMoveToSlot(CardmasterServerCard source, CardmasterServerCard target) {
		if (target == null) { return false;}
		if (source == null) { return false;}
		if (source.dummy) { return false;}
		if (!target.dummy) { return false;}
		
		return true;
		
		
	}
	boolean MoveToSlot(CardmasterServerCard source, CardmasterServerCard target) {
		if (!target.dummy) return false;
		if (source.dummy) return false;
			int attackmod = source.attackmod;
			int lifemod = source.lifemod;
	CardmasterServerCard ModifierSlot = GetModifierCard(source);
		
		target.copydata(source);
		target.attackmod = attackmod;
		target.lifemod = lifemod;
		source.copydata(new CardmasterServerCard());
if (ModifierSlot != null && GetModifierCard(target) != null) {
	if (!ModifierSlot.dummy) {
	
	GetModifierCard(target).copydata(ModifierSlot);
		ModifierSlot.copydata(new CardmasterServerCard());
		}
		}
		if (phase == PHASE_ATTACK || phase == PHASE_DEFEND) fix_attackers();
		return true;
		
	}
	
	CardmasterServerCard GetFirstFreeSlot(int player, String type) {
		//System.out.println("Getfreefirstslot: " + player + type);
		for (int i=0;i<5;i++) {
			//System.out.println("Checking.." + i);
			CardmasterServerCard card = getSlot(player,i,type);
			if (card.dummy) {  return card; }
		}
		return null;
		
		
		
	}
	
	
	boolean availableSlots(String type, int player) {
		if (player == 1 && type.equals("m"))
			for (int i =0;i<5;i++) if (m1[i].dummy) return true;
				
		if (player == 2 && type.equals("m"))
			for (int i =0;i<5;i++) if (m2[i].dummy) return true;
		if (player == 1 && type.equals("e"))
			for (int i =0;i<5;i++) if (e1[i].dummy) return true;
		if (player == 2 && type.equals("e"))
			for (int i =0;i<5;i++) if (e2[i].dummy) return true;
			
		return false;	
			
			
		
		
		
	}
	
	
	CardmasterServerCard modifiedValueCard(CardmasterServerCard card, int player) {
		if (card == null) return card;
		if (card.dummy) return card;
			CardmasterServerCard newcard = new CardmasterServerCard();
		newcard.copydata(card);
		newcard.attack = (int)getModifiedValue(card,"atk","A",owner(card));
		newcard.Dsac = (int)getModifiedValue(card,"dsa","A",owner(card));
		newcard.Gsac = (int)getModifiedValue(card,"gsa","A",owner(card));
		newcard.Lsac = (int)getModifiedValue(card,"lsa","A",owner(card));
	if (HasQuickHit(card)) { newcard.tempstatusstring += "quik,"; }
		newcard.Dcost = (int)getModifiedValue(card,"dco","A",owner(card));
		newcard.Gcost = (int)getModifiedValue(card,"gco","A",owner(card));
		newcard.Lcost = (int)getModifiedValue(card,"lco","A",owner(card));
		newcard.lifepoints = (int)getModifiedValue(card,"lif","A",owner(card));
		if (HasInvisible(card)) { newcard.tempstatusstring += "inv,"; }
		if (HasSpellImmune(card)) { newcard.tempstatusstring += "spim,"; }		
		if (HasAbilityImmune(card)) { newcard.tempstatusstring += "abim,"; }
		
		if (HasBoss(card)) { newcard.tempstatusstring += "boss,"; }
		
		if (HasOblitImmune(card)) { newcard.tempstatusstring += "obim,"; }
		if (Spiked(card)) { newcard.tempstatusstring += "nu,"; }
		if (HasRaddEffect(card)) { newcard.tempstatusstring += "radd,"; }
		if (HasEntrenching(card)) { newcard.tempstatusstring += "entr,"; }
		if (HasDecoying(card)) { newcard.tempstatusstring += "dcy,"; }
		if (HasCannotAttack(card)) { newcard.tempstatusstring += "xatk,"; }
		if (HasCannotDefend(card)) { newcard.tempstatusstring += "xdef,"; }
		if (HasCannotSac(card)) { newcard.tempstatusstring += "xsac,"; }
		if (HasAlly(card)) { newcard.tempstatusstring += "ally,"; }
		
		if (NumPower(card) > 0) { 
			int powr = NumPower(card);
			int numones = powr % 5;
			int num5s = powr /5;
			
				for (int i=0;i<num5s;i++) {
		 		
		 		newcard.tempstatusstring+="pow5,";
		 	
		 	}
		 	for (int i=0;i<numones;i++) {
		 		newcard.tempstatusstring+= "pow,";
		 	}
		
		
		
		
		
		}	
		if (HasAllTypes(card) || HasWallTypes(card)) { newcard.tempstatusstring += "allty,"; }	
		
		if (GetModifierCard(card) != null && !GetModifierCard(card).dummy) { newcard.tempstatusstring += "mdfr,"; }
		if (card.fleet) { newcard.tempstatusstring += "flt,"; 	}
		if (isActivatedConstant("M2ndL",owner(card),card)) { newcard.tempstatusstring += "2ndl,"; }
		if (card.GetStatusValue("facedown") >= 1) {
			//System.out.println("facedown..." + owner(card) + " " + player);
			if ((owner(card) != player && player != 0) || player == -1){
				 newcard.facedown = true;
			//	 card.name = "Face Down Card";
			 }
		}
		if (card.typecode.startsWith("d")) {
			CardmasterServerCard parent = GetModifierParent(card);
				if (parent.GetStatusValue("facedown") >= 1) {
			//System.out.println("facedown..." + owner(card) + " " + player);
				if ((owner(parent) != player && player != 0) || player == -1){
					 newcard.facedown = true;
				//	 card.name = "Face Down Card";
				 }
						}
			
			newcard.dizzy = parent.dizzy;
			
			
			
			
			
			
		}
		
		
	//	System.out.println("card: " + card.name + " newcard: " + newcard.name);
		return newcard;
		
	}
	
	boolean dizzy(CardmasterServerCard card, boolean dizzy) {
		if (card == null) return false;
		if (card.typecode == null) return false;
		if (card.typecode.startsWith("d")) card = GetModifierParent(card);
		
		boolean success = (card.dizzy || dizzy) && !(card.dizzy && dizzy);
		
		if (card.flag("focus") && dizzy) {
			return false;
		}
	
		card.dizzy = dizzy;
		
		String dizzycode = "Z";
		if (dizzy) dizzycode= "Z";
		else dizzycode = "U";
		
		
		if (dizzy == true && success && (phase == PHASE_ATTACK || phase == PHASE_DEFEND) && card.typecode != null) {
			int player = owner(card);
			String type = card.typecode;
			int slot = findSlotNumber(card);
						if (type != null)
						if (player != playerphase && type.equals("m") && defender[slot+1] > 0 && (!defenderlock[slot+1])) {
							attacker[defender[slot+1]] = 6;
							defender[slot+1] = 0;
							defenderlock[slot+1] = false;
							fix_attackers();
							message("ATD#" + playerphase + "#" + attacker[1]+ "#" + attacker[2]+ "#" + attacker[3]+ "#" + attacker[4]+ "#" + attacker[5] + "#");
				
						}
						if (type != null)
						if (player == playerphase && type.equals("m") && attacker[slot+1] > 0) {
							if (attacker[slot+1] < 6) {
							
								defender[attacker[slot+1]] = 0; 
								defenderlock[attacker[slot+1]] = false;

							}
							for (int i=0;i<6;i++)
								if (defender[i] == (slot+1)) {defender[i] = 0; defenderlock[i] = false;}
							attacker[slot+1] = 0;
								fix_attackers();
							message("ATD#" + playerphase + "#" + attacker[1]+ "#" + attacker[2]+ "#" + attacker[3]+ "#" + attacker[4]+ "#" + attacker[5] + "#");
				
							
						}			
			
			
			
			
			
			
		}
		
		if (success) alltriggeredabilities(card,"TC" + card.typecode + dizzycode + owner(card) );
if (card.typecode != null)		
			if (card.typecode.equals("m") && card.GetStatusValue("unstable")>=1 && success && dizzy) {
			damage_monster(owner(card),findSlotNumber(card),10);
		}
		
		return success;
		
	}
	public int bToI(boolean bool) {
		if (bool) return 1;
		else return 0;	
	}
	void abilityResolve(String play, boolean yes) {
		if (discardwait) return;
		int player = player_named(play);
		if (abilities.length == 0) {
			p1abilityresolve = false; p2abilityresolve = false;
		}
		else if (player == 1) {
			p1abilityresolve = yes;
		}
		else if (player == 2) {
			p2abilityresolve = yes;
		} 
		
		if (p1abilityresolve && p2abilityresolve) runWaitingAbilities();
		message("ABR#" + bToI(p1abilityresolve) + "#" + bToI(p2abilityresolve) + "#" + abilities.length + "#");
	}
	
	CardmasterServerCard putIntoPlayFree(int player, int cardid, CardmasterServerCard pointer) {
		return putIntoPlayFree(player,cardid,pointer,false);
		
	}
	CardmasterServerCard putIntoPlayFree(int player, int cardid, CardmasterServerCard pointer, boolean token) {
		String type = carddata[cardid].typecode;
		if (!availableSlots(type,player)) return null;
		CardmasterServerCard card = null;
		for (int i=0;i<5;i++) {
			if (type.equals("e") && player==1 && e1[i].dummy) { card = e1[i]; break; }	
			if (type.equals("e") && player==2 && e2[i].dummy) { card = e2[i]; break; }	
			if (type.equals("m") && player==1 && m1[i].dummy) { card = m1[i]; break; }	
			if (type.equals("m") && player==2 && m2[i].dummy) { card = m2[i]; break; }	
			
			
		}
		if (card == null) return null;
		card.rand = random.nextInt(10000);
		card.copydata(carddata[cardid]);
		pointer = card;
		pointer.token = token;
		alltriggeredabilities(card,"TC" + player + "*" + type);
		return card;
		
		
	}
	boolean putIntoPlayFree(int player, int cardid) {
		return putIntoPlayFree(player,cardid,false);
	}
	boolean putIntoPlayFree(int player, int cardid, boolean token) {
		String type = carddata[cardid].typecode;
		if (!availableSlots(type,player)) return false;
		CardmasterServerCard card = null;
		int slot = 0;
		for (int i=0;i<5;i++) {
			if (type.equals("e") && player==1 && e1[i].dummy) { slot = i; card = e1[i]; break; }	
			if (type.equals("e") && player==2 && e2[i].dummy) { slot = i; card = e2[i]; break; }	
			if (type.equals("m") && player==1 && m1[i].dummy) { slot = i; card = m1[i]; break; }	
			if (type.equals("m") && player==2 && m2[i].dummy) { slot = i; card = m2[i]; break; }	
			
			
		}
		if (card == null) return false;
		card.rand = random.nextInt(10000);
		card.copydata(carddata[cardid]);
		
		intoplay_ability_all(player,slot,type);
		alltriggeredabilities(card,"TC" + player + "*" + type);
		return true;
		
		
	}
	
	boolean putIntoPlayFreeToken(int player, int cardid) {
		String type = carddata[cardid].typecode;
		if (!availableSlots(type,player)) return false;
		CardmasterServerCard card = null;
		for (int i=0;i<5;i++) {
			if (type.equals("e") && player==1 && e1[i].dummy) { card = e1[i]; break; }	
			if (type.equals("e") && player==2 && e2[i].dummy) { card = e2[i]; break; }	
			if (type.equals("m") && player==1 && m1[i].dummy) { card = m1[i]; break; }	
			if (type.equals("m") && player==2 && m2[i].dummy) { card = m2[i]; break; }	
			
			
		}
		if (card == null) return false;
		card.rand = random.nextInt(10000);
		card.copydata(carddata[cardid]);
		card.token = true;
		alltriggeredabilities(card,"TC" + player + "*" + type);
		return true;
		
		
	}
	boolean hasCardsInHand(int player) {
		if (player == 1) {
			for (int i=0;i<8;i++) if (!(h1[i].dummy)) return true;
			return false;	
		}	
		if (player == 2) {
			for (int i=0;i<8;i++) if (!(h2[i].dummy)) return true;
			return false;	
		}
		return false;		
		
	}
	void to_graveyard(CardmasterServerCard card) {
		if (card == null) return;
		if (card.dummy) return;
		int own = owner(card);
		if (card == location) own = locationowner;
		if (card.owner != -1) own = card.owner;
	//	System.out.println("Token: " + card.token);
	
	
		if (own==1 || (own == 2 && switchhands)) {
			
		if (! card.token ) deck1.addGrave(card.cardid);	
		}
		
		else if (own == 2|| (own == 1 && switchhands)) {
		if (! card.token )deck2.addGrave(card.cardid);
			
		}
		card.rand = random.nextInt(10000);	
		card.copydata(new CardmasterServerCard());
		message(GRA(player_named(own)),player_named(own));
		MessageSTN();
		
	}
	
	
	int player_named(String playername) {
		if (playername.equals(name1)) return 1;
		return 2;		
	
	
}

	String player_named(int playername) {
		if (playername == 1) return name1;
		return name2;		
	
	
}


	boolean matchtrigger(String whattriggers, String abilitystring, CardmasterServerCard self, CardmasterServerCard trigger) {
		boolean triggered = true;
			
		StringTokenizer tokenizer = new StringTokenizer(abilitystring,";");
		abilitystring = tokenizer.nextToken();
		abilitystring = abilitystring.toUpperCase();
		whattriggers = whattriggers.toUpperCase();
		//System.out.println("Comparing " + whattriggers + " to " + abilitystring);
		// PLAYER abilities
		if (whattriggers.startsWith("T") && abilitystring.startsWith("T")) {
			
			if (whattriggers.substring(1).startsWith("P") && abilitystring.substring(1).startsWith("P"))
			
			for (int i=2;i<whattriggers.length();i++) {
				String lookcode = whattriggers.substring(i,i+1);
				//System.out.println("Checking lookcode: " + lookcode + " " + triggered);
				if (lookcode.equals("M")) 
					if (abilitystring.indexOf("M",2) == -1 
						&& abilitystring.indexOf("X",2) == -1) triggered = false;
				if (lookcode.equals("C")) 
					if (abilitystring.indexOf("C",2) == -1) triggered = false;
				if (lookcode.equals("A")) 
					if (abilitystring.indexOf("A",2) == -1) triggered = false;
			
				if (lookcode.equals("E")) 
					if (abilitystring.indexOf("E",2) == -1 
						&& abilitystring.indexOf("X",2) == -1) triggered = false;

		
						
						
				if (lookcode.equals("P")) 
					if (abilitystring.indexOf("P",2) == -1) triggered = false;

				if (lookcode.equals("%")) 
					if (abilitystring.indexOf("%",2) == -1) triggered = false;


				if (lookcode.equals("1")) {
					int player = owner(self);
					String code = "*";
					if (player == 1) code = "S";	
					if (player == 2) code = "O";
					
					if (abilitystring.indexOf(code,2) == -1 
						&& abilitystring.indexOf("B",2) == -1) triggered = false;
					
					
					
				}
				if (lookcode.equals("2")) {
					int player = owner(self);
					String code = "*";
					if (player == 2) code = "S";	
					if (player == 1) code = "O";
					
					if (abilitystring.indexOf(code,2) == -1 
						&& abilitystring.indexOf("B",2) == -1) triggered = false;
					
					
					
				}			
				if (lookcode.equals("L")) 
					if (abilitystring.indexOf("L",2) == -1 
						&& abilitystring.indexOf("J",2) == -1) triggered = false;		
				if (lookcode.equals("H")) 
					if (abilitystring.indexOf("H",2) == -1 
						&& abilitystring.indexOf("J",2) == -1) triggered = false;			

				


				if (lookcode.equals("N")) 
					if (abilitystring.indexOf("N",2) == -1 
						&& abilitystring.indexOf("K",2) == -1) triggered = false;	

				if (lookcode.equals("G")) 
					if (abilitystring.indexOf("G",2) == -1 
						&& abilitystring.indexOf("K",2) == -1) triggered = false;


									
			}
			else
			if (whattriggers.substring(1).startsWith("A") && abilitystring.substring(1).startsWith("A"))
			
			for (int i=2;i<whattriggers.length();i++) {
				String lookcode = whattriggers.substring(i,i+1);
					//System.out.println("Checking lookcode: " + lookcode + " " + triggered);
			
				if (lookcode.equals("E")) 
					if (abilitystring.indexOf("E",2) == -1 
						&& abilitystring.indexOf("D",2) == -1) triggered = false;		
				
				if (lookcode.equals("W")) 
					if (abilitystring.indexOf("W",2) == -1 
						&& abilitystring.indexOf("D",2) == -1) triggered = false;		

				if (lookcode.equals("K")) 
					if (abilitystring.indexOf("K",2) == -1 
						&& abilitystring.indexOf("A",2) == -1) triggered = false;		
																
				if (lookcode.equals("C")) 
					if (abilitystring.indexOf("C",2) == -1 
						&& abilitystring.indexOf("A",2) == -1) triggered = false;		
												
				
			}
			else
			if (whattriggers.substring(1).startsWith("C") && abilitystring.substring(1).startsWith("C"))
			
			for (int i=2;i<whattriggers.length();i++) {
				String lookcode = whattriggers.substring(i,i+1);
					//System.out.println("Checking lookcode: " + lookcode + " " + triggered);
			
				if (abilitystring.indexOf("T",2) != -1 && trigger != self) triggered = false;
				if (abilitystring.indexOf("N",2) != -1 && trigger == self) triggered = false;
				
				 
				if (lookcode.equals("1")) {
					int player = owner(self);
					String code = "{";
					if (player == 1) code = "S";	
					if (player == 2) code = "O";
					
					if (abilitystring.indexOf(code,2) == -1 
						&& abilitystring.indexOf("B",2) == -1) triggered = false;
					
					
					
				}
				else if (lookcode.equals("2")) {
					int player = owner(self);
					String code = "{";
					if (player == 2) code = "S";	
					if (player == 1) code = "O";
					
					if (abilitystring.indexOf(code,2) == -1 
						&& abilitystring.indexOf("B",2) == -1) triggered = false;
					
					
					
				}				
				if (lookcode.equals("T")) 
					if (abilitystring.indexOf("T",2) == -1 
						&& abilitystring.indexOf("D",2) == -1) triggered = false;				
				if (lookcode.equals("G")) 
					if (abilitystring.indexOf("G",2) == -1 
						&& abilitystring.indexOf("D",2) == -1) triggered = false;				
				if (lookcode.equals("P")) 
					if (abilitystring.indexOf("P",2) == -1 
						&& abilitystring.indexOf("A",2) == -1) triggered = false;				
				if (lookcode.equals("-")) 
					if (abilitystring.indexOf("-",2) == -1 
						&& abilitystring.indexOf("A",2) == -1) triggered = false;				


				if (lookcode.equals("M")) 
					if (abilitystring.indexOf("M",2) == -1 
						&& abilitystring.indexOf("X",2) == -1) triggered = false;				
				if (lookcode.equals("E")) 
					if (abilitystring.indexOf("E",2) == -1 
						&& abilitystring.indexOf("X",2) == -1) triggered = false;				


				if (lookcode.equals("Z")) 
					if (abilitystring.indexOf("Z",2) == -1 
						&& abilitystring.indexOf("Y",2) == -1) triggered = false;				

				if (lookcode.equals("U")) 
					if (abilitystring.indexOf("U",2) == -1 
						&& abilitystring.indexOf("Y",2) == -1) triggered = false;				
												
				if (lookcode.equals("R")) 
					if (abilitystring.indexOf("R",2) == -1 
						&& abilitystring.indexOf("L",2) == -1) triggered = false;				
				
				if (lookcode.equals("H")) 
					if (abilitystring.indexOf("H",2) == -1 
						&& abilitystring.indexOf("L",2) == -1) triggered = false;				

				if (lookcode.equals("W")) 
					if (abilitystring.indexOf("W",2) == -1 
						&& abilitystring.indexOf("Q",2) == -1) triggered = false;				
								
				if (lookcode.equals("F")) 
					if (abilitystring.indexOf("F",2) == -1 
						&& abilitystring.indexOf("Q",2) == -1) triggered = false;				
				if (lookcode.equals("K")) 
					if (abilitystring.indexOf("K",2) == -1) triggered = false;	
							
				if (lookcode.equals("!")) 
					if (abilitystring.indexOf("!",2) == -1) triggered = false;
				if (lookcode.equals(">")) 
					if (abilitystring.indexOf(">",2) == -1 
						&& abilitystring.indexOf(":",2) == -1) triggered = false;	
						
											
				if (lookcode.equals("P")) 
					if (abilitystring.indexOf("P",2) == -1) triggered = false;
											
				if (lookcode.equals("@")) 
					if (abilitystring.indexOf("@",2) == -1 
						&& abilitystring.indexOf(":",2) == -1) triggered = false;
				if (lookcode.equals("&")) 
					if (abilitystring.indexOf("&",2) == -1) triggered = false;

				if (lookcode.equals("<"))  // another card is killed by this card
					if (abilitystring.indexOf("<",2) == -1) triggered = false;
				if (lookcode.equals("~"))  // obliterate
					if (abilitystring.indexOf("~",2) == -1) triggered = false;

				if (lookcode.equals("A"))  // another card is damaged by this card
					if (abilitystring.indexOf("A",2) == -1) triggered = false;



				if (lookcode.equals("*")) 
					if (abilitystring.indexOf("*",2) == -1) triggered = false;
			} else triggered = false;
		} else triggered = false;	
		//System.out.println(triggered);	
		return triggered;	
		
	}
	
	void alltriggeredabilities(CardmasterServerCard trigger, String what) {
		//System.out.println("Checking trigger " + what);
		what= what.toUpperCase();
		for (int i =0;i<5;i++) {
			if (!e1[i].dummy) triggerabilities(trigger,e1[i],what);	
			if (!m1[i].dummy) triggerabilities(trigger,m1[i],what);	
			if (!e2[i].dummy) triggerabilities(trigger,e2[i],what);	
			if (!m2[i].dummy) triggerabilities(trigger,m2[i],what);	
			
			if (!de1[i].dummy) triggerabilities(trigger,de1[i],what);	
			if (!dm1[i].dummy) triggerabilities(trigger,dm1[i],what);	
			if (!de2[i].dummy) triggerabilities(trigger,de2[i],what);	
			if (!dm2[i].dummy) triggerabilities(trigger,dm2[i],what);	
			
		
		}	
			if (!location.dummy) triggerabilities(trigger,location,what);
			
	}
	int triggerabilities(CardmasterServerCard trigger, CardmasterServerCard self, String whattriggers) {
		if (self == null) return 0;
		if (self.dummy) return 0;
	//	int returnv = 0;
		int cardid =self.cardid;
		
		String abilitystring;
		abilitystring = ability_afterattack[cardid];
		if (!abilitystring.startsWith("T")) return 0;
		if (!matchtrigger(whattriggers,abilitystring,self,trigger)) return 0;	
		AbilityTarget target = null;
		if (whattriggers.startsWith("TCM")) target = new AbilityTarget(owner(trigger),findSlotNumber(trigger),"m");
		if (whattriggers.startsWith("TCE")) target = new AbilityTarget(owner(trigger),findSlotNumber(trigger),"e");		
		if (whattriggers.startsWith("TC%")) target = new AbilityTarget(locationowner,0,"l");
		if (whattriggers.startsWith("TP1")) target = new AbilityTarget(1);
		if (whattriggers.startsWith("TP2")) target = new AbilityTarget(2);
		if (target == null) return 0;		
		if (CardmasterData.DEBUGMODE)System.out.println("running triggered ability: " + abilitystring + " of card " + self.name + " by match " + whattriggers);
	
	if (!endedonce && winner == 0) 	duelendedbycard = "Triggered ability of " + self.name;
	
		int succode = runability(abilitystring, owner(self), target, self, findSlotNumber(trigger));
	resolve_drawcards();
				
		return succode;
		
	}
	
	
	int do_modifier_ability(String play, int slot, String type, int tplayer, String ttype, int tslot, String targettype) {
		// ability types:
		// G -> grave use tslot as the slot
		// S -> slot use ttype tslot
		// P -> player use tplayer
		// A -> auto, use nothing
		// C -. Card, same as do ability on monster and do ability on effect
		int player = player_named(play);
		CardmasterServerCard card = GetModifierCard(getSlot(player,slot,type));
		CardmasterServerCard parent = getSlot(player,slot,type);
		if (card == null) return -30;
		if (card.dummy) return -31;
		if (!card.ability) return -32;
		
		if (parent.dizzy) return -33;
		
		int cardid = card.cardid;
		
		
		if (isActivatedConstant("MNOAB",player,parent)) return -34;
		
		if (targettype.equals("s") && !card.targets) return -35;
		if (targettype.equals("g") && !card.targetg) return -36;
		if (targettype.equals("p") && !card.targetp) return -37;
		if (targettype.equals("a") && !card.targeta) return -38;
		if (targettype.equals("c") && ttype.equals("m") && !card.targetm) return -39;
		
		if (targettype.equals("c") && ttype.equals("e") && !card.targete) return -40;
		
		AbilityTarget target = null;
		int speed = card.speed;
		int tcardid = 0;
		if (targettype.equals("s")) {
			
			target = null;
		} else if (targettype.equals("p")) {
			target = new AbilityTarget(tplayer);
		
		} else if (targettype.equals("g")) {
			target = new AbilityTarget(player,tslot);
			tcardid = tslot;
		} else if (targettype.equals("a")) {
			target = null;
		} else if (targettype.equals("c")) {
			target = new AbilityTarget(tplayer,tslot,ttype);
			tcardid = getSlot(tplayer,tslot,ttype).cardid;
			
		}
		
		if (targettype.equals("c") && ttype.equals("m")&& decoy_in_play() && !HasDecoying(target.getCard()) ) {
			return -89;
		}
		if (target != null) if (target.isCard) {
			if (HasAbilityImmune(target.getCard())) return -899;
			
			
		}
		
		
		int succode = 0;
		if (speed != 0) {
			succode = addNewAbility(ability_command[cardid],player,target,card,tslot,true,speed,targettype,tplayer,cardid,-1,tcardid);
		}
		else {
			if (parent.GetStatusValue("glue") >=1) {
				dizzy(parent,true);
				parent.SetStatusValue("glue",0);
				message("MES#"+ parent.name + " Has been affected by the glue!");
				return 0;
				}
			
			
			if (parent.GetStatusValue("facedown") >=1) {
				parent.SetStatusValue("facedown",0);
			MessageSTC();
				}
		
			if ((!ability_command[cardid].endsWith("DEND;") && parent.flag("focus")))	
			{
				succode = 0;
				
			}
			else {
				succode = runability(ability_command[cardid],player,target,card,tslot);
			}
		resolve_drawcards();
		}
		boolean success = true;
		if (succode == 0) success = false;
		
		if (speed == 0) {
			
			if (succode == 1) dizzy(parent,true);	
			if (success) alltriggeredabilities(card,"TC" + type+ player + "!");
			if (success && targettype.equals("c")) alltriggeredabilities(target.getCard(),"TC"+ttype+"@"+ tplayer);
			if (success)triggerabilities(card,	target.getCard(),"TC" + parent.typecode.toUpperCase() + "&"+ tplayer);
		
			
				if(success) message("MES#" + play + " uses ability of " + getName(carddata[cardid]));
			MessageSTN();
			MessageSTC();
			message(HAND(play),play);	message(LOC()); //MDN();
		}
		
		
		return succode;
		
		
	}

	boolean do_ability_on_slot(String play, int slot, String type, int tslot) {
		int player = player_named(play);
		if (getSlot(player,slot,type) == null) return false;
		if (getSlot(player,slot,type).dummy) return false; // is a card
		if (!getSlot(player,slot,type).ability) return false; // has  ability
		if (!getSlot(player,slot,type).targets) return false; // ability target slots
		if (getSlot(player,slot,type).dizzy) return false; 
		
		int cardid = getSlot(player,slot,type).cardid;
		
		CardmasterServerCard card = getSlot(player,slot,type);
		if (isActivatedConstant("MNOAB",player,card)) return false;
		int speed = getSlot(player,slot,type).speed;
		
	
		
		int succode = 0;
		if (speed != 0)
			 succode = addNewAbility(ability_command[cardid],
									player,null,getSlot(player,slot,type),
									tslot, true, getSlot(player,slot,type).speed,
									"s",player,cardid,-1,0);
		if (speed == 0) {
		    CardmasterServerCard self = getSlot(player,slot,type);
		    
		    	   if (getSlot(player,slot,type).GetStatusValue("facedown") >=1) {
	getSlot(player,slot,type).SetStatusValue("facedown",0);
	MessageSTC();
	}
	
		    
		   	if(self.GetStatusValue("glue") >=1) {
		   		
		  
		   		
					dizzy(self,true);
					self.SetStatusValue("glue",0);
					message("MES#" + self.name + " has been affected by the glue!");
					return false;
				}
		   
		   
		   
	
		   
		
		     succode = runability(ability_command[cardid],player,null,getSlot(player,slot,type),tslot);
			 
		resolve_drawcards();
		
		}		
		boolean success = true;
		if (succode == 0) success = false;
		if (speed ==0) {
		
		
			
			if (succode == 1) dizzy(getSlot(player,slot,type),true);
			
			if (success) alltriggeredabilities(card,"TC"+ type+ player + "!" );
		
			if(success) message("MES#" + play + " uses ability of " + getName(carddata[cardid]));
			
			
			MessageSTN();
			MessageSTC(); //MDN();
			message(HAND(play),play);
			message(LOC());
		}
		return success;
				
		
	}

	boolean do_ability_grave(String play, int slot, String type, int tcard) {
		int player = player_named(play);
		if (getSlot(player,slot,type) == null) return false;
		if (getSlot(player,slot,type).dummy) return false; // is a card
		if (!getSlot(player,slot,type).ability) return false; // has  ability
		if (!getSlot(player,slot,type).targetg) return false; // ability target slots
		if (getSlot(player,slot,type).dizzy) return false; 
			CardmasterServerCard card = getSlot(player,slot,type);
			if (isActivatedConstant("MNOAB",player,card)) return false;
		AbilityTarget target = new AbilityTarget(player,tcard);
		int cardid = getSlot(player,slot,type).cardid;
		int speed = getSlot(player,slot,type).speed;
		int succode = 0;
		if (speed != 0) 
			succode = addNewAbility(ability_command[cardid],player,
							target,getSlot(player,slot,type),-1,true,
							getSlot(player,slot,type).speed, "g",
							player, cardid, -1, tcard); 
		if (speed == 0) {
			
			CardmasterServerCard self = getSlot(player,slot,type);
			
				if (getSlot(player,slot,type).GetStatusValue("facedown") >=1) {
				getSlot(player,slot,type).SetStatusValue("facedown",0);
				MessageSTC();
	}
			
		   	if(self.GetStatusValue("glue") >=1) {
		   		
					dizzy(self,true);
					self.SetStatusValue("glue",0);
					message("MES#" + self.name + " has been affected by the glue!");
					return false;
				}
			
			
		

			 succode = runability(ability_command[cardid],player,target,getSlot(player,slot,type),-1);
			
			
		resolve_drawcards();
					
		}
		boolean success = true;
		if (succode == 0) success = false;
		if (speed ==0) {
			if (succode == 1) dizzy(getSlot(player,slot,type),true);
			if (success) alltriggeredabilities(card,"TC" + type +player + "!" );
		
			if(success) {
				if (!skipturn) {
				
				message("MES#" + play + " uses ability of " + getName(carddata[cardid]));
				MessageSTN();
				MessageSTC(); //MDN();
				message(HAND(play),play);
				message(LOC());
			}
			if (returntohand != null)  {
					
						return_to_hand(returntohandplayer,returntohand);	
							returntohand = null;
						}
		}
		
		}
		return success;
				
		
	}

	void return_card_info(int cardid, String from) {
	//	for (int i=0;i<1000;i++);
		if (cardid == 0) message("CDD#0#b#", from);
		else message("CDD#" + cardid + "#" + carddata[cardid] + "#", from);	
		
	}
	boolean do_ability_auto(String play, int slot,String type) {
		int player = player_named(play);
		if (getSlot(player,slot,type) == null) return false;
		if (getSlot(player,slot,type).dummy) return false; // is a card
		if (!getSlot(player,slot,type).ability) return false; // has  ability
		if (!getSlot(player,slot,type).targeta) return false; // ability target all
		int cardid = getSlot(player,slot,type).cardid;
		if (getSlot(player,slot,type).dizzy) return false; 
			CardmasterServerCard card = getSlot(player,slot,type);
		if (isActivatedConstant("MNOAB",player,card)) return false;
		int speed = getSlot(player,slot,type).speed;
		int succode = 0;
		if (speed != 0) 
		
			succode = addNewAbility(ability_command[cardid],player,null,getSlot(player,slot,type),-1,
									true, speed, "a", 0, cardid, -1, 0);
		if (speed == 0) {
			
			
			CardmasterServerCard self = getSlot(player,slot,type);
				if (getSlot(player,slot,type).GetStatusValue("facedown") >=1) {
				getSlot(player,slot,type).SetStatusValue("facedown",0);
	MessageSTC();
	}
		   	if(self.GetStatusValue("glue") >=1) {
					dizzy(self,true);
					self.SetStatusValue("glue",0);
					message("MES#" + self.name + " has been affected by the glue!");
					return false;
				}
			
		
			 succode = runability(ability_command[cardid],player,null,getSlot(player,slot,type),-1);
			resolve_drawcards();
		}
		boolean success = true;
		if (succode == 0) success = false;
		if (speed ==0) {
		
			if (succode == 1) dizzy(getSlot(player,slot,type),true);		
			if (success) alltriggeredabilities(card,"TC" + type + player + "!");
		
			if(success) message("MES#" + play + " uses ability of " + getName(carddata[cardid]));
		
			MessageSTN();
			MessageSTC();
			message(HAND(play),play);	message(LOC()); //MDN();
			if (returntohand != null)  {
					
						return_to_hand(returntohandplayer,returntohand);	returntohand = null;	
						}
		}
		return success;
				
		
	}
	///////////////////////// BLARGLE
	boolean do_ability_player(String play, int slot,String type,int tplayer) {
		
		int player = player_named(play);
	if (getSlot(player,slot,type) == null) return false;
		if (getSlot(player,slot,type).dummy) return false; // is a card
	//	System.out.println("Isnt a dummy");
		if (!getSlot(player,slot,type).ability) return false; // has  ability
//		System.out.println("Has an ability");
		if (!getSlot(player,slot,type).targetp) return false; // ability target all
	if (getSlot(player,slot,type).dizzy) return false; 
			CardmasterServerCard card = getSlot(player,slot,type);
	if (isActivatedConstant("MNOAB",player,card)) return false;
///		System.out.println("Do the ability now, yah!");
		int cardid = getSlot(player,slot,type).cardid;
		AbilityTarget target = new AbilityTarget(tplayer);
		int speed = getSlot(player,slot,type).speed;
		int succode =0;
		if (speed != 0)
				succode = addNewAbility(ability_command[cardid],player,target,getSlot(player,slot,type),-1,
										true, speed, "p", tplayer, cardid, -1, 0);
			
			
			
		
		
		if (speed == 0){
				if (getSlot(player,slot,type).GetStatusValue("facedown") >=1) {
			getSlot(player,slot,type).SetStatusValue("facedown",0);
MessageSTC();
	}
			
			CardmasterServerCard self = getSlot(player,slot,type);
		   	if(self.GetStatusValue("glue") >=1) {
					dizzy(self,true);
					self.SetStatusValue("glue",0);
					message("MES#" + self.name + " has been affected by the glue!");
					return false;
				}
			
	
			 succode = runability(ability_command[cardid],player,target,getSlot(player,slot,type),-1);
			resolve_drawcards();
					
		}
		boolean success = true;
		if (speed == 0){
		
			if (succode == 0) success = false;
			if (succode == 1) dizzy(getSlot(player,slot,type),true);
			if (success) alltriggeredabilities(card,"TC" + type + player + "!");
				
					if(success) message("MES#" + play + " uses ability of " + getName(carddata[cardid]));
			MessageSTN();
			MessageSTC();
			message(HAND(play),play);	message(LOC()); //MDN();
			if (returntohand != null)  {
						
						return_to_hand(returntohandplayer,returntohand);returntohand = null	;
						}
		}
		return success;
				
		
	}
	void resolve_drawcards() {
		while(drawcard1 > 0) 
			draw_ability_card(1);
		
		
		
		while(drawcard2>0)
			draw_ability_card(2);
			
	
			
		while (drawcardopp1 >0)
			draw_ability_opp(1);
			
		while (drawcardopp2 >0)
			draw_ability_opp(2);
			
			if (returntohand != null)  {
					
						return_to_hand(returntohandplayer,returntohand);returntohand = null		;
						}
		
	}
	void intoplay_ability(int player, int slot, String type){
		if (getSlot(player,slot,type).dummy) return; // not likely to happen
		int cardid = getSlot(player,slot,type).cardid;
		if (ability_intoplay[cardid].startsWith("START") || ability_intoplay[cardid].startsWith("INTOP")) {
			runability(ability_intoplay[cardid],player,null,getSlot(player,slot,type),-1);	
			resolve_drawcards();
		}
		
	}

	void failure_ability(int player, int handslot) {
		failure_ability(player,handslot,null, -1);	
	}
	void failure_ability(int player, int handslot, AbilityTarget target, int targetslot){
	
		CardmasterServerCard card = null;
		if (player ==1) card = h1[handslot];
		else if (player ==2) card = h2[handslot];
		else return;
		int cardid = card.cardid;
		if (ability_intoplay[cardid].startsWith("FCOST")) {
			runability(ability_intoplay[cardid],player,target,card,targetslot);	
			resolve_drawcards();
		}
		else if (ability_afterattack[cardid].startsWith("FCOST")) {
			runability(ability_afterattack[cardid],player,target,card,targetslot);	
			resolve_drawcards();
		}
		else if (ability_defend[cardid].startsWith("FCOST")) {
			runability(ability_defend[cardid],player,target,card,targetslot);	
			resolve_drawcards();
		}
		else if (ability_leaveplay[cardid].startsWith("FCOST")) {
			runability(ability_leaveplay[cardid],player,target,card,targetslot);	
			resolve_drawcards();
		}
	}
	void failure_ability(int player, CardmasterServerCard card) {
		failure_ability(player,card,null, -1);	
	}
	
	void failure_ability(int player, CardmasterServerCard card, AbilityTarget target, int targetslot) {
		if (card.dummy) return;
		int cardid = card.cardid;
		//int player = owner(card);
		if (ability_intoplay[cardid].startsWith("FCOST")) {
			runability(ability_intoplay[cardid],player,target,card,targetslot);	
			resolve_drawcards();
		}
		else if (ability_afterattack[cardid].startsWith("FCOST")) {
			runability(ability_afterattack[cardid],player,target,card,targetslot);	
			resolve_drawcards();
		}
		else if (ability_defend[cardid].startsWith("FCOST")) {
			runability(ability_defend[cardid],player,target,card,targetslot);	
			resolve_drawcards();
		}
		else if (ability_leaveplay[cardid].startsWith("FCOST")) {
			runability(ability_leaveplay[cardid],player,target,card,targetslot);	
			resolve_drawcards();
		}
	}
	
	void intoplay_ability_all(int player, int slot, String type){
		if (getSlot(player,slot,type).dummy) return; // not likely to happen
		int cardid = getSlot(player,slot,type).cardid;
		if (ability_intoplay[cardid].startsWith("INTOP") ) {
			runability(ability_intoplay[cardid],player,null,getSlot(player,slot,type),-1);	
			resolve_drawcards();
		}
		
	}

	
	void leaveplay_ability(int player, int slot, String type, String kind){
	//	System.out.println("Leaveplay Ability...");
		if (getSlot(player,slot,type) == null) return;
		if (getSlot(player,slot,type).dummy) return; // not likely to happen
		int cardid = getSlot(player,slot,type).cardid;
	//	System.out.println("Leaveplay ability for slot " + slot + " is " + ability_leaveplay[cardid]);
		boolean runability = false;
		// DISCS = discarded from hand
		// GRVYD = card goes into graveyard from anywhere
		// OBLIT = card is obliterated/removed from play
		// RTTHD = card is returned to hand'
	    // LEAVE = card is removed from play in any way.
	    // KILLD = card is removed from the field in any way
	    
	    String abiltype = "Leaves Play ";
		if (ability_leaveplay[cardid].startsWith(kind)) {	
			runability = true;
			if (ability_leaveplay[cardid].startsWith("START")) {
				abiltype = "Destroy "; 
				
			}
			else if (ability_leaveplay[cardid].startsWith("DISCS")) {
				abiltype = "Discard "; 
				
			}
			else if (ability_leaveplay[cardid].startsWith("GRVYD")) {
				abiltype = "To Graveyard "; 
				
			}
			else if (ability_leaveplay[cardid].startsWith("OBLIT")) {
				abiltype = "Obliterate "; 
				
			}
			else if (ability_leaveplay[cardid].startsWith("RTTHD")) {
				abiltype = "Return to Hand "; 
				
			}
			else if (ability_leaveplay[cardid].startsWith("LEAVE")) {
				abiltype = "Leaves Play "; 
				
			}
			else if (ability_leaveplay[cardid].startsWith("KILLD")) {
				abiltype = "Leaves Field "; 
				
			}
			
		}
		
		else if (ability_leaveplay[cardid].startsWith("LEAVE")) runability = true; //card leaves play for whatever reason
		else if (ability_leaveplay[cardid].startsWith("GRVYD") && kind.equals("DISCS") && !getSlot(player,slot,type).token) { abiltype = "Discard "; runability = true;}
		else if (ability_leaveplay[cardid].startsWith("GRVYD") && kind.equals("START") && !getSlot(player,slot,type).token){ abiltype = "To Graveyard "; runability = true;}
		else if (ability_leaveplay[cardid].startsWith("KILLD") && kind.equals("OBLIT")) { abiltype = "Leave Field "; runability = true;}
		else if (ability_leaveplay[cardid].startsWith("KILLD") && kind.equals("START")) { abiltype = "Leave Field "; runability = true;}
		else if (ability_leaveplay[cardid].startsWith("KILLD") && kind.equals("RTTHD")) { abiltype = "Leave Field "; runability = true;}
		if (runability) {
			
			
				if (carddata[cardid].name != null) {
					
								if (!endedonce && winner == 0) duelendedbycard =  abiltype + "ability of " +carddata[cardid].name;
								
							}
			
		runability(ability_leaveplay[cardid],player,null,getSlot(player,slot,type),-1);
		resolve_drawcards();
			
		}
		
	}	
		
	boolean do_ability_on_effect(String play, int slot, String type, int tplayer, int tslot) {
		int player = player_named(play);
		if (getSlot(player,slot,type) == null) return false;
		if (getSlot(player,slot,type).dummy) return false; // is a card
		if (!getSlot(player,slot,type).ability) return false; // has  ability
		if (!getSlot(player,slot,type).targete) return false; // ability targets monsters
	
		int cardid = getSlot(player,slot,type).cardid;
		if (getSlot(player,slot,type).dizzy) return false; 
			CardmasterServerCard card = getSlot(player,slot,type);
	if (isActivatedConstant("MNOAB",player,card)) return false;
		AbilityTarget target = new AbilityTarget(tplayer,tslot,"e");
			if (HasAbilityImmune(target.getCard())) return false;
		int succode = 0;
		int speed = card.speed;
		
		if (speed != 0) {
		
			succode = addNewAbility(ability_command[cardid],player,target,card,tslot,
						true, speed, "e", tplayer, cardid, -1, target.getCard().cardid);
		}
		if (speed == 0){
				if (getSlot(player,slot,type).GetStatusValue("facedown") >=1) {
				getSlot(player,slot,type).SetStatusValue("facedown",0);
MessageSTC();
	}
			CardmasterServerCard self = getSlot(player,slot,type);
		   	if(self.GetStatusValue("glue") >=1) {
					dizzy(self,true);
					self.SetStatusValue("glue",0);
					message("MES#" + self.name + " has been affected by the glue!");
					return false;
				}
			
			
		
			succode = runability(ability_command[cardid],player,target,getSlot(player,slot,type),tslot);
		resolve_drawcards();
		
			
		}			
		boolean success = true;
		if (succode == 0) success = false;
		if (speed == 0) {
			
			if (succode == 1) dizzy(getSlot(player,slot,type),true);	
			if (success) alltriggeredabilities(card,"TC" + type+ player + "!");
			if (success) alltriggeredabilities(target.getCard(),"TCE@"+ tplayer);
			if (success)triggerabilities(card,	target.getCard(),"TC" + card.typecode.toUpperCase() + "&"+ tplayer);
		
			
					if(success) message("MES#" + play + " uses ability of " + getName(carddata[cardid]));
			MessageSTN();
			MessageSTC();
			message(HAND(play),play);	message(LOC()); //MDN();
		}
		return success;
				
		
	}
	
	

	
	boolean do_ability_on_monster(String play, int slot, String type, int tplayer, int tslot) {
		int player = player_named(play);
		if (getSlot(player,slot,type) == null) return false;
		if (getSlot(player,slot,type).dummy) return false; // is a card
		if (!getSlot(player,slot,type).ability) return false; // has  ability
		if (!getSlot(player,slot,type).targetm) return false; // ability targets monsters
		if (getSlot(player,slot,type).dizzy) return false; 
		CardmasterServerCard card = getSlot(player,slot,type);
		if (isActivatedConstant("MNOAB",player,card)) return false;
		int cardid = getSlot(player,slot,type).cardid;
		AbilityTarget target = new AbilityTarget(tplayer,tslot,"m");
		if (HasAbilityImmune(target.getCard())) return false;
		if (decoy_in_play() && !HasDecoying(target.getCard())) return false;
		int succode = 0;
		int speed = card.speed;
		
		if (speed != 0)
			succode = addNewAbility(ability_command[cardid],player,target,card,tslot,
						true, speed, "m", tplayer, cardid, -1, target.getCard().cardid);
		
		if (speed == 0){
			
			CardmasterServerCard self = getSlot(player,slot,type);
				if (getSlot(player,slot,type).GetStatusValue("facedown") >=1) {
				getSlot(player,slot,type).SetStatusValue("facedown",0);
MessageSTC();
	}	
		   	if(self.GetStatusValue("glue") >=1) {
					dizzy(self,true);
					self.SetStatusValue("glue",0);
					message("MES#" + self.name + " has been affected by the glue!");
					return false;
				}
			
				
			succode = runability(ability_command[cardid],player,target,getSlot(player,slot,type),tslot);
		resolve_drawcards();
			
		}						
		boolean success = true;
		if (succode == 0) success = false;
		if (speed == 0) {
		
			if (succode == 1) dizzy(getSlot(player,slot,type),true);	

			if (success) alltriggeredabilities(card,"TC" +type + player + "!");
			if (success) alltriggeredabilities(target.getCard(),"TCM@"+ tplayer);
			if (success) {
				if (card.typecode != null)
				triggerabilities(card,	target.getCard(),"TC" + card.typecode.toUpperCase() + "&"+ tplayer);
				}
			if(success) message("MES#" + play + " uses ability of " + getName(carddata[cardid]));
			MessageSTN();
			MessageSTC();
			message(HAND(play),play);	message(LOC()); //MDN();
		}
		return success;
				
		
	}
	boolean in_play(int cardid) {
		boolean found = false;
		for (int i=0;i<5;i++) {
			if (location.cardid == cardid) found = true;
			if (e1[i].cardid == cardid) found = true;
			if (e2[i].cardid == cardid) found = true;
			if (m1[i].cardid == cardid) found = true;
			if (m2[i].cardid == cardid) found = true;
		}
		return found;	
			
	}


	boolean cast_spell_grave(String play, int handslot, int tcard) {
		int player = player_named(play);
		if (player == 1 && waitcards1 > 0) return false;
		else if (player ==2 && waitcards2 > 0) return false;
		int cardid = 0;
		if (isActivatedConstant("PNOSP",player,null)) return false;
	//	System.out.println("check has card.");
		if (player == 1) { if (h1[handslot].dummy) return false; cardid = h1[handslot].cardid; }
		else if (player == 2) { if (h2[handslot].dummy) return false; cardid = h2[handslot].cardid; }
		else return false;
	//	System.out.println("check dummy");
		if (carddata[cardid].dummy) return false; // is a card
	//	System.out.println("check s");
		if (!carddata[cardid].typecode.equals("s")) return false;
	//	System.out.println("check targetm");
		if (!carddata[cardid].targetg) return false; // ability targets monsters
		if (carddata[tcard].typecode.equals("m") && !carddata[cardid].targetm) return false;
		if (carddata[tcard].typecode.equals("e") && !carddata[cardid].targete) return false;
		
	//	System.out.println("check sufficent mana");
		if (!manacheck(player,cardid)) return false;
		
		CardmasterServerCard card;
		if (player ==1) card = h1[handslot];
		else if (player==2) card = h2[handslot];
		else return false;
		if (card.markedForUse) return false;
		
		AbilityTarget target = new AbilityTarget(player,tcard);
		int success = 0;
		int speed = card.speed;
		card.markedForUse = true;
		if (speed != 0)
			success = addNewAbility(ability_intoplay[cardid],player,target,card,-1,false,speed,
								"g",player,cardid,handslot,tcard);
		else success = runability(ability_intoplay[cardid],player,target,card,-1);
		if(success !=0)message("SBC#" + carddata[cardid] + "#");
		if (speed == 0) {
		
			if(success != 0) { 
				
				
			    alltriggeredabilities(card,"TP" + player + "P");
		
				message("MES#" + play + " casts spell " + getName(carddata[cardid]));
				
				manapay(player,cardid); manapay_est(player,cardid);
				if (player ==1) to_graveyard(h1[handslot]);
				else if (player == 2) to_graveyard(h2[handslot]);
			resolve_drawcards();
			
				if (returntohand != null) 
				return_to_hand(returntohandplayer,returntohand);
			}
			MessageSTN();
			MessageSTC();
			message(HAND(play),play);	message(LOC()); //MDN();
		//	message(GRA());
		}
		if (success == 0) {
			card.markedForUse = false;
			return false;
			}
		else return true;
				
		
	}

	boolean cast_spell_on_monster(String play, int handslot, int tplayer, int tslot) {
		int player = player_named(play);
		
		if (player == 1 && waitcards1 > 0) return false;
		else if (player ==2 && waitcards2 > 0) return false;
		int cardid = 0;
		if (isActivatedConstant("PNOSP",player,null)) return false;
	//	System.out.println("check has card.");
		if (player == 1) {
			 if (h1[handslot].dummy) return false; 
			cardid = h1[handslot].cardid; }
		else if (player == 2) { 
			if (h2[handslot].dummy) return false; 
			cardid = h2[handslot].cardid; }
		else return false;
		//System.out.println("check dummy");
		if (carddata[cardid].dummy) return false; // is a card
		//System.out.println("check s");
		if (!carddata[cardid].typecode.equals("s")) return false;
		//System.out.println("check targetm");
		if (!carddata[cardid].targetm) return false; // ability targets monsters
		//System.out.println("check sufficent mana");
		if (!manacheck(player,cardid)) return false;
		
		
		AbilityTarget target = new AbilityTarget(tplayer,tslot,"m");
		if (HasSpellImmune(target.getCard())) return false;
		if (decoy_in_play() && !HasDecoying(target.getCard())) return false;
		CardmasterServerCard card;
		if (player ==1) card = h1[handslot];
		else if (player==2) card = h2[handslot];
		else return false;
		if (card.markedForUse) return false;
		
		card.markedForUse = true;
		int success = 0;
		int speed = card.speed;
		if (speed !=0)
			success = addNewAbility(ability_intoplay[cardid],player,target,card,tslot,false,speed,
							"m",tplayer,cardid,handslot,target.getCard().cardid);
		else 
		 success = runability(ability_intoplay[cardid],player,target,card,tslot);
		
		
		if (success !=0) message("SBC#" + carddata[cardid] + "#");
		
		
		if (speed == 0) {
			
			if(success != 0) { 
			 	alltriggeredabilities(card,"TP" + player + "P");
			 	alltriggeredabilities(target.getCard(),"TCM>" + tplayer);
				message("MES#" + play + " casts spell " + getName(carddata[cardid]));
				
				manapay(player,cardid); manapay_est(player,cardid);
				if (player ==1) to_graveyard(h1[handslot]);
				else if (player == 2) to_graveyard(h2[handslot]);
			resolve_drawcards();
		
			}
			MessageSTN();
			MessageSTC();
			message(HAND(play),play);	message(LOC()); //MDN();
		
		}
		if (success == 0) {
			card.markedForUse = false;
			
			return false;
			}
		else return true;
				
		
	}
	boolean cast_spell_on_effect(String play, int handslot, int tplayer, int tslot) {
		int player = player_named(play);
		int cardid = 0;
		if (player == 1 && waitcards1 > 0) return false;
		else if (player ==2 && waitcards2 > 0) return false;

		if (isActivatedConstant("PNOSP",player,null)) return false;


		if (player == 1) { if (h1[handslot].dummy) return false; cardid = h1[handslot].cardid; }
		else if (player == 2) { if (h2[handslot].dummy) return false; cardid = h2[handslot].cardid; }
		else return false;


		if (carddata[cardid].dummy) return false; // is a card


		if (!carddata[cardid].typecode.equals("s")) return false;

		if (!carddata[cardid].targete) return false; // ability targets monsters


		if (!manacheck(player,cardid)) return false;
		
		
		AbilityTarget target = new AbilityTarget(tplayer,tslot,"e");
			if (HasSpellImmune(target.getCard())) return false;
				CardmasterServerCard card;


		if (player ==1) card = h1[handslot];
		else if (player==2) card = h2[handslot];
		else return false;
		if (card.markedForUse) return false;
		
		card.markedForUse = true;
		int success = 0;
		int speed = card.speed;
		if (speed !=0)
			success = addNewAbility(ability_intoplay[cardid],player,target,card,tslot,false,speed,
							"e",tplayer,cardid,handslot,target.getCard().cardid);
		else
		 success = runability(ability_intoplay[cardid],player,target,card,tslot);
		
		
		if (success !=0) message("SBC#" + carddata[cardid] + "#");
		
		
		if (speed ==0) {
			
			if(success != 0) { 
				 alltriggeredabilities(card,"TP" + player + "P");
				 alltriggeredabilities(target.getCard(),"TCE>" + tplayer);
				message("MES#" + play + " casts spell " + getName(carddata[cardid]));
				//message("SBC#" + carddata[cardid] + "#");
				manapay(player,cardid);manapay_est(player,cardid); 
				if (player ==1) to_graveyard(h1[handslot]);
				else if (player == 2) to_graveyard(h2[handslot]);
				resolve_drawcards();
		
			}
			if (!skipturn) {
			
				MessageSTN();
				MessageSTC();
				message(HAND(play),play);	message(LOC()); //MDN();
			}
		}
		if (success == 0) {
			card.markedForUse = false;
			
			return false;
			}
		
		else return true; 
		
	}

	
		
	boolean cast_spell_on_slot(String play, int handslot, int tslot) {
		int player = player_named(play);
		int cardid = 0;
		if (player == 1 && waitcards1 > 0) return false;
		else if (player ==2 && waitcards2 > 0) return false;
		if (isActivatedConstant("PNOSP",player,null)) return false;
		if (player == 1) { if (h1[handslot].dummy) return false; cardid = h1[handslot].cardid; }
		else if (player == 2) { if (h2[handslot].dummy) return false; cardid = h2[handslot].cardid; }
		else return false;
		if (carddata[cardid].dummy) return false; // is a card
		if (!carddata[cardid].typecode.equals("s")) return false;
		if (!carddata[cardid].targets) return false; // ability targets monsters
		if (!manacheck(player,cardid)) return false;
		
		
	//	AbilityTarget target = new AbilityTarget(tplayer,tslot,"e");
		CardmasterServerCard card;
		if (player ==1) card = h1[handslot];
		else if (player==2) card = h2[handslot];
		else return false;
		if (card.markedForUse) return false;
		
		card.markedForUse = true;
		int success = 0;
		int speed = card.speed;
		if (speed != 0)
			success = addNewAbility(ability_intoplay[cardid],player,null,card,tslot,false,speed,
									"s",player,cardid,handslot,0);
									
		else success = runability(ability_intoplay[cardid],player,null,card,tslot);
		
		if (success !=0) message("SBC#" + carddata[cardid] + "#");
		if (speed ==0) {
			
			if(success!=0) { 
			 alltriggeredabilities(card,"TP" + player + "P");
				message("MES#" + play + " casts spell " + getName(carddata[cardid]));
				manapay(player,cardid); manapay_est(player,cardid);
				if (player ==1) to_graveyard(h1[handslot]);
				else if (player == 2) to_graveyard(h2[handslot]);
			resolve_drawcards();
		
			}
			MessageSTN();
			MessageSTC();
			message(HAND(play),play); 	message(LOC());//MDN();

		}
		if (success == 0) {
			card.markedForUse = false;
			
			return false;
			}
		else return true;
				
		
	}
	public int PowerInPlay(int player) {
		int allies = 0;
		for (int i=0;i<5;i++) {
		if (!getSlot(player,i,"m").dummy) {
			allies+=NumPower(getSlot(player,i,"m"));
		}
		if (!getSlot(player,i,"e").dummy) {
			allies+=NumPower(getSlot(player,i,"e"));
		}
		
		if (GetModifierCard(getSlot(player,i,"m"))!= null) {
			allies+=NumPower(GetModifierCard(getSlot(player,i,"m")));
		}
		
		if (GetModifierCard(getSlot(player,i,"e"))!= null) {
			allies+=NumPower(GetModifierCard(getSlot(player,i,"e")));
		}
		}
			if (!location.dummy) {
			allies+=NumPower(location);
		}
		return allies;
	}
	
	public int NumPower(CardmasterServerCard card) {
		int power = 0;
		if (card.flag("power1")) power++;
		if (card.flag("power2")) power+=2;
		if (card.flag("power3")) power+=3;
		if (card.flag("power4")) power+=4;
		return power;
	}
	/*
	public int PowerInPlay(int player) {
		int allies = 0;
		for (int i=0;i<5;i++) {
			if (!getSlot(player,i,"m").dummy) {
				if (getSlot(player,i,"m").flag("power1")) allies++;
				if (getSlot(player,i,"m").flag("power2")) allies+=2;
				if (getSlot(player,i,"m").flag("power3")) allies+=3;
				if (getSlot(player,i,"m").flag("power4")) allies+=4;
			}
			
			if (!getSlot(player,i,"e").dummy) {
				if (getSlot(player,i,"e").flag("power1")) allies++;
				if (getSlot(player,i,"e").flag("power2")) allies+=2;
				if (getSlot(player,i,"e").flag("power3")) allies+=3;
				if (getSlot(player,i,"e").flag("power4")) allies+=4;
			}
			
			if (GetModifierCard(getSlot(player,i,"m")) != null) {
			
			
				if (GetModifierCard(getSlot(player,i,"m")).flag("power1")) allies++;
				if (GetModifierCard(getSlot(player,i,"m")).flag("power2")) allies+=2;
				if (GetModifierCard(getSlot(player,i,"m")).flag("power3")) allies+=3;
				if (GetModifierCard(getSlot(player,i,"m")).flag("power4")) allies+=4;
			}
			
			if (GetModifierCard(getSlot(player,i,"e")) != null) {
			
			
				if (GetModifierCard(getSlot(player,i,"e")).flag("power1")) allies++;
				if (GetModifierCard(getSlot(player,i,"e")).flag("power2")) allies+=2;
				if (GetModifierCard(getSlot(player,i,"e")).flag("power3")) allies+=3;
				if (GetModifierCard(getSlot(player,i,"e")).flag("power4")) allies+=4;
				
				}
					
		}
		if (!location.dummy){
			if (location.flag("power3")) allies+=3;
			if (location.flag("power2")) allies+=2;
			if (location.flag("power4")) allies+=4;
				if (location.flag("power1")) allies++;
		}		
		return allies;
		
		
		
	}

*/
	public int AllyInPlay(int player) {
	
		int allies = 0;
		for (int i=0;i<5;i++) {
			if (!getSlot(player,i,"m").dummy) 
				if (HasAlly(getSlot(player,i,"m"))) allies++;
			if (!getSlot(player,i,"e").dummy) 
				if (HasAlly(getSlot(player,i,"e"))) allies++;
			if (GetModifierCard(getSlot(player,i,"m")) != null) 
			
				if (HasAlly(GetModifierCard(getSlot(player,i,"m")))) allies++;
			if (GetModifierCard(getSlot(player,i,"e")) != null) 
			
				if (HasAlly(GetModifierCard(getSlot(player,i,"e")))) allies++;
				
				
					
		}
		if (!location.dummy)
				if (HasAlly(location)) allies++;
		//	System.out.println("Player: " + player_named(player) + " HAS ALLY " + allies);		
		return allies;
		
		
	}



	boolean cast_spell_auto(String play, int handslot) {
		int player = player_named(play);
		int cardid = 0;
		if (player == 1 && waitcards1 > 0) return false;
		else if (player ==2 && waitcards2 > 0) return false;
		if (isActivatedConstant("PNOSP",player,null)) return false;
		if (player == 1) { if (h1[handslot].dummy) return false; cardid = h1[handslot].cardid; }
		else if (player == 2) { if (h2[handslot].dummy) return false; cardid = h2[handslot].cardid; }
		else return false;
		if (carddata[cardid].dummy) return false; // is a card
		if (!carddata[cardid].typecode.equals("s")) return false;
		if (!carddata[cardid].targeta) return false; // ability targets automatically
		if (!manacheck(player,cardid)) return false;
		
		
		CardmasterServerCard card;
		if (player ==1) card = h1[handslot];
		else if (player==2) card = h2[handslot];
		else return false;		
		if (card.markedForUse) return false;
		
		card.markedForUse = true;
		int success = 0;
		int speed = card.speed;
		if (speed !=0 )
			success = addNewAbility(ability_intoplay[cardid],player,null,card,-1,false, speed,
									"a",0,cardid,handslot,0);
		else
			 success = runability(ability_intoplay[cardid],player,null,card,-1);
		
		
		if (success !=0)message("SBC#" + carddata[cardid] + "#");
		
		if (speed ==0) {
		
		if(success != 0) { 
			 alltriggeredabilities(card,"TP" + player + "P");
				message("MES#" + play + " casts spell " + getName(carddata[cardid]));
				switchhands = false;
				manapay(player,cardid);manapay_est(player,cardid); 
				if (!switchhands) {
					
					if (player ==1) to_graveyard(h1[handslot]);
					else if (player == 2) to_graveyard(h2[handslot]);
					
				}else {
					if (player ==2) to_graveyard(h1[handslot]);
					else if (player == 1) to_graveyard(h2[handslot]);
				}
			resolve_drawcards();
		
			}
			MessageSTN();
			MessageSTC(); 
			message(HAND(play),play);	message(LOC());//MDN();
		}
		if (success == 0) {
			card.markedForUse = false;
			
			return false;
			}
		else return true;
				
		
	}

	boolean cast_spell_on_player(String play, int handslot, int tplayer) {
		int player = player_named(play);
		int cardid = 0;
		if (player == 1 && waitcards1 > 0) return false;
		else if (player ==2 && waitcards2 > 0) return false;
		if (isActivatedConstant("PNOSP",player,null)) return false;
		if (player == 1) { 
			if (h1[handslot].dummy) { //System.out.println("Not in hand 1"); 
			return false; }
			cardid = h1[handslot].cardid; 
		}
		else if (player == 2) { 
			if (h2[handslot].dummy) {
			return false; }
			cardid = h2[handslot].cardid; 
		}
		else return false;
		if (carddata[cardid].dummy) return false; // is a card
		if (!(carddata[cardid].typecode.equals("s"))) {
			return false;}
		if (!(carddata[cardid].targetp)) return false; // ability targets players
		if (!(manacheck(player,cardid))) return false;
		
		
		AbilityTarget target = new AbilityTarget(tplayer);
		CardmasterServerCard card;
		if (player ==1) card = h1[handslot];
		else if (player==2) card = h2[handslot];
		else return false;
		if (card.markedForUse) return false;
		
		card.markedForUse = true;
		int success = 0;
		int speed = card.speed;
		if (speed !=0 )
			success = addNewAbility(ability_intoplay[cardid],player,target,card,-1,false,speed,
						"p",tplayer,cardid,handslot,0);
		
		else  success = runability(ability_intoplay[cardid],player,target,card,-1);
		if (success !=0)	message("SBC#" + carddata[cardid] + "#");
		
		if (speed ==0) {
			
		//	System.out.println("Success: " + success);
			if(success!=0) { 
			 alltriggeredabilities(card,"TP" + player + "P");
				message("MES#" + play + " casts spell " + getName(carddata[cardid]));
			
				manapay(player,cardid);manapay_est(player,cardid); 
				if (player ==1) to_graveyard(h1[handslot]);
				else if (player == 2) to_graveyard(h2[handslot]);
			resolve_drawcards();
		
			}
			MessageSTN();
			MessageSTC();
			message(HAND(play),play);	message(LOC()); //MDN();
		}
		if (success == 0) {
			card.markedForUse = false;
			
			return false;
			}
		else return true;
				
		
	}
	void do_after_attack_abilities(int player) {

		for (int i=0;i<5;i++) {
			if (player==1) {
					int cardid = m1[i].cardid;
					if (ability_afterattack[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
							if (!endedonce && winner == 0) 	duelendedbycard = "After Attack ability of " + carddata[cardid].name;
								
							}
						 runability(ability_afterattack[cardid],player,null,m1[i],-1);
						 resolve_drawcards();
		
					}

					 cardid = e1[i].cardid;
					
					if (ability_afterattack[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "After Attack ability of " + carddata[cardid].name;
								
							}
					
						runability(ability_afterattack[cardid],player,null,e1[i],-1);
						resolve_drawcards();
		
					}
					
					
					cardid = dm1[i].cardid;
					if (ability_afterattack[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "After Attack ability of " + carddata[cardid].name;
								
							}
						 runability(ability_afterattack[cardid],player,null,dm1[i],-1);
						 resolve_drawcards();
		
					}

					 cardid = de1[i].cardid;
					
					if (ability_afterattack[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "After Attack ability of " + carddata[cardid].name;
								
							}
					
						runability(ability_afterattack[cardid],player,null,de1[i],-1);
						resolve_drawcards();
		
					}
					




			}
			if (player==2) {
				//if (!m2[i].dummy) {
					int cardid = m2[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_afterattack[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "After Attack ability of " + carddata[cardid].name;
								
							}
						
						 runability(ability_afterattack[cardid],player,null,m2[i],-1);
						resolve_drawcards();
		
					}

			//	}
				//if (!e2[i].dummy) {
					 cardid = e2[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_afterattack[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "After Attack ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability e" + i + " " 
						runability(ability_afterattack[cardid],player,null,e2[i],-1);
						resolve_drawcards();
					}

				//}	
				
				
				cardid = dm2[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_afterattack[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "After Attack ability of " + carddata[cardid].name;
								
							}
						
						 runability(ability_afterattack[cardid],player,null,dm2[i],-1);
						resolve_drawcards();
		
					}

			//	}
				//if (!e2[i].dummy) {
					 cardid = de2[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_afterattack[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "After Attack ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability e" + i + " " 
						runability(ability_afterattack[cardid],player,null,de2[i],-1);
						resolve_drawcards();
					}

						

			}
		}
		if (ability_afterattack[location.cardid].startsWith("START")) {
							if (carddata[location.cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "After Attack ability of " + carddata[location.cardid].name;
								
							}
			runability(ability_afterattack[location.cardid],player,null,location,-1);
		resolve_drawcards();
		
			
			
		}	
	}

	void do_attack_abilities(int player) {
		for (int i=0;i<5;i++) {
			if (player==1) {
				//if (!m1[i].dummy) {
					int cardid = m1[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_attack[cardid].startsWith("START")) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Attack ability of " + carddata[cardid].name;
								
							}
					//	System.out.println("running ability m" + i + " "
						 runability(ability_attack[cardid],player,null,m1[i],-1);
						 resolve_drawcards();
		
					}

			//	}
			//	if (!e1[i].dummy) {
					 cardid = e1[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_attack[cardid].startsWith("START")) {
						
							if (carddata[cardid].name != null) {
							if (!endedonce && winner == 0) 	duelendedbycard = "Attack ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability e" + i + " " 
						runability(ability_attack[cardid],player,null,e1[i],-1);
					resolve_drawcards();
		
					}
					
					cardid = dm1[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_attack[cardid].startsWith("START")) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Attack ability of " + carddata[cardid].name;
								
							}
					//	System.out.println("running ability m" + i + " "
						 runability(ability_attack[cardid],player,null,dm1[i],-1);
						 resolve_drawcards();
		
					}

			//	}
			//	if (!e1[i].dummy) {
					 cardid = de1[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_attack[cardid].startsWith("START")) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Attack ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability e" + i + " " 
						runability(ability_attack[cardid],player,null,de1[i],-1);
					resolve_drawcards();
		
					}

			//	}			

			}
			if (player==2) {
			//	if (!m2[i].dummy) {
					int cardid = m2[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_attack[cardid].startsWith("START")) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Attack ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability m" + i + " " 
						 runability(ability_attack[cardid],player,null,m2[i],-1);
						resolve_drawcards();
		
					}

				//}
			//	if (!e2[i].dummy) {
					 cardid = e2[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_attack[cardid].startsWith("START")) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Attack ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability e" + i + " " 
						runability(ability_attack[cardid],player,null,e2[i],-1);
						 resolve_drawcards();
		
					}
					
					
					cardid = dm2[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_attack[cardid].startsWith("START")) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Attack ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability m" + i + " " 
						 runability(ability_attack[cardid],player,null,dm2[i],-1);
						resolve_drawcards();
		
					}

				//}
			//	if (!e2[i].dummy) {
					 cardid = de2[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_attack[cardid].startsWith("START")) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Attack ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability e" + i + " " 
						runability(ability_attack[cardid],player,null,de2[i],-1);
						 resolve_drawcards();
		
					}

			//	}			

			}
		}
		if (ability_attack[location.cardid].startsWith("START")) {
						
							if (carddata[location.cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Attack ability of " + carddata[location.cardid].name;
								
							}
			runability(ability_attack[location.cardid],player,null,location,-1);
			resolve_drawcards();
		
			
			
		}	
	}

	void do_defend_abilities(int player) {
		for (int i=0;i<5;i++) {
			if (player==1) {
		//		if (!m1[i].dummy) {
					int cardid = m1[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_defend[cardid].startsWith("START") && !isActivatedConstant("MNDEF",player,m1[i])) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Defence ability of " + carddata[cardid].name;
								
							}
					//	System.out.println("running ability m" + i + " "
						 runability(ability_defend[cardid],player,null,m1[i],-1);
						resolve_drawcards();
		
					}

			//	}
			//	if (!e1[i].dummy) {
					 cardid = e1[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_defend[cardid].startsWith("START")&& !isActivatedConstant("MNDEF",player,e1[i])) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Defence ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability e" + i + " " 
						runability(ability_defend[cardid],player,null,e1[i],-1);
						resolve_drawcards();
		
					}
				cardid = dm1[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_defend[cardid].startsWith("START") && !isActivatedConstant("MNDEF",player,m1[i])) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Defence ability of " + carddata[cardid].name;
								
							}
					//	System.out.println("running ability m" + i + " "
						 runability(ability_defend[cardid],player,null,dm1[i],-1);
						resolve_drawcards();
		
					}

			//	}
			//	if (!e1[i].dummy) {
					 cardid = de1[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_defend[cardid].startsWith("START")&& !isActivatedConstant("MNDEF",player,e1[i])) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Defence ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability e" + i + " " 
						runability(ability_defend[cardid],player,null,de1[i],-1);
						resolve_drawcards();
		
					}
			//	}			

			}
			if (player==2) {
			//	if (!m2[i].dummy) {
					int cardid = m2[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_defend[cardid].startsWith("START")&& !isActivatedConstant("MNDEF",player,m2[i])) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Defence ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability m" + i + " " 
						 runability(ability_defend[cardid],player,null,m2[i],-1);
						resolve_drawcards();
		
					}

			//	}
			//	if (!e2[i].dummy) {
					 cardid = e2[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_defend[cardid].startsWith("START")&& !isActivatedConstant("MNDEF",player,e2[i])) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Defence ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability e" + i + " " 
						runability(ability_defend[cardid],player,null,e2[i],-1);
						resolve_drawcards();
		
					}
					cardid = dm2[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_defend[cardid].startsWith("START")&& !isActivatedConstant("MNDEF",player,m2[i])) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Defence ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability m" + i + " " 
						 runability(ability_defend[cardid],player,null,dm2[i],-1);
						resolve_drawcards();
		
					}

			//	}
			//	if (!e2[i].dummy) {
					 cardid = de2[i].cardid;
					//System.out.println("Card ability: " + ability_startturn[cardid]);
					if (ability_defend[cardid].startsWith("START")&& !isActivatedConstant("MNDEF",player,e2[i])) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Defence ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability e" + i + " " 
						runability(ability_defend[cardid],player,null,de2[i],-1);
						resolve_drawcards();
		
					}

			//	}			

			}
		}
		if (ability_defend[location.cardid].startsWith("START")) {
						
							if (carddata[location.cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Defence ability of " + carddata[location.cardid].name;
								
							}
			runability(ability_defend[location.cardid],player,null,location,-1);
			resolve_drawcards();
		
			
			
		}	
	}
	void do_begin_turn_abilities(int player) {
		for (int i=0;i<5;i++) {
			if (player==1) {
					int cardid = m1[i].cardid;
					if (ability_startturn[cardid].startsWith("START")) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[cardid].name;
								
							}
											
						
						 runability(ability_startturn[cardid],player,null,m1[i],-1);
						resolve_drawcards();
		
					}

			//if (!noeffects) {
			
					cardid = e1[i].cardid;
					if (ability_startturn[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[cardid].name;
								
							}
						runability(ability_startturn[cardid],player,null,e1[i],-1);
						resolve_drawcards();
		
					}
					cardid = dm1[i].cardid;
					if (ability_startturn[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[cardid].name;
								
							}
						 runability(ability_startturn[cardid],player,null,dm1[i],-1);
						resolve_drawcards();
		
					}

			//if (!noeffects) {
			
					cardid = de1[i].cardid;
					if (ability_startturn[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[cardid].name;
								
							}
						runability(ability_startturn[cardid],player,null,de1[i],-1);
						resolve_drawcards();
		
					}
		//	}
			
			}
			if (player==2) {
					int cardid = m2[i].cardid;
					if (ability_startturn[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[cardid].name;
								
							}
						 runability(ability_startturn[cardid],player,null,m2[i],-1);
						resolve_drawcards();
		
					}

					 cardid = e2[i].cardid;
			//	if (!noeffects) {
					if (ability_startturn[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[cardid].name;
								
							}
						runability(ability_startturn[cardid],player,null,e2[i],-1);
						 resolve_drawcards();
		
					}
					cardid = dm2[i].cardid;
					if (ability_startturn[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[cardid].name;
								
							}
						 runability(ability_startturn[cardid],player,null,dm2[i],-1);
						resolve_drawcards();
		
					}

					 cardid = de2[i].cardid;
			//	if (!noeffects) {
					if (ability_startturn[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[cardid].name;
								
							}
						runability(ability_startturn[cardid],player,null,de2[i],-1);
						 resolve_drawcards();
		
					}
			//	}


		

			}
		}
		if (ability_startturn[location.cardid].startsWith("START")) {
				if (carddata[location.cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[location.cardid].name;
								
							}
			runability(ability_startturn[location.cardid],player,null,location,-1);
		resolve_drawcards();
		
			
			
		}	
	}
	
		void do_begin_turn_abilities_2(int player) {
		for (int i=0;i<5;i++) {
			if (player==1) {
					int cardid = m1[i].cardid;
					if (ability_defend[cardid].startsWith("DRAWPH")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[cardid].name;
								
							}
						 runability(ability_defend[cardid],player,null,m1[i],-1);
						resolve_drawcards();
		
					}


					cardid = e1[i].cardid;
					if (ability_defend[cardid].startsWith("DRAWPH")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[cardid].name;
								
							}
						runability(ability_defend[cardid],player,null,e1[i],-1);
						resolve_drawcards();
		
					}
					
					cardid = dm1[i].cardid;
					if (ability_defend[cardid].startsWith("DRAWPH")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[cardid].name;
								
							}
						 runability(ability_defend[cardid],player,null,dm1[i],-1);
						resolve_drawcards();
		
					}


					cardid = de1[i].cardid;
					if (ability_defend[cardid].startsWith("DRAWPH")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[cardid].name;
								
							}
						runability(ability_defend[cardid],player,null,de1[i],-1);
						resolve_drawcards();
		
					}

			
			}
			if (player==2) {
					int cardid = m2[i].cardid;
					if (ability_defend[cardid].startsWith("DRAWPH")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[cardid].name;
								
							}
						 runability(ability_defend[cardid],player,null,m2[i],-1);
						resolve_drawcards();
		
					}

					 cardid = e2[i].cardid;
					if (ability_defend[cardid].startsWith("DRAWPH")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[cardid].name;
								
							}
						runability(ability_defend[cardid],player,null,e2[i],-1);
						 resolve_drawcards();
		
					}
					 cardid = dm2[i].cardid;
					if (ability_defend[cardid].startsWith("DRAWPH")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[cardid].name;
								
							}
						 runability(ability_defend[cardid],player,null,dm2[i],-1);
						resolve_drawcards();
		
					}

					 cardid = de2[i].cardid;
					if (ability_defend[cardid].startsWith("DRAWPH")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[cardid].name;
								
							}
						runability(ability_defend[cardid],player,null,de2[i],-1);
						 resolve_drawcards();
		
					}



		

			}
		}
		if (ability_defend[location.cardid].startsWith("DRAWPH")) {
							if (carddata[location.cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Draw Phase ability of " + carddata[location.cardid].name;
								
							}
			runability(ability_defend[location.cardid],player,null,location,-1);
		resolve_drawcards();
		
			
			
		}	
	}
	
	void do_defer_abilities(int player) {
		for (int i=0;i<5;i++) {
			if (player==1) {
					int cardid = m1[i].cardid;
					if (ability_startturn[cardid].startsWith("DEFER")) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Maintenance ability of " + carddata[cardid].name;
								
							}
						 runability(ability_startturn[cardid],player,null,m1[i],-1);
						 resolve_drawcards();
					}


					cardid = e1[i].cardid;
					if (ability_startturn[cardid].startsWith("DEFER")) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Maintenance ability of " + carddata[cardid].name;
								
							}
						runability(ability_startturn[cardid],player,null,e1[i],-1);
						resolve_drawcards();
		
					}	
					
					
					cardid = dm1[i].cardid;
					if (ability_startturn[cardid].startsWith("DEFER")) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Maintenance ability of " + carddata[cardid].name;
								
							}
						 runability(ability_startturn[cardid],player,null,dm1[i],-1);
						 resolve_drawcards();
					}


					cardid = de1[i].cardid;
					if (ability_startturn[cardid].startsWith("DEFER")) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Maintenance ability of " + carddata[cardid].name;
								
							}
						runability(ability_startturn[cardid],player,null,de1[i],-1);
						resolve_drawcards();
		
					}				
				
				
			}
			
			if (player ==2) {
					 int cardid = m2[i].cardid;
					if (ability_startturn[cardid].startsWith("DEFER")) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Maintenance ability of " + carddata[cardid].name;
								
							}
						 runability(ability_startturn[cardid],player,null,m2[i],-1);
						 resolve_drawcards();
		
					}

					 cardid = e2[i].cardid;
					if (ability_startturn[cardid].startsWith("DEFER")) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Maintenance ability of " + carddata[cardid].name;
								
							}
						runability(ability_startturn[cardid],player,null,e2[i],-1);
						 resolve_drawcards();
	
		
					}	
					
					cardid = dm2[i].cardid;
					if (ability_startturn[cardid].startsWith("DEFER")) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Maintenance ability of " + carddata[cardid].name;
								
							}
						 runability(ability_startturn[cardid],player,null,dm2[i],-1);
						 resolve_drawcards();
		
					}

					 cardid = de2[i].cardid;
					if (ability_startturn[cardid].startsWith("DEFER")) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Maintenance ability of " + carddata[cardid].name;
								
							}
						runability(ability_startturn[cardid],player,null,de2[i],-1);
						 resolve_drawcards();
	
		
					}				
				
				
			}
			
		}
		if (ability_startturn[location.cardid].startsWith("DEFER")) {
						
							if (carddata[location.cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Maintenance ability of " + carddata[location.cardid].name;
								
							}
			runability(ability_startturn[location.cardid],player,null,location,-1);
			resolve_drawcards();
		
			
			
		}
		
	}
	void do_end_game_abilities() {
		for (int i=0;i<5;i++) {
			int cardid = m1[1].cardid;
			if (ability_afterattack[cardid].startsWith("ENDGM")) {
				runability(ability_endturn[cardid],1,null,m1[i],-1);
			}	
			cardid = m2[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDGM")) {
			runability(ability_endturn[cardid],2,null,m2[i],-1);
			}
			cardid = e1[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDGM")) {
			runability(ability_endturn[cardid],1,null,e1[i],-1);
			}
			cardid = e2[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDGM")) {
			runability(ability_endturn[cardid],2,null,m2[i],-1);
			}
			
			
			
			if (dm1 == null) {	dm1 = new CardmasterServerCard[5];
			dm2 = new CardmasterServerCard[5];
			de1 = new CardmasterServerCard[5];
			de2 = new CardmasterServerCard[5];
			
			for (int k=0;k<5;k++) {
				dm1[k] = new CardmasterServerCard();
				dm2[k] = new CardmasterServerCard();
				de1[k] = new CardmasterServerCard();
				de2[k] = new CardmasterServerCard();
				
			}
			
			}


			
			
			
			cardid = dm1[1].cardid;
			if (ability_afterattack[cardid].startsWith("ENDGM")) {
				runability(ability_endturn[cardid],1,null,dm1[i],-1);
			}	
			cardid = dm2[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDGM")) {
			runability(ability_endturn[cardid],2,null,dm2[i],-1);
			}
			cardid = de1[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDGM")) {
			runability(ability_endturn[cardid],1,null,de1[i],-1);
			}
			cardid = de2[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDGM")) {
			runability(ability_endturn[cardid],2,null,dm2[i],-1);
			}
		
		}
			resolve_drawcards();
	}
	
	
	void do_lose_game_abilities(int player) {
		if (player == 1) 
		for (int i=0;i<5;i++) {
			
			int cardid = m1[1].cardid;
			if (ability_afterattack[cardid].startsWith("ENDLO")) {
				runability(ability_endturn[cardid],1,null,m1[i],-1);
			}	
				cardid = e1[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDLO")) {
			runability(ability_endturn[cardid],1,null,e1[i],-1);
			}
		cardid = dm1[1].cardid;
			if (ability_afterattack[cardid].startsWith("ENDLO")) {
				runability(ability_endturn[cardid],1,null,dm1[i],-1);
			}	
				cardid = de1[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDLO")) {
			runability(ability_endturn[cardid],1,null,de1[i],-1);
			}
		
		
		
		
		}
		if (player == 2) 
		for (int i=0;i<5;i++) {
		
			int cardid = m2[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDLO")) {
			runability(ability_endturn[cardid],2,null,m2[i],-1);
			}
				cardid = e2[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDLO")) {
			runability(ability_endturn[cardid],2,null,m2[i],-1);
			}
			
			cardid = dm2[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDLO")) {
			runability(ability_endturn[cardid],2,null,dm2[i],-1);
			}
				cardid = de2[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDLO")) {
			runability(ability_endturn[cardid],2,null,de2[i],-1);
			}
			
		}
		resolve_drawcards();	
	}
	
	
	
		void do_win_game_abilities(int player) {
		if (player == 1) 
		for (int i=0;i<5;i++) {
			
			int cardid = m1[1].cardid;
			if (ability_afterattack[cardid].startsWith("ENDWI")) {
				runability(ability_endturn[cardid],1,null,m1[i],-1);
			}	
				cardid = e1[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDWI")) {
			runability(ability_endturn[cardid],1,null,e1[i],-1);
			}
			
			cardid = dm1[1].cardid;
			if (ability_afterattack[cardid].startsWith("ENDWI")) {
				runability(ability_endturn[cardid],1,null,dm1[i],-1);
			}	
				cardid = de1[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDWI")) {
			runability(ability_endturn[cardid],1,null,de1[i],-1);
			}
		
		
		
		
		}
		if (player == 2) 
		for (int i=0;i<5;i++) {
		
int			cardid = m2[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDWI")) {
			runability(ability_endturn[cardid],2,null,m2[i],-1);
			}
				cardid = e2[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDWI")) {
			runability(ability_endturn[cardid],2,null,m2[i],-1);
			}
				cardid = dm2[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDWI")) {
			runability(ability_endturn[cardid],2,null,dm2[i],-1);
			}
				cardid = de2[i].cardid;
			if (ability_afterattack[cardid].startsWith("ENDWI")) {
			runability(ability_endturn[cardid],2,null,dm2[i],-1);
			}
			
		}
		resolve_drawcards();	
	}
	
	void do_end_turn_abilities(int player) {
		for (int i=0;i<5;i++) {
			if (player==1) {
			//	if (!m1[i].dummy) {
					int cardid = m1[i].cardid;
					//System.out.println("Card ability: " + ability_endturn[cardid]);
					if (ability_endturn[cardid].startsWith("START")) {
						
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Cleanup ability of " + carddata[cardid].name;
								
							}
					//	System.out.println("running ability m" + i + " "
						 runability(ability_endturn[cardid],player,null,m1[i],-1);
						resolve_drawcards();
		
					}

			//	}
			//	if (!e1[i].dummy) {
					cardid = e1[i].cardid;
					//System.out.println("Card ability: " + ability_endturn[cardid]);
					if (ability_endturn[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Cleanup ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability e" + i + " " 
						runability(ability_endturn[cardid],player,null,e1[i],-1);
						 resolve_drawcards();
					}
					
					
					cardid = dm1[i].cardid;
					//System.out.println("Card ability: " + ability_endturn[cardid]);
					if (ability_endturn[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Cleanup ability of " + carddata[cardid].name;
								
							}
					//	System.out.println("running ability m" + i + " "
						 runability(ability_endturn[cardid],player,null,dm1[i],-1);
						resolve_drawcards();
		
					}

			//	}
			//	if (!e1[i].dummy) {
					cardid = de1[i].cardid;
					//System.out.println("Card ability: " + ability_endturn[cardid]);
					if (ability_endturn[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Cleanup ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability e" + i + " " 
						runability(ability_endturn[cardid],player,null,de1[i],-1);
						 resolve_drawcards();
					}


			//	}			

			}
			if (player==2) {
			//	if (!m2[i].dummy) {
					int cardid = m2[i].cardid;
					//System.out.println("Card ability: " + ability_endturn[cardid]);
					if (ability_endturn[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Cleanup ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability m" + i + " " 
						 runability(ability_endturn[cardid],player,null,m2[i],-1);
						resolve_drawcards();
		
					}

			//	}
			//	if (!e2[i].dummy) {
					 cardid = e2[i].cardid;
					//System.out.println("Card ability: " + ability_endturn[cardid]);
					if (ability_endturn[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Cleanup ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability e" + i + " " 
						runability(ability_endturn[cardid],player,null,e2[i],-1);
						resolve_drawcards();
		
					}
					
					cardid = dm2[i].cardid;
					//System.out.println("Card ability: " + ability_endturn[cardid]);
					if (ability_endturn[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Cleanup ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability m" + i + " " 
						 runability(ability_endturn[cardid],player,null,dm2[i],-1);
						resolve_drawcards();
		
					}

			//	}
			//	if (!e2[i].dummy) {
					 cardid = de2[i].cardid;
					//System.out.println("Card ability: " + ability_endturn[cardid]);
					if (ability_endturn[cardid].startsWith("START")) {
							if (carddata[cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Cleanup ability of " + carddata[cardid].name;
								
							}
						//System.out.println("running ability e" + i + " " 
						runability(ability_endturn[cardid],player,null,de2[i],-1);
						resolve_drawcards();
		
					}


			//	}			

			}
		}
		if (ability_endturn[location.cardid].startsWith("START")) {
							if (carddata[location.cardid].name != null) {
								if (!endedonce && winner == 0) duelendedbycard = "Cleanup ability of " + carddata[location.cardid].name;
								
							}
			runability(ability_endturn[location.cardid],player,null,location,-1);
		resolve_drawcards();
		
			
			
		}	
	}
	void sac_card(String playername, String type, int slot) {
		if (abilities.length != 0) { return; }
		if (phase != PHASE_SAC) return;
		int num = player_named(playername);
		
		
		if (num == 1 && playerphase == 1) {
			if (waitcards1 > 0 ) return;
			//System.out.println("1 is discarding.  Type " + type + " slot " + slot);
			int sd = 0;
			int sl = 0;
			int sg = 0;
			CardmasterServerCard card = new CardmasterServerCard();
			if (type.equals("m")){
				card = m1[slot];

			} else if (type.equals("e")) {
				card = e1[slot];
				
			}
		
			if (card.dummy) return;
			if (HasCannotSac(card)) return;
			
				if (card.GetStatusValue("facedown") >=1) {
				card.SetStatusValue("facedown",0);
				
					return_card_info(card.cardid,playername);
				return_card_info(card.cardid,"$obs$");

	}	
			
			
			message("MES#" + playername + " sacrifices " + card.name);
			sd =  (int)getModifiedValue(card,"dsa","A",1);
			sg =(int)getModifiedValue(card,"gsa","A",1);
			sl = (int)getModifiedValue(card,"lsa","A",1);
			destroy_card(num,type,slot);
				
				
				
			
			p1D+=sd;
			p1G+=sg;
			p1L+=sl;
			p1Du+=sd;
			p1Gu+=sg;
			p1Lu+=sl;
			
			
			
			
		}	
		else if (num == 2 && playerphase == 2) {
			if (waitcards2 > 0 ) return;
			int sd = 0;
			int sl = 0;
			int sg = 0;
		//	System.out.println("2 is  Type " + type + " slot " + slot);
			CardmasterServerCard card = new CardmasterServerCard();
			if (type.equals("m")){
				card = m2[slot];

			} else if (type.equals("e")) {
				card = e2[slot];
				
			}
			if (card.dummy) return;
			if (HasCannotSac(card)) return;
				if (card.GetStatusValue("facedown") >=1) {
				card.SetStatusValue("facedown",0);

	}	
	
	if (card.GetStatusValue("facedown") >=1) {
				card.SetStatusValue("facedown",0);
				}
			message("MES#" + playername + " sacrifices " + card.name);
			//System.out.println("Killin that Card, Yo!");
			sd = (int) getModifiedValue(card,"dsa","A",2);
			sg =(int)getModifiedValue(card,"gsa","A",2);
			sl = (int)getModifiedValue(card,"lsa","A",2);
			destroy_card(num,type,slot);
				
				
				
			
			p2D+=sd;
			p2G+=sg;
			p2L+=sl;
			p2Du+=sd;
			p2Gu+=sg;
			p2Lu+=sl;
			
			
			
		}		
		
		
		
	}
	
	void discard_hand(int player) {
		CardmasterServerCard hand[];
		if (player == 1) hand = h1;
		else if (player ==2) hand = h2;
		else return; 
		for (int i=0;i<8;i++) {
			if (!hand[i].dummy) {
				alltriggeredabilities(hand[i],"TP" + player + "A");
				
				
				
				
				
				leaveplay_ability(player,  i,"h", "DISCS");
				to_graveyard(hand[i]);
				
				
			}
			
			
			
		}
		if (player == 1) waitcards1 = 0;
		if (player == 2) waitcards2 = 0;
		if (!skipturn) {
		
			MessageSTC();
			message(HAND(player_named(player)),player_named(player));	message(LOC()); 
		}
	}


	int discard_random(int player) {
System.out.println("DISCARD RANDOM : " + player);
		CardmasterServerCard hand[];
		if (player == 1) hand = h1;
		else if (player ==2) hand = h2;
		else {
System.out.println("DISCARD RANDOM : PLAYER BROKE");
			return -1; 
		}
		if (numCardsInHand(player) <= 0){
			
System.out.println("DISCARD RANDOM : NO HAND");
			 return -2;
		}
		int tries = 0;
		while (tries<1000) {
			tries++;
			int randchoice = random.nextInt(8);
			System.out.println("CHECKING HAND ID: " + randchoice);
			if (hand[randchoice].dummy) continue;
			System.out.println("DISCARDING " + hand[randchoice].name);
			alltriggeredabilities(hand[randchoice],"TP" + player + "A");
					leaveplay_ability(player,  randchoice,"h", "DISCS");
					to_graveyard(hand[randchoice]);
					tries = 99999;
					break;
		}
		
		
		
		if (tries != 99999) {
			
System.out.println("FOUND NO CARDS");
			return -3;
		}
		
		if (player == 1) waitcards1 = 0;
		if (player == 2) waitcards2 = 0;
		if (!skipturn) {
		
			MessageSTC();
			message(HAND(player_named(player)),player_named(player));	message(LOC()); 
		}
		return 1;
	}

	
	void discard_card(String playername, int handslot) {
		if (CardmasterData.DEBUGMODE) System.out.println("Discard: " + playername + " " + handslot);
		if (playername.equals(name1)) {
			if (waitcards1 >= 1) {
				if (!h1[handslot].dummy) {
					alltriggeredabilities(h1[handslot],"TP1A");
					leaveplay_ability(1,  handslot,"h", "DISCS");
					to_graveyard(h1[handslot]);
					waitcards1--;
					boolean drawn = false;
					if (waitdraw1 > 0) { draw_card_safe(1); waitdraw1--; drawn=true;}
					if (waitdraw1 > 0) { draw_card(1); waitdraw1--; drawn=true;}
					
					if (!drawn && waitdrawopp1 > 0 ) { draw_card_safe_opp(1); waitdrawopp1--;}
					
					if (!drawn && waitdrawopp1 > 0 ) { draw_card_opp(1); waitdrawopp1--;}
					
					message("MDN#" + 1 + "#" + waitcards1 + "#");
					if (waitcards1 > numCardsInHand(1)) waitcards1 = numCardsInHand(1);
					
					MessageSTC();
		message(HAND(playername),playername);	message(LOC()); //MDN();
					message("DSS#1#" + waitcards1 + "#");
					
					if (waitcards1 <= 0 && waitcards2 <= 0) discardwait = false;
					if (p1abilityresolve && p2abilityresolve) { runWaitingAbilities(); message("ABR#" + bToI(p1abilityresolve) + "#" + bToI(p2abilityresolve) + "#" + abilities.length + "#");
					}
					//message("MDN#1#" + waitcards1 + "#");
					
				}	
			}	
	
		}
		else if (playername.equals(name2)) {
			if (waitcards2 >= 1) {
				if (!h2[handslot].dummy) {
					alltriggeredabilities(h2[handslot],"TP2A");
					leaveplay_ability(2, handslot, "h", "DISCS");
					to_graveyard(h2[handslot]);
		
		
					waitcards2--;
		
		
					boolean drawn = false;
					if (waitdraw2 > 0) { draw_card_safe(2); waitdraw2--; drawn=true;}
					if (waitdraw2 > 0) { draw_card(2); waitdraw2--; drawn=true;}
					
					if (!drawn && waitdrawopp2 > 0 ) { draw_card_safe_opp(2); waitdrawopp2--;}
					
					if (!drawn && waitdrawopp2 > 0 ) { draw_card_opp(2); waitdrawopp2--;}
					if (waitcards2 > numCardsInHand(2)) waitcards2 = numCardsInHand(2);
					//if (waitcards2 ==0 && numCardsInHand(2) + waitdraw2 <= 8) while (waitdraw2 > 0) { draw_card_safe(2); waitdraw2--;}
		
		
		
		message("MDN#" + 2+ "#" + waitcards2 + "#");
		
					MessageSTC();
		message(HAND(playername),playername);	message(LOC()); //MDN();
					message("DSS#2#" + waitcards2 + "#");
					//message("MDN#2#" + waitcards2 + "#");
					if (waitcards1 <= 0 && waitcards2 <= 0) discardwait = false;
					if (p1abilityresolve && p2abilityresolve) { runWaitingAbilities(); message("ABR#" + bToI(p1abilityresolve) + "#" + bToI(p2abilityresolve) + "#" + abilities.length + "#");	}
					
				}	
			
			
			}
	
		}
	}
	
	CardmasterServerCard getSlot(int player, int slot, String type) {
		if (type == null) return null;
		if (player ==0 && slot == 0 && type.equals("l")) return location;
		
		if ((player == 1) && (type.equals("h"))) return h1[slot];
		if ((player == 2) && (type.equals("h"))) return h2[slot];
		
		if ((player > 2) || (player < 1) || (slot < 0 )|| (slot > 4) )return null;
		if ((player == 1) && (type.equals("m"))) return m1[slot];
		if ((player == 2) && (type.equals("m"))) return m2[slot];
		if ((player == 1) && (type.equals("e"))) return e1[slot];
		if ((player == 2) && (type.equals("e"))) return e2[slot];
		if ((player == 1) && (type.equals("de"))) return de1[slot];
		if ((player == 2) && (type.equals("de"))) return de2[slot];
		if ((player == 1) && (type.equals("dm"))) return dm1[slot];
		if ((player == 2) && (type.equals("dm"))) return dm2[slot];
		if (type.equals("l")) return location;
	
						
		return null;
		
	}
	int numCardsInHandExcept(int player, CardmasterServerCard card) {
		int numcards = numCardsInHand(player);
		if (isInPlayerHand(player,card) && !card.markedForUse) numcards = numcards -1;
		return numcards;
	}
	boolean isInPlayerHand(int player, CardmasterServerCard card) {
		CardmasterServerCard[] hand;
		if (player==1) hand = h1;
		else if (player ==2) hand=h2;
		else return false;
		for (int i=0;i<8;i++)
			if (hand[i]==card) return true;
		return false;
	}
	boolean isInPlay(int cardid) {
		for (int i =0;i<5;i++) {
			if (!(m1[i].dummy)) if (m1[i].cardid == cardid) return true;
			if (!(m2[i].dummy)) if (m2[i].cardid == cardid) return true;
			if (!(e1[i].dummy)) if (e1[i].cardid == cardid) return true;
			if (!(e2[i].dummy)) if (e2[i].cardid == cardid) return true;
			
			if (!(m1[i].dummy)) if (m1[i].name.equals(carddata[cardid].name)) return true;
			if (!(m2[i].dummy)) if (m2[i].name.equals((carddata[cardid].name))) return true;
			if (!(e1[i].dummy)) if (e1[i].name.equals((carddata[cardid].name))) return true;
			if (!(e2[i].dummy)) if (e2[i].name.equals((carddata[cardid].name))) return true;
			
			if (!(m1[i].dummy)) if (m1[i].flag("aka:"+(carddata[cardid]).name)) return true;
			if (!(m2[i].dummy)) if (m2[i].flag("aka:"+(carddata[cardid]).name)) return true;
			
			if (!(m1[i].dummy)) if (carddata[cardid].flag("aka:"+m1[i].name)) return true;
			if (!(m2[i].dummy)) if (carddata[cardid].flag("aka:"+m2[i].name)) return true;
			
			
		}	
		return false;
	}
   // PLAY AN EFFECT CARD
	int play_effect(String playername, int cardid,int slot, int handslot) {
		if (abilities.length != 0) { return -1000; }
		int player = 1;
		//System.out.println(playername + " attempts to play " + cardid + " on " + slot);
	
		if (playername.equals(name2)) player = 2;
		
		if (isActivatedConstant("PNOEF",player,null)) return -1;
		
		if (!getSlot(player, slot, "e").dummy) return -1;
		if (getCard(cardid).dummy) return -20;
		if (!getCard(cardid).typecode.equals("e")) return -21;
		if ((playerphase != player) || (phase != PHASE_PLAY)) return -2;  
		if (!manacheck(player,cardid)) return -3;
		if (isUnique(carddata[cardid]) && isInPlay(cardid)) return -98;
		
		if (player == 1) { if (waitcards1 > 0 ) return -52;
			if (h1[handslot].cardid == cardid) {
				manapay(player,cardid);
				
				h1[handslot].copydata(new CardmasterServerCard());	
				tempname = getCard(cardid).name;
				int success= create_effect(player, slot, cardid);
				
				if (success == 1) {
					if (getSlot(player,slot,"e").flag("face") || nextefacedown == true || nextentfacedown == true ||	(isActivatedConstant("PFCDE",player,null)) )  {
						
						nextefacedown = false;
						nextentfacedown = false;
						getSlot(player,slot,"e").SetStatusValue("facedown",1);
						
					}
					else {
						manapay_est(player,cardid);
					}
					message("MES#" + name1 + " plays " + getName(getSlot(player,slot,"e"),true));
					alltriggeredabilities(getSlot(player,slot,"e"),"TP1E");
					//alltriggeredabilities(getSlot(player,slot,"e"),"TCE*");
				}
				return success;
				
			}
		}
		if (player == 2) { if (waitcards2 > 0 ) return -52;
			if (h2[handslot].cardid == cardid) {
				manapay(player,cardid);
			
				
				h2[handslot].copydata(new CardmasterServerCard());
					tempname = getCard(cardid).name;
				int success= create_effect(player, slot, cardid);
				if (success == 1) { 
					alltriggeredabilities(getSlot(player,slot,"e"),"TP2E");
				//	alltriggeredabilities(getSlot(player,slot,"e"),"TCE*");
					if (getSlot(player,slot,"e").flag("face") || nextefacedown == true || nextentfacedown == true ||	(isActivatedConstant("PFCDM",player,null)) ) {
						nextentfacedown = false;
						nextefacedown = false;
						getSlot(player,slot,"e").SetStatusValue("facedown",1);
						
					}
					else {
						manapay_est(player,cardid);
					}
				 message("MES#" + name2 + " plays " + getName(getSlot(player,slot,"e"),true));
				 }
				return success;
			}
		}	
		return -4;
	}
	
	int play_modifier(String playername, int cardid, int slot, int tplayer, String ttype, int handslot) {
		if (abilities.length != 0) { return -1000; }
			int player = 1;
		//System.out.println(playername + " attempts to play " + cardid + " on " + slot);
	
		if (playername.equals(name2)) player = 2;
		if (getSlot(tplayer, slot, ttype).dummy) return -1;
		if (!getSlot(tplayer, slot, "d"+ttype).dummy) return -10;
		if (getCard(cardid).dummy) return -20;
		if (!getCard(cardid).typecode.startsWith("d")) return -21;
		if ((playerphase != player) || (phase != PHASE_PLAY)) return -2;  
		if (!manacheck(player,cardid)) return -3;
		if (isUnique(carddata[cardid]) && isInPlay(cardid)) return -98;
		if (!getCard(cardid).typecode.endsWith(ttype) && !getCard(cardid).typecode.equals("db")) return -99;
	
	
	if (player == 1) { 
		if (waitcards1 > 0 ) return -52;
			if (h1[handslot].cardid == cardid) {
			
				int success= create_modifier(player, cardid,tplayer, ttype,slot);
				if (success >=1) {
				
					manapay(player,cardid);
					manapay_est(player,cardid);
				h1[handslot].copydata(new CardmasterServerCard());	
			//	if (success == 1) { 
			//		alltriggeredabilities(getSlot(player,slot,"m"),"TP1M");
				//	alltriggeredabilities(getSlot(player,slot,"m"),"TCM1*");
				//}
				}
				if (success == 1) message("MES#" + name1 + " plays " + getName(carddata[cardid],true));
				return success;
				
			}
		}
		if (player == 2) { 
		if (waitcards2 > 0 ) return -52;
			if (h2[handslot].cardid == cardid) {
				
			
				
				int success=  create_modifier(player, cardid,tplayer, ttype,slot);
					if (success >=1) {	
					manapay(player,cardid);	manapay_est(player,cardid);
				h2[handslot].copydata(new CardmasterServerCard());
				}
				//if (success == 1) { 
				//	alltriggeredabilities(getSlot(player,slot,"m"),"TP2M");
				//}
				if (success == 1) message("MES#" + name2 + " plays " + getName(carddata[cardid],true));
				return success;
			
			}
		}	
		return -4000 + player;
		
		
		
		
	}
	
	int play_monster(String playername, int cardid,int slot, int handslot) {
		if (abilities.length != 0) { return -1000; }
		int player = 1;
		//System.out.println(playername + " attempts to play " + cardid + " on " + slot);
		if (playername.equals(name2)) player = 2;
		
		
		if (isActivatedConstant("PNOMO",player,null)) return -1;
		
	//	System.out.println("Card is a " + getCard(cardid).typecode);
		if (!getSlot(player, slot, "m").dummy) return -1;
		if (getCard(cardid).dummy) return -20;
		
		if (!getCard(cardid).typecode.equals("m")) return -21;
		if ((playerphase != player) || (phase != PHASE_PLAY)) return -2;  
		if (!manacheck(player,cardid)) return -3;
		if (isUnique(carddata[cardid]) && isInPlay(cardid)) return -98;
		if (player == 1) { if (waitcards1 > 0 ) return -52;
			if (h1[handslot].cardid == cardid) {
				manapay(player,cardid);
				h1[handslot].copydata(new CardmasterServerCard());	
				tempname = getCard(cardid).name;
				int success= create_monster(player, slot, cardid);
				if (success == 1) { 
					alltriggeredabilities(getSlot(player,slot,"m"),"TP1M");
				//	alltriggeredabilities(getSlot(player,slot,"m"),"TCM1*");
					if (getSlot(player,slot,"m").flag("face") || nextmfacedown == true || nextentfacedown ||	(isActivatedConstant("PFCDM",player,null)) ) {
						nextentfacedown = false;
						nextmfacedown = false;
						getSlot(player,slot,"m").SetStatusValue("facedown",1);
						
					}
					else {
						manapay_est(player,cardid);
					}
				 message("MES#" + name1 + " plays " + getName(getSlot(player,slot,"m"),false));
				 
				 }
				return success;
				
			}
		}
		if (player == 2) { if (waitcards2 > 0 ) return -52;
			if (h2[handslot].cardid == cardid) {
				manapay(player,cardid);
				h2[handslot].copydata(new CardmasterServerCard());
					tempname = getCard(cardid).name;
				int success= create_monster(player, slot, cardid);
				if (success == 1) { 
					alltriggeredabilities(getSlot(player,slot,"m"),"TP2M");
					if (getSlot(player,slot,"m").flag("face") || nextmfacedown == true || nextentfacedown || 	(isActivatedConstant("PFCDM",player,null)))  {
						nextentfacedown = false;
						nextmfacedown = false;
						getSlot(player,slot,"m").SetStatusValue("facedown",1);
						
					}
					else {
						manapay_est(player,cardid);
					}
				 message("MES#" + name2 + " plays " + getName(getSlot(player,slot,"m"),false));
				 
				 }
				return success;
			
			}
		}	
		return -4;
	}
	


	// PLAY A MONSTER CARD



	int play_location(String playername, int handslot) {
		if (abilities.length != 0) { return -1000; }
		int player = 1;
		//System.out.println(playername + " attempts to play " + cardid + " on " + slot);
		if (playername.equals(name2)) player = 2;
		
		if (isActivatedConstant("PNOLO",player,null)) return -3;
		int cardid = 0;
		if (player ==1) cardid = h1[handslot].cardid;
		if (player ==2) cardid = h2[handslot].cardid;
		
	//	System.out.println("Card is a " + getCard(cardid).typecode);
	//	System.out.println("Card # " + cardid);
		
	//	if (!getSlot(player, slot, "m").dummy) return -1;
		if (getCard(cardid).dummy) return -20;
		
		if (!getCard(cardid).typecode.equals("l")) return -21;
		if ((playerphase != player) || (phase != PHASE_PLAY)) return -2;  
		if (!manacheck(player,cardid)) return -3;
		if (isInPlay(cardid)) return -98; // locations are always Unique
		if (player == 1) { if (waitcards1 > 0 ) return -52;
			if (h1[handslot].cardid == cardid) {
				manapay(player,cardid);manapay_est(player,cardid);
				h1[handslot].copydata(new CardmasterServerCard());	
				int success= create_location(player, cardid);
				if (success == 1) { 
					alltriggeredabilities(location,"TP1%");
					
				}
				if (success == 1) message("MES#" + name1 + " plays location " + getName(carddata[cardid]));
				return success;
				
			}
		}
		if (player == 2) { if (waitcards2 > 0 ) return -53;
			if (h2[handslot].cardid == cardid) {
				manapay(player,cardid);manapay_est(player,cardid);
				h2[handslot].copydata(new CardmasterServerCard());
					
				int success= create_location(player, cardid);
				if (success == 1) { 
					alltriggeredabilities(location,"TP2%");
				}
				if (success == 1) message("MES#" + name2 + " plays location " + getName(carddata[cardid]));
				return success;
			
			}
		}	
		return -4;
	}
	
	

	
	int create_location(int player, int cardid) {
		if (getCard(cardid).dummy) return -1;
		if (!getCard(cardid).typecode.equals("l")) return -99;
		to_graveyard(location);
		locationowner = player;
		location.rand = random.nextInt(10000);
		location.copydata(getCard(cardid));
		intoplay_ability(0,0,"l");
		
		alltriggeredabilities(location,"TC%" + player + "*");
		if (!skipturn) {
		
			message(STP());
			MessageSTN();
			MessageSTC();
			}
		message(HAND(player_named(player)),player_named(player));	message(LOC());
		return 1;
	}
	// CREATE A MONSTER
	int create_monster(int player, int slot, int cardid) {
		
		return create_monster(player,slot,cardid,false);	
	}
	int create_monster(int player, int slot, int cardid, boolean token) {
		if (carddata[cardid].dummy) return -10;
		if (!(carddata[cardid].typecode.equals("m"))) return -11;
		if (player == 1) {
			if (!m1[slot].dummy) return -20;
			m1[slot].rand = random.nextInt(10000);
			m1[slot].copydata(carddata[cardid]);
			m1[slot].lifemod = 0;
			m1[slot].attackmod = 0;
		if(	m1[slot].flag("face")) m1[slot].SetStatusValue("facedown",1);
			m1[slot].token = token;
			intoplay_ability(player,slot,"m");
			alltriggeredabilities(getSlot(player,slot,"m"),"TCM1*");
				
			return 1;
	
		}else if (player == 2) {
			if (!m2[slot].dummy) return -20;
			m2[slot].rand = random.nextInt(10000);
			m2[slot].copydata(carddata[cardid]);
			m2[slot].lifemod = 0;
			m2[slot].attackmod = 0;
				m2[slot].token = token;
				if(	m2[slot].flag("face")) m2[slot].SetStatusValue("facedown",1);
			intoplay_ability(player,slot,"m");
	alltriggeredabilities(getSlot(player,slot,"m"),"TCM2*");
				
			return 1;
		}
		//System.out.println("What the hell? " + player);
		return -9;	
	}
	
	
	int create_modifier(int player, int cardid, int tplayer, String ttype, int tslot) {
		if (getCard(cardid) == null) return -101;
		if (getCard(cardid).dummy) return -101;
		CardmasterServerCard card = getCard(cardid);
		if (!card.typecode.startsWith("d")) return -199;
		if (!(card.typecode.endsWith(ttype)) && !(card.typecode.endsWith("b"))) { 
		System.out.println("Couldn't: " + ttype + " " + card.typecode);
		return -198; }
		
		CardmasterServerCard tcard = getSlot(tplayer,tslot,ttype);
		if (tcard == null) return -197;
		if (tcard.dummy) return -196;
		
		
		//if (!(tcard.mtype.equals("none")))
		
		if (!card.modType(tcard.mtype)) return -195;
		
		if (tplayer != player && card.modFlag("ownr")) return -194;
		if (tplayer == player && card.modFlag("opnt")) return -193;
		
		getSlot(tplayer,tslot,"d"+ttype).copydata(card);
		getSlot(tplayer,tslot,"d"+ttype).owner = player;
		
		intoplay_ability(tplayer,tslot,"d"+ttype);
		
		
		return 1;		
		}
		
	
	
	
	
	
	//create monster with attack/hp set.
	int create_monster(int player, int slot, int cardid, int attack, int lifepoints) {
		return create_monster(player,slot,cardid,attack,lifepoints,false);
	
	}		
		
	int create_monster(int player, int slot, int cardid, int attack, int lifepoints, boolean token) {
		if (carddata[cardid].dummy) return -10;
		if (!(carddata[cardid].typecode.equals("m"))) return -11;
		if (player == 1) {
			if (!m1[slot].dummy) return -20;
			m1[slot].rand = random.nextInt(10000);
			m1[slot].copydata(carddata[cardid]);
			m1[slot].lifepoints = lifepoints;
			m1[slot].attack = attack;
			m1[slot].lifemod = 0;
			m1[slot].attackmod = 0;
			m1[slot].token = token;
			intoplay_ability(player,slot,"m");
			alltriggeredabilities(getSlot(player,slot,"m"),"TCM*");
				
			return 1;
	
		}else if (player == 2) {
			if (!m2[slot].dummy) return -20;
			m2[slot].rand = random.nextInt(10000);
			m2[slot].copydata(carddata[cardid]);
			m2[slot].lifepoints = lifepoints;
			m2[slot].attack = attack;
			m2[slot].lifemod = 0;
			m2[slot].attackmod = 0;
			m2[slot].token = token;
			intoplay_ability(player,slot,"m");
			
			alltriggeredabilities(getSlot(player,slot,"m"),"TCM*");
				
			return 1;
		}
		//System.out.println("What the hell? " + player);
		return -9;	
	}
	// CREATE AN EFFECT
	int create_effect(int player, int slot, int cardid) {
		return create_effect(player,slot,cardid,false);
	}
	int create_effect(int player, int slot, int cardid, boolean token) {
		if (carddata[cardid].dummy) return -10;
		if (!(carddata[cardid].typecode.equals("e"))) return -11;
		if (player == 1) {
			if (!e1[slot].dummy) return -20;
			e1[slot].rand = random.nextInt(10000);
			e1[slot].copydata(carddata[cardid]);
			e1[slot].token = token;
			intoplay_ability(player,slot,"e");
			alltriggeredabilities(getSlot(player,slot,"e"),"TCE" + player +"*");
				
			return 1;
	
		}else if (player == 2) {
			if (!e2[slot].dummy) return -20;
			e2[slot].rand = random.nextInt(10000);
			e2[slot].copydata(carddata[cardid]);
			e2[slot].token = token;
			intoplay_ability(player,slot,"e");
			alltriggeredabilities(getSlot(player,slot,"e"),"TCE" + player+ "*");
				
			return 1;
		}
		//System.out.println("What the hell? " + player);
		return -9;	
	}
	int create_effect(int player, int slot, int cardid, int attack, int lifepoints) {
		return create_effect(player,slot,cardid,attack,lifepoints,false);
	}
	int create_effect(int player, int slot, int cardid, int attack, int lifepoints, boolean token) {
		if (carddata[cardid].dummy) return -10;
		if (!(carddata[cardid].typecode.equals("e"))) return -11;
		if (player == 1) {
			if (!e1[slot].dummy) return -20;
			e1[slot].rand = random.nextInt(10000);
			e1[slot].copydata(carddata[cardid]);
			e1[slot].lifepoints = lifepoints;
			e1[slot].attack = attack;
			e1[slot].token = token;
			intoplay_ability(player,slot,"e");
			alltriggeredabilities(getSlot(player,slot,"e"),"TCE" + player +"*");
				
			return 1;
	
		}else if (player == 2) {
			if (!e2[slot].dummy) return -20;
			e2[slot].rand = random.nextInt(10000);
			e2[slot].copydata(carddata[cardid]);
			e2[slot].lifepoints = lifepoints;
			e2[slot].attack = attack;
			e2[slot].token = token;
			intoplay_ability(player,slot,"e");
			alltriggeredabilities(getSlot(player,slot,"e"),"TCE" + player +"*");
				
			return 1;
		}
		//System.out.println("What the hell? " + player);
		return -9;	
	}	
	boolean manacheck(int player, int cardid) {
		if (carddata[cardid].dummy) return false;
		CardmasterServerCard card = carddata[cardid];
		int costD = (int)getModifiedValue(card,"dco","A", player);
		int costG = (int)getModifiedValue(card,"gco","A", player);
		int costL = (int)getModifiedValue(card,"lco","A", player);
		if (player == 1) {
			if ((p1D >= costD || costD <= 0) && 
				(p1G >= costG || costG <= 0) && 
				(p1L >= costL || costL <= 0)) return true;	
			
			
		}else if (player==2) {
			if ((p2D >= costD || costD <= 0) && 
				(p2G >= costG || costG <= 0) && 
				(p2L >= costL || costL <= 0)) return true;			
			
		}
		
		return false;	
	}
	void manapay_est(int player, int cardid) {
		if (carddata[cardid].dummy) return;
		CardmasterServerCard card = carddata[cardid];
		int Dcost =  (int)getModifiedValue(card,"dco","A", player);
		int Gcost =  (int)getModifiedValue(card,"gco","A", player);
		int Lcost =  (int)getModifiedValue(card,"lco","A", player);
		
		if (player == 1) {
			p1Du-= Dcost;
	
			p1Gu-= Gcost;
			p1Lu-= Lcost;
			if (p1Du < 0) p1Du = 0;
			if (p1Gu < 0) p1Gu = 0;
			if (p1Lu < 0) p1Lu = 0;
			
		}
		if (player == 2) {
			p2Du-= Dcost;
			p2Gu-= Gcost;
			p2Lu-= Lcost;
			if (p2Du < 0) p2Du = 0;
			if (p2Gu < 0) p2Gu = 0;
			if (p2Lu < 0) p2Lu = 0;
	
		}		
		
	
		
	
	}
	
	void manapay(int player, int cardid) {
		if (carddata[cardid].dummy) return;
		CardmasterServerCard card = carddata[cardid];
		int Dcost =  (int)getModifiedValue(card,"dco","A", player);
		int Gcost =  (int)getModifiedValue(card,"gco","A", player);
		int Lcost =  (int)getModifiedValue(card,"lco","A", player);
		
		if (player == 1) {
			setMana("D",1,p1D-Dcost);
			setMana("G",1,p1G-Gcost);
			setMana("L",1,p1L-Lcost);
			
	
		
		/*	if (Dcost > 0 || 
				Lcost > 0 ||
				Gcost > 0 ) {
				alltriggeredabilities(null,"TP1N");
					
				
				
				
			}*/
		}
		if (player == 2) {
			setMana("D",2,p2D-Dcost);
			setMana("G",2,p2G-Gcost);
			setMana("L",2,p2L-Lcost);
		/*	if (Dcost > 0 
				|| Lcost >0 
				|| Gcost > 0) {
				alltriggeredabilities(null,"TP2N");
					
				
				
				
			}*/
		}		
		
	}
	
	int AddToHand(int playernumber, int cardid) {
		if (playernumber == 1) {
			
			for (int i =0;i<8;i++) {
				if (h1[i].dummy) {	
					h1[i].rand = random.nextInt(10000);
					h1[i].copydata(getCard(cardid));
					
					message(HAND(player_named(playernumber)),player_named(playernumber));	message(LOC()); //MDN();
					return cardid;
				}
			}
			
		}
		else if (playernumber == 2) {
			
			for (int i =0;i<8;i++) {
				if (h2[i].dummy) {	
					h2[i].rand = random.nextInt(10000);
					h2[i].copydata(getCard(cardid));
					
					message(HAND(player_named(playernumber)),player_named(playernumber));	message(LOC()); //MDN();
					return cardid;
				}
			}
			
		}	
		return -1;		
			
	}
	
	int lowestattack = 99999;
	
	
	
	
	
	int return_to_hand(int playernumber, CardmasterServerCard thecard) {
		if (thecard == null) return -1;
		if (!thecard.dummy)	destroy_modifier(playernumber,thecard.typecode,findSlotNumber(thecard));


	
	
		
		String type = thecard.typecode;
		int slot = findSlotNumber(thecard);
		if (type == null) return -1;
	
	
	if (playernumber != playerphase && type.equals("m") && defender[slot+1] > 0) {
			attacker[defender[slot+1]] = 6;
			defender[slot+1] = 0;
			defenderlock[slot+1] = false;
		//	fix_attackers();
			message("ATD#" + playerphase + "#" + attacker[1]+ "#" + attacker[2]+ "#" + attacker[3]+ "#" + attacker[4]+ "#" + attacker[5] + "#");

		}
						
		else 				
		if (playernumber == playerphase && type.equals("m") && attacker[slot+1] > 0) {
			
			
//	System.out.println(": Fixing after bouncing attacker");
	
			if (attacker[slot+1] < 6) {
	
				defender[attacker[slot+1]] = 0; 
				defenderlock[attacker[slot+1]] = false;
			
			}
			for (int i=0;i<6;i++) {
	
	
	
	//System.out.println("Atkfix: Checking defender " + i + " for slot " + (slot+1) + "...");
			
				if (defender[i] == (slot+1))
				 {
				 	
				 	//System.out.println("Atkfix: Modifying defender " + i + " for slot " + (slot+1) + "...");
	
				 	
				 	defender[i] = 0; defenderlock[i] = false;}
			}	
				
			attacker[slot+1] = 0;
		//	fix_attackers();
			message("ATD#" + playerphase + "#" + attacker[1]+ "#" + attacker[2]+ "#" + attacker[3]+ "#" + attacker[4]+ "#" + attacker[5] + "#");

			
		}	
	
	
	
	
	if (thecard.token) {
	
	
	to_graveyard(thecard);
	return thecard.cardid;
}

	leaveplay_ability(playernumber, findSlotNumber(thecard), thecard.typecode, "RTTHD");
	if (thecard.dummy) return -1;
	
	
	
	
	
		if (playernumber == 1) {
			for (int i =0;i<8;i++) {
				if (h1[i].dummy) {
					int card = thecard.cardid;
					if (card <=0) return card;
					
					
					
				//	leaveplay_ability(playernumber, findSlotNumber(thecard), thecard.typecode, "RTTHD");
					
					if (! thecard.token ) h1[i].copydata(getCard(card));
					//System.out.println(playernumber + " (P1) Drawn card " + card);
				
					
					
						
						
						
					
					thecard.copydata(new CardmasterServerCard());
					if (!skipturn) {
					
					MessageSTC();
		message(HAND(player_named(playernumber)),player_named(playernumber));	message(LOC()); //MDN();
					}
					if (phase == PHASE_ATTACK || phase == PHASE_DEFEND) {
						fix_attackers();
						message("ATD#" + playerphase + "#" + attacker[1]+ "#" + attacker[2]+ "#" + attacker[3]+ "#" + attacker[4]+ "#" + attacker[5] + "#");
					}
					return card;
				}					
					
			}
		//	add_waiting(1,card);
			to_graveyard(thecard);
		}
		else if (playernumber == 2) {
			for (int i =0;i<8;i++) {
				if (h2[i].dummy) { 
					int card = thecard.cardid;
					if (card <=0) return card;
					
					
					//	leaveplay_ability(playernumber, findSlotNumber(thecard), thecard.typecode, "RTTHD");
					
					
					if (! thecard.token ) h2[i].copydata(getCard(card));
					//System.out.println(playernumber + " (P2) Drawn card " + card);
			
					
					thecard.copydata(new CardmasterServerCard());
					if (!skipturn) {
					
					MessageSTC();message(HAND(player_named(playernumber)),player_named(playernumber));	message(LOC()); //MDN();
					}
					if (phase == PHASE_ATTACK || phase == PHASE_DEFEND) {
						fix_attackers();
						message("ATD#" + playerphase + "#" + attacker[1]+ "#" + attacker[2]+ "#" + attacker[3]+ "#" + attacker[4]+ "#" + attacker[5] + "#");
					}
					return card;
				}					
					
			}
		//	add_waiting(2,card);
			to_graveyard(thecard);
		}		
		return -1;
	}
	int draw_ability_card(int player) {
		if (player == 1) drawcard1--;
		if (player == 2) drawcard2--;
		//System.out.println("drawcards1: " + drawcard1 + " 2: " + drawcard2);
		
		return draw_card(player);
	}


	int draw_ability_opp(int player) {
		if (player == 1) drawcardopp1--;
		if (player == 2) drawcardopp2--;
		//System.out.println("drawcards1: " + drawcard1 + " 2: " + drawcard2);
		
		return draw_card_opp(player);
	}
	
	
	int pollNextDrawCard(int playernumber ) {
		if (playernumber != 1 && playernumber != 2) return 0;
		int waiting = 0;
		if (playernumber ==1) waiting = drawcard1;
		else if (playernumber ==2) waiting = drawcard2;
		int nextdrawcard = 0;
		
		if (playernumber == 1) nextdrawcard= deck1.nextDrawCard(waiting);
		if (playernumber == 2) nextdrawcard= deck2.nextDrawCard(waiting );
		if (webrunner.cardmaster.CardmasterData.DEBUGMODE) {
			System.out.println("Next draw card: " + carddata[nextdrawcard].name);
		}
		return nextdrawcard;
		
	}
	
	int draw_card_opp(int playernumber) {
			if (CardmasterData.DEBUGMODE) System.out.println("Draw card: " + playernumber);
		if (playernumber == 1 && waitcards1 > 0) {
				waitdrawopp1++;
				message("MDN#" + playernumber + "#" + waitcards1 + "#");
				return -1;
	 	}
	 		if (playernumber == 2 && waitcards2 > 0) {
				waitdrawopp2++;
				message("MDN#" + playernumber + "#" + waitcards2 + "#");
				return -1;
	 	}
	 	
	 	if (numCardsInHand(1) == 8 && playernumber==1) {
	 			req_discard(1);
	 			waitdrawopp1++;
				return -1;	 			
	 		

	 	}
	 	
	 	if (numCardsInHand(2) == 8 && playernumber==2) {
	 			req_discard(2);
	 			waitdrawopp2++;
				return -1;	 			
	 		

	 	}	 	
	 	return draw_card_safe_opp(playernumber);
	}
	
	int draw_card(int playernumber) {
		if (CardmasterData.DEBUGMODE) System.out.println("Draw card: " + playernumber);
		if (playernumber == 1 && waitcards1 > 0) {
				waitdraw1++;
				message("MDN#" + playernumber + "#" + waitcards1 + "#");
				return -1;
	 	}
	 		if (playernumber == 2 && waitcards2 > 0) {
				waitdraw2++;
				message("MDN#" + playernumber + "#" + waitcards2 + "#");
				return -1;
	 	}
	 	
	 	if (numCardsInHand(1) == 8 && playernumber==1) {
	 			req_discard(1);
	 			waitdraw1++;
				return -1;	 			
	 		

	 	}
	 	
	 	if (numCardsInHand(2) == 8 && playernumber==2) {
	 			req_discard(2);
	 			waitdraw2++;
				return -1;	 			
	 		

	 	}	 	
	 	return draw_card_safe(playernumber);
	}
	
	
	
	
	int draw_card_safe(int playernumber) {
		
		if (CardmasterData.DEBUGMODE) System.out.println("Draw Card Safe: " + playernumber);
	//	System.out.println("D_C Drawcard1: " +drawcard1);
	//	System.out.println("D_C Drawcard2: " +drawcard2);
		if (playernumber == 1) {
		
			
			for (int i =0;i<8;i++) {
				if (h1[i].dummy) {
					if (deck1.deckcards == 0) { if (!endedonce && winner == 0) duelendedbytype = "Card Draw"; endduel(2); drawcard1=0; return -1; }
					int card = deck1.drawCard();
					if (card ==0) { message("MES#DEBUG: DRAW 0 ATTEMPTED#");}
					if (card == -1) { endduel(2); drawcard1 = 0; return -1; }
					if (card <=0) return card;
					h1[i].copydata(getCard(card));
					 alltriggeredabilities(h1[i],"TP1C");
					//System.out.println(playernumber + " (P1) Drawn card " + card);
					//message(STC()); MDN();
					if (waitcards1 > 0)  message("MDN#" + playernumber + "#" + waitcards1 + "#");
					if (!skipturn) message(HAND(name1),name1);
					return card;
				}					
					
			}
			if (waitcards1 > 0)  message("MDN#" + playernumber + "#" + waitcards1 + "#");
		//	req_discard(1);
		}
		else if (playernumber == 2) {
			
			for (int i =0;i<8;i++) {
				if (h2[i].dummy) { 
					if (deck2.deckcards == 0) { if (!endedonce && winner == 0) duelendedbytype = "Card Draw"; endduel(1); drawcard2=0; return -1; }
					int card = deck2.drawCard();
					if (card ==0) { message("MES#DEBUG: DRAW 0 ATTEMPTED#");}
					if (card == -1) { endduel(1); drawcard2 = 0;return -1; }
					if (card <=0) return card;
					h2[i].copydata(getCard(card));
					 alltriggeredabilities(h2[i],"TP2C");
					//System.out.println(playernumber + " (P2) Drawn card " + card);
					//message(STC()); MDN();
					if (waitcards2 > 0)  message("MDN#" + playernumber + "#" + waitcards2 + "#");
					if (!skipturn) message(HAND(name2),name2);
					return card;
				}					
					
			}
			if (waitcards2 > 0)  message("MDN#" + playernumber + "#" + waitcards2 + "#");
		//	req_discard(2);
		}		
		return -1;
	}
	

	
	int draw_card_safe_opp(int playernumber) {
		
//		 System.out.println(" Draw Card Safe opp: " + playernumber);
	//	System.out.println("D_C Drawcard1: " +drawcard1);
	//	System.out.println("D_C Drawcard2: " +drawcard2);
		if (playernumber == 1) {
			if (deck2.deckcards == 0) { if (!endedonce && winner == 0) duelendedbytype = "Card Draw"; endduel(2); drawcardopp1=0; return -1; }
			
			for (int i =0;i<8;i++) {
				if (h1[i].dummy) {
					int card = deck2.drawCard();
					if (card ==0) { message("MES#DEBUG: DRAW 0 ATTEMPTED#");}
					if (card == -1) { if (!endedonce && winner == 0) duelendedbytype = "Card Draw"; endduel(2); drawcardopp1 = 0; return -1; }
					if (card <=0) return card;
					h1[i].copydata(getCard(card));
					 alltriggeredabilities(h1[i],"TP1C");
					//System.out.println(playernumber + " (P1) Drawn card " + card);
					//message(STC()); MDN();
					if (waitcards1 > 0)  message("MDN#" + playernumber + "#" + waitcards1 + "#");
					if (!skipturn) message(HAND(name1),name1);
					return card;
				}					
					
			}
			if (waitcards1 > 0)  message("MDN#" + playernumber + "#" + waitcards1 + "#");
		//	req_discard(1);
		}
		else if (playernumber == 2) {
			
			for (int i =0;i<8;i++) {
				if (h2[i].dummy) { 
					if (deck1.deckcards == 0) { if (!endedonce && winner == 0) duelendedbytype = "Card Draw"; endduel(1); drawcardopp2=0; return -1; }
					int card = deck1.drawCard();
					if (card ==0) { message("MES#DEBUG: DRAW 0 ATTEMPTED#");}
					if (card == -1) { if (!endedonce && winner == 0) duelendedbytype = "Card Draw"; endduel(1); drawcardopp2 = 0;return -1; }
					if (card <=0) return card;
					h2[i].copydata(getCard(card));
					 alltriggeredabilities(h2[i],"TP2C");
					//System.out.println(playernumber + " (P2) Drawn card " + card);
					//message(STC()); MDN();
					if (waitcards2 > 0)  message("MDN#" + playernumber + "#" + waitcards2 + "#");
					if (!skipturn) message(HAND(name2),name2);
					return card;
				}					
					
			}
			if (waitcards2 > 0)  message("MDN#" + playernumber + "#" + waitcards2 + "#");
		//	req_discard(2);
		}		
		return -1;
	}


	public int numCardsInHand(int player) {
		int count = 0;
		if (player ==1) {
			for (int i=0;i<8;i++) {
				if (h1[i] == null) h1[i] = new CardmasterServerCard();
				if (!(h1[i].dummy)) if (!(h1[i].markedForUse)) count++;
				}	
		//	if (player == playercastingspell) count--;
			return count;
		}
			
		if (player ==2) {
			for (int i=0;i<8;i++) {
				if (!(h2[i].dummy)) if (!(h2[i].markedForUse)) count++;
				
				}
		//	if (player == playercastingspell) count--;
			return count;
		}
		return 0;
		
	}
	void req_discard(int player) {
		
		if (CardmasterData.DEBUGMODE) System.out.println("Req_Discard: " + player);

		if (hasCardsInHand(player)) {
			int x = 0;

		
		if (player==1) { waitcards1++; }
		else {waitcards2++;}
		if (waitcards1 > numCardsInHand(1)) waitcards1 = numCardsInHand(1);
		if (waitcards2 > numCardsInHand(2)) waitcards2 = numCardsInHand(2);
			
		if (player ==1) x = waitcards1;
		else x = waitcards2;
		if (x > 0) discardwait = true;
		message("MDN#" + player + "#" + x + "#",player_named(player));
	//	System.out.println("player " + player + " Must discard " + x );
		}
		
	}
	
	/*
	String listOfAbilities() {
		String abilitylist = "ABL#";
		for (int i=0;i<abilities.length;i++) {
			String sourcestring = "Nothing";
			String targetstring = "Nothing";
			if (abilities[i].ability) {
				sourcestring = player_named(abilities[i].player) + "'s " + abilities[i].self.name;
			}
			else {
				sourcestring = player_named(abilities[i].player) + "'s spell " + carddata[abilities[i].cardid].name;
			}
			
			String newability = sourcestring + " on " + targetstring;
		}
	}*/
	
	int addNewAbility(String abilitycode, int player, AbilityTarget target,
					    CardmasterServerCard self, 
						int targetslot, boolean isability, int speed, 
						String targettype, int tplayer, int spellid, int handslot, int targetid) {
		AbilitySpell newa = new AbilitySpell();
		newa.abilitycode = abilitycode;
		newa.player = player;
		newa.target = target;
		newa.self = self;
		newa.targetslot = targetslot;
		newa.ability = isability;
		newa.speed = speed;
		newa.targettype = targettype;
		newa.tplayer = tplayer;
		newa.spellid = spellid;
		newa.handslot = handslot;
		newa.cardid = spellid;
		newa.oldtargetid = targetid;
		/*
		for (int i=0;i<abilities.length;i++) {
			if (self == abilities[i].self) return 0;
			
		}*/
		if (discardwait) return 0;
		if (waitcards1 > 0 || waitcards2 > 0) return 0;
		
		
		
		int canrun = canRun(newa);
		if (canrun == 0) return 0;
		String targetname = "available targets";
		if (target != null) {
			
			if (target.isCard) targetname = getName(target.getCard()) + "(slot: " + (target.s+1)+ ")";
			if (target.isGrave) targetname = "card in graveyard";
			if (target.isPlayer) targetname = player_named(target.getPlayer());
		}
		
		if (isability) {
			if (!abilitycode.endsWith("DEND;") && self.flag("focus")) return 0; // this is an ability, and it should dizzy, but it can't.
		
		if (self.typecode.startsWith("d")) {
			if (!abilitycode.endsWith("DEND;") && GetModifierParent(self).flag("focus")) return 0; // this is an ability, and it should dizzy, but it can't.
		
		}
		
			if (self.GetStatusValue("facedown") >=1){
				 self.SetStatusValue("facedown",0);
				 MessageSTC();
				 
				 
				 
				 
			}
			if (self.typecode.startsWith("d")) {
				if (GetModifierParent(self).GetStatusValue("facedown") >= 1) {
					GetModifierParent(self).SetStatusValue("facedown",0);
				}
			}
			
		}
		
		if (isability) message("MES#" + player_named(player) + " will use ability of " + self.name + " on " + targetname + "#");
		if (!isability) message("MES#" + player_named(player) + " will cast " + self.name + " on " + targetname + "#");
		p2abilityresolve = false;
		p1abilityresolve = false;
	
		AbilitySpell[] temparray = new AbilitySpell[abilities.length +1];
		System.arraycopy(abilities,0,temparray,0,abilities.length);
		abilities = new AbilitySpell[abilities.length+1];
		System.arraycopy(temparray,0,abilities,0,temparray.length);
		abilities[abilities.length-1] = newa;
		//java.util.Arrays.sort(abilities);
		message("ABR#" + bToI(p1abilityresolve) + "#" + bToI(p2abilityresolve) + "#" + abilities.length + "#");
		//message(listOfAbilities());
		return canrun;
		
	}
	
	void runWaitingAbilities() {
		if (abilities.length ==0) { p1abilityresolve = false;  p2abilityresolve = false;
			abilitycounter = false;
			abilitycounternext = false;
			 return;
		}
	//	System.out.println("SORT STARTING ----------");
		java.util.Arrays.sort(abilities);
	//	System.out.println("------- SORT COMPLETE");
		int i = 0;
		for (i=0;i<abilities.length;i++) {
			if (discardwait) break;
			int player = abilities[i].player;
			int cardid = abilities[i].cardid;
			String ename = abilities[i].self.name;
			String tcode = abilities[i].self.typecode;
			boolean failure = false;
			if (abilitycounter) { failure = true; }
			if (abilitycounternext) { failure = true; abilitycounternext = false;	}
			boolean spellCantCast = false;
			int handslot = abilities[i].handslot;
			if (abilities[i].target != null)
			if (abilities[i].target.isCard && abilities[i].target.getCard().dummy) failure = true;
			
			if (abilities[i].ability) {
				if (abilities[i].self == null) failure = true;
				
				if (abilities[i].self.dummy) failure = true;
				if (!abilities[i].ability)  failure = true;
				if (abilities[i].self.cardid != cardid && abilities[i].self.cardid != 772 && abilities[i].self.cardid != 825)  failure = true;
				if (abilities[i].self.dizzy)  failure = true;
				
					
					
					
					
					
				
				
				
				
				
				
				if (abilities[i].target != null) {
				if (isActivatedConstant("MNOAB",player,abilities[i].self)) failure = true;
				if (abilities[i].target.isCard && HasAbilityImmune(abilities[i].target.getCard()))  failure = true;
				if (abilities[i].target.isCard && abilities[i].target.getCard() != null) {
					
					
					
					
					
				
					if (abilities[i].target.getCard().typecode != null) 
					if (abilities[i].target.getCard().typecode.equals("m") && 
						decoy_in_play() && 
						!HasDecoying(abilities[i].target.getCard())) 
							failure = true;
					}
				}
				if (abilities[i].self != null) {
			
				if (!failure) {
				
				if (abilities[i].self.GetStatusValue("facedown") >=1) {
					abilities[i].self.SetStatusValue("facedown",0);

				}	
				}
				if ( abilities[i].self.typecode != null) {
				
				String typecode = abilities[i].self.typecode.toUpperCase();
				
				if (typecode.startsWith("D")) {
					if (GetModifierParent(abilities[i].self).dizzy)  failure = true;
					if(GetModifierParent(abilities[i].self).GetStatusValue("glue") >=1 && !failure) {
						dizzy(GetModifierParent(abilities[i].self),true);
						GetModifierParent(abilities[i].self).SetStatusValue("glue",0);
						message("MES#" + GetModifierParent(abilities[i].self).name + " has been affected by the glue!");
						failure = true;
					}
				}
				}
				if(abilities[i].self.GetStatusValue("glue") >=1 && !failure) {
					dizzy(abilities[i].self,true);
					abilities[i].self.SetStatusValue("glue",0);
					message("MES#" + abilities[i].self.name + " has been affected by the glue!");
					failure = true;
				}
				
				
				}
				
				//if (abilities[i]oldtargetid != 0 &&  abilities[i].oldtargetid != abilities[i].getCard().cardid) continue;
				
				 
				
			} else {
				 // skip if you can't play spells and it's a spell
					if (isActivatedConstant("PNOSP",player,null))  { failure = true; spellCantCast = true; }
					if (player ==1 && h1[handslot].dummy) { failure = true;  spellCantCast = true; }
					if (player ==2 && h2[handslot].dummy)  { failure = true;  spellCantCast = true; }
					if (player != 1 && player != 2) failure = true;
					if (player == 1 && h1[handslot].cardid != abilities[i].spellid) { failure = true;  spellCantCast = true; }
					if (player == 2 && h2[handslot].cardid != abilities[i].spellid)  { failure = true; spellCantCast = true; }
					if (!manacheck(player,abilities[i].spellid)) { failure = true;  spellCantCast = true; }		
					if (abilities[i].target != null)
					if (abilities[i].target.isCard && HasSpellImmune(abilities[i].target.getCard()))  failure = true;
				
					//if (abilities[i].oldtargetid != 0 && abilities[i].oldtargetid != abilities[i].getCard().cardid) continue;
					//if (abilities[i].targettype.equals("s") && )
				
				 
			
			}
			
			int secondreturnval = 0;
			if (abilities[i].abilitycode.indexOf("DEND") != -1) secondreturnval = 2;
			int returnval = 0;
			
			
			spellcostG = (int)getModifiedValue(carddata[cardid],"gco","A", player);
			
			spellcostD = (int)getModifiedValue(carddata[cardid],"dco","A", player);
			spellcostL = (int)getModifiedValue(carddata[cardid],"lco","A", player);
			
			
			
			if (!failure) returnval = runit(abilities[i]);
			else returnval = 0;
			if (i<abilities.length) {
			
			if (abilities[i].ability) { // abilities
				if (returnval != 2 && secondreturnval != 2) dizzy(abilities[i].self,true);
				if (returnval == 1 || returnval == 2) {
					 alltriggeredabilities(abilities[i].self,"TC"+ tcode+ abilities[i].player + "!" );
				     message("MES#" + player_named(abilities[i].player) + "'s " + getName(carddata[cardid]) + " uses it's ability!");
					
					if (abilities[i].self != null && abilities[i].target != null) {
						if (abilities[i].self.typecode != null) {
							String typecode = abilities[i].self.typecode.toUpperCase();
//	System.out.println("Ability Use: " + abilities[i].self.name + " - " + typecode);
							
							if (typecode.startsWith("D")) {
								typecode = GetModifierParent(abilities[i].self).typecode.toUpperCase();
								triggerabilities(GetModifierParent(abilities[i].self),abilities[i].target.getCard(),"TC"+typecode+"&"+ abilities[i].tplayer);
							
							}
							else {
							
									triggerabilities(abilities[i].self,abilities[i].target.getCard(),"TC"+typecode+"&"+ abilities[i].tplayer);
							}
							
						}
					}
				
					if (abilities[i].targettype.equals("m")) { //TARGET MONSTER
					
						alltriggeredabilities(abilities[i].target.getCard(),"TCM@"+ abilities[i].tplayer);
					}
					if (abilities[i].targettype.equals("e")) { //TARGET MONSTER
					
						alltriggeredabilities(abilities[i].target.getCard(),"TCE@"+ abilities[i].tplayer);
					}
					if (abilities[i].targettype.equals("g")) {
					
		
		
		
					}
					if (returntohand != null)  {
						
						return_to_hand(returntohandplayer,returntohand);returntohand = null;	
						}	
					
				resolve_drawcards();
				} else {
					message("MES#" + player_named(player) + " fails to use ability of " + getName(carddata[cardid]));
				//	System.out.println(getName(carddata[cardid] + " - " + returnval);
					failure_ability(player,abilities[i].self,abilities[i].target,abilities[i].targetslot);
				}
				
			}else { // spells
				if (returnval != 0)  { 
					if (cardid == 846) {
						if (player == 1) gift_1_8010_1 = true;
						else if (player == 2) gift_2_8010_1 = true;
						
					}
					else if (cardid == 671) {
						if (player == 1) gift_1_8010_2 = true;
						else if (player == 2) gift_2_8010_2 = true;
						
					}
					else if (cardid == 455) {
						if (player == 1) gift_1_8010_3 = true;
						else if (player == 2) gift_2_8010_3 = true;
						
					}
					message("MES#" + player_named(player) + " casts spell " + getName(carddata[cardid]));
					message("SBC#" + carddata[cardid] + "#");
					manapay(player,cardid);manapay_est(player,cardid);
					
					
					alltriggeredabilities(abilities[i].self,"TP" + player + "P");
					
					
		
				
					if (abilities[i].targettype.equals("m")) {
					//	alltriggeredabilities(abilities[i].self,"TP" + player + "P");
					 	alltriggeredabilities(abilities[i].target.getCard(),"TCM>" + abilities[i].tplayer);
					}
					if (abilities[i].targettype.equals("e")) {
					//	alltriggeredabilities(abilities[i].self,"TP" + player + "P");
					 	alltriggeredabilities(abilities[i].target.getCard(),"TCE>" + abilities[i].tplayer);
					}					
					
					
					
						if (!switchhands) {
					
							if (player ==1) to_graveyard(h1[handslot]);
							else if (player == 2) to_graveyard(h2[handslot]);
						}else {	
							if (player ==2) to_graveyard(h1[handslot]);
							else if (player == 1) to_graveyard(h2[handslot]);
						}
						switchhands = false;
					
					
				
					
					
						if (returntohand != null)  {
						
						return_to_hand(returntohandplayer,returntohand);returntohand = null	;
						}
					
					resolve_drawcards();
					
				}
				else {
					message("MES#" + player_named(player) + " fails to cast " + getName(carddata[cardid]));
					if (!spellCantCast){
						manapay(player,cardid);manapay_est(player,cardid);
						failure_ability(player,handslot,abilities[i].target,abilities[i].handslot);
						}
						
					if (!spellCantCast) {
						
						if (!switchhands) {
					
							if (player ==1) to_graveyard(h1[handslot]);
							else if (player == 2) to_graveyard(h2[handslot]);
						}else {	
							if (player ==2) to_graveyard(h1[handslot]);
							else if (player == 1) to_graveyard(h2[handslot]);
						}
						
						switchhands = false;
					}
				}
				
			}
			switchhands = false;
			}
			
			
						switchhands = false;
		resolve_drawcards();
			
						switchhands = false;	
		}
		// i fails at 5.  SO go from indx 4 to indx 9
	//	i = i-1; // i = 4
		
	//	if (i < (abilities.length -1 )) { // if i = 9 fail
		if (i <= abilities.length) {
		
		//	System.out.println("Old ability length: " + abilities.length);
			AbilitySpell[] temparray = new AbilitySpell[abilities.length-i]; // new one is 10-4 = 6 so 0-5
			System.arraycopy(abilities,i,temparray,0,temparray.length);//Copy pos 4 through to pos 9 size six to 0-5 size six.
			abilities = temparray;
		//	System.out.println("New ability length: " + abilities.length);	
			
		}
		
		MessageSTN();
		MessageSTC(); //MDN();
		message(HAND(name1),name1);message(HAND(name2),name2);
		message(LOC());
		if (!discardwait) {
			abilitycounter = false;
			 abilities = new AbilitySpell[0];
		 	 p1abilityresolve = false;  p2abilityresolve = false; 
		 	 for (int j=0;j<8;j++) {
		 	 	h1[j].markedForUse = false;
		 	 	h2[j].markedForUse = false;
		 	 	
		 	 }
		 	 
		 	 return; }
		 else {
		 	message("MES#Ability resolve cancelled until discard completed#");
		 	
		 	return;
		 }
		
		
	}
	
	void init_game() {
		
		random = new MersenneTwisterFast();
		
		
		
		//	System.out.println(name1 + " versus " + name2);
			int r =  random.nextInt(10);
		//System.out.println("Randomizer: "+ r);
		if (r> 5) { 
		 	String tempname = name1;
		 	name1 = name2;
		 	name2 = tempname;
		
		}
		
		gamebegun =true;
		
		System.out.println(name1 + " versus " + name2);
		CardmasterLogManager.WriteLog("Game Start: " + name1 + " versus " + name2);
		if (name1 == null || name2 == null) {
			
				System.out.println("NAME ERROR 1");
			dead = true;
		}
		if (name1.equals("") || name2.equals("")) {
			
				System.out.println("NAME ERROR 2");
			dead = true;
		}
		//p1L = 1;
		//p2L = 1;
		//randomly switch name1 and name2
		loadCardData();
		loadAbilityData();
			h1 = new CardmasterServerCard[8];
			h2 = new CardmasterServerCard[8];
			m1 = new CardmasterServerCard[5];
			m2 = new CardmasterServerCard[5];
			e1 = new CardmasterServerCard[5];
			e2 = new CardmasterServerCard[5];
			dm1 = new CardmasterServerCard[5];
			dm2 = new CardmasterServerCard[5];
			de1 = new CardmasterServerCard[5];
			de2 = new CardmasterServerCard[5];
			
			
		location = new CardmasterServerCard();
		location.copydata(carddata[CardmasterData.DEFAULTLOCATION]); // make it a field
		abilities = new AbilitySpell[0];
		
		for (int i = 0;i<5;i++) {
		m1[i] = new CardmasterServerCard();m1[i].sn =0;
		m2[i] = new CardmasterServerCard();m2[i].sn =2;
		e2[i] = new CardmasterServerCard();e2[i].sn =3;
		e1[i] = new CardmasterServerCard();e1[i].sn =4;
		
		dm1[i] = new CardmasterServerCard();dm1[i].sn =5;
		dm2[i] = new CardmasterServerCard();dm2[i].sn =6;
		de1[i] = new CardmasterServerCard();de1[i].sn =7;
		de2[i] = new CardmasterServerCard();de2[i].sn =8;
		
		h1[i] = new CardmasterServerCard();
		h2[i] = new CardmasterServerCard();
		
	
		}
		for (int i = 5; i<8;i++) {
		h1[i] = new CardmasterServerCard();
		h2[i] = new CardmasterServerCard();
		}	
		
		deck1 = new CardmasterLibrary(name1);
		if (deck1.created == false) requestclose();
		deck1.PrepareDeck(random);
		deck2 = new CardmasterLibrary(name2);
		if (deck2.created == false) requestclose();
		
		
		deck2.PrepareDeck(random);
		
		decksize1 = deck1.deckcards;
		decksize2 = deck2.deckcards;
	
		
		draw_card(1);
		draw_card(1);
		draw_card(1);
		draw_card(1);
		
		draw_card(2);
		draw_card(2);
		draw_card(2);
		draw_card(2);
		draw_card(2); // initial hands.
		
		user1 = loadUser(name1);
		user2 = loadUser(name2);
		
		announcer();
		message("PRO#"+protocolnum+"#");
		random = new MersenneTwisterFast();
		doPhaseStuff();
	}
	
	
	void announcer() {
		String deck1name = deck1.deckname;
		String deck2name = deck2.deckname;
		
		message("CHA#Announcer#Going first is " + name1 + " using the deck " + deck1name + "#");
		message("CHA#Announcer#And going second is " + name2 + " using the deck " + deck2name + "#");
		
		
		
	}
	CardmasterServerCard getCard(int cardid) {
		//System.out.println("Getting card with id " + cardid);
		CardmasterServerCard newcard = new CardmasterServerCard();
		newcard.copydata(carddata[cardid]);
		return newcard;
		
		
	}
	void loadCardData() {
		carddata = webrunner.cardmaster.CardmasterData.loadCardData();
		
		
		/*new CardmasterServerCard[CardmasterData.NUMBER_OF_CARDS];
		try {
			FileReader reader = new FileReader(CardmasterData.DIRECTORY + "cards.csc");
			BufferedReader in = new BufferedReader(reader);
			
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(inputLine,"#");
				int cardid = Integer.parseInt(token.nextToken());
				 carddata[cardid] = new CardmasterServerCard(inputLine);
				
			//	System.out.println(carddata[cardid]);
				
				
			}
			
			in.close();
			reader.close();
			
			
		}
		catch (Exception e) {
			e.printStackTrace();}	
		*/
		
		
	}
	
	void loadAbilityData() { // Load card abilities.
		ability_intoplay = new String[CardmasterData.NUMBER_OF_CARDS];
		ability_command = new String[CardmasterData.NUMBER_OF_CARDS];
		ability_leaveplay = new String[CardmasterData.NUMBER_OF_CARDS];
		ability_endturn = new String[CardmasterData.NUMBER_OF_CARDS];
		ability_startturn = new String[CardmasterData.NUMBER_OF_CARDS];
		ability_defend = new String[CardmasterData.NUMBER_OF_CARDS];
		ability_attack = new String[CardmasterData.NUMBER_OF_CARDS];
		ability_afterattack = new String[CardmasterData.NUMBER_OF_CARDS];
		for (int i = 0;i<CardmasterData.NUMBER_OF_CARDS;i++) {
			ability_intoplay[i] = "NCODE;";	
			ability_command[i] = "NCODE;";	
			ability_leaveplay[i] = "NCODE;";	
			ability_endturn[i] = "NCODE;";	
			ability_startturn[i] = "NCODE;";
			ability_defend[i] = "NCODE;";
			ability_attack[i] = "NCODE;";
			ability_afterattack[i] = "NCODE;";				
			
		}	
		try {
			FileReader reader = new FileReader(CardmasterData.DIRECTORY + "abilities.csc");
			BufferedReader in = new BufferedReader(reader);
				
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(inputLine,"#");
				int cardid = Integer.parseInt(token.nextToken());
			//	System.out.println("Found ability for card "+  cardid);
				String test = token.nextToken();
			//	System.out.println(test);
				 ability_intoplay[cardid] = test;
				test = token.nextToken();//System.out.println(test);
				 ability_leaveplay[cardid] = test;
				test = token.nextToken();//System.out.println(test);
				 ability_endturn[cardid] = test;
				test = token.nextToken();//System.out.println(test);
				 ability_startturn[cardid] = test;
				 test = token.nextToken();//System.out.println(test);
				 ability_attack[cardid] = test;
				 test = token.nextToken();//System.out.println(test);
				 ability_afterattack[cardid] = test;
				 test = token.nextToken();//System.out.println(test);
				 ability_defend[cardid] = test;
				test = token.nextToken();//System.out.println(test);
				 ability_command[cardid] = test;
			//	System.out.println(carddata[cardid]);
				
				
			}
			in.close();
			reader.close();
				
				
		}
		catch (Exception e) {
				e.printStackTrace();
				}		
		
		
		// Gigas SECRET ability file takes precidence.
		try {
			FileReader reader = new FileReader(CardmasterData.DIRECTORY + "abilities_gigas.csc");
			BufferedReader in = new BufferedReader(reader);
				
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				StringTokenizer token = new StringTokenizer(inputLine,"#");
				int cardid = Integer.parseInt(token.nextToken());
			//	System.out.println("Found ability for card "+  cardid);
				String test = token.nextToken();
			//	System.out.println(test);
				if (!test.equals("NCODE;"))
				 	ability_intoplay[cardid] = test;
				test = token.nextToken();//System.out.println(test);
				 if (!test.equals("NCODE;"))
				 	ability_leaveplay[cardid] = test;
				test = token.nextToken();//System.out.println(test);
				 if (!test.equals("NCODE;"))
				 	ability_endturn[cardid] = test;
				test = token.nextToken();//System.out.println(test);
				 
				 if (!test.equals("NCODE;"))
				 	ability_startturn[cardid] = test;
				 
				 test = token.nextToken();//System.out.println(test);
				 
				 if (!test.equals("NCODE;"))
				 	ability_attack[cardid] = test;
				 test = token.nextToken();//System.out.println(test);
				 
			
				 
				 if (!test.equals("NCODE;"))
				 	ability_afterattack[cardid] = test;
				 test = token.nextToken();//System.out.println(test);
				 
				 if (!test.equals("NCODE;"))
				 	ability_defend[cardid] = test;
				
				
				test = token.nextToken();//System.out.println(test);
				
				if (!test.equals("NCODE;"))
				 	ability_command[cardid] = test;
			//	System.out.println(carddata[cardid]);
				
				
			}
			in.close();
			reader.close();
				
				
		}
		catch (Exception e) {
				e.printStackTrace();
				}
		
		
		
	}
	
	void nextPhase(String name) {
		int no;
		if (name.equals(name1)) {
			no = 1;
		} else { no = 2; }
	
		if (waitcards1 >0) {
			if (waitcards1 > numCardsInHand(1)) waitcards1 = numCardsInHand(1);
				
		 		message("MDN#1#" + waitcards1 + "#",player_named(1));
			return;
			
		}
		if (waitcards2 >0) {
			if (waitcards2 > numCardsInHand(2)) waitcards2 = numCardsInHand(2);
				 message("MDN#2#" + waitcards2 + "#",player_named(2));
			return; // cant change phase when waiting for discard
		
		
		}
			if (abilities== null)  return;
		if (abilities.length > 0 ){
				message("ABR#" + bToI(p1abilityresolve) + "#" + bToI(p2abilityresolve) + "#" + abilities.length + "#");
		
			 return;
			}
		if (no == playerphase && ( phase != 4 && phase != 5 )) {
			
			goNextPhase();	
			while (skipturn) goNextPhase();
			
		}
		else if (no != playerphase && ( phase == 4 || phase == 5)) {
			goNextPhase();	
			while (skipturn) goNextPhase();
			
		}
		else return;
	
	}	
	

	boolean HasInvisible(CardmasterServerCard card) {
		if (card == null) return false; if (card.dummy) return false;
		if (Spiked(card)) return false;
			if (HasAntiSkill(card,"inv")) return false;
		if (card.invis) return true;
		if (isActivatedConstant("MINVS",owner(card),card)) return true;
		if (card.GetStatusValue("invis") >= 1) return true;
		
		if (card.GetStatusValue("invturn") >= 1) return true;
		if (card.GetStatusValue("invtemp") >= 1) return true;
		if (card.GetStatusValue("invperm") >= 1) return true;
		
		
		return false;
	}
	
	
	boolean Spiked(CardmasterServerCard card) {
		if (card == null) return false; if (card.dummy) return false;
		if (card.GetStatusValue("nouse") >=1) return true;
		//	if (isActivatedConstant("MNOUS",owner(card),card)) return true;
	
		if (card.GetStatusValue("nousperm") >= 1) return true;
		if (card.GetStatusValue("noustemp") >= 1) return true;
			else return false;
	}

	boolean HasAntiSkill(CardmasterServerCard card,String skill)  {
		String constskill = "ABIX";
		String statusskill = "abix";
		if (skill == "abim") {
			constskill = "ABIX";
			statusskill = "abix";
		}
		else if (skill == "spim") {
			constskill = "SPIX";
			statusskill = "spix";
		}else if (skill == "quick") {
			constskill = "QUIX";
			statusskill = "quicx";
		}
		else if (skill == "entr") {
			constskill = "ENTX";
			statusskill = "entx";
		}else if (skill == "inv") {
			constskill = "INVX";
			statusskill = "invx";
		}
		else if (skill == "radd") {
			constskill = "RADX";
			statusskill = "radx";
		}else if (skill == "decoy") {
			constskill = "DCYX";
			statusskill = "decyx";
		}else return false;
		if (card == null) return false; if (card.dummy) return true;
		if (Spiked(card)) return  true;;
		if (isActivatedConstant("M"+constskill,owner(card),card)) return true;
		if (card.GetStatusValue(statusskill) >=1) return true;
		if (card.GetStatusValue(statusskill+"temp") >=1) return true;
		if (card.GetStatusValue(statusskill+"turn") >=1) return true;
		return false;
		
	}
	
	
		
	boolean HasSpellImmune(CardmasterServerCard card) {
		if (card == null) return false; if (card.dummy) return false;
		if (Spiked(card)) return false;
	
		if (HasAntiSkill(card,"spim")) return false;
		if (card.spellimmune) return true;
		if (isActivatedConstant("MSPIM",owner(card),card)) return true;
		
		
		if (card.GetStatusValue("spim") >=1) return true;
		if (card.GetStatusValue("spimtemp") >=1) return true;
		if (card.GetStatusValue("spimturn") >=1) return true;
		
		return false;
	}
	boolean HasAlly(CardmasterServerCard card) {
			if (card == null) return false; if (card.dummy) return false;
		if (Spiked(card)) return false;
			if (HasAntiSkill(card,"ally")) return false;
			
		if (card.flag("ally")) return true;
		if (isActivatedConstant("MALLY",owner(card),card)) return true;
		
		
		if (card.GetStatusValue("ally") >=1) return true;
		if (card.GetStatusValue("allytemp") >=1) return true;
		if (card.GetStatusValue("allyturn") >=1) return true;
		return false;
	
	}
	boolean HasAllTypes(CardmasterServerCard card) {
		
		if (card == null) return false; if (card.dummy) return false;
		if (Spiked(card)) return false;
	
		if (card.flag("alltype")) return true;
		if (isActivatedConstant("MALTY",owner(card),card)) return true;
		if (card.GetStatusValue("allty") >=1) return true;
		if (card.GetStatusValue("alltytemp") >=1) return true;
		if (card.GetStatusValue("alltyturn") >=1) return true;
		return false;
	}
	
	boolean HasWallTypes(CardmasterServerCard card) {
		
		if (card == null) return false; if (card.dummy) return false;
		if (Spiked(card)) return false;
		if (card.flag("waltype")) return true;
		if (isActivatedConstant("MWLTY",owner(card),card)) return true;
		if (card.GetStatusValue("walty") >=1) return true;
		if (card.GetStatusValue("waltytemp") >=1) return true;
		if (card.GetStatusValue("waltyturn") >=1) return true;
		return false;
	}
	boolean HasRaddEffect(CardmasterServerCard card) {
		
		if (card == null) return false; if (card.dummy) return false;
		if (Spiked(card)) return false;
			if (HasAntiSkill(card,"radd")) return false;
		if (card.flag("radd")) return true;
		if (isActivatedConstant("MRADD",owner(card),card)) return true;
		if (card.GetStatusValue("radd") >=1) return true;
		if (card.GetStatusValue("raddtemp") >=1) return true;
		if (card.GetStatusValue("raddturn") >=1) return true;
		return false;
	}
	boolean HasQuickHit(CardmasterServerCard card) {
		if (card == null) return false; if (card.dummy) return false;
		if (Spiked(card)) return false;
			if (HasAntiSkill(card,"quick")) return false;
		if (card.flag("quick")) return true;
		if (isActivatedConstant("MQUIK",owner(card),card)) return true;
		if (card.GetStatusValue("quick") >=1) return true;
		if (card.GetStatusValue("quicktemp") >=1) return true;
		if (card.GetStatusValue("quickturn") >=1) return true;
		return false;
	}
	boolean HasBoss(CardmasterServerCard card) {
			if (card == null) return false; if (card.dummy) return false;
		if (Spiked(card)) return false;
		
			if (HasAntiSkill(card,"boss")) return false;
		if (card.flag("boss")) return true;
		if (isActivatedConstant("MBOSS",owner(card),card)) return true;
		if (card.GetStatusValue("boss") >=1) return true;
		if (card.GetStatusValue("bosstemp") >=1) return true;
		if (card.GetStatusValue("bossturn") >=1) return true;
		return false;
		
	}
	
	
	boolean HasOblitImmune(CardmasterServerCard card) {
			if (card == null) return false; if (card.dummy) return false;
		if (Spiked(card)) return false;
		
			if (HasAntiSkill(card,"obim")) return false;
		if (card.flag("obim")) return true;
		if (isActivatedConstant("MOBIM",owner(card),card)) return true;
		if (card.GetStatusValue("obim") >=1) return true;
		if (card.GetStatusValue("obimtemp") >=1) return true;
		if (card.GetStatusValue("obimturn") >=1) return true;
		return false;
		
	}
	
	boolean HasAbilityImmune(CardmasterServerCard card) {
		if (card == null) return false; if (card.dummy) return false;
		if (Spiked(card)) return false;
		
			if (HasAntiSkill(card,"abim")) return false;
		if (card.abilityimmune) return true;
		if (isActivatedConstant("MABIM",owner(card),card)) return true;
		if (card.GetStatusValue("abim") >=1) return true;
		if (card.GetStatusValue("abimtemp") >=1) return true;
		if (card.GetStatusValue("abimturn") >=1) return true;
		return false;
	}
	
	boolean HasCannotSac(CardmasterServerCard card) {
		if (card == null) return false; if (card.dummy) return false;
		if (Spiked(card)) return false;
		
		if (card.flag("xsac")) return true;
		if (isActivatedConstant("MXSAC",owner(card),card)) return true;
		if (card.GetStatusValue("xsac") >=1) return true;
		if (card.GetStatusValue("xsactemp") >=1) return true;
		if (card.GetStatusValue("xsacturn") >=1) return true;
		if (card.GetStatusValue("charm")>=1) return true;
		return false;
	}
	
	
	boolean HasCannotAttack(CardmasterServerCard card) {
		if (card == null) return false; if (card.dummy) return false;
		if (Spiked(card)) return false;
		if (card.flag("wall")) return true;
		if (card.flag("xatk")) return true;
	//	if (ismType(card,"wall")) return true;
		if (isActivatedConstant("MXATK",owner(card),card)) return true;
		if (card.GetStatusValue("xatk") >=1) return true;
		if (card.GetStatusValue("xatktemp") >=1) return true;
		if (card.GetStatusValue("xatkturn") >=1) return true;
		return false;
	}
	
	boolean HasCannotDefend(CardmasterServerCard card) {
		if (card == null) return false; if (card.dummy) return false;
		if (Spiked(card)) return false;
		
		if (card.flag("xdef")) return true;
		
		if (isActivatedConstant("MXDEF",owner(card),card)) return true;
		if (card.GetStatusValue("xdef") >=1) return true;
		if (card.GetStatusValue("xdeftemp") >=1) return true;
		if (card.GetStatusValue("xdefturn") >=1) return true;
		return false;
	}
	
	boolean HasEntrenching(CardmasterServerCard card) {
		if (card == null) return false; if (card.dummy) return false;
		if (Spiked(card)) return false;
			if (HasAntiSkill(card,"ent")) return false;
		if (card.flag("ent")) return true;
		if (isActivatedConstant("MENTR",owner(card),card)) return true;
		if (card.flag("fdownen") && card.GetStatusValue("facedown") >=1) return true;
		if (isActivatedConstant("MENFD",owner(card),card) && card.GetStatusValue("facedown") >=1) return true;
		if (card.GetStatusValue("entrench") >=1) return true;
		if (card.GetStatusValue("enttemp") >=1) return true;
		if (card.GetStatusValue("entturn") >=1) return true;
		
		return false;
	}

	boolean HasDecoying (CardmasterServerCard card) {
		if (card == null) return false; if (card.dummy) return false;
		if (Spiked(card)) return false;
			if (HasAntiSkill(card,"decoy")) return false;
		if (card.flag("decoy")) return true;
		if (isActivatedConstant("MDCOY",owner(card),card)) return true;
		
		if (card.GetStatusValue("decoy") >=1) return true;
		if (card.GetStatusValue("decoytemp") >=1) return true;
		if (card.GetStatusValue("decoyturn") >=1) return true;
		return false;
	}
	boolean decoy_in_play() {
		for (int i=0;i<5;i++) {
			if (HasDecoying(m1[i])) return true;
			if (HasDecoying(m2[i])) return true;	
		
		
		
		}
		
		return false;
	}
	
	boolean can_target_with_spell(CardmasterServerCard card) {
		if (card.typecode.equals("m")) {
		
			if (decoy_in_play() && !HasDecoying(card)) return false;
			if (HasSpellImmune(card)) return false;
		
		}
		return true;
	}	
	void attack_command(String playername, int slot, int eslot, int type) {
		if (type == 1) {
			int value=attack(player_named(playername),slot);
			if (value == 1) message("MES#" + getSlot(player_named(playername),slot,"m").name + " (slot:" + (slot+1) + ") attacking directly");
			///System.out.println("ATTACK ATTEMPT RETURNS: " + value);			
		}
		else if (type == 2) {
			if (HasEntrenching(getSlot(not_player(player_named(playername)),eslot,"m"))) return;
		//	if (getSlot(not_player(player_named(playername)),eslot,"m").GetStatusValue("facedown") >=1) return;
			int value = attack(player_named(playername),slot,eslot);
			if (value == 1) message("MES#" + getSlot(player_named(playername),slot,"m").name + " (slot:" + (slot+1) + ") attacking " +
				getName(getSlot(not_player(player_named(playername)),eslot,"m")) + " (slot:" + (eslot+1) + ")");
			///System.out.println("ATTACK ATTEMPT RETURNS: " + value);			
			
		}
		else if (type == 3) {
			
			int truthvalue = defend(player_named(playername),slot,eslot);

			if (truthvalue==1) message("MES#Defend from " + getSlot(not_player(player_named(playername)),eslot,"m").name+ " (slot:" + (eslot+1) + 
			") using " + getSlot(player_named(playername),slot,"m").name + " (slot:" + (slot+1) + ")");
			
			//System.out.println("DEFEND ATTEMPT RETURNS: "+ truthvalue);
	
		}		
	}
	
	public boolean ismType(CardmasterServerCard card, String type) {
		String cardtype = card.mtype.toLowerCase();
		

		type = type.toLowerCase();
		boolean istype = true;
		if (HasAllTypes(card)) {
			return true;
		}
		if (HasWallTypes(card)) {
			return true;
		}
		if (cardtype.indexOf(type) == -1) istype = false;
		else istype = true;
		return istype;
		
	}
	
	// attack general
	int attack(int player, int slot) {
		if (!(playerphase == player && phase == PHASE_ATTACK)) return -1;
		if (player == 1) {
			if (m1[slot].dizzy || m1[slot].dummy) return -2;
		
		/*
			if (ismType(m1[slot],"wall")) {return -3;
			}
			if (m1[slot].flag("wall")) return -3;
		*/
			if (HasCannotAttack(m1[slot])) return -3;
			if(isActivatedConstant("MNATK",player,m1[slot])) return -3;
			if (m1[slot].GetStatusValue("khrima") == 1) return -4;
			if (attacker[slot+1] > 0 && attacker[slot+1]<6) { 
				attacker[defender[attacker[slot+1]]] = 0;
				defender[attacker[slot+1]] = 0; 
				defenderlock[attacker[slot+1]] = false;} // reset defender
			attacker[slot+1] = 6;		
			if (m1[slot].GetStatusValue("facedown") >=1) {
				m1[slot].SetStatusValue("facedown",0);
				MessageSTC();
			}		
			return 1;
		}	
		else if (player == 2) {
			if (m2[slot].dizzy || m2[slot].dummy) return -2;
		if (HasCannotAttack(m2[slot])) return -3;
			if(isActivatedConstant("MNATK",player,m2[slot])) return -3;
			if (m2[slot].GetStatusValue("khrima") == 1) return -4;
			if (attacker[slot+1] > 0 && attacker[slot+1]<6) { 
				attacker[defender[attacker[slot+1]]] = 0;
				defender[attacker[slot+1]] = 0;  
				defenderlock[attacker[slot+1]] = false;} // reset defender
			attacker[slot+1] = 6;
			if (m2[slot].GetStatusValue("facedown") >=1) {
				m2[slot].SetStatusValue("facedown",0);		
				MessageSTC();
			}
			return 1;
		}
		return 0;	
	}
	
	//attack monster
	int attack(int player, int slot, int eslot) {
		if (slot >=5 || eslot >=5 || slot < 0 || eslot < 0) return -1;
		if (!(playerphase == player && phase == PHASE_ATTACK)) return -1;
		if (player == 1) {
			if (m1[slot].dizzy || m1[slot].dummy) return -2;
		if (HasCannotAttack(m1[slot])) return -3;
			if(isActivatedConstant("MNATK",player,m1[slot])) return -3;
			if (m1[slot].GetStatusValue("khrima") == 1) return -5;
			if (m2[eslot].dummy) return -4;
			if (attacker[slot+1] > 0 && attacker[slot+1]<6) { 
				attacker[defender[attacker[slot+1]]] = 0; 
				defender[attacker[slot+1]] = 0;
			    defenderlock[attacker[slot+1]] = false; } // reset defender
			attacker[slot+1] = eslot+1;
			defender[eslot+1] = slot+1 ;	
			defenderlock[eslot+1] = true;// lock defender, so they cant change
			if (m1[slot].GetStatusValue("facedown") >=1) {
				m1[slot].SetStatusValue("facedown",0);
				MessageSTC();
			}
			return 1;
		}	
		else if (player == 2) {
			if (m2[slot].dizzy || m2[slot].dummy) return -2;
		if (HasCannotAttack(m2[slot])) return -3;
			if (m1[eslot].dummy) return-4;
			if(isActivatedConstant("MNATK",player,m2[slot])) return -3;
			if (m2[slot].GetStatusValue("khrima") == 1) return -5;
			if (attacker[slot+1] > 0 && attacker[slot+1]<6) { 
			 attacker[defender[attacker[slot+1]]] = 0; 
			 defender[attacker[slot+1]] = 0; 
			 defenderlock[attacker[slot+1]] = false; } // reset defender
			attacker[slot+1] = eslot+1;
			defender[eslot+1] = slot+1;	 
			defenderlock[eslot+1] = true;// lock defender, so they cant change
			if (m2[slot].GetStatusValue("facedown") >=1) {
				m2[slot].SetStatusValue("facedown",0);	
				MessageSTC();
			}
			return 1;
		}
		return 0;
					
		
		
	}
	void cancelattack(int player) {
		if (playerphase == player && phase == PHASE_ATTACK ) {
			message("MES#" + player_named(player)+ "'s attacks reset!#" );
			for (int i=0;i<6;i++) {
				if (attacker[i] != 6 )defenderlock[attacker[i]] = false;
				if (attacker[i] != 6)   defender[attacker[i]] = 0;
				attacker[i] = 0;
				
			}
		}
			if (playerphase != player && phase == PHASE_DEFEND ) {
			message("MES#" + player_named(player)+ "'s defenders reset!#" );
			for (int i=0;i<6;i++) {
				if (!defenderlock[i]) {
					attacker[defender[i]] = 6;
					defender[i] = 0;
				}
				
			
			}
			message("ATD#" + playerphase + "#" + attacker[1]+ "#" + attacker[2]+ "#" + attacker[3]+ "#" + attacker[4]+ "#" + attacker[5] + "#");
   
		}
	     
	}
	//Defend 
	int defend(int player, int slot, int eslot) {
		if (eslot >= 6) return -555;
		if (!(playerphase != player && phase == PHASE_DEFEND))  { 
		//System.out.println("not defending because incorrect phase or player"); 
		return -1; }
		CardmasterServerCard cards[] = m1;
		if (player == 1) {
			if (m1[slot].dummy) return -2;
			if (m2[eslot].dizzy) return -3;
			if (m1[slot].dizzy) return -4;
			if (m2[eslot].dummy) return -5;
			if (HasCannotDefend(m1[slot])) return -33;
			cards = m1;
		 	
		 	if (HasInvisible(m2[eslot]) && ! HasInvisible(m1[slot])) return -666;
		 	if (containscolor(m2[eslot].sneak,m1[slot].colorcode)) return -667;
		 	if (m2[eslot].flag("greywalk") ) {
		 		boolean greyeffect = false;
		 		for (int i=0;i<5;i++) {
		 			if (containscolor(e1[i].colorcode,8)) greyeffect = true;
		 		}
		 		if (greyeffect) return -668;
		 		
		 		
		 	}
		 //	if (containscolor(m1[slot].sneak,m2[eslot].colorcode)) return -667;
		 	
		} else if (player == 2) {
			if (m2[slot].dummy) return -2;
			if (m1[eslot].dizzy) return -3;
			if (m2[slot].dizzy) return -4;
			if (m1[eslot].dummy) return -5;
			if (HasCannotDefend(m2[slot])) return -33;
			cards = m2;
		 	
		 	if (HasInvisible(m1[eslot]) && ! HasInvisible(m2[slot])) return -666;
		 	if (containscolor(m1[eslot].sneak,m2[slot].colorcode)) return -667;	
		 	if (m1[eslot].flag("greywalk") ) {
		 		boolean greyeffect = false;
		 		for (int i=0;i<5;i++) {
		 			if (containscolor(e2[i].colorcode,8)) greyeffect = true;
		 		}
		 		if (greyeffect) return -668;
		 		
		 		
		 	}
		 	
		 	
		 //	if (containscolor(m2[slot].sneak,m1[eslot].colorcode)) return -667;	
		}
		
			if (defenderlock[slot+1] && attacker[defender[slot+1]] != (slot+1)) defenderlock[slot+1] = false;
			if (defenderlock[slot+1]) { 
		//System.out.println("Locked");
		 return -400; } // defender is locked
			
			if (attacker[eslot+1] == 0){ 
		//System.out.println("Not attacking");
		return -300; } // chosen defender isn't attacking 
			if (defender[slot+1] > 0) {attacker[defender[slot+1]] = 6;} // reset attacker.
			defender[slot+1] = eslot+1;
			attacker[eslot+1] = slot+1;
			
			
			if (cards[slot].GetStatusValue("facedown") >=1) {
				cards[slot].SetStatusValue("facedown",0);
				MessageSTC();
			}
			//System.out.println("Will defend");
			return 1;

	
	}
	int heal_player(int player, int amount) {
		alltriggeredabilities(null,"TP" + player + "H");
		if (player == 1) {
			p1life+= amount;
			message("MES#" + name1 + " gains " + amount + " life points!");
				
			
			
			return p1life;
		}
		else if (player == 2) {
			p2life += amount;
			message("MES#" + name2 + " gains " + amount + " life points!");
			return p2life;	
		}
		return 700;
		
	}
	int damage_player(int player, int amount) {
		if (amount < 0) return heal_player(player,-amount);
		
		if (amount == 0) {
			String name = name1;
			int life = p1life;
			if (player ==2) { name = name2; life = p2life; }
			message("MES#" + name + " takes zero damage.");
			return life;
			
		}
	//	System.out.println("Damaging player " +player + " for amount" + amount);
		
			if (isActivatedConstant("PMODD",player,null)) {
				amount = (int)((double)amount * tempcount2);
				
				}
			
		
		
		
		if (player ==1) { p1life-= amount; checkGifts();
		message("MES#" + name1 + " takes " + amount + " damage!");
		
		alltriggeredabilities(null,"TP1L");
		return p1life;}
		else if (player==2) { p2life-= amount; checkGifts();
			alltriggeredabilities(null,"TP2L");
		 message("MES#" + name2 + " takes " + amount + " damage!");return p2life; }
		//System.out.println("ERROR: DAMAGE_PLAYER");
		return 999;
		

		
	}
	
	void setMana(String mcolor, int player, int amount) {
		int diff = 0;
	//	int player;
		if (amount <0) amount = 0;
		if (player == 1 && mcolor.equals("D")) {
			diff = amount - p1D;
			p1D = amount;
			
			
		}
		else if (player == 1 && mcolor.equals("L")) {
			diff = amount - p1L;
			p1L = amount;
		
			
		}
		else if (player == 1 && mcolor.equals("G")) {
			diff = amount - p1G;
			p1G = amount;
			
			
		}
		else if (player == 2 && mcolor.equals("D")) {
			diff = amount - p2D;
			p2D = amount;
		
			
		}
		else if (player == 2 && mcolor.equals("L")) {
			diff = amount - p2L;
			p2L = amount;
		
			
		}
		else if (player == 2 && mcolor.equals("G")) {
			diff = amount - p2G;
			p2G = amount;
			
		}
		if (diff > 0) alltriggeredabilities(null,"TP1G");
		if (diff < 0) alltriggeredabilities(null,"TP1N");
	}
	
	void remove_from_play(CardmasterServerCard card) {
	
		for (int i=0;i<5;i++) {
			if (m1[i] == card) remove_from_play(1,"m",i);	
			else if (m2[i] == card) remove_from_play(2,"m",i);	
			else if (e1[i] == card) remove_from_play(1,"e",i);	
			else if (e2[i] == card) remove_from_play(2,"e",i);	
		}
		
	}
	void destroy_card(CardmasterServerCard card) {
		if (card == null) return;
		if (card.dummy) return;
		for (int i=0;i<5;i++) {
			if (m1[i] == card) destroy_card(1,"m",i);	
			else if (m2[i] == card) destroy_card(2,"m",i);	
			else if (e1[i] == card) destroy_card(1,"e",i);	
			else if (e2[i] == card) destroy_card(2,"e",i);	
		}
			
		
	}
	void cb_destroy_card(int player,String type, int slot) {
		getSlot(player,slot,type).markfordeath = true;
		
		}
		
	void destroy_marked_cards() {
		for (int i =0;i<5;i++) {
			if (m1[i] != null) if (!m1[i].dummy) if (m1[i].markfordeath) destroy_card(m1[i]);
			if (m2[i] != null) if (!m2[i].dummy) if (m2[i].markfordeath) destroy_card(m2[i]);
			if (e1[i] != null) if (!e1[i].dummy) if (e1[i].markfordeath) destroy_card(e1[i]);
			if (e2[i] != null) if (!e2[i].dummy) if (e2[i].markfordeath) destroy_card(e2[i]);
			
			
		}
		
	}
	void remove_from_play(int player, String type, int slot) {
			destroy_modifier(player,type,slot);
			if (player != playerphase && type.equals("m") && defender[slot+1] > 0) {
			attacker[defender[slot+1]] = 6;
			defender[slot+1] = 0;
			defenderlock[slot+1] = false;
			fix_attackers();
			message("ATD#" + playerphase + "#" + attacker[1]+ "#" + attacker[2]+ "#" + attacker[3]+ "#" + attacker[4]+ "#" + attacker[5] + "#");

		}
		if (player == playerphase && type.equals("m") && attacker[slot+1] > 0) {
			if (attacker[slot+1] < 6) {
			
				defender[attacker[slot+1]] = 0; 
				defenderlock[attacker[slot+1]] = false;
			
			}
			for (int i=0;i<6;i++)
				if (defender[i] == (slot+1)) {defender[i] = 0; defenderlock[i] = false;}
			attacker[slot+1] = 0;
			fix_attackers();
			message("ATD#" + playerphase + "#" + attacker[1]+ "#" + attacker[2]+ "#" + attacker[3]+ "#" + attacker[4]+ "#" + attacker[5] + "#");

			
		}
		int cardid = getSlot(player,slot,type).cardid;
//System.out.println("REMOVING " +getSlot(player,slot,type) + " FROM PLAY");
		leaveplay_ability(player, slot, type, "OBLIT");
		message("MES#" + getName(carddata[cardid]) + " is obliterated!");
		 alltriggeredabilities(getSlot(player,slot,type),"TC" + type + player +"~");
		 getSlot(player,slot,type).copydata(new CardmasterServerCard());
	}
	
	void destroy_modifier(int player,String type, int slot) {
		CardmasterServerCard card = getSlot(player,slot,type);
		if (card == null) return;
		if (card.dummy) return;
		if (GetModifierCard(card) == null) return;
		if (GetModifierCard(card).dummy) return;
		int cardid = GetModifierCard(card).cardid;
		String cardname = getName(GetModifierCard(card));
		to_graveyard(GetModifierCard(card));
		
		boolean secondlife = isActivatedConstant("M2ndL",player,getSlot(player,slot,"d"+type));
		
		boolean alwaysrundeaththing = getSlot(player,slot,"d"+type).flag("chaos");
		
		if (!secondlife || alwaysrundeaththing) leaveplay_ability(player, slot, "d"+type, "START");
		
		message("MES#" + cardname + " is destroyed!");
		getSlot(player,slot,"d"+type).markfordeath = false;
		if (! getSlot(player,slot,type).token) alltriggeredabilities(getSlot(player,slot,"d"+type),"TC" + "d" + player + "K");
		if (secondlife) return_to_hand(player, getSlot(player,slot,type));
		boolean nodeath = getSlot(player,slot,"d"+type).nodeath;
		
		if (!secondlife) to_graveyard(GetModifierCard(card));
		
	}
	
	void destroy_card(int player, String type, int slot) {
		destroy_modifier(player,type,slot);
		if (player != playerphase && type.equals("m") && defender[slot+1] > 0) {
			attacker[defender[slot+1]] = 6;
			defender[slot+1] = 0;
			defenderlock[slot+1] = false;
			fix_attackers();
			message("ATD#" + playerphase + "#" + attacker[1]+ "#" + attacker[2]+ "#" + attacker[3]+ "#" + attacker[4]+ "#" + attacker[5] + "#");

		}
		if (player == playerphase && type.equals("m") && attacker[slot+1] > 0) {
			if (attacker[slot+1] < 6) {
			
				defender[attacker[slot+1]] = 0; 
				defenderlock[attacker[slot+1]] = false;
			
			}
			for (int i=0;i<6;i++)
				if (defender[i] == (slot+1)) {defender[i] = 0; defenderlock[i] = false;}
			attacker[slot+1] = 0;
			fix_attackers();
			message("ATD#" + playerphase + "#" + attacker[1]+ "#" + attacker[2]+ "#" + attacker[3]+ "#" + attacker[4]+ "#" + attacker[5] + "#");

			
		}	
		int cardid = getSlot(player,slot,type).cardid;
		boolean secondlife = isActivatedConstant("M2ndL",player,getSlot(player,slot,type));
		
		boolean alwaysrundeaththing = getSlot(player,slot,type).flag("chaos");
		
		if (!secondlife || alwaysrundeaththing) leaveplay_ability(player, slot, type, "START");
		if (
			getSlot(player,slot,type).GetStatusValue("facedown") > 0
			) {
				return_card_info(cardid,player_named(not_player(player)));
				return_card_info(cardid,"$obs$");
			}
		message("MES#" + getName(carddata[cardid]) + " is destroyed!");
		getSlot(player,slot,type).markfordeath = false;
		if (! getSlot(player,slot,type).token) alltriggeredabilities(getSlot(player,slot,type),"TC" + type + player + "K");
		if (secondlife) return_to_hand(player, getSlot(player,slot,type));
		boolean nodeath = getSlot(player,slot,type).nodeath;
		
		if (!secondlife) to_graveyard(getSlot(player,slot,type));
	
		
		
	}
	boolean endedonce = false;
	void endduel (int player) {
		
		message("VICTORYBY#" + duelendedbycard + "#" + duelendedbytype + "#");
		if (endedonce)  return;
		endedonce = true;
		if (!ended) {
			skipturn = false;
			skipturn1 = 0;
			skipturn2 = 0;
			
			do_end_game_abilities();
			do_win_game_abilities(player);
			do_lose_game_abilities(not_player(player));
			boolean doublepoints = false;
		if (isActivatedConstant("PDOUB",player,null)) {
			doublepoints = true;
		}
		//loadUserData();
		String winner = null;
		String loser = null;
		ended = true;
		if (player==1) { winner = name1; loser = name2; }
		else if (player==2) { winner = name2; loser = name1; }
		System.out.println(winner + " defeats " + loser + " using " + duelendedbycard + " " + duelendedbytype);
		
		if (!((winner == null) || (loser == null))) {
		CardmasterUser winuser = loadUser(winner);
		CardmasterUser lossuser = loadUser(loser);
		
	
	/*	double regular_points = 75;
		double loserRatio = (double)(lossuser.wins);
		double winnerRatio = (double)(winuser.wins);
		if (loserRatio < 1) loserRatio = 1;
		if (winnerRatio <1) winnerRatio = 1;
		double bothRatio = loserRatio / (winnerRatio/5);
		int pointswon = (int)(regular_points * bothRatio);
		if (pointswon > 300) pointswon = 300;
		if (pointswon < 50) pointswon = 50;
	//	System.out.println("Points won:" pointswon);
		double otherRatio = winnerRatio / loserRatio;
		int pointsloser = (int)((regular_points / 6) * winnerRatio);
		if (pointsloser <10) pointsloser = 10;
		if (pointsloser >100) pointsloser = 100;
		
		*/
		
		int win_min = 200;
		int win_max = 400;
		int loss_min = 50;
		int loss_max = 200;
		
		int winwins, losewins, winlosses, loselosses;
		if (winuser != null) {winwins = winuser.wins; winlosses = winuser.losses;}
		else { winwins = 0; winlosses = 0;	}
		if (lossuser != null) {losewins = lossuser.wins; loselosses = lossuser.losses;}
		else {losewins = 0; loselosses = 0;	}
		
		
		int pointsloser = 100;
		if (winner != null) pointsloser = loss_min + ( winwins / 2) + (winlosses / 10);
		int pointswon = 300;
		if (winner != null) pointswon = win_min + (losewins) + (loselosses / 5);
		if (pointsloser > loss_max) pointsloser = loss_max;
		if (pointswon > win_max) pointswon = win_max;
		if (doublepoints) pointswon = pointswon*2;
		
		boolean success = 	 CardmasterData.userpatch(loser,"addp",pointsloser,"x") &&
		 CardmasterData.userpatch(winner, "addp",pointswon,"x") &&
		 CardmasterData.userpatch(winner,"addw",1,"x") &&
		 CardmasterData.userpatch(loser,"addl",1,"x");
		//if (!success) System.out.println("ERROR.");
		globalwinner = player;
		globalwon = pointswon;
		globalloser = pointsloser;
		message("WIN#" + player + "#" + pointswon + "#" + pointsloser + "#");
		CardmasterLogManager.WriteLog(winner + "("+pointswon+") defeats " + loser + "("+pointsloser+") using " + duelendedbycard + " - " + duelendedbytype);
	//	winuser.wins += 1;
	//	saveusers();
		}
		//ante
		requestclose();	
		}
		
	}
	int timeoutcounterish = 0;
	public void giveup(String name){ 
		if (name.equals(name1) || name1 == null || name1.equals("")) {
			message("MES#" + name1 + " Has timed out.");
			CardmasterLogManager.WriteLog("Game Timeout: " + name1);
		if (!endedonce && winner == 0) 	duelendedbycard = "None"; 
		if (!endedonce && winner == 0) duelendedbytype = "Timeout";
			timeoutcounterish++;
			if (timeoutcounterish >= 100) endedonce = false;
			endduel(2);
			
			}
		else if (name.equals(name2) || name2 == null || name2.equals("")) {
			message("MES#" + name2 + " Has timed out.");
			
			CardmasterLogManager.WriteLog("Game Timeout: " + name2);
		if (!endedonce && winner == 0) 	duelendedbytype = "Timeout";
			if (!endedonce && winner == 0)  duelendedbycard = "None";
			endduel(1);
				
		}
		else {
		if (!endedonce && winner == 0) 	duelendedbytype = "Timeout"; 
		if (!endedonce && winner == 0) 	duelendedbycard = "None";
			//System.out.println(name1 + " vs " + name2 + "Endduel 0: giveup called with " + name);
			endduel(0);
			
		}
	}
	
	int damage_monster(int player, int slot, int amount) {
		return damage_monster(player, slot, amount,0);	
		
	
	
	}
	double maxofthree(double a, boolean amatters, double b, boolean bmatters, double c, boolean cmatters) {
		double max = 0;
		if (a > max && amatters) max = a;
		if (b > max && bmatters) max = b;
		if (c > max && cmatters) max = c;
		return max;	
		
		
	}
	int damage_amount(CardmasterServerCard card, int colorsource, int amount) {
		
		double dMul = 1.0; // default values
		double gMul = 1.0;
		double lMul = 1.0;
			if (owner(card) == 1 && turndamage1) return 0;
			if (owner(card) == 2 && turndamage2) return 0;
		
			if (isActivatedConstant("MDAMD",owner(card),card)) {
		//		System.out.println("MDAMD " + amount);
				amount = (int)((double)amount / card.tempcount1);
				
			//	System.out.println("/" + card.tempcount1 + " =  " + amount);
				
				card.tempcount1 = 1;
			}
	
		
		
		
		if (card == null) return 0;
		if (card.dummy) return 0;
		if (colorsource == 0) return amount;
		if (card.colorcode == 0) return amount;
		if (!Spiked(card)) {
		
			if (containscolor(colorsource,2)) dMul = card.dDam;
			if (containscolor(colorsource,4)) lMul = card.lDam;
			if (containscolor(colorsource,8)) gMul = card.gDam;
		}			
		double multiplier = maxofthree(dMul,containscolor(colorsource,2),lMul,containscolor(colorsource,4),gMul,containscolor(colorsource,8));
		int result = (int)((double)amount * multiplier);
		
	
		
		if (HasRaddEffect(card) && result != 0) 	result = 1;
		
				
	
				
		
		
		return result;
	}
	
	int prevncheck(CardmasterServerCard card, int amount) {
		
			
			if (card.GetStatusValue("prevnx") == 1 && amount > 0) { // Prevent Next
				amount = 0;
		//		card.SetStatusValue("prevnx",0);
			}
			else if (card.GetStatusValue("prevent") > 0 && amount > 0) { // Prevent Next X damage
				int absorbamount = 0;
				int maxabsorb = card.GetStatusValue("prevent");
				if (maxabsorb >= amount) {
					absorbamount = amount;
				//	card.SetStatusValue("prevent",maxabsorb-absorbamount);
					amount =0;
				}
				else {
					absorbamount = card.GetStatusValue("prevent");
					amount = amount - absorbamount;
				//	card.SetStatusValue("prevent",0);
				}
				
			}
			else if (card.GetStatusValue("prevtn") > 0 && amount > 0) { // prevent next X damage until end of turn
				int absorbamount = 0;
				int maxabsorb = card.GetStatusValue("prevtn");
				if (maxabsorb >= amount) {
					absorbamount = amount;
				//	card.SetStatusValue("prevtn",maxabsorb-absorbamount);
					amount =0;
				}
				else {
					absorbamount = card.GetStatusValue("prevtn");
					amount = amount - absorbamount;
			//		card.SetStatusValue("prevtn",0);
				}
				
			}
			else if (card.GetStatusValue("prevntn") ==1 && amount > 0) { // prevent next damage until end of turn
				amount = 0;
			//	card.SetStatusValue("prevntn",0);
				
			}
			return amount;
			
	}
	
	int damage_monster(int player, int slot, int amount, int colorsource) {
			if (player == 1 && turndamage1) return 0;
			if (player == 2 && turndamage2) return 0;
			//System.out.println("AMOUNT damage: " + amount);
			int origamount = amount;
			CardmasterServerCard card = getSlot(player,slot,"m");
			if (card == null) return 0;
			if (card.dummy) return 0;
			amount = damage_amount(card,colorsource,amount);
			if (HasRaddEffect(card) && amount != 0) amount = 1;
		
				
	
			if (card.GetStatusValue("prevnx") == 1 && amount > 0) { // Prevent Next
				amount = 0;
				card.SetStatusValue("prevnx",0);
			}
			else if (card.GetStatusValue("prevent") > 0 && amount > 0) { // Prevent Next X damage
				int absorbamount = 0;
				int maxabsorb = card.GetStatusValue("prevent");
				if (maxabsorb >= amount) {
					absorbamount = amount;
					card.SetStatusValue("prevent",maxabsorb-absorbamount);
					amount =0;
				}
				else {
					absorbamount = card.GetStatusValue("prevent");
					amount = amount - absorbamount;
					card.SetStatusValue("prevent",0);
				}
				
			}
			else if (card.GetStatusValue("prevtn") > 0 && amount > 0) { // prevent next X damage until end of turn
				int absorbamount = 0;
				int maxabsorb = card.GetStatusValue("prevtn");
				if (maxabsorb >= amount) {
					absorbamount = amount;
					card.SetStatusValue("prevtn",maxabsorb-absorbamount);
					amount =0;
				}
				else {
					absorbamount = card.GetStatusValue("prevtn");
					amount = amount - absorbamount;
					card.SetStatusValue("prevtn",0);
				}
				
			}
			else if (card.GetStatusValue("prevntn") ==1 && amount > 0) { // prevent next damage until end of turn
				amount = 0;
				card.SetStatusValue("prevntn",0);
				
			}
			
			//System.out.println("New damage amount: " +amount);
			int endturnamount = 0;
			int realamount = amount;
			if (getSlot(player,slot,"m").lifemod >=0) {
			
				if (getSlot(player,slot,"m").lifemod >= amount) {
					endturnamount = amount;
					realamount = 0;
				}
				else{
					endturnamount = getSlot(player,slot,"m").lifemod;
					realamount = amount-endturnamount;
				}
			}
		
			int templife = amount- (getSlot(player,slot,"m").lifepoints + card.lifemod);
			card.lifepoints -= realamount;
			card.lifemod -= endturnamount;
			card.healfor = realamount;
			if (amount > 0) alltriggeredabilities(getSlot(player,slot,"m"),"TCM" +  player + "R");		
			message("MES#" + getName(card) + " takes " + amount + " damage!");
			
			
			if ((card.lifepoints+card.lifemod) <= 0) destroy_card(player, "m", slot);
			return templife;
	}
	boolean containscolor(int color, int checkcolor) {
		if (checkcolor == 0) return false;
		if (color == 0) return false;
		if (color == 14) return true;
		if (color == checkcolor) return true;
		if (color == 6 && (checkcolor ==2 || checkcolor==4 )) return true;
		if (color == 10 && (checkcolor ==2 || checkcolor==8 )) return true;
		if (color == 12 && (checkcolor ==4 || checkcolor==8 )) return true;
		return false;	
	}	
	boolean isUnique(CardmasterServerCard card) {
		if (isActivatedConstant("MNUNQ",owner(card),card)) return false;
		if (card.unique) return true;
		if (isActivatedConstant("MUNIQ",owner(card),card)) return true;
		//if (!card.unique) return false;
		return false;		
	}
	
	
	Vector <CardmasterServerCard> CardsMatching(int player, String cat, String cat2, String name, String type, CardmasterServerCard self, AbilityTarget target) {
		Vector <CardmasterServerCard> returnvec = new Vector<CardmasterServerCard>();
		
		int[] players;
		if (cat2.startsWith("b")) {
			players = new int[2];
			players[0] = player;
			players[1] = not_player(player);
		}
		else if (cat2.startsWith("s")) {
			players = new int[1];
			players[0] = player;
		}	
		else if (cat2.startsWith("o")) {
			players = new int[1];
			players[0] = not_player(player);
		}
		else players = new int[0];
		
		String[] types;
		if (cat2.endsWith("m")) {
			types = new String[1];
			types[0] = "m";
		}
		else if (cat2.endsWith("e")) {
			types = new String[1];
			types[0] = "e";
		}
		else if (cat2.endsWith("b")) {
			types = new String[2];
			types[0] = "m";
			types[1] = "e";
		}
		else types = new String[0];
		
		for (int p=0;p<players.length;p++) {
			for (int t=0;t<types.length;t++) {
				
				for (int i=0;i<5;i++) {
				
					if (matchcard(cat,name,type,getSlot(players[p],i,types[t]),self,target)) {
						returnvec.add(getSlot(players[p],i,types[t]));	
					}
				}				
				
			}
			
			
			
		}
		return returnvec;
	}
	
	
	
	
	boolean matchcard(String a, String c, String d, 
		CardmasterServerCard card, CardmasterServerCard self, AbilityTarget target) {
		if (card.dummy) return false;
		if (!card.matchable) return false;
		boolean abs = false;
		boolean selfc = false;
		boolean targ = false;
		boolean matchtype = true;
		boolean matchname = true;
		boolean matchedtype = false;
		boolean matchntype = false;
		boolean matchedname = false;
		boolean match = false;
		boolean isinset = false;
		boolean attackers = false;
		boolean matchcolor = false;
		boolean noself = false;
		boolean checktoken = false;
		boolean token = false;
		boolean abt = false;
		boolean matchcoloranti = false;
		boolean checkunique = false;
		boolean unique = false;
		boolean checkspi = false;
		boolean spellimmune = false;
		boolean checkabi = false;
		boolean abilityimmune = false;
		boolean matchflag = false;
		
		boolean matchsta = false;
		boolean matchstabool = false;
		
		boolean checkabil = false;
		boolean matchedabil = false;
		boolean matchabil = false;
		
		int requireally = 0;
		String matchflagstring= "";
		String matchstatus= "";
		
		
		
		boolean set = false;
		int setn = -1;
		
		int mcolor = 0;
		if (a.equals("all")) { abs = true; selfc = true; }
		else if (a.equals("trg")) { 
			targ = true;
		}
		else if (a.equals("abs")) { abs = true; noself=true; }
		else if (a.equals("abt")) { abs = true; selfc = true; abt = true; }
		else if (a.equals("slf")) { selfc = true; }
		else if (a.equals("atk")) { attackers= true; abs = true; selfc = true; }
		
		else if (a.startsWith("rnd[")) { 
			int pick = Integer.parseInt(a.substring(4));
			isinset = random.nextInt(10) < pick;
			
		}
	
		
		//	System.out.println("isinset: " + isinset + " matchedname: " + matchedname + " matchedtype: " +matchedtype);
		
		if (c.startsWith("!")) {
			c = c.substring(1);
			matchntype = true;
			
		}
		
		if (c.equals("none")) { matchtype = false; matchedtype = true; }
		if (c.equals("atk")) { attackers= true; matchtype = false; matchedtype = true; }
		
		//	System.out.println("isinset: " + isinset + " matchedname: " + matchedname + " matchedtype: " +matchedtype);
		
		
		if (d.equals("none")) { matchname = false; matchedname = true; }
		
		if (d.startsWith("low")) {
			matchname = false; matchedname = true;
			if (!IsLowestAttack(card)) return false;
		}
		
		if (d.startsWith("hasabi")) {
			matchname = false;
			matchedname = true;
			if (!card.ability) return false;
			
			
			
		}

		
		
		
		if (d.startsWith("has[") || d.startsWith("!hs[")) {
			String abiltype = d.substring(4);
			boolean matchb = false;
			
			matchname = false;
			matchedname = true;
			if (abiltype.equals("radd")) {
				matchb = HasRaddEffect(card);
				
			}
			else if (abiltype.equals("radd")) {
				matchb = HasRaddEffect(card);
			}
			else if (abiltype.equals("quik")) {
				matchb = HasQuickHit(card);
			}
			else if (abiltype.equals("boss")) {
				matchb = HasBoss(card);
			}
			else if (abiltype.equals("obim")) {
				matchb = HasOblitImmune(card);
			}
			else if (abiltype.equals("xsac")) {
				matchb = HasCannotSac(card);
			}
			else if (abiltype.equals("xatk")) {
				matchb = HasCannotAttack(card);
			}
			else if (abiltype.equals("xdef")) {
				matchb = HasCannotDefend(card);
			}
			else if (abiltype.equals("entr")) {
				matchb = HasEntrenching(card);
			}
			else if (abiltype.equals("dcoy")) {
				matchb = HasDecoying(card);
			}
			if (d.startsWith("!hs[")) matchb = !matchb;
			
			if (!matchb) return false;
			
			
		}
		
		
		
		if (d.startsWith("notabi")) {
			matchname = false;
			matchedname = true;
			if (card.ability) return false;
			
			
			
		}
		if (d.startsWith("aly[")) {
			matchname = false;
			matchedname = true;
			requireally = Integer.parseInt(d.substring(4));
			int allys = AllyInPlay(owner(self));
			
			
		//	System.out.println("ALY[ TEST: " + allys + " REQUIRES : " + requireally);
			
			if (requireally > allys) return false;
			
			
			
		}
		if (c.startsWith("aly[")) {
			 matchtype = false; matchedtype = true;
			requireally = Integer.parseInt(c.substring(4));
			int allys = AllyInPlay(owner(self));
			
			
			
		//	System.out.println("ALY[ TEST: " + allys + " REQUIRES : " + requireally);
			
			if (requireally > allys) return false;
			
			
			
		}
		if (d.startsWith("flg[")) {
				matchname = false;
			matchedname = true;
			matchflagstring = d.substring(4);
			matchflag = true;
		}
		
		
		if (d.startsWith("sta[")) {
				matchname = false;
			matchedname = true;
			matchstatus = d.substring(4);
			matchsta = true;
			matchstabool = true;
			
		}
			if (c.startsWith("sta[")) {
				matchtype = false;
			matchedtype = true;
			matchstatus = c.substring(4);
			matchsta = true;
			matchstabool = true;
			
		}
		
			if (c.startsWith("st0[")) {
				matchtype = false;
			matchedtype = true;
			matchstatus = c.substring(4);
			matchsta = true;
			matchstabool = false;
			
		}
		
		
				if (d.startsWith("st0[")) {
				matchname = false;
			matchedname = true;
			matchstatus = d.substring(4);
			matchsta = true;
			matchstabool = false;
			
		}
		
		
		
		
		if (c.startsWith("flg[")) {
			 matchtype = false; matchedtype = true;
			matchflagstring = c.substring(4);
			matchflag = true;
		}
		
		//	System.out.println("isinset: " + isinset + " matchedname: " + matchedname + " matchedtype: " +matchedtype);
		
		
		if (d.startsWith("col[")) {
			mcolor = Integer.parseInt(d.substring(4));
			matchname= false; 
			matchedname = true;
			matchcolor = true;
			matchcoloranti = false;
//System.out.println("Color..." + mcolor);
			
		}
		if (d.startsWith("cln[")) { // anti-color.
			mcolor = Integer.parseInt(d.substring(4));
			matchname= false; 
			matchedname = true;
			matchcolor = true;
			matchcoloranti = true;	
			
		}		
		//	System.out.println("isinset: " + isinset + " matchedname: " + matchedname + " matchedtype: " +matchedtype);
		if (c.startsWith("spi")) { // spell immune
			checkspi = true;
			matchtype = false;
			matchedtype = true;
			spellimmune = true;	
		}
		if (c.startsWith("abi")) { // spell immune
			checkabi = true;
			matchtype = false;
			matchedtype = true;
			abilityimmune = true;	
		}
		if (d.startsWith("unq")) {
			checkunique = true;
			matchname = false;
			matchedname = true;
			unique = true;	
		}
		if (d.startsWith("nunq")) {
			checkunique = true;
			matchname = false;
			matchedname = true;
			unique = false;
		}
		if (d.startsWith("tok")) {
			checktoken = true;
			matchname = false;
			matchedname = true;
			token = true;
				
		}
		if (d.startsWith("set[")) {
			set = true;
			matchname = false;
			matchedname = true;
			setn = Integer.parseInt(d.substring(4));
			if (card.expansioncode != setn) return false;
		}
		
		//	System.out.println("isinset: " + isinset + " matchedname: " + matchedname + " matchedtype: "+ matchedtype);
		
		
		if (d.startsWith("ntok")) {
			checktoken = true;
			matchname = false;
			matchedname = true;
			token = false;	
			
			
		}
		
		//	System.out.println("isinset: " + isinset + " matchedname: " + matchedname + " matchedtype: "+ matchedtype);
		
		
		
		//System.out.println("Checktoken..");
		
		
		
		if (checktoken) {
			if (card.token != token) return false;	
			
			
		}
		
		if (matchflag) {
			if (!card.flag(matchflagstring)) return false;
		}
		
		
		if (matchsta) {
			if ((card.GetStatusValue(matchstatus) > 0) != matchstabool) return false;
		}
		
		
		if (checkabi) {
			//if (!(card.typecode.equals("m"))) return false;
			if (abilityimmune != HasAbilityImmune(card)) return false;
			
		}
		if (checkspi) {
			//if (!(card.typecode.equals("m"))) return false;
			if (spellimmune != HasSpellImmune(card)) return false;
			
		}
		if (checkunique) {
			
			if (!(card.typecode.equals("m") || card.typecode.equals("e"))) return false;
			//System.out.println("checkunique: " + card.unique + " "+ unique);
			if (isUnique(card) != unique) return false;
			
			
		}
		//System.out.println("Matchcolor..");
		if (matchcolor) {
			if (!matchcoloranti) {
//System.out.println("MatchColor..." + card.colorcode + " vs " + mcolor);
			
				if (!containscolor(card.colorcode,mcolor)) { 
			//	System.out.println("Nomatch!");
				return false;
				}
				
			}
			else {
			
				if (containscolor(card.colorcode,mcolor)) return false;
				
			}
			
		}
		//System.out.println("Attackers..");
		if (attackers) {
			//System.out.println("Checking attacking..");
			if (!(isAttacking(card))) return false;
			
		}
		//	System.out.println("targe..");
		if (targ) {
			if (target == null) return false;
			if (!target.isCard) return false;
			if (target.getCard() == card) isinset = true;	
			
			
		}
		
		//	System.out.println("isinset: " + isinset + " matchedname: " + matchedname + " matchedtype: "+ matchedtype);
		
		
		if (selfc) {
			if (card == self) isinset = true;	
			if (self == GetModifierParent(card)) isinset = true;
			if (card == GetModifierParent(self)) isinset = true;
			
			
		}
		//	System.out.println("isinset: " + isinset + " matchedname: " + matchedname + " matchedtype: "+ matchedtype);
		
		//System.out.println("Noself..");
		if (noself) {
			if (card == self) return false;
				if (self == GetModifierParent(card)) return false;
			if (card == GetModifierParent(self)) return false;
		}
		if (abs) {
				
				if (card != self) isinset = true;
			
		}
	//	System.out.println("isinset: " + isinset + " matchedname: " + matchedname + " matchedtype: "+ matchedtype);
		
	//	System.out.println("All but target..");
		if (abt) {
			if (target != null)
			if (target.isCard)
			if (target.getCard() == card) return false;		
		}
		if (matchtype) {
			if (!matchntype) {
			
				if (card.typecode.equals("m") || card.typecode.equals("e"))
					if (ismType(card,c)) matchedtype = true;
			}
			else {
				if (card.typecode.equals("m")|| card.typecode.equals("e"))
					if (!ismType(card,c)) matchedtype = true;
			}	
		}
		if (matchname) {
			if (card.name.equals(d)) matchedname = true;
			if ((card.typecode.equals("m") || card.typecode.equals("e")) && card.flag("aka:"+d)) matchedname = true;
			
		}
	//	System.out.println("isinset: " + isinset + " matchedname: " + matchedname + " matchedtype: "+ matchedtype);
		return (isinset && matchedname && matchedtype);

	}
	
	int getvalue(String exp, int countvalue, int player, AbilityTarget targt, CardmasterServerCard self) {
		return getvalue(exp,countvalue,player,targt,self,0);

	}
	//Maxrand: -1 = lowest.  0 = random 2 = max
	int getvalue(String exp, int countvalue, int player, AbilityTarget targt, CardmasterServerCard self, int maxrand) {

		int tplayer = 0;
		if (targt != null)
			if (targt.isPlayer) tplayer = targt.getPlayer();
			
		StringTokenizer exptoken = new StringTokenizer(exp,":");
		if (!exptoken.hasMoreTokens()) return Integer.parseInt(exp);
		String cmd = exptoken.nextToken();
	//	System.out.println(cmd);
	//	System.out.println(exp);
		if (cmd.equals("cnt")) {
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)countvalue * multiplier);
		}
		else if (cmd.equals("pha")) {
			System.out.println("PHA CHECK " + phase);
			return phase;
		}
		else if (cmd.equals("ply")) {
				if (!exptoken.hasMoreTokens()) return 0;
				String ptype = exptoken.nextToken();
				int num = 1;
				if (ptype.equals("ownr")) num= owner(self);
				else if (ptype.equals("opnt")) num= not_player(owner(self));
				else if (ptype.equals("curn")) num = playerphase;
		System.out.println("PLY CHECK " + num);
				return num;
				
			
		}

		else if (cmd.equals("scg")) {
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)spellcostG* multiplier);
		}
		else if (cmd.equals("scl")) {
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)spellcostL* multiplier);
		}
		else if (cmd.equals("scd")) {
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)spellcostD* multiplier);
		}		
		else if (cmd.equals("cid")) { // self cardid
			return (self.cardid);
		}
		else if (cmd.equals("tci")) {
			return (targt.getCard().cardid);
		}
		else if (cmd.equals("trn")) {
				if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)turncount * multiplier);
		
		
		
		}
		
		
		else if (cmd.equals("ply")) {
				if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)playerphase * multiplier);
		}
		
		else if (cmd.equals("rnd")) { //random between two numbers.
			
				
			int offset = getvalue(exptoken.nextToken(),countvalue,player,targt,self); //-100
			int max = getvalue(exptoken.nextToken(),countvalue,player,targt,self); //-1
			if (maxrand == -1) return offset;
			
			max = max  + 1 - offset; // max = -1 + -1 + 100 = 99
			if (maxrand == 1) return max;
			random.nextInt(5);  // stupid randomizer.
			int randno = random.nextInt(max); //randno from 1-41
			int pickedno = randno + offset; // randno from -20 to 20
			if (exptoken.hasMoreTokens()) {
				String multip = exptoken.nextToken();
				if (multip == null) multip = "1.0";
				if (multip == "") multip = "1.0";
				double multiplier =Double.parseDouble(multip);
				pickedno = (int)(((double)pickedno) * multiplier);
			}
			if (CardmasterData.DEBUGMODE) System.out.println("Max: " + max + " offset: " + offset + " randno: " + randno + " pickedno " + pickedno); 
			return pickedno;
		
		
		}
		else if (cmd.equals("rnc")) { // random card number
			random.nextInt(5);
			CardmasterServerCard newCard = null;
			int i = 0;
			while (newCard == null) {
				i = random.nextInt(CardmasterData.NUMBER_OF_CARDS);
				if (i>= CardmasterData.NUMBER_OF_CARDS) continue;
				if (i <=0) continue;
				newCard = carddata[i];
				if (newCard == null) continue;
				if (newCard.dummy) { newCard = null; continue; }
				
			
			}
			return i;
		}
		
		else if (cmd.equals("lif")) { //Lifepoints
			if (!exptoken.hasMoreTokens()) return 0;
			boolean owner = false;
			boolean target = false;
			boolean opon = false;
			String playerd = exptoken.nextToken();
			if (playerd.equals("both")) { owner = true; opon = true; }
			else if (playerd.equals("ownr")) { owner = true; }
			else if (playerd.equals("opon") || playerd.equals("opnt")) { opon = true; }
			else if (playerd.equals("targ")) { target = true; }
			int life = 0;
			if (owner) {
				if (player ==1) {
					life+= p1life;	
				}else { life+= p2life; }	
			}
			if (opon) {
				if (player ==2) {
					life+= p1life;	
				}else { life+= p2life; }				
			}
			if (target) {
				if (tplayer ==1) {
					life+= p1life;	
				}else if (tplayer == 2){ life+= p2life; }				
			}
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)life * multiplier);			
		
			}
		
		else if (cmd.equals("powi")) { //Power if
			if (!exptoken.hasMoreTokens()) return 0;
			boolean owner = false;
			boolean target = false;
			boolean opon = false;
			String playerd = exptoken.nextToken();
			if (playerd.equals("both")) { owner = true; opon = true; }
			else if (playerd.equals("ownr")) { owner = true; }
			else if (playerd.equals("opon") || playerd.equals("opnt")) { opon = true; }
			else if (playerd.equals("targ")) { target = true; }
			int life = 0;
			if (owner) {
			life += PowerInPlay(player);
			}
			if (opon) {
				life += PowerInPlay(not_player(player));			
			}
			if (target) {
				life += PowerInPlay(tplayer);				
			}
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
				int check = Integer.parseInt(exptoken.nextToken());
			if (life < check) multiplier = 0;
			int value = Integer.parseInt(exptoken.nextToken());
			return (int)((double)value * multiplier);			
	}
		
		else if (cmd.equals("alyi")) { //Allies if
			if (!exptoken.hasMoreTokens()) return 0;
			boolean owner = false;
			boolean target = false;
			boolean opon = false;
			String playerd = exptoken.nextToken();
			if (playerd.equals("both")) { owner = true; opon = true; }
			else if (playerd.equals("ownr")) { owner = true; }
			else if (playerd.equals("opon") || playerd.equals("opnt")) { opon = true; }
			else if (playerd.equals("targ")) { target = true; }
			int life = 0;
			if (owner) {
			life += AllyInPlay(player);
			}
			if (opon) {
				life += AllyInPlay(not_player(player));			
			}
			if (target) {
				life += AllyInPlay(tplayer);				
			}
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			int check = Integer.parseInt(exptoken.nextToken());
			int subtract = Integer.parseInt(exptoken.nextToken());
			life = life - subtract;
			if (life < check) multiplier = 0;
			int value = Integer.parseInt(exptoken.nextToken());
			return (int)((double)value * multiplier);			

		
		
		
		
		
		}
		
		else if (cmd.equals("pow")) { //Power
			if (!exptoken.hasMoreTokens()) return 0;
			boolean owner = false;
			boolean target = false;
			boolean opon = false;
			String playerd = exptoken.nextToken();
			if (playerd.equals("both")) { owner = true; opon = true; }
			else if (playerd.equals("ownr")) { owner = true; }
			else if (playerd.equals("opon") || playerd.equals("opnt")) { opon = true; }
			else if (playerd.equals("targ")) { target = true; }
			int life = 0;
			if (owner) {
			life += PowerInPlay(player);
			}
			if (opon) {
				life += PowerInPlay(not_player(player));			
			}
			if (target) {
				life += PowerInPlay(tplayer);				
			}
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)life * multiplier);			
	}
		
		else if (cmd.equals("aly")) { //Allies
			if (!exptoken.hasMoreTokens()) return 0;
			boolean owner = false;
			boolean target = false;
			boolean opon = false;
			String playerd = exptoken.nextToken();
			if (playerd.equals("both")) { owner = true; opon = true; }
			else if (playerd.equals("ownr")) { owner = true; }
			else if (playerd.equals("opon") || playerd.equals("opnt")) { opon = true; }
			else if (playerd.equals("targ")) { target = true; }
			int life = 0;
			if (owner) {
			life += AllyInPlay(player);
			}
			if (opon) {
				life += AllyInPlay(not_player(player));			
			}
			if (target) {
				life += AllyInPlay(tplayer);				
			}
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			
			int subtract = 0;
			if (exptoken.hasMoreTokens()) subtract = Integer.parseInt(exptoken.nextToken());
			life = life - subtract;
			return (int)((double)life * multiplier);			

		}else if (cmd.equals("hnd")) { //Cards in hand
			if (!exptoken.hasMoreTokens()) return 0;
			boolean owner = false;
			boolean target = false;
			boolean opon = false;
			String playerd = exptoken.nextToken();
			if (playerd.equals("both")) { owner = true; opon = true; }
			else if (playerd.equals("ownr")) { owner = true; }
			else if (playerd.equals("opon") || playerd.equals("opnt")) { opon = true; }
			else if (playerd.equals("targ")) { target = true; }
			int v = 0;
			if (owner) {
			 	v+= numCardsInHand(player);
			}
			if (opon) {
				v+= numCardsInHand(not_player(player));			
			}
			if (target) {
				v+= numCardsInHand(tplayer);				
			}
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)v * multiplier);		

		}else if (cmd.equals("grv")) { //Cards in hand
			if (!exptoken.hasMoreTokens()) return 0;
			boolean owner = false;
			boolean target = false;
			boolean opon = false;
			String playerd = exptoken.nextToken();
			if (playerd.equals("both")) { owner = true; opon = true; }
			else if (playerd.equals("ownr")) { owner = true; }
			else if (playerd.equals("opon") || playerd.equals("opnt")) { opon = true; }
			else if (playerd.equals("targ")) { target = true; }
			int v = 0;
			if (owner) {
			 	v+= numgrave(player);
			}
			if (opon) {
				v+= numgrave(not_player(player));			
			}
			if (target) {
				v+= numgrave(tplayer);				
			}
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)v * multiplier);		

		}else if (cmd.equals("tdk")) { //tota lDeck value
			if (!exptoken.hasMoreTokens()) return 0;
			boolean owner = false;
			boolean target = false;
			boolean opon = false;
			String playerd = exptoken.nextToken();
			if (playerd.equals("both")) { owner = true; opon = true; }
			else if (playerd.equals("ownr")) { owner = true; }
			else if (playerd.equals("opon") || playerd.equals("opnt")) { opon = true; }
			else if (playerd.equals("targ")) { target = true; }
			int v = 0;
			if (owner) {
				if (player ==1) {
					v+= decksize1;	
				}else { v+= decksize2; }	
			}
			if (opon) {
				if (player ==2) {
					v+= decksize1;	
				}else { v+= decksize2; }				
			}
			if (target) {
				if (tplayer ==1) {
					v+= decksize1;	
				}else if (tplayer == 2){ v+= decksize2; }				
			}
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)v * multiplier);		

		}else if (cmd.equals("dek")) { //Deck value
			if (!exptoken.hasMoreTokens()) return 0;
			boolean owner = false;
			boolean target = false;
			boolean opon = false;
			String playerd = exptoken.nextToken();
			if (playerd.equals("both")) { owner = true; opon = true; }
			else if (playerd.equals("ownr")) { owner = true; }
			else if (playerd.equals("opon") || playerd.equals("opnt")) { opon = true; }
			else if (playerd.equals("targ")) { target = true; }
			int v = 0;
			if (owner) {
				if (player ==1) {
					v+= deck1.deckcards;	
				}else { v+=deck2.deckcards; }	
			}
			if (opon) {
				if (player ==2) {
					v+= deck1.deckcards;	
				}else { v+= deck2.deckcards; }				
			}
			if (target) {
				if (tplayer ==1) {
					v+= deck1.deckcards;	
				}else if (tplayer == 2){ v+= deck2.deckcards; }				
			}
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)v * multiplier);
		}else if (cmd.equals("mad")) { // D mana value.
			if (!exptoken.hasMoreTokens()) return 0;
			boolean owner = false;
			boolean target = false;
			boolean opon = false;
			String playerd = exptoken.nextToken();
			if (playerd.equals("both")) { owner = true; opon = true; }
			else if (playerd.equals("ownr")) { owner = true; }
			else if (playerd.equals("opon") || playerd.equals("opnt")) { opon = true; }
			else if (playerd.equals("targ")) { target = true; }
			int v = 0;
			if (owner) {
				if (player ==1) {
					v+= p1D;	
					
				}else { v+=p2D; }	
			}
			if (opon) {
				if (player ==2) {
					v+= p1D;	
				}else { v+= p2D; }				

			}
			if (target) {
				if (tplayer ==1) {
					v+= p1D;	
				}else if (tplayer == 2){ v+= p2D; }				
			}
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)v * multiplier);
		} 
	
	
	else if (cmd.equals("mcd")) { // D mana value.
			if (!exptoken.hasMoreTokens()) return 0;
			boolean owner = false;
			boolean target = false;
			boolean opon = false;
			String playerd = exptoken.nextToken();
			if (playerd.equals("both")) { owner = true; opon = true; }
			else if (playerd.equals("ownr")) { owner = true; }
			else if (playerd.equals("opon") || playerd.equals("opnt")) { opon = true; }
			else if (playerd.equals("targ")) { target = true; }
			int v = 0;
			if (owner) {
				if (player ==1) {
					v+= p1D;	
					
				}else { v+=p2D; }	
			}
			if (opon) {
				if (player ==2) {
					v+= p1D;	
				}else { v+= p2D; }				

			}
			if (target) {
				if (tplayer ==1) {
					v+= p1D;	
				}else if (tplayer == 2){ v+= p2D; }				
			}
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			v-=spellcostD;
			return (int)((double)v * multiplier);
		} 
		 
		else if (cmd.equals("mcg")) { // G mana value minus the cost of the current spell
			if (!exptoken.hasMoreTokens()) return 0;
			boolean owner = false;
			boolean target = false;
			boolean opon = false;
			String playerd = exptoken.nextToken();
			if (playerd.equals("both")) { owner = true; opon = true; }
			else if (playerd.equals("ownr")) { owner = true; }
			else if (playerd.equals("opon") || playerd.equals("opnt")) { opon = true; }
			else if (playerd.equals("targ")) { target = true; }
			int v = 0;
			if (owner) {
				if (player ==1) {
					v+= p1G;	
					
				}else { v+=p2G; }	
			}
			if (opon) {
				if (player ==2) {
					v+= p1G;	
				}else { v+= p2G; }				

			}
			if (target) {
				if (tplayer ==1) {
					v+= p1G;	
				}else if (tplayer == 2){ v+= p2G; }				
			}
			if (!exptoken.hasMoreTokens()) return 0;
			v-= spellcostG;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)v * multiplier);
		}
		
		
		
		else if (cmd.equals("mcl")) { // l mana value.
			if (!exptoken.hasMoreTokens()) return 0;
			boolean owner = false;
			boolean target = false;
			boolean opon = false;
			String playerd = exptoken.nextToken();
			if (playerd.equals("both")) { owner = true; opon = true; }
			else if (playerd.equals("ownr")) { owner = true; }
		else if (playerd.equals("opon") || playerd.equals("opnt")) { opon = true; }
			else if (playerd.equals("targ")) { target = true; }
			int v = 0;
			if (owner) {
				if (player ==1) {
					v+= p1L;	
					
				}else { v+=p2L; }	
			}
			if (opon) {
				if (player ==2) {
					v+= p1L;	
				}else { v+= p2L; }				

			}
			if (target) {
				if (tplayer ==1) {
					v+= p1L;	
				}else if (tplayer == 2){ v+= p2L; }				
			}
			if (!exptoken.hasMoreTokens()) return 0;
			v-= spellcostL;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)v * multiplier);


		}
				
		
		else if (cmd.equals("mag")) { // G mana value.
			if (!exptoken.hasMoreTokens()) return 0;
			boolean owner = false;
			boolean target = false;
			boolean opon = false;
			String playerd = exptoken.nextToken();
			if (playerd.equals("both")) { owner = true; opon = true; }
			else if (playerd.equals("ownr")) { owner = true; }
			else if (playerd.equals("opon") || playerd.equals("opnt")) { opon = true; }
			else if (playerd.equals("targ")) { target = true; }
			int v = 0;
			if (owner) {
				if (player ==1) {
					v+= p1G;	
					
				}else { v+=p2G; }	
			}
			if (opon) {
				if (player ==2) {
					v+= p1G;	
				}else { v+= p2G; }				

			}
			if (target) {
				if (tplayer ==1) {
					v+= p1G;	
				}else if (tplayer == 2){ v+= p2G; }				
			}
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)v * multiplier);
		}
		
		
		else if (cmd.equals("mal")) { // l mana value.
			if (!exptoken.hasMoreTokens()) return 0;
			boolean owner = false;
			boolean target = false;
			boolean opon = false;
			String playerd = exptoken.nextToken();
			if (playerd.equals("both")) { owner = true; opon = true; }
			else if (playerd.equals("ownr")) { owner = true; }
		else if (playerd.equals("opon") || playerd.equals("opnt")) { opon = true; }
			else if (playerd.equals("targ")) { target = true; }
			int v = 0;
			if (owner) {
				if (player ==1) {
					v+= p1L;	
					
				}else { v+=p2L; }	
			}
			if (opon) {
				if (player ==2) {
					v+= p1L;	
				}else { v+= p2L; }				

			}
			if (target) {
				if (tplayer ==1) {
					v+= p1L;	
				}else if (tplayer == 2){ v+= p2L; }				
			}
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)v * multiplier);


		}else if (cmd.equals("mgc")) {// monster G COST
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();
			int player1 = 0;
			int player2 = 0;
			int val = 0;
	
			boolean effect = false;
			boolean monster = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			if (b.endsWith("e")) { effect=true; }
			

			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val+= getModifiedValue(m1[i],"gco","A",1);}
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { val+= getModifiedValue(m2[i],"gco","A",2);}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val+= getModifiedValue(e1[i],"gco","A",1);}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val+= getModifiedValue(e2[i],"gco","A",2);}						
					
					
				
		
				
			}	
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier = Double.parseDouble(exptoken.nextToken());
			return (int)((double)val * multiplier);








			
			}else if (cmd.equals("mlc")) {// monster G COST
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();
			int player1 = 0;
			int player2 = 0;
			int val = 0;
	
			boolean effect = false;
			boolean monster = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			if (b.endsWith("e")) { effect=true; }
			

			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val+= getModifiedValue(m1[i],"lco","A",1);}
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { val+= getModifiedValue(m2[i],"lco","A",2);}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val+= getModifiedValue(e1[i],"lco","A",1);}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val+= getModifiedValue(e2[i],"lco","A",2);}						
					
					
				
		
				
			}	
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier = Double.parseDouble(exptoken.nextToken());
			return (int)((double)val * multiplier);
			}else if (cmd.equals("mdc")) {// monster G COST
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();
			int player1 = 0;
			int player2 = 0;
			int val = 0;
	
			boolean effect = false;
			boolean monster = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			if (b.endsWith("e")) { effect=true; }
			

			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val+= getModifiedValue(m1[i],"dco","A",1);}
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { val+= getModifiedValue(m2[i],"dco","A",2);}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val+= getModifiedValue(e1[i],"dco","A",1);}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val+= getModifiedValue(e2[i],"dco","A",2);}						
					
					
				
		
				
			}	
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier = Double.parseDouble(exptoken.nextToken());
			return (int)((double)val * multiplier);
		

		}else if (cmd.equals("num")) {// number of
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();
			int player1 = 0;
			int player2 = 0;
			int val = 0;
	
			boolean effect = false;
			boolean monster = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			if (b.endsWith("e")) { effect=true; }
			

			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val++;}
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { val++;}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val++;}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val++;}						
					
					
				
		
				
			}	
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier = Double.parseDouble(exptoken.nextToken());
			return (int)((double)val * multiplier);
			
	}else if (cmd.equals("exi")) {// if exists
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();
			int player1 = 0;
			int player2 = 0;
			int val = 0;
	
			boolean effect = false;
			boolean monster = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			if (b.endsWith("e")) { effect=true; }
			

			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val=1;}
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { val=1;}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val=1;}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val=1;}						
					
					
				
		
				
			}	
			//System.out.println("val: " + val);
			return val;
			
				

		}else if (cmd.equals("exm")) {// if exists multiplier
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			double multiplier = Double.parseDouble(exptoken.nextToken());
			int player1 = 0;
			int player2 = 0;
			int val = 0;
	
			boolean effect = false;
			boolean monster = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			if (b.endsWith("e")) { effect=true; }
			

			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val=1;}
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { val=1;}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val=1;}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val=1;}						
					
					
				
		
				
			}	
			//System.out.println("val: " + val + " multiplier:  " + multiplier);
			return (int)((double)val * multiplier);
			
				

		}
		
		
		else if (cmd.equals("mdc")) {// monster G COST
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();
			int player1 = 0;
			int player2 = 0;
			int val = 0;
	
			boolean effect = false;
			boolean monster = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			if (b.endsWith("e")) { effect=true; }
			

			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val+= m1[i].Dcost;}
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { val+= m2[i].Dcost;}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val+= e1[i].Dcost;}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val+= e2[i].Dcost;}						
					
					
				
		
				
			}	
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier = Double.parseDouble(exptoken.nextToken());
			return (int)((double)val * multiplier);
			
		

		}else if (cmd.equals("mgc")) {// monster L COST
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();
			int player1 = 0;
			int player2 = 0;
			int val = 0;
	
			boolean effect = false;
			boolean monster = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			if (b.endsWith("e")) { effect=true; }
			

			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val+= m1[i].Lcost;}
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { val+= m2[i].Lcost;}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val+= e1[i].Lcost;}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val+= e2[i].Lcost;}						
					
					
				
		
				
			}	
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier = Double.parseDouble(exptoken.nextToken());
			return (int)((double)val * multiplier);
			
		


		}else if (cmd.equals("mgs")) {// monster G SAC
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();
			int player1 = 0;
			int player2 = 0;
			int val = 0;
	
			boolean effect = false;
			boolean monster = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			if (b.endsWith("e")) { effect=true; }
			

			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val+= getModifiedValue(m1[i],"gsa","A",1);}
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) {val+= getModifiedValue(m2[i],"gsa","A",2);}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val+= getModifiedValue(e1[i],"gsa","A",1);}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val+= getModifiedValue(e2[i],"gsa","A",2);}						
					
					
				
		
				
			}	
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier = Double.parseDouble(exptoken.nextToken());
			//System.out.println("MGS: " + (int)((double)val * multiplier));
			return (int)((double)val * multiplier);
			
		



	}else if (cmd.equals("mds")) {// monster G SAC
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();
			int player1 = 0;
			int player2 = 0;
			int val = 0;
	
			boolean effect = false;
			boolean monster = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			if (b.endsWith("e")) { effect=true; }
			

			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val+= getModifiedValue(m1[i],"dsa","A",1);}
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { val+= getModifiedValue(m2[i],"dsa","A",2);}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val+= getModifiedValue(e1[i],"dsa","A",1);}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val+= getModifiedValue(e2[i],"dsa","A",2);}						
					
					
				
		
				
			}	
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier = Double.parseDouble(exptoken.nextToken());
			return (int)((double)val * multiplier);
			
		


	}else if (cmd.equals("mls")) {// monster G SAC
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();
			int player1 = 0;
			int player2 = 0;
			int val = 0;
	
			boolean effect = false;
			boolean monster = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			if (b.endsWith("e")) { effect=true; }
			

			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val+= getModifiedValue(m1[i],"lsa","A",1);}
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { val+= getModifiedValue(m2[i],"lsa","A",2);}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val+= getModifiedValue(e1[i],"lsa","A",1);;}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val+= getModifiedValue(e2[i],"lsa","A",2);;}						
					
					
				
		
				
			}	
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier = Double.parseDouble(exptoken.nextToken());
			return (int)((double)val * multiplier);
			
		








		}else if (cmd.equals("mdl")) {// monster default match
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();
			int player1 = 0;
			int player2 = 0;
			int val = 0;
	
			boolean effect = false;
			boolean monster = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			if (b.endsWith("e")) { effect=true; }
			

			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val+= carddata[m1[i].cardid].lifepoints;}
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { val+= carddata[m2[i].cardid].lifepoints;}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val+= carddata[e1[i].cardid].lifepoints;}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val+= carddata[e2[i].cardid].lifepoints;}						
					
					
				
		
				
			}	
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier = Double.parseDouble(exptoken.nextToken());
			return (int)((double)val * multiplier);
		}
		else if (cmd.equals("msv")) { // monster status value
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String statusname = exptoken.nextToken();
				int player1 = 0;
			int player2 = 0;
			int val = 0;
	
			boolean effect = false;
			boolean monster = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("e")) { effect = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			
			
			
			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val+= m1[i].GetStatusValue(statusname);}
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { val+= m2[i].GetStatusValue(statusname);}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val+= e1[i].GetStatusValue(statusname);}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val+= e2[i].GetStatusValue(statusname);}						
					
					
				
		
				
			}	
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier = Double.parseDouble(exptoken.nextToken());
			return (int)((double)val * multiplier);
		}
		
		else if (cmd.equals("msc")) { // monster status count (check who has >=1 on the status)
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String statusname = exptoken.nextToken();
				int player1 = 0;
			int player2 = 0;
			int val = 0;
	
			boolean effect = false;
			boolean monster = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			
			if (b.endsWith("e")) { effect = true; }
			
			
			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { if (m1[i].GetStatusValue(statusname)>=1)val++; }
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { if (m2[i].GetStatusValue(statusname)>=1)val++; }								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { if (e1[i].GetStatusValue(statusname)>=1)val++; }
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { if (m2[i].GetStatusValue(statusname)>=1)val++; }						
					
					
				
		
				
			}	
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier = Double.parseDouble(exptoken.nextToken());
			return (int)((double)val * multiplier);
		}
		
		else if (cmd.equals("mlp")) {// monster life point match sum
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();
			int player1 = 0;
			int player2 = 0;
			int val = 0;
	
			boolean effect = false;
			boolean monster = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			if (b.endsWith("e")) { effect=true; }
			

			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val+= getModifiedValue(m1[i],"lif","A", 1);}
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { val+= getModifiedValue(m2[i],"lif","A", 2);}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val+= e1[i].lifepoints;}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val+= e2[i].lifepoints;}						
					
					
				
		
				
			}	
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier = Double.parseDouble(exptoken.nextToken());
			return (int)((double)val * multiplier);
		}else if (cmd.equals("uap")) { // monster attack point match sum
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();
			int player1 = 0;
			int player2 = 0;
			int val = 0;
			boolean monster =false;
			boolean effect = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			if (b.endsWith("e")) { effect=true; }
			

			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val+= m1[i].attack;}
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { val+= m2[i].attack;}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val+= e1[i].attack;}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val+= e2[i].attack;}						
					
					
				
		
				
			}	

			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)val * multiplier);	
		}else if (cmd.equals("map")) { // monster attack point match sum
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();
			int player1 = 0;
			int player2 = 0;
			int val = 0;
			boolean monster =false;
			boolean effect = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			if (b.endsWith("e")) { effect=true; }
			

			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val+= getModifiedValue(m1[i],"atk","A", 1);}
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { val+= getModifiedValue(m2[i],"atk","A", 2);}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val+= getModifiedValue(e1[i],"atk","A", 1);}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val+= getModifiedValue(e2[i],"atk","A", 2);}						
					
					
				
		
				
			}	

			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)val * multiplier);
		}else if (cmd.equals("mlu")) {// monster life point match sum
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();
			int player1 = 0;
			int player2 = 0;
			int val = 0;
	
			boolean effect = false;
			boolean monster = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			if (b.endsWith("e")) { effect=true; }
			

			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val+= m1[i].lifepoints;}
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { val+= m2[i].lifepoints;}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val+= e1[i].lifepoints;}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val+= e2[i].lifepoints;}						
					
					
				
		
				
			}	
			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier = Double.parseDouble(exptoken.nextToken());
			return (int)((double)val * multiplier);
			
		}else if (cmd.equals("mau")) { // monster attack point match sum
			if (!exptoken.hasMoreTokens()) return 0;
			String a = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String b = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String c = exptoken.nextToken();if (!exptoken.hasMoreTokens()) return 0;
			String d = exptoken.nextToken();
			int player1 = 0;
			int player2 = 0;
			int val = 0;
			boolean monster =false;
			boolean effect = false;
			if (b.startsWith("b")) { player1 = 1; player2 = 2; }
			if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
			if (b.startsWith("s")) { player1 = player; player2 = player; }
			if (b.endsWith("m")) { monster = true; }
			if (b.endsWith("b")) { monster = true; effect = true; }
			
			if (b.endsWith("e")) { effect=true; }
			

			for (int i=0;i<5;i++) {
				if (player1==1 && monster) if (matchcard(a,c,d, m1[i], self, targt)) { val+= m1[i].attack; }
				if (player2==2 && monster) if (matchcard(a,c,d, m2[i], self, targt)) { val+= m2[i].attack;}								
	
				if (player1==1 && effect) if (matchcard(a,c,d, e1[i], self, targt)) { val+= e1[i].attack;}
				if (player2==2 && effect) if (matchcard(a,c,d, e2[i], self, targt)) { val+= e2[i].attack;}						
					
					
				
		
				
			}	
		


			if (!exptoken.hasMoreTokens()) return 0;
			double multiplier =Double.parseDouble(exptoken.nextToken());
			return (int)((double)val * multiplier);
			}
			return Integer.parseInt(exp);		
	}
	void checkGifts() {
		//player 1
		boolean giftp1 = false;
		boolean giftp2 = false;

		String p1cards;
		String p2cards;
		int manaadd = 0;
		manaadd = p1L+p1G+p2D;
		
		if (p1L+p1G+p1D != 0) if (p1life >= 400 && (p1life % (p1L+p1G+p1D) == 0) && !gift_1_8000) { CardmasterData.givePrebuiltDeck(name1,8000); giftp1 = true; gift_1_8000 = true;}
		if (p1life <= -100 && ((p1L+p1G+p1D) % 5 == 0) && !gift_1_8001) { CardmasterData.givePrebuiltDeck(name1,8001); giftp1 = true; gift_1_8001 = true;}

		if (p1L >= 30 && p1D <= 10 && p1G >= p1D && p1G <= p1L && (p1G % 3 == 0) &&  !gift_1_8002) { CardmasterData.givePrebuiltDeck(name1,8002); giftp1 = true; gift_1_8002 = true;}
		if (p1G >= 30 && p1L <= 10 && p1D >= p1L && p1D <= p1G  &&  (p1D % 3 == 0) &&!gift_1_8003) { CardmasterData.givePrebuiltDeck(name1,8003); giftp1 = true; gift_1_8003 = true;}
		if (p1D >= 30 && p1G <= 10 && p1L >= p1G && p1L <= p1D  && (p1L % 3 == 0) && !gift_1_8004) { CardmasterData.givePrebuiltDeck(name1,8004); giftp1 = true; gift_1_8004 = true;}
		if ((p1D + p1G + p1L) >= 110 && ((p2D + p2G + p2L) % 11 == 0) && !gift_1_8005) {CardmasterData.givePrebuiltDeck(name1,8005); giftp1 = true; gift_1_8005 = true;}
		if ((p1life -p2life)>=400 && (p1L-p2L)>=10 && (p1G-p2G)>=10 && (p1D-p2D)>=10 && !gift_1_8006) { CardmasterData.givePrebuiltDeck(name1,8006); giftp1 = true; gift_1_8006 = true;}
		if (((p2D + p2G + p2L)-(p1D+p1G+p1L)) >= 80 && !hasCardsInHand(1) && !gift_1_8007) { CardmasterData.givePrebuiltDeck(name1,8007); giftp1 = true; gift_1_8007 = true;}
		if (turncount >= 30 && hasCardsInHand(1) && p1L <= 5 && p1D <= 5 && p1G <= 5 && !gift_1_8008) { CardmasterData.givePrebuiltDeck(name1,8008); giftp1 = true; gift_1_8008 = true;}
		
		int dragoncount = 0;
		int effectcount = 0;
		for (int i=0;i<5;i++) {
			if (!m1[i].dummy)
			if (m1[i].name.equals("Rust Dragon")) {
				dragoncount++;
			}
			if (!e1[i].dummy) effectcount++;
		}
		
		if (dragoncount >= 3 && effectcount < dragoncount && effectcount > numCardsInHand(1) && effectcount % 2 == 0 && turncount % (p2D+1)== 0&& !gift_1_8009) {
			CardmasterData.givePrebuiltDeck(name1,8009); giftp1 = true; gift_1_8009 = true;
		}
		
		if (gift_1_8010_1 && gift_1_8010_2 && gift_1_8010_3 && p1D * 5 <= p1L + p1G && !gift_1_8010) {
			CardmasterData.givePrebuiltDeck(name1,8010); giftp1 = true; gift_1_8010 = true;
		}
		
		


		//player 2
		if (p2L+p2G+p2D != 0) if (p2life >= 400 && (p2life % (p2L+p2G+p2D) == 0) &&!gift_2_8000) { CardmasterData.givePrebuiltDeck(name2,8000); giftp2 = true; gift_2_8000 = true;}
		if (p2life <= -100 && ((p2L+p2G+p2D) % 5 == 0) &&!gift_2_8001) { CardmasterData.givePrebuiltDeck(name2,8001); giftp2 = true; gift_2_8001 = true;}

		if (p2L >= 30 && p2D <= 10 && p2G >= p2D && p2G <= p2L && (p2G % 3 == 0) && !gift_2_8002) { CardmasterData.givePrebuiltDeck(name2,8002); giftp2 = true; gift_2_8002 = true;}
		if (p2G >= 30 && p2L <= 10 && p2D >= p2L && p2D <= p2G && (p2D % 3 == 0) && !gift_2_8003) { CardmasterData.givePrebuiltDeck(name2,8003); giftp2 = true; gift_2_8003 = true;}
		if (p2D >= 30 && p2G <= 10 && p2L >= p2G && p2L <= p2D && (p2L % 3 == 0) &&!gift_2_8004) { CardmasterData.givePrebuiltDeck(name2,8004); giftp2 = true; gift_2_8004 = true;}
		if ((p2D + p2G + p2L) >= 110 && ((p1D + p1G + p1L) % 11 == 0) && !gift_2_8005) {CardmasterData.givePrebuiltDeck(name2,8005); giftp2 = true; gift_2_8005 = true;}
		if ((p2life -p1life)>=400 && (p2L-p1L)>=10 && (p2G-p1G)>=10 && (p2D-p1D)>=10 && !gift_2_8006) { CardmasterData.givePrebuiltDeck(name2,8006); giftp2 = true; gift_2_8006 = true;}
		if (((p1D + p1G + p1L)-(p2D+p2G+p2L)) >= 80 && !hasCardsInHand(2) && !gift_2_8007) { CardmasterData.givePrebuiltDeck(name2,8007); giftp2 = true; gift_2_8007 = true;}
		if (turncount >= 30 && hasCardsInHand(2) && p2L <= 5 && p2D <= 5 && p2G <= 5 &&  !gift_2_8008) { CardmasterData.givePrebuiltDeck(name2,8008); giftp2 = true; gift_2_8008 = true;}
		
		
		
		
				
		dragoncount = 0;
		effectcount = 0;
		for (int i=0;i<5;i++) {
			if (!m2[i].dummy)
			if (m2[i].name.equals("Rust Dragon")) {
				dragoncount++;
			}
			if (!e2[i].dummy) effectcount++;
		}
		
		if (dragoncount >= 3 && effectcount < dragoncount && effectcount > numCardsInHand(2) && effectcount % 2 == 0 && turncount % (p1D+1)== 0 && !gift_2_8009) {
			CardmasterData.givePrebuiltDeck(name2,8009); giftp2 = true; gift_2_8009 = true;
		}
		
		if (gift_2_8010_1 && gift_2_8010_2 && gift_2_8010_3 && p2D * 5 <= p2L + p2G && !gift_2_8010) {
			CardmasterData.givePrebuiltDeck(name2,8010); giftp2 = true; gift_2_8010 = true;
		}
		
		
		
		
		
		
		if (giftp1)
		{
			message("CHA#CMC Secret Service#" + name1 + " has unlocked a secret card...#");
			
		}
		if (giftp2)
		{
			message("CHA#CMC Secret Service#" + name2 + " has unlocked a secret card...#");
		}
	}
	int findSlotNumber(CardmasterServerCard card) {
		for (int i = 0;i<5;i++) {
			if (m1[i] == card) return i;
			if (m2[i] == card) return i;
			if (e1[i] == card) return i;
			if (e2[i] == card) return i;
		}							
					
		
		
		return -1;
	}
	
	
	
	int runability(String abilitycode, int player, AbilityTarget target, CardmasterServerCard self, int targetslot) {
		
		if (self != null) { // abilities of a modifier card run as if they are the card.
			if (self.typecode.startsWith("d")) {
				
			
//				System.out.println("Running ability of " + self.name + " as " + GetModifierParent(self).name);
				self= GetModifierParent(self);
				if (self == null) return 0;
			}
		}
		if (CardmasterData.DEBUGMODE) System.out.println("Running Ability");
		int returnval = runability(abilitycode, player, target, self, targetslot, false);
		if (waitcards1 > numCardsInHand(1)) waitcards1 = numCardsInHand(1);
		if (waitcards2 > numCardsInHand(2)) waitcards2 = numCardsInHand(2);
		 message("MDN#2#" + waitcards2 + "#",player_named(2));
		 message("MDN#1#" + waitcards1 + "#",player_named(1));
		 
		if (returnval == 1 || returnval == 2) {
			int val =  runability(abilitycode, player, target, self, targetslot, true);
			if (CardmasterData.DEBUGMODE) System.out.println(val);
			return val;
			
			
			}
		else { if (CardmasterData.DEBUGMODE) System.out.println("Ability Fail");
		 return 0; }
	}
	
	// player is owner.
	
	
	boolean IsLowestAttack(CardmasterServerCard card) {
		if (lowestfound) return false;
	
		for (int i=0;i<5;i++) {
			if (!m1[i].dummy && getModifiedValue(m1[i],"atk","A",1) < lowestattack) lowestattack = (int)getModifiedValue(m1[i],"atk","A",1);
			if (!m2[i].dummy && getModifiedValue(m2[i],"atk","A",2) < lowestattack) lowestattack = (int)getModifiedValue(m2[i],"atk","A",2);
		}
	//	System.out.println("Lowest attack: " + lowestattack + " card attack : " + card.attack);
		if (getModifiedValue(card,"atk","A",owner(card))  <= lowestattack) {
			lowestfound = true;
			 return true;
			 
		}
		return false;
		
		
	}	
	int runability(String abilitycode, int player, AbilityTarget target, CardmasterServerCard self, int targetslot, boolean perform) {
		// this assumes the ability can be played.  If there's an error, it returns
		// FALSE and the ability is considered failed.
		boolean skiptoend = false;
		boolean allreqs = true;
	//	if (perform) drawcard1 = 0;
	//	if (perform) drawcard2 = 0;
		int costd = 0;
		int costg = 0;
		int costl = 0;
		int count = 0;
		
		if (self != null) { // abilities of a modifier card run as if they are the card.
			if (self.typecode != null)
			if (self.typecode.startsWith("d")) {
				
			
				self= GetModifierParent(self);
				if (self == null) return 0;
			}
		
		
		
			if (Spiked(self)) return 0;
		
		}
		
		
			spellcostG = (int)getModifiedValue(self,"gco","A", player);
			
			spellcostD = (int)getModifiedValue(self,"dco","A", player);
			spellcostL = (int)getModifiedValue(self,"lco","A", player);
		
		
		boolean toskipa = false;
		boolean skipa = false;
		boolean toskipb = false;
		boolean skipb = false;
		boolean nextab = false; // if nextab is set, then next failure causes skipa, otherwise skipb and NOT failure.
		boolean upkeep = false;
		/*
		if (perform && self != null) {
			if (self.typecode.equals("m") || self.typecode.equals("e")) {
				if (self.GetStatusValue("glue") >= 1) {
					dizzy(self,true);
					self.SetStatusValue("glue",0);
					message("MES#" + self.name + " has been affected by the glue!");
					return 1;
					
				}	
				
				
				
				
			}
			
			
			
			
			
		}
		*/
		int tplayer = 0;
		if (target != null) if (target.isPlayer) tplayer = target.getPlayer();
		boolean started = true;
		StringTokenizer token = new StringTokenizer(abilitycode,";");
		while (token.hasMoreTokens()) {
			lowestfound = false;
				lowestattack = 99999;
			if (skiptoend == true && !perform) return 0;
			String command = token.nextToken();
			
			
			if (skipa && !command.startsWith("FIN-A")) { // skip "A" Choice.
				continue;
				
				}
			if (command.startsWith("FIN-A")) skipa = false;
			if (skipb && !command.startsWith("FIN-B")) { // skip "A" Choice.
				continue;
				
				}
			if (command.startsWith("FIN-B")) skipb = false;
					  
  				
			if (command.startsWith("NCODE")) return 0;
			else if (command.startsWith("BLK-A")) {
				if (toskipa) skipa=true;
				
				nextab = false;
				toskipa = false;
				
				}// code block A begins
				
			else if (command.startsWith("BLK-B")) {
				if (toskipb) skipb=true;
				
				nextab = false;
				toskipb = false;
				
				}
			if (command.startsWith("START")) continue;
			else if (command.startsWith("DEFER")) continue;
			else if (command.startsWith("END")) {
				if (perform && allreqs) {
				
				if (player ==1) {
						//	p1L-= costl;
					//		p1G-= costg;
				//			p1D-= costd;
							
							
			setMana("D",1,p1D-costd);
			setMana("G",1,p1G-costg);
			setMana("L",1,p1L-costl);
							
							
							
							p1Lu-= costl;
							p1Gu-= costg;
							p1Du-= costd;
							
			if (p1Du < 0) p1Du = 0;
			if (p1Gu < 0) p1Gu = 0;
			if (p1Lu < 0) p1Lu = 0;
							/*	if (costl > 0 || costg > 0 || costd > 0) {
									alltriggeredabilities(null,"TP1N");									
									
									
									
								}*/
							}	
						else if (player ==2) {
						//	p2L-= costl;
					//		p2G-= costg;
				//			p2D-= costd;
							
							
							
			setMana("D",2,p2D-costd);
			setMana("G",2,p2G-costg);
			setMana("L",2,p2L-costl);
							p2Lu-= costl;
							p2Gu-= costg;
							p2Du-= costd;
							
			if (p2Du < 0) p2Du = 0;
			if (p2Gu < 0) p2Gu = 0;
			if (p2Lu < 0) p2Lu = 0;
						/*		if (costl > 0 || costg > 0 || costd > 0) {
									alltriggeredabilities(null,"TP2N");									
									
									
									
								}*/
							}
						}		
				return 1;
			} 
			
			
			
			
			
			
			
			
			else if (command.startsWith("SWROO") && !skiptoend && perform) {
				CardmasterLibrary temp = deck1;
				deck1 = deck2;
				deck2 = temp;
					message("MES#Decks have been switched!#");
				
				
			}else if (command.startsWith("SWHND") && !skiptoend && perform) {
			
				SwitchHands();
				if (player == 1) player1crossedwires = !player1crossedwires;
				if (player == 2) player2crossedwires = !player2crossedwires;
					message("MES#Hands have been switched until beginning of " + player_named(player) + "'s next turn!#");
				
				
			}
			else if (command.startsWith("DREMV") && !skiptoend && perform) { // DECK remove (perminent)
				
					StringTokenizer cmdtoken = new StringTokenizer(command,"^");
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				
				String play = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int cardid = getvalue(cmdtoken.nextToken(),count,player,target,self);
				if (carddata[cardid] == null) return 0;
				int deckno = 0;
				CardmasterLibrary deck = null;
				if (play.equals("ownr")) {
					if (player == 1) {
						deckno = deck1.id;
						deck = deck1;
					}
					else if (player == 2) {
						deckno = deck2.id;
						deck = deck2;
					}
				}
				else if (play.equals("opnt") || play.equals("opon")) {
					if (player == 1) {
						deckno = deck2.id;
						deck = deck2;
					}
					else if (player == 2) {
						deckno = deck1.id;
						deck = deck1;
					}
				
				}
				if (deckno == 0) return 0;
				if (!deck.hasCard(cardid)) return 0;
				
				if(!webrunner.cardmaster.CardmasterData.transferCard(deckno,-1,cardid)) return 0;
				message("MES#" + player + " no longer owns " + getName(carddata[cardid]) + " perminently#");
				webrunner.cardmaster.CardmasterLogManager.WriteLog(getName(carddata[cardid]) + " is no longer in " + player_named(player)+"'s hands");
	
			}
			
			
			else if (command.startsWith("DADDC") && !skiptoend && perform) { // DECK add (perminent)
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				
				String play = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int cardid = getvalue(cmdtoken.nextToken(),count,player,target,self);
				if (carddata[cardid] == null) return 0;
				int deckno = 0;
				CardmasterLibrary deck = null;
				if (play.equals("ownr")) {
					if (player == 1) {
						deckno = deck1.id;
						deck = deck1;
					}
					else if (player == 2) {
						deckno = deck2.id;
						deck = deck2;
					}
				}
				else if(play.equals("opnt") || play.equals("opon")) {
					if (player == 1) {
						deckno = deck2.id;
						deck = deck2;
					}
					else if (player == 2) {
						deckno = deck1.id;
						deck = deck1;
					}
				
				}
				if (deckno == 0) return 0;
			//	if (!deck.hasCard(id)) return 0;
				
				
				if (!webrunner.cardmaster.CardmasterData.transferCard(-1,deckno,cardid)) return 0;
				message("MES#" + player + " now owns " + getName(carddata[cardid]) + " perminently#");
				
				webrunner.cardmaster.CardmasterLogManager.WriteLog(getName(carddata[cardid]) + " is now in " + player_named(player)+"'s hands");
	
			}
			
			
			else if (command.startsWith("GIVEC") && !skiptoend) { // steals a card from you
				//cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				if (perform) {
					
					
					if (numCardsInHand(player) <= 0) {skiptoend = true; continue;} // your hand is full
					if (numCardsInHand(not_player(player)) >= 8) {skiptoend = true; continue;} // opponents hand is full.
					int i = random.nextInt(8);
					CardmasterServerCard pickedcard;
					while (getSlot(player,i,"h").dummy) {
						i = random.nextInt(8);
					}
					pickedcard = getSlot(player,i,"h");
					
					CardmasterServerCard yourcard = getSlot(player,0,"h");
					for (int j = 0; j < 8 ; j++) {
						yourcard = getSlot(not_player(player),j,"h");
						if (yourcard.dummy) break;
					} 
					if (!yourcard.dummy) {skiptoend = true; continue;}
					yourcard.copydata(pickedcard);
					pickedcard.copydata(new CardmasterServerCard());
					message("MES#" + player_named(not_player(player)) + " received " + yourcard.name + "#");
				}
				else {
					if (numCardsInHand(player) <= 0) return 0;
					if (numCardsInHand(not_player(player)) >= 8) return 0;
					
					
					
				}		
			}
			
			else if (command.startsWith("CNCLA") && perform && !skiptoend) {
				abilitycounter = true;
				message("MES#" + player_named(player) + " counters abilities to be resolved!#");
			}
			else if (command.startsWith("CNCLR") && perform && !skiptoend) {
				abilitycounter = false;
				message("MES#" + player_named(player) + " counters abilities to be resolved!#");
			}
			else if (command.startsWith("CNCLN") && perform && !skiptoend) {
				abilitycounternext = true;
				message("MES#" + player_named(player) + " counters abilities to be resolved!#");
			}
				
			else if (command.startsWith("STEAL") && !perform && !skiptoend) {
				if (numCardsInHand(player) >= 8) return 0;
				if (numCardsInHand(not_player(player)) <= 0) return 0;
					
				
			}
			else if (command.startsWith("STEAL") && perform && !skiptoend) { // steals a card from your opponent.
				//cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				if (perform) {
					if (numCardsInHand(player) >= 8) {skiptoend = true; continue;} // your hand is full
					if (numCardsInHand(not_player(player)) <= 0) {skiptoend = true; continue;} // opponents hand is full.
					int i = random.nextInt(8);
					CardmasterServerCard pickedcard;
					while (getSlot(not_player(player),i,"h").dummy) {
						i = random.nextInt(8);
					}
					pickedcard = getSlot(not_player(player),i,"h");
					
					CardmasterServerCard yourcard = getSlot(player,0,"h");
					for (int j = 0; j < 8 ; j++) {
						yourcard = getSlot(player,j,"h");
						if (yourcard.dummy) break;
					} 
					if (!yourcard.dummy) {skiptoend = true; continue;}
					yourcard.copydata(pickedcard);
					pickedcard.copydata(new CardmasterServerCard());
					message("MES#" + player_named(player) + " stole " + yourcard.name + "#");
					
				}
				else {
					if (numCardsInHand(player) <= 0) return 0;
					if (numCardsInHand(not_player(player)) >= 8) return 0;
					
					
					
				}
			}	

			else if (command.startsWith("MESSG") && perform && !skiptoend) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String text = cmdtoken.nextToken();
				message("MES#"+text+"#");
				
			}

			else if (command.startsWith("COPYH") && perform && !skiptoend) { // steals a card from your opponent.
				//cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				if (perform) {
					if (numCardsInHand(player) >= 8) {skiptoend = true; continue;} // your hand is full
					if (numCardsInHand(not_player(player)) <= 0) {skiptoend = true; continue;} // opponents hand is full.
					int i = random.nextInt(8);
					CardmasterServerCard pickedcard;
					while (getSlot(not_player(player),i,"h").dummy) {
						i = random.nextInt(8);
					}
					pickedcard = getSlot(not_player(player),i,"h");
					
					CardmasterServerCard yourcard = getSlot(player,0,"h");
					for (int j = 0; j < 8 ; j++) {
						yourcard = getSlot(player,j,"h");
						if (yourcard.dummy) break;
					} 
					if (!yourcard.dummy) {skiptoend = true; continue;}
					yourcard.copydata(pickedcard);
					//pickedcard.copydata(new CardmasterServerCard());
					message("MES#" + player_named(player) + " copied " + yourcard.name + "#");
					
				}
				else {
					if (numCardsInHand(player) <= 0) return 0;
					if (numCardsInHand(not_player(player)) >= 8) return 0;
					
					
					
				}
			}	
		

		
			else if (command.startsWith("DEND")) {
				if (perform && allreqs) {
				
				if (player ==1) {
						
			setMana("D",1,p1D-costd);
			setMana("G",1,p1G-costg);
			setMana("L",1,p1L-costl);
							
							
							
							p1Lu-= costl;
							p1Gu-= costg;
							p1Du-= costd;
							
			if (p1Du < 0) p1Du = 0;
			if (p1Gu < 0) p1Gu = 0;
			if (p1Lu < 0) p1Lu = 0;
								
							}	
						else if (player ==2) {
						
			setMana("D",2,p2D-costd);
			setMana("G",2,p2G-costg);
			setMana("L",2,p2L-costl);
							p2Lu-= costl;
							p2Gu-= costg;
							p2Du-= costd;
			if (p2Du < 0) p2Du = 0;
			if (p2Gu < 0) p2Gu = 0;
			if (p2Lu < 0) p2Lu = 0;
							}	
					}		
				return 2;
			}
			else if (command.startsWith("COSTD")  && !skiptoend) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String cmd = cmdtoken.nextToken();
				costd = getvalue(cmd, count, player, target, self);
				boolean enough = true;
				if (player == 1 && costd > p1D) enough = false;
				if (player == 2 && costd > p2D) enough = false;		
				if (upkeep && !enough && perform) {
					destroy_card(self);	
					return 0;
					
				}
				else if (upkeep && !enough && !perform) {
					return 1; // make sure the upkeep runs properly.
				}
				else if (!enough && perform) {
					allreqs = false;
					skiptoend = true;	
					
				}else if (!enough && !perform) {
					return 0;
				}
				
			}else if (command.startsWith("COSTG")  && !skiptoend) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String cmd = cmdtoken.nextToken();
				costg = getvalue(cmd, count, player, target, self);
				boolean enough = true;
				if (player == 1 && costg > p1G) enough = false;	
				if (player == 2 && costg > p2G) enough = false;
				if (upkeep && !enough && perform) {
					destroy_card(self);	
					return 0;
					
				}
				else if (upkeep && !enough && !perform) {
					return 1; // make sure the upkeep runs properly.
				}
				else if (!enough && perform) {
					allreqs = false;
					skiptoend = true;	
					
				}else if (!enough && !perform) {
					return 0;
				}
				
			}else if (command.startsWith("COSTL")  && !skiptoend) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String cmd = cmdtoken.nextToken();
				costl = getvalue(cmd, count, player, target, self);
				boolean enough = true;
				if (player == 1 && costl > p1L) enough = false;	
				if (player == 2 && costl > p2L) enough = false;		
				if (upkeep && !enough && perform) {
					destroy_card(self);	
					return 0;
					
				}
				else if (upkeep && !enough && !perform) {
					return 1; // make sure the upkeep runs properly.
				}
				else if (!enough && perform) {
					allreqs = false;
					skiptoend = true;	
					
				}else if (!enough && !perform) {
					return 0;
				}
			
						
			}else if (command.startsWith("UPK")) { // cost is an upkeep cost.
				upkeep = true;
//	System.out.println("Upkeep running for card " + self.cardid + " : " + abilitycode);
			
				
			}else if (command.startsWith("NAB")) { // cost is an upkeep cost.
			toskipa = false;
			toskipb = false;
				nextab = true;
			
				
			}
			
			
			
				else if (command.startsWith("NOTRG")) { // the card is the target as 
				boolean itsfalse = true;
				if (target.getCard() != self) itsfalse = false;
				if (nextab){
					if (itsfalse) toskipa = true;
					else toskipb = true;
				}
				else {
				
				if (itsfalse && perform) {skiptoend = true; continue;}  // these returtn 1, because the ability counts as succeeding. 
				if (itsfalse && !perform) return 0;
				}
				
			}
			else if (command.startsWith("ISTRG")) { // the card is the target as 
				boolean itsfalse = true;
				if (target.getCard() == self) itsfalse = false;
				if (nextab){
					if (itsfalse) toskipa = true;
					else toskipb = true;
				}
				else {
				
				if (itsfalse && perform) {skiptoend = true; continue;}  // these returtn 1, because the ability counts as succeeding. 
				if (itsfalse && !perform) return 0;
				}
				
			}
			else if (command.startsWith("LOCCL")) { // location is color
				boolean itsfalse = true;
				
							StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				
				if (containscolor(location.colorcode,Integer.parseInt(cmdtoken.nextToken()))) itsfalse = false;
				if (nextab){
					if (itsfalse) toskipa = true;
					else toskipb = true;
				}
				else {
				
				if (itsfalse && perform) {skiptoend = true; continue;}  // these returtn 1, because the ability counts as succeeding. 
				if (itsfalse && !perform) return 0;}
				
			}else if (command.startsWith("LOCNC")) { // location is not color
				boolean itsfalse = false;
				
							StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				
				if (containscolor(location.colorcode,Integer.parseInt(cmdtoken.nextToken()))) itsfalse = true;
				
				if (nextab){
					if (itsfalse) toskipa = true;
					else toskipb = true;
				}
				else {
				
				if (itsfalse && perform) {skiptoend = true; continue;}  // these returtn 1, because the ability counts as succeeding. 
				if (itsfalse && !perform) return 0;
				}
				
			}
			else if (command.startsWith("SPIMM")) { // the target has spell immune
				boolean itsfalse = true;
				if (HasSpellImmune(target.getCard())) itsfalse = false;
				if (nextab){
					if (itsfalse) toskipa = true;
					else toskipb = true;
				}
				else {
				
				if (itsfalse && perform) {skiptoend = true; continue;}  // these returtn 1, because the ability counts as succeeding. 
				if (itsfalse && !perform) return 0; }
				
			}
			else if (command.startsWith("ABIMM")) { // the target has ability immune
				boolean itsfalse = true;
				if (HasAbilityImmune(target.getCard())) itsfalse = false;
				
				if (nextab){
					if (itsfalse) toskipa = true;
					else toskipb = true;
				}
				else {
				
				if (itsfalse && perform) {skiptoend = true; continue;}  // these returtn 1, because the ability counts as succeeding. 
				if (itsfalse && !perform) return 0;
				}	
			}
			else if (command.startsWith("ISATK")) { // the card attacked 
				boolean itsfalse = true;
				if (isAttacking(self)) itsfalse = false;
				if (nextab){
					if (itsfalse) toskipa = true;
					else toskipb = true;
				}
				else {
				
				if (itsfalse && perform) {skiptoend = true; continue;}  // these returtn 1, because the ability counts as succeeding. 
				if (itsfalse && !perform) return 0; }
				
			}	
			else if (command.startsWith("ISDEF")) { // is not defending
				boolean itsfalse = true;
				if (isDefending(self)) itsfalse = false;
				if (nextab){
					if (itsfalse) toskipa = true;
					else toskipb = true;
				}
				else {
				
				if (itsfalse && perform) {skiptoend = true; continue;}  // these returtn 1, because the ability counts as succeeding. 
				if (itsfalse && !perform) return 0; }
				
			}		
			else if (command.startsWith("NODEF")) { // is not defending
				boolean itsfalse = true;
				if (!isDefending(self)) itsfalse = false;
				if (nextab){
					if (itsfalse) toskipa = true;
					else toskipb = true;
				}
				else {
				
				if (itsfalse && perform) {skiptoend = true; continue;}  // these returtn 1, because the ability counts as succeeding. 
				if (itsfalse && !perform) return 0; }
				
			}
			else if (command.startsWith("ISATT")) { // the target card attacked 
//System.out.println("ISATT");			
				boolean ok = true;
				if (target == null) ok = false;
				if (ok) if (!isAttacking(target.getCard())) ok = false;
				
				if (nextab){
					if (ok) toskipa = true;
					else toskipb = true;
				}
				else {
				
				if (!ok && perform) {skiptoend = true; continue;}  // these returtn 1, because the ability counts as succeeding. 
				if (!ok && !perform) return 0;
				}
//	System.out.println("ISATT");				
			}
else if (command.startsWith("NOATT")) { // the target card attacked 
			
				boolean ok = true;
				if (target == null) ok = false;
				if (ok) if (isAttacking(target.getCard())) ok = false;
		if (!ok) System.out.println("TARGET IS ATTACKING");
		if (ok) System.out.println("TARGET IS NOT ATTACKING");
		
				if (nextab){
					if (!ok) toskipa = true;
					else toskipb = true;
				}
				else {
				
				if (!ok && perform) {skiptoend = true; continue;}  // these returtn 1, because the ability counts as succeeding. 
				if (!ok && !perform) return 0;
				}
			}
			else if (command.startsWith("CMP<=")) { // is a bigger or equal then b?
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String val1 = cmdtoken.nextToken();
				String val2 = cmdtoken.nextToken();
				int maxrand = 0;
				if (!perform) maxrand = 1;
				
				int a = getvalue(val1,count,player,target,self, maxrand);
				int b = getvalue(val2,count,player,target,self, maxrand);
				
				
				if (nextab){
					if (a > b) toskipa = true;
					else toskipb = true;
				}
				else {
				
				
				if (a > b && perform) {skiptoend = true; continue;}  // these returtn 1, because the ability counts as succeeding. 
				if (a > b && !perform) return 0;
				}
				
			}
			
			
			else if (command.startsWith("DIVIS")) { // is a divisible by b
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String val1 = cmdtoken.nextToken();
				String val2 = cmdtoken.nextToken();
				int maxrand = 0;
				if (!perform) maxrand = 1;
				
				int a = getvalue(val1,count,player,target,self, maxrand);
				int b = getvalue(val2,count,player,target,self, maxrand);
				//System.out.println("A: " + a + " B: " + b);
				boolean failure = (a % b) != 0;
				if (nextab) {
					if (failure) toskipa = true;
					else toskipb = true;
				} else {
				
				if (failure&& perform) {skiptoend = true; continue;}  // these returtn 1, because the ability counts as succeeding. 
				if (failure && !perform) return 0;
				}
				
			}
			
			
			else if (command.startsWith("CMP>=")) { // is a bigger or equal then b?
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String val1 = cmdtoken.nextToken();
				String val2 = cmdtoken.nextToken();
				int maxrand = 0;
				if (!perform) maxrand = 1;
				
				int a = getvalue(val1,count,player,target,self, maxrand);
				int b = getvalue(val2,count,player,target,self, maxrand);
				//System.out.println("A: " + a + " B: " + b);
				boolean failure = (a < b);
				if (nextab) {
					if (failure) toskipa = true;
					else toskipb = true;
				} else {
				
				if (a < b && perform) {skiptoend = true; continue;}  // these returtn 1, because the ability counts as succeeding. 
				if (a < b && !perform) return 0;
				}
				
			}
			else if (command.startsWith("CMP==")) { // is a bigger or equal then b?
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String val1 = cmdtoken.nextToken();
				String val2 = cmdtoken.nextToken();
				int maxrand = 0;
				if (!perform) maxrand = 1;
				
				int a = getvalue(val1,count,player,target,self, maxrand);
				int b = getvalue(val2,count,player,target,self, maxrand);
			//	System.out.println("EQUALITY CHECK: A: " + a + " B: " + b);
				
				boolean failure = (a != b);
				if (nextab) {
					if (failure) toskipa = true;
					else toskipb = true;
				} else {
				
				if (failure && perform) {skiptoend = true; continue;}  // these returtn 1, because the ability counts as succeeding. 
				if (failure && !perform) return 0;
				}
				
			}
			else if (command.startsWith("CMP!=")) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
			String val1 = cmdtoken.nextToken();
				String val2 = cmdtoken.nextToken();
				int maxrand = 0;
				if (!perform) maxrand = 1;
				
				int a = getvalue(val1,count,player,target,self, maxrand);
				int b = getvalue(val2,count,player,target,self, maxrand);
				boolean failure = (a == b);			
				if (nextab) {
					if (failure) toskipa = true;
					else toskipb = true;
				} else {
					if (a == b && perform) {skiptoend = true; continue;} 
					if (a == b && !perform) return 0;					
				}
				
			}
			 
			else if (command.startsWith("CMS>=") && perform) { // is a bigger or equal then b?
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String val1 = cmdtoken.nextToken();
				String val2 = cmdtoken.nextToken();
				int maxrand = 0;
				if (!perform) maxrand = 1;
				
				int a = getvalue(val1,count,player,target,self, maxrand);
				int b = getvalue(val2,count,player,target,self, maxrand);
				if (a < b && perform) {skiptoend = true; continue;}  // these returtn 1, because the ability counts as succeeding. 
				if (a < b && !perform) return 0;
				
			}
			else if (command.startsWith("CMS!=") && perform) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String val1 = cmdtoken.nextToken();
				String val2 = cmdtoken.nextToken();
				int maxrand = 0;
				if (!perform) maxrand = 1;
				
				int a = getvalue(val1,count,player,target,self, maxrand);
				int b = getvalue(val2,count,player,target,self, maxrand);
				if (a == b && perform) {skiptoend = true; continue;} 
				if (a == b && !perform) return 0;								
				
				
			}

			else if (command.startsWith("ONDIZ")) { // ONE NOT DIZZY
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
			
				Vector <CardmasterServerCard> matched = CardsMatching(player,a,b,c,d,self,target);
				boolean dizzy = false;
				for (CardmasterServerCard e : matched) {
					if (!e.dizzy) dizzy = true;
				}
				
				
				
				if (!dizzy && !perform) return 0;		
				if (!dizzy && perform) { skiptoend= true; continue; }		
								
			}	
			else if (command.startsWith("OIDIZ")) { // ONE IS DIZ
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
				
				boolean dizzy = false;		
				
				Vector <CardmasterServerCard> matched = CardsMatching(player,a,b,c,d,self,target);
				
				for (CardmasterServerCard e : matched) {
					if (e.dizzy) dizzy = true;	
				}
				
				
				
				if (!dizzy && !perform) return 0;		
				if (!dizzy && perform) { skiptoend= true; continue; }									
			}						
			else if (command.startsWith("ISDIZ")) { //ALL ARE DIZZY
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
			
				Vector <CardmasterServerCard> matched = CardsMatching(player,a,b,c,d,self,target);
				boolean dizzy = true;
				for (CardmasterServerCard e : matched) {
					if (!e.dizzy) dizzy=false;	
				}
				if (nextab) {
					if (!dizzy) toskipa = true;
					else toskipb = true;
				} else {
				if (!dizzy && !perform) return 0;		
				if (!dizzy && perform) { skiptoend= true; continue; }
				}									
			}				
			else if (command.startsWith("NODIZ")) { // NODIZZY
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
			
				Vector <CardmasterServerCard> matched = CardsMatching(player,a,b,c,d,self,target);
				boolean dizzy = true;
				for (CardmasterServerCard e : matched) {
					if (e.dizzy) dizzy=false;	
				}
				if (!dizzy && !perform) return 0;		
				if (!dizzy && perform) { skiptoend= true; continue; }									
			}
			else if (command.startsWith("MTCHT")) { //match target with matchcard
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				boolean matched = true;
				CardmasterServerCard check = target.getCard();
				if (check == null) matched = false;
				if (matched) if (effect && !check.typecode.equals("e"))	 matched = false;
				if (matched) if (monster && !check.typecode.equals("m"))	 matched = false;
				
				if (matched) {
					if (!(
						 (player1 == 1 && owner(check) == 1) ||
						 (player2 == 2 && owner(check) == 2)
						 ));
					
					
				}
				if (matched) matched = matchcard(a, c,d, check, self, target);
				if (!matched && !perform) return 0;		
				if (!matched && perform) { skiptoend= true; continue; }	
			}
			else if (command.startsWith("TSELF")) { // do target player self?
			boolean failure = false;
				if (!target.isPlayer) failure = true;
				if (!failure) {
					if (tplayer != player) failure = true;
				}
			
				
				
				if (nextab) {
					if (failure) toskipa = true;
					else toskipb = true;
				} else {
					if (failure && perform) {skiptoend = true; continue;}  // these returtn 1, because the ability counts as succeeding. 
					if (failure && !perform) return 0;
				
				}
			
			
			}
			else if (command.startsWith("EXIST")) { // CARD EXISTS..
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String name = cmdtoken.nextToken();
				boolean doeshave = false;
				if (player == 1) {
					for (int i=0;i<5;i++) {
						if (!m1[i].dummy)  doeshave = true;
						if (!e1[i].dummy)  doeshave = true;
							
					}
				}
				if (player == 2) {
					for (int i=0;i<5;i++) {
						if (!m2[i].dummy) doeshave = true;
						if (!e2[i].dummy)  doeshave = true;
							
					}					
					
				}
				if (!doeshave && !perform) return 0;
				if (!doeshave && perform) { skiptoend= true; continue; }
			}
			
			
			
			
			else if (command.startsWith("CRDON")) { // CARD EXISTS
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String name = cmdtoken.nextToken();
				boolean doeshave = false;
				if (player == 1) {
					for (int i=0;i<5;i++) {
						if (!m1[i].dummy) if (m1[i].name.equals(name)) doeshave = true;
						if (!e1[i].dummy) if (e1[i].name.equals(name)) doeshave = true;
							
					}
				}
				if (player == 2) {
					for (int i=0;i<5;i++) {
						if (!m2[i].dummy) if (m2[i].name.equals(name)) doeshave = true;
						if (!e2[i].dummy)if (e2[i].name.equals(name)) doeshave = true;
							
					}					
					
				}
				if (!doeshave && !perform) return 0;
				if (!doeshave && perform) { skiptoend= true; continue; }
			}
			

			else if (command.startsWith("CRDTT")) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command	
				if (perform) {
				
			
						
					if (target == null) { skiptoend= true; continue; }
					if (!target.isCard ) { skiptoend= true; continue; }
					if (!(target.getCard().typecode.equals("m")||target.getCard().typecode.equals("e"))) { skiptoend= true; continue; }
					String type = cmdtoken.nextToken();
					if (!(ismType(target.getCard(),type))) { skiptoend= true; continue; }
				}
				else {
					if (target == null) return 0;
					if (!target.isCard ) return 0;
					if (!(target.getCard().typecode.equals("m") || target.getCard().typecode.equals("e"))) return 0;
					String type = cmdtoken.nextToken();
					if (!(ismType(target.getCard(),type))) return 0;	
					
					
					
				}	
			}			
			else if (command.startsWith("CRDTN")) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command		
				
				if (perform) {
					if (target == null) { skiptoend= true; continue; }
					if (!target.isCard ) { skiptoend= true; continue; }
					String type = cmdtoken.nextToken();
					if (!(target.getCard().name.equals(type))) { skiptoend= true; continue; }
				}
				else {
					if (target == null) return 0;
					if (!target.isCard ) return 0;
					String type = cmdtoken.nextToken();
					if (!(target.getCard().name.equals(type))) return 0;
				}	
			}			


			else if (command.startsWith("COUNT")) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				Vector <CardmasterServerCard> matched = CardsMatching(player,a,b,c,d,self,target);
				boolean dizzy = false;
				for (CardmasterServerCard e : matched) {
					count++;	
				}
				//System.out.println("Count value: " + count);	
			}			
			else if (command.startsWith("TEMPV")) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command		

				String val = cmdtoken.nextToken();
				count =  getvalue(val, count, player, target, self);	
			}
			else if (command.startsWith("ADDCV")) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command		

				String val = cmdtoken.nextToken();
				
				String val2 = "cnt:1:";
				
				if (cmdtoken.hasMoreTokens()) val2 = cmdtoken.nextToken();
				count =  getvalue(val, count, player, target, self) + getvalue(val2, count, player, target, self);	
			}
		
			else if (command.startsWith("DAMAG") && !skiptoend) { // Damage Card.
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int player1 = 0;
				int player2 = 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
					int successful = 0;		
			
				Vector <CardmasterServerCard> matched = CardsMatching(player,a,b,c,d,self,target);
				boolean dizzy = false;
				for (CardmasterServerCard e : matched) {
					if (perform) damage_monster(owner(e),findSlotNumber(e),amount);
					successful++;
				}
				if (successful == 0 && perform) { skiptoend= true; continue; };
				if (successful == 0 && !perform) return 0;	
			//	if (!dizzy) return 0;								
			}
			else if ((command.startsWith("REMOV") || command.startsWith("OBLTR")) && !skiptoend) { // remove card from game entirely
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
				
			//	System.out.println("REMOV");
				
				Vector <CardmasterServerCard> matched = CardsMatching(player,a,b,c,d,self,target);
				
				int success = 0;
				for (CardmasterServerCard e : matched) {
						if (HasOblitImmune(e)) continue;
					if (perform) remove_from_play(owner(e),e.typecode,findSlotNumber(e));
					success++;
				}
				
				
				
				
				if (success == 0 && perform) { skiptoend= true; continue; };
				if (success == 0 && !perform) return 0;
			}

			else if (command.startsWith("REVRT") && !skiptoend) { // revert card
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
				
					Vector <CardmasterServerCard> matched = CardsMatching(player,a,b,c,d,self,target);
				
				int success = 0;
				for (CardmasterServerCard e : matched) {
					if (perform) e.copydata(carddata[e.cardid]);
					success++;
				}
				

				
				if (success == 0 && perform) { skiptoend= true; continue; };
				if (success == 0 && !perform) return 0;	
			}
			
			else if (command.startsWith("CNCEF") && !skiptoend) { // cancel endutrn abilities (CAN FAIL)
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
		
				int successful = 0;
				Vector <CardmasterServerCard> matched = CardsMatching(player,a,b,c,d,self,target);
			
				for (CardmasterServerCard e : matched) {
						if (cancelUntilEndTurn(e,perform)) successful++;
				}
				if (successful == 0 && perform) { skiptoend= true; continue; };
				if (successful == 0 && !perform) return 0;		
			}	
			else if (command.startsWith("CNCET") && !skiptoend) { // cancel endutrn abilities
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
				
				
				
				int successful = 0;
				Vector <CardmasterServerCard> matched = CardsMatching(player,a,b,c,d,self,target);
			
				for (CardmasterServerCard e : matched) {	
					if (cancelUntilEndTurn(e,perform)) successful++;
				}
			//		if (successful == 0 && perform) { skiptoend= true; continue; };
			//	if (successful == 0 && !perform) return 0;		
			}		

		
			else if (command.startsWith("ATKME") && !skiptoend) {
				boolean didit = false;
				if (isAttacking(target.getCard())) {
					int eslot = findSlotNumber(target.getCard());
					int slotn = findSlotNumber(self);
	//	System.out.println("eslot: " + eslot + " slotn: " + slotn);
					
					if (!defenderlock[slotn+1] && defender[slotn+1] == 0) {
	//					System.out.println("Stage one");
						if (attacker[eslot+1] >0) {
	//	System.out.println("Stage two");
							didit = true;
							
							if (perform) {
									///System.out.println("Stage three");
									
										
										// ATTACING DIRECTL
										
								if (attacker[eslot+1] != 6) {
									
								defender[attacker[eslot+1]] = 0;
								defenderlock[attacker[eslot+1]] = false;
								}
								attacker[eslot+1] = slotn+1;
								defenderlock[slotn+1] = true;
								defender[slotn+1] = eslot+1;
								
								
								fix_attackers();
								message("ATD#" + playerphase + "#" + attacker[1]+ "#" + attacker[2]+ "#" + attacker[3]+ "#" + attacker[4]+ "#" + attacker[5] + "#");
	
								
								
								
								
							}

						}
				
					}
				
				
				
				
				}
				if (!didit && perform) { skiptoend= true; continue; };
				if (!didit  && !perform) return 0;	
				
			}
			
			else if (command.startsWith("ATKPL") && !skiptoend) {
				boolean didit = false;
				if (isAttacking(target.getCard())) {
					int eslot = findSlotNumber(target.getCard());
					int slotn = 6;
					System.out.println("eslot: " + eslot);
				//	
					//if (!defenderlock[slotn+1] && defender[slotn+1] == 0) {
						System.out.println("Stage one");
						if (attacker[eslot+1] >0 && attacker[eslot+1] < 6) {
								System.out.println("Stage two");
							didit = true;
							
							if (perform) {
									System.out.println("Stage three");
								defender[attacker[eslot+1]] = 0;
								defenderlock[attacker[eslot+1]] = false;
								attacker[eslot+1] = slotn;
							//	defenderlock[slotn+1] = true;
							//	defender[slotn+1] = eslot+1;
								
								
								fix_attackers();
								message("ATD#" + playerphase + "#" + attacker[1]+ "#" + attacker[2]+ "#" + attacker[3]+ "#" + attacker[4]+ "#" + attacker[5] + "#");
	
								
								
								
								
							}
							
							
							
						}
						else if (attacker[eslot+1] == 6) {
							didit = true;
							if (perform) {
								attacker[eslot+1] = slotn;
								fix_attackers();
								message("ATD#" + playerphase + "#" + attacker[1]+ "#" + attacker[2]+ "#" + attacker[3]+ "#" + attacker[4]+ "#" + attacker[5] + "#");
	
								
							}
							
						}
						
						
						
					//}
				
				
				
				
				}
				if (!didit && perform) { skiptoend= true; continue; };
				if (!didit  && !perform) return 0;	
				
			}
			else if (command.startsWith("ABBLK") && !skiptoend) { // ability block
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
			
			
				int successful = 0;
				Vector <CardmasterServerCard> matched = CardsMatching(player,a,b,c,d,self,target);
			
				for (CardmasterServerCard e : matched) {	
					if (perform)e.ability = false;
					successful++;
				}
			
			
			
			
		
				if (successful == 0 && perform) { skiptoend= true; continue; };
				if (successful == 0 && !perform) return 0;		
			}		

		

			else if (command.startsWith("DESTR") && !skiptoend) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
			
			
			
				int successful = 0;
				Vector <CardmasterServerCard> matched = CardsMatching(player,a,b,c,d,self,target);
			
				for (CardmasterServerCard e : matched) {
					if (HasBoss(e)) continue;	
					if (perform) destroy_card(owner(e),e.typecode,findSlotNumber(e));
						successful++;
				
				}
			
			
			
				if (successful == 0 && perform) { skiptoend= true; continue; };
				if (successful == 0 && !perform) return 0;		
			}		


	else if (command.startsWith("DESTA") && !skiptoend) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
				/*int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				*/
				Vector <CardmasterServerCard> matched = CardsMatching(player,a,b,c,d,self,target);
				//int successful =1;
				
				for (CardmasterServerCard e : matched) {
					if (HasBoss(e)) continue;	
					if (perform) destroy_card(owner(e),e.typecode,findSlotNumber(e));
				
				}
				/*
				for (int i=0;i<5;i++) {
					
					//	System.out.println("Monster: " + monster + " Effect: " + effect + " Player1: " + player1 + " Player2: " + player2);
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) { 
							if (perform) destroy_card(1,"m",i);
							successful++;
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) { 
							if (perform) destroy_card(2,"m",i);
							successful++;
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) { 
							if (perform) destroy_card(1,"e",i);
							successful++;
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
							if (perform) destroy_card(2,"e",i);
							successful++;
						}
					}
				}
					if (successful == 0 && perform) { skiptoend= true; continue; };
				if (successful == 0 && !perform) return 0;		
				*/
			}		




			else if (command.startsWith("MATCH") && !skiptoend) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
				/*int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				*/
				int successful =0;
				Vector <CardmasterServerCard> matched = CardsMatching(player,a,b,c,d,self,target);
				successful = matched.size();
				/*
				for (int i=0;i<5;i++) {
					
					//	System.out.println("Monster: " + monster + " Effect: " + effect + " Player1: " + player1 + " Player2: " + player2);
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) { 
						//	if (perform) destroy_card(1,"m",i);
							successful++;
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) { 
						//	if (perform) destroy_card(2,"m",i);
							successful++;
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) { 
						//	if (perform) destroy_card(1,"e",i);
							successful++;
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
						//	if (perform) destroy_card(2,"e",i);
							successful++;
						}
					}
				}*/
				if (nextab) {
						if (successful == 0) toskipa = true;
						else toskipb = true;
					}
				else {
					
					if (successful == 0 && perform) { skiptoend= true; continue; };
					if (successful == 0 && !perform) return 0;		
				}
			}		


			else if (command.startsWith("DSTUN") && !skiptoend) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				int successful =1;
				for (int i=0;i<5;i++) {
					

					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) { 
						if (HasBoss(m1[i])) continue;
						//System.out.println("Destroying " + 1 + "m" + i);
							if (perform && m1[i] != null) if (!m1[i].dummy) destroy_card(1,"m",i);
							successful++;
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
						if (HasBoss(m2[i])) continue;
						//System.out.println("Destroying " + 2 + "m" + i);
							if (perform && m2[i] != null) if (!m2[i].dummy)  destroy_card(2,"m",i);
							successful++;
						}
					}
				}
				
			}	



	else if (command.startsWith("DSTRC")  && !skiptoend) { // destroy card for use in combat.
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				int successful =0;
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) { 
							if (HasBoss(getSlot(1,i,"m"))) continue;
							if (perform) cb_destroy_card(1,"m",i);
							successful++;
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) { 
							if (HasBoss(getSlot(2,i,"m"))) continue;
							if (perform) cb_destroy_card(2,"m",i);
							successful++;
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) { 
							if (HasBoss(getSlot(1,i,"e"))) continue;
							if (perform) cb_destroy_card(1,"e",i);
							successful++;
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
							if (HasBoss(getSlot(2,i,"e"))) continue;
							if (perform) cb_destroy_card(2,"e",i);
							successful++;
						}
					}
				}
					if (successful == 0 && perform) { skiptoend= true; continue; };
				if (successful == 0 && !perform) return 0;		
			}

			else if (command.startsWith("HEALD") && perform && !skiptoend) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				//int successful = 0;
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) { 
							
							getSlot(1,i,"m").lifepoints += getSlot(1,i,"m").healfor;
							getSlot(1,i,"m").SetStatusValue("burn",0);
							if (getSlot(1,i,"m").healfor >0) {
								 alltriggeredabilities(getSlot(1,i,"m"),"TCM1H");
								
								
							}
							//successful += getSlot(1,i,"m").healfor;
							getSlot(1,i,"m").healfor = 0;
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) { 
							getSlot(2,i,"m").lifepoints += getSlot(2,i,"m").healfor;
							getSlot(2,i,"m").SetStatusValue("burn",0);
							if (getSlot(2,i,"m").healfor >0) {
								 alltriggeredabilities(getSlot(2,i,"m"),"TCM2H");
								
								
							}
							//successful += getSlot(2,i,"m").healfor;
							getSlot(2,i,"m").healfor = 0;
						}
					}

				 }
				//if (successful ==0) return 0;
			}
			
			else if (command.startsWith("HLRST") && perform && !skiptoend) { // reset heal
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				//int successful = 0;
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) { 
							
							//getSlot(1,i,"m").lifepoints += getSlot(1,i,"m").healfor;
							
						
							//successful += getSlot(1,i,"m").healfor;
							getSlot(1,i,"m").healfor = 0;
							getSlot(1,i,"m").SetStatusValue("burn",0);
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) { 
						//	getSlot(2,i,"m").lifepoints += getSlot(2,i,"m").healfor;
							
							//successful += getSlot(2,i,"m").healfor;
							getSlot(2,i,"m").healfor = 0;
							getSlot(2,i,"m").SetStatusValue("burn",0);
						}
					}

				 }
				//if (successful ==0) return 0;
			}
			
			
			else if (command.startsWith("PASSI") && !skiptoend) {
				// special command for Passion's Intensity
				int countdizzy = 0;
				CardmasterServerCard[] mo;
				CardmasterServerCard[] randcards = new CardmasterServerCard[0];
				if (player == 1) mo = m1;
				else mo = m2;
				//System.out.println("PASSION!");
				for (int i=0;i<5;i++) {
					//System.out.println("Checking.. " + i);
					if (!mo[i].dummy) {
						//System.out.println("Card exists: " + mo[i].name);
						if (!mo[i].dizzy && mo[i] != self && mo[i] != target.getCard()) {
						if (!(mo[i].flag("focus"))){
							
						if (!isActivatedConstant("MNDIZ",player,mo[i])) {
									
							//System.out.println("Not self, not target, and not dizzy");
							countdizzy++;
							CardmasterServerCard[] temp = new CardmasterServerCard[randcards.length+1];
							System.arraycopy(randcards,0,temp,0,randcards.length);
							
							randcards = new CardmasterServerCard[randcards.length+1];
							System.arraycopy(temp,0,randcards,0,randcards.length);
							randcards[randcards.length-1] = mo[i];							
						}
						}
						
						
						
						}
					}
					
				}
				
				
				if (countdizzy > 0) {
					if (perform) {
					
						int pick = random.nextInt(randcards.length);
						message("MES#" + getName(randcards[pick]) + " is randomly chosen!");
						dizzy(randcards[pick],true);
					}
					
					
				} else {
					if ( perform) { skiptoend= true; continue; };
					if (!perform) return 0;
					
				}
				
				
				
			}			
			else if (command.startsWith("RHEAL")  && !skiptoend) { // needs healing
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
	//	System.out.println("RHEAL");
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				int successful = 0;
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) { 
							//getSlot(1,i,"m").lifepoints += getSlot(1,i,"m").healfor;
							successful += getSlot(1,i,"m").healfor;
							//getSlot(1,i,"m").healfor = 0;
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) { 
							//getSlot(2,i,"m").lifepoints += getSlot(2,i,"m").healfor;
							successful += getSlot(2,i,"m").healfor;
							//getSlot(2,i,"m").healfor = 0;
						}
					}

				 }
				 if (successful <= 0 && perform) { skiptoend= true; continue; };
				if (successful <= 0 && !perform) return 0;
			}							
			else if (command.startsWith("A-SET") && !skiptoend) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				int successful = 0;
				
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) {
							if (perform) {							
								int diff = amount - getSlot(1,i,"m").attack;
								getSlot(1,i,"m").attack = amount;
							//	getSlot(1,i,"m").attack+= getSlot(1,i,"m").attackmod;
								
								
								
								String updown = "";
								if (diff > 0) updown = "F";
								if (diff < 0) updown = "W";
								if (diff != 0) alltriggeredabilities(getSlot(1,i,"m"),"TCM1" + updown);
								
							}
							successful++;
							
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) { 
							if (perform) {
								int diff = amount - getSlot(2,i,"m").attack;
								getSlot(2,i,"m").attack = amount;
							//	getSlot(2,i,"m").attack+= getSlot(2,i,"m").attackmod;
								
								String updown = "";
								if (diff > 0) updown = "F";
								if (diff < 0) updown = "W";
								if (diff != 0) alltriggeredabilities(getSlot(2,i,"m"),"TCM2" + updown);
							}	
							successful++;

						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) {
							 if (perform) {
								int diff = amount - getSlot(1,i,"e").attack;
								
								getSlot(1,i,"e").attack = amount;
							//	getSlot(1,i,"e").attack+= getSlot(2,i,"e").attackmod;
								
								
								String updown = "";
								if (diff > 0) updown = "F";
								if (diff < 0) updown = "W";
								if (diff != 0) alltriggeredabilities(getSlot(1,i,"e"),"TCE1" + updown);
							}	
							successful++;

						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
							if (perform) {
								int diff = amount - getSlot(2,i,"e").attack;
								
								getSlot(2,i,"e").attack = amount;
								//getSlot(2,i,"e").attack+= getSlot(2,i,"e").attackmod;
								
								
								String updown = "";
								if (diff > 0) updown = "F";
								if (diff < 0) updown = "W";
								if (diff != 0) alltriggeredabilities(getSlot(2,i,"e"),"TCE2" + updown);
							}	
							successful++;

						}
					}
				}
				if (successful == 0 && perform) { skiptoend= true; continue; }
				if (successful == 0 && !perform) return 0;			
			}
			else if (command.startsWith("AMODF") && !skiptoend) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				int suc =0;
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) { 
							if (perform) {
								getSlot(1,i,"m").attack += amount;
								
								if (getSlot(1,i,"m").attack < 0) getSlot(1,i,"m").attack = 0;
								String updown = "";
								if (amount> 0) updown = "F";
								if (amount < 0) updown = "W";
								if (amount != 0) alltriggeredabilities(getSlot(1,i,"m"),"TCM1" + updown);
								}
							suc++; 
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) { 
							if (perform) {
								
								getSlot(2,i,"m").attack += amount;
								
								if (getSlot(2,i,"m").attack < 0) getSlot(2,i,"m").attack = 0;
								String updown = "";
								if (amount> 0) updown = "F";
								if (amount < 0) updown = "W";
								if (amount != 0) alltriggeredabilities(getSlot(2,i,"m"),"TCM2" + updown);
							}
							suc++;
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) { 
							if (perform) {
								
								getSlot(1,i,"e").attack += amount;
								
								if (getSlot(1,i,"e").attack < 0) getSlot(1,i,"e").attack = 0;
								String updown = "";
								if (amount> 0) updown = "F";
								if (amount < 0) updown = "W";
								if (amount != 0) alltriggeredabilities(getSlot(1,i,"e"),"TCE1" + updown);
							}								
							suc++;
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
							if (perform) {
								getSlot(2,i,"e").attack += amount;
								
								if (getSlot(2,i,"e").attack < 0) getSlot(2,i,"e").attack = 0;
								String updown = "";
								if (amount> 0) updown = "F";
								if (amount < 0) updown = "W";
								if (amount != 0) alltriggeredabilities(getSlot(2,i,"e"),"TCE2" + updown);
							}								
							suc++;
						}
					}
				}
				if (suc == 0 && perform) { skiptoend= true; continue; };
				if (suc == 0 && !perform) return 0;						
			}
			
			else if (command.startsWith("AMULT") && !skiptoend) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				double amount = Double.parseDouble(cmdtoken.nextToken());
				
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				int suc =0;
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) { 
							suc++;
							if (perform) {	
							
								getSlot(1,i,"m").attack *= amount;
								
								if (getSlot(1,i,"m").attack < 0) getSlot(1,i,"m").attack = 0;
								String updown = "";
								if (amount> 1) updown = "F";
								if (amount < 1) updown = "W";
								if (amount != 1) alltriggeredabilities(getSlot(1,i,"m"),"TCM1" + updown);
							}
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) { 
							suc++;
							if (perform) {
								getSlot(2,i,"m").attack *= amount;
								
								if (getSlot(2,i,"m").attack < 0) getSlot(2,i,"m").attack = 0;
								String updown = "";
								if (amount> 1) updown = "F";
								if (amount < 1) updown = "W";
								if (amount != 1) alltriggeredabilities(getSlot(2,i,"m"),"TCM2" + updown);
						} }
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) { 
							suc++;
							if (perform) {	
								getSlot(1,i,"e").attack *= amount;
								
								if (getSlot(1,i,"e").attack < 0) getSlot(1,i,"e").attack = 0;
								String updown = "";
								if (amount> 1) updown = "F";
								if (amount < 1) updown = "W";
								if (amount != 1) alltriggeredabilities(getSlot(1,i,"e"),"TCE1" + updown);
								
						} }
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
							suc++;
							if (perform) {
								getSlot(2,i,"e").attack *= amount;
								
								if (getSlot(2,i,"e").attack < 0) getSlot(2,i,"e").attack = 0;
								String updown = "";
								if (amount> 1) updown = "F";
								if (amount < 1) updown = "W";
								if (amount != 1) alltriggeredabilities(getSlot(2,i,"e"),"TCE2" + updown);
							}
						}
					}
				}
				if (suc == 0 && perform) { skiptoend= true; continue; };
				if (suc == 0 && !perform) return 0;					
			}
			
		
						
			else if (command.startsWith("L-SET") && !skiptoend) {
				//System.out.println("Life set");
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				if (amount <= 1) amount = 1;
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				int suc = 0;
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) { 
							
							suc++; if (perform) {
							
							
							int diff = amount - getSlot(1,i,"m").lifepoints;
							
							if (HasRaddEffect(getSlot(1,i,"m")))  {
								if (diff > 0) getSlot(1,i,"m").lifepoints +=1;	
								if (diff < 0) getSlot(1,i,"m").lifepoints -=1;	
								
								
							}
							
							else {
							
							getSlot(1,i,"m").lifepoints = amount;
							//getSlot(1,i,"m").lifepoints+= getSlot(1,i,"m").lifemod;
							}
							
							String updown = "";
							if (diff > 0) updown = "H";
							if (diff < 0) updown = "R";
							if (diff != 0) alltriggeredabilities(getSlot(1,i,"m"),"TCM1" + updown);
							}
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) {
							suc++; if (perform) { 
								int diff = amount - getSlot(2,i,"m").lifepoints;
										if (HasRaddEffect(getSlot(2,i,"m"))) {
								if (diff > 0) getSlot(2,i,"m").lifepoints +=1;	
								if (diff < 0) getSlot(2,i,"m").lifepoints -=1;	
								
								
							}
							
							else {
							
							getSlot(2,i,"m").lifepoints = amount;
							//getSlot(1,i,"m").lifepoints+= getSlot(1,i,"m").lifemod;
							}
								//getSlot(2,i,"m").lifepoints+= getSlot(2,i,"m").lifemod;
								
								suc++;
								String updown = "";
								if (diff > 0) updown = "H";
								if (diff < 0) updown = "R";
								if (diff != 0) alltriggeredabilities(getSlot(2,i,"m"),"TCM2" + updown);
							}
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) {
							suc++; if (perform) {
								int diff = amount - getSlot(1,i,"e").lifepoints; 
								getSlot(1,i,"e").lifepoints = amount;
								
							//	getSlot(1,i,"e").lifepoints+= getSlot(1,i,"e").lifemod;
								suc++;
								String updown = "";
								if (diff > 0) updown = "H";
								if (diff < 0) updown = "R";
								if (diff != 0) alltriggeredabilities(getSlot(1,i,"e"),"TCE1" + updown);
							}
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
						suc++; if (perform) {
								int diff = amount - getSlot(2,i,"e").lifepoints;
								getSlot(2,i,"e").lifepoints = amount;
								//getSlot(2,i,"e").lifepoints+= getSlot(2,i,"e").lifemod;
								suc++;
								String updown = "";
								if (diff > 0) updown = "H";
								if (diff < 0) updown = "R";
								if (diff != 0) alltriggeredabilities(getSlot(2,i,"e"),"TCE2" + updown);
							}
						}
					}
				
				}
				if (suc == 0 && perform) { skiptoend= true; continue; };
				if (suc == 0 && !perform) return 0;			; // is this the grail I seek?				
			}	



						
			else if (command.startsWith("LSETS") && !skiptoend) { // ignore radd and don't call trigger abilities
				//System.out.println("Life set");
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				if (amount <= 1) amount = 1;
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				int suc = 0;
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) { 
							
							suc++; if (perform) {
							
							
							
						
							
							getSlot(1,i,"m").lifepoints = amount;
							//getSlot(1,i,"m").lifepoints+= getSlot(1,i,"m").lifemod;
						
							}
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) {
							suc++; if (perform) { 
								
							
							getSlot(2,i,"m").lifepoints = amount;
							}
						
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) {
							suc++; if (perform) {
							
								getSlot(1,i,"e").lifepoints = amount;
								
							//	getSlot(1,i,"e").lifepoints+= getSlot(1,i,"e").lifemod;
								suc++;
							
							}
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
						suc++; if (perform) {
							
								getSlot(2,i,"e").lifepoints = amount;
								//getSlot(2,i,"e").lifepoints+= getSlot(2,i,"e").lifemod;
								suc++;
							
							}
						}
					}
				
				}
				if (suc == 0 && perform) { skiptoend= true; continue; };
				if (suc == 0 && !perform) return 0;			; // is this the grail I seek?				
				


			}


						
			else if (command.startsWith("LSETR") && !skiptoend) { // ignore RADD ability
				//System.out.println("Life set");
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				if (amount <= 1) amount = 1;
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				int suc = 0;
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) { 
							
							suc++; if (perform) {
							
							
							int diff = amount - getSlot(1,i,"m").lifepoints;
							
						
							
							
							getSlot(1,i,"m").lifepoints = amount;
							//getSlot(1,i,"m").lifepoints+= getSlot(1,i,"m").lifemod;
							
							
							String updown = "";
							if (diff > 0) updown = "H";
							if (diff < 0) updown = "R";
							if (diff != 0) alltriggeredabilities(getSlot(1,i,"m"),"TCM1" + updown);
							}
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) {
							suc++; if (perform) { 
								int diff = amount - getSlot(2,i,"m").lifepoints;
									
							
						
							getSlot(2,i,"m").lifepoints = amount;
							//getSlot(1,i,"m").lifepoints+= getSlot(1,i,"m").lifemod;
							
								//getSlot(2,i,"m").lifepoints+= getSlot(2,i,"m").lifemod;
								
								suc++;
								String updown = "";
								if (diff > 0) updown = "H";
								if (diff < 0) updown = "R";
								if (diff != 0) alltriggeredabilities(getSlot(2,i,"m"),"TCM2" + updown);
							}
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) {
							suc++; if (perform) {
								int diff = amount - getSlot(1,i,"e").lifepoints; 
								getSlot(1,i,"e").lifepoints = amount;
								
							//	getSlot(1,i,"e").lifepoints+= getSlot(1,i,"e").lifemod;
								suc++;
								String updown = "";
								if (diff > 0) updown = "H";
								if (diff < 0) updown = "R";
								if (diff != 0) alltriggeredabilities(getSlot(1,i,"e"),"TCE1" + updown);
							}
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
						suc++; if (perform) {
								int diff = amount - getSlot(2,i,"e").lifepoints;
								getSlot(2,i,"e").lifepoints = amount;
								//getSlot(2,i,"e").lifepoints+= getSlot(2,i,"e").lifemod;
								suc++;
								String updown = "";
								if (diff > 0) updown = "H";
								if (diff < 0) updown = "R";
								if (diff != 0) alltriggeredabilities(getSlot(2,i,"e"),"TCE2" + updown);
							}
						}
					}
				
				}
				if (suc == 0 && perform) { skiptoend= true; continue; };
				if (suc == 0 && !perform) return 0;			; // is this the grail I seek?				
			}	




			else if (command.startsWith("LMODF") && !skiptoend && perform) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int oamount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) {
							int amount = oamount;
							if (HasRaddEffect(getSlot(1,i,"m")) && amount >1) amount = 1;  
							if (HasRaddEffect(getSlot(1,i,"m")) && amount <1) amount = -1;   
							getSlot(1,i,"m").lifepoints += amount;
							String updown = "";
							if (amount > 0) updown = "H";
							if (amount < 0) updown = "R";
							if (amount != 0) alltriggeredabilities(getSlot(1,i,"m"),"TCM1" + updown);
							if ((getSlot(1,i,"m").lifepoints+getSlot(1,i,"m").lifemod) < 1) destroy_card(1,"m",i);
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) {
							int amount = oamount;
							if (HasRaddEffect(getSlot(2,i,"m")) && amount >1) amount = 1;  
							if (HasRaddEffect(getSlot(2,i,"m")) && amount <1) amount = -1;  
							
							getSlot(2,i,"m").lifepoints += amount;
							String updown = "";
							if (amount > 0) updown = "H";
							if (amount < 0) updown = "R";
							if (amount != 0) alltriggeredabilities(getSlot(2,i,"m"),"TCM2" + updown);
						
							if ((getSlot(2,i,"m").lifepoints+getSlot(2,i,"m").lifemod) < 1) destroy_card(2,"m",i);
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) {
							int amount = oamount;
							//if (getSlot(1,i,"e").flag("radd")) amount = 1;  
							getSlot(1,i,"e").lifepoints += amount;
							String updown = "";
							if (amount > 0) updown = "H";
							if (amount < 0) updown = "R";
							if (amount != 0) alltriggeredabilities(getSlot(1,i,"e"),"TCE1" + updown);
						
							//if (getSlot(1,i,"e").lifepoints < 1)destroy_card(1,"e",i);
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
						int amount = oamount;
							getSlot(2,i,"e").lifepoints += amount;
							String updown = "";
								if (amount > 0) updown = "H";
							if (amount < 0) updown = "R";
							if (amount != 0) alltriggeredabilities(getSlot(2,i,"e"),"TCE2" + updown);
						
							//if (getSlot(2,i,"e").lifepoints < 1) destroy_card(2,"e",i);;
						}
					}
				}			
			}



			else if (command.startsWith("STATU") && !skiptoend && perform) { // set a temporary flag 
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String flagname = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				boolean suc = false;
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, getSlot(1,i,"m"), self, target)) {
							suc = true;
							CardmasterServerCard card = getSlot(1,i,"m"); 
							if (perform) {
								card.SetStatusValue(flagname,amount);	
							}
						
						}
						if (player2==2) if (matchcard(a, c,d, getSlot(2,i,"m"), self, target)) {
							suc = true;
							CardmasterServerCard card = getSlot(2,i,"m");
							if (perform) {
								card.SetStatusValue(flagname,amount);	
							}  
						
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, getSlot(1,i,"e") , self, target)) {
							suc = true;
							CardmasterServerCard card = getSlot(1,i,"e"); 
							if (perform) {
								card.SetStatusValue(flagname,amount);	
							} 						
						
							//if (getSlot(1,i,"e").lifepoints < 1)destroy_card(1,"e",i);
						}
						if (player2==2) if (matchcard(a, c,d,  getSlot(2,i,"e"), self, target)) { 
					
							suc = true;
							CardmasterServerCard card = getSlot(2,i,"e"); 
							if (perform) {
								card.SetStatusValue(flagname,amount);	
							} 						
						}
					}
				}			
			
				if (!suc && perform) { skiptoend= true; continue; }
				if (!suc && !perform) return 0;	
			
			}



			else if (command.startsWith("S-SET") && !skiptoend && perform) { // speed modify
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				boolean found = false;
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) { 
							CardmasterServerCard targ = getSlot(1,i,"m");
							
							if (targ.ability) found = true;
							int oldspeed = targ.speed;
							targ.speed = amount;
						//	if (targ(1,i,"m").speed > 5) targ(1,i,"m").speed = 5;
					//		if (targ(1,i,"m").speed < 1) targ(1,i,"m").speed = 1;
							
						
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) { 
						//	getSlot(2,i,"m").speed += amount;
							
							CardmasterServerCard targ = getSlot(2,i,"m");
							
							if (targ.ability) found = true;
							int oldspeed = targ.speed;
							targ.speed = amount;
					//		if (targ(1,i,"m").speed > 5) targ(1,i,"m").speed = 5;
					//		if (targ(1,i,"m").speed < 1) targ(1,i,"m").speed = 1;
						
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) { 
							//getSlot(1,i,"e").speed += amount;
							
							CardmasterServerCard targ = getSlot(1,i,"e");
							
							if (targ.ability) found = true;
							int oldspeed = targ.speed;
							targ.speed = amount;
						//	if (targ(1,i,"m").speed > 5) targ(1,i,"m").speed = 5;
						//	if (targ(1,i,"m").speed < 1) targ(1,i,"m").speed = 1;
						
						
							//if (getSlot(1,i,"e").lifepoints < 1)destroy_card(1,"e",i);
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
						//	getSlot(2,i,"e").speed += amount;
							
							CardmasterServerCard targ = getSlot(2,i,"e");
							
							if (targ.ability) found = true;
							int oldspeed = targ.speed;
							targ.speed = amount;
						//	if (targ(1,i,"m").speed > 5) targ(1,i,"m").speed = 5;
						//	if (targ(1,i,"m").speed < 1) targ(1,i,"m").speed = 1;
							
						
							//if (getSlot(2,i,"e").lifepoints < 1) destroy_card(2,"e",i);;
						}
					}
				}
					if (!found && perform) { skiptoend= true; continue; }
				if (!found && !perform) return 0;			
			}

			else if (command.startsWith("SMODF") && !skiptoend)  { // speed modify
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				boolean found = false;
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) { 
							CardmasterServerCard targ = getSlot(1,i,"m");
							
							if (targ.ability) found = true;
							if (perform){
							
							int oldspeed = targ.speed;
							targ.speed += amount;
							if (targ.speed > 5) targ.speed = 5;
							if (oldspeed != 0) {
							
								if (targ.speed < 1) targ.speed = 1;
							} else if (targ.speed < 0) targ.speed = 0;
							}
							
						
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) { 
						//	getSlot(2,i,"m").speed += amount;
							
							CardmasterServerCard targ = getSlot(2,i,"m");
							
							if (targ.ability) found = true;
							if (perform){
							
							int oldspeed = targ.speed;
							
							targ.speed += amount;
							
							if (targ.speed > 5) targ.speed = 5;
							if (oldspeed != 0) {
							
								if (targ.speed < 1) targ.speed = 1;
							} else if (targ.speed < 0) targ.speed = 0;
							
							}
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) { 
							//getSlot(1,i,"e").speed += amount;
							
							CardmasterServerCard targ = getSlot(1,i,"e");
							
							if (targ.ability) found = true;
							
							if (perform) {
							
							int oldspeed = targ.speed;
							targ.speed += amount;
							if (targ.speed > 5) targ.speed = 5;
							if (oldspeed != 0) {
							
								if (targ.speed < 1) targ.speed = 1;
							} else if (targ.speed < 0) targ.speed = 0;
							}
						
						
							//if (getSlot(1,i,"e").lifepoints < 1)destroy_card(1,"e",i);
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
						//	getSlot(2,i,"e").speed += amount;
							
							CardmasterServerCard targ = getSlot(2,i,"e");
							
							if (targ.ability) found = true;
							if (perform){
							
							int oldspeed = targ.speed;
							targ.speed += amount;
							if (targ.speed > 5) targ.speed = 5;
							if (oldspeed != 0) {
							
								if (targ.speed < 1) targ.speed = 1;
							} else if (targ.speed < 0) targ.speed = 0;
							}
							
						
							//if (getSlot(2,i,"e").lifepoints < 1) destroy_card(2,"e",i);;
						}
					}
				}
					if (!found && perform) { skiptoend= true; continue; }
				if (!found && !perform) return 0;			
			}


		else if (command.startsWith("LMULT")&& !skiptoend && perform) { // mulitply lifepoints
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				double amount = Double.parseDouble(cmdtoken.nextToken());
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) { 
						int oldamount =getSlot(1,i,"m").lifepoints;
							getSlot(1,i,"m").lifepoints *= amount;	
							int newamount = (int)(getSlot(1,i,"m").lifepoints * amount);
							
							if (HasRaddEffect(getSlot(1,i,"m"))){
								if (oldamount < newamount) getSlot(1,i,"m").lifepoints = oldamount +1;
								if (newamount < oldamount) getSlot(1,i,"m").lifepoints = oldamount -1;
								
								
								
							}
						
						
							String updown = "";
							if (amount > 1) updown = "H";
							if (amount < 1) updown = "R";
							if (amount != 1) alltriggeredabilities(getSlot(1,i,"m"),"TCM1" + updown);
							if (getSlot(1,i,"m").lifepoints < 1) getSlot(1,i,"m").lifepoints = 1;
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) { 
						
						
						
							int oldamount =getSlot(2,i,"m").lifepoints;
						
							getSlot(2,i,"m").lifepoints *= amount;
							
							
							
								int newamount = (int)(getSlot(2,i,"m").lifepoints * amount);
							
							if (HasRaddEffect(getSlot(2,i,"m"))) {
									if (oldamount < newamount) getSlot(2,i,"m").lifepoints = oldamount +1;
								if (newamount < oldamount) getSlot(2,i,"m").lifepoints = oldamount -1;
								
								
								
							}
						
							
							
							
							String updown = "";
							if (amount > 1) updown = "H";
							if (amount < 1) updown = "R";
							if (amount != 1) alltriggeredabilities(getSlot(2,i,"m"),"TCM2" + updown);
						
							if (getSlot(2,i,"m").lifepoints < 1)getSlot(2,i,"m").lifepoints = 1;
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) { 
							getSlot(1,i,"e").lifepoints *= amount;
							String updown = "";
							if (amount > 1) updown = "H";
							if (amount < 1) updown = "R";
							if (amount != 1) alltriggeredabilities(getSlot(1,i,"e"),"TCE1" + updown);
						
							//if (getSlot(1,i,"e").lifepoints < 1)destroy_card(1,"e",i);
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
							getSlot(2,i,"e").lifepoints *= amount;
							String updown = "";
								if (amount > 1) updown = "H";
							if (amount < 1) updown = "R";
							if (amount != 1) alltriggeredabilities(getSlot(2,i,"e"),"TCE2" + updown);
						
							//if (getSlot(2,i,"e").lifepoints < 1) destroy_card(2,"e",i);;
						}
					}
				}
				
							
			}



	else if (command.startsWith("ADTYP")&& !skiptoend ) { // add type
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String type = cmdtoken.nextToken();
				
				Vector <CardmasterServerCard> matched = CardsMatching(player,a,b,c,d,self,target);
				
				
				boolean success = false;
				
				for (CardmasterServerCard e : matched) {
					if (!ismType(e,type)) {if (perform) e.mtype = e.mtype + " " + type;}
							success = true; 	
				}
				if (!success && perform) { skiptoend= true; continue; }
				if (!success && !perform) return 0;		
			}

	else if (command.startsWith("CHTYP") && !skiptoend) { // change type
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String type = cmdtoken.nextToken();
				Vector <CardmasterServerCard> matched = CardsMatching(player,a,b,c,d,self,target);
				
				
				boolean success = false;
				
				for (CardmasterServerCard e : matched) {
					if (!ismType(e,type)) {if (perform) e.mtype =  type;}
							success = true; 	
				}	
				if (!success && perform) { skiptoend= true; continue; };
				if (!success && !perform) return 0;			
			}



			else if (command.startsWith("LMODT") && !skiptoend && perform) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int oamount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) {
							int amount = oamount;
							if (HasRaddEffect(getSlot(1,i,"m")) && amount > 0) amount = 1; 
							 else if (HasRaddEffect(getSlot(1,i,"m")) && amount < 0) amount = -1; 
							 
						//	getSlot(1,i,"m").lifepoints += amount;
							getSlot(1,i,"m").lifemod += amount;
							String updown = "";
							if (amount > 0) updown = "H";
							if (amount < 0) updown = "R";
							if (amount != 0) alltriggeredabilities(getSlot(1,i,"m"),"TCM1" + updown);
							if ((getSlot(1,i,"m").lifepoints+getSlot(1,i,"m").lifemod) <= 0) destroy_card(1,"m",i);
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) {
							int amount = oamount;
							if (HasRaddEffect(getSlot(2,i,"m")) && amount > 0) amount = 1;  
							else if (HasRaddEffect(getSlot(2,i,"m")) && amount < 0) amount = -1; 
							 
						//	getSlot(2,i,"m").lifepoints += amount;
							getSlot(2,i,"m").lifemod += amount;
							String updown = "";
							if (amount > 0) updown = "H";
							if (amount < 0) updown = "R";
							if (amount != 0) alltriggeredabilities(getSlot(2,i,"m"),"TCM2" + updown);
						
							if ((getSlot(2,i,"m").lifepoints+getSlot(2,i,"m").lifemod) <= 0) destroy_card(2,"m",i);
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) {
							int amount = oamount;
							//if (getSlot(1,i,"e").flag("radd")) amount = 1;  
						//	getSlot(1,i,"e").lifepoints += amount;
							getSlot(1,i,"e").lifemod += amount;
							String updown = "";
							if (amount > 0) updown = "H";
							if (amount < 0) updown = "R";
							if (amount != 0) alltriggeredabilities(getSlot(1,i,"e"),"TCE1" + updown);
						
							//if (getSlot(1,i,"e").lifepoints < 1)destroy_card(1,"e",i);
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) {
							int amount = oamount;
							//if (getSlot(2,i,"e").flag("radd")) amount = 1;  
						//	getSlot(2,i,"e").lifepoints += amount;
							getSlot(2,i,"e").lifemod += amount;
							String updown = "";
								if (amount > 0) updown = "H";
							if (amount < 0) updown = "R";
							if (amount != 0) alltriggeredabilities(getSlot(2,i,"e"),"TCE2" + updown);
						
							//if (getSlot(2,i,"e").lifepoints < 1) destroy_card(2,"e",i);;
						}
					}
				}			
			}

			else if (command.startsWith("AMODT") && !skiptoend && perform) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) { 
							
							int changeamount = amount;
//							if ((amount + getSlot(1,i,"m").attack) < 0) changeamount = -1 * getSlot(1,i,"m").attack;
							getSlot(1,i,"m").attackmod += changeamount;
							//getSlot(1,i,"m").attack += changeamount;
							
							String updown = "";
							if (amount > 0) updown = "F";
							if (amount < 0) updown = "W";
							if (amount != 0) alltriggeredabilities(getSlot(1,i,"m"),"TCM1" + updown);
							//if (getSlot(1,i,"m").attack < 0) getSlot(1,i,"m").attack = 0;
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) { 
							int changeamount = amount;
//							if ((amount + getSlot(2,i,"m").attack) < 0) changeamount = -1 * getSlot(2,i,"m").attack;
							getSlot(2,i,"m").attackmod += changeamount;
						//	getSlot(2,i,"m").attack += amount;
							
							String updown = "";
							if (amount > 0) updown = "F";
							if (amount < 0) updown = "W";
							if (amount != 0) alltriggeredabilities(getSlot(2,i,"m"),"TCM2" + updown);
							//if (getSlot(2,i,"m").attack < 0) getSlot(2,i,"m").attack = 0;
							//if (getSlot(2,i,"m").lifepoints < 1) destroy_card(2,"m",i);
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) { 
							//getSlot(1,i,"e").attack += amount;
							int changeamount = amount;
							//if (amount + getSlot(1,i,"e").attack < 0) changeamount = -1 * getSlot(1,i,"e").attack;
						
							getSlot(1,i,"e").attackmod += changeamount;
							String updown = "";
							if (amount > 0) updown = "F";
							if (amount < 0) updown = "W";
							if (amount != 0) alltriggeredabilities(getSlot(1,i,"e"),"TCE1" + updown);
						//if (getSlot(1,i,"e").attack < 0) getSlot(1,i,"e").attack = 0;
							//if (getSlot(1,i,"e").lifepoints < 1)destroy_card(1,"e",i);
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
							//getSlot(2,i,"e").attack += amount;
							int changeamount = amount;
							//if (amount + getSlot(2,i,"e").attack < 0) changeamount = -1 * getSlot(2,i,"e").attack;
						
							getSlot(2,i,"e").attackmod += changeamount;
							String updown = "";
							if (amount > 0) updown = "F";
							if (amount < 0) updown = "W";
							if (amount != 0) alltriggeredabilities(getSlot(2,i,"e"),"TCE2" + updown);
						
							//if (getSlot(2,i,"e").lifepoints < 1) destroy_card(2,"e",i);;
						}
					}
				}			
			}


	else if (command.startsWith("BLKDZ") && !skiptoend) { // block undizzy for next turn
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
//System.out.println("BLKDZ");
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();//if (!cmdtoken.hasMoreTokens()) return 0;
				//int amount = getValue(cmdtoken.nextToken(),count,player,target,self);
				
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				int suc = 0;
				if (a.startsWith("rnd")) suc = 1;
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) {
						//	if (!getSlot(1,i,"m").dizzy) { 
							
								if (perform) getSlot(1,i,"m").noundizzy = true;
								suc++;
								
						//	}
							
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) { 
						//	if (!getSlot(2,i,"m").dizzy) { 
								if (perform)  getSlot(2,i,"m").noundizzy = true;
								suc++;
							//}
						
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) { 
							//if (!getSlot(1,i,"e").dizzy) { 
								if (perform)  getSlot(1,i,"e").noundizzy = true;
								suc++;
							//}
							
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
							//if (!getSlot(2,i,"e").dizzy) { 
								if (perform)  getSlot(2,i,"e").noundizzy = true;
								suc++;
							//}
						
						}
					}
				}
				if (suc == 0 && perform) { skiptoend= true; continue; }
				if (suc == 0 && !perform) return 0;						
			}	
			else if (command.startsWith("FLEET") && !skiptoend) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();//if (!cmdtoken.hasMoreTokens()) return 0;
				//int amount = getValue(cmdtoken.nextToken(),count,player,target,self);
				
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				int suc = 0;
				if (a.startsWith("rnd")) suc = 1;
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) {
						//	if (!getSlot(1,i,"m").dizzy) { 
							
								if (perform) getSlot(1,i,"m").fleet = true;
								suc++;
								
						//	}
							
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) { 
						//	if (!getSlot(2,i,"m").dizzy) { 
								if (perform)getSlot(2,i,"m").fleet = true;
								suc++;
							//}
						
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) { 
							//if (!getSlot(1,i,"e").dizzy) { 
								if (perform)getSlot(1,i,"e").fleet = true;
								suc++;
							//}
							
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
							//if (!getSlot(2,i,"e").dizzy) { 
								if (perform) getSlot(2,i,"e").fleet = true;
								suc++;
							//}
						
						}
					}
				}
				if (suc == 0 && perform) { skiptoend= true; continue; };
				if (suc == 0 && !perform) return 0;						
			}	
			
			else if (command.startsWith("DIZZY") && !skiptoend) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();//if (!cmdtoken.hasMoreTokens()) return 0;
				//int amount = getValue(cmdtoken.nextToken(),count,player,target,self);
				
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				int suc = 0;
				if (a.startsWith("rnd")) suc = 1;
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) {
						//	if (!getSlot(1,i,"m").dizzy) { 
								if (!(m1[i].flag("focus")))
								if (!isActivatedConstant("MNDIZ",player,getSlot(1,i,"m"))) {
									
									if (perform) dizzy(getSlot(1,i,"m"),true);
									suc++;
								}
								
						//	}
							
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) { 
						//	if (!getSlot(2,i,"m").dizzy) { 
							if (!(m2[i].flag("focus")))
							if (!isActivatedConstant("MNDIZ",player,getSlot(2,i,"m"))) {
									
								if (perform) dizzy(getSlot(2,i,"m"),true);
								suc++;
							}
						
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) { 
							if (!isActivatedConstant("MNDIZ",player,getSlot(1,i,"e"))) {
									
								if (perform) dizzy(getSlot(1,i,"e"),true);
								suc++;
							}
							
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) { 
							if (!isActivatedConstant("MNDIZ",player,getSlot(2,i,"e"))) {
									
								if (perform) dizzy(getSlot(2,i,"e"),true);
								suc++;
							}
						
						}
					}
				}
				if (suc == 0 && perform) { skiptoend= true; continue; };
				if (suc == 0 && !perform) return 0;						
			}	
			else if (command.startsWith("XXDIZ") && !skiptoend && perform) {
				dizzy(self,false);
			}		
			else if (command.startsWith("UNDIZ") && !skiptoend) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();//if (!cmdtoken.hasMoreTokens()) return 0;
				//int amount = getValue(cmdtoken.nextToken(),count,player,target,self);
				
				int player1 = 0;
				int player2 = 0;
				boolean monster = false;
				boolean effect = false;
				
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				
				int suc = 0;
				if (a.startsWith("rnd")) suc = 1;
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) {
							if (!isActivatedConstant("MNOUD",player,getSlot(1,i,"m"))) {
								
								 suc++; 
								if (perform) 	dizzy(getSlot(1,i,"m"),false);
							}	
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) {
							if (!isActivatedConstant("MNOUD",player,getSlot(2,i,"m"))) {
								
								 suc++; 
								if (perform) 	dizzy(getSlot(2,i,"m"),false);
							}
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) { 
							if (!isActivatedConstant("MNOUD",player,getSlot(1,i,"e"))) {
								
								 suc++; 
								if (perform) 	dizzy(getSlot(1,i,"e"),false);
							}
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) {
							if (!isActivatedConstant("MNOUD",player,getSlot(2,i,"e"))) {
								
								 suc++; 
								if (perform) 	dizzy(getSlot(2,i,"e"),false);
							}
						
						}
					}
				}
				if (suc == 0 && perform) { skiptoend= true; continue; }
				if (suc == 0 && !perform) return 0;					
			}
			else if (command.startsWith("SKIPT") && !skiptoend && perform) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				//System.out.println("Amount: " + amount);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) { owne = true; opon = true; }
				if (opon) {
					int opponent = not_player(player);
					addSkip(opponent,amount);
				}
				if (owne) {
					int opponent = player;
					addSkip(opponent,amount);		
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					//System.out.println("Targ: " + opponent);
					if (opponent == -1) return 0;
					addSkip(opponent,amount);
					
				}
					
			}
			
			
			
			else if (command.startsWith("RECYG") && !skiptoend && perform) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();
			//	int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				//System.out.println("Amount: " + amount);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) { owne = true; opon = true; }
				if (opon) {
					int opponent = not_player(player);
					
					if (opponent == 1) deck1.recyclegrave();
					if (opponent == 2) deck2.recyclegrave();
					
				//	addSkip(opponent,amount);
				}
				if (owne) {
					int opponent = player;
						if (opponent == 1) deck1.recyclegrave();
					if (opponent == 2) deck2.recyclegrave();
				//	addSkip(opponent,amount);		
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					//System.out.println("Targ: " + opponent);
					if (opponent == -1) return 0;
						if (opponent == 1) deck1.recyclegrave();
					if (opponent == 2) deck2.recyclegrave();
				//	addSkip(opponent,amount);
					
				}
					
			}	
			
			
					
			else if (command.startsWith("LIFEP") && !skiptoend && perform) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				//System.out.println("Amount: " + amount);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) { owne = true; opon = true; }
				
				if (opon) {
					int opponent = not_player(player);
					if (opponent == 1) { 
					
							if (damage_player(1,-1 * amount) <= 0) {
								if (!endedonce && winner == 0)  duelendedbytype = "Direct Damage - " + -amount;
								endduel(2);
								return 1;	
							}
				
							
						
					}	
					if (opponent == 2) { 
						
							if (damage_player(2,-1 * amount) <= 0)  {
								if (!endedonce && winner == 0)  duelendedbytype = "Direct Damage - " + -amount;
								endduel(1);	
								return 1;
							}
							
						
					}						
				}
				if (owne) {
					int opponent = player;
					if (opponent == 1) { 
						
							if (damage_player(1,0-amount) <= 0) {
								if (!endedonce && winner == 0)  duelendedbytype = "Direct Damage - " + -amount;
								endduel(2);	
								return 1;
							}
							
						
					}	
					if (opponent == 2) { 
						
							if (damage_player(2,0-amount) <= 0) {
								if (!endedonce && winner == 0)  duelendedbytype = "Direct Damage - " + -amount;
								endduel(1);	
								return 1;
							}
							
						
					}
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
					
					
					if (opponent == 1) { 
					
							if (damage_player(1,-1*amount) <= 0) {
						if (!endedonce && winner == 0) 	duelendedbytype = "Direct Damage - " + -amount;
								endduel(2);	
							}
							
						
					}	
					if (opponent == 2) { 
						
							if (damage_player(2,-1*amount) <= 0) {
						if (!endedonce && winner == 0) 	duelendedbytype = "Direct Damage - " + -amount;
								endduel(1);	
							}
							
						
					}
					
				}
					
			}

			else if (command.startsWith("KILL^") && !skiptoend && perform) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();
				//System.out.println("KILL");
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
			//	if (play.equals("both")) { owne = true; opon = true; }
				
				if (opon) {
					int opponent = not_player(player);
					if (opponent == 1) { 
						if (!endedonce && winner == 0) duelendedbytype = "Win Condition";
							
								endduel(2);
								return 1;	
											
							
						
					}	
					if (opponent == 2) { 
						if (!endedonce && winner == 0) duelendedbytype = "Win Condition";
					
								endduel(1);	
								return 1;
					}				
				}
				if (owne) {
					int opponent = player;
					if (opponent == 1) { 
					if (!endedonce && winner == 0) 	duelendedbytype = "Lose Condition";
				
								endduel(2);	
								return 1;
						
					}	
					if (opponent == 2) { 
					
					if (!endedonce && winner == 0) 	duelendedbytype = "Lose Condition";
								endduel(1);	
								return 1;
						
					}
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
					
					
					if (opponent == 1) { 
					if (!endedonce && winner == 0) 	duelendedbytype = "Win Condition";
					
								endduel(2);	
				
							
						
					}	
					if (opponent == 2) { 
						
					if (!endedonce && winner == 0) 	duelendedbytype = "Win Condition";
								endduel(1);	
				
							
						
					}
					
				}
					
			}



			else if (command.startsWith("LISET") && !skiptoend && perform) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				//System.out.println("Amount: " + amount);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) { owne = true; opon = true; }
				
				if (opon) {
					int opponent = not_player(player);
					if (opponent == 1) { 
						int diff = amount - p1life;
						p1life=amount; 
							String updown = "";
						if (diff > 0) updown = "H";
						if (diff < 0) updown = "L";
						if (diff != 0) alltriggeredabilities(null,"TP1" + updown);
						
						 
						
					}	
					if (opponent == 2) { 
						int diff = amount - p2life;
						p2life=amount; 
							String updown = "";
						if (diff > 0) updown = "H";
						if (diff < 0) updown = "L";
						if (diff != 0) alltriggeredabilities(null,"TP2" + updown);	
						
					}						
				}
				if (owne) {
					int opponent = player;
					if (opponent == 1) { 
						int diff = amount - p1life;
						p1life=amount; 
							String updown = "";
						if (diff > 0) updown = "H";
						if (diff < 0) updown = "L";
						if (diff != 0) alltriggeredabilities(null,"TP1" + updown);
						
					}	
					if (opponent == 2) { 
						int diff = amount - p2life;
						p2life=amount; 
							String updown = "";
						if (diff > 0) updown = "H";
						if (diff < 0) updown = "L";
						if (diff != 0) alltriggeredabilities(null,"TP2" + updown);	
						
					}
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
					
					
					if (opponent == 1) { 
						int diff = amount - p1life;
						p1life=amount; 
							String updown = "";
						if (diff > 0) updown = "H";
						if (diff < 0) updown = "L";
						if (diff != 0) alltriggeredabilities(null,"TP1" + updown);
						
					}	
					if (opponent == 2) { 
						int diff = amount - p2life;
						p2life=amount; 
							String updown = "";
						if (diff > 0) updown = "H";
						if (diff < 0) updown = "L";
						if (diff != 0) alltriggeredabilities(null,"TP2" + updown);						
					}
					
				}
					
			}


			else if (command.startsWith("MANAD") && !skiptoend && perform) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) { owne = true; opon = true; }
				
				if (opon) {
					int opponent = not_player(player);
					if (opponent == 1) { setMana("D",1,p1D+amount); 
						p1Du+=amount; }		
					if (opponent == 2) { setMana("D",2,p2D+amount);
						p2Du+=amount; }	 
				}
				if (owne) {
					int opponent = player;
					if (opponent == 1) {setMana("D",1,p1D+amount); 
						p1Du+=amount; }	
					if (opponent == 2) { setMana("D",2,p2D+amount);
						p2Du+=amount; }	 
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
					if (opponent == 1) {setMana("D",1,p1D+amount); 
						p1Du+=amount; }
					if (opponent == 2) { setMana("D",2,p2D+amount); 
						p2Du+=amount; }	
					
				}
					
			}		
			else if (command.startsWith("MANAG")&& !skiptoend && perform) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) { owne = true; opon = true; }
				
				if (opon) {
					int opponent = not_player(player);
					if (opponent == 1) {
						setMana("G",1,p1G+amount);
						p1Gu+=amount; 
						}
					if (opponent == 2) { setMana("G",2,p2G+amount); 
						p2Gu+=amount; }	
				}
				if (owne) {
					int opponent = player;
					if (opponent == 1) {setMana("G",1,p1G+amount); 
						p1Gu+=amount; }
					if (opponent == 2) { setMana("G",2,p2G+amount);
						p2Gu+=amount;  }
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
					if (opponent == 1) {setMana("G",1,p1G+amount);
						p1Gu+=amount; }	 
					if (opponent == 2) { setMana("G",2,p2G+amount);
						p2Gu+=amount; }	 
					
				}
					
			}
			else if (command.startsWith("CNISM")&& !skiptoend && perform) { // special ability COMMUNISM
				int totalmana = p1L+p1D+p1G+p2D+p2L+p2G;
				totalmana = totalmana / 6;
				setMana("D",1,totalmana);
				setMana("G",1,totalmana);
				setMana("L",1,totalmana);
				setMana("D",2,totalmana);
				setMana("L",2,totalmana);
				setMana("G",2,totalmana);
				p2Gu = totalmana;
				p1Gu = totalmana;
				p2Du = totalmana;
				p1Du = totalmana;
				p2Lu = totalmana;
				p1Lu = totalmana;
				
				
			
				
				
			}
			else if (command.startsWith("MANAL")&& !skiptoend && perform) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) {  owne = true; opon = true; }
				
				if (opon) {
					int opponent = not_player(player);
					if (opponent == 1) {setMana("L",1,p1L+amount);
						p1Lu+=amount; }	 
					if (opponent == 2) { setMana("L",2,p2L+amount);
						p2Lu+=amount; }	 
				}
				if (owne) {
					int opponent = player;
					if (opponent == 1) {setMana("L",1,p1L+amount);
						p1Lu+=amount; }	 
					if (opponent == 2) { setMana("L",2,p2L+amount); 
						p2Lu+=amount; }	
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
					if (opponent == 1) {setMana("L",1,p1L+amount); 
						p1Lu+=amount;  } 	
					if (opponent == 2) { setMana("L",2,p2L+amount); 
						p2Lu+=amount; }	
					
				}
					
			}
			else if (command.startsWith("SMANL")&& !skiptoend && perform) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) {  owne = true; opon = true; }
				
				if (opon) {
					int opponent = not_player(player);
					if (opponent == 1) {setMana("L",1,amount); 
						p1Lu=amount;}
					if (opponent == 2) { setMana("L",2,amount); 
						p2Lu=amount;}
				}
				if (owne) {
					int opponent = player;
					if (opponent == 1) {setMana("L",1,amount); 
						p1Lu=amount; }
					if (opponent == 2) { setMana("L",2,amount); 
						p2Lu=amount; }	
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
					if (opponent == 1) {setMana("L",1,amount); 
						p1Lu=amount; }
					if (opponent == 2) { setMana("L",2,amount); 
						p2Lu=amount; }	
					
				}
					
			}			
			else if (command.startsWith("SMANG")&& !skiptoend && perform) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) {  owne = true; opon = true;}
				
				if (opon) {
					int opponent = not_player(player);
					if (opponent == 1) {setMana("G",1,amount); 
						p1Gu=amount; }
					if (opponent == 2) { setMana("G",2,amount); 
						p2Gu=amount; }
				}
				if (owne) {
					int opponent = player;
					if (opponent == 1) {setMana("G",1,amount); 
						p1Gu=amount; }
					if (opponent == 2) { setMana("G",2,amount);
						p2Gu=amount;  }
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
					if (opponent == 1) {setMana("G",1,amount); 
						p1Gu=amount; }
					if (opponent == 2) { setMana("G",2,amount); 
						p2Gu=amount; }
					
				}
					
			}
			else if (command.startsWith("SMAND")&& !skiptoend && perform) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) {  owne = true; opon = true; }
				
				if (opon) {
					int opponent = not_player(player);
					if (opponent == 1) {setMana("D",1,amount);
						p1Du=amount;  }
					if (opponent == 2) { setMana("D",2,amount);
						p2Du=amount;  }
				}
				if (owne) {
					int opponent = player;
					if (opponent == 1) {setMana("D",1,amount);
						p1Du=amount;  }
					if (opponent == 2) { setMana("D",2,amount); 
						p2Du=amount; }
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
					if (opponent == 1) {setMana("D",1,amount);
						p1Du=amount;  }
					if (opponent == 2) { setMana("D",2,amount);
						p2Du=amount;  }	
					
				}
					
			}		
			
			else if (command.startsWith("MILLN") && !skiptoend && perform) {
					StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();
				if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
			//	int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				//System.out.println("Determining who to do it to...");
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) {  owne = true; opon = true;}
								
				if (opon) {
					int opponent = not_player(player);
					mill(opponent,amount);
				}
				if (owne) {
					int opponent = player;
					mill(opponent,amount);	
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
					mill(opponent,amount);
					
				}
				//System.out.println("Card Drawn...");
				
			}	
 
			else if (command.startsWith("DRAWN") && !skiptoend && perform) { // draw more cards
			//	System.out.println("Drawing a card...");
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();
				if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
			//	int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				//System.out.println("Determining who to do it to...");
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) {  owne = true; opon = true;}
				
				
				for (int x = 0;x<amount;x++) {				
				if (opon) {
					int opponent = not_player(player);
					drawCardInc(opponent);
				}
				if (owne) {
					int opponent = player;
					drawCardInc(opponent);	
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
					drawCardInc(opponent);
					
				}
				//System.out.println("Card Drawn...");
				}
					
			}


			else if (command.startsWith("DRAWO") && !skiptoend && perform) { // draw from opponent's deck
			//	System.out.println("Drawing a card...");
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();
				if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
			//	int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				//System.out.println("Determining who to do it to...");
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) {  owne = true; opon = true;}
				//System.out.println("Amount: " + amount);
				for (int x = 0;x<amount;x++) {				
				if (opon) {
					int opponent = not_player(player);
					drawCardOpp(opponent);
				}
				if (owne) {
					int opponent = player;
					drawCardOpp(opponent);	
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
					drawCardOpp(opponent);
					
				}
				//System.out.println("Card Drawn...");
				}
					
			}
			
			
			else if (command.startsWith("NXDRC")) { // puts next card to draw in count.
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				
				String play = cmdtoken.nextToken();
				boolean opon = false;
				boolean targ = false;
				boolean ownr = false;
				//System.out.println("Determining who to do it to...");
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) ownr = true;
				int opponent = 0;
				if (opon) {
					opponent = not_player(player);
					
					
				}
				
				if (ownr) {
					opponent = player;
					
				}
				if (targ) {
					if (target == null) return 0;
					opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
				}
				int cardid = pollNextDrawCard(opponent);
				if (cardid <= 0) return 0;
				count = cardid;
				
			//	if (play.equals("both")) {  owne = true; opon = true; }
					
				
			}
			else if (command.startsWith("IDTYP") ) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;				
				int id = getvalue(cmdtoken.nextToken(),count,player,target,self);
				String typecode = cmdtoken.nextToken();
				CardmasterServerCard pollcard = carddata[id];
				boolean failure = false;
				if (pollcard == null) failure = true;
				if (!failure) if (!pollcard.typecode.equals(typecode) && !typecode.equals("a")) failure = true;
				
				if (nextab) {
					if (failure) toskipa = true;
					else toskipb = true;
				}
				else {
					return 0;
					
					
				}
				
			}
			else if (command.startsWith("IDRAC") ) { // monster type thing
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;				
				int id = getvalue(cmdtoken.nextToken(),count,player,target,self);
				String typecode = cmdtoken.nextToken();
				CardmasterServerCard pollcard = carddata[id];
				boolean failure = false;
				if (pollcard == null) failure = true;
				if (!failure) if (!pollcard.typecode.equals("m")) failure = true;
				if (!failure) if (!pollcard.mtype.equals(typecode)) failure = true;
				
				if (nextab) {
					if (failure) toskipa = true;
					else toskipb = true;
				}
				else {
					return 0;
					
					
				}
				
			}
			
			else if (command.startsWith("NODAM")&& !skiptoend && perform) { // no damage until end of turn
				//System.out.println("Drawing a card...");
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();
			//	int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				//System.out.println("Determining who to do it to...");
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) {  owne = true; opon = true; }
				
				if (opon) {
					int opponent = not_player(player);
					SetNoDamage(opponent);
				}
				if (owne) {
					int opponent = player;
					SetNoDamage(opponent);	
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
					SetNoDamage(opponent);
					
				}
				//System.out.println("Card Drawn...");
					
			}
			
			
			
			else if (command.startsWith("NXTFD")&& !skiptoend && perform) { // no damage until end of turn
				//System.out.println("Drawing a card...");
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String type = cmdtoken.nextToken();
			//	int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				if (type.equals("m")) {
					nextmfacedown = true;
				}
				//System.out.println("Card Drawn...");
				if (type.equals("e")) {
					nextefacedown = true;
				}
				
				if (type.equals("a")) {
					nextentfacedown = true;
				}	
			}
			
			
			else if (command.startsWith("TOTOP")&& !skiptoend && perform) {
				//System.out.println("Drawing a card...");
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String type = cmdtoken.nextToken();
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				//System.out.println("Determining who to do it to...");
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) {  owne = true; opon = true; }
				
				
				CardmasterLibrary[] deck = new CardmasterLibrary[2];
				deck[0] = deck1;
				deck[1] = deck2;
				int success = 0;
				if (opon) {
					int opponent = not_player(player);
					if (deck[opponent-1].moveToTop(type,carddata)) success++;
				}
				if (owne) {
					int opponent = player;
					if (deck[opponent-1].moveToTop(type,carddata)) success++;
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
					if (deck[opponent-1].moveToTop(type,carddata)) success++;
					
				}
				if (success==0)
					{skiptoend = true; continue;}
					
			}
			
			
			
			
			
			else if (command.startsWith("T2TOP")&& !skiptoend && perform) {
				//System.out.println("Drawing a card...");
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String type = cmdtoken.nextToken();
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				//System.out.println("Determining who to do it to...");
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) {  owne = true; opon = true; }
				
				
				CardmasterLibrary[] deck = new CardmasterLibrary[2];
				deck[0] = deck1;
				deck[1] = deck2;
				int success = 0;
				if (opon) {
					int opponent = not_player(player);
					if (deck[opponent-1].movemTypeToTop(type,carddata)) success++;
				}
				if (owne) {
					int opponent = player;
					if (deck[opponent-1].movemTypeToTop(type,carddata)) success++;
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
					if (deck[opponent-1].movemTypeToTop(type,carddata)) success++;
					
				}
				if (success==0)
					{skiptoend = true; continue;}
					
			}
			
			else if (command.startsWith("DRAWC")&& !skiptoend && perform) {
				//System.out.println("Drawing a card...");
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();
			//	int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				//System.out.println("Determining who to do it to...");
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) {  owne = true; opon = true; }
				
				if (opon) {
					int opponent = not_player(player);
					drawCardInc(opponent);
				}
				if (owne) {
					int opponent = player;
					drawCardInc(opponent);	
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
					drawCardInc(opponent);
					
				}
			
					
			}
			else if (command.startsWith("DISAA") && !skiptoend && perform ) { // discard entire hand
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();//if (!cmdtoken.hasMoreTokens()) return 0;
				//int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) { owne = true; opon = true; }
				
			
				if (opon) {
					int opponent = not_player(player);
					int blah;
					 discard_hand(opponent);
				}
				if (owne) {
					int opponent = player;
					int blah;
					if (opponent ==1) blah = waitcards1;
					 discard_hand(opponent);
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
				
					discard_hand(opponent);
					
				}	
				
				
				
			}
				else if (command.startsWith("DISRN") && !skiptoend && perform ) { // discard entire hand
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();//if (!cmdtoken.hasMoreTokens()) return 0;
				//int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				System.out.println("DISRN");
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) { owne = true; opon = true; }
				boolean success = false;
			
				if (opon) {
					int opponent = not_player(player);
					int blah;
					if (discard_random(opponent) == 1) success = true;
				}
				if (owne) {
					int opponent = player;
					int blah;
					if (opponent ==1) blah = waitcards1;
					 if (discard_random(opponent) == 1) success = true;
					
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
				
					if (discard_random(opponent) == 1) success = true;
					
				}	
				if (!success) {
				
					if (perform) {skiptoend = true; continue;} else return 0;
				}
				
			}
			else if (command.startsWith("DISFB") && !skiptoend) { // discard check twice.
			StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);if (!cmdtoken.hasMoreTokens()) return 0;
				int amounto = getvalue(cmdtoken.nextToken(),count,player,target,self);
				
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) { owne = true; opon = true; }
				
				boolean success = false;
			
				
				if (opon) {
					int opponent = not_player(player);
					int blah;
					if (opponent ==1) blah = waitcards1;
					else blah = waitcards2;
					if (amounto != 0)
					if (blah < numCardsInHandExcept(opponent,self)) {
						success = true;
						if (perform) req_discard(opponent);
					}
				}
				if (owne) {
					int opponent = player;
					int blah;
					if (opponent ==1) blah = waitcards1;
					else blah = waitcards2;
					if (amount != 0)
					if (blah < numCardsInHandExcept(opponent,self)) {
						success = true;
						if (perform) req_discard(opponent);
					}
					
				}				
				if (targ) {
					if (target == null) if (perform) {skiptoend = true; continue;} else return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) if (perform) {skiptoend = true; continue;} else return 0;
					
					int amt = amount;
					if (opponent == not_player(player)) amt = amounto;
					
					
					
					int blah;
			
					if (opponent ==1) blah = waitcards1;
					else blah = waitcards2;
					if (amt != 0)
					if (blah < numCardsInHandExcept(opponent,self)) {
						success = true;
						if (perform) req_discard(opponent);
					}
					
				}
				if (!success)
					if (perform) {skiptoend = true; continue;} else return 0;
					
			}
			
			
			
			
			else if (command.startsWith("DISCF") && !skiptoend) { 
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();//if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) { owne = true; opon = true; }
				if (amount < 1) {
					
					if (perform) {skiptoend = true; continue;} else return 0;
					
					
				}
				if (opon&owne) {
				 if (waitcards1 >= numCardsInHandExcept(1,self) &&
				 	waitcards2 >= numCardsInHandExcept(2,self))
				 	if (perform) {skiptoend = true; continue;} else return 0;
				 	
				 
					
				}
				if (opon) {
					int opponent = not_player(player);
					int blah;
					if (opponent ==1) blah = waitcards1;
					else blah = waitcards2;
					if (blah >= numCardsInHandExcept(opponent,self)) if (perform) {skiptoend = true; continue;} else return 0;
					if (perform) req_discard(opponent);
				}
				if (owne) {
					int opponent = player;
					int blah;
					if (opponent ==1) blah = waitcards1;
					else blah = waitcards2;
					if (blah >= numCardsInHandExcept(opponent,self)) if (perform) {skiptoend = true; continue;} else return 0;
					if (perform) req_discard(opponent);	
					
				}				
				if (targ) {
					if (target == null) if (perform) {skiptoend = true; continue;} else return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) if (perform) {skiptoend = true; continue;} else return 0;
					int blah;
					if (opponent ==1) blah = waitcards1;
					else blah = waitcards2;
					if (blah >= numCardsInHandExcept(opponent,self)) if (perform) {skiptoend = true; continue;} else return 0;
					if (perform) req_discard(opponent);
					
				}
					
			}
			
			
			
			else if (command.startsWith("DISCA") && !skiptoend) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();//if (!cmdtoken.hasMoreTokens()) return 0;
				//int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) { owne = true; opon = true; }
				
				if (opon&owne) {
				 if (waitcards1 >= numCardsInHandExcept(1,self) &&
				 	waitcards2 >= numCardsInHandExcept(2,self)) {
				 	
				 	if (perform) {skiptoend = true; continue;} else return 0;
				 	}
				 
					
				}
				if (opon) {
					int opponent = not_player(player);
					int blah;
					if (opponent ==1) blah = waitcards1;
					else blah = waitcards2;
					if (blah >= numCardsInHandExcept(opponent,self))
					{ if (perform) {skiptoend = true; continue;} else return 0; }
					if (perform) req_discard(opponent);
				}
				if (owne) {
					int opponent = player;
					int blah;
					if (opponent ==1) blah = waitcards1;
					else blah = waitcards2;
					if (blah >= numCardsInHandExcept(opponent,self))
					 {
					 	 if (upkeep) destroy_card(self);
					 	 if (perform) {skiptoend = true; continue;} else return 0;
					 	 
					 	 }
					if (perform) req_discard(opponent);	
				
				 	
				}				
				if (targ) {
					if (target == null) {if (perform) {skiptoend = true; continue;} else return 0;}
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1){ if (perform) {skiptoend = true; continue;} else return 0;}
					int blah;
					if (opponent ==1) blah = waitcards1;
					else blah = waitcards2;
					if (blah >= numCardsInHandExcept(opponent,self)){
						 if (perform) {skiptoend = true; continue;} else return 0;}
					if (perform) req_discard(opponent);
					
				}
					
			}
		else if (command.startsWith("MRPHS")&& !skiptoend && perform) { // change to default of, keep stats 
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;				
				int id = getvalue(cmdtoken.nextToken(),count,player,target,self);
				int oldattack = self.attack;
				int oldattackm = self.attackmod;
				int oldlife = self.lifepoints;
				int oldlifem = self.lifemod;
				self.copydata(carddata[id]);
				self.attack = oldattack;
				self.attackmod = oldattackm;
				self.lifepoints = oldlife;
				self.lifemod = oldlifem;
				
			}
			
			else if (command.startsWith("MORPH")&& !skiptoend && perform) { // change to default of 
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;				
				int id = getvalue(cmdtoken.nextToken(),count,player,target,self);
				self.copydata(carddata[id]);
				
			}
	
			else if (command.startsWith("RETRN")&& !skiptoend)  { // Bounce back to hand.
			//	if (self.token) {if (perform) {
			//		skiptoend = true; continue;} else return 0;
					
					
					
			//		}
				if (numCardsInHand(player) == 8) {if (perform) {skiptoend = true; continue;} else return 0;}
				if (perform) return_to_hand(player,self);					
			}
			else if (command.startsWith("ADDTH") && !skiptoend) { // add to hand
				
					StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int id = getvalue(cmdtoken.nextToken(),count,player,target,self);
//		System.out.println("ADDING " + id + " TO HAND ADDTH - " + play);
				if (carddata[id] == null) return 0;
				if (id == 0) return 0;
//		System.out.println("IS " + carddata[id].name);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) { owne = true; opon = true; }
		if (!opon && !owne && !targ) {
				//	System.out.println("ERROR: NO TARGET PLAYER");
				}
				int success = 0;
				if (opon) {
					int opponent = not_player(player);
					if (numCardsInHand(opponent) < 8) {
						success++;
						if (perform) AddToHand(opponent,id);
					}
				}
				if (owne) {
					int opponent = player;
					if (numCardsInHand(opponent) < 8) {
						success++;
						if (perform) AddToHand(opponent,id);
					}
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;
					if (numCardsInHand(opponent) < 8) {
						success++;
						if (perform) AddToHand(opponent,id);
					}
				}
			//	System.out.println("GOT TO END OF ADDTH : " + success);
				if (success == 0 && perform) { skiptoend= true; continue; };
				if (success == 0 && !perform) return 0;		
				
			}



	else if (command.startsWith("DRWRD") && !skiptoend) { // add to hand
				int[] decknos = null;
				int primarydeck = -1;
				if (player == 1) {
					decknos = user1.decks;
					primarydeck = user1.primarydeck;

				}
				else if (player == 2) {
					decknos = user2.decks;
					primarydeck = user2.primarydeck;
				}
				if (decknos == null) {
					if (perform) {skiptoend = true; continue;} else return 0;
				}
				if(numCardsInHand(player)==8) {
					if (perform) {skiptoend = true; continue;} else return 0;
				}
			
				if (decknos.length == 1) {if (perform) {skiptoend = true; continue;} else return 0;}
				
				int [] unusabledecks = new int[0];
				for (int i=0;i<decknos.length;i++) {
				//	System.out.println("Checking deck " + decknos[i]);
					if (decknos[i] <= 0) {
						int[] tempdecks = new int[unusabledecks.length];
						System.arraycopy(unusabledecks,0,tempdecks,0,unusabledecks.length);
						unusabledecks = new int[unusabledecks.length+1];
						System.arraycopy(tempdecks,0,unusabledecks,0,tempdecks.length);
						unusabledecks[unusabledecks.length-1] = decknos[i];
					//	System.out.println("Cant use... Deck Zero");
						continue;
							
						
						
					}
					if (decknos[i] == primarydeck) {
						int[] tempdecks = new int[unusabledecks.length];
						System.arraycopy(unusabledecks,0,tempdecks,0,unusabledecks.length);
						unusabledecks = new int[unusabledecks.length+1];
						System.arraycopy(tempdecks,0,unusabledecks,0,tempdecks.length);
						unusabledecks[unusabledecks.length-1] = decknos[i];
					//	System.out.println("Cant use... Primary Deck");
						continue;
						
						
						
						
					}
					
					CardmasterLibrary lib = new CardmasterLibrary(player_named(player),decknos[i]);
					if (lib == null) {
						int[] tempdecks = new int[unusabledecks.length];
						System.arraycopy(unusabledecks,0,tempdecks,0,unusabledecks.length);
						unusabledecks = new int[unusabledecks.length+1];
						System.arraycopy(tempdecks,0,unusabledecks,0,tempdecks.length);
						unusabledecks[unusabledecks.length-1] = decknos[i];
					//	System.out.println("Cant use... couldnt load");
						continue;
					}
					if (lib.numcards ==0) {
						int[] tempdecks = new int[unusabledecks.length];
						System.arraycopy(unusabledecks,0,tempdecks,0,unusabledecks.length);
						unusabledecks = new int[unusabledecks.length+1];
						System.arraycopy(tempdecks,0,unusabledecks,0,tempdecks.length);
						unusabledecks[unusabledecks.length-1] = decknos[i];
					//	System.out.println("Cant use... Empty");
						continue;
					}
					
					
				}
				if (unusabledecks.length == decknos.length) {
				//	System.out.println("No usable decks.");
					if (perform) {skiptoend = true; continue;} else return 0;
					}
				int chosendeck = -1;
				while (chosendeck == -1) {
					int randdeck = random.nextInt(decknos.length);
					int randdeckno = decknos[randdeck];
					//System.out.println("Checking random deck .. " + randdeckno);
					boolean isInArray = false;
					for (int i=0;i<unusabledecks.length;i++) {
					//	System.out.println("usd: " + unusabledecks[i] + " versus " + randdeckno);
						if (unusabledecks[i] == randdeckno) isInArray = true;
					}
					if (isInArray) continue;
					else {chosendeck = randdeckno;}
						
				}
			//	System.out.println("Dec chosen: " + chosendeck);
				CardmasterLibrary lib = new CardmasterLibrary(player_named(player),chosendeck);
				//if (lib.created == false) {
					
				//	System.out.println("Cant use... Primary Deck");
					
				//	if (perform) {skiptoend = true; continue;} else return 0;} 
				lib.PrepareDeck(random);
				int cardno = lib.drawCard();
				if (cardno <=0){if (perform) {skiptoend = true; continue;} else return 0;} 
				if (perform) {if (AddToHand(player,cardno) == -1) {skiptoend = true; continue;} }

			}



			else if (command.startsWith("EFTOM") && !skiptoend) {
				
				if (!target.isCard) if (perform) {skiptoend = true; continue;} else return 0;
				
				CardmasterServerCard card = target.getCard();
				if (!availableSlots("m", player)){ if (perform) {skiptoend = true; continue;} else return 0;}
				
				int slotnumber = findSlotNumber(GetFirstFreeSlot(player,"m")); 
				int id = 931;
				if (perform) {
					if (!putIntoPlayFreeToken(player, id)) {skiptoend = true; continue;}
					
					CardmasterServerCard newCard = getSlot(player,slotnumber,"m");
					newCard.picture = card.picture;
					newCard.mtype = "animate";
					if (card.mtype != null)
					if (!card.mtype.equals("none")) {
						newCard.mtype += " " + card.mtype;
					}
					MersenneTwisterFast newrand = new MersenneTwisterFast(10000-card.cardid);
					double mult1 = newrand.nextDouble() * 0.5 + 0.75;
					double mult2 = newrand.nextDouble() * 0.5 + 0.75;
					int add1 = newrand.nextInt(4)+1;
					int add2 = newrand.nextInt(4)+1;
					newCard.attack = (int)(((card.Lcost / 3) + (card.Gcost * 1.5) + card.Dcost*2) * 5 * mult1) + add1;
					newCard.lifepoints = (int)(((card.Dcost / 3) + (card.Lcost*2) + (card.Gcost*1.5)) * 6 * mult2) + add2;
					newCard.colorcode = card.colorcode;
					newCard.Gcost = card.Gcost;
					newCard.Lcost = card.Lcost;
					newCard.Dcost = card.Dcost;
					newCard.Gsac = card.Gsac;
					newCard.Dsac = card.Dsac;
					newCard.Lsac = card.Lsac;
					newCard.name = card.name;
					newCard.dizzy = card.dizzy;
					newCard.expansioncode = card.expansioncode;
					
				}
				
				
				
			}
			else if (command.startsWith("RETTA") &&!skiptoend)  { // Bounce targetback to hand.
				if (!target.isCard) if (perform) {skiptoend = true; continue;} else return 0;
				CardmasterServerCard card = target.getCard();
				if (numCardsInHand(owner(card)) == 8) if (perform) {skiptoend = true; continue;} else return 0;
				if (perform) return_to_hand(owner(card),card);					
			}
				else if (command.startsWith("DECKL") && !skiptoend && perform) { // list cards in deck.
				
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();//if (!cmdtoken.hasMoreTokens()) return 0;
				int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) { owne = true; opon = true; }
				
			
				if (opon) {
					int opponent = not_player(player);
					showtopcards(opponent,amount);
				}
				if (owne) {
					int opponent = player;

					showtopcards(opponent,amount);
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;

					showtopcards(opponent,amount);
				}
				
				
				
			}
			
			else if (command.startsWith("HANDL") && !skiptoend && perform) { // list cards in hand.
				
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String play = cmdtoken.nextToken();//if (!cmdtoken.hasMoreTokens()) return 0;
				//int amount = getvalue(cmdtoken.nextToken(),count,player,target,self);
				boolean opon = false;
				boolean targ = false;
				boolean owne = false;
				
				if (play.equals("opnt")) opon = true; if (play.equals("opon")) opon = true;
				if (play.equals("targ")) targ = true;
				if (play.equals("ownr")) owne = true;
				if (play.equals("both")) { owne = true; opon = true; }
				
			
				if (opon) {
					int opponent = not_player(player);
					listcards(opponent);
				}
				if (owne) {
					int opponent = player;

					listcards(opponent);
				}				
				if (targ) {
					if (target == null) return 0;
					int opponent = -1;
					if (target.isCard) opponent = owner(target.getCard());
					if (target.isPlayer ) opponent = target.getPlayer();
					if (opponent == -1) return 0;

					listcards(opponent);
				}
				
				
				
			}
			

			else if (command.startsWith("CALLM") && !skiptoend) { //All empty spaces are filled with the monster. 
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				
				int id = getvalue(cmdtoken.nextToken(),count,player,target,self);
				if (isUnique(carddata[id]) && isInPlay(id))if (perform) {skiptoend = true; continue;} else return 0;
				int suc = 0;
				for (int i=0;i<5;i++) {
					if ((player==1 && m1[i].dummy)||(player==2 && m2[i].dummy)) {
						if (perform) if (create_monster(player,i,id,true) != 1)if (perform) {skiptoend = true; continue;} else return 0;	
						if (perform) getSlot(player,i,"m").token = true;
						suc++;
					}
						
				
				}	
				if (nextab) {
					if (suc == 0) toskipa = true;
					else toskipb = true;
				}
				else  if (suc==0) {if (perform) {skiptoend = true; continue;} else return 0;	}			
					
			}
			else if (command.startsWith("MAKMD") && !skiptoend) { //make monster, default stats.
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				
				int id = getvalue(cmdtoken.nextToken(),count,player,target,self);if (!cmdtoken.hasMoreTokens()) return 0;
				String slott = cmdtoken.nextToken();
				int slot;
				if (slott.equals("t")) slot = targetslot;
				else slot = findSlotNumber(self);
				String selfname = "argleblarglebloo";
				if (self != null) selfname = self.name;
				if (isUnique(carddata[id]) && isInPlay(id) && ( ! carddata[id].name.equals(selfname))) if (perform) {skiptoend = true; continue;} else return 0;
					
				if (perform)if (create_monster(player,slot,id, true) != 1) {
					
					
					
					if (perform) {skiptoend = true; continue;} else return 0;					
				}
				if (perform) getSlot(player,slot,"m").token = true;
			}
			
			
			else if (command.startsWith("MAKMP") && !skiptoend) { //make monster, default stats.
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				
				int id = getvalue(cmdtoken.nextToken(),count,player,target,self);
				//String slott = cmdtoken.nextToken();
				String selfname = "argleblarglebloo";
				if (self != null) selfname = self.name;
				if (isUnique(carddata[id]) && isInPlay(id) && ( ! carddata[id].name.equals(selfname))) if (perform) {skiptoend = true; continue;} else return 0;
				CardmasterServerCard resultCard =null;
				CardmasterServerCard resultCard2 =null;
				if (perform) resultCard = putIntoPlayFree(player, id, resultCard2,true);
				
				if (perform) if (resultCard == null) {skiptoend = true; continue;}
				if (perform) resultCard.token = true;
			}
			
			else if (command.startsWith("NOTOK") && perform && !skiptoend) { // makes it not a token
			
				self.token = false;
			
			}
	
			else if (command.startsWith("REPLI")&& !skiptoend) {
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				CardmasterServerCard card = target.getCard();
				boolean didit = false;
				if (!card.dummy) {
				
					
					String play = cmdtoken.nextToken();
					//System.out.println("REPLICATOR TEST: " + owner(card) + " " + play);
					if (
						 (play.equals("ownr") && owner(card)==player) ||
						 (play.equals("opnt") && owner(card)!=player) ||
						 (play.equals("opon") && owner(card)!=player) ||
						 (play.equals("both"))
						 
						 )
						 
						  {
						  	didit = true;
						if (perform) {
							
							self.rand = random.nextInt(10000);	
							self.copydata(card);
							self.rand = random.nextInt(10000);	
							card.rand = random.nextInt(10000);
						}
					}
					
				
				}	
				
				if (!didit && perform) { skiptoend= true; continue; }
				if (!didit  && !perform) return 0;	
				
			}
			else if (command.startsWith("COPYU")&& !skiptoend) { // Copy undizzy 
				
				CardmasterServerCard card = getSlot(player, targetslot, self.typecode);
				if (!card.dummy) if (perform) {skiptoend = true; continue;} else return 0;
				if (perform) {
					card.rand = random.nextInt(10000);	
					card.copydata(self);
				}
				
				
			}
			else if (command.startsWith("COPYC")&& !skiptoend) { // change to default of 
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String slott = cmdtoken.nextToken();
				CardmasterServerCard card = getSlot(player, targetslot, slott);
				if (!card.dummy) {if (perform) {skiptoend = true; continue;} else return 0;}
				if (perform){
					card.rand = random.nextInt(10000);	
					 card.copydata(self);
					 }
				if (perform) if (card == null) if (perform) {skiptoend = true; continue;} else return 0;
				if (perform) if (card.dummy) if (perform) {skiptoend = true; continue;} else return 0;
				if (perform) card.dizzy = true;
				if (perform) card.token = true;
				
				
				
			}
			
			
		
			else if (command.startsWith("MAKMS")&& !skiptoend) { //make monster, special stats.
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				
				int id =  getvalue(cmdtoken.nextToken(),count,player,target,self);
				if (!cmdtoken.hasMoreTokens()) return 0;
				String slott = cmdtoken.nextToken();
				int slot;
				if (slott.equals("t")) slot = targetslot;
				else slot = findSlotNumber(self);				
				int attack = getvalue(cmdtoken.nextToken(),count,player,target,self);
				if (!cmdtoken.hasMoreTokens()) return 0;
				int lifep = getvalue(cmdtoken.nextToken(),count,player,target,self);
				String selfname = "argleblarglebloo";
				if (self != null) selfname = self.name;
				if (isUnique(carddata[id]) && isInPlay(id) && ( ! carddata[id].name.equals(selfname))) if (perform) {skiptoend = true; continue;} else return 0;
				
				int returnval = 1;
				if (perform) returnval= create_monster(player,slot,id,attack,lifep,true);
				if (perform) getSlot(player,slot,"m").token = true;
			//	System.out.println("BLAH" + returnval);
				if (returnval != 1)if (perform) {skiptoend = true; continue;} else return 0;					
					
			}
			else if (command.startsWith("CHLOC")&& !skiptoend && perform) { //change location.
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				
				int id =  getvalue(cmdtoken.nextToken(),count,player,target,self);
				
					
			
			
				if (isInPlay(id)) return 0;
				
				int returnval = create_location(player,id);
				if (returnval == 1) location.token = true;
			//	System.out.println("BLAH" + returnval);
				if (returnval != 1) return 0;					
					
			}

			

			else if (command.startsWith("MAKED")&& !skiptoend) { //make effect, default stats
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				
				int id =  getvalue(cmdtoken.nextToken(),count,player,target,self);if (!cmdtoken.hasMoreTokens()) return 0;
				String slott = cmdtoken.nextToken();
				int slot;
				if (slott.equals("t")) slot = targetslot;
				else slot = findSlotNumber(self);		
				String selfname = "argleblarglebloo";
				if (self != null) selfname = self.name;
				if (isUnique(carddata[id]) && isInPlay(id) && ( ! carddata[id].name.equals(selfname))) if (perform) {skiptoend = true; continue;} else return 0;
				if (perform) if (create_effect(player,slot,id,true) != 1) {
					
				if (perform) {skiptoend = true; continue;} else return 0; }
				if (perform)getSlot(player,slot,"e").token = true;					
					
			}

			else if (command.startsWith("MAKES") && !skiptoend) { //make monster, special stats.
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				
				int id =  getvalue(cmdtoken.nextToken(),count,player,target,self);if (!cmdtoken.hasMoreTokens()) return 0;
				String slott = cmdtoken.nextToken();
				int slot;
				if (slott.equals("t")) slot = targetslot;
				else slot = findSlotNumber(self);				
				int attack = getvalue(cmdtoken.nextToken(),count,player,target,self);if (!cmdtoken.hasMoreTokens()) return 0;
				int lifep = getvalue(cmdtoken.nextToken(),count,player,target,self);
				String selfname = "argleblarglebloo";
				if (self != null) selfname = self.name;
				if (isUnique(carddata[id]) && isInPlay(id) && ( ! carddata[id].name.equals(selfname))) if (perform) {skiptoend = true; continue;} else return 0;
				if (perform) if (create_effect(player,slot,id,attack,lifep,true) != 1) { 
				
				
				if (perform) {skiptoend = true; continue;} else return 0;			
				}
				if (perform)getSlot(player,slot,"e").token = true;		
					
			}
			
			else if (command.startsWith("POSSE") && !skiptoend) { // possession
				if (!target.isCard) if (perform) {skiptoend = true; continue;} else return 0;
				if (target.getCard().dummy) if (perform) {skiptoend = true; continue;} else return 0;
				if (perform) destroy_card(self);
				if (perform) {
				CardmasterServerCard crd = target.getCard();
					self.copydata(target.getCard());
					
					if (GetModifierCard(target.getCard()) != null && !GetModifierCard(target.getCard()).dummy) {
						if (!GetModifierCard(self).dummy)		destroy_modifier(owner(self),self.typecode,findSlotNumber(self));
						GetModifierCard(self).copydata(GetModifierCard(target.getCard()));
						GetModifierCard(target.getCard()).copydata(new CardmasterServerCard());
					} 
					
					self.rand = random.nextInt(10000);

					target.getCard().copydata(new CardmasterServerCard());
				
					
				}			
			}
			else if (command.startsWith("GRV2S") && !skiptoend) { //return cardid to play, sets as TOKEN with 25% life
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String cardtype = cmdtoken.nextToken();
				if (! target.isGrave) if (perform) {skiptoend = true; continue;} else return 0;
				int cardid = target.s;
				
				if (!cardtype.equals("a")) if (! cardtype.equals(carddata[cardid].typecode)) if (perform) {skiptoend = true; continue;} else return 0;
				if (!availableSlots(cardtype, player)) if (perform) {skiptoend = true; continue;} else return 0;
				
				int id = -1;
				
				if (isUnique(carddata[cardid]) && isInPlay(cardid)) {
					if (perform) {skiptoend = true; continue;} else return 0;
				}
				
				if (perform) {
				
					if (player == 1) {
						id = deck1.drawGrave(cardid);
							
					}
					if (player == 2) {
						id = deck2.drawGrave(cardid);	
						
					}
				} else {
					
					if (player == 1 && deck1.isInGrave(cardid)) id = cardid;
					if (player == 2 && deck2.isInGrave(cardid)) id = cardid;
					
					
				}				
				if (perform) message(GRA(player_named(player)),player_named(player));
				if (id <= 0) {
					if (perform) {skiptoend = true; continue;} else return 0;
					}
				
				CardmasterServerCard resultCard =null;
				CardmasterServerCard resultCard2 = null;
				
				if (perform) resultCard = putIntoPlayFree(player, id, resultCard2,true);
		
				if (perform) if (resultCard == null) {skiptoend = true; continue;}
				if (perform) resultCard.lifepoints *= 0.25;
				if (perform) resultCard.token = true;
				if (perform) if (resultCard.lifepoints <= 0) resultCard.lifepoints = 1;
				
			}
			else if (command.startsWith("SWTCH") && !skiptoend) {
				
				if (!target.isCard)  {
					if (perform ) { skiptoend = true; continue;	}
					else { return 0;}
				}
				
				if (self == null)  {
					if (perform ) { skiptoend = true; continue;	}
					else { return 0;}
				}
				
				if (target.getCard().dummy)  {
					if (perform ) { skiptoend = true; continue;	}
					else { return 0;}
				}
				
				if (!target.getCard().typecode.equals(self.typecode))  {
					if (perform ) { skiptoend = true; continue;	}
					else { return 0;}
				}
				
				CardmasterServerCard temp = new CardmasterServerCard();
				if (perform) {
				
				MoveToSlot(target.getCard(),temp);
				target.getCard().copydata(new CardmasterServerCard());
				MoveToSlot(self,target.getCard());
				self.copydata(new CardmasterServerCard());
				MoveToSlot(temp,self);
								
				}
				
			}
			
			else if (command.startsWith("SHELL") && !skiptoend && !perform) {
			
				ShellGame(player);
				
				
				
				
			}
			
			else if (command.startsWith("MOVET")&& !skiptoend) { // move to a target slot..
				// MOVET^match^match^match^match^where
				// Where : opnt - first oponent slot
				//       : frst - first available slot
				//       : targ - target slot
				
				
			
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String a = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String b = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String c = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String d = cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				String place = cmdtoken.nextToken();
				
				boolean dizzy= false;
				if (cmdtoken.hasMoreTokens()) {
					dizzy = true;
				}
				
			//	int player1 = 0;
		//		int player2 = 0;
		//		boolean monster = false;
		//		boolean effect = false;
				/*
				if (b.startsWith("b")) { player1 = 1; player2 = 2; }
				if (b.startsWith("o")) { player1 = not_player(player); player2= not_player(player); }
				if (b.startsWith("s")) { player1 = player; player2 = player; }
				if (b.endsWith("b")) { monster = true; effect = true; }
				if (b.endsWith("m")) { monster = true; }
				if (b.endsWith("e")) { effect = true; }
				*/
				int suc = 0;
				if (a.startsWith("rnd")) suc = 1;
				//System.out.println("MOVET!");
				
				
				
				
				Vector <CardmasterServerCard> matched = CardsMatching(player,a,b,c,d,self,target);
//				boolean dizzy = true;
				for (CardmasterServerCard e : matched) {
					CardmasterServerCard targets = null;
					if (place.equals("targ")) {
								targets = getSlot(player,targetslot,e.typecode);						
							}
							else if (place.equals("opnt") || place.equals("opon")) {
								targets = GetFirstFreeSlot(not_player(owner(e)),e.typecode);	
							}
							else if (place.equals("ownr") || place.equals("frst")) {
								targets = GetFirstFreeSlot(owner(e),e.typecode);
								
							}
						CardmasterServerCard movingcard = e;
						if (CanMoveToSlot(movingcard,targets)) {
									suc++;
								 //System.out.println("CanMoveToSlot!!");
								if (perform) {
									if (MoveToSlot(movingcard,targets)) {
										
										targets.matchable = false;
										if (dizzy) dizzy(targets,true);
									}
								}
								
							
							
							}
								
				}
				if (nextab) {
					if (suc ==0) toskipa = true;
					else toskipb = true;
				} else {
				if (suc == 0&& !perform) return 0;		
				if (suc == 0 && perform) { skiptoend= true; continue; }
				}									
				
				/*
				for (int i=0;i<5;i++) {
					if (monster) {
						if (player1==1) if (matchcard(a, c,d, m1[i], self, target)) {
							
							CardmasterServerCard targets = null;
							if (place.equals("targ")) {
								targets = getSlot(1,targetslot,"m");						
							}
							else if (place.equals("opnt") || place.equals("opon")) {
								targets = GetFirstFreeSlot(2,"m");	
							}
							else if (place.equals("ownr") || place.equals("frst")) {
								targets = GetFirstFreeSlot(1,"m");
								
							}
							//System.out.println(targets);
							CardmasterServerCard movingcard = getSlot(1,i,"m");
							if (CanMoveToSlot(movingcard,targets)) {
								//System.out.println("CanMoveToSlot!!");
								 	suc++;
								if (perform) {
									if (MoveToSlot(movingcard,targets)) {
									
										targets.matchable = false;
										if (dizzy) dizzy(targets,true);
										
									}
								}
								
							
							
							}
							
						}
						if (player2==2) if (matchcard(a, c,d, m2[i], self, target)) {
							CardmasterServerCard targets = null;
							if (place.equals("targ")) {
								targets = getSlot(2,targetslot,"m");						
							}
							else if (place.equals("opnt") || place.equals("opon")) {
								targets = GetFirstFreeSlot(1,"m");	
							}
							else if (place.equals("ownr") || place.equals("frst")) {
								targets = GetFirstFreeSlot(2,"m");
								
							}
							//System.out.println(targets);
							CardmasterServerCard movingcard = getSlot(2,i,"m");
							if (CanMoveToSlot(movingcard,targets)) {
									suc++;
								 //System.out.println("CanMoveToSlot!!");
								if (perform) {
									if (MoveToSlot(movingcard,targets)) {
										
										targets.matchable = false;
										if (dizzy) dizzy(targets,true);
									}
								}
								
							
							
							}
							
						
						}
					}
					if (effect) {
						if (player1==1) if (matchcard(a, c,d, e1[i], self, target)) { 


							CardmasterServerCard targets = null;
							if (place.equals("targ")) {
								targets = getSlot(1,targetslot,"e");						
							}
							else if (place.equals("opnt") || place.equals("opon")) {
								targets = GetFirstFreeSlot(2,"e");	
							}
							else if (place.equals("ownr") || place.equals("frst")) {
								targets = GetFirstFreeSlot(1,"e");
								
							}
							//System.out.println(targets);
							CardmasterServerCard movingcard = getSlot(1,i,"e");
							if (CanMoveToSlot(movingcard,targets)) {
							
								 suc++;
								if (perform) {
									if (MoveToSlot(movingcard,targets)) {
										
										targets.matchable = false;
										if (dizzy) dizzy(targets,true);
									}
								}
								
							
							
							}
							
						
							
						}
						if (player2==2) if (matchcard(a, c,d, e2[i], self, target)) {
							
							
							CardmasterServerCard targets = null;
							if (place.equals("targ")) {
								targets = getSlot(2,targetslot,"e");						
							}
							else if (place.equals("opnt") || place.equals("opon")) {
								targets = GetFirstFreeSlot(1,"e");	
							}
							else if (place.equals("ownr") || place.equals("frst")) {
								targets = GetFirstFreeSlot(2,"e");
								
							}
//							System.out.println(targets);
							CardmasterServerCard movingcard = getSlot(2,i,"e");
							if (CanMoveToSlot(movingcard,targets)) {
								suc++;
								 
								if (perform) {
									if (MoveToSlot(movingcard,targets)) {
									
										targets.matchable = false;
										if (dizzy) dizzy(targets,true);
									}
								}
								
							
							
							}
								
						
						}
					}
				}*/
				ResetMatchable();
				if (suc == 0 && perform) { skiptoend= true; continue; }
				if (suc == 0 && !perform) return 0;					
				
				
				
				
				
			}
			
			else if (command.startsWith("GRVCS") && !skiptoend) {
				
				
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String cardtype = cmdtoken.nextToken();
				if (target == null){if (perform) {skiptoend = true; continue;} else return 0;}
				if (! target.isGrave) {if (perform) {skiptoend = true; continue;} else return 0;}
				int cardid = target.s;
				
				if (!cardtype.equals("a")) if (! cardtype.equals(carddata[cardid].typecode)) if (perform) {skiptoend = true; continue;} else return 0;
				if (!availableSlots(cardtype, player)) if (perform) {skiptoend = true; continue;} else return 0;
				
				int id = -1;
			
					
					if (player == 1 && deck1.isInGrave(cardid)) id = cardid;
					if (player == 2 && deck2.isInGrave(cardid)) id = cardid;
					
				
				if (id == -1){
				if (perform) {skiptoend = true; continue;} else return 0;
				}				
				
				if (isUnique(carddata[id]) && isInPlay(id)) {
				if (perform) {skiptoend = true; continue;} else return 0;
				}				
				if (perform) message(GRA(player_named(player)),player_named(player));
				if (id <= 0) {if (perform) {skiptoend = true; continue;} else return 0;}
				count = (int)getModifiedValue(carddata[cardid],"dco","A",player) +
						(int)getModifiedValue(carddata[cardid],"gco","A",player) +
						(int)getModifiedValue(carddata[cardid],"lco","A",player); 
				
				
				
						
						
						
			}
			
			else if (command.startsWith("GRV2P") && !skiptoend) { //return cardid to play
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String cardtype = cmdtoken.nextToken();
				if (! target.isGrave) {if (perform) {skiptoend = true; continue;} else return 0;}
				int cardid = target.s;
				
				if (!cardtype.equals("a")) if (! cardtype.equals(carddata[cardid].typecode)) {if (perform) {skiptoend = true; continue;} else return 0;}
				if (!availableSlots(cardtype, player)){ if (perform) {skiptoend = true; continue;} else return 0;}
				
				int id = -1;
				if (perform) {
					
					if (player == 1) {
						id = deck1.drawGrave(cardid);
							
					}
					if (player == 2) {
						id = deck2.drawGrave(cardid);	
						
					}
				} else {
					
					if (player == 1 && deck1.isInGrave(cardid)) id = cardid;
					if (player == 2 && deck2.isInGrave(cardid)) id = cardid;
					
					
				}
				if (isUnique(carddata[id]) && isInPlay(id)) {
					if (perform) {skiptoend = true; continue;} else return 0;
				}				
				if (perform) message(GRA(player_named(player)),player_named(player));
				if (id <= 0) {if (perform) {skiptoend = true; continue;} else return 0;}
				count = (int)getModifiedValue(carddata[cardid],"dco","A",player) +
						(int)getModifiedValue(carddata[cardid],"gco","A",player) +
						(int)getModifiedValue(carddata[cardid],"lco","A",player); 
				
				if (perform) {
					if (! putIntoPlayFree(player, id)) {skiptoend = true; continue;}
					}
				
			}	

			
			else if (command.startsWith("MAKOP")&& !skiptoend) { // bring into play automatically in first slot.
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;
				int id = getvalue(cmdtoken.nextToken(),count,player,target,self);if (!cmdtoken.hasMoreTokens()) return 0;
				String slotowner = cmdtoken.nextToken();
				int theplayer = player;
				if (slotowner.equals("opnt") || slotowner.equals("opon")) theplayer = not_player(player);
				
				if (id <= 0) if (perform) {skiptoend = true; continue;} else return 0;
				if (perform) if (! putIntoPlayFreeToken(theplayer, id)) {skiptoend = true; continue;}
				
			}
			else if (command.startsWith("JUNKY") && !skiptoend) {
				if (perform) {
					
					for (int i=numCardsInHand(player);i<8;i++) {
						int id = 0;
						if (player == 1) {
							id = deck1.drawGrave();
								
						}
						if (player == 2) {
							id = deck2.drawGrave();	
							
						}
						if (id <1) continue;
						AddToHand(player,id);
						id = 0;
					}
				
					
					
				}
				
				
				
			}
			else if (command.startsWith("GRRND") && !skiptoend) { //Random grave card.
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
			//	System.out.println("Test...");
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String fromplays = cmdtoken.nextToken();
				String toplays = cmdtoken.nextToken();
				int fromplayer = 1;
				int toplayer = 1;
				
				if (fromplays.equals("opnt") || fromplays.equals("opon")) {
					fromplayer = not_player(player);
				}
				else if (fromplays.equals("ownr")) {
					fromplayer = player;
				}
				
				if (toplays.equals("opnt") || toplays.equals("opon")) {
					toplayer = not_player(player);
				}
				else if (toplays.equals("ownr")) {
					toplayer = player;
				}
				
			//	System.out.println("AND...");
			//	if (! target.isGrave) if (perform) {skiptoend = true; continue;} else return 0;
			//	System.out.println("TEST");
			//	int cardid = target.s;

				int id = -1;
				if (perform) {
			
					if (fromplayer == 1) {
						id = deck1.drawGrave();
							
					}
					if (fromplayer == 2) {
						id = deck2.drawGrave();	
						
					}
				} 
				else {
					id = 1;
				}
				if (perform) message(GRA(player_named(fromplayer)),player_named(fromplayer));
				if (id <= 0) if (perform) {skiptoend = true; continue;} else return 0;
				if (perform) { 
				CardmasterServerCard card = new CardmasterServerCard();
				card.rand = random.nextInt(10000);
			 	card.copydata(carddata[id]);
			 //	System.out.println("GIVING " + toplayer + " CARD " + id + " - SCAVENGE");
				 returntohand = card;
				 returntohandplayer = toplayer; 
				 }
				
				
				
			}	
			
			
			else if (command.startsWith("GRAVE") && !skiptoend) { //return cardid to hand.
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
			//	System.out.println("Test...");
				cmdtoken.nextToken();if (!cmdtoken.hasMoreTokens()) return 0;// eat the command
				String cardtype = cmdtoken.nextToken();
			//	System.out.println("AND...");
				if (! target.isGrave) if (perform) {skiptoend = true; continue;} else return 0;
			//	System.out.println("TEST");
				int cardid = target.s;
				if (! cardtype.equals(carddata[cardid].typecode))  if (perform) {skiptoend = true; continue;} else return 0;
				int id = -1;
					if (perform) {
				
					if (player == 1) {
						id = deck1.drawGrave(cardid);
							
					}
					if (player == 2) {
						id = deck2.drawGrave(cardid);	
						
					}
				} else {
					
					if (player == 1 && deck1.isInGrave(cardid)) id = cardid;
					if (player == 2 && deck2.isInGrave(cardid)) id = cardid;
					
					
				}
				if (perform) message(GRA(player_named(player)),player_named(player));
				if (id <= 0) if (perform) {skiptoend = true; continue;} else return 0;
				if (perform) { CardmasterServerCard card = new CardmasterServerCard();
				card.rand = random.nextInt(10000);
			 card.copydata(carddata[id]);
				 returntohand = card;
				 returntohandplayer = player; }
				
				
				
			}	
			
			
			
			
			
			else if (command.startsWith("SYMBI") && !skiptoend) { // Merging
			//	StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
			//	if (!cmdtoken.hasMoreTokens()) return 0;
			//	cmdtoken.nextToken();// eat the command
		
		/*
		
				if (!cmdtoken.hasMoreTokens()) return 0;
				String findcard = cmdtoken.nextToken();
				
				if (!cmdtoken.hasMoreTokens()) return 0;
				int newcardid = Integer.parseInt(cmdtoken.nextToken());
				
				if (!cmdtoken.hasMoreTokens()) return 0;*/
			//	System.out.println("SYMBIOTE 0");
				if (target == null) {
					if (perform) {skiptoend = true; continue;} 
					else return 0;
				}
				//System.out.println("SYMBIOTE 1");
				if (!target.isCard) {
					if (perform) {skiptoend = true; continue;} 
					else return 0;
				}
			//	System.out.println("SYMBIOTE 2");
				
				if (target.getCard().dummy) {if (perform) {skiptoend = true; continue;} else return 0;}
			//	System.out.println("SYMBIOTE 3");
			
				if (target.getCard() == self) {if (perform) {skiptoend = true; continue;} else return 0;}
			//	System.out.println("SYMBIOTE 4");
				//if (!(target.getCard().name.equals(findcard))) if (perform) {skiptoend = true; continue;} else return 0;
				if (owner(target.getCard()) != player){ if (perform) {skiptoend = true; continue;} else return 0;}
			//	System.out.println("SYMBIOTE 5");
				
				int attack = target.getCard().attack + self.attack;
				int lifepoints = target.getCard().lifepoints + self.lifepoints;
				int newcardid = 923;
				
				if (ismType(target.getCard(),"soldier") 
					||ismType(target.getCard(),"elf") 
					||ismType(target.getCard(),"cleric")
					||ismType(target.getCard(),"general")
					||ismType(target.getCard(),"captain") ||ismType(target.getCard(),"capitan")
					||ismType(target.getCard(),"npc")||ismType(target.getCard(),"angel") 
					||ismType(target.getCard(),"dragobo") ||ismType(target.getCard(),"scientist") 
					||ismType(target.getCard(),"hacker") ||ismType(target.getCard(),"gunner") 
					||ismType(target.getCard(),"hero")||ismType(target.getCard(),"lord") 
					||ismType(target.getCard(),"morphing cat") 
					||ismType(target.getCard(),"paladin")||ismType(target.getCard(),"villain") 
					||ismType(target.getCard(),"ninja") ||ismType(target.getCard(),"boss") 
					||ismType(target.getCard(),"bard") ||ismType(target.getCard(),"townsfolk") 
					||ismType(target.getCard(),"mage")||ismType(target.getCard(),"sister") 
					||ismType(target.getCard(),"theif") ||ismType(target.getCard(),"thief")  
					) newcardid= 919;
					if (ismType(target.getCard(),"slime") 
					) newcardid= 920;
				if (ismType(target.getCard(),"skeleton") 
					||ismType(target.getCard(),"horror") 
					||ismType(target.getCard(),"demon")
					||ismType(target.getCard(),"insect")
					
					) newcardid= 921;
					
				if (ismType(target.getCard(),"robot") 
			||ismType(target.getCard(),"cyborg") ||ismType(target.getCard(),"computer") 
			||ismType(target.getCard(),"generator") 
					||ismType(target.getCard(),"ship") 
					||ismType(target.getCard(),"zor") 
					) newcardid= 922;
			    if (ismType(target.getCard(),"dragon") 
			||ismType(target.getCard(),"wyrm") 
					||ismType(target.getCard(),"dinosaur") 
					) newcardid= 924;		
			if (ismType(target.getCard(),"golem")) 
					
					 newcardid= 934;			
				if (ismType(target.getCard(),"wall") 
			||ismType(target.getCard(),"weapon") 
			||ismType(target.getCard(),"building") 		 
					) newcardid= 933;			
					
				if (perform) {
					destroy_card(self);
					self.rand = random.nextInt(10000);
					self.copydata(carddata[newcardid]);
					self.attack = attack;
					self.lifepoints = lifepoints;
					self.token = true;
					self.mtype = "symbiote " + target.getCard().mtype;
					destroy_card(target.getCard());
				}
			}
			
			else if (command.startsWith("MERGE") && !skiptoend) { // Merging
				StringTokenizer cmdtoken = new StringTokenizer(command,"^");	
				if (!cmdtoken.hasMoreTokens()) return 0;
				cmdtoken.nextToken();// eat the command
		
		
		
				if (!cmdtoken.hasMoreTokens()) return 0;
				String findcard = cmdtoken.nextToken();
				
				if (!cmdtoken.hasMoreTokens()) return 0;
				int newcardid = Integer.parseInt(cmdtoken.nextToken());
				
				if (!cmdtoken.hasMoreTokens()) return 0;
				int attack = getvalue(cmdtoken.nextToken(),count,player,target,self);
				
				if (!cmdtoken.hasMoreTokens()) return 0;
				int lifepoints = getvalue(cmdtoken.nextToken(),count,player,target,self);
				if (!target.isCard) if (perform) {skiptoend = true; continue;} else return 0;
				if (target.getCard().dummy) if (perform) {skiptoend = true; continue;} else return 0;

				
				if (!(target.getCard().name.equals(findcard))) if (perform) {skiptoend = true; continue;} else return 0;
				if (owner(target.getCard()) != player) if (perform) {skiptoend = true; continue;} else return 0;
				if (perform) destroy_card(target.getCard());
				if (perform)destroy_card(self);
				if (perform)self.rand = random.nextInt(10000);
				if (perform)self.copydata(carddata[newcardid]);
				if (perform)self.attack = attack;
				if (perform)self.lifepoints = lifepoints;
				if (perform)self.token = true;
			}
		}	
		return 1;
	
	
	}	
	int not_player(int player) {
		if (player == 1) return 2;
		else return 1;	
		
	}
	
	boolean isDefending(CardmasterServerCard card) {
		if (owner(card) == playerphase) return false;
		if (card.dummy) return false;
		if (!(card.typecode.equals("m"))) return false;	
		if (card.dizzy && phase != PHASE_RESOLVE) return false; 
		for (int i =0;i<5;i++) {

		//	System.out.println("Checking attacker " + i);
			if ((m1[i] == card) && (defender[i+1] >0)&& defender[i+1] <= 6 && playerphase == 2) {
				
				if (attacker[defender[i+1]] == i+1) return true;
				
			}
			if ((m2[i] == card) && (defender[i+1] >0)&& defender[i+1] <= 6 && playerphase == 1) {
				
				if (attacker[defender[i+1]] == i+1) return true;
				
			}			
		}	
		return false;
		
	}
	
	boolean isAttacking(CardmasterServerCard card) {
		if (owner(card) != playerphase) return false;
		
		if (card.dummy) return false;
		if (!(card.typecode.equals("m"))) return false;
			
		if (card.dizzy && phase != PHASE_RESOLVE) return false; 
		for (int i =0;i<5;i++) {

		//	System.out.println("Checking attacker " + i);
			if ((m1[i] == card) && (attacker[i+1] >0)&& attacker[i+1] <= 6 && playerphase == 1) {
				if (attacker[i+1] == 6) return true;
				if (defender[attacker[i+1]] == i+1) return true;
				
			}
			if ((m2[i] == card) && (attacker[i+1] >0)&& attacker[i+1] <= 6 && playerphase == 2) {
				if (attacker[i+1] == 6) return true;
				
				if (defender[attacker[i+1]] == i+1) return true;
				
				
			
			}
		}	
		return false;
	}
	
	boolean cancelUntilEndTurn(CardmasterServerCard card, boolean change) {
		if (card == null) return false;
		if (card.dummy) return false;
		
		
		
		
		
		
		
		
		if (card.attackmod ==0 && card.lifemod ==0) return false;
		if (change) {
			card.lifemod = 0;
			card.attackmod = 0;
			if (card.typecode.equals("m")) if (card.attack <=0) card.attack = 0;
			if (card.typecode.equals("m")) if (card.lifepoints <= 0) destroy_card(card);
		}
		return true;
	}
	
	void DoCharms(int player ) {
		
		for (int i=0;i<5;i++) {
			CardmasterServerCard m = getSlot(player,i,"m");
		
				if (m.GetStatusValue("charm") >0) {
				
					
					if (m.GetStatusValue("charm") == 1) {
						m.SetStatusValue("charm",m.GetStatusValue("charm")-1);
						CardmasterServerCard m2 = GetFirstFreeSlot(not_player(player),"m");
						if (m2 == null) m.SetStatusValue("charm",1);
						else MoveToFirstFreeSlot(m,not_player(player));
					}
					else m.SetStatusValue("charm",m.GetStatusValue("charm")-1);

				}
		
		
		CardmasterServerCard e = getSlot(player,i,"e");
		
				if (e.GetStatusValue("charm") >0) {
				
					
					if (e.GetStatusValue("charm") == 1) {
						e.SetStatusValue("charm",e.GetStatusValue("charm")-1);
						CardmasterServerCard e2 = GetFirstFreeSlot(not_player(player),"e");
						if (e2 == null) e.SetStatusValue("charm",1);
						else MoveToFirstFreeSlot(e,not_player(player));
					}
					else e.SetStatusValue("charm",e.GetStatusValue("charm")-1);

				}
		
		
	}
	}
		void TempStatus(String statusname, boolean isturn) {
			TempStatus(statusname,isturn,0);
		}
	void TempStatus(String statusname, boolean isturn, int owner) {
		for (int i=0;i<5;i++) {
		
		if (isturn) {
		if (owner != 2)	m1[i].SetStatusValue(statusname,0);
		if (owner != 1)		m2[i].SetStatusValue(statusname,0);
		if (owner != 2)			e1[i].SetStatusValue(statusname,0);
		if (owner != 1)		e2[i].SetStatusValue(statusname,0);
		}
		else {
		
			if (owner != 2 && m1[i].GetStatusValue(statusname) >=1  && playerphase == 1 ) {
				m1[i].SetStatusValue(statusname,m1[i].GetStatusValue(statusname)-1);
			}
			if (owner != 1 && m2[i].GetStatusValue(statusname) >=1 && playerphase == 2 ) {
				m2[i].SetStatusValue(statusname,m2[i].GetStatusValue(statusname)-1);
			}
				if (owner != 2 && e1[i].GetStatusValue(statusname) >=1   && playerphase == 1) {
				e1[i].SetStatusValue(statusname,e1[i].GetStatusValue(statusname)-1);
			}
			if (owner != 1 && e2[i].GetStatusValue(statusname) >=1 && playerphase == 2) {
				e2[i].SetStatusValue(statusname,e2[i].GetStatusValue(statusname)-1);
			}
		}
		
		}
	}
	
	void untilendofturn() {
		
		
		
			
			TempStatus("prevtn",true);
			TempStatus("prevntn",true);
			
			TempStatus("invturn",true);
			TempStatus("invis",true);
		
			TempStatus("spimturn",true);
			TempStatus("raddturn",true);
			TempStatus("abimturn",true);
			TempStatus("quickturn",true);
			TempStatus("entturn",true);
			TempStatus("decoyturn",true);
			TempStatus("alltyturn",true);
			TempStatus("walltyturn",true);
			
			TempStatus("abixturn",true);
			TempStatus("spixturn",true);
			TempStatus("quixturn",true);
			TempStatus("entxturn",true);
			TempStatus("invxturn",true);
			TempStatus("radxturn",true);
			
			TempStatus("decyxturn",true);
			TempStatus("raddturn",true);
			TempStatus("xsacturn",true);
			TempStatus("xatkturn",true);
			TempStatus("xdefturn",true);
			TempStatus("tentturn",true);	
			
			
			
			TempStatus("invtemp",false);
			TempStatus("spimtemp",false);
			TempStatus("raddtemp",false);
			TempStatus("abimtemp",false);
			TempStatus("quicktemp",false);
			TempStatus("enttemp",false);
			TempStatus("decoytemp",false);
			TempStatus("alltytemp",false);
			TempStatus("walltytemp",false);
			
			TempStatus("abixtemp",false);
			TempStatus("spixtemp",false);
			TempStatus("quixtemp",false);
			TempStatus("entxtemp",false);
			TempStatus("invxtemp",false);
			TempStatus("radxtemp",false);
			
			TempStatus("decyxtemp",false);
			TempStatus("raddtemp",false);
			TempStatus("xsactemp",false);
			TempStatus("xatktemp",false);
			TempStatus("xdeftemp",false);
			TempStatus("tenttemp",false);	
			
		
		
		for (int i = 0;i<5;i++) {
	
			
			if (!m1[i].dummy) {
			
			
		
			
			
			
			
				if (m1[i].GetStatusValue("poison") >0 && playerphase == 1) {
					damage_monster(1,i,10);
					m1[i].SetStatusValue("poison",m1[i].GetStatusValue("poison")-1);
				}
				if (m1[i].GetStatusValue("burn") > 0 && playerphase == 1) {
					boolean burn = random.nextInt(100) > 50;
					if (burn) {
						damage_monster(1,i,5);
						}
						else {
							m1[i].SetStatusValue("burn",0);
						}
				}
				/*
				if (m1[i].GetStatusValue("tempcontrol") >=1) {
					m1[i].SetStatusValue("tempcontrol",m1[i].GetStatusValue("tempcontrol")-1);
					
					if (m1[i].GetStatusValue("tempcontrol") == 0) {
						
					}
					
				}
				*/ //incomplete
			
			}
			
			if (!m2[i].dummy) {
				
				if (m2[i].GetStatusValue("poison") >0 && playerphase == 2) {
					damage_monster(2,i,10);
					m2[i].SetStatusValue("poison",m2[i].GetStatusValue("poison")-1);
				}
				
			
			
			
			if (m2[i].GetStatusValue("burn") > 0&& playerphase == 2) {
					boolean burn = random.nextInt(100) > 50;
					if (burn) {
						damage_monster(2,i,5);
						}
						else {
							m2[i].SetStatusValue("burn",0);
						}
				}
			}
			
			
			
		
			
			
			cancelUntilEndTurn(m1[i],true);
			cancelUntilEndTurn(m2[i],true);
			cancelUntilEndTurn(e1[i],true);
			cancelUntilEndTurn(e2[i],true);		
					
					
					
			
			
			
		}
		
		
	}
	int owner(CardmasterServerCard card){
		for (int i =0;i<5;i++) {
			if (m1[i] == card) return 1;
			if (e1[i] == card) return 1;
			
			
			
			if (m2[i] == card) return 2;
			if (e2[i] == card) return 2;
			
			if (dm1[i] == card) return 1;
			if (de1[i] == card) return 1;
			
			
			
			if (dm2[i] == card) return 2;
			if (de2[i] == card) return 2;
						
		}
		
		for (int i=0;i<8;i++) {
			if (h1[i] == card) return 1;
			if (h2[i] == card) return 2;
		}
		if (card == location) {
			return playerphase;
				
			
		}
		if (card == tempp1card) {
			return 1;
		}
		if (card == tempp2card) {
			return 2;
		}
		return 0;	
		
	}
	void resolveattack() {
		for (int i=1;i<6;i++) {
			if (defender[i] > 0 && getSlot(not_player(playerphase),i-1,"m").dummy) { 
				attacker[defender[i]] = 6;
				defender[i] = 0;
			}
			if (defender[i] > 0 &&  !defenderlock[i] && getSlot(not_player(playerphase),i-1,"m").dizzy) { 
				attacker[defender[i]] = 6;
				defender[i] = 0;
			}
			
			
			
		/*
			if (!(getSlot(playerphase,i-1,"m").dummy)) {
				if (attacker[i] != 0)
				if (getSlot(playerphase,i-1,"m").GetStatusValue("facedown") >=1) {
					getSlot(playerphase,i-1,"m").SetStatusValue("facedown",0);
					return_card_info(getSlot(playerphase,i-1,"m").cardid,player_named(not_player(playerphase)));
					return_card_info(getSlot(playerphase,i-1,"m").cardid,"$obs$");
				}
			
			}
			if (!(getSlot(not_player(playerphase),i-1,"m").dummy)) {
				if (defender[i] != 0)
				if (getSlot(not_player(playerphase),i-1,"m").GetStatusValue("facedown") >=1) {
					getSlot(not_player(playerphase),i-1,"m").SetStatusValue("facedown",0);
					return_card_info(getSlot(not_player(playerphase),i-1,"m").cardid,player_named((playerphase)));
					return_card_info(getSlot(not_player(playerphase),i-1,"m").cardid,"$obs$");
				}
			
			}
			*/
						
		}
		/*
		
		for (int i=1;i<6;i++) {
					if (!(getSlot(playerphase,i-1,"m").dummy)) {
				if (attacker[i] != 0)
				if (getSlot(playerphase,i-1,"m").GetStatusValue("facedown") >=1) {
					getSlot(playerphase,i-1,"m").SetStatusValue("facedown",0);
					return_card_info(getSlot(playerphase,i-1,"m").cardid,player_named(not_player(playerphase)));
					return_card_info(getSlot(playerphase,i-1,"m").cardid,"$obs$");
				}
			
			}
			if (!(getSlot(not_player(playerphase),i-1,"m").dummy)) {
				if (defender[i] != 0)
				if (getSlot(not_player(playerphase),i-1,"m").GetStatusValue("facedown") >=1) {
					getSlot(not_player(playerphase),i-1,"m").SetStatusValue("facedown",0);
					return_card_info(getSlot(not_player(playerphase),i-1,"m").cardid,player_named((playerphase)));
					return_card_info(getSlot(not_player(playerphase),i-1,"m").cardid,"$obs$");
				}
			
			}
		}
		
		*/
		
		for(int i = 1; i<6; i++) {
			
			if (!(getSlot(playerphase,i-1,"m").dummy))
			if ((!(getSlot(playerphase,i-1,"m").dizzy))) 
			
			if (attacker[i] == 6) { // attack player direclty
			String cardname = getSlot(playerphase,i-1,"m").name;
				//attackresult[10] += (int)getModifiedValue(getSlot(playerphase,i-1,"m"),"atk","P",playerphase); // save damage to player
				if (!isActivatedConstant("MNODT",playerphase,getSlot(playerphase,i-1,"m")))dizzy(getSlot(playerphase,i-1,"m"),true);
				
				alltriggeredabilities(getSlot(playerphase,i-1,"m"),"TCM" + playerphase + "P");
				
				int adamage = (int)getModifiedValue(getSlot(playerphase,i-1,"m"),"atk","P",playerphase);
		
				if (getSlot(playerphase,i-1,"m").GetStatusValue("facedown") >=1) {
					getSlot(playerphase,i-1,"m").SetStatusValue("facedown",0);
					return_card_info(getSlot(playerphase,i-1,"m").cardid,player_named(not_player(playerphase)));
					return_card_info(getSlot(playerphase,i-1,"m").cardid,"$obs$");
				}
				
				
				
					int adamage2 = adamage;
					if (isActivatedConstant("PMODD",not_player(playerphase),null)) {
								 adamage2 = (int)((double)adamage2 * tempcount2);
							
							}
				
				
				
				attackresult[10]+=adamage2;
				if (damage_player(not_player(playerphase),adamage) <= 0) {
					if (winner ==0){
					
					winner = playerphase;
					if (!endedonce) 	duelendedbycard = "Combat of " + cardname;
					if (!endedonce) 	duelendedbytype = "Direct Attack - " + adamage + " damage";
					}
						
				}
			}
			
			else if (attacker[i] > 0 && attacker[i] < 6 && defender[attacker[i]] == i) { //attack some card.
				if (!(getSlot(playerphase,i-1,"m").dummy))
				if ((!(getSlot(playerphase,i-1,"m").dizzy))&&( (!(getSlot(not_player(playerphase),attacker[i]-1,"m").dizzy))||defenderlock[attacker[i]] )) {
					int attackercolor = getSlot(playerphase,i-1,"m").colorcode;
					int defendercolor = getSlot(not_player(playerphase),attacker[i]-1,"m").colorcode;
					
					String anim = getSlot(playerphase,i-1,"m").attanim;
                    String danim = getSlot(not_player(playerphase),attacker[i]-1,"m").attanim;
                   
				//	System.out.println("Animations: " + anim + danim);
					
					
						
					CardmasterServerCard attackcard = getSlot(playerphase,i-1,"m");
					CardmasterServerCard defcard = getSlot(not_player(playerphase),attacker[i]-1,"m");
					
					boolean attackquick = HasQuickHit(attackcard);
					boolean defendquick = HasQuickHit(defcard);
					
					
					
					int damage = (int)getModifiedValue(getSlot(not_player(playerphase),attacker[i]-1,"m"),"atk","D",not_player(playerphase));
                    int adamage = (int)getModifiedValue(getSlot(playerphase,i-1,"m"),"atk","C",playerphase);
                    
                  
                    if (getSlot(not_player(playerphase),attacker[i]-1,"m").flag("swat")) adamage = adamage / 2;
                    
					
					if (getSlot(not_player(playerphase),attacker[i]-1,"m").flag("combmatch")) damage = (int)getModifiedValue(getSlot(playerphase,i-1,"m"),"atk","C",playerphase);
					if (getSlot(playerphase,i-1,"m").flag("combmatch")) adamage = (int)getModifiedValue(getSlot(not_player(playerphase),attacker[i]-1,"m"),"atk","D",not_player(playerphase));
					
					
					
					if (getSlot(not_player(playerphase),attacker[i]-1,"m").flag("koba")) {
						if (random.nextInt(100) > 80) {
							damage = 100;
						}
					}
					if (getSlot(playerphase,i-1,"m").flag("koba")) {
						if (random.nextInt(100) > 80) {
							adamage = 100;
						}
					}
					
				
					
				
			
					int atemp = attacker[i];		
					boolean deflock = defenderlock[atemp];
				
					String cardname = attackcard.name;
					boolean defenderdies = false;
					boolean attackerdies = false;
					
										
					attackresult[attacker[i]-1] = damage_amount(getSlot(not_player(playerphase),attacker[i]-1,"m"),
													attackercolor,
													adamage);
						attackresult[i-1+5] = damage_amount(getSlot(playerphase,i-1,"m"),defendercolor,damage);
					
					
					if (attackresult[attacker[i]-1] >= (defcard.lifepoints+defcard.lifemod) &&
						prevncheck(getSlot(not_player(playerphase),attacker[i]-1,"m"),attackresult[attacker[i]-1])  >= (defcard.lifepoints+defcard.lifemod)
					
					
					) defenderdies = true; 
					if (attackresult[attacker[i]-1+5] >= (attackcard.lifepoints+defcard.lifemod)&&
						prevncheck(getSlot(playerphase,i-1,"m"),attackresult[attacker[i]-1+5])  >= (defcard.lifepoints+defcard.lifemod)) attackerdies = true; 
					
					 if (attackquick && !defendquick && defenderdies) damage = 0;
					 
						attackresult[i-1+5] = damage_amount(getSlot(playerphase,i-1,"m"),defendercolor,damage);
					
						
				  
                   // if (defendquick && !attackquick) adamage = 0;
                    
					
                     if (adamage > 0) message("ANM#" + not_player(playerphase) + "#m#" + (attacker[i]-1) + "#" + anim + "#");
                    if (damage > 0) message("ANM#" + playerphase + "#m#" + (i-1) + "#" + danim + "#");
                    
                    
                    if (prevncheck(getSlot((playerphase),i-1,"m"),damage) > 0 &&
                    	damage_amount(getSlot(playerphase,i-1,"m"),defendercolor,damage) > 0 && !(attackquick && !defendquick && defenderdies))
                    	triggerabilities(getSlot(playerphase,i-1,"m"),
                    	getSlot(not_player(playerphase),attacker[i]-1,"m"),"TCM" + playerphase + "A")	;				
                    			
								

					if (getSlot(not_player(playerphase),attacker[i]-1,"m") != null)
                    if (damage_amount(getSlot(not_player(playerphase),attacker[i]-1,"m"),attackercolor,adamage) > 0 &&
                    	(prevncheck(getSlot(not_player(playerphase),attacker[i]-1,"m"),adamage)) > 0
                    
                    ) 
                    {
                    
                    
                    	 
                    
                    	triggerabilities(getSlot(not_player(playerphase),attacker[i]-1,"m"),
                    	getSlot(playerphase,i-1,"m"),"TCM" + not_player(playerphase) + "A")	;				
                    }	

					if (getSlot(playerphase,i-1,"m") != null  && getSlot(not_player(playerphase),attacker[i]-1,"m") != null)
                    if (
					(prevncheck(getSlot((playerphase),i-1,"m"),damage) >= 
                     	       (getSlot((playerphase),i-1,"m").lifepoints+
                     		    getSlot((playerphase),i-1,"m").lifemod)) &&
                    	
                     	damage_amount(getSlot(playerphase,i-1,"m"),defendercolor,damage) >= 
						      (getSlot(playerphase,i-1,"m").lifepoints+getSlot(playerphase,i-1,"m").lifemod))
                    	triggerabilities(getSlot(playerphase,i-1,"m"),
                    	getSlot(not_player(playerphase),attacker[i]-1,"m"),"TCM" + playerphase + "<");		

					
					if (getSlot(not_player(playerphase),attacker[i]-1,"m") != null && getSlot(not_player(playerphase),attacker[i]-1,"m") != null)
                    if (
					
					(prevncheck(getSlot(not_player(playerphase),attacker[i]-1,"m"),adamage)) 
							>= (getSlot(not_player(playerphase),attacker[i]-1,"m").lifepoints+
								getSlot(not_player(playerphase),attacker[i]-1,"m").lifemod) &&
                    	
					damage_amount(getSlot(not_player(playerphase),attacker[i]-1,"m"),attackercolor,adamage)
						    >= (getSlot(not_player(playerphase),attacker[i]-1,"m").lifepoints+
							getSlot(not_player(playerphase),attacker[i]-1,"m").lifemod))
                    	triggerabilities(getSlot(not_player(playerphase),attacker[i]-1,"m"),
								getSlot(playerphase,i-1,"m"),"TCM" + not_player(playerphase) + "<")	;				
                    
					int attackeri = attacker[i]-1;
						
					
                   	
					
                    int spillover =  0;
                    // adamage = damage that attacker does to defender
                    // damage = damage that defender does to attacker
                   
                    //if (!(attackerdies && defendquick && !attackquick)) 
                   // if (defendquick && !attackquick) {
                   // 	spillover = 0;
                    //}
                    //else {
                    	
               	if (getSlot(playerphase,i-1,"m").GetStatusValue("facedown") >=1) {
					getSlot(playerphase,i-1,"m").SetStatusValue("facedown",0);
					return_card_info(getSlot(playerphase,i-1,"m").cardid,player_named(not_player(playerphase)));
					return_card_info(getSlot(playerphase,i-1,"m").cardid,"$obs$");
				}
				if (getSlot(not_player(playerphase),attacker[i]-1,"m") != null)
				if (getSlot(not_player(playerphase),attacker[i]-1,"m").GetStatusValue("facedown") >=1) {
					getSlot(not_player(playerphase),attacker[i]-1,"m").SetStatusValue("facedown",0);
					return_card_info(getSlot(not_player(playerphase),attacker[i]-1,"m").cardid,player_named((playerphase)));
					return_card_info(getSlot(not_player(playerphase),attacker[i]-1,"m").cardid,"$obs$");
				}
			 	
                    
                    	spillover  = damage_monster(not_player(playerphase),attackeri,adamage, attackercolor);
					//}
					spillover = (spillover / 2);
					
				
					if (!(defcard.dummy && attackquick && !defendquick)) {
					
						damage_monster(playerphase,i-1,damage, defendercolor);
					}
					else {
						attackresult[attacker[i]-1+5] = 0;
					}	
					if (!getSlot(playerphase,i-1,"m").dummy)
					if (!isActivatedConstant("MNODT",playerphase,getSlot(playerphase,i-1,"m")))dizzy(getSlot(playerphase,i-1,"m"),true);
					
						
					spillover = (int)getModifiedOverflowValue(not_player(playerphase),spillover);
					if (spillover >0 && !deflock) {
								int spillover2 = spillover;
							if (isActivatedConstant("PMODD",not_player(playerphase),null)) {
								spillover2 = (int)((double)spillover2 * tempcount2);
							
							}
						
						attackresult[10]+=spillover2; //Spillover damage.
						if (damage_player(not_player(playerphase),spillover) <= 0) {
							if (winner ==0){
								winner = playerphase;
							if (!endedonce ) 	duelendedbycard = "Combat of " + cardname;
							if (!endedonce ) 	duelendedbytype = "Overflow - " + spillover + " damage";
							}
								
							
						}	
					}			
				}
			}
	
		}
		destroy_marked_cards();
		
		
		
	}
	
	// Go to next phase.
	void goNextPhase() {
		
		
		
		phase++;
		
		if (phase == 6) {
			phase = 0;
			if (playerphase == 1) playerphase = 2;
			else if (playerphase == 2) { playerphase = 1; turncount++; }	
			
		}
		//System.out.println("Phase " + phase);
		p2abilityresolve = false;
		p1abilityresolve = false;
		abilities = new AbilitySpell[0];
		message("ABR#" + bToI(p1abilityresolve) + "#" + bToI(p2abilityresolve) + "#" + abilities.length + "#");
		
		doPhaseStuff();
	}
	
	
	void doPhaseStuff() {
	//	if (deck1 == null) break;
		random.nextInt(2);
	//	message(STC()); MDN();
		if (phase == PHASE_DRAW) {
			turndamage1 = false;
			turndamage2 = false;
			attacking = false;
			for (int i=1;i<6;i++) { attacker[i] = 0; defender[i] = 0; defenderlock[i] = false; }
			for (int i =0;i<11;i++) { attackresult[i] = 0; }
			// mana gain
			if (playerphase == 2 && turncount > 1) {
				p2D++;
				p2G++;
				p2L++;
				p2Du++;
				p2Gu++;
				p2Lu++;
						
			}
			else if (playerphase == 1 && turncount > 1) {
				p1D++;
				p1G++;
				p1L++;
				p1Du++;
				p1Gu++;
				p1Lu++;		
			}
			if (skipturn) {
				 skipturn = false;	
	if ((playerphase == 1 && skipturn1>0)||( playerphase == 2 && skipturn2 > 0)) {
		skipturn = true;
			//message("MES#"+player_named(playerphase)+" skips a turn!");
		} 
		else {
			message("MES#"+player_named(playerphase)+" can play again!");
			
		}
		
		
				 
				 
				 }	
			untilendofturn();	 
			do_end_turn_abilities(not_player(playerphase));
			
			DoCharms(playerphase);
			do_begin_turn_abilities(playerphase);
			
			do_begin_turn_abilities_2(playerphase);
			if (playerphase == 1 && player1crossedwires) {
				player1crossedwires = false;
				SwitchHands();
			}
			if (playerphase == 2 && player2crossedwires) {
				player2crossedwires = false;
				SwitchHands();
			}
			undizzy(playerphase);
			
			if (!isActivatedConstant("PNODR",playerphase,null)) {
				if (!endedonce && winner == 0) duelendedbycard = "None";
				draw_card(playerphase);// draw a card.
			}
			do_defer_abilities(playerphase);
			
			int totalmanas = 0;
			int totalmanao = 0;
			CardmasterServerCard[] mo = m1;
			CardmasterServerCard[] mp = m2;
			int decksizes=0;
			int decksizeo=0;
			
			if (playerphase==1) {
				totalmanas = (p1L+p1G+p1D);
				totalmanao = (p2L+p2G+p2D);
				mo = m1;	
				mp = m2;
				decksizeo = deck2.deckcards;
				decksizes = deck1.deckcards;
			}
			else if (playerphase==2) {
				totalmanao = (p1L+p1G+p1D);
				totalmanas = (p2L+p2G+p2D);
				mo = m2;
				mp = m1;
				decksizeo = deck1.deckcards;
				decksizes = deck2.deckcards;
				
			}
			boolean undizzything = false;
			int sgrave = numgrave(playerphase);
			int ograve = numgrave(not_player(playerphase));
			
			if (sgrave == 0) sgrave = 1;
			if (ograve == 0) ograve = 1;
			if (decksizeo == 0) decksizeo = 1;
			if (decksizes == 0) decksizes = 1;
			
			if (
				(
				(ograve % (decksizeo))
				 / (sgrave)
				 ) >=
				 (
				(sgrave % (decksizes))
				 / (ograve)
				 )
				 ) {
				 
				 	undizzything = true;
				}
			for (int i=0;i<5;i++) {
				if (!mo[i].dummy){
					if (mo[i].GetStatusValue("year2") == 1) {
						runability("START;LMODT^slf^bm^none^none^"+totalmanas+"^;LMODT^slf^bm^none^none^"+(-totalmanao)+"^;END;",playerphase,null,mo[i],-1);
						
					}
				}
				if (!mp[i].dummy) {
					if (mp[i].GetStatusValue("year2") == 1 && undizzything) {
						if (!isActivatedConstant("MNOUD",not_player(playerphase),mp[i])) {
							
							dizzy(mp[i],false);
						
						}
						
					}
				}
			}
			
			
			
			//	System.out.println("Skipturn1: " + skipturn1 + " Skipturn2: " + skipturn2);
			if ((playerphase == 1 && skipturn1>0)||( playerphase == 2 && skipturn2 > 0)) {
				if (playerphase ==1) skipturn1--;
				if (playerphase ==2) skipturn2--;
				skipturn = true;
				message("MES#"+player_named(playerphase)+" skips a turn!");
				
				
				
				
			}
			// Do this player's start of turn cleanup abilities
			
		
		}
		else if (phase == PHASE_SAC) {
			for (int i = 0;i<5;i++) {
				
				getSlot(playerphase,i,"m").SetStatusValue("nouse",0);
				getSlot(playerphase,i,"e").SetStatusValue("nouse",0);
				
				
				if (getSlot(playerphase,i,"m").GetStatusValue("noustemp") > 0) {
					getSlot(playerphase,i,"m").SetStatusValue("noustemp",getSlot(playerphase,i,"m").GetStatusValue("noustemp")-1);
					
				}
				
				if (getSlot(playerphase,i,"e").GetStatusValue("noustemp") > 0) {
					getSlot(playerphase,i,"e").SetStatusValue("noustemp",getSlot(playerphase,i,"e").GetStatusValue("noustemp")-1);
					
				}
				
				
					
				
				
				
			}
		}
		else if (phase == PHASE_PLAY) {
			
			
		}
		else if (phase == PHASE_ATTACK) {
			
			attacking = false;
			for (int i=1;i<6;i++) { attacker[i] = 0; defender[i] = 0; defenderlock[i] = false; }
			for (int i =0;i<11;i++) { attackresult[i] = 0; }
		}
		else if ((phase == PHASE_DEFEND)) {
			
				attacking = false;
			for (int i=1;i<6;i++)	if (attacker[i] > 0) attacking = true;
				if( attacking == false) { goNextPhase(); return; } // skip if no attack
				fix_attackers();
				message("ATD#" + playerphase + "#" + attacker[1]+ "#" + attacker[2]+ "#" + attacker[3]+ "#" + attacker[4]+ "#" + attacker[5] + "#");
		}
		else if (phase == PHASE_RESOLVE) {
		//	if (!attacking) { goNextPhase(); return; } // skip if no attack
			// resolve attack
			attacking = false;
			do_defend_abilities(not_player(playerphase));
			do_attack_abilities(playerphase);
			resolveattack();
			do_after_attack_abilities(playerphase);
			String messagesend = "ATR#" + playerphase + "#";
			for (int i =0;i<11;i++) {
				messagesend = messagesend + attackresult[i] + "#";
			}
			message(messagesend); // send attack result.

			if (winner!=0) {
			
				
				
				endduel(winner);
			}
			nextmfacedown = false;
    		nextefacedown = false;
    		nextentfacedown = false;
			
		}
		
		
			if (!skipturn) {
			
			message(STP());
			MessageSTN();
			MessageSTC();
			
			message(HAND(player_named(playerphase)),player_named(playerphase));
			message(HAND(player_named(not_player(playerphase))),player_named(not_player(playerphase)));
				message(LOC()); MDN();
			
			}
	}
	int numgrave(int player) {
	if (player == 1) return deck1.gravecards;
	if (player == 2) return deck2.gravecards;
	return 0;
		
	}
	void fix_attackers() {
		for (int i=1;i<6;i++) {
		
			if (attacker[i] != 6 && attacker[i] != 0){
			
				if (i != defender[attacker[i]]) {
				
					attacker[i] = 6; 
				}
			}
				
		}
		
		for (int i=1;i<6;i++) {
			
			
			if (attacker[i] > 0) {
				
				if (getSlot(playerphase,i-1,"m").dummy) {
					
					
					if (attacker[i] < 6) {
						defender[attacker[i]] = 0;
						defenderlock[attacker[i]] = false;
						
					}
					
					System.out.println("Atkfix: Setting attacker["+i+"] to 0");
					attacker[i] = 0;
					
					
				}
				
			}
			
			if (defender[i] > 0) {
				if (getSlot(not_player(playerphase),i-1,"m").dummy) {
					defenderlock[i] = false;
					if (defender[i] < 6) {
						attacker[defender[i]] = 6;
//							System.out.println("Atkfix: Setting attacker["+defender[i]+"] to 6");
						
					}
					defender[i] = 0;
				
				
				}
				
				
			}
			
			
			
		}
		
	}
	

	void undizzy(int player) {
		if (player ==1) {
			for (int i =0;i<5;i++) {
				
				if  (isActivatedConstant("MNUND",player,m1[i])) m1[i].noundizzy = true;
				if  (isActivatedConstant("MNUND",player,e1[i])) e1[i].noundizzy = true;
				
				
				if (!m1[i].dummy && !m1[i].noundizzy)  dizzy(m1[i],false);
				if (!e1[i].dummy  && !e1[i].noundizzy) dizzy(e1[i],false);
				m1[i].noundizzy = false;e1[i].noundizzy = false;
				if (m1[i] != null) if (!m1[i].dummy) if (m1[i].fleet) destroy_card(m1[i]);
				if (e1[i] != null) if (!e1[i].dummy) if (e1[i].fleet) destroy_card(e1[i]);
				
			}
			
		}
		if (player ==2) {
			for (int i =0;i<5;i++) {
				
				if  (isActivatedConstant("MNUND",player,e2[i])) e2[i].noundizzy = true;
				if  (isActivatedConstant("MNUND",player,m2[i])) m2[i].noundizzy = true;
				
				if (!m2[i].dummy && !m2[i].noundizzy) dizzy(m2[i],false);
				if (!e2[i].dummy && !e2[i].noundizzy) dizzy(e2[i],false);
				m2[i].noundizzy = false;e2[i].noundizzy = false;
				if (m2[i] != null) if (!m2[i].dummy) if (m2[i].fleet) destroy_card(m2[i]);
				if (e2[i] != null) if (!e2[i].dummy) if (e2[i].fleet) destroy_card(e2[i]);
			}
			
		}		
		if (!skipturn) {
		
		MessageSTC();	message(LOC()); //MDN();
		}
		
	}
	CardmasterChatRoom() {
		random = new MersenneTwisterFast();
		//System.out.println("Room created.");
		empty = true;
		dead = false;
		name1 = "";
		name2 = "";
		name2wait = "";
		messagebuffer = new String[100];
		messageto = new String[100];
		for (int i=0;i<100;i++) messageto[i] = "";
		messageid = new int[100];
		currentmessageid = 1;
		currentindex = 0;
		loadCardData();
		loadAbilityData();
			
		attacker = new int[6];
		defender = new int[6];
		defenderlock = new boolean[6];
		attackresult = new int[11]; //0-4 are opponent, 5-9 are yours, and 10 is. player.
			h1 = new CardmasterServerCard[8];
			h2 = new CardmasterServerCard[8];
			m1 = new CardmasterServerCard[5];
			m2 = new CardmasterServerCard[5];
			e1 = new CardmasterServerCard[5];
			e2 = new CardmasterServerCard[5];
		for (int i = 0;i<5;i++) {
		m1[i] = new CardmasterServerCard();
		m2[i] = new CardmasterServerCard();
		e2[i] = new CardmasterServerCard();
		e1[i] = new CardmasterServerCard();
		h1[i] = new CardmasterServerCard();
		h2[i] = new CardmasterServerCard();
	
		}
		for (int i = 5; i<8;i++) {
		h1[i] = new CardmasterServerCard();
		h2[i] = new CardmasterServerCard();
		}

		}





	public void message(String message, String name) {
	//	System.out.println(" ----- " + name + " : " + message );
		
		currentindex ++;
		if (currentindex >= 100) {
			currentindex = 0;	
			
			
		}
		currentmessageid++;
	////System.out.println("In " + currentindex + " message " + currentmessageid + " : " + message);
		messagebuffer[currentindex] = message;
		messageid[currentindex] = currentmessageid;
		messageto[currentindex] = name;
	}
		
	
	public void message(String message) {	
		//System.out.println(" ----- " + "Allmessage: " + message);
		message(message,"$all$");
	}

	public int messageTag() {
		return currentmessageid;
	}
	
	public String getToName(int id) {
		int index;
		index = -1;
	//	System.out.println("Looking for name " + id);
		if (id > currentmessageid) return "";
		for (int i = 0;i<100;i++) {
			if (messageid[i] == id) { 
			index = i;
	//	System.out.println("Found name in "+ i);
				}
		}
		if (index == -1) return "";
	//System.out.println("Returning name " + messageto[index]);
		return messageto[index];
	}	
	public String getMessageFor(int id, String name) {
				int index;
		index = -1;
	//	System.out.println("Looking for " + id);
		if (id > currentmessageid) return "";
		for (int i = 0;i<100;i++) {
			if (messageid[i] == id) { 
			index = i;
			//	System.out.println("Found message in "+ i);
				}
		}
		if (index == -1) return "";
	//	System.out.println("Returning " + messagebuffer[index]);
	 	String toname = messageto[index];
	 	//System.out.println("Message id: " + id + " is for " + messageto[index] + " contents : " + messagebuffer[index]);
	 	if (toname.equals("$all$") || toname.equals(name) || toname.equals(""))	
		return messagebuffer[index];
		else return null;
		
		
	}
	public String getMessage(int id) {
		int index;
		index = -1;
	//	System.out.println("Looking for " + id);
		if (id > currentmessageid) return "";
		for (int i = 0;i<100;i++) {
			if (messageid[i] == id) { 
			index = i;
			//	System.out.println("Found message in "+ i);
				}
		}
		if (index == -1) return "";
	//	System.out.println("Returning " + messagebuffer[index]);
		return messagebuffer[index];
	}
	

	
	public String STP() {
		String name = name2;
		if (playerphase == 1)  name = name1; 
		return ("STP"  + "#" +  name  + "#" +  phase  + "#" +  turncount + "#"); 
		
	}
	public String GRA(String name) { // returns the GRAVE string.
				CardmasterLibrary deck = null;
			if (name == null) return "GRA#NULL#0#";
			if (name1 == null) return "GRA#NULL#0#";
			if (name2 == null) return "GRA#NULL#0#";
			if (deck2 == null) return "GRA#NULL#0#";
			if (deck1 == null) return "GRA#NULL#0#";
			
			
			
			if (name.equals(name1)) deck = deck1;
			else if (name.equals(name2)) deck = deck2;
			else return "GRA#" + name + "#0#";
			
			String gravestring = "GRA#" + name + "#";
			for (int i =0;i<deck.gravecards;i++) {
				if (carddata[deck.grave[i]] != null)
				gravestring = gravestring+deck.grave[i]+"#";
			}
			return gravestring;				
	}	
	
	
	public String GRA() { // returns the GRAVE string.
		//return "GRA#";
			String gravestring = "GRA#PL1#";
			for (int i =0;i<deck1.gravecards;i++) {
				if (carddata[deck1.grave[i]] != null)
				gravestring = gravestring+carddata[deck1.grave[i]].cardid+"#";
			}
			gravestring = gravestring + "PL2#";	
			for (int i =0;i<deck2.gravecards;i++) {
				if (carddata[deck2.grave[i]] != null)
				gravestring = gravestring+carddata[deck2.grave[i]].cardid+"#";
			}
		
			return gravestring;				
		
	}
	public void MDN() {
		if (waitcards1 > numCardsInHand(1)) waitcards1 = numCardsInHand(1);
		if (waitcards2 > numCardsInHand(2)) waitcards2 = numCardsInHand(2); 
		message("MDN#" + 1 + "#" + waitcards1 + "#");
		message("MDN#" + 2 + "#" + waitcards2 + "#");
		
	}
	
	public String LOC() {
		if (location == null) return ("LOC#" + CardmasterData.DEFAULTLOCATION + "#");
		return("LOC#" + location.cardid + "#");	
		
	}
	public String HAND(String name) {
		if (name == null || name1 == null || name2 == null || h1 == null || h2 == null) {
			return "HND#unknown0#0#0#0#0#0#0#0#";
		}
		for (int i=0;i<8;i++) {
			if (h1[i] == null || h2[i] == null) return "HND#unknown0#0#0#0#0#0#0#0#"; 
		}
		CardmasterServerCard[] hand = null;
		if (name.equals(name1)) hand =h1;
		else if (name.equals(name2)) hand = h2;
		else return "HND#" + name + "#0#0#0#0#0#0#0#0#";
		return ("HND#" + name +
						"#" + hand[0].cardid +
		
						"#" + hand[1].cardid +
						"#" + hand[2].cardid +
						"#" + hand[3].cardid +
						"#" + hand[4].cardid +
						"#" + hand[5].cardid +
		
						"#" + hand[6].cardid +
						"#" + hand[7].cardid + "#");
						
	}
	public void MessageMod() {
		boolean modinplay = false;
		for (int i=0;i<5;i++) {
			if (!de1[i].dummy) modinplay= true;
			if (!de2[i].dummy) modinplay= true;
			if (!dm1[i].dummy) modinplay= true;
			if (!dm2[i].dummy) modinplay= true;
			
		}
		
		if (!modinplay) {
			message("MOD#NONE#");
		}
		
		else {
			int player =0;
				String returnstr = "MOD#" 
							+modifiedValueCard(dm1[0],player)+"#"
							+modifiedValueCard(dm1[1],player)+"#"
							+modifiedValueCard(dm1[2],player)+"#"
							+modifiedValueCard(dm1[3],player)+"#"
							+modifiedValueCard(dm1[4],player)+"#"
						  +modifiedValueCard(de1[0],player)+"#"
						  +modifiedValueCard(de1[1],player)+"#"
						  +modifiedValueCard(de1[2],player)+"#"
						  +modifiedValueCard(de1[3],player)+"#"
						  +modifiedValueCard(de1[4],player)+"#"
						  +modifiedValueCard(dm2[0],player)+"#"
						  +modifiedValueCard(dm2[1],player)+"#"
						  +modifiedValueCard(dm2[2],player)+"#"
						  +modifiedValueCard(dm2[3],player)+"#"
						  +modifiedValueCard(dm2[4],player)+"#"
						  +modifiedValueCard(de2[0],player)+"#"
						  +modifiedValueCard(de2[1],player)+"#"
						  +modifiedValueCard(de2[2],player)+"#"
						  +modifiedValueCard(de2[3],player)+"#"
						  +modifiedValueCard(de2[4],player)+"#";
				message(returnstr);	 
			
			
			
		}
		
		
	}
	
	public String STN(boolean showplayer1, boolean showplayer2, int player) {
		checkGifts();
		int gravecards1 = 0;
		int gravecards2 =0;
		if (deck1!= null) gravecards1 = deck1.gravecards;
		if (deck2!= null) gravecards2 = deck2.gravecards;
		
		
		String p1Dt = p1D + "";
		String p2Dt = p2D + "";
		String p1Lt = p1L + "";
		String p2Lt = p2L + "";
		String p1Gt = p1G + "";
		String p2Gt = p2G + "";
		
	
			if ((player == 1||player == -1) && !showplayer2 ) {
				
				
				p2Dt = "00" + Math.abs(p2Du);
				p2Lt = "00" + Math.abs(p2Lu);
				p2Gt = "00" + Math.abs(p2Gu);
				
				if (p2Du <0) p2Dt = "-" + p2Dt;
				if (p2Lu <0) p2Lt = "-" + p2Lt;
				if (p2Gu <0) p2Gt = "-" + p2Gt;
				
			}
			
			
			if ((player == 2||player == -1) && !showplayer1 ) {
				
				p1Dt = "00" + Math.abs(p1Du);
				p1Lt = "00" + Math.abs(p1Lu);
				p1Gt = "00" + Math.abs(p1Gu);
				
				
				if (p1Du <0) p1Dt = "-" + p1Dt;
				if (p1Lu <0) p1Lt = "-" + p1Lt;
				if (p1Gu <0) p1Gt = "-" + p1Gt;
				
				
			}
			
		
			
			
		
		
		
		return ("STN#" + name1 + "#" + p1life + "#" + p1Dt + "#" + p1Lt+ "#" + p1Gt + "#" + numCardsInHand(1) + "#" +gravecards1+
				"#" + name2  + "#" + p2life + "#" + p2Dt + "#" + p2Lt  + "#" +  p2Gt + "#"+ numCardsInHand(2) + "#" + gravecards2 + "#");
		
	}
	
	public void MessageSTN() {
		boolean showplayer1 =true;
		boolean showplayer2 = true;
		for (int i=0;i<5;i++) {
			if (m1[i] == null) return;
			if (m2[i] == null) return;
			if (e1[i] == null) return;
			if (e2[i] == null) return;
			if (m1[i].GetStatusValue("facedown")>=1) showplayer1 = false;
			if (m2[i].GetStatusValue("facedown")>=1) showplayer2 = false;
			if (e1[i].GetStatusValue("facedown")>=1) showplayer1 = false;
			if (e2[i].GetStatusValue("facedown")>=1) showplayer2 = false;
		}
		if (showplayer1) {
			p1Du = p1D;
			p1Lu = p1L;
			p1Gu = p1G;
		}
		if (showplayer2) {
			p2Du = p2D;
			p2Lu = p2L;
			p2Gu = p2G;
		}
		if (showplayer1 && showplayer2) {
		
			
			message(STN(true,true,0));
		}
		else {
			message(STN(showplayer1,showplayer2,1),name1);
			message(STN(showplayer1,showplayer2,2),name2);
			message(STN(showplayer1,showplayer2,-1),"$obs$");
			
			
		}
	}
	public void MessageSTC() {
		if (deck1 == null || deck2 == null) {message("STC#b#b#b#b#b#b#b#b#b#b#0#b#b#b#b#b#b#b#b#b#b#0#");return;}
		boolean split =false;
		for (int i=0;i<5;i++) {
			if (m1[i] == null) m1[i] = new CardmasterServerCard();
			if (m2[i] == null) m2[i] = new CardmasterServerCard();
			if (e1[i] == null) e1[i] = new CardmasterServerCard();
			if (e2[i] == null) e2[i] = new CardmasterServerCard();
			if (m1[i].GetStatusValue("facedown")>=1) split = true;
			if (m2[i].GetStatusValue("facedown")>=1) split = true;
			if (e1[i].GetStatusValue("facedown")>=1) split = true;
			if (e2[i].GetStatusValue("facedown")>=1) split = true;
			
		}
		if (!split) {
			message(STCNEW(0));
		}
		else {
//			System.out.println("SPLIT!");
			message(STCNEW(1),name1);
			message(STCNEW(2),name2);
			message(STCNEW(-1),"$obs$");
		}
		MessageMod();
		
	}

	public String STCNEW(int player) {
		if (deck1 == null || deck2 == null) return "STC#b#b#b#b#b#b#b#b#b#b#0#b#b#b#b#b#b#b#b#b#b#0#";
		
		try {
			String returnstr = "STC#" 
							+modifiedValueCard(m1[0],player)+"#"
							+modifiedValueCard(m1[1],player)+"#"
							+modifiedValueCard(m1[2],player)+"#"
							+modifiedValueCard(m1[3],player)+"#"
							+modifiedValueCard(m1[4],player)+"#"
						  +modifiedValueCard(e1[0],player)+"#"
						  +modifiedValueCard(e1[1],player)+"#"
						  +modifiedValueCard(e1[2],player)+"#"
						  +modifiedValueCard(e1[3],player)+"#"
						  +modifiedValueCard(e1[4],player)+"#"
						  +deck1.deckcards + "#"
						  +modifiedValueCard(m2[0],player)+"#"
						  +modifiedValueCard(m2[1],player)+"#"
						  +modifiedValueCard(m2[2],player)+"#"
						  +modifiedValueCard(m2[3],player)+"#"
						  +modifiedValueCard(m2[4],player)+"#"
						  +modifiedValueCard(e2[0],player)+"#"
						  +modifiedValueCard(e2[1],player)+"#"
						  +modifiedValueCard(e2[2],player)+"#"
						  +modifiedValueCard(e2[3],player)+"#"
						  +modifiedValueCard(e2[4],player)+"#"
						  +deck2.deckcards + "#";
						  //System.out.println(returnstr);
				return (returnstr
						  
						  );
			
		}
		catch (NullPointerException e) {
			e.printStackTrace();
			return ("STC#b#b#b#b#b#b#b#b#b#b#0#b#b#b#b#b#b#b#b#b#b#0#");
			
		}
		
		
	}
	
	
	public String getName() {
		return messagebuffername;	
		
	}
	
	

	//Close Room
	public void close() {
		message("CLO#");
		name1 = "";
		name2 = "";
		name2wait = "";
		dead = true;
		
				System.out.println("CLOSED");
		
		
		
	}
	public void drawCardInc(int player) {
		
		if (player == 1) drawcard1++;
		if (player == 2) drawcard2++;
	//	System.out.println("Drawcard1: " +drawcard1);
	//	System.out.println("Drawcard2: " +drawcard2);
		
		
		
	}
	public void mill(int player, int amount) {
		CardmasterLibrary deck = null;
		if (player == 1) deck = deck1;
		if (player == 2) deck = deck2;
		if (deck == null) return;
		for (int i=0;i<amount;i++) {
			int card = deck.drawCard();
			if (card ==0) { message("MES#DEBUG: DRAW 0 ATTEMPTED#");}
			if (card == -1) {
			if (!endedonce && winner == 0) 	duelendedbytype = "Card lost from deck";
				endduel(not_player(player));
				break;
			}
			CardmasterServerCard newcard = null;
			if (player == 1) {
			if (!endedonce && winner == 0) 	duelendedbytype = "Card lost from deck";
				tempp1card = new CardmasterServerCard();
				newcard = tempp1card;	
			}
			else {
			if (!endedonce && winner == 0) 	duelendedbytype = "Card lost from deck";
				 tempp2card = new CardmasterServerCard();
				 newcard = tempp2card;	
				}
			if (carddata[card] == null) return;
			if (carddata[card].dummy) return;
			newcard.rand = random.nextInt(10000);
			newcard.copydata(carddata[card]);
				message("MES#" + player_named(player) + " loses " + newcard.name + " to the Graveyard#");	
			to_graveyard(newcard);
		}
	}
	
	public void drawCardOpp(int player) {
		if (player ==1) drawcardopp1++;
		if (player ==2) drawcardopp2++;
		
	}
	// Chat Message
	public int say(String name, String message) {
	//	System.out.println("Received message from " + name + " : " + message);
	// CHA#" + playerroom.getName() + "#" + outputLine + "#
	
	
	
		if (message.startsWith("/me ")) {
			message("CHA#ACTION#" + name + " " + message.substring(4));
			
			
		}else if (message.startsWith("/surrender") && (name.equals(name1) || name.equals(name2))) {
			message("MES#" + name + " has surrendered.#");	
		if (!endedonce && winner == 0) 	duelendedbycard = "None";
		if (!endedonce && winner == 0) 	duelendedbytype = "Surrender";
			endduel(not_player(player_named(name)));	
		}else if (message.startsWith("/attackcancel") && (name.equals(name1) || name.equals(name2))) {
			
			
			cancelattack(player_named(name));

	}else if (message.startsWith("/magicforall") && (name.equals("webrunner"))) {
			p1D += 25;
			p1G += 25;
			p1L += 25;
			p2D += 25;
			p2G += 25;
			p2L += 25;
			message("MES#DEBUG MANA!#");
	}else if (message.startsWith("/undizzyall") && (name.equals("webrunner"))) {
			undizzy(1);
			undizzy(2);
			message("MES#ALL UNDIZZY!#");

	}else if (message.startsWith("/random") && (name.equals("webrunner"))) {
			
			message("MES#" + random.nextInt(10) + "#");
	}else if (message.startsWith("/cardforme") && (name.equals("webrunner"))) {
			draw_card(player_named(name));
			message("MES#DEBUG DRAW!#");
	}else if (message.startsWith("/cardforyou") && (name.equals("webrunner"))) {
			draw_card(not_player(player_named(name)));
			message("MES#DEBUG DRAW!#");
					
		}else if (message.startsWith("/invisible") && (name.equals(name1) || name.equals(name2))) {
			message("CHA#SYSTEM#" + name + " wishes to make the game invisible.#");
			if (player_named(name) == 1) { p1invisible = true; }
			if (player_named(name) == 2) { p2invisible = true; }
			if (p1invisible && p2invisible) { 
				invisible = true;
				message("MES#The game is now invisible#");
			}
			
			
		}else if (message.startsWith("/visible") && (name.equals(name1) || name.equals(name2))) {
			message("CHA#SYSTEM#" + name + " wishes to make the game visible.#");
			if (player_named(name) == 1) { p1invisible = false; }
			if (player_named(name) == 2) { p2invisible = false; }
			if (!p1invisible && !p2invisible) { 
				invisible = false;
				message("MES#The game is now visible#");
			}				
			
		}
		
		else {message("CHA#" + name + "#" + message + "#"); }
		
	//	System.out.println("Message Number " + messageid);
		return 1;
	}

	// Opponent Found
	public void opfound(int roomnumber) {
		if (!gamebegun) {
		
		this.roomnumber =roomnumber;
/*	try{
			FileWriter writer = new FileWriter(CardmasterData.DIRECTORY + "games.csc", true); // append
			PrintWriter out = new PrintWriter(writer);
			out.print(System.getProperty("line.separator") + roomnumber + "#" + name1 + "#" + name2 + "#");
	
			out.close();
			writer.close();
		}catch(Exception e){}*/
		message("OPF#" + name1 + "#");
		message("OPF#" + name2 + "#");
		message("MES#Cardmaster Conflict version " + version);
		init_game();
		}
		
	}
	

	
	
	 class AbilityTarget { // data implementing a target
		public boolean isCard = false;
		public boolean isPlayer = false;
		public boolean isGrave = false;
		public int p;
		public int s;
		public String t;
		AbilityTarget(int player, int slot, String type) { // target is a card
			isCard = true;
			p = player;
			s = slot;
			t = type;
			
			
		} 
		AbilityTarget(int player, int cardid) {
			p = player;
			isGrave = true;
			s = cardid;
			
			
		}
		AbilityTarget(int player) { // target is a player
			isPlayer = true;
			p = player;
			
		}
		public CardmasterServerCard getCard() {
			if (!isCard) return null;
			else return getSlot(p,s,t);
			
		}
		
		public int getPlayer() {
			if (!isPlayer) return 0;
			else return p;
			
			
			
		}
		
		
		
		
		
		
		
	}
	
	boolean isActivatedConstantCard(CardmasterServerCard checkcard,String constant, int player, CardmasterServerCard card) {
		if (checkcard == null) return false;
		if (checkcard.dummy) return false;
		if (Spiked(checkcard)) return false;
		String checkstring = ability_afterattack[checkcard.cardid];
				if (checkstring == null) return false;
	//	System.out.println("Checking checkstring..." + checkstring);
		if (! checkstring.startsWith("CONSS")) {
			
			checkstring = ability_defend[checkcard.cardid];
				if (! checkstring.startsWith("CONSS")) { return false; }
			
		}
		
		StringTokenizer tokenizer = new StringTokenizer(checkstring, ";");
		tokenizer.nextToken();
		if (constant == null) return false;
		
		while (tokenizer.hasMoreTokens()) {
			String command = tokenizer.nextToken();
			StringTokenizer cmdToken = new StringTokenizer(command,"^");
			String cmd = cmdToken.nextToken();
			//System.out.println("checking for CMD " + cmd + " = findable " + constant);
			if (! constant.equals(cmd)) continue;
			if (cmd.startsWith("P")) {
				String play = cmdToken.nextToken();
				
				
				
				if (play.equals("opnt")) {
					if (owner(checkcard) == player) continue;	
				}
				if (play.equals("ownr")) {
					if (owner(checkcard) != player) continue;	
					
				}
				
				
				
				if (cmd.equals("PMODD")) {
		
					tempcount2 = tempcount2 * Double.parseDouble(cmdToken.nextToken());
			
				}
				
			
				
				
				return true;
				
			}

			if (cmd.startsWith("M")) {
				if (card == null) continue;
				String a = cmdToken.nextToken();
				String b = cmdToken.nextToken();
				String c = cmdToken.nextToken();
				String d = cmdToken.nextToken();
				
				
			
				
				
				
				if (checkcard == null) return false;
				if (card == null) return false;
				if (b == null) return false;
				if (b.startsWith("s") && ( owner(checkcard) != owner(card) )) return false;
				if (b.startsWith("o") && ( owner(checkcard) == owner(card) )) return false;
				if (card == null) return false;
				if (card.typecode == null) return false;				
				if (b.endsWith("m") && !card.typecode.equals("m")) return false;
				if (b.endsWith("e") && !card.typecode.equals("e")) return false;
				
				
			//		System.out.println(cmd + ": a:" + a +" b:"+ b +" c:"+ c +" d:"+ d + " tc1:" + card.tempcount1);
					
			//	System.out.println("Againsnt c:"+ card.mtype +" d:"+ card.name + " test: " + tempcount1);
					
				if (matchcard(a, c, d, card, checkcard, null)) {
					if (cmd.equals("MDAMD")) {
						
						card.tempcount1 = card.tempcount1 * Double.parseDouble(cmdToken.nextToken());
			
					
					}
					
					
					if (cmd.equals("M2ndL")) {
						card.tempcount1 = Integer.parseInt(cmdToken.nextToken());
						card.tempcount2 = Integer.parseInt(cmdToken.nextToken()); // used "all second life" cost
						
					
					}
				
					
					
					return true;
				 }
				 else {
				 	if (cmd.equals("MDAMD")) {
				//	System.out.println("MDAMD: NOT MATCHED");
					
				}
				 	
				 	}
			}
			
			
			
		}
		
		return false;
		
		
	}
	
	// victory stuff.
	//CardmasterUser[] users;
	boolean isActivatedConstant(String constant, int player, CardmasterServerCard card) {
		tempcount1 = 1;
		tempcount2 = 1;
		boolean returnv = false;
		for (int i = 0; i<5; i++) {
			if (!m1[i].dummy && isActivatedConstantCard(m1[i], constant, player,  card)) returnv= true;
			if (!m2[i].dummy && isActivatedConstantCard(m2[i], constant, player, card)) returnv= true;
			if (!e1[i].dummy && isActivatedConstantCard(e1[i], constant,  player, card)) returnv= true;
			if (!e2[i].dummy && isActivatedConstantCard(e2[i],  constant,  player, card)) returnv= true;
			
			if (!dm1[i].dummy && isActivatedConstantCard(dm1[i], constant, player,  card)) returnv= true;
			if (!dm2[i].dummy && isActivatedConstantCard(dm2[i], constant, player, card)) returnv= true;
			if (!de1[i].dummy && isActivatedConstantCard(de1[i], constant,  player, card)) returnv= true;
			if (!de2[i].dummy && isActivatedConstantCard(de2[i],  constant,  player, card)) returnv= true;
			
			
			
		}
		if (isActivatedConstantCard(location,  constant,  player, card)) return true;

		return returnv;
		
		
	}
	double getModifiedOverflowValue(int player, double value) { // this is just for OVER
		for (int i =0;i<5;i++) {
			if (!m1[i].dummy) value = runConstAbility(player,m1[i],value, "ovr", "A");
			if (!e1[i].dummy) value = runConstAbility(player,e1[i],value, "ovr", "A");
			if (!m2[i].dummy) value = runConstAbility(player,m2[i],value, "ovr", "A");
			if (!e2[i].dummy) value = runConstAbility(player,e2[i],value, "ovr", "A");
			
			
			if (!dm1[i].dummy) value = runConstAbility(player,dm1[i],value, "ovr", "A");
			if (!de1[i].dummy) value = runConstAbility(player,de1[i],value, "ovr", "A");
			if (!dm2[i].dummy) value = runConstAbility(player,dm2[i],value, "ovr", "A");
			if (!de2[i].dummy) value = runConstAbility(player,de2[i],value, "ovr", "A");
			
		}
		value = runConstAbility(player,location,value, "ovr", "A");
		return (int)value;
		
		
	}

	
	
	
	double getModifiedValue(double value, String valuename, String time, int owner) {
		
		
		CardmasterServerCard card = new CardmasterServerCard();
		for (int i =0;i<5;i++) {
			if (!m1[i].dummy) value = runConstAbility(card,m1[i],value, valuename, time, owner);
			if (!e1[i].dummy) value = runConstAbility(card,e1[i],value, valuename, time, owner);
			if (!m2[i].dummy) value = runConstAbility(card,m2[i],value, valuename, time, owner);
			if (!e2[i].dummy) value = runConstAbility(card,e2[i],value, valuename, time, owner);
			if (!dm1[i].dummy) value = runConstAbility(card,dm1[i],value, valuename, time, owner);
			if (!de1[i].dummy) value = runConstAbility(card,de1[i],value, valuename, time, owner);
			if (!dm2[i].dummy) value = runConstAbility(card,dm2[i],value, valuename, time, owner);
			if (!de2[i].dummy) value = runConstAbility(card,de2[i],value, valuename, time, owner);
						
		}
		value = runConstAbility(card,location,value, valuename, time, owner);
		return value;
		
	}	
	
	double getModifiedValue(CardmasterServerCard card, String valuename, String time, int owner) {
	//	System.out.println("La la la...");
		double value = 0;
		
		boolean cake = false;
		if (card == null) return 0;
		if (card.typecode == null) return 0;
		
		if (card.typecode.equals("m")) {
			if (card.GetStatusValue("year2") == 1) cake = true;
		}
		
		if (valuename.equals("atk")) {
		
			value = card.attack + card.attackmod;
			
			if (GetModifierCard(card) != null) {
				if (!GetModifierCard(card).dummy) {
					value += GetModifierCard(card).attack;
				}
				
			}
			
			
			if (cake) {
				value += numCardsInHand(owner(card));
				value -= numCardsInHand(not_player(owner(card)));
			}
			
			
			if (card == null) value = 0;
			else if (card.typecode == null) value = 0;
			if (card.typecode.equals("m") && value <= 0) value = 0;
			
			
			
			
			
			}
		else if (valuename.equals("lif")) {
			value = card.lifepoints + card.lifemod;
				
			}
		else if (valuename.equals("lco")) value = card.Lcost;
		else if (valuename.equals("dco")) value = card.Dcost;
		else if (valuename.equals("gco")) value = card.Gcost;
		else if (valuename.equals("lsa")) value = card.Lsac;
		else if (valuename.equals("dsa")) value = card.Dsac;
		else if (valuename.equals("gsa")) value = card.Gsac;
		else if (valuename.equals("spd")) value = card.speed;
		
		else return 0;
		
		for (int i =0;i<5;i++) {
			if (!m1[i].dummy) value = runConstAbility(card,m1[i],value, valuename, time, owner);
			if (!e1[i].dummy) value = runConstAbility(card,e1[i],value, valuename, time, owner);
			if (!m2[i].dummy) value = runConstAbility(card,m2[i],value, valuename, time, owner);
			if (!e2[i].dummy) value = runConstAbility(card,e2[i],value, valuename, time, owner);
			
		}
		value = runConstAbility(card,location,value, valuename, time, owner);
		return value;
		
	}

	int tempcount;
	double runConstAbility(int player, CardmasterServerCard runcard, double value, String valname, String time) {
		if (runcard.dummy) return value;
		int cardid = runcard.cardid;
		String commandstring;
		if (ability_defend[cardid].startsWith("CONSP")) commandstring = ability_defend[cardid];
		else if (ability_afterattack[cardid].startsWith("CONSP")) commandstring = ability_afterattack[cardid];
		else return value;
		if (Spiked(runcard)) return value;
		if (runcard.typecode.equals("d")) runcard = GetModifierParent(runcard);
		if (runcard == null) return value;
		
		StringTokenizer tokenizer = new StringTokenizer(commandstring, ";");
		tokenizer.nextToken();
		while (tokenizer.hasMoreTokens()) {
			
			String command = tokenizer.nextToken();
			StringTokenizer ctokenizer = new StringTokenizer(command,"^");
			String ccode = ctokenizer.nextToken();
			if (! ccode.startsWith(valname)) continue;
			if (! (ccode.endsWith(time) || ccode.endsWith("A"))) continue;
			String players = ctokenizer.nextToken();
			if ((players.equals("ownr")) && (player != owner(runcard))) continue;
			if ((players.equals("opnt")) && (player == owner(runcard))) continue;
			
	//		String a = ctokenizer.nextToken();
	//		String b = ctokenizer.nextToken();
	//		String c = ctokenizer.nextToken();
	//		String d = ctokenizer.nextToken();
			double val1 = Double.parseDouble(ctokenizer.nextToken());
			double val2 = Double.parseDouble(ctokenizer.nextToken());
				
		//	if (
		//	if (! (b.endsWith("b") || b.endsWith(card.typecode))) continue;
		//	if (b.startsWith("o") && owner(card) == owner(runcard)) continue;
		//	if (b.startsWith("s") && owner(card) != owner(runcard)) continue;
		//	if (! matchcard(a,c,d,card,runcard,null)) continue;
		
			value = value * val1;
			value = value + val2;
			if (value <= 0) value = 0;
	
		}	
		return value;	
	}
	
	double runConstAbility(CardmasterServerCard card, CardmasterServerCard runcard, double value, String valname, String time, int owner) {
		if (card == null) return value;
		if (runcard.dummy) return value;
		int cardid = runcard.cardid;
		String commandstring; 
		if (Spiked(runcard)) return value;
		if (ability_defend[cardid].startsWith("CONSC")) commandstring = ability_defend[cardid];
		else if (ability_afterattack[cardid].startsWith("CONSC")) commandstring = ability_afterattack[cardid];
		else return value;
		if (runcard.typecode.equals("d")) runcard = GetModifierParent(runcard);
		if (runcard == null) return value;
		StringTokenizer tokenizer = new StringTokenizer(commandstring, ";");
		tokenizer.nextToken();
		while (tokenizer.hasMoreTokens()) {
			
			
			
			String command = tokenizer.nextToken();
			
			
			
			
		
			StringTokenizer ctokenizer = new StringTokenizer(command,"^");
			String ccode = ctokenizer.nextToken();
			
			if (ccode.equals("match")) {
				String a = ctokenizer.nextToken();
				String b = ctokenizer.nextToken();
				String c = ctokenizer.nextToken();
				String d = ctokenizer.nextToken();
				if (! matchcard(a,c,d,card,runcard,null)) return value; // MATCH ENDS THE CHECK IMMEDIATELY
				
				
				
			}
			
			if (ccode.equals("cmp>=")) {
				
				int val1 = getvalue(ctokenizer.nextToken(),0,owner(runcard),new AbilityTarget(owner(card),findSlotNumber(card),card.typecode),runcard);
				int val2 = getvalue(ctokenizer.nextToken(),0,owner(runcard),new AbilityTarget(owner(card),findSlotNumber(card),card.typecode),runcard);
				if (val1 < val2) return value;
				
				
			}
			
			if (! ccode.startsWith(valname)) continue;
			if (! (ccode.endsWith(time) || ccode.endsWith("A"))) continue;
			String a = ctokenizer.nextToken();
			String b = ctokenizer.nextToken();
			String c = ctokenizer.nextToken();
			String d = ctokenizer.nextToken();
			
			
				if (! (b.endsWith("b") || b.endsWith(card.typecode))) continue;
			if (b.startsWith("o") && owner == owner(runcard)) continue;
			if (b.startsWith("s") && owner != owner(runcard)) continue;
			if (! matchcard(a,c,d,card,runcard,null)) continue;
			
			
			double val1 = 0;
			String val1s = ctokenizer.nextToken();
			try{
			
				val1 = Double.parseDouble(val1s);
			
			} catch(NumberFormatException e) {
				
				val1 = getvalue(val1s,0,owner(runcard),new AbilityTarget(owner(card),findSlotNumber(card),card.typecode),runcard);
				
				
			}
		//	double val2 = Double.parseDouble(ctokenizer.nextToken());
		
			
			
			int val2 = getvalue(ctokenizer.nextToken(),0,owner(runcard),new AbilityTarget(owner(card),findSlotNumber(card),card.typecode),runcard);
			
			value = value * val1;
			value = value + val2;
			if (value <= 0) value = 0;
	
		}	
		return value;	
	}



	public CardmasterUser loadUser(String name) {
		return CardmasterData.loadUser(name);
		
		
	}	
	public int canRun(AbilitySpell spell) {
		return  runability(spell.abilitycode, spell.player, spell.target, spell.self, spell.targetslot, false);
		
	}
	

	
	public int runit(AbilitySpell spell) {
		
		if (spell.ability && spell.self == null) return 0;
		if (spell.ability && spell.self.dummy) return 0;
		if (carddata[spell.cardid].name != null && !endedonce && winner == 0) {
			if (spell.ability) duelendedbycard = "ability of " + carddata[spell.cardid].name;
			else duelendedbycard = "the spell " + carddata[spell.cardid].name;
		}
		int val = runability(spell.abilitycode, spell.player, spell.target, spell.self, spell.targetslot, true);
		if (waitcards1 > numCardsInHand(1)) waitcards1 = numCardsInHand(1);
		if (waitcards2 > numCardsInHand(2)) waitcards2 = numCardsInHand(2);
		 message("MDN#2#" + waitcards2 + "#",player_named(2));
		 message("MDN#1#" + waitcards1 + "#",player_named(1));
		return val;
	}	
	
	
	class AbilitySpell implements Comparable{
		public boolean ability = true;
		public int speed;
		public String abilitycode;
		public int player;
		public AbilityTarget target;
		public CardmasterServerCard self;
		public int targetslot;
		public String targettype; 
		public int tplayer;
		public int spellid;
		public int handslot;
		public int cardid;
		public int oldtargetid;
		public AbilitySpell() {
			speed = 0;
		}
		
		public int updateSpeed() {
			if(ability) {
				speed = self.speed;
				return speed;
				
			}
			
			else return speed;
			
		}
	
		public int compareTo(Object o) {
			AbilitySpell spell =(AbilitySpell)o;
			if (CardmasterData.DEBUGMODE) System.out.println("Comparing " + self.name + " and " + spell.self.name);
			if (CardmasterData.DEBUGMODE)  System.out.println("Comparing " + self.speed + " and " + spell.self.speed);
			if (self.speed < spell.self.speed) return -1;
			if (self.speed == spell.self.speed) {
				
				if (spell.player == player) return 0;
				else if (player == playerphase) return -1;
				else if (player == not_player(playerphase)) return 1;
				return 0;
					
				
				
				
			}
			if (self.speed > spell.self.speed) return 1;
			else return 0;
			
			
		}
		
	}
	
	

}
