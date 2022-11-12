package webrunner.cardmaster;
import java.io.*;
import java.util.StringTokenizer;
public class ShowCardDataBean {
		
	
		CardmasterServerCard carddata[];
	public void loadCardData() {
		carddata = CardmasterData.loadCardData();
		
		
	}
	
public 	ShowCardDataBean(){}
	
	public int getCommonness(int i) {
		int cardid = i;
		if (!carddata[cardid].dummy) return (carddata[cardid].printed);
		else return 0;
	}
	public CardmasterServerCard getCard(int i) {
		int cardid = i;
		if (carddata[cardid] == null) return null;
		if (!carddata[cardid].dummy) return (carddata[cardid]);
		else return null;		
		
	}

	public int getRarity(int i) {
		int common = getCommonness(i);
		int rarity = 101 - common;
		return rarity;		
	}
	public int getBuyCost(int i) {
		return 	CardmasterData.calcBuy(getCommonness(i));	
	}
	public int getSellCost(int i) {
		return getBuyCost(i)/5;	
		
	}
	public String getCardName(int i) {
	//	//System.out.println("Getting" + i);
		int cardid = i;
		if (!carddata[cardid].dummy) return (carddata[cardid].name);
		else return ("Dummy Card");
		
		
	}
	
	public String getImage(int i) {
		int cardid = i;
		if (!carddata[cardid].dummy) return ("cardpics/" + carddata[cardid].picture);	
		else return ("x");
		
	}
	

	public String getManaCost(int i) {
		int cardid = i;
			if (!carddata[cardid].dummy) {
				String manastring = "";
				if (carddata[cardid].Dcost >0) manastring = manastring + "<img src=images/dmana.gif>" + (carddata[cardid].Dcost) ;
				if (carddata[cardid].Gcost >0) manastring = manastring + "<img src=images/gmana.gif>" + (carddata[cardid].Gcost) ;
				if (carddata[cardid].Lcost >0) manastring = manastring + "<img src=images/lmana.gif>" + (carddata[cardid].Lcost); 
				if (manastring.equals("")) manastring = "Zero";
				return manastring;
				
			}	
				
				
		else return("Zero");	
	}
	public String getSacVal(int i) {
		int cardid = i;
			if (!carddata[cardid].dummy) {
				String manastring = "";
				if (carddata[cardid].Dsac >0) manastring = manastring + "<img src=images/dmana.gif>" + (carddata[cardid].Dsac) ;
				if (carddata[cardid].Gsac >0) manastring = manastring + "<img src=images/gmana.gif>" + (carddata[cardid].Gsac) ;
				if (carddata[cardid].Lsac >0) manastring = manastring + "<img src=images/lmana.gif>" + (carddata[cardid].Lsac) ;
				if (manastring.equals("")) manastring = "Zero";
				return manastring;
				
			}	
				
				
		else return "Zero" ;	
	}	
	
	
	
	public String getCardText(int i) {
		int cardid = i;
		
		if (!carddata[cardid].dummy) {
			StringTokenizer tokenizer = new StringTokenizer(carddata[cardid].cardtext,"$");
			int numpads = 5-tokenizer.countTokens();
			String cardtext = tokenizer.nextToken();
			while (tokenizer.hasMoreTokens()) {
				cardtext = cardtext + "<br>" + tokenizer.nextToken();	
				
			}
			for (int j=0;j<numpads;j++) {
				cardtext = cardtext + "<br>";	
				
				
			}
			
			cardtext = cardtext.replaceAll("\\(D\\)","<img src=images/dmana.gif>");
			cardtext = cardtext.replaceAll("\\(L\\)","<img src=images/lmana.gif>");
			cardtext = cardtext.replaceAll("\\(G\\)","<img src=images/gmana.gif>");
			cardtext = cardtext.replaceAll("\\(>\\)","<img src=images/abilitypointer.gif>");
			cardtext = cardtext.replaceAll("\\(Z\\)","<img src=images/abilitydizzy.gif>");
			
			return cardtext;
			
		}
		return "";
	}	
	public String getBackColor(int i) {
	//	//System.out.println(i);
		int cardid = i;
		if (!carddata[cardid].dummy) {
			if (carddata[cardid].colorcode == 2) return "#7C015A";
			if (carddata[cardid].colorcode == 4) return "white";
			if (carddata[cardid].colorcode == 8) return "#D4D4D4";
			if (carddata[cardid].colorcode == 2 + 4) return "black";
			if (carddata[cardid].colorcode == 2 + 8) return "#333333";
			if (carddata[cardid].colorcode == 4 + 8) return "#DDDDDD";
			if (carddata[cardid].colorcode == 2 + 4 + 8) return "#FEBF49";
		}	
		
		return "black";
	}	
	public String getTextColor(int i) {	int cardid = i;
		if (!carddata[cardid].dummy) {
			if (carddata[cardid].colorcode == 2) return "orange";
			if (carddata[cardid].colorcode == 4) return "black";
			if (carddata[cardid].colorcode == 8) return "#444444";
			if (carddata[cardid].colorcode == 2 + 4) return "red";
			if (carddata[cardid].colorcode == 2 + 8) return "#DDDDDD";
			if (carddata[cardid].colorcode == 4 + 8) return "black";
			if (carddata[cardid].colorcode == 2 + 4 + 8) return "black";
			
				
				
		}	
		
		return "black";
	}	

	
}