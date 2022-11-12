package webrunner.cardmaster;
import java.io.*;
import java.util.Random;


public class CardmasterUserFactory {
	public String username;
	String password;
	String decknumber;
	public String email;
	public int regcode;	
	CardmasterUser users[];
	CardmasterUser user;
	public CardmasterUserFactory(){}
	
	public void setEmail(String email) {
		this.email = email;	
		
	}
	public void setUsername(String name) {
		username = name;	
		
	}
	public void setPassword(String pass) {
		//System.out.println("Setting password to " + pass);	
		password = pass;
	}
	public void setDeckNumber(String number) {
		decknumber = number;	
		
	}
	public boolean createUserFromData() {
//		loadUserData();	
	if (CardmasterData.DEBUGMODE ) System.out.println("Create User...");
	
		if (decknumber.equals("-1")) return false;
		
		
	if (CardmasterData.DEBUGMODE ) System.out.println("deck fine...");
	
	
		if (userAlreadyExists(username)) return false;
	
	
	if (CardmasterData.DEBUGMODE ) System.out.println("user finer...");
	
		if (emailAlreadyExists(email)) return false;
		
		
	if (CardmasterData.DEBUGMODE ) System.out.println("email fine...");
	
		if (!validName(username)) return false;
		
		
	if (CardmasterData.DEBUGMODE ) System.out.println("name fine...");
	
		user = new CardmasterUser(username,password);
		if (!user.addDeck(Integer.parseInt(decknumber))) return false;
		
		user.password = password;
	if (CardmasterData.DEBUGMODE ) System.out.println("deck fine 2");
		
		if (!user.setPrimaryDeck(Integer.parseInt(decknumber))) return false;
		
		
	if (CardmasterData.DEBUGMODE ) System.out.println("deck fine 3.");
		
		user.email = email;
		genRegCode();
		user.verifcode = regcode;
		user.year = 1000;
		user.month = 10;
		
		
	if (CardmasterData.DEBUGMODE ) System.out.println("returning...");
	
		return true;
		
	}
	
	void genRegCode() {
		MersenneTwisterFast rand =new MersenneTwisterFast();
		regcode = rand.nextInt(60000);
		
	}

	
	public boolean saveUser() {
		//File waitfile = new File(CardmasterData.DIRECTORY + "wait/" + username);
		/*
		File savefile = new File(CardmasterData.DIRECTORY + "users/" + username + ".usr");
		//while (waitfile.exists()) {};
		FileWriter writer  = null;
		PrintWriter out = null;
		try {
			
			writer = new FileWriter(savefile); // append
			out = new PrintWriter(writer);
			
			
			out.println(user);
			out.close();
			writer.close();
//			waitfile.delete();
			webrunner.cardmaster.CardmasterData.SaveEmailFor(user);
			return true;
			
			
		}catch(Exception e){
			
				try{if (out != null) out.close();}catch(Exception xe) {xe.printStackTrace();}
			//		try{writer.close();}catch(Exception xe) {xe.printStackTrace();}
			try{if (writer != null)writer.close();}catch(Exception xe) {xe.printStackTrace();}
						
			
			
			return false;}
		
		
		*/
		
		if (!CardmasterDatabase.CreateDefaultUser(user)) {
			return false;
		}
		webrunner.cardmaster.CardmasterData.SaveEmailFor(user);
		return true;
		
		
	}
	public boolean emailAlreadyExists(String name) {
		
		
			/*
		String email = name;
		File emailfile = new File(CardmasterData.DIRECTORY + "email/" + email);
		if (emailfile.exists()) return true;
		else return false;
		
		*/
		
		int returnval = CardmasterDatabase.EmailExists(name);
		
		
		for (int i=0;i<5 && returnval == -1;i++) {
			returnval = CardmasterDatabase.EmailExists(name);
		}
		if (returnval == 1) return true;
		else return false;
		
		
		
		/*
		 *
		 *
			if (CardmasterData.DEBUGMODE ) System.out.println("email check start");
		CardmasterUser[] users = CardmasterData.loadUserData();
			if (CardmasterData.DEBUGMODE ) System.out.println("users loaded");
		for (int i = 1;i<users.length;i++) {
		//System.out.println(name + " against " + users[i].name);
			if (users[i].email.equals(name)) {
					if (CardmasterData.DEBUGMODE ) System.out.println("found email");
			//	//System.out.println("THE SAME");
				return true;
			}
		}
			if (CardmasterData.DEBUGMODE ) System.out.println("no email found, continuing...");
			
			*/
		
		
	}	
	public boolean userAlreadyExists(String name) {

		if (CardmasterData.loadUser(name) == null) return false;
		else return true;
		
	}
	public boolean validName(String name) {
		if (name.indexOf("#") != -1) return false;
		if (name.indexOf("^") != -1) return false;
		if (name.indexOf(":") != -1) return false;
		if (name.indexOf(".") != -1) return false;
		
		return true;	
		
		
	}
	
	public void deleteFile() {
		File file = new File(CardmasterData.DIRECTORY + "decks/deck_" + decknumber + ".cmc");
		if (file.exists()) file.delete();
			
		
	}

	

}