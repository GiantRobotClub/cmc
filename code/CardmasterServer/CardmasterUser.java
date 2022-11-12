package webrunner.cardmaster;
import java.util.StringTokenizer;
import java.util.Calendar;
public class CardmasterUser {
	public int primarydeck;
	public int[] decks; // implement later.
	public String name;
	public String password;
	public int points;
	public int wins;
	public int losses;
	public boolean encryptedpassword = false;
	public boolean emailverified =false;
	public int verifcode;
	public String email;
	public int year;
	public int day = 1;
	public int month;// LAST LOGGED IN
	public byte[] bytepassword;
	public ExtraData[] ed;
	
	
	
	
	
	public boolean equals(CardmasterUser user) {
		if (user.name.equals(name)) return true;
		else return false;	
		
		
	}
	public String decryptedPassword() {
		if (!encryptedpassword) return password;
		char[] depass = new char[password.length()];
	 	password.getChars(0,password.length(),depass,0);
		int displacement = ((int)(name.charAt(0)) - (int)' ') / 20 -1;
		int skipno = (int)':';
		
		for (int i=0;i<depass.length;i++) {
			char c = depass[i];
			depass[i] = (char)((int)c - displacement);
			
			if ((int)(depass[i]) > skipno) {
				depass[i]--;
				
			}
		//	//System.out.println("was " + c + " now + " + depass[i] + " val " + (int)depass[i]);
		
		
		
		}	
		return new String(depass);
		
	}
	public void encryptPassword() {
		//System.out.println("Encrypt Password Function: " + password + "/" + name);
		if (password.indexOf((char)123)>=0) return;
		if (password.indexOf((char)124)>=0) return;
		if (password.indexOf((char)125)>=0) return;
		if (password.indexOf((char)126)>=0) return;
		if (password.indexOf((char)127)>=0) return;
		
		if (password == null || name == null) return;
		if (encryptedpassword) return;
		int displacement = ((int)(name.charAt(0)) - (int)' ') / 20 -1;
		int skipno = (int)':'; // skip : code.
		char[] newpassword = new char[password.length()];
		password.getChars(0,password.length(),newpassword,0);
		for (int i=0;i<newpassword.length;i++) {
			char c = newpassword[i];
			newpassword[i] = (char)((int)c + displacement);
			
			if ((int)(newpassword[i]) >= skipno) {
				newpassword[i]++;
				
			}
	//	//System.out.println("was " + c + " now " + newpassword[i] + " val " + (int)newpassword[i]);
		
		
		}
		password = new String(newpassword);
		encryptedpassword = true;
	}




	public ExtraData getED(String name) {
	//	System.out.println("ED.length: " + ed.length + " (");
		for (int i=0;i<ed.length;i++) {
		//	System.out.print(i+",");
			if (ed[i].name.equals(name)) {
	//			System.out.print(")\n");
				 return ed[i];
			}
			
		}	
	//	System.out.print(")\n");	
		return null;
		
	}

	public String readExtraA(String name) {
		ExtraData edn = getED(name);
		if (edn == null) return null;
		else return edn.dataa;
		
		
		
	}
	public String readExtraB(String name) {
		ExtraData edn = getED(name);
		if (edn == null) return null;
		else return edn.datab;
		
		
		
	}
	int createExtraData(String name, String dataa, String datab) {
		
			if (getED(name) != null) return -1;		
			ExtraData newEd = new ExtraData(name,dataa,datab);
			
			ExtraData[] edt = new ExtraData[ed.length + 1];
			System.arraycopy(ed,0,edt,0,ed.length);
			edt[ed.length] = newEd;
			ed = new ExtraData[edt.length];
			System.arraycopy(edt,0,ed,0,edt.length);		
			return 1;
		
	}
	public int changeExtraData(String name, String dataa, String datab) {
		int returnval = 0;
		ExtraData edn = getED(name);
		CardmasterLogManager.WriteLog("Setting extra data for " + this.name + " " + name+ ": [" + dataa + "][" + datab+"]");	
		
		if (edn == null) {
			
			return createExtraData(name, dataa, datab);	
			
			
		}
		else {
			
			edn.dataa = dataa;
			edn.datab = datab;	
			return 0;
		}
		
		
	}


