package webrunner.cardmaster;
import java.io.*;
public class CardmasterLogin{
	String username;
	String password;
	String errorstring;
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		//System.out.println(password);
		this.password = password;	
		
	}
	//CardmasterUser users[];
	CardmasterUser user;

	public CardmasterLogin(){}

	public boolean Login() {
		//loadUserData();
		errorstring = "";
			user = CardmasterData.loadUser(username);
			System.out.println("Looking for user..");
			if (user == null) return false;
			System.out.println("User Found!");
			if (user.readExtraA("banned") != null) 
				if (user.readExtraA("banned").equals("yes")) {
					errorstring = "You have been banned";
					return false;
				}
			String passcheck = user.password;
			if (user.encryptedpassword) passcheck = user.decryptedPassword();
			//System.out.println(username + " " + user.name + " " + password + " " + passcheck);
			boolean correct = false;
			
			if (user != null && user.password.equals("BYTEPASSWORD") && user.bytepassword != null) {
				correct = CardmasterData.checkPassword(user.name,password);
				System.out.println("Encrypted password!");
				
			}
			else if (user != null && password.equals(passcheck)) {
				correct = true;
			}	
	 	
	
			
			if (correct) {
				if (user.emailverified) {
					System.out.println(username + " logged in.");
					
					CardmasterData.userpatch(username, "logn", 0, "x");
					
					if (!user.password.equals("BYTEPASSWORD")) CardmasterData.userpatch(username, "encp",0,"x");
					
					
				return true; }
				else {
					System.out.println("Verification Error");
					
					
					
					return false; }
					
			}
			if ( ! user.name.equals(username) ) System.out.println("Name not found");
			if ( ! passcheck.equals(password) ) System.out.println("Password not found");
					
		
		return false;
		
	}	
	
	
	
	
	
}