	CardmasterUser(String data) throws NumberFormatException{
			
	//	//System.out.println(data);
		decks = new int[1];
		StringTokenizer tokenizer = new StringTokenizer(data,":");
		tokenizer.nextToken(); // name:
		name = tokenizer.nextToken(); //actual name
		tokenizer.nextToken(); // password
		password = tokenizer.nextToken(); //actual password
		tokenizer.nextToken(); // decks
		String decklist = tokenizer.nextToken(); // deck list
		StringTokenizer token = new StringTokenizer(decklist,".");
			while (token.hasMoreTokens()) {
				addDeck(Integer.parseInt(token.nextToken()));		
				
			}
		
		
		
		tokenizer.nextToken(); // primary	
		primarydeck = Integer.parseInt(tokenizer.nextToken());		
		tokenizer.nextToken(); // points
		points = Integer.parseInt(tokenizer.nextToken());
		////System.out.println(name + " has points " + points);
		tokenizer.nextToken(); // wins
		wins = Integer.parseInt(tokenizer.nextToken());
		tokenizer.nextToken(); // losses
		losses = Integer.parseInt(tokenizer.nextToken());
		if (tokenizer.hasMoreTokens()) {
		int val = Integer.parseInt(tokenizer.nextToken());
		if (val == 0) encryptedpassword = false;
		else encryptedpassword = true;
		}
		else encryptedpassword = false;
		
		if (tokenizer.hasMoreTokens()) {
			int val = Integer.parseInt(tokenizer.nextToken());
			if (val ==0 ) emailverified = false;
			else emailverified = true;	
			
			
		}
		else emailverified = true;
		
		if (tokenizer.hasMoreTokens()) {
			email = tokenizer.nextToken();
			
		}
		else email = "signed@up.early";
		
		if (tokenizer.hasMoreTokens()) {
			verifcode = Integer.parseInt(tokenizer.nextToken());
				
			
		}
		else verifcode = 0;
		Calendar rightNow = Calendar.getInstance();
		month = rightNow.get(Calendar.MONTH);
		
		year = rightNow.get(Calendar.YEAR);	
		if (tokenizer.hasMoreTokens()) {
			month = Integer.parseInt(tokenizer.nextToken());
			year = Integer.parseInt(tokenizer.nextToken());
		
			
			
			
		}
		else {
			rightNow = Calendar.getInstance();
			month = rightNow.get(Calendar.MONTH);
		
			year = rightNow.get(Calendar.YEAR);	
			
			
		}
	}	
	
	CardmasterUser() {
		
	}

	
	CardmasterUser(String name, String password) {
		this.name = name;
		//this.password = password;
		this.bytepassword = CardmasterData.encrypt(password);
		password = "BYTEPASSWORD";
		wins = 0;
		losses = 0;
		points = 1000;
		password = "BYTEPASSWORD";
		decks = new int[1];
		
	}
	//name:webrunner:pass:online10:decks:1.:primary:1:
	public String toString() {
		String decklist = "";
		for (int i=1;i<decks.length;i++) {
			if (i!=1) decklist = decklist + ".";
			decklist = decklist + decks[i];	
		}
		int val = 0;
		if (encryptedpassword) val = 1;
		int verif = 0;
		if (emailverified) verif = 1;
		return ("name:" + name + ":pass:" + password + ":decks:" + decklist + ".:primary:" + primarydeck + ":points:" + points + ":wins:" +wins +":loss:" +losses + ":" + val + ":" + verif + ":" + email + ":" + verifcode + ":" + month + ":" + year + ":" );
		
		
	}
	public boolean setPrimaryDeck(int i) {
		if (hasDeck(i)) {
			primarydeck = i;
			return true;}
		else return false;	
		
	}
	public boolean addDeck(int i) {
		if (!hasDeck(i)) {
			int[] tempdeck = new int[decks.length +1];
			System.arraycopy(decks,0,tempdeck,0,decks.length);
			tempdeck[decks.length] = i;
			decks = new int[decks.length +1];
			System.arraycopy(tempdeck,0,decks,0,tempdeck.length);
			return true;
		}
		return false;
		
	}
	public boolean hasDeck(int d) {
		for (int i =1;i<decks.length;i++) {
			if (decks[i] == d) return true;
			
			}	
		return false;
		
	}
	public void removeDeck(int d) {
		int i;
		if (primarydeck == d) return;
		for (i =1;i<decks.length;i++) {
			if (decks[i] == d) {
				decks[i] = decks[decks.length-1];
				int[] tempdeck = new int[decks.length -1];
				System.arraycopy(decks,0,tempdeck,0,tempdeck.length);
				decks = new int[decks.length-1];
				System.arraycopy(tempdeck,0,decks,0,tempdeck.length);
				return;
				
			}
			
			
		}	
		
		
	}

	
	
	
	

	
	
	
}